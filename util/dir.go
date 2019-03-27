package util

import (
	"log"
	"os"
	"path/filepath"

	"github.com/gravetii/diztl/diztl"
)

const (
	shareSuffix  = "/Documents/diztl/share"
	outputSuffix = "/output"
)

var rootdir, _ = os.UserHomeDir()

// ShareDir : The default diztl share directory.
var ShareDir = rootdir + shareSuffix

// OutputDir : The diztl output directory.
var OutputDir = ShareDir + outputSuffix

// GetOutputPath : Returns the output file path for the file.
func GetOutputPath(filename string) string {
	return OutputDir + "/" + filename
}

// GetFilename : Returns the filename of the file pointed by the metadata.
func GetFilename(m *diztl.FileMetadata) string {
	return filepath.Base(m.GetPath())
}

// EnsureDirs : Checks if the required directories are created, creating them if not.
func EnsureDirs() {
	ensureDir(ShareDir)
	ensureDir(OutputDir)
}

func ensureDir(dir string) {
	info, err := os.Stat(dir)
	if os.IsNotExist(err) {
		log.Printf("Creating directory: %s\n", dir)
		if err := os.MkdirAll(dir, os.ModePerm); err != nil {
			log.Fatalf("Could not create directory - %s: %v", dir, err)
		}
	} else if !info.IsDir() {
		log.Fatalf("Seems like there's a resource already: %s\n", dir)
	} else if err != nil {
		log.Fatalf("Could not ensure that directory exists - %s: %v", dir, err)
	}
}
