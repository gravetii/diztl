package service

import (
	"context"
	"fmt"
	"io"
	"log"

	"github.com/gravetii/diztl/shutdown"

	"github.com/gravetii/diztl/dir"

	"github.com/gravetii/diztl/diztl"
	"github.com/gravetii/diztl/file"
	"github.com/gravetii/diztl/indexer"
)

// NodeService implements the node server interface definition.
type NodeService struct {
	Indexer *indexer.FileIndexer
}

// NewNode returns an instance of the Node Service.
func NewNode() *NodeService {
	f, err := indexer.NewFileIndexer()
	if err != nil {
		log.Fatalf("Error while creating the node service: %v", err)
	}

	s := &NodeService{Indexer: f}
	shutdown.Listen(s)
	return s
}

// Init performs the necessary initialisation when the service comes up for the first time.
func (s *NodeService) Init() {
	log.Println("Initialising node service...")

	// Ensure that necessary folders are created
	dir.EnsureShareDirs()
	dir.EnsureOutputDir()

	if err := s.Indexer.Index(); err != nil {
		log.Fatalf("Error while indexing files: %v", err)
	}
}

// OnShutdown defines actions to perform on node shutdown.
func (s *NodeService) OnShutdown() {
	if err := s.Indexer.Close(); err != nil {
		log.Printf("Error while closing file watcher: %v\n", err)
	} else {
		log.Println("Closed file watcher successfully.")
	}
}

// Search - The tracker invokes the search call on all the nodes when it broadcasts a search request from another node.
func (s *NodeService) Search(ctx context.Context, request *diztl.SearchReq) (*diztl.SearchResp, error) {
	log.Printf("Received search request: %v\n", request.GetSource())
	files := s.Indexer.Search(request.GetFilename())
	response := diztl.SearchResp{Files: files}
	return &response, nil
}

// Upload - A requesting node invokes this call on this node asking it to upload the file of interest.
func (s *NodeService) Upload(request *diztl.DownloadReq, stream diztl.DiztlService_UploadServer) error {
	metadata := request.GetMetadata()
	r, err := file.CreateReader(metadata, request.GetContract())
	if err != nil {
		return err
	}

	for {
		f, err := r.Read()
		if err != nil {
			r.Close()
			if err == io.EOF {
				log.Printf("Finished uploading file: %s\n", metadata.GetPath())
				break
			}

			return err
		}

		if f.Chunk == 1 {
			log.Printf("Uploading file: %s\n", metadata.GetPath())
		}

		serr := stream.Send(f)
		if serr != nil {
			log.Printf("Upload failed due to error in recipient host: %v\n", serr)
			fmt.Println("Upload failed, but relax! It's not you, it's them.")
			break
		}
	}

	return nil
}

// Ping - Any node can invoke this call on any node to see if it's currently active.
func (s *NodeService) Ping(ctx context.Context, request *diztl.PingReq) (*diztl.PingResp, error) {
	log.Printf("Received ping from %v\n", request.GetSource())
	return &diztl.PingResp{Message: "online"}, nil
}
