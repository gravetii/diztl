package io.github.gravetii.tracker;

import io.github.gravetii.client.NodeNotConnectedException;
import io.github.gravetii.grpc.*;
import io.github.gravetii.keeper.NodeKeeper;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TrackerServiceImpl extends TrackerServiceGrpc.TrackerServiceImplBase {

  private static final Logger logger =
      LoggerFactory.getLogger(TrackerServiceImpl.class.getCanonicalName());

  private final NodeKeeper keeper;

  public TrackerServiceImpl(NodeKeeper keeper) {
    this.keeper = keeper;
  }

  @Override
  public void register(RegisterReq request, StreamObserver<RegisterResp> observer) {
    Node node = keeper.register(request.getSelf());
    RegisterResp response = RegisterResp.newBuilder().setNode(node).build();
    observer.onNext(response);
    observer.onCompleted();
  }

  @Override
  public void search(SearchReq request, StreamObserver<SearchResp> observer) {
    logger.info("Received search request from {}", request.getSource().getIp());
    keeper
        .nodes()
        .forEach(
            (node, conn) -> {
              SearchResp response = conn.newBlockingStub().search(request);
              logger.info("Search response - {}", response);
              observer.onNext(response);
            });

    observer.onCompleted();
  }

  @Override
  public void disconnect(DisconnectReq request, StreamObserver<DisconnectResp> observer) {
    logger.info("Received disconnect request from {}", request.getNode().getIp());
    if (!keeper.disconnect(request.getNode())) {
      observer.onError(new NodeNotConnectedException());
    } else {
      String msg = "You have disconnected from the network";
      DisconnectResp response = DisconnectResp.newBuilder().setMessage(msg).build();
      observer.onNext(response);
      observer.onCompleted();
    }
  }
}
