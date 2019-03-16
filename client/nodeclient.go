package client

import (
	"context"
	"io"
	"log"
	"time"

	"github.com/gravetii/diztl/diztl"
	pb "github.com/gravetii/diztl/diztl"
	"github.com/gravetii/diztl/util"
	"google.golang.org/grpc"
)

var nodeclient *NodeClient

// NodeClient : This struct enables communication with the tracker and/or other nodes.
type NodeClient struct {
	node    *diztl.Node
	tracker diztl.TrackerServiceClient
}

func connectToTracker() diztl.TrackerServiceClient {
	trackerHost := util.GetMyIP()
	trackerAddr := trackerHost + ":50052"
	conn, err := grpc.Dial(trackerAddr, grpc.WithInsecure())
	if err != nil {
		log.Fatalf("Could not connect to tracker: %v", err)
		panic(err)
	}

	log.Println("Successfully connected to tracker...")
	return pb.NewTrackerServiceClient(conn)
}

// Init : Initialises the NodeClient.
func Init() {
	log.Println("Initialising nodeclient...")
	nodeclient = &NodeClient{}
	tracker := connectToTracker()
	nodeclient.tracker = tracker
	nodeclient.register()
	log.Println("Successfully initialised nodeclient.")
	log.Println("\nEnter a search...")
	ReadUserInput()
}

func (c *NodeClient) register() {
	ip := util.GetMyIP()
	node := &diztl.Node{Ip: ip}
	ctx, cancel := context.WithTimeout(context.Background(), time.Second)
	defer cancel()
	rnode, err := c.tracker.Register(ctx, node)
	if err != nil {
		log.Fatalf("Error while registering node to tracker: %v", err)
		panic(err)
	}

	c.node = &diztl.Node{Ip: rnode.GetIp(), Id: rnode.GetId()}
	log.Printf("Successfully registered node to tracker: %s, %d", rnode.GetIp(), rnode.GetId())
}

// Search : Search for files on the network that have names with the given pattern.
func (c *NodeClient) Search(pattern string) ([]*diztl.SearchResponse, error) {
	results := []*diztl.SearchResponse{}
	log.Printf("Searching for pattern: %s", pattern)
	r := diztl.SearchRequest{Filename: pattern, Source: c.node}
	ctx, cancel := context.WithTimeout(context.Background(), time.Second)
	defer cancel()
	stream, _ := c.tracker.Search(ctx, &r)
	for {
		resp, err := stream.Recv()
		if err == io.EOF {
			break
		}
		if err != nil {
			log.Fatalf("Error while reading search response from another node: %v", err)
			continue
		}

		results = append(results, resp)
	}

	return results, nil
}
