package io.github.gravetii.client;

import com.google.common.util.concurrent.ListenableFuture;
import io.github.gravetii.grpc.Diztl;
import io.github.gravetii.grpc.Diztl.Node;
import io.github.gravetii.grpc.Diztl.RegisterReq;
import io.github.gravetii.grpc.Diztl.RegisterResp;
import io.github.gravetii.keeper.KeeperService;
import io.github.gravetii.keeper.TrackerConnection;
import io.github.gravetii.scene.start.StartScene;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
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

  public static void search(String query, Diztl.FileConstraint constraint, StartScene scene) {
    Diztl.SearchReq request =
        Diztl.SearchReq.newBuilder()
            .setSource(node)
            .setQuery(query)
            .setConstraint(constraint)
            .build();

    logger.info("Searching for pattern - {}", query);
    SearchService service = new SearchService(query, constraint, scene);
    connection.asyncStub.search(request, service.newObserver());
  }

  public static void download(Diztl.FileMetadata file, Diztl.Node source, StartScene scene) {
    DownloadService service = new DownloadService(file, source, scene);
    Diztl.UploadReq request =
        Diztl.UploadReq.newBuilder().setSource(node).setMetadata(file).build();
    KeeperService.get().getOrCreate(source).asyncStub.upload(request, service.newObserver());
  }
}
