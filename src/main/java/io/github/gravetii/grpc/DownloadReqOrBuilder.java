// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: diztl.proto

package io.github.gravetii.grpc;

public interface DownloadReqOrBuilder extends
    // @@protoc_insertion_point(interface_extends:DownloadReq)
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
   * <code>.FileMetadata file = 2;</code>
   */
  boolean hasFile();
  /**
   * <code>.FileMetadata file = 2;</code>
   */
  io.github.gravetii.grpc.FileMetadata getFile();
  /**
   * <code>.FileMetadata file = 2;</code>
   */
  io.github.gravetii.grpc.FileMetadataOrBuilder getFileOrBuilder();

  /**
   * <pre>
   * the custom downloads directory, if chosen.
   * </pre>
   *
   * <code>string dir = 3;</code>
   */
  java.lang.String getDir();
  /**
   * <pre>
   * the custom downloads directory, if chosen.
   * </pre>
   *
   * <code>string dir = 3;</code>
   */
  com.google.protobuf.ByteString
      getDirBytes();
}
