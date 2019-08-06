package io.github.gravetii.client.handler;

import com.google.common.util.concurrent.ListenableFuture;
import io.github.gravetii.client.connection.CommunicationClient;
import io.github.gravetii.client.connection.Connection;
import io.github.gravetii.gen.Diztl;
import io.github.gravetii.scene.start.StartScene;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Paths;

public class DownloadFileListHandler {
  private static final Logger logger =
      LoggerFactory.getLogger(DownloadFileListHandler.class.getCanonicalName());

  private StartScene scene;
  private Diztl.Node node;
  private Diztl.FileMetadata file;

  public DownloadFileListHandler(StartScene scene, Diztl.Node node, Diztl.FileMetadata file) {
    this.scene = scene;
    this.node = node;
    this.file = file;
  }

  private String getDownloadsFolder(Diztl.FileMetadata r) {
    String baseDir = CommunicationClient.get().getUserDirs().getDownloadsDir();
    String path = "";
    if (r.getDir().equals(file.getDir())) {
      path = Paths.get(baseDir, FilenameUtils.getName(file.getDir())).toString();
    } else {
      int idx = r.getDir().indexOf(file.getDir());
      String remPath = r.getDir().substring(1 + idx + file.getDir().length());
      path =
          Paths.get(baseDir, FilenameUtils.getName(file.getDir()), FilenameUtils.normalize(remPath))
              .toString();
    }

    return path;
  }

  public void process(Connection connection) {
    Diztl.FetchFileListReq req =
        Diztl.FetchFileListReq.newBuilder().setDir(file.getDir()).setNode(node).build();
    ListenableFuture<Diztl.FetchFileListResp> f = connection.getFutureStub().fetchFileList(req);
    f.addListener(
        () -> {
          try {
            Diztl.FetchFileListResp resp = f.get();
            resp.getFilesList()
                .forEach(
                    r -> {
                      String dir = getDownloadsFolder(r);
                      CommunicationClient.get().download(r, node, scene, dir);
                    });
          } catch (Exception e) {
            logger.error("Error while downloading file list from node {} -", node.getIp(), e);
          }
        },
        ExecutionHandler.get());
  }
}
