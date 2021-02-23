// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: diztl.proto

package io.github.gravetii.grpc;

/**
 * Protobuf type {@code DownloadReq}
 */
public  final class DownloadReq extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:DownloadReq)
    DownloadReqOrBuilder {
private static final long serialVersionUID = 0L;
  // Use DownloadReq.newBuilder() to construct.
  private DownloadReq(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private DownloadReq() {
    dir_ = "";
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private DownloadReq(
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
          case 18: {
            io.github.gravetii.grpc.FileMetadata.Builder subBuilder = null;
            if (file_ != null) {
              subBuilder = file_.toBuilder();
            }
            file_ = input.readMessage(io.github.gravetii.grpc.FileMetadata.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(file_);
              file_ = subBuilder.buildPartial();
            }

            break;
          }
          case 26: {
            java.lang.String s = input.readStringRequireUtf8();

            dir_ = s;
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
    return io.github.gravetii.grpc.Diztl.internal_static_DownloadReq_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return io.github.gravetii.grpc.Diztl.internal_static_DownloadReq_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            io.github.gravetii.grpc.DownloadReq.class, io.github.gravetii.grpc.DownloadReq.Builder.class);
  }

  public static final int SOURCE_FIELD_NUMBER = 1;
  private io.github.gravetii.grpc.Node source_;
  /**
   * <code>.Node source = 1;</code>
   */
  public boolean hasSource() {
    return source_ != null;
  }
  /**
   * <code>.Node source = 1;</code>
   */
  public io.github.gravetii.grpc.Node getSource() {
    return source_ == null ? io.github.gravetii.grpc.Node.getDefaultInstance() : source_;
  }
  /**
   * <code>.Node source = 1;</code>
   */
  public io.github.gravetii.grpc.NodeOrBuilder getSourceOrBuilder() {
    return getSource();
  }

  public static final int FILE_FIELD_NUMBER = 2;
  private io.github.gravetii.grpc.FileMetadata file_;
  /**
   * <code>.FileMetadata file = 2;</code>
   */
  public boolean hasFile() {
    return file_ != null;
  }
  /**
   * <code>.FileMetadata file = 2;</code>
   */
  public io.github.gravetii.grpc.FileMetadata getFile() {
    return file_ == null ? io.github.gravetii.grpc.FileMetadata.getDefaultInstance() : file_;
  }
  /**
   * <code>.FileMetadata file = 2;</code>
   */
  public io.github.gravetii.grpc.FileMetadataOrBuilder getFileOrBuilder() {
    return getFile();
  }

  public static final int DIR_FIELD_NUMBER = 3;
  private volatile java.lang.Object dir_;
  /**
   * <pre>
   * the custom downloads directory, if chosen.
   * </pre>
   *
   * <code>string dir = 3;</code>
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
   * <pre>
   * the custom downloads directory, if chosen.
   * </pre>
   *
   * <code>string dir = 3;</code>
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
    if (source_ != null) {
      output.writeMessage(1, getSource());
    }
    if (file_ != null) {
      output.writeMessage(2, getFile());
    }
    if (!getDirBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 3, dir_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (source_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, getSource());
    }
    if (file_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, getFile());
    }
    if (!getDirBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, dir_);
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
    if (!(obj instanceof io.github.gravetii.grpc.DownloadReq)) {
      return super.equals(obj);
    }
    io.github.gravetii.grpc.DownloadReq other = (io.github.gravetii.grpc.DownloadReq) obj;

    boolean result = true;
    result = result && (hasSource() == other.hasSource());
    if (hasSource()) {
      result = result && getSource()
          .equals(other.getSource());
    }
    result = result && (hasFile() == other.hasFile());
    if (hasFile()) {
      result = result && getFile()
          .equals(other.getFile());
    }
    result = result && getDir()
        .equals(other.getDir());
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
    if (hasSource()) {
      hash = (37 * hash) + SOURCE_FIELD_NUMBER;
      hash = (53 * hash) + getSource().hashCode();
    }
    if (hasFile()) {
      hash = (37 * hash) + FILE_FIELD_NUMBER;
      hash = (53 * hash) + getFile().hashCode();
    }
    hash = (37 * hash) + DIR_FIELD_NUMBER;
    hash = (53 * hash) + getDir().hashCode();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static io.github.gravetii.grpc.DownloadReq parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.github.gravetii.grpc.DownloadReq parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.github.gravetii.grpc.DownloadReq parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.github.gravetii.grpc.DownloadReq parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.github.gravetii.grpc.DownloadReq parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.github.gravetii.grpc.DownloadReq parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.github.gravetii.grpc.DownloadReq parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static io.github.gravetii.grpc.DownloadReq parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static io.github.gravetii.grpc.DownloadReq parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static io.github.gravetii.grpc.DownloadReq parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static io.github.gravetii.grpc.DownloadReq parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static io.github.gravetii.grpc.DownloadReq parseFrom(
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
  public static Builder newBuilder(io.github.gravetii.grpc.DownloadReq prototype) {
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
   * Protobuf type {@code DownloadReq}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:DownloadReq)
      io.github.gravetii.grpc.DownloadReqOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return io.github.gravetii.grpc.Diztl.internal_static_DownloadReq_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return io.github.gravetii.grpc.Diztl.internal_static_DownloadReq_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              io.github.gravetii.grpc.DownloadReq.class, io.github.gravetii.grpc.DownloadReq.Builder.class);
    }

    // Construct using io.github.gravetii.grpc.DownloadReq.newBuilder()
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
      if (sourceBuilder_ == null) {
        source_ = null;
      } else {
        source_ = null;
        sourceBuilder_ = null;
      }
      if (fileBuilder_ == null) {
        file_ = null;
      } else {
        file_ = null;
        fileBuilder_ = null;
      }
      dir_ = "";

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return io.github.gravetii.grpc.Diztl.internal_static_DownloadReq_descriptor;
    }

    @java.lang.Override
    public io.github.gravetii.grpc.DownloadReq getDefaultInstanceForType() {
      return io.github.gravetii.grpc.DownloadReq.getDefaultInstance();
    }

    @java.lang.Override
    public io.github.gravetii.grpc.DownloadReq build() {
      io.github.gravetii.grpc.DownloadReq result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public io.github.gravetii.grpc.DownloadReq buildPartial() {
      io.github.gravetii.grpc.DownloadReq result = new io.github.gravetii.grpc.DownloadReq(this);
      if (sourceBuilder_ == null) {
        result.source_ = source_;
      } else {
        result.source_ = sourceBuilder_.build();
      }
      if (fileBuilder_ == null) {
        result.file_ = file_;
      } else {
        result.file_ = fileBuilder_.build();
      }
      result.dir_ = dir_;
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
      if (other instanceof io.github.gravetii.grpc.DownloadReq) {
        return mergeFrom((io.github.gravetii.grpc.DownloadReq)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(io.github.gravetii.grpc.DownloadReq other) {
      if (other == io.github.gravetii.grpc.DownloadReq.getDefaultInstance()) return this;
      if (other.hasSource()) {
        mergeSource(other.getSource());
      }
      if (other.hasFile()) {
        mergeFile(other.getFile());
      }
      if (!other.getDir().isEmpty()) {
        dir_ = other.dir_;
        onChanged();
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
      io.github.gravetii.grpc.DownloadReq parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (io.github.gravetii.grpc.DownloadReq) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private io.github.gravetii.grpc.Node source_ = null;
    private com.google.protobuf.SingleFieldBuilderV3<
        io.github.gravetii.grpc.Node, io.github.gravetii.grpc.Node.Builder, io.github.gravetii.grpc.NodeOrBuilder> sourceBuilder_;
    /**
     * <code>.Node source = 1;</code>
     */
    public boolean hasSource() {
      return sourceBuilder_ != null || source_ != null;
    }
    /**
     * <code>.Node source = 1;</code>
     */
    public io.github.gravetii.grpc.Node getSource() {
      if (sourceBuilder_ == null) {
        return source_ == null ? io.github.gravetii.grpc.Node.getDefaultInstance() : source_;
      } else {
        return sourceBuilder_.getMessage();
      }
    }
    /**
     * <code>.Node source = 1;</code>
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
     * <code>.Node source = 1;</code>
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
     * <code>.Node source = 1;</code>
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
     * <code>.Node source = 1;</code>
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
     * <code>.Node source = 1;</code>
     */
    public io.github.gravetii.grpc.Node.Builder getSourceBuilder() {
      
      onChanged();
      return getSourceFieldBuilder().getBuilder();
    }
    /**
     * <code>.Node source = 1;</code>
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
     * <code>.Node source = 1;</code>
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

    private io.github.gravetii.grpc.FileMetadata file_ = null;
    private com.google.protobuf.SingleFieldBuilderV3<
        io.github.gravetii.grpc.FileMetadata, io.github.gravetii.grpc.FileMetadata.Builder, io.github.gravetii.grpc.FileMetadataOrBuilder> fileBuilder_;
    /**
     * <code>.FileMetadata file = 2;</code>
     */
    public boolean hasFile() {
      return fileBuilder_ != null || file_ != null;
    }
    /**
     * <code>.FileMetadata file = 2;</code>
     */
    public io.github.gravetii.grpc.FileMetadata getFile() {
      if (fileBuilder_ == null) {
        return file_ == null ? io.github.gravetii.grpc.FileMetadata.getDefaultInstance() : file_;
      } else {
        return fileBuilder_.getMessage();
      }
    }
    /**
     * <code>.FileMetadata file = 2;</code>
     */
    public Builder setFile(io.github.gravetii.grpc.FileMetadata value) {
      if (fileBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        file_ = value;
        onChanged();
      } else {
        fileBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>.FileMetadata file = 2;</code>
     */
    public Builder setFile(
        io.github.gravetii.grpc.FileMetadata.Builder builderForValue) {
      if (fileBuilder_ == null) {
        file_ = builderForValue.build();
        onChanged();
      } else {
        fileBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>.FileMetadata file = 2;</code>
     */
    public Builder mergeFile(io.github.gravetii.grpc.FileMetadata value) {
      if (fileBuilder_ == null) {
        if (file_ != null) {
          file_ =
            io.github.gravetii.grpc.FileMetadata.newBuilder(file_).mergeFrom(value).buildPartial();
        } else {
          file_ = value;
        }
        onChanged();
      } else {
        fileBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>.FileMetadata file = 2;</code>
     */
    public Builder clearFile() {
      if (fileBuilder_ == null) {
        file_ = null;
        onChanged();
      } else {
        file_ = null;
        fileBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>.FileMetadata file = 2;</code>
     */
    public io.github.gravetii.grpc.FileMetadata.Builder getFileBuilder() {
      
      onChanged();
      return getFileFieldBuilder().getBuilder();
    }
    /**
     * <code>.FileMetadata file = 2;</code>
     */
    public io.github.gravetii.grpc.FileMetadataOrBuilder getFileOrBuilder() {
      if (fileBuilder_ != null) {
        return fileBuilder_.getMessageOrBuilder();
      } else {
        return file_ == null ?
            io.github.gravetii.grpc.FileMetadata.getDefaultInstance() : file_;
      }
    }
    /**
     * <code>.FileMetadata file = 2;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        io.github.gravetii.grpc.FileMetadata, io.github.gravetii.grpc.FileMetadata.Builder, io.github.gravetii.grpc.FileMetadataOrBuilder> 
        getFileFieldBuilder() {
      if (fileBuilder_ == null) {
        fileBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            io.github.gravetii.grpc.FileMetadata, io.github.gravetii.grpc.FileMetadata.Builder, io.github.gravetii.grpc.FileMetadataOrBuilder>(
                getFile(),
                getParentForChildren(),
                isClean());
        file_ = null;
      }
      return fileBuilder_;
    }

    private java.lang.Object dir_ = "";
    /**
     * <pre>
     * the custom downloads directory, if chosen.
     * </pre>
     *
     * <code>string dir = 3;</code>
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
     * <pre>
     * the custom downloads directory, if chosen.
     * </pre>
     *
     * <code>string dir = 3;</code>
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
     * <pre>
     * the custom downloads directory, if chosen.
     * </pre>
     *
     * <code>string dir = 3;</code>
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
     * <pre>
     * the custom downloads directory, if chosen.
     * </pre>
     *
     * <code>string dir = 3;</code>
     */
    public Builder clearDir() {
      
      dir_ = getDefaultInstance().getDir();
      onChanged();
      return this;
    }
    /**
     * <pre>
     * the custom downloads directory, if chosen.
     * </pre>
     *
     * <code>string dir = 3;</code>
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


    // @@protoc_insertion_point(builder_scope:DownloadReq)
  }

  // @@protoc_insertion_point(class_scope:DownloadReq)
  private static final io.github.gravetii.grpc.DownloadReq DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new io.github.gravetii.grpc.DownloadReq();
  }

  public static io.github.gravetii.grpc.DownloadReq getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<DownloadReq>
      PARSER = new com.google.protobuf.AbstractParser<DownloadReq>() {
    @java.lang.Override
    public DownloadReq parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new DownloadReq(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<DownloadReq> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<DownloadReq> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public io.github.gravetii.grpc.DownloadReq getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

