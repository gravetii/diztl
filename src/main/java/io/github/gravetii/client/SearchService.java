package io.github.gravetii.client;

import io.github.gravetii.controller.start.FileResult;
import io.github.gravetii.grpc.Diztl;
import io.github.gravetii.scene.start.ResultListComponent;
import io.github.gravetii.scene.start.StartScene;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SearchService {

  private static final Logger logger =
      LoggerFactory.getLogger(SearchService.class.getCanonicalName());

  private final String query;
  private final Diztl.FileConstraint constraint;
  private final StartScene scene;

  public SearchService(String query, Diztl.FileConstraint constraint, StartScene scene) {
    this.query = query;
    this.constraint = constraint;
    this.scene = scene;
  }

  public StreamObserver<Diztl.SearchResp> newObserver() {
    ResultListComponent component = scene.addNewSearchTab(query);
    return new StreamObserver<>() {
      @Override
      public void onNext(Diztl.SearchResp resp) {
        logger.info("Adding files to show - {}", resp.getFilesList());
        resp.getFilesList().forEach(file -> component.show(new FileResult(file, resp.getNode())));
      }

      @Override
      public void onError(Throwable e) {
        logger.error("File search error", e);
      }

      @Override
      public void onCompleted() {
        logger.info("Search completed");
      }
    };
  }
}
