package io.github.gravetii;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class AppContext {
  private static final Logger logger = LoggerFactory.getLogger(AppContext.class.getCanonicalName());

  public static List<String> shareDirs;
  public static String outputDir;

  public static void updateShareDirs(List<String> dirs) {
    shareDirs = dirs;
  }

  public static void updateOutputDir(String dir) {
    outputDir = dir;
  }

  public static List<String> getShareDirs() {
    return shareDirs;
  }

  public static String getOutputDir() {
    return outputDir;
  }
}
