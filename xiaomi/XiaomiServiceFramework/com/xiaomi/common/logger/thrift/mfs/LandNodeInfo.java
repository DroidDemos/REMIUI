package com.xiaomi.common.logger.thrift.mfs;

import java.io.Serializable;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.thrift.TApplicationException;
import org.apache.thrift.TBase;
import org.apache.thrift.TBaseHelper;
import org.apache.thrift.TException;
import org.apache.thrift.TFieldIdEnum;
import org.apache.thrift.meta_data.FieldMetaData;
import org.apache.thrift.meta_data.FieldValueMetaData;
import org.apache.thrift.meta_data.MapMetaData;
import org.apache.thrift.protocol.TField;
import org.apache.thrift.protocol.TMap;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.protocol.TProtocolUtil;
import org.apache.thrift.protocol.TStruct;
import org.apache.thrift.protocol.TType;
import org.apache.thrift.transport.TTransportException;

public class LandNodeInfo implements TBase<LandNodeInfo, _Fields>, Serializable, Cloneable {
    private static final TField DURATION_FIELD_DESC;
    private static final TField EXP_INFO_FIELD_DESC;
    private static final TField FAILED_COUNT_FIELD_DESC;
    private static final TField HTTP_INFO_FIELD_DESC;
    private static final TField IP_FIELD_DESC;
    private static final TField SIZE_FIELD_DESC;
    private static final TStruct STRUCT_DESC;
    private static final TField SUCCESS_COUNT_FIELD_DESC;
    private static final int __DURATION_ISSET_ID = 2;
    private static final int __FAILED_COUNT_ISSET_ID = 0;
    private static final int __SIZE_ISSET_ID = 3;
    private static final int __SUCCESS_COUNT_ISSET_ID = 1;
    public static final Map<_Fields, FieldMetaData> metaDataMap;
    private BitSet __isset_bit_vector;
    private long duration;
    private Map<String, Integer> exp_info;
    private int failed_count;
    private Map<Integer, Integer> http_info;
    private String ip;
    private int size;
    private int success_count;

    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$xiaomi$common$logger$thrift$mfs$LandNodeInfo$_Fields;

        static {
            $SwitchMap$com$xiaomi$common$logger$thrift$mfs$LandNodeInfo$_Fields = new int[_Fields.values().length];
            try {
                $SwitchMap$com$xiaomi$common$logger$thrift$mfs$LandNodeInfo$_Fields[_Fields.IP.ordinal()] = LandNodeInfo.__SUCCESS_COUNT_ISSET_ID;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$xiaomi$common$logger$thrift$mfs$LandNodeInfo$_Fields[_Fields.FAILED_COUNT.ordinal()] = LandNodeInfo.__DURATION_ISSET_ID;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$xiaomi$common$logger$thrift$mfs$LandNodeInfo$_Fields[_Fields.SUCCESS_COUNT.ordinal()] = LandNodeInfo.__SIZE_ISSET_ID;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$xiaomi$common$logger$thrift$mfs$LandNodeInfo$_Fields[_Fields.DURATION.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$xiaomi$common$logger$thrift$mfs$LandNodeInfo$_Fields[_Fields.SIZE.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$xiaomi$common$logger$thrift$mfs$LandNodeInfo$_Fields[_Fields.EXP_INFO.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$com$xiaomi$common$logger$thrift$mfs$LandNodeInfo$_Fields[_Fields.HTTP_INFO.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
        }
    }

    public enum _Fields implements TFieldIdEnum {
        IP((short) 1, Constants.JSON_TAG_IP),
        FAILED_COUNT((short) 2, "failed_count"),
        SUCCESS_COUNT((short) 3, "success_count"),
        DURATION((short) 4, "duration"),
        SIZE((short) 5, "size"),
        EXP_INFO((short) 6, "exp_info"),
        HTTP_INFO((short) 7, "http_info");
        
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
                case LandNodeInfo.__SUCCESS_COUNT_ISSET_ID /*1*/:
                    return IP;
                case LandNodeInfo.__DURATION_ISSET_ID /*2*/:
                    return FAILED_COUNT;
                case LandNodeInfo.__SIZE_ISSET_ID /*3*/:
                    return SUCCESS_COUNT;
                case TTransportException.END_OF_FILE /*4*/:
                    return DURATION;
                case TProtocolException.NOT_IMPLEMENTED /*5*/:
                    return SIZE;
                case TApplicationException.INTERNAL_ERROR /*6*/:
                    return EXP_INFO;
                case TApplicationException.PROTOCOL_ERROR /*7*/:
                    return HTTP_INFO;
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
        STRUCT_DESC = new TStruct("LandNodeInfo");
        IP_FIELD_DESC = new TField(Constants.JSON_TAG_IP, TType.STRING, (short) 1);
        FAILED_COUNT_FIELD_DESC = new TField("failed_count", (byte) 8, (short) 2);
        SUCCESS_COUNT_FIELD_DESC = new TField("success_count", (byte) 8, (short) 3);
        DURATION_FIELD_DESC = new TField("duration", (byte) 10, (short) 4);
        SIZE_FIELD_DESC = new TField("size", (byte) 8, (short) 5);
        EXP_INFO_FIELD_DESC = new TField("exp_info", TType.MAP, (short) 6);
        HTTP_INFO_FIELD_DESC = new TField("http_info", TType.MAP, (short) 7);
        Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
        tmpMap.put(_Fields.IP, new FieldMetaData(Constants.JSON_TAG_IP, (byte) 1, new FieldValueMetaData(TType.STRING)));
        tmpMap.put(_Fields.FAILED_COUNT, new FieldMetaData("failed_count", (byte) 1, new FieldValueMetaData((byte) 8)));
        tmpMap.put(_Fields.SUCCESS_COUNT, new FieldMetaData("success_count", (byte) 1, new FieldValueMetaData((byte) 8)));
        tmpMap.put(_Fields.DURATION, new FieldMetaData("duration", (byte) 1, new FieldValueMetaData((byte) 10)));
        tmpMap.put(_Fields.SIZE, new FieldMetaData("size", (byte) 1, new FieldValueMetaData((byte) 8)));
        tmpMap.put(_Fields.EXP_INFO, new FieldMetaData("exp_info", (byte) 2, new MapMetaData(TType.MAP, new FieldValueMetaData(TType.STRING), new FieldValueMetaData((byte) 8))));
        tmpMap.put(_Fields.HTTP_INFO, new FieldMetaData("http_info", (byte) 2, new MapMetaData(TType.MAP, new FieldValueMetaData((byte) 8), new FieldValueMetaData((byte) 8))));
        metaDataMap = Collections.unmodifiableMap(tmpMap);
        FieldMetaData.addStructMetaDataMap(LandNodeInfo.class, metaDataMap);
    }

    public LandNodeInfo() {
        this.__isset_bit_vector = new BitSet(4);
    }

    public LandNodeInfo(String ip, int failed_count, int success_count, long duration, int size) {
        this();
        this.ip = ip;
        this.failed_count = failed_count;
        setFailed_countIsSet(true);
        this.success_count = success_count;
        setSuccess_countIsSet(true);
        this.duration = duration;
        setDurationIsSet(true);
        this.size = size;
        setSizeIsSet(true);
    }

    public LandNodeInfo(LandNodeInfo other) {
        this.__isset_bit_vector = new BitSet(4);
        this.__isset_bit_vector.clear();
        this.__isset_bit_vector.or(other.__isset_bit_vector);
        if (other.isSetIp()) {
            this.ip = other.ip;
        }
        this.failed_count = other.failed_count;
        this.success_count = other.success_count;
        this.duration = other.duration;
        this.size = other.size;
        if (other.isSetExp_info()) {
            Map<String, Integer> __this__exp_info = new HashMap();
            for (Entry<String, Integer> other_element : other.exp_info.entrySet()) {
                __this__exp_info.put((String) other_element.getKey(), (Integer) other_element.getValue());
            }
            this.exp_info = __this__exp_info;
        }
        if (other.isSetHttp_info()) {
            Map<Integer, Integer> __this__http_info = new HashMap();
            for (Entry<Integer, Integer> other_element2 : other.http_info.entrySet()) {
                __this__http_info.put((Integer) other_element2.getKey(), (Integer) other_element2.getValue());
            }
            this.http_info = __this__http_info;
        }
    }

    public LandNodeInfo deepCopy() {
        return new LandNodeInfo(this);
    }

    public void clear() {
        this.ip = null;
        setFailed_countIsSet(false);
        this.failed_count = __FAILED_COUNT_ISSET_ID;
        setSuccess_countIsSet(false);
        this.success_count = __FAILED_COUNT_ISSET_ID;
        setDurationIsSet(false);
        this.duration = 0;
        setSizeIsSet(false);
        this.size = __FAILED_COUNT_ISSET_ID;
        this.exp_info = null;
        this.http_info = null;
    }

    public String getIp() {
        return this.ip;
    }

    public LandNodeInfo setIp(String ip) {
        this.ip = ip;
        return this;
    }

    public void unsetIp() {
        this.ip = null;
    }

    public boolean isSetIp() {
        return this.ip != null;
    }

    public void setIpIsSet(boolean value) {
        if (!value) {
            this.ip = null;
        }
    }

    public int getFailed_count() {
        return this.failed_count;
    }

    public LandNodeInfo setFailed_count(int failed_count) {
        this.failed_count = failed_count;
        setFailed_countIsSet(true);
        return this;
    }

    public void unsetFailed_count() {
        this.__isset_bit_vector.clear(__FAILED_COUNT_ISSET_ID);
    }

    public boolean isSetFailed_count() {
        return this.__isset_bit_vector.get(__FAILED_COUNT_ISSET_ID);
    }

    public void setFailed_countIsSet(boolean value) {
        this.__isset_bit_vector.set(__FAILED_COUNT_ISSET_ID, value);
    }

    public int getSuccess_count() {
        return this.success_count;
    }

    public LandNodeInfo setSuccess_count(int success_count) {
        this.success_count = success_count;
        setSuccess_countIsSet(true);
        return this;
    }

    public void unsetSuccess_count() {
        this.__isset_bit_vector.clear(__SUCCESS_COUNT_ISSET_ID);
    }

    public boolean isSetSuccess_count() {
        return this.__isset_bit_vector.get(__SUCCESS_COUNT_ISSET_ID);
    }

    public void setSuccess_countIsSet(boolean value) {
        this.__isset_bit_vector.set(__SUCCESS_COUNT_ISSET_ID, value);
    }

    public long getDuration() {
        return this.duration;
    }

    public LandNodeInfo setDuration(long duration) {
        this.duration = duration;
        setDurationIsSet(true);
        return this;
    }

    public void unsetDuration() {
        this.__isset_bit_vector.clear(__DURATION_ISSET_ID);
    }

    public boolean isSetDuration() {
        return this.__isset_bit_vector.get(__DURATION_ISSET_ID);
    }

    public void setDurationIsSet(boolean value) {
        this.__isset_bit_vector.set(__DURATION_ISSET_ID, value);
    }

    public int getSize() {
        return this.size;
    }

    public LandNodeInfo setSize(int size) {
        this.size = size;
        setSizeIsSet(true);
        return this;
    }

    public void unsetSize() {
        this.__isset_bit_vector.clear(__SIZE_ISSET_ID);
    }

    public boolean isSetSize() {
        return this.__isset_bit_vector.get(__SIZE_ISSET_ID);
    }

    public void setSizeIsSet(boolean value) {
        this.__isset_bit_vector.set(__SIZE_ISSET_ID, value);
    }

    public int getExp_infoSize() {
        return this.exp_info == null ? __FAILED_COUNT_ISSET_ID : this.exp_info.size();
    }

    public void putToExp_info(String key, int val) {
        if (this.exp_info == null) {
            this.exp_info = new HashMap();
        }
        this.exp_info.put(key, Integer.valueOf(val));
    }

    public Map<String, Integer> getExp_info() {
        return this.exp_info;
    }

    public LandNodeInfo setExp_info(Map<String, Integer> exp_info) {
        this.exp_info = exp_info;
        return this;
    }

    public void unsetExp_info() {
        this.exp_info = null;
    }

    public boolean isSetExp_info() {
        return this.exp_info != null;
    }

    public void setExp_infoIsSet(boolean value) {
        if (!value) {
            this.exp_info = null;
        }
    }

    public int getHttp_infoSize() {
        return this.http_info == null ? __FAILED_COUNT_ISSET_ID : this.http_info.size();
    }

    public void putToHttp_info(int key, int val) {
        if (this.http_info == null) {
            this.http_info = new HashMap();
        }
        this.http_info.put(Integer.valueOf(key), Integer.valueOf(val));
    }

    public Map<Integer, Integer> getHttp_info() {
        return this.http_info;
    }

    public LandNodeInfo setHttp_info(Map<Integer, Integer> http_info) {
        this.http_info = http_info;
        return this;
    }

    public void unsetHttp_info() {
        this.http_info = null;
    }

    public boolean isSetHttp_info() {
        return this.http_info != null;
    }

    public void setHttp_infoIsSet(boolean value) {
        if (!value) {
            this.http_info = null;
        }
    }

    public void setFieldValue(_Fields field, Object value) {
        switch (AnonymousClass1.$SwitchMap$com$xiaomi$common$logger$thrift$mfs$LandNodeInfo$_Fields[field.ordinal()]) {
            case __SUCCESS_COUNT_ISSET_ID /*1*/:
                if (value == null) {
                    unsetIp();
                    return;
                } else {
                    setIp((String) value);
                    return;
                }
            case __DURATION_ISSET_ID /*2*/:
                if (value == null) {
                    unsetFailed_count();
                    return;
                } else {
                    setFailed_count(((Integer) value).intValue());
                    return;
                }
            case __SIZE_ISSET_ID /*3*/:
                if (value == null) {
                    unsetSuccess_count();
                    return;
                } else {
                    setSuccess_count(((Integer) value).intValue());
                    return;
                }
            case TTransportException.END_OF_FILE /*4*/:
                if (value == null) {
                    unsetDuration();
                    return;
                } else {
                    setDuration(((Long) value).longValue());
                    return;
                }
            case TProtocolException.NOT_IMPLEMENTED /*5*/:
                if (value == null) {
                    unsetSize();
                    return;
                } else {
                    setSize(((Integer) value).intValue());
                    return;
                }
            case TApplicationException.INTERNAL_ERROR /*6*/:
                if (value == null) {
                    unsetExp_info();
                    return;
                } else {
                    setExp_info((Map) value);
                    return;
                }
            case TApplicationException.PROTOCOL_ERROR /*7*/:
                if (value == null) {
                    unsetHttp_info();
                    return;
                } else {
                    setHttp_info((Map) value);
                    return;
                }
            default:
                return;
        }
    }

    public Object getFieldValue(_Fields field) {
        switch (AnonymousClass1.$SwitchMap$com$xiaomi$common$logger$thrift$mfs$LandNodeInfo$_Fields[field.ordinal()]) {
            case __SUCCESS_COUNT_ISSET_ID /*1*/:
                return getIp();
            case __DURATION_ISSET_ID /*2*/:
                return new Integer(getFailed_count());
            case __SIZE_ISSET_ID /*3*/:
                return new Integer(getSuccess_count());
            case TTransportException.END_OF_FILE /*4*/:
                return new Long(getDuration());
            case TProtocolException.NOT_IMPLEMENTED /*5*/:
                return new Integer(getSize());
            case TApplicationException.INTERNAL_ERROR /*6*/:
                return getExp_info();
            case TApplicationException.PROTOCOL_ERROR /*7*/:
                return getHttp_info();
            default:
                throw new IllegalStateException();
        }
    }

    public boolean isSet(_Fields field) {
        if (field == null) {
            throw new IllegalArgumentException();
        }
        switch (AnonymousClass1.$SwitchMap$com$xiaomi$common$logger$thrift$mfs$LandNodeInfo$_Fields[field.ordinal()]) {
            case __SUCCESS_COUNT_ISSET_ID /*1*/:
                return isSetIp();
            case __DURATION_ISSET_ID /*2*/:
                return isSetFailed_count();
            case __SIZE_ISSET_ID /*3*/:
                return isSetSuccess_count();
            case TTransportException.END_OF_FILE /*4*/:
                return isSetDuration();
            case TProtocolException.NOT_IMPLEMENTED /*5*/:
                return isSetSize();
            case TApplicationException.INTERNAL_ERROR /*6*/:
                return isSetExp_info();
            case TApplicationException.PROTOCOL_ERROR /*7*/:
                return isSetHttp_info();
            default:
                throw new IllegalStateException();
        }
    }

    public boolean equals(Object that) {
        if (that != null && (that instanceof LandNodeInfo)) {
            return equals((LandNodeInfo) that);
        }
        return false;
    }

    public boolean equals(LandNodeInfo that) {
        if (that == null) {
            return false;
        }
        boolean this_present_ip = isSetIp();
        boolean that_present_ip = that.isSetIp();
        if (this_present_ip || that_present_ip) {
            if (!this_present_ip || !that_present_ip) {
                return false;
            }
            if (!this.ip.equals(that.ip)) {
                return false;
            }
        }
        if (!(__SUCCESS_COUNT_ISSET_ID == null && __SUCCESS_COUNT_ISSET_ID == null)) {
            if (__SUCCESS_COUNT_ISSET_ID == null || __SUCCESS_COUNT_ISSET_ID == null) {
                return false;
            }
            if (this.failed_count != that.failed_count) {
                return false;
            }
        }
        if (!(__SUCCESS_COUNT_ISSET_ID == null && __SUCCESS_COUNT_ISSET_ID == null)) {
            if (__SUCCESS_COUNT_ISSET_ID == null || __SUCCESS_COUNT_ISSET_ID == null) {
                return false;
            }
            if (this.success_count != that.success_count) {
                return false;
            }
        }
        if (!(__SUCCESS_COUNT_ISSET_ID == null && __SUCCESS_COUNT_ISSET_ID == null)) {
            if (__SUCCESS_COUNT_ISSET_ID == null || __SUCCESS_COUNT_ISSET_ID == null) {
                return false;
            }
            if (this.duration != that.duration) {
                return false;
            }
        }
        if (!(__SUCCESS_COUNT_ISSET_ID == null && __SUCCESS_COUNT_ISSET_ID == null)) {
            if (__SUCCESS_COUNT_ISSET_ID == null || __SUCCESS_COUNT_ISSET_ID == null) {
                return false;
            }
            if (this.size != that.size) {
                return false;
            }
        }
        boolean this_present_exp_info = isSetExp_info();
        boolean that_present_exp_info = that.isSetExp_info();
        if (this_present_exp_info || that_present_exp_info) {
            if (!this_present_exp_info || !that_present_exp_info) {
                return false;
            }
            if (!this.exp_info.equals(that.exp_info)) {
                return false;
            }
        }
        boolean this_present_http_info = isSetHttp_info();
        boolean that_present_http_info = that.isSetHttp_info();
        if (this_present_http_info || that_present_http_info) {
            if (!this_present_http_info || !that_present_http_info) {
                return false;
            }
            if (!this.http_info.equals(that.http_info)) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        return __FAILED_COUNT_ISSET_ID;
    }

    public int compareTo(LandNodeInfo other) {
        if (!getClass().equals(other.getClass())) {
            return getClass().getName().compareTo(other.getClass().getName());
        }
        LandNodeInfo typedOther = other;
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
        lastComparison = Boolean.valueOf(isSetFailed_count()).compareTo(Boolean.valueOf(typedOther.isSetFailed_count()));
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetFailed_count()) {
            lastComparison = TBaseHelper.compareTo(this.failed_count, typedOther.failed_count);
            if (lastComparison != 0) {
                return lastComparison;
            }
        }
        lastComparison = Boolean.valueOf(isSetSuccess_count()).compareTo(Boolean.valueOf(typedOther.isSetSuccess_count()));
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetSuccess_count()) {
            lastComparison = TBaseHelper.compareTo(this.success_count, typedOther.success_count);
            if (lastComparison != 0) {
                return lastComparison;
            }
        }
        lastComparison = Boolean.valueOf(isSetDuration()).compareTo(Boolean.valueOf(typedOther.isSetDuration()));
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetDuration()) {
            lastComparison = TBaseHelper.compareTo(this.duration, typedOther.duration);
            if (lastComparison != 0) {
                return lastComparison;
            }
        }
        lastComparison = Boolean.valueOf(isSetSize()).compareTo(Boolean.valueOf(typedOther.isSetSize()));
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetSize()) {
            lastComparison = TBaseHelper.compareTo(this.size, typedOther.size);
            if (lastComparison != 0) {
                return lastComparison;
            }
        }
        lastComparison = Boolean.valueOf(isSetExp_info()).compareTo(Boolean.valueOf(typedOther.isSetExp_info()));
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetExp_info()) {
            lastComparison = TBaseHelper.compareTo(this.exp_info, typedOther.exp_info);
            if (lastComparison != 0) {
                return lastComparison;
            }
        }
        lastComparison = Boolean.valueOf(isSetHttp_info()).compareTo(Boolean.valueOf(typedOther.isSetHttp_info()));
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetHttp_info()) {
            lastComparison = TBaseHelper.compareTo(this.http_info, typedOther.http_info);
            if (lastComparison != 0) {
                return lastComparison;
            }
        }
        return __FAILED_COUNT_ISSET_ID;
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
                if (!isSetFailed_count()) {
                    throw new TProtocolException("Required field 'failed_count' was not found in serialized data! Struct: " + toString());
                } else if (!isSetSuccess_count()) {
                    throw new TProtocolException("Required field 'success_count' was not found in serialized data! Struct: " + toString());
                } else if (!isSetDuration()) {
                    throw new TProtocolException("Required field 'duration' was not found in serialized data! Struct: " + toString());
                } else if (isSetSize()) {
                    validate();
                    return;
                } else {
                    throw new TProtocolException("Required field 'size' was not found in serialized data! Struct: " + toString());
                }
            }
            switch (field.id) {
                case __SUCCESS_COUNT_ISSET_ID /*1*/:
                    if (field.type != TType.STRING) {
                        TProtocolUtil.skip(iprot, field.type);
                        break;
                    } else {
                        this.ip = iprot.readString();
                        break;
                    }
                case __DURATION_ISSET_ID /*2*/:
                    if (field.type != (byte) 8) {
                        TProtocolUtil.skip(iprot, field.type);
                        break;
                    }
                    this.failed_count = iprot.readI32();
                    setFailed_countIsSet(true);
                    break;
                case __SIZE_ISSET_ID /*3*/:
                    if (field.type != (byte) 8) {
                        TProtocolUtil.skip(iprot, field.type);
                        break;
                    }
                    this.success_count = iprot.readI32();
                    setSuccess_countIsSet(true);
                    break;
                case TTransportException.END_OF_FILE /*4*/:
                    if (field.type != (byte) 10) {
                        TProtocolUtil.skip(iprot, field.type);
                        break;
                    }
                    this.duration = iprot.readI64();
                    setDurationIsSet(true);
                    break;
                case TProtocolException.NOT_IMPLEMENTED /*5*/:
                    if (field.type != (byte) 8) {
                        TProtocolUtil.skip(iprot, field.type);
                        break;
                    }
                    this.size = iprot.readI32();
                    setSizeIsSet(true);
                    break;
                case TApplicationException.INTERNAL_ERROR /*6*/:
                    if (field.type != TType.MAP) {
                        TProtocolUtil.skip(iprot, field.type);
                        break;
                    }
                    TMap _map0 = iprot.readMapBegin();
                    this.exp_info = new HashMap(_map0.size * __DURATION_ISSET_ID);
                    for (int _i1 = __FAILED_COUNT_ISSET_ID; _i1 < _map0.size; _i1 += __SUCCESS_COUNT_ISSET_ID) {
                        this.exp_info.put(iprot.readString(), Integer.valueOf(iprot.readI32()));
                    }
                    iprot.readMapEnd();
                    break;
                case TApplicationException.PROTOCOL_ERROR /*7*/:
                    if (field.type != TType.MAP) {
                        TProtocolUtil.skip(iprot, field.type);
                        break;
                    }
                    TMap _map4 = iprot.readMapBegin();
                    this.http_info = new HashMap(_map4.size * __DURATION_ISSET_ID);
                    for (int _i5 = __FAILED_COUNT_ISSET_ID; _i5 < _map4.size; _i5 += __SUCCESS_COUNT_ISSET_ID) {
                        this.http_info.put(Integer.valueOf(iprot.readI32()), Integer.valueOf(iprot.readI32()));
                    }
                    iprot.readMapEnd();
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
        if (this.ip != null) {
            oprot.writeFieldBegin(IP_FIELD_DESC);
            oprot.writeString(this.ip);
            oprot.writeFieldEnd();
        }
        oprot.writeFieldBegin(FAILED_COUNT_FIELD_DESC);
        oprot.writeI32(this.failed_count);
        oprot.writeFieldEnd();
        oprot.writeFieldBegin(SUCCESS_COUNT_FIELD_DESC);
        oprot.writeI32(this.success_count);
        oprot.writeFieldEnd();
        oprot.writeFieldBegin(DURATION_FIELD_DESC);
        oprot.writeI64(this.duration);
        oprot.writeFieldEnd();
        oprot.writeFieldBegin(SIZE_FIELD_DESC);
        oprot.writeI32(this.size);
        oprot.writeFieldEnd();
        if (this.exp_info != null && isSetExp_info()) {
            oprot.writeFieldBegin(EXP_INFO_FIELD_DESC);
            oprot.writeMapBegin(new TMap(TType.STRING, (byte) 8, this.exp_info.size()));
            for (Entry<String, Integer> _iter8 : this.exp_info.entrySet()) {
                oprot.writeString((String) _iter8.getKey());
                oprot.writeI32(((Integer) _iter8.getValue()).intValue());
            }
            oprot.writeMapEnd();
            oprot.writeFieldEnd();
        }
        if (this.http_info != null && isSetHttp_info()) {
            oprot.writeFieldBegin(HTTP_INFO_FIELD_DESC);
            oprot.writeMapBegin(new TMap((byte) 8, (byte) 8, this.http_info.size()));
            for (Entry<Integer, Integer> _iter9 : this.http_info.entrySet()) {
                oprot.writeI32(((Integer) _iter9.getKey()).intValue());
                oprot.writeI32(((Integer) _iter9.getValue()).intValue());
            }
            oprot.writeMapEnd();
            oprot.writeFieldEnd();
        }
        oprot.writeFieldStop();
        oprot.writeStructEnd();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("LandNodeInfo(");
        sb.append("ip:");
        if (this.ip == null) {
            sb.append("null");
        } else {
            sb.append(this.ip);
        }
        if (!false) {
            sb.append(", ");
        }
        sb.append("failed_count:");
        sb.append(this.failed_count);
        if (!false) {
            sb.append(", ");
        }
        sb.append("success_count:");
        sb.append(this.success_count);
        if (!false) {
            sb.append(", ");
        }
        sb.append("duration:");
        sb.append(this.duration);
        if (!false) {
            sb.append(", ");
        }
        sb.append("size:");
        sb.append(this.size);
        boolean first = false;
        if (isSetExp_info()) {
            if (__FAILED_COUNT_ISSET_ID == null) {
                sb.append(", ");
            }
            sb.append("exp_info:");
            if (this.exp_info == null) {
                sb.append("null");
            } else {
                sb.append(this.exp_info);
            }
            first = false;
        }
        if (isSetHttp_info()) {
            if (!first) {
                sb.append(", ");
            }
            sb.append("http_info:");
            if (this.http_info == null) {
                sb.append("null");
            } else {
                sb.append(this.http_info);
            }
        }
        sb.append(")");
        return sb.toString();
    }

    public void validate() throws TException {
        if (this.ip == null) {
            throw new TProtocolException("Required field 'ip' was not present! Struct: " + toString());
        }
    }
}
