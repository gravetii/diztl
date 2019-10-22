import QtQuick.Controls 2.5

TabBar {
    id: bar
    width: parent.width
    TabButton {
        text: qsTr("Home")
    }
    TabButton {
        text: qsTr("Discover")
    }
    TabButton {
        text: qsTr("Activity")
    }
}