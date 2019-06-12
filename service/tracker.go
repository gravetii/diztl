package service

import (
	"context"
	"os"

	"github.com/gravetii/diztl/conf"

	"github.com/gravetii/diztl/shutdown"

	"github.com/gravetii/diztl/diztl"
	"github.com/gravetii/diztl/keeper"
	"github.com/gravetii/diztl/logger"
)

// TrackerService : Implements the tracker server interface definition.
type TrackerService struct {
	nk *keeper.NodeKeeper
}

// NewTracker : Returns an instance of the Tracker Service.
func NewTracker() *TrackerService {
	nk := keeper.New()
	tracker := &TrackerService{nk}
	shutdown.Listen(tracker)
	return tracker
}

// Register : Every node that joins the network invokes this method on the tracker to register itself.
func (s *TrackerService) Register(ctx context.Context, req *diztl.RegisterReq) (*diztl.RegisterResp, error) {
	node := req.GetNode()
	s.nk.Register(node)
	resp := &diztl.RegisterResp{Node: node}
	return resp, nil
}

// Search : Invoked by a search request by any node.
func (s *TrackerService) Search(request *diztl.SearchReq, stream diztl.TrackerService_SearchServer) error {
	logger.Log.Printf("Received search request from node %s: %v\n", request.GetSource().GetIp(), *request)
	responses := s.broadcast(request)
	for _, r := range responses {
		stream.Send(r)
	}

	return nil
}

// Disconnect : A disconnecting node invokes this call on the tracker before leaving the network.
func (s *TrackerService) Disconnect(ctx context.Context, request *diztl.DisconnectReq) (*diztl.DisconnectResp, error) {
	node := request.GetNode()
	logger.Log.Printf("Received disconnect request from node %s\n", node.GetIp())
	if !s.nk.Disconnect(node) {
		logger.Log.Printf("Disconnect returned false for %s\n", node.GetIp())
	}

	return &diztl.DisconnectResp{}, nil
}

func (s *TrackerService) broadcast(request *diztl.SearchReq) []*diztl.SearchResp {
	logger.Log.Printf("Broadcasting search request to all nodes on the network: %s, %s\n", request.GetSource().GetIp(), request.GetFilename())
	responses := []*diztl.SearchResp{}

	for _, node := range s.nk.Nodes {
		c, err := s.nk.GetConnection(node)
		if err == nil {
			ctx, cancel := context.WithTimeout(context.Background(), conf.SearchTimeout())
			defer cancel()
			r, err := c.Search(ctx, request)
			if err != nil {
				logger.Log.Printf("Error while invoking Search on node %s: %v\n", node.GetIp(), err)
			} else if len(r.GetFiles()) > 0 {
				r.Node = node
				responses = append(responses, r)
			}
		} else {
			logger.Log.Printf("Could not connect to node %s: %v\n", node.GetIp(), err)
			continue
		}
	}

	return responses
}

// OnShutdown : Actions to perform on shutdown.
func (s *TrackerService) OnShutdown() {
	s.nk.Close()
	logger.Log.Println("Tracker shut down successfully.")
	os.Exit(0)
}
