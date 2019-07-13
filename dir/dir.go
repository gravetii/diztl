package dir

import (
	"errors"
	"os"
	"path/filepath"

	"github.com/gravetii/diztl/conf"
)

// GetShareDirs returns the user-configured share directories on this node.
func GetShareDirs() ([]string, error) {
	for _, dir := range conf.ShareDirs() {
		if err := ensure(dir); err != nil {
			return nil, errors.New("Could not ensure share directories exist - " + err.Error())
		}
	}

	return conf.ShareDirs(), nil
}

// GetOutputDir returns the user-configured output directory on this node.
func GetOutputDir() (string, error) {
	if err := ensure(conf.OutputDir()); err != nil {
		return "", errors.New("Could not ensure output directory exists - " + err.Error())
	}

	return conf.OutputDir(), nil
}

// GetOutputPath : Returns the user-configured output path for the file with given name
// or an error if any.
func GetOutputPath(fname string) (string, error) {
	outputDir, err := GetOutputDir()
	if err != nil {
		return "", err
	}

	return filepath.Join(outputDir, fname), nil
}

// GetLogPath returns the path to the logger file or an error if any.
func GetLogPath(fname string) (string, error) {
	if err := ensure(conf.AppDir()); err != nil {
		return "", errors.New("Could not ensure app directory exists - " + err.Error())
	}

	return filepath.Join(conf.AppDir(), fname), nil
}

func ensure(dir string) error {
	if err := os.MkdirAll(dir, os.ModePerm); err != nil {
		return err
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
