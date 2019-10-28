import QtQuick 2.12
import QtQuick.Layouts 1.1
import QtGraphicalEffects 1.12
import QtQuick.Controls 1.4
import QtQuick.Controls.Styles 1.4

TabView {
    tabPosition: Qt.BottomEdge
    style: TabViewStyle {
        tabOverlap: 1
        frameOverlap: 1
        tabBar: Rectangle {
            color: "#D3D3D3"
        }
        tabsAlignment: Qt.AlignLeft
        tab: Rectangle {
            color: styleData.selected ? "steelblue" : "lightsteelblue"
            border.color: "#D3D3D3"
            implicitWidth: Math.max(text.width + 10, 80)
            implicitHeight: 25
            radius: 2
            Text {
                id: text
                anchors.centerIn: parent
                text: styleData.title
                color: styleData.selected ? "white" : "black"
            }
        }
        frame: Rectangle {
            color: "steelblue"
        }
    }
    Tab {
        title: "log"
        LogArea {
            id: logArea
            Layout.fillHeight: true
            Layout.fillWidth: true
        }
    }
    Tab {
        id: resultListTab
        title: "Result list"
        ResultList {
            Layout.fillHeight: true
            Layout.fillWidth: true
        }
    }
}