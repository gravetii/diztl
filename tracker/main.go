package main

import (
	"log"
	"net"

	builder "github.com/gravetii/diztl/builder"
	"github.com/gravetii/diztl/config"
	pb "github.com/gravetii/diztl/diztl"
	service "github.com/gravetii/diztl/service"
	"google.golang.org/grpc"
)

func main() {
	lis, err := net.Listen("tcp", ":"+config.TrackerPort)
	if err != nil {
		log.Fatalf("Unable to start server :%v", err)
	}

	s := grpc.NewServer()
	nk := builder.NewNodeKeeper()
	pb.RegisterTrackerServiceServer(s, &service.TrackerService{Nodekeeper: nk})
	log.Printf("Server started on port: %s", config.TrackerPort)
	serr := s.Serve(lis)
	if serr != nil {
		log.Fatalf("Failed to serve: %v", err)
	}

	log.Println("Shutting down tracker server.")
}
