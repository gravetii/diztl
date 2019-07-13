package io.github.gravetii.controller;

import com.jfoenix.controls.JFXButton;
import io.github.gravetii.client.DiztlClient;
import io.github.gravetii.scene.share.UserDirsScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.List;

public class UserDirsController implements FxController {

  @FXML private ListView<String> dirsList;
  @FXML private JFXButton addBtn;
  @FXML private JFXButton removeBtn;
  @FXML private JFXButton submitBtn;

  private Stage stage;
  private UserDirsScene parent;

  public UserDirsController(Stage stage, UserDirsScene parent) {
    this.stage = stage;
    this.parent = parent;
  }

  @FXML
  public void initialize() {
    DiztlClient.get().getUserDirs(true, false, parent);
  }

  @FXML
  public void addDir(ActionEvent event) {
    System.out.println("Adding folder");
    DirectoryChooser chooser = new DirectoryChooser();
    chooser.setTitle("Choose folder to share...");
    chooser.setInitialDirectory(new File(System.getProperty("user.dir")));
    File selectedFolder = chooser.showDialog(stage);
    dirsList.getItems().add(selectedFolder.getPath());
  }

  @FXML
  public void removeDir(ActionEvent event) {
    System.out.println("Removing item");
    List<String> selectedItems = dirsList.getSelectionModel().getSelectedItems();
    selectedItems.forEach(item -> {
      System.out.println(item);
      dirsList.getItems().remove(item);
    });
  }

  @FXML
  public void onSubmit(ActionEvent event) {
    System.out.println("Updating user folders");
    DiztlClient.get().updateUserDirs(dirsList.getItems(), "");
  }

  public void displayShareDirs(List<String> shareDirs) {
    shareDirs.forEach(item -> dirsList.getItems().add(item));
  }
}
