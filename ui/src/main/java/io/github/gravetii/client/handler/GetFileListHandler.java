package io.github.gravetii.client.handler;

import io.github.gravetii.client.connection.Connection;
import io.github.gravetii.gen.Diztl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class GetFileListHandler {
  private static final Logger logger = LoggerFactory.getLogger(GetFileListHandler.class.getCanonicalName());

  private Diztl.Node node;
  private Diztl.FileMetadata file;

  public GetFileListHandler(Diztl.Node node, Diztl.FileMetadata file) {
    this.node = node;
    this.file = file;
  }

  public List<Diztl.FileMetadata> process(Connection connection) {
    Diztl.FetchFileListReq req = Diztl.FetchFileListReq.newBuilder().setFile(file).setNode(node).build();
    Diztl.FetchFileListResp resp = connection.getStub().fetchFileList(req);
    System.out.println(resp);
    return resp.getFilesList();
  }
}
