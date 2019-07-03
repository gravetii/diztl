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
public final class TrackerServiceGrpc {

  private TrackerServiceGrpc() {}

  public static final String SERVICE_NAME = "TrackerService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<Diztl.RegisterReq,
      Diztl.RegisterResp> getRegisterMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Register",
      requestType = Diztl.RegisterReq.class,
      responseType = Diztl.RegisterResp.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<Diztl.RegisterReq,
      Diztl.RegisterResp> getRegisterMethod() {
    io.grpc.MethodDescriptor<Diztl.RegisterReq, Diztl.RegisterResp> getRegisterMethod;
    if ((getRegisterMethod = TrackerServiceGrpc.getRegisterMethod) == null) {
      synchronized (TrackerServiceGrpc.class) {
        if ((getRegisterMethod = TrackerServiceGrpc.getRegisterMethod) == null) {
          TrackerServiceGrpc.getRegisterMethod = getRegisterMethod = 
              io.grpc.MethodDescriptor.<Diztl.RegisterReq, Diztl.RegisterResp>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "TrackerService", "Register"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  Diztl.RegisterReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  Diztl.RegisterResp.getDefaultInstance()))
                  .setSchemaDescriptor(new TrackerServiceMethodDescriptorSupplier("Register"))
                  .build();
          }
        }
     }
     return getRegisterMethod;
  }

  private static volatile io.grpc.MethodDescriptor<Diztl.SearchReq,
      Diztl.SearchResp> getSearchMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Search",
      requestType = Diztl.SearchReq.class,
      responseType = Diztl.SearchResp.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<Diztl.SearchReq,
      Diztl.SearchResp> getSearchMethod() {
    io.grpc.MethodDescriptor<Diztl.SearchReq, Diztl.SearchResp> getSearchMethod;
    if ((getSearchMethod = TrackerServiceGrpc.getSearchMethod) == null) {
      synchronized (TrackerServiceGrpc.class) {
        if ((getSearchMethod = TrackerServiceGrpc.getSearchMethod) == null) {
          TrackerServiceGrpc.getSearchMethod = getSearchMethod = 
              io.grpc.MethodDescriptor.<Diztl.SearchReq, Diztl.SearchResp>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "TrackerService", "Search"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  Diztl.SearchReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  Diztl.SearchResp.getDefaultInstance()))
                  .setSchemaDescriptor(new TrackerServiceMethodDescriptorSupplier("Search"))
                  .build();
          }
        }
     }
     return getSearchMethod;
  }

  private static volatile io.grpc.MethodDescriptor<Diztl.DisconnectReq,
      Diztl.DisconnectResp> getDisconnectMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Disconnect",
      requestType = Diztl.DisconnectReq.class,
      responseType = Diztl.DisconnectResp.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<Diztl.DisconnectReq,
      Diztl.DisconnectResp> getDisconnectMethod() {
    io.grpc.MethodDescriptor<Diztl.DisconnectReq, Diztl.DisconnectResp> getDisconnectMethod;
    if ((getDisconnectMethod = TrackerServiceGrpc.getDisconnectMethod) == null) {
      synchronized (TrackerServiceGrpc.class) {
        if ((getDisconnectMethod = TrackerServiceGrpc.getDisconnectMethod) == null) {
          TrackerServiceGrpc.getDisconnectMethod = getDisconnectMethod = 
              io.grpc.MethodDescriptor.<Diztl.DisconnectReq, Diztl.DisconnectResp>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "TrackerService", "Disconnect"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  Diztl.DisconnectReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  Diztl.DisconnectResp.getDefaultInstance()))
                  .setSchemaDescriptor(new TrackerServiceMethodDescriptorSupplier("Disconnect"))
                  .build();
          }
        }
     }
     return getDisconnectMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static TrackerServiceStub newStub(io.grpc.Channel channel) {
    return new TrackerServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static TrackerServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new TrackerServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static TrackerServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new TrackerServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class TrackerServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void register(Diztl.RegisterReq request,
        io.grpc.stub.StreamObserver<Diztl.RegisterResp> responseObserver) {
      asyncUnimplementedUnaryCall(getRegisterMethod(), responseObserver);
    }

    /**
     */
    public void search(Diztl.SearchReq request,
        io.grpc.stub.StreamObserver<Diztl.SearchResp> responseObserver) {
      asyncUnimplementedUnaryCall(getSearchMethod(), responseObserver);
    }

    /**
     */
    public void disconnect(Diztl.DisconnectReq request,
        io.grpc.stub.StreamObserver<Diztl.DisconnectResp> responseObserver) {
      asyncUnimplementedUnaryCall(getDisconnectMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getRegisterMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                Diztl.RegisterReq,
                Diztl.RegisterResp>(
                  this, METHODID_REGISTER)))
          .addMethod(
            getSearchMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                Diztl.SearchReq,
                Diztl.SearchResp>(
                  this, METHODID_SEARCH)))
          .addMethod(
            getDisconnectMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                Diztl.DisconnectReq,
                Diztl.DisconnectResp>(
                  this, METHODID_DISCONNECT)))
          .build();
    }
  }

  /**
   */
  public static final class TrackerServiceStub extends io.grpc.stub.AbstractStub<TrackerServiceStub> {
    private TrackerServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private TrackerServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TrackerServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new TrackerServiceStub(channel, callOptions);
    }

    /**
     */
    public void register(Diztl.RegisterReq request,
        io.grpc.stub.StreamObserver<Diztl.RegisterResp> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRegisterMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void search(Diztl.SearchReq request,
        io.grpc.stub.StreamObserver<Diztl.SearchResp> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getSearchMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void disconnect(Diztl.DisconnectReq request,
        io.grpc.stub.StreamObserver<Diztl.DisconnectResp> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDisconnectMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class TrackerServiceBlockingStub extends io.grpc.stub.AbstractStub<TrackerServiceBlockingStub> {
    private TrackerServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private TrackerServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TrackerServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new TrackerServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public Diztl.RegisterResp register(Diztl.RegisterReq request) {
      return blockingUnaryCall(
          getChannel(), getRegisterMethod(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<Diztl.SearchResp> search(
        Diztl.SearchReq request) {
      return blockingServerStreamingCall(
          getChannel(), getSearchMethod(), getCallOptions(), request);
    }

    /**
     */
    public Diztl.DisconnectResp disconnect(Diztl.DisconnectReq request) {
      return blockingUnaryCall(
          getChannel(), getDisconnectMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class TrackerServiceFutureStub extends io.grpc.stub.AbstractStub<TrackerServiceFutureStub> {
    private TrackerServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private TrackerServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TrackerServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new TrackerServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<Diztl.RegisterResp> register(
        Diztl.RegisterReq request) {
      return futureUnaryCall(
          getChannel().newCall(getRegisterMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<Diztl.DisconnectResp> disconnect(
        Diztl.DisconnectReq request) {
      return futureUnaryCall(
          getChannel().newCall(getDisconnectMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_REGISTER = 0;
  private static final int METHODID_SEARCH = 1;
  private static final int METHODID_DISCONNECT = 2;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final TrackerServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(TrackerServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_REGISTER:
          serviceImpl.register((Diztl.RegisterReq) request,
              (io.grpc.stub.StreamObserver<Diztl.RegisterResp>) responseObserver);
          break;
        case METHODID_SEARCH:
          serviceImpl.search((Diztl.SearchReq) request,
              (io.grpc.stub.StreamObserver<Diztl.SearchResp>) responseObserver);
          break;
        case METHODID_DISCONNECT:
          serviceImpl.disconnect((Diztl.DisconnectReq) request,
              (io.grpc.stub.StreamObserver<Diztl.DisconnectResp>) responseObserver);
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

  private static abstract class TrackerServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    TrackerServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return Diztl.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("TrackerService");
    }
  }

  private static final class TrackerServiceFileDescriptorSupplier
      extends TrackerServiceBaseDescriptorSupplier {
    TrackerServiceFileDescriptorSupplier() {}
  }

  private static final class TrackerServiceMethodDescriptorSupplier
      extends TrackerServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    TrackerServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (TrackerServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new TrackerServiceFileDescriptorSupplier())
              .addMethod(getRegisterMethod())
              .addMethod(getSearchMethod())
              .addMethod(getDisconnectMethod())
              .build();
        }
      }
    }
    return result;
  }
}
