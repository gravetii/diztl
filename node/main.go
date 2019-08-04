package main

import (
	"fmt"
	"net"

	"github.com/gravetii/diztl/addr"

	"github.com/gravetii/diztl/startup"

	"github.com/gravetii/diztl/conf"
	"github.com/gravetii/diztl/diztl"
	"google.golang.org/grpc"

	"github.com/gravetii/diztl/service"
	"github.com/gravetii/logger"
)

func main() {
	// Execute the initial startup steps
	startup.Execute()

	lis, err := net.Listen("tcp", ":"+conf.NodePort())
	if err != nil {
		logger.Errorf("Unable to start node - %v\n", err)
		return
	}

	s := grpc.NewServer()
	node := service.NewNode()
	diztl.RegisterDiztlServiceServer(s, node)
	node.Init()
	logger.Infof("Node %s is now up...\n", addr.LocalIP())
	fmt.Printf("You are now online!\n")
	serr := s.Serve(lis)
	if serr != nil {
		logger.Errorf("Failed to serve - %v\n", err)
		return
	}
}
