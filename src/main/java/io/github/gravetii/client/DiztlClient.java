package io.github.gravetii.client;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import io.github.gravetii.grpc.*;
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
import java.util.concurrent.TimeUnit;

@Singleton
public class DiztlClient {

  private static final Logger logger =
      LoggerFactory.getLogger(DiztlClient.class.getCanonicalName());

  private final DBService dbService;
  private final NodeKeeper keeper;

  private TrackerConnection connection;

  private Node node;

  @Inject
  public DiztlClient(DBService dbService, NodeKeeper keeper) {
    this.dbService = dbService;
    this.keeper = keeper;
  }

  /**
   * Connect to the tracker
   */
  private void connect() {
    ManagedChannelBuilder<?> builder =
            ManagedChannelBuilder.forTarget(dbService.getTrackerAddress());
    ManagedChannel channel = builder.usePlaintext().build();
    this.connection = new TrackerConnection(channel);
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

  public void register() {
    this.node = null;
    this.connect();
    String ip = getMyIP();
    Node self = Node.newBuilder().setIp(ip).build();
    RegisterReq request = RegisterReq.newBuilder().setSelf(self).build();
    RegisterResp response =
        connection.blockingStub.withDeadlineAfter(3, TimeUnit.SECONDS).register(request);
    this.node = response.getNode();
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
