package main

import (
	"log"
	"net"

	builder "github.com/gravetii/diztl/builder"
	pb "github.com/gravetii/diztl/diztl"
	service "github.com/gravetii/diztl/service"
	"google.golang.org/grpc"
)

const (
	port = ":50052"
)

func main() {
	lis, err := net.Listen("tcp", port)
	if err != nil {
		log.Fatalf("Unable to start server :%v", err)
	}

	s := grpc.NewServer()
	nk := builder.NewNodeKeeper()
	pb.RegisterTrackerServiceServer(s, &service.TrackerService{Nodekeeper: nk})
	log.Printf("Server started on port %s", port)
	serr := s.Serve(lis)
	if serr != nil {
		log.Fatalf("Failed to serve: %v", err)
	}

	log.Println("Shutting down tracker server.")
}
