package startup

import (
	"github.com/gravetii/diztl/conf"
	"github.com/gravetii/diztl/logger"
)

// Execute runs common startup steps for trackers and nodes.
func Execute() {
	// load configuration.
	if err := conf.Load(); err != nil {
		panic(err)
	}

	// initialize the logger.
	if err := logger.Load(); err != nil {
		panic(err)
	}
}
