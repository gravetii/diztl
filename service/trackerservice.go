package service

import (
	"context"
	"log"

	"github.com/gravetii/diztl/builder"
	pb "github.com/gravetii/diztl/diztl"
)

// TrackerService : Implements the tracker server interface definition.
type TrackerService struct {
	nodekeeper *builder.NodeKeeper
}

// Register : func
func (s *TrackerService) Register(ctx context.Context, in *pb.Node) (*pb.Node, error) {
	log.Printf("Received register request from node: %v", in.GetIp())
	return &pb.Node{Ip: in.GetIp(), Id: 1}, nil
}

// Search : func
func (s *TrackerService) Search(in *pb.SearchRequest, stream pb.TrackerService_SearchServer) error {
	log.Printf("Received search request: %v", in.GetSource().GetIp())
	return nil
}
