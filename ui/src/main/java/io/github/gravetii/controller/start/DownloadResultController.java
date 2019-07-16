package io.github.gravetii.controller.start;

import io.github.gravetii.client.handler.DownloadTask;
import io.github.gravetii.controller.FxController;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ProgressBarTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class DownloadResultController implements FxController {
  private Stage stage;

  @FXML private TableView<DownloadTask> downloadResultTbl;
  @FXML private TableColumn<DownloadTask, String> fileNameTblCol;
  @FXML private TableColumn<DownloadTask, Double> statusTblCol;
  @FXML private TableColumn<DownloadTask, String> sizeTblCol;
  @FXML private TableColumn<DownloadTask, String> typeTblCol;

  public DownloadResultController(Stage stage) {
    this.stage = stage;
  }

  @FXML
  public void initialize() {
    fileNameTblCol.setCellValueFactory(new PropertyValueFactory<>("name"));
    statusTblCol.setCellValueFactory(new PropertyValueFactory<>("progress"));
    statusTblCol.setCellFactory(ProgressBarTableCell.forTableColumn());
    sizeTblCol.setCellValueFactory(new PropertyValueFactory<>("size"));
    typeTblCol.setCellValueFactory(new PropertyValueFactory<>("type"));
  }

  public void show(DownloadTask value) {
    downloadResultTbl.getItems().add(value);
  }

  public void reset() {
    downloadResultTbl.getItems().clear();
  }

}
