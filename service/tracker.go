package service

import (
	"context"
	"os"

	"github.com/gravetii/diztl/conf"

	"github.com/gravetii/diztl/shutdown"

	"github.com/gravetii/diztl/diztl"
	"github.com/gravetii/diztl/keeper"
	"github.com/gravetii/logger"
)

// TrackerService : Implements the tracker server interface definition.
type TrackerService struct {
	nk *keeper.NodeKeeper
}

// NewTracker : Returns an instance of the Tracker Service.
func NewTracker() *TrackerService {
	nk := keeper.New()
	tracker := &TrackerService{nk: nk}
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
func (s *TrackerService) Search(req *diztl.SearchReq, stream diztl.TrackerService_SearchServer) error {
	logger.Debugf("Received search request from node %s: %v\n", req.GetSource().GetIp(), *req)
	resp := make(chan *diztl.SearchResp)
	go func() {
		for _, node := range s.nk.Nodes {
			s.ask(req, node, resp)
		}

		close(resp)
	}()

	for r := range resp {
		if r != nil {
			stream.Send(r)
		}
	}

	return nil
}

// Disconnect : A disconnecting node invokes this call on the tracker before leaving the network.
func (s *TrackerService) Disconnect(ctx context.Context, request *diztl.DisconnectReq) (*diztl.DisconnectResp, error) {
	node := request.GetNode()
	logger.Debugf("Received disconnect request from node %s\n", node.GetIp())
	if !s.nk.Disconnect(node) {
		logger.Warnf("Disconnect returned false for %s\n", node.GetIp())
	}

	return &diztl.DisconnectResp{}, nil
}

func (s *TrackerService) ask(request *diztl.SearchReq, node *diztl.Node, resp chan *diztl.SearchResp) {
	c, err := s.nk.GetConnection(node)
	if err == nil {
		ctx, cancel := context.WithTimeout(context.Background(), conf.SearchTimeout())
		defer cancel()
		r, err := c.Search(ctx, request)
		if err != nil {
			logger.Errorf("Error while invoking Search on node %s - %v\n", node.GetIp(), err)
		} else if len(r.GetFiles()) > 0 {
			resp <- r
		}
	} else {
		logger.Warnf("Could not connect to node from tracker: %s - %v\n", node.GetIp(), err)
	}
}

// OnShutdown : Actions to perform on shutdown.
func (s *TrackerService) OnShutdown() {
	s.nk.Close()
	logger.Infof("Tracker shut down successfully.\n")
	os.Exit(0)
}
