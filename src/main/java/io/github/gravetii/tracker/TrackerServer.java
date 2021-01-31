package io.github.gravetii.tracker;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/** The gRPC server for trackers * */
public class TrackerServer {

  private static final Logger logger =
      LoggerFactory.getLogger(TrackerServer.class.getCanonicalName());

  private static final int port = 50036;

  private Server server;

  private void start() throws IOException {
    this.server = ServerBuilder.forPort(port).addService(new TrackerServiceImpl()).build().start();
    logger.info("Tracker server started on {}", port);
    Runtime.getRuntime()
        .addShutdownHook(
            new Thread(
                () -> {
                  logger.info("Shutting down tracker server due to JVM shutdown");
                  server.shutdownNow();
                  logger.info("Tracker server shutdown successful");
                }));
  }

  private void blockUntilShutdown() throws InterruptedException {
    if (this.server != null) this.server.awaitTermination();
  }

  public static void main(String[] args) throws Exception {
    TrackerServer tracker = new TrackerServer();
    tracker.start();
    tracker.blockUntilShutdown();
  }
}
