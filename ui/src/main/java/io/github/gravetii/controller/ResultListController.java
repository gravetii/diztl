package io.github.gravetii.controller;

import io.github.gravetii.client.DiztlClient;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ResultListController implements FxController {
  private Stage stage;

  @FXML private TableView<FileResult> resultListTbl;
  @FXML private TableColumn<FileResult, String> fileNameTblCol;
  @FXML private TableColumn<FileResult, String> fileSizeTblCol;

  public ResultListController(Stage stage) {
    this.stage = stage;
  }

  @FXML
  public void initialize() {
    this.fileNameTblCol.setCellValueFactory(new PropertyValueFactory<>("fileName"));
    this.fileSizeTblCol.setCellValueFactory(new PropertyValueFactory<>("fileSize"));
    this.resultListTbl.setRowFactory(
        callback -> {
          TableRow<FileResult> row = new TableRow<>();
          row.setOnMouseClicked(
              event -> {
                if (event.getClickCount() == 2) {
                  if (!row.isEmpty()) {
                    FileResult result = row.getItem();
                    DiztlClient.get().download(result.getFile(), result.getSource());
                  }
                }
              });

          return row;
        });
  }

  public void show(FileResult value) {
    this.resultListTbl.getItems().add(value);
  }

  public void reset() {
    this.resultListTbl.getItems().clear();
  }
}
