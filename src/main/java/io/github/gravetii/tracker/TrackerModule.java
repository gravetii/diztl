package io.github.gravetii.tracker;

import com.google.inject.AbstractModule;
import io.github.gravetii.keeper.NodeKeeper;

public class TrackerModule extends AbstractModule {

  @Override
  public void configure() {
    bind(NodeKeeper.class).toInstance(new NodeKeeper());
  }
}
