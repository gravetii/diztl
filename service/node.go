package service

import (
	"context"
	"fmt"
	"io"
	"time"

	"github.com/gravetii/diztl/addr"
	"github.com/gravetii/diztl/conf"
	"github.com/gravetii/diztl/dir"
	"github.com/gravetii/diztl/keeper"
	"github.com/gravetii/diztl/shutdown"
	"github.com/pkg/errors"
	"google.golang.org/grpc"

	"github.com/gravetii/diztl/diztl"
	"github.com/gravetii/diztl/file"
	"github.com/gravetii/diztl/indexer"
	"github.com/gravetii/logger"
)

// NodeService implements the node server interface definition.
type NodeService struct {
	node    *diztl.Node
	Indexer *indexer.FileIndexer
	t       diztl.TrackerServiceClient
	nk      *keeper.NodeKeeper
	m       *grpc.Server
}

// NewNode returns an instance of the Node Service.
func NewNode(m *grpc.Server) *NodeService {
	f := indexer.NewFileIndexer()
	s := &NodeService{Indexer: f, m: m}
	return s
}

// Init performs the necessary initialisation when the service comes up for the first time.
func (s *NodeService) Init() error {
	logger.Debugf("Initialising node service...\n")
	s.nk = keeper.New()
	shutdown.Listen(s)
	logger.Debugf("Finished initialising node service.\n")
	return nil
}

func (s *NodeService) clearIndex() {
	logger.Debugf("Clearing current file index.\n")
	s.Indexer = indexer.NewFileIndexer()
}

func (s *NodeService) connectToTracker(trackerAddr string) error {
	conn, err := grpc.Dial(trackerAddr, grpc.WithInsecure(),
		grpc.WithBlock(), grpc.WithTimeout(conf.TrackerConnectTimeout()))
	if err != nil {
		logger.Errorf("Couldn't connect to tracker - %v\n", err)
		return err
	}

	s.t = diztl.NewTrackerServiceClient(conn)
	logger.Debugf("Successfully connected to tracker...\n")
	return nil
}

func (s *NodeService) disconnect() error {
	ctx, cancel := context.WithTimeout(context.Background(), conf.DisconnectTimeout())
	defer cancel()
	req := diztl.DisconnectReq{Node: s.node}
	_, err := s.t.Disconnect(ctx, &req)
	if err != nil {
		logger.Errorf("Error while disconnecting node from tracker - %v\n", err)
		return err
	}

	fmt.Println("\nBye!")
	return nil
}

// Register registers the node to the tracker specified in the configuration.
func (s *NodeService) Register() error {
	tracker := conf.TrackerHost() + ":" + conf.TrackerPort()
	if err := s.connectToTracker(tracker); err != nil {
		return err
	}

	request := &diztl.RegisterReq{Self: &diztl.Node{Ip: addr.LocalIP()}}
	c, cancel := context.WithTimeout(context.Background(), time.Second)
	defer cancel()
	resp, err := s.t.Register(c, request)
	if err != nil {
		logger.Errorf("Error while registering to tracker - %v\n", err)
		return err
	}

	n := resp.GetNode()
	s.node = &diztl.Node{Ip: n.GetIp(), Id: n.GetId()}
	logger.Infof("Successfully registered to tracker - %v\n", s.node)
	return nil
}

// UpdateTracker updates the details of the tracker in config.
func (s *NodeService) UpdateTracker(ctx context.Context, request *diztl.UpdateTrackerReq) (*diztl.UpdateTrackerResp, error) {
	logger.Debugf("Received update tracker request: %v\n", request)
	conf.UpdateTracker(request.GetTracker())
	return &diztl.UpdateTrackerResp{Code: 1}, nil
}

// Search - The tracker invokes the search call on all the nodes when it broadcasts a search request from another node.
func (s *NodeService) Search(ctx context.Context, request *diztl.SearchReq) (*diztl.SearchResp, error) {
	logger.Debugf("Received search request for %s from %v\n", request.GetQuery(), request.GetSource())
	files := s.Indexer.Search(request.GetQuery(), request.GetConstraint())
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
				logger.Infof("Finished uploading file: %s\n", dir.GetFilePath(metadata))
				break
			}

			return err
		}

		if f.Chunk == 1 {
			logger.Debugf("Uploading file: %s\n", dir.GetFilePath(metadata))
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
	return &diztl.PingResp{Code: 1, Message: "online"}, nil
}

// Find finds for files in the network whose name has the given query string.
func (s *NodeService) Find(ctx context.Context, request *diztl.FindReq) (*diztl.FindResp, error) {
	logger.Infof("Received find call: %v\n", request)
	results := []*diztl.SearchResp{}
	logger.Debugf("Searching for query: %s\n", request.GetQuery())
	r := diztl.SearchReq{Query: request.GetQuery(), Constraint: request.GetConstraint(), Source: s.node}
	c, cancel := context.WithTimeout(context.Background(), conf.SearchTimeout())
	defer cancel()
	stream, err := s.t.Search(c, &r)
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
func (s *NodeService) Download(request *diztl.DownloadReq, stream diztl.DiztlService_DownloadServer) error {
	logger.Infof("Received download call: %v\n", request)
	c, cancel := context.WithTimeout(context.Background(), conf.DownloadTimeout())
	defer cancel()
	client, err := s.nk.GetConnection(request.GetSource())
	if err != nil {
		return err
	}

	contract := &diztl.UploadContract{ChunkSize: conf.ChunkSize()}
	r := &diztl.UploadReq{Source: request.GetSource(), Metadata: request.GetFile(), Contract: contract}
	ustream, err := client.Upload(c, r)
	if err != nil {
		logger.Errorf("Download failed due to error in sender host - %v\n", err)
		fmt.Println("Download failed. It's not you, it's them.")
		return err
	}

	var w *file.Writer

	for {
		s, err := ustream.Recv()
		if err != nil {
			if err == io.EOF {
				serr := w.Close()
				if serr != nil {
					return serr
				}
				break
			}
			break
		}

		if s.GetChunk() == 1 {
			w, err = file.CreateWriter(s.GetMetadata(), s.GetChunks(), request.GetDir())
			if err != nil {
				return err
			}

			logger.Infof("Downloading file: %s. Prepared to receive %d chunks.\n", s.GetMetadata().GetName(), s.GetChunks())
		}

		if err := w.Write(s.GetData()); err != nil {
			return err
		}

		if s.GetChunk() == 1 {
			stream.Send(&diztl.DownloadChunk{Chunk: 1, Chunks: s.GetChunks()})
		} else {
			stream.Send(&diztl.DownloadChunk{Chunk: s.GetChunk()})
		}
	}

	return nil
}

// GetUserDirs returns the configured user directories.
func (s *NodeService) GetUserDirs(ctx context.Context, request *diztl.UserDirsReq) (*diztl.UserDirsResp, error) {
	logger.Infof("Received GetUserDirs call: %v\n", request)
	resp := diztl.UserDirsResp{}
	shareDirs, err := dir.GetShareDirs()
	if err == nil {
		resp.Share = shareDirs
	}

	downloadsDir, err := dir.GetDownloadsDir()
	if err == nil {
		resp.Downloads = downloadsDir
	}

	return &resp, nil
}

// UpdateUserDirs updates the user directories in config.
func (s *NodeService) UpdateUserDirs(ctx context.Context, request *diztl.UpdateUserDirsReq) (*diztl.UpdateUserDirsResp, error) {
	logger.Infof("Received UpdateUserDirs call: %v\n", request)
	if len(request.GetShare()) > 0 {
		conf.UpdateShareDirs(request.GetShare())
	}

	if request.GetDownloads() != "" {
		conf.UpdateDownloadsDir(request.GetDownloads())
	}

	logger.Infof("Finished updating user share dirs\n")
	resp := diztl.UpdateUserDirsResp{Message: "Finished updating user share dirs"}
	return &resp, nil
}

// GetTracker returns the tracker host.
func (s *NodeService) GetTracker(ctx context.Context, request *diztl.GetTrackerReq) (*diztl.GetTrackerResp, error) {
	logger.Infof("Got GetTracker call: %v\n", request)
	return &diztl.GetTrackerResp{Tracker: conf.TrackerHost()}, nil
}

// Index indexes all the files in the shared directories.
func (s *NodeService) Index(paths chan string) error {
	logger.Infof("Indexing shared files...\n")
	s.clearIndex()
	if err := s.Indexer.Index(paths); err != nil {
		logger.Errorf("Error while indexing files: %v\n", err)
		return err
	}

	return nil
}

// FetchFileList is invoked by the frontend to fetch the file list for the given file from another node.
func (s *NodeService) FetchFileList(ctx context.Context, request *diztl.FetchFileListReq) (*diztl.FetchFileListResp, error) {
	c, cancel := context.WithTimeout(context.Background(), 4*time.Second)
	defer cancel()
	client, err := s.nk.GetConnection(request.GetNode())
	if err != nil {
		return nil, err
	}

	r := &diztl.GetFileListReq{Source: s.node, Dir: request.GetDir()}
	resp, err := client.GetFileList(c, r)
	if err != nil {
		logger.Errorf("Couldn't fetch file list from node %v - %v", request.GetNode(), err)
		return nil, errors.Wrap(err, "Couldn't fetch file list from node")
	}

	return &diztl.FetchFileListResp{Files: resp.GetFiles()}, nil
}

// GetFileList returns all the indexed files in the parent folder of the given file.
func (s *NodeService) GetFileList(ctx context.Context, request *diztl.GetFileListReq) (*diztl.GetFileListResp, error) {
	logger.Infof("Got GetFileList call from %s for dir %v\n", request.GetSource(), request.GetDir())
	files := s.Indexer.GetFileList(request.GetDir())
	return &diztl.GetFileListResp{Files: files}, nil
}

// Close closes down the node. This call is invoked by the front-end after receiving an app shutdown signal.
func (s *NodeService) Close(ctx context.Context, request *diztl.CloseReq) (*diztl.CloseResp, error) {
	defer shutdown.SendSignal()
	return &diztl.CloseResp{Message: "Node successfully shutdown"}, nil
}

// OnShutdown defines actions to perform on node shutdown.
func (s *NodeService) OnShutdown() {
	s.nk.Close()
	s.disconnect()
	s.m.GracefulStop()
}
