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

  private static CommunicationClient INSTANCE = null;
  private Connection connection;

  private CommunicationClient() throws Exception {
    ManagedChannel channel =
        ManagedChannelBuilder.forAddress("127.0.0.1", 50051).usePlaintext().build();
    this.connection = new Connection(channel);
    Runtime.getRuntime()
        .addShutdownHook(
            new Thread(
                () -> {
                  ExecutionHandler.shutdown();
                  connection.close();
                }));
  }

  public static void init() throws Exception {
    logger.info("Initializing communication client.");
    INSTANCE = new CommunicationClient();
    INSTANCE.getUserDirs(true, true);
  }

  public static CommunicationClient get() {
    return INSTANCE;
  }

  public void find(String pattern, StartScene scene) {
    new FindHandler(scene, pattern).process(connection);
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

  public void updateUserDirs(List<String> share, String output) {
    new UpdateUserDirsHandler(share, output).process(connection);
  }

  public void index(StartScene scene) {
    new FileIndexHandler(scene).process(connection);
  }
}
