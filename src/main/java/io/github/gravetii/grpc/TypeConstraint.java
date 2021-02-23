// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: diztl.proto

package io.github.gravetii.grpc;

/**
 * Protobuf type {@code TypeConstraint}
 */
public  final class TypeConstraint extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:TypeConstraint)
    TypeConstraintOrBuilder {
private static final long serialVersionUID = 0L;
  // Use TypeConstraint.newBuilder() to construct.
  private TypeConstraint(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private TypeConstraint() {
    type_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private TypeConstraint(
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
          case 8: {

            type_ = input.readInt32();
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
    return io.github.gravetii.grpc.Diztl.internal_static_TypeConstraint_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return io.github.gravetii.grpc.Diztl.internal_static_TypeConstraint_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            io.github.gravetii.grpc.TypeConstraint.class, io.github.gravetii.grpc.TypeConstraint.Builder.class);
  }

  public static final int TYPE_FIELD_NUMBER = 1;
  private int type_;
  /**
   * <pre>
   * type indicates the file type.
   * 0 - Any (disregard the constraint)
   * 1 - video - mp4, mkv, mpeg, mov, webm, flv
   * 2 - image - png, jpg, jpeg, ico, gif
   * 3 - audio - mp3, wav, ogg
   * 4 - document - txt, pdf, ppt, doc, xls, csv
   * 5 - compressed - zip, gz, rar, 7z
   * 6 - executable - exe, dmg, sh
   * </pre>
   *
   * <code>int32 type = 1;</code>
   */
  public int getType() {
    return type_;
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
    if (type_ != 0) {
      output.writeInt32(1, type_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (type_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, type_);
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
    if (!(obj instanceof io.github.gravetii.grpc.TypeConstraint)) {
      return super.equals(obj);
    }
    io.github.gravetii.grpc.TypeConstraint other = (io.github.gravetii.grpc.TypeConstraint) obj;

    boolean result = true;
    result = result && (getType()
        == other.getType());
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
    hash = (37 * hash) + TYPE_FIELD_NUMBER;
    hash = (53 * hash) + getType();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static io.github.gravetii.grpc.TypeConstraint parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.github.gravetii.grpc.TypeConstraint parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.github.gravetii.grpc.TypeConstraint parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.github.gravetii.grpc.TypeConstraint parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.github.gravetii.grpc.TypeConstraint parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.github.gravetii.grpc.TypeConstraint parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.github.gravetii.grpc.TypeConstraint parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static io.github.gravetii.grpc.TypeConstraint parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static io.github.gravetii.grpc.TypeConstraint parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static io.github.gravetii.grpc.TypeConstraint parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static io.github.gravetii.grpc.TypeConstraint parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static io.github.gravetii.grpc.TypeConstraint parseFrom(
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
  public static Builder newBuilder(io.github.gravetii.grpc.TypeConstraint prototype) {
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
   * Protobuf type {@code TypeConstraint}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:TypeConstraint)
      io.github.gravetii.grpc.TypeConstraintOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return io.github.gravetii.grpc.Diztl.internal_static_TypeConstraint_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return io.github.gravetii.grpc.Diztl.internal_static_TypeConstraint_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              io.github.gravetii.grpc.TypeConstraint.class, io.github.gravetii.grpc.TypeConstraint.Builder.class);
    }

    // Construct using io.github.gravetii.grpc.TypeConstraint.newBuilder()
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
      type_ = 0;

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return io.github.gravetii.grpc.Diztl.internal_static_TypeConstraint_descriptor;
    }

    @java.lang.Override
    public io.github.gravetii.grpc.TypeConstraint getDefaultInstanceForType() {
      return io.github.gravetii.grpc.TypeConstraint.getDefaultInstance();
    }

    @java.lang.Override
    public io.github.gravetii.grpc.TypeConstraint build() {
      io.github.gravetii.grpc.TypeConstraint result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public io.github.gravetii.grpc.TypeConstraint buildPartial() {
      io.github.gravetii.grpc.TypeConstraint result = new io.github.gravetii.grpc.TypeConstraint(this);
      result.type_ = type_;
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
      if (other instanceof io.github.gravetii.grpc.TypeConstraint) {
        return mergeFrom((io.github.gravetii.grpc.TypeConstraint)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(io.github.gravetii.grpc.TypeConstraint other) {
      if (other == io.github.gravetii.grpc.TypeConstraint.getDefaultInstance()) return this;
      if (other.getType() != 0) {
        setType(other.getType());
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
      io.github.gravetii.grpc.TypeConstraint parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (io.github.gravetii.grpc.TypeConstraint) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private int type_ ;
    /**
     * <pre>
     * type indicates the file type.
     * 0 - Any (disregard the constraint)
     * 1 - video - mp4, mkv, mpeg, mov, webm, flv
     * 2 - image - png, jpg, jpeg, ico, gif
     * 3 - audio - mp3, wav, ogg
     * 4 - document - txt, pdf, ppt, doc, xls, csv
     * 5 - compressed - zip, gz, rar, 7z
     * 6 - executable - exe, dmg, sh
     * </pre>
     *
     * <code>int32 type = 1;</code>
     */
    public int getType() {
      return type_;
    }
    /**
     * <pre>
     * type indicates the file type.
     * 0 - Any (disregard the constraint)
     * 1 - video - mp4, mkv, mpeg, mov, webm, flv
     * 2 - image - png, jpg, jpeg, ico, gif
     * 3 - audio - mp3, wav, ogg
     * 4 - document - txt, pdf, ppt, doc, xls, csv
     * 5 - compressed - zip, gz, rar, 7z
     * 6 - executable - exe, dmg, sh
     * </pre>
     *
     * <code>int32 type = 1;</code>
     */
    public Builder setType(int value) {
      
      type_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * type indicates the file type.
     * 0 - Any (disregard the constraint)
     * 1 - video - mp4, mkv, mpeg, mov, webm, flv
     * 2 - image - png, jpg, jpeg, ico, gif
     * 3 - audio - mp3, wav, ogg
     * 4 - document - txt, pdf, ppt, doc, xls, csv
     * 5 - compressed - zip, gz, rar, 7z
     * 6 - executable - exe, dmg, sh
     * </pre>
     *
     * <code>int32 type = 1;</code>
     */
    public Builder clearType() {
      
      type_ = 0;
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


    // @@protoc_insertion_point(builder_scope:TypeConstraint)
  }

  // @@protoc_insertion_point(class_scope:TypeConstraint)
  private static final io.github.gravetii.grpc.TypeConstraint DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new io.github.gravetii.grpc.TypeConstraint();
  }

  public static io.github.gravetii.grpc.TypeConstraint getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<TypeConstraint>
      PARSER = new com.google.protobuf.AbstractParser<TypeConstraint>() {
    @java.lang.Override
    public TypeConstraint parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new TypeConstraint(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<TypeConstraint> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<TypeConstraint> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public io.github.gravetii.grpc.TypeConstraint getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

