package indexer

import (
	"errors"
	"fmt"
	"log"
	"os"
	"path/filepath"
	"strings"

	"github.com/gravetii/diztl/conf"
	"github.com/gravetii/diztl/file"

	"github.com/gravetii/diztl/dir"

	"github.com/gravetii/diztl/counter"
	"github.com/gravetii/diztl/diztl"
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
		log.Printf("Error while creating hash, not adding file %s - %v\n", path, err)
		return
	}

	tokens := dir.Split(path)
	fpath := ""
	parent := t.root
	for n, token := range tokens {
		fpath = filepath.Join(fpath, token)
		parent = t.addPath(fpath, token, parent, info, hash, n != len(tokens)-1)
	}

	log.Printf("Added %d. %s, %x\n", t.counter.Value(), path, hash.Checksum)
}

func (t *TreeIndex) addPath(path string, token string, parent *TreeNode,
	info os.FileInfo, hash *diztl.FileHash, isDir bool) *TreeNode {

	var treenode TreeNode
	if node, exists := parent.children[token]; exists {
		treenode = *node
	} else {
		treenode = TreeNode{isDir: isDir, path: path, parent: parent, children: make(map[string]*TreeNode)}
		if !isDir {
			metadata := diztl.FileMetadata{Path: path, Id: t.counter.IncrBy1(), Size: info.Size(), Name: token, Hash: hash}
			treenode.file = &metadata
		}

		parent.children[token] = &treenode
	}

	return &treenode
}

func (t *TreeIndex) removePath(path string) error {
	tokens := dir.Split(path)
	fpath := ""
	node := t.root
	parent := node.parent
	for _, token := range tokens {
		fpath = filepath.Join(fpath, token)
		node = node.children[token]
		if node == nil {
			return errors.New("Invalid path to remove")
		}

		parent = node.parent
		if parent == nil {
			return errors.New("Invalid path to remove")
		}
	}

	token := tokens[len(tokens)-1]
	delete(parent.children, token)
	t.counter.DecrBy1()
	log.Printf("Removed %s\n", path)
	return nil
}

func (t *TreeIndex) validate() error {
	minFiles := conf.MinIndexFiles()
	if t.counter.Value() < minFiles {
		fmt.Printf("You need to share at least %d files to diztl before you can ask!\n", minFiles)
		return errors.New("less than minimum number of indexed files")
	}

	return nil
}

func (t *TreeIndex) search(pattern string) []*diztl.FileMetadata {
	return traverse(t.root, pattern)
}

func traverse(root *TreeNode, pattern string) []*diztl.FileMetadata {
	res := []*diztl.FileMetadata{}
	for _, treenode := range root.children {
		if !treenode.isDir {
			path := treenode.file.GetPath()
			if strings.Contains(strings.ToLower(path), strings.ToLower(pattern)) {
				res = append(res, treenode.file)
			}
		} else {
			res = append(res, traverse(treenode, pattern)...)
		}
	}

	return res
}
