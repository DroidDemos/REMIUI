package com.xiaomi.xmpush.thrift;

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

public class XmPushActionUnSubscription implements TBase<XmPushActionUnSubscription, _Fields>, Serializable, Cloneable {
    private static final TField APP_ID_FIELD_DESC;
    private static final TField CATEGORY_FIELD_DESC;
    private static final TField DEBUG_FIELD_DESC;
    private static final TField ID_FIELD_DESC;
    private static final TField PACKAGE_NAME_FIELD_DESC;
    private static final TStruct STRUCT_DESC;
    private static final TField TARGET_FIELD_DESC;
    private static final TField TOPIC_FIELD_DESC;
    public static final Map<_Fields, FieldMetaData> metaDataMap;
    public String appId;
    public String category;
    public String debug;
    public String id;
    public String packageName;
    public Target target;
    public String topic;

    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$xiaomi$xmpush$thrift$XmPushActionUnSubscription$_Fields;

        static {
            $SwitchMap$com$xiaomi$xmpush$thrift$XmPushActionUnSubscription$_Fields = new int[_Fields.values().length];
            try {
                $SwitchMap$com$xiaomi$xmpush$thrift$XmPushActionUnSubscription$_Fields[_Fields.DEBUG.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$xiaomi$xmpush$thrift$XmPushActionUnSubscription$_Fields[_Fields.TARGET.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$xiaomi$xmpush$thrift$XmPushActionUnSubscription$_Fields[_Fields.ID.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$xiaomi$xmpush$thrift$XmPushActionUnSubscription$_Fields[_Fields.APP_ID.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$xiaomi$xmpush$thrift$XmPushActionUnSubscription$_Fields[_Fields.TOPIC.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$xiaomi$xmpush$thrift$XmPushActionUnSubscription$_Fields[_Fields.PACKAGE_NAME.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$com$xiaomi$xmpush$thrift$XmPushActionUnSubscription$_Fields[_Fields.CATEGORY.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
        }
    }

    public enum _Fields implements TFieldIdEnum {
        DEBUG((short) 1, "debug"),
        TARGET((short) 2, "target"),
        ID((short) 3, Constants.JSON_TAG_ID),
        APP_ID((short) 4, "appId"),
        TOPIC((short) 5, "topic"),
        PACKAGE_NAME((short) 6, "packageName"),
        CATEGORY((short) 7, "category");
        
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
                    return TOPIC;
                case TApplicationException.INTERNAL_ERROR /*6*/:
                    return PACKAGE_NAME;
                case TApplicationException.PROTOCOL_ERROR /*7*/:
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
        STRUCT_DESC = new TStruct("XmPushActionUnSubscription");
        DEBUG_FIELD_DESC = new TField("debug", TType.STRING, (short) 1);
        TARGET_FIELD_DESC = new TField("target", TType.STRUCT, (short) 2);
        ID_FIELD_DESC = new TField(Constants.JSON_TAG_ID, TType.STRING, (short) 3);
        APP_ID_FIELD_DESC = new TField("appId", TType.STRING, (short) 4);
        TOPIC_FIELD_DESC = new TField("topic", TType.STRING, (short) 5);
        PACKAGE_NAME_FIELD_DESC = new TField("packageName", TType.STRING, (short) 6);
        CATEGORY_FIELD_DESC = new TField("category", TType.STRING, (short) 7);
        Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
        tmpMap.put(_Fields.DEBUG, new FieldMetaData("debug", (byte) 2, new FieldValueMetaData(TType.STRING)));
        tmpMap.put(_Fields.TARGET, new FieldMetaData("target", (byte) 2, new StructMetaData(TType.STRUCT, Target.class)));
        tmpMap.put(_Fields.ID, new FieldMetaData(Constants.JSON_TAG_ID, (byte) 1, new FieldValueMetaData(TType.STRING)));
        tmpMap.put(_Fields.APP_ID, new FieldMetaData("appId", (byte) 1, new FieldValueMetaData(TType.STRING)));
        tmpMap.put(_Fields.TOPIC, new FieldMetaData("topic", (byte) 1, new FieldValueMetaData(TType.STRING)));
        tmpMap.put(_Fields.PACKAGE_NAME, new FieldMetaData("packageName", (byte) 2, new FieldValueMetaData(TType.STRING)));
        tmpMap.put(_Fields.CATEGORY, new FieldMetaData("category", (byte) 2, new FieldValueMetaData(TType.STRING)));
        metaDataMap = Collections.unmodifiableMap(tmpMap);
        FieldMetaData.addStructMetaDataMap(XmPushActionUnSubscription.class, metaDataMap);
    }

    public XmPushActionUnSubscription(String id, String appId, String topic) {
        this();
        this.id = id;
        this.appId = appId;
        this.topic = topic;
    }

    public XmPushActionUnSubscription(XmPushActionUnSubscription other) {
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
        if (other.isSetTopic()) {
            this.topic = other.topic;
        }
        if (other.isSetPackageName()) {
            this.packageName = other.packageName;
        }
        if (other.isSetCategory()) {
            this.category = other.category;
        }
    }

    public XmPushActionUnSubscription deepCopy() {
        return new XmPushActionUnSubscription(this);
    }

    public void clear() {
        this.debug = null;
        this.target = null;
        this.id = null;
        this.appId = null;
        this.topic = null;
        this.packageName = null;
        this.category = null;
    }

    public String getDebug() {
        return this.debug;
    }

    public XmPushActionUnSubscription setDebug(String debug) {
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

    public XmPushActionUnSubscription setTarget(Target target) {
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

    public XmPushActionUnSubscription setId(String id) {
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

    public XmPushActionUnSubscription setAppId(String appId) {
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

    public String getTopic() {
        return this.topic;
    }

    public XmPushActionUnSubscription setTopic(String topic) {
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

    public String getPackageName() {
        return this.packageName;
    }

    public XmPushActionUnSubscription setPackageName(String packageName) {
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

    public String getCategory() {
        return this.category;
    }

    public XmPushActionUnSubscription setCategory(String category) {
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
        switch (AnonymousClass1.$SwitchMap$com$xiaomi$xmpush$thrift$XmPushActionUnSubscription$_Fields[field.ordinal()]) {
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
                    unsetTopic();
                    return;
                } else {
                    setTopic((String) value);
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
        switch (AnonymousClass1.$SwitchMap$com$xiaomi$xmpush$thrift$XmPushActionUnSubscription$_Fields[field.ordinal()]) {
            case TTransportException.NOT_OPEN /*1*/:
                return getDebug();
            case TTransportException.ALREADY_OPEN /*2*/:
                return getTarget();
            case TTransportException.TIMED_OUT /*3*/:
                return getId();
            case TTransportException.END_OF_FILE /*4*/:
                return getAppId();
            case TProtocolException.NOT_IMPLEMENTED /*5*/:
                return getTopic();
            case TApplicationException.INTERNAL_ERROR /*6*/:
                return getPackageName();
            case TApplicationException.PROTOCOL_ERROR /*7*/:
                return getCategory();
            default:
                throw new IllegalStateException();
        }
    }

    public boolean isSet(_Fields field) {
        if (field == null) {
            throw new IllegalArgumentException();
        }
        switch (AnonymousClass1.$SwitchMap$com$xiaomi$xmpush$thrift$XmPushActionUnSubscription$_Fields[field.ordinal()]) {
            case TTransportException.NOT_OPEN /*1*/:
                return isSetDebug();
            case TTransportException.ALREADY_OPEN /*2*/:
                return isSetTarget();
            case TTransportException.TIMED_OUT /*3*/:
                return isSetId();
            case TTransportException.END_OF_FILE /*4*/:
                return isSetAppId();
            case TProtocolException.NOT_IMPLEMENTED /*5*/:
                return isSetTopic();
            case TApplicationException.INTERNAL_ERROR /*6*/:
                return isSetPackageName();
            case TApplicationException.PROTOCOL_ERROR /*7*/:
                return isSetCategory();
            default:
                throw new IllegalStateException();
        }
    }

    public boolean equals(Object that) {
        if (that != null && (that instanceof XmPushActionUnSubscription)) {
            return equals((XmPushActionUnSubscription) that);
        }
        return false;
    }

    public boolean equals(XmPushActionUnSubscription that) {
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

    public int compareTo(XmPushActionUnSubscription other) {
        if (!getClass().equals(other.getClass())) {
            return getClass().getName().compareTo(other.getClass().getName());
        }
        XmPushActionUnSubscription typedOther = other;
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
                        this.topic = iprot.readString();
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
        if (this.topic != null) {
            oprot.writeFieldBegin(TOPIC_FIELD_DESC);
            oprot.writeString(this.topic);
            oprot.writeFieldEnd();
        }
        if (this.packageName != null && isSetPackageName()) {
            oprot.writeFieldBegin(PACKAGE_NAME_FIELD_DESC);
            oprot.writeString(this.packageName);
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
        StringBuilder sb = new StringBuilder("XmPushActionUnSubscription(");
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
        if (!false) {
            sb.append(", ");
        }
        sb.append("topic:");
        if (this.topic == null) {
            sb.append("null");
        } else {
            sb.append(this.topic);
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
        } else if (this.topic == null) {
            throw new TProtocolException("Required field 'topic' was not present! Struct: " + toString());
        }
    }
}
