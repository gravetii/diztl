package builder

import (
	"log"
	"sync"

	"github.com/gravetii/diztl/diztl"
	"google.golang.org/grpc"
)

// NodeKeeper : A struct type that keeps track of active nodes.
type NodeKeeper struct {
	activeNodes       map[string]*diztl.Node
	activeCount       int32
	activeConnections map[string]*grpc.ClientConn
	mux               sync.Mutex
}

// Register : Called when a new node asks the tracker to register itself. Here the tracker assigns a unique ID to the node.
func (nodekeeper *NodeKeeper) Register(node *diztl.Node) {
	nodekeeper.mux.Lock()
	defer nodekeeper.mux.Unlock()
	nodekeeper.activeCount++
	c := nodekeeper.activeCount
	node.Id = c
	nodekeeper.activeNodes[node.GetIp()] = node
	log.Printf("Node successfully registered: %s", node.GetIp())
}

// ActiveNodes :Returns the list of active nodes as a map of IP -> Node.
func (nodekeeper *NodeKeeper) ActiveNodes() map[string]*diztl.Node {
	return nodekeeper.activeNodes
}

// GetConnection : Returns a connection to any node.
func (nodekeeper *NodeKeeper) GetConnection(node diztl.Node) (*grpc.ClientConn, error) {
	conn, ok := nodekeeper.activeConnections[node.GetIp()]
	if !ok {
		// connection not present already, so we create a new one.
		conn, err := grpc.Dial(node.Address(), grpc.WithInsecure())
		if err != nil {
			log.Fatalf("did not connect: %v", err)
		}

		nodekeeper.activeConnections[node.GetIp()] = conn
		return conn, err
	}

	return conn, nil
}
