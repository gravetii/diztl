package io.github.gravetii.controller;

import com.jfoenix.controls.JFXButton;
import io.github.gravetii.client.DiztlClient;
import io.github.gravetii.scene.share.ShareFoldersScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.List;

public class ShareFoldersController implements FxController {

  @FXML private ListView<String> foldersList;
  @FXML private JFXButton addBtn;
  @FXML private JFXButton removeBtn;

  private Stage stage;
  private ShareFoldersScene parent;

  public ShareFoldersController(Stage stage, ShareFoldersScene parent) {
    this.stage = stage;
    this.parent = parent;
  }

  @FXML
  public void initialize() {
    DiztlClient.get().getUserDirs(true, false, parent);
  }

  @FXML
  public void addFolder(ActionEvent event) {
    System.out.println("Adding folder");
    DirectoryChooser chooser = new DirectoryChooser();
    chooser.setTitle("Choose folder to share...");
    chooser.setInitialDirectory(new File(System.getProperty("user.dir")));
    File selectedFolder = chooser.showDialog(stage);
    foldersList.getItems().add(selectedFolder.getPath());
  }

  @FXML
  public void removeFolder(ActionEvent event) {
    System.out.println("Removing item");
    List<String> selectedItems = foldersList.getSelectionModel().getSelectedItems();
    selectedItems.forEach(item -> {
      System.out.println(item);
      foldersList.getItems().remove(item);
    });
  }

  public void displayShareDirs(List<String> shareDirs) {
    shareDirs.forEach(item -> foldersList.getItems().add(item));
  }
}
