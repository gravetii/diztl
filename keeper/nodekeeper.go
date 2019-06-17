package keeper

import (
	"sync"

	"github.com/google/uuid"

	"github.com/gravetii/diztl/addr"
	"github.com/gravetii/diztl/conf"
	"github.com/gravetii/diztl/counter"
	"github.com/gravetii/diztl/diztl"
	pb "github.com/gravetii/diztl/diztl"
	"github.com/gravetii/logger"
	"google.golang.org/grpc"
)

// NodeKeeper : A struct type that keeps track of active nodes.
type NodeKeeper struct {
	Nodes       map[string]*diztl.Node
	Count       *counter.AtomicCounter
	connections map[string]*grpc.ClientConn
	mux         sync.Mutex
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
		logger.Warnf("Stale connection found for %s\n", node.GetIp())
	}
	uuid := uuid.New()
	node.Id = uuid.String()
	nk.Nodes[node.GetIp()] = node
	logger.Infof("Node successfully registered: %s, %s\n", node.GetIp(), node.GetId())
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

	conn, err := grpc.Dial(addr.Address(node), grpc.WithInsecure(),
		grpc.WithBlock(), grpc.WithTimeout(conf.NodeConnectTimeout()))
	if err != nil {
		logger.Errorf("Could not connect to node %s: %v\n", node.GetIp(), err)
		return nil, err
	}

	logger.Debugf("Created connection to node %s\n", node.GetIp())
	nk.connections[node.GetIp()] = conn
	r := pb.NewDiztlServiceClient(conn)
	return r, nil
}

// Disconnect : Invalidates the given node from the nodekeeper when the client disconnects.
// Returns true if the node could be invalidated, false if there's no entry for this node.
func (nk *NodeKeeper) Disconnect(node *diztl.Node) bool {
	return nk.invalidateIfExists(node)
}

// Close : Clears the resources held by this nodekeeper, making it unusable for further operations.
func (nk *NodeKeeper) Close() {
	for node, conn := range nk.connections {
		err := conn.Close()
		if err != nil {
			logger.Errorf("Error while closing connection to %s - %v\n", node, err)
		} else {
			logger.Debugf("Closed connection to %s\n", node)
		}
	}

	logger.Infof("Nodekeeper shut down successfully.\n")
}
