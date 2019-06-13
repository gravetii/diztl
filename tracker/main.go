package main

import (
	"net"

	"github.com/gravetii/diztl/addr"
	"github.com/gravetii/diztl/conf"
	"github.com/gravetii/diztl/diztl"
	"github.com/gravetii/diztl/logger"
	"github.com/gravetii/diztl/service"
	"github.com/gravetii/diztl/startup"
	"google.golang.org/grpc"
)

func main() {
	// Execute the initial startup steps
	startup.Execute()

	lis, err := net.Listen("tcp", ":"+conf.TrackerPort())
	if err != nil {
		logger.Log.Fatalf("Unable to start server :%v", err)
	}

	s := grpc.NewServer()
	ip := addr.GetMyIP()
	diztl.RegisterTrackerServiceServer(s, service.NewTracker())
	logger.Log.Printf("Server started on %s:%s\n", ip, conf.TrackerPort())
	serr := s.Serve(lis)
	if serr != nil {
		logger.Log.Fatalf("Failed to serve: %v\n", err)
	}

	logger.Log.Println("Shutting down tracker server.")
}
