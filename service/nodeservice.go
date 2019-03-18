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
	filename := request.GetMetadata().GetName()
	filepath := util.GetSharePath(filename)

	f, err := openFile(filepath)
	if err != nil {
		return err
	}

	p := make([]byte, bufsize)
	reader := bufio.NewReader(f)
	c := 1

	for {
		n, err := reader.Read(p)
		if err == io.EOF {
			log.Printf("Finished uploading file :%s", filename)
			break
		} else {
			metadata := diztl.FileMetadata{Name: filename}
			fchunk := &diztl.File{Metadata: &metadata, Data: p, Chunk: int32(c)}
			stream.Send(fchunk)
			log.Printf("Wrote bytes: %d, Chunk no. %d", n, c)
			c++
		}
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
