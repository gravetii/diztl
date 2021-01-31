package io.github.gravetii.util;

import javafx.concurrent.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class DiztlExecutorService {

  private static final Logger logger =
      LoggerFactory.getLogger(DiztlExecutorService.class.getCanonicalName());

  private static final int NUM_THREADS = 5;

  public static final ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);

  public static void close() {
    try {
      executor.shutdown();
      boolean closed = executor.awaitTermination(5, TimeUnit.SECONDS);
      if (!closed) executor.shutdownNow();
    } catch (Exception e) {
      logger.error("Error while shutting down executor service", e);
    }
  }

  public static void execute(Task<?> task) {
    executor.execute(task);
  }
}
