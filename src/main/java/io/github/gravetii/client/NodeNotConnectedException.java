package io.github.gravetii.client;

public class NodeNotConnectedException extends Exception {

  public NodeNotConnectedException() {
    super("node isn't connected to the tracker");
  }

  public NodeNotConnectedException(Throwable cause) {
    super("node isn't connected to the tracker", cause);
  }
}
