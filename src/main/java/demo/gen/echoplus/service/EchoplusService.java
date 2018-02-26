/**
 * Autogenerated by Thrift Compiler (0.11.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package demo.gen.echoplus.service;

import demo.gen.echoplus.struct.Input;
import demo.gen.echoplus.struct.Output;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.11.0)", date = "2018-02-23")
public class EchoplusService {

  public interface Iface {

    public Output echo(Input input1, java.lang.String input2) throws org.apache.thrift.TException;

  }

  public interface AsyncIface {

    public void echo(Input input1, java.lang.String input2, org.apache.thrift.async.AsyncMethodCallback<Output> resultHandler) throws org.apache.thrift.TException;

  }

  public static class Client extends org.apache.thrift.TServiceClient implements Iface {
    public static class Factory implements org.apache.thrift.TServiceClientFactory<Client> {
      public Factory() {}
      public Client getClient(org.apache.thrift.protocol.TProtocol prot) {
        return new Client(prot);
      }
      public Client getClient(org.apache.thrift.protocol.TProtocol iprot, org.apache.thrift.protocol.TProtocol oprot) {
        return new Client(iprot, oprot);
      }
    }

    public Client(org.apache.thrift.protocol.TProtocol prot)
    {
      super(prot, prot);
    }

    public Client(org.apache.thrift.protocol.TProtocol iprot, org.apache.thrift.protocol.TProtocol oprot) {
      super(iprot, oprot);
    }

    public Output echo(Input input1, java.lang.String input2) throws org.apache.thrift.TException
    {
      send_echo(input1, input2);
      return recv_echo();
    }

    public void send_echo(Input input1, java.lang.String input2) throws org.apache.thrift.TException
    {
      echo_args args = new echo_args();
      args.setInput1(input1);
      args.setInput2(input2);
      sendBase("echo", args);
    }

    public Output recv_echo() throws org.apache.thrift.TException
    {
      echo_result result = new echo_result();
      receiveBase(result, "echo");
      if (result.isSetSuccess()) {
        return result.success;
      }
      throw new org.apache.thrift.TApplicationException(org.apache.thrift.TApplicationException.MISSING_RESULT, "echo failed: unknown result");
    }

  }
  public static class AsyncClient extends org.apache.thrift.async.TAsyncClient implements AsyncIface {
    public static class Factory implements org.apache.thrift.async.TAsyncClientFactory<AsyncClient> {
      private org.apache.thrift.async.TAsyncClientManager clientManager;
      private org.apache.thrift.protocol.TProtocolFactory protocolFactory;
      public Factory(org.apache.thrift.async.TAsyncClientManager clientManager, org.apache.thrift.protocol.TProtocolFactory protocolFactory) {
        this.clientManager = clientManager;
        this.protocolFactory = protocolFactory;
      }
      public AsyncClient getAsyncClient(org.apache.thrift.transport.TNonblockingTransport transport) {
        return new AsyncClient(protocolFactory, clientManager, transport);
      }
    }

    public AsyncClient(org.apache.thrift.protocol.TProtocolFactory protocolFactory, org.apache.thrift.async.TAsyncClientManager clientManager, org.apache.thrift.transport.TNonblockingTransport transport) {
      super(protocolFactory, clientManager, transport);
    }

    public void echo(Input input1, java.lang.String input2, org.apache.thrift.async.AsyncMethodCallback<Output> resultHandler) throws org.apache.thrift.TException {
      checkReady();
      echo_call method_call = new echo_call(input1, input2, resultHandler, this, ___protocolFactory, ___transport);
      this.___currentMethod = method_call;
      ___manager.call(method_call);
    }

    public static class echo_call extends org.apache.thrift.async.TAsyncMethodCall<Output> {
      private Input input1;
      private java.lang.String input2;
      public echo_call(Input input1, java.lang.String input2, org.apache.thrift.async.AsyncMethodCallback<Output> resultHandler, org.apache.thrift.async.TAsyncClient client, org.apache.thrift.protocol.TProtocolFactory protocolFactory, org.apache.thrift.transport.TNonblockingTransport transport) throws org.apache.thrift.TException {
        super(client, protocolFactory, transport, resultHandler, false);
        this.input1 = input1;
        this.input2 = input2;
      }

      public void write_args(org.apache.thrift.protocol.TProtocol prot) throws org.apache.thrift.TException {
        prot.writeMessageBegin(new org.apache.thrift.protocol.TMessage("echo", org.apache.thrift.protocol.TMessageType.CALL, 0));
        echo_args args = new echo_args();
        args.setInput1(input1);
        args.setInput2(input2);
        args.write(prot);
        prot.writeMessageEnd();
      }

      public Output getResult() throws org.apache.thrift.TException {
        if (getState() != org.apache.thrift.async.TAsyncMethodCall.State.RESPONSE_READ) {
          throw new java.lang.IllegalStateException("Method call not finished!");
        }
        org.apache.thrift.transport.TMemoryInputTransport memoryTransport = new org.apache.thrift.transport.TMemoryInputTransport(getFrameBuffer().array());
        org.apache.thrift.protocol.TProtocol prot = client.getProtocolFactory().getProtocol(memoryTransport);
        return (new Client(prot)).recv_echo();
      }
    }

  }

  public static class Processor<I extends Iface> extends org.apache.thrift.TBaseProcessor<I> implements org.apache.thrift.TProcessor {
    private static final org.slf4j.Logger _LOGGER = org.slf4j.LoggerFactory.getLogger(Processor.class.getName());
    public Processor(I iface) {
      super(iface, getProcessMap(new java.util.HashMap<java.lang.String, org.apache.thrift.ProcessFunction<I, ? extends org.apache.thrift.TBase>>()));
    }

    protected Processor(I iface, java.util.Map<java.lang.String, org.apache.thrift.ProcessFunction<I, ? extends org.apache.thrift.TBase>> processMap) {
      super(iface, getProcessMap(processMap));
    }

    private static <I extends Iface> java.util.Map<java.lang.String,  org.apache.thrift.ProcessFunction<I, ? extends org.apache.thrift.TBase>> getProcessMap(java.util.Map<java.lang.String, org.apache.thrift.ProcessFunction<I, ? extends  org.apache.thrift.TBase>> processMap) {
      processMap.put("echo", new echo());
      return processMap;
    }

    public static class echo<I extends Iface> extends org.apache.thrift.ProcessFunction<I, echo_args> {
      public echo() {
        super("echo");
      }

      public echo_args getEmptyArgsInstance() {
        return new echo_args();
      }

      protected boolean isOneway() {
        return false;
      }

      protected boolean handleRuntimeExceptions() {
        return false;
      }

      public echo_result getResult(I iface, echo_args args) throws org.apache.thrift.TException {
        echo_result result = new echo_result();
        result.success = iface.echo(args.input1, args.input2);
        return result;
      }
    }

  }

  public static class AsyncProcessor<I extends AsyncIface> extends org.apache.thrift.TBaseAsyncProcessor<I> {
    private static final org.slf4j.Logger _LOGGER = org.slf4j.LoggerFactory.getLogger(AsyncProcessor.class.getName());
    public AsyncProcessor(I iface) {
      super(iface, getProcessMap(new java.util.HashMap<java.lang.String, org.apache.thrift.AsyncProcessFunction<I, ? extends org.apache.thrift.TBase, ?>>()));
    }

    protected AsyncProcessor(I iface, java.util.Map<java.lang.String,  org.apache.thrift.AsyncProcessFunction<I, ? extends  org.apache.thrift.TBase, ?>> processMap) {
      super(iface, getProcessMap(processMap));
    }

    private static <I extends AsyncIface> java.util.Map<java.lang.String,  org.apache.thrift.AsyncProcessFunction<I, ? extends  org.apache.thrift.TBase,?>> getProcessMap(java.util.Map<java.lang.String,  org.apache.thrift.AsyncProcessFunction<I, ? extends  org.apache.thrift.TBase, ?>> processMap) {
      processMap.put("echo", new echo());
      return processMap;
    }

    public static class echo<I extends AsyncIface> extends org.apache.thrift.AsyncProcessFunction<I, echo_args, Output> {
      public echo() {
        super("echo");
      }

      public echo_args getEmptyArgsInstance() {
        return new echo_args();
      }

      public org.apache.thrift.async.AsyncMethodCallback<Output> getResultHandler(final org.apache.thrift.server.AbstractNonblockingServer.AsyncFrameBuffer fb, final int seqid) {
        final org.apache.thrift.AsyncProcessFunction fcall = this;
        return new org.apache.thrift.async.AsyncMethodCallback<Output>() {
          public void onComplete(Output o) {
            echo_result result = new echo_result();
            result.success = o;
            try {
              fcall.sendResponse(fb, result, org.apache.thrift.protocol.TMessageType.REPLY,seqid);
            } catch (org.apache.thrift.transport.TTransportException e) {
              _LOGGER.error("TTransportException writing to internal frame buffer", e);
              fb.close();
            } catch (java.lang.Exception e) {
              _LOGGER.error("Exception writing to internal frame buffer", e);
              onError(e);
            }
          }
          public void onError(java.lang.Exception e) {
            byte msgType = org.apache.thrift.protocol.TMessageType.REPLY;
            org.apache.thrift.TSerializable msg;
            echo_result result = new echo_result();
            if (e instanceof org.apache.thrift.transport.TTransportException) {
              _LOGGER.error("TTransportException inside handler", e);
              fb.close();
              return;
            } else if (e instanceof org.apache.thrift.TApplicationException) {
              _LOGGER.error("TApplicationException inside handler", e);
              msgType = org.apache.thrift.protocol.TMessageType.EXCEPTION;
              msg = (org.apache.thrift.TApplicationException)e;
            } else {
              _LOGGER.error("Exception inside handler", e);
              msgType = org.apache.thrift.protocol.TMessageType.EXCEPTION;
              msg = new org.apache.thrift.TApplicationException(org.apache.thrift.TApplicationException.INTERNAL_ERROR, e.getMessage());
            }
            try {
              fcall.sendResponse(fb,msg,msgType,seqid);
            } catch (java.lang.Exception ex) {
              _LOGGER.error("Exception writing to internal frame buffer", ex);
              fb.close();
            }
          }
        };
      }

      protected boolean isOneway() {
        return false;
      }

      public void start(I iface, echo_args args, org.apache.thrift.async.AsyncMethodCallback<Output> resultHandler) throws org.apache.thrift.TException {
        iface.echo(args.input1, args.input2,resultHandler);
      }
    }

  }

  public static class echo_args implements org.apache.thrift.TBase<echo_args, echo_args._Fields>, java.io.Serializable, Cloneable, Comparable<echo_args>   {
    private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("echo_args");

    private static final org.apache.thrift.protocol.TField INPUT1_FIELD_DESC = new org.apache.thrift.protocol.TField("input1", org.apache.thrift.protocol.TType.STRUCT, (short)1);
    private static final org.apache.thrift.protocol.TField INPUT2_FIELD_DESC = new org.apache.thrift.protocol.TField("input2", org.apache.thrift.protocol.TType.STRING, (short)2);

    private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new echo_argsStandardSchemeFactory();
    private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new echo_argsTupleSchemeFactory();

    public Input input1; // required
    public java.lang.String input2; // required

    /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
    public enum _Fields implements org.apache.thrift.TFieldIdEnum {
      INPUT1((short)1, "input1"),
      INPUT2((short)2, "input2");

      private static final java.util.Map<java.lang.String, _Fields> byName = new java.util.HashMap<java.lang.String, _Fields>();

      static {
        for (_Fields field : java.util.EnumSet.allOf(_Fields.class)) {
          byName.put(field.getFieldName(), field);
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, or null if its not found.
       */
      public static _Fields findByThriftId(int fieldId) {
        switch(fieldId) {
          case 1: // INPUT1
            return INPUT1;
          case 2: // INPUT2
            return INPUT2;
          default:
            return null;
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, throwing an exception
       * if it is not found.
       */
      public static _Fields findByThriftIdOrThrow(int fieldId) {
        _Fields fields = findByThriftId(fieldId);
        if (fields == null) throw new java.lang.IllegalArgumentException("Field " + fieldId + " doesn't exist!");
        return fields;
      }

      /**
       * Find the _Fields constant that matches name, or null if its not found.
       */
      public static _Fields findByName(java.lang.String name) {
        return byName.get(name);
      }

      private final short _thriftId;
      private final java.lang.String _fieldName;

      _Fields(short thriftId, java.lang.String fieldName) {
        _thriftId = thriftId;
        _fieldName = fieldName;
      }

      public short getThriftFieldId() {
        return _thriftId;
      }

      public java.lang.String getFieldName() {
        return _fieldName;
      }
    }

    // isset id assignments
    public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
    static {
      java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
      tmpMap.put(_Fields.INPUT1, new org.apache.thrift.meta_data.FieldMetaData("input1", org.apache.thrift.TFieldRequirementType.DEFAULT, 
          new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, Input.class)));
      tmpMap.put(_Fields.INPUT2, new org.apache.thrift.meta_data.FieldMetaData("input2", org.apache.thrift.TFieldRequirementType.DEFAULT, 
          new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
      metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
      org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(echo_args.class, metaDataMap);
    }

    public echo_args() {
    }

    public echo_args(
      Input input1,
      java.lang.String input2)
    {
      this();
      this.input1 = input1;
      this.input2 = input2;
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public echo_args(echo_args other) {
      if (other.isSetInput1()) {
        this.input1 = new Input(other.input1);
      }
      if (other.isSetInput2()) {
        this.input2 = other.input2;
      }
    }

    public echo_args deepCopy() {
      return new echo_args(this);
    }

    @Override
    public void clear() {
      this.input1 = null;
      this.input2 = null;
    }

    public Input getInput1() {
      return this.input1;
    }

    public echo_args setInput1(Input input1) {
      this.input1 = input1;
      return this;
    }

    public void unsetInput1() {
      this.input1 = null;
    }

    /** Returns true if field input1 is set (has been assigned a value) and false otherwise */
    public boolean isSetInput1() {
      return this.input1 != null;
    }

    public void setInput1IsSet(boolean value) {
      if (!value) {
        this.input1 = null;
      }
    }

    public java.lang.String getInput2() {
      return this.input2;
    }

    public echo_args setInput2(java.lang.String input2) {
      this.input2 = input2;
      return this;
    }

    public void unsetInput2() {
      this.input2 = null;
    }

    /** Returns true if field input2 is set (has been assigned a value) and false otherwise */
    public boolean isSetInput2() {
      return this.input2 != null;
    }

    public void setInput2IsSet(boolean value) {
      if (!value) {
        this.input2 = null;
      }
    }

    public void setFieldValue(_Fields field, java.lang.Object value) {
      switch (field) {
      case INPUT1:
        if (value == null) {
          unsetInput1();
        } else {
          setInput1((Input)value);
        }
        break;

      case INPUT2:
        if (value == null) {
          unsetInput2();
        } else {
          setInput2((java.lang.String)value);
        }
        break;

      }
    }

    public java.lang.Object getFieldValue(_Fields field) {
      switch (field) {
      case INPUT1:
        return getInput1();

      case INPUT2:
        return getInput2();

      }
      throw new java.lang.IllegalStateException();
    }

    /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
    public boolean isSet(_Fields field) {
      if (field == null) {
        throw new java.lang.IllegalArgumentException();
      }

      switch (field) {
      case INPUT1:
        return isSetInput1();
      case INPUT2:
        return isSetInput2();
      }
      throw new java.lang.IllegalStateException();
    }

    @Override
    public boolean equals(java.lang.Object that) {
      if (that == null)
        return false;
      if (that instanceof echo_args)
        return this.equals((echo_args)that);
      return false;
    }

    public boolean equals(echo_args that) {
      if (that == null)
        return false;
      if (this == that)
        return true;

      boolean this_present_input1 = true && this.isSetInput1();
      boolean that_present_input1 = true && that.isSetInput1();
      if (this_present_input1 || that_present_input1) {
        if (!(this_present_input1 && that_present_input1))
          return false;
        if (!this.input1.equals(that.input1))
          return false;
      }

      boolean this_present_input2 = true && this.isSetInput2();
      boolean that_present_input2 = true && that.isSetInput2();
      if (this_present_input2 || that_present_input2) {
        if (!(this_present_input2 && that_present_input2))
          return false;
        if (!this.input2.equals(that.input2))
          return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      int hashCode = 1;

      hashCode = hashCode * 8191 + ((isSetInput1()) ? 131071 : 524287);
      if (isSetInput1())
        hashCode = hashCode * 8191 + input1.hashCode();

      hashCode = hashCode * 8191 + ((isSetInput2()) ? 131071 : 524287);
      if (isSetInput2())
        hashCode = hashCode * 8191 + input2.hashCode();

      return hashCode;
    }

    @Override
    public int compareTo(echo_args other) {
      if (!getClass().equals(other.getClass())) {
        return getClass().getName().compareTo(other.getClass().getName());
      }

      int lastComparison = 0;

      lastComparison = java.lang.Boolean.valueOf(isSetInput1()).compareTo(other.isSetInput1());
      if (lastComparison != 0) {
        return lastComparison;
      }
      if (isSetInput1()) {
        lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.input1, other.input1);
        if (lastComparison != 0) {
          return lastComparison;
        }
      }
      lastComparison = java.lang.Boolean.valueOf(isSetInput2()).compareTo(other.isSetInput2());
      if (lastComparison != 0) {
        return lastComparison;
      }
      if (isSetInput2()) {
        lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.input2, other.input2);
        if (lastComparison != 0) {
          return lastComparison;
        }
      }
      return 0;
    }

    public _Fields fieldForId(int fieldId) {
      return _Fields.findByThriftId(fieldId);
    }

    public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
      scheme(iprot).read(iprot, this);
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
      scheme(oprot).write(oprot, this);
    }

    @Override
    public java.lang.String toString() {
      java.lang.StringBuilder sb = new java.lang.StringBuilder("echo_args(");
      boolean first = true;

      sb.append("input1:");
      if (this.input1 == null) {
        sb.append("null");
      } else {
        sb.append(this.input1);
      }
      first = false;
      if (!first) sb.append(", ");
      sb.append("input2:");
      if (this.input2 == null) {
        sb.append("null");
      } else {
        sb.append(this.input2);
      }
      first = false;
      sb.append(")");
      return sb.toString();
    }

    public void validate() throws org.apache.thrift.TException {
      // check for required fields
      // check for sub-struct validity
      if (input1 != null) {
        input1.validate();
      }
    }

    private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
      try {
        write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
      } catch (org.apache.thrift.TException te) {
        throw new java.io.IOException(te);
      }
    }

    private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, java.lang.ClassNotFoundException {
      try {
        read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
      } catch (org.apache.thrift.TException te) {
        throw new java.io.IOException(te);
      }
    }

    private static class echo_argsStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
      public echo_argsStandardScheme getScheme() {
        return new echo_argsStandardScheme();
      }
    }

    private static class echo_argsStandardScheme extends org.apache.thrift.scheme.StandardScheme<echo_args> {

      public void read(org.apache.thrift.protocol.TProtocol iprot, echo_args struct) throws org.apache.thrift.TException {
        org.apache.thrift.protocol.TField schemeField;
        iprot.readStructBegin();
        while (true)
        {
          schemeField = iprot.readFieldBegin();
          if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
            break;
          }
          switch (schemeField.id) {
            case 1: // INPUT1
              if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
                struct.input1 = new Input();
                struct.input1.read(iprot);
                struct.setInput1IsSet(true);
              } else { 
                org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
              }
              break;
            case 2: // INPUT2
              if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
                struct.input2 = iprot.readString();
                struct.setInput2IsSet(true);
              } else { 
                org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
              }
              break;
            default:
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
          }
          iprot.readFieldEnd();
        }
        iprot.readStructEnd();

        // check for required fields of primitive type, which can't be checked in the validate method
        struct.validate();
      }

      public void write(org.apache.thrift.protocol.TProtocol oprot, echo_args struct) throws org.apache.thrift.TException {
        struct.validate();

        oprot.writeStructBegin(STRUCT_DESC);
        if (struct.input1 != null) {
          oprot.writeFieldBegin(INPUT1_FIELD_DESC);
          struct.input1.write(oprot);
          oprot.writeFieldEnd();
        }
        if (struct.input2 != null) {
          oprot.writeFieldBegin(INPUT2_FIELD_DESC);
          oprot.writeString(struct.input2);
          oprot.writeFieldEnd();
        }
        oprot.writeFieldStop();
        oprot.writeStructEnd();
      }

    }

    private static class echo_argsTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
      public echo_argsTupleScheme getScheme() {
        return new echo_argsTupleScheme();
      }
    }

    private static class echo_argsTupleScheme extends org.apache.thrift.scheme.TupleScheme<echo_args> {

      @Override
      public void write(org.apache.thrift.protocol.TProtocol prot, echo_args struct) throws org.apache.thrift.TException {
        org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
        java.util.BitSet optionals = new java.util.BitSet();
        if (struct.isSetInput1()) {
          optionals.set(0);
        }
        if (struct.isSetInput2()) {
          optionals.set(1);
        }
        oprot.writeBitSet(optionals, 2);
        if (struct.isSetInput1()) {
          struct.input1.write(oprot);
        }
        if (struct.isSetInput2()) {
          oprot.writeString(struct.input2);
        }
      }

      @Override
      public void read(org.apache.thrift.protocol.TProtocol prot, echo_args struct) throws org.apache.thrift.TException {
        org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
        java.util.BitSet incoming = iprot.readBitSet(2);
        if (incoming.get(0)) {
          struct.input1 = new Input();
          struct.input1.read(iprot);
          struct.setInput1IsSet(true);
        }
        if (incoming.get(1)) {
          struct.input2 = iprot.readString();
          struct.setInput2IsSet(true);
        }
      }
    }

    private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
      return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
    }
  }

  public static class echo_result implements org.apache.thrift.TBase<echo_result, echo_result._Fields>, java.io.Serializable, Cloneable, Comparable<echo_result>   {
    private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("echo_result");

    private static final org.apache.thrift.protocol.TField SUCCESS_FIELD_DESC = new org.apache.thrift.protocol.TField("success", org.apache.thrift.protocol.TType.STRUCT, (short)0);

    private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new echo_resultStandardSchemeFactory();
    private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new echo_resultTupleSchemeFactory();

    public Output success; // required

    /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
    public enum _Fields implements org.apache.thrift.TFieldIdEnum {
      SUCCESS((short)0, "success");

      private static final java.util.Map<java.lang.String, _Fields> byName = new java.util.HashMap<java.lang.String, _Fields>();

      static {
        for (_Fields field : java.util.EnumSet.allOf(_Fields.class)) {
          byName.put(field.getFieldName(), field);
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, or null if its not found.
       */
      public static _Fields findByThriftId(int fieldId) {
        switch(fieldId) {
          case 0: // SUCCESS
            return SUCCESS;
          default:
            return null;
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, throwing an exception
       * if it is not found.
       */
      public static _Fields findByThriftIdOrThrow(int fieldId) {
        _Fields fields = findByThriftId(fieldId);
        if (fields == null) throw new java.lang.IllegalArgumentException("Field " + fieldId + " doesn't exist!");
        return fields;
      }

      /**
       * Find the _Fields constant that matches name, or null if its not found.
       */
      public static _Fields findByName(java.lang.String name) {
        return byName.get(name);
      }

      private final short _thriftId;
      private final java.lang.String _fieldName;

      _Fields(short thriftId, java.lang.String fieldName) {
        _thriftId = thriftId;
        _fieldName = fieldName;
      }

      public short getThriftFieldId() {
        return _thriftId;
      }

      public java.lang.String getFieldName() {
        return _fieldName;
      }
    }

    // isset id assignments
    public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
    static {
      java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
      tmpMap.put(_Fields.SUCCESS, new org.apache.thrift.meta_data.FieldMetaData("success", org.apache.thrift.TFieldRequirementType.DEFAULT, 
          new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, Output.class)));
      metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
      org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(echo_result.class, metaDataMap);
    }

    public echo_result() {
    }

    public echo_result(
      Output success)
    {
      this();
      this.success = success;
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public echo_result(echo_result other) {
      if (other.isSetSuccess()) {
        this.success = new Output(other.success);
      }
    }

    public echo_result deepCopy() {
      return new echo_result(this);
    }

    @Override
    public void clear() {
      this.success = null;
    }

    public Output getSuccess() {
      return this.success;
    }

    public echo_result setSuccess(Output success) {
      this.success = success;
      return this;
    }

    public void unsetSuccess() {
      this.success = null;
    }

    /** Returns true if field success is set (has been assigned a value) and false otherwise */
    public boolean isSetSuccess() {
      return this.success != null;
    }

    public void setSuccessIsSet(boolean value) {
      if (!value) {
        this.success = null;
      }
    }

    public void setFieldValue(_Fields field, java.lang.Object value) {
      switch (field) {
      case SUCCESS:
        if (value == null) {
          unsetSuccess();
        } else {
          setSuccess((Output)value);
        }
        break;

      }
    }

    public java.lang.Object getFieldValue(_Fields field) {
      switch (field) {
      case SUCCESS:
        return getSuccess();

      }
      throw new java.lang.IllegalStateException();
    }

    /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
    public boolean isSet(_Fields field) {
      if (field == null) {
        throw new java.lang.IllegalArgumentException();
      }

      switch (field) {
      case SUCCESS:
        return isSetSuccess();
      }
      throw new java.lang.IllegalStateException();
    }

    @Override
    public boolean equals(java.lang.Object that) {
      if (that == null)
        return false;
      if (that instanceof echo_result)
        return this.equals((echo_result)that);
      return false;
    }

    public boolean equals(echo_result that) {
      if (that == null)
        return false;
      if (this == that)
        return true;

      boolean this_present_success = true && this.isSetSuccess();
      boolean that_present_success = true && that.isSetSuccess();
      if (this_present_success || that_present_success) {
        if (!(this_present_success && that_present_success))
          return false;
        if (!this.success.equals(that.success))
          return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      int hashCode = 1;

      hashCode = hashCode * 8191 + ((isSetSuccess()) ? 131071 : 524287);
      if (isSetSuccess())
        hashCode = hashCode * 8191 + success.hashCode();

      return hashCode;
    }

    @Override
    public int compareTo(echo_result other) {
      if (!getClass().equals(other.getClass())) {
        return getClass().getName().compareTo(other.getClass().getName());
      }

      int lastComparison = 0;

      lastComparison = java.lang.Boolean.valueOf(isSetSuccess()).compareTo(other.isSetSuccess());
      if (lastComparison != 0) {
        return lastComparison;
      }
      if (isSetSuccess()) {
        lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.success, other.success);
        if (lastComparison != 0) {
          return lastComparison;
        }
      }
      return 0;
    }

    public _Fields fieldForId(int fieldId) {
      return _Fields.findByThriftId(fieldId);
    }

    public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
      scheme(iprot).read(iprot, this);
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
      scheme(oprot).write(oprot, this);
      }

    @Override
    public java.lang.String toString() {
      java.lang.StringBuilder sb = new java.lang.StringBuilder("echo_result(");
      boolean first = true;

      sb.append("success:");
      if (this.success == null) {
        sb.append("null");
      } else {
        sb.append(this.success);
      }
      first = false;
      sb.append(")");
      return sb.toString();
    }

    public void validate() throws org.apache.thrift.TException {
      // check for required fields
      // check for sub-struct validity
      if (success != null) {
        success.validate();
      }
    }

    private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
      try {
        write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
      } catch (org.apache.thrift.TException te) {
        throw new java.io.IOException(te);
      }
    }

    private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, java.lang.ClassNotFoundException {
      try {
        read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
      } catch (org.apache.thrift.TException te) {
        throw new java.io.IOException(te);
      }
    }

    private static class echo_resultStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
      public echo_resultStandardScheme getScheme() {
        return new echo_resultStandardScheme();
      }
    }

    private static class echo_resultStandardScheme extends org.apache.thrift.scheme.StandardScheme<echo_result> {

      public void read(org.apache.thrift.protocol.TProtocol iprot, echo_result struct) throws org.apache.thrift.TException {
        org.apache.thrift.protocol.TField schemeField;
        iprot.readStructBegin();
        while (true)
        {
          schemeField = iprot.readFieldBegin();
          if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
            break;
          }
          switch (schemeField.id) {
            case 0: // SUCCESS
              if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
                struct.success = new Output();
                struct.success.read(iprot);
                struct.setSuccessIsSet(true);
              } else { 
                org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
              }
              break;
            default:
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
          }
          iprot.readFieldEnd();
        }
        iprot.readStructEnd();

        // check for required fields of primitive type, which can't be checked in the validate method
        struct.validate();
      }

      public void write(org.apache.thrift.protocol.TProtocol oprot, echo_result struct) throws org.apache.thrift.TException {
        struct.validate();

        oprot.writeStructBegin(STRUCT_DESC);
        if (struct.success != null) {
          oprot.writeFieldBegin(SUCCESS_FIELD_DESC);
          struct.success.write(oprot);
          oprot.writeFieldEnd();
        }
        oprot.writeFieldStop();
        oprot.writeStructEnd();
      }

    }

    private static class echo_resultTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
      public echo_resultTupleScheme getScheme() {
        return new echo_resultTupleScheme();
      }
    }

    private static class echo_resultTupleScheme extends org.apache.thrift.scheme.TupleScheme<echo_result> {

      @Override
      public void write(org.apache.thrift.protocol.TProtocol prot, echo_result struct) throws org.apache.thrift.TException {
        org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
        java.util.BitSet optionals = new java.util.BitSet();
        if (struct.isSetSuccess()) {
          optionals.set(0);
        }
        oprot.writeBitSet(optionals, 1);
        if (struct.isSetSuccess()) {
          struct.success.write(oprot);
        }
      }

      @Override
      public void read(org.apache.thrift.protocol.TProtocol prot, echo_result struct) throws org.apache.thrift.TException {
        org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
        java.util.BitSet incoming = iprot.readBitSet(1);
        if (incoming.get(0)) {
          struct.success = new Output();
          struct.success.read(iprot);
          struct.setSuccessIsSet(true);
        }
      }
    }

    private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
      return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
    }
  }

}