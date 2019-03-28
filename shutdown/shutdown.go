package shutdown

import (
	"os"
	"os/signal"
	"syscall"
)

var listener = createListener()
var callbacks = []Callback{}

func createListener() chan os.Signal {
	listener := make(chan os.Signal)
	signal.Notify(listener, syscall.SIGTERM, syscall.SIGINT)
	go func() {
		<-listener
		for _, cb := range callbacks {
			cb.Execute()
		}
	}()

	return listener
}

// Listen : Any component can ask to listen to shutdown signals through this call
// and provide a callback that they want to be executed after receiving the signal.
func Listen(cb Callback) {
	callbacks = append(callbacks, cb)
}

// Callback : Represents a callback which needs to be executed on
// receiving the shutdown signal.
type Callback interface {
	Execute()
}
