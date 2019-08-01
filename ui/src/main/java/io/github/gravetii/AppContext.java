package io.github.gravetii;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppContext {
  private static final Logger logger = LoggerFactory.getLogger(AppContext.class.getCanonicalName());

  private static String tracker;

  public static void updateTracker(String host) {
    tracker = host;
  }

  public static String getTracker() {
    return tracker;
  }
}
