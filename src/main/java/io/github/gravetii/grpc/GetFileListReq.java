// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: diztl.proto

package io.github.gravetii.grpc;

/**
 * Protobuf type {@code GetFileListReq}
 */
public  final class GetFileListReq extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:GetFileListReq)
    GetFileListReqOrBuilder {
private static final long serialVersionUID = 0L;
  // Use GetFileListReq.newBuilder() to construct.
  private GetFileListReq(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private GetFileListReq() {
    dir_ = "";
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private GetFileListReq(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    int mutable_bitField0_ = 0;
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          case 10: {
            java.lang.String s = input.readStringRequireUtf8();

            dir_ = s;
            break;
          }
          case 18: {
            io.github.gravetii.grpc.Node.Builder subBuilder = null;
            if (source_ != null) {
              subBuilder = source_.toBuilder();
            }
            source_ = input.readMessage(io.github.gravetii.grpc.Node.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(source_);
              source_ = subBuilder.buildPartial();
            }

            break;
          }
          default: {
            if (!parseUnknownFieldProto3(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return io.github.gravetii.grpc.Diztl.internal_static_GetFileListReq_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return io.github.gravetii.grpc.Diztl.internal_static_GetFileListReq_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            io.github.gravetii.grpc.GetFileListReq.class, io.github.gravetii.grpc.GetFileListReq.Builder.class);
  }

  public static final int DIR_FIELD_NUMBER = 1;
  private volatile java.lang.Object dir_;
  /**
   * <code>string dir = 1;</code>
   */
  public java.lang.String getDir() {
    java.lang.Object ref = dir_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      dir_ = s;
      return s;
    }
  }
  /**
   * <code>string dir = 1;</code>
   */
  public com.google.protobuf.ByteString
      getDirBytes() {
    java.lang.Object ref = dir_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      dir_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int SOURCE_FIELD_NUMBER = 2;
  private io.github.gravetii.grpc.Node source_;
  /**
   * <code>.Node source = 2;</code>
   */
  public boolean hasSource() {
    return source_ != null;
  }
  /**
   * <code>.Node source = 2;</code>
   */
  public io.github.gravetii.grpc.Node getSource() {
    return source_ == null ? io.github.gravetii.grpc.Node.getDefaultInstance() : source_;
  }
  /**
   * <code>.Node source = 2;</code>
   */
  public io.github.gravetii.grpc.NodeOrBuilder getSourceOrBuilder() {
    return getSource();
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (!getDirBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, dir_);
    }
    if (source_ != null) {
      output.writeMessage(2, getSource());
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!getDirBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, dir_);
    }
    if (source_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, getSource());
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof io.github.gravetii.grpc.GetFileListReq)) {
      return super.equals(obj);
    }
    io.github.gravetii.grpc.GetFileListReq other = (io.github.gravetii.grpc.GetFileListReq) obj;

    boolean result = true;
    result = result && getDir()
        .equals(other.getDir());
    result = result && (hasSource() == other.hasSource());
    if (hasSource()) {
      result = result && getSource()
          .equals(other.getSource());
    }
    result = result && unknownFields.equals(other.unknownFields);
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + DIR_FIELD_NUMBER;
    hash = (53 * hash) + getDir().hashCode();
    if (hasSource()) {
      hash = (37 * hash) + SOURCE_FIELD_NUMBER;
      hash = (53 * hash) + getSource().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static io.github.gravetii.grpc.GetFileListReq parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.github.gravetii.grpc.GetFileListReq parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.github.gravetii.grpc.GetFileListReq parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.github.gravetii.grpc.GetFileListReq parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.github.gravetii.grpc.GetFileListReq parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.github.gravetii.grpc.GetFileListReq parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.github.gravetii.grpc.GetFileListReq parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static io.github.gravetii.grpc.GetFileListReq parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static io.github.gravetii.grpc.GetFileListReq parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static io.github.gravetii.grpc.GetFileListReq parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static io.github.gravetii.grpc.GetFileListReq parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static io.github.gravetii.grpc.GetFileListReq parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(io.github.gravetii.grpc.GetFileListReq prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code GetFileListReq}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:GetFileListReq)
      io.github.gravetii.grpc.GetFileListReqOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return io.github.gravetii.grpc.Diztl.internal_static_GetFileListReq_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return io.github.gravetii.grpc.Diztl.internal_static_GetFileListReq_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              io.github.gravetii.grpc.GetFileListReq.class, io.github.gravetii.grpc.GetFileListReq.Builder.class);
    }

    // Construct using io.github.gravetii.grpc.GetFileListReq.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      dir_ = "";

      if (sourceBuilder_ == null) {
        source_ = null;
      } else {
        source_ = null;
        sourceBuilder_ = null;
      }
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return io.github.gravetii.grpc.Diztl.internal_static_GetFileListReq_descriptor;
    }

    @java.lang.Override
    public io.github.gravetii.grpc.GetFileListReq getDefaultInstanceForType() {
      return io.github.gravetii.grpc.GetFileListReq.getDefaultInstance();
    }

    @java.lang.Override
    public io.github.gravetii.grpc.GetFileListReq build() {
      io.github.gravetii.grpc.GetFileListReq result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public io.github.gravetii.grpc.GetFileListReq buildPartial() {
      io.github.gravetii.grpc.GetFileListReq result = new io.github.gravetii.grpc.GetFileListReq(this);
      result.dir_ = dir_;
      if (sourceBuilder_ == null) {
        result.source_ = source_;
      } else {
        result.source_ = sourceBuilder_.build();
      }
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return (Builder) super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return (Builder) super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return (Builder) super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return (Builder) super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return (Builder) super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return (Builder) super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof io.github.gravetii.grpc.GetFileListReq) {
        return mergeFrom((io.github.gravetii.grpc.GetFileListReq)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(io.github.gravetii.grpc.GetFileListReq other) {
      if (other == io.github.gravetii.grpc.GetFileListReq.getDefaultInstance()) return this;
      if (!other.getDir().isEmpty()) {
        dir_ = other.dir_;
        onChanged();
      }
      if (other.hasSource()) {
        mergeSource(other.getSource());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      io.github.gravetii.grpc.GetFileListReq parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (io.github.gravetii.grpc.GetFileListReq) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private java.lang.Object dir_ = "";
    /**
     * <code>string dir = 1;</code>
     */
    public java.lang.String getDir() {
      java.lang.Object ref = dir_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        dir_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string dir = 1;</code>
     */
    public com.google.protobuf.ByteString
        getDirBytes() {
      java.lang.Object ref = dir_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        dir_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string dir = 1;</code>
     */
    public Builder setDir(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      dir_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string dir = 1;</code>
     */
    public Builder clearDir() {
      
      dir_ = getDefaultInstance().getDir();
      onChanged();
      return this;
    }
    /**
     * <code>string dir = 1;</code>
     */
    public Builder setDirBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      dir_ = value;
      onChanged();
      return this;
    }

    private io.github.gravetii.grpc.Node source_ = null;
    private com.google.protobuf.SingleFieldBuilderV3<
        io.github.gravetii.grpc.Node, io.github.gravetii.grpc.Node.Builder, io.github.gravetii.grpc.NodeOrBuilder> sourceBuilder_;
    /**
     * <code>.Node source = 2;</code>
     */
    public boolean hasSource() {
      return sourceBuilder_ != null || source_ != null;
    }
    /**
     * <code>.Node source = 2;</code>
     */
    public io.github.gravetii.grpc.Node getSource() {
      if (sourceBuilder_ == null) {
        return source_ == null ? io.github.gravetii.grpc.Node.getDefaultInstance() : source_;
      } else {
        return sourceBuilder_.getMessage();
      }
    }
    /**
     * <code>.Node source = 2;</code>
     */
    public Builder setSource(io.github.gravetii.grpc.Node value) {
      if (sourceBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        source_ = value;
        onChanged();
      } else {
        sourceBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>.Node source = 2;</code>
     */
    public Builder setSource(
        io.github.gravetii.grpc.Node.Builder builderForValue) {
      if (sourceBuilder_ == null) {
        source_ = builderForValue.build();
        onChanged();
      } else {
        sourceBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>.Node source = 2;</code>
     */
    public Builder mergeSource(io.github.gravetii.grpc.Node value) {
      if (sourceBuilder_ == null) {
        if (source_ != null) {
          source_ =
            io.github.gravetii.grpc.Node.newBuilder(source_).mergeFrom(value).buildPartial();
        } else {
          source_ = value;
        }
        onChanged();
      } else {
        sourceBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>.Node source = 2;</code>
     */
    public Builder clearSource() {
      if (sourceBuilder_ == null) {
        source_ = null;
        onChanged();
      } else {
        source_ = null;
        sourceBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>.Node source = 2;</code>
     */
    public io.github.gravetii.grpc.Node.Builder getSourceBuilder() {
      
      onChanged();
      return getSourceFieldBuilder().getBuilder();
    }
    /**
     * <code>.Node source = 2;</code>
     */
    public io.github.gravetii.grpc.NodeOrBuilder getSourceOrBuilder() {
      if (sourceBuilder_ != null) {
        return sourceBuilder_.getMessageOrBuilder();
      } else {
        return source_ == null ?
            io.github.gravetii.grpc.Node.getDefaultInstance() : source_;
      }
    }
    /**
     * <code>.Node source = 2;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        io.github.gravetii.grpc.Node, io.github.gravetii.grpc.Node.Builder, io.github.gravetii.grpc.NodeOrBuilder> 
        getSourceFieldBuilder() {
      if (sourceBuilder_ == null) {
        sourceBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            io.github.gravetii.grpc.Node, io.github.gravetii.grpc.Node.Builder, io.github.gravetii.grpc.NodeOrBuilder>(
                getSource(),
                getParentForChildren(),
                isClean());
        source_ = null;
      }
      return sourceBuilder_;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFieldsProto3(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:GetFileListReq)
  }

  // @@protoc_insertion_point(class_scope:GetFileListReq)
  private static final io.github.gravetii.grpc.GetFileListReq DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new io.github.gravetii.grpc.GetFileListReq();
  }

  public static io.github.gravetii.grpc.GetFileListReq getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<GetFileListReq>
      PARSER = new com.google.protobuf.AbstractParser<GetFileListReq>() {
    @java.lang.Override
    public GetFileListReq parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new GetFileListReq(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<GetFileListReq> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<GetFileListReq> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public io.github.gravetii.grpc.GetFileListReq getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
