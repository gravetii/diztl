package io.github.gravetii.store;

import java.util.Set;

public interface DBService {

  void saveShareDirs(Set<String> dirs);

  Set<String> getShareDirs();

  void saveTrackerAddress(String address);

  String getTrackerAddress();
}
