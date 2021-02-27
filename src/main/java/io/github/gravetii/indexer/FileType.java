package io.github.gravetii.indexer;

import java.util.*;
import java.util.stream.Collectors;

public enum FileType {
  UNKNOWN(Collections.emptyList()),
  VIDEO(Arrays.asList("mp4", "mkv", "mpeg", "mov", "webm", "flv")),
  IMAGE(Arrays.asList("png", "jpg", "jpeg", "ico", "gif")),
  AUDIO(Arrays.asList("mp3", "wav", "ogg")),
  DOCUMENT(Arrays.asList("txt", "pdf", "ppt", "doc", "xls", "csv")),
  COMPRESSED(Arrays.asList("zip", "gz", "rar", "7z")),
  EXECUTABLE(Arrays.asList("exe", "dmg", "sh"));

  private static final Map<String, FileType> map = new HashMap<>();

  static {
    Arrays.stream(FileType.values())
        .forEach(x -> map.putAll(x.extensions.stream().collect(Collectors.toMap(y -> y, y -> x))));
  }

  public static FileType get(String ext) {
    return map.getOrDefault(ext, FileType.UNKNOWN);
  }

  private final List<String> extensions;

  FileType(List<String> extensions) {
    this.extensions = extensions;
  }
}
