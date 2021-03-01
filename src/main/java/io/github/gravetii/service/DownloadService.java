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
import io.github.gravetii.util.Utils;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Path;

public class DownloadService {

  private static final Logger logger =
      LoggerFactory.getLogger(DownloadService.class.getCanonicalName());

  private final DiztlClient client;
  private final StartScene scene;

  public DownloadService(DiztlClient client, StartScene scene) {
    this.client = client;
    this.scene = scene;
  }

  private StreamObserver<FileChunk> newObserver(DownloadResult result, File directory) {
    return new StreamObserver<>() {
      BufferedOutputStream stream = null;

      @Override
      public void onNext(FileChunk chunk) {
        byte[] data = chunk.getData().toByteArray();
        if (chunk.getChunk() == 1) {
          File out = Path.of(directory.getAbsolutePath(), chunk.getMetadata().getName()).toFile();
          try {
            stream = new BufferedOutputStream(new FileOutputStream(out));
            result.first(chunk.getChunks());
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

  /** Download the file represented by the given FileResult to the given directory. */
  public void download(FileResult result, String directory) {
    FileMetadata metadata = result.getFile();
    DownloadResult download = new DownloadResult(metadata, directory);
    scene.show(download);
    DiztlExecutorService.execute(download);
    try {
      File out = new File(directory);
      Utils.ensureDir(out);
      StreamObserver<FileChunk> observer = newObserver(download, out);
      DownloadRequest request = new DownloadRequest(metadata, result.getSource(), observer);
      client.download(request);
    } catch (NodeNotConnectedException e) {
      download.onError(e);
      scene.writeConnectionErrorToLog();
    } catch (IOException e) {
      download.onError(e);
      logger.error("Couldn't download file {}", metadata.getName(), e);
    }
  }
}
