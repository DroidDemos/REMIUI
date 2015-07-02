package com.xiaomi.xmpush.thrift;

import com.xiaomi.push.service.PushServiceConstants;
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
import org.apache.thrift.protocol.TField;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.protocol.TProtocolUtil;
import org.apache.thrift.protocol.TStruct;
import org.apache.thrift.protocol.TType;
import org.apache.thrift.transport.TTransportException;

public class Target implements TBase<Target, _Fields>, Serializable, Cloneable {
    private static final TField CHANNEL_ID_FIELD_DESC;
    private static final TField IS_PREVIEW_FIELD_DESC;
    private static final TField RESOURCE_FIELD_DESC;
    private static final TField SERVER_FIELD_DESC;
    private static final TStruct STRUCT_DESC;
    private static final TField USER_ID_FIELD_DESC;
    private static final int __CHANNELID_ISSET_ID = 0;
    private static final int __ISPREVIEW_ISSET_ID = 1;
    public static final Map<_Fields, FieldMetaData> metaDataMap;
    private BitSet __isset_bit_vector;
    public long channelId;
    public boolean isPreview;
    public String resource;
    public String server;
    public String userId;

    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$xiaomi$xmpush$thrift$Target$_Fields;

        static {
            $SwitchMap$com$xiaomi$xmpush$thrift$Target$_Fields = new int[_Fields.values().length];
            try {
                $SwitchMap$com$xiaomi$xmpush$thrift$Target$_Fields[_Fields.CHANNEL_ID.ordinal()] = Target.__ISPREVIEW_ISSET_ID;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$xiaomi$xmpush$thrift$Target$_Fields[_Fields.USER_ID.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$xiaomi$xmpush$thrift$Target$_Fields[_Fields.SERVER.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$xiaomi$xmpush$thrift$Target$_Fields[_Fields.RESOURCE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$xiaomi$xmpush$thrift$Target$_Fields[_Fields.IS_PREVIEW.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
        }
    }

    public enum _Fields implements TFieldIdEnum {
        CHANNEL_ID((short) 1, "channelId"),
        USER_ID((short) 2, Constants.JSON_TAG_USERID),
        SERVER((short) 3, "server"),
        RESOURCE((short) 4, "resource"),
        IS_PREVIEW((short) 5, "isPreview");
        
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
                case Target.__ISPREVIEW_ISSET_ID /*1*/:
                    return CHANNEL_ID;
                case TTransportException.ALREADY_OPEN /*2*/:
                    return USER_ID;
                case TTransportException.TIMED_OUT /*3*/:
                    return SERVER;
                case TTransportException.END_OF_FILE /*4*/:
                    return RESOURCE;
                case TProtocolException.NOT_IMPLEMENTED /*5*/:
                    return IS_PREVIEW;
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
        STRUCT_DESC = new TStruct("Target");
        CHANNEL_ID_FIELD_DESC = new TField("channelId", (byte) 10, (short) 1);
        USER_ID_FIELD_DESC = new TField(Constants.JSON_TAG_USERID, TType.STRING, (short) 2);
        SERVER_FIELD_DESC = new TField("server", TType.STRING, (short) 3);
        RESOURCE_FIELD_DESC = new TField("resource", TType.STRING, (short) 4);
        IS_PREVIEW_FIELD_DESC = new TField("isPreview", (byte) 2, (short) 5);
        Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
        tmpMap.put(_Fields.CHANNEL_ID, new FieldMetaData("channelId", (byte) 1, new FieldValueMetaData((byte) 10)));
        tmpMap.put(_Fields.USER_ID, new FieldMetaData(Constants.JSON_TAG_USERID, (byte) 1, new FieldValueMetaData(TType.STRING)));
        tmpMap.put(_Fields.SERVER, new FieldMetaData("server", (byte) 2, new FieldValueMetaData(TType.STRING)));
        tmpMap.put(_Fields.RESOURCE, new FieldMetaData("resource", (byte) 2, new FieldValueMetaData(TType.STRING)));
        tmpMap.put(_Fields.IS_PREVIEW, new FieldMetaData("isPreview", (byte) 2, new FieldValueMetaData((byte) 2)));
        metaDataMap = Collections.unmodifiableMap(tmpMap);
        FieldMetaData.addStructMetaDataMap(Target.class, metaDataMap);
    }

    public Target() {
        this.__isset_bit_vector = new BitSet(2);
        this.channelId = 5;
        this.server = PushServiceConstants.XMPP_SERVICE_NAME;
        this.resource = "";
        this.isPreview = false;
    }

    public Target(long channelId, String userId) {
        this();
        this.channelId = channelId;
        setChannelIdIsSet(true);
        this.userId = userId;
    }

    public Target(Target other) {
        this.__isset_bit_vector = new BitSet(2);
        this.__isset_bit_vector.clear();
        this.__isset_bit_vector.or(other.__isset_bit_vector);
        this.channelId = other.channelId;
        if (other.isSetUserId()) {
            this.userId = other.userId;
        }
        if (other.isSetServer()) {
            this.server = other.server;
        }
        if (other.isSetResource()) {
            this.resource = other.resource;
        }
        this.isPreview = other.isPreview;
    }

    public Target deepCopy() {
        return new Target(this);
    }

    public void clear() {
        this.channelId = 5;
        this.userId = null;
        this.server = PushServiceConstants.XMPP_SERVICE_NAME;
        this.resource = "";
        this.isPreview = false;
    }

    public long getChannelId() {
        return this.channelId;
    }

    public Target setChannelId(long channelId) {
        this.channelId = channelId;
        setChannelIdIsSet(true);
        return this;
    }

    public void unsetChannelId() {
        this.__isset_bit_vector.clear(__CHANNELID_ISSET_ID);
    }

    public boolean isSetChannelId() {
        return this.__isset_bit_vector.get(__CHANNELID_ISSET_ID);
    }

    public void setChannelIdIsSet(boolean value) {
        this.__isset_bit_vector.set(__CHANNELID_ISSET_ID, value);
    }

    public String getUserId() {
        return this.userId;
    }

    public Target setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public void unsetUserId() {
        this.userId = null;
    }

    public boolean isSetUserId() {
        return this.userId != null;
    }

    public void setUserIdIsSet(boolean value) {
        if (!value) {
            this.userId = null;
        }
    }

    public String getServer() {
        return this.server;
    }

    public Target setServer(String server) {
        this.server = server;
        return this;
    }

    public void unsetServer() {
        this.server = null;
    }

    public boolean isSetServer() {
        return this.server != null;
    }

    public void setServerIsSet(boolean value) {
        if (!value) {
            this.server = null;
        }
    }

    public String getResource() {
        return this.resource;
    }

    public Target setResource(String resource) {
        this.resource = resource;
        return this;
    }

    public void unsetResource() {
        this.resource = null;
    }

    public boolean isSetResource() {
        return this.resource != null;
    }

    public void setResourceIsSet(boolean value) {
        if (!value) {
            this.resource = null;
        }
    }

    public boolean isIsPreview() {
        return this.isPreview;
    }

    public Target setIsPreview(boolean isPreview) {
        this.isPreview = isPreview;
        setIsPreviewIsSet(true);
        return this;
    }

    public void unsetIsPreview() {
        this.__isset_bit_vector.clear(__ISPREVIEW_ISSET_ID);
    }

    public boolean isSetIsPreview() {
        return this.__isset_bit_vector.get(__ISPREVIEW_ISSET_ID);
    }

    public void setIsPreviewIsSet(boolean value) {
        this.__isset_bit_vector.set(__ISPREVIEW_ISSET_ID, value);
    }

    public void setFieldValue(_Fields field, Object value) {
        switch (AnonymousClass1.$SwitchMap$com$xiaomi$xmpush$thrift$Target$_Fields[field.ordinal()]) {
            case __ISPREVIEW_ISSET_ID /*1*/:
                if (value == null) {
                    unsetChannelId();
                    return;
                } else {
                    setChannelId(((Long) value).longValue());
                    return;
                }
            case TTransportException.ALREADY_OPEN /*2*/:
                if (value == null) {
                    unsetUserId();
                    return;
                } else {
                    setUserId((String) value);
                    return;
                }
            case TTransportException.TIMED_OUT /*3*/:
                if (value == null) {
                    unsetServer();
                    return;
                } else {
                    setServer((String) value);
                    return;
                }
            case TTransportException.END_OF_FILE /*4*/:
                if (value == null) {
                    unsetResource();
                    return;
                } else {
                    setResource((String) value);
                    return;
                }
            case TProtocolException.NOT_IMPLEMENTED /*5*/:
                if (value == null) {
                    unsetIsPreview();
                    return;
                } else {
                    setIsPreview(((Boolean) value).booleanValue());
                    return;
                }
            default:
                return;
        }
    }

    public Object getFieldValue(_Fields field) {
        switch (AnonymousClass1.$SwitchMap$com$xiaomi$xmpush$thrift$Target$_Fields[field.ordinal()]) {
            case __ISPREVIEW_ISSET_ID /*1*/:
                return new Long(getChannelId());
            case TTransportException.ALREADY_OPEN /*2*/:
                return getUserId();
            case TTransportException.TIMED_OUT /*3*/:
                return getServer();
            case TTransportException.END_OF_FILE /*4*/:
                return getResource();
            case TProtocolException.NOT_IMPLEMENTED /*5*/:
                return new Boolean(isIsPreview());
            default:
                throw new IllegalStateException();
        }
    }

    public boolean isSet(_Fields field) {
        if (field == null) {
            throw new IllegalArgumentException();
        }
        switch (AnonymousClass1.$SwitchMap$com$xiaomi$xmpush$thrift$Target$_Fields[field.ordinal()]) {
            case __ISPREVIEW_ISSET_ID /*1*/:
                return isSetChannelId();
            case TTransportException.ALREADY_OPEN /*2*/:
                return isSetUserId();
            case TTransportException.TIMED_OUT /*3*/:
                return isSetServer();
            case TTransportException.END_OF_FILE /*4*/:
                return isSetResource();
            case TProtocolException.NOT_IMPLEMENTED /*5*/:
                return isSetIsPreview();
            default:
                throw new IllegalStateException();
        }
    }

    public boolean equals(Object that) {
        if (that != null && (that instanceof Target)) {
            return equals((Target) that);
        }
        return false;
    }

    public boolean equals(Target that) {
        if (that == null) {
            return false;
        }
        if (!(__ISPREVIEW_ISSET_ID == null && __ISPREVIEW_ISSET_ID == null)) {
            if (__ISPREVIEW_ISSET_ID == null || __ISPREVIEW_ISSET_ID == null) {
                return false;
            }
            if (this.channelId != that.channelId) {
                return false;
            }
        }
        boolean this_present_userId = isSetUserId();
        boolean that_present_userId = that.isSetUserId();
        if (this_present_userId || that_present_userId) {
            if (!this_present_userId || !that_present_userId) {
                return false;
            }
            if (!this.userId.equals(that.userId)) {
                return false;
            }
        }
        boolean this_present_server = isSetServer();
        boolean that_present_server = that.isSetServer();
        if (this_present_server || that_present_server) {
            if (!this_present_server || !that_present_server) {
                return false;
            }
            if (!this.server.equals(that.server)) {
                return false;
            }
        }
        boolean this_present_resource = isSetResource();
        boolean that_present_resource = that.isSetResource();
        if (this_present_resource || that_present_resource) {
            if (!this_present_resource || !that_present_resource) {
                return false;
            }
            if (!this.resource.equals(that.resource)) {
                return false;
            }
        }
        boolean this_present_isPreview = isSetIsPreview();
        boolean that_present_isPreview = that.isSetIsPreview();
        if (this_present_isPreview || that_present_isPreview) {
            if (!this_present_isPreview || !that_present_isPreview) {
                return false;
            }
            if (this.isPreview != that.isPreview) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        return __CHANNELID_ISSET_ID;
    }

    public int compareTo(Target other) {
        if (!getClass().equals(other.getClass())) {
            return getClass().getName().compareTo(other.getClass().getName());
        }
        Target typedOther = other;
        int lastComparison = Boolean.valueOf(isSetChannelId()).compareTo(Boolean.valueOf(typedOther.isSetChannelId()));
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetChannelId()) {
            lastComparison = TBaseHelper.compareTo(this.channelId, typedOther.channelId);
            if (lastComparison != 0) {
                return lastComparison;
            }
        }
        lastComparison = Boolean.valueOf(isSetUserId()).compareTo(Boolean.valueOf(typedOther.isSetUserId()));
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetUserId()) {
            lastComparison = TBaseHelper.compareTo(this.userId, typedOther.userId);
            if (lastComparison != 0) {
                return lastComparison;
            }
        }
        lastComparison = Boolean.valueOf(isSetServer()).compareTo(Boolean.valueOf(typedOther.isSetServer()));
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetServer()) {
            lastComparison = TBaseHelper.compareTo(this.server, typedOther.server);
            if (lastComparison != 0) {
                return lastComparison;
            }
        }
        lastComparison = Boolean.valueOf(isSetResource()).compareTo(Boolean.valueOf(typedOther.isSetResource()));
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetResource()) {
            lastComparison = TBaseHelper.compareTo(this.resource, typedOther.resource);
            if (lastComparison != 0) {
                return lastComparison;
            }
        }
        lastComparison = Boolean.valueOf(isSetIsPreview()).compareTo(Boolean.valueOf(typedOther.isSetIsPreview()));
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetIsPreview()) {
            lastComparison = TBaseHelper.compareTo(this.isPreview, typedOther.isPreview);
            if (lastComparison != 0) {
                return lastComparison;
            }
        }
        return __CHANNELID_ISSET_ID;
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
                if (isSetChannelId()) {
                    validate();
                    return;
                }
                throw new TProtocolException("Required field 'channelId' was not found in serialized data! Struct: " + toString());
            }
            switch (field.id) {
                case __ISPREVIEW_ISSET_ID /*1*/:
                    if (field.type != (byte) 10) {
                        TProtocolUtil.skip(iprot, field.type);
                        break;
                    }
                    this.channelId = iprot.readI64();
                    setChannelIdIsSet(true);
                    break;
                case TTransportException.ALREADY_OPEN /*2*/:
                    if (field.type != TType.STRING) {
                        TProtocolUtil.skip(iprot, field.type);
                        break;
                    } else {
                        this.userId = iprot.readString();
                        break;
                    }
                case TTransportException.TIMED_OUT /*3*/:
                    if (field.type != TType.STRING) {
                        TProtocolUtil.skip(iprot, field.type);
                        break;
                    } else {
                        this.server = iprot.readString();
                        break;
                    }
                case TTransportException.END_OF_FILE /*4*/:
                    if (field.type != TType.STRING) {
                        TProtocolUtil.skip(iprot, field.type);
                        break;
                    } else {
                        this.resource = iprot.readString();
                        break;
                    }
                case TProtocolException.NOT_IMPLEMENTED /*5*/:
                    if (field.type != (byte) 2) {
                        TProtocolUtil.skip(iprot, field.type);
                        break;
                    }
                    this.isPreview = iprot.readBool();
                    setIsPreviewIsSet(true);
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
        oprot.writeFieldBegin(CHANNEL_ID_FIELD_DESC);
        oprot.writeI64(this.channelId);
        oprot.writeFieldEnd();
        if (this.userId != null) {
            oprot.writeFieldBegin(USER_ID_FIELD_DESC);
            oprot.writeString(this.userId);
            oprot.writeFieldEnd();
        }
        if (this.server != null && isSetServer()) {
            oprot.writeFieldBegin(SERVER_FIELD_DESC);
            oprot.writeString(this.server);
            oprot.writeFieldEnd();
        }
        if (this.resource != null && isSetResource()) {
            oprot.writeFieldBegin(RESOURCE_FIELD_DESC);
            oprot.writeString(this.resource);
            oprot.writeFieldEnd();
        }
        if (isSetIsPreview()) {
            oprot.writeFieldBegin(IS_PREVIEW_FIELD_DESC);
            oprot.writeBool(this.isPreview);
            oprot.writeFieldEnd();
        }
        oprot.writeFieldStop();
        oprot.writeStructEnd();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Target(");
        sb.append("channelId:");
        sb.append(this.channelId);
        if (!false) {
            sb.append(", ");
        }
        sb.append("userId:");
        if (this.userId == null) {
            sb.append("null");
        } else {
            sb.append(this.userId);
        }
        boolean first = false;
        if (isSetServer()) {
            if (__CHANNELID_ISSET_ID == null) {
                sb.append(", ");
            }
            sb.append("server:");
            if (this.server == null) {
                sb.append("null");
            } else {
                sb.append(this.server);
            }
            first = false;
        }
        if (isSetResource()) {
            if (!first) {
                sb.append(", ");
            }
            sb.append("resource:");
            if (this.resource == null) {
                sb.append("null");
            } else {
                sb.append(this.resource);
            }
            first = false;
        }
        if (isSetIsPreview()) {
            if (!first) {
                sb.append(", ");
            }
            sb.append("isPreview:");
            sb.append(this.isPreview);
        }
        sb.append(")");
        return sb.toString();
    }

    public void validate() throws TException {
        if (this.userId == null) {
            throw new TProtocolException("Required field 'userId' was not present! Struct: " + toString());
        }
    }
}
