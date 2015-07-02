package com.xiaomi.common.logger.thrift.mfs;

import java.io.Serializable;
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
import org.apache.thrift.protocol.TProtocolUtil;
import org.apache.thrift.protocol.TStruct;
import org.apache.thrift.protocol.TType;
import org.apache.thrift.transport.TTransportException;

public class Location implements TBase<Location, _Fields>, Serializable, Cloneable {
    private static final TField CITY_FIELD_DESC;
    private static final TField CONTRY_FIELD_DESC;
    private static final TField ISP_FIELD_DESC;
    private static final TField PROVINCE_FIELD_DESC;
    private static final TStruct STRUCT_DESC;
    public static final Map<_Fields, FieldMetaData> metaDataMap;
    private String city;
    private String contry;
    private String isp;
    private String province;

    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$xiaomi$common$logger$thrift$mfs$Location$_Fields;

        static {
            $SwitchMap$com$xiaomi$common$logger$thrift$mfs$Location$_Fields = new int[_Fields.values().length];
            try {
                $SwitchMap$com$xiaomi$common$logger$thrift$mfs$Location$_Fields[_Fields.CONTRY.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$xiaomi$common$logger$thrift$mfs$Location$_Fields[_Fields.PROVINCE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$xiaomi$common$logger$thrift$mfs$Location$_Fields[_Fields.CITY.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$xiaomi$common$logger$thrift$mfs$Location$_Fields[_Fields.ISP.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    public enum _Fields implements TFieldIdEnum {
        CONTRY((short) 1, "contry"),
        PROVINCE((short) 2, "province"),
        CITY((short) 3, "city"),
        ISP((short) 4, "isp");
        
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
                    return CONTRY;
                case TTransportException.ALREADY_OPEN /*2*/:
                    return PROVINCE;
                case TTransportException.TIMED_OUT /*3*/:
                    return CITY;
                case TTransportException.END_OF_FILE /*4*/:
                    return ISP;
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
        STRUCT_DESC = new TStruct("Location");
        CONTRY_FIELD_DESC = new TField("contry", TType.STRING, (short) 1);
        PROVINCE_FIELD_DESC = new TField("province", TType.STRING, (short) 2);
        CITY_FIELD_DESC = new TField("city", TType.STRING, (short) 3);
        ISP_FIELD_DESC = new TField("isp", TType.STRING, (short) 4);
        Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
        tmpMap.put(_Fields.CONTRY, new FieldMetaData("contry", (byte) 2, new FieldValueMetaData(TType.STRING)));
        tmpMap.put(_Fields.PROVINCE, new FieldMetaData("province", (byte) 2, new FieldValueMetaData(TType.STRING)));
        tmpMap.put(_Fields.CITY, new FieldMetaData("city", (byte) 2, new FieldValueMetaData(TType.STRING)));
        tmpMap.put(_Fields.ISP, new FieldMetaData("isp", (byte) 2, new FieldValueMetaData(TType.STRING)));
        metaDataMap = Collections.unmodifiableMap(tmpMap);
        FieldMetaData.addStructMetaDataMap(Location.class, metaDataMap);
    }

    public Location(Location other) {
        if (other.isSetContry()) {
            this.contry = other.contry;
        }
        if (other.isSetProvince()) {
            this.province = other.province;
        }
        if (other.isSetCity()) {
            this.city = other.city;
        }
        if (other.isSetIsp()) {
            this.isp = other.isp;
        }
    }

    public Location deepCopy() {
        return new Location(this);
    }

    public void clear() {
        this.contry = null;
        this.province = null;
        this.city = null;
        this.isp = null;
    }

    public String getContry() {
        return this.contry;
    }

    public Location setContry(String contry) {
        this.contry = contry;
        return this;
    }

    public void unsetContry() {
        this.contry = null;
    }

    public boolean isSetContry() {
        return this.contry != null;
    }

    public void setContryIsSet(boolean value) {
        if (!value) {
            this.contry = null;
        }
    }

    public String getProvince() {
        return this.province;
    }

    public Location setProvince(String province) {
        this.province = province;
        return this;
    }

    public void unsetProvince() {
        this.province = null;
    }

    public boolean isSetProvince() {
        return this.province != null;
    }

    public void setProvinceIsSet(boolean value) {
        if (!value) {
            this.province = null;
        }
    }

    public String getCity() {
        return this.city;
    }

    public Location setCity(String city) {
        this.city = city;
        return this;
    }

    public void unsetCity() {
        this.city = null;
    }

    public boolean isSetCity() {
        return this.city != null;
    }

    public void setCityIsSet(boolean value) {
        if (!value) {
            this.city = null;
        }
    }

    public String getIsp() {
        return this.isp;
    }

    public Location setIsp(String isp) {
        this.isp = isp;
        return this;
    }

    public void unsetIsp() {
        this.isp = null;
    }

    public boolean isSetIsp() {
        return this.isp != null;
    }

    public void setIspIsSet(boolean value) {
        if (!value) {
            this.isp = null;
        }
    }

    public void setFieldValue(_Fields field, Object value) {
        switch (AnonymousClass1.$SwitchMap$com$xiaomi$common$logger$thrift$mfs$Location$_Fields[field.ordinal()]) {
            case TTransportException.NOT_OPEN /*1*/:
                if (value == null) {
                    unsetContry();
                    return;
                } else {
                    setContry((String) value);
                    return;
                }
            case TTransportException.ALREADY_OPEN /*2*/:
                if (value == null) {
                    unsetProvince();
                    return;
                } else {
                    setProvince((String) value);
                    return;
                }
            case TTransportException.TIMED_OUT /*3*/:
                if (value == null) {
                    unsetCity();
                    return;
                } else {
                    setCity((String) value);
                    return;
                }
            case TTransportException.END_OF_FILE /*4*/:
                if (value == null) {
                    unsetIsp();
                    return;
                } else {
                    setIsp((String) value);
                    return;
                }
            default:
                return;
        }
    }

    public Object getFieldValue(_Fields field) {
        switch (AnonymousClass1.$SwitchMap$com$xiaomi$common$logger$thrift$mfs$Location$_Fields[field.ordinal()]) {
            case TTransportException.NOT_OPEN /*1*/:
                return getContry();
            case TTransportException.ALREADY_OPEN /*2*/:
                return getProvince();
            case TTransportException.TIMED_OUT /*3*/:
                return getCity();
            case TTransportException.END_OF_FILE /*4*/:
                return getIsp();
            default:
                throw new IllegalStateException();
        }
    }

    public boolean isSet(_Fields field) {
        if (field == null) {
            throw new IllegalArgumentException();
        }
        switch (AnonymousClass1.$SwitchMap$com$xiaomi$common$logger$thrift$mfs$Location$_Fields[field.ordinal()]) {
            case TTransportException.NOT_OPEN /*1*/:
                return isSetContry();
            case TTransportException.ALREADY_OPEN /*2*/:
                return isSetProvince();
            case TTransportException.TIMED_OUT /*3*/:
                return isSetCity();
            case TTransportException.END_OF_FILE /*4*/:
                return isSetIsp();
            default:
                throw new IllegalStateException();
        }
    }

    public boolean equals(Object that) {
        if (that != null && (that instanceof Location)) {
            return equals((Location) that);
        }
        return false;
    }

    public boolean equals(Location that) {
        if (that == null) {
            return false;
        }
        boolean this_present_contry = isSetContry();
        boolean that_present_contry = that.isSetContry();
        if ((this_present_contry || that_present_contry) && (!this_present_contry || !that_present_contry || !this.contry.equals(that.contry))) {
            return false;
        }
        boolean this_present_province = isSetProvince();
        boolean that_present_province = that.isSetProvince();
        if ((this_present_province || that_present_province) && (!this_present_province || !that_present_province || !this.province.equals(that.province))) {
            return false;
        }
        boolean this_present_city = isSetCity();
        boolean that_present_city = that.isSetCity();
        if ((this_present_city || that_present_city) && (!this_present_city || !that_present_city || !this.city.equals(that.city))) {
            return false;
        }
        boolean this_present_isp = isSetIsp();
        boolean that_present_isp = that.isSetIsp();
        if ((this_present_isp || that_present_isp) && (!this_present_isp || !that_present_isp || !this.isp.equals(that.isp))) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return 0;
    }

    public int compareTo(Location other) {
        if (!getClass().equals(other.getClass())) {
            return getClass().getName().compareTo(other.getClass().getName());
        }
        Location typedOther = other;
        int lastComparison = Boolean.valueOf(isSetContry()).compareTo(Boolean.valueOf(typedOther.isSetContry()));
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetContry()) {
            lastComparison = TBaseHelper.compareTo(this.contry, typedOther.contry);
            if (lastComparison != 0) {
                return lastComparison;
            }
        }
        lastComparison = Boolean.valueOf(isSetProvince()).compareTo(Boolean.valueOf(typedOther.isSetProvince()));
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetProvince()) {
            lastComparison = TBaseHelper.compareTo(this.province, typedOther.province);
            if (lastComparison != 0) {
                return lastComparison;
            }
        }
        lastComparison = Boolean.valueOf(isSetCity()).compareTo(Boolean.valueOf(typedOther.isSetCity()));
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetCity()) {
            lastComparison = TBaseHelper.compareTo(this.city, typedOther.city);
            if (lastComparison != 0) {
                return lastComparison;
            }
        }
        lastComparison = Boolean.valueOf(isSetIsp()).compareTo(Boolean.valueOf(typedOther.isSetIsp()));
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetIsp()) {
            lastComparison = TBaseHelper.compareTo(this.isp, typedOther.isp);
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
                        this.contry = iprot.readString();
                        break;
                    }
                case TTransportException.ALREADY_OPEN /*2*/:
                    if (field.type != TType.STRING) {
                        TProtocolUtil.skip(iprot, field.type);
                        break;
                    } else {
                        this.province = iprot.readString();
                        break;
                    }
                case TTransportException.TIMED_OUT /*3*/:
                    if (field.type != TType.STRING) {
                        TProtocolUtil.skip(iprot, field.type);
                        break;
                    } else {
                        this.city = iprot.readString();
                        break;
                    }
                case TTransportException.END_OF_FILE /*4*/:
                    if (field.type != TType.STRING) {
                        TProtocolUtil.skip(iprot, field.type);
                        break;
                    } else {
                        this.isp = iprot.readString();
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
        if (this.contry != null && isSetContry()) {
            oprot.writeFieldBegin(CONTRY_FIELD_DESC);
            oprot.writeString(this.contry);
            oprot.writeFieldEnd();
        }
        if (this.province != null && isSetProvince()) {
            oprot.writeFieldBegin(PROVINCE_FIELD_DESC);
            oprot.writeString(this.province);
            oprot.writeFieldEnd();
        }
        if (this.city != null && isSetCity()) {
            oprot.writeFieldBegin(CITY_FIELD_DESC);
            oprot.writeString(this.city);
            oprot.writeFieldEnd();
        }
        if (this.isp != null && isSetIsp()) {
            oprot.writeFieldBegin(ISP_FIELD_DESC);
            oprot.writeString(this.isp);
            oprot.writeFieldEnd();
        }
        oprot.writeFieldStop();
        oprot.writeStructEnd();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Location(");
        boolean first = true;
        if (isSetContry()) {
            sb.append("contry:");
            if (this.contry == null) {
                sb.append("null");
            } else {
                sb.append(this.contry);
            }
            first = false;
        }
        if (isSetProvince()) {
            if (!first) {
                sb.append(", ");
            }
            sb.append("province:");
            if (this.province == null) {
                sb.append("null");
            } else {
                sb.append(this.province);
            }
            first = false;
        }
        if (isSetCity()) {
            if (!first) {
                sb.append(", ");
            }
            sb.append("city:");
            if (this.city == null) {
                sb.append("null");
            } else {
                sb.append(this.city);
            }
            first = false;
        }
        if (isSetIsp()) {
            if (!first) {
                sb.append(", ");
            }
            sb.append("isp:");
            if (this.isp == null) {
                sb.append("null");
            } else {
                sb.append(this.isp);
            }
        }
        sb.append(")");
        return sb.toString();
    }

    public void validate() throws TException {
    }
}
