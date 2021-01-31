package io.github.gravetii.keeper;

import io.github.gravetii.grpc.DiztlServiceGrpc;
import io.github.gravetii.grpc.DiztlServiceGrpc.DiztlServiceBlockingStub;
import io.github.gravetii.grpc.DiztlServiceGrpc.DiztlServiceFutureStub;
import io.github.gravetii.grpc.DiztlServiceGrpc.DiztlServiceStub;
import io.grpc.ManagedChannel;

public class DiztlConnection {

  public final DiztlServiceBlockingStub blockingStub;
  public final DiztlServiceFutureStub futureStub;
  public final DiztlServiceStub asyncStub;

  public DiztlConnection(ManagedChannel channel) {
    this.blockingStub = DiztlServiceGrpc.newBlockingStub(channel);
    this.futureStub = DiztlServiceGrpc.newFutureStub(channel);
    this.asyncStub = DiztlServiceGrpc.newStub(channel);
  }
}
