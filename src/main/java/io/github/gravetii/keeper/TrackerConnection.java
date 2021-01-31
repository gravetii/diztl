package io.github.gravetii.keeper;

import io.github.gravetii.grpc.TrackerServiceGrpc;
import io.github.gravetii.grpc.TrackerServiceGrpc.TrackerServiceBlockingStub;
import io.github.gravetii.grpc.TrackerServiceGrpc.TrackerServiceFutureStub;
import io.github.gravetii.grpc.TrackerServiceGrpc.TrackerServiceStub;
import io.grpc.ManagedChannel;

public class TrackerConnection {

  public final TrackerServiceBlockingStub blockingStub;
  public final TrackerServiceFutureStub futureStub;
  public final TrackerServiceStub asyncStub;

  public TrackerConnection(ManagedChannel channel) {
    this.blockingStub = TrackerServiceGrpc.newBlockingStub(channel);
    this.futureStub = TrackerServiceGrpc.newFutureStub(channel);
    this.asyncStub = TrackerServiceGrpc.newStub(channel);
  }
}
