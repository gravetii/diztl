<p align="center">
<img src="static/icon.png" width="450" height="400" alt="Diztl Icon" />
</p>

# Diztl

<p align="center">
<a href="https://opensource.org/licenses/MIT"><img src="https://img.shields.io/badge/License-MIT-yellow.svg" alt="License: MIT" /></a>
<a href="https://www.paypal.me/sandeepdasika"><img src="https://img.shields.io/badge/Donate-PayPal-green.svg" alt="Donate" /></a>
<a href="https://gitter.im/diztl/community?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge"><img src="https://badges.gitter.im/diztl/community.svg" alt="Chat on Gitter" /></a>
<a href="https://saythanks.io/to/gravetii"><img src="https://img.shields.io/badge/Say%20Thanks-!-1EAEDB.svg" alt="Say Thanks!" /></a>
</p>

A peer-to-peer file discovery and sharing tool for LANs!

## Implementation
Diztl consists of two main components:
- Tracker: The `Tracker`'s responsibility is to allow co-ordination and communication between the different `Node`s.
- Node: A `Node` is basically any peer in the network. It can share resources as well as request for and download resources from other `Node`s in the network.

The current implementation isn't completely decentralized in that the search queries from a `Node` are sent to the `Tracker` which then broadcasts the request across all peers in the network, requesting them to reply with the files they have that might be of interest to the caller `Node`.

Once the requesting `Node` decides on the file it wants to download from the target peer, communication happens solely between the two peers without any intervention from the `Tracker`.

When a `Node` first starts up, all the shared files are indexed and made available for search by other peers in the network. The `Node` then connects to the `Tracker` and registers itself after which it can participate in the network and communicate with other nodes.
By default, the download directory for each node is `<user's home directory>/diztl/downloads`. The share and download folders can be configured by clicking on the `Configure dirs` button.

For the formats of different request-response structures, take a look at the `diztl/diztl.proto` file which contains the protobuf specifications as well as the gRPC service definitions.

## Built With
- JavaFX: An open source, client application platform for desktop built on Java.
- gRPC: The project uses [gRPC](https://grpc.io/docs/) as its communication protocol along with protocol buffers as the data-interchange format.

## Contributing
Please read CONTRIBUTING.md for details on our code of conduct, and the process for submitting pull requests to the project.

## Authors
- Sandeep Dasika

## License
MIT

---

<div>Icons made by <a href="https://www.flaticon.com/authors/kiranshastry" title="Kiranshastry">Kiranshastry</a> from <a href="https://www.flaticon.com/" 			    title="Flaticon">www.flaticon.com</a> is licensed by <a href="http://creativecommons.org/licenses/by/3.0/" 			    title="Creative Commons BY 3.0" target="_blank">CC 3.0 BY</a></div>
