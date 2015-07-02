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
import org.apache.thrift.TApplicationException;
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

public class IssManagementCenterLogEntry implements WithCommon, TBase<IssManagementCenterLogEntry, _Fields>, Serializable, Cloneable {
    private static final TField APP_ID_FIELD_DESC;
    private static final TField COMMON_FIELD_DESC;
    private static final TField FILE_ID_FIELD_DESC;
    private static final TField FILE_SIZE_FIELD_DESC;
    private static final TField MIME_TYPE_FIELD_DESC;
    private static final TField STORE_TYPE_FIELD_DESC;
    private static final TStruct STRUCT_DESC;
    private static final TField URL_FIELD_DESC;
    private static final int __APPID_ISSET_ID = 0;
    private static final int __FILESIZE_ISSET_ID = 2;
    private static final int __STORETYPE_ISSET_ID = 1;
    public static final Map<_Fields, FieldMetaData> metaDataMap;
    private BitSet __isset_bit_vector;
    public int appId;
    public Common common;
    public String fileId;
    public long fileSize;
    public String mimeType;
    public int storeType;
    public String url;

    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$xiaomi$common$logger$thrift$mfs$IssManagementCenterLogEntry$_Fields;

        static {
            $SwitchMap$com$xiaomi$common$logger$thrift$mfs$IssManagementCenterLogEntry$_Fields = new int[_Fields.values().length];
            try {
                $SwitchMap$com$xiaomi$common$logger$thrift$mfs$IssManagementCenterLogEntry$_Fields[_Fields.COMMON.ordinal()] = IssManagementCenterLogEntry.__STORETYPE_ISSET_ID;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$xiaomi$common$logger$thrift$mfs$IssManagementCenterLogEntry$_Fields[_Fields.APP_ID.ordinal()] = IssManagementCenterLogEntry.__FILESIZE_ISSET_ID;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$xiaomi$common$logger$thrift$mfs$IssManagementCenterLogEntry$_Fields[_Fields.URL.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$xiaomi$common$logger$thrift$mfs$IssManagementCenterLogEntry$_Fields[_Fields.STORE_TYPE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$xiaomi$common$logger$thrift$mfs$IssManagementCenterLogEntry$_Fields[_Fields.FILE_ID.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$xiaomi$common$logger$thrift$mfs$IssManagementCenterLogEntry$_Fields[_Fields.MIME_TYPE.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$com$xiaomi$common$logger$thrift$mfs$IssManagementCenterLogEntry$_Fields[_Fields.FILE_SIZE.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
        }
    }

    public enum _Fields implements TFieldIdEnum {
        COMMON((short) 1, "common"),
        APP_ID((short) 2, "appId"),
        URL((short) 3, "url"),
        STORE_TYPE((short) 4, "storeType"),
        FILE_ID((short) 5, "fileId"),
        MIME_TYPE((short) 6, "mimeType"),
        FILE_SIZE((short) 7, "fileSize");
        
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
                case IssManagementCenterLogEntry.__STORETYPE_ISSET_ID /*1*/:
                    return COMMON;
                case IssManagementCenterLogEntry.__FILESIZE_ISSET_ID /*2*/:
                    return APP_ID;
                case TTransportException.TIMED_OUT /*3*/:
                    return URL;
                case TTransportException.END_OF_FILE /*4*/:
                    return STORE_TYPE;
                case TProtocolException.NOT_IMPLEMENTED /*5*/:
                    return FILE_ID;
                case TApplicationException.INTERNAL_ERROR /*6*/:
                    return MIME_TYPE;
                case TApplicationException.PROTOCOL_ERROR /*7*/:
                    return FILE_SIZE;
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
        STRUCT_DESC = new TStruct("IssManagementCenterLogEntry");
        COMMON_FIELD_DESC = new TField("common", TType.STRUCT, (short) 1);
        APP_ID_FIELD_DESC = new TField("appId", (byte) 8, (short) 2);
        URL_FIELD_DESC = new TField("url", TType.STRING, (short) 3);
        STORE_TYPE_FIELD_DESC = new TField("storeType", (byte) 8, (short) 4);
        FILE_ID_FIELD_DESC = new TField("fileId", TType.STRING, (short) 5);
        MIME_TYPE_FIELD_DESC = new TField("mimeType", TType.STRING, (short) 6);
        FILE_SIZE_FIELD_DESC = new TField("fileSize", (byte) 10, (short) 7);
        Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
        tmpMap.put(_Fields.COMMON, new FieldMetaData("common", (byte) 1, new StructMetaData(TType.STRUCT, Common.class)));
        tmpMap.put(_Fields.APP_ID, new FieldMetaData("appId", (byte) 1, new FieldValueMetaData((byte) 8)));
        tmpMap.put(_Fields.URL, new FieldMetaData("url", (byte) 1, new FieldValueMetaData(TType.STRING)));
        tmpMap.put(_Fields.STORE_TYPE, new FieldMetaData("storeType", (byte) 1, new FieldValueMetaData((byte) 8)));
        tmpMap.put(_Fields.FILE_ID, new FieldMetaData("fileId", (byte) 2, new FieldValueMetaData(TType.STRING)));
        tmpMap.put(_Fields.MIME_TYPE, new FieldMetaData("mimeType", (byte) 2, new FieldValueMetaData(TType.STRING)));
        tmpMap.put(_Fields.FILE_SIZE, new FieldMetaData("fileSize", (byte) 2, new FieldValueMetaData((byte) 10)));
        metaDataMap = Collections.unmodifiableMap(tmpMap);
        FieldMetaData.addStructMetaDataMap(IssManagementCenterLogEntry.class, metaDataMap);
    }

    public IssManagementCenterLogEntry() {
        this.__isset_bit_vector = new BitSet(3);
    }

    public IssManagementCenterLogEntry(Common common, int appId, String url, int storeType) {
        this();
        this.common = common;
        this.appId = appId;
        setAppIdIsSet(true);
        this.url = url;
        this.storeType = storeType;
        setStoreTypeIsSet(true);
    }

    public IssManagementCenterLogEntry(IssManagementCenterLogEntry other) {
        this.__isset_bit_vector = new BitSet(3);
        this.__isset_bit_vector.clear();
        this.__isset_bit_vector.or(other.__isset_bit_vector);
        if (other.isSetCommon()) {
            this.common = new Common(other.common);
        }
        this.appId = other.appId;
        if (other.isSetUrl()) {
            this.url = other.url;
        }
        this.storeType = other.storeType;
        if (other.isSetFileId()) {
            this.fileId = other.fileId;
        }
        if (other.isSetMimeType()) {
            this.mimeType = other.mimeType;
        }
        this.fileSize = other.fileSize;
    }

    public IssManagementCenterLogEntry deepCopy() {
        return new IssManagementCenterLogEntry(this);
    }

    public void clear() {
        this.common = null;
        setAppIdIsSet(false);
        this.appId = __APPID_ISSET_ID;
        this.url = null;
        setStoreTypeIsSet(false);
        this.storeType = __APPID_ISSET_ID;
        this.fileId = null;
        this.mimeType = null;
        setFileSizeIsSet(false);
        this.fileSize = 0;
    }

    public Common getCommon() {
        return this.common;
    }

    public IssManagementCenterLogEntry setCommon(Common common) {
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

    public int getAppId() {
        return this.appId;
    }

    public IssManagementCenterLogEntry setAppId(int appId) {
        this.appId = appId;
        setAppIdIsSet(true);
        return this;
    }

    public void unsetAppId() {
        this.__isset_bit_vector.clear(__APPID_ISSET_ID);
    }

    public boolean isSetAppId() {
        return this.__isset_bit_vector.get(__APPID_ISSET_ID);
    }

    public void setAppIdIsSet(boolean value) {
        this.__isset_bit_vector.set(__APPID_ISSET_ID, value);
    }

    public String getUrl() {
        return this.url;
    }

    public IssManagementCenterLogEntry setUrl(String url) {
        this.url = url;
        return this;
    }

    public void unsetUrl() {
        this.url = null;
    }

    public boolean isSetUrl() {
        return this.url != null;
    }

    public void setUrlIsSet(boolean value) {
        if (!value) {
            this.url = null;
        }
    }

    public int getStoreType() {
        return this.storeType;
    }

    public IssManagementCenterLogEntry setStoreType(int storeType) {
        this.storeType = storeType;
        setStoreTypeIsSet(true);
        return this;
    }

    public void unsetStoreType() {
        this.__isset_bit_vector.clear(__STORETYPE_ISSET_ID);
    }

    public boolean isSetStoreType() {
        return this.__isset_bit_vector.get(__STORETYPE_ISSET_ID);
    }

    public void setStoreTypeIsSet(boolean value) {
        this.__isset_bit_vector.set(__STORETYPE_ISSET_ID, value);
    }

    public String getFileId() {
        return this.fileId;
    }

    public IssManagementCenterLogEntry setFileId(String fileId) {
        this.fileId = fileId;
        return this;
    }

    public void unsetFileId() {
        this.fileId = null;
    }

    public boolean isSetFileId() {
        return this.fileId != null;
    }

    public void setFileIdIsSet(boolean value) {
        if (!value) {
            this.fileId = null;
        }
    }

    public String getMimeType() {
        return this.mimeType;
    }

    public IssManagementCenterLogEntry setMimeType(String mimeType) {
        this.mimeType = mimeType;
        return this;
    }

    public void unsetMimeType() {
        this.mimeType = null;
    }

    public boolean isSetMimeType() {
        return this.mimeType != null;
    }

    public void setMimeTypeIsSet(boolean value) {
        if (!value) {
            this.mimeType = null;
        }
    }

    public long getFileSize() {
        return this.fileSize;
    }

    public IssManagementCenterLogEntry setFileSize(long fileSize) {
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

    public void setFieldValue(_Fields field, Object value) {
        switch (AnonymousClass1.$SwitchMap$com$xiaomi$common$logger$thrift$mfs$IssManagementCenterLogEntry$_Fields[field.ordinal()]) {
            case __STORETYPE_ISSET_ID /*1*/:
                if (value == null) {
                    unsetCommon();
                    return;
                } else {
                    setCommon((Common) value);
                    return;
                }
            case __FILESIZE_ISSET_ID /*2*/:
                if (value == null) {
                    unsetAppId();
                    return;
                } else {
                    setAppId(((Integer) value).intValue());
                    return;
                }
            case TTransportException.TIMED_OUT /*3*/:
                if (value == null) {
                    unsetUrl();
                    return;
                } else {
                    setUrl((String) value);
                    return;
                }
            case TTransportException.END_OF_FILE /*4*/:
                if (value == null) {
                    unsetStoreType();
                    return;
                } else {
                    setStoreType(((Integer) value).intValue());
                    return;
                }
            case TProtocolException.NOT_IMPLEMENTED /*5*/:
                if (value == null) {
                    unsetFileId();
                    return;
                } else {
                    setFileId((String) value);
                    return;
                }
            case TApplicationException.INTERNAL_ERROR /*6*/:
                if (value == null) {
                    unsetMimeType();
                    return;
                } else {
                    setMimeType((String) value);
                    return;
                }
            case TApplicationException.PROTOCOL_ERROR /*7*/:
                if (value == null) {
                    unsetFileSize();
                    return;
                } else {
                    setFileSize(((Long) value).longValue());
                    return;
                }
            default:
                return;
        }
    }

    public Object getFieldValue(_Fields field) {
        switch (AnonymousClass1.$SwitchMap$com$xiaomi$common$logger$thrift$mfs$IssManagementCenterLogEntry$_Fields[field.ordinal()]) {
            case __STORETYPE_ISSET_ID /*1*/:
                return getCommon();
            case __FILESIZE_ISSET_ID /*2*/:
                return new Integer(getAppId());
            case TTransportException.TIMED_OUT /*3*/:
                return getUrl();
            case TTransportException.END_OF_FILE /*4*/:
                return new Integer(getStoreType());
            case TProtocolException.NOT_IMPLEMENTED /*5*/:
                return getFileId();
            case TApplicationException.INTERNAL_ERROR /*6*/:
                return getMimeType();
            case TApplicationException.PROTOCOL_ERROR /*7*/:
                return new Long(getFileSize());
            default:
                throw new IllegalStateException();
        }
    }

    public boolean isSet(_Fields field) {
        if (field == null) {
            throw new IllegalArgumentException();
        }
        switch (AnonymousClass1.$SwitchMap$com$xiaomi$common$logger$thrift$mfs$IssManagementCenterLogEntry$_Fields[field.ordinal()]) {
            case __STORETYPE_ISSET_ID /*1*/:
                return isSetCommon();
            case __FILESIZE_ISSET_ID /*2*/:
                return isSetAppId();
            case TTransportException.TIMED_OUT /*3*/:
                return isSetUrl();
            case TTransportException.END_OF_FILE /*4*/:
                return isSetStoreType();
            case TProtocolException.NOT_IMPLEMENTED /*5*/:
                return isSetFileId();
            case TApplicationException.INTERNAL_ERROR /*6*/:
                return isSetMimeType();
            case TApplicationException.PROTOCOL_ERROR /*7*/:
                return isSetFileSize();
            default:
                throw new IllegalStateException();
        }
    }

    public boolean equals(Object that) {
        if (that != null && (that instanceof IssManagementCenterLogEntry)) {
            return equals((IssManagementCenterLogEntry) that);
        }
        return false;
    }

    public boolean equals(IssManagementCenterLogEntry that) {
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
        if (!(__STORETYPE_ISSET_ID == null && __STORETYPE_ISSET_ID == null)) {
            if (__STORETYPE_ISSET_ID == null || __STORETYPE_ISSET_ID == null) {
                return false;
            }
            if (this.appId != that.appId) {
                return false;
            }
        }
        boolean this_present_url = isSetUrl();
        boolean that_present_url = that.isSetUrl();
        if (this_present_url || that_present_url) {
            if (!this_present_url || !that_present_url) {
                return false;
            }
            if (!this.url.equals(that.url)) {
                return false;
            }
        }
        if (!(__STORETYPE_ISSET_ID == null && __STORETYPE_ISSET_ID == null)) {
            if (__STORETYPE_ISSET_ID == null || __STORETYPE_ISSET_ID == null) {
                return false;
            }
            if (this.storeType != that.storeType) {
                return false;
            }
        }
        boolean this_present_fileId = isSetFileId();
        boolean that_present_fileId = that.isSetFileId();
        if (this_present_fileId || that_present_fileId) {
            if (!this_present_fileId || !that_present_fileId) {
                return false;
            }
            if (!this.fileId.equals(that.fileId)) {
                return false;
            }
        }
        boolean this_present_mimeType = isSetMimeType();
        boolean that_present_mimeType = that.isSetMimeType();
        if (this_present_mimeType || that_present_mimeType) {
            if (!this_present_mimeType || !that_present_mimeType) {
                return false;
            }
            if (!this.mimeType.equals(that.mimeType)) {
                return false;
            }
        }
        boolean this_present_fileSize = isSetFileSize();
        boolean that_present_fileSize = that.isSetFileSize();
        if (this_present_fileSize || that_present_fileSize) {
            if (!this_present_fileSize || !that_present_fileSize) {
                return false;
            }
            if (this.fileSize != that.fileSize) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        return __APPID_ISSET_ID;
    }

    public int compareTo(IssManagementCenterLogEntry other) {
        if (!getClass().equals(other.getClass())) {
            return getClass().getName().compareTo(other.getClass().getName());
        }
        IssManagementCenterLogEntry typedOther = other;
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
        lastComparison = Boolean.valueOf(isSetAppId()).compareTo(Boolean.valueOf(typedOther.isSetAppId()));
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetAppId()) {
            lastComparison = TBaseHelper.compareTo(this.appId, typedOther.appId);
            if (lastComparison != 0) {
                return lastComparison;
            }
        }
        lastComparison = Boolean.valueOf(isSetUrl()).compareTo(Boolean.valueOf(typedOther.isSetUrl()));
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetUrl()) {
            lastComparison = TBaseHelper.compareTo(this.url, typedOther.url);
            if (lastComparison != 0) {
                return lastComparison;
            }
        }
        lastComparison = Boolean.valueOf(isSetStoreType()).compareTo(Boolean.valueOf(typedOther.isSetStoreType()));
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetStoreType()) {
            lastComparison = TBaseHelper.compareTo(this.storeType, typedOther.storeType);
            if (lastComparison != 0) {
                return lastComparison;
            }
        }
        lastComparison = Boolean.valueOf(isSetFileId()).compareTo(Boolean.valueOf(typedOther.isSetFileId()));
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetFileId()) {
            lastComparison = TBaseHelper.compareTo(this.fileId, typedOther.fileId);
            if (lastComparison != 0) {
                return lastComparison;
            }
        }
        lastComparison = Boolean.valueOf(isSetMimeType()).compareTo(Boolean.valueOf(typedOther.isSetMimeType()));
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetMimeType()) {
            lastComparison = TBaseHelper.compareTo(this.mimeType, typedOther.mimeType);
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
        return __APPID_ISSET_ID;
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
                if (!isSetAppId()) {
                    throw new TProtocolException("Required field 'appId' was not found in serialized data! Struct: " + toString());
                } else if (isSetStoreType()) {
                    validate();
                    return;
                } else {
                    throw new TProtocolException("Required field 'storeType' was not found in serialized data! Struct: " + toString());
                }
            }
            switch (field.id) {
                case __STORETYPE_ISSET_ID /*1*/:
                    if (field.type != TType.STRUCT) {
                        TProtocolUtil.skip(iprot, field.type);
                        break;
                    }
                    this.common = new Common();
                    this.common.read(iprot);
                    break;
                case __FILESIZE_ISSET_ID /*2*/:
                    if (field.type != (byte) 8) {
                        TProtocolUtil.skip(iprot, field.type);
                        break;
                    }
                    this.appId = iprot.readI32();
                    setAppIdIsSet(true);
                    break;
                case TTransportException.TIMED_OUT /*3*/:
                    if (field.type != TType.STRING) {
                        TProtocolUtil.skip(iprot, field.type);
                        break;
                    } else {
                        this.url = iprot.readString();
                        break;
                    }
                case TTransportException.END_OF_FILE /*4*/:
                    if (field.type != (byte) 8) {
                        TProtocolUtil.skip(iprot, field.type);
                        break;
                    }
                    this.storeType = iprot.readI32();
                    setStoreTypeIsSet(true);
                    break;
                case TProtocolException.NOT_IMPLEMENTED /*5*/:
                    if (field.type != TType.STRING) {
                        TProtocolUtil.skip(iprot, field.type);
                        break;
                    } else {
                        this.fileId = iprot.readString();
                        break;
                    }
                case TApplicationException.INTERNAL_ERROR /*6*/:
                    if (field.type != TType.STRING) {
                        TProtocolUtil.skip(iprot, field.type);
                        break;
                    } else {
                        this.mimeType = iprot.readString();
                        break;
                    }
                case TApplicationException.PROTOCOL_ERROR /*7*/:
                    if (field.type != (byte) 10) {
                        TProtocolUtil.skip(iprot, field.type);
                        break;
                    }
                    this.fileSize = iprot.readI64();
                    setFileSizeIsSet(true);
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
        oprot.writeFieldBegin(APP_ID_FIELD_DESC);
        oprot.writeI32(this.appId);
        oprot.writeFieldEnd();
        if (this.url != null) {
            oprot.writeFieldBegin(URL_FIELD_DESC);
            oprot.writeString(this.url);
            oprot.writeFieldEnd();
        }
        oprot.writeFieldBegin(STORE_TYPE_FIELD_DESC);
        oprot.writeI32(this.storeType);
        oprot.writeFieldEnd();
        if (this.fileId != null && isSetFileId()) {
            oprot.writeFieldBegin(FILE_ID_FIELD_DESC);
            oprot.writeString(this.fileId);
            oprot.writeFieldEnd();
        }
        if (this.mimeType != null && isSetMimeType()) {
            oprot.writeFieldBegin(MIME_TYPE_FIELD_DESC);
            oprot.writeString(this.mimeType);
            oprot.writeFieldEnd();
        }
        if (isSetFileSize()) {
            oprot.writeFieldBegin(FILE_SIZE_FIELD_DESC);
            oprot.writeI64(this.fileSize);
            oprot.writeFieldEnd();
        }
        oprot.writeFieldStop();
        oprot.writeStructEnd();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("IssManagementCenterLogEntry(");
        sb.append("common:");
        if (this.common == null) {
            sb.append("null");
        } else {
            sb.append(this.common);
        }
        if (!false) {
            sb.append(", ");
        }
        sb.append("appId:");
        sb.append(this.appId);
        if (!false) {
            sb.append(", ");
        }
        sb.append("url:");
        if (this.url == null) {
            sb.append("null");
        } else {
            sb.append(this.url);
        }
        if (!false) {
            sb.append(", ");
        }
        sb.append("storeType:");
        sb.append(this.storeType);
        boolean first = false;
        if (isSetFileId()) {
            if (__APPID_ISSET_ID == null) {
                sb.append(", ");
            }
            sb.append("fileId:");
            if (this.fileId == null) {
                sb.append("null");
            } else {
                sb.append(this.fileId);
            }
            first = false;
        }
        if (isSetMimeType()) {
            if (!first) {
                sb.append(", ");
            }
            sb.append("mimeType:");
            if (this.mimeType == null) {
                sb.append("null");
            } else {
                sb.append(this.mimeType);
            }
            first = false;
        }
        if (isSetFileSize()) {
            if (!first) {
                sb.append(", ");
            }
            sb.append("fileSize:");
            sb.append(this.fileSize);
        }
        sb.append(")");
        return sb.toString();
    }

    public void validate() throws TException {
        if (this.common == null) {
            throw new TProtocolException("Required field 'common' was not present! Struct: " + toString());
        } else if (this.url == null) {
            throw new TProtocolException("Required field 'url' was not present! Struct: " + toString());
        }
    }
}
