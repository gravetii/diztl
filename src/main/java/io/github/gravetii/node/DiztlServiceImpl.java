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
    Path path = Paths.get(request.getMetadata().getDir(), request.getMetadata().getName());
    File file = new File(path.toString());
    if (!file.exists()) return;
    BufferedInputStream stream = null;
    int chunkSize = request.getContract().getChunkSize();
    int chunks = Math.max((int) (FileUtils.sizeOf(file) / chunkSize), 1);

    try {
      stream = new BufferedInputStream(new FileInputStream(file));
      byte[] buffer = new byte[chunkSize];
      int chunk = 1;
      int b;
      while ((b = stream.read(buffer)) > 0) {
        ByteString data = ByteString.copyFrom(buffer, 0, b);
        FileChunk.Builder builder = FileChunk.newBuilder().setChunk(chunk).setData(data);
        if (chunk == 1) builder.setMetadata(request.getMetadata()).setChunks(chunks);
        responseObserver.onNext(builder.build());
        chunk += 1;
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
