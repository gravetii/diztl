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

func index() {
	ixer := builder.FileIndexer{}
	log.Println("Indexing files...")
	ixer.Index()
	log.Println("Finished indexing files...")
}

func main() {
	lis, err := net.Listen("tcp", port)
	if err != nil {
		log.Fatalf("Unable to start node server: %v", err)
	}

	s := grpc.NewServer()
	pb.RegisterDiztlServiceServer(s, &service.NodeService{})

	// Index share directory here.
	go index()

	serr := s.Serve(lis)
	if serr != nil {
		log.Fatalf("Failed to serve: %v", err)
	}
}
