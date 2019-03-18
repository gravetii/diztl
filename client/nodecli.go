package client

import (
	"fmt"
	"time"

	pb "github.com/gravetii/diztl/diztl"
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
		fmt.Printf("***************  DIZTL  ***************\n\n")
		fmt.Printf("DIZTL | Enter a pattern to search for. * to Exit\n")
		fmt.Scanf("%s", &pattern)
		if pattern == "*" {
			fmt.Printf("Thank you for using Diztl. Bye!\n")
			break
		} else {
			fmt.Printf("Performing search for pattern: %s\n", pattern)
			responses, _ := nodeclient.Search(pattern)
			fmt.Printf("%30s | %30s", "Option", "File Name\n")
			for c, resp := range responses {
				m := *resp.GetFiles()[0]
				fmt.Printf("%30d | %30s\n", c, m.GetName())
			}

			fmt.Printf("Enter option to download file.\n")
			fmt.Scanf("%d", &opt)
			r := responses[opt]
			m := r.GetFiles()[0]
			s := r.GetNode()
			req := pb.DownloadRequest{Source: s, Metadata: m}
			nodeclient.download(req)
		}
	}
}
