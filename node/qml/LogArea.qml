import QtQuick 2.12
import QtQuick.Layouts 1.1
import QtGraphicalEffects 1.12
import QtQuick.Controls 2.3
import QtQuick.Controls.Styles 1.4

Rectangle {
    border.color: "#D3D3D3"
    border.width: 1
    TextArea {
        id: logTextArea
        readOnly: true
        font.family: "Arial"
        font.pixelSize: 13
        textMargin: 10, 10, 10, 10
        text: "Welcome to diztl!\n\n"
        selectByMouse: true
        color: "#000000"
        background: null
        selectionColor: "steelblue"
    }

    Flickable {
        anchors.fill: parent
        TextArea.flickable: logTextArea
        ScrollBar.vertical: ScrollBar {}
        ScrollBar.horizontal: ScrollBar {}
    }

    Component.onCompleted: onComplete()
    function onComplete() {
        qmlBridge.index()
    }

    function addText(text) {
        logTextArea.append(text)
    }

    function addTextn(text) {
        logTextArea.append(text + "\n")
    }

    function addnText(text) {
        logTextArea.append("\n" + text)
    }

    Connections {
        target: qmlBridge
        onFileIndexed: {
            addText("Finished indexing file - " + fpath)
        }
        onIndexComplete: {
            addTextn("Finished indexing all shared files.")
            qmlBridge.registerToTracker()
        }
        onRegisterToTrackerComplete: {
            addTextn("Successfully registered to tracker at " + addr)
        }
    }

}