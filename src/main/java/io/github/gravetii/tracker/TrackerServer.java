package io.github.gravetii.tracker;

import com.google.inject.Guice;
import com.google.inject.Injector;
import io.github.gravetii.keeper.NodeKeeper;
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

  private static Injector injector;

  private Server server;

  private void start() throws IOException {
    NodeKeeper keeper = injector.getInstance(NodeKeeper.class);
    ServerBuilder<?> builder =
        ServerBuilder.forPort(port).addService(new TrackerServiceImpl(keeper));
    this.server = builder.build().start();
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
    TrackerServer.injector = Guice.createInjector(new TrackerModule());
    TrackerServer tracker = new TrackerServer();
    tracker.start();
    tracker.blockUntilShutdown();
  }
}
