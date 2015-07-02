package com.xiaomi.common.logger.thrift.mfs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.thrift.TBase;
import org.apache.thrift.TBaseHelper;
import org.apache.thrift.TException;
import org.apache.thrift.TFieldIdEnum;
import org.apache.thrift.meta_data.FieldMetaData;
import org.apache.thrift.meta_data.FieldValueMetaData;
import org.apache.thrift.meta_data.ListMetaData;
import org.apache.thrift.meta_data.StructMetaData;
import org.apache.thrift.protocol.TField;
import org.apache.thrift.protocol.TList;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.protocol.TProtocolUtil;
import org.apache.thrift.protocol.TStruct;
import org.apache.thrift.protocol.TType;
import org.apache.thrift.transport.TTransportException;

public class HostInfo implements TBase<HostInfo, _Fields>, Serializable, Cloneable {
    private static final TField HOST_FIELD_DESC;
    private static final TField LAND_NODE_INFO_FIELD_DESC;
    private static final TStruct STRUCT_DESC;
    public static final Map<_Fields, FieldMetaData> metaDataMap;
    private String host;
    private List<LandNodeInfo> land_node_info;

    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$xiaomi$common$logger$thrift$mfs$HostInfo$_Fields;

        static {
            $SwitchMap$com$xiaomi$common$logger$thrift$mfs$HostInfo$_Fields = new int[_Fields.values().length];
            try {
                $SwitchMap$com$xiaomi$common$logger$thrift$mfs$HostInfo$_Fields[_Fields.HOST.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$xiaomi$common$logger$thrift$mfs$HostInfo$_Fields[_Fields.LAND_NODE_INFO.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    public enum _Fields implements TFieldIdEnum {
        HOST((short) 1, "host"),
        LAND_NODE_INFO((short) 2, "land_node_info");
        
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
                    return HOST;
                case TTransportException.ALREADY_OPEN /*2*/:
                    return LAND_NODE_INFO;
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
        STRUCT_DESC = new TStruct("HostInfo");
        HOST_FIELD_DESC = new TField("host", TType.STRING, (short) 1);
        LAND_NODE_INFO_FIELD_DESC = new TField("land_node_info", TType.LIST, (short) 2);
        Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
        tmpMap.put(_Fields.HOST, new FieldMetaData("host", (byte) 1, new FieldValueMetaData(TType.STRING)));
        tmpMap.put(_Fields.LAND_NODE_INFO, new FieldMetaData("land_node_info", (byte) 1, new ListMetaData(TType.LIST, new StructMetaData(TType.STRUCT, LandNodeInfo.class))));
        metaDataMap = Collections.unmodifiableMap(tmpMap);
        FieldMetaData.addStructMetaDataMap(HostInfo.class, metaDataMap);
    }

    public HostInfo(String host, List<LandNodeInfo> land_node_info) {
        this();
        this.host = host;
        this.land_node_info = land_node_info;
    }

    public HostInfo(HostInfo other) {
        if (other.isSetHost()) {
            this.host = other.host;
        }
        if (other.isSetLand_node_info()) {
            List<LandNodeInfo> __this__land_node_info = new ArrayList();
            for (LandNodeInfo other_element : other.land_node_info) {
                __this__land_node_info.add(new LandNodeInfo(other_element));
            }
            this.land_node_info = __this__land_node_info;
        }
    }

    public HostInfo deepCopy() {
        return new HostInfo(this);
    }

    public void clear() {
        this.host = null;
        this.land_node_info = null;
    }

    public String getHost() {
        return this.host;
    }

    public HostInfo setHost(String host) {
        this.host = host;
        return this;
    }

    public void unsetHost() {
        this.host = null;
    }

    public boolean isSetHost() {
        return this.host != null;
    }

    public void setHostIsSet(boolean value) {
        if (!value) {
            this.host = null;
        }
    }

    public int getLand_node_infoSize() {
        return this.land_node_info == null ? 0 : this.land_node_info.size();
    }

    public Iterator<LandNodeInfo> getLand_node_infoIterator() {
        return this.land_node_info == null ? null : this.land_node_info.iterator();
    }

    public void addToLand_node_info(LandNodeInfo elem) {
        if (this.land_node_info == null) {
            this.land_node_info = new ArrayList();
        }
        this.land_node_info.add(elem);
    }

    public List<LandNodeInfo> getLand_node_info() {
        return this.land_node_info;
    }

    public HostInfo setLand_node_info(List<LandNodeInfo> land_node_info) {
        this.land_node_info = land_node_info;
        return this;
    }

    public void unsetLand_node_info() {
        this.land_node_info = null;
    }

    public boolean isSetLand_node_info() {
        return this.land_node_info != null;
    }

    public void setLand_node_infoIsSet(boolean value) {
        if (!value) {
            this.land_node_info = null;
        }
    }

    public void setFieldValue(_Fields field, Object value) {
        switch (AnonymousClass1.$SwitchMap$com$xiaomi$common$logger$thrift$mfs$HostInfo$_Fields[field.ordinal()]) {
            case TTransportException.NOT_OPEN /*1*/:
                if (value == null) {
                    unsetHost();
                    return;
                } else {
                    setHost((String) value);
                    return;
                }
            case TTransportException.ALREADY_OPEN /*2*/:
                if (value == null) {
                    unsetLand_node_info();
                    return;
                } else {
                    setLand_node_info((List) value);
                    return;
                }
            default:
                return;
        }
    }

    public Object getFieldValue(_Fields field) {
        switch (AnonymousClass1.$SwitchMap$com$xiaomi$common$logger$thrift$mfs$HostInfo$_Fields[field.ordinal()]) {
            case TTransportException.NOT_OPEN /*1*/:
                return getHost();
            case TTransportException.ALREADY_OPEN /*2*/:
                return getLand_node_info();
            default:
                throw new IllegalStateException();
        }
    }

    public boolean isSet(_Fields field) {
        if (field == null) {
            throw new IllegalArgumentException();
        }
        switch (AnonymousClass1.$SwitchMap$com$xiaomi$common$logger$thrift$mfs$HostInfo$_Fields[field.ordinal()]) {
            case TTransportException.NOT_OPEN /*1*/:
                return isSetHost();
            case TTransportException.ALREADY_OPEN /*2*/:
                return isSetLand_node_info();
            default:
                throw new IllegalStateException();
        }
    }

    public boolean equals(Object that) {
        if (that != null && (that instanceof HostInfo)) {
            return equals((HostInfo) that);
        }
        return false;
    }

    public boolean equals(HostInfo that) {
        if (that == null) {
            return false;
        }
        boolean this_present_host = isSetHost();
        boolean that_present_host = that.isSetHost();
        if ((this_present_host || that_present_host) && (!this_present_host || !that_present_host || !this.host.equals(that.host))) {
            return false;
        }
        boolean this_present_land_node_info = isSetLand_node_info();
        boolean that_present_land_node_info = that.isSetLand_node_info();
        if ((this_present_land_node_info || that_present_land_node_info) && (!this_present_land_node_info || !that_present_land_node_info || !this.land_node_info.equals(that.land_node_info))) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return 0;
    }

    public int compareTo(HostInfo other) {
        if (!getClass().equals(other.getClass())) {
            return getClass().getName().compareTo(other.getClass().getName());
        }
        HostInfo typedOther = other;
        int lastComparison = Boolean.valueOf(isSetHost()).compareTo(Boolean.valueOf(typedOther.isSetHost()));
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetHost()) {
            lastComparison = TBaseHelper.compareTo(this.host, typedOther.host);
            if (lastComparison != 0) {
                return lastComparison;
            }
        }
        lastComparison = Boolean.valueOf(isSetLand_node_info()).compareTo(Boolean.valueOf(typedOther.isSetLand_node_info()));
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetLand_node_info()) {
            lastComparison = TBaseHelper.compareTo(this.land_node_info, typedOther.land_node_info);
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
                        this.host = iprot.readString();
                        break;
                    }
                case TTransportException.ALREADY_OPEN /*2*/:
                    if (field.type != TType.LIST) {
                        TProtocolUtil.skip(iprot, field.type);
                        break;
                    }
                    TList _list10 = iprot.readListBegin();
                    this.land_node_info = new ArrayList(_list10.size);
                    for (int _i11 = 0; _i11 < _list10.size; _i11++) {
                        LandNodeInfo _elem12 = new LandNodeInfo();
                        _elem12.read(iprot);
                        this.land_node_info.add(_elem12);
                    }
                    iprot.readListEnd();
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
        if (this.host != null) {
            oprot.writeFieldBegin(HOST_FIELD_DESC);
            oprot.writeString(this.host);
            oprot.writeFieldEnd();
        }
        if (this.land_node_info != null) {
            oprot.writeFieldBegin(LAND_NODE_INFO_FIELD_DESC);
            oprot.writeListBegin(new TList(TType.STRUCT, this.land_node_info.size()));
            for (LandNodeInfo _iter13 : this.land_node_info) {
                _iter13.write(oprot);
            }
            oprot.writeListEnd();
            oprot.writeFieldEnd();
        }
        oprot.writeFieldStop();
        oprot.writeStructEnd();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("HostInfo(");
        sb.append("host:");
        if (this.host == null) {
            sb.append("null");
        } else {
            sb.append(this.host);
        }
        if (!false) {
            sb.append(", ");
        }
        sb.append("land_node_info:");
        if (this.land_node_info == null) {
            sb.append("null");
        } else {
            sb.append(this.land_node_info);
        }
        sb.append(")");
        return sb.toString();
    }

    public void validate() throws TException {
        if (this.host == null) {
            throw new TProtocolException("Required field 'host' was not present! Struct: " + toString());
        } else if (this.land_node_info == null) {
            throw new TProtocolException("Required field 'land_node_info' was not present! Struct: " + toString());
        }
    }
}
