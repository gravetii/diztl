package io.github.gravetii.client.handler;

import com.google.common.util.concurrent.ListenableFuture;
import io.github.gravetii.client.connection.Connection;
import io.github.gravetii.controller.start.FileResult;
import io.github.gravetii.gen.Diztl;
import io.github.gravetii.scene.start.ResultListComponent;
import io.github.gravetii.scene.start.StartScene;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetFileListHandler {
  private static final Logger logger = LoggerFactory.getLogger(GetFileListHandler.class.getCanonicalName());

  private StartScene scene;
  private Diztl.Node node;
  private Diztl.FileMetadata file;

  public GetFileListHandler(StartScene scene, Diztl.Node node, Diztl.FileMetadata file) {
    this.scene = scene;
    this.node = node;
    this.file = file;
  }

  public void process(Connection connection) {
    ResultListComponent component = scene.addNewFileListTab(file);
    Diztl.FetchFileListReq req = Diztl.FetchFileListReq.newBuilder().setFile(file).setNode(node).build();
    ListenableFuture<Diztl.FetchFileListResp> f = connection.getFutureStub().fetchFileList(req);
    f.addListener(() -> {
      try {
        Diztl.FetchFileListResp resp = f.get();
        resp.getFilesList().forEach(
                r -> {
                  component.getController().show(new FileResult(r, node));
                }
        );
      } catch (Exception e) {
        logger.error("Error while getting file list from node {} -", node.getIp(), e);
      }
    }, ExecutionHandler.get());
  }
}
