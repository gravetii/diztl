package io.github.gravetii.scene.userdir;

import io.github.gravetii.scene.FxDimensions;
import io.github.gravetii.scene.FxScene;
import javafx.geometry.Dimension2D;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.util.Optional;

public class UserDirsScene extends FxScene {
  private ShareDirsComponent shareDirsComponent;
  private UserDirsSubmitComponent userDirsSubmitComponent;

  public UserDirsScene(Stage stage) throws Exception {
    super(stage, new BorderPane());
    this.shareDirsComponent = new ShareDirsComponent(stage, this);
    this.userDirsSubmitComponent = new UserDirsSubmitComponent(stage, shareDirsComponent);
  }

  @Override
  public Region build() {
    BorderPane pane = (BorderPane) root;
    pane.setCenter(shareDirsComponent.getNode());
    pane.setBottom(userDirsSubmitComponent.getNode());
    return pane;
  }

  @Override
  protected String title() {
    return "Configure user folders";
  }

  @Override
  protected Optional<FxDimensions> preferredDimensions() {
    FxDimensions dimensions =
        new FxDimensions(
            new Dimension2D(620, 460), new Dimension2D(620, 460), new Dimension2D(620, 460));
    return Optional.of(dimensions);
  }
}
