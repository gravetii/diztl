package keeper

import (
	"log"
	"sync"

	"github.com/google/uuid"

	"github.com/gravetii/diztl/config"
	"github.com/gravetii/diztl/counter"
	"github.com/gravetii/diztl/diztl"
	pb "github.com/gravetii/diztl/diztl"
	"github.com/gravetii/diztl/util"
	"google.golang.org/grpc"
)

// NodeKeeper : A struct type that keeps track of active nodes.
type NodeKeeper struct {
	Nodes       map[string]*diztl.Node
	Count       *counter.AtomicCounter
	connections map[string]*grpc.ClientConn
	mux         sync.Mutex
	// diztl.DiztlServiceClient
}

// New : Returns a new instance of the NodeKeeper.
func New() *NodeKeeper {
	nodes := make(map[string]*diztl.Node)
	connections := make(map[string]*grpc.ClientConn)
	nk := NodeKeeper{Nodes: nodes, connections: connections, Count: counter.NewAtomic(0)}
	return &nk
}

// Register : Called when a new node asks the tracker to register itself. Here the tracker assigns a unique ID to the node.
func (nk *NodeKeeper) Register(node *diztl.Node) {
	nk.mux.Lock()
	defer nk.mux.Unlock()
	if nk.invalidateIfExists(node) {
		log.Printf("Stale connection found for %s\n", node.GetIp())
	}
	uuid, _ := uuid.NewRandom()
	node.Id = uuid.String()
	nk.Nodes[node.GetIp()] = node
	log.Printf("Node successfully registered: %s, %s\n", node.GetIp(), node.GetId())
}

func (nk *NodeKeeper) invalidateIfExists(node *diztl.Node) bool {
	_, exists := nk.Nodes[node.GetIp()]
	if exists {
		delete(nk.Nodes, node.GetIp())
		delete(nk.connections, node.GetIp())
		return true
	}

	return false
}

// GetConnection : Returns a connection to any node.
func (nk *NodeKeeper) GetConnection(node *diztl.Node) (pb.DiztlServiceClient, error) {
	if conn, exists := nk.connections[node.GetIp()]; exists {
		return pb.NewDiztlServiceClient(conn), nil
	}
	conn, err := grpc.Dial(util.Address(node), grpc.WithInsecure(),
		grpc.WithBlock(), grpc.WithTimeout(config.NodeConnectTimeout))
	if err != nil {
		return nil, err
	}

	log.Printf("Created connection to node %s\n", node.GetIp())
	nk.connections[node.GetIp()] = conn
	r := pb.NewDiztlServiceClient(conn)
	return r, nil
}

// Disconnect : Invalidates the given node from the nodekeeper when the client disconnects.
// Returns true if the node could be invalidated, false if there's no entry of this node
// in the nodekeeper.
func (nk *NodeKeeper) Disconnect(node *diztl.Node) bool {
	return nk.invalidateIfExists(node)
}

// Close : Clears the resources held by this nodekeeper, making it unusable for further operations.
func (nk *NodeKeeper) Close() {
	for node, conn := range nk.connections {
		err := conn.Close()
		if err != nil {
			log.Printf("Error while closing connection to %s: %v", node, err)
		} else {
			log.Printf("Closed connection to %s\n", node)
		}
	}

	log.Println("Nodekeeper shut down successfully.")
}
