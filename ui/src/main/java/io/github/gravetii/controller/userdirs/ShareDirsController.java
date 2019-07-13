package io.github.gravetii.controller.userdirs;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import io.github.gravetii.client.DiztlClient;
import io.github.gravetii.controller.FxController;
import io.github.gravetii.scene.userdir.UserDirsScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.List;

public class ShareDirsController implements FxController {
  private Stage stage;
  private UserDirsScene parent;

  @FXML private JFXListView<Label> dirsList;
  @FXML private JFXButton addBtn;
  @FXML private JFXButton removeBtn;

  public ShareDirsController(Stage stage, UserDirsScene parent) {
    this.stage = stage;
    this.parent = parent;
  }

  @FXML
  public void initialize() {
    DiztlClient.get().getUserDirs(true, false, parent);
    dirsList.setDepth(1);
    removeBtn.disableProperty().bind(dirsList.getSelectionModel().selectedItemProperty().isNull());
  }

  @FXML
  public void addDir(ActionEvent event) {
    DirectoryChooser chooser = new DirectoryChooser();
    chooser.setTitle("Choose folder to share...");
    chooser.setInitialDirectory(new File(System.getProperty("user.dir")));
    File selectedFolder = chooser.showDialog(stage);
    if (selectedFolder != null) {
      dirsList.getItems().add(new Label(selectedFolder.getPath()));
    }
  }

  @FXML
  public void removeDir(ActionEvent event) {
    System.out.println("Removing item");
    List<Label> selectedItems = dirsList.getSelectionModel().getSelectedItems();
    selectedItems.forEach(item -> {
      dirsList.getItems().remove(item);
    });
  }

  public void displayDirs(List<String> dirs) {
    dirs.forEach(dir -> {
      dirsList.getItems().add(new Label(dir));
    });
  }

}
