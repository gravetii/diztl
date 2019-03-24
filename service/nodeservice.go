package service

import (
	"bufio"
	"context"
	"io"
	"log"
	"os"

	"github.com/gravetii/diztl/config"
	"github.com/gravetii/diztl/diztl"
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
		panic(err)
	}

	return NodeService{Indexer: f}
}

// Init : Performs the necessary initialisation when the service comes up for the first time.
func (s *NodeService) Init() {
	log.Println("Initialising node service...")
	err := s.Indexer.Index()
	if err != nil {
		log.Fatalf("Error while indexing files: %v", err)
		panic(err)
	}
}

// Search : func
func (s *NodeService) Search(ctx context.Context, request *diztl.SearchRequest) (*diztl.SearchResponse, error) {
	log.Printf("Received search request: %v", request.GetSource())
	files := s.Indexer.Search(request.GetFilename())
	// todo - check explicit conversion from int to int32
	response := diztl.SearchResponse{Count: int32(len(files)), Files: files, Node: s.node}
	return &response, nil
}

// Upload : func
func (s *NodeService) Upload(request *diztl.DownloadRequest, stream diztl.DiztlService_UploadServer) error {
	fpath := request.GetMetadata().GetPath()

	f, err := openFile(fpath)
	if err != nil {
		return err
	}

	p := make([]byte, config.ChunkBufSize)
	reader := bufio.NewReader(f)
	chunk := util.NewCounter(1)

	for {
		if chunk.Value() == 1 {
			log.Printf("Uploading file: %s\n", fpath)
			// Send metadata of the file without actual payload in the first chunk.
			fchunk := &diztl.File{Metadata: request.GetMetadata(), Chunk: 1}
			stream.Send(fchunk)
		} else {
			_, err := reader.Read(p)
			if err == io.EOF {
				log.Printf("Finished uploading file :%s", fpath)
				break
			}
			fchunk := &diztl.File{Data: p, Chunk: chunk.Value()}
			stream.Send(fchunk)
		}

		chunk.IncrBy1()
	}

	return nil
}

func openFile(fpath string) (*os.File, error) {
	f, err := os.Open(fpath)
	if err != nil {
		if os.IsNotExist(err) {
			log.Fatalf("Specified file %s does not exist: %v", fpath, err)
		} else {
			log.Fatalf("Error while reading file %s to upload: %v", fpath, err)
		}

		return nil, err
	}

	return f, err
}
