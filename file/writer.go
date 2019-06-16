package file

import (
	"bufio"
	"bytes"
	"errors"
	"io"
	"os"
	"path"

	"gopkg.in/cheggaaa/pb.v1"

	"github.com/gravetii/diztl/dir"
	"github.com/gravetii/diztl/diztl"
	"github.com/gravetii/diztl/logger"
)

// Writer - the file writer.
type Writer struct {
	metadata *diztl.FileMetadata
	f        *os.File
	o        *out
}

type out struct {
	buf     *bufio.Writer
	pbar    *pb.ProgressBar
	mwriter io.Writer
}

// CreateWriter returns an instance of the Writer for the given file metadata.
func CreateWriter(metadata *diztl.FileMetadata) (*Writer, error) {
	exists, err := checkIfOutFileExists(metadata.GetName())
	if err != nil {
		return nil, err
	}

	if exists {
		return nil, errors.New("file with given name already exists in output folder")
	}

	f, err := createTempFile(metadata.GetName())
	if err != nil {
		return nil, err
	}

	o := createOut(f, metadata)
	return &Writer{metadata, f, o}, nil
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
		os.Remove(obj.f.Name())
		return nil, errors.New("Invalid checksum, file is probably corrupted")
	}

	return obj.moveToOutputDir()
}

func (obj *Writer) moveToOutputDir() (*os.File, error) {
	fname := path.Base(obj.f.Name())
	fpath, err := dir.GetOutputPath(fname)
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

// createTempFile creates the file with the given name in the system's temp directory
// before it's moved to the user-configured output folder.
func createTempFile(fname string) (*os.File, error) {
	fpath, err := dir.GetTempPathForDownload(fname)
	if err != nil {
		return nil, err
	}

	f, err := os.Create(fpath)
	if err != nil {
		logger.Log.Printf("Unable to create temp file for download %s - %v\n", fpath, err)
		return nil, errors.New("could not create temp file for download - " + err.Error())
	}

	return f, nil
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

// Chunks returns the total number of chunks that need to be written to create the file.
func (obj *Writer) Chunks() int32 {
	return obj.metadata.GetChunks()
}
