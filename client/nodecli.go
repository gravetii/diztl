package client

import (
	"fmt"
	"log"

	pb "github.com/gravetii/diztl/diztl"
)

// UserCLI : Starts a for{} to take user inputs for file search.
func UserCLI() {
	for {
		in, ok := input()
		if !ok {
			break
		}

		s, ok := search(in)
		if ok {
			display(s)
			res, ok := optInput(s)
			if ok {
				go download(res)
			}
		}
	}
}

func display(res []*searchResult) {
	fmt.Printf("\n%30s | %30s", "Option", "File Name\n")
	for c, r := range res {
		fmt.Printf("%30d | %30s\n", c+1, r.file.GetName())
	}
}

func input() (string, bool) {
	var pattern string
	fmt.Printf("\n\n***************  DIZTL  ***************\n\n")
	fmt.Printf("Enter a pattern to search for. '*' to Exit - ")
	fmt.Scanf("%s", &pattern)

	if pattern == "*" {
		fmt.Printf("Thank you for using Diztl. Bye!\n")
		return "*", false
	}

	return pattern, true
}

func download(res []*searchResult) {
	for _, r := range res {
		req := &pb.DownloadReq{Source: r.node, Metadata: r.file}
		f, err := nodeclient.download(req)
		if err != nil {
			log.Printf("Error while downloading file %s: %v\n", r.file.GetName(), err)
		} else {
			log.Printf("Finished downloading file: %s\n", f.Name())
		}
	}
}
