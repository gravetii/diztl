package io.github.gravetii.model;

import io.github.gravetii.grpc.FileConstraint;
import io.github.gravetii.grpc.SearchResp;
import io.grpc.stub.StreamObserver;

public class SearchRequest {

  private final String query;
  private final FileConstraint constraint;
  private final StreamObserver<SearchResp> observer;

  public SearchRequest(
      String query, FileConstraint constraint, StreamObserver<SearchResp> observer) {
    this.query = query;
    this.constraint = constraint;
    this.observer = observer;
  }

  public String getQuery() {
    return query;
  }

  public FileConstraint getConstraint() {
    return constraint;
  }

  public StreamObserver<SearchResp> getObserver() {
    return observer;
  }
}
