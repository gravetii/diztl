package io.github.gravetii.keeper;

import io.github.gravetii.grpc.Diztl;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class KeeperService {

  private static final Logger logger =
      LoggerFactory.getLogger(KeeperService.class.getCanonicalName());

  private final Map<Diztl.Node, DiztlConnection> connections;

  private static volatile KeeperService instance = null;

  public static KeeperService get() {
    if (instance == null) {
      synchronized (KeeperService.class) {
        if (instance == null) instance = new KeeperService();
      }
    }

    return instance;
  }

  private KeeperService() {
    this.connections = new ConcurrentHashMap<>();
  }

  private DiztlConnection newConnection(Diztl.Node node) {
    ManagedChannelBuilder<?> builder = ManagedChannelBuilder.forAddress(node.getIp(), 50035);
    ManagedChannel channel = builder.usePlaintext().build();
    return new DiztlConnection(channel);
  }

  public DiztlConnection getOrCreate(Diztl.Node node) {
    if (connections.containsKey(node)) return connections.get(node);
    DiztlConnection connection = newConnection(node);
    connections.put(node, connection);
    return connection;
  }

  public Diztl.Node register(Diztl.Node node) {
    if (connections.containsKey(node)) logger.warn("Node {} already in connections", node);
    UUID uuid = UUID.randomUUID();
    Diztl.Node result = Diztl.Node.newBuilder().setId(uuid.toString()).setIp(node.getIp()).build();
    DiztlConnection connection = newConnection(result);
    connections.put(result, connection);
    return result;
  }

  public boolean disconnect(Diztl.Node node) {
    DiztlConnection connection = connections.remove(node);
    return connection != null;
  }

  public Map<Diztl.Node, DiztlConnection> nodes() {
    return this.connections;
  }
}
