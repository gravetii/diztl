package file

import (
	"bufio"
	"errors"
	"io"
	"os"
	"path"

	"github.com/gravetii/diztl/dir"
	"github.com/gravetii/diztl/diztl"

	"github.com/gravetii/diztl/counter"
	"github.com/gravetii/diztl/logger"
)

// Reader - The file reader object which reads chunks of data from the underlying buffer.
type Reader struct {
	buf      *bufio.Reader
	metadata *diztl.FileMetadata
	contract *diztl.DownloadContract
	f        *os.File
	chunk    *counter.Counter
}

// CreateReader returns an instance of the Reader for the given file metadata and download contract.
func CreateReader(metadata *diztl.FileMetadata, contract *diztl.DownloadContract) (*Reader, error) {
	// Copy the source file to the system's temp directory to avoid effect of user changes
	// and use that file to upload.
	f, err := copyToTempDir(metadata)
	if err != nil {
		return nil, err
	}

	chunks := int32(metadata.GetSize() / int64(contract.GetChunkSize()))
	metadata.Chunks = chunks
	buf := bufio.NewReader(f)
	reader := Reader{buf, metadata, contract, f, counter.New(0)}
	reader.reset()
	return &reader, nil
}

func (r *Reader) reset() {
	r.f.Seek(0, 0)
}

// createTempFileFromSource creates a copy of the source file in the system's temp directory.
func createTempFileFromSource(src string) (*os.File, error) {
	dest, err := dir.GetTempPathForUpload(path.Base(src))
	if err != nil {
		return nil, err
	}

	f, err := os.Create(dest)
	if err != nil {
		logger.Log.Printf("Error while creating temp file for upload: %s - %v\n", dest, err)
		return nil, errors.New("could not create temp file for upload - " + err.Error())
	}

	return f, nil
}

func copyToTempDir(metadata *diztl.FileMetadata) (*os.File, error) {
	src := metadata.GetPath()
	in, err := openFile(src)
	if err != nil {
		return nil, err
	}

	defer in.Close()
	out, err := createTempFileFromSource(src)
	if err != nil {
		return nil, err
	}

	_, err = io.Copy(out, in)
	if err != nil {
		logger.Log.Printf("Error while copying source file to temp dir: %s - %v\n", src, err)
		return nil, errors.New("could not copy source file to temp dir - " + err.Error())
	}

	logger.Log.Printf("Copied source file %s to temp dir %s\n", src, out.Name())
	return out, nil
}

func openFile(fpath string) (*os.File, error) {
	f, err := os.Open(fpath)
	if err != nil {
		if os.IsNotExist(err) {
			logger.Log.Printf("Specified file %s does not exist: %v\n", fpath, err)
		} else {
			logger.Log.Printf("Error while reading file %s to upload: %v\n", fpath, err)
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
	r.f.Close()
}
