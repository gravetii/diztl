package dir

import (
	"os"
	"path/filepath"

	"github.com/gravetii/diztl/conf"
	"github.com/gravetii/diztl/diztl"
	"github.com/pkg/errors"
)

// GetShareDirs returns the user-configured share directories on this node.
func GetShareDirs() ([]string, error) {
	for _, dir := range conf.ShareDirs() {
		if err := Ensure(dir); err != nil {
			return nil, errors.Wrap(err, "Couldn't ensure share directories exist")
		}
	}

	return conf.ShareDirs(), nil
}

// GetDownloadsDir returns the user-configured downloads directory on this node.
func GetDownloadsDir() (string, error) {
	if err := Ensure(conf.DownloadsDir()); err != nil {
		return "", err
	}

	return conf.DownloadsDir(), nil
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
		return errors.Wrap(err, "Couldn't ensure directory exists")
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
