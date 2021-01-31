package io.github.gravetii.indexer;

import org.apache.commons.io.FileUtils;

import java.io.File;

public class IndexedFile {

  private final File file;
  private final String checksum;
  private final long modifiedTs = System.currentTimeMillis();

  public IndexedFile(File file, String checksum) {
    this.file = file;
    this.checksum = checksum;
  }

  public File getFile() {
    return file;
  }

  public String getChecksum() {
    return checksum;
  }

  public long getModifiedTs() {
    return modifiedTs;
  }

  public String getPath() {
    return file.getPath();
  }

  public String getName() {
    return file.getName();
  }

  public String getDir() {
    return file.getParent();
  }

  public long getSize() {
    return FileUtils.sizeOf(file);
  }

  @Override
  public String toString() {
    return "IndexedFile{"
        + "file="
        + file
        + ", checksum='"
        + checksum
        + '\''
        + ", modifiedTs="
        + modifiedTs
        + '}';
  }
}
