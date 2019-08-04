package conf

import (
	"errors"
	"os"
	"path/filepath"
	"time"

	"github.com/fsnotify/fsnotify"
	"github.com/gravetii/viper"
)

var rootdir, _ = os.UserHomeDir()

var shareDirDefault = filepath.Join(rootdir, "Documents", "diztl", "share")
var downloadsDirDefault = filepath.Join(shareDirDefault, "downloads")
var appDirDefault = filepath.Join(rootdir, "Library", "Logs", "diztl")

type dir struct {
	Share     []string `yaml:"share"`
	Downloads string   `yaml:"downloads"`
	App       string   `yaml:"app"`
}

type conf struct {
	Tracker       map[string]string        `yaml:"tracker"`
	Node          map[string]string        `yaml:"node"`
	MinIndexFiles int32                    `yaml:"minIndexFiles"`
	Dir           dir                      `yaml:"dir"`
	Timeout       map[string]time.Duration `yaml:"timeout"`
	ChunkSize     int32                    `yaml:"chunkSize"`
	LogLevel      string                   `yaml:"logLevel"`
}

var config *conf

func init() {
	// Set the config file for viper.
	viper.SetConfigFile("config.yml")
}

// Load loads the app-wide configuration or returns an error if any.
func Load() error {
	err := load()
	if err != nil {
		return err
	}

	watch()
	return nil
}

func load() error {
	err := viper.ReadInConfig()
	if err != nil {
		return err
	}

	config = &conf{}
	err = viper.Unmarshal(config)
	if err != nil {
		return errors.New("Unable to unmarshall config into struct - " + err.Error())
	}

	return nil
}

func watch() {
	viper.WatchConfig()
	viper.OnConfigChange(func(e fsnotify.Event) {
		// If the config file is changed, load it again.
		load()
	})
}

// UpdateShareDirs sets the share directories in the config file to the given values.
func UpdateShareDirs(value []string) {
	set("dir.share", value)
}

// UpdateDownloadsDir sets the downlods directory in the config file to the given value.
func UpdateDownloadsDir(value string) {
	set("dir.downloads", value)
}

// UpdateTracker updates the IP address of the tracker in the config file.
func UpdateTracker(host string) {
	set("tracker.host", host)
}

func set(key string, value interface{}) {
	viper.Set(key, value)
	viper.WriteConfig()
}

// MinIndexFiles returns the minimum cumulative number of files that each node should index to enter the network.
func MinIndexFiles() int32 {
	return config.MinIndexFiles
}

// ShareDirs returns the share directories on this node that need to be indexed when the node starts up.
func ShareDirs() []string {
	dirs := config.Dir.Share
	if len(dirs) == 0 {
		dirs = []string{shareDirDefault}
		config.Dir.Share = dirs
	}

	return dirs
}

// DownloadsDir returns the downloads directory on this node where the downloaded files are placed.
func DownloadsDir() string {
	dir := config.Dir.Downloads
	if dir == "" {
		dir = downloadsDirDefault
		config.Dir.Downloads = dir
	}

	return dir
}

// AppDir returns the application directory on this node where process generated files are placed.
func AppDir() string {
	dir := config.Dir.App
	if dir == "" {
		dir = appDirDefault
		config.Dir.App = dir
	}

	return dir
}

// TrackerHost returns the host of the tracker.
func TrackerHost() string {
	return config.Tracker["host"]
}

// TrackerPort returns the port of the tracker.
func TrackerPort() string {
	return config.Tracker["port"]
}

// NodePort returns the port exposed by each Node for communication with the other nodes
// and the tracker.
func NodePort() string {
	return config.Node["port"]
}

// DownloadTimeout returns the download timeout.
func DownloadTimeout() time.Duration {
	return time.Second * config.Timeout["download"]
}

// PingTimeout returns the ping timeout.
func PingTimeout() time.Duration {
	return time.Second * config.Timeout["ping"]
}

// SearchTimeout returns the search timeout.
func SearchTimeout() time.Duration {
	return time.Second * config.Timeout["search"]
}

// DisconnectTimeout returns the disconnect timeout.
func DisconnectTimeout() time.Duration {
	return time.Second * config.Timeout["disconnect"]
}

// TrackerConnectTimeout returns the tracker connect timeout.
func TrackerConnectTimeout() time.Duration {
	return time.Second * config.Timeout["trackerConnect"]
}

// NodeConnectTimeout returns the node connect timeout.
func NodeConnectTimeout() time.Duration {
	return time.Second * config.Timeout["nodeConnect"]
}

// ChunkSize returns the size of each chunk of a file.
func ChunkSize() int32 {
	return config.ChunkSize
}

// LogLevel is the level for the app-wide logger.
func LogLevel() string {
	return config.LogLevel
}
