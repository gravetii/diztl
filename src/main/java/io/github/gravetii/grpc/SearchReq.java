// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: diztl.proto

package io.github.gravetii.grpc;

/**
 * Protobuf type {@code SearchReq}
 */
public  final class SearchReq extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:SearchReq)
    SearchReqOrBuilder {
private static final long serialVersionUID = 0L;
  // Use SearchReq.newBuilder() to construct.
  private SearchReq(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private SearchReq() {
    query_ = "";
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private SearchReq(
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

            query_ = s;
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
          case 26: {
            io.github.gravetii.grpc.FileConstraint.Builder subBuilder = null;
            if (constraint_ != null) {
              subBuilder = constraint_.toBuilder();
            }
            constraint_ = input.readMessage(io.github.gravetii.grpc.FileConstraint.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(constraint_);
              constraint_ = subBuilder.buildPartial();
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
    return io.github.gravetii.grpc.Diztl.internal_static_SearchReq_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return io.github.gravetii.grpc.Diztl.internal_static_SearchReq_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            io.github.gravetii.grpc.SearchReq.class, io.github.gravetii.grpc.SearchReq.Builder.class);
  }

  public static final int QUERY_FIELD_NUMBER = 1;
  private volatile java.lang.Object query_;
  /**
   * <pre>
   * The filename to search for.
   * </pre>
   *
   * <code>string query = 1;</code>
   */
  public java.lang.String getQuery() {
    java.lang.Object ref = query_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      query_ = s;
      return s;
    }
  }
  /**
   * <pre>
   * The filename to search for.
   * </pre>
   *
   * <code>string query = 1;</code>
   */
  public com.google.protobuf.ByteString
      getQueryBytes() {
    java.lang.Object ref = query_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      query_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int SOURCE_FIELD_NUMBER = 2;
  private io.github.gravetii.grpc.Node source_;
  /**
   * <pre>
   * The origin node of this request.
   * </pre>
   *
   * <code>.Node source = 2;</code>
   */
  public boolean hasSource() {
    return source_ != null;
  }
  /**
   * <pre>
   * The origin node of this request.
   * </pre>
   *
   * <code>.Node source = 2;</code>
   */
  public io.github.gravetii.grpc.Node getSource() {
    return source_ == null ? io.github.gravetii.grpc.Node.getDefaultInstance() : source_;
  }
  /**
   * <pre>
   * The origin node of this request.
   * </pre>
   *
   * <code>.Node source = 2;</code>
   */
  public io.github.gravetii.grpc.NodeOrBuilder getSourceOrBuilder() {
    return getSource();
  }

  public static final int CONSTRAINT_FIELD_NUMBER = 3;
  private io.github.gravetii.grpc.FileConstraint constraint_;
  /**
   * <code>.FileConstraint constraint = 3;</code>
   */
  public boolean hasConstraint() {
    return constraint_ != null;
  }
  /**
   * <code>.FileConstraint constraint = 3;</code>
   */
  public io.github.gravetii.grpc.FileConstraint getConstraint() {
    return constraint_ == null ? io.github.gravetii.grpc.FileConstraint.getDefaultInstance() : constraint_;
  }
  /**
   * <code>.FileConstraint constraint = 3;</code>
   */
  public io.github.gravetii.grpc.FileConstraintOrBuilder getConstraintOrBuilder() {
    return getConstraint();
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
    if (!getQueryBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, query_);
    }
    if (source_ != null) {
      output.writeMessage(2, getSource());
    }
    if (constraint_ != null) {
      output.writeMessage(3, getConstraint());
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!getQueryBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, query_);
    }
    if (source_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, getSource());
    }
    if (constraint_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(3, getConstraint());
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
    if (!(obj instanceof io.github.gravetii.grpc.SearchReq)) {
      return super.equals(obj);
    }
    io.github.gravetii.grpc.SearchReq other = (io.github.gravetii.grpc.SearchReq) obj;

    boolean result = true;
    result = result && getQuery()
        .equals(other.getQuery());
    result = result && (hasSource() == other.hasSource());
    if (hasSource()) {
      result = result && getSource()
          .equals(other.getSource());
    }
    result = result && (hasConstraint() == other.hasConstraint());
    if (hasConstraint()) {
      result = result && getConstraint()
          .equals(other.getConstraint());
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
    hash = (37 * hash) + QUERY_FIELD_NUMBER;
    hash = (53 * hash) + getQuery().hashCode();
    if (hasSource()) {
      hash = (37 * hash) + SOURCE_FIELD_NUMBER;
      hash = (53 * hash) + getSource().hashCode();
    }
    if (hasConstraint()) {
      hash = (37 * hash) + CONSTRAINT_FIELD_NUMBER;
      hash = (53 * hash) + getConstraint().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static io.github.gravetii.grpc.SearchReq parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.github.gravetii.grpc.SearchReq parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.github.gravetii.grpc.SearchReq parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.github.gravetii.grpc.SearchReq parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.github.gravetii.grpc.SearchReq parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.github.gravetii.grpc.SearchReq parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.github.gravetii.grpc.SearchReq parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static io.github.gravetii.grpc.SearchReq parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static io.github.gravetii.grpc.SearchReq parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static io.github.gravetii.grpc.SearchReq parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static io.github.gravetii.grpc.SearchReq parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static io.github.gravetii.grpc.SearchReq parseFrom(
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
  public static Builder newBuilder(io.github.gravetii.grpc.SearchReq prototype) {
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
   * Protobuf type {@code SearchReq}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:SearchReq)
      io.github.gravetii.grpc.SearchReqOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return io.github.gravetii.grpc.Diztl.internal_static_SearchReq_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return io.github.gravetii.grpc.Diztl.internal_static_SearchReq_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              io.github.gravetii.grpc.SearchReq.class, io.github.gravetii.grpc.SearchReq.Builder.class);
    }

    // Construct using io.github.gravetii.grpc.SearchReq.newBuilder()
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
      query_ = "";

      if (sourceBuilder_ == null) {
        source_ = null;
      } else {
        source_ = null;
        sourceBuilder_ = null;
      }
      if (constraintBuilder_ == null) {
        constraint_ = null;
      } else {
        constraint_ = null;
        constraintBuilder_ = null;
      }
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return io.github.gravetii.grpc.Diztl.internal_static_SearchReq_descriptor;
    }

    @java.lang.Override
    public io.github.gravetii.grpc.SearchReq getDefaultInstanceForType() {
      return io.github.gravetii.grpc.SearchReq.getDefaultInstance();
    }

    @java.lang.Override
    public io.github.gravetii.grpc.SearchReq build() {
      io.github.gravetii.grpc.SearchReq result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public io.github.gravetii.grpc.SearchReq buildPartial() {
      io.github.gravetii.grpc.SearchReq result = new io.github.gravetii.grpc.SearchReq(this);
      result.query_ = query_;
      if (sourceBuilder_ == null) {
        result.source_ = source_;
      } else {
        result.source_ = sourceBuilder_.build();
      }
      if (constraintBuilder_ == null) {
        result.constraint_ = constraint_;
      } else {
        result.constraint_ = constraintBuilder_.build();
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
      if (other instanceof io.github.gravetii.grpc.SearchReq) {
        return mergeFrom((io.github.gravetii.grpc.SearchReq)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(io.github.gravetii.grpc.SearchReq other) {
      if (other == io.github.gravetii.grpc.SearchReq.getDefaultInstance()) return this;
      if (!other.getQuery().isEmpty()) {
        query_ = other.query_;
        onChanged();
      }
      if (other.hasSource()) {
        mergeSource(other.getSource());
      }
      if (other.hasConstraint()) {
        mergeConstraint(other.getConstraint());
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
      io.github.gravetii.grpc.SearchReq parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (io.github.gravetii.grpc.SearchReq) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private java.lang.Object query_ = "";
    /**
     * <pre>
     * The filename to search for.
     * </pre>
     *
     * <code>string query = 1;</code>
     */
    public java.lang.String getQuery() {
      java.lang.Object ref = query_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        query_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <pre>
     * The filename to search for.
     * </pre>
     *
     * <code>string query = 1;</code>
     */
    public com.google.protobuf.ByteString
        getQueryBytes() {
      java.lang.Object ref = query_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        query_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <pre>
     * The filename to search for.
     * </pre>
     *
     * <code>string query = 1;</code>
     */
    public Builder setQuery(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      query_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * The filename to search for.
     * </pre>
     *
     * <code>string query = 1;</code>
     */
    public Builder clearQuery() {
      
      query_ = getDefaultInstance().getQuery();
      onChanged();
      return this;
    }
    /**
     * <pre>
     * The filename to search for.
     * </pre>
     *
     * <code>string query = 1;</code>
     */
    public Builder setQueryBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      query_ = value;
      onChanged();
      return this;
    }

    private io.github.gravetii.grpc.Node source_ = null;
    private com.google.protobuf.SingleFieldBuilderV3<
        io.github.gravetii.grpc.Node, io.github.gravetii.grpc.Node.Builder, io.github.gravetii.grpc.NodeOrBuilder> sourceBuilder_;
    /**
     * <pre>
     * The origin node of this request.
     * </pre>
     *
     * <code>.Node source = 2;</code>
     */
    public boolean hasSource() {
      return sourceBuilder_ != null || source_ != null;
    }
    /**
     * <pre>
     * The origin node of this request.
     * </pre>
     *
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
     * <pre>
     * The origin node of this request.
     * </pre>
     *
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
     * <pre>
     * The origin node of this request.
     * </pre>
     *
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
     * <pre>
     * The origin node of this request.
     * </pre>
     *
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
     * <pre>
     * The origin node of this request.
     * </pre>
     *
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
     * <pre>
     * The origin node of this request.
     * </pre>
     *
     * <code>.Node source = 2;</code>
     */
    public io.github.gravetii.grpc.Node.Builder getSourceBuilder() {
      
      onChanged();
      return getSourceFieldBuilder().getBuilder();
    }
    /**
     * <pre>
     * The origin node of this request.
     * </pre>
     *
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
     * <pre>
     * The origin node of this request.
     * </pre>
     *
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

    private io.github.gravetii.grpc.FileConstraint constraint_ = null;
    private com.google.protobuf.SingleFieldBuilderV3<
        io.github.gravetii.grpc.FileConstraint, io.github.gravetii.grpc.FileConstraint.Builder, io.github.gravetii.grpc.FileConstraintOrBuilder> constraintBuilder_;
    /**
     * <code>.FileConstraint constraint = 3;</code>
     */
    public boolean hasConstraint() {
      return constraintBuilder_ != null || constraint_ != null;
    }
    /**
     * <code>.FileConstraint constraint = 3;</code>
     */
    public io.github.gravetii.grpc.FileConstraint getConstraint() {
      if (constraintBuilder_ == null) {
        return constraint_ == null ? io.github.gravetii.grpc.FileConstraint.getDefaultInstance() : constraint_;
      } else {
        return constraintBuilder_.getMessage();
      }
    }
    /**
     * <code>.FileConstraint constraint = 3;</code>
     */
    public Builder setConstraint(io.github.gravetii.grpc.FileConstraint value) {
      if (constraintBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        constraint_ = value;
        onChanged();
      } else {
        constraintBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>.FileConstraint constraint = 3;</code>
     */
    public Builder setConstraint(
        io.github.gravetii.grpc.FileConstraint.Builder builderForValue) {
      if (constraintBuilder_ == null) {
        constraint_ = builderForValue.build();
        onChanged();
      } else {
        constraintBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>.FileConstraint constraint = 3;</code>
     */
    public Builder mergeConstraint(io.github.gravetii.grpc.FileConstraint value) {
      if (constraintBuilder_ == null) {
        if (constraint_ != null) {
          constraint_ =
            io.github.gravetii.grpc.FileConstraint.newBuilder(constraint_).mergeFrom(value).buildPartial();
        } else {
          constraint_ = value;
        }
        onChanged();
      } else {
        constraintBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>.FileConstraint constraint = 3;</code>
     */
    public Builder clearConstraint() {
      if (constraintBuilder_ == null) {
        constraint_ = null;
        onChanged();
      } else {
        constraint_ = null;
        constraintBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>.FileConstraint constraint = 3;</code>
     */
    public io.github.gravetii.grpc.FileConstraint.Builder getConstraintBuilder() {
      
      onChanged();
      return getConstraintFieldBuilder().getBuilder();
    }
    /**
     * <code>.FileConstraint constraint = 3;</code>
     */
    public io.github.gravetii.grpc.FileConstraintOrBuilder getConstraintOrBuilder() {
      if (constraintBuilder_ != null) {
        return constraintBuilder_.getMessageOrBuilder();
      } else {
        return constraint_ == null ?
            io.github.gravetii.grpc.FileConstraint.getDefaultInstance() : constraint_;
      }
    }
    /**
     * <code>.FileConstraint constraint = 3;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        io.github.gravetii.grpc.FileConstraint, io.github.gravetii.grpc.FileConstraint.Builder, io.github.gravetii.grpc.FileConstraintOrBuilder> 
        getConstraintFieldBuilder() {
      if (constraintBuilder_ == null) {
        constraintBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            io.github.gravetii.grpc.FileConstraint, io.github.gravetii.grpc.FileConstraint.Builder, io.github.gravetii.grpc.FileConstraintOrBuilder>(
                getConstraint(),
                getParentForChildren(),
                isClean());
        constraint_ = null;
      }
      return constraintBuilder_;
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


    // @@protoc_insertion_point(builder_scope:SearchReq)
  }

  // @@protoc_insertion_point(class_scope:SearchReq)
  private static final io.github.gravetii.grpc.SearchReq DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new io.github.gravetii.grpc.SearchReq();
  }

  public static io.github.gravetii.grpc.SearchReq getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<SearchReq>
      PARSER = new com.google.protobuf.AbstractParser<SearchReq>() {
    @java.lang.Override
    public SearchReq parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new SearchReq(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<SearchReq> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<SearchReq> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public io.github.gravetii.grpc.SearchReq getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
