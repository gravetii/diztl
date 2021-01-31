package io.github.gravetii.model;

import java.util.List;

public class UserDirs {
  private final List<String> shareDirs;
  private final String downloadsDir;

  public UserDirs(List<String> shareDirs, String downloadsDir) {
    this.shareDirs = shareDirs;
    this.downloadsDir = downloadsDir;
  }

  public List<String> getShareDirs() {
    return shareDirs;
  }

  public String getDownloadsDir() {
    return downloadsDir;
  }
}
