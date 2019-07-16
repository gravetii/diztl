package io.github.gravetii.client.handler;

import com.google.common.util.concurrent.ListenableFuture;
import io.github.gravetii.client.connection.CommunicationClient;
import io.github.gravetii.client.connection.Connection;
import io.github.gravetii.gen.Diztl;
import io.github.gravetii.scene.userdir.UserDirsScene;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserDirsHandler {
  private static final Logger logger =
      LoggerFactory.getLogger(UserDirsHandler.class.getCanonicalName());

  private UserDirsScene scene;
  private boolean share;
  private boolean output;

  public UserDirsHandler(UserDirsScene scene, boolean share, boolean output) {
    this.scene = scene;
    this.share = share;
    this.output = output;
  }

  public void process(Connection connection) {
    Diztl.UserDirsReq req =
        Diztl.UserDirsReq.newBuilder().setShare(share).setOutput(output).build();
    logger.info("Fetching user dirs - share: {}, output: {}", share, output);
    ListenableFuture<Diztl.UserDirsResp> f = connection.getFutureStub().getUserDirs(req);
    f.addListener(
        () -> {
          try {
            Diztl.UserDirsResp resp = f.get();
            logger.info("Got share folders: {}", resp.getShareList());
            scene.displayShareDirs(resp.getShareList());
          } catch (Exception e) {
            logger.error("Error while trying to fetch user dirs:", e);
          }
        },
        CommunicationClient.get().executor());
  }
}
