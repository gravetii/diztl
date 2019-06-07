package file

import (
	"bufio"
	"log"
	"os"

	"github.com/gravetii/diztl/diztl"

	"github.com/gravetii/diztl/counter"
)

// Reader : The file reader.
type Reader struct {
	buf      *bufio.Reader
	metadata *diztl.FileMetadata
	contract *diztl.DownloadContract
	file     *os.File
	chunk    *counter.Counter
}

// CreateReader returns an instance of the Reader for the given file metadata and download contract.
func CreateReader(metadata *diztl.FileMetadata, contract *diztl.DownloadContract) (*Reader, error) {
	file, err := openFile(metadata.GetPath())
	if err != nil {
		return nil, err
	}

	chunks := int32(metadata.GetSize() / int64(contract.GetChunkSize()))
	metadata.Chunks = chunks
	buf := bufio.NewReader(file)
	reader := Reader{buf, metadata, contract, file, counter.New(0)}
	return &reader, nil
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

// Read reads the next chunk of data from the source buffer and returns it for
// transmission to the receiver node.
func (r *Reader) Read() (*diztl.FileChunk, error) {
	data, err := r.read()
	if err != nil {
		return nil, err
	}

	c := r.chunk.IncrBy1()
	fchunk := diztl.FileChunk{Chunk: c, Data: data}
	if c == 1 {
		fchunk.Metadata = r.metadata
	}

	return &fchunk, nil
}

func (r *Reader) read() ([]byte, error) {
	chunkSize := r.contract.GetChunkSize()
	p := make([]byte, chunkSize)
	n, err := r.buf.Read(p)
	if err != nil {
		return nil, err
	}

	if n < len(p) {
		res := make([]byte, n)
		copy(res, p)
		return res, nil
	}

	return p, nil
}

// Close closes the underlying file opened by this reader.
func (r *Reader) Close() {
	r.file.Close()
}
