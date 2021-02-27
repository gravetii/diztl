package io.github.gravetii.controller.start;

import io.github.gravetii.client.DiztlClient;
import io.github.gravetii.controller.FxController;
import io.github.gravetii.scene.start.StartScene;
import io.github.gravetii.service.DownloadService;
import io.github.gravetii.store.DBService;
import io.github.gravetii.util.FxUtils;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResultListController implements FxController {

  private static final Logger logger =
      LoggerFactory.getLogger(ResultListController.class.getCanonicalName());

  private final DBService store;
  private final StartScene scene;
  private final DownloadService service;

  @FXML private TableView<FileResult> resultListTbl;
  @FXML private TableColumn<FileResult, String> fileNameTblCol;
  @FXML private TableColumn<FileResult, String> fileSizeTblCol;
  @FXML private TableColumn<FileResult, String> fileTypeTblCol;
  @FXML private TableColumn<FileResult, String> filePathTblCol;

  public ResultListController(DiztlClient client, DBService store, StartScene scene) {
    this.store = store;
    this.scene = scene;
    this.service = new DownloadService(client, scene);
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
              e -> {
                if (e.getClickCount() == 2 && !row.isEmpty())
                  service.download(row.getItem(), store.getDownloadDir());
              });

          return row;
        });

    setColumnWidths();
  }

  private void addDownloadMenuItem(TableRow<FileResult> row, ContextMenu menu) {
    MenuItem menuItem = new MenuItem("Download");
    menuItem.setOnAction(e -> service.download(row.getItem(), store.getDownloadDir()));
    menu.getItems().add(menuItem);
  }

  private void addDownloadToFolderMenuItem(TableRow<FileResult> row, ContextMenu menu) {
    MenuItem menuItem = new MenuItem("Download to folder");
    menuItem.setOnAction(
        e -> {
          String dir = FxUtils.chooseDir(scene.getWindow());
          if (dir != null) service.download(row.getItem(), dir);
        });

    menu.getItems().add(menuItem);
  }

  private void setContextMenu(TableRow<FileResult> row) {
    ContextMenu menu = new ContextMenu();
    addDownloadMenuItem(row, menu);
    addDownloadToFolderMenuItem(row, menu);
    row.contextMenuProperty()
        .bind(Bindings.when(row.emptyProperty()).then((ContextMenu) null).otherwise(menu));
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
