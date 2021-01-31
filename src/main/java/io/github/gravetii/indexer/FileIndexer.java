package io.github.gravetii.indexer;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/** Indexes all the files present in the given directories, making them available for search. */
public class FileIndexer {

  private static final Logger logger =
      LoggerFactory.getLogger(FileIndexer.class.getCanonicalName());

  private final List<String> dirs;
  private final List<IndexedFile> indexedFiles;

  public FileIndexer(List<String> dirs) {
    this.dirs = dirs;
    this.indexedFiles = new ArrayList<>();
  }

  /**
   * Performs a file walk of the files in the given directory and indexes each file by computing its
   * checksum.
   */
  private List<IndexedFile> fileWalk(String dir) {
    File f = new File(dir);
    List<IndexedFile> result = new ArrayList<>();
    Iterator<File> itr = FileUtils.iterateFiles(f, null, true);
    itr.forEachRemaining(
        x -> {
          try {
            //            String checksum = DigestUtils.sha1Hex(new FileInputStream(x));
            IndexedFile file = new IndexedFile(x, "a");
            result.add(file);
          } catch (Exception e) {
            logger.error("Error while generating checksum for file {}", x);
          }
        });

    return result;
  }

  /** Indexes all the files present in the supplied list of directories. */
  public List<IndexedFile> index() {
    dirs.forEach(x -> indexedFiles.addAll(this.fileWalk(x)));
    return indexedFiles;
  }

  public List<IndexedFile> search(String query) {
    return indexedFiles.stream()
        .filter(
            x ->
                Pattern.compile(Pattern.quote(query), Pattern.CASE_INSENSITIVE)
                    .matcher(x.getPath())
                    .find())
        .collect(Collectors.toList());
  }
}
