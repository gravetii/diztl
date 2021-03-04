package io.github.gravetii.util;

import io.github.gravetii.grpc.FileMetadata;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Utils {

  public static String humanReadableByteCount(long bytes) {
    int unit = 1000;
    if (bytes < unit) return bytes + " B";
    int exp = (int) (Math.log(bytes) / Math.log(unit));
    char pre = "kMGTPE".charAt(exp - 1);
    return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
  }

  public static Long getByteCount(double value, String unit) {
    long multiplier = 1000000;
    if (unit.equals("kB")) {
      multiplier = multiplier / 1000;
    } else if (unit.equals("GB")) {
      multiplier = multiplier * 1000;
    }

    return (long) (value * multiplier);
  }

  public static Path getFilePath(FileMetadata file) {
    return Paths.get(file.getDir(), file.getName());
  }

  /** Ensure that the given directory exists on the file system. */
  public static File ensureDir(String directory) throws IOException {
    File file = new File(directory);
    FileUtils.forceMkdir(file);
    return file;
  }
}
