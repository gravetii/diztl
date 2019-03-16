package client

import "fmt"

// ReadUserInput : Read user input for search.
func ReadUserInput() {
	var pattern string
	for {
		fmt.Println("*****  DIZTL  *****")
		fmt.Println("DIZTL | Enter a pattern to search for. * to exit...")
		fmt.Scanf("%s", &pattern)
		if pattern == "*" {
			break
		} else {
			fmt.Printf("Performing search for pattern: %s", pattern)
			resp, _ := nodeclient.Search(pattern)
			fmt.Printf("Final search response: %v\n", resp)
		}
	}
}
