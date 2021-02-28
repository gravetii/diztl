package io.github.gravetii.service;

import com.google.protobuf.ByteString;
import io.github.gravetii.grpc.FileChunk;
import io.github.gravetii.grpc.UploadReq;
import io.grpc.stub.StreamObserver;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class UploadService {

  private static final Logger logger =
      LoggerFactory.getLogger(UploadService.class.getCanonicalName());

  private static File getFileToUpload(UploadReq request) {
    Path path = Paths.get(request.getMetadata().getDir(), request.getMetadata().getName());
    return new File(path.toString());
  }

  /** Read a single chunk specified by the given size from the given input stream. */
  private static ByteString readChunk(BufferedInputStream stream, int size) throws IOException {
    byte[] buffer = new byte[size];
    int b = stream.read(buffer);
    return b > 0 ? ByteString.copyFrom(buffer, 0, b) : null;
  }

  private static void close(BufferedInputStream stream) {
    if (stream != null) {
      try {
        stream.close();
      } catch (IOException e) {
        logger.error("Error while closing input stream", e);
      }
    }
  }

  public void upload(UploadReq request, StreamObserver<FileChunk> observer) {
    BufferedInputStream stream = null;

    try {
      File file = getFileToUpload(request);
      stream = new BufferedInputStream(new FileInputStream(file));
      int chunkSize = request.getContract().getChunkSize();
      int chunks = Math.max((int) (FileUtils.sizeOf(file) / chunkSize), 1);
      int chunk = 0;
      ByteString data;
      while ((data = readChunk(stream, chunkSize)) != null) {
        FileChunk.Builder builder = FileChunk.newBuilder().setChunk(++chunk).setData(data);
        if (chunk == 1) builder.setMetadata(request.getMetadata()).setChunks(chunks);
        observer.onNext(builder.build());
      }
      observer.onCompleted();
    } catch (FileNotFoundException e) {
      observer.onError(new Exception("Requested file doesn't exist"));
    } catch (IOException e) {
      observer.onError(new Exception("Error while reading file"));
    } finally {
      close(stream);
    }
  }
}
