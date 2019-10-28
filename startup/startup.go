package startup

import (
	"os"

	"github.com/gravetii/diztl/addr"
	"github.com/gravetii/diztl/conf"
	"github.com/gravetii/diztl/dir"
	"github.com/gravetii/logger"
	"github.com/pkg/errors"
)

const (
	logFile = "diztl.log"
)

// Execute runs common startup steps for trackers and nodes.
func Execute() {
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
		return errors.Wrap(err, "Couldn't open log file")
	}

	logger.SetLevel(conf.LogLevel())
	logger.SetOutput(f)
	logger.Debugf("Successfully initialised logger.\n")
	return nil
}
