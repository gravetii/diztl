package main

import (
	"fmt"
	"net"

	"github.com/gravetii/diztl/startup"

	"github.com/gravetii/diztl/conf"
	"github.com/gravetii/diztl/diztl"
	"github.com/gravetii/diztl/logger"
	"github.com/gravetii/go-figure"
	"google.golang.org/grpc"

	"github.com/gravetii/diztl/client"
	"github.com/gravetii/diztl/service"
)

func main() {
	// Execute the initial startup steps
	startup.Execute()

	lis, err := net.Listen("tcp", ":"+conf.NodePort())
	if err != nil {
		logger.Log.Fatalf("Unable to start node server: %v", err)
	}

	s := grpc.NewServer()
	node := service.NewNode()
	diztl.RegisterDiztlServiceServer(s, node)
	node.Init()
	client.Init()
	logger.Log.Println("Started node server...")
	displayBanner()
	client.StartCLI()
	serr := s.Serve(lis)
	if serr != nil {
		logger.Log.Fatalf("Failed to serve: %v", err)
	}
}

func displayBanner() {
	figure := figure.NewFigure("DIZTL", conf.BannerFont(), true)
	figure.Print()
	fmt.Printf("\n\nYou are now online!\n\n")
}
