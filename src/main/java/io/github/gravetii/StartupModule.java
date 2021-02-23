package io.github.gravetii;

import com.google.inject.AbstractModule;
import io.github.gravetii.scene.start.StartScene;

public class StartupModule extends AbstractModule {

  @Override
  public void configure() {
    bind(StartScene.class).toInstance(new StartScene());
  }
}
