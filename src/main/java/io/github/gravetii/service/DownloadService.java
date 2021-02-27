package io.github.gravetii.service;

import io.github.gravetii.client.DiztlClient;
import io.github.gravetii.client.NodeNotConnectedException;
import io.github.gravetii.controller.start.FileResult;
import io.github.gravetii.grpc.FileChunk;
import io.github.gravetii.grpc.FileMetadata;
import io.github.gravetii.model.DownloadRequest;
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

  private final DiztlClient client;
  private final StartScene scene;

  public DownloadService(DiztlClient client, StartScene scene) {
    this.client = client;
    this.scene = scene;
  }

  private StreamObserver<FileChunk> newObserver(DownloadResult result, String outputDir) {
    return new StreamObserver<>() {
      BufferedOutputStream stream = null;

      @Override
      public void onNext(FileChunk chunk) {
        byte[] data = chunk.getData().toByteArray();
        if (chunk.getChunk() == 1) {
          final Path out = Paths.get(outputDir, chunk.getMetadata().getName());
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
        try {
          result.onError(throwable);
          logger.error("Error while downloading file {}", result.getFilepath(), throwable);
          if (stream != null) stream.close();
        } catch (IOException e) {
          logger.error("Error while closing output file", e);
        }
      }

      @Override
      public void onCompleted() {
        try {
          stream.close();
          result.onComplete();
        } catch (IOException e) {
          logger.error("Error while closing output file", e);
        }
      }
    };
  }

  /** Download the file represented by the given FileResult to the given folder. */
  public void download(FileResult result, String folder) {
    FileMetadata file = result.getFile();
    DownloadResult download = new DownloadResult(file, folder);
    scene.show(download);
    DiztlExecutorService.execute(download);
    try {
      StreamObserver<FileChunk> observer = newObserver(download, folder);
      DownloadRequest request = new DownloadRequest(file, result.getSource(), observer);
      client.download(request);
    } catch (NodeNotConnectedException e) {
      download.onError(e);
      scene.writeConnectionErrorToLog();
    }
  }
}
