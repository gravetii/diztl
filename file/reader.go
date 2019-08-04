package file

import (
	"bufio"
	"io"
	"io/ioutil"
	"math"
	"os"

	"github.com/gravetii/diztl/dir"
	"github.com/gravetii/diztl/diztl"
	"github.com/pkg/errors"

	"github.com/gravetii/diztl/counter"
	"github.com/gravetii/logger"
)

// Reader - The file reader object which reads chunks of data from the underlying buffer.
type Reader struct {
	buf      *bufio.Reader
	metadata *diztl.FileMetadata
	chunks   int32
	contract *diztl.UploadContract
	f        *os.File
	chunk    *counter.Counter
}

// CreateReader returns an instance of the Reader for the given file metadata and upload contract.
func CreateReader(metadata *diztl.FileMetadata, contract *diztl.UploadContract) (*Reader, error) {
	// Copy the source file to the system's temp directory to avoid effect of user changes
	// and use that file to upload.
	f, err := copyToTempDir(metadata)
	if err != nil {
		return nil, err
	}

	chunks := int32(math.Ceil(float64(metadata.GetSize()) / float64(contract.GetChunkSize())))
	buf := bufio.NewReader(f)
	reader := Reader{buf, metadata, chunks, contract, f, counter.New(0)}
	reader.reset()
	return &reader, nil
}

func (r *Reader) reset() {
	r.f.Seek(0, 0)
}

func copyToTempDir(metadata *diztl.FileMetadata) (*os.File, error) {
	src := dir.GetFilePath(metadata)
	fname := metadata.GetName()

	in, err := openFile(src)
	if err != nil {
		return nil, err
	}

	defer in.Close()

	out, err := ioutil.TempFile("", fname)
	if err != nil {
		logger.Errorf("Unable to create temp file for upload: %s - %v\n", src, err)
		return nil, errors.Wrap(err, "Couldn't create temp file for upload")
	}

	logger.Debugf("Created temp file for upload from %s - %s\n", src, out.Name())

	_, err = io.Copy(out, in)
	if err != nil {
		logger.Errorf("Error while copying source file to temp dir: %s - %v\n", out.Name(), err)
		return nil, errors.Wrap(err, "Couldn't copy source file to temp dir")
	}

	logger.Debugf("Copied source file %s to temp dir %s\n", src, out.Name())
	return out, nil
}

func openFile(fpath string) (*os.File, error) {
	f, err := os.Open(fpath)
	if err != nil {
		if os.IsNotExist(err) {
			logger.Errorf("Specified file %s does not exist: %v\n", fpath, err)
		} else {
			logger.Errorf("Error while opening file %s to upload: %v\n", fpath, err)
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
		fchunk.Chunks = r.chunks
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

// Close closes the underlying file opened by this reader and also deletes it
// because it is a copy of the source file.
func (r *Reader) Close() {
	r.f.Close()
	defer os.Remove(r.f.Name())
}
