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
func (s *NodeService) Search(ctx context.Context, request *diztl.SearchRequest) (*diztl.SearchResponse, error) {
	log.Printf("Received search request: %v", request.GetSource())
	files := s.Indexer.Search(request.GetFilename())
	// todo - check explicit conversion from int to int32
	response := diztl.SearchResponse{Count: int32(len(files)), Files: files}
	return &response, nil
}

// Upload : func
func (s *NodeService) Upload(in *diztl.DownloadRequest, stream diztl.DiztlService_UploadServer) error {
	return nil
}
