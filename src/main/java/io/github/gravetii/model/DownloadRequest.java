package io.github.gravetii.model;

import io.github.gravetii.grpc.FileChunk;
import io.github.gravetii.grpc.FileMetadata;
import io.github.gravetii.grpc.Node;
import io.grpc.stub.StreamObserver;

public class DownloadRequest {

  private final FileMetadata file;
  private final Node node;
  private final StreamObserver<FileChunk> observer;

  public DownloadRequest(FileMetadata file, Node node, StreamObserver<FileChunk> observer) {
    this.file = file;
    this.node = node;
    this.observer = observer;
  }

  public FileMetadata getFile() {
    return file;
  }

  public Node getNode() {
    return node;
  }

  public StreamObserver<FileChunk> getObserver() {
    return observer;
  }
}
