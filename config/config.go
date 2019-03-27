package config

import "time"

// TrackerHost : Tracker service host.
var TrackerHost = "localhost"

// TrackerPort : Tracker service port.
var TrackerPort = "50052"

// NodePort : Node service port.
var NodePort = "50051"

// TrackerAddress : The address of the Tracker service.
var TrackerAddress = TrackerHost + ":" + TrackerPort

// MinIndexFiles : Minimum number of files each node has to share to the network.
var MinIndexFiles int32 = 5

// DownloadTimeout : The timeout after which file download errors out.
var DownloadTimeout = 5 * time.Minute

// ChunkBufSize : The buffer size required for a single chunk of a file.
var ChunkBufSize = 1024 * 512

// TrackerConnectTimeout : Duration after which client connection to tracker times out.
var TrackerConnectTimeout = 5 * time.Second
