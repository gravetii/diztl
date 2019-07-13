package io.github.gravetii.scene.userdir;

import io.github.gravetii.scene.FxDimensions;
import io.github.gravetii.scene.FxScene;
import javafx.geometry.Dimension2D;
import javafx.stage.Stage;

import java.util.List;
import java.util.Optional;

public class UserDirsScene extends FxScene {
  private ShareDirsComponent shareDirsComponent;
  private UserDirsSubmitComponent userDirsSubmitComponent;

  public UserDirsScene(Stage stage) throws Exception {
    super(stage);
    this.shareDirsComponent = new ShareDirsComponent(stage, this);
    this.userDirsSubmitComponent = new UserDirsSubmitComponent(stage, shareDirsComponent);
  }

  @Override
  protected void build() {
    this.showCenter(shareDirsComponent).showBottom(userDirsSubmitComponent);
  }

  @Override
  protected String title() {
    return "Configure user folders";
  }

  @Override
  protected Optional<FxDimensions> preferredDimensions() {
    FxDimensions dimensions = new FxDimensions(new Dimension2D(620, 350));
    return Optional.of(dimensions);
  }

  public void displayShareDirs(List<String> shareDirs) {
    this.shareDirsComponent.displayDirs(shareDirs);
  }
}
