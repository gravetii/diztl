package startup

import (
	"errors"
	"os"

	"github.com/gravetii/diztl/addr"
	"github.com/gravetii/diztl/conf"
	"github.com/gravetii/diztl/dir"
	"github.com/gravetii/logger"
)

const (
	logFile = "diztl_new.log"
)

// Execute runs common startup steps for trackers and nodes.
func Execute() {
	// Load configuration.
	if err := conf.Load(); err != nil {
		panic(err)
	}

	// Initialize the logger.
	if err := loadLogger(); err != nil {
		panic(err)
	}

	// Fetch the host's IP address.
	if err := addr.Find(); err != nil {
		panic(err)
	}
}

func loadLogger() error {
	fpath, err := dir.GetLogPath(logFile)
	if err != nil {
		return err
	}

	f, err := os.OpenFile(fpath,
		os.O_RDWR|os.O_CREATE|os.O_APPEND, 0666)
	if err != nil {
		return errors.New("Could not open log file - " + err.Error())
	}

	logger.SetLevel("debug")
	logger.SetOutput(f)
	logger.Debugf("Successfully initialised logger.\n")
	return nil
}
