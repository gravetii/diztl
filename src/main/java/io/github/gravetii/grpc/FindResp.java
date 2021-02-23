// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: diztl.proto

package io.github.gravetii.grpc;

/**
 * Protobuf type {@code FindResp}
 */
public  final class FindResp extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:FindResp)
    FindRespOrBuilder {
private static final long serialVersionUID = 0L;
  // Use FindResp.newBuilder() to construct.
  private FindResp(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private FindResp() {
    responses_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private FindResp(
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
            if (!((mutable_bitField0_ & 0x00000001) == 0x00000001)) {
              responses_ = new java.util.ArrayList<io.github.gravetii.grpc.SearchResp>();
              mutable_bitField0_ |= 0x00000001;
            }
            responses_.add(
                input.readMessage(io.github.gravetii.grpc.SearchResp.parser(), extensionRegistry));
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
      if (((mutable_bitField0_ & 0x00000001) == 0x00000001)) {
        responses_ = java.util.Collections.unmodifiableList(responses_);
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return io.github.gravetii.grpc.Diztl.internal_static_FindResp_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return io.github.gravetii.grpc.Diztl.internal_static_FindResp_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            io.github.gravetii.grpc.FindResp.class, io.github.gravetii.grpc.FindResp.Builder.class);
  }

  public static final int RESPONSES_FIELD_NUMBER = 1;
  private java.util.List<io.github.gravetii.grpc.SearchResp> responses_;
  /**
   * <code>repeated .SearchResp responses = 1;</code>
   */
  public java.util.List<io.github.gravetii.grpc.SearchResp> getResponsesList() {
    return responses_;
  }
  /**
   * <code>repeated .SearchResp responses = 1;</code>
   */
  public java.util.List<? extends io.github.gravetii.grpc.SearchRespOrBuilder> 
      getResponsesOrBuilderList() {
    return responses_;
  }
  /**
   * <code>repeated .SearchResp responses = 1;</code>
   */
  public int getResponsesCount() {
    return responses_.size();
  }
  /**
   * <code>repeated .SearchResp responses = 1;</code>
   */
  public io.github.gravetii.grpc.SearchResp getResponses(int index) {
    return responses_.get(index);
  }
  /**
   * <code>repeated .SearchResp responses = 1;</code>
   */
  public io.github.gravetii.grpc.SearchRespOrBuilder getResponsesOrBuilder(
      int index) {
    return responses_.get(index);
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
    for (int i = 0; i < responses_.size(); i++) {
      output.writeMessage(1, responses_.get(i));
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    for (int i = 0; i < responses_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, responses_.get(i));
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
    if (!(obj instanceof io.github.gravetii.grpc.FindResp)) {
      return super.equals(obj);
    }
    io.github.gravetii.grpc.FindResp other = (io.github.gravetii.grpc.FindResp) obj;

    boolean result = true;
    result = result && getResponsesList()
        .equals(other.getResponsesList());
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
    if (getResponsesCount() > 0) {
      hash = (37 * hash) + RESPONSES_FIELD_NUMBER;
      hash = (53 * hash) + getResponsesList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static io.github.gravetii.grpc.FindResp parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.github.gravetii.grpc.FindResp parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.github.gravetii.grpc.FindResp parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.github.gravetii.grpc.FindResp parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.github.gravetii.grpc.FindResp parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.github.gravetii.grpc.FindResp parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.github.gravetii.grpc.FindResp parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static io.github.gravetii.grpc.FindResp parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static io.github.gravetii.grpc.FindResp parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static io.github.gravetii.grpc.FindResp parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static io.github.gravetii.grpc.FindResp parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static io.github.gravetii.grpc.FindResp parseFrom(
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
  public static Builder newBuilder(io.github.gravetii.grpc.FindResp prototype) {
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
   * Protobuf type {@code FindResp}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:FindResp)
      io.github.gravetii.grpc.FindRespOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return io.github.gravetii.grpc.Diztl.internal_static_FindResp_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return io.github.gravetii.grpc.Diztl.internal_static_FindResp_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              io.github.gravetii.grpc.FindResp.class, io.github.gravetii.grpc.FindResp.Builder.class);
    }

    // Construct using io.github.gravetii.grpc.FindResp.newBuilder()
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
        getResponsesFieldBuilder();
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      if (responsesBuilder_ == null) {
        responses_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
      } else {
        responsesBuilder_.clear();
      }
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return io.github.gravetii.grpc.Diztl.internal_static_FindResp_descriptor;
    }

    @java.lang.Override
    public io.github.gravetii.grpc.FindResp getDefaultInstanceForType() {
      return io.github.gravetii.grpc.FindResp.getDefaultInstance();
    }

    @java.lang.Override
    public io.github.gravetii.grpc.FindResp build() {
      io.github.gravetii.grpc.FindResp result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public io.github.gravetii.grpc.FindResp buildPartial() {
      io.github.gravetii.grpc.FindResp result = new io.github.gravetii.grpc.FindResp(this);
      int from_bitField0_ = bitField0_;
      if (responsesBuilder_ == null) {
        if (((bitField0_ & 0x00000001) == 0x00000001)) {
          responses_ = java.util.Collections.unmodifiableList(responses_);
          bitField0_ = (bitField0_ & ~0x00000001);
        }
        result.responses_ = responses_;
      } else {
        result.responses_ = responsesBuilder_.build();
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
      if (other instanceof io.github.gravetii.grpc.FindResp) {
        return mergeFrom((io.github.gravetii.grpc.FindResp)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(io.github.gravetii.grpc.FindResp other) {
      if (other == io.github.gravetii.grpc.FindResp.getDefaultInstance()) return this;
      if (responsesBuilder_ == null) {
        if (!other.responses_.isEmpty()) {
          if (responses_.isEmpty()) {
            responses_ = other.responses_;
            bitField0_ = (bitField0_ & ~0x00000001);
          } else {
            ensureResponsesIsMutable();
            responses_.addAll(other.responses_);
          }
          onChanged();
        }
      } else {
        if (!other.responses_.isEmpty()) {
          if (responsesBuilder_.isEmpty()) {
            responsesBuilder_.dispose();
            responsesBuilder_ = null;
            responses_ = other.responses_;
            bitField0_ = (bitField0_ & ~0x00000001);
            responsesBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getResponsesFieldBuilder() : null;
          } else {
            responsesBuilder_.addAllMessages(other.responses_);
          }
        }
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
      io.github.gravetii.grpc.FindResp parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (io.github.gravetii.grpc.FindResp) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private java.util.List<io.github.gravetii.grpc.SearchResp> responses_ =
      java.util.Collections.emptyList();
    private void ensureResponsesIsMutable() {
      if (!((bitField0_ & 0x00000001) == 0x00000001)) {
        responses_ = new java.util.ArrayList<io.github.gravetii.grpc.SearchResp>(responses_);
        bitField0_ |= 0x00000001;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        io.github.gravetii.grpc.SearchResp, io.github.gravetii.grpc.SearchResp.Builder, io.github.gravetii.grpc.SearchRespOrBuilder> responsesBuilder_;

    /**
     * <code>repeated .SearchResp responses = 1;</code>
     */
    public java.util.List<io.github.gravetii.grpc.SearchResp> getResponsesList() {
      if (responsesBuilder_ == null) {
        return java.util.Collections.unmodifiableList(responses_);
      } else {
        return responsesBuilder_.getMessageList();
      }
    }
    /**
     * <code>repeated .SearchResp responses = 1;</code>
     */
    public int getResponsesCount() {
      if (responsesBuilder_ == null) {
        return responses_.size();
      } else {
        return responsesBuilder_.getCount();
      }
    }
    /**
     * <code>repeated .SearchResp responses = 1;</code>
     */
    public io.github.gravetii.grpc.SearchResp getResponses(int index) {
      if (responsesBuilder_ == null) {
        return responses_.get(index);
      } else {
        return responsesBuilder_.getMessage(index);
      }
    }
    /**
     * <code>repeated .SearchResp responses = 1;</code>
     */
    public Builder setResponses(
        int index, io.github.gravetii.grpc.SearchResp value) {
      if (responsesBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureResponsesIsMutable();
        responses_.set(index, value);
        onChanged();
      } else {
        responsesBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .SearchResp responses = 1;</code>
     */
    public Builder setResponses(
        int index, io.github.gravetii.grpc.SearchResp.Builder builderForValue) {
      if (responsesBuilder_ == null) {
        ensureResponsesIsMutable();
        responses_.set(index, builderForValue.build());
        onChanged();
      } else {
        responsesBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .SearchResp responses = 1;</code>
     */
    public Builder addResponses(io.github.gravetii.grpc.SearchResp value) {
      if (responsesBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureResponsesIsMutable();
        responses_.add(value);
        onChanged();
      } else {
        responsesBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <code>repeated .SearchResp responses = 1;</code>
     */
    public Builder addResponses(
        int index, io.github.gravetii.grpc.SearchResp value) {
      if (responsesBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureResponsesIsMutable();
        responses_.add(index, value);
        onChanged();
      } else {
        responsesBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .SearchResp responses = 1;</code>
     */
    public Builder addResponses(
        io.github.gravetii.grpc.SearchResp.Builder builderForValue) {
      if (responsesBuilder_ == null) {
        ensureResponsesIsMutable();
        responses_.add(builderForValue.build());
        onChanged();
      } else {
        responsesBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .SearchResp responses = 1;</code>
     */
    public Builder addResponses(
        int index, io.github.gravetii.grpc.SearchResp.Builder builderForValue) {
      if (responsesBuilder_ == null) {
        ensureResponsesIsMutable();
        responses_.add(index, builderForValue.build());
        onChanged();
      } else {
        responsesBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .SearchResp responses = 1;</code>
     */
    public Builder addAllResponses(
        java.lang.Iterable<? extends io.github.gravetii.grpc.SearchResp> values) {
      if (responsesBuilder_ == null) {
        ensureResponsesIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, responses_);
        onChanged();
      } else {
        responsesBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <code>repeated .SearchResp responses = 1;</code>
     */
    public Builder clearResponses() {
      if (responsesBuilder_ == null) {
        responses_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
        onChanged();
      } else {
        responsesBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>repeated .SearchResp responses = 1;</code>
     */
    public Builder removeResponses(int index) {
      if (responsesBuilder_ == null) {
        ensureResponsesIsMutable();
        responses_.remove(index);
        onChanged();
      } else {
        responsesBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <code>repeated .SearchResp responses = 1;</code>
     */
    public io.github.gravetii.grpc.SearchResp.Builder getResponsesBuilder(
        int index) {
      return getResponsesFieldBuilder().getBuilder(index);
    }
    /**
     * <code>repeated .SearchResp responses = 1;</code>
     */
    public io.github.gravetii.grpc.SearchRespOrBuilder getResponsesOrBuilder(
        int index) {
      if (responsesBuilder_ == null) {
        return responses_.get(index);  } else {
        return responsesBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <code>repeated .SearchResp responses = 1;</code>
     */
    public java.util.List<? extends io.github.gravetii.grpc.SearchRespOrBuilder> 
         getResponsesOrBuilderList() {
      if (responsesBuilder_ != null) {
        return responsesBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(responses_);
      }
    }
    /**
     * <code>repeated .SearchResp responses = 1;</code>
     */
    public io.github.gravetii.grpc.SearchResp.Builder addResponsesBuilder() {
      return getResponsesFieldBuilder().addBuilder(
          io.github.gravetii.grpc.SearchResp.getDefaultInstance());
    }
    /**
     * <code>repeated .SearchResp responses = 1;</code>
     */
    public io.github.gravetii.grpc.SearchResp.Builder addResponsesBuilder(
        int index) {
      return getResponsesFieldBuilder().addBuilder(
          index, io.github.gravetii.grpc.SearchResp.getDefaultInstance());
    }
    /**
     * <code>repeated .SearchResp responses = 1;</code>
     */
    public java.util.List<io.github.gravetii.grpc.SearchResp.Builder> 
         getResponsesBuilderList() {
      return getResponsesFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        io.github.gravetii.grpc.SearchResp, io.github.gravetii.grpc.SearchResp.Builder, io.github.gravetii.grpc.SearchRespOrBuilder> 
        getResponsesFieldBuilder() {
      if (responsesBuilder_ == null) {
        responsesBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            io.github.gravetii.grpc.SearchResp, io.github.gravetii.grpc.SearchResp.Builder, io.github.gravetii.grpc.SearchRespOrBuilder>(
                responses_,
                ((bitField0_ & 0x00000001) == 0x00000001),
                getParentForChildren(),
                isClean());
        responses_ = null;
      }
      return responsesBuilder_;
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


    // @@protoc_insertion_point(builder_scope:FindResp)
  }

  // @@protoc_insertion_point(class_scope:FindResp)
  private static final io.github.gravetii.grpc.FindResp DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new io.github.gravetii.grpc.FindResp();
  }

  public static io.github.gravetii.grpc.FindResp getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<FindResp>
      PARSER = new com.google.protobuf.AbstractParser<FindResp>() {
    @java.lang.Override
    public FindResp parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new FindResp(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<FindResp> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<FindResp> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public io.github.gravetii.grpc.FindResp getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
