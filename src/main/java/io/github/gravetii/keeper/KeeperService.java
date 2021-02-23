package io.github.gravetii.keeper;

import io.github.gravetii.grpc.Node;
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

  private final Map<Node, DiztlConnection> connections;

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

  private DiztlConnection newConnection(Node node) {
    ManagedChannelBuilder<?> builder = ManagedChannelBuilder.forAddress(node.getIp(), 50035);
    ManagedChannel channel = builder.usePlaintext().build();
    return new DiztlConnection(channel);
  }

  public DiztlConnection getOrCreate(Node node) {
    if (connections.containsKey(node)) return connections.get(node);
    DiztlConnection connection = newConnection(node);
    connections.put(node, connection);
    return connection;
  }

  public Node register(Node node) {
    if (connections.containsKey(node)) logger.warn("Node {} already in connections", node);
    UUID uuid = UUID.randomUUID();
    Node result = Node.newBuilder().setId(uuid.toString()).setIp(node.getIp()).build();
    DiztlConnection connection = newConnection(result);
    connections.put(result, connection);
    return result;
  }

  public boolean disconnect(Node node) {
    DiztlConnection connection = connections.remove(node);
    return connection != null;
  }

  public Map<Node, DiztlConnection> nodes() {
    return this.connections;
  }
}
