package io.github.gravetii.client.connection;

import io.github.gravetii.AppContext;
import io.github.gravetii.client.handler.*;
import io.github.gravetii.gen.Diztl.FileMetadata;
import io.github.gravetii.gen.Diztl.Node;
import io.github.gravetii.scene.start.StartScene;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

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

  public void register(StartScene scene) {
    new RegisterHandler(scene).process(connection);
  }

  public void find(String query, StartScene scene) {
    new FindHandler(scene, query).process(connection);
  }

  public void download(FileMetadata file, Node source, StartScene scene) {
    new DownloadHandler(file, source, scene, AppContext.getOutputDir()).process(connection);
  }

  public void download(FileMetadata file, Node source, StartScene scene, String outputDir) {
    new DownloadHandler(file, source, scene, outputDir).process(connection);
  }

  public void getUserDirs(boolean share, boolean output) {
    new UserDirsHandler(share, output).process(connection);
  }

  public void updateUserDirs(List<String> share, String output, StartScene scene) {
    new UpdateUserDirsHandler(share, output, scene).process(connection);
  }

  public void updateTracker(String host, StartScene scene) {
    new UpdateTrackerHandler(host, scene).process(connection);
  }

  public void index(StartScene scene) {
    getUserDirs(true, true);
    new FileIndexHandler(scene).process(connection);
  }
}
