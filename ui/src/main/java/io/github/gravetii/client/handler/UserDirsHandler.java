package io.github.gravetii.client.handler;

import com.google.common.util.concurrent.ListenableFuture;
import io.github.gravetii.client.DiztlClient;
import io.github.gravetii.client.DiztlConnection;
import io.github.gravetii.gen.Diztl;
import io.github.gravetii.scene.share.ShareFoldersScene;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserDirsHandler {
  private static final Logger logger = LoggerFactory.getLogger(UserDirsHandler.class.getCanonicalName());

  private ShareFoldersScene scene;
  private boolean share;
  private boolean output;

  public UserDirsHandler(ShareFoldersScene scene, boolean share, boolean output) {
    this.scene = scene;
    this.share = share;
    this.output = output;
  }

  public void process(DiztlConnection connection) {
    Diztl.UserDirsReq req = Diztl.UserDirsReq.newBuilder().setShare(share).setOutput(output).build();
    logger.info("Fetching user dirs - share: {}, output: {}", share, output);
    ListenableFuture<Diztl.UserDirsResp> f = connection.getFutureStub().getUserDirs(req);
    f.addListener(() -> {
      try {
        Diztl.UserDirsResp resp = f.get();
        logger.info("Got share folders: {}", resp.getShareList());
        scene.displayUserFolders(resp.getShareList());
      } catch (Exception e) {
        logger.error("Error while trying to fetch user dirs:", e);
      }
    }, DiztlClient.get().executor());
  }
}
