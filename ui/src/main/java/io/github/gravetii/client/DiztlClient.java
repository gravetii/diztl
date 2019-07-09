package io.github.gravetii.client;

import io.github.gravetii.client.handler.DownloadHandler;
import io.github.gravetii.client.handler.FindHandler;
import io.github.gravetii.gen.Diztl.FileMetadata;
import io.github.gravetii.gen.Diztl.Node;
import io.github.gravetii.scene.start.StartScene;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.DatagramSocket;
import java.net.InetAddress;

public class DiztlClient {
  private static final Logger logger =
      LoggerFactory.getLogger(DiztlClient.class.getCanonicalName());

  private static DiztlClient INSTANCE = null;

  private DiztlConnection connection;

  private DiztlClient() throws Exception {
    String ip = fetchLocalIp();
    ManagedChannel channel = ManagedChannelBuilder.forAddress(ip, 50051).usePlaintext().build();
    this.connection = new DiztlConnection(channel);
    Runtime.getRuntime().addShutdownHook(new Thread(() -> connection.close()));
  }

  public static void init() throws Exception {
    logger.info("Initialized DiztlClient.");
    INSTANCE = new DiztlClient();
  }

  public static DiztlClient get() {
    return INSTANCE;
  }

  private static String fetchLocalIp() throws Exception {
    try (final DatagramSocket socket = new DatagramSocket()) {
      socket.connect(InetAddress.getByName("8.8.8.8"), 80);
      String ip = socket.getLocalAddress().getHostAddress();
      logger.info("Got local IP address as {}", ip);
      return ip;
    } catch (Exception e) {
      logger.error("Unable to fetch the host's local IP", e);
      throw new Exception("Unable to fetch the host's local IP", e);
    }
  }

  public void find(String pattern, StartScene scene) {
    new FindHandler(scene, pattern).process(connection);
  }

  public void download(FileMetadata file, Node source) {
    new DownloadHandler(file, source).process(connection);
  }
}
