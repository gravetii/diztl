package client

import (
	"bufio"
	"log"
	"os"

	"github.com/gravetii/diztl/diztl"
	"github.com/gravetii/diztl/util"
)

type fileWriter struct {
	metadata *diztl.FileMetadata
	buf      *bufio.Writer
	f        *os.File
}

func (obj *fileWriter) close() {
	obj.buf.Flush()
	obj.f.Close()
}

func createWriter(m *diztl.FileMetadata) (*fileWriter, error) {
	f, err := createFile(m)
	if err != nil {
		return nil, err
	}

	buf := bufio.NewWriter(f)
	return &fileWriter{metadata: m, buf: buf, f: f}, nil
}

func createFile(metadata *diztl.FileMetadata) (*os.File, error) {
	fname := util.GetFilename(metadata)
	fpath := util.GetOutputPath(fname)
	f, err := os.Create(fpath)
	if err != nil {
		log.Fatalf("Unable to create file %s: %v", fpath, err)
		return nil, err
	}

	return f, nil
}

func (obj *fileWriter) write(data []byte) error {
	_, err := obj.buf.Write(data)
	if err != nil {
		return err
	}

	return nil
}

func (obj *fileWriter) name() string {
	return obj.f.Name()
}

func (obj *fileWriter) chunks() int32 {
	return obj.metadata.GetChunks()
}
