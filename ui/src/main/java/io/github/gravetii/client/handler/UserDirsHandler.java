package io.github.gravetii.client.handler;

import io.github.gravetii.client.connection.Connection;
import io.github.gravetii.gen.Diztl;
import io.github.gravetii.pojo.UserDirs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserDirsHandler {
  private static final Logger logger =
      LoggerFactory.getLogger(UserDirsHandler.class.getCanonicalName());

  public UserDirs process(Connection connection) {
    Diztl.UserDirsReq req = Diztl.UserDirsReq.getDefaultInstance();
    logger.info("Fetching user dirs...");
    Diztl.UserDirsResp resp = connection.getStub().getUserDirs(req);
    return new UserDirs(resp.getShareList(), resp.getDownloads());
  }
}
