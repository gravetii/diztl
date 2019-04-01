package client

import (
	"fmt"
	"log"
	"strconv"

	"github.com/gravetii/diztl/diztl"
	pb "github.com/gravetii/diztl/diztl"
)

type searchResult struct {
	node *diztl.Node
	file *diztl.FileMetadata
}

// UserCLI : Starts a for{} to take user inputs for file search.
func UserCLI() {
	for {
		in, ok := searchInput()
		if !ok {
			break
		}

		s, ok := search(in)
		if ok {
			display(s)
			r, ok := optInput(s)
			if ok {
				download(r)
			}
		}
	}
}

func search(in string) ([]*searchResult, bool) {
	fmt.Printf("Performing search for string: %s\n", in)
	responses, err := nodeclient.Search(in)
	if err != nil {
		log.Printf("Could not search due to an error: %v\n", err)
		fmt.Println("Unable to search the network at this moment.")
		return nil, false
	}

	return validateResponses(responses)
}

func display(res []*searchResult) {
	fmt.Printf("\n%30s | %30s", "Option", "File Name\n")
	for c, r := range res {
		fmt.Printf("%30d | %30s\n", c+1, r.file.GetName())
	}
}

func searchInput() (string, bool) {
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

func optInput(res []*searchResult) (*searchResult, bool) {
	var opt string
	fmt.Printf("Enter option to download file - ")
	fmt.Scanf("%s", &opt)
	return validateOption(opt, res)
}

func download(r *searchResult) {
	req := &pb.DownloadReq{Source: r.node, Metadata: r.file}
	f, err := nodeclient.download(req)
	if err != nil {
		log.Printf("Error while downloading file %s: %v\n", r.file.GetName(), err)
	} else {
		log.Printf("Finished downloading file: %s\n", f.Name())
	}
}

func validateResponses(responses []*diztl.SearchResp) ([]*searchResult, bool) {
	if len(responses) == 0 {
		fmt.Printf("No files with the given name were found in the network. Try another search!")
		return nil, false
	}

	r := []*searchResult{}
	for _, resp := range responses {
		for _, file := range resp.GetFiles() {
			r = append(r, &searchResult{resp.GetNode(), file})
		}
	}

	return r, true
}

func validateOption(opt string, files []*searchResult) (*searchResult, bool) {
	o, err := strconv.Atoi(opt)
	if err != nil {
		fmt.Printf("Invalid option, please try again!\n")
		return nil, false
	}

	if o <= 0 || o > len(files) {
		fmt.Printf("Invalid option, please try again!\n")
		return nil, false
	}

	return files[o-1], true
}
