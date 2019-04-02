package client

import (
	"fmt"
	"strconv"
	"strings"
)

func optInput(files []*searchResult) ([]*searchResult, bool) {
	var opt string
	fmt.Printf("Enter option(s) to download file separated by ','- ")
	fmt.Scanf("%s", &opt)
	return parseOpt(opt, files)
}

func parseOpt(opt string, files []*searchResult) ([]*searchResult, bool) {
	res := []*searchResult{}
	for _, opt := range strings.Split(opt, ",") {
		o, ok := validateOpt(opt, len(files))
		if ok {
			res = append(res, files[o-1])
		}
	}

	return res, true
}

func validateOpt(opt string, n int) (int, bool) {
	o, err := strconv.Atoi(opt)
	if err != nil {
		fmt.Printf("Invalid option, please try again!\n")
		return -1, false
	}

	if o <= 0 || o > n {
		fmt.Printf("Invalid option, please try again!\n")
		return -1, false
	}

	return o, true
}
