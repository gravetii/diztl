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

  /** the connection to tracker */
  private TrackerConnection connection;

  private volatile Node node;

  @Inject
  public DiztlClient(DBService dbService, NodeKeeper keeper) {
    this.dbService = dbService;
    this.keeper = keeper;
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

  /** Connect to the tracker */
  private void connect() {
    // terminate the current connection
    if (connection != null) connection.close();
    ManagedChannelBuilder<?> builder =
        ManagedChannelBuilder.forTarget(dbService.getTrackerAddress());
    ManagedChannel channel = builder.usePlaintext().build();
    connection = new TrackerConnection(channel);
    logger.info("Connected to tracker - 1");
  }

  public synchronized void register() {
    this.node = null;
    this.connect();
    String ip = getMyIP();
    Node self = Node.newBuilder().setIp(ip).build();
    RegisterReq request = RegisterReq.newBuilder().setSelf(self).build();
    this.node =
        connection
            .newBlockingStub()
            .withDeadlineAfter(3, TimeUnit.SECONDS)
            .register(request)
            .getNode();
  }

  public Node getSelf() {
    return node;
  }

  private void checkConnectionState() throws NodeNotConnectedException {
    // ideally, this should never happen
    if (node == null) {
      synchronized (this) {
        if (node == null) {
          logger.warn("node isn't registered, trying to register now...");
          try {
            this.register();
          } catch (Exception e) {
            throw new NodeNotConnectedException(e);
          }
        }
      }
    }

    if (node == null) throw new NodeNotConnectedException();
  }

  public void search(String query, FileConstraint constraint, StreamObserver<SearchResp> observer)
      throws NodeNotConnectedException {
    checkConnectionState();
    SearchReq request =
        SearchReq.newBuilder().setSource(node).setQuery(query).setConstraint(constraint).build();
    connection.newAsyncStub().search(request, observer);
  }

  public void download(FileMetadata file, Node source, StreamObserver<FileChunk> observer)
      throws NodeNotConnectedException {
    checkConnectionState();
    UploadReq request = UploadReq.newBuilder().setSource(node).setMetadata(file).build();
    keeper.getOrCreate(source).newAsyncStub().upload(request, observer);
  }
}
