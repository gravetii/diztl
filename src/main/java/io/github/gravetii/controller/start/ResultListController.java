package io.github.gravetii.controller.start;

import io.github.gravetii.client.DiztlClient;
import io.github.gravetii.controller.FxController;
import io.github.gravetii.grpc.FileChunk;
import io.github.gravetii.grpc.FileMetadata;
import io.github.gravetii.model.DownloadResult;
import io.github.gravetii.scene.start.StartScene;
import io.github.gravetii.util.DiztlExecutorService;
import io.grpc.stub.StreamObserver;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ResultListController implements FxController {

  private static final Logger logger =
      LoggerFactory.getLogger(ResultListController.class.getCanonicalName());

  private static final String DOWNLOAD_FOLDER = "/Users/s0d01bw/Documents/diztl_downloads";

  private final DiztlClient client;
  private final StartScene scene;

  @FXML private TableView<FileResult> resultListTbl;
  @FXML private TableColumn<FileResult, String> fileNameTblCol;
  @FXML private TableColumn<FileResult, String> fileSizeTblCol;
  @FXML private TableColumn<FileResult, String> fileTypeTblCol;
  @FXML private TableColumn<FileResult, String> filePathTblCol;

  public ResultListController(DiztlClient client, StartScene scene) {
    this.client = client;
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
                if (event.getClickCount() == 2 && !row.isEmpty()) download(row.getItem());
              });

          return row;
        });

    setColumnWidths();
  }

  private void addDownloadMenuItem(TableRow<FileResult> row, ContextMenu menu) {
    MenuItem menuItem = new MenuItem("Download");
    menuItem.setOnAction(event -> download(row.getItem()));
    menu.getItems().add(menuItem);
  }

  private void setContextMenu(TableRow<FileResult> row) {
    ContextMenu menu = new ContextMenu();
    addDownloadMenuItem(row, menu);
    row.contextMenuProperty()
        .bind(Bindings.when(row.emptyProperty()).then((ContextMenu) null).otherwise(menu));
  }

  private StreamObserver<FileChunk> newObserver(FileMetadata file) {
    DownloadResult result = new DownloadResult(file, DOWNLOAD_FOLDER);
    scene.show(result);
    DiztlExecutorService.execute(result);
    return new StreamObserver<>() {
      BufferedOutputStream stream = null;

      @Override
      public void onNext(FileChunk chunk) {
        byte[] data = chunk.getData().toByteArray();
        if (chunk.getChunk() == 1) {
          final Path out = Paths.get(DOWNLOAD_FOLDER, chunk.getMetadata().getName());
          try {
            stream = new BufferedOutputStream(new FileOutputStream(out.toString()));
            result.first(chunk);
          } catch (FileNotFoundException e) {
            logger.error("Error while creating output file {}", out, e);
            result.onError(e);
          }
        }

        try {
          stream.write(data);
          result.onNext(chunk);
        } catch (IOException e) {
          logger.error("Error while writing chunk", e);
          result.onError(e);
        }
      }

      @Override
      public void onError(Throwable throwable) {
        try {
          stream.close();
          result.onError(throwable);
        } catch (IOException e) {
          logger.error("Error while closing output file", e);
        }
      }

      @Override
      public void onCompleted() {
        try {
          stream.close();
          result.onComplete();
        } catch (IOException e) {
          logger.error("Error while closing output file", e);
        }
      }
    };
  }

  private void download(FileResult result) {
    FileMetadata file = result.getFile();
    client.download(file, result.getSource(), this.newObserver(file));
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
