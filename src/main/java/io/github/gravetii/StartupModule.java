package io.github.gravetii;

import com.google.inject.AbstractModule;
import io.github.gravetii.keeper.NodeKeeper;
import io.github.gravetii.store.DBService;
import io.github.gravetii.store.LightweightDBService;

public class StartupModule extends AbstractModule {

  @Override
  public void configure() {
    bind(DBService.class).toInstance(new LightweightDBService());
    bind(NodeKeeper.class).toInstance(new NodeKeeper());
  }
}
