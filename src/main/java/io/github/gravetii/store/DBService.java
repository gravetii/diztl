package io.github.gravetii.store;

import java.util.Set;

public interface DBService {

  void saveShareDirs(Set<String> dirs);

  Set<String> getShareDirs();

  void saveDownloadsDir(String dir);

  String getDownloadsDir();

  void saveTrackerAddress(String address);

  String getTrackerAddress();

  void saveBufferSize(int sz);

  int getBufferSize();

  void close();
}
