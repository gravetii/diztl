package main

import (
	"context"
	"log"
	"net"

	pb "github.com/gravetii/diztl/diztl"
	"google.golang.org/grpc"
)

const (
	port = ":50052"
)

// server is used to implement diztl.TrackerServer
type server struct{}

func (s *server) Register(ctx context.Context, in *pb.Node) (*pb.Node, error) {
	log.Printf("Received register request: %v", in.GetIp())
	return &pb.Node{Ip: in.GetIp(), Id: 1}, nil
}

func (s *server) Search(in *pb.SearchRequest, stream pb.TrackerService_SearchServer) error {
	log.Printf("Received search request: %v", in.GetSource().GetIp())
	return nil
}

func main() {
	lis, err := net.Listen("tcp", port)
	if err != nil {
		log.Fatalf("Unable to start server :%v", err)
	}

	s := grpc.NewServer()
	pb.RegisterTrackerServiceServer(s, &server{})
	serr := s.Serve(lis)
	if serr != nil {
		log.Fatalf("Failed to serve: %v", err)
	}
}
