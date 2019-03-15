package diztl

// Address : Get the address of a Node.
func (node Node) Address() string {
	return node.GetIp() + ":" + "50051"
}
