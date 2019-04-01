package shutdown

import (
	"os"
	"os/signal"
	"syscall"
)

var listener = createListener()
var closeables = []Closeable{}

func createListener() chan os.Signal {
	listener := make(chan os.Signal)
	signal.Notify(listener, syscall.SIGTERM, syscall.SIGINT)
	go func() {
		<-listener
		for _, cb := range closeables {
			cb.OnShutdown()
		}
	}()

	return listener
}

// Listen : Any component can ask to listen to shutdown signals through this call and
// provide an OnShutdown() implementation they want to be executed after receiving the signal.
func Listen(c Closeable) {
	closeables = append(closeables, c)
}

// Closeable : Interface that represents a closeable type, typically a component
// that requires open resources to be closed/invalidated.
type Closeable interface {
	OnShutdown()
}
