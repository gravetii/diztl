import QtQuick 2.12
import QtQuick.Layouts 1.1
import QtGraphicalEffects 1.12
import QtQuick.Controls 2.3
import QtQuick.Controls.Styles 1.4

Rectangle {
    border.color: "#D3D3D3"
    border.width: 1
    TextArea {
        readOnly: true
        textMargin: 10, 10, 10, 10
        text: "Welcome to diztl!"
        selectByMouse: true
        color: "#000000"
        background: null
        selectionColor: "steelblue"
    }
}