package io.github.gravetii.store;

import java.util.List;

public interface DBService {

  void saveShareDirs(List<String> dirs);
  List<String> getShareDirs();

  void saveTrackerAddress(String address);
  String getTrackerAddress();

}
