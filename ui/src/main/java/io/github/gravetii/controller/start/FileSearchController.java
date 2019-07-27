package io.github.gravetii.controller.start;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import io.github.gravetii.client.connection.CommunicationClient;
import io.github.gravetii.controller.FxController;
import io.github.gravetii.scene.start.StartScene;
import javafx.fxml.FXML;
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
  }

  @FXML
  public void search() {
    String in = searchBox.getText();
    if (!in.isEmpty()) {
      logger.debug("Searching for pattern: {}", in);
      CommunicationClient.get().find(in, parent);
    }
  }
}
