package client

import (
	"context"
	"fmt"
	"io"
	"log"
	"os"
	"time"

	"github.com/gravetii/diztl/addr"
	"github.com/gravetii/diztl/shutdown"

	"github.com/gravetii/diztl/config"
	"github.com/gravetii/diztl/file"
	"github.com/gravetii/diztl/keeper"

	"github.com/gravetii/diztl/diztl"
	pb "github.com/gravetii/diztl/diztl"
	"google.golang.org/grpc"
)

var nodeclient *NodeClient

// NodeClient : This struct enables communication with the tracker and/or other nodes.
type NodeClient struct {
	node    *diztl.Node
	tracker diztl.TrackerServiceClient
	nk      *keeper.NodeKeeper
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
	nk := keeper.New()
	nodeclient = &NodeClient{nk: nk}
	nodeclient.connectToTracker()
	nodeclient.register()
	log.Println("Finished initialising nodeclient.")
	shutdown.Listen(nodeclient)
	go UserCLI()
}

// OnShutdown : Actions to perform on shutdown.
func (c *NodeClient) OnShutdown() {
	c.nk.Close()
	c.disconnect()
	os.Exit(0)
}

func (c *NodeClient) register() {
	ip := addr.GetMyIP()
	node := &diztl.Node{Ip: ip}
	req := &diztl.RegisterReq{Node: node}
	ctx, cancel := context.WithTimeout(context.Background(), time.Second)
	defer cancel()
	resp, err := c.tracker.Register(ctx, req)
	if err != nil {
		log.Fatalf("Error while registering node to tracker: %v", err)
	}

	rnode := resp.GetNode()
	c.node = &diztl.Node{Ip: rnode.GetIp(), Id: rnode.GetId()}
	log.Printf("Successfully registered node to tracker: %s, %s\n", rnode.GetIp(), rnode.GetId())
}

func (c *NodeClient) disconnect() {
	ctx, cancel := context.WithTimeout(context.Background(), config.DisconnectTimeout)
	defer cancel()
	req := diztl.DisconnectReq{Node: c.node}
	_, err := c.tracker.Disconnect(ctx, &req)
	if err != nil {
		log.Fatalf("Error while disconnecting: %v", err)
	}

	fmt.Println("\nBye!")
}

// Search : Search for files on the network that have names with the given pattern.
func (c *NodeClient) Search(pattern string) ([]*diztl.SearchResp, error) {
	results := []*diztl.SearchResp{}
	log.Printf("Searching for pattern: %s\n", pattern)
	r := diztl.SearchReq{Filename: pattern, Source: c.node}
	ctx, cancel := context.WithTimeout(context.Background(), time.Second)
	defer cancel()
	stream, err := c.tracker.Search(ctx, &r)
	if err != nil {
		return nil, err
	}
	for {
		resp, err := stream.Recv()
		if err == io.EOF {
			break
		}
		if err != nil {
			log.Printf("Error while reading search response from another node\n: %v", err)
			continue
		}

		results = append(results, resp)
	}

	return results, nil
}

func (c *NodeClient) download(r *pb.DownloadReq) (*os.File, error) {
	ctx, cancel := context.WithTimeout(context.Background(), config.DownloadTimeout)
	defer cancel()
	client, err := c.nk.GetConnection(r.GetSource())
	if err != nil {
		log.Printf("Could not connect to node %s: %v\n", r.GetSource().GetIp(), err)
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

			log.Printf("Downloading file: %s. Prepared to receive %d chunks.\n", w.Name(), w.Chunks())
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
