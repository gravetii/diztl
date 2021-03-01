package io.github.gravetii.store;

import com.google.inject.Singleton;
import io.github.gravetii.App;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.prefs.Preferences;
import java.util.stream.Collectors;

@Singleton
public class LightweightDBService implements DBService {

  private static final Logger logger =
      LoggerFactory.getLogger(LightweightDBService.class.getCanonicalName());

  private static final String SHARE_DIRS_KEY = "user_share_dirs";
  private static final String DOWNLOADS_DIR_KEY = "downloads_dir";
  private static final String TRACKER_ADDR_KEY = "tracker";
  private static final String BUFFER_SIZE_KEY = "buf_size";

  private final Preferences prefs;
  private final String defaultDownloadsDir;

  public LightweightDBService() {
    this.prefs = Preferences.userNodeForPackage(App.class);
    this.defaultDownloadsDir = getDefaultDownloadsDir();
  }

  private String getDefaultDownloadsDir() {
    String home = SystemUtils.getUserHome().getAbsolutePath();
    return Path.of(home, "diztl", "downloads").toString();
  }

  private void flush() {
    try {
      prefs.flush();
    } catch (Throwable throwable) {
      logger.warn("Error while flushing store", throwable);
    }
  }

  public void saveShareDirs(Set<String> dirs) {
    StringBuilder value = new StringBuilder();
    dirs.forEach(
        x -> {
          value.append(x);
          value.append('$');
        });

    prefs.put(SHARE_DIRS_KEY, value.toString());
    this.flush();
  }

  public Set<String> getShareDirs() {
    String value = prefs.get(SHARE_DIRS_KEY, "");
    if (StringUtils.isEmpty(value)) return Collections.emptySet();
    return Arrays.stream(value.split("\\$"))
        .filter(StringUtils::isNotEmpty)
        .collect(Collectors.toSet());
  }

  @Override
  public void saveDownloadsDir(String dir) {
    prefs.put(DOWNLOADS_DIR_KEY, dir);
    this.flush();
  }

  @Override
  public String getDownloadsDir() {
    return prefs.get(DOWNLOADS_DIR_KEY, defaultDownloadsDir);
  }

  @Override
  public void saveTrackerAddress(String address) {
    prefs.put(TRACKER_ADDR_KEY, address);
    this.flush();
  }

  @Override
  public String getTrackerAddress() {
    return prefs.get(TRACKER_ADDR_KEY, "127.0.0.1:50036");
  }

  @Override
  public void saveBufferSize(int sz) {
    prefs.putInt(BUFFER_SIZE_KEY, sz);
    this.flush();
  }

  @Override
  public int getBufferSize() {
    return prefs.getInt(BUFFER_SIZE_KEY, 512 * 512);
  }

  @Override
  public void close() {
    this.flush();
  }
}
