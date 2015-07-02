package com.xiaomi.xmpush.thrift;

import com.xiaomi.push.service.PushConstants;
import com.xiaomi.push.service.PushServiceConstants;
import java.io.Serializable;
import java.nio.ByteBuffer;
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
import org.apache.thrift.meta_data.EnumMetaData;
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

public class XmPushActionContainer implements TBase<XmPushActionContainer, _Fields>, Serializable, Cloneable {
    private static final TField ACTION_FIELD_DESC;
    private static final TField APPID_FIELD_DESC;
    private static final TField ENCRYPT_ACTION_FIELD_DESC;
    private static final TField IS_REQUEST_FIELD_DESC;
    private static final TField META_INFO_FIELD_DESC;
    private static final TField PACKAGE_NAME_FIELD_DESC;
    private static final TField PUSH_ACTION_FIELD_DESC;
    private static final TStruct STRUCT_DESC;
    private static final TField TARGET_FIELD_DESC;
    private static final int __ENCRYPTACTION_ISSET_ID = 0;
    private static final int __ISREQUEST_ISSET_ID = 1;
    public static final Map<_Fields, FieldMetaData> metaDataMap;
    private BitSet __isset_bit_vector;
    public ActionType action;
    public String appid;
    public boolean encryptAction;
    public boolean isRequest;
    public PushMetaInfo metaInfo;
    public String packageName;
    public ByteBuffer pushAction;
    public Target target;

    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$xiaomi$xmpush$thrift$XmPushActionContainer$_Fields;

        static {
            $SwitchMap$com$xiaomi$xmpush$thrift$XmPushActionContainer$_Fields = new int[_Fields.values().length];
            try {
                $SwitchMap$com$xiaomi$xmpush$thrift$XmPushActionContainer$_Fields[_Fields.ACTION.ordinal()] = XmPushActionContainer.__ISREQUEST_ISSET_ID;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$xiaomi$xmpush$thrift$XmPushActionContainer$_Fields[_Fields.ENCRYPT_ACTION.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$xiaomi$xmpush$thrift$XmPushActionContainer$_Fields[_Fields.IS_REQUEST.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$xiaomi$xmpush$thrift$XmPushActionContainer$_Fields[_Fields.PUSH_ACTION.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$xiaomi$xmpush$thrift$XmPushActionContainer$_Fields[_Fields.APPID.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$xiaomi$xmpush$thrift$XmPushActionContainer$_Fields[_Fields.PACKAGE_NAME.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$com$xiaomi$xmpush$thrift$XmPushActionContainer$_Fields[_Fields.TARGET.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$com$xiaomi$xmpush$thrift$XmPushActionContainer$_Fields[_Fields.META_INFO.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
        }
    }

    public enum _Fields implements TFieldIdEnum {
        ACTION((short) 1, "action"),
        ENCRYPT_ACTION((short) 2, "encryptAction"),
        IS_REQUEST((short) 3, "isRequest"),
        PUSH_ACTION((short) 4, "pushAction"),
        APPID((short) 5, PushServiceConstants.EXTENSION_ATTRIBUTE_OPENPLATFORM_APPID),
        PACKAGE_NAME((short) 6, "packageName"),
        TARGET((short) 7, "target"),
        META_INFO((short) 8, "metaInfo");
        
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
                case XmPushActionContainer.__ISREQUEST_ISSET_ID /*1*/:
                    return ACTION;
                case TTransportException.ALREADY_OPEN /*2*/:
                    return ENCRYPT_ACTION;
                case TTransportException.TIMED_OUT /*3*/:
                    return IS_REQUEST;
                case TTransportException.END_OF_FILE /*4*/:
                    return PUSH_ACTION;
                case TProtocolException.NOT_IMPLEMENTED /*5*/:
                    return APPID;
                case TApplicationException.INTERNAL_ERROR /*6*/:
                    return PACKAGE_NAME;
                case TApplicationException.PROTOCOL_ERROR /*7*/:
                    return TARGET;
                case PushConstants.ERROR_RECEIVE_TIMEOUT /*8*/:
                    return META_INFO;
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
        STRUCT_DESC = new TStruct("XmPushActionContainer");
        ACTION_FIELD_DESC = new TField("action", (byte) 8, (short) 1);
        ENCRYPT_ACTION_FIELD_DESC = new TField("encryptAction", (byte) 2, (short) 2);
        IS_REQUEST_FIELD_DESC = new TField("isRequest", (byte) 2, (short) 3);
        PUSH_ACTION_FIELD_DESC = new TField("pushAction", TType.STRING, (short) 4);
        APPID_FIELD_DESC = new TField(PushServiceConstants.EXTENSION_ATTRIBUTE_OPENPLATFORM_APPID, TType.STRING, (short) 5);
        PACKAGE_NAME_FIELD_DESC = new TField("packageName", TType.STRING, (short) 6);
        TARGET_FIELD_DESC = new TField("target", TType.STRUCT, (short) 7);
        META_INFO_FIELD_DESC = new TField("metaInfo", TType.STRUCT, (short) 8);
        Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
        tmpMap.put(_Fields.ACTION, new FieldMetaData("action", (byte) 1, new EnumMetaData(TType.ENUM, ActionType.class)));
        tmpMap.put(_Fields.ENCRYPT_ACTION, new FieldMetaData("encryptAction", (byte) 1, new FieldValueMetaData((byte) 2)));
        tmpMap.put(_Fields.IS_REQUEST, new FieldMetaData("isRequest", (byte) 1, new FieldValueMetaData((byte) 2)));
        tmpMap.put(_Fields.PUSH_ACTION, new FieldMetaData("pushAction", (byte) 1, new FieldValueMetaData(TType.STRING)));
        tmpMap.put(_Fields.APPID, new FieldMetaData(PushServiceConstants.EXTENSION_ATTRIBUTE_OPENPLATFORM_APPID, (byte) 2, new FieldValueMetaData(TType.STRING)));
        tmpMap.put(_Fields.PACKAGE_NAME, new FieldMetaData("packageName", (byte) 2, new FieldValueMetaData(TType.STRING)));
        tmpMap.put(_Fields.TARGET, new FieldMetaData("target", (byte) 1, new StructMetaData(TType.STRUCT, Target.class)));
        tmpMap.put(_Fields.META_INFO, new FieldMetaData("metaInfo", (byte) 2, new StructMetaData(TType.STRUCT, PushMetaInfo.class)));
        metaDataMap = Collections.unmodifiableMap(tmpMap);
        FieldMetaData.addStructMetaDataMap(XmPushActionContainer.class, metaDataMap);
    }

    public XmPushActionContainer() {
        this.__isset_bit_vector = new BitSet(2);
        this.encryptAction = true;
        this.isRequest = true;
    }

    public XmPushActionContainer(ActionType action, boolean encryptAction, boolean isRequest, ByteBuffer pushAction, Target target) {
        this();
        this.action = action;
        this.encryptAction = encryptAction;
        setEncryptActionIsSet(true);
        this.isRequest = isRequest;
        setIsRequestIsSet(true);
        this.pushAction = pushAction;
        this.target = target;
    }

    public XmPushActionContainer(XmPushActionContainer other) {
        this.__isset_bit_vector = new BitSet(2);
        this.__isset_bit_vector.clear();
        this.__isset_bit_vector.or(other.__isset_bit_vector);
        if (other.isSetAction()) {
            this.action = other.action;
        }
        this.encryptAction = other.encryptAction;
        this.isRequest = other.isRequest;
        if (other.isSetPushAction()) {
            this.pushAction = TBaseHelper.copyBinary(other.pushAction);
        }
        if (other.isSetAppid()) {
            this.appid = other.appid;
        }
        if (other.isSetPackageName()) {
            this.packageName = other.packageName;
        }
        if (other.isSetTarget()) {
            this.target = new Target(other.target);
        }
        if (other.isSetMetaInfo()) {
            this.metaInfo = new PushMetaInfo(other.metaInfo);
        }
    }

    public XmPushActionContainer deepCopy() {
        return new XmPushActionContainer(this);
    }

    public void clear() {
        this.action = null;
        this.encryptAction = true;
        this.isRequest = true;
        this.pushAction = null;
        this.appid = null;
        this.packageName = null;
        this.target = null;
        this.metaInfo = null;
    }

    public ActionType getAction() {
        return this.action;
    }

    public XmPushActionContainer setAction(ActionType action) {
        this.action = action;
        return this;
    }

    public void unsetAction() {
        this.action = null;
    }

    public boolean isSetAction() {
        return this.action != null;
    }

    public void setActionIsSet(boolean value) {
        if (!value) {
            this.action = null;
        }
    }

    public boolean isEncryptAction() {
        return this.encryptAction;
    }

    public XmPushActionContainer setEncryptAction(boolean encryptAction) {
        this.encryptAction = encryptAction;
        setEncryptActionIsSet(true);
        return this;
    }

    public void unsetEncryptAction() {
        this.__isset_bit_vector.clear(__ENCRYPTACTION_ISSET_ID);
    }

    public boolean isSetEncryptAction() {
        return this.__isset_bit_vector.get(__ENCRYPTACTION_ISSET_ID);
    }

    public void setEncryptActionIsSet(boolean value) {
        this.__isset_bit_vector.set(__ENCRYPTACTION_ISSET_ID, value);
    }

    public boolean isIsRequest() {
        return this.isRequest;
    }

    public XmPushActionContainer setIsRequest(boolean isRequest) {
        this.isRequest = isRequest;
        setIsRequestIsSet(true);
        return this;
    }

    public void unsetIsRequest() {
        this.__isset_bit_vector.clear(__ISREQUEST_ISSET_ID);
    }

    public boolean isSetIsRequest() {
        return this.__isset_bit_vector.get(__ISREQUEST_ISSET_ID);
    }

    public void setIsRequestIsSet(boolean value) {
        this.__isset_bit_vector.set(__ISREQUEST_ISSET_ID, value);
    }

    public byte[] getPushAction() {
        setPushAction(TBaseHelper.rightSize(this.pushAction));
        return this.pushAction.array();
    }

    public ByteBuffer BufferForPushAction() {
        return this.pushAction;
    }

    public XmPushActionContainer setPushAction(byte[] pushAction) {
        setPushAction(ByteBuffer.wrap(pushAction));
        return this;
    }

    public XmPushActionContainer setPushAction(ByteBuffer pushAction) {
        this.pushAction = pushAction;
        return this;
    }

    public void unsetPushAction() {
        this.pushAction = null;
    }

    public boolean isSetPushAction() {
        return this.pushAction != null;
    }

    public void setPushActionIsSet(boolean value) {
        if (!value) {
            this.pushAction = null;
        }
    }

    public String getAppid() {
        return this.appid;
    }

    public XmPushActionContainer setAppid(String appid) {
        this.appid = appid;
        return this;
    }

    public void unsetAppid() {
        this.appid = null;
    }

    public boolean isSetAppid() {
        return this.appid != null;
    }

    public void setAppidIsSet(boolean value) {
        if (!value) {
            this.appid = null;
        }
    }

    public String getPackageName() {
        return this.packageName;
    }

    public XmPushActionContainer setPackageName(String packageName) {
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

    public Target getTarget() {
        return this.target;
    }

    public XmPushActionContainer setTarget(Target target) {
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

    public PushMetaInfo getMetaInfo() {
        return this.metaInfo;
    }

    public XmPushActionContainer setMetaInfo(PushMetaInfo metaInfo) {
        this.metaInfo = metaInfo;
        return this;
    }

    public void unsetMetaInfo() {
        this.metaInfo = null;
    }

    public boolean isSetMetaInfo() {
        return this.metaInfo != null;
    }

    public void setMetaInfoIsSet(boolean value) {
        if (!value) {
            this.metaInfo = null;
        }
    }

    public void setFieldValue(_Fields field, Object value) {
        switch (AnonymousClass1.$SwitchMap$com$xiaomi$xmpush$thrift$XmPushActionContainer$_Fields[field.ordinal()]) {
            case __ISREQUEST_ISSET_ID /*1*/:
                if (value == null) {
                    unsetAction();
                    return;
                } else {
                    setAction((ActionType) value);
                    return;
                }
            case TTransportException.ALREADY_OPEN /*2*/:
                if (value == null) {
                    unsetEncryptAction();
                    return;
                } else {
                    setEncryptAction(((Boolean) value).booleanValue());
                    return;
                }
            case TTransportException.TIMED_OUT /*3*/:
                if (value == null) {
                    unsetIsRequest();
                    return;
                } else {
                    setIsRequest(((Boolean) value).booleanValue());
                    return;
                }
            case TTransportException.END_OF_FILE /*4*/:
                if (value == null) {
                    unsetPushAction();
                    return;
                } else {
                    setPushAction((ByteBuffer) value);
                    return;
                }
            case TProtocolException.NOT_IMPLEMENTED /*5*/:
                if (value == null) {
                    unsetAppid();
                    return;
                } else {
                    setAppid((String) value);
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
                    unsetTarget();
                    return;
                } else {
                    setTarget((Target) value);
                    return;
                }
            case PushConstants.ERROR_RECEIVE_TIMEOUT /*8*/:
                if (value == null) {
                    unsetMetaInfo();
                    return;
                } else {
                    setMetaInfo((PushMetaInfo) value);
                    return;
                }
            default:
                return;
        }
    }

    public Object getFieldValue(_Fields field) {
        switch (AnonymousClass1.$SwitchMap$com$xiaomi$xmpush$thrift$XmPushActionContainer$_Fields[field.ordinal()]) {
            case __ISREQUEST_ISSET_ID /*1*/:
                return getAction();
            case TTransportException.ALREADY_OPEN /*2*/:
                return new Boolean(isEncryptAction());
            case TTransportException.TIMED_OUT /*3*/:
                return new Boolean(isIsRequest());
            case TTransportException.END_OF_FILE /*4*/:
                return getPushAction();
            case TProtocolException.NOT_IMPLEMENTED /*5*/:
                return getAppid();
            case TApplicationException.INTERNAL_ERROR /*6*/:
                return getPackageName();
            case TApplicationException.PROTOCOL_ERROR /*7*/:
                return getTarget();
            case PushConstants.ERROR_RECEIVE_TIMEOUT /*8*/:
                return getMetaInfo();
            default:
                throw new IllegalStateException();
        }
    }

    public boolean isSet(_Fields field) {
        if (field == null) {
            throw new IllegalArgumentException();
        }
        switch (AnonymousClass1.$SwitchMap$com$xiaomi$xmpush$thrift$XmPushActionContainer$_Fields[field.ordinal()]) {
            case __ISREQUEST_ISSET_ID /*1*/:
                return isSetAction();
            case TTransportException.ALREADY_OPEN /*2*/:
                return isSetEncryptAction();
            case TTransportException.TIMED_OUT /*3*/:
                return isSetIsRequest();
            case TTransportException.END_OF_FILE /*4*/:
                return isSetPushAction();
            case TProtocolException.NOT_IMPLEMENTED /*5*/:
                return isSetAppid();
            case TApplicationException.INTERNAL_ERROR /*6*/:
                return isSetPackageName();
            case TApplicationException.PROTOCOL_ERROR /*7*/:
                return isSetTarget();
            case PushConstants.ERROR_RECEIVE_TIMEOUT /*8*/:
                return isSetMetaInfo();
            default:
                throw new IllegalStateException();
        }
    }

    public boolean equals(Object that) {
        if (that != null && (that instanceof XmPushActionContainer)) {
            return equals((XmPushActionContainer) that);
        }
        return false;
    }

    public boolean equals(XmPushActionContainer that) {
        if (that == null) {
            return false;
        }
        boolean this_present_action = isSetAction();
        boolean that_present_action = that.isSetAction();
        if (this_present_action || that_present_action) {
            if (!this_present_action || !that_present_action) {
                return false;
            }
            if (!this.action.equals(that.action)) {
                return false;
            }
        }
        if (!(__ISREQUEST_ISSET_ID == null && __ISREQUEST_ISSET_ID == null)) {
            if (__ISREQUEST_ISSET_ID == null || __ISREQUEST_ISSET_ID == null) {
                return false;
            }
            if (this.encryptAction != that.encryptAction) {
                return false;
            }
        }
        if (!(__ISREQUEST_ISSET_ID == null && __ISREQUEST_ISSET_ID == null)) {
            if (__ISREQUEST_ISSET_ID == null || __ISREQUEST_ISSET_ID == null) {
                return false;
            }
            if (this.isRequest != that.isRequest) {
                return false;
            }
        }
        boolean this_present_pushAction = isSetPushAction();
        boolean that_present_pushAction = that.isSetPushAction();
        if (this_present_pushAction || that_present_pushAction) {
            if (!this_present_pushAction || !that_present_pushAction) {
                return false;
            }
            if (!this.pushAction.equals(that.pushAction)) {
                return false;
            }
        }
        boolean this_present_appid = isSetAppid();
        boolean that_present_appid = that.isSetAppid();
        if (this_present_appid || that_present_appid) {
            if (!this_present_appid || !that_present_appid) {
                return false;
            }
            if (!this.appid.equals(that.appid)) {
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
        boolean this_present_metaInfo = isSetMetaInfo();
        boolean that_present_metaInfo = that.isSetMetaInfo();
        if (this_present_metaInfo || that_present_metaInfo) {
            if (!this_present_metaInfo || !that_present_metaInfo) {
                return false;
            }
            if (!this.metaInfo.equals(that.metaInfo)) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        return __ENCRYPTACTION_ISSET_ID;
    }

    public int compareTo(XmPushActionContainer other) {
        if (!getClass().equals(other.getClass())) {
            return getClass().getName().compareTo(other.getClass().getName());
        }
        XmPushActionContainer typedOther = other;
        int lastComparison = Boolean.valueOf(isSetAction()).compareTo(Boolean.valueOf(typedOther.isSetAction()));
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetAction()) {
            lastComparison = TBaseHelper.compareTo(this.action, typedOther.action);
            if (lastComparison != 0) {
                return lastComparison;
            }
        }
        lastComparison = Boolean.valueOf(isSetEncryptAction()).compareTo(Boolean.valueOf(typedOther.isSetEncryptAction()));
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetEncryptAction()) {
            lastComparison = TBaseHelper.compareTo(this.encryptAction, typedOther.encryptAction);
            if (lastComparison != 0) {
                return lastComparison;
            }
        }
        lastComparison = Boolean.valueOf(isSetIsRequest()).compareTo(Boolean.valueOf(typedOther.isSetIsRequest()));
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetIsRequest()) {
            lastComparison = TBaseHelper.compareTo(this.isRequest, typedOther.isRequest);
            if (lastComparison != 0) {
                return lastComparison;
            }
        }
        lastComparison = Boolean.valueOf(isSetPushAction()).compareTo(Boolean.valueOf(typedOther.isSetPushAction()));
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetPushAction()) {
            lastComparison = TBaseHelper.compareTo(this.pushAction, typedOther.pushAction);
            if (lastComparison != 0) {
                return lastComparison;
            }
        }
        lastComparison = Boolean.valueOf(isSetAppid()).compareTo(Boolean.valueOf(typedOther.isSetAppid()));
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetAppid()) {
            lastComparison = TBaseHelper.compareTo(this.appid, typedOther.appid);
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
        lastComparison = Boolean.valueOf(isSetMetaInfo()).compareTo(Boolean.valueOf(typedOther.isSetMetaInfo()));
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetMetaInfo()) {
            lastComparison = TBaseHelper.compareTo(this.metaInfo, typedOther.metaInfo);
            if (lastComparison != 0) {
                return lastComparison;
            }
        }
        return __ENCRYPTACTION_ISSET_ID;
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
                if (!isSetEncryptAction()) {
                    throw new TProtocolException("Required field 'encryptAction' was not found in serialized data! Struct: " + toString());
                } else if (isSetIsRequest()) {
                    validate();
                    return;
                } else {
                    throw new TProtocolException("Required field 'isRequest' was not found in serialized data! Struct: " + toString());
                }
            }
            switch (field.id) {
                case __ISREQUEST_ISSET_ID /*1*/:
                    if (field.type != (byte) 8) {
                        TProtocolUtil.skip(iprot, field.type);
                        break;
                    } else {
                        this.action = ActionType.findByValue(iprot.readI32());
                        break;
                    }
                case TTransportException.ALREADY_OPEN /*2*/:
                    if (field.type != (byte) 2) {
                        TProtocolUtil.skip(iprot, field.type);
                        break;
                    }
                    this.encryptAction = iprot.readBool();
                    setEncryptActionIsSet(true);
                    break;
                case TTransportException.TIMED_OUT /*3*/:
                    if (field.type != (byte) 2) {
                        TProtocolUtil.skip(iprot, field.type);
                        break;
                    }
                    this.isRequest = iprot.readBool();
                    setIsRequestIsSet(true);
                    break;
                case TTransportException.END_OF_FILE /*4*/:
                    if (field.type != TType.STRING) {
                        TProtocolUtil.skip(iprot, field.type);
                        break;
                    } else {
                        this.pushAction = iprot.readBinary();
                        break;
                    }
                case TProtocolException.NOT_IMPLEMENTED /*5*/:
                    if (field.type != TType.STRING) {
                        TProtocolUtil.skip(iprot, field.type);
                        break;
                    } else {
                        this.appid = iprot.readString();
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
                    if (field.type != TType.STRUCT) {
                        TProtocolUtil.skip(iprot, field.type);
                        break;
                    }
                    this.target = new Target();
                    this.target.read(iprot);
                    break;
                case PushConstants.ERROR_RECEIVE_TIMEOUT /*8*/:
                    if (field.type != TType.STRUCT) {
                        TProtocolUtil.skip(iprot, field.type);
                        break;
                    }
                    this.metaInfo = new PushMetaInfo();
                    this.metaInfo.read(iprot);
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
        if (this.action != null) {
            oprot.writeFieldBegin(ACTION_FIELD_DESC);
            oprot.writeI32(this.action.getValue());
            oprot.writeFieldEnd();
        }
        oprot.writeFieldBegin(ENCRYPT_ACTION_FIELD_DESC);
        oprot.writeBool(this.encryptAction);
        oprot.writeFieldEnd();
        oprot.writeFieldBegin(IS_REQUEST_FIELD_DESC);
        oprot.writeBool(this.isRequest);
        oprot.writeFieldEnd();
        if (this.pushAction != null) {
            oprot.writeFieldBegin(PUSH_ACTION_FIELD_DESC);
            oprot.writeBinary(this.pushAction);
            oprot.writeFieldEnd();
        }
        if (this.appid != null && isSetAppid()) {
            oprot.writeFieldBegin(APPID_FIELD_DESC);
            oprot.writeString(this.appid);
            oprot.writeFieldEnd();
        }
        if (this.packageName != null && isSetPackageName()) {
            oprot.writeFieldBegin(PACKAGE_NAME_FIELD_DESC);
            oprot.writeString(this.packageName);
            oprot.writeFieldEnd();
        }
        if (this.target != null) {
            oprot.writeFieldBegin(TARGET_FIELD_DESC);
            this.target.write(oprot);
            oprot.writeFieldEnd();
        }
        if (this.metaInfo != null && isSetMetaInfo()) {
            oprot.writeFieldBegin(META_INFO_FIELD_DESC);
            this.metaInfo.write(oprot);
            oprot.writeFieldEnd();
        }
        oprot.writeFieldStop();
        oprot.writeStructEnd();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("XmPushActionContainer(");
        sb.append("action:");
        if (this.action == null) {
            sb.append("null");
        } else {
            sb.append(this.action);
        }
        if (!false) {
            sb.append(", ");
        }
        sb.append("encryptAction:");
        sb.append(this.encryptAction);
        if (!false) {
            sb.append(", ");
        }
        sb.append("isRequest:");
        sb.append(this.isRequest);
        if (!false) {
            sb.append(", ");
        }
        sb.append("pushAction:");
        if (this.pushAction == null) {
            sb.append("null");
        } else {
            TBaseHelper.toString(this.pushAction, sb);
        }
        boolean first = false;
        if (isSetAppid()) {
            if (__ENCRYPTACTION_ISSET_ID == null) {
                sb.append(", ");
            }
            sb.append("appid:");
            if (this.appid == null) {
                sb.append("null");
            } else {
                sb.append(this.appid);
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
        sb.append("target:");
        if (this.target == null) {
            sb.append("null");
        } else {
            sb.append(this.target);
        }
        if (isSetMetaInfo()) {
            if (!false) {
                sb.append(", ");
            }
            sb.append("metaInfo:");
            if (this.metaInfo == null) {
                sb.append("null");
            } else {
                sb.append(this.metaInfo);
            }
        }
        sb.append(")");
        return sb.toString();
    }

    public void validate() throws TException {
        if (this.action == null) {
            throw new TProtocolException("Required field 'action' was not present! Struct: " + toString());
        } else if (this.pushAction == null) {
            throw new TProtocolException("Required field 'pushAction' was not present! Struct: " + toString());
        } else if (this.target == null) {
            throw new TProtocolException("Required field 'target' was not present! Struct: " + toString());
        }
    }
}
