package io.github.gravetii.scene.userdir;

import io.github.gravetii.scene.FxScene;
import javafx.stage.Stage;

import java.util.List;

public class UserDirsScene extends FxScene {
  private ShareDirsComponent shareDirsComponent;

  public UserDirsScene(Stage stage) throws Exception {
    super(stage);
    this.shareDirsComponent = new ShareDirsComponent(stage, this);
  }

  @Override
  protected void build() {
    this.showCenter(shareDirsComponent);
  }

  @Override
  protected String title() {
    return "Configure user folders";
  }

  public void displayShareDirs(List<String> shareDirs) {
    this.shareDirsComponent.displayDirs(shareDirs);
  }

}
