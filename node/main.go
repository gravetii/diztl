package main

import (
	"context"
	"log"
	"net"

	pb "github.com/gravetii/diztl/diztl"
	"google.golang.org/grpc"
)

const (
	port = ":50051"
)

type nodeservice struct{}

func (s *nodeservice) Search(ctx context.Context, in *pb.SearchRequest) (*pb.SearchResponse, error) {
	log.Printf("Received search request: %v", in.GetSource())
	return &pb.SearchResponse{Count: 1, Files: nil, Node: nil}, nil
}

func (s *nodeservice) Upload(in *pb.DownloadRequest, stream pb.DiztlService_UploadServer) error {
	return nil
}

func main() {
	lis, err := net.Listen("tcp", port)
	if err != nil {
		log.Fatalf("Unable to start node server: %v", err)
	}

	s := grpc.NewServer()
	pb.RegisterDiztlServiceServer(s, &nodeservice{})
	serr := s.Serve(lis)
	if serr != nil {
		log.Fatalf("Failed to serve: %v", err)
	}
}
