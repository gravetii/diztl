package client

import (
	"bufio"
	"context"
	"io"
	"log"
	"os"
	"time"

	"github.com/gravetii/diztl/builder"

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
	nk := builder.NewNodeKeeper()
	nodeclient = &NodeClient{nodekeeper: nk}
	tracker := connectToTracker()
	nodeclient.tracker = tracker
	nodeclient.register()
	log.Println("Successfully initialised nodeclient.")
	log.Println("\nEnter a search...")
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

func (c *NodeClient) download(r *pb.DownloadRequest) error {
	ctx, cancel := context.WithTimeout(context.Background(), downloadTimeout)
	defer cancel()
	client, err := c.nodekeeper.GetConnection(r.GetSource())
	if err != nil {
		log.Fatalf("Could not connect to node %s: %v", r.GetSource().GetIp(), err)
		return err
	}

	stream, _ := client.Upload(ctx, r)
	var buf *bufio.Writer
	var obj *os.File
	var metadata *diztl.FileMetadata

	for {
		f, err := stream.Recv()
		if err != nil {
			if err == io.EOF {
				buf.Flush()
				obj.Close()
				log.Printf("Finished downloading file: %s", metadata.GetName())
				break
			}

			return err
		}

		if f.GetChunk() == 1 {
			metadata = f.GetMetadata()
			fpath := util.GetOutputPath(metadata.GetName())
			obj, err := createFile(fpath)
			if err != nil {
				return err
			}

			buf = bufio.NewWriter(obj)
			log.Printf("Downloading file: %s\n", obj.Name())
		} else {
			_, err := buf.Write(f.GetData())
			if err != nil {
				return err
			}
		}
	}

	return nil
}

func createFile(fpath string) (*os.File, error) {
	f, err := os.Create(fpath)
	if err != nil {
		log.Fatalf("Unable to create file %s: %v", fpath, err)
		return nil, err
	}

	return f, nil
}
