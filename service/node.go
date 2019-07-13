package service

import (
	"context"
	"fmt"
	"io"
	"os"
	"time"

	"github.com/gravetii/diztl/addr"
	"github.com/gravetii/diztl/conf"
	"github.com/gravetii/diztl/dir"
	"github.com/gravetii/diztl/keeper"
	"github.com/gravetii/diztl/shutdown"
	"google.golang.org/grpc"

	"github.com/gravetii/diztl/diztl"
	"github.com/gravetii/diztl/file"
	"github.com/gravetii/diztl/indexer"
	"github.com/gravetii/logger"
)

// NodeService implements the node server interface definition.
type NodeService struct {
	node        *diztl.Node
	Indexer     *indexer.FileIndexer
	trackerConn *grpc.ClientConn
	nk          *keeper.NodeKeeper
}

// NewNode returns an instance of the Node Service.
func NewNode() *NodeService {
	f, err := indexer.NewFileIndexer()
	if err != nil {
		logger.Errorf("Error while instantiating node - %v\n", err)
	}

	s := &NodeService{Indexer: f}
	return s
}

// Init performs the necessary initialisation when the service comes up for the first time.
func (s *NodeService) Init() error {
	logger.Debugf("Initialising node service...\n")
	if err := s.Indexer.Index(); err != nil {
		logger.Errorf("Error while indexing files: %v", err)
		return err
	}

	s.nk = keeper.New()
	s.connectToTracker()
	s.register()
	shutdown.Listen(s)
	logger.Debugf("Finished initialising node service.\n")
	return nil
}

func (s *NodeService) register() error {
	ip := addr.LocalIP()
	node := &diztl.Node{Ip: ip}
	req := &diztl.RegisterReq{Node: node}
	ctx, cancel := context.WithTimeout(context.Background(), time.Second)
	defer cancel()
	t := s.tracker()
	resp, err := t.Register(ctx, req)
	if err != nil {
		logger.Errorf("Error while registering node to tracker - %v\n", err)
		return err
	}

	rnode := resp.GetNode()
	s.node = &diztl.Node{Ip: rnode.GetIp(), Id: rnode.GetId()}
	logger.Infof("Successfully registered node to tracker: %s, %s\n", rnode.GetIp(), rnode.GetId())
	return nil
}

func (s *NodeService) tracker() diztl.TrackerServiceClient {
	return diztl.NewTrackerServiceClient(s.trackerConn)
}

func (s *NodeService) connectToTracker() error {
	conn, err := grpc.Dial(conf.TrackerAddress(), grpc.WithInsecure(),
		grpc.WithBlock(), grpc.WithTimeout(conf.TrackerConnectTimeout()))
	if err != nil {
		logger.Errorf("Couldn't connect to tracker - %v\n", err)
		return err
	}

	s.trackerConn = conn
	logger.Debugf("Successfully connected to tracker...\n")
	return nil
}

func (s *NodeService) disconnectFromTracker() error {
	ctx, cancel := context.WithTimeout(context.Background(), conf.DisconnectTimeout())
	defer cancel()
	req := diztl.DisconnectReq{Node: s.node}
	t := s.tracker()
	_, err := t.Disconnect(ctx, &req)
	if err != nil {
		logger.Errorf("Error while disconnecting node from tracker - %v\n", err)
		return err
	}

	fmt.Println("\nBye!")
	return nil
}

// OnShutdown defines actions to perform on node shutdown.
func (s *NodeService) OnShutdown() {
	if err := s.Indexer.Close(); err != nil {
		logger.Errorf("Error while closing file watcher: %v\n", err)
	} else {
		logger.Infof("Closed file watcher successfully.\n")
	}

	s.nk.Close()
	s.disconnectFromTracker()
	os.Exit(0)
}

// Search - The tracker invokes the search call on all the nodes when it broadcasts a search request from another node.
func (s *NodeService) Search(ctx context.Context, request *diztl.SearchReq) (*diztl.SearchResp, error) {
	logger.Debugf("Received search request for %s from %v\n", request.GetFilename(), request.GetSource())
	files := s.Indexer.Search(request.GetFilename())
	response := diztl.SearchResp{Files: files, Node: s.node}
	return &response, nil
}

// Upload - A requesting node invokes this call on this node asking it to upload the file of interest.
func (s *NodeService) Upload(request *diztl.UploadReq, stream diztl.DiztlService_UploadServer) error {
	metadata := request.GetMetadata()
	r, err := file.CreateReader(metadata, request.GetContract())
	if err != nil {
		return err
	}

	for {
		f, err := r.Read()
		if err != nil {
			r.Close()
			if err == io.EOF {
				logger.Infof("Finished uploading file: %s\n", metadata.GetPath())
				break
			}

			return err
		}

		if f.Chunk == 1 {
			logger.Debugf("Uploading file: %s\n", metadata.GetPath())
		}

		serr := stream.Send(f)
		if serr != nil {
			logger.Errorf("Upload failed due to error in recipient host: %v\n", serr)
			fmt.Println("Upload failed, but relax! It's not you, it's them.")
			break
		}
	}

	return nil
}

// Ping - Any node can invoke this call on any node to see if it's currently active.
func (s *NodeService) Ping(ctx context.Context, request *diztl.PingReq) (*diztl.PingResp, error) {
	logger.Infof("Received ping from %v\n", request.GetSource())
	return &diztl.PingResp{Message: "online"}, nil
}

// Find finds for files in the network whose name has the given pattern string.
func (s *NodeService) Find(ctx context.Context, request *diztl.FindReq) (*diztl.FindResp, error) {
	logger.Infof("Received find call: %v\n", request)
	results := []*diztl.SearchResp{}
	logger.Debugf("Searching for pattern: %s\n", request.GetPattern())
	r := diztl.SearchReq{Filename: request.GetPattern(), Source: s.node}
	c, cancel := context.WithTimeout(context.Background(), conf.SearchTimeout())
	defer cancel()
	t := s.tracker()
	stream, err := t.Search(c, &r)
	if err != nil {
		return nil, err
	}

	for {
		resp, err := stream.Recv()
		if err != nil {
			if err != io.EOF {
				logger.Errorf("Error while reading search response from tracker\n: %v", err)
			}

			break
		}

		results = append(results, resp)
	}

	return &diztl.FindResp{Responses: results}, nil
}

// Download downloads a file.
func (s *NodeService) Download(ctx context.Context, request *diztl.DownloadReq) (*diztl.DownloadResp, error) {
	logger.Infof("Received download call: %v\n", request)
	c, cancel := context.WithTimeout(context.Background(), conf.DownloadTimeout())
	defer cancel()
	client, err := s.nk.GetConnection(request.GetSource())
	if err != nil {
		return nil, err
	}

	contract := &diztl.UploadContract{ChunkSize: conf.ChunkSize()}
	r := &diztl.UploadReq{Source: request.GetSource(), Metadata: request.GetMetadata(), Contract: contract}
	stream, err := client.Upload(c, r)
	if err != nil {
		logger.Errorf("Download failed due to error in sender host - %v\n", err)
		fmt.Println("Download failed. It's not you, it's them.")
		return nil, err
	}

	var w *file.Writer

	for {
		s, err := stream.Recv()
		if err != nil {
			if err == io.EOF {
				_, serr := w.Close()
				if serr != nil {
					return nil, serr
				}

				//return f, nil
				break
			}

			// return nil, err
			break
		}

		if s.GetChunk() == 1 {
			w, err = file.CreateWriter(s.GetMetadata())
			if err != nil {
				return nil, err
			}

			logger.Infof("Downloading file: %s. Prepared to receive %d chunks.\n", s.GetMetadata().GetName(), w.Chunks())
		}

		if err := w.Write(s.GetData()); err != nil {
			return nil, err
		}
	}

	return &diztl.DownloadResp{Message: "Successfully downloaded file..."}, nil
}

// GetUserDirs returns the user-configured directories.
func (s *NodeService) GetUserDirs(ctx context.Context, request *diztl.UserDirsReq) (*diztl.UserDirsResp, error) {
	logger.Infof("Received GetUserDirs call: %v\n", request)
	resp := diztl.UserDirsResp{}

	if request.GetShare() {
		shareDirs, err := dir.GetShareDirs()
		if err == nil {
			resp.Share = shareDirs
		}
	}

	if request.GetOutput() {
		outputDir, err := dir.GetOutputDir()
		if err == nil {
			resp.Output = outputDir
		}
	}

	return &resp, nil
}
