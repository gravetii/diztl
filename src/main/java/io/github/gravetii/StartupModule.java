package io.github.gravetii;

import com.google.inject.AbstractModule;
import io.github.gravetii.scene.start.StartScene;
import javafx.stage.Stage;

public class StartupModule extends AbstractModule {

  private final Stage stage;

  public StartupModule(Stage stage) {
    this.stage = stage;
  }

  @Override
  public void configure() {
    bind(StartScene.class).toInstance(new StartScene(stage));
  }
}
