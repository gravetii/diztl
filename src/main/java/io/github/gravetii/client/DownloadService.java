package io.github.gravetii.client;

import io.github.gravetii.grpc.FileChunk;
import io.github.gravetii.grpc.FileMetadata;
import io.github.gravetii.grpc.Node;
import io.github.gravetii.model.DownloadResult;
import io.github.gravetii.scene.start.StartScene;
import io.github.gravetii.util.DiztlExecutorService;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DownloadService {

  private static final Logger logger =
      LoggerFactory.getLogger(DownloadService.class.getCanonicalName());

  private static final String DOWNLOAD_FOLDER = "/Users/s0d01bw/Documents/diztl_downloads";

  private final FileMetadata file;
  private final Node source;
  private final StartScene scene;

  public DownloadService(FileMetadata file, Node source, StartScene scene) {
    this.file = file;
    this.source = source;
    this.scene = scene;
  }

  public StreamObserver<FileChunk> newObserver() {
    DownloadResult result = new DownloadResult(file, DOWNLOAD_FOLDER);
    DiztlExecutorService.execute(result);
    scene.show(result);
    return new StreamObserver<>() {
      BufferedOutputStream stream = null;

      @Override
      public void onNext(FileChunk chunk) {
        byte[] data = chunk.getData().toByteArray();
        if (chunk.getChunk() == 1) {
          final Path out = Paths.get(DOWNLOAD_FOLDER, chunk.getMetadata().getName());
          try {
            stream = new BufferedOutputStream(new FileOutputStream(out.toString()));
            result.first(chunk);
          } catch (FileNotFoundException e) {
            logger.error("Error while creating output file {}", out, e);
            result.onError(e);
          }
        }

        try {
          stream.write(data);
          result.onNext(chunk);
        } catch (IOException e) {
          logger.error("Error while writing chunk", e);
          result.onError(e);
        }
      }

      @Override
      public void onError(Throwable throwable) {
        logger.error("Error while downloading file {} from node {}", file, source, throwable);
        result.onError(throwable);
      }

      @Override
      public void onCompleted() {
        try {
          stream.flush();
          stream.close();
          result.onComplete();
        } catch (IOException e) {
          logger.error("Error while closing output file", e);
        }
      }
    };
  }
}
