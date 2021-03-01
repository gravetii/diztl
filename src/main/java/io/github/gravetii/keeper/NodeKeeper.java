package io.github.gravetii.keeper;

import com.google.inject.Singleton;
import io.github.gravetii.grpc.Node;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Singleton
public class NodeKeeper {

  static class NodeData {
    private final Node node;
    private final DiztlConnection connection;

    NodeData(Node node, DiztlConnection connection) {
      this.node = node;
      this.connection = connection;
    }
  }

  private static final Logger logger = LoggerFactory.getLogger(NodeKeeper.class.getCanonicalName());

  private final Map<String, NodeData> connections = new ConcurrentHashMap<>();

  public NodeKeeper() {}

  private DiztlConnection newConnection(Node node) {
    ManagedChannelBuilder<?> builder = ManagedChannelBuilder.forAddress(node.getIp(), 50035);
    ManagedChannel channel = builder.usePlaintext().build();
    return new DiztlConnection(channel);
  }

  public DiztlConnection getOrCreate(Node node) {
    if (connections.containsKey(node.getIp())) return connections.get(node.getIp()).connection;
    DiztlConnection connection = newConnection(node);
    connections.put(node.getIp(), new NodeData(node, connection));
    return connection;
  }

  public Node register(Node node) {
    if (connections.containsKey(node.getIp())) {
      logger.warn("Node {} already in connections", node.getIp());
      return connections.get(node.getIp()).node;
    }

    UUID uuid = UUID.randomUUID();
    Node result = Node.newBuilder().setId(uuid.toString()).setIp(node.getIp()).build();
    DiztlConnection connection = newConnection(result);
    connections.put(result.getIp(), new NodeData(result, connection));
    return result;
  }

  public boolean disconnect(Node node) {
    NodeData data = connections.remove(node.getIp());
    if (data != null) {
      data.connection.close();
      return true;
    }

    return false;
  }

  public Map<Node, DiztlConnection> nodes() {
    return connections.values().stream().collect(Collectors.toMap(x -> x.node, x -> x.connection));
  }

  public void close() {
    connections.forEach((x, y) -> y.connection.close());
  }
}
