// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: diztl.proto

package io.github.gravetii.grpc;

public interface RegisterReqOrBuilder extends
    // @@protoc_insertion_point(interface_extends:RegisterReq)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * Register self to the tracker.
   * </pre>
   *
   * <code>.Node self = 1;</code>
   */
  boolean hasSelf();
  /**
   * <pre>
   * Register self to the tracker.
   * </pre>
   *
   * <code>.Node self = 1;</code>
   */
  io.github.gravetii.grpc.Node getSelf();
  /**
   * <pre>
   * Register self to the tracker.
   * </pre>
   *
   * <code>.Node self = 1;</code>
   */
  io.github.gravetii.grpc.NodeOrBuilder getSelfOrBuilder();

  /**
   * <code>string tracker = 2;</code>
   */
  java.lang.String getTracker();
  /**
   * <code>string tracker = 2;</code>
   */
  com.google.protobuf.ByteString
      getTrackerBytes();
}
