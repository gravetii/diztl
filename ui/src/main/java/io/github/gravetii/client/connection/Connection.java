package io.github.gravetii.client.connection;

import io.github.gravetii.gen.DiztlServiceGrpc;
import io.grpc.ManagedChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class Connection {
  private static final Logger logger = LoggerFactory.getLogger(Connection.class.getCanonicalName());

  private ManagedChannel channel;
  private DiztlServiceGrpc.DiztlServiceBlockingStub stub;
  private DiztlServiceGrpc.DiztlServiceFutureStub futureStub;
  private DiztlServiceGrpc.DiztlServiceStub asyncStub;

  Connection(ManagedChannel channel) {
    this.channel = channel;
    this.stub = DiztlServiceGrpc.newBlockingStub(channel);
    this.futureStub = DiztlServiceGrpc.newFutureStub(channel);
    this.asyncStub = DiztlServiceGrpc.newStub(channel);
  }

  public DiztlServiceGrpc.DiztlServiceBlockingStub getStub() {
    return stub;
  }

  public DiztlServiceGrpc.DiztlServiceFutureStub getFutureStub() {
    return futureStub;
  }

  public DiztlServiceGrpc.DiztlServiceStub getAsyncstub() {
    return asyncStub;
  }

  void close() {
    channel.shutdown();
    try {
      boolean terminated = channel.awaitTermination(5, TimeUnit.SECONDS);
      if (!terminated) {
        channel.shutdownNow();
      }
    } catch (Exception e) {
      logger.error("Exception while shutting down communication channel", e);
    }

    this.channel.shutdownNow();
    logger.info("Successfully closed communication channel");
  }
}
