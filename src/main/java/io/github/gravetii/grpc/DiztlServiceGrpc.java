package io.github.gravetii.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.34.1)",
    comments = "Source: diztl.proto")
public final class DiztlServiceGrpc {

  private DiztlServiceGrpc() {}

  public static final String SERVICE_NAME = "DiztlService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<io.github.gravetii.grpc.SearchReq,
      io.github.gravetii.grpc.SearchResp> getSearchMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Search",
      requestType = io.github.gravetii.grpc.SearchReq.class,
      responseType = io.github.gravetii.grpc.SearchResp.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.github.gravetii.grpc.SearchReq,
      io.github.gravetii.grpc.SearchResp> getSearchMethod() {
    io.grpc.MethodDescriptor<io.github.gravetii.grpc.SearchReq, io.github.gravetii.grpc.SearchResp> getSearchMethod;
    if ((getSearchMethod = DiztlServiceGrpc.getSearchMethod) == null) {
      synchronized (DiztlServiceGrpc.class) {
        if ((getSearchMethod = DiztlServiceGrpc.getSearchMethod) == null) {
          DiztlServiceGrpc.getSearchMethod = getSearchMethod =
              io.grpc.MethodDescriptor.<io.github.gravetii.grpc.SearchReq, io.github.gravetii.grpc.SearchResp>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Search"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.github.gravetii.grpc.SearchReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.github.gravetii.grpc.SearchResp.getDefaultInstance()))
              .setSchemaDescriptor(new DiztlServiceMethodDescriptorSupplier("Search"))
              .build();
        }
      }
    }
    return getSearchMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.github.gravetii.grpc.UploadReq,
      io.github.gravetii.grpc.FileChunk> getUploadMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Upload",
      requestType = io.github.gravetii.grpc.UploadReq.class,
      responseType = io.github.gravetii.grpc.FileChunk.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<io.github.gravetii.grpc.UploadReq,
      io.github.gravetii.grpc.FileChunk> getUploadMethod() {
    io.grpc.MethodDescriptor<io.github.gravetii.grpc.UploadReq, io.github.gravetii.grpc.FileChunk> getUploadMethod;
    if ((getUploadMethod = DiztlServiceGrpc.getUploadMethod) == null) {
      synchronized (DiztlServiceGrpc.class) {
        if ((getUploadMethod = DiztlServiceGrpc.getUploadMethod) == null) {
          DiztlServiceGrpc.getUploadMethod = getUploadMethod =
              io.grpc.MethodDescriptor.<io.github.gravetii.grpc.UploadReq, io.github.gravetii.grpc.FileChunk>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Upload"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.github.gravetii.grpc.UploadReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.github.gravetii.grpc.FileChunk.getDefaultInstance()))
              .setSchemaDescriptor(new DiztlServiceMethodDescriptorSupplier("Upload"))
              .build();
        }
      }
    }
    return getUploadMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.github.gravetii.grpc.PingReq,
      io.github.gravetii.grpc.PingResp> getPingMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Ping",
      requestType = io.github.gravetii.grpc.PingReq.class,
      responseType = io.github.gravetii.grpc.PingResp.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.github.gravetii.grpc.PingReq,
      io.github.gravetii.grpc.PingResp> getPingMethod() {
    io.grpc.MethodDescriptor<io.github.gravetii.grpc.PingReq, io.github.gravetii.grpc.PingResp> getPingMethod;
    if ((getPingMethod = DiztlServiceGrpc.getPingMethod) == null) {
      synchronized (DiztlServiceGrpc.class) {
        if ((getPingMethod = DiztlServiceGrpc.getPingMethod) == null) {
          DiztlServiceGrpc.getPingMethod = getPingMethod =
              io.grpc.MethodDescriptor.<io.github.gravetii.grpc.PingReq, io.github.gravetii.grpc.PingResp>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Ping"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.github.gravetii.grpc.PingReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.github.gravetii.grpc.PingResp.getDefaultInstance()))
              .setSchemaDescriptor(new DiztlServiceMethodDescriptorSupplier("Ping"))
              .build();
        }
      }
    }
    return getPingMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.github.gravetii.grpc.FindReq,
      io.github.gravetii.grpc.FindResp> getFindMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Find",
      requestType = io.github.gravetii.grpc.FindReq.class,
      responseType = io.github.gravetii.grpc.FindResp.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.github.gravetii.grpc.FindReq,
      io.github.gravetii.grpc.FindResp> getFindMethod() {
    io.grpc.MethodDescriptor<io.github.gravetii.grpc.FindReq, io.github.gravetii.grpc.FindResp> getFindMethod;
    if ((getFindMethod = DiztlServiceGrpc.getFindMethod) == null) {
      synchronized (DiztlServiceGrpc.class) {
        if ((getFindMethod = DiztlServiceGrpc.getFindMethod) == null) {
          DiztlServiceGrpc.getFindMethod = getFindMethod =
              io.grpc.MethodDescriptor.<io.github.gravetii.grpc.FindReq, io.github.gravetii.grpc.FindResp>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Find"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.github.gravetii.grpc.FindReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.github.gravetii.grpc.FindResp.getDefaultInstance()))
              .setSchemaDescriptor(new DiztlServiceMethodDescriptorSupplier("Find"))
              .build();
        }
      }
    }
    return getFindMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.github.gravetii.grpc.DownloadReq,
      io.github.gravetii.grpc.DownloadChunk> getDownloadMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Download",
      requestType = io.github.gravetii.grpc.DownloadReq.class,
      responseType = io.github.gravetii.grpc.DownloadChunk.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<io.github.gravetii.grpc.DownloadReq,
      io.github.gravetii.grpc.DownloadChunk> getDownloadMethod() {
    io.grpc.MethodDescriptor<io.github.gravetii.grpc.DownloadReq, io.github.gravetii.grpc.DownloadChunk> getDownloadMethod;
    if ((getDownloadMethod = DiztlServiceGrpc.getDownloadMethod) == null) {
      synchronized (DiztlServiceGrpc.class) {
        if ((getDownloadMethod = DiztlServiceGrpc.getDownloadMethod) == null) {
          DiztlServiceGrpc.getDownloadMethod = getDownloadMethod =
              io.grpc.MethodDescriptor.<io.github.gravetii.grpc.DownloadReq, io.github.gravetii.grpc.DownloadChunk>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Download"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.github.gravetii.grpc.DownloadReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.github.gravetii.grpc.DownloadChunk.getDefaultInstance()))
              .setSchemaDescriptor(new DiztlServiceMethodDescriptorSupplier("Download"))
              .build();
        }
      }
    }
    return getDownloadMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.github.gravetii.grpc.UserDirsReq,
      io.github.gravetii.grpc.UserDirsResp> getGetUserDirsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetUserDirs",
      requestType = io.github.gravetii.grpc.UserDirsReq.class,
      responseType = io.github.gravetii.grpc.UserDirsResp.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.github.gravetii.grpc.UserDirsReq,
      io.github.gravetii.grpc.UserDirsResp> getGetUserDirsMethod() {
    io.grpc.MethodDescriptor<io.github.gravetii.grpc.UserDirsReq, io.github.gravetii.grpc.UserDirsResp> getGetUserDirsMethod;
    if ((getGetUserDirsMethod = DiztlServiceGrpc.getGetUserDirsMethod) == null) {
      synchronized (DiztlServiceGrpc.class) {
        if ((getGetUserDirsMethod = DiztlServiceGrpc.getGetUserDirsMethod) == null) {
          DiztlServiceGrpc.getGetUserDirsMethod = getGetUserDirsMethod =
              io.grpc.MethodDescriptor.<io.github.gravetii.grpc.UserDirsReq, io.github.gravetii.grpc.UserDirsResp>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetUserDirs"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.github.gravetii.grpc.UserDirsReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.github.gravetii.grpc.UserDirsResp.getDefaultInstance()))
              .setSchemaDescriptor(new DiztlServiceMethodDescriptorSupplier("GetUserDirs"))
              .build();
        }
      }
    }
    return getGetUserDirsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.github.gravetii.grpc.UpdateUserDirsReq,
      io.github.gravetii.grpc.UpdateUserDirsResp> getUpdateUserDirsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpdateUserDirs",
      requestType = io.github.gravetii.grpc.UpdateUserDirsReq.class,
      responseType = io.github.gravetii.grpc.UpdateUserDirsResp.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.github.gravetii.grpc.UpdateUserDirsReq,
      io.github.gravetii.grpc.UpdateUserDirsResp> getUpdateUserDirsMethod() {
    io.grpc.MethodDescriptor<io.github.gravetii.grpc.UpdateUserDirsReq, io.github.gravetii.grpc.UpdateUserDirsResp> getUpdateUserDirsMethod;
    if ((getUpdateUserDirsMethod = DiztlServiceGrpc.getUpdateUserDirsMethod) == null) {
      synchronized (DiztlServiceGrpc.class) {
        if ((getUpdateUserDirsMethod = DiztlServiceGrpc.getUpdateUserDirsMethod) == null) {
          DiztlServiceGrpc.getUpdateUserDirsMethod = getUpdateUserDirsMethod =
              io.grpc.MethodDescriptor.<io.github.gravetii.grpc.UpdateUserDirsReq, io.github.gravetii.grpc.UpdateUserDirsResp>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpdateUserDirs"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.github.gravetii.grpc.UpdateUserDirsReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.github.gravetii.grpc.UpdateUserDirsResp.getDefaultInstance()))
              .setSchemaDescriptor(new DiztlServiceMethodDescriptorSupplier("UpdateUserDirs"))
              .build();
        }
      }
    }
    return getUpdateUserDirsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.github.gravetii.grpc.GetTrackerReq,
      io.github.gravetii.grpc.GetTrackerResp> getGetTrackerMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetTracker",
      requestType = io.github.gravetii.grpc.GetTrackerReq.class,
      responseType = io.github.gravetii.grpc.GetTrackerResp.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.github.gravetii.grpc.GetTrackerReq,
      io.github.gravetii.grpc.GetTrackerResp> getGetTrackerMethod() {
    io.grpc.MethodDescriptor<io.github.gravetii.grpc.GetTrackerReq, io.github.gravetii.grpc.GetTrackerResp> getGetTrackerMethod;
    if ((getGetTrackerMethod = DiztlServiceGrpc.getGetTrackerMethod) == null) {
      synchronized (DiztlServiceGrpc.class) {
        if ((getGetTrackerMethod = DiztlServiceGrpc.getGetTrackerMethod) == null) {
          DiztlServiceGrpc.getGetTrackerMethod = getGetTrackerMethod =
              io.grpc.MethodDescriptor.<io.github.gravetii.grpc.GetTrackerReq, io.github.gravetii.grpc.GetTrackerResp>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetTracker"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.github.gravetii.grpc.GetTrackerReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.github.gravetii.grpc.GetTrackerResp.getDefaultInstance()))
              .setSchemaDescriptor(new DiztlServiceMethodDescriptorSupplier("GetTracker"))
              .build();
        }
      }
    }
    return getGetTrackerMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.github.gravetii.grpc.UpdateTrackerReq,
      io.github.gravetii.grpc.UpdateTrackerResp> getUpdateTrackerMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpdateTracker",
      requestType = io.github.gravetii.grpc.UpdateTrackerReq.class,
      responseType = io.github.gravetii.grpc.UpdateTrackerResp.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.github.gravetii.grpc.UpdateTrackerReq,
      io.github.gravetii.grpc.UpdateTrackerResp> getUpdateTrackerMethod() {
    io.grpc.MethodDescriptor<io.github.gravetii.grpc.UpdateTrackerReq, io.github.gravetii.grpc.UpdateTrackerResp> getUpdateTrackerMethod;
    if ((getUpdateTrackerMethod = DiztlServiceGrpc.getUpdateTrackerMethod) == null) {
      synchronized (DiztlServiceGrpc.class) {
        if ((getUpdateTrackerMethod = DiztlServiceGrpc.getUpdateTrackerMethod) == null) {
          DiztlServiceGrpc.getUpdateTrackerMethod = getUpdateTrackerMethod =
              io.grpc.MethodDescriptor.<io.github.gravetii.grpc.UpdateTrackerReq, io.github.gravetii.grpc.UpdateTrackerResp>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpdateTracker"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.github.gravetii.grpc.UpdateTrackerReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.github.gravetii.grpc.UpdateTrackerResp.getDefaultInstance()))
              .setSchemaDescriptor(new DiztlServiceMethodDescriptorSupplier("UpdateTracker"))
              .build();
        }
      }
    }
    return getUpdateTrackerMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.github.gravetii.grpc.IndexReq,
      io.github.gravetii.grpc.IndexResp> getIndexMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Index",
      requestType = io.github.gravetii.grpc.IndexReq.class,
      responseType = io.github.gravetii.grpc.IndexResp.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<io.github.gravetii.grpc.IndexReq,
      io.github.gravetii.grpc.IndexResp> getIndexMethod() {
    io.grpc.MethodDescriptor<io.github.gravetii.grpc.IndexReq, io.github.gravetii.grpc.IndexResp> getIndexMethod;
    if ((getIndexMethod = DiztlServiceGrpc.getIndexMethod) == null) {
      synchronized (DiztlServiceGrpc.class) {
        if ((getIndexMethod = DiztlServiceGrpc.getIndexMethod) == null) {
          DiztlServiceGrpc.getIndexMethod = getIndexMethod =
              io.grpc.MethodDescriptor.<io.github.gravetii.grpc.IndexReq, io.github.gravetii.grpc.IndexResp>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Index"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.github.gravetii.grpc.IndexReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.github.gravetii.grpc.IndexResp.getDefaultInstance()))
              .setSchemaDescriptor(new DiztlServiceMethodDescriptorSupplier("Index"))
              .build();
        }
      }
    }
    return getIndexMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.github.gravetii.grpc.RegisterReq,
      io.github.gravetii.grpc.RegisterResp> getRegisterMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Register",
      requestType = io.github.gravetii.grpc.RegisterReq.class,
      responseType = io.github.gravetii.grpc.RegisterResp.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.github.gravetii.grpc.RegisterReq,
      io.github.gravetii.grpc.RegisterResp> getRegisterMethod() {
    io.grpc.MethodDescriptor<io.github.gravetii.grpc.RegisterReq, io.github.gravetii.grpc.RegisterResp> getRegisterMethod;
    if ((getRegisterMethod = DiztlServiceGrpc.getRegisterMethod) == null) {
      synchronized (DiztlServiceGrpc.class) {
        if ((getRegisterMethod = DiztlServiceGrpc.getRegisterMethod) == null) {
          DiztlServiceGrpc.getRegisterMethod = getRegisterMethod =
              io.grpc.MethodDescriptor.<io.github.gravetii.grpc.RegisterReq, io.github.gravetii.grpc.RegisterResp>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Register"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.github.gravetii.grpc.RegisterReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.github.gravetii.grpc.RegisterResp.getDefaultInstance()))
              .setSchemaDescriptor(new DiztlServiceMethodDescriptorSupplier("Register"))
              .build();
        }
      }
    }
    return getRegisterMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.github.gravetii.grpc.FetchFileListReq,
      io.github.gravetii.grpc.FetchFileListResp> getFetchFileListMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "FetchFileList",
      requestType = io.github.gravetii.grpc.FetchFileListReq.class,
      responseType = io.github.gravetii.grpc.FetchFileListResp.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.github.gravetii.grpc.FetchFileListReq,
      io.github.gravetii.grpc.FetchFileListResp> getFetchFileListMethod() {
    io.grpc.MethodDescriptor<io.github.gravetii.grpc.FetchFileListReq, io.github.gravetii.grpc.FetchFileListResp> getFetchFileListMethod;
    if ((getFetchFileListMethod = DiztlServiceGrpc.getFetchFileListMethod) == null) {
      synchronized (DiztlServiceGrpc.class) {
        if ((getFetchFileListMethod = DiztlServiceGrpc.getFetchFileListMethod) == null) {
          DiztlServiceGrpc.getFetchFileListMethod = getFetchFileListMethod =
              io.grpc.MethodDescriptor.<io.github.gravetii.grpc.FetchFileListReq, io.github.gravetii.grpc.FetchFileListResp>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "FetchFileList"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.github.gravetii.grpc.FetchFileListReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.github.gravetii.grpc.FetchFileListResp.getDefaultInstance()))
              .setSchemaDescriptor(new DiztlServiceMethodDescriptorSupplier("FetchFileList"))
              .build();
        }
      }
    }
    return getFetchFileListMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.github.gravetii.grpc.GetFileListReq,
      io.github.gravetii.grpc.GetFileListResp> getGetFileListMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetFileList",
      requestType = io.github.gravetii.grpc.GetFileListReq.class,
      responseType = io.github.gravetii.grpc.GetFileListResp.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.github.gravetii.grpc.GetFileListReq,
      io.github.gravetii.grpc.GetFileListResp> getGetFileListMethod() {
    io.grpc.MethodDescriptor<io.github.gravetii.grpc.GetFileListReq, io.github.gravetii.grpc.GetFileListResp> getGetFileListMethod;
    if ((getGetFileListMethod = DiztlServiceGrpc.getGetFileListMethod) == null) {
      synchronized (DiztlServiceGrpc.class) {
        if ((getGetFileListMethod = DiztlServiceGrpc.getGetFileListMethod) == null) {
          DiztlServiceGrpc.getGetFileListMethod = getGetFileListMethod =
              io.grpc.MethodDescriptor.<io.github.gravetii.grpc.GetFileListReq, io.github.gravetii.grpc.GetFileListResp>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetFileList"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.github.gravetii.grpc.GetFileListReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.github.gravetii.grpc.GetFileListResp.getDefaultInstance()))
              .setSchemaDescriptor(new DiztlServiceMethodDescriptorSupplier("GetFileList"))
              .build();
        }
      }
    }
    return getGetFileListMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.github.gravetii.grpc.CloseReq,
      io.github.gravetii.grpc.CloseResp> getCloseMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Close",
      requestType = io.github.gravetii.grpc.CloseReq.class,
      responseType = io.github.gravetii.grpc.CloseResp.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.github.gravetii.grpc.CloseReq,
      io.github.gravetii.grpc.CloseResp> getCloseMethod() {
    io.grpc.MethodDescriptor<io.github.gravetii.grpc.CloseReq, io.github.gravetii.grpc.CloseResp> getCloseMethod;
    if ((getCloseMethod = DiztlServiceGrpc.getCloseMethod) == null) {
      synchronized (DiztlServiceGrpc.class) {
        if ((getCloseMethod = DiztlServiceGrpc.getCloseMethod) == null) {
          DiztlServiceGrpc.getCloseMethod = getCloseMethod =
              io.grpc.MethodDescriptor.<io.github.gravetii.grpc.CloseReq, io.github.gravetii.grpc.CloseResp>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Close"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.github.gravetii.grpc.CloseReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.github.gravetii.grpc.CloseResp.getDefaultInstance()))
              .setSchemaDescriptor(new DiztlServiceMethodDescriptorSupplier("Close"))
              .build();
        }
      }
    }
    return getCloseMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static DiztlServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<DiztlServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<DiztlServiceStub>() {
        @java.lang.Override
        public DiztlServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new DiztlServiceStub(channel, callOptions);
        }
      };
    return DiztlServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static DiztlServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<DiztlServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<DiztlServiceBlockingStub>() {
        @java.lang.Override
        public DiztlServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new DiztlServiceBlockingStub(channel, callOptions);
        }
      };
    return DiztlServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static DiztlServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<DiztlServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<DiztlServiceFutureStub>() {
        @java.lang.Override
        public DiztlServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new DiztlServiceFutureStub(channel, callOptions);
        }
      };
    return DiztlServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class DiztlServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void search(io.github.gravetii.grpc.SearchReq request,
        io.grpc.stub.StreamObserver<io.github.gravetii.grpc.SearchResp> responseObserver) {
      asyncUnimplementedUnaryCall(getSearchMethod(), responseObserver);
    }

    /**
     */
    public void upload(io.github.gravetii.grpc.UploadReq request,
        io.grpc.stub.StreamObserver<io.github.gravetii.grpc.FileChunk> responseObserver) {
      asyncUnimplementedUnaryCall(getUploadMethod(), responseObserver);
    }

    /**
     */
    public void ping(io.github.gravetii.grpc.PingReq request,
        io.grpc.stub.StreamObserver<io.github.gravetii.grpc.PingResp> responseObserver) {
      asyncUnimplementedUnaryCall(getPingMethod(), responseObserver);
    }

    /**
     */
    public void find(io.github.gravetii.grpc.FindReq request,
        io.grpc.stub.StreamObserver<io.github.gravetii.grpc.FindResp> responseObserver) {
      asyncUnimplementedUnaryCall(getFindMethod(), responseObserver);
    }

    /**
     */
    public void download(io.github.gravetii.grpc.DownloadReq request,
        io.grpc.stub.StreamObserver<io.github.gravetii.grpc.DownloadChunk> responseObserver) {
      asyncUnimplementedUnaryCall(getDownloadMethod(), responseObserver);
    }

    /**
     */
    public void getUserDirs(io.github.gravetii.grpc.UserDirsReq request,
        io.grpc.stub.StreamObserver<io.github.gravetii.grpc.UserDirsResp> responseObserver) {
      asyncUnimplementedUnaryCall(getGetUserDirsMethod(), responseObserver);
    }

    /**
     */
    public void updateUserDirs(io.github.gravetii.grpc.UpdateUserDirsReq request,
        io.grpc.stub.StreamObserver<io.github.gravetii.grpc.UpdateUserDirsResp> responseObserver) {
      asyncUnimplementedUnaryCall(getUpdateUserDirsMethod(), responseObserver);
    }

    /**
     */
    public void getTracker(io.github.gravetii.grpc.GetTrackerReq request,
        io.grpc.stub.StreamObserver<io.github.gravetii.grpc.GetTrackerResp> responseObserver) {
      asyncUnimplementedUnaryCall(getGetTrackerMethod(), responseObserver);
    }

    /**
     */
    public void updateTracker(io.github.gravetii.grpc.UpdateTrackerReq request,
        io.grpc.stub.StreamObserver<io.github.gravetii.grpc.UpdateTrackerResp> responseObserver) {
      asyncUnimplementedUnaryCall(getUpdateTrackerMethod(), responseObserver);
    }

    /**
     */
    public void index(io.github.gravetii.grpc.IndexReq request,
        io.grpc.stub.StreamObserver<io.github.gravetii.grpc.IndexResp> responseObserver) {
      asyncUnimplementedUnaryCall(getIndexMethod(), responseObserver);
    }

    /**
     */
    public void register(io.github.gravetii.grpc.RegisterReq request,
        io.grpc.stub.StreamObserver<io.github.gravetii.grpc.RegisterResp> responseObserver) {
      asyncUnimplementedUnaryCall(getRegisterMethod(), responseObserver);
    }

    /**
     */
    public void fetchFileList(io.github.gravetii.grpc.FetchFileListReq request,
        io.grpc.stub.StreamObserver<io.github.gravetii.grpc.FetchFileListResp> responseObserver) {
      asyncUnimplementedUnaryCall(getFetchFileListMethod(), responseObserver);
    }

    /**
     */
    public void getFileList(io.github.gravetii.grpc.GetFileListReq request,
        io.grpc.stub.StreamObserver<io.github.gravetii.grpc.GetFileListResp> responseObserver) {
      asyncUnimplementedUnaryCall(getGetFileListMethod(), responseObserver);
    }

    /**
     */
    public void close(io.github.gravetii.grpc.CloseReq request,
        io.grpc.stub.StreamObserver<io.github.gravetii.grpc.CloseResp> responseObserver) {
      asyncUnimplementedUnaryCall(getCloseMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getSearchMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.github.gravetii.grpc.SearchReq,
                io.github.gravetii.grpc.SearchResp>(
                  this, METHODID_SEARCH)))
          .addMethod(
            getUploadMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                io.github.gravetii.grpc.UploadReq,
                io.github.gravetii.grpc.FileChunk>(
                  this, METHODID_UPLOAD)))
          .addMethod(
            getPingMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.github.gravetii.grpc.PingReq,
                io.github.gravetii.grpc.PingResp>(
                  this, METHODID_PING)))
          .addMethod(
            getFindMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.github.gravetii.grpc.FindReq,
                io.github.gravetii.grpc.FindResp>(
                  this, METHODID_FIND)))
          .addMethod(
            getDownloadMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                io.github.gravetii.grpc.DownloadReq,
                io.github.gravetii.grpc.DownloadChunk>(
                  this, METHODID_DOWNLOAD)))
          .addMethod(
            getGetUserDirsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.github.gravetii.grpc.UserDirsReq,
                io.github.gravetii.grpc.UserDirsResp>(
                  this, METHODID_GET_USER_DIRS)))
          .addMethod(
            getUpdateUserDirsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.github.gravetii.grpc.UpdateUserDirsReq,
                io.github.gravetii.grpc.UpdateUserDirsResp>(
                  this, METHODID_UPDATE_USER_DIRS)))
          .addMethod(
            getGetTrackerMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.github.gravetii.grpc.GetTrackerReq,
                io.github.gravetii.grpc.GetTrackerResp>(
                  this, METHODID_GET_TRACKER)))
          .addMethod(
            getUpdateTrackerMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.github.gravetii.grpc.UpdateTrackerReq,
                io.github.gravetii.grpc.UpdateTrackerResp>(
                  this, METHODID_UPDATE_TRACKER)))
          .addMethod(
            getIndexMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                io.github.gravetii.grpc.IndexReq,
                io.github.gravetii.grpc.IndexResp>(
                  this, METHODID_INDEX)))
          .addMethod(
            getRegisterMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.github.gravetii.grpc.RegisterReq,
                io.github.gravetii.grpc.RegisterResp>(
                  this, METHODID_REGISTER)))
          .addMethod(
            getFetchFileListMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.github.gravetii.grpc.FetchFileListReq,
                io.github.gravetii.grpc.FetchFileListResp>(
                  this, METHODID_FETCH_FILE_LIST)))
          .addMethod(
            getGetFileListMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.github.gravetii.grpc.GetFileListReq,
                io.github.gravetii.grpc.GetFileListResp>(
                  this, METHODID_GET_FILE_LIST)))
          .addMethod(
            getCloseMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.github.gravetii.grpc.CloseReq,
                io.github.gravetii.grpc.CloseResp>(
                  this, METHODID_CLOSE)))
          .build();
    }
  }

  /**
   */
  public static final class DiztlServiceStub extends io.grpc.stub.AbstractAsyncStub<DiztlServiceStub> {
    private DiztlServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DiztlServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new DiztlServiceStub(channel, callOptions);
    }

    /**
     */
    public void search(io.github.gravetii.grpc.SearchReq request,
        io.grpc.stub.StreamObserver<io.github.gravetii.grpc.SearchResp> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSearchMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void upload(io.github.gravetii.grpc.UploadReq request,
        io.grpc.stub.StreamObserver<io.github.gravetii.grpc.FileChunk> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getUploadMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void ping(io.github.gravetii.grpc.PingReq request,
        io.grpc.stub.StreamObserver<io.github.gravetii.grpc.PingResp> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getPingMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void find(io.github.gravetii.grpc.FindReq request,
        io.grpc.stub.StreamObserver<io.github.gravetii.grpc.FindResp> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getFindMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void download(io.github.gravetii.grpc.DownloadReq request,
        io.grpc.stub.StreamObserver<io.github.gravetii.grpc.DownloadChunk> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getDownloadMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getUserDirs(io.github.gravetii.grpc.UserDirsReq request,
        io.grpc.stub.StreamObserver<io.github.gravetii.grpc.UserDirsResp> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetUserDirsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void updateUserDirs(io.github.gravetii.grpc.UpdateUserDirsReq request,
        io.grpc.stub.StreamObserver<io.github.gravetii.grpc.UpdateUserDirsResp> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUpdateUserDirsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getTracker(io.github.gravetii.grpc.GetTrackerReq request,
        io.grpc.stub.StreamObserver<io.github.gravetii.grpc.GetTrackerResp> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetTrackerMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void updateTracker(io.github.gravetii.grpc.UpdateTrackerReq request,
        io.grpc.stub.StreamObserver<io.github.gravetii.grpc.UpdateTrackerResp> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUpdateTrackerMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void index(io.github.gravetii.grpc.IndexReq request,
        io.grpc.stub.StreamObserver<io.github.gravetii.grpc.IndexResp> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getIndexMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void register(io.github.gravetii.grpc.RegisterReq request,
        io.grpc.stub.StreamObserver<io.github.gravetii.grpc.RegisterResp> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRegisterMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void fetchFileList(io.github.gravetii.grpc.FetchFileListReq request,
        io.grpc.stub.StreamObserver<io.github.gravetii.grpc.FetchFileListResp> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getFetchFileListMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getFileList(io.github.gravetii.grpc.GetFileListReq request,
        io.grpc.stub.StreamObserver<io.github.gravetii.grpc.GetFileListResp> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetFileListMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void close(io.github.gravetii.grpc.CloseReq request,
        io.grpc.stub.StreamObserver<io.github.gravetii.grpc.CloseResp> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCloseMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class DiztlServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<DiztlServiceBlockingStub> {
    private DiztlServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DiztlServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new DiztlServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public io.github.gravetii.grpc.SearchResp search(io.github.gravetii.grpc.SearchReq request) {
      return blockingUnaryCall(
          getChannel(), getSearchMethod(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<io.github.gravetii.grpc.FileChunk> upload(
        io.github.gravetii.grpc.UploadReq request) {
      return blockingServerStreamingCall(
          getChannel(), getUploadMethod(), getCallOptions(), request);
    }

    /**
     */
    public io.github.gravetii.grpc.PingResp ping(io.github.gravetii.grpc.PingReq request) {
      return blockingUnaryCall(
          getChannel(), getPingMethod(), getCallOptions(), request);
    }

    /**
     */
    public io.github.gravetii.grpc.FindResp find(io.github.gravetii.grpc.FindReq request) {
      return blockingUnaryCall(
          getChannel(), getFindMethod(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<io.github.gravetii.grpc.DownloadChunk> download(
        io.github.gravetii.grpc.DownloadReq request) {
      return blockingServerStreamingCall(
          getChannel(), getDownloadMethod(), getCallOptions(), request);
    }

    /**
     */
    public io.github.gravetii.grpc.UserDirsResp getUserDirs(io.github.gravetii.grpc.UserDirsReq request) {
      return blockingUnaryCall(
          getChannel(), getGetUserDirsMethod(), getCallOptions(), request);
    }

    /**
     */
    public io.github.gravetii.grpc.UpdateUserDirsResp updateUserDirs(io.github.gravetii.grpc.UpdateUserDirsReq request) {
      return blockingUnaryCall(
          getChannel(), getUpdateUserDirsMethod(), getCallOptions(), request);
    }

    /**
     */
    public io.github.gravetii.grpc.GetTrackerResp getTracker(io.github.gravetii.grpc.GetTrackerReq request) {
      return blockingUnaryCall(
          getChannel(), getGetTrackerMethod(), getCallOptions(), request);
    }

    /**
     */
    public io.github.gravetii.grpc.UpdateTrackerResp updateTracker(io.github.gravetii.grpc.UpdateTrackerReq request) {
      return blockingUnaryCall(
          getChannel(), getUpdateTrackerMethod(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<io.github.gravetii.grpc.IndexResp> index(
        io.github.gravetii.grpc.IndexReq request) {
      return blockingServerStreamingCall(
          getChannel(), getIndexMethod(), getCallOptions(), request);
    }

    /**
     */
    public io.github.gravetii.grpc.RegisterResp register(io.github.gravetii.grpc.RegisterReq request) {
      return blockingUnaryCall(
          getChannel(), getRegisterMethod(), getCallOptions(), request);
    }

    /**
     */
    public io.github.gravetii.grpc.FetchFileListResp fetchFileList(io.github.gravetii.grpc.FetchFileListReq request) {
      return blockingUnaryCall(
          getChannel(), getFetchFileListMethod(), getCallOptions(), request);
    }

    /**
     */
    public io.github.gravetii.grpc.GetFileListResp getFileList(io.github.gravetii.grpc.GetFileListReq request) {
      return blockingUnaryCall(
          getChannel(), getGetFileListMethod(), getCallOptions(), request);
    }

    /**
     */
    public io.github.gravetii.grpc.CloseResp close(io.github.gravetii.grpc.CloseReq request) {
      return blockingUnaryCall(
          getChannel(), getCloseMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class DiztlServiceFutureStub extends io.grpc.stub.AbstractFutureStub<DiztlServiceFutureStub> {
    private DiztlServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DiztlServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new DiztlServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<io.github.gravetii.grpc.SearchResp> search(
        io.github.gravetii.grpc.SearchReq request) {
      return futureUnaryCall(
          getChannel().newCall(getSearchMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<io.github.gravetii.grpc.PingResp> ping(
        io.github.gravetii.grpc.PingReq request) {
      return futureUnaryCall(
          getChannel().newCall(getPingMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<io.github.gravetii.grpc.FindResp> find(
        io.github.gravetii.grpc.FindReq request) {
      return futureUnaryCall(
          getChannel().newCall(getFindMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<io.github.gravetii.grpc.UserDirsResp> getUserDirs(
        io.github.gravetii.grpc.UserDirsReq request) {
      return futureUnaryCall(
          getChannel().newCall(getGetUserDirsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<io.github.gravetii.grpc.UpdateUserDirsResp> updateUserDirs(
        io.github.gravetii.grpc.UpdateUserDirsReq request) {
      return futureUnaryCall(
          getChannel().newCall(getUpdateUserDirsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<io.github.gravetii.grpc.GetTrackerResp> getTracker(
        io.github.gravetii.grpc.GetTrackerReq request) {
      return futureUnaryCall(
          getChannel().newCall(getGetTrackerMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<io.github.gravetii.grpc.UpdateTrackerResp> updateTracker(
        io.github.gravetii.grpc.UpdateTrackerReq request) {
      return futureUnaryCall(
          getChannel().newCall(getUpdateTrackerMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<io.github.gravetii.grpc.RegisterResp> register(
        io.github.gravetii.grpc.RegisterReq request) {
      return futureUnaryCall(
          getChannel().newCall(getRegisterMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<io.github.gravetii.grpc.FetchFileListResp> fetchFileList(
        io.github.gravetii.grpc.FetchFileListReq request) {
      return futureUnaryCall(
          getChannel().newCall(getFetchFileListMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<io.github.gravetii.grpc.GetFileListResp> getFileList(
        io.github.gravetii.grpc.GetFileListReq request) {
      return futureUnaryCall(
          getChannel().newCall(getGetFileListMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<io.github.gravetii.grpc.CloseResp> close(
        io.github.gravetii.grpc.CloseReq request) {
      return futureUnaryCall(
          getChannel().newCall(getCloseMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SEARCH = 0;
  private static final int METHODID_UPLOAD = 1;
  private static final int METHODID_PING = 2;
  private static final int METHODID_FIND = 3;
  private static final int METHODID_DOWNLOAD = 4;
  private static final int METHODID_GET_USER_DIRS = 5;
  private static final int METHODID_UPDATE_USER_DIRS = 6;
  private static final int METHODID_GET_TRACKER = 7;
  private static final int METHODID_UPDATE_TRACKER = 8;
  private static final int METHODID_INDEX = 9;
  private static final int METHODID_REGISTER = 10;
  private static final int METHODID_FETCH_FILE_LIST = 11;
  private static final int METHODID_GET_FILE_LIST = 12;
  private static final int METHODID_CLOSE = 13;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final DiztlServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(DiztlServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SEARCH:
          serviceImpl.search((io.github.gravetii.grpc.SearchReq) request,
              (io.grpc.stub.StreamObserver<io.github.gravetii.grpc.SearchResp>) responseObserver);
          break;
        case METHODID_UPLOAD:
          serviceImpl.upload((io.github.gravetii.grpc.UploadReq) request,
              (io.grpc.stub.StreamObserver<io.github.gravetii.grpc.FileChunk>) responseObserver);
          break;
        case METHODID_PING:
          serviceImpl.ping((io.github.gravetii.grpc.PingReq) request,
              (io.grpc.stub.StreamObserver<io.github.gravetii.grpc.PingResp>) responseObserver);
          break;
        case METHODID_FIND:
          serviceImpl.find((io.github.gravetii.grpc.FindReq) request,
              (io.grpc.stub.StreamObserver<io.github.gravetii.grpc.FindResp>) responseObserver);
          break;
        case METHODID_DOWNLOAD:
          serviceImpl.download((io.github.gravetii.grpc.DownloadReq) request,
              (io.grpc.stub.StreamObserver<io.github.gravetii.grpc.DownloadChunk>) responseObserver);
          break;
        case METHODID_GET_USER_DIRS:
          serviceImpl.getUserDirs((io.github.gravetii.grpc.UserDirsReq) request,
              (io.grpc.stub.StreamObserver<io.github.gravetii.grpc.UserDirsResp>) responseObserver);
          break;
        case METHODID_UPDATE_USER_DIRS:
          serviceImpl.updateUserDirs((io.github.gravetii.grpc.UpdateUserDirsReq) request,
              (io.grpc.stub.StreamObserver<io.github.gravetii.grpc.UpdateUserDirsResp>) responseObserver);
          break;
        case METHODID_GET_TRACKER:
          serviceImpl.getTracker((io.github.gravetii.grpc.GetTrackerReq) request,
              (io.grpc.stub.StreamObserver<io.github.gravetii.grpc.GetTrackerResp>) responseObserver);
          break;
        case METHODID_UPDATE_TRACKER:
          serviceImpl.updateTracker((io.github.gravetii.grpc.UpdateTrackerReq) request,
              (io.grpc.stub.StreamObserver<io.github.gravetii.grpc.UpdateTrackerResp>) responseObserver);
          break;
        case METHODID_INDEX:
          serviceImpl.index((io.github.gravetii.grpc.IndexReq) request,
              (io.grpc.stub.StreamObserver<io.github.gravetii.grpc.IndexResp>) responseObserver);
          break;
        case METHODID_REGISTER:
          serviceImpl.register((io.github.gravetii.grpc.RegisterReq) request,
              (io.grpc.stub.StreamObserver<io.github.gravetii.grpc.RegisterResp>) responseObserver);
          break;
        case METHODID_FETCH_FILE_LIST:
          serviceImpl.fetchFileList((io.github.gravetii.grpc.FetchFileListReq) request,
              (io.grpc.stub.StreamObserver<io.github.gravetii.grpc.FetchFileListResp>) responseObserver);
          break;
        case METHODID_GET_FILE_LIST:
          serviceImpl.getFileList((io.github.gravetii.grpc.GetFileListReq) request,
              (io.grpc.stub.StreamObserver<io.github.gravetii.grpc.GetFileListResp>) responseObserver);
          break;
        case METHODID_CLOSE:
          serviceImpl.close((io.github.gravetii.grpc.CloseReq) request,
              (io.grpc.stub.StreamObserver<io.github.gravetii.grpc.CloseResp>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class DiztlServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    DiztlServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return io.github.gravetii.grpc.Diztl.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("DiztlService");
    }
  }

  private static final class DiztlServiceFileDescriptorSupplier
      extends DiztlServiceBaseDescriptorSupplier {
    DiztlServiceFileDescriptorSupplier() {}
  }

  private static final class DiztlServiceMethodDescriptorSupplier
      extends DiztlServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    DiztlServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (DiztlServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new DiztlServiceFileDescriptorSupplier())
              .addMethod(getSearchMethod())
              .addMethod(getUploadMethod())
              .addMethod(getPingMethod())
              .addMethod(getFindMethod())
              .addMethod(getDownloadMethod())
              .addMethod(getGetUserDirsMethod())
              .addMethod(getUpdateUserDirsMethod())
              .addMethod(getGetTrackerMethod())
              .addMethod(getUpdateTrackerMethod())
              .addMethod(getIndexMethod())
              .addMethod(getRegisterMethod())
              .addMethod(getFetchFileListMethod())
              .addMethod(getGetFileListMethod())
              .addMethod(getCloseMethod())
              .build();
        }
      }
    }
    return result;
  }
}
