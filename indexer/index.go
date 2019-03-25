package indexer

import (
	"errors"
	"fmt"
	"log"
	"os"
	"strings"

	"github.com/gravetii/diztl/config"
	"github.com/gravetii/diztl/diztl"
	"github.com/gravetii/diztl/util"
)

// Index : Represents a file-index.
type Index struct {
	files   map[string]*diztl.FileMetadata
	counter *util.AtomicCounter
}

func newIndex() *Index {
	// A non-atomic counter would probably do here too.
	return &Index{files: make(map[string]*diztl.FileMetadata), counter: util.NewAtomicCounter(0)}
}

func (index *Index) add(path string, info os.FileInfo) {
	f := diztl.FileMetadata{Path: path, Id: index.counter.IncrBy1(), Size: info.Size()}
	index.files[path] = &f
	log.Printf("Added %d. %s.", index.counter.Value(), path)
}

func (index *Index) remove(path string) {
	delete(index.files, path)
	index.counter.DecrBy1()
	log.Printf("Removed %s.", path)
}

func (index *Index) validate() error {
	if index.counter.Value() < config.MinIndexFiles {
		fmt.Printf("You need to share at least %d files to diztl before you can ask!\n", config.MinIndexFiles)
		return errors.New("less than minimum number of indexed files")
	}

	return nil
}

func (index *Index) search(pattern string) []*diztl.FileMetadata {
	result := []*diztl.FileMetadata{}
	for path, file := range index.files {
		if strings.Contains(strings.ToLower(path), strings.ToLower(pattern)) {
			result = append(result, file)
		}
	}

	return result
}
