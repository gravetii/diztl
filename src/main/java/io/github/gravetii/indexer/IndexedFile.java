package io.github.gravetii.indexer;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import java.io.File;

public class IndexedFile {

  private final File file;
  private final String checksum;
  private final String path;
  private final String name;
  private final String dir;
  private final long size;
  private final String ext;
  private final FileType type;

  public IndexedFile(File file, String checksum) {
    this.file = file;
    this.checksum = checksum;
    this.path = file.getPath();
    this.name = file.getName();
    this.dir = file.getParent();
    this.size = FileUtils.sizeOf(file);
    this.ext = FilenameUtils.getExtension(file.getName());
    this.type = FileType.get(ext);
  }

  public File getFile() {
    return file;
  }

  public String getChecksum() {
    return checksum;
  }

  public String getPath() {
    return path;
  }

  public String getName() {
    return name;
  }

  public String getDir() {
    return dir;
  }

  public long getSize() {
    return size;
  }

  public String getExt() {
    return ext;
  }

  public FileType getType() {
    return type;
  }

  @Override
  public String toString() {
    return "IndexedFile{"
        + "file="
        + file
        + ", checksum='"
        + checksum
        + '\''
        + ", path='"
        + path
        + '\''
        + ", name='"
        + name
        + '\''
        + ", dir='"
        + dir
        + '\''
        + ", size="
        + size
        + ", ext='"
        + ext
        + '\''
        + ", type="
        + type
        + '}';
  }
}
