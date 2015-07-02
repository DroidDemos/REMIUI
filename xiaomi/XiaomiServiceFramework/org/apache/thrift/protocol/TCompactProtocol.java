package org.apache.thrift.protocol;

import com.xiaomi.push.service.PushConstants;
import com.xiaomi.xmsf.push.service.Constants;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import org.apache.thrift.ShortStack;
import org.apache.thrift.TApplicationException;
import org.apache.thrift.TException;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

public final class TCompactProtocol extends TProtocol {
    private static final TStruct ANONYMOUS_STRUCT;
    private static final byte PROTOCOL_ID = (byte) -126;
    private static final TField TSTOP;
    private static final byte TYPE_MASK = (byte) -32;
    private static final int TYPE_SHIFT_AMOUNT = 5;
    private static final byte VERSION = (byte) 1;
    private static final byte VERSION_MASK = (byte) 31;
    private static final byte[] ttypeToCompactType;
    private Boolean boolValue_;
    private TField booleanField_;
    private byte[] byteDirectBuffer;
    byte[] byteRawBuf;
    byte[] i32buf;
    private short lastFieldId_;
    private ShortStack lastField_;
    byte[] varint64out;

    public static class Factory implements TProtocolFactory {
        public TProtocol getProtocol(TTransport trans) {
            return new TCompactProtocol(trans);
        }
    }

    private static class Types {
        public static final byte BINARY = (byte) 8;
        public static final byte BOOLEAN_FALSE = (byte) 2;
        public static final byte BOOLEAN_TRUE = (byte) 1;
        public static final byte BYTE = (byte) 3;
        public static final byte DOUBLE = (byte) 7;
        public static final byte I16 = (byte) 4;
        public static final byte I32 = (byte) 5;
        public static final byte I64 = (byte) 6;
        public static final byte LIST = (byte) 9;
        public static final byte MAP = (byte) 11;
        public static final byte SET = (byte) 10;
        public static final byte STRUCT = (byte) 12;

        private Types() {
        }
    }

    static {
        ANONYMOUS_STRUCT = new TStruct("");
        TSTOP = new TField("", (byte) 0, (short) 0);
        ttypeToCompactType = new byte[16];
        ttypeToCompactType[0] = (byte) 0;
        ttypeToCompactType[2] = VERSION;
        ttypeToCompactType[3] = (byte) 3;
        ttypeToCompactType[6] = (byte) 4;
        ttypeToCompactType[8] = (byte) 5;
        ttypeToCompactType[10] = (byte) 6;
        ttypeToCompactType[4] = (byte) 7;
        ttypeToCompactType[11] = (byte) 8;
        ttypeToCompactType[15] = (byte) 9;
        ttypeToCompactType[14] = (byte) 10;
        ttypeToCompactType[13] = TType.STRING;
        ttypeToCompactType[12] = TType.STRUCT;
    }

    public TCompactProtocol(TTransport transport) {
        super(transport);
        this.lastField_ = new ShortStack(15);
        this.lastFieldId_ = (short) 0;
        this.booleanField_ = null;
        this.boolValue_ = null;
        this.i32buf = new byte[TYPE_SHIFT_AMOUNT];
        this.varint64out = new byte[10];
        this.byteDirectBuffer = new byte[1];
        this.byteRawBuf = new byte[1];
    }

    public void reset() {
        this.lastField_.clear();
        this.lastFieldId_ = (short) 0;
    }

    public void writeMessageBegin(TMessage message) throws TException {
        writeByteDirect((byte) PROTOCOL_ID);
        writeByteDirect(((message.type << TYPE_SHIFT_AMOUNT) & -32) | 1);
        writeVarint32(message.seqid);
        writeString(message.name);
    }

    public void writeStructBegin(TStruct struct) throws TException {
        this.lastField_.push(this.lastFieldId_);
        this.lastFieldId_ = (short) 0;
    }

    public void writeStructEnd() throws TException {
        this.lastFieldId_ = this.lastField_.pop();
    }

    public void writeFieldBegin(TField field) throws TException {
        if (field.type == (byte) 2) {
            this.booleanField_ = field;
        } else {
            writeFieldBeginInternal(field, (byte) -1);
        }
    }

    private void writeFieldBeginInternal(TField field, byte typeOverride) throws TException {
        byte typeToWrite;
        if (typeOverride == (byte) -1) {
            typeToWrite = getCompactType(field.type);
        } else {
            typeToWrite = typeOverride;
        }
        if (field.id <= this.lastFieldId_ || field.id - this.lastFieldId_ > 15) {
            writeByteDirect(typeToWrite);
            writeI16(field.id);
        } else {
            writeByteDirect(((field.id - this.lastFieldId_) << 4) | typeToWrite);
        }
        this.lastFieldId_ = field.id;
    }

    public void writeFieldStop() throws TException {
        writeByteDirect((byte) 0);
    }

    public void writeMapBegin(TMap map) throws TException {
        if (map.size == 0) {
            writeByteDirect(0);
            return;
        }
        writeVarint32(map.size);
        writeByteDirect((getCompactType(map.keyType) << 4) | getCompactType(map.valueType));
    }

    public void writeListBegin(TList list) throws TException {
        writeCollectionBegin(list.elemType, list.size);
    }

    public void writeSetBegin(TSet set) throws TException {
        writeCollectionBegin(set.elemType, set.size);
    }

    public void writeBool(boolean b) throws TException {
        byte b2 = VERSION;
        if (this.booleanField_ != null) {
            TField tField = this.booleanField_;
            if (!b) {
                b2 = (byte) 2;
            }
            writeFieldBeginInternal(tField, b2);
            this.booleanField_ = null;
            return;
        }
        if (!b) {
            b2 = (byte) 2;
        }
        writeByteDirect(b2);
    }

    public void writeByte(byte b) throws TException {
        writeByteDirect(b);
    }

    public void writeI16(short i16) throws TException {
        writeVarint32(intToZigZag(i16));
    }

    public void writeI32(int i32) throws TException {
        writeVarint32(intToZigZag(i32));
    }

    public void writeI64(long i64) throws TException {
        writeVarint64(longToZigzag(i64));
    }

    public void writeDouble(double dub) throws TException {
        byte[] data = new byte[]{(byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
        fixedLongToBytes(Double.doubleToLongBits(dub), data, 0);
        this.trans_.write(data);
    }

    public void writeString(String str) throws TException {
        try {
            byte[] bytes = str.getBytes("UTF-8");
            writeBinary(bytes, 0, bytes.length);
        } catch (UnsupportedEncodingException e) {
            throw new TException("UTF-8 not supported!");
        }
    }

    public void writeBinary(ByteBuffer bin) throws TException {
        writeBinary(bin.array(), bin.position() + bin.arrayOffset(), (bin.limit() - bin.position()) - bin.arrayOffset());
    }

    private void writeBinary(byte[] buf, int offset, int length) throws TException {
        writeVarint32(length);
        this.trans_.write(buf, offset, length);
    }

    public void writeMessageEnd() throws TException {
    }

    public void writeMapEnd() throws TException {
    }

    public void writeListEnd() throws TException {
    }

    public void writeSetEnd() throws TException {
    }

    public void writeFieldEnd() throws TException {
    }

    protected void writeCollectionBegin(byte elemType, int size) throws TException {
        if (size <= 14) {
            writeByteDirect((size << 4) | getCompactType(elemType));
            return;
        }
        writeByteDirect(getCompactType(elemType) | 240);
        writeVarint32(size);
    }

    private void writeVarint32(int n) throws TException {
        int idx;
        int idx2 = 0;
        while ((n & -128) != 0) {
            idx = idx2 + 1;
            this.i32buf[idx2] = (byte) ((n & 127) | 128);
            n >>>= 7;
            idx2 = idx;
        }
        idx = idx2 + 1;
        this.i32buf[idx2] = (byte) n;
        this.trans_.write(this.i32buf, 0, idx);
    }

    private void writeVarint64(long n) throws TException {
        int idx;
        int idx2 = 0;
        while ((-128 & n) != 0) {
            idx = idx2 + 1;
            this.varint64out[idx2] = (byte) ((int) ((127 & n) | 128));
            n >>>= 7;
            idx2 = idx;
        }
        idx = idx2 + 1;
        this.varint64out[idx2] = (byte) ((int) n);
        this.trans_.write(this.varint64out, 0, idx);
    }

    private long longToZigzag(long l) {
        return (l << 1) ^ (l >> 63);
    }

    private int intToZigZag(int n) {
        return (n << 1) ^ (n >> 31);
    }

    private void fixedLongToBytes(long n, byte[] buf, int off) {
        buf[off + 0] = (byte) ((int) (n & 255));
        buf[off + 1] = (byte) ((int) ((n >> 8) & 255));
        buf[off + 2] = (byte) ((int) ((n >> 16) & 255));
        buf[off + 3] = (byte) ((int) ((n >> 24) & 255));
        buf[off + 4] = (byte) ((int) ((n >> 32) & 255));
        buf[off + TYPE_SHIFT_AMOUNT] = (byte) ((int) ((n >> 40) & 255));
        buf[off + 6] = (byte) ((int) ((n >> 48) & 255));
        buf[off + 7] = (byte) ((int) ((n >> 56) & 255));
    }

    private void writeByteDirect(byte b) throws TException {
        this.byteDirectBuffer[0] = b;
        this.trans_.write(this.byteDirectBuffer);
    }

    private void writeByteDirect(int n) throws TException {
        writeByteDirect((byte) n);
    }

    public TMessage readMessageBegin() throws TException {
        byte protocolId = readByte();
        if (protocolId != PROTOCOL_ID) {
            throw new TProtocolException("Expected protocol id " + Integer.toHexString(-126) + " but got " + Integer.toHexString(protocolId));
        }
        byte versionAndType = readByte();
        byte version = (byte) (versionAndType & 31);
        if (version != VERSION) {
            throw new TProtocolException("Expected version 1 but got " + version);
        }
        return new TMessage(readString(), (byte) ((versionAndType >> TYPE_SHIFT_AMOUNT) & 3), readVarint32());
    }

    public TStruct readStructBegin() throws TException {
        this.lastField_.push(this.lastFieldId_);
        this.lastFieldId_ = (short) 0;
        return ANONYMOUS_STRUCT;
    }

    public void readStructEnd() throws TException {
        this.lastFieldId_ = this.lastField_.pop();
    }

    public TField readFieldBegin() throws TException {
        byte type = readByte();
        if (type == (byte) 0) {
            return TSTOP;
        }
        short fieldId;
        short modifier = (short) ((type & 240) >> 4);
        if (modifier == (short) 0) {
            fieldId = readI16();
        } else {
            fieldId = (short) (this.lastFieldId_ + modifier);
        }
        TField field = new TField("", getTType((byte) (type & 15)), fieldId);
        if (isBoolType(type)) {
            this.boolValue_ = ((byte) (type & 15)) == VERSION ? Boolean.TRUE : Boolean.FALSE;
        }
        this.lastFieldId_ = field.id;
        return field;
    }

    public TMap readMapBegin() throws TException {
        int size = readVarint32();
        byte keyAndValueType = size == 0 ? (byte) 0 : readByte();
        return new TMap(getTType((byte) (keyAndValueType >> 4)), getTType((byte) (keyAndValueType & 15)), size);
    }

    public TList readListBegin() throws TException {
        byte size_and_type = readByte();
        int size = (size_and_type >> 4) & 15;
        if (size == 15) {
            size = readVarint32();
        }
        return new TList(getTType(size_and_type), size);
    }

    public TSet readSetBegin() throws TException {
        return new TSet(readListBegin());
    }

    public boolean readBool() throws TException {
        boolean z = true;
        if (this.boolValue_ != null) {
            boolean result = this.boolValue_.booleanValue();
            this.boolValue_ = null;
            return result;
        }
        if (readByte() != VERSION) {
            z = false;
        }
        return z;
    }

    public byte readByte() throws TException {
        if (this.trans_.getBytesRemainingInBuffer() > 0) {
            byte b = this.trans_.getBuffer()[this.trans_.getBufferPosition()];
            this.trans_.consumeBuffer(1);
            return b;
        }
        this.trans_.readAll(this.byteRawBuf, 0, 1);
        return this.byteRawBuf[0];
    }

    public short readI16() throws TException {
        return (short) zigzagToInt(readVarint32());
    }

    public int readI32() throws TException {
        return zigzagToInt(readVarint32());
    }

    public long readI64() throws TException {
        return zigzagToLong(readVarint64());
    }

    public double readDouble() throws TException {
        byte[] longBits = new byte[8];
        this.trans_.readAll(longBits, 0, 8);
        return Double.longBitsToDouble(bytesToLong(longBits));
    }

    public String readString() throws TException {
        int length = readVarint32();
        if (length == 0) {
            return "";
        }
        try {
            if (this.trans_.getBytesRemainingInBuffer() < length) {
                return new String(readBinary(length), "UTF-8");
            }
            String str = new String(this.trans_.getBuffer(), this.trans_.getBufferPosition(), length, "UTF-8");
            this.trans_.consumeBuffer(length);
            return str;
        } catch (UnsupportedEncodingException e) {
            throw new TException("UTF-8 not supported!");
        }
    }

    public ByteBuffer readBinary() throws TException {
        int length = readVarint32();
        if (length == 0) {
            return ByteBuffer.wrap(new byte[0]);
        }
        byte[] buf = new byte[length];
        this.trans_.readAll(buf, 0, length);
        return ByteBuffer.wrap(buf);
    }

    private byte[] readBinary(int length) throws TException {
        if (length == 0) {
            return new byte[0];
        }
        byte[] buf = new byte[length];
        this.trans_.readAll(buf, 0, length);
        return buf;
    }

    public void readMessageEnd() throws TException {
    }

    public void readFieldEnd() throws TException {
    }

    public void readMapEnd() throws TException {
    }

    public void readListEnd() throws TException {
    }

    public void readSetEnd() throws TException {
    }

    private int readVarint32() throws TException {
        int result = 0;
        int shift = 0;
        byte b;
        if (this.trans_.getBytesRemainingInBuffer() >= TYPE_SHIFT_AMOUNT) {
            byte[] buf = this.trans_.getBuffer();
            int pos = this.trans_.getBufferPosition();
            int off = 0;
            while (true) {
                b = buf[pos + off];
                result |= (b & 127) << shift;
                if ((b & 128) != 128) {
                    break;
                }
                shift += 7;
                off++;
            }
            this.trans_.consumeBuffer(off + 1);
        } else {
            while (true) {
                b = readByte();
                result |= (b & 127) << shift;
                if ((b & 128) != 128) {
                    break;
                }
                shift += 7;
            }
        }
        return result;
    }

    private long readVarint64() throws TException {
        int shift = 0;
        long result = 0;
        byte b;
        if (this.trans_.getBytesRemainingInBuffer() >= 10) {
            byte[] buf = this.trans_.getBuffer();
            int pos = this.trans_.getBufferPosition();
            int off = 0;
            while (true) {
                b = buf[pos + off];
                result |= ((long) (b & 127)) << shift;
                if ((b & 128) != 128) {
                    break;
                }
                shift += 7;
                off++;
            }
            this.trans_.consumeBuffer(off + 1);
        } else {
            while (true) {
                b = readByte();
                result |= ((long) (b & 127)) << shift;
                if ((b & 128) != 128) {
                    break;
                }
                shift += 7;
            }
        }
        return result;
    }

    private int zigzagToInt(int n) {
        return (n >>> 1) ^ (-(n & 1));
    }

    private long zigzagToLong(long n) {
        return (n >>> 1) ^ (-(1 & n));
    }

    private long bytesToLong(byte[] bytes) {
        return ((((((((((long) bytes[7]) & 255) << 56) | ((((long) bytes[6]) & 255) << 48)) | ((((long) bytes[TYPE_SHIFT_AMOUNT]) & 255) << 40)) | ((((long) bytes[4]) & 255) << 32)) | ((((long) bytes[3]) & 255) << 24)) | ((((long) bytes[2]) & 255) << 16)) | ((((long) bytes[1]) & 255) << 8)) | (((long) bytes[0]) & 255);
    }

    private boolean isBoolType(byte b) {
        int lowerNibble = b & 15;
        if (lowerNibble == 1 || lowerNibble == 2) {
            return true;
        }
        return false;
    }

    private byte getTType(byte type) throws TProtocolException {
        switch ((byte) (type & 15)) {
            case TTransportException.UNKNOWN /*0*/:
                return (byte) 0;
            case TTransportException.NOT_OPEN /*1*/:
            case TTransportException.ALREADY_OPEN /*2*/:
                return (byte) 2;
            case TTransportException.TIMED_OUT /*3*/:
                return (byte) 3;
            case TTransportException.END_OF_FILE /*4*/:
                return (byte) 6;
            case TYPE_SHIFT_AMOUNT /*5*/:
                return (byte) 8;
            case TApplicationException.INTERNAL_ERROR /*6*/:
                return (byte) 10;
            case TApplicationException.PROTOCOL_ERROR /*7*/:
                return (byte) 4;
            case PushConstants.ERROR_RECEIVE_TIMEOUT /*8*/:
                return TType.STRING;
            case PushConstants.ERROR_READ_ERROR /*9*/:
                return TType.LIST;
            case Constants.VALUE_MAX_FAILEDCOUNT /*10*/:
                return TType.SET;
            case PushConstants.ERROR_RESET /*11*/:
                return TType.MAP;
            case PushConstants.ERROR_NO_CLIENT /*12*/:
                return TType.STRUCT;
            default:
                throw new TProtocolException("don't know what type: " + ((byte) (type & 15)));
        }
    }

    private byte getCompactType(byte ttype) {
        return ttypeToCompactType[ttype];
    }
}
