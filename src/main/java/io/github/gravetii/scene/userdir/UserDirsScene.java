package io.github.gravetii.scene.userdir;

import io.github.gravetii.scene.FxDimensions;
import io.github.gravetii.scene.FxScene;
import io.github.gravetii.scene.start.StartScene;
import javafx.geometry.Dimension2D;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.Optional;

public class UserDirsScene extends FxScene {

  public UserDirsScene(Stage stage, StartScene scene) {
    super(stage, new BorderPane());
    UserDirsComponent component = new UserDirsComponent(stage, scene);
    BorderPane pane = (BorderPane) root;
    pane.setCenter(component.getNode());
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
