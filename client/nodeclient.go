package client

import (
	"context"
	"fmt"
	"io"
	"os"
	"time"

	"github.com/gravetii/diztl/addr"
	"github.com/gravetii/diztl/conf"
	"github.com/gravetii/diztl/shutdown"

	"github.com/gravetii/diztl/file"
	"github.com/gravetii/diztl/keeper"

	"github.com/gravetii/diztl/diztl"
	"github.com/gravetii/diztl/logger"
	"google.golang.org/grpc"
)

var nodeclient *NodeClient

// NodeClient : This struct enables communication with the tracker and/or other nodes.
type NodeClient struct {
	node        *diztl.Node
	trackerConn *grpc.ClientConn
	nk          *keeper.NodeKeeper
}

func (c *NodeClient) connectToTracker() {
	conn, err := grpc.Dial(conf.TrackerAddress(), grpc.WithInsecure(),
		grpc.WithBlock(), grpc.WithTimeout(conf.TrackerConnectTimeout()))
	if err != nil {
		logger.Log.Fatalf("Could not connect to tracker: %v", err)
	}

	c.trackerConn = conn
	logger.Log.Println("Successfully connected to tracker...")
}

func (c *NodeClient) tracker() diztl.TrackerServiceClient {
	return diztl.NewTrackerServiceClient(c.trackerConn)
}

// Init initialises the NodeClient.
func Init() {
	logger.Log.Println("Initialising nodeclient...")
	nk := keeper.New()
	nodeclient = &NodeClient{nk: nk}
	nodeclient.connectToTracker()
	nodeclient.register()
	logger.Log.Println("Finished initialising nodeclient.")
	shutdown.Listen(nodeclient)
}

// StartCLI starts the input loop to take user requests.
func StartCLI() {
	go userCLI()
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
	t := c.tracker()
	resp, err := t.Register(ctx, req)
	if err != nil {
		logger.Log.Fatalf("Error while registering node to tracker: %v", err)
	}

	rnode := resp.GetNode()
	c.node = &diztl.Node{Ip: rnode.GetIp(), Id: rnode.GetId()}
	logger.Log.Printf("Successfully registered node to tracker: %s, %s\n", rnode.GetIp(), rnode.GetId())
}

func (c *NodeClient) disconnect() {
	ctx, cancel := context.WithTimeout(context.Background(), conf.DisconnectTimeout())
	defer cancel()
	req := diztl.DisconnectReq{Node: c.node}
	t := c.tracker()
	_, err := t.Disconnect(ctx, &req)
	if err != nil {
		logger.Log.Fatalf("Error while disconnecting: %v", err)
	}

	fmt.Println("\nBye!")
}

// Search searches for files on the network that have names with the given pattern.
func (c *NodeClient) Search(pattern string) ([]*diztl.SearchResp, error) {
	results := []*diztl.SearchResp{}
	logger.Log.Printf("Searching for pattern: %s\n", pattern)
	r := diztl.SearchReq{Filename: pattern, Source: c.node}
	ctx, cancel := context.WithTimeout(context.Background(), conf.SearchTimeout())
	defer cancel()
	t := c.tracker()
	stream, err := t.Search(ctx, &r)
	if err != nil {
		return nil, err
	}
	for {
		resp, err := stream.Recv()
		if err != nil {
			if err != io.EOF {
				logger.Log.Printf("Error while reading search response from tracker\n: %v", err)
			}

			break
		}

		results = append(results, resp)
	}

	return results, nil
}

// Ping pings another node to see if it's currently active.
func (c *NodeClient) Ping(node *diztl.Node) (*diztl.PingResp, error) {
	ctx, cancel := context.WithTimeout(context.Background(), conf.PingTimeout())
	defer cancel()
	client, err := c.nk.GetConnection(node)
	if err != nil {
		return nil, err
	}

	req := diztl.PingReq{Source: c.node, Dest: node}
	return client.Ping(ctx, &req)
}

func (c *NodeClient) download(r *diztl.DownloadReq) (*os.File, error) {
	ctx, cancel := context.WithTimeout(context.Background(), conf.DownloadTimeout())
	defer cancel()
	client, err := c.nk.GetConnection(r.GetSource())
	if err != nil {
		return nil, err
	}

	stream, err := client.Upload(ctx, r)
	if err != nil {
		logger.Log.Printf("Download failed due to error in sender host: %v\n", err)
		fmt.Println("Download failed. It's not you, it's them.")
		return nil, err
	}

	var w *file.Writer

	for {
		fc, err := stream.Recv()
		if err != nil {
			if err == io.EOF {
				f, serr := w.Close()
				if serr != nil {
					return nil, serr
				}

				return f, nil
			}

			return nil, err
		}

		if fc.GetChunk() == 1 {
			w, err = file.CreateWriter(fc.GetMetadata())
			if err != nil {
				return nil, err
			}

			logger.Log.Printf("Downloading file: %s. Prepared to receive %d chunks.\n", fc.GetMetadata().GetName(), w.Chunks())
		}

		if err := w.Write(fc.GetData()); err != nil {
			return nil, err
		}
	}
}
