package addr

import (
	"net"

	"github.com/gravetii/diztl/conf"
	"github.com/gravetii/diztl/diztl"
	"github.com/gravetii/diztl/logger"
)

var ip string

func findMyIP() string {
	conn, err := net.Dial("udp", "8.8.8.8:80")
	if err != nil {
		logger.Log.Fatalf("Could not fetch the host's IP: %v", err)
	}

	defer conn.Close()
	addr := conn.LocalAddr().(*net.UDPAddr)
	ip := addr.IP.String()
	logger.Log.Printf("Got IP: %s\n", ip)
	return ip
}

// GetMyIP returns the host's IP address.
func GetMyIP() string {
	if ip == "" {
		ip = findMyIP()
	}

	return ip
}

// Address : Returns the address of the node as a string.
func Address(node *diztl.Node) string {
	return node.GetIp() + ":" + conf.NodePort()
}
