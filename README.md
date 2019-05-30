# <img src="diztl_icon.png" width="50" height="50" alt="Diztl Icon"> Diztl
A peer-to-peer file discovery and sharing platform written in Go!

## Getting started
```go get -u github.com/gravetii/diztl```

## Configuration steps

- Make a copy of the `config-template.yml` which is a bare-bones configuration file for diztl, and name it `config.yml` - `cp config-template.yml config.yml`. The `config.yml` file is the actual config file required for diztl to work. If you want to make any changes to the configuration, this is the file you want to edit. Note that this file is untracked in the repo (unlike the `config-template.yml`) which ensures that changes specific to your environment aren't reflected publicly. If any changes are to be reflected publicly, they need to go into the `config-template.yml` file.
- Optionally, you can also add the `config.yml` entry in your `.git/info/exclude` file so that git doesn't prompt you to add it to the stage all the time.

## Running diztl

In the root folder of the project:
- `go run tracker/main.go`: This runs the tracker node on the localhost.
- Specify the local IP address of the `Tracker` in the `config.yml` file to allow `Node`s to connect to it.
- `go run node/main.go`: Run this anywhere on any machine in the network to fire up a `Node`.

Once the `Node` starts up without any errors, you will be able to search for files that have been shared by other peers in the same network.

## Implementation
Diztl consists of two main components:
- Tracker: The `Tracker`'s responsibility is to allow co-ordination and communication between the different `Node`s.
- Node: A `Node` is basically any peer in the network. It can share resources as well as request for and download resources from other `Node`s in the network.

The current implementation isn't completely decentralized in that the search queries from a `Node` are sent to the `Tracker` which then broadcasts the request across all peers in the network, requesting them to reply back with the files they have that might be of interest to the caller `Node`.

Once the requesting `Node` decides on the file it wants to download from the target peer, communication happens solely between the two peers without any intervention from the `Tracker`.

When the `Node` first starts up, it indexes all the files to be shared in the default share folder located under `<user_root>/Documents/diztl/share`. By default, downloaded files are located under `/output` directory of the shared folder. The `Node` then connects to the `Tracker` and registers itself after which it can participate in the network and communicate with other nodes. The share and output folders can be configured by changing the corresponding configurations in the `config.yml` file (requires restart of tracker and/or node).

For the formats of different request-response structures, take a look at the `diztl/diztl.proto` file which contains the protobuf specifications as well as the gRPC service definitions.

## Built With
- gRPC: The project uses [gRPC](https://grpc.io/docs/) as its communication protocol along with protocol buffers as the data-interchange format.

- fsnotify: The project makes use of [fsnotify](https://github.com/fsnotify/fsnotify) to detect file-system changes and indexes them dynamically, thus making them avaiable for search across the entire network in real-time.


Edit [May 30, 2019]: Watching on file-system changes is currently disabled through config because of [this](https://github.com/gravetii/diztl/issues/7) issue. This means new files added to shared folders aren't immediately available to other peers without node restart.

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
