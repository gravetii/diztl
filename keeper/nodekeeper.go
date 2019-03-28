package keeper

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

// New : Returns a new instance of the NodeKeeper.
func New() *NodeKeeper {
	nodes := make(map[string]*diztl.Node)
	connections := make(map[string]diztl.DiztlServiceClient)
	nk := NodeKeeper{Nodes: nodes, Connections: connections, Count: counter.NewAtomic(0)}
	return &nk
}

// Register : Called when a new node asks the tracker to register itself. Here the tracker assigns a unique ID to the node.
func (nk *NodeKeeper) Register(node *diztl.Node) {
	nk.mux.Lock()
	defer nk.mux.Unlock()
	c, stale := nk.invalidateIfExists(node)
	if !stale {
		c = nk.Count.IncrBy1()
	}

	node.Id = c
	nk.Nodes[node.GetIp()] = node
	log.Printf("Node successfully registered: %s, %d\n", node.GetIp(), node.GetId())
}

func (nk *NodeKeeper) invalidateIfExists(node *diztl.Node) (int32, bool) {
	v, exists := nk.Nodes[node.GetIp()]
	if exists {
		log.Printf("Stale connection found for %s, invalidating...\n", node.GetIp())
		c := v.GetId()
		delete(nk.Nodes, node.GetIp())
		delete(nk.Connections, node.GetIp())
		return c, true
	}

	return -1, false
}

// GetConnection : Returns a connection to any node.
func (nk *NodeKeeper) GetConnection(node *diztl.Node) (pb.DiztlServiceClient, error) {
	if c, exists := nk.Connections[node.GetIp()]; exists {
		return c, nil
	}
	conn, err := grpc.Dial(util.Address(node), grpc.WithInsecure(),
		grpc.WithBlock(), grpc.WithTimeout(config.NodeConnectTimeout))
	if err != nil {
		return nil, err
	}
	r := pb.NewDiztlServiceClient(conn)
	nk.Connections[node.GetIp()] = r
	return r, nil
}
