package com.xiaomi.common.logger.thrift.mfs;

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
import org.apache.thrift.transport.TTransportException;

public class PassportLandNodeInfo implements TBase<PassportLandNodeInfo, _Fields>, Serializable, Cloneable {
    private static final TField EID_FIELD_DESC;
    private static final TField IP_FIELD_DESC;
    private static final TField RT_FIELD_DESC;
    private static final TStruct STRUCT_DESC;
    private static final int __EID_ISSET_ID = 1;
    private static final int __IP_ISSET_ID = 0;
    private static final int __RT_ISSET_ID = 2;
    public static final Map<_Fields, FieldMetaData> metaDataMap;
    private BitSet __isset_bit_vector;
    private int eid;
    private int ip;
    private int rt;

    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$xiaomi$common$logger$thrift$mfs$PassportLandNodeInfo$_Fields;

        static {
            $SwitchMap$com$xiaomi$common$logger$thrift$mfs$PassportLandNodeInfo$_Fields = new int[_Fields.values().length];
            try {
                $SwitchMap$com$xiaomi$common$logger$thrift$mfs$PassportLandNodeInfo$_Fields[_Fields.IP.ordinal()] = PassportLandNodeInfo.__EID_ISSET_ID;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$xiaomi$common$logger$thrift$mfs$PassportLandNodeInfo$_Fields[_Fields.EID.ordinal()] = PassportLandNodeInfo.__RT_ISSET_ID;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$xiaomi$common$logger$thrift$mfs$PassportLandNodeInfo$_Fields[_Fields.RT.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    public enum _Fields implements TFieldIdEnum {
        IP((short) 1, Constants.JSON_TAG_IP),
        EID((short) 2, "eid"),
        RT((short) 3, "rt");
        
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
                case PassportLandNodeInfo.__EID_ISSET_ID /*1*/:
                    return IP;
                case PassportLandNodeInfo.__RT_ISSET_ID /*2*/:
                    return EID;
                case TTransportException.TIMED_OUT /*3*/:
                    return RT;
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
        STRUCT_DESC = new TStruct("PassportLandNodeInfo");
        IP_FIELD_DESC = new TField(Constants.JSON_TAG_IP, (byte) 8, (short) 1);
        EID_FIELD_DESC = new TField("eid", (byte) 8, (short) 2);
        RT_FIELD_DESC = new TField("rt", (byte) 8, (short) 3);
        Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
        tmpMap.put(_Fields.IP, new FieldMetaData(Constants.JSON_TAG_IP, (byte) 1, new FieldValueMetaData((byte) 8)));
        tmpMap.put(_Fields.EID, new FieldMetaData("eid", (byte) 1, new FieldValueMetaData((byte) 8)));
        tmpMap.put(_Fields.RT, new FieldMetaData("rt", (byte) 1, new FieldValueMetaData((byte) 8)));
        metaDataMap = Collections.unmodifiableMap(tmpMap);
        FieldMetaData.addStructMetaDataMap(PassportLandNodeInfo.class, metaDataMap);
    }

    public PassportLandNodeInfo() {
        this.__isset_bit_vector = new BitSet(3);
    }

    public PassportLandNodeInfo(int ip, int eid, int rt) {
        this();
        this.ip = ip;
        setIpIsSet(true);
        this.eid = eid;
        setEidIsSet(true);
        this.rt = rt;
        setRtIsSet(true);
    }

    public PassportLandNodeInfo(PassportLandNodeInfo other) {
        this.__isset_bit_vector = new BitSet(3);
        this.__isset_bit_vector.clear();
        this.__isset_bit_vector.or(other.__isset_bit_vector);
        this.ip = other.ip;
        this.eid = other.eid;
        this.rt = other.rt;
    }

    public PassportLandNodeInfo deepCopy() {
        return new PassportLandNodeInfo(this);
    }

    public void clear() {
        setIpIsSet(false);
        this.ip = __IP_ISSET_ID;
        setEidIsSet(false);
        this.eid = __IP_ISSET_ID;
        setRtIsSet(false);
        this.rt = __IP_ISSET_ID;
    }

    public int getIp() {
        return this.ip;
    }

    public PassportLandNodeInfo setIp(int ip) {
        this.ip = ip;
        setIpIsSet(true);
        return this;
    }

    public void unsetIp() {
        this.__isset_bit_vector.clear(__IP_ISSET_ID);
    }

    public boolean isSetIp() {
        return this.__isset_bit_vector.get(__IP_ISSET_ID);
    }

    public void setIpIsSet(boolean value) {
        this.__isset_bit_vector.set(__IP_ISSET_ID, value);
    }

    public int getEid() {
        return this.eid;
    }

    public PassportLandNodeInfo setEid(int eid) {
        this.eid = eid;
        setEidIsSet(true);
        return this;
    }

    public void unsetEid() {
        this.__isset_bit_vector.clear(__EID_ISSET_ID);
    }

    public boolean isSetEid() {
        return this.__isset_bit_vector.get(__EID_ISSET_ID);
    }

    public void setEidIsSet(boolean value) {
        this.__isset_bit_vector.set(__EID_ISSET_ID, value);
    }

    public int getRt() {
        return this.rt;
    }

    public PassportLandNodeInfo setRt(int rt) {
        this.rt = rt;
        setRtIsSet(true);
        return this;
    }

    public void unsetRt() {
        this.__isset_bit_vector.clear(__RT_ISSET_ID);
    }

    public boolean isSetRt() {
        return this.__isset_bit_vector.get(__RT_ISSET_ID);
    }

    public void setRtIsSet(boolean value) {
        this.__isset_bit_vector.set(__RT_ISSET_ID, value);
    }

    public void setFieldValue(_Fields field, Object value) {
        switch (AnonymousClass1.$SwitchMap$com$xiaomi$common$logger$thrift$mfs$PassportLandNodeInfo$_Fields[field.ordinal()]) {
            case __EID_ISSET_ID /*1*/:
                if (value == null) {
                    unsetIp();
                    return;
                } else {
                    setIp(((Integer) value).intValue());
                    return;
                }
            case __RT_ISSET_ID /*2*/:
                if (value == null) {
                    unsetEid();
                    return;
                } else {
                    setEid(((Integer) value).intValue());
                    return;
                }
            case TTransportException.TIMED_OUT /*3*/:
                if (value == null) {
                    unsetRt();
                    return;
                } else {
                    setRt(((Integer) value).intValue());
                    return;
                }
            default:
                return;
        }
    }

    public Object getFieldValue(_Fields field) {
        switch (AnonymousClass1.$SwitchMap$com$xiaomi$common$logger$thrift$mfs$PassportLandNodeInfo$_Fields[field.ordinal()]) {
            case __EID_ISSET_ID /*1*/:
                return new Integer(getIp());
            case __RT_ISSET_ID /*2*/:
                return new Integer(getEid());
            case TTransportException.TIMED_OUT /*3*/:
                return new Integer(getRt());
            default:
                throw new IllegalStateException();
        }
    }

    public boolean isSet(_Fields field) {
        if (field == null) {
            throw new IllegalArgumentException();
        }
        switch (AnonymousClass1.$SwitchMap$com$xiaomi$common$logger$thrift$mfs$PassportLandNodeInfo$_Fields[field.ordinal()]) {
            case __EID_ISSET_ID /*1*/:
                return isSetIp();
            case __RT_ISSET_ID /*2*/:
                return isSetEid();
            case TTransportException.TIMED_OUT /*3*/:
                return isSetRt();
            default:
                throw new IllegalStateException();
        }
    }

    public boolean equals(Object that) {
        if (that != null && (that instanceof PassportLandNodeInfo)) {
            return equals((PassportLandNodeInfo) that);
        }
        return false;
    }

    public boolean equals(PassportLandNodeInfo that) {
        if (that == null) {
            return false;
        }
        if ((__EID_ISSET_ID != null || __EID_ISSET_ID != null) && (__EID_ISSET_ID == null || __EID_ISSET_ID == null || this.ip != that.ip)) {
            return false;
        }
        if ((__EID_ISSET_ID != null || __EID_ISSET_ID != null) && (__EID_ISSET_ID == null || __EID_ISSET_ID == null || this.eid != that.eid)) {
            return false;
        }
        if ((__EID_ISSET_ID != null || __EID_ISSET_ID != null) && (__EID_ISSET_ID == null || __EID_ISSET_ID == null || this.rt != that.rt)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return __IP_ISSET_ID;
    }

    public int compareTo(PassportLandNodeInfo other) {
        if (!getClass().equals(other.getClass())) {
            return getClass().getName().compareTo(other.getClass().getName());
        }
        PassportLandNodeInfo typedOther = other;
        int lastComparison = Boolean.valueOf(isSetIp()).compareTo(Boolean.valueOf(typedOther.isSetIp()));
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetIp()) {
            lastComparison = TBaseHelper.compareTo(this.ip, typedOther.ip);
            if (lastComparison != 0) {
                return lastComparison;
            }
        }
        lastComparison = Boolean.valueOf(isSetEid()).compareTo(Boolean.valueOf(typedOther.isSetEid()));
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetEid()) {
            lastComparison = TBaseHelper.compareTo(this.eid, typedOther.eid);
            if (lastComparison != 0) {
                return lastComparison;
            }
        }
        lastComparison = Boolean.valueOf(isSetRt()).compareTo(Boolean.valueOf(typedOther.isSetRt()));
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetRt()) {
            lastComparison = TBaseHelper.compareTo(this.rt, typedOther.rt);
            if (lastComparison != 0) {
                return lastComparison;
            }
        }
        return __IP_ISSET_ID;
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
                if (!isSetIp()) {
                    throw new TProtocolException("Required field 'ip' was not found in serialized data! Struct: " + toString());
                } else if (!isSetEid()) {
                    throw new TProtocolException("Required field 'eid' was not found in serialized data! Struct: " + toString());
                } else if (isSetRt()) {
                    validate();
                    return;
                } else {
                    throw new TProtocolException("Required field 'rt' was not found in serialized data! Struct: " + toString());
                }
            }
            switch (field.id) {
                case __EID_ISSET_ID /*1*/:
                    if (field.type != (byte) 8) {
                        TProtocolUtil.skip(iprot, field.type);
                        break;
                    }
                    this.ip = iprot.readI32();
                    setIpIsSet(true);
                    break;
                case __RT_ISSET_ID /*2*/:
                    if (field.type != (byte) 8) {
                        TProtocolUtil.skip(iprot, field.type);
                        break;
                    }
                    this.eid = iprot.readI32();
                    setEidIsSet(true);
                    break;
                case TTransportException.TIMED_OUT /*3*/:
                    if (field.type != (byte) 8) {
                        TProtocolUtil.skip(iprot, field.type);
                        break;
                    }
                    this.rt = iprot.readI32();
                    setRtIsSet(true);
                    break;
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
        oprot.writeFieldBegin(IP_FIELD_DESC);
        oprot.writeI32(this.ip);
        oprot.writeFieldEnd();
        oprot.writeFieldBegin(EID_FIELD_DESC);
        oprot.writeI32(this.eid);
        oprot.writeFieldEnd();
        oprot.writeFieldBegin(RT_FIELD_DESC);
        oprot.writeI32(this.rt);
        oprot.writeFieldEnd();
        oprot.writeFieldStop();
        oprot.writeStructEnd();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("PassportLandNodeInfo(");
        sb.append("ip:");
        sb.append(this.ip);
        if (!false) {
            sb.append(", ");
        }
        sb.append("eid:");
        sb.append(this.eid);
        if (!false) {
            sb.append(", ");
        }
        sb.append("rt:");
        sb.append(this.rt);
        sb.append(")");
        return sb.toString();
    }

    public void validate() throws TException {
    }
}
