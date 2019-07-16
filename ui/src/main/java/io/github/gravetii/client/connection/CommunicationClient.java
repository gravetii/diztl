package io.github.gravetii.client.connection;

import io.github.gravetii.client.handler.DownloadHandler;
import io.github.gravetii.client.handler.FindHandler;
import io.github.gravetii.client.handler.UpdateUserDirsHandler;
import io.github.gravetii.client.handler.UserDirsHandler;
import io.github.gravetii.gen.Diztl.FileMetadata;
import io.github.gravetii.gen.Diztl.Node;
import io.github.gravetii.scene.start.StartScene;
import io.github.gravetii.scene.userdir.UserDirsScene;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CommunicationClient {
  private static final Logger logger =
      LoggerFactory.getLogger(CommunicationClient.class.getCanonicalName());

  private static CommunicationClient INSTANCE = null;
  private final ExecutorService executor;
  private Connection connection;
  private String ip;

  private CommunicationClient() throws Exception {
    this.ip = fetchLocalIp();
    ManagedChannel channel = ManagedChannelBuilder.forAddress(ip, 50051).usePlaintext().build();
    this.connection = new Connection(channel);
    this.executor =
        Executors.newFixedThreadPool(
            3,
            (r) -> {
              Thread thread = new Thread(r);
              thread.setDaemon(true);
              return thread;
            });

    Runtime.getRuntime().addShutdownHook(new Thread(() -> connection.close()));
  }

  public static void init() throws Exception {
    logger.info("Initialized communication client.");
    INSTANCE = new CommunicationClient();
  }

  public static CommunicationClient get() {
    return INSTANCE;
  }

  private String fetchLocalIp() throws Exception {
    try (final Socket socket = new Socket()) {
      socket.connect(new InetSocketAddress("google.com", 80));
      ip = socket.getLocalAddress().getHostAddress();
      logger.info("Got local IP as: {}", ip);
      return ip;
    } catch (Exception e) {
      logger.error("Unable to fetch the host's local IP", e);
      throw new Exception("Unable to fetch the host's local IP", e);
    }
  }

  public ExecutorService executor() {
    return executor;
  }

  public void find(String pattern, StartScene scene) {
    new FindHandler(scene, pattern).process(connection);
  }

  public void download(FileMetadata file, Node source, StartScene scene) {
    new DownloadHandler(file, source, scene).process(connection);
  }

  public void getUserDirs(boolean share, boolean output, UserDirsScene scene) {
    new UserDirsHandler(scene, share, output).process(connection);
  }

  public void updateUserDirs(List<String> share, String output) {
    new UpdateUserDirsHandler(share, output).process(connection);
  }
}
