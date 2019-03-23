package builder

import (
	"errors"
	"fmt"
	"log"
	"os"
	"path/filepath"
	"strings"

	"github.com/gravetii/diztl/config"
	"github.com/gravetii/diztl/diztl"
	"github.com/gravetii/diztl/util"
)

// FileIndexer : The struct type that represents a file indexer on a node which indexes all the shared files.
type FileIndexer struct {
	files []*diztl.FileMetadata
}

// Index : Indexes all the files in the given directory thus making them available for discovery by peers.
func (f *FileIndexer) Index() error {
	dir := util.GetShareDir()
	log.Printf("Indexing files in directory: %s", dir)
	files := filewalk(dir)
	f.files = files
	if !f.hasMin() {
		return errors.New("less than minimum number of indexed files")
	}

	log.Println("Indexing finished successfully.")
	return nil
}

func (f *FileIndexer) hasMin() bool {
	if len(f.files) < config.MinIndexFiles {
		fmt.Printf("You need to share at least %d files to diztl before you can ask!\n", config.MinIndexFiles)
		return false
	}

	return true
}

// Search : Searches for a given pattern in the names of the indexed files and returns those files.
func (f *FileIndexer) Search(pattern string) []*diztl.FileMetadata {
	result := []*diztl.FileMetadata{}
	for _, file := range f.files {
		fname := util.GetFilename(file)
		if strings.Contains(fname, pattern) {
			result = append(result, file)
		}
	}

	return result
}

// Performs a recursive file walk of the given directory path.
func filewalk(dir string) []*diztl.FileMetadata {
	files := []*diztl.FileMetadata{}
	counter := util.NewCounter(0)
	log.Println("Performing file walk...")
	err := filepath.Walk(dir, func(path string, info os.FileInfo, err error) error {
		if info.IsDir() {
			return nil
		}

		counter.IncrBy1()
		metadata := diztl.FileMetadata{Id: counter.Value(), Path: path, Size: info.Size()}
		files = append(files, &metadata)
		log.Printf("%d. %s, %d, %d", counter.Value(), metadata.GetPath(), metadata.GetId(), metadata.GetSize())
		return nil
	})

	if err != nil {
		panic(err)
	}

	return files
}
