package io.github.gravetii.scene.share;

import io.github.gravetii.scene.FxScene;
import javafx.stage.Stage;

import java.util.List;

public class UserDirsScene extends FxScene {
  private UserDirsComponent userDirsComponent;

  public UserDirsScene(Stage stage) throws Exception {
    super(stage);
    this.userDirsComponent = new UserDirsComponent(stage, this);
  }

  @Override
  protected void build() {
    this.showCenter(userDirsComponent);
  }

  @Override
  protected String title() {
    return "Configure share folders";
  }

  public void displayUserFolders(List<String> shareDirs) {
    this.userDirsComponent.displayShareDirs(shareDirs);
  }

}
