package builder

import (
	"log"
	"sync"

	"github.com/gravetii/diztl/diztl"
	"google.golang.org/grpc"
)

// NodeKeeper : A struct type that keeps track of active nodes.
type NodeKeeper struct {
	ActiveNodes       map[string]*diztl.Node
	activeCount       int32
	ActiveConnections map[string]*grpc.ClientConn
	mux               sync.Mutex
}

// Register : Called when a new node asks the tracker to register itself. Here the tracker assigns a unique ID to the node.
func (nodekeeper *NodeKeeper) Register(node *diztl.Node) {
	nodekeeper.mux.Lock()
	defer nodekeeper.mux.Unlock()
	nodekeeper.activeCount++
	c := nodekeeper.activeCount
	node.Id = c
	nodekeeper.ActiveNodes[node.GetIp()] = node
	log.Printf("Node successfully registered: %s", node.GetIp())
}

// GetConnection : Returns a connection to any node.
func (nodekeeper *NodeKeeper) GetConnection(node diztl.Node) (*grpc.ClientConn, error) {
	conn, ok := nodekeeper.ActiveConnections[node.GetIp()]
	if !ok {
		// connection not present already, so we create a new one.
		conn, err := grpc.Dial(node.Address(), grpc.WithInsecure())
		if err != nil {
			log.Fatalf("did not connect: %v", err)
		}

		nodekeeper.ActiveConnections[node.GetIp()] = conn
		return conn, err
	}

	return conn, nil
}
