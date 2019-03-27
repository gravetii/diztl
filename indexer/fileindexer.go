package indexer

import (
	"log"
	"os"
	"path/filepath"

	"github.com/fsnotify/fsnotify"
	"github.com/gravetii/diztl/diztl"
	"github.com/gravetii/diztl/util"
)

// FileIndexer : The struct type that represents a file indexer on a node which indexes all the shared files.
type FileIndexer struct {
	index   *Index
	watcher *fsnotify.Watcher
}

// NewFileIndexer : Returns a new instance of FileIndexer.
func NewFileIndexer() (*FileIndexer, error) {
	f := FileIndexer{index: newIndex()}
	w, err := fsnotify.NewWatcher()
	if err != nil {
		log.Fatalf("Unable to establish file-system watcher: %v", err)
		return nil, err
	}

	// figure out how & when to close this watcher. [SIGINT/SIGTERM]
	f.watcher = w
	return &f, nil
}

// Index : Indexes all the files in the given directory thus making them available for discovery by peers.
func (f *FileIndexer) Index() error {
	log.Println("Started file indexing process.")
	f.dirwalk()
	go f.watch()
	log.Println("Indexing finished successfully.")
	return nil
}

func (f *FileIndexer) dirwalk() {
	dir := util.ShareDir
	if err := f.filewalk(dir); err != nil {
		log.Fatalf("Error while performing filewalk for dir %s: %v", dir, err)
	}
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
		f.add(path, info)
	} else if isRename(event) {
		// some issue with fsnotify? why does rename => remove?
		f.remove(path)
	}
}

// Performs a recursive file walk of the given directory path.
func (f *FileIndexer) filewalk(dir string) error {
	log.Println("Performing file walk...")
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
		f.index.add(path, info)
	}

	return nil
}

func (f *FileIndexer) remove(path string) error {
	log.Printf("Removing path :%s\n", path)
	// No need to remove path from watcher, fsnotify does it by default. So, just remove the path from the index.
	f.index.remove(path)
	return nil
}
