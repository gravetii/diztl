package io.github.gravetii.node;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import io.github.gravetii.client.DiztlClient;
import io.github.gravetii.grpc.*;
import io.github.gravetii.indexer.FileIndexer;
import io.github.gravetii.service.UploadService;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class DiztlServiceImpl extends DiztlServiceGrpc.DiztlServiceImplBase {

  private static final Logger logger =
      LoggerFactory.getLogger(DiztlServiceImpl.class.getCanonicalName());

  private final DiztlClient client;
  private final FileIndexer indexer;
  private final UploadService uploader;

  @Inject
  public DiztlServiceImpl(DiztlClient client, FileIndexer indexer) {
    this.client = client;
    this.indexer = indexer;
    this.uploader = new UploadService();
  }

  @Override
  public void search(SearchReq request, StreamObserver<SearchResp> responseObserver) {
    List<FileMetadata> files =
        indexer.search(request.getQuery(), request.getConstraint()).stream()
            .map(
                x ->
                    FileMetadata.newBuilder()
                        .setDir(x.getDir())
                        .setName(x.getName())
                        .setId(1)
                        .setSize(x.getSize())
                        .setHash(FileHash.newBuilder().build())
                        .build())
            .collect(Collectors.toList());

    SearchResp resp = SearchResp.newBuilder().addAllFiles(files).setNode(client.getSelf()).build();
    responseObserver.onNext(resp);
    responseObserver.onCompleted();
  }

  @Override
  public void upload(UploadReq request, StreamObserver<FileChunk> observer) {
    logger.debug("Received upload request from node {}", request.getSource());
    uploader.upload(request, observer);
  }
}
