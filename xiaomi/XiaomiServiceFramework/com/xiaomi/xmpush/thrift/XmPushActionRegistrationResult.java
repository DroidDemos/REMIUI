package com.xiaomi.xmpush.thrift;

import com.xiaomi.push.service.PushConstants;
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

public class XmPushActionRegistrationResult implements TBase<XmPushActionRegistrationResult, _Fields>, Serializable, Cloneable {
    private static final TField APP_ID_FIELD_DESC;
    private static final TField DEBUG_FIELD_DESC;
    private static final TField ERROR_CODE_FIELD_DESC;
    private static final TField ID_FIELD_DESC;
    private static final TField PACKAGE_NAME_FIELD_DESC;
    private static final TField REASON_FIELD_DESC;
    private static final TField REG_ID_FIELD_DESC;
    private static final TField REG_SECRET_FIELD_DESC;
    private static final TField REQUEST_FIELD_DESC;
    private static final TStruct STRUCT_DESC;
    private static final TField TARGET_FIELD_DESC;
    private static final int __ERRORCODE_ISSET_ID = 0;
    public static final Map<_Fields, FieldMetaData> metaDataMap;
    private BitSet __isset_bit_vector;
    public String appId;
    public String debug;
    public long errorCode;
    public String id;
    public String packageName;
    public String reason;
    public String regId;
    public String regSecret;
    public XmPushActionRegistration request;
    public Target target;

    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$xiaomi$xmpush$thrift$XmPushActionRegistrationResult$_Fields;

        static {
            $SwitchMap$com$xiaomi$xmpush$thrift$XmPushActionRegistrationResult$_Fields = new int[_Fields.values().length];
            try {
                $SwitchMap$com$xiaomi$xmpush$thrift$XmPushActionRegistrationResult$_Fields[_Fields.DEBUG.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$xiaomi$xmpush$thrift$XmPushActionRegistrationResult$_Fields[_Fields.TARGET.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$xiaomi$xmpush$thrift$XmPushActionRegistrationResult$_Fields[_Fields.ID.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$xiaomi$xmpush$thrift$XmPushActionRegistrationResult$_Fields[_Fields.APP_ID.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$xiaomi$xmpush$thrift$XmPushActionRegistrationResult$_Fields[_Fields.REQUEST.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$xiaomi$xmpush$thrift$XmPushActionRegistrationResult$_Fields[_Fields.ERROR_CODE.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$com$xiaomi$xmpush$thrift$XmPushActionRegistrationResult$_Fields[_Fields.REASON.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$com$xiaomi$xmpush$thrift$XmPushActionRegistrationResult$_Fields[_Fields.REG_ID.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                $SwitchMap$com$xiaomi$xmpush$thrift$XmPushActionRegistrationResult$_Fields[_Fields.REG_SECRET.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                $SwitchMap$com$xiaomi$xmpush$thrift$XmPushActionRegistrationResult$_Fields[_Fields.PACKAGE_NAME.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
        }
    }

    public enum _Fields implements TFieldIdEnum {
        DEBUG((short) 1, "debug"),
        TARGET((short) 2, "target"),
        ID((short) 3, Constants.JSON_TAG_ID),
        APP_ID((short) 4, "appId"),
        REQUEST((short) 5, "request"),
        ERROR_CODE((short) 6, "errorCode"),
        REASON((short) 7, "reason"),
        REG_ID((short) 8, "regId"),
        REG_SECRET((short) 9, "regSecret"),
        PACKAGE_NAME((short) 10, "packageName");
        
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
                    return DEBUG;
                case TTransportException.ALREADY_OPEN /*2*/:
                    return TARGET;
                case TTransportException.TIMED_OUT /*3*/:
                    return ID;
                case TTransportException.END_OF_FILE /*4*/:
                    return APP_ID;
                case TProtocolException.NOT_IMPLEMENTED /*5*/:
                    return REQUEST;
                case TApplicationException.INTERNAL_ERROR /*6*/:
                    return ERROR_CODE;
                case TApplicationException.PROTOCOL_ERROR /*7*/:
                    return REASON;
                case PushConstants.ERROR_RECEIVE_TIMEOUT /*8*/:
                    return REG_ID;
                case PushConstants.ERROR_READ_ERROR /*9*/:
                    return REG_SECRET;
                case Constants.VALUE_MAX_FAILEDCOUNT /*10*/:
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
        STRUCT_DESC = new TStruct("XmPushActionRegistrationResult");
        DEBUG_FIELD_DESC = new TField("debug", TType.STRING, (short) 1);
        TARGET_FIELD_DESC = new TField("target", TType.STRUCT, (short) 2);
        ID_FIELD_DESC = new TField(Constants.JSON_TAG_ID, TType.STRING, (short) 3);
        APP_ID_FIELD_DESC = new TField("appId", TType.STRING, (short) 4);
        REQUEST_FIELD_DESC = new TField("request", TType.STRUCT, (short) 5);
        ERROR_CODE_FIELD_DESC = new TField("errorCode", (byte) 10, (short) 6);
        REASON_FIELD_DESC = new TField("reason", TType.STRING, (short) 7);
        REG_ID_FIELD_DESC = new TField("regId", TType.STRING, (short) 8);
        REG_SECRET_FIELD_DESC = new TField("regSecret", TType.STRING, (short) 9);
        PACKAGE_NAME_FIELD_DESC = new TField("packageName", TType.STRING, (short) 10);
        Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
        tmpMap.put(_Fields.DEBUG, new FieldMetaData("debug", (byte) 2, new FieldValueMetaData(TType.STRING)));
        tmpMap.put(_Fields.TARGET, new FieldMetaData("target", (byte) 2, new StructMetaData(TType.STRUCT, Target.class)));
        tmpMap.put(_Fields.ID, new FieldMetaData(Constants.JSON_TAG_ID, (byte) 1, new FieldValueMetaData(TType.STRING)));
        tmpMap.put(_Fields.APP_ID, new FieldMetaData("appId", (byte) 1, new FieldValueMetaData(TType.STRING)));
        tmpMap.put(_Fields.REQUEST, new FieldMetaData("request", (byte) 2, new StructMetaData(TType.STRUCT, XmPushActionRegistration.class)));
        tmpMap.put(_Fields.ERROR_CODE, new FieldMetaData("errorCode", (byte) 1, new FieldValueMetaData((byte) 10)));
        tmpMap.put(_Fields.REASON, new FieldMetaData("reason", (byte) 2, new FieldValueMetaData(TType.STRING)));
        tmpMap.put(_Fields.REG_ID, new FieldMetaData("regId", (byte) 2, new FieldValueMetaData(TType.STRING)));
        tmpMap.put(_Fields.REG_SECRET, new FieldMetaData("regSecret", (byte) 2, new FieldValueMetaData(TType.STRING)));
        tmpMap.put(_Fields.PACKAGE_NAME, new FieldMetaData("packageName", (byte) 2, new FieldValueMetaData(TType.STRING)));
        metaDataMap = Collections.unmodifiableMap(tmpMap);
        FieldMetaData.addStructMetaDataMap(XmPushActionRegistrationResult.class, metaDataMap);
    }

    public XmPushActionRegistrationResult() {
        this.__isset_bit_vector = new BitSet(1);
    }

    public XmPushActionRegistrationResult(String id, String appId, long errorCode) {
        this();
        this.id = id;
        this.appId = appId;
        this.errorCode = errorCode;
        setErrorCodeIsSet(true);
    }

    public XmPushActionRegistrationResult(XmPushActionRegistrationResult other) {
        this.__isset_bit_vector = new BitSet(1);
        this.__isset_bit_vector.clear();
        this.__isset_bit_vector.or(other.__isset_bit_vector);
        if (other.isSetDebug()) {
            this.debug = other.debug;
        }
        if (other.isSetTarget()) {
            this.target = new Target(other.target);
        }
        if (other.isSetId()) {
            this.id = other.id;
        }
        if (other.isSetAppId()) {
            this.appId = other.appId;
        }
        if (other.isSetRequest()) {
            this.request = new XmPushActionRegistration(other.request);
        }
        this.errorCode = other.errorCode;
        if (other.isSetReason()) {
            this.reason = other.reason;
        }
        if (other.isSetRegId()) {
            this.regId = other.regId;
        }
        if (other.isSetRegSecret()) {
            this.regSecret = other.regSecret;
        }
        if (other.isSetPackageName()) {
            this.packageName = other.packageName;
        }
    }

    public XmPushActionRegistrationResult deepCopy() {
        return new XmPushActionRegistrationResult(this);
    }

    public void clear() {
        this.debug = null;
        this.target = null;
        this.id = null;
        this.appId = null;
        this.request = null;
        setErrorCodeIsSet(false);
        this.errorCode = 0;
        this.reason = null;
        this.regId = null;
        this.regSecret = null;
        this.packageName = null;
    }

    public String getDebug() {
        return this.debug;
    }

    public XmPushActionRegistrationResult setDebug(String debug) {
        this.debug = debug;
        return this;
    }

    public void unsetDebug() {
        this.debug = null;
    }

    public boolean isSetDebug() {
        return this.debug != null;
    }

    public void setDebugIsSet(boolean value) {
        if (!value) {
            this.debug = null;
        }
    }

    public Target getTarget() {
        return this.target;
    }

    public XmPushActionRegistrationResult setTarget(Target target) {
        this.target = target;
        return this;
    }

    public void unsetTarget() {
        this.target = null;
    }

    public boolean isSetTarget() {
        return this.target != null;
    }

    public void setTargetIsSet(boolean value) {
        if (!value) {
            this.target = null;
        }
    }

    public String getId() {
        return this.id;
    }

    public XmPushActionRegistrationResult setId(String id) {
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

    public XmPushActionRegistrationResult setAppId(String appId) {
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

    public XmPushActionRegistration getRequest() {
        return this.request;
    }

    public XmPushActionRegistrationResult setRequest(XmPushActionRegistration request) {
        this.request = request;
        return this;
    }

    public void unsetRequest() {
        this.request = null;
    }

    public boolean isSetRequest() {
        return this.request != null;
    }

    public void setRequestIsSet(boolean value) {
        if (!value) {
            this.request = null;
        }
    }

    public long getErrorCode() {
        return this.errorCode;
    }

    public XmPushActionRegistrationResult setErrorCode(long errorCode) {
        this.errorCode = errorCode;
        setErrorCodeIsSet(true);
        return this;
    }

    public void unsetErrorCode() {
        this.__isset_bit_vector.clear(0);
    }

    public boolean isSetErrorCode() {
        return this.__isset_bit_vector.get(0);
    }

    public void setErrorCodeIsSet(boolean value) {
        this.__isset_bit_vector.set(0, value);
    }

    public String getReason() {
        return this.reason;
    }

    public XmPushActionRegistrationResult setReason(String reason) {
        this.reason = reason;
        return this;
    }

    public void unsetReason() {
        this.reason = null;
    }

    public boolean isSetReason() {
        return this.reason != null;
    }

    public void setReasonIsSet(boolean value) {
        if (!value) {
            this.reason = null;
        }
    }

    public String getRegId() {
        return this.regId;
    }

    public XmPushActionRegistrationResult setRegId(String regId) {
        this.regId = regId;
        return this;
    }

    public void unsetRegId() {
        this.regId = null;
    }

    public boolean isSetRegId() {
        return this.regId != null;
    }

    public void setRegIdIsSet(boolean value) {
        if (!value) {
            this.regId = null;
        }
    }

    public String getRegSecret() {
        return this.regSecret;
    }

    public XmPushActionRegistrationResult setRegSecret(String regSecret) {
        this.regSecret = regSecret;
        return this;
    }

    public void unsetRegSecret() {
        this.regSecret = null;
    }

    public boolean isSetRegSecret() {
        return this.regSecret != null;
    }

    public void setRegSecretIsSet(boolean value) {
        if (!value) {
            this.regSecret = null;
        }
    }

    public String getPackageName() {
        return this.packageName;
    }

    public XmPushActionRegistrationResult setPackageName(String packageName) {
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
        switch (AnonymousClass1.$SwitchMap$com$xiaomi$xmpush$thrift$XmPushActionRegistrationResult$_Fields[field.ordinal()]) {
            case TTransportException.NOT_OPEN /*1*/:
                if (value == null) {
                    unsetDebug();
                    return;
                } else {
                    setDebug((String) value);
                    return;
                }
            case TTransportException.ALREADY_OPEN /*2*/:
                if (value == null) {
                    unsetTarget();
                    return;
                } else {
                    setTarget((Target) value);
                    return;
                }
            case TTransportException.TIMED_OUT /*3*/:
                if (value == null) {
                    unsetId();
                    return;
                } else {
                    setId((String) value);
                    return;
                }
            case TTransportException.END_OF_FILE /*4*/:
                if (value == null) {
                    unsetAppId();
                    return;
                } else {
                    setAppId((String) value);
                    return;
                }
            case TProtocolException.NOT_IMPLEMENTED /*5*/:
                if (value == null) {
                    unsetRequest();
                    return;
                } else {
                    setRequest((XmPushActionRegistration) value);
                    return;
                }
            case TApplicationException.INTERNAL_ERROR /*6*/:
                if (value == null) {
                    unsetErrorCode();
                    return;
                } else {
                    setErrorCode(((Long) value).longValue());
                    return;
                }
            case TApplicationException.PROTOCOL_ERROR /*7*/:
                if (value == null) {
                    unsetReason();
                    return;
                } else {
                    setReason((String) value);
                    return;
                }
            case PushConstants.ERROR_RECEIVE_TIMEOUT /*8*/:
                if (value == null) {
                    unsetRegId();
                    return;
                } else {
                    setRegId((String) value);
                    return;
                }
            case PushConstants.ERROR_READ_ERROR /*9*/:
                if (value == null) {
                    unsetRegSecret();
                    return;
                } else {
                    setRegSecret((String) value);
                    return;
                }
            case Constants.VALUE_MAX_FAILEDCOUNT /*10*/:
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
        switch (AnonymousClass1.$SwitchMap$com$xiaomi$xmpush$thrift$XmPushActionRegistrationResult$_Fields[field.ordinal()]) {
            case TTransportException.NOT_OPEN /*1*/:
                return getDebug();
            case TTransportException.ALREADY_OPEN /*2*/:
                return getTarget();
            case TTransportException.TIMED_OUT /*3*/:
                return getId();
            case TTransportException.END_OF_FILE /*4*/:
                return getAppId();
            case TProtocolException.NOT_IMPLEMENTED /*5*/:
                return getRequest();
            case TApplicationException.INTERNAL_ERROR /*6*/:
                return new Long(getErrorCode());
            case TApplicationException.PROTOCOL_ERROR /*7*/:
                return getReason();
            case PushConstants.ERROR_RECEIVE_TIMEOUT /*8*/:
                return getRegId();
            case PushConstants.ERROR_READ_ERROR /*9*/:
                return getRegSecret();
            case Constants.VALUE_MAX_FAILEDCOUNT /*10*/:
                return getPackageName();
            default:
                throw new IllegalStateException();
        }
    }

    public boolean isSet(_Fields field) {
        if (field == null) {
            throw new IllegalArgumentException();
        }
        switch (AnonymousClass1.$SwitchMap$com$xiaomi$xmpush$thrift$XmPushActionRegistrationResult$_Fields[field.ordinal()]) {
            case TTransportException.NOT_OPEN /*1*/:
                return isSetDebug();
            case TTransportException.ALREADY_OPEN /*2*/:
                return isSetTarget();
            case TTransportException.TIMED_OUT /*3*/:
                return isSetId();
            case TTransportException.END_OF_FILE /*4*/:
                return isSetAppId();
            case TProtocolException.NOT_IMPLEMENTED /*5*/:
                return isSetRequest();
            case TApplicationException.INTERNAL_ERROR /*6*/:
                return isSetErrorCode();
            case TApplicationException.PROTOCOL_ERROR /*7*/:
                return isSetReason();
            case PushConstants.ERROR_RECEIVE_TIMEOUT /*8*/:
                return isSetRegId();
            case PushConstants.ERROR_READ_ERROR /*9*/:
                return isSetRegSecret();
            case Constants.VALUE_MAX_FAILEDCOUNT /*10*/:
                return isSetPackageName();
            default:
                throw new IllegalStateException();
        }
    }

    public boolean equals(Object that) {
        if (that != null && (that instanceof XmPushActionRegistrationResult)) {
            return equals((XmPushActionRegistrationResult) that);
        }
        return false;
    }

    public boolean equals(XmPushActionRegistrationResult that) {
        if (that == null) {
            return false;
        }
        boolean this_present_debug = isSetDebug();
        boolean that_present_debug = that.isSetDebug();
        if (this_present_debug || that_present_debug) {
            if (!this_present_debug || !that_present_debug) {
                return false;
            }
            if (!this.debug.equals(that.debug)) {
                return false;
            }
        }
        boolean this_present_target = isSetTarget();
        boolean that_present_target = that.isSetTarget();
        if (this_present_target || that_present_target) {
            if (!this_present_target || !that_present_target) {
                return false;
            }
            if (!this.target.equals(that.target)) {
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
        boolean this_present_request = isSetRequest();
        boolean that_present_request = that.isSetRequest();
        if (this_present_request || that_present_request) {
            if (!this_present_request || !that_present_request) {
                return false;
            }
            if (!this.request.equals(that.request)) {
                return false;
            }
        }
        if (!(1 == null && 1 == null)) {
            if (1 == null || 1 == null) {
                return false;
            }
            if (this.errorCode != that.errorCode) {
                return false;
            }
        }
        boolean this_present_reason = isSetReason();
        boolean that_present_reason = that.isSetReason();
        if (this_present_reason || that_present_reason) {
            if (!this_present_reason || !that_present_reason) {
                return false;
            }
            if (!this.reason.equals(that.reason)) {
                return false;
            }
        }
        boolean this_present_regId = isSetRegId();
        boolean that_present_regId = that.isSetRegId();
        if (this_present_regId || that_present_regId) {
            if (!this_present_regId || !that_present_regId) {
                return false;
            }
            if (!this.regId.equals(that.regId)) {
                return false;
            }
        }
        boolean this_present_regSecret = isSetRegSecret();
        boolean that_present_regSecret = that.isSetRegSecret();
        if (this_present_regSecret || that_present_regSecret) {
            if (!this_present_regSecret || !that_present_regSecret) {
                return false;
            }
            if (!this.regSecret.equals(that.regSecret)) {
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
        return 0;
    }

    public int compareTo(XmPushActionRegistrationResult other) {
        if (!getClass().equals(other.getClass())) {
            return getClass().getName().compareTo(other.getClass().getName());
        }
        XmPushActionRegistrationResult typedOther = other;
        int lastComparison = Boolean.valueOf(isSetDebug()).compareTo(Boolean.valueOf(typedOther.isSetDebug()));
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetDebug()) {
            lastComparison = TBaseHelper.compareTo(this.debug, typedOther.debug);
            if (lastComparison != 0) {
                return lastComparison;
            }
        }
        lastComparison = Boolean.valueOf(isSetTarget()).compareTo(Boolean.valueOf(typedOther.isSetTarget()));
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetTarget()) {
            lastComparison = TBaseHelper.compareTo(this.target, typedOther.target);
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
        lastComparison = Boolean.valueOf(isSetRequest()).compareTo(Boolean.valueOf(typedOther.isSetRequest()));
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetRequest()) {
            lastComparison = TBaseHelper.compareTo(this.request, typedOther.request);
            if (lastComparison != 0) {
                return lastComparison;
            }
        }
        lastComparison = Boolean.valueOf(isSetErrorCode()).compareTo(Boolean.valueOf(typedOther.isSetErrorCode()));
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetErrorCode()) {
            lastComparison = TBaseHelper.compareTo(this.errorCode, typedOther.errorCode);
            if (lastComparison != 0) {
                return lastComparison;
            }
        }
        lastComparison = Boolean.valueOf(isSetReason()).compareTo(Boolean.valueOf(typedOther.isSetReason()));
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetReason()) {
            lastComparison = TBaseHelper.compareTo(this.reason, typedOther.reason);
            if (lastComparison != 0) {
                return lastComparison;
            }
        }
        lastComparison = Boolean.valueOf(isSetRegId()).compareTo(Boolean.valueOf(typedOther.isSetRegId()));
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetRegId()) {
            lastComparison = TBaseHelper.compareTo(this.regId, typedOther.regId);
            if (lastComparison != 0) {
                return lastComparison;
            }
        }
        lastComparison = Boolean.valueOf(isSetRegSecret()).compareTo(Boolean.valueOf(typedOther.isSetRegSecret()));
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetRegSecret()) {
            lastComparison = TBaseHelper.compareTo(this.regSecret, typedOther.regSecret);
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
                if (isSetErrorCode()) {
                    validate();
                    return;
                }
                throw new TProtocolException("Required field 'errorCode' was not found in serialized data! Struct: " + toString());
            }
            switch (field.id) {
                case TTransportException.NOT_OPEN /*1*/:
                    if (field.type != TType.STRING) {
                        TProtocolUtil.skip(iprot, field.type);
                        break;
                    } else {
                        this.debug = iprot.readString();
                        break;
                    }
                case TTransportException.ALREADY_OPEN /*2*/:
                    if (field.type != TType.STRUCT) {
                        TProtocolUtil.skip(iprot, field.type);
                        break;
                    }
                    this.target = new Target();
                    this.target.read(iprot);
                    break;
                case TTransportException.TIMED_OUT /*3*/:
                    if (field.type != TType.STRING) {
                        TProtocolUtil.skip(iprot, field.type);
                        break;
                    } else {
                        this.id = iprot.readString();
                        break;
                    }
                case TTransportException.END_OF_FILE /*4*/:
                    if (field.type != TType.STRING) {
                        TProtocolUtil.skip(iprot, field.type);
                        break;
                    } else {
                        this.appId = iprot.readString();
                        break;
                    }
                case TProtocolException.NOT_IMPLEMENTED /*5*/:
                    if (field.type != TType.STRUCT) {
                        TProtocolUtil.skip(iprot, field.type);
                        break;
                    }
                    this.request = new XmPushActionRegistration();
                    this.request.read(iprot);
                    break;
                case TApplicationException.INTERNAL_ERROR /*6*/:
                    if (field.type != (byte) 10) {
                        TProtocolUtil.skip(iprot, field.type);
                        break;
                    }
                    this.errorCode = iprot.readI64();
                    setErrorCodeIsSet(true);
                    break;
                case TApplicationException.PROTOCOL_ERROR /*7*/:
                    if (field.type != TType.STRING) {
                        TProtocolUtil.skip(iprot, field.type);
                        break;
                    } else {
                        this.reason = iprot.readString();
                        break;
                    }
                case PushConstants.ERROR_RECEIVE_TIMEOUT /*8*/:
                    if (field.type != TType.STRING) {
                        TProtocolUtil.skip(iprot, field.type);
                        break;
                    } else {
                        this.regId = iprot.readString();
                        break;
                    }
                case PushConstants.ERROR_READ_ERROR /*9*/:
                    if (field.type != TType.STRING) {
                        TProtocolUtil.skip(iprot, field.type);
                        break;
                    } else {
                        this.regSecret = iprot.readString();
                        break;
                    }
                case Constants.VALUE_MAX_FAILEDCOUNT /*10*/:
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
        if (this.debug != null && isSetDebug()) {
            oprot.writeFieldBegin(DEBUG_FIELD_DESC);
            oprot.writeString(this.debug);
            oprot.writeFieldEnd();
        }
        if (this.target != null && isSetTarget()) {
            oprot.writeFieldBegin(TARGET_FIELD_DESC);
            this.target.write(oprot);
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
        if (this.request != null && isSetRequest()) {
            oprot.writeFieldBegin(REQUEST_FIELD_DESC);
            this.request.write(oprot);
            oprot.writeFieldEnd();
        }
        oprot.writeFieldBegin(ERROR_CODE_FIELD_DESC);
        oprot.writeI64(this.errorCode);
        oprot.writeFieldEnd();
        if (this.reason != null && isSetReason()) {
            oprot.writeFieldBegin(REASON_FIELD_DESC);
            oprot.writeString(this.reason);
            oprot.writeFieldEnd();
        }
        if (this.regId != null && isSetRegId()) {
            oprot.writeFieldBegin(REG_ID_FIELD_DESC);
            oprot.writeString(this.regId);
            oprot.writeFieldEnd();
        }
        if (this.regSecret != null && isSetRegSecret()) {
            oprot.writeFieldBegin(REG_SECRET_FIELD_DESC);
            oprot.writeString(this.regSecret);
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
        StringBuilder sb = new StringBuilder("XmPushActionRegistrationResult(");
        boolean first = true;
        if (isSetDebug()) {
            sb.append("debug:");
            if (this.debug == null) {
                sb.append("null");
            } else {
                sb.append(this.debug);
            }
            first = false;
        }
        if (isSetTarget()) {
            if (!first) {
                sb.append(", ");
            }
            sb.append("target:");
            if (this.target == null) {
                sb.append("null");
            } else {
                sb.append(this.target);
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
        first = false;
        if (isSetRequest()) {
            if (null == null) {
                sb.append(", ");
            }
            sb.append("request:");
            if (this.request == null) {
                sb.append("null");
            } else {
                sb.append(this.request);
            }
            first = false;
        }
        if (!first) {
            sb.append(", ");
        }
        sb.append("errorCode:");
        sb.append(this.errorCode);
        first = false;
        if (isSetReason()) {
            if (null == null) {
                sb.append(", ");
            }
            sb.append("reason:");
            if (this.reason == null) {
                sb.append("null");
            } else {
                sb.append(this.reason);
            }
            first = false;
        }
        if (isSetRegId()) {
            if (!first) {
                sb.append(", ");
            }
            sb.append("regId:");
            if (this.regId == null) {
                sb.append("null");
            } else {
                sb.append(this.regId);
            }
            first = false;
        }
        if (isSetRegSecret()) {
            if (!first) {
                sb.append(", ");
            }
            sb.append("regSecret:");
            if (this.regSecret == null) {
                sb.append("null");
            } else {
                sb.append(this.regSecret);
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
        }
    }
}
