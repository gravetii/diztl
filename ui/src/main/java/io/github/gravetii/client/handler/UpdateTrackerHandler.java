package io.github.gravetii.client.handler;

import com.google.common.util.concurrent.ListenableFuture;
import io.github.gravetii.client.connection.CommunicationClient;
import io.github.gravetii.client.connection.Connection;
import io.github.gravetii.gen.Diztl;
import io.github.gravetii.scene.start.StartScene;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UpdateTrackerHandler {
  private static final Logger logger =
      LoggerFactory.getLogger(UpdateTrackerHandler.class.getCanonicalName());

  private String host;
  private StartScene scene;

  public UpdateTrackerHandler(String host, StartScene scene) {
    this.host = host;
    this.scene = scene;
  }

  public void process(Connection connection) {
    Diztl.UpdateTrackerReq req = Diztl.UpdateTrackerReq.newBuilder().setTracker(host).build();
    ListenableFuture<Diztl.UpdateTrackerResp> f = connection.getFutureStub().updateTracker(req);
    f.addListener(
        () -> {
          try {
            Diztl.UpdateTrackerResp resp = f.get();
            logger.info("Response code from updating tracker - {}", resp.getCode());
            logger.info("Register call removed here...");
          } catch (Exception e) {
            logger.error("Error while updating tracker -", e);
          }
        },
        ExecutionHandler.get());
  }
}
