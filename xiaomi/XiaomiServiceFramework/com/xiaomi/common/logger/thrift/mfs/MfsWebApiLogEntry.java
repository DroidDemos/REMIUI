package com.xiaomi.common.logger.thrift.mfs;

import com.xiaomi.common.logger.WithCommon;
import com.xiaomi.common.logger.thrift.Common;
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
import org.apache.thrift.meta_data.StructMetaData;
import org.apache.thrift.protocol.TField;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.protocol.TProtocolUtil;
import org.apache.thrift.protocol.TStruct;
import org.apache.thrift.protocol.TType;
import org.apache.thrift.transport.TTransportException;

public class MfsWebApiLogEntry implements WithCommon, TBase<MfsWebApiLogEntry, _Fields>, Serializable, Cloneable {
    private static final TField COMMON_FIELD_DESC;
    private static final TField DIGEST_FIELD_DESC;
    private static final TField FILE_SIZE_FIELD_DESC;
    private static final TField PERMENANT_FIELD_DESC;
    private static final TStruct STRUCT_DESC;
    private static final TField TO_ID_FIELD_DESC;
    private static final int __FILESIZE_ISSET_ID = 0;
    private static final int __PERMENANT_ISSET_ID = 1;
    public static final Map<_Fields, FieldMetaData> metaDataMap;
    private BitSet __isset_bit_vector;
    public Common common;
    public String digest;
    public long fileSize;
    public boolean permenant;
    public String toId;

    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$xiaomi$common$logger$thrift$mfs$MfsWebApiLogEntry$_Fields;

        static {
            $SwitchMap$com$xiaomi$common$logger$thrift$mfs$MfsWebApiLogEntry$_Fields = new int[_Fields.values().length];
            try {
                $SwitchMap$com$xiaomi$common$logger$thrift$mfs$MfsWebApiLogEntry$_Fields[_Fields.COMMON.ordinal()] = MfsWebApiLogEntry.__PERMENANT_ISSET_ID;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$xiaomi$common$logger$thrift$mfs$MfsWebApiLogEntry$_Fields[_Fields.TO_ID.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$xiaomi$common$logger$thrift$mfs$MfsWebApiLogEntry$_Fields[_Fields.DIGEST.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$xiaomi$common$logger$thrift$mfs$MfsWebApiLogEntry$_Fields[_Fields.FILE_SIZE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$xiaomi$common$logger$thrift$mfs$MfsWebApiLogEntry$_Fields[_Fields.PERMENANT.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
        }
    }

    public enum _Fields implements TFieldIdEnum {
        COMMON((short) 1, "common"),
        TO_ID((short) 2, "toId"),
        DIGEST((short) 3, "digest"),
        FILE_SIZE((short) 4, "fileSize"),
        PERMENANT((short) 5, "permenant");
        
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
                case MfsWebApiLogEntry.__PERMENANT_ISSET_ID /*1*/:
                    return COMMON;
                case TTransportException.ALREADY_OPEN /*2*/:
                    return TO_ID;
                case TTransportException.TIMED_OUT /*3*/:
                    return DIGEST;
                case TTransportException.END_OF_FILE /*4*/:
                    return FILE_SIZE;
                case TProtocolException.NOT_IMPLEMENTED /*5*/:
                    return PERMENANT;
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
        STRUCT_DESC = new TStruct("MfsWebApiLogEntry");
        COMMON_FIELD_DESC = new TField("common", TType.STRUCT, (short) 1);
        TO_ID_FIELD_DESC = new TField("toId", TType.STRING, (short) 2);
        DIGEST_FIELD_DESC = new TField("digest", TType.STRING, (short) 3);
        FILE_SIZE_FIELD_DESC = new TField("fileSize", (byte) 10, (short) 4);
        PERMENANT_FIELD_DESC = new TField("permenant", (byte) 2, (short) 5);
        Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
        tmpMap.put(_Fields.COMMON, new FieldMetaData("common", (byte) 1, new StructMetaData(TType.STRUCT, Common.class)));
        tmpMap.put(_Fields.TO_ID, new FieldMetaData("toId", (byte) 1, new FieldValueMetaData(TType.STRING)));
        tmpMap.put(_Fields.DIGEST, new FieldMetaData("digest", (byte) 1, new FieldValueMetaData(TType.STRING)));
        tmpMap.put(_Fields.FILE_SIZE, new FieldMetaData("fileSize", (byte) 1, new FieldValueMetaData((byte) 10)));
        tmpMap.put(_Fields.PERMENANT, new FieldMetaData("permenant", (byte) 1, new FieldValueMetaData((byte) 2)));
        metaDataMap = Collections.unmodifiableMap(tmpMap);
        FieldMetaData.addStructMetaDataMap(MfsWebApiLogEntry.class, metaDataMap);
    }

    public MfsWebApiLogEntry() {
        this.__isset_bit_vector = new BitSet(2);
    }

    public MfsWebApiLogEntry(Common common, String toId, String digest, long fileSize, boolean permenant) {
        this();
        this.common = common;
        this.toId = toId;
        this.digest = digest;
        this.fileSize = fileSize;
        setFileSizeIsSet(true);
        this.permenant = permenant;
        setPermenantIsSet(true);
    }

    public MfsWebApiLogEntry(MfsWebApiLogEntry other) {
        this.__isset_bit_vector = new BitSet(2);
        this.__isset_bit_vector.clear();
        this.__isset_bit_vector.or(other.__isset_bit_vector);
        if (other.isSetCommon()) {
            this.common = new Common(other.common);
        }
        if (other.isSetToId()) {
            this.toId = other.toId;
        }
        if (other.isSetDigest()) {
            this.digest = other.digest;
        }
        this.fileSize = other.fileSize;
        this.permenant = other.permenant;
    }

    public MfsWebApiLogEntry deepCopy() {
        return new MfsWebApiLogEntry(this);
    }

    public void clear() {
        this.common = null;
        this.toId = null;
        this.digest = null;
        setFileSizeIsSet(false);
        this.fileSize = 0;
        setPermenantIsSet(false);
        this.permenant = false;
    }

    public Common getCommon() {
        return this.common;
    }

    public MfsWebApiLogEntry setCommon(Common common) {
        this.common = common;
        return this;
    }

    public void unsetCommon() {
        this.common = null;
    }

    public boolean isSetCommon() {
        return this.common != null;
    }

    public void setCommonIsSet(boolean value) {
        if (!value) {
            this.common = null;
        }
    }

    public String getToId() {
        return this.toId;
    }

    public MfsWebApiLogEntry setToId(String toId) {
        this.toId = toId;
        return this;
    }

    public void unsetToId() {
        this.toId = null;
    }

    public boolean isSetToId() {
        return this.toId != null;
    }

    public void setToIdIsSet(boolean value) {
        if (!value) {
            this.toId = null;
        }
    }

    public String getDigest() {
        return this.digest;
    }

    public MfsWebApiLogEntry setDigest(String digest) {
        this.digest = digest;
        return this;
    }

    public void unsetDigest() {
        this.digest = null;
    }

    public boolean isSetDigest() {
        return this.digest != null;
    }

    public void setDigestIsSet(boolean value) {
        if (!value) {
            this.digest = null;
        }
    }

    public long getFileSize() {
        return this.fileSize;
    }

    public MfsWebApiLogEntry setFileSize(long fileSize) {
        this.fileSize = fileSize;
        setFileSizeIsSet(true);
        return this;
    }

    public void unsetFileSize() {
        this.__isset_bit_vector.clear(__FILESIZE_ISSET_ID);
    }

    public boolean isSetFileSize() {
        return this.__isset_bit_vector.get(__FILESIZE_ISSET_ID);
    }

    public void setFileSizeIsSet(boolean value) {
        this.__isset_bit_vector.set(__FILESIZE_ISSET_ID, value);
    }

    public boolean isPermenant() {
        return this.permenant;
    }

    public MfsWebApiLogEntry setPermenant(boolean permenant) {
        this.permenant = permenant;
        setPermenantIsSet(true);
        return this;
    }

    public void unsetPermenant() {
        this.__isset_bit_vector.clear(__PERMENANT_ISSET_ID);
    }

    public boolean isSetPermenant() {
        return this.__isset_bit_vector.get(__PERMENANT_ISSET_ID);
    }

    public void setPermenantIsSet(boolean value) {
        this.__isset_bit_vector.set(__PERMENANT_ISSET_ID, value);
    }

    public void setFieldValue(_Fields field, Object value) {
        switch (AnonymousClass1.$SwitchMap$com$xiaomi$common$logger$thrift$mfs$MfsWebApiLogEntry$_Fields[field.ordinal()]) {
            case __PERMENANT_ISSET_ID /*1*/:
                if (value == null) {
                    unsetCommon();
                    return;
                } else {
                    setCommon((Common) value);
                    return;
                }
            case TTransportException.ALREADY_OPEN /*2*/:
                if (value == null) {
                    unsetToId();
                    return;
                } else {
                    setToId((String) value);
                    return;
                }
            case TTransportException.TIMED_OUT /*3*/:
                if (value == null) {
                    unsetDigest();
                    return;
                } else {
                    setDigest((String) value);
                    return;
                }
            case TTransportException.END_OF_FILE /*4*/:
                if (value == null) {
                    unsetFileSize();
                    return;
                } else {
                    setFileSize(((Long) value).longValue());
                    return;
                }
            case TProtocolException.NOT_IMPLEMENTED /*5*/:
                if (value == null) {
                    unsetPermenant();
                    return;
                } else {
                    setPermenant(((Boolean) value).booleanValue());
                    return;
                }
            default:
                return;
        }
    }

    public Object getFieldValue(_Fields field) {
        switch (AnonymousClass1.$SwitchMap$com$xiaomi$common$logger$thrift$mfs$MfsWebApiLogEntry$_Fields[field.ordinal()]) {
            case __PERMENANT_ISSET_ID /*1*/:
                return getCommon();
            case TTransportException.ALREADY_OPEN /*2*/:
                return getToId();
            case TTransportException.TIMED_OUT /*3*/:
                return getDigest();
            case TTransportException.END_OF_FILE /*4*/:
                return new Long(getFileSize());
            case TProtocolException.NOT_IMPLEMENTED /*5*/:
                return new Boolean(isPermenant());
            default:
                throw new IllegalStateException();
        }
    }

    public boolean isSet(_Fields field) {
        if (field == null) {
            throw new IllegalArgumentException();
        }
        switch (AnonymousClass1.$SwitchMap$com$xiaomi$common$logger$thrift$mfs$MfsWebApiLogEntry$_Fields[field.ordinal()]) {
            case __PERMENANT_ISSET_ID /*1*/:
                return isSetCommon();
            case TTransportException.ALREADY_OPEN /*2*/:
                return isSetToId();
            case TTransportException.TIMED_OUT /*3*/:
                return isSetDigest();
            case TTransportException.END_OF_FILE /*4*/:
                return isSetFileSize();
            case TProtocolException.NOT_IMPLEMENTED /*5*/:
                return isSetPermenant();
            default:
                throw new IllegalStateException();
        }
    }

    public boolean equals(Object that) {
        if (that != null && (that instanceof MfsWebApiLogEntry)) {
            return equals((MfsWebApiLogEntry) that);
        }
        return false;
    }

    public boolean equals(MfsWebApiLogEntry that) {
        if (that == null) {
            return false;
        }
        boolean this_present_common = isSetCommon();
        boolean that_present_common = that.isSetCommon();
        if (this_present_common || that_present_common) {
            if (!this_present_common || !that_present_common) {
                return false;
            }
            if (!this.common.equals(that.common)) {
                return false;
            }
        }
        boolean this_present_toId = isSetToId();
        boolean that_present_toId = that.isSetToId();
        if (this_present_toId || that_present_toId) {
            if (!this_present_toId || !that_present_toId) {
                return false;
            }
            if (!this.toId.equals(that.toId)) {
                return false;
            }
        }
        boolean this_present_digest = isSetDigest();
        boolean that_present_digest = that.isSetDigest();
        if (this_present_digest || that_present_digest) {
            if (!this_present_digest || !that_present_digest) {
                return false;
            }
            if (!this.digest.equals(that.digest)) {
                return false;
            }
        }
        if (!(__PERMENANT_ISSET_ID == null && __PERMENANT_ISSET_ID == null)) {
            if (__PERMENANT_ISSET_ID == null || __PERMENANT_ISSET_ID == null) {
                return false;
            }
            if (this.fileSize != that.fileSize) {
                return false;
            }
        }
        if (!(__PERMENANT_ISSET_ID == null && __PERMENANT_ISSET_ID == null)) {
            if (__PERMENANT_ISSET_ID == null || __PERMENANT_ISSET_ID == null) {
                return false;
            }
            if (this.permenant != that.permenant) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        return __FILESIZE_ISSET_ID;
    }

    public int compareTo(MfsWebApiLogEntry other) {
        if (!getClass().equals(other.getClass())) {
            return getClass().getName().compareTo(other.getClass().getName());
        }
        MfsWebApiLogEntry typedOther = other;
        int lastComparison = Boolean.valueOf(isSetCommon()).compareTo(Boolean.valueOf(typedOther.isSetCommon()));
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetCommon()) {
            lastComparison = TBaseHelper.compareTo(this.common, typedOther.common);
            if (lastComparison != 0) {
                return lastComparison;
            }
        }
        lastComparison = Boolean.valueOf(isSetToId()).compareTo(Boolean.valueOf(typedOther.isSetToId()));
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetToId()) {
            lastComparison = TBaseHelper.compareTo(this.toId, typedOther.toId);
            if (lastComparison != 0) {
                return lastComparison;
            }
        }
        lastComparison = Boolean.valueOf(isSetDigest()).compareTo(Boolean.valueOf(typedOther.isSetDigest()));
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetDigest()) {
            lastComparison = TBaseHelper.compareTo(this.digest, typedOther.digest);
            if (lastComparison != 0) {
                return lastComparison;
            }
        }
        lastComparison = Boolean.valueOf(isSetFileSize()).compareTo(Boolean.valueOf(typedOther.isSetFileSize()));
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetFileSize()) {
            lastComparison = TBaseHelper.compareTo(this.fileSize, typedOther.fileSize);
            if (lastComparison != 0) {
                return lastComparison;
            }
        }
        lastComparison = Boolean.valueOf(isSetPermenant()).compareTo(Boolean.valueOf(typedOther.isSetPermenant()));
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetPermenant()) {
            lastComparison = TBaseHelper.compareTo(this.permenant, typedOther.permenant);
            if (lastComparison != 0) {
                return lastComparison;
            }
        }
        return __FILESIZE_ISSET_ID;
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
                if (!isSetFileSize()) {
                    throw new TProtocolException("Required field 'fileSize' was not found in serialized data! Struct: " + toString());
                } else if (isSetPermenant()) {
                    validate();
                    return;
                } else {
                    throw new TProtocolException("Required field 'permenant' was not found in serialized data! Struct: " + toString());
                }
            }
            switch (field.id) {
                case __PERMENANT_ISSET_ID /*1*/:
                    if (field.type != TType.STRUCT) {
                        TProtocolUtil.skip(iprot, field.type);
                        break;
                    }
                    this.common = new Common();
                    this.common.read(iprot);
                    break;
                case TTransportException.ALREADY_OPEN /*2*/:
                    if (field.type != TType.STRING) {
                        TProtocolUtil.skip(iprot, field.type);
                        break;
                    } else {
                        this.toId = iprot.readString();
                        break;
                    }
                case TTransportException.TIMED_OUT /*3*/:
                    if (field.type != TType.STRING) {
                        TProtocolUtil.skip(iprot, field.type);
                        break;
                    } else {
                        this.digest = iprot.readString();
                        break;
                    }
                case TTransportException.END_OF_FILE /*4*/:
                    if (field.type != (byte) 10) {
                        TProtocolUtil.skip(iprot, field.type);
                        break;
                    }
                    this.fileSize = iprot.readI64();
                    setFileSizeIsSet(true);
                    break;
                case TProtocolException.NOT_IMPLEMENTED /*5*/:
                    if (field.type != (byte) 2) {
                        TProtocolUtil.skip(iprot, field.type);
                        break;
                    }
                    this.permenant = iprot.readBool();
                    setPermenantIsSet(true);
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
        if (this.common != null) {
            oprot.writeFieldBegin(COMMON_FIELD_DESC);
            this.common.write(oprot);
            oprot.writeFieldEnd();
        }
        if (this.toId != null) {
            oprot.writeFieldBegin(TO_ID_FIELD_DESC);
            oprot.writeString(this.toId);
            oprot.writeFieldEnd();
        }
        if (this.digest != null) {
            oprot.writeFieldBegin(DIGEST_FIELD_DESC);
            oprot.writeString(this.digest);
            oprot.writeFieldEnd();
        }
        oprot.writeFieldBegin(FILE_SIZE_FIELD_DESC);
        oprot.writeI64(this.fileSize);
        oprot.writeFieldEnd();
        oprot.writeFieldBegin(PERMENANT_FIELD_DESC);
        oprot.writeBool(this.permenant);
        oprot.writeFieldEnd();
        oprot.writeFieldStop();
        oprot.writeStructEnd();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("MfsWebApiLogEntry(");
        sb.append("common:");
        if (this.common == null) {
            sb.append("null");
        } else {
            sb.append(this.common);
        }
        if (!false) {
            sb.append(", ");
        }
        sb.append("toId:");
        if (this.toId == null) {
            sb.append("null");
        } else {
            sb.append(this.toId);
        }
        if (!false) {
            sb.append(", ");
        }
        sb.append("digest:");
        if (this.digest == null) {
            sb.append("null");
        } else {
            sb.append(this.digest);
        }
        if (!false) {
            sb.append(", ");
        }
        sb.append("fileSize:");
        sb.append(this.fileSize);
        if (!false) {
            sb.append(", ");
        }
        sb.append("permenant:");
        sb.append(this.permenant);
        sb.append(")");
        return sb.toString();
    }

    public void validate() throws TException {
        if (this.common == null) {
            throw new TProtocolException("Required field 'common' was not present! Struct: " + toString());
        } else if (this.toId == null) {
            throw new TProtocolException("Required field 'toId' was not present! Struct: " + toString());
        } else if (this.digest == null) {
            throw new TProtocolException("Required field 'digest' was not present! Struct: " + toString());
        }
    }
}
