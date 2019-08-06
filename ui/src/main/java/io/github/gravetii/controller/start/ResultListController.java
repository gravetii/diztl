package io.github.gravetii.controller.start;

import io.github.gravetii.client.connection.CommunicationClient;
import io.github.gravetii.controller.FxController;
import io.github.gravetii.scene.start.StartScene;
import io.github.gravetii.util.Utils;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.util.List;

public class ResultListController implements FxController {
  private Stage stage;
  private StartScene scene;

  @FXML private TableView<FileResult> resultListTbl;
  @FXML private TableColumn<FileResult, String> fileNameTblCol;
  @FXML private TableColumn<FileResult, String> fileSizeTblCol;
  @FXML private TableColumn<FileResult, String> fileTypeTblCol;
  @FXML private TableColumn<FileResult, String> filePathTblCol;

  public ResultListController(Stage stage, StartScene scene) {
    this.stage = stage;
    this.scene = scene;
  }

  @FXML
  public void initialize() {
    fileNameTblCol.setCellValueFactory(new PropertyValueFactory<>("name"));
    fileSizeTblCol.setCellValueFactory(new PropertyValueFactory<>("size"));
    fileTypeTblCol.setCellValueFactory(new PropertyValueFactory<>("type"));
    filePathTblCol.setCellValueFactory(new PropertyValueFactory<>("path"));
    resultListTbl.setRowFactory(
        callback -> {
          TableRow<FileResult> row = new TableRow<>();
          setContextMenu(row);
          row.setOnMouseClicked(
              event -> {
                if (event.getClickCount() == 2) {
                  if (!row.isEmpty()) {
                    download(row.getItem());
                  }
                }
              });

          return row;
        });

    setColumnWidths();
  }

  private void setContextMenu(TableRow<FileResult> row) {
    ContextMenu menu = new ContextMenu();
    MenuItem downloadMenuItem = new MenuItem("Download");
    MenuItem downloadToFolderMenuItem = new MenuItem("Download to...");
    MenuItem getFileListMenuItem = new MenuItem("Browse file list");
    downloadMenuItem.setOnAction(
        event -> {
          download(row.getItem());
        });
    downloadToFolderMenuItem.setOnAction(
        event -> {
          String dir = Utils.chooseDir(stage);
          if (dir != null) {
            downloadToFolder(row.getItem(), dir);
          }
        });
    getFileListMenuItem.setOnAction(event -> {
      CommunicationClient.get().getFileList(scene, row.getItem().getSource(), row.getItem().getFile());
    });

    menu.getItems().add(downloadMenuItem);
    menu.getItems().add(downloadToFolderMenuItem);
    menu.getItems().add(getFileListMenuItem);
    row.contextMenuProperty()
        .bind(Bindings.when(row.emptyProperty()).then((ContextMenu) null).otherwise(menu));
  }

  private void download(FileResult result) {
    CommunicationClient.get().download(result.getFile(), result.getSource(), scene);
  }

  private void downloadToFolder(FileResult result, String downloadsDir) {
    CommunicationClient.get().download(result.getFile(), result.getSource(), scene, downloadsDir);
  }

  private void setColumnWidths() {
    fileNameTblCol.prefWidthProperty().bind(resultListTbl.widthProperty().multiply(0.3));
    fileSizeTblCol.prefWidthProperty().bind(resultListTbl.widthProperty().multiply(0.2));
    fileTypeTblCol.prefWidthProperty().bind(resultListTbl.widthProperty().multiply(0.2));
    filePathTblCol.prefWidthProperty().bind(resultListTbl.widthProperty().multiply(0.3));
  }

  public void show(FileResult result) {
    resultListTbl.getItems().add(result);
  }
}
