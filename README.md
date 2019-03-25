# diztl
A distributed peer-to-peer file discovery and sharing platform written in Go!

## Getting started
```go get -u github.com/gravetii/diztl```

## Implementation
The diztl peer-to-peer file discovery and sharing platform consists of two main components:
- Tracker: The `Tracker`'s responsibility is to allow co-ordination and communication between the different `Node`s.
- Node: A `Node` is basically any peer in the network. It can share resources as well as request for and download resources from other `Node`s in the network.

The current implementation isn't completely decentralized in that the search queries from a `Node` are sent to the `Tracker` which then broadcasts the request across all peers in the network, requesting them to reply back with the files it has that might be of interest to the caller `Node`.

Once the requesting `Node` decides on the file it wants to download from the target peer, communication happens solely between the two peers without any intervention from the `Tracker`.

When the `Node` first starts up, it indexes all the files to be shared in the default share folder located under `<user_root>/Documents/diztl/share`. By default, downloaded files are located under `/output` directory of the shared folder.

## Running diztl

In the root folder of the project:
- `go run tracker/main.go`: This runs the tracker node on the localhost.
- Specify the local IP address of the `Tracker` in `config.go` under `TrackerHost` to allow `Node`s to connect to it.
- `go run node/main.go`: Run this anywhere on any machine in the network to fire up a `Node`.

Once the `Node` starts up without any errors, you will be able to search for files that have been shared by other peers in the same network.

## Built With
- gRPC: The project uses gRPC as its communication protocol along with protocol-buffers as the data-interchange format.

- fsnotify: The project makes use of [fsnotify](https://github.com/fsnotify/fsnotify) to detect file-system changes and indexe them dynamically, thus making them avaiable for search across the entire network in real-time.

## Authors
- Sandeep Dasika


## License
This project is licensed under the MIT License - see the LICENSE.md file for details