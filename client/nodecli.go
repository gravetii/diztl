package client

import (
	"fmt"
	"log"
	"time"

	"github.com/gravetii/diztl/diztl"
	pb "github.com/gravetii/diztl/diztl"
)

const (
	bufsize = 1024 * 512
)

type searchResult struct {
	node *diztl.Node
	file *diztl.FileMetadata
}

var downloadTimeout = 5 * time.Minute

// UserCLI : Starts a for{} to take user inputs for file search.
func UserCLI() {
	var pattern string
	var opt int

	for {
		fmt.Printf("\n\n***************  DIZTL  ***************\n\n")
		fmt.Printf("Enter a pattern to search for. * to Exit - ")
		fmt.Scanf("%s", &pattern)
		if pattern == "*" {
			fmt.Printf("Thank you for using Diztl. Bye!\n")
			break
		}

		fmt.Printf("Performing search for pattern: %s\n", pattern)
		responses, _ := nodeclient.Search(pattern)
		res, ok := validateResponses(responses)

		if ok {
			fmt.Printf("%30s | %30s", "Option", "File Name\n")
			for c, r := range res {
				fmt.Printf("%30d | %30s\n", c+1, r.file.GetName())
			}

			fmt.Printf("Enter option to download file - ")
			fmt.Scanf("%d", &opt)
			r := validateOption(opt, res)
			if r == nil {
				fmt.Printf("Invalid option, please try again!\n")
				continue
			} else {
				download(r)
			}
		}
	}
}

func download(r *searchResult) {
	req := &pb.DownloadRequest{Source: r.node, Metadata: r.file}
	err := nodeclient.download(req)
	if err != nil {
		log.Fatalf("Error while downloading file %s: %v", r.file.GetName(), err)
	}
}

func validateResponses(responses []*diztl.SearchResponse) ([]*searchResult, bool) {
	r := []*searchResult{}
	if len(responses) == 0 {
		fmt.Printf("No files with the given name were found in the network. Try another search!")
		return nil, false
	}

	for _, resp := range responses {
		if resp.GetCount() > 0 {
			for _, file := range resp.GetFiles() {
				r = append(r, &searchResult{resp.GetNode(), file})
				log.Printf("Appended file: %v", *file)
			}
		}
	}

	return r, true
}

func validateOption(o int, files []*searchResult) *searchResult {
	if o <= len(files) {
		return files[o-1]
	}

	return nil
}
