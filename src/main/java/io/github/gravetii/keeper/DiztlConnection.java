package io.github.gravetii.keeper;

import io.github.gravetii.grpc.DiztlServiceGrpc;
import io.github.gravetii.grpc.DiztlServiceGrpc.DiztlServiceBlockingStub;
import io.github.gravetii.grpc.DiztlServiceGrpc.DiztlServiceFutureStub;
import io.github.gravetii.grpc.DiztlServiceGrpc.DiztlServiceStub;
import io.grpc.ManagedChannel;

public class DiztlConnection {

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
}
