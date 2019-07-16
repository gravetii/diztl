package io.github.gravetii.controller.start;

import io.github.gravetii.controller.FxController;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class DownloadResultController implements FxController {
  private Stage stage;

  @FXML private TableView<DownloadResult> downloadResultTbl;
  @FXML private TableColumn<DownloadResult, String> fileNameTblCol;
  @FXML private TableColumn<DownloadResult, String> statusTblCol;
  @FXML private TableColumn<DownloadResult, String> sizeTblCol;
  @FXML private TableColumn<DownloadResult, String> typeTblCol;

  public DownloadResultController(Stage stage) {
    this.stage = stage;
  }

  @FXML
  public void initialize() {
    fileNameTblCol.setCellValueFactory(new PropertyValueFactory<>("name"));
    statusTblCol.setCellValueFactory(new PropertyValueFactory<>("status"));
    sizeTblCol.setCellValueFactory(new PropertyValueFactory<>("size"));
    typeTblCol.setCellValueFactory(new PropertyValueFactory<>("type"));
  }

  public void show(DownloadResult value) {
    downloadResultTbl.getItems().add(value);
  }

  public void reset() {
    downloadResultTbl.getItems().clear();
  }

}
