package io.github.gravetii.client;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import io.github.gravetii.grpc.*;
import io.github.gravetii.keeper.KeeperService;
import io.github.gravetii.keeper.NodeKeeper;
import io.github.gravetii.keeper.TrackerConnection;
import io.github.gravetii.store.DBService;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.net.Socket;

@Singleton
public class DiztlClient {

  private static final Logger logger =
      LoggerFactory.getLogger(DiztlClient.class.getCanonicalName());

  private final NodeKeeper keeper;
  private final TrackerConnection connection;
  private final Node node;

  @Inject
  public DiztlClient(DBService dbService, NodeKeeper keeper) {
    this.keeper = keeper;
    ManagedChannelBuilder<?> builder =
        ManagedChannelBuilder.forTarget(dbService.getTrackerAddress());
    ManagedChannel channel = builder.usePlaintext().build();
    this.connection = new TrackerConnection(channel);
    this.node = register();
  }

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

  private Node register() {
    String ip = getMyIP();
    Node self = Node.newBuilder().setIp(ip).build();
    RegisterReq request = RegisterReq.newBuilder().setSelf(self).build();
    return connection.blockingStub.register(request).getNode();
  }

  public Node getSelf() {
    return node;
  }

  public void search(String query, FileConstraint constraint, StreamObserver<SearchResp> observer) {
    SearchReq request =
        SearchReq.newBuilder().setSource(node).setQuery(query).setConstraint(constraint).build();
    logger.info("Searching for pattern - {}", query);
    connection.asyncStub.search(request, observer);
  }

  public void download(FileMetadata file, Node source, StreamObserver<FileChunk> observer) {
    UploadReq request = UploadReq.newBuilder().setSource(node).setMetadata(file).build();
    keeper.getOrCreate(source).asyncStub.upload(request, observer);
  }
}
