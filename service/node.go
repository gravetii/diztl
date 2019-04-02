package service

import (
	"context"
	"io"
	"log"

	"github.com/gravetii/diztl/conf"
	"github.com/gravetii/diztl/shutdown"

	"github.com/gravetii/diztl/dir"

	"github.com/gravetii/diztl/diztl"
	"github.com/gravetii/diztl/file"
	"github.com/gravetii/diztl/indexer"
)

// NodeService : Implements the node server interface definition.
type NodeService struct {
	Indexer *indexer.FileIndexer
}

// NewNode : Returns an instance of the Node Service.
func NewNode() *NodeService {
	f, err := indexer.NewFileIndexer()
	if err != nil {
		log.Fatalf("Error while creating the node service: %v", err)
	}

	s := &NodeService{Indexer: f}
	shutdown.Listen(s)
	return s
}

// Init : Performs the necessary initialisation when the service comes up for the first time.
func (s *NodeService) Init() {
	log.Println("Initialising node service...")

	// Ensure that necessary folders are created
	dir.Ensure()

	if err := s.Indexer.Index(); err != nil {
		log.Fatalf("Error while indexing files: %v", err)
	}
}

// OnShutdown : Actions to perform on shutdown.
func (s *NodeService) OnShutdown() {
	if err := s.Indexer.Close(); err != nil {
		log.Printf("Error while closing file watcher: %v\n", err)
	} else {
		log.Println("Closed file watcher successfully.")
	}
}

// Search : The tracker invokes the search call when it broadcasts a search request from another node.
func (s *NodeService) Search(ctx context.Context, request *diztl.SearchReq) (*diztl.SearchResp, error) {
	log.Printf("Received search request: %v\n", request.GetSource())
	files := s.Indexer.Search(request.GetFilename())
	response := diztl.SearchResp{Files: files}
	return &response, nil
}

// Upload : A requesting node invokes this call on this node asking it to upload the file of interest.
func (s *NodeService) Upload(request *diztl.DownloadReq, stream diztl.DiztlService_UploadServer) error {
	metadata := request.GetMetadata()
	r, err := file.CreateReader(metadata.GetPath())
	if err != nil {
		return err
	}

	for {
		data, err := r.Read()
		if err != nil {
			if err == io.EOF {
				log.Printf("Finished uploading file: %s\n", metadata.GetPath())
				break
			}

			return err
		}

		fchunk := diztl.FileChunk{Chunk: r.Chunk(), Data: data}
		if r.Chunk() == 1 {
			chunks := int32(metadata.GetSize() / int64(conf.ChunkBufSize()))
			metadata.Chunks = chunks
			log.Printf("Uploading file: %s\n", metadata.GetPath())
			fchunk.Metadata = metadata
		}

		stream.Send(&fchunk)
	}

	return nil
}
