package main

import (
	"log"
	"net"

	pb "github.com/gravetii/diztl/diztl"
	"github.com/gravetii/diztl/nodeservice"
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
	pb.RegisterDiztlServiceServer(s, &nodeservice.NodeService{})
	serr := s.Serve(lis)
	if serr != nil {
		log.Fatalf("Failed to serve: %v", err)
	}
}
