package io.github.gravetii.scene.userdir;

import io.github.gravetii.scene.FxDimensions;
import io.github.gravetii.scene.FxScene;
import io.github.gravetii.scene.start.StartScene;
import javafx.geometry.Dimension2D;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.util.Optional;

public class UserDirsScene extends FxScene {
  private final UserDirsComponent userDirsComponent;

  public UserDirsScene(Stage stage, StartScene scene) {
    super(stage, new BorderPane());
    this.userDirsComponent = new UserDirsComponent(stage, scene);
  }

  @Override
  public Region build() {
    BorderPane pane = (BorderPane) root;
    pane.setCenter(userDirsComponent.getNode());
    return pane;
  }

  @Override
  public String title() {
    return "Configure user folders";
  }

  @Override
  public Optional<FxDimensions> preferredDimensions() {
    FxDimensions dimensions =
        new FxDimensions(
            new Dimension2D(650, 520), new Dimension2D(650, 520), new Dimension2D(650, 520));
    return Optional.of(dimensions);
  }
}
