package service

import (
	"context"
	"log"
	"time"

	"github.com/gravetii/diztl/builder"
	"github.com/gravetii/diztl/diztl"
	pb "github.com/gravetii/diztl/diztl"
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
	return nil
}

func (s *TrackerService) broadcast(request *diztl.SearchRequest) []diztl.SearchResponse {
	log.Printf("Broadcasting search request to all nodes on the network: %s", request.GetSource().GetIp())
	responses := []diztl.SearchResponse{}

	for _, node := range s.Nodekeeper.ActiveNodes {
		conn, err := s.Nodekeeper.GetConnection(*node)
		if err != nil {
			panic(err)
		}

		ctx, cancel := context.WithTimeout(context.Background(), time.Second)
		defer cancel()
		r, err := pb.NewDiztlServiceClient(conn).Search(ctx, request)
		if err != nil {
			log.Fatalf("Could not call search on node %s: %v", node.GetIp(), err)
		} else if r.Count > 0 {
			responses = append(responses, *r)
		}
	}

	return responses
}
