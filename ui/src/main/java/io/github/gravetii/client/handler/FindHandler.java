package io.github.gravetii.client.handler;

import com.google.common.util.concurrent.ListenableFuture;
import io.github.gravetii.client.connection.CommunicationClient;
import io.github.gravetii.client.connection.Connection;
import io.github.gravetii.gen.Diztl;
import io.github.gravetii.scene.start.StartScene;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FindHandler {
  private static final Logger logger =
      LoggerFactory.getLogger(FindHandler.class.getCanonicalName());

  private StartScene scene;
  private String pattern;

  public FindHandler(StartScene scene, String pattern) {
    this.scene = scene;
    this.pattern = pattern;
  }

  public void process(Connection connection) {
    scene.reset();
    Diztl.FindReq req = Diztl.FindReq.newBuilder().setPattern(pattern).build();
    logger.info("Searching for pattern - {}", pattern);
    ListenableFuture<Diztl.FindResp> f = connection.getFutureStub().find(req);
    f.addListener(
        () -> {
          try {
            Diztl.FindResp resp = f.get();
            resp.getResponsesList()
                .forEach(
                    r -> {
                      r.getFilesList()
                          .forEach(
                              file -> {
                                scene.showFileResult(file, r.getNode());
                              });
                    });
          } catch (Exception e) {
            logger.error("Error while finding for pattern:", e);
          }
        },
        CommunicationClient.get().executor());
  }
}
