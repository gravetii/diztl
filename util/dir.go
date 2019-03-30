package util

import (
	"log"
	"os"
	"path/filepath"
)

var rootdir, _ = os.UserHomeDir()
var shareDirElems = []string{rootdir, "Documents", "diztl", "share"}
var outputDirElems = append(shareDirElems, "output")

// ShareDir : The default diztl share directory.
var ShareDir = filepath.Join(shareDirElems...)

// OutputDir : The diztl output directory.
var OutputDir = filepath.Join(outputDirElems...)

// GetOutputPath : Returns the output file path for the file.
func GetOutputPath(filename string) string {
	return filepath.Join(OutputDir, filename)
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
			log.Fatalf("Could not create directory - %s: %v\n", dir, err)
		}
	} else if !info.IsDir() {
		log.Fatalf("Seems like there's a resource already: %s\n", dir)
	} else if err != nil {
		log.Fatalf("Could not ensure that directory exists - %s: %v\n", dir, err)
	}
}
