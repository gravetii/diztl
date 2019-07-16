package io.github.gravetii.controller.start;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import io.github.gravetii.client.connection.CommunicationClient;
import io.github.gravetii.controller.FxController;
import io.github.gravetii.scene.start.StartScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class FileSearchController implements FxController {
  private StartScene parent;

  @FXML private JFXTextField searchBox;
  @FXML private JFXButton searchBtn;

  public FileSearchController(StartScene parent) {
    this.parent = parent;
  }

  @FXML
  public void search(ActionEvent event) {
    String in = searchBox.getText();
    System.out.println("Searching for pattern: " + in);
    CommunicationClient.get().find(in, parent);
  }
}
