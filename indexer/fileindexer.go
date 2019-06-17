package indexer

import (
	"os"
	"path/filepath"

	"github.com/fsnotify/fsnotify"
	"github.com/gravetii/diztl/conf"
	"github.com/gravetii/diztl/dir"
	"github.com/gravetii/diztl/diztl"
	"github.com/gravetii/logger"
)

// FileIndexer is the struct type that represents a file indexer on a node which indexes all the shared files.
type FileIndexer struct {
	index   *TreeIndex
	watcher *fsnotify.Watcher
}

// NewFileIndexer : Returns a new instance of FileIndexer.
func NewFileIndexer() (*FileIndexer, error) {
	f := FileIndexer{index: NewTreeIndex()}
	w, err := fsnotify.NewWatcher()
	if err != nil {
		logger.Errorf("Unable to establish file-system watcher - %v\n", err)
		return nil, err
	}

	f.watcher = w
	return &f, nil
}

// Index indexes all the files in the user-configured shared folders,
// making them available for discovery by peers.
func (f *FileIndexer) Index() error {
	logger.Debugf("Started file indexing process.\n")
	if err := f.dirwalk(); err != nil {
		return err
	}

	if conf.UseWatcher() {
		go f.watch()
	}

	logger.Infof("File indexing finished successfully.")
	return nil
}

// Close closes any resources held by this file indexer.
func (f *FileIndexer) Close() error {
	return f.watcher.Close()
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

func (f *FileIndexer) watch() {
	for {
		select {
		case event := <-f.watcher.Events:
			f.handleWatchEvent(event)
		case err := <-f.watcher.Errors:
			logger.Errorf("Got error from watcher: %v\n", err)
		}
	}
}

func (f *FileIndexer) handleWatchEvent(e fsnotify.Event) {
	if e.Op == fsnotify.Create {
		f.handleCreateEvent(e.Name)
	} else if e.Op == fsnotify.Remove {
		f.handleRemoveEvent(e.Name)
	} else if e.Op == fsnotify.Rename || e.Op == fsnotify.Remove {
		f.handleRemoveEvent(e.Name)
	}
}

func (f *FileIndexer) handleCreateEvent(path string) {
	info, _ := os.Stat(path)
	f.add(path, info)
}

func (f *FileIndexer) handleRemoveEvent(path string) {
	f.remove(path)
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
	if info.IsDir() {
		if conf.UseWatcher() {
			if err := f.watcher.Add(path); err != nil {
				logger.Errorf("Error while watching directory: %s - %v\n", path, err)
			}
		}
	} else {
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
