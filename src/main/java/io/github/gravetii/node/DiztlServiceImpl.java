package io.github.gravetii.node;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.protobuf.ByteString;
import io.github.gravetii.client.DiztlClient;
import io.github.gravetii.grpc.*;
import io.github.gravetii.indexer.FileIndexer;
import io.grpc.stub.StreamObserver;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class DiztlServiceImpl extends DiztlServiceGrpc.DiztlServiceImplBase {

  private static final Logger logger =
      LoggerFactory.getLogger(DiztlServiceImpl.class.getCanonicalName());

  private static final int BUFFER_SIZE = 1024 * 1024;

  private final DiztlClient client;
  private final FileIndexer indexer;

  @Inject
  public DiztlServiceImpl(DiztlClient client, FileIndexer indexer) {
    this.client = client;
    this.indexer = indexer;
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
  public void upload(UploadReq request, StreamObserver<FileChunk> responseObserver) {
    logger.debug("Received upload request from node {}", request.getSource().getIp());
    String dir = request.getMetadata().getDir();
    String name = request.getMetadata().getName();
    Path path = Paths.get(dir, name);
    File file = new File(path.toString());
    if (!file.exists()) {
      logger.warn("File not found - {}", file);
      return;
    }

    BufferedInputStream stream = null;

    try {
      stream = new BufferedInputStream(new FileInputStream(file));
      byte[] buffer = new byte[BUFFER_SIZE];
      int chunk = 1;
      int b;
      while ((b = stream.read(buffer)) > 0) {
        ByteString data = ByteString.copyFrom(buffer, 0, b);
        FileChunk c =
            FileChunk.newBuilder()
                .setChunk(chunk++)
                .setData(data)
                .setMetadata(request.getMetadata())
                .setChunks(Math.max((int) (FileUtils.sizeOf(file) / BUFFER_SIZE), 1))
                .build();
        responseObserver.onNext(c);
      }
      responseObserver.onCompleted();
    } catch (Exception e) {
      responseObserver.onError(new Exception("Error while uploading file", e));
    } finally {
      if (stream != null) {
        try {
          stream.close();
        } catch (IOException e) {
          logger.error("Exception while closing input stream");
        }
      }
    }
  }
}
