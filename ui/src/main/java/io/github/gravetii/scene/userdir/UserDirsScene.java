package io.github.gravetii.scene.userdir;

import io.github.gravetii.scene.FxDimensions;
import io.github.gravetii.scene.FxScene;
import javafx.geometry.Dimension2D;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.util.Optional;

public class UserDirsScene extends FxScene {
  private UserDirsComponent userDirsComponent;
  private UserDirsSubmitComponent userDirsSubmitComponent;

  public UserDirsScene(Stage stage) throws Exception {
    super(stage, new BorderPane());
    this.userDirsComponent = new UserDirsComponent(stage, this);
    this.userDirsSubmitComponent = new UserDirsSubmitComponent(stage, userDirsComponent);
  }

  @Override
  public Region build() {
    BorderPane pane = (BorderPane) root;
    pane.setCenter(userDirsComponent.getNode());
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
            new Dimension2D(650, 520), new Dimension2D(650, 520), new Dimension2D(650, 520));
    return Optional.of(dimensions);
  }
}
