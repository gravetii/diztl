package io.github.gravetii.controller.userdir;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import io.github.gravetii.client.connection.CommunicationClient;
import io.github.gravetii.controller.FxController;
import io.github.gravetii.pojo.UserDirs;
import io.github.gravetii.scene.start.StartScene;
import io.github.gravetii.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class UserDirsController implements FxController {
  private static final Logger logger = LoggerFactory.getLogger(UserDirsController.class.getCanonicalName());

  private Stage stage;
  private StartScene scene;
  private Set<String> dirs = new HashSet<>();
  private boolean shareChanged = false;
  private boolean downloadsChanged = false;

  @FXML private JFXListView<Label> shareDirsList;
  @FXML private JFXButton addBtn;
  @FXML private JFXButton removeBtn;
  @FXML private JFXListView<Label> downloadsDir;
  @FXML private JFXButton folderBtn;

  public UserDirsController(Stage stage, StartScene scene) {
    this.stage = stage;
    this.scene = scene;
  }

  @FXML
  public void initialize() {
    removeBtn
        .disableProperty()
        .bind(shareDirsList.getSelectionModel().selectedItemProperty().isNull());
    UserDirs dirs = CommunicationClient.get().getUserDirs();
    displayShareDirs(dirs.getShareDirs());
    displayDownloadsDir(dirs.getDownloadsDir());
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
    logger.debug("Removed call to updateUserDirs here in CommunicationClient...");
    stage.close();
  }

  @FXML
  public void cancel(ActionEvent event) {
    stage.close();
  }
}
