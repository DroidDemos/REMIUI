package com.xiaomi.xmpush.thrift;

import com.xiaomi.push.service.PushConstants;
import com.xiaomi.push.service.PushServiceConstants;
import com.xiaomi.xmsf.push.service.Constants;
import java.io.Serializable;
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

public class XmPushActionRegistration implements TBase<XmPushActionRegistration, _Fields>, Serializable, Cloneable {
    private static final TField ALIAS_NAME_FIELD_DESC;
    private static final TField APP_ID_FIELD_DESC;
    private static final TField APP_VERSION_FIELD_DESC;
    private static final TField DEBUG_FIELD_DESC;
    private static final TField DEVICE_ID_FIELD_DESC;
    private static final TField ID_FIELD_DESC;
    private static final TField PACKAGE_NAME_FIELD_DESC;
    private static final TField SDK_VERSION_FIELD_DESC;
    private static final TStruct STRUCT_DESC;
    private static final TField TARGET_FIELD_DESC;
    private static final TField TOKEN_FIELD_DESC;
    public static final Map<_Fields, FieldMetaData> metaDataMap;
    public String aliasName;
    public String appId;
    public String appVersion;
    public String debug;
    public String deviceId;
    public String id;
    public String packageName;
    public String sdkVersion;
    public Target target;
    public String token;

    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$xiaomi$xmpush$thrift$XmPushActionRegistration$_Fields;

        static {
            $SwitchMap$com$xiaomi$xmpush$thrift$XmPushActionRegistration$_Fields = new int[_Fields.values().length];
            try {
                $SwitchMap$com$xiaomi$xmpush$thrift$XmPushActionRegistration$_Fields[_Fields.DEBUG.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$xiaomi$xmpush$thrift$XmPushActionRegistration$_Fields[_Fields.TARGET.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$xiaomi$xmpush$thrift$XmPushActionRegistration$_Fields[_Fields.ID.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$xiaomi$xmpush$thrift$XmPushActionRegistration$_Fields[_Fields.APP_ID.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$xiaomi$xmpush$thrift$XmPushActionRegistration$_Fields[_Fields.APP_VERSION.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$xiaomi$xmpush$thrift$XmPushActionRegistration$_Fields[_Fields.PACKAGE_NAME.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$com$xiaomi$xmpush$thrift$XmPushActionRegistration$_Fields[_Fields.TOKEN.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$com$xiaomi$xmpush$thrift$XmPushActionRegistration$_Fields[_Fields.DEVICE_ID.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                $SwitchMap$com$xiaomi$xmpush$thrift$XmPushActionRegistration$_Fields[_Fields.ALIAS_NAME.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                $SwitchMap$com$xiaomi$xmpush$thrift$XmPushActionRegistration$_Fields[_Fields.SDK_VERSION.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
        }
    }

    public enum _Fields implements TFieldIdEnum {
        DEBUG((short) 1, "debug"),
        TARGET((short) 2, "target"),
        ID((short) 3, Constants.JSON_TAG_ID),
        APP_ID((short) 4, "appId"),
        APP_VERSION((short) 5, "appVersion"),
        PACKAGE_NAME((short) 6, "packageName"),
        TOKEN((short) 7, PushServiceConstants.EXTRA_TOKEN),
        DEVICE_ID((short) 8, "deviceId"),
        ALIAS_NAME((short) 9, "aliasName"),
        SDK_VERSION((short) 10, "sdkVersion");
        
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
                    return APP_VERSION;
                case TApplicationException.INTERNAL_ERROR /*6*/:
                    return PACKAGE_NAME;
                case TApplicationException.PROTOCOL_ERROR /*7*/:
                    return TOKEN;
                case PushConstants.ERROR_RECEIVE_TIMEOUT /*8*/:
                    return DEVICE_ID;
                case PushConstants.ERROR_READ_ERROR /*9*/:
                    return ALIAS_NAME;
                case Constants.VALUE_MAX_FAILEDCOUNT /*10*/:
                    return SDK_VERSION;
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
        STRUCT_DESC = new TStruct("XmPushActionRegistration");
        DEBUG_FIELD_DESC = new TField("debug", TType.STRING, (short) 1);
        TARGET_FIELD_DESC = new TField("target", TType.STRUCT, (short) 2);
        ID_FIELD_DESC = new TField(Constants.JSON_TAG_ID, TType.STRING, (short) 3);
        APP_ID_FIELD_DESC = new TField("appId", TType.STRING, (short) 4);
        APP_VERSION_FIELD_DESC = new TField("appVersion", TType.STRING, (short) 5);
        PACKAGE_NAME_FIELD_DESC = new TField("packageName", TType.STRING, (short) 6);
        TOKEN_FIELD_DESC = new TField(PushServiceConstants.EXTRA_TOKEN, TType.STRING, (short) 7);
        DEVICE_ID_FIELD_DESC = new TField("deviceId", TType.STRING, (short) 8);
        ALIAS_NAME_FIELD_DESC = new TField("aliasName", TType.STRING, (short) 9);
        SDK_VERSION_FIELD_DESC = new TField("sdkVersion", TType.STRING, (short) 10);
        Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
        tmpMap.put(_Fields.DEBUG, new FieldMetaData("debug", (byte) 2, new FieldValueMetaData(TType.STRING)));
        tmpMap.put(_Fields.TARGET, new FieldMetaData("target", (byte) 2, new StructMetaData(TType.STRUCT, Target.class)));
        tmpMap.put(_Fields.ID, new FieldMetaData(Constants.JSON_TAG_ID, (byte) 1, new FieldValueMetaData(TType.STRING)));
        tmpMap.put(_Fields.APP_ID, new FieldMetaData("appId", (byte) 1, new FieldValueMetaData(TType.STRING)));
        tmpMap.put(_Fields.APP_VERSION, new FieldMetaData("appVersion", (byte) 2, new FieldValueMetaData(TType.STRING)));
        tmpMap.put(_Fields.PACKAGE_NAME, new FieldMetaData("packageName", (byte) 2, new FieldValueMetaData(TType.STRING)));
        tmpMap.put(_Fields.TOKEN, new FieldMetaData(PushServiceConstants.EXTRA_TOKEN, (byte) 1, new FieldValueMetaData(TType.STRING)));
        tmpMap.put(_Fields.DEVICE_ID, new FieldMetaData("deviceId", (byte) 2, new FieldValueMetaData(TType.STRING)));
        tmpMap.put(_Fields.ALIAS_NAME, new FieldMetaData("aliasName", (byte) 2, new FieldValueMetaData(TType.STRING)));
        tmpMap.put(_Fields.SDK_VERSION, new FieldMetaData("sdkVersion", (byte) 2, new FieldValueMetaData(TType.STRING)));
        metaDataMap = Collections.unmodifiableMap(tmpMap);
        FieldMetaData.addStructMetaDataMap(XmPushActionRegistration.class, metaDataMap);
    }

    public XmPushActionRegistration(String id, String appId, String token) {
        this();
        this.id = id;
        this.appId = appId;
        this.token = token;
    }

    public XmPushActionRegistration(XmPushActionRegistration other) {
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
        if (other.isSetAppVersion()) {
            this.appVersion = other.appVersion;
        }
        if (other.isSetPackageName()) {
            this.packageName = other.packageName;
        }
        if (other.isSetToken()) {
            this.token = other.token;
        }
        if (other.isSetDeviceId()) {
            this.deviceId = other.deviceId;
        }
        if (other.isSetAliasName()) {
            this.aliasName = other.aliasName;
        }
        if (other.isSetSdkVersion()) {
            this.sdkVersion = other.sdkVersion;
        }
    }

    public XmPushActionRegistration deepCopy() {
        return new XmPushActionRegistration(this);
    }

    public void clear() {
        this.debug = null;
        this.target = null;
        this.id = null;
        this.appId = null;
        this.appVersion = null;
        this.packageName = null;
        this.token = null;
        this.deviceId = null;
        this.aliasName = null;
        this.sdkVersion = null;
    }

    public String getDebug() {
        return this.debug;
    }

    public XmPushActionRegistration setDebug(String debug) {
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

    public XmPushActionRegistration setTarget(Target target) {
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

    public XmPushActionRegistration setId(String id) {
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

    public XmPushActionRegistration setAppId(String appId) {
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

    public String getAppVersion() {
        return this.appVersion;
    }

    public XmPushActionRegistration setAppVersion(String appVersion) {
        this.appVersion = appVersion;
        return this;
    }

    public void unsetAppVersion() {
        this.appVersion = null;
    }

    public boolean isSetAppVersion() {
        return this.appVersion != null;
    }

    public void setAppVersionIsSet(boolean value) {
        if (!value) {
            this.appVersion = null;
        }
    }

    public String getPackageName() {
        return this.packageName;
    }

    public XmPushActionRegistration setPackageName(String packageName) {
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

    public String getToken() {
        return this.token;
    }

    public XmPushActionRegistration setToken(String token) {
        this.token = token;
        return this;
    }

    public void unsetToken() {
        this.token = null;
    }

    public boolean isSetToken() {
        return this.token != null;
    }

    public void setTokenIsSet(boolean value) {
        if (!value) {
            this.token = null;
        }
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public XmPushActionRegistration setDeviceId(String deviceId) {
        this.deviceId = deviceId;
        return this;
    }

    public void unsetDeviceId() {
        this.deviceId = null;
    }

    public boolean isSetDeviceId() {
        return this.deviceId != null;
    }

    public void setDeviceIdIsSet(boolean value) {
        if (!value) {
            this.deviceId = null;
        }
    }

    public String getAliasName() {
        return this.aliasName;
    }

    public XmPushActionRegistration setAliasName(String aliasName) {
        this.aliasName = aliasName;
        return this;
    }

    public void unsetAliasName() {
        this.aliasName = null;
    }

    public boolean isSetAliasName() {
        return this.aliasName != null;
    }

    public void setAliasNameIsSet(boolean value) {
        if (!value) {
            this.aliasName = null;
        }
    }

    public String getSdkVersion() {
        return this.sdkVersion;
    }

    public XmPushActionRegistration setSdkVersion(String sdkVersion) {
        this.sdkVersion = sdkVersion;
        return this;
    }

    public void unsetSdkVersion() {
        this.sdkVersion = null;
    }

    public boolean isSetSdkVersion() {
        return this.sdkVersion != null;
    }

    public void setSdkVersionIsSet(boolean value) {
        if (!value) {
            this.sdkVersion = null;
        }
    }

    public void setFieldValue(_Fields field, Object value) {
        switch (AnonymousClass1.$SwitchMap$com$xiaomi$xmpush$thrift$XmPushActionRegistration$_Fields[field.ordinal()]) {
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
                    unsetAppVersion();
                    return;
                } else {
                    setAppVersion((String) value);
                    return;
                }
            case TApplicationException.INTERNAL_ERROR /*6*/:
                if (value == null) {
                    unsetPackageName();
                    return;
                } else {
                    setPackageName((String) value);
                    return;
                }
            case TApplicationException.PROTOCOL_ERROR /*7*/:
                if (value == null) {
                    unsetToken();
                    return;
                } else {
                    setToken((String) value);
                    return;
                }
            case PushConstants.ERROR_RECEIVE_TIMEOUT /*8*/:
                if (value == null) {
                    unsetDeviceId();
                    return;
                } else {
                    setDeviceId((String) value);
                    return;
                }
            case PushConstants.ERROR_READ_ERROR /*9*/:
                if (value == null) {
                    unsetAliasName();
                    return;
                } else {
                    setAliasName((String) value);
                    return;
                }
            case Constants.VALUE_MAX_FAILEDCOUNT /*10*/:
                if (value == null) {
                    unsetSdkVersion();
                    return;
                } else {
                    setSdkVersion((String) value);
                    return;
                }
            default:
                return;
        }
    }

    public Object getFieldValue(_Fields field) {
        switch (AnonymousClass1.$SwitchMap$com$xiaomi$xmpush$thrift$XmPushActionRegistration$_Fields[field.ordinal()]) {
            case TTransportException.NOT_OPEN /*1*/:
                return getDebug();
            case TTransportException.ALREADY_OPEN /*2*/:
                return getTarget();
            case TTransportException.TIMED_OUT /*3*/:
                return getId();
            case TTransportException.END_OF_FILE /*4*/:
                return getAppId();
            case TProtocolException.NOT_IMPLEMENTED /*5*/:
                return getAppVersion();
            case TApplicationException.INTERNAL_ERROR /*6*/:
                return getPackageName();
            case TApplicationException.PROTOCOL_ERROR /*7*/:
                return getToken();
            case PushConstants.ERROR_RECEIVE_TIMEOUT /*8*/:
                return getDeviceId();
            case PushConstants.ERROR_READ_ERROR /*9*/:
                return getAliasName();
            case Constants.VALUE_MAX_FAILEDCOUNT /*10*/:
                return getSdkVersion();
            default:
                throw new IllegalStateException();
        }
    }

    public boolean isSet(_Fields field) {
        if (field == null) {
            throw new IllegalArgumentException();
        }
        switch (AnonymousClass1.$SwitchMap$com$xiaomi$xmpush$thrift$XmPushActionRegistration$_Fields[field.ordinal()]) {
            case TTransportException.NOT_OPEN /*1*/:
                return isSetDebug();
            case TTransportException.ALREADY_OPEN /*2*/:
                return isSetTarget();
            case TTransportException.TIMED_OUT /*3*/:
                return isSetId();
            case TTransportException.END_OF_FILE /*4*/:
                return isSetAppId();
            case TProtocolException.NOT_IMPLEMENTED /*5*/:
                return isSetAppVersion();
            case TApplicationException.INTERNAL_ERROR /*6*/:
                return isSetPackageName();
            case TApplicationException.PROTOCOL_ERROR /*7*/:
                return isSetToken();
            case PushConstants.ERROR_RECEIVE_TIMEOUT /*8*/:
                return isSetDeviceId();
            case PushConstants.ERROR_READ_ERROR /*9*/:
                return isSetAliasName();
            case Constants.VALUE_MAX_FAILEDCOUNT /*10*/:
                return isSetSdkVersion();
            default:
                throw new IllegalStateException();
        }
    }

    public boolean equals(Object that) {
        if (that != null && (that instanceof XmPushActionRegistration)) {
            return equals((XmPushActionRegistration) that);
        }
        return false;
    }

    public boolean equals(XmPushActionRegistration that) {
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
        boolean this_present_appVersion = isSetAppVersion();
        boolean that_present_appVersion = that.isSetAppVersion();
        if (this_present_appVersion || that_present_appVersion) {
            if (!this_present_appVersion || !that_present_appVersion) {
                return false;
            }
            if (!this.appVersion.equals(that.appVersion)) {
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
        boolean this_present_token = isSetToken();
        boolean that_present_token = that.isSetToken();
        if (this_present_token || that_present_token) {
            if (!this_present_token || !that_present_token) {
                return false;
            }
            if (!this.token.equals(that.token)) {
                return false;
            }
        }
        boolean this_present_deviceId = isSetDeviceId();
        boolean that_present_deviceId = that.isSetDeviceId();
        if (this_present_deviceId || that_present_deviceId) {
            if (!this_present_deviceId || !that_present_deviceId) {
                return false;
            }
            if (!this.deviceId.equals(that.deviceId)) {
                return false;
            }
        }
        boolean this_present_aliasName = isSetAliasName();
        boolean that_present_aliasName = that.isSetAliasName();
        if (this_present_aliasName || that_present_aliasName) {
            if (!this_present_aliasName || !that_present_aliasName) {
                return false;
            }
            if (!this.aliasName.equals(that.aliasName)) {
                return false;
            }
        }
        boolean this_present_sdkVersion = isSetSdkVersion();
        boolean that_present_sdkVersion = that.isSetSdkVersion();
        if (this_present_sdkVersion || that_present_sdkVersion) {
            if (!this_present_sdkVersion || !that_present_sdkVersion) {
                return false;
            }
            if (!this.sdkVersion.equals(that.sdkVersion)) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        return 0;
    }

    public int compareTo(XmPushActionRegistration other) {
        if (!getClass().equals(other.getClass())) {
            return getClass().getName().compareTo(other.getClass().getName());
        }
        XmPushActionRegistration typedOther = other;
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
        lastComparison = Boolean.valueOf(isSetAppVersion()).compareTo(Boolean.valueOf(typedOther.isSetAppVersion()));
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetAppVersion()) {
            lastComparison = TBaseHelper.compareTo(this.appVersion, typedOther.appVersion);
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
        lastComparison = Boolean.valueOf(isSetToken()).compareTo(Boolean.valueOf(typedOther.isSetToken()));
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetToken()) {
            lastComparison = TBaseHelper.compareTo(this.token, typedOther.token);
            if (lastComparison != 0) {
                return lastComparison;
            }
        }
        lastComparison = Boolean.valueOf(isSetDeviceId()).compareTo(Boolean.valueOf(typedOther.isSetDeviceId()));
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetDeviceId()) {
            lastComparison = TBaseHelper.compareTo(this.deviceId, typedOther.deviceId);
            if (lastComparison != 0) {
                return lastComparison;
            }
        }
        lastComparison = Boolean.valueOf(isSetAliasName()).compareTo(Boolean.valueOf(typedOther.isSetAliasName()));
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetAliasName()) {
            lastComparison = TBaseHelper.compareTo(this.aliasName, typedOther.aliasName);
            if (lastComparison != 0) {
                return lastComparison;
            }
        }
        lastComparison = Boolean.valueOf(isSetSdkVersion()).compareTo(Boolean.valueOf(typedOther.isSetSdkVersion()));
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetSdkVersion()) {
            lastComparison = TBaseHelper.compareTo(this.sdkVersion, typedOther.sdkVersion);
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
                    if (field.type != TType.STRING) {
                        TProtocolUtil.skip(iprot, field.type);
                        break;
                    } else {
                        this.appVersion = iprot.readString();
                        break;
                    }
                case TApplicationException.INTERNAL_ERROR /*6*/:
                    if (field.type != TType.STRING) {
                        TProtocolUtil.skip(iprot, field.type);
                        break;
                    } else {
                        this.packageName = iprot.readString();
                        break;
                    }
                case TApplicationException.PROTOCOL_ERROR /*7*/:
                    if (field.type != TType.STRING) {
                        TProtocolUtil.skip(iprot, field.type);
                        break;
                    } else {
                        this.token = iprot.readString();
                        break;
                    }
                case PushConstants.ERROR_RECEIVE_TIMEOUT /*8*/:
                    if (field.type != TType.STRING) {
                        TProtocolUtil.skip(iprot, field.type);
                        break;
                    } else {
                        this.deviceId = iprot.readString();
                        break;
                    }
                case PushConstants.ERROR_READ_ERROR /*9*/:
                    if (field.type != TType.STRING) {
                        TProtocolUtil.skip(iprot, field.type);
                        break;
                    } else {
                        this.aliasName = iprot.readString();
                        break;
                    }
                case Constants.VALUE_MAX_FAILEDCOUNT /*10*/:
                    if (field.type != TType.STRING) {
                        TProtocolUtil.skip(iprot, field.type);
                        break;
                    } else {
                        this.sdkVersion = iprot.readString();
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
        if (this.appVersion != null && isSetAppVersion()) {
            oprot.writeFieldBegin(APP_VERSION_FIELD_DESC);
            oprot.writeString(this.appVersion);
            oprot.writeFieldEnd();
        }
        if (this.packageName != null && isSetPackageName()) {
            oprot.writeFieldBegin(PACKAGE_NAME_FIELD_DESC);
            oprot.writeString(this.packageName);
            oprot.writeFieldEnd();
        }
        if (this.token != null) {
            oprot.writeFieldBegin(TOKEN_FIELD_DESC);
            oprot.writeString(this.token);
            oprot.writeFieldEnd();
        }
        if (this.deviceId != null && isSetDeviceId()) {
            oprot.writeFieldBegin(DEVICE_ID_FIELD_DESC);
            oprot.writeString(this.deviceId);
            oprot.writeFieldEnd();
        }
        if (this.aliasName != null && isSetAliasName()) {
            oprot.writeFieldBegin(ALIAS_NAME_FIELD_DESC);
            oprot.writeString(this.aliasName);
            oprot.writeFieldEnd();
        }
        if (this.sdkVersion != null && isSetSdkVersion()) {
            oprot.writeFieldBegin(SDK_VERSION_FIELD_DESC);
            oprot.writeString(this.sdkVersion);
            oprot.writeFieldEnd();
        }
        oprot.writeFieldStop();
        oprot.writeStructEnd();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("XmPushActionRegistration(");
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
        if (isSetAppVersion()) {
            if (null == null) {
                sb.append(", ");
            }
            sb.append("appVersion:");
            if (this.appVersion == null) {
                sb.append("null");
            } else {
                sb.append(this.appVersion);
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
            first = false;
        }
        if (!first) {
            sb.append(", ");
        }
        sb.append("token:");
        if (this.token == null) {
            sb.append("null");
        } else {
            sb.append(this.token);
        }
        first = false;
        if (isSetDeviceId()) {
            if (null == null) {
                sb.append(", ");
            }
            sb.append("deviceId:");
            if (this.deviceId == null) {
                sb.append("null");
            } else {
                sb.append(this.deviceId);
            }
            first = false;
        }
        if (isSetAliasName()) {
            if (!first) {
                sb.append(", ");
            }
            sb.append("aliasName:");
            if (this.aliasName == null) {
                sb.append("null");
            } else {
                sb.append(this.aliasName);
            }
            first = false;
        }
        if (isSetSdkVersion()) {
            if (!first) {
                sb.append(", ");
            }
            sb.append("sdkVersion:");
            if (this.sdkVersion == null) {
                sb.append("null");
            } else {
                sb.append(this.sdkVersion);
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
        } else if (this.token == null) {
            throw new TProtocolException("Required field 'token' was not present! Struct: " + toString());
        }
    }
}
