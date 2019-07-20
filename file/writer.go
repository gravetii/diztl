package file

import (
	"bufio"
	"bytes"
	"errors"
	"io"
	"io/ioutil"
	"os"

	"gopkg.in/cheggaaa/pb.v1"

	"github.com/gravetii/diztl/dir"
	"github.com/gravetii/diztl/diztl"
	"github.com/gravetii/logger"
)

// Writer - the file writer.
type Writer struct {
	metadata *diztl.FileMetadata
	chunks   int32
	f        *os.File
	o        *out
}

type out struct {
	buf     *bufio.Writer
	pbar    *pb.ProgressBar
	mwriter io.Writer
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
	o := createOut(f, metadata)
	return &Writer{metadata, chunks, f, o}, nil
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

func createOut(f *os.File, metadata *diztl.FileMetadata) *out {
	buf := bufio.NewWriter(f)
	pbar := pb.New64(metadata.GetSize()).SetUnits(pb.U_BYTES_DEC).SetWidth(100)
	pbar.ManualUpdate = true
	pbar.Start()
	mwriter := io.MultiWriter(buf, pbar)
	o := out{buf, pbar, mwriter}
	return &o
}

func (o *out) close() {
	o.buf.Flush()
	o.pbar.Finish()
}

// Close closes the resources held by this writer and returns the created file after verifying checksum.
func (obj *Writer) Close() (*os.File, error) {
	obj.o.close()
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

func (o *out) write(data []byte) error {
	_, err := o.mwriter.Write(data)
	o.pbar.Update()
	return err
}

// Write writes the given set of bytes to the underlying buffer.
func (obj *Writer) Write(data []byte) error {
	return obj.o.write(data)
}
