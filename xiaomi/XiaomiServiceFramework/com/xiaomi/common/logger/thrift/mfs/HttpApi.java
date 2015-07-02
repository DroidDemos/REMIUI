package com.xiaomi.common.logger.thrift.mfs;

import com.xiaomi.push.service.PushConstants;
import com.xiaomi.push.service.PushServiceConstants;
import com.xiaomi.xmsf.push.service.Constants;
import java.io.Serializable;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.apache.thrift.TApplicationException;
import org.apache.thrift.TBase;
import org.apache.thrift.TBaseHelper;
import org.apache.thrift.TException;
import org.apache.thrift.TFieldIdEnum;
import org.apache.thrift.meta_data.FieldMetaData;
import org.apache.thrift.meta_data.FieldValueMetaData;
import org.apache.thrift.meta_data.SetMetaData;
import org.apache.thrift.meta_data.StructMetaData;
import org.apache.thrift.protocol.TField;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.protocol.TProtocolUtil;
import org.apache.thrift.protocol.TSet;
import org.apache.thrift.protocol.TStruct;
import org.apache.thrift.protocol.TType;
import org.apache.thrift.transport.TTransportException;

public class HttpApi implements TBase<HttpApi, _Fields>, Serializable, Cloneable {
    private static final TField APP_NAME_FIELD_DESC;
    private static final TField APP_VERSION_FIELD_DESC;
    private static final TField CATEGORY_FIELD_DESC;
    private static final TField CLIENT_IP_FIELD_DESC;
    private static final TField HOST_INFO_FIELD_DESC;
    private static final TField LOCATION_FIELD_DESC;
    private static final TField NETWORK_FIELD_DESC;
    private static final TStruct STRUCT_DESC;
    private static final TField UUID_FIELD_DESC;
    private static final TField VERSION_FIELD_DESC;
    private static final TField VERSION_TYPE_FIELD_DESC;
    public static final Map<_Fields, FieldMetaData> metaDataMap;
    private String app_name;
    private String app_version;
    private String category;
    private String client_ip;
    private Set<HostInfo> host_info;
    private Location location;
    private String network;
    private String uuid;
    private String version;
    private String version_type;

    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$xiaomi$common$logger$thrift$mfs$HttpApi$_Fields;

        static {
            $SwitchMap$com$xiaomi$common$logger$thrift$mfs$HttpApi$_Fields = new int[_Fields.values().length];
            try {
                $SwitchMap$com$xiaomi$common$logger$thrift$mfs$HttpApi$_Fields[_Fields.CATEGORY.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$xiaomi$common$logger$thrift$mfs$HttpApi$_Fields[_Fields.UUID.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$xiaomi$common$logger$thrift$mfs$HttpApi$_Fields[_Fields.VERSION.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$xiaomi$common$logger$thrift$mfs$HttpApi$_Fields[_Fields.NETWORK.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$xiaomi$common$logger$thrift$mfs$HttpApi$_Fields[_Fields.CLIENT_IP.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$xiaomi$common$logger$thrift$mfs$HttpApi$_Fields[_Fields.LOCATION.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$com$xiaomi$common$logger$thrift$mfs$HttpApi$_Fields[_Fields.HOST_INFO.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$com$xiaomi$common$logger$thrift$mfs$HttpApi$_Fields[_Fields.VERSION_TYPE.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                $SwitchMap$com$xiaomi$common$logger$thrift$mfs$HttpApi$_Fields[_Fields.APP_NAME.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                $SwitchMap$com$xiaomi$common$logger$thrift$mfs$HttpApi$_Fields[_Fields.APP_VERSION.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
        }
    }

    public enum _Fields implements TFieldIdEnum {
        CATEGORY((short) 1, "category"),
        UUID((short) 2, PushServiceConstants.EXTRA_UUID),
        VERSION((short) 3, "version"),
        NETWORK((short) 4, "network"),
        CLIENT_IP((short) 5, "client_ip"),
        LOCATION((short) 6, PushServiceConstants.EXTENSION_ELEMENT_LOCATION),
        HOST_INFO((short) 7, "host_info"),
        VERSION_TYPE((short) 8, "version_type"),
        APP_NAME((short) 9, "app_name"),
        APP_VERSION((short) 10, "app_version");
        
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
                    return CATEGORY;
                case TTransportException.ALREADY_OPEN /*2*/:
                    return UUID;
                case TTransportException.TIMED_OUT /*3*/:
                    return VERSION;
                case TTransportException.END_OF_FILE /*4*/:
                    return NETWORK;
                case TProtocolException.NOT_IMPLEMENTED /*5*/:
                    return CLIENT_IP;
                case TApplicationException.INTERNAL_ERROR /*6*/:
                    return LOCATION;
                case TApplicationException.PROTOCOL_ERROR /*7*/:
                    return HOST_INFO;
                case PushConstants.ERROR_RECEIVE_TIMEOUT /*8*/:
                    return VERSION_TYPE;
                case PushConstants.ERROR_READ_ERROR /*9*/:
                    return APP_NAME;
                case Constants.VALUE_MAX_FAILEDCOUNT /*10*/:
                    return APP_VERSION;
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
        STRUCT_DESC = new TStruct("HttpApi");
        CATEGORY_FIELD_DESC = new TField("category", TType.STRING, (short) 1);
        UUID_FIELD_DESC = new TField(PushServiceConstants.EXTRA_UUID, TType.STRING, (short) 2);
        VERSION_FIELD_DESC = new TField("version", TType.STRING, (short) 3);
        NETWORK_FIELD_DESC = new TField("network", TType.STRING, (short) 4);
        CLIENT_IP_FIELD_DESC = new TField("client_ip", TType.STRING, (short) 5);
        LOCATION_FIELD_DESC = new TField(PushServiceConstants.EXTENSION_ELEMENT_LOCATION, TType.STRUCT, (short) 6);
        HOST_INFO_FIELD_DESC = new TField("host_info", TType.SET, (short) 7);
        VERSION_TYPE_FIELD_DESC = new TField("version_type", TType.STRING, (short) 8);
        APP_NAME_FIELD_DESC = new TField("app_name", TType.STRING, (short) 9);
        APP_VERSION_FIELD_DESC = new TField("app_version", TType.STRING, (short) 10);
        Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
        tmpMap.put(_Fields.CATEGORY, new FieldMetaData("category", (byte) 1, new FieldValueMetaData(TType.STRING)));
        tmpMap.put(_Fields.UUID, new FieldMetaData(PushServiceConstants.EXTRA_UUID, (byte) 1, new FieldValueMetaData(TType.STRING)));
        tmpMap.put(_Fields.VERSION, new FieldMetaData("version", (byte) 1, new FieldValueMetaData(TType.STRING)));
        tmpMap.put(_Fields.NETWORK, new FieldMetaData("network", (byte) 1, new FieldValueMetaData(TType.STRING)));
        tmpMap.put(_Fields.CLIENT_IP, new FieldMetaData("client_ip", (byte) 2, new FieldValueMetaData(TType.STRING)));
        tmpMap.put(_Fields.LOCATION, new FieldMetaData(PushServiceConstants.EXTENSION_ELEMENT_LOCATION, (byte) 2, new StructMetaData(TType.STRUCT, Location.class)));
        tmpMap.put(_Fields.HOST_INFO, new FieldMetaData("host_info", (byte) 2, new SetMetaData(TType.SET, new StructMetaData(TType.STRUCT, HostInfo.class))));
        tmpMap.put(_Fields.VERSION_TYPE, new FieldMetaData("version_type", (byte) 2, new FieldValueMetaData(TType.STRING)));
        tmpMap.put(_Fields.APP_NAME, new FieldMetaData("app_name", (byte) 2, new FieldValueMetaData(TType.STRING)));
        tmpMap.put(_Fields.APP_VERSION, new FieldMetaData("app_version", (byte) 2, new FieldValueMetaData(TType.STRING)));
        metaDataMap = Collections.unmodifiableMap(tmpMap);
        FieldMetaData.addStructMetaDataMap(HttpApi.class, metaDataMap);
    }

    public HttpApi() {
        this.category = "";
        this.version_type = "";
        this.app_name = "";
        this.app_version = "";
    }

    public HttpApi(String category, String uuid, String version, String network) {
        this();
        this.category = category;
        this.uuid = uuid;
        this.version = version;
        this.network = network;
    }

    public HttpApi(HttpApi other) {
        if (other.isSetCategory()) {
            this.category = other.category;
        }
        if (other.isSetUuid()) {
            this.uuid = other.uuid;
        }
        if (other.isSetVersion()) {
            this.version = other.version;
        }
        if (other.isSetNetwork()) {
            this.network = other.network;
        }
        if (other.isSetClient_ip()) {
            this.client_ip = other.client_ip;
        }
        if (other.isSetLocation()) {
            this.location = new Location(other.location);
        }
        if (other.isSetHost_info()) {
            Set<HostInfo> __this__host_info = new HashSet();
            for (HostInfo other_element : other.host_info) {
                __this__host_info.add(new HostInfo(other_element));
            }
            this.host_info = __this__host_info;
        }
        if (other.isSetVersion_type()) {
            this.version_type = other.version_type;
        }
        if (other.isSetApp_name()) {
            this.app_name = other.app_name;
        }
        if (other.isSetApp_version()) {
            this.app_version = other.app_version;
        }
    }

    public HttpApi deepCopy() {
        return new HttpApi(this);
    }

    public void clear() {
        this.category = "";
        this.uuid = null;
        this.version = null;
        this.network = null;
        this.client_ip = null;
        this.location = null;
        this.host_info = null;
        this.version_type = "";
        this.app_name = "";
        this.app_version = "";
    }

    public String getCategory() {
        return this.category;
    }

    public HttpApi setCategory(String category) {
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

    public String getUuid() {
        return this.uuid;
    }

    public HttpApi setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public void unsetUuid() {
        this.uuid = null;
    }

    public boolean isSetUuid() {
        return this.uuid != null;
    }

    public void setUuidIsSet(boolean value) {
        if (!value) {
            this.uuid = null;
        }
    }

    public String getVersion() {
        return this.version;
    }

    public HttpApi setVersion(String version) {
        this.version = version;
        return this;
    }

    public void unsetVersion() {
        this.version = null;
    }

    public boolean isSetVersion() {
        return this.version != null;
    }

    public void setVersionIsSet(boolean value) {
        if (!value) {
            this.version = null;
        }
    }

    public String getNetwork() {
        return this.network;
    }

    public HttpApi setNetwork(String network) {
        this.network = network;
        return this;
    }

    public void unsetNetwork() {
        this.network = null;
    }

    public boolean isSetNetwork() {
        return this.network != null;
    }

    public void setNetworkIsSet(boolean value) {
        if (!value) {
            this.network = null;
        }
    }

    public String getClient_ip() {
        return this.client_ip;
    }

    public HttpApi setClient_ip(String client_ip) {
        this.client_ip = client_ip;
        return this;
    }

    public void unsetClient_ip() {
        this.client_ip = null;
    }

    public boolean isSetClient_ip() {
        return this.client_ip != null;
    }

    public void setClient_ipIsSet(boolean value) {
        if (!value) {
            this.client_ip = null;
        }
    }

    public Location getLocation() {
        return this.location;
    }

    public HttpApi setLocation(Location location) {
        this.location = location;
        return this;
    }

    public void unsetLocation() {
        this.location = null;
    }

    public boolean isSetLocation() {
        return this.location != null;
    }

    public void setLocationIsSet(boolean value) {
        if (!value) {
            this.location = null;
        }
    }

    public int getHost_infoSize() {
        return this.host_info == null ? 0 : this.host_info.size();
    }

    public Iterator<HostInfo> getHost_infoIterator() {
        return this.host_info == null ? null : this.host_info.iterator();
    }

    public void addToHost_info(HostInfo elem) {
        if (this.host_info == null) {
            this.host_info = new HashSet();
        }
        this.host_info.add(elem);
    }

    public Set<HostInfo> getHost_info() {
        return this.host_info;
    }

    public HttpApi setHost_info(Set<HostInfo> host_info) {
        this.host_info = host_info;
        return this;
    }

    public void unsetHost_info() {
        this.host_info = null;
    }

    public boolean isSetHost_info() {
        return this.host_info != null;
    }

    public void setHost_infoIsSet(boolean value) {
        if (!value) {
            this.host_info = null;
        }
    }

    public String getVersion_type() {
        return this.version_type;
    }

    public HttpApi setVersion_type(String version_type) {
        this.version_type = version_type;
        return this;
    }

    public void unsetVersion_type() {
        this.version_type = null;
    }

    public boolean isSetVersion_type() {
        return this.version_type != null;
    }

    public void setVersion_typeIsSet(boolean value) {
        if (!value) {
            this.version_type = null;
        }
    }

    public String getApp_name() {
        return this.app_name;
    }

    public HttpApi setApp_name(String app_name) {
        this.app_name = app_name;
        return this;
    }

    public void unsetApp_name() {
        this.app_name = null;
    }

    public boolean isSetApp_name() {
        return this.app_name != null;
    }

    public void setApp_nameIsSet(boolean value) {
        if (!value) {
            this.app_name = null;
        }
    }

    public String getApp_version() {
        return this.app_version;
    }

    public HttpApi setApp_version(String app_version) {
        this.app_version = app_version;
        return this;
    }

    public void unsetApp_version() {
        this.app_version = null;
    }

    public boolean isSetApp_version() {
        return this.app_version != null;
    }

    public void setApp_versionIsSet(boolean value) {
        if (!value) {
            this.app_version = null;
        }
    }

    public void setFieldValue(_Fields field, Object value) {
        switch (AnonymousClass1.$SwitchMap$com$xiaomi$common$logger$thrift$mfs$HttpApi$_Fields[field.ordinal()]) {
            case TTransportException.NOT_OPEN /*1*/:
                if (value == null) {
                    unsetCategory();
                    return;
                } else {
                    setCategory((String) value);
                    return;
                }
            case TTransportException.ALREADY_OPEN /*2*/:
                if (value == null) {
                    unsetUuid();
                    return;
                } else {
                    setUuid((String) value);
                    return;
                }
            case TTransportException.TIMED_OUT /*3*/:
                if (value == null) {
                    unsetVersion();
                    return;
                } else {
                    setVersion((String) value);
                    return;
                }
            case TTransportException.END_OF_FILE /*4*/:
                if (value == null) {
                    unsetNetwork();
                    return;
                } else {
                    setNetwork((String) value);
                    return;
                }
            case TProtocolException.NOT_IMPLEMENTED /*5*/:
                if (value == null) {
                    unsetClient_ip();
                    return;
                } else {
                    setClient_ip((String) value);
                    return;
                }
            case TApplicationException.INTERNAL_ERROR /*6*/:
                if (value == null) {
                    unsetLocation();
                    return;
                } else {
                    setLocation((Location) value);
                    return;
                }
            case TApplicationException.PROTOCOL_ERROR /*7*/:
                if (value == null) {
                    unsetHost_info();
                    return;
                } else {
                    setHost_info((Set) value);
                    return;
                }
            case PushConstants.ERROR_RECEIVE_TIMEOUT /*8*/:
                if (value == null) {
                    unsetVersion_type();
                    return;
                } else {
                    setVersion_type((String) value);
                    return;
                }
            case PushConstants.ERROR_READ_ERROR /*9*/:
                if (value == null) {
                    unsetApp_name();
                    return;
                } else {
                    setApp_name((String) value);
                    return;
                }
            case Constants.VALUE_MAX_FAILEDCOUNT /*10*/:
                if (value == null) {
                    unsetApp_version();
                    return;
                } else {
                    setApp_version((String) value);
                    return;
                }
            default:
                return;
        }
    }

    public Object getFieldValue(_Fields field) {
        switch (AnonymousClass1.$SwitchMap$com$xiaomi$common$logger$thrift$mfs$HttpApi$_Fields[field.ordinal()]) {
            case TTransportException.NOT_OPEN /*1*/:
                return getCategory();
            case TTransportException.ALREADY_OPEN /*2*/:
                return getUuid();
            case TTransportException.TIMED_OUT /*3*/:
                return getVersion();
            case TTransportException.END_OF_FILE /*4*/:
                return getNetwork();
            case TProtocolException.NOT_IMPLEMENTED /*5*/:
                return getClient_ip();
            case TApplicationException.INTERNAL_ERROR /*6*/:
                return getLocation();
            case TApplicationException.PROTOCOL_ERROR /*7*/:
                return getHost_info();
            case PushConstants.ERROR_RECEIVE_TIMEOUT /*8*/:
                return getVersion_type();
            case PushConstants.ERROR_READ_ERROR /*9*/:
                return getApp_name();
            case Constants.VALUE_MAX_FAILEDCOUNT /*10*/:
                return getApp_version();
            default:
                throw new IllegalStateException();
        }
    }

    public boolean isSet(_Fields field) {
        if (field == null) {
            throw new IllegalArgumentException();
        }
        switch (AnonymousClass1.$SwitchMap$com$xiaomi$common$logger$thrift$mfs$HttpApi$_Fields[field.ordinal()]) {
            case TTransportException.NOT_OPEN /*1*/:
                return isSetCategory();
            case TTransportException.ALREADY_OPEN /*2*/:
                return isSetUuid();
            case TTransportException.TIMED_OUT /*3*/:
                return isSetVersion();
            case TTransportException.END_OF_FILE /*4*/:
                return isSetNetwork();
            case TProtocolException.NOT_IMPLEMENTED /*5*/:
                return isSetClient_ip();
            case TApplicationException.INTERNAL_ERROR /*6*/:
                return isSetLocation();
            case TApplicationException.PROTOCOL_ERROR /*7*/:
                return isSetHost_info();
            case PushConstants.ERROR_RECEIVE_TIMEOUT /*8*/:
                return isSetVersion_type();
            case PushConstants.ERROR_READ_ERROR /*9*/:
                return isSetApp_name();
            case Constants.VALUE_MAX_FAILEDCOUNT /*10*/:
                return isSetApp_version();
            default:
                throw new IllegalStateException();
        }
    }

    public boolean equals(Object that) {
        if (that != null && (that instanceof HttpApi)) {
            return equals((HttpApi) that);
        }
        return false;
    }

    public boolean equals(HttpApi that) {
        if (that == null) {
            return false;
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
        boolean this_present_uuid = isSetUuid();
        boolean that_present_uuid = that.isSetUuid();
        if (this_present_uuid || that_present_uuid) {
            if (!this_present_uuid || !that_present_uuid) {
                return false;
            }
            if (!this.uuid.equals(that.uuid)) {
                return false;
            }
        }
        boolean this_present_version = isSetVersion();
        boolean that_present_version = that.isSetVersion();
        if (this_present_version || that_present_version) {
            if (!this_present_version || !that_present_version) {
                return false;
            }
            if (!this.version.equals(that.version)) {
                return false;
            }
        }
        boolean this_present_network = isSetNetwork();
        boolean that_present_network = that.isSetNetwork();
        if (this_present_network || that_present_network) {
            if (!this_present_network || !that_present_network) {
                return false;
            }
            if (!this.network.equals(that.network)) {
                return false;
            }
        }
        boolean this_present_client_ip = isSetClient_ip();
        boolean that_present_client_ip = that.isSetClient_ip();
        if (this_present_client_ip || that_present_client_ip) {
            if (!this_present_client_ip || !that_present_client_ip) {
                return false;
            }
            if (!this.client_ip.equals(that.client_ip)) {
                return false;
            }
        }
        boolean this_present_location = isSetLocation();
        boolean that_present_location = that.isSetLocation();
        if (this_present_location || that_present_location) {
            if (!this_present_location || !that_present_location) {
                return false;
            }
            if (!this.location.equals(that.location)) {
                return false;
            }
        }
        boolean this_present_host_info = isSetHost_info();
        boolean that_present_host_info = that.isSetHost_info();
        if (this_present_host_info || that_present_host_info) {
            if (!this_present_host_info || !that_present_host_info) {
                return false;
            }
            if (!this.host_info.equals(that.host_info)) {
                return false;
            }
        }
        boolean this_present_version_type = isSetVersion_type();
        boolean that_present_version_type = that.isSetVersion_type();
        if (this_present_version_type || that_present_version_type) {
            if (!this_present_version_type || !that_present_version_type) {
                return false;
            }
            if (!this.version_type.equals(that.version_type)) {
                return false;
            }
        }
        boolean this_present_app_name = isSetApp_name();
        boolean that_present_app_name = that.isSetApp_name();
        if (this_present_app_name || that_present_app_name) {
            if (!this_present_app_name || !that_present_app_name) {
                return false;
            }
            if (!this.app_name.equals(that.app_name)) {
                return false;
            }
        }
        boolean this_present_app_version = isSetApp_version();
        boolean that_present_app_version = that.isSetApp_version();
        if (this_present_app_version || that_present_app_version) {
            if (!this_present_app_version || !that_present_app_version) {
                return false;
            }
            if (!this.app_version.equals(that.app_version)) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        return 0;
    }

    public int compareTo(HttpApi other) {
        if (!getClass().equals(other.getClass())) {
            return getClass().getName().compareTo(other.getClass().getName());
        }
        HttpApi typedOther = other;
        int lastComparison = Boolean.valueOf(isSetCategory()).compareTo(Boolean.valueOf(typedOther.isSetCategory()));
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetCategory()) {
            lastComparison = TBaseHelper.compareTo(this.category, typedOther.category);
            if (lastComparison != 0) {
                return lastComparison;
            }
        }
        lastComparison = Boolean.valueOf(isSetUuid()).compareTo(Boolean.valueOf(typedOther.isSetUuid()));
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetUuid()) {
            lastComparison = TBaseHelper.compareTo(this.uuid, typedOther.uuid);
            if (lastComparison != 0) {
                return lastComparison;
            }
        }
        lastComparison = Boolean.valueOf(isSetVersion()).compareTo(Boolean.valueOf(typedOther.isSetVersion()));
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetVersion()) {
            lastComparison = TBaseHelper.compareTo(this.version, typedOther.version);
            if (lastComparison != 0) {
                return lastComparison;
            }
        }
        lastComparison = Boolean.valueOf(isSetNetwork()).compareTo(Boolean.valueOf(typedOther.isSetNetwork()));
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetNetwork()) {
            lastComparison = TBaseHelper.compareTo(this.network, typedOther.network);
            if (lastComparison != 0) {
                return lastComparison;
            }
        }
        lastComparison = Boolean.valueOf(isSetClient_ip()).compareTo(Boolean.valueOf(typedOther.isSetClient_ip()));
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetClient_ip()) {
            lastComparison = TBaseHelper.compareTo(this.client_ip, typedOther.client_ip);
            if (lastComparison != 0) {
                return lastComparison;
            }
        }
        lastComparison = Boolean.valueOf(isSetLocation()).compareTo(Boolean.valueOf(typedOther.isSetLocation()));
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetLocation()) {
            lastComparison = TBaseHelper.compareTo(this.location, typedOther.location);
            if (lastComparison != 0) {
                return lastComparison;
            }
        }
        lastComparison = Boolean.valueOf(isSetHost_info()).compareTo(Boolean.valueOf(typedOther.isSetHost_info()));
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetHost_info()) {
            lastComparison = TBaseHelper.compareTo(this.host_info, typedOther.host_info);
            if (lastComparison != 0) {
                return lastComparison;
            }
        }
        lastComparison = Boolean.valueOf(isSetVersion_type()).compareTo(Boolean.valueOf(typedOther.isSetVersion_type()));
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetVersion_type()) {
            lastComparison = TBaseHelper.compareTo(this.version_type, typedOther.version_type);
            if (lastComparison != 0) {
                return lastComparison;
            }
        }
        lastComparison = Boolean.valueOf(isSetApp_name()).compareTo(Boolean.valueOf(typedOther.isSetApp_name()));
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetApp_name()) {
            lastComparison = TBaseHelper.compareTo(this.app_name, typedOther.app_name);
            if (lastComparison != 0) {
                return lastComparison;
            }
        }
        lastComparison = Boolean.valueOf(isSetApp_version()).compareTo(Boolean.valueOf(typedOther.isSetApp_version()));
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetApp_version()) {
            lastComparison = TBaseHelper.compareTo(this.app_version, typedOther.app_version);
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
                        this.category = iprot.readString();
                        break;
                    }
                case TTransportException.ALREADY_OPEN /*2*/:
                    if (field.type != TType.STRING) {
                        TProtocolUtil.skip(iprot, field.type);
                        break;
                    } else {
                        this.uuid = iprot.readString();
                        break;
                    }
                case TTransportException.TIMED_OUT /*3*/:
                    if (field.type != TType.STRING) {
                        TProtocolUtil.skip(iprot, field.type);
                        break;
                    } else {
                        this.version = iprot.readString();
                        break;
                    }
                case TTransportException.END_OF_FILE /*4*/:
                    if (field.type != TType.STRING) {
                        TProtocolUtil.skip(iprot, field.type);
                        break;
                    } else {
                        this.network = iprot.readString();
                        break;
                    }
                case TProtocolException.NOT_IMPLEMENTED /*5*/:
                    if (field.type != TType.STRING) {
                        TProtocolUtil.skip(iprot, field.type);
                        break;
                    } else {
                        this.client_ip = iprot.readString();
                        break;
                    }
                case TApplicationException.INTERNAL_ERROR /*6*/:
                    if (field.type != TType.STRUCT) {
                        TProtocolUtil.skip(iprot, field.type);
                        break;
                    }
                    this.location = new Location();
                    this.location.read(iprot);
                    break;
                case TApplicationException.PROTOCOL_ERROR /*7*/:
                    if (field.type != TType.SET) {
                        TProtocolUtil.skip(iprot, field.type);
                        break;
                    }
                    TSet _set14 = iprot.readSetBegin();
                    this.host_info = new HashSet(_set14.size * 2);
                    for (int _i15 = 0; _i15 < _set14.size; _i15++) {
                        HostInfo _elem16 = new HostInfo();
                        _elem16.read(iprot);
                        this.host_info.add(_elem16);
                    }
                    iprot.readSetEnd();
                    break;
                case PushConstants.ERROR_RECEIVE_TIMEOUT /*8*/:
                    if (field.type != TType.STRING) {
                        TProtocolUtil.skip(iprot, field.type);
                        break;
                    } else {
                        this.version_type = iprot.readString();
                        break;
                    }
                case PushConstants.ERROR_READ_ERROR /*9*/:
                    if (field.type != TType.STRING) {
                        TProtocolUtil.skip(iprot, field.type);
                        break;
                    } else {
                        this.app_name = iprot.readString();
                        break;
                    }
                case Constants.VALUE_MAX_FAILEDCOUNT /*10*/:
                    if (field.type != TType.STRING) {
                        TProtocolUtil.skip(iprot, field.type);
                        break;
                    } else {
                        this.app_version = iprot.readString();
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
        if (this.category != null) {
            oprot.writeFieldBegin(CATEGORY_FIELD_DESC);
            oprot.writeString(this.category);
            oprot.writeFieldEnd();
        }
        if (this.uuid != null) {
            oprot.writeFieldBegin(UUID_FIELD_DESC);
            oprot.writeString(this.uuid);
            oprot.writeFieldEnd();
        }
        if (this.version != null) {
            oprot.writeFieldBegin(VERSION_FIELD_DESC);
            oprot.writeString(this.version);
            oprot.writeFieldEnd();
        }
        if (this.network != null) {
            oprot.writeFieldBegin(NETWORK_FIELD_DESC);
            oprot.writeString(this.network);
            oprot.writeFieldEnd();
        }
        if (this.client_ip != null && isSetClient_ip()) {
            oprot.writeFieldBegin(CLIENT_IP_FIELD_DESC);
            oprot.writeString(this.client_ip);
            oprot.writeFieldEnd();
        }
        if (this.location != null && isSetLocation()) {
            oprot.writeFieldBegin(LOCATION_FIELD_DESC);
            this.location.write(oprot);
            oprot.writeFieldEnd();
        }
        if (this.host_info != null && isSetHost_info()) {
            oprot.writeFieldBegin(HOST_INFO_FIELD_DESC);
            oprot.writeSetBegin(new TSet(TType.STRUCT, this.host_info.size()));
            for (HostInfo _iter17 : this.host_info) {
                _iter17.write(oprot);
            }
            oprot.writeSetEnd();
            oprot.writeFieldEnd();
        }
        if (this.version_type != null && isSetVersion_type()) {
            oprot.writeFieldBegin(VERSION_TYPE_FIELD_DESC);
            oprot.writeString(this.version_type);
            oprot.writeFieldEnd();
        }
        if (this.app_name != null && isSetApp_name()) {
            oprot.writeFieldBegin(APP_NAME_FIELD_DESC);
            oprot.writeString(this.app_name);
            oprot.writeFieldEnd();
        }
        if (this.app_version != null && isSetApp_version()) {
            oprot.writeFieldBegin(APP_VERSION_FIELD_DESC);
            oprot.writeString(this.app_version);
            oprot.writeFieldEnd();
        }
        oprot.writeFieldStop();
        oprot.writeStructEnd();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("HttpApi(");
        sb.append("category:");
        if (this.category == null) {
            sb.append("null");
        } else {
            sb.append(this.category);
        }
        if (!false) {
            sb.append(", ");
        }
        sb.append("uuid:");
        if (this.uuid == null) {
            sb.append("null");
        } else {
            sb.append(this.uuid);
        }
        if (!false) {
            sb.append(", ");
        }
        sb.append("version:");
        if (this.version == null) {
            sb.append("null");
        } else {
            sb.append(this.version);
        }
        if (!false) {
            sb.append(", ");
        }
        sb.append("network:");
        if (this.network == null) {
            sb.append("null");
        } else {
            sb.append(this.network);
        }
        boolean first = false;
        if (isSetClient_ip()) {
            if (null == null) {
                sb.append(", ");
            }
            sb.append("client_ip:");
            if (this.client_ip == null) {
                sb.append("null");
            } else {
                sb.append(this.client_ip);
            }
            first = false;
        }
        if (isSetLocation()) {
            if (!first) {
                sb.append(", ");
            }
            sb.append("location:");
            if (this.location == null) {
                sb.append("null");
            } else {
                sb.append(this.location);
            }
            first = false;
        }
        if (isSetHost_info()) {
            if (!first) {
                sb.append(", ");
            }
            sb.append("host_info:");
            if (this.host_info == null) {
                sb.append("null");
            } else {
                sb.append(this.host_info);
            }
            first = false;
        }
        if (isSetVersion_type()) {
            if (!first) {
                sb.append(", ");
            }
            sb.append("version_type:");
            if (this.version_type == null) {
                sb.append("null");
            } else {
                sb.append(this.version_type);
            }
            first = false;
        }
        if (isSetApp_name()) {
            if (!first) {
                sb.append(", ");
            }
            sb.append("app_name:");
            if (this.app_name == null) {
                sb.append("null");
            } else {
                sb.append(this.app_name);
            }
            first = false;
        }
        if (isSetApp_version()) {
            if (!first) {
                sb.append(", ");
            }
            sb.append("app_version:");
            if (this.app_version == null) {
                sb.append("null");
            } else {
                sb.append(this.app_version);
            }
        }
        sb.append(")");
        return sb.toString();
    }

    public void validate() throws TException {
        if (this.category == null) {
            throw new TProtocolException("Required field 'category' was not present! Struct: " + toString());
        } else if (this.uuid == null) {
            throw new TProtocolException("Required field 'uuid' was not present! Struct: " + toString());
        } else if (this.version == null) {
            throw new TProtocolException("Required field 'version' was not present! Struct: " + toString());
        } else if (this.network == null) {
            throw new TProtocolException("Required field 'network' was not present! Struct: " + toString());
        }
    }
}
