package org.apache.thrift;

import com.xiaomi.push.service.PushConstants;
import com.xiaomi.xmsf.push.service.Constants;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import org.apache.thrift.protocol.TBinaryProtocol.Factory;
import org.apache.thrift.protocol.TField;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.protocol.TProtocolUtil;
import org.apache.thrift.protocol.TType;
import org.apache.thrift.transport.TMemoryInputTransport;
import org.apache.thrift.transport.TTransportException;

public class TDeserializer {
    private final TProtocol protocol_;
    private final TMemoryInputTransport trans_;

    public TDeserializer() {
        this(new Factory());
    }

    public TDeserializer(TProtocolFactory protocolFactory) {
        this.trans_ = new TMemoryInputTransport();
        this.protocol_ = protocolFactory.getProtocol(this.trans_);
    }

    public void deserialize(TBase base, byte[] bytes) throws TException {
        try {
            this.trans_.reset(bytes);
            base.read(this.protocol_);
        } finally {
            this.protocol_.reset();
        }
    }

    public void deserialize(TBase base, String data, String charset) throws TException {
        try {
            deserialize(base, data.getBytes(charset));
            this.protocol_.reset();
        } catch (UnsupportedEncodingException e) {
            throw new TException("JVM DOES NOT SUPPORT ENCODING: " + charset);
        } catch (Throwable th) {
            this.protocol_.reset();
        }
    }

    public void partialDeserialize(TBase tb, byte[] bytes, TFieldIdEnum fieldIdPathFirst, TFieldIdEnum... fieldIdPathRest) throws TException {
        try {
            if (locateField(bytes, fieldIdPathFirst, fieldIdPathRest) != null) {
                tb.read(this.protocol_);
            }
            this.protocol_.reset();
        } catch (Throwable e) {
            throw new TException(e);
        } catch (Throwable th) {
            this.protocol_.reset();
        }
    }

    public Boolean partialDeserializeBool(byte[] bytes, TFieldIdEnum fieldIdPathFirst, TFieldIdEnum... fieldIdPathRest) throws TException {
        return (Boolean) partialDeserializeField((byte) 2, bytes, fieldIdPathFirst, fieldIdPathRest);
    }

    public Byte partialDeserializeByte(byte[] bytes, TFieldIdEnum fieldIdPathFirst, TFieldIdEnum... fieldIdPathRest) throws TException {
        return (Byte) partialDeserializeField((byte) 3, bytes, fieldIdPathFirst, fieldIdPathRest);
    }

    public Double partialDeserializeDouble(byte[] bytes, TFieldIdEnum fieldIdPathFirst, TFieldIdEnum... fieldIdPathRest) throws TException {
        return (Double) partialDeserializeField((byte) 4, bytes, fieldIdPathFirst, fieldIdPathRest);
    }

    public Short partialDeserializeI16(byte[] bytes, TFieldIdEnum fieldIdPathFirst, TFieldIdEnum... fieldIdPathRest) throws TException {
        return (Short) partialDeserializeField((byte) 6, bytes, fieldIdPathFirst, fieldIdPathRest);
    }

    public Integer partialDeserializeI32(byte[] bytes, TFieldIdEnum fieldIdPathFirst, TFieldIdEnum... fieldIdPathRest) throws TException {
        return (Integer) partialDeserializeField((byte) 8, bytes, fieldIdPathFirst, fieldIdPathRest);
    }

    public Long partialDeserializeI64(byte[] bytes, TFieldIdEnum fieldIdPathFirst, TFieldIdEnum... fieldIdPathRest) throws TException {
        return (Long) partialDeserializeField((byte) 10, bytes, fieldIdPathFirst, fieldIdPathRest);
    }

    public String partialDeserializeString(byte[] bytes, TFieldIdEnum fieldIdPathFirst, TFieldIdEnum... fieldIdPathRest) throws TException {
        return (String) partialDeserializeField(TType.STRING, bytes, fieldIdPathFirst, fieldIdPathRest);
    }

    public ByteBuffer partialDeserializeByteArray(byte[] bytes, TFieldIdEnum fieldIdPathFirst, TFieldIdEnum... fieldIdPathRest) throws TException {
        return (ByteBuffer) partialDeserializeField((byte) 100, bytes, fieldIdPathFirst, fieldIdPathRest);
    }

    public Short partialDeserializeSetFieldIdInUnion(byte[] bytes, TFieldIdEnum fieldIdPathFirst, TFieldIdEnum... fieldIdPathRest) throws TException {
        try {
            if (locateField(bytes, fieldIdPathFirst, fieldIdPathRest) != null) {
                this.protocol_.readStructBegin();
                Short valueOf = Short.valueOf(this.protocol_.readFieldBegin().id);
                this.protocol_.reset();
                return valueOf;
            }
            this.protocol_.reset();
            return null;
        } catch (Throwable e) {
            throw new TException(e);
        } catch (Throwable th) {
            this.protocol_.reset();
        }
    }

    private Object partialDeserializeField(byte ttype, byte[] bytes, TFieldIdEnum fieldIdPathFirst, TFieldIdEnum... fieldIdPathRest) throws TException {
        try {
            TField field = locateField(bytes, fieldIdPathFirst, fieldIdPathRest);
            if (field != null) {
                Object valueOf;
                switch (ttype) {
                    case TTransportException.ALREADY_OPEN /*2*/:
                        if (field.type == (byte) 2) {
                            valueOf = Boolean.valueOf(this.protocol_.readBool());
                            this.protocol_.reset();
                            return valueOf;
                        }
                        break;
                    case TTransportException.TIMED_OUT /*3*/:
                        if (field.type == (byte) 3) {
                            valueOf = Byte.valueOf(this.protocol_.readByte());
                            this.protocol_.reset();
                            return valueOf;
                        }
                        break;
                    case TTransportException.END_OF_FILE /*4*/:
                        if (field.type == (byte) 4) {
                            valueOf = Double.valueOf(this.protocol_.readDouble());
                            this.protocol_.reset();
                            return valueOf;
                        }
                        break;
                    case TApplicationException.INTERNAL_ERROR /*6*/:
                        if (field.type == (byte) 6) {
                            valueOf = Short.valueOf(this.protocol_.readI16());
                            this.protocol_.reset();
                            return valueOf;
                        }
                        break;
                    case PushConstants.ERROR_RECEIVE_TIMEOUT /*8*/:
                        if (field.type == (byte) 8) {
                            valueOf = Integer.valueOf(this.protocol_.readI32());
                            this.protocol_.reset();
                            return valueOf;
                        }
                        break;
                    case Constants.VALUE_MAX_FAILEDCOUNT /*10*/:
                        if (field.type == (byte) 10) {
                            valueOf = Long.valueOf(this.protocol_.readI64());
                            this.protocol_.reset();
                            return valueOf;
                        }
                        break;
                    case PushConstants.ERROR_RESET /*11*/:
                        if (field.type == TType.STRING) {
                            valueOf = this.protocol_.readString();
                            this.protocol_.reset();
                            return valueOf;
                        }
                        break;
                    case (byte) 100:
                        if (field.type == TType.STRING) {
                            valueOf = this.protocol_.readBinary();
                            this.protocol_.reset();
                            return valueOf;
                        }
                        break;
                }
            }
            this.protocol_.reset();
            return null;
        } catch (Throwable e) {
            throw new TException(e);
        } catch (Throwable th) {
            this.protocol_.reset();
        }
    }

    private TField locateField(byte[] bytes, TFieldIdEnum fieldIdPathFirst, TFieldIdEnum... fieldIdPathRest) throws TException {
        this.trans_.reset(bytes);
        TFieldIdEnum[] fieldIdPath = new TFieldIdEnum[(fieldIdPathRest.length + 1)];
        fieldIdPath[0] = fieldIdPathFirst;
        for (int i = 0; i < fieldIdPathRest.length; i++) {
            fieldIdPath[i + 1] = fieldIdPathRest[i];
        }
        int curPathIndex = 0;
        TField field = null;
        this.protocol_.readStructBegin();
        while (curPathIndex < fieldIdPath.length) {
            field = this.protocol_.readFieldBegin();
            if (field.type == (byte) 0 || field.id > fieldIdPath[curPathIndex].getThriftFieldId()) {
                return null;
            }
            if (field.id != fieldIdPath[curPathIndex].getThriftFieldId()) {
                TProtocolUtil.skip(this.protocol_, field.type);
                this.protocol_.readFieldEnd();
            } else {
                curPathIndex++;
                if (curPathIndex < fieldIdPath.length) {
                    this.protocol_.readStructBegin();
                }
            }
        }
        return field;
    }

    public void fromString(TBase base, String data) throws TException {
        deserialize(base, data.getBytes());
    }
}
