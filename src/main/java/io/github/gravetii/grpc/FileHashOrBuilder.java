// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: diztl.proto

package io.github.gravetii.grpc;

public interface FileHashOrBuilder extends
    // @@protoc_insertion_point(interface_extends:FileHash)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * The checksum of the file.
   * </pre>
   *
   * <code>bytes checksum = 1;</code>
   */
  com.google.protobuf.ByteString getChecksum();

  /**
   * <pre>
   * The timestamp at which the file's checksum was calculated.
   * </pre>
   *
   * <code>int64 ts = 2;</code>
   */
  long getTs();
}