package io.github.gravetii.client.handler;

import com.google.common.util.concurrent.ListenableFuture;
import io.github.gravetii.client.connection.CommunicationClient;
import io.github.gravetii.client.connection.Connection;
import io.github.gravetii.controller.start.FileResult;
import io.github.gravetii.gen.Diztl;
import io.github.gravetii.pojo.UserDirs;
import io.github.gravetii.scene.start.ResultListComponent;
import io.github.gravetii.scene.start.StartScene;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DownloadFileListHandler {
  private static final Logger logger = LoggerFactory.getLogger(DownloadFileListHandler.class.getCanonicalName());

  private StartScene scene;
  private Diztl.Node node;
  private Diztl.FileMetadata file;

  public DownloadFileListHandler(StartScene scene, Diztl.Node node, Diztl.FileMetadata file) {
    this.scene = scene;
    this.node = node;
    this.file = file;
  }

  private String getDownloadsFolder() {
    UserDirs dirs = CommunicationClient.get().getUserDirs();
    String out = dirs.getDownloadsDir();
    File dir = new File(file.getDir());
    System.out.println("Got downloads folder as: " + Paths.get(out, dir.getName()).toString());
    return Paths.get(out, dir.getName()).toString();
  }

  public void process(Connection connection) {
    Diztl.FetchFileListReq req = Diztl.FetchFileListReq.newBuilder().setFile(file).setNode(node).build();
    ListenableFuture<Diztl.FetchFileListResp> f = connection.getFutureStub().fetchFileList(req);
    String downloadsDir = getDownloadsFolder();
    f.addListener(() -> {
      try {
        Diztl.FetchFileListResp resp = f.get();
        resp.getFilesList().forEach(
                r -> {
                  CommunicationClient.get().download(r, node, scene, downloadsDir);
                }
        );
      } catch (Exception e) {
        logger.error("Error while downloading file list from node {} -", node.getIp(), e);
      }
    }, ExecutionHandler.get());
  }
}
