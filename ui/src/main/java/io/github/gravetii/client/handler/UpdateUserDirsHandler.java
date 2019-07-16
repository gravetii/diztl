package io.github.gravetii.client.handler;

import com.google.common.util.concurrent.ListenableFuture;
import io.github.gravetii.client.connection.Connection;
import io.github.gravetii.gen.Diztl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class UpdateUserDirsHandler {
  private static final Logger logger =
      LoggerFactory.getLogger(UpdateUserDirsHandler.class.getCanonicalName());

  private List<String> share;
  private String output;

  public UpdateUserDirsHandler(List<String> share, String output) {
    this.share = share;
    this.output = output;
  }

  public void process(Connection connection) {
    Diztl.UpdateUserDirsReq req =
        Diztl.UpdateUserDirsReq.newBuilder().addAllShare(share).setOutput(output).build();
    logger.info("Updating user dirs - share: {}, output: {}", share, output);
    ListenableFuture<Diztl.UpdateUserDirsResp> f = connection.getFutureStub().updateUserDirs(req);
    f.addListener(
        () -> {
          try {
            logger.info(f.get().getMessage());
          } catch (Exception e) {
            logger.error("Error while updating user dirs:", e);
          }
        },
        ExecutionHandler.get());
  }
}
