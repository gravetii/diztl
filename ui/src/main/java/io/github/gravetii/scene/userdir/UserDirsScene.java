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

  public UserDirsScene(Stage stage) {
    super(stage, new BorderPane());
    this.userDirsComponent = new UserDirsComponent(stage, this);
  }

  @Override
  public Region build() {
    BorderPane pane = (BorderPane) root;
    pane.setCenter(userDirsComponent.getNode());
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
