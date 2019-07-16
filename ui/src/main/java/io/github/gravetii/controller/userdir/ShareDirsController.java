package io.github.gravetii.controller.userdir;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import io.github.gravetii.client.connection.CommunicationClient;
import io.github.gravetii.controller.FxController;
import io.github.gravetii.scene.userdir.UserDirsScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.*;

public class ShareDirsController implements FxController {
  private Stage stage;
  private UserDirsScene parent;
  private Set<String> dirs;

  @FXML private JFXListView<Label> dirsList;
  @FXML private JFXButton addBtn;
  @FXML private JFXButton removeBtn;

  public ShareDirsController(Stage stage, UserDirsScene parent) {
    this.stage = stage;
    this.parent = parent;
    this.dirs = new HashSet<>();
  }

  @FXML
  public void initialize() {
    CommunicationClient.get().getUserDirs(true, false, parent);
    removeBtn.disableProperty().bind(dirsList.getSelectionModel().selectedItemProperty().isNull());
  }

  @FXML
  public void addDir(ActionEvent event) {
    DirectoryChooser chooser = new DirectoryChooser();
    chooser.setTitle("Choose folder to share...");
    chooser.setInitialDirectory(new File(System.getProperty("user.dir")));
    File selectedDir = chooser.showDialog(stage);
    if (selectedDir != null && !dirs.contains(selectedDir.getPath())) {
      displayDirs(Collections.singletonList(selectedDir.getPath()));
    }
  }

  @FXML
  public void removeDir(ActionEvent event) {
    List<Label> selectedItems = dirsList.getSelectionModel().getSelectedItems();

    // To ensure that not all shared folders are removed.
    if (selectedItems.size() != dirs.size()) {
      selectedItems.forEach(
          item -> {
            dirsList.getItems().remove(item);
            dirs.remove(item.getText());
          });
    }
  }

  public void displayDirs(List<String> dirs) {
    dirs.forEach(
        dir -> {
          dirsList.getItems().add(new Label(dir));
          this.dirs.add(dir);
        });
  }

  public List<String> getFinalDirs() {
    return new ArrayList<>(dirs);
  }
}
