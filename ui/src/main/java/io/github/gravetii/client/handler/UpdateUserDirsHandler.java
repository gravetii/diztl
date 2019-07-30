package io.github.gravetii.client.handler;

import com.google.common.util.concurrent.ListenableFuture;
import io.github.gravetii.client.connection.CommunicationClient;
import io.github.gravetii.client.connection.Connection;
import io.github.gravetii.gen.Diztl;
import io.github.gravetii.scene.start.StartScene;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class UpdateUserDirsHandler {
  private static final Logger logger =
      LoggerFactory.getLogger(UpdateUserDirsHandler.class.getCanonicalName());

  private List<String> share;
  private String output;
  private StartScene scene;

  public UpdateUserDirsHandler(List<String> share, String output, StartScene scene) {
    this.share = share;
    this.output = output;
    this.scene = scene;
  }

  public void process(Connection connection) {
    Diztl.UpdateUserDirsReq req =
        Diztl.UpdateUserDirsReq.newBuilder().addAllShare(share).setOutput(output).build();
    logger.info("Updating user dirs - share: {}, output: {}", share, output);
    ListenableFuture<Diztl.UpdateUserDirsResp> f = connection.getFutureStub().updateUserDirs(req);
    f.addListener(
        () -> {
          try {
            if (!output.equals("")) {
              scene.writeToLog("Updated downloads directory to " + output + ".\n");
            }
            if (!share.isEmpty()) {
              scene.writeToLog("Updated share folders, re-indexing files now...\n");
              CommunicationClient.get().index(scene);
            }
          } catch (Exception e) {
            logger.error("Error while updating user dirs:", e);
          }
        },
        ExecutionHandler.get());
  }
}
