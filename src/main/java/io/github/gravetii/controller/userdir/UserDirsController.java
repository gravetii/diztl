package io.github.gravetii.controller.userdir;

import io.github.gravetii.controller.FxController;
import io.github.gravetii.store.DBService;
import io.github.gravetii.util.FxUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserDirsController implements FxController {

  private static final Logger logger =
      LoggerFactory.getLogger(UserDirsController.class.getCanonicalName());

  private final DBService dbService;

  private final Set<String> dirs = new HashSet<>();
  private boolean shareDirsUpdated = false;
  private boolean downloadsDirUpdated = false;

  @FXML private ListView<Label> shareDirsList;
  @FXML private Button addBtn;
  @FXML private Button removeBtn;
  @FXML private ListView<Label> downloadsDir;
  @FXML private Button folderBtn;

  public UserDirsController(DBService dbService) {
    this.dbService = dbService;
  }

  @FXML
  public void initialize() {
    removeBtn
        .disableProperty()
        .bind(shareDirsList.getSelectionModel().selectedItemProperty().isNull());

    Set<String> shareDirs = dbService.getShareDirs();
    String downloadsDir = dbService.getDownloadsDir();
    displayShareDirs(shareDirs);
    displayDownloadsDir(downloadsDir);
  }

  @FXML
  public void addDir(ActionEvent e) {
    Stage stage = (Stage) shareDirsList.getScene().getWindow();
    String dir = FxUtils.chooseDir(stage);
    if (dir != null && !dirs.contains(dir)) {
      displayShareDirs(Collections.singleton(dir));
      shareDirsUpdated = true;
    }
  }

  @FXML
  public void removeDir(ActionEvent event) {
    List<Label> selectedItems = shareDirsList.getSelectionModel().getSelectedItems();
    if (selectedItems.size() != dirs.size()) {
      selectedItems.forEach(
          x -> {
            shareDirsList.getItems().remove(x);
            dirs.remove(x.getText());
          });

      shareDirsUpdated = true;
    }
  }

  public void displayShareDirs(Set<String> dirs) {
    dirs.forEach(
        x -> {
          shareDirsList.getItems().add(new Label(x));
          this.dirs.add(x);
        });
  }

  public void displayDownloadsDir(String dir) {
    downloadsDir.getItems().clear();
    downloadsDir.getItems().add(new Label(dir));
  }

  @FXML
  public void updateDir(ActionEvent event) {
    Stage stage = (Stage) downloadsDir.getScene().getWindow();
    String dir = FxUtils.chooseDir(stage);
    if (dir != null) {
      displayDownloadsDir(dir);
      downloadsDirUpdated = true;
    }
  }

  @FXML
  public void ok(ActionEvent event) {
    if (shareDirsUpdated) dbService.saveShareDirs(dirs);
    if (downloadsDirUpdated) {
      String dir = downloadsDir.getItems().get(0).getText();
      dbService.saveDownloadsDir(dir);
    }

    close();
  }

  @FXML
  public void cancel(ActionEvent event) {
    close();
  }

  private void close() {
    Window window = shareDirsList.getScene().getWindow();
    if (window instanceof Stage) {
      Stage stage = (Stage) window;
      stage.close();
    }
  }
}
