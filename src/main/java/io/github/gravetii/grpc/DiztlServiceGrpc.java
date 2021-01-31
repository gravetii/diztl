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
  private static volatile io.grpc.MethodDescriptor<io.github.gravetii.grpc.Diztl.SearchReq,
      io.github.gravetii.grpc.Diztl.SearchResp> getSearchMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Search",
      requestType = io.github.gravetii.grpc.Diztl.SearchReq.class,
      responseType = io.github.gravetii.grpc.Diztl.SearchResp.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.github.gravetii.grpc.Diztl.SearchReq,
      io.github.gravetii.grpc.Diztl.SearchResp> getSearchMethod() {
    io.grpc.MethodDescriptor<io.github.gravetii.grpc.Diztl.SearchReq, io.github.gravetii.grpc.Diztl.SearchResp> getSearchMethod;
    if ((getSearchMethod = DiztlServiceGrpc.getSearchMethod) == null) {
      synchronized (DiztlServiceGrpc.class) {
        if ((getSearchMethod = DiztlServiceGrpc.getSearchMethod) == null) {
          DiztlServiceGrpc.getSearchMethod = getSearchMethod =
              io.grpc.MethodDescriptor.<io.github.gravetii.grpc.Diztl.SearchReq, io.github.gravetii.grpc.Diztl.SearchResp>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Search"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.github.gravetii.grpc.Diztl.SearchReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.github.gravetii.grpc.Diztl.SearchResp.getDefaultInstance()))
              .setSchemaDescriptor(new DiztlServiceMethodDescriptorSupplier("Search"))
              .build();
        }
      }
    }
    return getSearchMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.github.gravetii.grpc.Diztl.UploadReq,
      io.github.gravetii.grpc.Diztl.FileChunk> getUploadMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Upload",
      requestType = io.github.gravetii.grpc.Diztl.UploadReq.class,
      responseType = io.github.gravetii.grpc.Diztl.FileChunk.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<io.github.gravetii.grpc.Diztl.UploadReq,
      io.github.gravetii.grpc.Diztl.FileChunk> getUploadMethod() {
    io.grpc.MethodDescriptor<io.github.gravetii.grpc.Diztl.UploadReq, io.github.gravetii.grpc.Diztl.FileChunk> getUploadMethod;
    if ((getUploadMethod = DiztlServiceGrpc.getUploadMethod) == null) {
      synchronized (DiztlServiceGrpc.class) {
        if ((getUploadMethod = DiztlServiceGrpc.getUploadMethod) == null) {
          DiztlServiceGrpc.getUploadMethod = getUploadMethod =
              io.grpc.MethodDescriptor.<io.github.gravetii.grpc.Diztl.UploadReq, io.github.gravetii.grpc.Diztl.FileChunk>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Upload"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.github.gravetii.grpc.Diztl.UploadReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.github.gravetii.grpc.Diztl.FileChunk.getDefaultInstance()))
              .setSchemaDescriptor(new DiztlServiceMethodDescriptorSupplier("Upload"))
              .build();
        }
      }
    }
    return getUploadMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.github.gravetii.grpc.Diztl.PingReq,
      io.github.gravetii.grpc.Diztl.PingResp> getPingMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Ping",
      requestType = io.github.gravetii.grpc.Diztl.PingReq.class,
      responseType = io.github.gravetii.grpc.Diztl.PingResp.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.github.gravetii.grpc.Diztl.PingReq,
      io.github.gravetii.grpc.Diztl.PingResp> getPingMethod() {
    io.grpc.MethodDescriptor<io.github.gravetii.grpc.Diztl.PingReq, io.github.gravetii.grpc.Diztl.PingResp> getPingMethod;
    if ((getPingMethod = DiztlServiceGrpc.getPingMethod) == null) {
      synchronized (DiztlServiceGrpc.class) {
        if ((getPingMethod = DiztlServiceGrpc.getPingMethod) == null) {
          DiztlServiceGrpc.getPingMethod = getPingMethod =
              io.grpc.MethodDescriptor.<io.github.gravetii.grpc.Diztl.PingReq, io.github.gravetii.grpc.Diztl.PingResp>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Ping"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.github.gravetii.grpc.Diztl.PingReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.github.gravetii.grpc.Diztl.PingResp.getDefaultInstance()))
              .setSchemaDescriptor(new DiztlServiceMethodDescriptorSupplier("Ping"))
              .build();
        }
      }
    }
    return getPingMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.github.gravetii.grpc.Diztl.FindReq,
      io.github.gravetii.grpc.Diztl.FindResp> getFindMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Find",
      requestType = io.github.gravetii.grpc.Diztl.FindReq.class,
      responseType = io.github.gravetii.grpc.Diztl.FindResp.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.github.gravetii.grpc.Diztl.FindReq,
      io.github.gravetii.grpc.Diztl.FindResp> getFindMethod() {
    io.grpc.MethodDescriptor<io.github.gravetii.grpc.Diztl.FindReq, io.github.gravetii.grpc.Diztl.FindResp> getFindMethod;
    if ((getFindMethod = DiztlServiceGrpc.getFindMethod) == null) {
      synchronized (DiztlServiceGrpc.class) {
        if ((getFindMethod = DiztlServiceGrpc.getFindMethod) == null) {
          DiztlServiceGrpc.getFindMethod = getFindMethod =
              io.grpc.MethodDescriptor.<io.github.gravetii.grpc.Diztl.FindReq, io.github.gravetii.grpc.Diztl.FindResp>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Find"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.github.gravetii.grpc.Diztl.FindReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.github.gravetii.grpc.Diztl.FindResp.getDefaultInstance()))
              .setSchemaDescriptor(new DiztlServiceMethodDescriptorSupplier("Find"))
              .build();
        }
      }
    }
    return getFindMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.github.gravetii.grpc.Diztl.DownloadReq,
      io.github.gravetii.grpc.Diztl.DownloadChunk> getDownloadMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Download",
      requestType = io.github.gravetii.grpc.Diztl.DownloadReq.class,
      responseType = io.github.gravetii.grpc.Diztl.DownloadChunk.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<io.github.gravetii.grpc.Diztl.DownloadReq,
      io.github.gravetii.grpc.Diztl.DownloadChunk> getDownloadMethod() {
    io.grpc.MethodDescriptor<io.github.gravetii.grpc.Diztl.DownloadReq, io.github.gravetii.grpc.Diztl.DownloadChunk> getDownloadMethod;
    if ((getDownloadMethod = DiztlServiceGrpc.getDownloadMethod) == null) {
      synchronized (DiztlServiceGrpc.class) {
        if ((getDownloadMethod = DiztlServiceGrpc.getDownloadMethod) == null) {
          DiztlServiceGrpc.getDownloadMethod = getDownloadMethod =
              io.grpc.MethodDescriptor.<io.github.gravetii.grpc.Diztl.DownloadReq, io.github.gravetii.grpc.Diztl.DownloadChunk>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Download"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.github.gravetii.grpc.Diztl.DownloadReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.github.gravetii.grpc.Diztl.DownloadChunk.getDefaultInstance()))
              .setSchemaDescriptor(new DiztlServiceMethodDescriptorSupplier("Download"))
              .build();
        }
      }
    }
    return getDownloadMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.github.gravetii.grpc.Diztl.UserDirsReq,
      io.github.gravetii.grpc.Diztl.UserDirsResp> getGetUserDirsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetUserDirs",
      requestType = io.github.gravetii.grpc.Diztl.UserDirsReq.class,
      responseType = io.github.gravetii.grpc.Diztl.UserDirsResp.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.github.gravetii.grpc.Diztl.UserDirsReq,
      io.github.gravetii.grpc.Diztl.UserDirsResp> getGetUserDirsMethod() {
    io.grpc.MethodDescriptor<io.github.gravetii.grpc.Diztl.UserDirsReq, io.github.gravetii.grpc.Diztl.UserDirsResp> getGetUserDirsMethod;
    if ((getGetUserDirsMethod = DiztlServiceGrpc.getGetUserDirsMethod) == null) {
      synchronized (DiztlServiceGrpc.class) {
        if ((getGetUserDirsMethod = DiztlServiceGrpc.getGetUserDirsMethod) == null) {
          DiztlServiceGrpc.getGetUserDirsMethod = getGetUserDirsMethod =
              io.grpc.MethodDescriptor.<io.github.gravetii.grpc.Diztl.UserDirsReq, io.github.gravetii.grpc.Diztl.UserDirsResp>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetUserDirs"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.github.gravetii.grpc.Diztl.UserDirsReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.github.gravetii.grpc.Diztl.UserDirsResp.getDefaultInstance()))
              .setSchemaDescriptor(new DiztlServiceMethodDescriptorSupplier("GetUserDirs"))
              .build();
        }
      }
    }
    return getGetUserDirsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.github.gravetii.grpc.Diztl.UpdateUserDirsReq,
      io.github.gravetii.grpc.Diztl.UpdateUserDirsResp> getUpdateUserDirsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpdateUserDirs",
      requestType = io.github.gravetii.grpc.Diztl.UpdateUserDirsReq.class,
      responseType = io.github.gravetii.grpc.Diztl.UpdateUserDirsResp.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.github.gravetii.grpc.Diztl.UpdateUserDirsReq,
      io.github.gravetii.grpc.Diztl.UpdateUserDirsResp> getUpdateUserDirsMethod() {
    io.grpc.MethodDescriptor<io.github.gravetii.grpc.Diztl.UpdateUserDirsReq, io.github.gravetii.grpc.Diztl.UpdateUserDirsResp> getUpdateUserDirsMethod;
    if ((getUpdateUserDirsMethod = DiztlServiceGrpc.getUpdateUserDirsMethod) == null) {
      synchronized (DiztlServiceGrpc.class) {
        if ((getUpdateUserDirsMethod = DiztlServiceGrpc.getUpdateUserDirsMethod) == null) {
          DiztlServiceGrpc.getUpdateUserDirsMethod = getUpdateUserDirsMethod =
              io.grpc.MethodDescriptor.<io.github.gravetii.grpc.Diztl.UpdateUserDirsReq, io.github.gravetii.grpc.Diztl.UpdateUserDirsResp>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpdateUserDirs"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.github.gravetii.grpc.Diztl.UpdateUserDirsReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.github.gravetii.grpc.Diztl.UpdateUserDirsResp.getDefaultInstance()))
              .setSchemaDescriptor(new DiztlServiceMethodDescriptorSupplier("UpdateUserDirs"))
              .build();
        }
      }
    }
    return getUpdateUserDirsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.github.gravetii.grpc.Diztl.GetTrackerReq,
      io.github.gravetii.grpc.Diztl.GetTrackerResp> getGetTrackerMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetTracker",
      requestType = io.github.gravetii.grpc.Diztl.GetTrackerReq.class,
      responseType = io.github.gravetii.grpc.Diztl.GetTrackerResp.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.github.gravetii.grpc.Diztl.GetTrackerReq,
      io.github.gravetii.grpc.Diztl.GetTrackerResp> getGetTrackerMethod() {
    io.grpc.MethodDescriptor<io.github.gravetii.grpc.Diztl.GetTrackerReq, io.github.gravetii.grpc.Diztl.GetTrackerResp> getGetTrackerMethod;
    if ((getGetTrackerMethod = DiztlServiceGrpc.getGetTrackerMethod) == null) {
      synchronized (DiztlServiceGrpc.class) {
        if ((getGetTrackerMethod = DiztlServiceGrpc.getGetTrackerMethod) == null) {
          DiztlServiceGrpc.getGetTrackerMethod = getGetTrackerMethod =
              io.grpc.MethodDescriptor.<io.github.gravetii.grpc.Diztl.GetTrackerReq, io.github.gravetii.grpc.Diztl.GetTrackerResp>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetTracker"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.github.gravetii.grpc.Diztl.GetTrackerReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.github.gravetii.grpc.Diztl.GetTrackerResp.getDefaultInstance()))
              .setSchemaDescriptor(new DiztlServiceMethodDescriptorSupplier("GetTracker"))
              .build();
        }
      }
    }
    return getGetTrackerMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.github.gravetii.grpc.Diztl.UpdateTrackerReq,
      io.github.gravetii.grpc.Diztl.UpdateTrackerResp> getUpdateTrackerMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpdateTracker",
      requestType = io.github.gravetii.grpc.Diztl.UpdateTrackerReq.class,
      responseType = io.github.gravetii.grpc.Diztl.UpdateTrackerResp.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.github.gravetii.grpc.Diztl.UpdateTrackerReq,
      io.github.gravetii.grpc.Diztl.UpdateTrackerResp> getUpdateTrackerMethod() {
    io.grpc.MethodDescriptor<io.github.gravetii.grpc.Diztl.UpdateTrackerReq, io.github.gravetii.grpc.Diztl.UpdateTrackerResp> getUpdateTrackerMethod;
    if ((getUpdateTrackerMethod = DiztlServiceGrpc.getUpdateTrackerMethod) == null) {
      synchronized (DiztlServiceGrpc.class) {
        if ((getUpdateTrackerMethod = DiztlServiceGrpc.getUpdateTrackerMethod) == null) {
          DiztlServiceGrpc.getUpdateTrackerMethod = getUpdateTrackerMethod =
              io.grpc.MethodDescriptor.<io.github.gravetii.grpc.Diztl.UpdateTrackerReq, io.github.gravetii.grpc.Diztl.UpdateTrackerResp>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpdateTracker"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.github.gravetii.grpc.Diztl.UpdateTrackerReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.github.gravetii.grpc.Diztl.UpdateTrackerResp.getDefaultInstance()))
              .setSchemaDescriptor(new DiztlServiceMethodDescriptorSupplier("UpdateTracker"))
              .build();
        }
      }
    }
    return getUpdateTrackerMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.github.gravetii.grpc.Diztl.IndexReq,
      io.github.gravetii.grpc.Diztl.IndexResp> getIndexMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Index",
      requestType = io.github.gravetii.grpc.Diztl.IndexReq.class,
      responseType = io.github.gravetii.grpc.Diztl.IndexResp.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<io.github.gravetii.grpc.Diztl.IndexReq,
      io.github.gravetii.grpc.Diztl.IndexResp> getIndexMethod() {
    io.grpc.MethodDescriptor<io.github.gravetii.grpc.Diztl.IndexReq, io.github.gravetii.grpc.Diztl.IndexResp> getIndexMethod;
    if ((getIndexMethod = DiztlServiceGrpc.getIndexMethod) == null) {
      synchronized (DiztlServiceGrpc.class) {
        if ((getIndexMethod = DiztlServiceGrpc.getIndexMethod) == null) {
          DiztlServiceGrpc.getIndexMethod = getIndexMethod =
              io.grpc.MethodDescriptor.<io.github.gravetii.grpc.Diztl.IndexReq, io.github.gravetii.grpc.Diztl.IndexResp>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Index"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.github.gravetii.grpc.Diztl.IndexReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.github.gravetii.grpc.Diztl.IndexResp.getDefaultInstance()))
              .setSchemaDescriptor(new DiztlServiceMethodDescriptorSupplier("Index"))
              .build();
        }
      }
    }
    return getIndexMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.github.gravetii.grpc.Diztl.RegisterReq,
      io.github.gravetii.grpc.Diztl.RegisterResp> getRegisterMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Register",
      requestType = io.github.gravetii.grpc.Diztl.RegisterReq.class,
      responseType = io.github.gravetii.grpc.Diztl.RegisterResp.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.github.gravetii.grpc.Diztl.RegisterReq,
      io.github.gravetii.grpc.Diztl.RegisterResp> getRegisterMethod() {
    io.grpc.MethodDescriptor<io.github.gravetii.grpc.Diztl.RegisterReq, io.github.gravetii.grpc.Diztl.RegisterResp> getRegisterMethod;
    if ((getRegisterMethod = DiztlServiceGrpc.getRegisterMethod) == null) {
      synchronized (DiztlServiceGrpc.class) {
        if ((getRegisterMethod = DiztlServiceGrpc.getRegisterMethod) == null) {
          DiztlServiceGrpc.getRegisterMethod = getRegisterMethod =
              io.grpc.MethodDescriptor.<io.github.gravetii.grpc.Diztl.RegisterReq, io.github.gravetii.grpc.Diztl.RegisterResp>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Register"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.github.gravetii.grpc.Diztl.RegisterReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.github.gravetii.grpc.Diztl.RegisterResp.getDefaultInstance()))
              .setSchemaDescriptor(new DiztlServiceMethodDescriptorSupplier("Register"))
              .build();
        }
      }
    }
    return getRegisterMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.github.gravetii.grpc.Diztl.FetchFileListReq,
      io.github.gravetii.grpc.Diztl.FetchFileListResp> getFetchFileListMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "FetchFileList",
      requestType = io.github.gravetii.grpc.Diztl.FetchFileListReq.class,
      responseType = io.github.gravetii.grpc.Diztl.FetchFileListResp.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.github.gravetii.grpc.Diztl.FetchFileListReq,
      io.github.gravetii.grpc.Diztl.FetchFileListResp> getFetchFileListMethod() {
    io.grpc.MethodDescriptor<io.github.gravetii.grpc.Diztl.FetchFileListReq, io.github.gravetii.grpc.Diztl.FetchFileListResp> getFetchFileListMethod;
    if ((getFetchFileListMethod = DiztlServiceGrpc.getFetchFileListMethod) == null) {
      synchronized (DiztlServiceGrpc.class) {
        if ((getFetchFileListMethod = DiztlServiceGrpc.getFetchFileListMethod) == null) {
          DiztlServiceGrpc.getFetchFileListMethod = getFetchFileListMethod =
              io.grpc.MethodDescriptor.<io.github.gravetii.grpc.Diztl.FetchFileListReq, io.github.gravetii.grpc.Diztl.FetchFileListResp>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "FetchFileList"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.github.gravetii.grpc.Diztl.FetchFileListReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.github.gravetii.grpc.Diztl.FetchFileListResp.getDefaultInstance()))
              .setSchemaDescriptor(new DiztlServiceMethodDescriptorSupplier("FetchFileList"))
              .build();
        }
      }
    }
    return getFetchFileListMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.github.gravetii.grpc.Diztl.GetFileListReq,
      io.github.gravetii.grpc.Diztl.GetFileListResp> getGetFileListMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetFileList",
      requestType = io.github.gravetii.grpc.Diztl.GetFileListReq.class,
      responseType = io.github.gravetii.grpc.Diztl.GetFileListResp.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.github.gravetii.grpc.Diztl.GetFileListReq,
      io.github.gravetii.grpc.Diztl.GetFileListResp> getGetFileListMethod() {
    io.grpc.MethodDescriptor<io.github.gravetii.grpc.Diztl.GetFileListReq, io.github.gravetii.grpc.Diztl.GetFileListResp> getGetFileListMethod;
    if ((getGetFileListMethod = DiztlServiceGrpc.getGetFileListMethod) == null) {
      synchronized (DiztlServiceGrpc.class) {
        if ((getGetFileListMethod = DiztlServiceGrpc.getGetFileListMethod) == null) {
          DiztlServiceGrpc.getGetFileListMethod = getGetFileListMethod =
              io.grpc.MethodDescriptor.<io.github.gravetii.grpc.Diztl.GetFileListReq, io.github.gravetii.grpc.Diztl.GetFileListResp>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetFileList"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.github.gravetii.grpc.Diztl.GetFileListReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.github.gravetii.grpc.Diztl.GetFileListResp.getDefaultInstance()))
              .setSchemaDescriptor(new DiztlServiceMethodDescriptorSupplier("GetFileList"))
              .build();
        }
      }
    }
    return getGetFileListMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.github.gravetii.grpc.Diztl.CloseReq,
      io.github.gravetii.grpc.Diztl.CloseResp> getCloseMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Close",
      requestType = io.github.gravetii.grpc.Diztl.CloseReq.class,
      responseType = io.github.gravetii.grpc.Diztl.CloseResp.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.github.gravetii.grpc.Diztl.CloseReq,
      io.github.gravetii.grpc.Diztl.CloseResp> getCloseMethod() {
    io.grpc.MethodDescriptor<io.github.gravetii.grpc.Diztl.CloseReq, io.github.gravetii.grpc.Diztl.CloseResp> getCloseMethod;
    if ((getCloseMethod = DiztlServiceGrpc.getCloseMethod) == null) {
      synchronized (DiztlServiceGrpc.class) {
        if ((getCloseMethod = DiztlServiceGrpc.getCloseMethod) == null) {
          DiztlServiceGrpc.getCloseMethod = getCloseMethod =
              io.grpc.MethodDescriptor.<io.github.gravetii.grpc.Diztl.CloseReq, io.github.gravetii.grpc.Diztl.CloseResp>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Close"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.github.gravetii.grpc.Diztl.CloseReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.github.gravetii.grpc.Diztl.CloseResp.getDefaultInstance()))
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
    public void search(io.github.gravetii.grpc.Diztl.SearchReq request,
        io.grpc.stub.StreamObserver<io.github.gravetii.grpc.Diztl.SearchResp> responseObserver) {
      asyncUnimplementedUnaryCall(getSearchMethod(), responseObserver);
    }

    /**
     */
    public void upload(io.github.gravetii.grpc.Diztl.UploadReq request,
        io.grpc.stub.StreamObserver<io.github.gravetii.grpc.Diztl.FileChunk> responseObserver) {
      asyncUnimplementedUnaryCall(getUploadMethod(), responseObserver);
    }

    /**
     */
    public void ping(io.github.gravetii.grpc.Diztl.PingReq request,
        io.grpc.stub.StreamObserver<io.github.gravetii.grpc.Diztl.PingResp> responseObserver) {
      asyncUnimplementedUnaryCall(getPingMethod(), responseObserver);
    }

    /**
     */
    public void find(io.github.gravetii.grpc.Diztl.FindReq request,
        io.grpc.stub.StreamObserver<io.github.gravetii.grpc.Diztl.FindResp> responseObserver) {
      asyncUnimplementedUnaryCall(getFindMethod(), responseObserver);
    }

    /**
     */
    public void download(io.github.gravetii.grpc.Diztl.DownloadReq request,
        io.grpc.stub.StreamObserver<io.github.gravetii.grpc.Diztl.DownloadChunk> responseObserver) {
      asyncUnimplementedUnaryCall(getDownloadMethod(), responseObserver);
    }

    /**
     */
    public void getUserDirs(io.github.gravetii.grpc.Diztl.UserDirsReq request,
        io.grpc.stub.StreamObserver<io.github.gravetii.grpc.Diztl.UserDirsResp> responseObserver) {
      asyncUnimplementedUnaryCall(getGetUserDirsMethod(), responseObserver);
    }

    /**
     */
    public void updateUserDirs(io.github.gravetii.grpc.Diztl.UpdateUserDirsReq request,
        io.grpc.stub.StreamObserver<io.github.gravetii.grpc.Diztl.UpdateUserDirsResp> responseObserver) {
      asyncUnimplementedUnaryCall(getUpdateUserDirsMethod(), responseObserver);
    }

    /**
     */
    public void getTracker(io.github.gravetii.grpc.Diztl.GetTrackerReq request,
        io.grpc.stub.StreamObserver<io.github.gravetii.grpc.Diztl.GetTrackerResp> responseObserver) {
      asyncUnimplementedUnaryCall(getGetTrackerMethod(), responseObserver);
    }

    /**
     */
    public void updateTracker(io.github.gravetii.grpc.Diztl.UpdateTrackerReq request,
        io.grpc.stub.StreamObserver<io.github.gravetii.grpc.Diztl.UpdateTrackerResp> responseObserver) {
      asyncUnimplementedUnaryCall(getUpdateTrackerMethod(), responseObserver);
    }

    /**
     */
    public void index(io.github.gravetii.grpc.Diztl.IndexReq request,
        io.grpc.stub.StreamObserver<io.github.gravetii.grpc.Diztl.IndexResp> responseObserver) {
      asyncUnimplementedUnaryCall(getIndexMethod(), responseObserver);
    }

    /**
     */
    public void register(io.github.gravetii.grpc.Diztl.RegisterReq request,
        io.grpc.stub.StreamObserver<io.github.gravetii.grpc.Diztl.RegisterResp> responseObserver) {
      asyncUnimplementedUnaryCall(getRegisterMethod(), responseObserver);
    }

    /**
     */
    public void fetchFileList(io.github.gravetii.grpc.Diztl.FetchFileListReq request,
        io.grpc.stub.StreamObserver<io.github.gravetii.grpc.Diztl.FetchFileListResp> responseObserver) {
      asyncUnimplementedUnaryCall(getFetchFileListMethod(), responseObserver);
    }

    /**
     */
    public void getFileList(io.github.gravetii.grpc.Diztl.GetFileListReq request,
        io.grpc.stub.StreamObserver<io.github.gravetii.grpc.Diztl.GetFileListResp> responseObserver) {
      asyncUnimplementedUnaryCall(getGetFileListMethod(), responseObserver);
    }

    /**
     */
    public void close(io.github.gravetii.grpc.Diztl.CloseReq request,
        io.grpc.stub.StreamObserver<io.github.gravetii.grpc.Diztl.CloseResp> responseObserver) {
      asyncUnimplementedUnaryCall(getCloseMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getSearchMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.github.gravetii.grpc.Diztl.SearchReq,
                io.github.gravetii.grpc.Diztl.SearchResp>(
                  this, METHODID_SEARCH)))
          .addMethod(
            getUploadMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                io.github.gravetii.grpc.Diztl.UploadReq,
                io.github.gravetii.grpc.Diztl.FileChunk>(
                  this, METHODID_UPLOAD)))
          .addMethod(
            getPingMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.github.gravetii.grpc.Diztl.PingReq,
                io.github.gravetii.grpc.Diztl.PingResp>(
                  this, METHODID_PING)))
          .addMethod(
            getFindMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.github.gravetii.grpc.Diztl.FindReq,
                io.github.gravetii.grpc.Diztl.FindResp>(
                  this, METHODID_FIND)))
          .addMethod(
            getDownloadMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                io.github.gravetii.grpc.Diztl.DownloadReq,
                io.github.gravetii.grpc.Diztl.DownloadChunk>(
                  this, METHODID_DOWNLOAD)))
          .addMethod(
            getGetUserDirsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.github.gravetii.grpc.Diztl.UserDirsReq,
                io.github.gravetii.grpc.Diztl.UserDirsResp>(
                  this, METHODID_GET_USER_DIRS)))
          .addMethod(
            getUpdateUserDirsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.github.gravetii.grpc.Diztl.UpdateUserDirsReq,
                io.github.gravetii.grpc.Diztl.UpdateUserDirsResp>(
                  this, METHODID_UPDATE_USER_DIRS)))
          .addMethod(
            getGetTrackerMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.github.gravetii.grpc.Diztl.GetTrackerReq,
                io.github.gravetii.grpc.Diztl.GetTrackerResp>(
                  this, METHODID_GET_TRACKER)))
          .addMethod(
            getUpdateTrackerMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.github.gravetii.grpc.Diztl.UpdateTrackerReq,
                io.github.gravetii.grpc.Diztl.UpdateTrackerResp>(
                  this, METHODID_UPDATE_TRACKER)))
          .addMethod(
            getIndexMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                io.github.gravetii.grpc.Diztl.IndexReq,
                io.github.gravetii.grpc.Diztl.IndexResp>(
                  this, METHODID_INDEX)))
          .addMethod(
            getRegisterMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.github.gravetii.grpc.Diztl.RegisterReq,
                io.github.gravetii.grpc.Diztl.RegisterResp>(
                  this, METHODID_REGISTER)))
          .addMethod(
            getFetchFileListMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.github.gravetii.grpc.Diztl.FetchFileListReq,
                io.github.gravetii.grpc.Diztl.FetchFileListResp>(
                  this, METHODID_FETCH_FILE_LIST)))
          .addMethod(
            getGetFileListMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.github.gravetii.grpc.Diztl.GetFileListReq,
                io.github.gravetii.grpc.Diztl.GetFileListResp>(
                  this, METHODID_GET_FILE_LIST)))
          .addMethod(
            getCloseMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.github.gravetii.grpc.Diztl.CloseReq,
                io.github.gravetii.grpc.Diztl.CloseResp>(
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
    public void search(io.github.gravetii.grpc.Diztl.SearchReq request,
        io.grpc.stub.StreamObserver<io.github.gravetii.grpc.Diztl.SearchResp> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSearchMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void upload(io.github.gravetii.grpc.Diztl.UploadReq request,
        io.grpc.stub.StreamObserver<io.github.gravetii.grpc.Diztl.FileChunk> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getUploadMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void ping(io.github.gravetii.grpc.Diztl.PingReq request,
        io.grpc.stub.StreamObserver<io.github.gravetii.grpc.Diztl.PingResp> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getPingMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void find(io.github.gravetii.grpc.Diztl.FindReq request,
        io.grpc.stub.StreamObserver<io.github.gravetii.grpc.Diztl.FindResp> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getFindMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void download(io.github.gravetii.grpc.Diztl.DownloadReq request,
        io.grpc.stub.StreamObserver<io.github.gravetii.grpc.Diztl.DownloadChunk> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getDownloadMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getUserDirs(io.github.gravetii.grpc.Diztl.UserDirsReq request,
        io.grpc.stub.StreamObserver<io.github.gravetii.grpc.Diztl.UserDirsResp> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetUserDirsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void updateUserDirs(io.github.gravetii.grpc.Diztl.UpdateUserDirsReq request,
        io.grpc.stub.StreamObserver<io.github.gravetii.grpc.Diztl.UpdateUserDirsResp> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUpdateUserDirsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getTracker(io.github.gravetii.grpc.Diztl.GetTrackerReq request,
        io.grpc.stub.StreamObserver<io.github.gravetii.grpc.Diztl.GetTrackerResp> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetTrackerMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void updateTracker(io.github.gravetii.grpc.Diztl.UpdateTrackerReq request,
        io.grpc.stub.StreamObserver<io.github.gravetii.grpc.Diztl.UpdateTrackerResp> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUpdateTrackerMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void index(io.github.gravetii.grpc.Diztl.IndexReq request,
        io.grpc.stub.StreamObserver<io.github.gravetii.grpc.Diztl.IndexResp> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getIndexMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void register(io.github.gravetii.grpc.Diztl.RegisterReq request,
        io.grpc.stub.StreamObserver<io.github.gravetii.grpc.Diztl.RegisterResp> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRegisterMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void fetchFileList(io.github.gravetii.grpc.Diztl.FetchFileListReq request,
        io.grpc.stub.StreamObserver<io.github.gravetii.grpc.Diztl.FetchFileListResp> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getFetchFileListMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getFileList(io.github.gravetii.grpc.Diztl.GetFileListReq request,
        io.grpc.stub.StreamObserver<io.github.gravetii.grpc.Diztl.GetFileListResp> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetFileListMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void close(io.github.gravetii.grpc.Diztl.CloseReq request,
        io.grpc.stub.StreamObserver<io.github.gravetii.grpc.Diztl.CloseResp> responseObserver) {
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
    public io.github.gravetii.grpc.Diztl.SearchResp search(io.github.gravetii.grpc.Diztl.SearchReq request) {
      return blockingUnaryCall(
          getChannel(), getSearchMethod(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<io.github.gravetii.grpc.Diztl.FileChunk> upload(
        io.github.gravetii.grpc.Diztl.UploadReq request) {
      return blockingServerStreamingCall(
          getChannel(), getUploadMethod(), getCallOptions(), request);
    }

    /**
     */
    public io.github.gravetii.grpc.Diztl.PingResp ping(io.github.gravetii.grpc.Diztl.PingReq request) {
      return blockingUnaryCall(
          getChannel(), getPingMethod(), getCallOptions(), request);
    }

    /**
     */
    public io.github.gravetii.grpc.Diztl.FindResp find(io.github.gravetii.grpc.Diztl.FindReq request) {
      return blockingUnaryCall(
          getChannel(), getFindMethod(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<io.github.gravetii.grpc.Diztl.DownloadChunk> download(
        io.github.gravetii.grpc.Diztl.DownloadReq request) {
      return blockingServerStreamingCall(
          getChannel(), getDownloadMethod(), getCallOptions(), request);
    }

    /**
     */
    public io.github.gravetii.grpc.Diztl.UserDirsResp getUserDirs(io.github.gravetii.grpc.Diztl.UserDirsReq request) {
      return blockingUnaryCall(
          getChannel(), getGetUserDirsMethod(), getCallOptions(), request);
    }

    /**
     */
    public io.github.gravetii.grpc.Diztl.UpdateUserDirsResp updateUserDirs(io.github.gravetii.grpc.Diztl.UpdateUserDirsReq request) {
      return blockingUnaryCall(
          getChannel(), getUpdateUserDirsMethod(), getCallOptions(), request);
    }

    /**
     */
    public io.github.gravetii.grpc.Diztl.GetTrackerResp getTracker(io.github.gravetii.grpc.Diztl.GetTrackerReq request) {
      return blockingUnaryCall(
          getChannel(), getGetTrackerMethod(), getCallOptions(), request);
    }

    /**
     */
    public io.github.gravetii.grpc.Diztl.UpdateTrackerResp updateTracker(io.github.gravetii.grpc.Diztl.UpdateTrackerReq request) {
      return blockingUnaryCall(
          getChannel(), getUpdateTrackerMethod(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<io.github.gravetii.grpc.Diztl.IndexResp> index(
        io.github.gravetii.grpc.Diztl.IndexReq request) {
      return blockingServerStreamingCall(
          getChannel(), getIndexMethod(), getCallOptions(), request);
    }

    /**
     */
    public io.github.gravetii.grpc.Diztl.RegisterResp register(io.github.gravetii.grpc.Diztl.RegisterReq request) {
      return blockingUnaryCall(
          getChannel(), getRegisterMethod(), getCallOptions(), request);
    }

    /**
     */
    public io.github.gravetii.grpc.Diztl.FetchFileListResp fetchFileList(io.github.gravetii.grpc.Diztl.FetchFileListReq request) {
      return blockingUnaryCall(
          getChannel(), getFetchFileListMethod(), getCallOptions(), request);
    }

    /**
     */
    public io.github.gravetii.grpc.Diztl.GetFileListResp getFileList(io.github.gravetii.grpc.Diztl.GetFileListReq request) {
      return blockingUnaryCall(
          getChannel(), getGetFileListMethod(), getCallOptions(), request);
    }

    /**
     */
    public io.github.gravetii.grpc.Diztl.CloseResp close(io.github.gravetii.grpc.Diztl.CloseReq request) {
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
    public com.google.common.util.concurrent.ListenableFuture<io.github.gravetii.grpc.Diztl.SearchResp> search(
        io.github.gravetii.grpc.Diztl.SearchReq request) {
      return futureUnaryCall(
          getChannel().newCall(getSearchMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<io.github.gravetii.grpc.Diztl.PingResp> ping(
        io.github.gravetii.grpc.Diztl.PingReq request) {
      return futureUnaryCall(
          getChannel().newCall(getPingMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<io.github.gravetii.grpc.Diztl.FindResp> find(
        io.github.gravetii.grpc.Diztl.FindReq request) {
      return futureUnaryCall(
          getChannel().newCall(getFindMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<io.github.gravetii.grpc.Diztl.UserDirsResp> getUserDirs(
        io.github.gravetii.grpc.Diztl.UserDirsReq request) {
      return futureUnaryCall(
          getChannel().newCall(getGetUserDirsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<io.github.gravetii.grpc.Diztl.UpdateUserDirsResp> updateUserDirs(
        io.github.gravetii.grpc.Diztl.UpdateUserDirsReq request) {
      return futureUnaryCall(
          getChannel().newCall(getUpdateUserDirsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<io.github.gravetii.grpc.Diztl.GetTrackerResp> getTracker(
        io.github.gravetii.grpc.Diztl.GetTrackerReq request) {
      return futureUnaryCall(
          getChannel().newCall(getGetTrackerMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<io.github.gravetii.grpc.Diztl.UpdateTrackerResp> updateTracker(
        io.github.gravetii.grpc.Diztl.UpdateTrackerReq request) {
      return futureUnaryCall(
          getChannel().newCall(getUpdateTrackerMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<io.github.gravetii.grpc.Diztl.RegisterResp> register(
        io.github.gravetii.grpc.Diztl.RegisterReq request) {
      return futureUnaryCall(
          getChannel().newCall(getRegisterMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<io.github.gravetii.grpc.Diztl.FetchFileListResp> fetchFileList(
        io.github.gravetii.grpc.Diztl.FetchFileListReq request) {
      return futureUnaryCall(
          getChannel().newCall(getFetchFileListMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<io.github.gravetii.grpc.Diztl.GetFileListResp> getFileList(
        io.github.gravetii.grpc.Diztl.GetFileListReq request) {
      return futureUnaryCall(
          getChannel().newCall(getGetFileListMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<io.github.gravetii.grpc.Diztl.CloseResp> close(
        io.github.gravetii.grpc.Diztl.CloseReq request) {
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
          serviceImpl.search((io.github.gravetii.grpc.Diztl.SearchReq) request,
              (io.grpc.stub.StreamObserver<io.github.gravetii.grpc.Diztl.SearchResp>) responseObserver);
          break;
        case METHODID_UPLOAD:
          serviceImpl.upload((io.github.gravetii.grpc.Diztl.UploadReq) request,
              (io.grpc.stub.StreamObserver<io.github.gravetii.grpc.Diztl.FileChunk>) responseObserver);
          break;
        case METHODID_PING:
          serviceImpl.ping((io.github.gravetii.grpc.Diztl.PingReq) request,
              (io.grpc.stub.StreamObserver<io.github.gravetii.grpc.Diztl.PingResp>) responseObserver);
          break;
        case METHODID_FIND:
          serviceImpl.find((io.github.gravetii.grpc.Diztl.FindReq) request,
              (io.grpc.stub.StreamObserver<io.github.gravetii.grpc.Diztl.FindResp>) responseObserver);
          break;
        case METHODID_DOWNLOAD:
          serviceImpl.download((io.github.gravetii.grpc.Diztl.DownloadReq) request,
              (io.grpc.stub.StreamObserver<io.github.gravetii.grpc.Diztl.DownloadChunk>) responseObserver);
          break;
        case METHODID_GET_USER_DIRS:
          serviceImpl.getUserDirs((io.github.gravetii.grpc.Diztl.UserDirsReq) request,
              (io.grpc.stub.StreamObserver<io.github.gravetii.grpc.Diztl.UserDirsResp>) responseObserver);
          break;
        case METHODID_UPDATE_USER_DIRS:
          serviceImpl.updateUserDirs((io.github.gravetii.grpc.Diztl.UpdateUserDirsReq) request,
              (io.grpc.stub.StreamObserver<io.github.gravetii.grpc.Diztl.UpdateUserDirsResp>) responseObserver);
          break;
        case METHODID_GET_TRACKER:
          serviceImpl.getTracker((io.github.gravetii.grpc.Diztl.GetTrackerReq) request,
              (io.grpc.stub.StreamObserver<io.github.gravetii.grpc.Diztl.GetTrackerResp>) responseObserver);
          break;
        case METHODID_UPDATE_TRACKER:
          serviceImpl.updateTracker((io.github.gravetii.grpc.Diztl.UpdateTrackerReq) request,
              (io.grpc.stub.StreamObserver<io.github.gravetii.grpc.Diztl.UpdateTrackerResp>) responseObserver);
          break;
        case METHODID_INDEX:
          serviceImpl.index((io.github.gravetii.grpc.Diztl.IndexReq) request,
              (io.grpc.stub.StreamObserver<io.github.gravetii.grpc.Diztl.IndexResp>) responseObserver);
          break;
        case METHODID_REGISTER:
          serviceImpl.register((io.github.gravetii.grpc.Diztl.RegisterReq) request,
              (io.grpc.stub.StreamObserver<io.github.gravetii.grpc.Diztl.RegisterResp>) responseObserver);
          break;
        case METHODID_FETCH_FILE_LIST:
          serviceImpl.fetchFileList((io.github.gravetii.grpc.Diztl.FetchFileListReq) request,
              (io.grpc.stub.StreamObserver<io.github.gravetii.grpc.Diztl.FetchFileListResp>) responseObserver);
          break;
        case METHODID_GET_FILE_LIST:
          serviceImpl.getFileList((io.github.gravetii.grpc.Diztl.GetFileListReq) request,
              (io.grpc.stub.StreamObserver<io.github.gravetii.grpc.Diztl.GetFileListResp>) responseObserver);
          break;
        case METHODID_CLOSE:
          serviceImpl.close((io.github.gravetii.grpc.Diztl.CloseReq) request,
              (io.grpc.stub.StreamObserver<io.github.gravetii.grpc.Diztl.CloseResp>) responseObserver);
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
