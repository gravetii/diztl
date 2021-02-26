package io.github.gravetii.keeper;

import io.github.gravetii.grpc.DiztlServiceGrpc;
import io.github.gravetii.grpc.DiztlServiceGrpc.DiztlServiceBlockingStub;
import io.github.gravetii.grpc.DiztlServiceGrpc.DiztlServiceFutureStub;
import io.github.gravetii.grpc.DiztlServiceGrpc.DiztlServiceStub;
import io.grpc.ManagedChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class DiztlConnection {

  private static final Logger logger =
      LoggerFactory.getLogger(DiztlConnection.class.getCanonicalName());

  private final ManagedChannel channel;

  public DiztlConnection(ManagedChannel channel) {
    this.channel = channel;
  }

  public DiztlServiceBlockingStub newBlockingStub() {
    return DiztlServiceGrpc.newBlockingStub(channel);
  }

  public DiztlServiceFutureStub newFutureStub() {
    return DiztlServiceGrpc.newFutureStub(channel);
  }

  public DiztlServiceStub newAsyncStub() {
    return DiztlServiceGrpc.newStub(channel);
  }

  public void close() {
    if (channel != null && !channel.isShutdown()) {
      try {
        channel.shutdown();
        channel.awaitTermination(2, TimeUnit.SECONDS);
        channel.shutdownNow();
      } catch (InterruptedException e) {
        logger.error("Thread interrupted while closing diztl connection", e);
      }
    }
  }
}
