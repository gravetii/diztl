package service

import (
	"context"
	"log"

	"github.com/gravetii/diztl/builder"
	"github.com/gravetii/diztl/diztl"
)

// NodeService : Implements the node server interface definition.
type NodeService struct {
	Indexer *builder.FileIndexer
}

// Init : Performs the necessary initialisations for this service.
func (s *NodeService) Init() {
	log.Println("Initialising node service...")
	s.Indexer.Index()
}

// Search : func
func (s *NodeService) Search(ctx context.Context, in *diztl.SearchRequest) (*diztl.SearchResponse, error) {
	log.Printf("Received search request: %v", in.GetSource())
	return &diztl.SearchResponse{Count: 1, Files: nil, Node: nil}, nil
}

// Upload : func
func (s *NodeService) Upload(in *diztl.DownloadRequest, stream diztl.DiztlService_UploadServer) error {
	return nil
}
