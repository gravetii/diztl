package io.github.gravetii.client.handler;

import com.google.common.util.concurrent.ListenableFuture;
import io.github.gravetii.client.connection.Connection;
import io.github.gravetii.gen.Diztl;
import io.github.gravetii.scene.start.StartScene;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RegisterHandler {
  private static final Logger logger =
      LoggerFactory.getLogger(RegisterHandler.class.getCanonicalName());

  private StartScene scene;

  public RegisterHandler(StartScene scene) {
    this.scene = scene;
  }

  public void process(Connection connection) {
    Diztl.Node self = Diztl.Node.getDefaultInstance();
    Diztl.RegisterReq req = Diztl.RegisterReq.newBuilder().setSelf(self).build();
    ListenableFuture<Diztl.RegisterResp> f = connection.getFutureStub().register(req);
    f.addListener(
        () -> {
          try {
            Diztl.RegisterResp resp = f.get();
            logger.info("Successfully registered to tracker at {}.", resp.getTracker().getIp());
            scene.writeToLog(
                "Successfully registered to tracker at " + resp.getTracker().getIp() + "." + "\n");
          } catch (Exception e) {
            logger.error("Error while registering to tracker - ", e);
          }
        },
        ExecutionHandler.get());
  }
}
