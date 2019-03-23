package builder

import (
	"log"
	"os"
	"path/filepath"
	"strings"

	"github.com/gravetii/diztl/diztl"
	"github.com/gravetii/diztl/util"
)

// FileIndexer : The struct type that represents a file indexer on a node which indexes all the shared files.
type FileIndexer struct {
	files []*diztl.FileMetadata
}

// Index : Indexes all the files in the given directory thus making them available for discovery by peers.
func (f *FileIndexer) Index(dir string) {
	log.Println("Indexing files...")
	files := filewalk(dir)
	f.files = files
	log.Println("Finished indexing.")
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
