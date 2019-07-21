package dir

import (
	"errors"
	"os"
	"path/filepath"

	"github.com/gravetii/diztl/conf"
	"github.com/gravetii/diztl/diztl"
)

// GetShareDirs returns the user-configured share directories on this node.
func GetShareDirs() ([]string, error) {
	for _, dir := range conf.ShareDirs() {
		if err := Ensure(dir); err != nil {
			return nil, errors.New("Could not ensure share directories exist - " + err.Error())
		}
	}

	return conf.ShareDirs(), nil
}

// GetOutputDir returns the user-configured output directory on this node.
func GetOutputDir() (string, error) {
	if err := Ensure(conf.OutputDir()); err != nil {
		return "", err
	}

	return conf.OutputDir(), nil
}

// GetLogPath returns the path to the logger file or an error if any.
func GetLogPath(fname string) (string, error) {
	if err := Ensure(conf.AppDir()); err != nil {
		return "", err
	}

	return filepath.Join(conf.AppDir(), fname), nil
}

// Ensure checks if the given directory exists in the file-system; creates it if not.
func Ensure(dir string) error {
	if err := os.MkdirAll(dir, os.ModePerm); err != nil {
		return errors.New("Couldn't ensure that directory exists - " + err.Error())
	}

	return nil
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

// GetFilePath returns the path to the file using the enclosing dir and file name.
func GetFilePath(metadata *diztl.FileMetadata) string {
	return filepath.Join(metadata.GetDir(), metadata.GetName())
}
