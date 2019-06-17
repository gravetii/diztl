package main

import (
	"net"

	"github.com/gravetii/diztl/addr"
	"github.com/gravetii/diztl/conf"
	"github.com/gravetii/diztl/diztl"
	"github.com/gravetii/diztl/service"
	"github.com/gravetii/diztl/startup"
	"github.com/gravetii/logger"
	"google.golang.org/grpc"
)

func main() {
	// Execute the initial startup steps
	startup.Execute()

	lis, err := net.Listen("tcp", ":"+conf.TrackerPort())
	if err != nil {
		logger.Errorf("Unable to start tracker - %v\n", err)
	}

	s := grpc.NewServer()
	ip := addr.LocalIP()
	diztl.RegisterTrackerServiceServer(s, service.NewTracker())
	logger.Infof("Tracker is up and running - %s:%s\n", ip, conf.TrackerPort())
	serr := s.Serve(lis)
	if serr != nil {
		logger.Errorf("Failed to serve: %v\n", err)
		return
	}

	logger.Infof("Shutting down tracker server.\n")
}
