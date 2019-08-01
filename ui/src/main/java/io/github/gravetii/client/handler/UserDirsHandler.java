package io.github.gravetii.client.handler;

import io.github.gravetii.client.connection.Connection;
import io.github.gravetii.gen.Diztl;
import io.github.gravetii.pojo.UserDirs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserDirsHandler {
  private static final Logger logger =
      LoggerFactory.getLogger(UserDirsHandler.class.getCanonicalName());

  private boolean share;
  private boolean output;

  public UserDirsHandler(boolean share, boolean output) {
    this.share = share;
    this.output = output;
  }

  public UserDirs process(Connection connection) {
    Diztl.UserDirsReq req =
        Diztl.UserDirsReq.newBuilder().setShare(share).setOutput(output).build();
    logger.info("Fetching user dirs - share: {}, output: {}", share, output);
    Diztl.UserDirsResp resp = connection.getStub().getUserDirs(req);
    return new UserDirs(resp.getShareList(), resp.getOutput());
  }
}
