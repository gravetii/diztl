package io.github.gravetii.tracker;

import io.github.gravetii.grpc.*;
import io.github.gravetii.keeper.KeeperService;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TrackerServiceImpl extends TrackerServiceGrpc.TrackerServiceImplBase {

  private static final Logger logger =
      LoggerFactory.getLogger(TrackerServiceImpl.class.getCanonicalName());

  public TrackerServiceImpl() {}

  @Override
  public void register(RegisterReq request, StreamObserver<RegisterResp> responseObserver) {
    Node node = KeeperService.get().register(request.getSelf());
    RegisterResp response = RegisterResp.newBuilder().setNode(node).build();
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void search(SearchReq request, StreamObserver<SearchResp> observer) {
    logger.info("Received search request from {}", request.getSource().getIp());
    KeeperService.get()
        .nodes()
        .forEach(
            (node, conn) -> {
              SearchResp response = conn.blockingStub.search(request);
              logger.info("Search response - {}", response);
              observer.onNext(response);
            });

    observer.onCompleted();
  }
}
