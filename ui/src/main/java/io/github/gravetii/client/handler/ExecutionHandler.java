package io.github.gravetii.client.handler;

import javafx.concurrent.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutionHandler {
  private static final Logger logger =
      LoggerFactory.getLogger(ExecutionHandler.class.getCanonicalName());

  private static final ExecutorService EXECUTOR =
      Executors.newFixedThreadPool(
          3,
          (r) -> {
            Thread thread = new Thread(r);
            thread.setDaemon(true);
            return thread;
          });

  public static void submit(Task<Void> task) {
    EXECUTOR.execute(task);
  }

  public static ExecutorService get() {
    return EXECUTOR;
  }

  public static void shutdown() {
    EXECUTOR.shutdown();
    try {
      boolean terminated = EXECUTOR.awaitTermination(5, TimeUnit.SECONDS);
      if (!terminated) {
        EXECUTOR.shutdownNow();
      }
    } catch (Exception e) {
      logger.error("Exception while shutting down execution handler", e);
    }
  }
}
