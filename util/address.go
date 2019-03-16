package util

import (
	"log"
	"net"

	"github.com/gravetii/diztl/diztl"
)

// GetMyIP : Returns the host's IP address.
func GetMyIP() string {
	addrs, _ := net.InterfaceAddrs()

	ip := "127.0.0.1"
	for _, a := range addrs {
		if ipnet, ok := a.(*net.IPNet); ok && !ipnet.IP.IsLoopback() {
			if ipnet.IP.To4() != nil {
				ip = ipnet.IP.String()
			}
		}
	}

	log.Printf("Got local IP as %s", ip)
	return ip
}

// Address : Returns the address of the node as a string.
func Address(node *diztl.Node) string {
	return node.GetIp() + ":" + "50051"
}
