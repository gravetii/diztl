package logger

import (
	"log"
	"os"

	"github.com/gravetii/diztl/dir"
)

// Log denotes the application wide logger used to write the process logs.
var Log *log.Logger

const (
	logFile = "diztl.log"
)

// Load loads the app wide logger or returns an error if any.
func Load() error {
	fpath, err := dir.GetLogPath(logFile)
	if err != nil {
		return err
	}

	f, err := os.OpenFile(fpath,
		os.O_RDWR|os.O_CREATE|os.O_APPEND, 0666)
	if err != nil {
		return err
	}

	Log = log.New(f, "", log.LstdFlags)
	Log.Println("Successfully initialized logger")
	return nil
}
