package indexer

import (
	"fmt"
	"os"
	"path/filepath"
	"strings"

	"github.com/gravetii/diztl/conf"
	"github.com/gravetii/diztl/file"
	"github.com/pkg/errors"

	"github.com/gravetii/diztl/dir"

	"github.com/gravetii/diztl/counter"
	"github.com/gravetii/diztl/diztl"
	"github.com/gravetii/logger"
)

// TreeNode : Represents a single unit of the TreeIndex.
type TreeNode struct {
	isDir    bool
	path     string
	file     *diztl.FileMetadata
	parent   *TreeNode
	children map[string]*TreeNode
}

// TreeIndex : Represents a file-index saved as a Tree.
type TreeIndex struct {
	// A dummy root node - to support multiple shared folders.
	root    *TreeNode
	counter *counter.AtomicCounter
}

// NewTreeIndex : Creates a new instance of the TreeIndex type.
func NewTreeIndex() *TreeIndex {
	r := &TreeNode{isDir: false, path: "", children: make(map[string]*TreeNode)}
	t := &TreeIndex{root: r, counter: counter.NewAtomic(0)}
	return t
}

func (t *TreeIndex) addFile(path string, info os.FileInfo) {
	hash, err := file.Hash(path)
	if err != nil {
		logger.Errorf("Error while creating hash, not adding file %s - %v\n", path, err)
		return
	}

	tokens := dir.Split(path)
	fpath := ""
	parent := t.root
	for n, token := range tokens {
		fpath = filepath.Join(fpath, token)
		parent = t.addPath(fpath, token, parent, info, hash, n != len(tokens)-1)
	}

	logger.Debugf("Added %d. %s, %x\n", t.counter.Value(), path, hash.Checksum)
}

func (t *TreeIndex) find(path string) *diztl.FileMetadata {
	tokens := dir.Split(path)
	parent := t.root
	for _, token := range tokens {
		if node, exists := parent.children[token]; exists {
			parent = node
		} else {
			return nil
		}
	}

	return parent.file
}

func (t *TreeIndex) addPath(path string, token string, parent *TreeNode,
	info os.FileInfo, hash *diztl.FileHash, isDir bool) *TreeNode {

	var treenode TreeNode
	if node, exists := parent.children[token]; exists {
		treenode = *node
	} else {
		treenode = TreeNode{isDir: isDir, path: path, parent: parent, children: make(map[string]*TreeNode)}
		if !isDir {
			metadata := diztl.FileMetadata{Dir: filepath.Dir(path), Id: t.counter.IncrBy1(), Size: info.Size(), Name: token, Hash: hash}
			treenode.file = &metadata
		}

		parent.children[token] = &treenode
	}

	return &treenode
}

func (t *TreeIndex) validate() error {
	minFiles := conf.MinIndexFiles()
	if t.counter.Value() < minFiles {
		fmt.Printf("You need to share at least %d files to diztl before you can ask!\n", minFiles)
		return errors.New("Less than minimum number of indexed files")
	}

	return nil
}

func (t *TreeIndex) getFileList(path string) []*diztl.FileMetadata {
	tokens := dir.Split(path)
	parent := t.root
	for _, token := range tokens {
		if node, exists := parent.children[token]; exists {
			parent = node
		}
	}

	files := t.getFileListInternal(parent)
	return files
}

func (t *TreeIndex) getFileListInternal(node *TreeNode) []*diztl.FileMetadata {
	var files []*diztl.FileMetadata
	for _, n := range node.children {
		if !n.isDir {
			files = append(files, n.file)
		} else {
			files = append(files, t.getFileListInternal(n)...)
		}
	}

	return files
}

func (t *TreeIndex) search(query string, constraint *diztl.FileConstraint) []*diztl.FileMetadata {
	return traverse(t.root, query, constraint)
}

func satisfiesConstraints(file *diztl.FileMetadata, query string, constraint *diztl.FileConstraint) bool {
	path := dir.GetFilePath(file)
	if !strings.Contains(strings.ToLower(path), strings.ToLower(query)) {
		return false
	}

	ext := filepath.Ext(path)
	typeValue := constraint.GetCtype().GetType()
	if !matchesType(ext, typeValue) {
		return false
	}

	key := constraint.GetCsize().GetKey()
	value := constraint.GetCsize().GetValue()
	if key == 0 {
		if file.GetSize() < value {
			return false
		}
	} else if key == 1 {
		if file.GetSize() >= value {
			return false
		}
	}

	return true
}

func matchesType(ext string, typeValue int32) bool {
	if typeValue == 0 {
		return true
	} else if typeValue == 1 {
		return ext == ".mp4" || ext == ".mkv" || ext == ".mpeg" || ext == ".mov" || ext == ".webm" || ext == ".flv"
	} else if typeValue == 2 {
		return ext == ".png" || ext == ".jpg" || ext == ".jpeg" || ext == ".ico" || ext == ".gif"
	} else if typeValue == 3 {
		return ext == ".mp3" || ext == ".wav" || ext == ".ogg"
	} else if typeValue == 4 {
		return ext == ".txt" || ext == ".pdf" || ext == ".ppt" || ext == ".doc" || ext == ".xls" || ext == ".csv"
	} else if typeValue == 5 {
		return ext == ".zip" || ext == ".gz" || ext == ".rar" || ext == ".7z"
	} else if typeValue == 6 {
		return ext == ".exe" || ext == ".dmg" || ext == ".sh"
	}

	return true
}

func traverse(root *TreeNode, query string, constraint *diztl.FileConstraint) []*diztl.FileMetadata {
	res := []*diztl.FileMetadata{}
	for _, treenode := range root.children {
		if !treenode.isDir {
			if satisfiesConstraints(treenode.file, query, constraint) {
				res = append(res, treenode.file)
			}
		} else {
			res = append(res, traverse(treenode, query, constraint)...)
		}
	}

	return res
}
