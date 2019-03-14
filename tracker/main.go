package main

import (
	"log"
	"net"

	pb "github.com/gravetii/diztl/diztl"
	nk "github.com/gravetii/diztl/nodekeeper"
	"github.com/gravetii/diztl/trackerservice"
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
	nodekeeper := &nk.NodeKeeper{}
	pb.RegisterTrackerServiceServer(s, &trackerservice.TrackerService{"nodekeeper": nodekeeper})
	serr := s.Serve(lis)
	if serr != nil {
		log.Fatalf("Failed to serve: %v", err)
	}
}
