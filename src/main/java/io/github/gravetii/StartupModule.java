package io.github.gravetii;

import com.google.inject.AbstractModule;
import io.github.gravetii.scene.start.StartScene;
import io.github.gravetii.store.DBService;
import io.github.gravetii.store.LightweightDBService;

public class StartupModule extends AbstractModule {

  @Override
  public void configure() {
    bind(StartScene.class).toInstance(new StartScene());
    bind(DBService.class).toInstance(new LightweightDBService());
  }
}
