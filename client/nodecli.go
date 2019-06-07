package client

import (
	"bufio"
	"fmt"
	"log"
	"os"

	"github.com/gravetii/diztl/conf"

	pb "github.com/gravetii/diztl/diztl"
)

// UserCLI : Starts a for{} to take user inputs for file search.
func UserCLI() {
	r := bufio.NewScanner(os.Stdin)
	for {
		in, ok := input(r)
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

func input(r *bufio.Scanner) (string, bool) {
	fmt.Printf("\n\n***************  DIZTL  ***************\n\n")
	fmt.Printf("Enter a pattern to search for. '*' to Exit - ")
	r.Scan()
	pattern := r.Text()

	if pattern == "*" {
		fmt.Printf("Thank you for using Diztl. Bye!\n")
		return "*", false
	}

	return pattern, true
}

func download(res []*searchResult) {
	for _, r := range res {
		c := pb.DownloadContract{ChunkSize: conf.ChunkSize()}
		req := pb.DownloadReq{Source: r.node, Metadata: r.file, Contract: &c}
		f, err := nodeclient.download(&req)
		if err != nil {
			log.Printf("Error while downloading file %s: %v\n", r.file.GetName(), err)
		} else {
			log.Printf("Finished downloading file: %s\n", f.Name())
		}
	}
}
