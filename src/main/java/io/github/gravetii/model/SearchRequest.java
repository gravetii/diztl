package io.github.gravetii.model;

import io.github.gravetii.grpc.SearchConstraints;
import io.github.gravetii.grpc.SearchResp;
import io.grpc.stub.StreamObserver;

public class SearchRequest {

  private final String query;
  private final SearchConstraints constraints;
  private final StreamObserver<SearchResp> observer;

  public SearchRequest(
      String query, SearchConstraints constraints, StreamObserver<SearchResp> observer) {
    this.query = query;
    this.constraints = constraints;
    this.observer = observer;
  }

  public String getQuery() {
    return query;
  }

  public SearchConstraints getConstraints() {
    return constraints;
  }

  public StreamObserver<SearchResp> getObserver() {
    return observer;
  }
}
