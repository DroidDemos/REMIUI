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
import org.apache.thrift.protocol.TField;
import org.apache.thrift.protocol.TMap;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.protocol.TProtocolUtil;
import org.apache.thrift.protocol.TStruct;
import org.apache.thrift.protocol.TType;
import org.apache.thrift.transport.TTransportException;

public class PushMetaInfo implements TBase<PushMetaInfo, _Fields>, Serializable, Cloneable {
    private static final TField DESCRIPTION_FIELD_DESC;
    private static final TField EXTRA_FIELD_DESC;
    private static final TField ID_FIELD_DESC;
    private static final TField MESSAGE_TS_FIELD_DESC;
    private static final TField NOTIFY_ID_FIELD_DESC;
    private static final TField NOTIFY_TYPE_FIELD_DESC;
    private static final TField PASS_THROUGH_FIELD_DESC;
    private static final TStruct STRUCT_DESC;
    private static final TField TITLE_FIELD_DESC;
    private static final TField TOPIC_FIELD_DESC;
    private static final TField URL_FIELD_DESC;
    private static final int __MESSAGETS_ISSET_ID = 0;
    private static final int __NOTIFYID_ISSET_ID = 3;
    private static final int __NOTIFYTYPE_ISSET_ID = 1;
    private static final int __PASSTHROUGH_ISSET_ID = 2;
    public static final Map<_Fields, FieldMetaData> metaDataMap;
    private BitSet __isset_bit_vector;
    public String description;
    public Map<String, String> extra;
    public String id;
    public long messageTs;
    public int notifyId;
    public int notifyType;
    public int passThrough;
    public String title;
    public String topic;
    public String url;

    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$xiaomi$xmpush$thrift$PushMetaInfo$_Fields;

        static {
            $SwitchMap$com$xiaomi$xmpush$thrift$PushMetaInfo$_Fields = new int[_Fields.values().length];
            try {
                $SwitchMap$com$xiaomi$xmpush$thrift$PushMetaInfo$_Fields[_Fields.ID.ordinal()] = PushMetaInfo.__NOTIFYTYPE_ISSET_ID;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$xiaomi$xmpush$thrift$PushMetaInfo$_Fields[_Fields.MESSAGE_TS.ordinal()] = PushMetaInfo.__PASSTHROUGH_ISSET_ID;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$xiaomi$xmpush$thrift$PushMetaInfo$_Fields[_Fields.TOPIC.ordinal()] = PushMetaInfo.__NOTIFYID_ISSET_ID;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$xiaomi$xmpush$thrift$PushMetaInfo$_Fields[_Fields.TITLE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$xiaomi$xmpush$thrift$PushMetaInfo$_Fields[_Fields.DESCRIPTION.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$xiaomi$xmpush$thrift$PushMetaInfo$_Fields[_Fields.NOTIFY_TYPE.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$com$xiaomi$xmpush$thrift$PushMetaInfo$_Fields[_Fields.URL.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$com$xiaomi$xmpush$thrift$PushMetaInfo$_Fields[_Fields.PASS_THROUGH.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                $SwitchMap$com$xiaomi$xmpush$thrift$PushMetaInfo$_Fields[_Fields.NOTIFY_ID.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                $SwitchMap$com$xiaomi$xmpush$thrift$PushMetaInfo$_Fields[_Fields.EXTRA.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
        }
    }

    public enum _Fields implements TFieldIdEnum {
        ID((short) 1, Constants.JSON_TAG_ID),
        MESSAGE_TS((short) 2, "messageTs"),
        TOPIC((short) 3, "topic"),
        TITLE((short) 4, PushServiceConstants.EXTRA_RECIPIENT_TITLE),
        DESCRIPTION((short) 5, "description"),
        NOTIFY_TYPE((short) 6, "notifyType"),
        URL((short) 7, "url"),
        PASS_THROUGH((short) 8, "passThrough"),
        NOTIFY_ID((short) 9, "notifyId"),
        EXTRA((short) 10, "extra");
        
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
                case PushMetaInfo.__NOTIFYTYPE_ISSET_ID /*1*/:
                    return ID;
                case PushMetaInfo.__PASSTHROUGH_ISSET_ID /*2*/:
                    return MESSAGE_TS;
                case PushMetaInfo.__NOTIFYID_ISSET_ID /*3*/:
                    return TOPIC;
                case TTransportException.END_OF_FILE /*4*/:
                    return TITLE;
                case TProtocolException.NOT_IMPLEMENTED /*5*/:
                    return DESCRIPTION;
                case TApplicationException.INTERNAL_ERROR /*6*/:
                    return NOTIFY_TYPE;
                case TApplicationException.PROTOCOL_ERROR /*7*/:
                    return URL;
                case PushConstants.ERROR_RECEIVE_TIMEOUT /*8*/:
                    return PASS_THROUGH;
                case PushConstants.ERROR_READ_ERROR /*9*/:
                    return NOTIFY_ID;
                case Constants.VALUE_MAX_FAILEDCOUNT /*10*/:
                    return EXTRA;
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
        STRUCT_DESC = new TStruct("PushMetaInfo");
        ID_FIELD_DESC = new TField(Constants.JSON_TAG_ID, TType.STRING, (short) 1);
        MESSAGE_TS_FIELD_DESC = new TField("messageTs", (byte) 10, (short) 2);
        TOPIC_FIELD_DESC = new TField("topic", TType.STRING, (short) 3);
        TITLE_FIELD_DESC = new TField(PushServiceConstants.EXTRA_RECIPIENT_TITLE, TType.STRING, (short) 4);
        DESCRIPTION_FIELD_DESC = new TField("description", TType.STRING, (short) 5);
        NOTIFY_TYPE_FIELD_DESC = new TField("notifyType", (byte) 8, (short) 6);
        URL_FIELD_DESC = new TField("url", TType.STRING, (short) 7);
        PASS_THROUGH_FIELD_DESC = new TField("passThrough", (byte) 8, (short) 8);
        NOTIFY_ID_FIELD_DESC = new TField("notifyId", (byte) 8, (short) 9);
        EXTRA_FIELD_DESC = new TField("extra", TType.MAP, (short) 10);
        Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
        tmpMap.put(_Fields.ID, new FieldMetaData(Constants.JSON_TAG_ID, (byte) 1, new FieldValueMetaData(TType.STRING)));
        tmpMap.put(_Fields.MESSAGE_TS, new FieldMetaData("messageTs", (byte) 1, new FieldValueMetaData((byte) 10)));
        tmpMap.put(_Fields.TOPIC, new FieldMetaData("topic", (byte) 2, new FieldValueMetaData(TType.STRING)));
        tmpMap.put(_Fields.TITLE, new FieldMetaData(PushServiceConstants.EXTRA_RECIPIENT_TITLE, (byte) 2, new FieldValueMetaData(TType.STRING)));
        tmpMap.put(_Fields.DESCRIPTION, new FieldMetaData("description", (byte) 2, new FieldValueMetaData(TType.STRING)));
        tmpMap.put(_Fields.NOTIFY_TYPE, new FieldMetaData("notifyType", (byte) 2, new FieldValueMetaData((byte) 8)));
        tmpMap.put(_Fields.URL, new FieldMetaData("url", (byte) 2, new FieldValueMetaData(TType.STRING)));
        tmpMap.put(_Fields.PASS_THROUGH, new FieldMetaData("passThrough", (byte) 2, new FieldValueMetaData((byte) 8)));
        tmpMap.put(_Fields.NOTIFY_ID, new FieldMetaData("notifyId", (byte) 2, new FieldValueMetaData((byte) 8)));
        tmpMap.put(_Fields.EXTRA, new FieldMetaData("extra", (byte) 2, new MapMetaData(TType.MAP, new FieldValueMetaData(TType.STRING), new FieldValueMetaData(TType.STRING))));
        metaDataMap = Collections.unmodifiableMap(tmpMap);
        FieldMetaData.addStructMetaDataMap(PushMetaInfo.class, metaDataMap);
    }

    public PushMetaInfo() {
        this.__isset_bit_vector = new BitSet(4);
    }

    public PushMetaInfo(String id, long messageTs) {
        this();
        this.id = id;
        this.messageTs = messageTs;
        setMessageTsIsSet(true);
    }

    public PushMetaInfo(PushMetaInfo other) {
        this.__isset_bit_vector = new BitSet(4);
        this.__isset_bit_vector.clear();
        this.__isset_bit_vector.or(other.__isset_bit_vector);
        if (other.isSetId()) {
            this.id = other.id;
        }
        this.messageTs = other.messageTs;
        if (other.isSetTopic()) {
            this.topic = other.topic;
        }
        if (other.isSetTitle()) {
            this.title = other.title;
        }
        if (other.isSetDescription()) {
            this.description = other.description;
        }
        this.notifyType = other.notifyType;
        if (other.isSetUrl()) {
            this.url = other.url;
        }
        this.passThrough = other.passThrough;
        this.notifyId = other.notifyId;
        if (other.isSetExtra()) {
            Map<String, String> __this__extra = new HashMap();
            for (Entry<String, String> other_element : other.extra.entrySet()) {
                __this__extra.put((String) other_element.getKey(), (String) other_element.getValue());
            }
            this.extra = __this__extra;
        }
    }

    public PushMetaInfo deepCopy() {
        return new PushMetaInfo(this);
    }

    public void clear() {
        this.id = null;
        setMessageTsIsSet(false);
        this.messageTs = 0;
        this.topic = null;
        this.title = null;
        this.description = null;
        setNotifyTypeIsSet(false);
        this.notifyType = __MESSAGETS_ISSET_ID;
        this.url = null;
        setPassThroughIsSet(false);
        this.passThrough = __MESSAGETS_ISSET_ID;
        setNotifyIdIsSet(false);
        this.notifyId = __MESSAGETS_ISSET_ID;
        this.extra = null;
    }

    public String getId() {
        return this.id;
    }

    public PushMetaInfo setId(String id) {
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

    public long getMessageTs() {
        return this.messageTs;
    }

    public PushMetaInfo setMessageTs(long messageTs) {
        this.messageTs = messageTs;
        setMessageTsIsSet(true);
        return this;
    }

    public void unsetMessageTs() {
        this.__isset_bit_vector.clear(__MESSAGETS_ISSET_ID);
    }

    public boolean isSetMessageTs() {
        return this.__isset_bit_vector.get(__MESSAGETS_ISSET_ID);
    }

    public void setMessageTsIsSet(boolean value) {
        this.__isset_bit_vector.set(__MESSAGETS_ISSET_ID, value);
    }

    public String getTopic() {
        return this.topic;
    }

    public PushMetaInfo setTopic(String topic) {
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

    public String getTitle() {
        return this.title;
    }

    public PushMetaInfo setTitle(String title) {
        this.title = title;
        return this;
    }

    public void unsetTitle() {
        this.title = null;
    }

    public boolean isSetTitle() {
        return this.title != null;
    }

    public void setTitleIsSet(boolean value) {
        if (!value) {
            this.title = null;
        }
    }

    public String getDescription() {
        return this.description;
    }

    public PushMetaInfo setDescription(String description) {
        this.description = description;
        return this;
    }

    public void unsetDescription() {
        this.description = null;
    }

    public boolean isSetDescription() {
        return this.description != null;
    }

    public void setDescriptionIsSet(boolean value) {
        if (!value) {
            this.description = null;
        }
    }

    public int getNotifyType() {
        return this.notifyType;
    }

    public PushMetaInfo setNotifyType(int notifyType) {
        this.notifyType = notifyType;
        setNotifyTypeIsSet(true);
        return this;
    }

    public void unsetNotifyType() {
        this.__isset_bit_vector.clear(__NOTIFYTYPE_ISSET_ID);
    }

    public boolean isSetNotifyType() {
        return this.__isset_bit_vector.get(__NOTIFYTYPE_ISSET_ID);
    }

    public void setNotifyTypeIsSet(boolean value) {
        this.__isset_bit_vector.set(__NOTIFYTYPE_ISSET_ID, value);
    }

    public String getUrl() {
        return this.url;
    }

    public PushMetaInfo setUrl(String url) {
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

    public int getPassThrough() {
        return this.passThrough;
    }

    public PushMetaInfo setPassThrough(int passThrough) {
        this.passThrough = passThrough;
        setPassThroughIsSet(true);
        return this;
    }

    public void unsetPassThrough() {
        this.__isset_bit_vector.clear(__PASSTHROUGH_ISSET_ID);
    }

    public boolean isSetPassThrough() {
        return this.__isset_bit_vector.get(__PASSTHROUGH_ISSET_ID);
    }

    public void setPassThroughIsSet(boolean value) {
        this.__isset_bit_vector.set(__PASSTHROUGH_ISSET_ID, value);
    }

    public int getNotifyId() {
        return this.notifyId;
    }

    public PushMetaInfo setNotifyId(int notifyId) {
        this.notifyId = notifyId;
        setNotifyIdIsSet(true);
        return this;
    }

    public void unsetNotifyId() {
        this.__isset_bit_vector.clear(__NOTIFYID_ISSET_ID);
    }

    public boolean isSetNotifyId() {
        return this.__isset_bit_vector.get(__NOTIFYID_ISSET_ID);
    }

    public void setNotifyIdIsSet(boolean value) {
        this.__isset_bit_vector.set(__NOTIFYID_ISSET_ID, value);
    }

    public int getExtraSize() {
        return this.extra == null ? __MESSAGETS_ISSET_ID : this.extra.size();
    }

    public void putToExtra(String key, String val) {
        if (this.extra == null) {
            this.extra = new HashMap();
        }
        this.extra.put(key, val);
    }

    public Map<String, String> getExtra() {
        return this.extra;
    }

    public PushMetaInfo setExtra(Map<String, String> extra) {
        this.extra = extra;
        return this;
    }

    public void unsetExtra() {
        this.extra = null;
    }

    public boolean isSetExtra() {
        return this.extra != null;
    }

    public void setExtraIsSet(boolean value) {
        if (!value) {
            this.extra = null;
        }
    }

    public void setFieldValue(_Fields field, Object value) {
        switch (AnonymousClass1.$SwitchMap$com$xiaomi$xmpush$thrift$PushMetaInfo$_Fields[field.ordinal()]) {
            case __NOTIFYTYPE_ISSET_ID /*1*/:
                if (value == null) {
                    unsetId();
                    return;
                } else {
                    setId((String) value);
                    return;
                }
            case __PASSTHROUGH_ISSET_ID /*2*/:
                if (value == null) {
                    unsetMessageTs();
                    return;
                } else {
                    setMessageTs(((Long) value).longValue());
                    return;
                }
            case __NOTIFYID_ISSET_ID /*3*/:
                if (value == null) {
                    unsetTopic();
                    return;
                } else {
                    setTopic((String) value);
                    return;
                }
            case TTransportException.END_OF_FILE /*4*/:
                if (value == null) {
                    unsetTitle();
                    return;
                } else {
                    setTitle((String) value);
                    return;
                }
            case TProtocolException.NOT_IMPLEMENTED /*5*/:
                if (value == null) {
                    unsetDescription();
                    return;
                } else {
                    setDescription((String) value);
                    return;
                }
            case TApplicationException.INTERNAL_ERROR /*6*/:
                if (value == null) {
                    unsetNotifyType();
                    return;
                } else {
                    setNotifyType(((Integer) value).intValue());
                    return;
                }
            case TApplicationException.PROTOCOL_ERROR /*7*/:
                if (value == null) {
                    unsetUrl();
                    return;
                } else {
                    setUrl((String) value);
                    return;
                }
            case PushConstants.ERROR_RECEIVE_TIMEOUT /*8*/:
                if (value == null) {
                    unsetPassThrough();
                    return;
                } else {
                    setPassThrough(((Integer) value).intValue());
                    return;
                }
            case PushConstants.ERROR_READ_ERROR /*9*/:
                if (value == null) {
                    unsetNotifyId();
                    return;
                } else {
                    setNotifyId(((Integer) value).intValue());
                    return;
                }
            case Constants.VALUE_MAX_FAILEDCOUNT /*10*/:
                if (value == null) {
                    unsetExtra();
                    return;
                } else {
                    setExtra((Map) value);
                    return;
                }
            default:
                return;
        }
    }

    public Object getFieldValue(_Fields field) {
        switch (AnonymousClass1.$SwitchMap$com$xiaomi$xmpush$thrift$PushMetaInfo$_Fields[field.ordinal()]) {
            case __NOTIFYTYPE_ISSET_ID /*1*/:
                return getId();
            case __PASSTHROUGH_ISSET_ID /*2*/:
                return new Long(getMessageTs());
            case __NOTIFYID_ISSET_ID /*3*/:
                return getTopic();
            case TTransportException.END_OF_FILE /*4*/:
                return getTitle();
            case TProtocolException.NOT_IMPLEMENTED /*5*/:
                return getDescription();
            case TApplicationException.INTERNAL_ERROR /*6*/:
                return new Integer(getNotifyType());
            case TApplicationException.PROTOCOL_ERROR /*7*/:
                return getUrl();
            case PushConstants.ERROR_RECEIVE_TIMEOUT /*8*/:
                return new Integer(getPassThrough());
            case PushConstants.ERROR_READ_ERROR /*9*/:
                return new Integer(getNotifyId());
            case Constants.VALUE_MAX_FAILEDCOUNT /*10*/:
                return getExtra();
            default:
                throw new IllegalStateException();
        }
    }

    public boolean isSet(_Fields field) {
        if (field == null) {
            throw new IllegalArgumentException();
        }
        switch (AnonymousClass1.$SwitchMap$com$xiaomi$xmpush$thrift$PushMetaInfo$_Fields[field.ordinal()]) {
            case __NOTIFYTYPE_ISSET_ID /*1*/:
                return isSetId();
            case __PASSTHROUGH_ISSET_ID /*2*/:
                return isSetMessageTs();
            case __NOTIFYID_ISSET_ID /*3*/:
                return isSetTopic();
            case TTransportException.END_OF_FILE /*4*/:
                return isSetTitle();
            case TProtocolException.NOT_IMPLEMENTED /*5*/:
                return isSetDescription();
            case TApplicationException.INTERNAL_ERROR /*6*/:
                return isSetNotifyType();
            case TApplicationException.PROTOCOL_ERROR /*7*/:
                return isSetUrl();
            case PushConstants.ERROR_RECEIVE_TIMEOUT /*8*/:
                return isSetPassThrough();
            case PushConstants.ERROR_READ_ERROR /*9*/:
                return isSetNotifyId();
            case Constants.VALUE_MAX_FAILEDCOUNT /*10*/:
                return isSetExtra();
            default:
                throw new IllegalStateException();
        }
    }

    public boolean equals(Object that) {
        if (that != null && (that instanceof PushMetaInfo)) {
            return equals((PushMetaInfo) that);
        }
        return false;
    }

    public boolean equals(PushMetaInfo that) {
        if (that == null) {
            return false;
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
        if (!(__NOTIFYTYPE_ISSET_ID == null && __NOTIFYTYPE_ISSET_ID == null)) {
            if (__NOTIFYTYPE_ISSET_ID == null || __NOTIFYTYPE_ISSET_ID == null) {
                return false;
            }
            if (this.messageTs != that.messageTs) {
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
        boolean this_present_title = isSetTitle();
        boolean that_present_title = that.isSetTitle();
        if (this_present_title || that_present_title) {
            if (!this_present_title || !that_present_title) {
                return false;
            }
            if (!this.title.equals(that.title)) {
                return false;
            }
        }
        boolean this_present_description = isSetDescription();
        boolean that_present_description = that.isSetDescription();
        if (this_present_description || that_present_description) {
            if (!this_present_description || !that_present_description) {
                return false;
            }
            if (!this.description.equals(that.description)) {
                return false;
            }
        }
        boolean this_present_notifyType = isSetNotifyType();
        boolean that_present_notifyType = that.isSetNotifyType();
        if (this_present_notifyType || that_present_notifyType) {
            if (!this_present_notifyType || !that_present_notifyType) {
                return false;
            }
            if (this.notifyType != that.notifyType) {
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
        boolean this_present_passThrough = isSetPassThrough();
        boolean that_present_passThrough = that.isSetPassThrough();
        if (this_present_passThrough || that_present_passThrough) {
            if (!this_present_passThrough || !that_present_passThrough) {
                return false;
            }
            if (this.passThrough != that.passThrough) {
                return false;
            }
        }
        boolean this_present_notifyId = isSetNotifyId();
        boolean that_present_notifyId = that.isSetNotifyId();
        if (this_present_notifyId || that_present_notifyId) {
            if (!this_present_notifyId || !that_present_notifyId) {
                return false;
            }
            if (this.notifyId != that.notifyId) {
                return false;
            }
        }
        boolean this_present_extra = isSetExtra();
        boolean that_present_extra = that.isSetExtra();
        if (this_present_extra || that_present_extra) {
            if (!this_present_extra || !that_present_extra) {
                return false;
            }
            if (!this.extra.equals(that.extra)) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        return __MESSAGETS_ISSET_ID;
    }

    public int compareTo(PushMetaInfo other) {
        if (!getClass().equals(other.getClass())) {
            return getClass().getName().compareTo(other.getClass().getName());
        }
        PushMetaInfo typedOther = other;
        int lastComparison = Boolean.valueOf(isSetId()).compareTo(Boolean.valueOf(typedOther.isSetId()));
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetId()) {
            lastComparison = TBaseHelper.compareTo(this.id, typedOther.id);
            if (lastComparison != 0) {
                return lastComparison;
            }
        }
        lastComparison = Boolean.valueOf(isSetMessageTs()).compareTo(Boolean.valueOf(typedOther.isSetMessageTs()));
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetMessageTs()) {
            lastComparison = TBaseHelper.compareTo(this.messageTs, typedOther.messageTs);
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
        lastComparison = Boolean.valueOf(isSetTitle()).compareTo(Boolean.valueOf(typedOther.isSetTitle()));
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetTitle()) {
            lastComparison = TBaseHelper.compareTo(this.title, typedOther.title);
            if (lastComparison != 0) {
                return lastComparison;
            }
        }
        lastComparison = Boolean.valueOf(isSetDescription()).compareTo(Boolean.valueOf(typedOther.isSetDescription()));
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetDescription()) {
            lastComparison = TBaseHelper.compareTo(this.description, typedOther.description);
            if (lastComparison != 0) {
                return lastComparison;
            }
        }
        lastComparison = Boolean.valueOf(isSetNotifyType()).compareTo(Boolean.valueOf(typedOther.isSetNotifyType()));
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetNotifyType()) {
            lastComparison = TBaseHelper.compareTo(this.notifyType, typedOther.notifyType);
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
        lastComparison = Boolean.valueOf(isSetPassThrough()).compareTo(Boolean.valueOf(typedOther.isSetPassThrough()));
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetPassThrough()) {
            lastComparison = TBaseHelper.compareTo(this.passThrough, typedOther.passThrough);
            if (lastComparison != 0) {
                return lastComparison;
            }
        }
        lastComparison = Boolean.valueOf(isSetNotifyId()).compareTo(Boolean.valueOf(typedOther.isSetNotifyId()));
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetNotifyId()) {
            lastComparison = TBaseHelper.compareTo(this.notifyId, typedOther.notifyId);
            if (lastComparison != 0) {
                return lastComparison;
            }
        }
        lastComparison = Boolean.valueOf(isSetExtra()).compareTo(Boolean.valueOf(typedOther.isSetExtra()));
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetExtra()) {
            lastComparison = TBaseHelper.compareTo(this.extra, typedOther.extra);
            if (lastComparison != 0) {
                return lastComparison;
            }
        }
        return __MESSAGETS_ISSET_ID;
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
                if (isSetMessageTs()) {
                    validate();
                    return;
                }
                throw new TProtocolException("Required field 'messageTs' was not found in serialized data! Struct: " + toString());
            }
            switch (field.id) {
                case __NOTIFYTYPE_ISSET_ID /*1*/:
                    if (field.type != TType.STRING) {
                        TProtocolUtil.skip(iprot, field.type);
                        break;
                    } else {
                        this.id = iprot.readString();
                        break;
                    }
                case __PASSTHROUGH_ISSET_ID /*2*/:
                    if (field.type != (byte) 10) {
                        TProtocolUtil.skip(iprot, field.type);
                        break;
                    }
                    this.messageTs = iprot.readI64();
                    setMessageTsIsSet(true);
                    break;
                case __NOTIFYID_ISSET_ID /*3*/:
                    if (field.type != TType.STRING) {
                        TProtocolUtil.skip(iprot, field.type);
                        break;
                    } else {
                        this.topic = iprot.readString();
                        break;
                    }
                case TTransportException.END_OF_FILE /*4*/:
                    if (field.type != TType.STRING) {
                        TProtocolUtil.skip(iprot, field.type);
                        break;
                    } else {
                        this.title = iprot.readString();
                        break;
                    }
                case TProtocolException.NOT_IMPLEMENTED /*5*/:
                    if (field.type != TType.STRING) {
                        TProtocolUtil.skip(iprot, field.type);
                        break;
                    } else {
                        this.description = iprot.readString();
                        break;
                    }
                case TApplicationException.INTERNAL_ERROR /*6*/:
                    if (field.type != (byte) 8) {
                        TProtocolUtil.skip(iprot, field.type);
                        break;
                    }
                    this.notifyType = iprot.readI32();
                    setNotifyTypeIsSet(true);
                    break;
                case TApplicationException.PROTOCOL_ERROR /*7*/:
                    if (field.type != TType.STRING) {
                        TProtocolUtil.skip(iprot, field.type);
                        break;
                    } else {
                        this.url = iprot.readString();
                        break;
                    }
                case PushConstants.ERROR_RECEIVE_TIMEOUT /*8*/:
                    if (field.type != (byte) 8) {
                        TProtocolUtil.skip(iprot, field.type);
                        break;
                    }
                    this.passThrough = iprot.readI32();
                    setPassThroughIsSet(true);
                    break;
                case PushConstants.ERROR_READ_ERROR /*9*/:
                    if (field.type != (byte) 8) {
                        TProtocolUtil.skip(iprot, field.type);
                        break;
                    }
                    this.notifyId = iprot.readI32();
                    setNotifyIdIsSet(true);
                    break;
                case Constants.VALUE_MAX_FAILEDCOUNT /*10*/:
                    if (field.type != TType.MAP) {
                        TProtocolUtil.skip(iprot, field.type);
                        break;
                    }
                    TMap _map0 = iprot.readMapBegin();
                    this.extra = new HashMap(_map0.size * __PASSTHROUGH_ISSET_ID);
                    for (int _i1 = __MESSAGETS_ISSET_ID; _i1 < _map0.size; _i1 += __NOTIFYTYPE_ISSET_ID) {
                        this.extra.put(iprot.readString(), iprot.readString());
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
        if (this.id != null) {
            oprot.writeFieldBegin(ID_FIELD_DESC);
            oprot.writeString(this.id);
            oprot.writeFieldEnd();
        }
        oprot.writeFieldBegin(MESSAGE_TS_FIELD_DESC);
        oprot.writeI64(this.messageTs);
        oprot.writeFieldEnd();
        if (this.topic != null && isSetTopic()) {
            oprot.writeFieldBegin(TOPIC_FIELD_DESC);
            oprot.writeString(this.topic);
            oprot.writeFieldEnd();
        }
        if (this.title != null && isSetTitle()) {
            oprot.writeFieldBegin(TITLE_FIELD_DESC);
            oprot.writeString(this.title);
            oprot.writeFieldEnd();
        }
        if (this.description != null && isSetDescription()) {
            oprot.writeFieldBegin(DESCRIPTION_FIELD_DESC);
            oprot.writeString(this.description);
            oprot.writeFieldEnd();
        }
        if (isSetNotifyType()) {
            oprot.writeFieldBegin(NOTIFY_TYPE_FIELD_DESC);
            oprot.writeI32(this.notifyType);
            oprot.writeFieldEnd();
        }
        if (this.url != null && isSetUrl()) {
            oprot.writeFieldBegin(URL_FIELD_DESC);
            oprot.writeString(this.url);
            oprot.writeFieldEnd();
        }
        if (isSetPassThrough()) {
            oprot.writeFieldBegin(PASS_THROUGH_FIELD_DESC);
            oprot.writeI32(this.passThrough);
            oprot.writeFieldEnd();
        }
        if (isSetNotifyId()) {
            oprot.writeFieldBegin(NOTIFY_ID_FIELD_DESC);
            oprot.writeI32(this.notifyId);
            oprot.writeFieldEnd();
        }
        if (this.extra != null && isSetExtra()) {
            oprot.writeFieldBegin(EXTRA_FIELD_DESC);
            oprot.writeMapBegin(new TMap(TType.STRING, TType.STRING, this.extra.size()));
            for (Entry<String, String> _iter4 : this.extra.entrySet()) {
                oprot.writeString((String) _iter4.getKey());
                oprot.writeString((String) _iter4.getValue());
            }
            oprot.writeMapEnd();
            oprot.writeFieldEnd();
        }
        oprot.writeFieldStop();
        oprot.writeStructEnd();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("PushMetaInfo(");
        sb.append("id:");
        if (this.id == null) {
            sb.append("null");
        } else {
            sb.append(this.id);
        }
        if (!false) {
            sb.append(", ");
        }
        sb.append("messageTs:");
        sb.append(this.messageTs);
        boolean first = false;
        if (isSetTopic()) {
            if (__MESSAGETS_ISSET_ID == null) {
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
        if (isSetTitle()) {
            if (!first) {
                sb.append(", ");
            }
            sb.append("title:");
            if (this.title == null) {
                sb.append("null");
            } else {
                sb.append(this.title);
            }
            first = false;
        }
        if (isSetDescription()) {
            if (!first) {
                sb.append(", ");
            }
            sb.append("description:");
            if (this.description == null) {
                sb.append("null");
            } else {
                sb.append(this.description);
            }
            first = false;
        }
        if (isSetNotifyType()) {
            if (!first) {
                sb.append(", ");
            }
            sb.append("notifyType:");
            sb.append(this.notifyType);
            first = false;
        }
        if (isSetUrl()) {
            if (!first) {
                sb.append(", ");
            }
            sb.append("url:");
            if (this.url == null) {
                sb.append("null");
            } else {
                sb.append(this.url);
            }
            first = false;
        }
        if (isSetPassThrough()) {
            if (!first) {
                sb.append(", ");
            }
            sb.append("passThrough:");
            sb.append(this.passThrough);
            first = false;
        }
        if (isSetNotifyId()) {
            if (!first) {
                sb.append(", ");
            }
            sb.append("notifyId:");
            sb.append(this.notifyId);
            first = false;
        }
        if (isSetExtra()) {
            if (!first) {
                sb.append(", ");
            }
            sb.append("extra:");
            if (this.extra == null) {
                sb.append("null");
            } else {
                sb.append(this.extra);
            }
        }
        sb.append(")");
        return sb.toString();
    }

    public void validate() throws TException {
        if (this.id == null) {
            throw new TProtocolException("Required field 'id' was not present! Struct: " + toString());
        }
    }
}
