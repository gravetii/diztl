package io.github.gravetii.controller.download;

import io.github.gravetii.controller.FxController;
import io.github.gravetii.model.DownloadResult;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.*;
import javafx.scene.control.cell.ProgressBarTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.io.File;

public class DownloadResultController implements FxController {
  private static final Logger logger =
      LoggerFactory.getLogger(DownloadResultController.class.getCanonicalName());

  @FXML private TableView<DownloadResult> downloadResultTbl;
  @FXML private TableColumn<DownloadResult, String> fileNameTblCol;
  @FXML private TableColumn<DownloadResult, Double> progressTblCol;
  @FXML private TableColumn<DownloadResult, String> sizeTblCol;
  @FXML private TableColumn<DownloadResult, String> typeTblCol;
  @FXML private TableColumn<DownloadResult, String> statusTblCol;
  @FXML private TableColumn<DownloadResult, String> pathTblCol;

  public DownloadResultController() {}

  @FXML
  public void initialize() {
    fileNameTblCol.setCellValueFactory(new PropertyValueFactory<>("name"));
    statusTblCol.setCellValueFactory(new PropertyValueFactory<>("message"));
    sizeTblCol.setCellValueFactory(new PropertyValueFactory<>("size"));
    typeTblCol.setCellValueFactory(new PropertyValueFactory<>("type"));
    pathTblCol.setCellValueFactory(new PropertyValueFactory<>("path"));
    progressTblCol.setCellValueFactory(new PropertyValueFactory<>("progress"));
    progressTblCol.setCellFactory(ProgressBarTableCell.forTableColumn());
    downloadResultTbl.setRowFactory(
        callback -> {
          TableRow<DownloadResult> row = new TableRow<>();
          setContextMenu(row);
          row.setOnMouseClicked(
              event -> {
                if (event.getClickCount() == 2) {
                  if (!row.isEmpty()) {
                    open(row.getItem());
                  }
                }
              });
          return row;
        });
    setColumnWidths();
    setContextMenu();
  }

  private void addClearMenuItem(ContextMenu menu) {
    MenuItem clearMenuItem = new MenuItem("Clear All");
    clearMenuItem.disableProperty().bind(Bindings.isEmpty(downloadResultTbl.getItems()));
    clearMenuItem.setOnAction(event -> downloadResultTbl.getItems().clear());
    menu.getItems().add(clearMenuItem);
  }

  private void addOpenMenuItem(TableRow<DownloadResult> row, ContextMenu menu) {
    MenuItem openMenuItem = new MenuItem("Open");
    openMenuItem.setOnAction(event -> open(row.getItem()));
    menu.getItems().add(openMenuItem);
  }

  private void addShowInFolderMenuItem(TableRow<DownloadResult> row, ContextMenu menu) {
    MenuItem showInFolderMenuItem = new MenuItem("Show in folder");
    showInFolderMenuItem.setOnAction(event -> showInFolder(row.getItem()));
    menu.getItems().add(showInFolderMenuItem);
  }

  private void addRemoveMenuItem(TableRow<DownloadResult> row, ContextMenu menu) {
    MenuItem removeMenuItem = new MenuItem("Remove");
    removeMenuItem.setOnAction(event -> downloadResultTbl.getItems().remove(row.getItem()));
    menu.getItems().add(removeMenuItem);
  }

  private void setContextMenu() {
    ContextMenu menu = new ContextMenu();
    addClearMenuItem(menu);
    downloadResultTbl.setContextMenu(menu);
  }

  private void setContextMenu(TableRow<DownloadResult> row) {
    ContextMenu menu = new ContextMenu();
    addOpenMenuItem(row, menu);
    addShowInFolderMenuItem(row, menu);
    addRemoveMenuItem(row, menu);
    addClearMenuItem(menu);
    row.contextMenuProperty()
        .bind(Bindings.when(row.emptyProperty()).then((ContextMenu) null).otherwise(menu));
  }

  private void open(DownloadResult result) {
    if (result.isDone()) {
      try {
        Desktop.getDesktop().open(new File(result.getFilepath()));
      } catch (Exception e) {
        logger.error("Unable to open file - {}", result.getFilepath());
      }
    }
  }

  private void showInFolder(DownloadResult result) {
    if (result.isDone()) {
      try {
        Desktop.getDesktop().open(new File(result.getPath()));
      } catch (Exception e) {
        logger.error("Unable to open folder - {}", result.getPath());
      }
    }
  }

  private void setColumnWidths() {
    fileNameTblCol.prefWidthProperty().bind(downloadResultTbl.widthProperty().multiply(0.2));
    statusTblCol.prefWidthProperty().bind(downloadResultTbl.widthProperty().multiply(0.1));
    sizeTblCol.prefWidthProperty().bind(downloadResultTbl.widthProperty().multiply(0.1));
    typeTblCol.prefWidthProperty().bind(downloadResultTbl.widthProperty().multiply(0.1));
    progressTblCol.prefWidthProperty().bind(downloadResultTbl.widthProperty().multiply(0.2));
    pathTblCol.prefWidthProperty().bind(downloadResultTbl.widthProperty().multiply(0.3));
  }

  public void show(DownloadResult result) {
    downloadResultTbl.getItems().add(result);
  }
}
