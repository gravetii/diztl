package main

import (
	"log"
	"net"

	"github.com/gravetii/diztl/builder"

	"github.com/gravetii/diztl/client"
	diztl "github.com/gravetii/diztl/diztl"
	"github.com/gravetii/diztl/service"
	"google.golang.org/grpc"
)

const (
	port = ":50051"
)

func main() {
	lis, err := net.Listen("tcp", port)
	if err != nil {
		log.Fatalf("Unable to start node server: %v", err)
	}

	s := grpc.NewServer()
	nodeservice := service.NodeService{Indexer: &builder.FileIndexer{}}
	diztl.RegisterDiztlServiceServer(s, &nodeservice)
	go nodeservice.Init()
	go client.Init()
	log.Println("Started node server...")
	serr := s.Serve(lis)
	if serr != nil {
		log.Fatalf("Failed to serve: %v", err)
	}
}
