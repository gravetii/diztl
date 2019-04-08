package conf

import (
	"io/ioutil"
	"log"
	"os"
	"path/filepath"
	"time"

	"gopkg.in/yaml.v2"
)

var rootdir, _ = os.UserHomeDir()

var shareDirDefault = filepath.Join(rootdir, "Documents", "diztl", "share")
var outputDirDefault = filepath.Join(shareDirDefault, "output")

type dir struct {
	Share  []string `yaml:"share"`
	Output string   `yaml:"output"`
}

type conf struct {
	Tracker       map[string]string        `yaml:"tracker"`
	Node          map[string]string        `yaml:"node"`
	MinIndexFiles int32                    `yaml:"minIndexFiles"`
	Dir           dir                      `yaml:"dir"`
	Timeout       map[string]time.Duration `yaml:"timeout"`
	ChunkBufSize  int32                    `yaml:"chunkBufSize"`
}

var config = getConf()

func getConf() *conf {
	config := &conf{}
	log.Println("Reading config...")
	f, err := ioutil.ReadFile("config.yaml")
	if err != nil {
		log.Fatalf("Error while reading conf: %v ", err)
	}
	err = yaml.Unmarshal(f, config)
	if err != nil {
		log.Fatalf("Error while unmarshalling conf file: %v", err)
	}

	return config
}

// MinIndexFiles : The minimum cumulative number of files that each node should index to enter the network.
func MinIndexFiles() int32 {
	return config.MinIndexFiles
}

// ShareDirs : The share directories of this node that need to be indexed when the node starts up.
func ShareDirs() []string {
	dirs := config.Dir.Share
	if len(dirs) == 0 {
		dirs = []string{shareDirDefault}
		config.Dir.Share = dirs
	}

	log.Printf("Share dirs: %s", dirs)
	return dirs
}

// OutputDir : The output directory of this node where the downloaded files are placed.
func OutputDir() string {
	dir := config.Dir.Output
	if dir == "" {
		dir = outputDirDefault
		config.Dir.Output = dir
	}

	log.Printf("Output dir: %s", dir)
	return dir
}

// TrackerPort : The port of the tracker service.
func TrackerPort() string {
	return config.Tracker["port"]
}

// TrackerAddress : The address of the tracker.
func TrackerAddress() string {
	addr := config.Tracker["host"] + ":" + config.Tracker["port"]
	return addr
}

// NodePort : The port exposed by each Node for communication with the other nodes
// and the tracker.
func NodePort() string {
	return config.Node["port"]
}

// DownloadTimeout : The download timeout.
func DownloadTimeout() time.Duration {
	return time.Second * config.Timeout["download"]
}

// SearchTimeout : The search timeout.
func SearchTimeout() time.Duration {
	return time.Second * config.Timeout["search"]
}

// DisconnectTimeout : The disconnect timeout.
func DisconnectTimeout() time.Duration {
	return time.Second * config.Timeout["disconnect"]
}

// TrackerConnectTimeout : The tracker connect timeout.
func TrackerConnectTimeout() time.Duration {
	return time.Second * config.Timeout["trackerConnect"]
}

// NodeConnectTimeout : The node connect timeout.
func NodeConnectTimeout() time.Duration {
	return time.Second * config.Timeout["nodeConnect"]
}

// ChunkBufSize : The size of each chunk of a file.
func ChunkBufSize() int32 {
	return config.ChunkBufSize
}
