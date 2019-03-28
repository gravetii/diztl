package service

import (
	"context"
	"io"
	"log"

	"github.com/gravetii/diztl/config"
	"github.com/gravetii/diztl/diztl"
	"github.com/gravetii/diztl/file"
	"github.com/gravetii/diztl/indexer"
	"github.com/gravetii/diztl/util"
)

// NodeService : Implements the node server interface definition.
type NodeService struct {
	Indexer *indexer.FileIndexer
	node    *diztl.Node
}

// NewNodeService : Returns an instance of the Node Service.
func NewNodeService() NodeService {
	f, err := indexer.NewFileIndexer()
	if err != nil {
		log.Fatalf("Error while creating the node service: %v", err)
	}

	return NodeService{Indexer: f}
}

// Init : Performs the necessary initialisation when the service comes up for the first time.
func (s *NodeService) Init() {
	log.Println("Initialising node service...")

	// Ensure that necessary folders are created
	util.EnsureDirs()

	if err := s.Indexer.Index(); err != nil {
		log.Fatalf("Error while indexing files: %v", err)
	}
}

// Search : The tracker invokes the search call when it broadcasts a search request from another node.
func (s *NodeService) Search(ctx context.Context, request *diztl.SearchRequest) (*diztl.SearchResponse, error) {
	log.Printf("Received search request: %v\n", request.GetSource())
	files := s.Indexer.Search(request.GetFilename())
	response := diztl.SearchResponse{Files: files, Node: s.node}
	return &response, nil
}

// Upload : A requesting node invokes this call on this node asking it to upload the file of interest.
func (s *NodeService) Upload(request *diztl.DownloadRequest, stream diztl.DiztlService_UploadServer) error {
	metadata := request.GetMetadata()
	fpath := metadata.GetPath()

	r, err := file.CreateReader(metadata)
	if err != nil {
		return err
	}

	for {
		data, err := r.Read()
		if err != nil {
			if err == io.EOF {
				log.Printf("Finished uploading file :%s\n", fpath)
				break
			}

			return err
		}

		fchunk := diztl.FileChunk{Chunk: r.Chunk(), Data: data}
		if r.Chunk() == 1 {
			chunks := int32(metadata.GetSize() / int64(config.ChunkBufSize))
			metadata.Chunks = chunks
			log.Printf("Uploading file: %s\n", fpath)
			fchunk.Metadata = metadata
		}

		stream.Send(&fchunk)
	}

	return nil
}
