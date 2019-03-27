package service

import (
	"bufio"
	"log"
	"os"

	"github.com/gravetii/diztl/config"
	"github.com/gravetii/diztl/counter"
	"github.com/gravetii/diztl/diztl"
)

type fileReader struct {
	buf *bufio.Reader
	f   *os.File
	c   *counter.Counter
}

func createReader(m *diztl.FileMetadata) (*fileReader, error) {
	fpath := m.GetPath()
	f, err := openFile(fpath)
	if err != nil {
		return nil, err
	}

	buf := bufio.NewReader(f)
	return &fileReader{buf: buf, f: f, c: counter.New(0)}, nil
}

func openFile(fpath string) (*os.File, error) {
	f, err := os.Open(fpath)
	if err != nil {
		if os.IsNotExist(err) {
			log.Fatalf("Specified file %s does not exist: %v", fpath, err)
		} else {
			log.Fatalf("Error while reading file %s to upload: %v", fpath, err)
		}

		return nil, err
	}

	return f, nil
}

func (obj *fileReader) read() ([]byte, error) {
	p := make([]byte, config.ChunkBufSize)
	_, err := obj.buf.Read(p)
	if err != nil {
		return nil, err
	}

	obj.c.IncrBy1()
	return p, nil
}

func (obj *fileReader) chunk() int32 {
	return obj.c.Value()
}
