package io.github.gravetii.client.handler;

import io.github.gravetii.client.connection.Connection;
import io.github.gravetii.gen.Diztl;
import io.github.gravetii.scene.start.StartScene;
import io.grpc.stub.StreamObserver;
import javafx.application.Platform;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicInteger;

public class FileIndexHandler {
  private static final Logger logger =
      LoggerFactory.getLogger(FileIndexHandler.class.getCanonicalName());

  private StartScene scene;

  public FileIndexHandler(StartScene scene) {
    this.scene = scene;
  }

  public void process(Connection connection) {
    Diztl.IndexReq req = Diztl.IndexReq.newBuilder().build();
    connection.getAsyncstub().index(req, createObserver());
  }

  private void showFooterText(Object... tokens) {
    StringBuilder builder = new StringBuilder();
    for (Object token : tokens) {
      builder.append(token).append(" ");
    }

    builder.append("\n");
    Platform.runLater(
        () -> {
          scene.showFooter(builder.toString());
        });
  }

  private StreamObserver<Diztl.IndexResp> createObserver() {
    return new StreamObserver<Diztl.IndexResp>() {
      AtomicInteger indexedFilesCount = new AtomicInteger();

      @Override
      public void onNext(Diztl.IndexResp value) {
        showFooterText("Finished indexing - ", value.getFpath());
        indexedFilesCount.incrementAndGet();
      }

      @Override
      public void onError(Throwable t) {
        logger.error("Error while indexing files - {}", t.getMessage());
      }

      @Override
      public void onCompleted() {
        showFooterText("Finished indexing all", indexedFilesCount, "shared files");
      }
    };
  }
}
