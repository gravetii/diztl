package io.github.gravetii.indexer;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import io.github.gravetii.store.DBService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Singleton
public class FileIndexer {

  private static final Logger logger =
      LoggerFactory.getLogger(FileIndexer.class.getCanonicalName());

  private final DBService dbService;

  private final List<IndexedFile> files = new ArrayList<>();

  @Inject
  public FileIndexer(DBService dbService) {
    this.dbService = dbService;
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
            FileInputStream stream = new FileInputStream(x);
            String checksum = DigestUtils.sha1Hex(stream);
            IndexedFile file = new IndexedFile(x, checksum);
            stream.close();
            result.add(file);
          } catch (Exception e) {
            logger.error("Error while generating checksum for file {}", x);
          }
        });

    return result;
  }

  public List<IndexedFile> index() {
    Set<String> dirs = dbService.getShareDirs();
    files.clear();
    dirs.forEach(x -> files.addAll(this.fileWalk(x)));
    return files;
  }

  public List<IndexedFile> search(String query) {
    return files.stream()
        .filter(
            x ->
                Pattern.compile(Pattern.quote(query), Pattern.CASE_INSENSITIVE)
                    .matcher(x.getPath())
                    .find())
        .collect(Collectors.toList());
  }
}
