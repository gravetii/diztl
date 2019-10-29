package io.github.gravetii.client.connection;

import io.github.gravetii.client.handler.*;
import io.github.gravetii.gen.Diztl;
import io.github.gravetii.gen.Diztl.FileMetadata;
import io.github.gravetii.gen.Diztl.Node;
import io.github.gravetii.pojo.UserDirs;
import io.github.gravetii.scene.start.StartScene;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommunicationClient {
  private static final Logger logger =
      LoggerFactory.getLogger(CommunicationClient.class.getCanonicalName());

  private static volatile CommunicationClient INSTANCE = null;
  private Connection connection;

  private CommunicationClient() {
    ManagedChannel channel =
        ManagedChannelBuilder.forAddress("127.0.0.1", 50051).usePlaintext().build();
    this.connection = new Connection(channel);
    Runtime.getRuntime()
        .addShutdownHook(
            new Thread(
                () -> {
                  connection.close();
                  ExecutionHandler.shutdown();
                }));
  }

  public static CommunicationClient get() {
    if (INSTANCE == null) {
      synchronized (CommunicationClient.class) {
        if (INSTANCE == null) {
          INSTANCE = new CommunicationClient();
        }
      }
    }

    return INSTANCE;
  }

  public void find(String query, Diztl.FileConstraint constraint, StartScene scene) {
    new FindHandler(scene, query, constraint).process(connection);
  }

  public void download(FileMetadata file, Node source, StartScene scene) {
    UserDirs dirs = CommunicationClient.get().getUserDirs();
    new DownloadHandler(file, source, scene, dirs.getDownloadsDir()).process(connection);
  }

  public void download(FileMetadata file, Node source, StartScene scene, String downloadsDir) {
    new DownloadHandler(file, source, scene, downloadsDir).process(connection);
  }

  public UserDirs getUserDirs() {
    return new UserDirsHandler().process(connection);
  }

  public String getTracker() {
    return new GetTrackerHandler().process(connection);
  }

  public void updateTracker(String host, StartScene scene) {
    new UpdateTrackerHandler(host, scene).process(connection);
  }

  public void getFileList(StartScene scene, Diztl.Node node, FileMetadata file) {
    new GetFileListHandler(scene, node, file).process(connection);
  }

  public void downloadFileList(StartScene scene, Diztl.Node node, Diztl.FileMetadata file) {
    new DownloadFileListHandler(scene, node, file).process(connection);
  }
}
