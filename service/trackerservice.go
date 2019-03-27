package service

import (
	"context"
	"log"

	"github.com/gravetii/diztl/builder"
	"github.com/gravetii/diztl/config"
	"github.com/gravetii/diztl/diztl"
)

// TrackerService : Implements the tracker server interface definition.
type TrackerService struct {
	Nodekeeper *builder.NodeKeeper
}

// Register : Every node that joins the network invokes this method on the tracker to register itself.
func (s *TrackerService) Register(ctx context.Context, node *diztl.Node) (*diztl.Node, error) {
	s.Nodekeeper.Register(node)
	return node, nil
}

// Search : Invoked by a search request by any node.
func (s *TrackerService) Search(request *diztl.SearchRequest, stream diztl.TrackerService_SearchServer) error {
	log.Printf("Received search request from node %s: %v\n", request.GetSource().GetIp(), *request)
	responses := s.broadcast(request)
	for _, r := range responses {
		stream.Send(r)
	}

	return nil
}

func (s *TrackerService) broadcast(request *diztl.SearchRequest) []*diztl.SearchResponse {
	log.Printf("Broadcasting search request to all nodes on the network: %s, %s\n", request.GetSource().GetIp(), request.GetFilename())
	responses := []*diztl.SearchResponse{}

	for _, node := range s.Nodekeeper.Nodes {
		c, err := s.Nodekeeper.GetConnection(node)
		if err == nil {
			ctx, cancel := context.WithTimeout(context.Background(), config.SearchTimeout)
			defer cancel()
			r, err := c.Search(ctx, request)
			if err != nil {
				log.Printf("Error while invoking Search on node %s: %v\n", node.GetIp(), err)
			} else if r.Count > 0 {
				responses = append(responses, r)
			}
		} else {
			log.Printf("Could not connect to node %s: %v\n", node.GetIp(), err)
			continue
		}
	}

	return responses
}
