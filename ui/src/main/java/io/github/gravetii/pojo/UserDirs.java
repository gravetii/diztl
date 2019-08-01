package io.github.gravetii.pojo;

import java.util.List;

public class UserDirs {
  private List<String> shareDirs;
  private String outputDir;

  public UserDirs(List<String> shareDirs, String outputDir) {
    this.shareDirs = shareDirs;
    this.outputDir = outputDir;
  }

  public List<String> getShareDirs() {
    return shareDirs;
  }

  public String getOutputDir() {
    return outputDir;
  }
}
