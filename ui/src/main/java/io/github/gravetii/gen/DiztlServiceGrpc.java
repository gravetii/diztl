package io.github.gravetii.gen;

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
    value = "by gRPC proto compiler (version 1.18.0)",
    comments = "Source: diztl.proto")
public final class DiztlServiceGrpc {

  private DiztlServiceGrpc() {}

  public static final String SERVICE_NAME = "DiztlService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<Diztl.SearchReq,
      Diztl.SearchResp> getSearchMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Search",
      requestType = Diztl.SearchReq.class,
      responseType = Diztl.SearchResp.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<Diztl.SearchReq,
      Diztl.SearchResp> getSearchMethod() {
    io.grpc.MethodDescriptor<Diztl.SearchReq, Diztl.SearchResp> getSearchMethod;
    if ((getSearchMethod = DiztlServiceGrpc.getSearchMethod) == null) {
      synchronized (DiztlServiceGrpc.class) {
        if ((getSearchMethod = DiztlServiceGrpc.getSearchMethod) == null) {
          DiztlServiceGrpc.getSearchMethod = getSearchMethod = 
              io.grpc.MethodDescriptor.<Diztl.SearchReq, Diztl.SearchResp>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "DiztlService", "Search"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  Diztl.SearchReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  Diztl.SearchResp.getDefaultInstance()))
                  .setSchemaDescriptor(new DiztlServiceMethodDescriptorSupplier("Search"))
                  .build();
          }
        }
     }
     return getSearchMethod;
  }

  private static volatile io.grpc.MethodDescriptor<Diztl.UploadReq,
      Diztl.FileChunk> getUploadMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Upload",
      requestType = Diztl.UploadReq.class,
      responseType = Diztl.FileChunk.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<Diztl.UploadReq,
      Diztl.FileChunk> getUploadMethod() {
    io.grpc.MethodDescriptor<Diztl.UploadReq, Diztl.FileChunk> getUploadMethod;
    if ((getUploadMethod = DiztlServiceGrpc.getUploadMethod) == null) {
      synchronized (DiztlServiceGrpc.class) {
        if ((getUploadMethod = DiztlServiceGrpc.getUploadMethod) == null) {
          DiztlServiceGrpc.getUploadMethod = getUploadMethod = 
              io.grpc.MethodDescriptor.<Diztl.UploadReq, Diztl.FileChunk>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "DiztlService", "Upload"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  Diztl.UploadReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  Diztl.FileChunk.getDefaultInstance()))
                  .setSchemaDescriptor(new DiztlServiceMethodDescriptorSupplier("Upload"))
                  .build();
          }
        }
     }
     return getUploadMethod;
  }

  private static volatile io.grpc.MethodDescriptor<Diztl.PingReq,
      Diztl.PingResp> getPingMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Ping",
      requestType = Diztl.PingReq.class,
      responseType = Diztl.PingResp.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<Diztl.PingReq,
      Diztl.PingResp> getPingMethod() {
    io.grpc.MethodDescriptor<Diztl.PingReq, Diztl.PingResp> getPingMethod;
    if ((getPingMethod = DiztlServiceGrpc.getPingMethod) == null) {
      synchronized (DiztlServiceGrpc.class) {
        if ((getPingMethod = DiztlServiceGrpc.getPingMethod) == null) {
          DiztlServiceGrpc.getPingMethod = getPingMethod = 
              io.grpc.MethodDescriptor.<Diztl.PingReq, Diztl.PingResp>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "DiztlService", "Ping"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  Diztl.PingReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  Diztl.PingResp.getDefaultInstance()))
                  .setSchemaDescriptor(new DiztlServiceMethodDescriptorSupplier("Ping"))
                  .build();
          }
        }
     }
     return getPingMethod;
  }

  private static volatile io.grpc.MethodDescriptor<Diztl.FindReq,
      Diztl.FindResp> getFindMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Find",
      requestType = Diztl.FindReq.class,
      responseType = Diztl.FindResp.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<Diztl.FindReq,
      Diztl.FindResp> getFindMethod() {
    io.grpc.MethodDescriptor<Diztl.FindReq, Diztl.FindResp> getFindMethod;
    if ((getFindMethod = DiztlServiceGrpc.getFindMethod) == null) {
      synchronized (DiztlServiceGrpc.class) {
        if ((getFindMethod = DiztlServiceGrpc.getFindMethod) == null) {
          DiztlServiceGrpc.getFindMethod = getFindMethod = 
              io.grpc.MethodDescriptor.<Diztl.FindReq, Diztl.FindResp>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "DiztlService", "Find"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  Diztl.FindReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  Diztl.FindResp.getDefaultInstance()))
                  .setSchemaDescriptor(new DiztlServiceMethodDescriptorSupplier("Find"))
                  .build();
          }
        }
     }
     return getFindMethod;
  }

  private static volatile io.grpc.MethodDescriptor<Diztl.DownloadReq,
      Diztl.DownloadResp> getDownloadMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Download",
      requestType = Diztl.DownloadReq.class,
      responseType = Diztl.DownloadResp.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<Diztl.DownloadReq,
      Diztl.DownloadResp> getDownloadMethod() {
    io.grpc.MethodDescriptor<Diztl.DownloadReq, Diztl.DownloadResp> getDownloadMethod;
    if ((getDownloadMethod = DiztlServiceGrpc.getDownloadMethod) == null) {
      synchronized (DiztlServiceGrpc.class) {
        if ((getDownloadMethod = DiztlServiceGrpc.getDownloadMethod) == null) {
          DiztlServiceGrpc.getDownloadMethod = getDownloadMethod = 
              io.grpc.MethodDescriptor.<Diztl.DownloadReq, Diztl.DownloadResp>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "DiztlService", "Download"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  Diztl.DownloadReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  Diztl.DownloadResp.getDefaultInstance()))
                  .setSchemaDescriptor(new DiztlServiceMethodDescriptorSupplier("Download"))
                  .build();
          }
        }
     }
     return getDownloadMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static DiztlServiceStub newStub(io.grpc.Channel channel) {
    return new DiztlServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static DiztlServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new DiztlServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static DiztlServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new DiztlServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class DiztlServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void search(Diztl.SearchReq request,
        io.grpc.stub.StreamObserver<Diztl.SearchResp> responseObserver) {
      asyncUnimplementedUnaryCall(getSearchMethod(), responseObserver);
    }

    /**
     */
    public void upload(Diztl.UploadReq request,
        io.grpc.stub.StreamObserver<Diztl.FileChunk> responseObserver) {
      asyncUnimplementedUnaryCall(getUploadMethod(), responseObserver);
    }

    /**
     */
    public void ping(Diztl.PingReq request,
        io.grpc.stub.StreamObserver<Diztl.PingResp> responseObserver) {
      asyncUnimplementedUnaryCall(getPingMethod(), responseObserver);
    }

    /**
     */
    public void find(Diztl.FindReq request,
        io.grpc.stub.StreamObserver<Diztl.FindResp> responseObserver) {
      asyncUnimplementedUnaryCall(getFindMethod(), responseObserver);
    }

    /**
     */
    public void download(Diztl.DownloadReq request,
        io.grpc.stub.StreamObserver<Diztl.DownloadResp> responseObserver) {
      asyncUnimplementedUnaryCall(getDownloadMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getSearchMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                Diztl.SearchReq,
                Diztl.SearchResp>(
                  this, METHODID_SEARCH)))
          .addMethod(
            getUploadMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                Diztl.UploadReq,
                Diztl.FileChunk>(
                  this, METHODID_UPLOAD)))
          .addMethod(
            getPingMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                Diztl.PingReq,
                Diztl.PingResp>(
                  this, METHODID_PING)))
          .addMethod(
            getFindMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                Diztl.FindReq,
                Diztl.FindResp>(
                  this, METHODID_FIND)))
          .addMethod(
            getDownloadMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                Diztl.DownloadReq,
                Diztl.DownloadResp>(
                  this, METHODID_DOWNLOAD)))
          .build();
    }
  }

  /**
   */
  public static final class DiztlServiceStub extends io.grpc.stub.AbstractStub<DiztlServiceStub> {
    private DiztlServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private DiztlServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DiztlServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new DiztlServiceStub(channel, callOptions);
    }

    /**
     */
    public void search(Diztl.SearchReq request,
        io.grpc.stub.StreamObserver<Diztl.SearchResp> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSearchMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void upload(Diztl.UploadReq request,
        io.grpc.stub.StreamObserver<Diztl.FileChunk> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getUploadMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void ping(Diztl.PingReq request,
        io.grpc.stub.StreamObserver<Diztl.PingResp> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getPingMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void find(Diztl.FindReq request,
        io.grpc.stub.StreamObserver<Diztl.FindResp> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getFindMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void download(Diztl.DownloadReq request,
        io.grpc.stub.StreamObserver<Diztl.DownloadResp> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDownloadMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class DiztlServiceBlockingStub extends io.grpc.stub.AbstractStub<DiztlServiceBlockingStub> {
    private DiztlServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private DiztlServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DiztlServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new DiztlServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public Diztl.SearchResp search(Diztl.SearchReq request) {
      return blockingUnaryCall(
          getChannel(), getSearchMethod(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<Diztl.FileChunk> upload(
        Diztl.UploadReq request) {
      return blockingServerStreamingCall(
          getChannel(), getUploadMethod(), getCallOptions(), request);
    }

    /**
     */
    public Diztl.PingResp ping(Diztl.PingReq request) {
      return blockingUnaryCall(
          getChannel(), getPingMethod(), getCallOptions(), request);
    }

    /**
     */
    public Diztl.FindResp find(Diztl.FindReq request) {
      return blockingUnaryCall(
          getChannel(), getFindMethod(), getCallOptions(), request);
    }

    /**
     */
    public Diztl.DownloadResp download(Diztl.DownloadReq request) {
      return blockingUnaryCall(
          getChannel(), getDownloadMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class DiztlServiceFutureStub extends io.grpc.stub.AbstractStub<DiztlServiceFutureStub> {
    private DiztlServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private DiztlServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DiztlServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new DiztlServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<Diztl.SearchResp> search(
        Diztl.SearchReq request) {
      return futureUnaryCall(
          getChannel().newCall(getSearchMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<Diztl.PingResp> ping(
        Diztl.PingReq request) {
      return futureUnaryCall(
          getChannel().newCall(getPingMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<Diztl.FindResp> find(
        Diztl.FindReq request) {
      return futureUnaryCall(
          getChannel().newCall(getFindMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<Diztl.DownloadResp> download(
        Diztl.DownloadReq request) {
      return futureUnaryCall(
          getChannel().newCall(getDownloadMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SEARCH = 0;
  private static final int METHODID_UPLOAD = 1;
  private static final int METHODID_PING = 2;
  private static final int METHODID_FIND = 3;
  private static final int METHODID_DOWNLOAD = 4;

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
          serviceImpl.search((Diztl.SearchReq) request,
              (io.grpc.stub.StreamObserver<Diztl.SearchResp>) responseObserver);
          break;
        case METHODID_UPLOAD:
          serviceImpl.upload((Diztl.UploadReq) request,
              (io.grpc.stub.StreamObserver<Diztl.FileChunk>) responseObserver);
          break;
        case METHODID_PING:
          serviceImpl.ping((Diztl.PingReq) request,
              (io.grpc.stub.StreamObserver<Diztl.PingResp>) responseObserver);
          break;
        case METHODID_FIND:
          serviceImpl.find((Diztl.FindReq) request,
              (io.grpc.stub.StreamObserver<Diztl.FindResp>) responseObserver);
          break;
        case METHODID_DOWNLOAD:
          serviceImpl.download((Diztl.DownloadReq) request,
              (io.grpc.stub.StreamObserver<Diztl.DownloadResp>) responseObserver);
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
      return Diztl.getDescriptor();
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
              .build();
        }
      }
    }
    return result;
  }
}
