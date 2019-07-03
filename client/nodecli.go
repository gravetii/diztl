package client

import (
	"bufio"
	"fmt"
	"os"

	"github.com/gravetii/diztl/conf"

	"github.com/gravetii/diztl/diztl"
	"github.com/gravetii/logger"
)

func userCLI() {
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
		c := diztl.UploadContract{ChunkSize: conf.ChunkSize()}
		req := diztl.UploadReq{Source: r.node, Metadata: r.file, Contract: &c}
		f, err := nodeclient.download(&req)
		if err != nil {
			logger.Errorf("Error while downloading file %s: %v\n", r.file.GetName(), err)
		} else {
			logger.Infof("Finished downloading file: %s\n", f.Name())
			f.Close()
		}
	}
}
