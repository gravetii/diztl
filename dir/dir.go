package dir

import (
	"log"
	"os"
	"path/filepath"

	"github.com/gravetii/diztl/conf"
)

// GetOutputPath : Returns the output file path for the file.
func GetOutputPath(filename string) string {
	return filepath.Join(conf.OutputDir(), filename)
}

// GetTempPath returns the path to the file in the system's temp directory.
// This is where the file is first created on the receiver node when download
// is initiated and is later moved to the output folder when download completes.
func GetTempPath(filename string) string {
	return filepath.Join(os.TempDir(), filename)
}

// EnsureShareDirs ensures that user-configured share directories are created.
func EnsureShareDirs() {
	for _, dir := range conf.ShareDirs() {
		ensureDir(dir)
	}
}

// EnsureOutputDir ensures that the user-configured output directory is created.
func EnsureOutputDir() {
	ensureDir(conf.OutputDir())
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

// Split : Splits the path into individual tokens and returns a slice of the tokens.
func Split(path string) []string {
	res := []string{}
	path = filepath.Clean(path)
	dir, name := filepath.Split(path)
	dir = filepath.Clean(dir)
	if dir == "/" {
		res = append(res, dir)
	} else if dir != "" {
		r := Split(dir)
		if len(r) > 0 {
			res = append(res, r...)
		}
	}

	res = append(res, name)
	return res
}
