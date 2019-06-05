package file

import (
	"bufio"
	"bytes"
	"errors"
	"log"
	"os"

	"github.com/gravetii/diztl/dir"
	"github.com/gravetii/diztl/diztl"
)

// Writer - the file writer.
type Writer struct {
	metadata *diztl.FileMetadata
	buf      *bufio.Writer
	f        *os.File
}

// Close closes the resources held by this writer and returns the created file after verifying checksum.
func (obj *Writer) Close() (*os.File, error) {
	obj.f.Close()
	if !obj.verifyChecksum() {
		return obj.f, errors.New("Invalid checksum, file is probably corrupted")
	}

	return obj.f, nil
}

func (obj *Writer) verifyChecksum() bool {
	hash, err := Hash(obj.f.Name())
	if err != nil {
		log.Printf("Unable to verify checksum for file %s. File is probably corrupted.\n", obj.f.Name())
		return false
	}

	return bytes.Equal(obj.metadata.Hash, hash)
}

// CreateWriter returns an instance of the Writer for the given file metadata.
func CreateWriter(metadata *diztl.FileMetadata) (*Writer, error) {
	f, err := createFile(metadata.GetName())
	if err != nil {
		return nil, err
	}

	buf := bufio.NewWriter(f)
	return &Writer{metadata, buf, f}, nil
}

func createFile(fname string) (*os.File, error) {
	fpath := dir.GetOutputPath(fname)
	dir.EnsureOutputDir()
	f, err := os.Create(fpath)
	if err != nil {
		log.Printf("Unable to create file %s: %v\n", fpath, err)
		return nil, err
	}

	return f, nil
}

// Write writes the given set of bytes to the underlying buffer.
func (obj *Writer) Write(data []byte) error {
	_, err := obj.buf.Write(data)
	return err
}

// Name returns the name of the file that will be created by this file writer.
func (obj *Writer) Name() string {
	return obj.f.Name()
}

// Chunks returns the total number of chunks that need to be written to create the file.
func (obj *Writer) Chunks() int32 {
	return obj.metadata.GetChunks()
}
