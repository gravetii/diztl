package file

import (
	"bufio"
	"bytes"
	"errors"
	"io/ioutil"
	"os"

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
}

// CreateWriter returns an instance of the Writer for the given file metadata.
func CreateWriter(metadata *diztl.FileMetadata, chunks int32) (*Writer, error) {
	fname := metadata.GetName()
	exists, err := checkIfOutFileExists(fname)
	if err != nil {
		return nil, err
	}

	if exists {
		return nil, errors.New("A file with given name already exists in output folder")
	}

	f, err := ioutil.TempFile("", fname)
	if err != nil {
		logger.Errorf("Unable to create temp file for download: %s - %v\n", dir.GetFilePath(metadata), err)
		return nil, errors.New("Could not create temp file for download - " + err.Error())
	}

	logger.Debugf("Created temp file for download from %s - %s\n", dir.GetFilePath(metadata), f.Name())
	buf := bufio.NewWriter(f)
	return &Writer{metadata, chunks, f, buf}, nil
}

// checks if a file with given name is already present before starting download.
func checkIfOutFileExists(fname string) (bool, error) {
	fpath, err := dir.GetOutputPath(fname)
	if err != nil {
		return false, err
	}

	_, err = os.Stat(fpath)
	return !os.IsNotExist(err), nil
}

// Close closes the resources held by this writer and returns the created file after verifying checksum.
func (obj *Writer) Close() (*os.File, error) {
	obj.buf.Flush()
	obj.f.Close()
	if !obj.verifyChecksum() {
		defer os.Remove(obj.f.Name())
		return nil, errors.New("Invalid checksum, file is probably corrupted")
	}

	return obj.moveToOutputDir()
}

func (obj *Writer) moveToOutputDir() (*os.File, error) {
	fpath, err := dir.GetOutputPath(obj.metadata.GetName())
	if err != nil {
		return nil, err
	}

	if err := os.Rename(obj.f.Name(), fpath); err != nil {
		return nil, err
	}

	f, err := openFile(fpath)
	if err != nil {
		return nil, err
	}

	return f, nil
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
