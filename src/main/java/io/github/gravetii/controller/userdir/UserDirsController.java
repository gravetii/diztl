package io.github.gravetii.controller.userdir;

import io.github.gravetii.controller.FxController;
import io.github.gravetii.scene.start.StartScene;
import io.github.gravetii.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class UserDirsController implements FxController {

  private static final Logger logger =
      LoggerFactory.getLogger(UserDirsController.class.getCanonicalName());

  private final Stage stage;
  private final StartScene scene;
  private final Set<String> dirs = new HashSet<>();
  private boolean shareChanged = false;
  private boolean downloadsChanged = false;

  @FXML private ListView<Label> shareDirsList;
  @FXML private Button addBtn;
  @FXML private Button removeBtn;
  @FXML private ListView<Label> downloadsDir;
  @FXML private Button folderBtn;

  public UserDirsController(Stage stage, StartScene scene) {
    this.stage = stage;
    this.scene = scene;
  }

  @FXML
  public void initialize() {
    removeBtn
        .disableProperty()
        .bind(shareDirsList.getSelectionModel().selectedItemProperty().isNull());
    // todo get shared dirs here...

    //    UserDirs dirs = CommunicationClient.get().getUserDirs();

    List<String> shareDirs = new ArrayList<>();
    shareDirs.add("dummy share dir 1");
    shareDirs.add("dummy share dir 2");
    displayShareDirs(shareDirs);
    displayDownloadsDir("dummy download dirs");
  }

  @FXML
  public void addDir(ActionEvent event) {
    String dir = Utils.chooseDir(stage);
    if (dir != null && !dirs.contains(dir)) {
      displayShareDirs(Collections.singletonList(dir));
      shareChanged = true;
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
      shareChanged = true;
    }
  }

  public void displayShareDirs(List<String> dirs) {
    dirs.forEach(
        dir -> {
          shareDirsList.getItems().add(new Label(dir));
          this.dirs.add(dir);
        });
  }

  public void displayDownloadsDir(String dir) {
    downloadsDir.getItems().clear();
    downloadsDir.getItems().add(new Label(dir));
  }

  @FXML
  public void updateDir(ActionEvent event) {
    String dir = Utils.chooseDir(stage);
    if (dir != null) {
      displayDownloadsDir(dir);
      downloadsChanged = true;
    }
  }

  @FXML
  public void ok(ActionEvent event) {
    List<String> share = shareChanged ? new ArrayList<>(dirs) : Collections.emptyList();
    String out = downloadsChanged ? downloadsDir.getItems().get(0).getText() : "";
    // todo: Update user dirs here...
    //    CommunicationClient.get().updateUserDirs(share, out, scene);

    logger.info("Update user dirs here...");
    stage.close();
  }

  @FXML
  public void cancel(ActionEvent event) {
    stage.close();
  }
}
