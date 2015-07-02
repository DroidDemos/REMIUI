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
import java.util.Map.Entry;
import org.apache.thrift.TApplicationException;
import org.apache.thrift.TBase;
import org.apache.thrift.TBaseHelper;
import org.apache.thrift.TException;
import org.apache.thrift.TFieldIdEnum;
import org.apache.thrift.meta_data.FieldMetaData;
import org.apache.thrift.meta_data.FieldValueMetaData;
import org.apache.thrift.meta_data.MapMetaData;
import org.apache.thrift.meta_data.StructMetaData;
import org.apache.thrift.protocol.TField;
import org.apache.thrift.protocol.TMap;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.protocol.TProtocolUtil;
import org.apache.thrift.protocol.TStruct;
import org.apache.thrift.protocol.TType;
import org.apache.thrift.transport.TTransportException;

public class XmPushActionPresence implements TBase<XmPushActionPresence, _Fields>, Serializable, Cloneable {
    private static final TField APP_ID_FIELD_DESC;
    private static final TField APP_VERSION_FIELD_DESC;
    private static final TField DEBUG_FIELD_DESC;
    private static final TField FROM_FIELD_DESC;
    private static final TField ID_FIELD_DESC;
    private static final TField IS_ONLINE_FIELD_DESC;
    private static final TField PARAMS_FIELD_DESC;
    private static final TField SDK_VERSION_FIELD_DESC;
    private static final TStruct STRUCT_DESC;
    private static final TField TARGET_FIELD_DESC;
    private static final int __ISONLINE_ISSET_ID = 0;
    public static final Map<_Fields, FieldMetaData> metaDataMap;
    private BitSet __isset_bit_vector;
    public String appId;
    public String appVersion;
    public String debug;
    public String from;
    public String id;
    public boolean isOnline;
    public Map<String, String> params;
    public String sdkVersion;
    public Target target;

    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$xiaomi$xmpush$thrift$XmPushActionPresence$_Fields;

        static {
            $SwitchMap$com$xiaomi$xmpush$thrift$XmPushActionPresence$_Fields = new int[_Fields.values().length];
            try {
                $SwitchMap$com$xiaomi$xmpush$thrift$XmPushActionPresence$_Fields[_Fields.DEBUG.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$xiaomi$xmpush$thrift$XmPushActionPresence$_Fields[_Fields.TARGET.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$xiaomi$xmpush$thrift$XmPushActionPresence$_Fields[_Fields.ID.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$xiaomi$xmpush$thrift$XmPushActionPresence$_Fields[_Fields.APP_ID.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$xiaomi$xmpush$thrift$XmPushActionPresence$_Fields[_Fields.APP_VERSION.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$xiaomi$xmpush$thrift$XmPushActionPresence$_Fields[_Fields.SDK_VERSION.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$com$xiaomi$xmpush$thrift$XmPushActionPresence$_Fields[_Fields.PARAMS.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$com$xiaomi$xmpush$thrift$XmPushActionPresence$_Fields[_Fields.IS_ONLINE.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                $SwitchMap$com$xiaomi$xmpush$thrift$XmPushActionPresence$_Fields[_Fields.FROM.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
        }
    }

    public enum _Fields implements TFieldIdEnum {
        DEBUG((short) 1, "debug"),
        TARGET((short) 2, "target"),
        ID((short) 3, Constants.JSON_TAG_ID),
        APP_ID((short) 4, "appId"),
        APP_VERSION((short) 5, "appVersion"),
        SDK_VERSION((short) 6, "sdkVersion"),
        PARAMS((short) 7, "params"),
        IS_ONLINE((short) 8, "isOnline"),
        FROM((short) 9, PushServiceConstants.EXTRA_FROM);
        
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
                    return SDK_VERSION;
                case TApplicationException.PROTOCOL_ERROR /*7*/:
                    return PARAMS;
                case PushConstants.ERROR_RECEIVE_TIMEOUT /*8*/:
                    return IS_ONLINE;
                case PushConstants.ERROR_READ_ERROR /*9*/:
                    return FROM;
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
        STRUCT_DESC = new TStruct("XmPushActionPresence");
        DEBUG_FIELD_DESC = new TField("debug", TType.STRING, (short) 1);
        TARGET_FIELD_DESC = new TField("target", TType.STRUCT, (short) 2);
        ID_FIELD_DESC = new TField(Constants.JSON_TAG_ID, TType.STRING, (short) 3);
        APP_ID_FIELD_DESC = new TField("appId", TType.STRING, (short) 4);
        APP_VERSION_FIELD_DESC = new TField("appVersion", TType.STRING, (short) 5);
        SDK_VERSION_FIELD_DESC = new TField("sdkVersion", TType.STRING, (short) 6);
        PARAMS_FIELD_DESC = new TField("params", TType.MAP, (short) 7);
        IS_ONLINE_FIELD_DESC = new TField("isOnline", (byte) 2, (short) 8);
        FROM_FIELD_DESC = new TField(PushServiceConstants.EXTRA_FROM, TType.STRING, (short) 9);
        Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
        tmpMap.put(_Fields.DEBUG, new FieldMetaData("debug", (byte) 2, new FieldValueMetaData(TType.STRING)));
        tmpMap.put(_Fields.TARGET, new FieldMetaData("target", (byte) 2, new StructMetaData(TType.STRUCT, Target.class)));
        tmpMap.put(_Fields.ID, new FieldMetaData(Constants.JSON_TAG_ID, (byte) 1, new FieldValueMetaData(TType.STRING)));
        tmpMap.put(_Fields.APP_ID, new FieldMetaData("appId", (byte) 1, new FieldValueMetaData(TType.STRING)));
        tmpMap.put(_Fields.APP_VERSION, new FieldMetaData("appVersion", (byte) 2, new FieldValueMetaData(TType.STRING)));
        tmpMap.put(_Fields.SDK_VERSION, new FieldMetaData("sdkVersion", (byte) 2, new FieldValueMetaData(TType.STRING)));
        tmpMap.put(_Fields.PARAMS, new FieldMetaData("params", (byte) 2, new MapMetaData(TType.MAP, new FieldValueMetaData(TType.STRING), new FieldValueMetaData(TType.STRING))));
        tmpMap.put(_Fields.IS_ONLINE, new FieldMetaData("isOnline", (byte) 2, new FieldValueMetaData((byte) 2)));
        tmpMap.put(_Fields.FROM, new FieldMetaData(PushServiceConstants.EXTRA_FROM, (byte) 2, new FieldValueMetaData(TType.STRING)));
        metaDataMap = Collections.unmodifiableMap(tmpMap);
        FieldMetaData.addStructMetaDataMap(XmPushActionPresence.class, metaDataMap);
    }

    public XmPushActionPresence() {
        this.__isset_bit_vector = new BitSet(1);
    }

    public XmPushActionPresence(String id, String appId) {
        this();
        this.id = id;
        this.appId = appId;
    }

    public XmPushActionPresence(XmPushActionPresence other) {
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
        if (other.isSetAppVersion()) {
            this.appVersion = other.appVersion;
        }
        if (other.isSetSdkVersion()) {
            this.sdkVersion = other.sdkVersion;
        }
        if (other.isSetParams()) {
            Map<String, String> __this__params = new HashMap();
            for (Entry<String, String> other_element : other.params.entrySet()) {
                __this__params.put((String) other_element.getKey(), (String) other_element.getValue());
            }
            this.params = __this__params;
        }
        this.isOnline = other.isOnline;
        if (other.isSetFrom()) {
            this.from = other.from;
        }
    }

    public XmPushActionPresence deepCopy() {
        return new XmPushActionPresence(this);
    }

    public void clear() {
        this.debug = null;
        this.target = null;
        this.id = null;
        this.appId = null;
        this.appVersion = null;
        this.sdkVersion = null;
        this.params = null;
        setIsOnlineIsSet(false);
        this.isOnline = false;
        this.from = null;
    }

    public String getDebug() {
        return this.debug;
    }

    public XmPushActionPresence setDebug(String debug) {
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

    public XmPushActionPresence setTarget(Target target) {
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

    public XmPushActionPresence setId(String id) {
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

    public XmPushActionPresence setAppId(String appId) {
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

    public XmPushActionPresence setAppVersion(String appVersion) {
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

    public String getSdkVersion() {
        return this.sdkVersion;
    }

    public XmPushActionPresence setSdkVersion(String sdkVersion) {
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

    public int getParamsSize() {
        return this.params == null ? 0 : this.params.size();
    }

    public void putToParams(String key, String val) {
        if (this.params == null) {
            this.params = new HashMap();
        }
        this.params.put(key, val);
    }

    public Map<String, String> getParams() {
        return this.params;
    }

    public XmPushActionPresence setParams(Map<String, String> params) {
        this.params = params;
        return this;
    }

    public void unsetParams() {
        this.params = null;
    }

    public boolean isSetParams() {
        return this.params != null;
    }

    public void setParamsIsSet(boolean value) {
        if (!value) {
            this.params = null;
        }
    }

    public boolean isIsOnline() {
        return this.isOnline;
    }

    public XmPushActionPresence setIsOnline(boolean isOnline) {
        this.isOnline = isOnline;
        setIsOnlineIsSet(true);
        return this;
    }

    public void unsetIsOnline() {
        this.__isset_bit_vector.clear(0);
    }

    public boolean isSetIsOnline() {
        return this.__isset_bit_vector.get(0);
    }

    public void setIsOnlineIsSet(boolean value) {
        this.__isset_bit_vector.set(0, value);
    }

    public String getFrom() {
        return this.from;
    }

    public XmPushActionPresence setFrom(String from) {
        this.from = from;
        return this;
    }

    public void unsetFrom() {
        this.from = null;
    }

    public boolean isSetFrom() {
        return this.from != null;
    }

    public void setFromIsSet(boolean value) {
        if (!value) {
            this.from = null;
        }
    }

    public void setFieldValue(_Fields field, Object value) {
        switch (AnonymousClass1.$SwitchMap$com$xiaomi$xmpush$thrift$XmPushActionPresence$_Fields[field.ordinal()]) {
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
                    unsetSdkVersion();
                    return;
                } else {
                    setSdkVersion((String) value);
                    return;
                }
            case TApplicationException.PROTOCOL_ERROR /*7*/:
                if (value == null) {
                    unsetParams();
                    return;
                } else {
                    setParams((Map) value);
                    return;
                }
            case PushConstants.ERROR_RECEIVE_TIMEOUT /*8*/:
                if (value == null) {
                    unsetIsOnline();
                    return;
                } else {
                    setIsOnline(((Boolean) value).booleanValue());
                    return;
                }
            case PushConstants.ERROR_READ_ERROR /*9*/:
                if (value == null) {
                    unsetFrom();
                    return;
                } else {
                    setFrom((String) value);
                    return;
                }
            default:
                return;
        }
    }

    public Object getFieldValue(_Fields field) {
        switch (AnonymousClass1.$SwitchMap$com$xiaomi$xmpush$thrift$XmPushActionPresence$_Fields[field.ordinal()]) {
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
                return getSdkVersion();
            case TApplicationException.PROTOCOL_ERROR /*7*/:
                return getParams();
            case PushConstants.ERROR_RECEIVE_TIMEOUT /*8*/:
                return new Boolean(isIsOnline());
            case PushConstants.ERROR_READ_ERROR /*9*/:
                return getFrom();
            default:
                throw new IllegalStateException();
        }
    }

    public boolean isSet(_Fields field) {
        if (field == null) {
            throw new IllegalArgumentException();
        }
        switch (AnonymousClass1.$SwitchMap$com$xiaomi$xmpush$thrift$XmPushActionPresence$_Fields[field.ordinal()]) {
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
                return isSetSdkVersion();
            case TApplicationException.PROTOCOL_ERROR /*7*/:
                return isSetParams();
            case PushConstants.ERROR_RECEIVE_TIMEOUT /*8*/:
                return isSetIsOnline();
            case PushConstants.ERROR_READ_ERROR /*9*/:
                return isSetFrom();
            default:
                throw new IllegalStateException();
        }
    }

    public boolean equals(Object that) {
        if (that != null && (that instanceof XmPushActionPresence)) {
            return equals((XmPushActionPresence) that);
        }
        return false;
    }

    public boolean equals(XmPushActionPresence that) {
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
        boolean this_present_params = isSetParams();
        boolean that_present_params = that.isSetParams();
        if (this_present_params || that_present_params) {
            if (!this_present_params || !that_present_params) {
                return false;
            }
            if (!this.params.equals(that.params)) {
                return false;
            }
        }
        boolean this_present_isOnline = isSetIsOnline();
        boolean that_present_isOnline = that.isSetIsOnline();
        if (this_present_isOnline || that_present_isOnline) {
            if (!this_present_isOnline || !that_present_isOnline) {
                return false;
            }
            if (this.isOnline != that.isOnline) {
                return false;
            }
        }
        boolean this_present_from = isSetFrom();
        boolean that_present_from = that.isSetFrom();
        if (this_present_from || that_present_from) {
            if (!this_present_from || !that_present_from) {
                return false;
            }
            if (!this.from.equals(that.from)) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        return 0;
    }

    public int compareTo(XmPushActionPresence other) {
        if (!getClass().equals(other.getClass())) {
            return getClass().getName().compareTo(other.getClass().getName());
        }
        XmPushActionPresence typedOther = other;
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
        lastComparison = Boolean.valueOf(isSetParams()).compareTo(Boolean.valueOf(typedOther.isSetParams()));
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetParams()) {
            lastComparison = TBaseHelper.compareTo(this.params, typedOther.params);
            if (lastComparison != 0) {
                return lastComparison;
            }
        }
        lastComparison = Boolean.valueOf(isSetIsOnline()).compareTo(Boolean.valueOf(typedOther.isSetIsOnline()));
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetIsOnline()) {
            lastComparison = TBaseHelper.compareTo(this.isOnline, typedOther.isOnline);
            if (lastComparison != 0) {
                return lastComparison;
            }
        }
        lastComparison = Boolean.valueOf(isSetFrom()).compareTo(Boolean.valueOf(typedOther.isSetFrom()));
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetFrom()) {
            lastComparison = TBaseHelper.compareTo(this.from, typedOther.from);
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
                        this.sdkVersion = iprot.readString();
                        break;
                    }
                case TApplicationException.PROTOCOL_ERROR /*7*/:
                    if (field.type != TType.MAP) {
                        TProtocolUtil.skip(iprot, field.type);
                        break;
                    }
                    TMap _map33 = iprot.readMapBegin();
                    this.params = new HashMap(_map33.size * 2);
                    for (int _i34 = 0; _i34 < _map33.size; _i34++) {
                        this.params.put(iprot.readString(), iprot.readString());
                    }
                    iprot.readMapEnd();
                    break;
                case PushConstants.ERROR_RECEIVE_TIMEOUT /*8*/:
                    if (field.type != (byte) 2) {
                        TProtocolUtil.skip(iprot, field.type);
                        break;
                    }
                    this.isOnline = iprot.readBool();
                    setIsOnlineIsSet(true);
                    break;
                case PushConstants.ERROR_READ_ERROR /*9*/:
                    if (field.type != TType.STRING) {
                        TProtocolUtil.skip(iprot, field.type);
                        break;
                    } else {
                        this.from = iprot.readString();
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
        if (this.sdkVersion != null && isSetSdkVersion()) {
            oprot.writeFieldBegin(SDK_VERSION_FIELD_DESC);
            oprot.writeString(this.sdkVersion);
            oprot.writeFieldEnd();
        }
        if (this.params != null && isSetParams()) {
            oprot.writeFieldBegin(PARAMS_FIELD_DESC);
            oprot.writeMapBegin(new TMap(TType.STRING, TType.STRING, this.params.size()));
            for (Entry<String, String> _iter37 : this.params.entrySet()) {
                oprot.writeString((String) _iter37.getKey());
                oprot.writeString((String) _iter37.getValue());
            }
            oprot.writeMapEnd();
            oprot.writeFieldEnd();
        }
        if (isSetIsOnline()) {
            oprot.writeFieldBegin(IS_ONLINE_FIELD_DESC);
            oprot.writeBool(this.isOnline);
            oprot.writeFieldEnd();
        }
        if (this.from != null && isSetFrom()) {
            oprot.writeFieldBegin(FROM_FIELD_DESC);
            oprot.writeString(this.from);
            oprot.writeFieldEnd();
        }
        oprot.writeFieldStop();
        oprot.writeStructEnd();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("XmPushActionPresence(");
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
            first = false;
        }
        if (isSetParams()) {
            if (!first) {
                sb.append(", ");
            }
            sb.append("params:");
            if (this.params == null) {
                sb.append("null");
            } else {
                sb.append(this.params);
            }
            first = false;
        }
        if (isSetIsOnline()) {
            if (!first) {
                sb.append(", ");
            }
            sb.append("isOnline:");
            sb.append(this.isOnline);
            first = false;
        }
        if (isSetFrom()) {
            if (!first) {
                sb.append(", ");
            }
            sb.append("from:");
            if (this.from == null) {
                sb.append("null");
            } else {
                sb.append(this.from);
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
