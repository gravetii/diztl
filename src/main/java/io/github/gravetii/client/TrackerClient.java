package io.github.gravetii.client;

import com.google.inject.Inject;
import io.github.gravetii.grpc.FileConstraint;
import io.github.gravetii.grpc.Node;
import io.github.gravetii.grpc.RegisterReq;
import io.github.gravetii.grpc.SearchReq;
import io.github.gravetii.keeper.TrackerConnection;
import io.github.gravetii.scene.start.StartScene;
import io.github.gravetii.store.DBService;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.net.Socket;

public class TrackerClient {

  private static final Logger logger =
      LoggerFactory.getLogger(TrackerClient.class.getCanonicalName());

  private final DBService dbService;
  private final TrackerConnection connection;
  private final Node node;

  private static String getMyIP() {
    try {
      Socket socket = new Socket();
      socket.connect(new InetSocketAddress("google.com", 80));
      String ip = socket.getLocalAddress().getHostAddress();
      logger.info("Local IP - {}", ip);
      return ip;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Inject
  public TrackerClient(DBService dbService) {
    this.dbService = dbService;
    ManagedChannelBuilder<?> builder =
        ManagedChannelBuilder.forTarget(dbService.getTrackerAddress());
    ManagedChannel channel = builder.usePlaintext().build();
    this.connection = new TrackerConnection(channel);
    this.node = register();
  }

  private Node register() {
    String ip = getMyIP();
    Node self = Node.newBuilder().setIp(ip).build();
    RegisterReq request = RegisterReq.newBuilder().setSelf(self).build();
    return connection.blockingStub.register(request).getNode();
  }

  public void search(String query, FileConstraint constraint, StartScene scene) {
    SearchReq request =
        SearchReq.newBuilder().setSource(node).setQuery(query).setConstraint(constraint).build();
    logger.info("Searching for pattern - {}", query);
    SearchService service = new SearchService(query, constraint, scene);
    connection.asyncStub.search(request, service.newObserver());
  }
}
