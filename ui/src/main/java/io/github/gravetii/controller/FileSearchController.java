package io.github.gravetii.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import io.github.gravetii.client.DiztlClient;
import io.github.gravetii.scene.start.StartScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class FileSearchController implements FxController {
  private StartScene parent;
  private DiztlClient client;

  @FXML private JFXTextField searchBox;
  @FXML private JFXButton searchBtn;

  public FileSearchController(StartScene parent) {
    this.parent = parent;
    this.client = new DiztlClient();
  }

  @FXML
  public void search(ActionEvent event) {
    String in = searchBox.getText();
    System.out.println("Searching for pattern: " + in);
    client.find(in, parent);
  }

}
