package service

import (
	"bufio"
	"context"
	"io"
	"log"
	"os"

	"github.com/gravetii/diztl/builder"
	"github.com/gravetii/diztl/diztl"
	"github.com/gravetii/diztl/util"
)

const (
	bufsize = 1024 * 512
)

// NodeService : Implements the node server interface definition.
type NodeService struct {
	Indexer *builder.FileIndexer
	node    *diztl.Node
}

// Init : Performs the necessary initialisation when the service comes up for the first time.
func (s *NodeService) Init() {
	log.Println("Initialising node service...")
	sharedir := util.GetShareDir()
	s.Indexer.Index(sharedir)
}

// Search : func
func (s *NodeService) Search(ctx context.Context, request *diztl.SearchRequest) (*diztl.SearchResponse, error) {
	log.Printf("Received search request: %v", request.GetSource())
	files := s.Indexer.Search(request.GetFilename())
	// todo - check explicit conversion from int to int32
	response := diztl.SearchResponse{Count: int32(len(files)), Files: files}
	return &response, nil
}

// Upload : func
func (s *NodeService) Upload(request *diztl.DownloadRequest, stream diztl.DiztlService_UploadServer) error {
	log.Printf("Uploading file for download request: %v", request)
	fname := request.GetMetadata().GetName()
	fpath := util.GetSharePath(fname)

	f, err := openFile(fpath)
	if err != nil {
		return err
	}

	p := make([]byte, bufsize)
	reader := bufio.NewReader(f)
	chunk := 1

	for {
		if chunk == 1 {
			// Send metadata of the file without actual payload in the first chunk.
			fchunk := &diztl.File{Metadata: request.GetMetadata(), Chunk: 1}
			stream.Send(fchunk)
			log.Printf("Sent file metadata in starting chunk.")
		} else {
			_, err := reader.Read(p)
			if err == io.EOF {
				log.Printf("Finished uploading file :%s", fname)
				break
			}
			fchunk := &diztl.File{Data: p, Chunk: int32(chunk)}
			stream.Send(fchunk)
		}

		chunk++
	}

	return nil
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

	return f, err
}
