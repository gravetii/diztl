package addr

import (
	"errors"
	"net"

	"github.com/gravetii/diztl/conf"
	"github.com/gravetii/diztl/diztl"
	"github.com/gravetii/logger"
)

var ip string

// Find finds the host's local IP address.
// Typically, this is done during the initial starup of the tracker/node.
func Find() error {
	conn, err := net.Dial("udp", "8.8.8.8:80")
	if err != nil {
		logger.Errorf("Could not fetch host's local ip - %v\n", err)
		return errors.New("Could not fetch host's local ip - " + err.Error())
	}

	defer conn.Close()
	addr := conn.LocalAddr().(*net.UDPAddr)
	ip = addr.IP.String()
	logger.Debugf("Host's local ip - %s\n", ip)
	return nil
}

// LocalIP returns the host's IP address.
func LocalIP() string {
	return ip
}

// Address : Returns the address of the node as a string.
func Address(node *diztl.Node) string {
	return node.GetIp() + ":" + conf.NodePort()
}
