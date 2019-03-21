package util

import (
	"os"
	"path/filepath"

	"github.com/gravetii/diztl/diztl"
)

const (
	suffix = "/Documents/diztl/share"
)

var rootdir, _ = os.UserHomeDir()

// GetShareDir : Returns the default share directory.
func GetShareDir() string {
	dir := rootdir + suffix
	return dir
}

// GetOutputPath : Returns the output file path for the file.
func GetOutputPath(filename string) string {
	return GetShareDir() + "/output/" + filename
}

// GetFilename returns the filename of the file pointed by the metadata.
func GetFilename(m *diztl.FileMetadata) string {
	return filepath.Base(m.GetPath())
}
