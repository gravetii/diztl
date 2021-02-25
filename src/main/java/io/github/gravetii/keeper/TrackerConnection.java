package io.github.gravetii.keeper;

import io.github.gravetii.grpc.TrackerServiceGrpc;
import io.github.gravetii.grpc.TrackerServiceGrpc.TrackerServiceBlockingStub;
import io.github.gravetii.grpc.TrackerServiceGrpc.TrackerServiceFutureStub;
import io.github.gravetii.grpc.TrackerServiceGrpc.TrackerServiceStub;
import io.grpc.ManagedChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class TrackerConnection {

  private static final Logger logger =
      LoggerFactory.getLogger(TrackerConnection.class.getCanonicalName());

  private final ManagedChannel channel;

  public TrackerConnection(ManagedChannel channel) {
    this.channel = channel;
  }

  public TrackerServiceBlockingStub newBlockingStub() {
    return TrackerServiceGrpc.newBlockingStub(channel);
  }

  public TrackerServiceFutureStub newFutureStub() {
    return TrackerServiceGrpc.newFutureStub(channel);
  }

  public TrackerServiceStub newAsyncStub() {
    return TrackerServiceGrpc.newStub(channel);
  }

  public void close() {
    if (channel != null && !channel.isShutdown()) {
      try {
        channel.shutdown();
        channel.awaitTermination(5, TimeUnit.SECONDS);
        channel.shutdownNow();
      } catch (InterruptedException e) {
        logger.error("Thread interrupted while awaiting tracker connection shutdown", e);
      }
    }
  }
}
