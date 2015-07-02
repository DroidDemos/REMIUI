package com.xiaomi.common.logger.thrift;

import com.xiaomi.push.service.PushServiceConstants;
import java.io.Serializable;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.thrift.TBase;
import org.apache.thrift.TBaseHelper;
import org.apache.thrift.TException;
import org.apache.thrift.TFieldIdEnum;
import org.apache.thrift.meta_data.FieldMetaData;
import org.apache.thrift.meta_data.FieldValueMetaData;
import org.apache.thrift.protocol.TField;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.protocol.TProtocolUtil;
import org.apache.thrift.protocol.TStruct;
import org.apache.thrift.protocol.TType;
import org.apache.thrift.transport.TTransportException;

public class Common implements TBase<Common, _Fields>, Serializable, Cloneable {
    private static final TField CLIENT_IP_FIELD_DESC;
    private static final TField SERVER_HOST_FIELD_DESC;
    private static final TField SERVER_IP_FIELD_DESC;
    private static final TStruct STRUCT_DESC;
    private static final TField TIME_FIELD_DESC;
    private static final TField UUID_FIELD_DESC;
    private static final int __UUID_ISSET_ID = 0;
    public static final Map<_Fields, FieldMetaData> metaDataMap;
    private BitSet __isset_bit_vector;
    public String clientIp;
    public String serverHost;
    public String serverIp;
    public String time;
    public long uuid;

    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$xiaomi$common$logger$thrift$Common$_Fields;

        static {
            $SwitchMap$com$xiaomi$common$logger$thrift$Common$_Fields = new int[_Fields.values().length];
            try {
                $SwitchMap$com$xiaomi$common$logger$thrift$Common$_Fields[_Fields.UUID.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$xiaomi$common$logger$thrift$Common$_Fields[_Fields.TIME.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$xiaomi$common$logger$thrift$Common$_Fields[_Fields.CLIENT_IP.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$xiaomi$common$logger$thrift$Common$_Fields[_Fields.SERVER_IP.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$xiaomi$common$logger$thrift$Common$_Fields[_Fields.SERVER_HOST.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
        }
    }

    public enum _Fields implements TFieldIdEnum {
        UUID((short) 1, PushServiceConstants.EXTRA_UUID),
        TIME((short) 2, "time"),
        CLIENT_IP((short) 3, "clientIp"),
        SERVER_IP((short) 4, "serverIp"),
        SERVER_HOST((short) 5, "serverHost");
        
        private static final Map<String, _Fields> byName;
        private final String _fieldName;
        private final short _thriftId;

        static {
            byName = new HashMap();
            Iterator i$ = EnumSet.allOf(_Fields.class).iterator();
            while (i$.hasNext()) {
                _Fields field = (_Fields) i$.next();
                byName.put(field.getFieldName(), field);
            }
        }

        public static _Fields findByThriftId(int fieldId) {
            switch (fieldId) {
                case TTransportException.NOT_OPEN /*1*/:
                    return UUID;
                case TTransportException.ALREADY_OPEN /*2*/:
                    return TIME;
                case TTransportException.TIMED_OUT /*3*/:
                    return CLIENT_IP;
                case TTransportException.END_OF_FILE /*4*/:
                    return SERVER_IP;
                case TProtocolException.NOT_IMPLEMENTED /*5*/:
                    return SERVER_HOST;
                default:
                    return null;
            }
        }

        public static _Fields findByThriftIdOrThrow(int fieldId) {
            _Fields fields = findByThriftId(fieldId);
            if (fields != null) {
                return fields;
            }
            throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
        }

        public static _Fields findByName(String name) {
            return (_Fields) byName.get(name);
        }

        private _Fields(short thriftId, String fieldName) {
            this._thriftId = thriftId;
            this._fieldName = fieldName;
        }

        public short getThriftFieldId() {
            return this._thriftId;
        }

        public String getFieldName() {
            return this._fieldName;
        }
    }

    static {
        STRUCT_DESC = new TStruct("Common");
        UUID_FIELD_DESC = new TField(PushServiceConstants.EXTRA_UUID, (byte) 10, (short) 1);
        TIME_FIELD_DESC = new TField("time", TType.STRING, (short) 2);
        CLIENT_IP_FIELD_DESC = new TField("clientIp", TType.STRING, (short) 3);
        SERVER_IP_FIELD_DESC = new TField("serverIp", TType.STRING, (short) 4);
        SERVER_HOST_FIELD_DESC = new TField("serverHost", TType.STRING, (short) 5);
        Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
        tmpMap.put(_Fields.UUID, new FieldMetaData(PushServiceConstants.EXTRA_UUID, (byte) 2, new FieldValueMetaData((byte) 10)));
        tmpMap.put(_Fields.TIME, new FieldMetaData("time", (byte) 2, new FieldValueMetaData(TType.STRING)));
        tmpMap.put(_Fields.CLIENT_IP, new FieldMetaData("clientIp", (byte) 2, new FieldValueMetaData(TType.STRING)));
        tmpMap.put(_Fields.SERVER_IP, new FieldMetaData("serverIp", (byte) 2, new FieldValueMetaData(TType.STRING)));
        tmpMap.put(_Fields.SERVER_HOST, new FieldMetaData("serverHost", (byte) 2, new FieldValueMetaData(TType.STRING)));
        metaDataMap = Collections.unmodifiableMap(tmpMap);
        FieldMetaData.addStructMetaDataMap(Common.class, metaDataMap);
    }

    public Common() {
        this.__isset_bit_vector = new BitSet(1);
        this.uuid = 0;
        this.time = "";
        this.clientIp = "";
        this.serverIp = "";
        this.serverHost = "";
    }

    public Common(Common other) {
        this.__isset_bit_vector = new BitSet(1);
        this.__isset_bit_vector.clear();
        this.__isset_bit_vector.or(other.__isset_bit_vector);
        this.uuid = other.uuid;
        if (other.isSetTime()) {
            this.time = other.time;
        }
        if (other.isSetClientIp()) {
            this.clientIp = other.clientIp;
        }
        if (other.isSetServerIp()) {
            this.serverIp = other.serverIp;
        }
        if (other.isSetServerHost()) {
            this.serverHost = other.serverHost;
        }
    }

    public Common deepCopy() {
        return new Common(this);
    }

    public void clear() {
        this.uuid = 0;
        this.time = "";
        this.clientIp = "";
        this.serverIp = "";
        this.serverHost = "";
    }

    public long getUuid() {
        return this.uuid;
    }

    public Common setUuid(long uuid) {
        this.uuid = uuid;
        setUuidIsSet(true);
        return this;
    }

    public void unsetUuid() {
        this.__isset_bit_vector.clear(0);
    }

    public boolean isSetUuid() {
        return this.__isset_bit_vector.get(0);
    }

    public void setUuidIsSet(boolean value) {
        this.__isset_bit_vector.set(0, value);
    }

    public String getTime() {
        return this.time;
    }

    public Common setTime(String time) {
        this.time = time;
        return this;
    }

    public void unsetTime() {
        this.time = null;
    }

    public boolean isSetTime() {
        return this.time != null;
    }

    public void setTimeIsSet(boolean value) {
        if (!value) {
            this.time = null;
        }
    }

    public String getClientIp() {
        return this.clientIp;
    }

    public Common setClientIp(String clientIp) {
        this.clientIp = clientIp;
        return this;
    }

    public void unsetClientIp() {
        this.clientIp = null;
    }

    public boolean isSetClientIp() {
        return this.clientIp != null;
    }

    public void setClientIpIsSet(boolean value) {
        if (!value) {
            this.clientIp = null;
        }
    }

    public String getServerIp() {
        return this.serverIp;
    }

    public Common setServerIp(String serverIp) {
        this.serverIp = serverIp;
        return this;
    }

    public void unsetServerIp() {
        this.serverIp = null;
    }

    public boolean isSetServerIp() {
        return this.serverIp != null;
    }

    public void setServerIpIsSet(boolean value) {
        if (!value) {
            this.serverIp = null;
        }
    }

    public String getServerHost() {
        return this.serverHost;
    }

    public Common setServerHost(String serverHost) {
        this.serverHost = serverHost;
        return this;
    }

    public void unsetServerHost() {
        this.serverHost = null;
    }

    public boolean isSetServerHost() {
        return this.serverHost != null;
    }

    public void setServerHostIsSet(boolean value) {
        if (!value) {
            this.serverHost = null;
        }
    }

    public void setFieldValue(_Fields field, Object value) {
        switch (AnonymousClass1.$SwitchMap$com$xiaomi$common$logger$thrift$Common$_Fields[field.ordinal()]) {
            case TTransportException.NOT_OPEN /*1*/:
                if (value == null) {
                    unsetUuid();
                    return;
                } else {
                    setUuid(((Long) value).longValue());
                    return;
                }
            case TTransportException.ALREADY_OPEN /*2*/:
                if (value == null) {
                    unsetTime();
                    return;
                } else {
                    setTime((String) value);
                    return;
                }
            case TTransportException.TIMED_OUT /*3*/:
                if (value == null) {
                    unsetClientIp();
                    return;
                } else {
                    setClientIp((String) value);
                    return;
                }
            case TTransportException.END_OF_FILE /*4*/:
                if (value == null) {
                    unsetServerIp();
                    return;
                } else {
                    setServerIp((String) value);
                    return;
                }
            case TProtocolException.NOT_IMPLEMENTED /*5*/:
                if (value == null) {
                    unsetServerHost();
                    return;
                } else {
                    setServerHost((String) value);
                    return;
                }
            default:
                return;
        }
    }

    public Object getFieldValue(_Fields field) {
        switch (AnonymousClass1.$SwitchMap$com$xiaomi$common$logger$thrift$Common$_Fields[field.ordinal()]) {
            case TTransportException.NOT_OPEN /*1*/:
                return new Long(getUuid());
            case TTransportException.ALREADY_OPEN /*2*/:
                return getTime();
            case TTransportException.TIMED_OUT /*3*/:
                return getClientIp();
            case TTransportException.END_OF_FILE /*4*/:
                return getServerIp();
            case TProtocolException.NOT_IMPLEMENTED /*5*/:
                return getServerHost();
            default:
                throw new IllegalStateException();
        }
    }

    public boolean isSet(_Fields field) {
        if (field == null) {
            throw new IllegalArgumentException();
        }
        switch (AnonymousClass1.$SwitchMap$com$xiaomi$common$logger$thrift$Common$_Fields[field.ordinal()]) {
            case TTransportException.NOT_OPEN /*1*/:
                return isSetUuid();
            case TTransportException.ALREADY_OPEN /*2*/:
                return isSetTime();
            case TTransportException.TIMED_OUT /*3*/:
                return isSetClientIp();
            case TTransportException.END_OF_FILE /*4*/:
                return isSetServerIp();
            case TProtocolException.NOT_IMPLEMENTED /*5*/:
                return isSetServerHost();
            default:
                throw new IllegalStateException();
        }
    }

    public boolean equals(Object that) {
        if (that != null && (that instanceof Common)) {
            return equals((Common) that);
        }
        return false;
    }

    public boolean equals(Common that) {
        if (that == null) {
            return false;
        }
        boolean this_present_uuid = isSetUuid();
        boolean that_present_uuid = that.isSetUuid();
        if (this_present_uuid || that_present_uuid) {
            if (!this_present_uuid || !that_present_uuid) {
                return false;
            }
            if (this.uuid != that.uuid) {
                return false;
            }
        }
        boolean this_present_time = isSetTime();
        boolean that_present_time = that.isSetTime();
        if (this_present_time || that_present_time) {
            if (!this_present_time || !that_present_time) {
                return false;
            }
            if (!this.time.equals(that.time)) {
                return false;
            }
        }
        boolean this_present_clientIp = isSetClientIp();
        boolean that_present_clientIp = that.isSetClientIp();
        if (this_present_clientIp || that_present_clientIp) {
            if (!this_present_clientIp || !that_present_clientIp) {
                return false;
            }
            if (!this.clientIp.equals(that.clientIp)) {
                return false;
            }
        }
        boolean this_present_serverIp = isSetServerIp();
        boolean that_present_serverIp = that.isSetServerIp();
        if (this_present_serverIp || that_present_serverIp) {
            if (!this_present_serverIp || !that_present_serverIp) {
                return false;
            }
            if (!this.serverIp.equals(that.serverIp)) {
                return false;
            }
        }
        boolean this_present_serverHost = isSetServerHost();
        boolean that_present_serverHost = that.isSetServerHost();
        if (this_present_serverHost || that_present_serverHost) {
            if (!this_present_serverHost || !that_present_serverHost) {
                return false;
            }
            if (!this.serverHost.equals(that.serverHost)) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        return 0;
    }

    public int compareTo(Common other) {
        if (!getClass().equals(other.getClass())) {
            return getClass().getName().compareTo(other.getClass().getName());
        }
        Common typedOther = other;
        int lastComparison = Boolean.valueOf(isSetUuid()).compareTo(Boolean.valueOf(typedOther.isSetUuid()));
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetUuid()) {
            lastComparison = TBaseHelper.compareTo(this.uuid, typedOther.uuid);
            if (lastComparison != 0) {
                return lastComparison;
            }
        }
        lastComparison = Boolean.valueOf(isSetTime()).compareTo(Boolean.valueOf(typedOther.isSetTime()));
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetTime()) {
            lastComparison = TBaseHelper.compareTo(this.time, typedOther.time);
            if (lastComparison != 0) {
                return lastComparison;
            }
        }
        lastComparison = Boolean.valueOf(isSetClientIp()).compareTo(Boolean.valueOf(typedOther.isSetClientIp()));
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetClientIp()) {
            lastComparison = TBaseHelper.compareTo(this.clientIp, typedOther.clientIp);
            if (lastComparison != 0) {
                return lastComparison;
            }
        }
        lastComparison = Boolean.valueOf(isSetServerIp()).compareTo(Boolean.valueOf(typedOther.isSetServerIp()));
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetServerIp()) {
            lastComparison = TBaseHelper.compareTo(this.serverIp, typedOther.serverIp);
            if (lastComparison != 0) {
                return lastComparison;
            }
        }
        lastComparison = Boolean.valueOf(isSetServerHost()).compareTo(Boolean.valueOf(typedOther.isSetServerHost()));
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetServerHost()) {
            lastComparison = TBaseHelper.compareTo(this.serverHost, typedOther.serverHost);
            if (lastComparison != 0) {
                return lastComparison;
            }
        }
        return 0;
    }

    public _Fields fieldForId(int fieldId) {
        return _Fields.findByThriftId(fieldId);
    }

    public void read(TProtocol iprot) throws TException {
        iprot.readStructBegin();
        while (true) {
            TField field = iprot.readFieldBegin();
            if (field.type == (byte) 0) {
                iprot.readStructEnd();
                validate();
                return;
            }
            switch (field.id) {
                case TTransportException.NOT_OPEN /*1*/:
                    if (field.type != (byte) 10) {
                        TProtocolUtil.skip(iprot, field.type);
                        break;
                    }
                    this.uuid = iprot.readI64();
                    setUuidIsSet(true);
                    break;
                case TTransportException.ALREADY_OPEN /*2*/:
                    if (field.type != TType.STRING) {
                        TProtocolUtil.skip(iprot, field.type);
                        break;
                    } else {
                        this.time = iprot.readString();
                        break;
                    }
                case TTransportException.TIMED_OUT /*3*/:
                    if (field.type != TType.STRING) {
                        TProtocolUtil.skip(iprot, field.type);
                        break;
                    } else {
                        this.clientIp = iprot.readString();
                        break;
                    }
                case TTransportException.END_OF_FILE /*4*/:
                    if (field.type != TType.STRING) {
                        TProtocolUtil.skip(iprot, field.type);
                        break;
                    } else {
                        this.serverIp = iprot.readString();
                        break;
                    }
                case TProtocolException.NOT_IMPLEMENTED /*5*/:
                    if (field.type != TType.STRING) {
                        TProtocolUtil.skip(iprot, field.type);
                        break;
                    } else {
                        this.serverHost = iprot.readString();
                        break;
                    }
                default:
                    TProtocolUtil.skip(iprot, field.type);
                    break;
            }
            iprot.readFieldEnd();
        }
    }

    public void write(TProtocol oprot) throws TException {
        validate();
        oprot.writeStructBegin(STRUCT_DESC);
        if (isSetUuid()) {
            oprot.writeFieldBegin(UUID_FIELD_DESC);
            oprot.writeI64(this.uuid);
            oprot.writeFieldEnd();
        }
        if (this.time != null && isSetTime()) {
            oprot.writeFieldBegin(TIME_FIELD_DESC);
            oprot.writeString(this.time);
            oprot.writeFieldEnd();
        }
        if (this.clientIp != null && isSetClientIp()) {
            oprot.writeFieldBegin(CLIENT_IP_FIELD_DESC);
            oprot.writeString(this.clientIp);
            oprot.writeFieldEnd();
        }
        if (this.serverIp != null && isSetServerIp()) {
            oprot.writeFieldBegin(SERVER_IP_FIELD_DESC);
            oprot.writeString(this.serverIp);
            oprot.writeFieldEnd();
        }
        if (this.serverHost != null && isSetServerHost()) {
            oprot.writeFieldBegin(SERVER_HOST_FIELD_DESC);
            oprot.writeString(this.serverHost);
            oprot.writeFieldEnd();
        }
        oprot.writeFieldStop();
        oprot.writeStructEnd();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Common(");
        boolean first = true;
        if (isSetUuid()) {
            sb.append("uuid:");
            sb.append(this.uuid);
            first = false;
        }
        if (isSetTime()) {
            if (!first) {
                sb.append(", ");
            }
            sb.append("time:");
            if (this.time == null) {
                sb.append("null");
            } else {
                sb.append(this.time);
            }
            first = false;
        }
        if (isSetClientIp()) {
            if (!first) {
                sb.append(", ");
            }
            sb.append("clientIp:");
            if (this.clientIp == null) {
                sb.append("null");
            } else {
                sb.append(this.clientIp);
            }
            first = false;
        }
        if (isSetServerIp()) {
            if (!first) {
                sb.append(", ");
            }
            sb.append("serverIp:");
            if (this.serverIp == null) {
                sb.append("null");
            } else {
                sb.append(this.serverIp);
            }
            first = false;
        }
        if (isSetServerHost()) {
            if (!first) {
                sb.append(", ");
            }
            sb.append("serverHost:");
            if (this.serverHost == null) {
                sb.append("null");
            } else {
                sb.append(this.serverHost);
            }
        }
        sb.append(")");
        return sb.toString();
    }

    public void validate() throws TException {
    }
}
