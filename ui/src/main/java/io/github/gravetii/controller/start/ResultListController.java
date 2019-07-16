package io.github.gravetii.controller.start;

import io.github.gravetii.client.DiztlClient;
import io.github.gravetii.controller.FxController;
import io.github.gravetii.scene.start.StartScene;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ResultListController implements FxController {
  private Stage stage;
  private StartScene parent;

  @FXML private TableView<FileResult> resultListTbl;
  @FXML private TableColumn<FileResult, String> fileNameTblCol;
  @FXML private TableColumn<FileResult, String> fileSizeTblCol;
  @FXML private TableColumn<FileResult, String> fileTypeTblCol;
  @FXML private TableColumn<FileResult, String> filePathTblCol;

  public ResultListController(Stage stage, StartScene parent) {
    this.stage = stage;
    this.parent = parent;
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
          row.setOnMouseClicked(
              event -> {
                if (event.getClickCount() == 2) {
                  if (!row.isEmpty()) {
                    FileResult result = row.getItem();
                    DiztlClient.get().download(result.getFile(), result.getSource(), parent);
                  }
                }
              });

          return row;
        });
  }

  public void show(FileResult value) {
    resultListTbl.getItems().add(value);
  }

  public void reset() {
    resultListTbl.getItems().clear();
  }
}