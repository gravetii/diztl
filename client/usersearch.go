package client

import (
	"fmt"

	"github.com/gravetii/diztl/diztl"
	"github.com/gravetii/diztl/logger"
)

type searchResult struct {
	node *diztl.Node
	file *diztl.FileMetadata
}

func search(input string) ([]*searchResult, bool) {
	fmt.Printf("Performing search for string: %s\n", input)
	responses, err := nodeclient.Search(input)
	if err != nil {
		logger.Log.Printf("Could not search due to an error: %v\n", err)
		fmt.Println("Unable to search the network at this moment.")
		return nil, false
	}

	return validateResponses(responses)
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
