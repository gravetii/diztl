package io.github.gravetii.client;

import com.google.common.util.concurrent.ListenableFuture;
import io.github.gravetii.grpc.*;
import io.github.gravetii.keeper.TrackerConnection;
import io.github.gravetii.scene.start.StartScene;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.net.Socket;

public class DiztlClient {

  private static final Logger logger =
      LoggerFactory.getLogger(DiztlClient.class.getCanonicalName());

  // todo: Get from store...
  private static final String TRACKER_HOST = "127.0.0.1";
  private static final int TRACKER_PORT = 50036;

  public static Node node = null;
  private static final TrackerConnection connection;

  static {
    ManagedChannelBuilder<?> builder = ManagedChannelBuilder.forAddress(TRACKER_HOST, TRACKER_PORT);
    ManagedChannel channel = builder.usePlaintext().build();
    connection = new TrackerConnection(channel);
  }

  private static String getMyIP() throws Exception {
    Socket socket = new Socket();
    socket.connect(new InetSocketAddress("google.com", 80));
    String ip = socket.getLocalAddress().getHostAddress();
    logger.info("Local IP - {}", ip);
    return ip;
  }

  public static ListenableFuture<RegisterResp> register() throws Exception {
    String ip = getMyIP();
    Node self = Node.newBuilder().setIp(ip).build();
    RegisterReq request = RegisterReq.newBuilder().setSelf(self).build();
    return connection.futureStub.register(request);
  }

  public static void search(
      String query, FileConstraint constraint, StreamObserver<SearchResp> observer) {
    SearchReq request =
        SearchReq.newBuilder().setSource(node).setQuery(query).setConstraint(constraint).build();
    logger.info("Searching for pattern - {}", query);
    connection.asyncStub.search(request, observer);
  }

  public static void download(FileMetadata file, Node source, StartScene scene) {
    DownloadService service = new DownloadService(scene);
    service.download(file, node, source);
  }
}
