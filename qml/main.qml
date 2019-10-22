import QtQuick 2.12
import QtQuick.Layouts 1.1
import QtGraphicalEffects 1.12
import QtQuick.Controls 2.12

Rectangle {
  width: 1200
  height: 750
  id:root
  RowLayout {
    anchors.fill: parent
    FileSearch {
      id: fileSearch
      Layout.fillHeight: true
      Layout.preferredHeight: 300
    }
    MainTabView {
      Layout.fillHeight: true
      Layout.fillWidth: true
    }
  }
}