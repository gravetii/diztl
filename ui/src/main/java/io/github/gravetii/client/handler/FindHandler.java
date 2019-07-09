package io.github.gravetii.client.handler;

import io.github.gravetii.client.DiztlConnection;
import io.github.gravetii.gen.Diztl;
import io.github.gravetii.scene.start.StartScene;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FindHandler {
  private static final Logger logger =
      LoggerFactory.getLogger(FindHandler.class.getCanonicalName());

  private StartScene scene;
  private String pattern;

  public FindHandler(StartScene scene, String pattern) {
    this.scene = scene;
    this.pattern = pattern;
  }

  public void process(DiztlConnection connection) {
    scene.reset();
    Diztl.FindReq req = Diztl.FindReq.newBuilder().setPattern(pattern).build();
    logger.info("Searching for pattern - {}", pattern);
    connection.getAsyncstub().find(req, createObserver());
  }

  private StreamObserver<Diztl.FindResp> createObserver() {
    return new StreamObserver<Diztl.FindResp>() {
      @Override
      public void onNext(Diztl.FindResp value) {
        value
            .getResponsesList()
            .forEach(
                r -> {
                  r.getFilesList()
                      .forEach(
                          f -> {
                            scene.show(f, r.getNode());
                          });
                });
      }

      @Override
      public void onError(Throwable t) {}

      @Override
      public void onCompleted() {
        logger.info("Finished searching for pattern {}", pattern);
      }
    };
  }
}
