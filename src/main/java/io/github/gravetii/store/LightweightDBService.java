package io.github.gravetii.store;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.prefs.Preferences;
import java.util.stream.Collectors;

public class LightweightDBService implements DBService {

  private static final String SHARE_DIRS_KEY = "user_share_dirs";
  private static final String TRACKER_ADDR_KEY = "tracker";

  private static final Preferences prefs = Preferences.userNodeForPackage(LightweightDBService.class);

  public void saveShareDirs(List<String> dirs) {
    StringBuilder value = new StringBuilder();
    dirs.forEach(
        x -> {
          value.append(x);
          value.append('$');
        });

    prefs.put(SHARE_DIRS_KEY, value.toString());
  }

  public List<String> getShareDirs() {
    String value = prefs.get(SHARE_DIRS_KEY, "");
    if (StringUtils.isEmpty(value)) return Collections.emptyList();
    return Arrays.stream(value.split("\\$"))
        .filter(StringUtils::isNotEmpty)
        .collect(Collectors.toList());
  }

  public void saveTrackerAddress(String address) {
    prefs.put(TRACKER_ADDR_KEY, address);
  }

  public String getTrackerAddress() {
    return prefs.get(TRACKER_ADDR_KEY, "127.0.0.1:50036");
  }
}
