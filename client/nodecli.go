package client

import (
	"bufio"
	"context"
	"fmt"
	"io"
	"log"
	"os"
	"time"

	pb "github.com/gravetii/diztl/diztl"
	"github.com/gravetii/diztl/util"
)

const (
	bufsize = 1024 * 512
)

var downloadTimeout = 5 * time.Minute

// ReadUserInput : Read user input for search.
func ReadUserInput() {
	var pattern string
	var opt int

	for {
		fmt.Println("*****  DIZTL  *****")
		fmt.Println("DIZTL | Enter a pattern to search for. * to Exit.")
		fmt.Scanf("%s", &pattern)
		if pattern == "*" {
			break
		} else {
			fmt.Printf("Performing search for pattern: %s\n", pattern)
			responses, _ := nodeclient.Search(pattern)
			fmt.Printf("%50s | %50s", "Option", "File Name\n")
			for c, resp := range responses {
				m := *resp.GetFiles()[0]
				fmt.Printf("%30d | %30s\n", c, m.GetName())
			}

			fmt.Printf("Enter option to download file.")
			fmt.Scanf("%d", &opt)
			r := responses[opt]
			m := r.GetFiles()[0]
			s := r.GetNode()
			req := pb.DownloadRequest{Source: s, Metadata: m}
			log.Println("Calling download on nodeclient")
			nodeclient.Download(req)
		}
	}
}

func download(r pb.DownloadRequest) {
	ctx, cancel := context.WithTimeout(context.Background(), downloadTimeout)
	defer cancel()
	client, _ := getConnection(r.GetSource())
	log.Printf("Calling upload for request: %v", r)
	stream, _ := client.Upload(ctx, &r)
	var buf *bufio.Writer
	var obj *os.File
	var filename string

	for {
		f, err := stream.Recv()

		if err == io.EOF {
			buf.Flush()
			obj.Close()
			log.Printf("Finished reading file at %d chunk.", f.GetChunk())
			log.Printf("Finished downloading file: %s", filename)
			break
		}

		if f.GetChunk() == 1 {
			filename = f.GetMetadata().GetName()
			filepath := util.GetOutputPath(filename)
			obj, _ = os.Create(filepath)
			buf = bufio.NewWriter(obj)
			log.Printf("Created new file on first chunk: %s", filename)
		}

		n, _ := buf.Write(f.GetData())
		log.Printf("Read bytes: %d, Chunk no. %d", n, f.GetChunk())
	}
}
