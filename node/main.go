package main

import (
	"fmt"
	"net"
	"os"

	"github.com/gravetii/diztl/addr"
	"github.com/gravetii/diztl/conf"
	"github.com/gravetii/diztl/diztl"
	"github.com/gravetii/diztl/service"
	"github.com/gravetii/diztl/startup"
	"github.com/gravetii/logger"
	"github.com/therecipe/qt/core"
	"github.com/therecipe/qt/quick"
	"github.com/therecipe/qt/quickcontrols2"
	"github.com/therecipe/qt/widgets"
	"google.golang.org/grpc"
)

const (
	configFile = "node-config.yml"
)

var node *service.NodeService

// QmlBridge to pass data back and forth with the frontend.
type QmlBridge struct {
	core.QObject
	// QmlBridge constructor
	_ func() `constructor:"init"`

	// slots
	_ func(term string) `slot:"search"`
	_ func()            `slot:"index"`

	// signals
	_ func(fpath string) `signal:"indexComplete"`
}

func (qmlBridge *QmlBridge) init() {
	fmt.Println("Initializing QmlBridge...")
}

func (qmlBridge *QmlBridge) configure() {
	// connect the search slot whenever the user searches
	// for files with a query term.
	qmlBridge.ConnectSearch(func(term string) {
		fmt.Println("Searching for string:", term)
	})

	qmlBridge.ConnectIndex(func() {
		paths := make(chan string)
		go func() {
			for p := range paths {
				qmlBridge.IndexComplete(p)
			}
		}()
		go node.Index(paths)
	})
}

// Start the diztl UI.
func startUI() {
	core.QCoreApplication_SetAttribute(core.Qt__AA_EnableHighDpiScaling, true)
	widgets.NewQApplication(len(os.Args), os.Args)
	quickcontrols2.QQuickStyle_SetStyle("Material")
	view := quick.NewQQuickView(nil)
	view.SetMinimumSize(core.NewQSize2(600, 300))
	view.SetResizeMode(quick.QQuickView__SizeRootObjectToView)
	view.SetTitle("Diztl")
	var qmlBridge = NewQmlBridge(nil)
	qmlBridge.configure()
	view.RootContext().SetContextProperty("qmlBridge", qmlBridge)
	view.SetSource(core.NewQUrl3("./qml/main.qml", 0))
	view.Show()
	widgets.QApplication_Exec()
}

func startServer() {
	// Execute the initial startup steps
	startup.Execute()

	lis, err := net.Listen("tcp", ":"+conf.NodePort())
	if err != nil {
		logger.Errorf("Unable to start node - %v\n", err)
		return
	}

	s := grpc.NewServer()
	node = service.NewNode(s)
	diztl.RegisterDiztlServiceServer(s, node)
	node.Init()
	logger.Infof("Node %s is now up...\n", addr.LocalIP())
	fmt.Printf("You are now online!\n")
	serr := s.Serve(lis)
	if serr != nil {
		logger.Errorf("Failed to serve - %v\n", err)
		return
	}

	logger.Infof("Node successfully shut down.\n")
}

func main() {
	if err := conf.Load(configFile); err != nil {
		panic(err)
	}

	go startServer()
	startUI()
}
