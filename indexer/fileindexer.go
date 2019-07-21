package indexer

import (
	"os"
	"path/filepath"

	"github.com/gravetii/diztl/dir"
	"github.com/gravetii/diztl/diztl"
	"github.com/gravetii/logger"
)

// FileIndexer is the struct type that represents a file indexer on a node which indexes all the shared files.
type FileIndexer struct {
	index *TreeIndex
}

// NewFileIndexer : Returns a new instance of FileIndexer.
func NewFileIndexer() *FileIndexer {
	return &FileIndexer{index: NewTreeIndex()}
}

// Index indexes all the files in the user-configured shared folders,
// making them available for discovery by peers.
func (f *FileIndexer) Index() error {
	logger.Debugf("Started file indexing process.\n")
	if err := f.dirwalk(); err != nil {
		return err
	}

	logger.Infof("File indexing finished successfully.")
	return nil
}

func (f *FileIndexer) dirwalk() error {
	dirs, err := dir.GetShareDirs()
	if err != nil {
		return err
	}

	for _, dir := range dirs {
		if err := f.filewalk(dir); err != nil {
			return err
		}
	}

	return nil
}

// Performs a recursive file walk of the given directory path.
func (f *FileIndexer) filewalk(dir string) error {
	logger.Debugf("Performing filewalk for dir %s\n", dir)
	err := filepath.Walk(dir, func(path string, info os.FileInfo, err error) error {
		return f.add(path, info)
	})

	if err != nil {
		return err
	}

	return f.index.validate()
}

// Search searches for a given pattern in the names of the indexed files and returns files that match.
func (f *FileIndexer) Search(pattern string) []*diztl.FileMetadata {
	return f.index.search(pattern)
}

func (f *FileIndexer) add(path string, info os.FileInfo) error {
	if !info.IsDir() {
		f.index.addFile(path, info)
	}

	return nil
}

func (f *FileIndexer) remove(path string) error {
	// No need to remove path from watcher, fsnotify does it by default.
	// So, just remove the path from the index.
	err := f.index.removePath(path)
	if err != nil {
		logger.Errorf("Error while removing path from index - %s: %v\n", path, err)
	}

	return err
}
