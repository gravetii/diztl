package main

import (
	"log"
	"net"

	"github.com/gravetii/diztl/builder"

	pb "github.com/gravetii/diztl/diztl"
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
	ns := service.NodeService{Indexer: &builder.FileIndexer{}}
	pb.RegisterDiztlServiceServer(s, &ns)
	go ns.Init()
	serr := s.Serve(lis)
	if serr != nil {
		log.Fatalf("Failed to serve: %v", err)
	}
}
