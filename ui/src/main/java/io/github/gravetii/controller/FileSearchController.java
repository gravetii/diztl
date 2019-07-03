package io.github.gravetii.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import io.github.gravetii.client.DiztlClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class FileSearchController implements FxController {
  private Stage stage;
  private DiztlClient client;

  @FXML private JFXTextField searchBox;
  @FXML private JFXButton searchBtn;

  public FileSearchController(Stage stage) {
    this.stage = stage;
    this.client = new DiztlClient();
  }

  @FXML
  public void search(ActionEvent event) {
    String in = searchBox.getText();
    System.out.println("Searching for pattern: " + in);
    client.find(in);
  }

}
