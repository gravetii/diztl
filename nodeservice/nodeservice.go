package nodeservice

import (
	"context"
	"log"

	pb "github.com/gravetii/diztl/diztl"
)

// NodeService : Implements the node server interface definition.
type NodeService struct{}

// Search : func
func (s *NodeService) Search(ctx context.Context, in *pb.SearchRequest) (*pb.SearchResponse, error) {
	log.Printf("Received search request: %v", in.GetSource())
	return &pb.SearchResponse{Count: 1, Files: nil, Node: nil}, nil
}

// Upload : func
func (s *NodeService) Upload(in *pb.DownloadRequest, stream pb.DiztlService_UploadServer) error {
	return nil
}
