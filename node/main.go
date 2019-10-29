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

// Start the diztl UI.
func startUI() {
	core.QCoreApplication_SetAttribute(core.Qt__AA_EnableHighDpiScaling, true)
	widgets.NewQApplication(len(os.Args), os.Args)
	quickcontrols2.QQuickStyle_SetStyle("Material")
	view := quick.NewQQuickView(nil)
	view.SetMinimumSize(core.NewQSize2(650, 400))
	view.SetResizeMode(quick.QQuickView__SizeRootObjectToView)
	view.SetTitle("Diztl")
	var qmlBridge = NewQmlBridge(nil)
	qmlBridge.configure(node)
	view.RootContext().SetContextProperty("qmlBridge", qmlBridge)
	view.SetSource(core.NewQUrl3("./qml/main.qml", 0))
	view.Show()
	widgets.QApplication_Exec()
}

func startServer() {
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

	startup.Execute()
	go startServer()
	startUI()
}
