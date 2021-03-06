// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: diztl.proto

package io.github.gravetii.grpc;

/**
 * Protobuf type {@code FileChunk}
 */
public  final class FileChunk extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:FileChunk)
    FileChunkOrBuilder {
private static final long serialVersionUID = 0L;
  // Use FileChunk.newBuilder() to construct.
  private FileChunk(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private FileChunk() {
    data_ = com.google.protobuf.ByteString.EMPTY;
    chunk_ = 0;
    chunks_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private FileChunk(
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
            io.github.gravetii.grpc.FileMetadata.Builder subBuilder = null;
            if (metadata_ != null) {
              subBuilder = metadata_.toBuilder();
            }
            metadata_ = input.readMessage(io.github.gravetii.grpc.FileMetadata.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(metadata_);
              metadata_ = subBuilder.buildPartial();
            }

            break;
          }
          case 18: {

            data_ = input.readBytes();
            break;
          }
          case 24: {

            chunk_ = input.readInt32();
            break;
          }
          case 32: {

            chunks_ = input.readInt32();
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
    return io.github.gravetii.grpc.Diztl.internal_static_FileChunk_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return io.github.gravetii.grpc.Diztl.internal_static_FileChunk_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            io.github.gravetii.grpc.FileChunk.class, io.github.gravetii.grpc.FileChunk.Builder.class);
  }

  public static final int METADATA_FIELD_NUMBER = 1;
  private io.github.gravetii.grpc.FileMetadata metadata_;
  /**
   * <pre>
   * The metadata of the parent file typically sent in the first file chunk.
   * </pre>
   *
   * <code>.FileMetadata metadata = 1;</code>
   */
  public boolean hasMetadata() {
    return metadata_ != null;
  }
  /**
   * <pre>
   * The metadata of the parent file typically sent in the first file chunk.
   * </pre>
   *
   * <code>.FileMetadata metadata = 1;</code>
   */
  public io.github.gravetii.grpc.FileMetadata getMetadata() {
    return metadata_ == null ? io.github.gravetii.grpc.FileMetadata.getDefaultInstance() : metadata_;
  }
  /**
   * <pre>
   * The metadata of the parent file typically sent in the first file chunk.
   * </pre>
   *
   * <code>.FileMetadata metadata = 1;</code>
   */
  public io.github.gravetii.grpc.FileMetadataOrBuilder getMetadataOrBuilder() {
    return getMetadata();
  }

  public static final int DATA_FIELD_NUMBER = 2;
  private com.google.protobuf.ByteString data_;
  /**
   * <pre>
   * The data of this file chunk.
   * </pre>
   *
   * <code>bytes data = 2;</code>
   */
  public com.google.protobuf.ByteString getData() {
    return data_;
  }

  public static final int CHUNK_FIELD_NUMBER = 3;
  private int chunk_;
  /**
   * <pre>
   * The chunk number of this chunk of data starting from one.
   * </pre>
   *
   * <code>int32 chunk = 3;</code>
   */
  public int getChunk() {
    return chunk_;
  }

  public static final int CHUNKS_FIELD_NUMBER = 4;
  private int chunks_;
  /**
   * <pre>
   * The total number of chunks of this file that the receiver should
   * expect...sent only in the first file chunk.
   * </pre>
   *
   * <code>int32 chunks = 4;</code>
   */
  public int getChunks() {
    return chunks_;
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
    if (metadata_ != null) {
      output.writeMessage(1, getMetadata());
    }
    if (!data_.isEmpty()) {
      output.writeBytes(2, data_);
    }
    if (chunk_ != 0) {
      output.writeInt32(3, chunk_);
    }
    if (chunks_ != 0) {
      output.writeInt32(4, chunks_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (metadata_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, getMetadata());
    }
    if (!data_.isEmpty()) {
      size += com.google.protobuf.CodedOutputStream
        .computeBytesSize(2, data_);
    }
    if (chunk_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(3, chunk_);
    }
    if (chunks_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(4, chunks_);
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
    if (!(obj instanceof io.github.gravetii.grpc.FileChunk)) {
      return super.equals(obj);
    }
    io.github.gravetii.grpc.FileChunk other = (io.github.gravetii.grpc.FileChunk) obj;

    boolean result = true;
    result = result && (hasMetadata() == other.hasMetadata());
    if (hasMetadata()) {
      result = result && getMetadata()
          .equals(other.getMetadata());
    }
    result = result && getData()
        .equals(other.getData());
    result = result && (getChunk()
        == other.getChunk());
    result = result && (getChunks()
        == other.getChunks());
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
    if (hasMetadata()) {
      hash = (37 * hash) + METADATA_FIELD_NUMBER;
      hash = (53 * hash) + getMetadata().hashCode();
    }
    hash = (37 * hash) + DATA_FIELD_NUMBER;
    hash = (53 * hash) + getData().hashCode();
    hash = (37 * hash) + CHUNK_FIELD_NUMBER;
    hash = (53 * hash) + getChunk();
    hash = (37 * hash) + CHUNKS_FIELD_NUMBER;
    hash = (53 * hash) + getChunks();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static io.github.gravetii.grpc.FileChunk parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.github.gravetii.grpc.FileChunk parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.github.gravetii.grpc.FileChunk parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.github.gravetii.grpc.FileChunk parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.github.gravetii.grpc.FileChunk parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.github.gravetii.grpc.FileChunk parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.github.gravetii.grpc.FileChunk parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static io.github.gravetii.grpc.FileChunk parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static io.github.gravetii.grpc.FileChunk parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static io.github.gravetii.grpc.FileChunk parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static io.github.gravetii.grpc.FileChunk parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static io.github.gravetii.grpc.FileChunk parseFrom(
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
  public static Builder newBuilder(io.github.gravetii.grpc.FileChunk prototype) {
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
   * Protobuf type {@code FileChunk}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:FileChunk)
      io.github.gravetii.grpc.FileChunkOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return io.github.gravetii.grpc.Diztl.internal_static_FileChunk_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return io.github.gravetii.grpc.Diztl.internal_static_FileChunk_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              io.github.gravetii.grpc.FileChunk.class, io.github.gravetii.grpc.FileChunk.Builder.class);
    }

    // Construct using io.github.gravetii.grpc.FileChunk.newBuilder()
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
      if (metadataBuilder_ == null) {
        metadata_ = null;
      } else {
        metadata_ = null;
        metadataBuilder_ = null;
      }
      data_ = com.google.protobuf.ByteString.EMPTY;

      chunk_ = 0;

      chunks_ = 0;

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return io.github.gravetii.grpc.Diztl.internal_static_FileChunk_descriptor;
    }

    @java.lang.Override
    public io.github.gravetii.grpc.FileChunk getDefaultInstanceForType() {
      return io.github.gravetii.grpc.FileChunk.getDefaultInstance();
    }

    @java.lang.Override
    public io.github.gravetii.grpc.FileChunk build() {
      io.github.gravetii.grpc.FileChunk result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public io.github.gravetii.grpc.FileChunk buildPartial() {
      io.github.gravetii.grpc.FileChunk result = new io.github.gravetii.grpc.FileChunk(this);
      if (metadataBuilder_ == null) {
        result.metadata_ = metadata_;
      } else {
        result.metadata_ = metadataBuilder_.build();
      }
      result.data_ = data_;
      result.chunk_ = chunk_;
      result.chunks_ = chunks_;
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
      if (other instanceof io.github.gravetii.grpc.FileChunk) {
        return mergeFrom((io.github.gravetii.grpc.FileChunk)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(io.github.gravetii.grpc.FileChunk other) {
      if (other == io.github.gravetii.grpc.FileChunk.getDefaultInstance()) return this;
      if (other.hasMetadata()) {
        mergeMetadata(other.getMetadata());
      }
      if (other.getData() != com.google.protobuf.ByteString.EMPTY) {
        setData(other.getData());
      }
      if (other.getChunk() != 0) {
        setChunk(other.getChunk());
      }
      if (other.getChunks() != 0) {
        setChunks(other.getChunks());
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
      io.github.gravetii.grpc.FileChunk parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (io.github.gravetii.grpc.FileChunk) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private io.github.gravetii.grpc.FileMetadata metadata_ = null;
    private com.google.protobuf.SingleFieldBuilderV3<
        io.github.gravetii.grpc.FileMetadata, io.github.gravetii.grpc.FileMetadata.Builder, io.github.gravetii.grpc.FileMetadataOrBuilder> metadataBuilder_;
    /**
     * <pre>
     * The metadata of the parent file typically sent in the first file chunk.
     * </pre>
     *
     * <code>.FileMetadata metadata = 1;</code>
     */
    public boolean hasMetadata() {
      return metadataBuilder_ != null || metadata_ != null;
    }
    /**
     * <pre>
     * The metadata of the parent file typically sent in the first file chunk.
     * </pre>
     *
     * <code>.FileMetadata metadata = 1;</code>
     */
    public io.github.gravetii.grpc.FileMetadata getMetadata() {
      if (metadataBuilder_ == null) {
        return metadata_ == null ? io.github.gravetii.grpc.FileMetadata.getDefaultInstance() : metadata_;
      } else {
        return metadataBuilder_.getMessage();
      }
    }
    /**
     * <pre>
     * The metadata of the parent file typically sent in the first file chunk.
     * </pre>
     *
     * <code>.FileMetadata metadata = 1;</code>
     */
    public Builder setMetadata(io.github.gravetii.grpc.FileMetadata value) {
      if (metadataBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        metadata_ = value;
        onChanged();
      } else {
        metadataBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <pre>
     * The metadata of the parent file typically sent in the first file chunk.
     * </pre>
     *
     * <code>.FileMetadata metadata = 1;</code>
     */
    public Builder setMetadata(
        io.github.gravetii.grpc.FileMetadata.Builder builderForValue) {
      if (metadataBuilder_ == null) {
        metadata_ = builderForValue.build();
        onChanged();
      } else {
        metadataBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <pre>
     * The metadata of the parent file typically sent in the first file chunk.
     * </pre>
     *
     * <code>.FileMetadata metadata = 1;</code>
     */
    public Builder mergeMetadata(io.github.gravetii.grpc.FileMetadata value) {
      if (metadataBuilder_ == null) {
        if (metadata_ != null) {
          metadata_ =
            io.github.gravetii.grpc.FileMetadata.newBuilder(metadata_).mergeFrom(value).buildPartial();
        } else {
          metadata_ = value;
        }
        onChanged();
      } else {
        metadataBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <pre>
     * The metadata of the parent file typically sent in the first file chunk.
     * </pre>
     *
     * <code>.FileMetadata metadata = 1;</code>
     */
    public Builder clearMetadata() {
      if (metadataBuilder_ == null) {
        metadata_ = null;
        onChanged();
      } else {
        metadata_ = null;
        metadataBuilder_ = null;
      }

      return this;
    }
    /**
     * <pre>
     * The metadata of the parent file typically sent in the first file chunk.
     * </pre>
     *
     * <code>.FileMetadata metadata = 1;</code>
     */
    public io.github.gravetii.grpc.FileMetadata.Builder getMetadataBuilder() {
      
      onChanged();
      return getMetadataFieldBuilder().getBuilder();
    }
    /**
     * <pre>
     * The metadata of the parent file typically sent in the first file chunk.
     * </pre>
     *
     * <code>.FileMetadata metadata = 1;</code>
     */
    public io.github.gravetii.grpc.FileMetadataOrBuilder getMetadataOrBuilder() {
      if (metadataBuilder_ != null) {
        return metadataBuilder_.getMessageOrBuilder();
      } else {
        return metadata_ == null ?
            io.github.gravetii.grpc.FileMetadata.getDefaultInstance() : metadata_;
      }
    }
    /**
     * <pre>
     * The metadata of the parent file typically sent in the first file chunk.
     * </pre>
     *
     * <code>.FileMetadata metadata = 1;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        io.github.gravetii.grpc.FileMetadata, io.github.gravetii.grpc.FileMetadata.Builder, io.github.gravetii.grpc.FileMetadataOrBuilder> 
        getMetadataFieldBuilder() {
      if (metadataBuilder_ == null) {
        metadataBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            io.github.gravetii.grpc.FileMetadata, io.github.gravetii.grpc.FileMetadata.Builder, io.github.gravetii.grpc.FileMetadataOrBuilder>(
                getMetadata(),
                getParentForChildren(),
                isClean());
        metadata_ = null;
      }
      return metadataBuilder_;
    }

    private com.google.protobuf.ByteString data_ = com.google.protobuf.ByteString.EMPTY;
    /**
     * <pre>
     * The data of this file chunk.
     * </pre>
     *
     * <code>bytes data = 2;</code>
     */
    public com.google.protobuf.ByteString getData() {
      return data_;
    }
    /**
     * <pre>
     * The data of this file chunk.
     * </pre>
     *
     * <code>bytes data = 2;</code>
     */
    public Builder setData(com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      data_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * The data of this file chunk.
     * </pre>
     *
     * <code>bytes data = 2;</code>
     */
    public Builder clearData() {
      
      data_ = getDefaultInstance().getData();
      onChanged();
      return this;
    }

    private int chunk_ ;
    /**
     * <pre>
     * The chunk number of this chunk of data starting from one.
     * </pre>
     *
     * <code>int32 chunk = 3;</code>
     */
    public int getChunk() {
      return chunk_;
    }
    /**
     * <pre>
     * The chunk number of this chunk of data starting from one.
     * </pre>
     *
     * <code>int32 chunk = 3;</code>
     */
    public Builder setChunk(int value) {
      
      chunk_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * The chunk number of this chunk of data starting from one.
     * </pre>
     *
     * <code>int32 chunk = 3;</code>
     */
    public Builder clearChunk() {
      
      chunk_ = 0;
      onChanged();
      return this;
    }

    private int chunks_ ;
    /**
     * <pre>
     * The total number of chunks of this file that the receiver should
     * expect...sent only in the first file chunk.
     * </pre>
     *
     * <code>int32 chunks = 4;</code>
     */
    public int getChunks() {
      return chunks_;
    }
    /**
     * <pre>
     * The total number of chunks of this file that the receiver should
     * expect...sent only in the first file chunk.
     * </pre>
     *
     * <code>int32 chunks = 4;</code>
     */
    public Builder setChunks(int value) {
      
      chunks_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * The total number of chunks of this file that the receiver should
     * expect...sent only in the first file chunk.
     * </pre>
     *
     * <code>int32 chunks = 4;</code>
     */
    public Builder clearChunks() {
      
      chunks_ = 0;
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


    // @@protoc_insertion_point(builder_scope:FileChunk)
  }

  // @@protoc_insertion_point(class_scope:FileChunk)
  private static final io.github.gravetii.grpc.FileChunk DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new io.github.gravetii.grpc.FileChunk();
  }

  public static io.github.gravetii.grpc.FileChunk getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<FileChunk>
      PARSER = new com.google.protobuf.AbstractParser<FileChunk>() {
    @java.lang.Override
    public FileChunk parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new FileChunk(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<FileChunk> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<FileChunk> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public io.github.gravetii.grpc.FileChunk getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

