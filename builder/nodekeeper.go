package builder

import (
	"log"
	"sync"

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
	Connections map[string]diztl.DiztlServiceClient
	mux         sync.Mutex
}

// NewNodeKeeper : Return a new instance of NodeKeeper.
func NewNodeKeeper() *NodeKeeper {
	nodes := make(map[string]*diztl.Node)
	connections := make(map[string]diztl.DiztlServiceClient)
	nk := NodeKeeper{Nodes: nodes, Connections: connections, Count: counter.NewAtomic(0)}
	return &nk
}

// Register : Called when a new node asks the tracker to register itself. Here the tracker assigns a unique ID to the node.
func (nodekeeper *NodeKeeper) Register(node *diztl.Node) {
	nodekeeper.mux.Lock()
	defer nodekeeper.mux.Unlock()
	c := nodekeeper.Count.IncrBy1()
	node.Id = c
	nodekeeper.Nodes[node.GetIp()] = node
	log.Printf("Node successfully registered: %s, %d\n", node.GetIp(), node.GetId())
}

// GetConnection : Returns a connection to any node.
func (nodekeeper *NodeKeeper) GetConnection(node *diztl.Node) (pb.DiztlServiceClient, error) {
	if c, exists := nodekeeper.Connections[node.GetIp()]; exists {
		return c, nil
	}
	conn, err := grpc.Dial(util.Address(node), grpc.WithInsecure(),
		grpc.WithBlock(), grpc.WithTimeout(config.NodeConnectTimeout))
	if err != nil {
		return nil, err
	}
	r := pb.NewDiztlServiceClient(conn)
	nodekeeper.Connections[node.GetIp()] = r
	return r, nil
}
