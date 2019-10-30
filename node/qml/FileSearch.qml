import QtQuick 2.12
import QtQuick.Layouts 1.1
import QtGraphicalEffects 1.12
import QtQuick.Controls 2.3

Rectangle {
    width: 325
    height: 300
    Column {
        spacing: 25
        Row {
            x: 10
            y: 20
            spacing: 20
            TextField {
                id: searchBox
                width: 200
                height: 50
                placeholderText: "Search..."
                color: "#000000"
                selectionColor: "steelblue"
                selectByMouse: true
                Keys.onReturnPressed: {
                    searchBtn.search()
                }
                Keys.onEnterPressed: {
                    searchBtn.search()
                }
            }
            Button {
                id: searchBtn
                width: 80
                height: 45
                text: "Go!"
                onClicked: search()
                function search() {
                    var text = searchBox.text.trim()
                    if (text != "") {
                        qmlBridge.search(text,
                        fileSizeKey.currentIndex,
                        fileSizeValue.text.trim(),
                        fileSizeUnit.currentIndex,
                        fileType.currentIndex
                        )
                    } else {
                        searchBox.text = ""
                    }
                }
            }
        }

        Row {
            x: 10
            spacing: 10
            ComboBox {
                id: fileSizeKey
                currentIndex: 0
                width: 120
                height: 50
                model: ["At least", "Less than"]
            }
            TextField {
                id: fileSizeValue
                width: 80
                height: 50
                text: "0"
                maximumLength: 4
                horizontalAlignment: TextInput.AlignHCenter
            }
            ComboBox {
                id: fileSizeUnit
                currentIndex: 1
                width: 80
                height: 50
                model: ["kB", "MB", "GB"]
            }
        }

        Row {
            x: 10
            spacing: 10
            ComboBox {
                id: fileType
                currentIndex: 0
                width: 300
                height: 50
                model: [
                    "Any",
                    "video (.mp4, .mkv, .mpeg, .mov, .webm, .flv)",
                    "image (.png, .jpg, .ico, .gif)",
                    "audio (.mp3, .wav)",
                    "document (.txt, .pdf, .ppt, .doc, .xls, .csv)",
                    "compressed (.zip, .gz, .7z, .rar)",
                    "executable (.exe, .dmg, .sh)",
                ]
            }
        }
    }
}

