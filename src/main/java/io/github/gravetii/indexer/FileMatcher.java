package io.github.gravetii.indexer;

import io.github.gravetii.grpc.SearchConstraints;
import io.github.gravetii.grpc.SizeConstraint;
import io.github.gravetii.grpc.TypeConstraint;

import java.util.regex.Pattern;

public class FileMatcher {

  private final String query;
  private final SearchConstraints constraints;

  public FileMatcher(String query, SearchConstraints constraints) {
    this.query = query;
    this.constraints = constraints;
  }

  private boolean checkSize(IndexedFile file) {
    if (constraints.hasCsize()) {
      SizeConstraint sc = constraints.getCsize();
      if (sc.getKey() == 0) {
        return file.getSize() >= sc.getValue();
      } else if (sc.getKey() == 1) {
        return file.getSize() < sc.getValue();
      }
    }

    return true;
  }

  private boolean checkType(IndexedFile file) {
    if (constraints.hasCtype()) {
      TypeConstraint tc = constraints.getCtype();
      if (tc.getType() == 0) return true;
      if (tc.getType() == 1) return file.getType() == FileType.VIDEO;
      else if (tc.getType() == 2) return file.getType() == FileType.IMAGE;
      else if (tc.getType() == 3) return file.getType() == FileType.AUDIO;
      else if (tc.getType() == 4) return file.getType() == FileType.DOCUMENT;
      else if (tc.getType() == 5) return file.getType() == FileType.COMPRESSED;
      else if (tc.getType() == 6) return file.getType() == FileType.EXECUTABLE;
    }

    return true;
  }

  public boolean matchesQuery(IndexedFile file) {
    return Pattern.compile(Pattern.quote(query), Pattern.CASE_INSENSITIVE)
        .matcher(file.getPath())
        .find();
  }

  public boolean matchesConstraint(IndexedFile file) {
    return checkSize(file) && checkType(file);
  }
}
