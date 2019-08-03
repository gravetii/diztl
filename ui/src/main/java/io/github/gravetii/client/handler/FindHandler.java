package io.github.gravetii.client.handler;

import com.google.common.util.concurrent.ListenableFuture;
import io.github.gravetii.client.connection.Connection;
import io.github.gravetii.controller.start.FileResult;
import io.github.gravetii.gen.Diztl;
import io.github.gravetii.scene.start.ResultListComponent;
import io.github.gravetii.scene.start.StartScene;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FindHandler {
  private static final Logger logger =
      LoggerFactory.getLogger(FindHandler.class.getCanonicalName());

  private StartScene scene;
  private String query;

  public FindHandler(StartScene scene, String query) {
    this.scene = scene;
    this.query = query;
  }

  public void process(Connection connection) {
    ResultListComponent resultListComponent = scene.addNewSearchTab(query);
    Diztl.FindReq req = Diztl.FindReq.newBuilder().setQuery(query).build();
    logger.info("Searching for pattern - {}", query);
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
                                resultListComponent
                                    .getController()
                                    .show(new FileResult(file, r.getNode()));
                              });
                    });
          } catch (Exception e) {
            logger.error("Error while finding for pattern -", e);
          }
        },
        ExecutionHandler.get());
  }
}
