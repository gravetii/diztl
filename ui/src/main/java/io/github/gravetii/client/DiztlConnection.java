package io.github.gravetii.client;

import io.github.gravetii.gen.DiztlServiceGrpc;
import io.grpc.ManagedChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DiztlConnection {
  private static final Logger logger =
      LoggerFactory.getLogger(DiztlConnection.class.getCanonicalName());

  private ManagedChannel channel;
  private DiztlServiceGrpc.DiztlServiceBlockingStub stub;
  private DiztlServiceGrpc.DiztlServiceFutureStub futureStub;
  private DiztlServiceGrpc.DiztlServiceStub asyncStub;

  DiztlConnection(ManagedChannel channel) {
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
    this.channel.shutdownNow();
    logger.info("Successfully closed communication channel");
  }
}
