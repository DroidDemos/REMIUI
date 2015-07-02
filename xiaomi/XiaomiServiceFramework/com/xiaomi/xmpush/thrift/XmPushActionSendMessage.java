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

public class XmPushActionSendMessage implements TBase<XmPushActionSendMessage, _Fields>, Serializable, Cloneable {
    private static final TField ALIAS_NAME_FIELD_DESC;
    private static final TField APP_ID_FIELD_DESC;
    private static final TField CATEGORY_FIELD_DESC;
    private static final TField DEBUG_FIELD_DESC;
    private static final TField ID_FIELD_DESC;
    private static final TField MESSAGE_FIELD_DESC;
    private static final TField NEED_ACK_FIELD_DESC;
    private static final TField PACKAGE_NAME_FIELD_DESC;
    private static final TField PARAMS_FIELD_DESC;
    private static final TStruct STRUCT_DESC;
    private static final TField TARGET_FIELD_DESC;
    private static final TField TOPIC_FIELD_DESC;
    private static final int __NEEDACK_ISSET_ID = 0;
    public static final Map<_Fields, FieldMetaData> metaDataMap;
    private BitSet __isset_bit_vector;
    public String aliasName;
    public String appId;
    public String category;
    public String debug;
    public String id;
    public PushMessage message;
    public boolean needAck;
    public String packageName;
    public Map<String, String> params;
    public Target target;
    public String topic;

    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$xiaomi$xmpush$thrift$XmPushActionSendMessage$_Fields;

        static {
            $SwitchMap$com$xiaomi$xmpush$thrift$XmPushActionSendMessage$_Fields = new int[_Fields.values().length];
            try {
                $SwitchMap$com$xiaomi$xmpush$thrift$XmPushActionSendMessage$_Fields[_Fields.DEBUG.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$xiaomi$xmpush$thrift$XmPushActionSendMessage$_Fields[_Fields.TARGET.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$xiaomi$xmpush$thrift$XmPushActionSendMessage$_Fields[_Fields.ID.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$xiaomi$xmpush$thrift$XmPushActionSendMessage$_Fields[_Fields.APP_ID.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$xiaomi$xmpush$thrift$XmPushActionSendMessage$_Fields[_Fields.PACKAGE_NAME.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$xiaomi$xmpush$thrift$XmPushActionSendMessage$_Fields[_Fields.TOPIC.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$com$xiaomi$xmpush$thrift$XmPushActionSendMessage$_Fields[_Fields.ALIAS_NAME.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$com$xiaomi$xmpush$thrift$XmPushActionSendMessage$_Fields[_Fields.MESSAGE.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                $SwitchMap$com$xiaomi$xmpush$thrift$XmPushActionSendMessage$_Fields[_Fields.NEED_ACK.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                $SwitchMap$com$xiaomi$xmpush$thrift$XmPushActionSendMessage$_Fields[_Fields.PARAMS.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
            try {
                $SwitchMap$com$xiaomi$xmpush$thrift$XmPushActionSendMessage$_Fields[_Fields.CATEGORY.ordinal()] = 11;
            } catch (NoSuchFieldError e11) {
            }
        }
    }

    public enum _Fields implements TFieldIdEnum {
        DEBUG((short) 1, "debug"),
        TARGET((short) 2, "target"),
        ID((short) 3, Constants.JSON_TAG_ID),
        APP_ID((short) 4, "appId"),
        PACKAGE_NAME((short) 5, "packageName"),
        TOPIC((short) 6, "topic"),
        ALIAS_NAME((short) 7, "aliasName"),
        MESSAGE((short) 8, "message"),
        NEED_ACK((short) 9, "needAck"),
        PARAMS((short) 10, "params"),
        CATEGORY((short) 11, "category");
        
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
                    return PACKAGE_NAME;
                case TApplicationException.INTERNAL_ERROR /*6*/:
                    return TOPIC;
                case TApplicationException.PROTOCOL_ERROR /*7*/:
                    return ALIAS_NAME;
                case PushConstants.ERROR_RECEIVE_TIMEOUT /*8*/:
                    return MESSAGE;
                case PushConstants.ERROR_READ_ERROR /*9*/:
                    return NEED_ACK;
                case Constants.VALUE_MAX_FAILEDCOUNT /*10*/:
                    return PARAMS;
                case PushConstants.ERROR_RESET /*11*/:
                    return CATEGORY;
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
        STRUCT_DESC = new TStruct("XmPushActionSendMessage");
        DEBUG_FIELD_DESC = new TField("debug", TType.STRING, (short) 1);
        TARGET_FIELD_DESC = new TField("target", TType.STRUCT, (short) 2);
        ID_FIELD_DESC = new TField(Constants.JSON_TAG_ID, TType.STRING, (short) 3);
        APP_ID_FIELD_DESC = new TField("appId", TType.STRING, (short) 4);
        PACKAGE_NAME_FIELD_DESC = new TField("packageName", TType.STRING, (short) 5);
        TOPIC_FIELD_DESC = new TField("topic", TType.STRING, (short) 6);
        ALIAS_NAME_FIELD_DESC = new TField("aliasName", TType.STRING, (short) 7);
        MESSAGE_FIELD_DESC = new TField("message", TType.STRUCT, (short) 8);
        NEED_ACK_FIELD_DESC = new TField("needAck", (byte) 2, (short) 9);
        PARAMS_FIELD_DESC = new TField("params", TType.MAP, (short) 10);
        CATEGORY_FIELD_DESC = new TField("category", TType.STRING, (short) 11);
        Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
        tmpMap.put(_Fields.DEBUG, new FieldMetaData("debug", (byte) 2, new FieldValueMetaData(TType.STRING)));
        tmpMap.put(_Fields.TARGET, new FieldMetaData("target", (byte) 2, new StructMetaData(TType.STRUCT, Target.class)));
        tmpMap.put(_Fields.ID, new FieldMetaData(Constants.JSON_TAG_ID, (byte) 1, new FieldValueMetaData(TType.STRING)));
        tmpMap.put(_Fields.APP_ID, new FieldMetaData("appId", (byte) 1, new FieldValueMetaData(TType.STRING)));
        tmpMap.put(_Fields.PACKAGE_NAME, new FieldMetaData("packageName", (byte) 2, new FieldValueMetaData(TType.STRING)));
        tmpMap.put(_Fields.TOPIC, new FieldMetaData("topic", (byte) 2, new FieldValueMetaData(TType.STRING)));
        tmpMap.put(_Fields.ALIAS_NAME, new FieldMetaData("aliasName", (byte) 2, new FieldValueMetaData(TType.STRING)));
        tmpMap.put(_Fields.MESSAGE, new FieldMetaData("message", (byte) 2, new StructMetaData(TType.STRUCT, PushMessage.class)));
        tmpMap.put(_Fields.NEED_ACK, new FieldMetaData("needAck", (byte) 2, new FieldValueMetaData((byte) 2)));
        tmpMap.put(_Fields.PARAMS, new FieldMetaData("params", (byte) 2, new MapMetaData(TType.MAP, new FieldValueMetaData(TType.STRING), new FieldValueMetaData(TType.STRING))));
        tmpMap.put(_Fields.CATEGORY, new FieldMetaData("category", (byte) 2, new FieldValueMetaData(TType.STRING)));
        metaDataMap = Collections.unmodifiableMap(tmpMap);
        FieldMetaData.addStructMetaDataMap(XmPushActionSendMessage.class, metaDataMap);
    }

    public XmPushActionSendMessage() {
        this.__isset_bit_vector = new BitSet(1);
        this.needAck = true;
    }

    public XmPushActionSendMessage(String id, String appId) {
        this();
        this.id = id;
        this.appId = appId;
    }

    public XmPushActionSendMessage(XmPushActionSendMessage other) {
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
        if (other.isSetPackageName()) {
            this.packageName = other.packageName;
        }
        if (other.isSetTopic()) {
            this.topic = other.topic;
        }
        if (other.isSetAliasName()) {
            this.aliasName = other.aliasName;
        }
        if (other.isSetMessage()) {
            this.message = new PushMessage(other.message);
        }
        this.needAck = other.needAck;
        if (other.isSetParams()) {
            Map<String, String> __this__params = new HashMap();
            for (Entry<String, String> other_element : other.params.entrySet()) {
                __this__params.put((String) other_element.getKey(), (String) other_element.getValue());
            }
            this.params = __this__params;
        }
        if (other.isSetCategory()) {
            this.category = other.category;
        }
    }

    public XmPushActionSendMessage deepCopy() {
        return new XmPushActionSendMessage(this);
    }

    public void clear() {
        this.debug = null;
        this.target = null;
        this.id = null;
        this.appId = null;
        this.packageName = null;
        this.topic = null;
        this.aliasName = null;
        this.message = null;
        this.needAck = true;
        this.params = null;
        this.category = null;
    }

    public String getDebug() {
        return this.debug;
    }

    public XmPushActionSendMessage setDebug(String debug) {
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

    public XmPushActionSendMessage setTarget(Target target) {
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

    public XmPushActionSendMessage setId(String id) {
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

    public XmPushActionSendMessage setAppId(String appId) {
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

    public String getPackageName() {
        return this.packageName;
    }

    public XmPushActionSendMessage setPackageName(String packageName) {
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

    public String getTopic() {
        return this.topic;
    }

    public XmPushActionSendMessage setTopic(String topic) {
        this.topic = topic;
        return this;
    }

    public void unsetTopic() {
        this.topic = null;
    }

    public boolean isSetTopic() {
        return this.topic != null;
    }

    public void setTopicIsSet(boolean value) {
        if (!value) {
            this.topic = null;
        }
    }

    public String getAliasName() {
        return this.aliasName;
    }

    public XmPushActionSendMessage setAliasName(String aliasName) {
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

    public PushMessage getMessage() {
        return this.message;
    }

    public XmPushActionSendMessage setMessage(PushMessage message) {
        this.message = message;
        return this;
    }

    public void unsetMessage() {
        this.message = null;
    }

    public boolean isSetMessage() {
        return this.message != null;
    }

    public void setMessageIsSet(boolean value) {
        if (!value) {
            this.message = null;
        }
    }

    public boolean isNeedAck() {
        return this.needAck;
    }

    public XmPushActionSendMessage setNeedAck(boolean needAck) {
        this.needAck = needAck;
        setNeedAckIsSet(true);
        return this;
    }

    public void unsetNeedAck() {
        this.__isset_bit_vector.clear(0);
    }

    public boolean isSetNeedAck() {
        return this.__isset_bit_vector.get(0);
    }

    public void setNeedAckIsSet(boolean value) {
        this.__isset_bit_vector.set(0, value);
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

    public XmPushActionSendMessage setParams(Map<String, String> params) {
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

    public String getCategory() {
        return this.category;
    }

    public XmPushActionSendMessage setCategory(String category) {
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

    public void setFieldValue(_Fields field, Object value) {
        switch (AnonymousClass1.$SwitchMap$com$xiaomi$xmpush$thrift$XmPushActionSendMessage$_Fields[field.ordinal()]) {
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
                    unsetPackageName();
                    return;
                } else {
                    setPackageName((String) value);
                    return;
                }
            case TApplicationException.INTERNAL_ERROR /*6*/:
                if (value == null) {
                    unsetTopic();
                    return;
                } else {
                    setTopic((String) value);
                    return;
                }
            case TApplicationException.PROTOCOL_ERROR /*7*/:
                if (value == null) {
                    unsetAliasName();
                    return;
                } else {
                    setAliasName((String) value);
                    return;
                }
            case PushConstants.ERROR_RECEIVE_TIMEOUT /*8*/:
                if (value == null) {
                    unsetMessage();
                    return;
                } else {
                    setMessage((PushMessage) value);
                    return;
                }
            case PushConstants.ERROR_READ_ERROR /*9*/:
                if (value == null) {
                    unsetNeedAck();
                    return;
                } else {
                    setNeedAck(((Boolean) value).booleanValue());
                    return;
                }
            case Constants.VALUE_MAX_FAILEDCOUNT /*10*/:
                if (value == null) {
                    unsetParams();
                    return;
                } else {
                    setParams((Map) value);
                    return;
                }
            case PushConstants.ERROR_RESET /*11*/:
                if (value == null) {
                    unsetCategory();
                    return;
                } else {
                    setCategory((String) value);
                    return;
                }
            default:
                return;
        }
    }

    public Object getFieldValue(_Fields field) {
        switch (AnonymousClass1.$SwitchMap$com$xiaomi$xmpush$thrift$XmPushActionSendMessage$_Fields[field.ordinal()]) {
            case TTransportException.NOT_OPEN /*1*/:
                return getDebug();
            case TTransportException.ALREADY_OPEN /*2*/:
                return getTarget();
            case TTransportException.TIMED_OUT /*3*/:
                return getId();
            case TTransportException.END_OF_FILE /*4*/:
                return getAppId();
            case TProtocolException.NOT_IMPLEMENTED /*5*/:
                return getPackageName();
            case TApplicationException.INTERNAL_ERROR /*6*/:
                return getTopic();
            case TApplicationException.PROTOCOL_ERROR /*7*/:
                return getAliasName();
            case PushConstants.ERROR_RECEIVE_TIMEOUT /*8*/:
                return getMessage();
            case PushConstants.ERROR_READ_ERROR /*9*/:
                return new Boolean(isNeedAck());
            case Constants.VALUE_MAX_FAILEDCOUNT /*10*/:
                return getParams();
            case PushConstants.ERROR_RESET /*11*/:
                return getCategory();
            default:
                throw new IllegalStateException();
        }
    }

    public boolean isSet(_Fields field) {
        if (field == null) {
            throw new IllegalArgumentException();
        }
        switch (AnonymousClass1.$SwitchMap$com$xiaomi$xmpush$thrift$XmPushActionSendMessage$_Fields[field.ordinal()]) {
            case TTransportException.NOT_OPEN /*1*/:
                return isSetDebug();
            case TTransportException.ALREADY_OPEN /*2*/:
                return isSetTarget();
            case TTransportException.TIMED_OUT /*3*/:
                return isSetId();
            case TTransportException.END_OF_FILE /*4*/:
                return isSetAppId();
            case TProtocolException.NOT_IMPLEMENTED /*5*/:
                return isSetPackageName();
            case TApplicationException.INTERNAL_ERROR /*6*/:
                return isSetTopic();
            case TApplicationException.PROTOCOL_ERROR /*7*/:
                return isSetAliasName();
            case PushConstants.ERROR_RECEIVE_TIMEOUT /*8*/:
                return isSetMessage();
            case PushConstants.ERROR_READ_ERROR /*9*/:
                return isSetNeedAck();
            case Constants.VALUE_MAX_FAILEDCOUNT /*10*/:
                return isSetParams();
            case PushConstants.ERROR_RESET /*11*/:
                return isSetCategory();
            default:
                throw new IllegalStateException();
        }
    }

    public boolean equals(Object that) {
        if (that != null && (that instanceof XmPushActionSendMessage)) {
            return equals((XmPushActionSendMessage) that);
        }
        return false;
    }

    public boolean equals(XmPushActionSendMessage that) {
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
        boolean this_present_topic = isSetTopic();
        boolean that_present_topic = that.isSetTopic();
        if (this_present_topic || that_present_topic) {
            if (!this_present_topic || !that_present_topic) {
                return false;
            }
            if (!this.topic.equals(that.topic)) {
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
        boolean this_present_message = isSetMessage();
        boolean that_present_message = that.isSetMessage();
        if (this_present_message || that_present_message) {
            if (!this_present_message || !that_present_message) {
                return false;
            }
            if (!this.message.equals(that.message)) {
                return false;
            }
        }
        boolean this_present_needAck = isSetNeedAck();
        boolean that_present_needAck = that.isSetNeedAck();
        if (this_present_needAck || that_present_needAck) {
            if (!this_present_needAck || !that_present_needAck) {
                return false;
            }
            if (this.needAck != that.needAck) {
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
        boolean this_present_category = isSetCategory();
        boolean that_present_category = that.isSetCategory();
        if (this_present_category || that_present_category) {
            if (!this_present_category || !that_present_category) {
                return false;
            }
            if (!this.category.equals(that.category)) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        return 0;
    }

    public int compareTo(XmPushActionSendMessage other) {
        if (!getClass().equals(other.getClass())) {
            return getClass().getName().compareTo(other.getClass().getName());
        }
        XmPushActionSendMessage typedOther = other;
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
        lastComparison = Boolean.valueOf(isSetTopic()).compareTo(Boolean.valueOf(typedOther.isSetTopic()));
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetTopic()) {
            lastComparison = TBaseHelper.compareTo(this.topic, typedOther.topic);
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
        lastComparison = Boolean.valueOf(isSetMessage()).compareTo(Boolean.valueOf(typedOther.isSetMessage()));
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetMessage()) {
            lastComparison = TBaseHelper.compareTo(this.message, typedOther.message);
            if (lastComparison != 0) {
                return lastComparison;
            }
        }
        lastComparison = Boolean.valueOf(isSetNeedAck()).compareTo(Boolean.valueOf(typedOther.isSetNeedAck()));
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetNeedAck()) {
            lastComparison = TBaseHelper.compareTo(this.needAck, typedOther.needAck);
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
                        this.packageName = iprot.readString();
                        break;
                    }
                case TApplicationException.INTERNAL_ERROR /*6*/:
                    if (field.type != TType.STRING) {
                        TProtocolUtil.skip(iprot, field.type);
                        break;
                    } else {
                        this.topic = iprot.readString();
                        break;
                    }
                case TApplicationException.PROTOCOL_ERROR /*7*/:
                    if (field.type != TType.STRING) {
                        TProtocolUtil.skip(iprot, field.type);
                        break;
                    } else {
                        this.aliasName = iprot.readString();
                        break;
                    }
                case PushConstants.ERROR_RECEIVE_TIMEOUT /*8*/:
                    if (field.type != TType.STRUCT) {
                        TProtocolUtil.skip(iprot, field.type);
                        break;
                    }
                    this.message = new PushMessage();
                    this.message.read(iprot);
                    break;
                case PushConstants.ERROR_READ_ERROR /*9*/:
                    if (field.type != (byte) 2) {
                        TProtocolUtil.skip(iprot, field.type);
                        break;
                    }
                    this.needAck = iprot.readBool();
                    setNeedAckIsSet(true);
                    break;
                case Constants.VALUE_MAX_FAILEDCOUNT /*10*/:
                    if (field.type != TType.MAP) {
                        TProtocolUtil.skip(iprot, field.type);
                        break;
                    }
                    TMap _map5 = iprot.readMapBegin();
                    this.params = new HashMap(_map5.size * 2);
                    for (int _i6 = 0; _i6 < _map5.size; _i6++) {
                        this.params.put(iprot.readString(), iprot.readString());
                    }
                    iprot.readMapEnd();
                    break;
                case PushConstants.ERROR_RESET /*11*/:
                    if (field.type != TType.STRING) {
                        TProtocolUtil.skip(iprot, field.type);
                        break;
                    } else {
                        this.category = iprot.readString();
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
        if (this.packageName != null && isSetPackageName()) {
            oprot.writeFieldBegin(PACKAGE_NAME_FIELD_DESC);
            oprot.writeString(this.packageName);
            oprot.writeFieldEnd();
        }
        if (this.topic != null && isSetTopic()) {
            oprot.writeFieldBegin(TOPIC_FIELD_DESC);
            oprot.writeString(this.topic);
            oprot.writeFieldEnd();
        }
        if (this.aliasName != null && isSetAliasName()) {
            oprot.writeFieldBegin(ALIAS_NAME_FIELD_DESC);
            oprot.writeString(this.aliasName);
            oprot.writeFieldEnd();
        }
        if (this.message != null && isSetMessage()) {
            oprot.writeFieldBegin(MESSAGE_FIELD_DESC);
            this.message.write(oprot);
            oprot.writeFieldEnd();
        }
        if (isSetNeedAck()) {
            oprot.writeFieldBegin(NEED_ACK_FIELD_DESC);
            oprot.writeBool(this.needAck);
            oprot.writeFieldEnd();
        }
        if (this.params != null && isSetParams()) {
            oprot.writeFieldBegin(PARAMS_FIELD_DESC);
            oprot.writeMapBegin(new TMap(TType.STRING, TType.STRING, this.params.size()));
            for (Entry<String, String> _iter9 : this.params.entrySet()) {
                oprot.writeString((String) _iter9.getKey());
                oprot.writeString((String) _iter9.getValue());
            }
            oprot.writeMapEnd();
            oprot.writeFieldEnd();
        }
        if (this.category != null && isSetCategory()) {
            oprot.writeFieldBegin(CATEGORY_FIELD_DESC);
            oprot.writeString(this.category);
            oprot.writeFieldEnd();
        }
        oprot.writeFieldStop();
        oprot.writeStructEnd();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("XmPushActionSendMessage(");
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
        if (isSetPackageName()) {
            if (null == null) {
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
        if (isSetTopic()) {
            if (!first) {
                sb.append(", ");
            }
            sb.append("topic:");
            if (this.topic == null) {
                sb.append("null");
            } else {
                sb.append(this.topic);
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
        if (isSetMessage()) {
            if (!first) {
                sb.append(", ");
            }
            sb.append("message:");
            if (this.message == null) {
                sb.append("null");
            } else {
                sb.append(this.message);
            }
            first = false;
        }
        if (isSetNeedAck()) {
            if (!first) {
                sb.append(", ");
            }
            sb.append("needAck:");
            sb.append(this.needAck);
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
        if (isSetCategory()) {
            if (!first) {
                sb.append(", ");
            }
            sb.append("category:");
            if (this.category == null) {
                sb.append("null");
            } else {
                sb.append(this.category);
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
