package io.github.gravetii.controller.download;

import io.github.gravetii.client.handler.DownloadResult;
import io.github.gravetii.controller.FxController;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ProgressBarTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class DownloadResultController implements FxController {
  private Stage stage;

  @FXML private TableView<DownloadResult> downloadResultTbl;
  @FXML private TableColumn<DownloadResult, String> fileNameTblCol;
  @FXML private TableColumn<DownloadResult, Double> progressTblCol;
  @FXML private TableColumn<DownloadResult, String> sizeTblCol;
  @FXML private TableColumn<DownloadResult, String> typeTblCol;
  @FXML private TableColumn<DownloadResult, String> statusTblCol;

  public DownloadResultController(Stage stage) {
    this.stage = stage;
  }

  @FXML
  public void initialize() {
    fileNameTblCol.setCellValueFactory(new PropertyValueFactory<>("name"));
    statusTblCol.setCellValueFactory(new PropertyValueFactory<>("message"));
    sizeTblCol.setCellValueFactory(new PropertyValueFactory<>("size"));
    typeTblCol.setCellValueFactory(new PropertyValueFactory<>("type"));
    progressTblCol.setCellValueFactory(new PropertyValueFactory<>("progress"));
    progressTblCol.setCellFactory(ProgressBarTableCell.forTableColumn());
  }

  public void show(DownloadResult result) {
    downloadResultTbl.getItems().add(result);
  }

  public void reset() {
    downloadResultTbl.getItems().clear();
  }
}
