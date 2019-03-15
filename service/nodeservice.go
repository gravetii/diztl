package service

import (
	"context"
	"log"
	"net"
	"time"

	"github.com/gravetii/diztl/builder"
	"github.com/gravetii/diztl/diztl"
	pb "github.com/gravetii/diztl/diztl"
	"google.golang.org/grpc"
)

// NodeService : Implements the node server interface definition.
type NodeService struct {
	Indexer *builder.FileIndexer
	node    *diztl.Node
	tracker *grpc.ClientConn
}

func getMyIP() string {
	addrs, _ := net.InterfaceAddrs()

	ip := "127.0.0.1"
	for _, a := range addrs {
		if ipnet, ok := a.(*net.IPNet); ok && !ipnet.IP.IsLoopback() {
			if ipnet.IP.To4() != nil {
				ip = ipnet.IP.String()
			}
		}
	}

	log.Printf("Got local IP as %s", ip)
	return ip
}

func (s *NodeService) connectToTracker() {
	ip := getMyIP()

	addr := ip + ":50052"
	conn, err := grpc.Dial(addr, grpc.WithInsecure())
	if err != nil {
		log.Fatalf("Could not connect to tracker: %v", err)
	}

	s.tracker = conn
	log.Println("Successfully connected to tracker.")
}

// Register this node to the tracker.
func (s *NodeService) register() {
	ip := getMyIP()
	node := &diztl.Node{Ip: ip}
	ctx, cancel := context.WithTimeout(context.Background(), time.Second)
	defer cancel()
	rnode, err := pb.NewTrackerServiceClient(s.tracker).Register(ctx, node)
	if err != nil {
		log.Fatalf("Error while registering node to tracker: %v", err)
		panic(err)
	}

	s.node = &diztl.Node{Ip: rnode.GetIp(), Id: rnode.GetId()}
	log.Printf("Successfully registered node to tracker: %s, %d", s.node.GetIp(), s.node.GetId())
}

// Init : Performs the necessary initialisation when the service comes up for the first time.
func (s *NodeService) Init() {
	log.Println("Initialising node service...")
	s.Indexer.Index()
	s.connectToTracker()
	s.register()
	log.Println("Finished initialising client...")
}

// Search : func
func (s *NodeService) Search(ctx context.Context, request *diztl.SearchRequest) (*diztl.SearchResponse, error) {
	log.Printf("Received search request: %v", request.GetSource())
	files := s.Indexer.Search(request.GetFilename())
	// todo - check explicit conversion from int to int32
	response := diztl.SearchResponse{Count: int32(len(files)), Files: files}
	return &response, nil
}

// Upload : func
func (s *NodeService) Upload(in *diztl.DownloadRequest, stream diztl.DiztlService_UploadServer) error {
	return nil
}
