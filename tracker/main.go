package main

import (
	"fmt"
	"net"

	"github.com/gravetii/diztl/addr"
	"github.com/gravetii/diztl/conf"
	"github.com/gravetii/diztl/diztl"
	"github.com/gravetii/diztl/service"
	"github.com/gravetii/diztl/startup"
	"github.com/gravetii/logger"
	"google.golang.org/grpc"
)

const (
	configFile = "tracker-config.yml"
)

func main() {
	if err := conf.Load(configFile); err != nil {
		panic(err)
	}

	// Execute the initial startup steps
	startup.Execute()

	lis, err := net.Listen("tcp", ":"+conf.TrackerPort())
	if err != nil {
		logger.Errorf("Unable to start tracker - %v\n", err)
	}

	s := grpc.NewServer()
	ip := addr.LocalIP()
	diztl.RegisterTrackerServiceServer(s, service.NewTracker(s))
	logger.Infof("Tracker is up and running - %s:%s\n", ip, conf.TrackerPort())
	fmt.Printf("Tracker is running on %s:%s\n", ip, conf.TrackerPort())
	serr := s.Serve(lis)
	if serr != nil {
		logger.Errorf("Failed to serve: %v\n", err)
		return
	}

	logger.Infof("Tracker server successfully shut down.\n")
}
