// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: diztl.proto

package io.github.gravetii.grpc;

public interface PingReqOrBuilder extends
    // @@protoc_insertion_point(interface_extends:PingReq)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>.Node source = 1;</code>
   */
  boolean hasSource();
  /**
   * <code>.Node source = 1;</code>
   */
  io.github.gravetii.grpc.Node getSource();
  /**
   * <code>.Node source = 1;</code>
   */
  io.github.gravetii.grpc.NodeOrBuilder getSourceOrBuilder();

  /**
   * <code>.Node dest = 2;</code>
   */
  boolean hasDest();
  /**
   * <code>.Node dest = 2;</code>
   */
  io.github.gravetii.grpc.Node getDest();
  /**
   * <code>.Node dest = 2;</code>
   */
  io.github.gravetii.grpc.NodeOrBuilder getDestOrBuilder();
}
