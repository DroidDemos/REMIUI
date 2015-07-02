package org.apache.thrift;

import com.xiaomi.xmsf.push.service.Constants;
import org.apache.thrift.protocol.TField;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolUtil;
import org.apache.thrift.protocol.TStruct;
import org.apache.thrift.protocol.TType;

public class TApplicationException extends TException {
    public static final int BAD_SEQUENCE_ID = 4;
    public static final int INTERNAL_ERROR = 6;
    public static final int INVALID_MESSAGE_TYPE = 2;
    private static final TField MESSAGE_FIELD;
    public static final int MISSING_RESULT = 5;
    public static final int PROTOCOL_ERROR = 7;
    private static final TStruct TAPPLICATION_EXCEPTION_STRUCT;
    private static final TField TYPE_FIELD;
    public static final int UNKNOWN = 0;
    public static final int UNKNOWN_METHOD = 1;
    public static final int WRONG_METHOD_NAME = 3;
    private static final long serialVersionUID = 1;
    protected int type_;

    static {
        TAPPLICATION_EXCEPTION_STRUCT = new TStruct("TApplicationException");
        MESSAGE_FIELD = new TField("message", TType.STRING, (short) 1);
        TYPE_FIELD = new TField(Constants.JSON_TAG_ADSTYPE, (byte) 8, (short) 2);
    }

    public TApplicationException() {
        this.type_ = UNKNOWN;
    }

    public TApplicationException(int type) {
        this.type_ = UNKNOWN;
        this.type_ = type;
    }

    public TApplicationException(int type, String message) {
        super(message);
        this.type_ = UNKNOWN;
        this.type_ = type;
    }

    public TApplicationException(String message) {
        super(message);
        this.type_ = UNKNOWN;
    }

    public int getType() {
        return this.type_;
    }

    public static TApplicationException read(TProtocol iprot) throws TException {
        iprot.readStructBegin();
        String message = null;
        int type = UNKNOWN;
        while (true) {
            TField field = iprot.readFieldBegin();
            if (field.type == (byte) 0) {
                iprot.readStructEnd();
                return new TApplicationException(type, message);
            }
            switch (field.id) {
                case UNKNOWN_METHOD /*1*/:
                    if (field.type != TType.STRING) {
                        TProtocolUtil.skip(iprot, field.type);
                        break;
                    }
                    message = iprot.readString();
                    break;
                case INVALID_MESSAGE_TYPE /*2*/:
                    if (field.type != (byte) 8) {
                        TProtocolUtil.skip(iprot, field.type);
                        break;
                    }
                    type = iprot.readI32();
                    break;
                default:
                    TProtocolUtil.skip(iprot, field.type);
                    break;
            }
            iprot.readFieldEnd();
        }
    }

    public void write(TProtocol oprot) throws TException {
        oprot.writeStructBegin(TAPPLICATION_EXCEPTION_STRUCT);
        if (getMessage() != null) {
            oprot.writeFieldBegin(MESSAGE_FIELD);
            oprot.writeString(getMessage());
            oprot.writeFieldEnd();
        }
        oprot.writeFieldBegin(TYPE_FIELD);
        oprot.writeI32(this.type_);
        oprot.writeFieldEnd();
        oprot.writeFieldStop();
        oprot.writeStructEnd();
    }
}
