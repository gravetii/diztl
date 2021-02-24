package io.github.gravetii.scene.userdir;

import io.github.gravetii.scene.FxDimensions;
import io.github.gravetii.scene.FxScene;
import io.github.gravetii.store.DBService;
import javafx.geometry.Dimension2D;
import javafx.scene.layout.BorderPane;

import java.util.Optional;

public class UserDirsScene extends FxScene {

  public UserDirsScene(DBService dbService) {
    super(new BorderPane());
    UserDirsComponent component = new UserDirsComponent(dbService);
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
