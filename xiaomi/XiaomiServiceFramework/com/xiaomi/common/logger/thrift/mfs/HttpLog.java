package com.xiaomi.common.logger.thrift.mfs;

import com.xiaomi.common.logger.thrift.Common;
import java.io.Serializable;
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

public class HttpLog implements TBase<HttpLog, _Fields>, Serializable, Cloneable {
    private static final TField CATEGORY_FIELD_DESC;
    private static final TField COMMON_FIELD_DESC;
    private static final TField HTTP_API_FIELD_DESC;
    private static final TField PASSPORT_FIELD_DESC;
    private static final TStruct STRUCT_DESC;
    public static final Map<_Fields, FieldMetaData> metaDataMap;
    private String category;
    private Common common;
    private HttpApi httpApi;
    private Passport passport;

    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$xiaomi$common$logger$thrift$mfs$HttpLog$_Fields;

        static {
            $SwitchMap$com$xiaomi$common$logger$thrift$mfs$HttpLog$_Fields = new int[_Fields.values().length];
            try {
                $SwitchMap$com$xiaomi$common$logger$thrift$mfs$HttpLog$_Fields[_Fields.COMMON.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$xiaomi$common$logger$thrift$mfs$HttpLog$_Fields[_Fields.CATEGORY.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$xiaomi$common$logger$thrift$mfs$HttpLog$_Fields[_Fields.HTTP_API.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$xiaomi$common$logger$thrift$mfs$HttpLog$_Fields[_Fields.PASSPORT.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    public enum _Fields implements TFieldIdEnum {
        COMMON((short) 1, "common"),
        CATEGORY((short) 2, "category"),
        HTTP_API((short) 3, "httpApi"),
        PASSPORT((short) 4, "passport");
        
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
                    return COMMON;
                case TTransportException.ALREADY_OPEN /*2*/:
                    return CATEGORY;
                case TTransportException.TIMED_OUT /*3*/:
                    return HTTP_API;
                case TTransportException.END_OF_FILE /*4*/:
                    return PASSPORT;
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
        STRUCT_DESC = new TStruct("HttpLog");
        COMMON_FIELD_DESC = new TField("common", TType.STRUCT, (short) 1);
        CATEGORY_FIELD_DESC = new TField("category", TType.STRING, (short) 2);
        HTTP_API_FIELD_DESC = new TField("httpApi", TType.STRUCT, (short) 3);
        PASSPORT_FIELD_DESC = new TField("passport", TType.STRUCT, (short) 4);
        Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
        tmpMap.put(_Fields.COMMON, new FieldMetaData("common", (byte) 1, new StructMetaData(TType.STRUCT, Common.class)));
        tmpMap.put(_Fields.CATEGORY, new FieldMetaData("category", (byte) 1, new FieldValueMetaData(TType.STRING)));
        tmpMap.put(_Fields.HTTP_API, new FieldMetaData("httpApi", (byte) 2, new StructMetaData(TType.STRUCT, HttpApi.class)));
        tmpMap.put(_Fields.PASSPORT, new FieldMetaData("passport", (byte) 2, new StructMetaData(TType.STRUCT, Passport.class)));
        metaDataMap = Collections.unmodifiableMap(tmpMap);
        FieldMetaData.addStructMetaDataMap(HttpLog.class, metaDataMap);
    }

    public HttpLog() {
        this.category = "";
    }

    public HttpLog(Common common, String category) {
        this();
        this.common = common;
        this.category = category;
    }

    public HttpLog(HttpLog other) {
        if (other.isSetCommon()) {
            this.common = new Common(other.common);
        }
        if (other.isSetCategory()) {
            this.category = other.category;
        }
        if (other.isSetHttpApi()) {
            this.httpApi = new HttpApi(other.httpApi);
        }
        if (other.isSetPassport()) {
            this.passport = new Passport(other.passport);
        }
    }

    public HttpLog deepCopy() {
        return new HttpLog(this);
    }

    public void clear() {
        this.common = null;
        this.category = "";
        this.httpApi = null;
        this.passport = null;
    }

    public Common getCommon() {
        return this.common;
    }

    public HttpLog setCommon(Common common) {
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

    public String getCategory() {
        return this.category;
    }

    public HttpLog setCategory(String category) {
        this.category = category;
        return this;
    }

    public void unsetCategory() {
        this.category = null;
    }

    public boolean isSetCategory() {
        return this.category != null;
    }

    public void setCategoryIsSet(boolean value) {
        if (!value) {
            this.category = null;
        }
    }

    public HttpApi getHttpApi() {
        return this.httpApi;
    }

    public HttpLog setHttpApi(HttpApi httpApi) {
        this.httpApi = httpApi;
        return this;
    }

    public void unsetHttpApi() {
        this.httpApi = null;
    }

    public boolean isSetHttpApi() {
        return this.httpApi != null;
    }

    public void setHttpApiIsSet(boolean value) {
        if (!value) {
            this.httpApi = null;
        }
    }

    public Passport getPassport() {
        return this.passport;
    }

    public HttpLog setPassport(Passport passport) {
        this.passport = passport;
        return this;
    }

    public void unsetPassport() {
        this.passport = null;
    }

    public boolean isSetPassport() {
        return this.passport != null;
    }

    public void setPassportIsSet(boolean value) {
        if (!value) {
            this.passport = null;
        }
    }

    public void setFieldValue(_Fields field, Object value) {
        switch (AnonymousClass1.$SwitchMap$com$xiaomi$common$logger$thrift$mfs$HttpLog$_Fields[field.ordinal()]) {
            case TTransportException.NOT_OPEN /*1*/:
                if (value == null) {
                    unsetCommon();
                    return;
                } else {
                    setCommon((Common) value);
                    return;
                }
            case TTransportException.ALREADY_OPEN /*2*/:
                if (value == null) {
                    unsetCategory();
                    return;
                } else {
                    setCategory((String) value);
                    return;
                }
            case TTransportException.TIMED_OUT /*3*/:
                if (value == null) {
                    unsetHttpApi();
                    return;
                } else {
                    setHttpApi((HttpApi) value);
                    return;
                }
            case TTransportException.END_OF_FILE /*4*/:
                if (value == null) {
                    unsetPassport();
                    return;
                } else {
                    setPassport((Passport) value);
                    return;
                }
            default:
                return;
        }
    }

    public Object getFieldValue(_Fields field) {
        switch (AnonymousClass1.$SwitchMap$com$xiaomi$common$logger$thrift$mfs$HttpLog$_Fields[field.ordinal()]) {
            case TTransportException.NOT_OPEN /*1*/:
                return getCommon();
            case TTransportException.ALREADY_OPEN /*2*/:
                return getCategory();
            case TTransportException.TIMED_OUT /*3*/:
                return getHttpApi();
            case TTransportException.END_OF_FILE /*4*/:
                return getPassport();
            default:
                throw new IllegalStateException();
        }
    }

    public boolean isSet(_Fields field) {
        if (field == null) {
            throw new IllegalArgumentException();
        }
        switch (AnonymousClass1.$SwitchMap$com$xiaomi$common$logger$thrift$mfs$HttpLog$_Fields[field.ordinal()]) {
            case TTransportException.NOT_OPEN /*1*/:
                return isSetCommon();
            case TTransportException.ALREADY_OPEN /*2*/:
                return isSetCategory();
            case TTransportException.TIMED_OUT /*3*/:
                return isSetHttpApi();
            case TTransportException.END_OF_FILE /*4*/:
                return isSetPassport();
            default:
                throw new IllegalStateException();
        }
    }

    public boolean equals(Object that) {
        if (that != null && (that instanceof HttpLog)) {
            return equals((HttpLog) that);
        }
        return false;
    }

    public boolean equals(HttpLog that) {
        if (that == null) {
            return false;
        }
        boolean this_present_common = isSetCommon();
        boolean that_present_common = that.isSetCommon();
        if ((this_present_common || that_present_common) && (!this_present_common || !that_present_common || !this.common.equals(that.common))) {
            return false;
        }
        boolean this_present_category = isSetCategory();
        boolean that_present_category = that.isSetCategory();
        if ((this_present_category || that_present_category) && (!this_present_category || !that_present_category || !this.category.equals(that.category))) {
            return false;
        }
        boolean this_present_httpApi = isSetHttpApi();
        boolean that_present_httpApi = that.isSetHttpApi();
        if ((this_present_httpApi || that_present_httpApi) && (!this_present_httpApi || !that_present_httpApi || !this.httpApi.equals(that.httpApi))) {
            return false;
        }
        boolean this_present_passport = isSetPassport();
        boolean that_present_passport = that.isSetPassport();
        if ((this_present_passport || that_present_passport) && (!this_present_passport || !that_present_passport || !this.passport.equals(that.passport))) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return 0;
    }

    public int compareTo(HttpLog other) {
        if (!getClass().equals(other.getClass())) {
            return getClass().getName().compareTo(other.getClass().getName());
        }
        HttpLog typedOther = other;
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
        lastComparison = Boolean.valueOf(isSetCategory()).compareTo(Boolean.valueOf(typedOther.isSetCategory()));
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetCategory()) {
            lastComparison = TBaseHelper.compareTo(this.category, typedOther.category);
            if (lastComparison != 0) {
                return lastComparison;
            }
        }
        lastComparison = Boolean.valueOf(isSetHttpApi()).compareTo(Boolean.valueOf(typedOther.isSetHttpApi()));
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetHttpApi()) {
            lastComparison = TBaseHelper.compareTo(this.httpApi, typedOther.httpApi);
            if (lastComparison != 0) {
                return lastComparison;
            }
        }
        lastComparison = Boolean.valueOf(isSetPassport()).compareTo(Boolean.valueOf(typedOther.isSetPassport()));
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetPassport()) {
            lastComparison = TBaseHelper.compareTo(this.passport, typedOther.passport);
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
                        this.category = iprot.readString();
                        break;
                    }
                case TTransportException.TIMED_OUT /*3*/:
                    if (field.type != TType.STRUCT) {
                        TProtocolUtil.skip(iprot, field.type);
                        break;
                    }
                    this.httpApi = new HttpApi();
                    this.httpApi.read(iprot);
                    break;
                case TTransportException.END_OF_FILE /*4*/:
                    if (field.type != TType.STRUCT) {
                        TProtocolUtil.skip(iprot, field.type);
                        break;
                    }
                    this.passport = new Passport();
                    this.passport.read(iprot);
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
        if (this.category != null) {
            oprot.writeFieldBegin(CATEGORY_FIELD_DESC);
            oprot.writeString(this.category);
            oprot.writeFieldEnd();
        }
        if (this.httpApi != null && isSetHttpApi()) {
            oprot.writeFieldBegin(HTTP_API_FIELD_DESC);
            this.httpApi.write(oprot);
            oprot.writeFieldEnd();
        }
        if (this.passport != null && isSetPassport()) {
            oprot.writeFieldBegin(PASSPORT_FIELD_DESC);
            this.passport.write(oprot);
            oprot.writeFieldEnd();
        }
        oprot.writeFieldStop();
        oprot.writeStructEnd();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("HttpLog(");
        sb.append("common:");
        if (this.common == null) {
            sb.append("null");
        } else {
            sb.append(this.common);
        }
        if (!false) {
            sb.append(", ");
        }
        sb.append("category:");
        if (this.category == null) {
            sb.append("null");
        } else {
            sb.append(this.category);
        }
        boolean first = false;
        if (isSetHttpApi()) {
            if (null == null) {
                sb.append(", ");
            }
            sb.append("httpApi:");
            if (this.httpApi == null) {
                sb.append("null");
            } else {
                sb.append(this.httpApi);
            }
            first = false;
        }
        if (isSetPassport()) {
            if (!first) {
                sb.append(", ");
            }
            sb.append("passport:");
            if (this.passport == null) {
                sb.append("null");
            } else {
                sb.append(this.passport);
            }
        }
        sb.append(")");
        return sb.toString();
    }

    public void validate() throws TException {
        if (this.common == null) {
            throw new TProtocolException("Required field 'common' was not present! Struct: " + toString());
        } else if (this.category == null) {
            throw new TProtocolException("Required field 'category' was not present! Struct: " + toString());
        }
    }
}
