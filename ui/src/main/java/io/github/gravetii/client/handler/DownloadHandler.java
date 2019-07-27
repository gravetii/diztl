package io.github.gravetii.client.handler;

import io.github.gravetii.client.connection.Connection;
import io.github.gravetii.gen.Diztl;
import io.github.gravetii.scene.start.StartScene;
import io.github.gravetii.util.Utils;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DownloadHandler {
  private static final Logger logger =
      LoggerFactory.getLogger(DownloadHandler.class.getCanonicalName());

  private Diztl.FileMetadata file;
  private Diztl.Node source;
  private StartScene scene;
  private DownloadResult result;

  public DownloadHandler(
      Diztl.FileMetadata file, Diztl.Node source, StartScene scene, String outputDir) {
    this.file = file;
    this.source = source;
    this.scene = scene;
    this.result = new DownloadResult(file, outputDir);
  }

  public void process(Connection connection) {
    logger.info("Downloading file from {} - {}", source.getIp(), file.getName());
    ExecutionHandler.submit(result);
    scene.show(result);
    Diztl.DownloadReq req =
        Diztl.DownloadReq.newBuilder()
            .setFile(file)
            .setSource(source)
            .setDir(result.getPath())
            .build();
    connection.getAsyncstub().download(req, createObserver());
  }

  private StreamObserver<Diztl.DownloadChunk> createObserver() {
    return new StreamObserver<Diztl.DownloadChunk>() {
      @Override
      public void onNext(Diztl.DownloadChunk value) {
        if (value.getChunk() == 1) {
          result.first(value);
        }

        result.next(value.getChunk());
      }

      @Override
      public void onError(Throwable t) {
        logger.error("Error while downloading file - {}", t.getMessage());
        result.error(t);
        scene.writeToErrorLog(
            "Error while downloading file "
                + Utils.getFullFilePath(file)
                + " - "
                + t.getMessage()
                + ".");
      }

      @Override
      public void onCompleted() {
        logger.info("Finished downloading file: {}", file.getName());
        result.completed();
      }
    };
  }
}
