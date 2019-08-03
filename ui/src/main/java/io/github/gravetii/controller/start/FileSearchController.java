package io.github.gravetii.controller.start;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import io.github.gravetii.client.connection.CommunicationClient;
import io.github.gravetii.controller.FxController;
import io.github.gravetii.gen.Diztl;
import io.github.gravetii.scene.start.StartScene;
import io.github.gravetii.util.Utils;
import javafx.fxml.FXML;
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

  private StartScene parent;

  @FXML private AnchorPane parentCtnr;
  @FXML private JFXTextField searchBox;
  @FXML private JFXButton searchBtn;
  @FXML private HBox hboxCtnr;
  @FXML private ComboBox<String> sizeKey;
  @FXML private TextField sizeValue;
  @FXML private ComboBox<String> sizeUnit;
  @FXML private ComboBox<String> fileType;

  public FileSearchController(StartScene parent) {
    this.parent = parent;
  }

  @FXML
  public void initialize() {
    searchBox.setOnKeyPressed(
        event -> {
          if (event.getCode() == KeyCode.ENTER) {
            search();
          }
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

  @FXML
  public void search() {
    String query = searchBox.getText();
    if (!query.isEmpty()) {
      double sz = Double.parseDouble(sizeValue.getText());
      String unit = sizeUnit.getSelectionModel().getSelectedItem();
      long bytes = Utils.getByteCount(sz, unit);
      int key = sizeKey.getSelectionModel().getSelectedIndex();
      Diztl.SizeConstraint size =
          Diztl.SizeConstraint.newBuilder().setKey(key).setValue(bytes).build();
      int ftype = fileType.getSelectionModel().getSelectedIndex();
      Diztl.TypeConstraint type = Diztl.TypeConstraint.newBuilder().setType(ftype).build();
      Diztl.FileConstraint constraint =
          Diztl.FileConstraint.newBuilder().setCsize(size).setCtype(type).build();
      CommunicationClient.get().find(query, constraint, parent);
    }
  }
}
