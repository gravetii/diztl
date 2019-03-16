package service

import (
	"context"
	"log"
	"time"

	"github.com/gravetii/diztl/builder"
	"github.com/gravetii/diztl/diztl"
)

// TrackerService : Implements the tracker server interface definition.
type TrackerService struct {
	Nodekeeper *builder.NodeKeeper
}

// Register : Every node that joins the network invokes this method on the tracker to register itself.
func (s *TrackerService) Register(ctx context.Context, node *diztl.Node) (*diztl.Node, error) {
	s.Nodekeeper.Register(node)
	log.Printf("Successfully registered node %s, %d", node.GetIp(), node.GetId())
	return node, nil
}

// Search : Invoked by a search request by any node.
func (s *TrackerService) Search(request *diztl.SearchRequest, stream diztl.TrackerService_SearchServer) error {
	log.Printf("Received search request: %v", request.GetSource().GetIp())
	responses := s.broadcast(request)
	log.Printf("Got responses from broadcast request as: %v", responses)
	for _, r := range responses {
		stream.Send(r)
	}

	return nil
}

func (s *TrackerService) broadcast(request *diztl.SearchRequest) []*diztl.SearchResponse {
	log.Printf("Broadcasting search request to all nodes on the network: %s, %s", request.GetSource().GetIp(), request.GetFilename())
	responses := []*diztl.SearchResponse{}

	for _, node := range s.Nodekeeper.ActiveNodes {
		c, err := s.Nodekeeper.GetConnection(*node)
		if err != nil {
			log.Printf("Could not connect to node %s: %v", node.GetIp(), err)
		} else {
			ctx, cancel := context.WithTimeout(context.Background(), time.Second)
			defer cancel()
			r, err := c.Search(ctx, request)
			if err != nil {
				log.Fatalf("Error while invoking Search on node %s: %v", node.GetIp(), err)
			} else if r.Count > 0 {
				responses = append(responses, r)
			}
		}
	}

	return responses
}
