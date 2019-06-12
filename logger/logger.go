package logger

import (
	"log"
	"os"
	"path/filepath"

	"github.com/gravetii/diztl/conf"
)

// Log denotes the application wide logger used to write the process logs.
var Log *log.Logger

const (
	logFile = "diztl.log"
)

// Load loads the app wide logger.
func Load() {
	fpath := filepath.Join(conf.AppDir(), logFile)
	f, err := os.OpenFile(fpath,
		os.O_RDWR|os.O_CREATE|os.O_APPEND, 0666)
	if err != nil {
		panic(err)
	}

	Log = log.New(f, "", log.LstdFlags)
	Log.Println("Successfully initialized logger")
}
