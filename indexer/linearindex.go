package indexer

import (
	"errors"
	"fmt"
	"os"
	"path/filepath"
	"strings"

	"github.com/gravetii/diztl/conf"
	"github.com/gravetii/diztl/counter"
	"github.com/gravetii/diztl/diztl"
	"github.com/gravetii/diztl/logger"
)

// Index : Represents a file-index.
type Index struct {
	files   map[string]*diztl.FileMetadata
	counter *counter.AtomicCounter
}

func newIndex() *Index {
	// A non-atomic counter would probably do here too.
	return &Index{files: make(map[string]*diztl.FileMetadata), counter: counter.NewAtomic(0)}
}

func (index *Index) add(path string, info os.FileInfo) {
	f := diztl.FileMetadata{Path: path, Id: index.counter.IncrBy1(), Size: info.Size(), Name: filepath.Base(path)}
	index.files[path] = &f
	logger.Log.Printf("Added %d. %s\n", index.counter.Value(), path)
}

func (index *Index) remove(path string) {
	delete(index.files, path)
	index.counter.DecrBy1()
	logger.Log.Printf("Removed %s\n", path)
}

func (index *Index) validate() error {
	minFiles := conf.MinIndexFiles()
	if index.counter.Value() < minFiles {
		fmt.Printf("You need to share at least %d files to diztl before you can ask!\n", minFiles)
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
