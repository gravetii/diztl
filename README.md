# <img src="diztl_icon.png" width="50" height="50" alt="Diztl Icon"> Diztl
A distributed peer-to-peer file discovery and sharing platform written in Go!

## Getting started
```go get -u github.com/gravetii/diztl```

## Implementation
The diztl peer-to-peer file discovery and sharing platform consists of two main components:
- Tracker: The `Tracker`'s responsibility is to allow co-ordination and communication between the different `Node`s.
- Node: A `Node` is basically any peer in the network. It can share resources as well as request for and download resources from other `Node`s in the network.

The current implementation isn't completely decentralized in that the search queries from a `Node` are sent to the `Tracker` which then broadcasts the request across all peers in the network, requesting them to reply back with the files it has that might be of interest to the caller `Node`.

Once the requesting `Node` decides on the file it wants to download from the target peer, communication happens solely between the two peers without any intervention from the `Tracker`.

When the `Node` first starts up, it indexes all the files to be shared in the default share folder located under `<user_root>/Documents/diztl/share`. By default, downloaded files are located under `/output` directory of the shared folder. It then connects to the `Tracker` and registers itself after which it can participate in the network and communicate with other nodes.

## Running diztl

In the root folder of the project:
- `go run tracker/main.go`: This runs the tracker node on the localhost.
- Specify the local IP address of the `Tracker` in `config.go` under `TrackerHost` to allow `Node`s to connect to it.
- `go run node/main.go`: Run this anywhere on any machine in the network to fire up a `Node`.

Once the `Node` starts up without any errors, you will be able to search for files that have been shared by other peers in the same network.

## Built With
- gRPC: The project uses [gRPC](https://grpc.io/docs/) as its communication protocol along with protocol buffers as the data-interchange format.

- fsnotify: The project makes use of [fsnotify](https://github.com/fsnotify/fsnotify) to detect file-system changes and indexes them dynamically, thus making them avaiable for search across the entire network in real-time.

## Contributing
Please read CONTRIBUTING.md for details on our code of conduct, and the process for submitting pull requests to the project.

## IRC Channel
You can join the IRC channel `#diztl` on freenode to discuss more about the project.

## Authors
- Sandeep Dasika


## License
This project is licensed under the MIT License - see the LICENSE.md file for details

---

<div>Icons made by <a href="https://www.flaticon.com/authors/kiranshastry" title="Kiranshastry">Kiranshastry</a> from <a href="https://www.flaticon.com/" 			    title="Flaticon">www.flaticon.com</a> is licensed by <a href="http://creativecommons.org/licenses/by/3.0/" 			    title="Creative Commons BY 3.0" target="_blank">CC 3.0 BY</a></div>