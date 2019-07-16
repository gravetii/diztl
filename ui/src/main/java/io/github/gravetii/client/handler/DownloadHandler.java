package io.github.gravetii.client.handler;

import io.github.gravetii.client.DiztlConnection;
import io.github.gravetii.gen.Diztl;
import io.github.gravetii.scene.start.StartScene;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DownloadHandler {
  private static final Logger logger =
      LoggerFactory.getLogger(DownloadHandler.class.getCanonicalName());

  private Diztl.FileMetadata file;
  private Diztl.Node source;
  private StartScene scene;
  private DownloadProgressTask progressTask;

  public DownloadHandler(Diztl.FileMetadata file, Diztl.Node source, StartScene scene) {
    this.file = file;
    this.source = source;
    this.scene = scene;
    this.progressTask = new DownloadProgressTask();
    //scene.getProgressBar().progressProperty().bind(progressTask.progressProperty());
  }

  public void process(DiztlConnection connection) {
    logger.info("Downloading file from {} - {}", source.getIp(), file.getName());
    Diztl.DownloadReq req =
        Diztl.DownloadReq.newBuilder().setMetadata(file).setSource(source).build();
    connection.getAsyncstub().download(req, createObserver());
  }

  private StreamObserver<Diztl.DownloadChunk> createObserver() {
    return new StreamObserver<Diztl.DownloadChunk>() {
      @Override
      public void onNext(Diztl.DownloadChunk value) {
        if (value.getChunk() == 1) {
          Diztl.FileMetadata file = value.getMetadata();
          progressTask.setChunks(file.getChunks());
          scene.showDownloadResult(file);
        } else {
          progressTask.update(value.getChunk());
        }
      }

      @Override
      public void onError(Throwable t) {
        logger.error("Error while downloading file:", t);
      }

      @Override
      public void onCompleted() {
        logger.info("Finished downloading file: {}", file.getName());
      }
    };
  }
}
