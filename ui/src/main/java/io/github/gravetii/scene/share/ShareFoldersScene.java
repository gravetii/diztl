package io.github.gravetii.scene.share;

import io.github.gravetii.scene.FxScene;
import javafx.stage.Stage;

import java.util.List;

public class ShareFoldersScene extends FxScene {
  private ShareFoldersComponent shareFoldersComponent;

  public ShareFoldersScene(Stage stage) throws Exception {
    super(stage);
    this.shareFoldersComponent = new ShareFoldersComponent(stage, this);
  }

  @Override
  protected void build() {
    this.showCenter(shareFoldersComponent);
  }

  @Override
  protected String title() {
    return "Configure share folders";
  }

  public void displayUserFolders(List<String> shareDirs) {
    this.shareFoldersComponent.displayShareDirs(shareDirs);
  }

}
