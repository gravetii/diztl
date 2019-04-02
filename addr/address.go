package addr

import (
	"log"
	"net"

	"github.com/gravetii/diztl/conf"
	"github.com/gravetii/diztl/diztl"
)

var ip = findMyIP()

func findMyIP() string {
	conn, err := net.Dial("udp", "8.8.8.8:80")
	if err != nil {
		log.Fatalf("Could not fetch the host's IP: %v", err)
	}

	defer conn.Close()
	addr := conn.LocalAddr().(*net.UDPAddr)
	ip := addr.IP.String()
	log.Printf("Got IP: %s\n", ip)
	return ip
}

// GetMyIP : Returns the host's IP address.
func GetMyIP() string {
	return ip
}

// Address : Returns the address of the node as a string.
func Address(node *diztl.Node) string {
	return node.GetIp() + ":" + conf.NodePort()
}
