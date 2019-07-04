package io.github.gravetii.client;

import io.github.gravetii.gen.Diztl;
import io.github.gravetii.gen.Diztl.DownloadReq;
import io.github.gravetii.gen.Diztl.FileMetadata;
import io.github.gravetii.gen.Diztl.Node;
import io.github.gravetii.scene.start.StartScene;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.net.Socket;

public class DiztlClient {
  private static final Logger logger = LoggerFactory.getLogger(DiztlClient.class.getCanonicalName());

  private DiztlConnection connection;

  private static String getMyIP() {
    Socket socket = new Socket();
    try {
      socket.connect(new InetSocketAddress("google.com", 80));
      String ip = socket.getLocalAddress().getHostAddress();
      logger.info("Got local IP as: {}", ip);
      return ip;
    } catch (Exception e) {
      return null;
    } finally {
      try {
        socket.close();
      } catch (Exception e) {

      }
    }
  }

  private DiztlClient(String host, int port) {
    ManagedChannel channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
    this.connection = new DiztlConnection(channel);
  }

  public DiztlClient() {
    this(getMyIP(), 50051);
  }

  public void find(String pattern, StartScene scene) {
    StreamObserver<Diztl.FindResp> observer = new StreamObserver<Diztl.FindResp>() {
      @Override
      public void onNext(Diztl.FindResp value) {
        System.out.println("Got Find response: " + value.getResponsesList());
        value.getResponsesList().forEach(r -> {
          r.getFilesList().forEach(f -> {
            scene.showFileResult(f, r.getNode());
          });
        });
      }

      @Override
      public void onError(Throwable t) {

      }

      @Override
      public void onCompleted() {
        System.out.println("Find completed.");
      }
    };

    Diztl.FindReq req = Diztl.FindReq.newBuilder().setPattern(pattern).build();
    connection.getAsyncstub().find(req, observer);
  }

  public void download(FileMetadata file, Node source) {
    StreamObserver<Diztl.DownloadResp> observer = new StreamObserver<Diztl.DownloadResp>() {
      @Override
      public void onNext(Diztl.DownloadResp value) {
        System.out.println("On next called...");
      }

      @Override
      public void onError(Throwable t) {

      }

      @Override
      public void onCompleted() {
        System.out.println("Download completed...");
      }
    };

    System.out.println("Downloading file: " + file.getName() + " from " + source.getIp());
    DownloadReq dreq = DownloadReq.newBuilder().setMetadata(file).setSource(source).build();
    connection.getAsyncstub().download(dreq, observer);
  }

}
