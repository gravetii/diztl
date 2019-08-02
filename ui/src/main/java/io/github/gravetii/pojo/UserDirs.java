package io.github.gravetii.pojo;

import java.util.List;

public class UserDirs {
  private List<String> shareDirs;
  private String downloadsDir;

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
