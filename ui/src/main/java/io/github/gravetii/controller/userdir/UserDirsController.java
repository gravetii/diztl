package io.github.gravetii.controller.userdir;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import io.github.gravetii.AppContext;
import io.github.gravetii.controller.FxController;
import io.github.gravetii.scene.userdir.UserDirsScene;
import io.github.gravetii.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.util.*;

public class UserDirsController implements FxController {
  private Stage stage;
  private UserDirsScene parent;
  private Set<String> dirs;

  @FXML private JFXListView<Label> shareDirsList;
  @FXML private JFXButton addBtn;
  @FXML private JFXButton removeBtn;
  @FXML private JFXListView<Label> outputDir;
  @FXML private JFXButton folderBtn;

  public UserDirsController(Stage stage, UserDirsScene parent) {
    this.stage = stage;
    this.parent = parent;
    this.dirs = new HashSet<>();
  }

  @FXML
  public void initialize() {
    removeBtn
        .disableProperty()
        .bind(shareDirsList.getSelectionModel().selectedItemProperty().isNull());
    displayShareDirs(AppContext.getShareDirs());
    displayOutputDir(AppContext.getOutputDir());
  }

  @FXML
  public void addDir(ActionEvent event) {
    String dir = Utils.chooseDir(stage);
    if (dir != null && !dirs.contains(dir)) {
      displayShareDirs(Collections.singletonList(dir));
    }
  }

  @FXML
  public void removeDir(ActionEvent event) {
    List<Label> selectedItems = shareDirsList.getSelectionModel().getSelectedItems();
    if (selectedItems.size() != dirs.size()) {
      selectedItems.forEach(
          item -> {
            shareDirsList.getItems().remove(item);
            dirs.remove(item.getText());
          });
    }
  }

  public void displayShareDirs(List<String> dirs) {
    dirs.forEach(
        dir -> {
          shareDirsList.getItems().add(new Label(dir));
          this.dirs.add(dir);
        });
  }

  public void displayOutputDir(String dir) {
    outputDir.getItems().clear();
    outputDir.getItems().add(new Label(dir));
  }

  public List<String> getShareDirs() {
    return new ArrayList<>(dirs);
  }

  @FXML
  public void updateDir(ActionEvent event) {
    String dir = Utils.chooseDir(stage);
    if (dir != null) {
      displayOutputDir(dir);
    }
  }

  public String getOutputDir() {
    return outputDir.getItems().get(0).getText();
  }
}
