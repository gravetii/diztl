package file

import (
	"bufio"
	"bytes"
	"errors"
	"io/ioutil"
	"os"
	"path/filepath"

	"github.com/gravetii/diztl/dir"
	"github.com/gravetii/diztl/diztl"
	"github.com/gravetii/logger"
)

// Writer - the file writer.
type Writer struct {
	metadata *diztl.FileMetadata
	chunks   int32
	f        *os.File
	buf      *bufio.Writer
	out      string
}

// CreateWriter returns an instance of the Writer for the given file metadata.
func CreateWriter(metadata *diztl.FileMetadata, chunks int32, out string) (*Writer, error) {
	fname := metadata.GetName()
	exists, err := checkIfOutFileExists(out, fname)
	if err != nil {
		return nil, err
	}

	if exists {
		return nil, errors.New("A file with the given name already exists in the downloads folder")
	}

	f, err := ioutil.TempFile("", fname)
	if err != nil {
		logger.Errorf("Unable to create temp file for download: %s - %v\n", dir.GetFilePath(metadata), err)
		return nil, errors.New("Could not create temp file for download - " + err.Error())
	}

	logger.Debugf("Created temp file for download from %s - %s\n", dir.GetFilePath(metadata), f.Name())
	buf := bufio.NewWriter(f)
	return &Writer{metadata, chunks, f, buf, out}, nil
}

// checks if a file with given name is already present before starting download.
func checkIfOutFileExists(out string, fname string) (bool, error) {
	if err := dir.Ensure(out); err != nil {
		return false, err
	}

	fpath := filepath.Join(out, fname)
	_, err := os.Stat(fpath)
	return !os.IsNotExist(err), nil
}

// Close closes the resources held by this writer and returns the created file after verifying checksum.
func (obj *Writer) Close() error {
	obj.buf.Flush()
	obj.f.Close()
	if !obj.verifyChecksum() {
		defer os.Remove(obj.f.Name())
		return errors.New("Invalid checksum, file is probably corrupted")
	}

	return obj.moveToOutputDir()
}

func (obj *Writer) moveToOutputDir() error {
	if err := dir.Ensure(obj.out); err != nil {
		return err
	}

	fpath := filepath.Join(obj.out, obj.metadata.GetName())
	if err := os.Rename(obj.f.Name(), fpath); err != nil {
		return err
	}

	return nil
}

func (obj *Writer) verifyChecksum() bool {
	c := obj.metadata.Hash.Checksum
	hash, err := Hash(obj.f.Name())
	if err != nil {
		return false
	}

	return bytes.Equal(c, hash.Checksum)
}

// Write writes the given set of bytes to the underlying buffer.
func (obj *Writer) Write(data []byte) error {
	_, err := obj.buf.Write(data)
	return err
}
