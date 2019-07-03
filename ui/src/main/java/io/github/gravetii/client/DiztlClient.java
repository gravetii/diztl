package io.github.gravetii.client;

import io.github.gravetii.gen.Diztl;
import io.github.gravetii.gen.Diztl.DownloadReq;
import io.github.gravetii.gen.Diztl.FileMetadata;
import io.github.gravetii.gen.Diztl.Node;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
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

  public void find(String pattern) {
    Diztl.FindReq req = Diztl.FindReq.newBuilder().setPattern(pattern).build();
    Diztl.FindResp resp = connection.getStub().find(req);
    System.out.println("Found following files, downloading first one.");
    resp.getResponsesList().forEach(r -> {
      r.getFilesList().forEach(f -> {
        System.out.println(f.getName());
        download(f, r.getNode());
      });
    });
  }

  public void download(FileMetadata metadata, Node source) {
    System.out.println("Downloading file now...");
    DownloadReq dreq = DownloadReq.newBuilder().setMetadata(metadata).setSource(source).build();
    connection.getStub().download(dreq);
  }

}
