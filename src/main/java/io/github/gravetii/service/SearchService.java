package io.github.gravetii.service;

import io.github.gravetii.client.DiztlClient;
import io.github.gravetii.client.NodeNotConnectedException;
import io.github.gravetii.controller.start.FileResult;
import io.github.gravetii.grpc.FileConstraint;
import io.github.gravetii.grpc.SearchResp;
import io.github.gravetii.model.SearchRequest;
import io.github.gravetii.scene.start.ResultListComponent;
import io.github.gravetii.scene.start.StartScene;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SearchService {

  private static final Logger logger =
      LoggerFactory.getLogger(SearchService.class.getCanonicalName());

  private final DiztlClient client;
  private final StartScene scene;

  public SearchService(DiztlClient client, StartScene scene) {
    this.client = client;
    this.scene = scene;
  }

  private StreamObserver<SearchResp> newObserver(String query) {
    ResultListComponent component = scene.addNewSearchTab(query);
    return new StreamObserver<>() {
      @Override
      public void onNext(SearchResp resp) {
        resp.getFilesList().forEach(file -> component.show(new FileResult(file, resp.getNode())));
      }

      @Override
      public void onError(Throwable throwable) {
        logger.error("File search error for query {}", query, throwable);
      }

      @Override
      public void onCompleted() {
        logger.debug("Search completed for query {}", query);
      }
    };
  }

  public void search(String query, FileConstraint constraint) {
    try {
      SearchRequest request = new SearchRequest(query, constraint, newObserver(query));
      client.search(request);
    } catch (NodeNotConnectedException e) {
      scene.writeConnectionErrorToLog();
    }
  }
}
