package io.github.gravetii.client.handler;

import io.github.gravetii.client.DiztlConnection;
import io.github.gravetii.gen.Diztl;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DownloadHandler {
  private static final Logger logger =
      LoggerFactory.getLogger(DownloadHandler.class.getCanonicalName());

  private Diztl.FileMetadata file;
  private Diztl.Node source;

  public DownloadHandler(Diztl.FileMetadata file, Diztl.Node source) {
    this.file = file;
    this.source = source;
  }

  public void process(DiztlConnection connection) {
    logger.info("Downloading file from {} - {}", source.getIp(), file.getName());
    Diztl.DownloadReq dreq =
        Diztl.DownloadReq.newBuilder().setMetadata(file).setSource(source).build();
    connection.getAsyncstub().download(dreq, createObserver());
  }

  private StreamObserver<Diztl.DownloadChunk> createObserver() {
    return new StreamObserver<Diztl.DownloadChunk>() {
      @Override
      public void onNext(Diztl.DownloadChunk value) {
        if (value.getChunk() == 1) {
          // First chunk...process the metadata field for file details if required.
          Diztl.FileMetadata file = value.getMetadata();
          int totalChunks = file.getChunks();
          logger.info(
              "Received first chunk of file {}, total chunks - {}", file.getName(), totalChunks);
        } else {
          logger.info("Received chunk {}", value.getChunk());
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
