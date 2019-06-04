package main

import (
	"fmt"
	"log"
	"net"

	"github.com/gravetii/diztl/conf"
	"github.com/gravetii/diztl/diztl"
	"google.golang.org/grpc"

	"github.com/gravetii/diztl/client"
	"github.com/gravetii/diztl/service"
)

func main() {
	lis, err := net.Listen("tcp", ":"+conf.NodePort())
	if err != nil {
		log.Fatalf("Unable to start node server: %v", err)
	}

	s := grpc.NewServer()
	node := service.NewNode()
	diztl.RegisterDiztlServiceServer(s, node)
	node.Init()
	client.Init()
	log.Println("Started node server...")
	fmt.Println("You are now online!")
	serr := s.Serve(lis)
	if serr != nil {
		log.Fatalf("Failed to serve: %v", err)
	}
}
