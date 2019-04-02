package indexer

import (
	"log"
	"os"
	"path/filepath"

	"github.com/fsnotify/fsnotify"
	"github.com/gravetii/diztl/conf"
	"github.com/gravetii/diztl/diztl"
)

// FileIndexer : The struct type that represents a file indexer on a node which indexes all the shared files.
type FileIndexer struct {
	index   *TreeIndex
	watcher *fsnotify.Watcher
}

// NewFileIndexer : Returns a new instance of FileIndexer.
func NewFileIndexer() (*FileIndexer, error) {
	f := FileIndexer{index: NewTreeIndex()}
	w, err := fsnotify.NewWatcher()
	if err != nil {
		log.Fatalf("Unable to establish file-system watcher: %v", err)
		return nil, err
	}

	f.watcher = w
	return &f, nil
}

// Index : Indexes all the files in the given directory thus making them available for discovery by peers.
func (f *FileIndexer) Index() error {
	log.Println("Started file indexing process.")
	err := f.dirwalk()
	if err != nil {
		return err
	}

	go f.watch()
	log.Println("Indexing finished successfully.")
	return nil
}

// Close : Closes any resources held by this file-indexer.
func (f *FileIndexer) Close() error {
	return f.watcher.Close()
}

func (f *FileIndexer) dirwalk() error {
	for _, dir := range conf.ShareDirs() {
		err := f.filewalk(dir)
		if err != nil {
			return err
		}
	}

	return nil
}

func (f *FileIndexer) watch() {
	for {
		select {
		case event := <-f.watcher.Events:
			f.handleFsEvent(event)
		case err := <-f.watcher.Errors:
			log.Printf("Got error from watcher: %v\n", err)
		}
	}
}

func (f *FileIndexer) handleFsEvent(event fsnotify.Event) {
	path := event.Name
	if isCreate(event) {
		info, _ := os.Stat(path)
		err := f.add(path, info)
		if err != nil {
			log.Printf("Error while adding path: %s\n", path)
		}
	} else if isRename(event) {
		log.Printf("Got rename for %s\n", event.Name)
		// some issue with fsnotify? why does rename => remove?
		err := f.remove(path)
		if err != nil {
			log.Printf("Error while removing path: %s\n", path)
		}
	}
}

// Performs a recursive file walk of the given directory path.
func (f *FileIndexer) filewalk(dir string) error {
	log.Printf("Performing filewalk for dir %s\n", dir)
	err := filepath.Walk(dir, func(path string, info os.FileInfo, err error) error {
		return f.add(path, info)
	})

	if err != nil {
		return err
	}

	return f.index.validate()
}

// Search : Searches for a given pattern in the names of the indexed files and returns those files.
func (f *FileIndexer) Search(pattern string) []*diztl.FileMetadata {
	return f.index.search(pattern)
}

func (f *FileIndexer) add(path string, info os.FileInfo) error {
	err := f.watcher.Add(path)
	if err != nil {
		log.Printf("Error while adding path to watcher - %s: %v\n", path, err)
	}

	if !info.IsDir() {
		f.index.addFile(path, info)
	}

	return nil
}

func (f *FileIndexer) remove(path string) error {
	log.Printf("Removing path :%s\n", path)
	// No need to remove path from watcher, fsnotify does it by default.
	// So, just remove the path from the index.
	err := f.index.removePath(path)
	return err
}
