package io.github.gravetii.node;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class DiztlServer {

  private static final Logger logger =
      LoggerFactory.getLogger(DiztlServer.class.getCanonicalName());

  private static final int PORT = 50035;

  private final DiztlServiceImpl service;

  private Server server;

  public DiztlServer(DiztlServiceImpl service) {
    this.service = service;
  }

  public void start() {
    new Thread(
            () -> {
              try {
                ServerBuilder<?> builder = ServerBuilder.forPort(PORT).addService(service);
                server = builder.build().start();
                logger.info("Started diztl server...");
                if (server != null) server.awaitTermination();
              } catch (Exception e) {
                logger.error("Error while starting diztl server", e);
              }
            })
        .start();
  }

  public void stop() {
    if (server != null) {
      server.shutdown();
      try {
        server.awaitTermination(2, TimeUnit.MINUTES);
        server.shutdownNow();
        logger.info("diztl server terminated");
      } catch (InterruptedException e) {
        logger.error("Thread interrupted while awaiting server termination", e);
      }
    }
  }
}
