package file

import (
	"bufio"
	"log"
	"os"

	"github.com/gravetii/diztl/conf"
	"github.com/gravetii/diztl/counter"
)

// Reader : The file reader.
type Reader struct {
	buf *bufio.Reader
	f   *os.File
	c   *counter.Counter
}

// CreateReader : Returns an instance of the Reader for the given file path.
func CreateReader(fpath string) (*Reader, error) {
	f, err := openFile(fpath)
	if err != nil {
		return nil, err
	}

	buf := bufio.NewReader(f)
	return &Reader{buf, f, counter.New(0)}, nil
}

func openFile(fpath string) (*os.File, error) {
	f, err := os.Open(fpath)
	if err != nil {
		if os.IsNotExist(err) {
			log.Printf("Specified file %s does not exist: %v\n", fpath, err)
		} else {
			log.Printf("Error while reading file %s to upload: %v\n", fpath, err)
		}

		return nil, err
	}

	return f, nil
}

// Read : Reads a set of bytes from the underlying file and writes it to the array for transmission.
func (obj *Reader) Read() ([]byte, error) {
	p := make([]byte, conf.ChunkBufSize())
	_, err := obj.buf.Read(p)
	if err != nil {
		return nil, err
	}

	obj.c.IncrBy1()
	return p, nil
}

// Chunk : Returns the number of chunks read from the file at any point in time.
func (obj *Reader) Chunk() int32 {
	return obj.c.Value()
}
