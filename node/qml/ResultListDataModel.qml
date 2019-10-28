import QtQuick 2.12
import QtQuick.Layouts 1.1
import QtGraphicalEffects 1.12
import QtQuick.Controls 1.4
import QtQuick.Controls.Styles 1.4

ListModel {
    id: resultListModel
    Component.onCompleted: {
        resultListModel.append(
            {
                "file": "solee_bonkers.mp4",
                "size": "123M",
                "type": ".mp4",
                "path": "/Users/s0d01bw/Documents/diztl/share"
            }
        )
    }
}