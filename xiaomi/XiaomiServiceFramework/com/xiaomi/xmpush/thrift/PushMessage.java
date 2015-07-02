package com.xiaomi.xmpush.thrift;

import com.xiaomi.push.service.PushConstants;
import com.xiaomi.push.service.PushServiceConstants;
import com.xiaomi.xmsf.push.service.Constants;
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

public class PushMessage implements TBase<PushMessage, _Fields>, Serializable, Cloneable {
    private static final TField APP_ID_FIELD_DESC;
    private static final TField COLLAPSE_KEY_FIELD_DESC;
    private static final TField CREATE_AT_FIELD_DESC;
    private static final TField ID_FIELD_DESC;
    private static final TField PACKAGE_NAME_FIELD_DESC;
    private static final TField PAYLOAD_FIELD_DESC;
    private static final TStruct STRUCT_DESC;
    private static final TField TO_FIELD_DESC;
    private static final TField TTL_FIELD_DESC;
    private static final int __CREATEAT_ISSET_ID = 0;
    private static final int __TTL_ISSET_ID = 1;
    public static final Map<_Fields, FieldMetaData> metaDataMap;
    private BitSet __isset_bit_vector;
    public String appId;
    public String collapseKey;
    public long createAt;
    public String id;
    public String packageName;
    public String payload;
    public Target to;
    public long ttl;

    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$xiaomi$xmpush$thrift$PushMessage$_Fields;

        static {
            $SwitchMap$com$xiaomi$xmpush$thrift$PushMessage$_Fields = new int[_Fields.values().length];
            try {
                $SwitchMap$com$xiaomi$xmpush$thrift$PushMessage$_Fields[_Fields.TO.ordinal()] = PushMessage.__TTL_ISSET_ID;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$xiaomi$xmpush$thrift$PushMessage$_Fields[_Fields.ID.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$xiaomi$xmpush$thrift$PushMessage$_Fields[_Fields.APP_ID.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$xiaomi$xmpush$thrift$PushMessage$_Fields[_Fields.PAYLOAD.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$xiaomi$xmpush$thrift$PushMessage$_Fields[_Fields.CREATE_AT.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$xiaomi$xmpush$thrift$PushMessage$_Fields[_Fields.TTL.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$com$xiaomi$xmpush$thrift$PushMessage$_Fields[_Fields.COLLAPSE_KEY.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$com$xiaomi$xmpush$thrift$PushMessage$_Fields[_Fields.PACKAGE_NAME.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
        }
    }

    public enum _Fields implements TFieldIdEnum {
        TO((short) 1, PushServiceConstants.EXTRA_TO),
        ID((short) 2, Constants.JSON_TAG_ID),
        APP_ID((short) 3, "appId"),
        PAYLOAD((short) 4, PushServiceConstants.EXTENSION_ELEMENT_PAYLOAD),
        CREATE_AT((short) 5, "createAt"),
        TTL((short) 6, "ttl"),
        COLLAPSE_KEY((short) 7, "collapseKey"),
        PACKAGE_NAME((short) 8, "packageName");
        
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
                case PushMessage.__TTL_ISSET_ID /*1*/:
                    return TO;
                case TTransportException.ALREADY_OPEN /*2*/:
                    return ID;
                case TTransportException.TIMED_OUT /*3*/:
                    return APP_ID;
                case TTransportException.END_OF_FILE /*4*/:
                    return PAYLOAD;
                case TProtocolException.NOT_IMPLEMENTED /*5*/:
                    return CREATE_AT;
                case TApplicationException.INTERNAL_ERROR /*6*/:
                    return TTL;
                case TApplicationException.PROTOCOL_ERROR /*7*/:
                    return COLLAPSE_KEY;
                case PushConstants.ERROR_RECEIVE_TIMEOUT /*8*/:
                    return PACKAGE_NAME;
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
        STRUCT_DESC = new TStruct("PushMessage");
        TO_FIELD_DESC = new TField(PushServiceConstants.EXTRA_TO, TType.STRUCT, (short) 1);
        ID_FIELD_DESC = new TField(Constants.JSON_TAG_ID, TType.STRING, (short) 2);
        APP_ID_FIELD_DESC = new TField("appId", TType.STRING, (short) 3);
        PAYLOAD_FIELD_DESC = new TField(PushServiceConstants.EXTENSION_ELEMENT_PAYLOAD, TType.STRING, (short) 4);
        CREATE_AT_FIELD_DESC = new TField("createAt", (byte) 10, (short) 5);
        TTL_FIELD_DESC = new TField("ttl", (byte) 10, (short) 6);
        COLLAPSE_KEY_FIELD_DESC = new TField("collapseKey", TType.STRING, (short) 7);
        PACKAGE_NAME_FIELD_DESC = new TField("packageName", TType.STRING, (short) 8);
        Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
        tmpMap.put(_Fields.TO, new FieldMetaData(PushServiceConstants.EXTRA_TO, (byte) 2, new StructMetaData(TType.STRUCT, Target.class)));
        tmpMap.put(_Fields.ID, new FieldMetaData(Constants.JSON_TAG_ID, (byte) 1, new FieldValueMetaData(TType.STRING)));
        tmpMap.put(_Fields.APP_ID, new FieldMetaData("appId", (byte) 1, new FieldValueMetaData(TType.STRING)));
        tmpMap.put(_Fields.PAYLOAD, new FieldMetaData(PushServiceConstants.EXTENSION_ELEMENT_PAYLOAD, (byte) 1, new FieldValueMetaData(TType.STRING)));
        tmpMap.put(_Fields.CREATE_AT, new FieldMetaData("createAt", (byte) 2, new FieldValueMetaData((byte) 10)));
        tmpMap.put(_Fields.TTL, new FieldMetaData("ttl", (byte) 2, new FieldValueMetaData((byte) 10)));
        tmpMap.put(_Fields.COLLAPSE_KEY, new FieldMetaData("collapseKey", (byte) 2, new FieldValueMetaData(TType.STRING)));
        tmpMap.put(_Fields.PACKAGE_NAME, new FieldMetaData("packageName", (byte) 2, new FieldValueMetaData(TType.STRING)));
        metaDataMap = Collections.unmodifiableMap(tmpMap);
        FieldMetaData.addStructMetaDataMap(PushMessage.class, metaDataMap);
    }

    public PushMessage() {
        this.__isset_bit_vector = new BitSet(2);
    }

    public PushMessage(String id, String appId, String payload) {
        this();
        this.id = id;
        this.appId = appId;
        this.payload = payload;
    }

    public PushMessage(PushMessage other) {
        this.__isset_bit_vector = new BitSet(2);
        this.__isset_bit_vector.clear();
        this.__isset_bit_vector.or(other.__isset_bit_vector);
        if (other.isSetTo()) {
            this.to = new Target(other.to);
        }
        if (other.isSetId()) {
            this.id = other.id;
        }
        if (other.isSetAppId()) {
            this.appId = other.appId;
        }
        if (other.isSetPayload()) {
            this.payload = other.payload;
        }
        this.createAt = other.createAt;
        this.ttl = other.ttl;
        if (other.isSetCollapseKey()) {
            this.collapseKey = other.collapseKey;
        }
        if (other.isSetPackageName()) {
            this.packageName = other.packageName;
        }
    }

    public PushMessage deepCopy() {
        return new PushMessage(this);
    }

    public void clear() {
        this.to = null;
        this.id = null;
        this.appId = null;
        this.payload = null;
        setCreateAtIsSet(false);
        this.createAt = 0;
        setTtlIsSet(false);
        this.ttl = 0;
        this.collapseKey = null;
        this.packageName = null;
    }

    public Target getTo() {
        return this.to;
    }

    public PushMessage setTo(Target to) {
        this.to = to;
        return this;
    }

    public void unsetTo() {
        this.to = null;
    }

    public boolean isSetTo() {
        return this.to != null;
    }

    public void setToIsSet(boolean value) {
        if (!value) {
            this.to = null;
        }
    }

    public String getId() {
        return this.id;
    }

    public PushMessage setId(String id) {
        this.id = id;
        return this;
    }

    public void unsetId() {
        this.id = null;
    }

    public boolean isSetId() {
        return this.id != null;
    }

    public void setIdIsSet(boolean value) {
        if (!value) {
            this.id = null;
        }
    }

    public String getAppId() {
        return this.appId;
    }

    public PushMessage setAppId(String appId) {
        this.appId = appId;
        return this;
    }

    public void unsetAppId() {
        this.appId = null;
    }

    public boolean isSetAppId() {
        return this.appId != null;
    }

    public void setAppIdIsSet(boolean value) {
        if (!value) {
            this.appId = null;
        }
    }

    public String getPayload() {
        return this.payload;
    }

    public PushMessage setPayload(String payload) {
        this.payload = payload;
        return this;
    }

    public void unsetPayload() {
        this.payload = null;
    }

    public boolean isSetPayload() {
        return this.payload != null;
    }

    public void setPayloadIsSet(boolean value) {
        if (!value) {
            this.payload = null;
        }
    }

    public long getCreateAt() {
        return this.createAt;
    }

    public PushMessage setCreateAt(long createAt) {
        this.createAt = createAt;
        setCreateAtIsSet(true);
        return this;
    }

    public void unsetCreateAt() {
        this.__isset_bit_vector.clear(__CREATEAT_ISSET_ID);
    }

    public boolean isSetCreateAt() {
        return this.__isset_bit_vector.get(__CREATEAT_ISSET_ID);
    }

    public void setCreateAtIsSet(boolean value) {
        this.__isset_bit_vector.set(__CREATEAT_ISSET_ID, value);
    }

    public long getTtl() {
        return this.ttl;
    }

    public PushMessage setTtl(long ttl) {
        this.ttl = ttl;
        setTtlIsSet(true);
        return this;
    }

    public void unsetTtl() {
        this.__isset_bit_vector.clear(__TTL_ISSET_ID);
    }

    public boolean isSetTtl() {
        return this.__isset_bit_vector.get(__TTL_ISSET_ID);
    }

    public void setTtlIsSet(boolean value) {
        this.__isset_bit_vector.set(__TTL_ISSET_ID, value);
    }

    public String getCollapseKey() {
        return this.collapseKey;
    }

    public PushMessage setCollapseKey(String collapseKey) {
        this.collapseKey = collapseKey;
        return this;
    }

    public void unsetCollapseKey() {
        this.collapseKey = null;
    }

    public boolean isSetCollapseKey() {
        return this.collapseKey != null;
    }

    public void setCollapseKeyIsSet(boolean value) {
        if (!value) {
            this.collapseKey = null;
        }
    }

    public String getPackageName() {
        return this.packageName;
    }

    public PushMessage setPackageName(String packageName) {
        this.packageName = packageName;
        return this;
    }

    public void unsetPackageName() {
        this.packageName = null;
    }

    public boolean isSetPackageName() {
        return this.packageName != null;
    }

    public void setPackageNameIsSet(boolean value) {
        if (!value) {
            this.packageName = null;
        }
    }

    public void setFieldValue(_Fields field, Object value) {
        switch (AnonymousClass1.$SwitchMap$com$xiaomi$xmpush$thrift$PushMessage$_Fields[field.ordinal()]) {
            case __TTL_ISSET_ID /*1*/:
                if (value == null) {
                    unsetTo();
                    return;
                } else {
                    setTo((Target) value);
                    return;
                }
            case TTransportException.ALREADY_OPEN /*2*/:
                if (value == null) {
                    unsetId();
                    return;
                } else {
                    setId((String) value);
                    return;
                }
            case TTransportException.TIMED_OUT /*3*/:
                if (value == null) {
                    unsetAppId();
                    return;
                } else {
                    setAppId((String) value);
                    return;
                }
            case TTransportException.END_OF_FILE /*4*/:
                if (value == null) {
                    unsetPayload();
                    return;
                } else {
                    setPayload((String) value);
                    return;
                }
            case TProtocolException.NOT_IMPLEMENTED /*5*/:
                if (value == null) {
                    unsetCreateAt();
                    return;
                } else {
                    setCreateAt(((Long) value).longValue());
                    return;
                }
            case TApplicationException.INTERNAL_ERROR /*6*/:
                if (value == null) {
                    unsetTtl();
                    return;
                } else {
                    setTtl(((Long) value).longValue());
                    return;
                }
            case TApplicationException.PROTOCOL_ERROR /*7*/:
                if (value == null) {
                    unsetCollapseKey();
                    return;
                } else {
                    setCollapseKey((String) value);
                    return;
                }
            case PushConstants.ERROR_RECEIVE_TIMEOUT /*8*/:
                if (value == null) {
                    unsetPackageName();
                    return;
                } else {
                    setPackageName((String) value);
                    return;
                }
            default:
                return;
        }
    }

    public Object getFieldValue(_Fields field) {
        switch (AnonymousClass1.$SwitchMap$com$xiaomi$xmpush$thrift$PushMessage$_Fields[field.ordinal()]) {
            case __TTL_ISSET_ID /*1*/:
                return getTo();
            case TTransportException.ALREADY_OPEN /*2*/:
                return getId();
            case TTransportException.TIMED_OUT /*3*/:
                return getAppId();
            case TTransportException.END_OF_FILE /*4*/:
                return getPayload();
            case TProtocolException.NOT_IMPLEMENTED /*5*/:
                return new Long(getCreateAt());
            case TApplicationException.INTERNAL_ERROR /*6*/:
                return new Long(getTtl());
            case TApplicationException.PROTOCOL_ERROR /*7*/:
                return getCollapseKey();
            case PushConstants.ERROR_RECEIVE_TIMEOUT /*8*/:
                return getPackageName();
            default:
                throw new IllegalStateException();
        }
    }

    public boolean isSet(_Fields field) {
        if (field == null) {
            throw new IllegalArgumentException();
        }
        switch (AnonymousClass1.$SwitchMap$com$xiaomi$xmpush$thrift$PushMessage$_Fields[field.ordinal()]) {
            case __TTL_ISSET_ID /*1*/:
                return isSetTo();
            case TTransportException.ALREADY_OPEN /*2*/:
                return isSetId();
            case TTransportException.TIMED_OUT /*3*/:
                return isSetAppId();
            case TTransportException.END_OF_FILE /*4*/:
                return isSetPayload();
            case TProtocolException.NOT_IMPLEMENTED /*5*/:
                return isSetCreateAt();
            case TApplicationException.INTERNAL_ERROR /*6*/:
                return isSetTtl();
            case TApplicationException.PROTOCOL_ERROR /*7*/:
                return isSetCollapseKey();
            case PushConstants.ERROR_RECEIVE_TIMEOUT /*8*/:
                return isSetPackageName();
            default:
                throw new IllegalStateException();
        }
    }

    public boolean equals(Object that) {
        if (that != null && (that instanceof PushMessage)) {
            return equals((PushMessage) that);
        }
        return false;
    }

    public boolean equals(PushMessage that) {
        if (that == null) {
            return false;
        }
        boolean this_present_to = isSetTo();
        boolean that_present_to = that.isSetTo();
        if (this_present_to || that_present_to) {
            if (!this_present_to || !that_present_to) {
                return false;
            }
            if (!this.to.equals(that.to)) {
                return false;
            }
        }
        boolean this_present_id = isSetId();
        boolean that_present_id = that.isSetId();
        if (this_present_id || that_present_id) {
            if (!this_present_id || !that_present_id) {
                return false;
            }
            if (!this.id.equals(that.id)) {
                return false;
            }
        }
        boolean this_present_appId = isSetAppId();
        boolean that_present_appId = that.isSetAppId();
        if (this_present_appId || that_present_appId) {
            if (!this_present_appId || !that_present_appId) {
                return false;
            }
            if (!this.appId.equals(that.appId)) {
                return false;
            }
        }
        boolean this_present_payload = isSetPayload();
        boolean that_present_payload = that.isSetPayload();
        if (this_present_payload || that_present_payload) {
            if (!this_present_payload || !that_present_payload) {
                return false;
            }
            if (!this.payload.equals(that.payload)) {
                return false;
            }
        }
        boolean this_present_createAt = isSetCreateAt();
        boolean that_present_createAt = that.isSetCreateAt();
        if (this_present_createAt || that_present_createAt) {
            if (!this_present_createAt || !that_present_createAt) {
                return false;
            }
            if (this.createAt != that.createAt) {
                return false;
            }
        }
        boolean this_present_ttl = isSetTtl();
        boolean that_present_ttl = that.isSetTtl();
        if (this_present_ttl || that_present_ttl) {
            if (!this_present_ttl || !that_present_ttl) {
                return false;
            }
            if (this.ttl != that.ttl) {
                return false;
            }
        }
        boolean this_present_collapseKey = isSetCollapseKey();
        boolean that_present_collapseKey = that.isSetCollapseKey();
        if (this_present_collapseKey || that_present_collapseKey) {
            if (!this_present_collapseKey || !that_present_collapseKey) {
                return false;
            }
            if (!this.collapseKey.equals(that.collapseKey)) {
                return false;
            }
        }
        boolean this_present_packageName = isSetPackageName();
        boolean that_present_packageName = that.isSetPackageName();
        if (this_present_packageName || that_present_packageName) {
            if (!this_present_packageName || !that_present_packageName) {
                return false;
            }
            if (!this.packageName.equals(that.packageName)) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        return __CREATEAT_ISSET_ID;
    }

    public int compareTo(PushMessage other) {
        if (!getClass().equals(other.getClass())) {
            return getClass().getName().compareTo(other.getClass().getName());
        }
        PushMessage typedOther = other;
        int lastComparison = Boolean.valueOf(isSetTo()).compareTo(Boolean.valueOf(typedOther.isSetTo()));
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetTo()) {
            lastComparison = TBaseHelper.compareTo(this.to, typedOther.to);
            if (lastComparison != 0) {
                return lastComparison;
            }
        }
        lastComparison = Boolean.valueOf(isSetId()).compareTo(Boolean.valueOf(typedOther.isSetId()));
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetId()) {
            lastComparison = TBaseHelper.compareTo(this.id, typedOther.id);
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
        lastComparison = Boolean.valueOf(isSetPayload()).compareTo(Boolean.valueOf(typedOther.isSetPayload()));
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetPayload()) {
            lastComparison = TBaseHelper.compareTo(this.payload, typedOther.payload);
            if (lastComparison != 0) {
                return lastComparison;
            }
        }
        lastComparison = Boolean.valueOf(isSetCreateAt()).compareTo(Boolean.valueOf(typedOther.isSetCreateAt()));
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetCreateAt()) {
            lastComparison = TBaseHelper.compareTo(this.createAt, typedOther.createAt);
            if (lastComparison != 0) {
                return lastComparison;
            }
        }
        lastComparison = Boolean.valueOf(isSetTtl()).compareTo(Boolean.valueOf(typedOther.isSetTtl()));
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetTtl()) {
            lastComparison = TBaseHelper.compareTo(this.ttl, typedOther.ttl);
            if (lastComparison != 0) {
                return lastComparison;
            }
        }
        lastComparison = Boolean.valueOf(isSetCollapseKey()).compareTo(Boolean.valueOf(typedOther.isSetCollapseKey()));
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetCollapseKey()) {
            lastComparison = TBaseHelper.compareTo(this.collapseKey, typedOther.collapseKey);
            if (lastComparison != 0) {
                return lastComparison;
            }
        }
        lastComparison = Boolean.valueOf(isSetPackageName()).compareTo(Boolean.valueOf(typedOther.isSetPackageName()));
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetPackageName()) {
            lastComparison = TBaseHelper.compareTo(this.packageName, typedOther.packageName);
            if (lastComparison != 0) {
                return lastComparison;
            }
        }
        return __CREATEAT_ISSET_ID;
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
                case __TTL_ISSET_ID /*1*/:
                    if (field.type != TType.STRUCT) {
                        TProtocolUtil.skip(iprot, field.type);
                        break;
                    }
                    this.to = new Target();
                    this.to.read(iprot);
                    break;
                case TTransportException.ALREADY_OPEN /*2*/:
                    if (field.type != TType.STRING) {
                        TProtocolUtil.skip(iprot, field.type);
                        break;
                    } else {
                        this.id = iprot.readString();
                        break;
                    }
                case TTransportException.TIMED_OUT /*3*/:
                    if (field.type != TType.STRING) {
                        TProtocolUtil.skip(iprot, field.type);
                        break;
                    } else {
                        this.appId = iprot.readString();
                        break;
                    }
                case TTransportException.END_OF_FILE /*4*/:
                    if (field.type != TType.STRING) {
                        TProtocolUtil.skip(iprot, field.type);
                        break;
                    } else {
                        this.payload = iprot.readString();
                        break;
                    }
                case TProtocolException.NOT_IMPLEMENTED /*5*/:
                    if (field.type != (byte) 10) {
                        TProtocolUtil.skip(iprot, field.type);
                        break;
                    }
                    this.createAt = iprot.readI64();
                    setCreateAtIsSet(true);
                    break;
                case TApplicationException.INTERNAL_ERROR /*6*/:
                    if (field.type != (byte) 10) {
                        TProtocolUtil.skip(iprot, field.type);
                        break;
                    }
                    this.ttl = iprot.readI64();
                    setTtlIsSet(true);
                    break;
                case TApplicationException.PROTOCOL_ERROR /*7*/:
                    if (field.type != TType.STRING) {
                        TProtocolUtil.skip(iprot, field.type);
                        break;
                    } else {
                        this.collapseKey = iprot.readString();
                        break;
                    }
                case PushConstants.ERROR_RECEIVE_TIMEOUT /*8*/:
                    if (field.type != TType.STRING) {
                        TProtocolUtil.skip(iprot, field.type);
                        break;
                    } else {
                        this.packageName = iprot.readString();
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
        if (this.to != null && isSetTo()) {
            oprot.writeFieldBegin(TO_FIELD_DESC);
            this.to.write(oprot);
            oprot.writeFieldEnd();
        }
        if (this.id != null) {
            oprot.writeFieldBegin(ID_FIELD_DESC);
            oprot.writeString(this.id);
            oprot.writeFieldEnd();
        }
        if (this.appId != null) {
            oprot.writeFieldBegin(APP_ID_FIELD_DESC);
            oprot.writeString(this.appId);
            oprot.writeFieldEnd();
        }
        if (this.payload != null) {
            oprot.writeFieldBegin(PAYLOAD_FIELD_DESC);
            oprot.writeString(this.payload);
            oprot.writeFieldEnd();
        }
        if (isSetCreateAt()) {
            oprot.writeFieldBegin(CREATE_AT_FIELD_DESC);
            oprot.writeI64(this.createAt);
            oprot.writeFieldEnd();
        }
        if (isSetTtl()) {
            oprot.writeFieldBegin(TTL_FIELD_DESC);
            oprot.writeI64(this.ttl);
            oprot.writeFieldEnd();
        }
        if (this.collapseKey != null && isSetCollapseKey()) {
            oprot.writeFieldBegin(COLLAPSE_KEY_FIELD_DESC);
            oprot.writeString(this.collapseKey);
            oprot.writeFieldEnd();
        }
        if (this.packageName != null && isSetPackageName()) {
            oprot.writeFieldBegin(PACKAGE_NAME_FIELD_DESC);
            oprot.writeString(this.packageName);
            oprot.writeFieldEnd();
        }
        oprot.writeFieldStop();
        oprot.writeStructEnd();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("PushMessage(");
        boolean first = true;
        if (isSetTo()) {
            sb.append("to:");
            if (this.to == null) {
                sb.append("null");
            } else {
                sb.append(this.to);
            }
            first = false;
        }
        if (!first) {
            sb.append(", ");
        }
        sb.append("id:");
        if (this.id == null) {
            sb.append("null");
        } else {
            sb.append(this.id);
        }
        if (!false) {
            sb.append(", ");
        }
        sb.append("appId:");
        if (this.appId == null) {
            sb.append("null");
        } else {
            sb.append(this.appId);
        }
        if (!false) {
            sb.append(", ");
        }
        sb.append("payload:");
        if (this.payload == null) {
            sb.append("null");
        } else {
            sb.append(this.payload);
        }
        first = false;
        if (isSetCreateAt()) {
            if (__CREATEAT_ISSET_ID == null) {
                sb.append(", ");
            }
            sb.append("createAt:");
            sb.append(this.createAt);
            first = false;
        }
        if (isSetTtl()) {
            if (!first) {
                sb.append(", ");
            }
            sb.append("ttl:");
            sb.append(this.ttl);
            first = false;
        }
        if (isSetCollapseKey()) {
            if (!first) {
                sb.append(", ");
            }
            sb.append("collapseKey:");
            if (this.collapseKey == null) {
                sb.append("null");
            } else {
                sb.append(this.collapseKey);
            }
            first = false;
        }
        if (isSetPackageName()) {
            if (!first) {
                sb.append(", ");
            }
            sb.append("packageName:");
            if (this.packageName == null) {
                sb.append("null");
            } else {
                sb.append(this.packageName);
            }
        }
        sb.append(")");
        return sb.toString();
    }

    public void validate() throws TException {
        if (this.id == null) {
            throw new TProtocolException("Required field 'id' was not present! Struct: " + toString());
        } else if (this.appId == null) {
            throw new TProtocolException("Required field 'appId' was not present! Struct: " + toString());
        } else if (this.payload == null) {
            throw new TProtocolException("Required field 'payload' was not present! Struct: " + toString());
        }
    }
}
