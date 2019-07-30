package io.github.gravetii.client.handler;

import io.github.gravetii.AppContext;
import io.github.gravetii.client.connection.Connection;
import io.github.gravetii.gen.Diztl;
import io.github.gravetii.scene.start.StartScene;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class FileIndexHandler {
  private static final Logger logger =
      LoggerFactory.getLogger(FileIndexHandler.class.getCanonicalName());

  private StartScene scene;

  public FileIndexHandler(StartScene scene) {
    this.scene = scene;
  }

  public void process(Connection connection) {
    List<String> shareDirs = AppContext.getShareDirs();
    StringBuilder builder = new StringBuilder();
    builder.append("Indexing files in the following folders -").append("\n");
    for (int i = 0; i < shareDirs.size(); ++i) {
      builder.append("\t").append(i + 1).append(". ").append(shareDirs.get(i)).append("\n");
    }

    logText(builder.toString());
    connection.getAsyncstub().index(Diztl.IndexReq.getDefaultInstance(), createObserver());
  }

  private void logText(String text) {
    scene.writeToLog(text);
  }

  private StreamObserver<Diztl.IndexResp> createObserver() {
    return new StreamObserver<Diztl.IndexResp>() {
      AtomicInteger indexedFilesCount = new AtomicInteger();

      @Override
      public void onNext(Diztl.IndexResp value) {
        logText("Finished indexing " + value.getFpath());
        indexedFilesCount.incrementAndGet();
      }

      @Override
      public void onError(Throwable t) {
        logger.error("Error while indexing files - {}", t.getMessage());
      }

      @Override
      public void onCompleted() {
        logText("Finished indexing all " + indexedFilesCount + " shared files.\n");
      }
    };
  }
}
