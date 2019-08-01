package io.github.gravetii.client.handler;

import io.github.gravetii.client.connection.Connection;
import io.github.gravetii.gen.Diztl;

public class GetTrackerHandler {
  public String process(Connection connection) {
    Diztl.GetTrackerReq req = Diztl.GetTrackerReq.getDefaultInstance();
    Diztl.GetTrackerResp resp = connection.getStub().getTracker(req);
    return resp.getTracker().getIp();
  }
}
