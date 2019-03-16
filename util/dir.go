package util

import (
	"os"
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

// GetSharePath : Returns the file share path for the file.
func GetSharePath(filename string) string {
	return GetShareDir() + "/" + filename
}

// GetOutputPath : Returns the output file path for the file.
func GetOutputPath(filename string) string {
	return GetShareDir() + "/output/" + filename
}
