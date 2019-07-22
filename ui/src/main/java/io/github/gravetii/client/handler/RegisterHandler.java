package io.github.gravetii.client.handler;

import com.google.common.util.concurrent.ListenableFuture;
import io.github.gravetii.client.connection.Connection;
import io.github.gravetii.gen.Diztl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RegisterHandler {
  private static final Logger logger =
      LoggerFactory.getLogger(RegisterHandler.class.getCanonicalName());

  // the tracker's IP.
  private String host;

  public RegisterHandler(String host) {
    this.host = host;
  }

  public void process(Connection connection) {
    Diztl.Node self = Diztl.Node.getDefaultInstance();
    Diztl.Node tracker = Diztl.Node.newBuilder().setIp(host).build();
    Diztl.RegisterReq req =
        Diztl.RegisterReq.newBuilder().setSelf(self).setTracker(tracker).build();
    ListenableFuture<Diztl.RegisterResp> f = connection.getFutureStub().register(req);
    f.addListener(
        () -> {
          try {
            Diztl.RegisterResp resp = f.get();
            logger.info("Register response: {}", resp);
          } catch (Exception e) {
            logger.error("Error while registering to tracker - ", e);
          }
        },
        ExecutionHandler.get());
  }
}
