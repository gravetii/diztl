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
  }

  private static final int METHODID_SEARCH = 0;
  private static final int METHODID_UPLOAD = 1;
  private static final int METHODID_PING = 2;

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
              .build();
        }
      }
    }
    return result;
  }
}
