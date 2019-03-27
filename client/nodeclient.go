package client

import (
	"context"
	"fmt"
	"io"
	"log"
	"os"
	"time"

	"github.com/gravetii/diztl/builder"
	"github.com/gravetii/diztl/config"
	"github.com/gravetii/diztl/file"

	"github.com/gravetii/diztl/diztl"
	pb "github.com/gravetii/diztl/diztl"
	"github.com/gravetii/diztl/util"
	"google.golang.org/grpc"
)

var nodeclient *NodeClient

// NodeClient : This struct enables communication with the tracker and/or other nodes.
type NodeClient struct {
	node       *diztl.Node
	tracker    diztl.TrackerServiceClient
	nodekeeper *builder.NodeKeeper
}

func (c *NodeClient) connectToTracker() {
	conn, err := grpc.Dial(config.TrackerAddress, grpc.WithInsecure(),
		grpc.WithBlock(), grpc.WithTimeout(config.TrackerConnectTimeout))
	if err != nil {
		log.Fatalf("Could not connect to tracker: %v", err)
	}

	log.Println("Successfully connected to tracker...")
	c.tracker = pb.NewTrackerServiceClient(conn)
}

// Init : Initialises the NodeClient.
func Init() {
	log.Println("Initialising nodeclient...")
	nk := builder.NewNodeKeeper()
	nodeclient = &NodeClient{nodekeeper: nk}
	nodeclient.connectToTracker()
	nodeclient.register()
	log.Println("Successfully initialised nodeclient.")
	go UserCLI()
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

func (c *NodeClient) download(r *pb.DownloadRequest) (*os.File, error) {
	ctx, cancel := context.WithTimeout(context.Background(), config.DownloadTimeout)
	defer cancel()
	client, err := c.nodekeeper.GetConnection(r.GetSource())
	if err != nil {
		log.Fatalf("Could not connect to node %s: %v", r.GetSource().GetIp(), err)
		return nil, err
	}

	stream, _ := client.Upload(ctx, r)
	var w *file.Writer

	for {
		f, err := stream.Recv()
		if err != nil {
			if err == io.EOF {
				return w.Close(), nil
			}

			return nil, err
		}

		if f.GetChunk() == 1 {
			w, err = file.CreateWriter(f.GetMetadata())
			if err != nil {
				return nil, err
			}

			log.Printf("Downloading file: %s. Prepared to receive %d chunks.", w.Name(), w.Chunks())
		}

		if err := w.Write(f.GetData()); err != nil {
			return nil, err
		}

		logProg(f.GetChunk(), w.Chunks())
	}
}

func logProg(chunk int32, chunks int32) {
	if chunk == chunks {
		fmt.Println("..")
	} else {
		c := chunks / 10
		if c != 0 {
			if chunk%c == 0 {
				fmt.Print("..")
			}
		}
	}
}
