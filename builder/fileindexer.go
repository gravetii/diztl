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
	log.Printf("Searching in indexer for pattern: %s", pattern)
	result := []*diztl.FileMetadata{}
	for _, file := range f.files {
		if strings.Contains(file.Name, pattern) {
			log.Printf("Got a valid search result: %s", file.Name)
			result = append(result, file)
		}
	}

	return result
}

// Performs a recursive file walk of the given directory path.
func filewalk(dir string) []*diztl.FileMetadata {
	files := []*diztl.FileMetadata{}
	var start int32
	counter := util.Counter{Count: &start}
	log.Println("Performing file walk...")
	err := filepath.Walk(dir, func(path string, info os.FileInfo, err error) error {
		if info.IsDir() {
			return nil
		}

		counter.IncrBy1()
		metadata := diztl.FileMetadata{Id: counter.Value(), Name: info.Name()}
		files = append(files, &metadata)
		log.Printf("%d: %s", metadata.Id, metadata.Name)
		return nil
	})

	if err != nil {
		panic(err)
	}

	return files
}
