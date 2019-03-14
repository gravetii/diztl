package service

import (
	"context"
	"log"

	"github.com/gravetii/diztl/builder"
	"github.com/gravetii/diztl/diztl"
)

// TrackerService : Implements the tracker server interface definition.
type TrackerService struct {
	nodekeeper *builder.NodeKeeper
}

// Register : Every node invokes this method on the tracker to register itself.
func (s *TrackerService) Register(ctx context.Context, node *diztl.Node) (*diztl.Node, error) {
	log.Printf("Received register request from node: %v", node.GetIp())
	s.nodekeeper.Register(node)
	return node, nil
}

// Search : Invoked by a search request by any node.
func (s *TrackerService) Search(request *diztl.SearchRequest, stream diztl.TrackerService_SearchServer) error {
	log.Printf("Received search request: %v", request.GetSource().GetIp())
	return nil
}

func broadcast(request *diztl.SearchRequest) {
	log.Printf("Broadcasting search request: %v", request.GetSource().GetIp())
	//todo
}
