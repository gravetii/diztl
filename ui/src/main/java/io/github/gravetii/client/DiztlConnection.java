package io.github.gravetii.client;

import io.github.gravetii.gen.*;

import io.grpc.ManagedChannel;

public class DiztlConnection {
  private ManagedChannel channel;
  private DiztlServiceGrpc.DiztlServiceBlockingStub stub;
  private DiztlServiceGrpc.DiztlServiceStub asyncStub;

  public DiztlConnection(ManagedChannel channel) {
    this.channel = channel;
    this.stub = DiztlServiceGrpc.newBlockingStub(channel);
    this.asyncStub = DiztlServiceGrpc.newStub(channel);
  }

  public ManagedChannel getChannel() {
    return channel;
  }

  public DiztlServiceGrpc.DiztlServiceBlockingStub getStub() {
    return stub;
  }

  public DiztlServiceGrpc.DiztlServiceStub getAsyncstub() {
    return asyncStub;
  }

}
