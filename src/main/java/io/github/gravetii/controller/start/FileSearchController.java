package io.github.gravetii.controller.start;

import io.github.gravetii.client.DiztlClient;
import io.github.gravetii.client.NodeNotConnectedException;
import io.github.gravetii.controller.FxController;
import io.github.gravetii.grpc.FileConstraint;
import io.github.gravetii.grpc.SearchResp;
import io.github.gravetii.grpc.SizeConstraint;
import io.github.gravetii.grpc.TypeConstraint;
import io.github.gravetii.model.SearchRequest;
import io.github.gravetii.scene.start.ResultListComponent;
import io.github.gravetii.scene.start.StartScene;
import io.github.gravetii.util.Utils;
import io.grpc.stub.StreamObserver;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileSearchController implements FxController {
  private static final Logger logger =
      LoggerFactory.getLogger(FileSearchController.class.getCanonicalName());

  private final DiztlClient client;
  private final StartScene scene;

  @FXML private AnchorPane parentCtnr;
  @FXML private TextField searchBox;
  @FXML private Button searchBtn;
  @FXML private HBox hboxCtnr;
  @FXML private ComboBox<String> sizeKey;
  @FXML private TextField sizeValue;
  @FXML private ComboBox<String> sizeUnit;
  @FXML private ComboBox<String> fileType;

  public FileSearchController(DiztlClient client, StartScene scene) {
    this.client = client;
    this.scene = scene;
  }

  @FXML
  public void initialize() {
    searchBox.setOnKeyPressed(
        e -> {
          if (e.getCode() == KeyCode.ENTER) search();
        });

    populateSizeKeys();
    populateSizeUnits();
    populateFileTypes();
    sizeValue.setText("0");
  }

  private void populateSizeKeys() {
    sizeKey.getItems().add("At least");
    sizeKey.getItems().add("Less than");
    sizeKey.getSelectionModel().select(0);
  }

  private void populateSizeUnits() {
    sizeUnit.getItems().add("kB");
    sizeUnit.getItems().add("MB");
    sizeUnit.getItems().add("GB");
    sizeUnit.setValue("MB");
    sizeUnit.getSelectionModel().select(1);
  }

  private void populateFileTypes() {
    fileType.getItems().add("Any");
    fileType.getItems().add("video (.mp4, .mkv, .mpeg, .mov, .webm, .flv)");
    fileType.getItems().add("image (.png, .jpg, .ico, .gif)");
    fileType.getItems().add("audio (.mp3, .wav)");
    fileType.getItems().add("document (.txt, .pdf, .ppt, .doc, .xls, .csv)");
    fileType.getItems().add("compressed (.zip, .gz, .7z, .rar)");
    fileType.getItems().add("executable (.exe, .dmg, .sh)");
    fileType.getSelectionModel().select(0);
  }

  private StreamObserver<SearchResp> newObserver(String query) {
    ResultListComponent component = scene.addNewSearchTab(query);
    return new StreamObserver<>() {
      @Override
      public void onNext(SearchResp resp) {
        resp.getFilesList().forEach(file -> component.show(new FileResult(file, resp.getNode())));
      }

      @Override
      public void onError(Throwable throwable) {
        logger.error("File search error", throwable);
      }

      @Override
      public void onCompleted() {
        logger.info("Search completed");
      }
    };
  }

  @FXML
  public void search() {
    String query = searchBox.getText();
    if (!query.isEmpty()) {
      double sz = Double.parseDouble(sizeValue.getText());
      String unit = sizeUnit.getSelectionModel().getSelectedItem();
      long bytes = Utils.getByteCount(sz, unit);
      int key = sizeKey.getSelectionModel().getSelectedIndex();
      SizeConstraint size = SizeConstraint.newBuilder().setKey(key).setValue(bytes).build();
      int ftype = fileType.getSelectionModel().getSelectedIndex();
      TypeConstraint type = TypeConstraint.newBuilder().setType(ftype).build();
      FileConstraint constraint = FileConstraint.newBuilder().setCsize(size).setCtype(type).build();
      try {
        SearchRequest request = new SearchRequest(query, constraint, newObserver(query));
        client.search(request);
      } catch (NodeNotConnectedException e) {
        scene.writeConnectionErrorToLog();
      }
    }
  }
}
