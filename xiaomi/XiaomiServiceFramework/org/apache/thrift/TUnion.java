package org.apache.thrift;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.apache.thrift.protocol.TField;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.protocol.TStruct;

public abstract class TUnion<T extends TUnion, F> implements TBase<T, F> {
    protected F setField_;
    protected Object value_;

    protected abstract void checkType(F f, Object obj) throws ClassCastException;

    protected abstract F enumForId(short s);

    protected abstract TField getFieldDesc(F f);

    protected abstract TStruct getStructDesc();

    protected abstract Object readValue(TProtocol tProtocol, TField tField) throws TException;

    protected abstract void writeValue(TProtocol tProtocol) throws TException;

    protected TUnion() {
        this.setField_ = null;
        this.value_ = null;
    }

    protected TUnion(F setField, Object value) {
        setFieldValue((Object) setField, value);
    }

    protected TUnion(TUnion<T, F> other) {
        if (other.getClass().equals(getClass())) {
            this.setField_ = other.setField_;
            this.value_ = deepCopyObject(other.value_);
            return;
        }
        throw new ClassCastException();
    }

    private static Object deepCopyObject(Object o) {
        if (o instanceof TBase) {
            return ((TBase) o).deepCopy();
        }
        if (o instanceof byte[]) {
            byte[] other_val = (byte[]) o;
            Object this_val = new byte[other_val.length];
            System.arraycopy(other_val, 0, this_val, 0, other_val.length);
            return this_val;
        } else if (o instanceof List) {
            return deepCopyList((List) o);
        } else {
            if (o instanceof Set) {
                return deepCopySet((Set) o);
            }
            return o instanceof Map ? deepCopyMap((Map) o) : o;
        }
    }

    private static Map deepCopyMap(Map<Object, Object> map) {
        Map copy = new HashMap();
        for (Entry<Object, Object> entry : map.entrySet()) {
            copy.put(deepCopyObject(entry.getKey()), deepCopyObject(entry.getValue()));
        }
        return copy;
    }

    private static Set deepCopySet(Set set) {
        Set copy = new HashSet();
        for (Object o : set) {
            copy.add(deepCopyObject(o));
        }
        return copy;
    }

    private static List deepCopyList(List list) {
        List copy = new ArrayList(list.size());
        for (Object o : list) {
            copy.add(deepCopyObject(o));
        }
        return copy;
    }

    public F getSetField() {
        return this.setField_;
    }

    public Object getFieldValue() {
        return this.value_;
    }

    public Object getFieldValue(F fieldId) {
        if (fieldId == this.setField_) {
            return getFieldValue();
        }
        throw new IllegalArgumentException("Cannot get the value of field " + fieldId + " because union's set field is " + this.setField_);
    }

    public Object getFieldValue(int fieldId) {
        return getFieldValue(enumForId((short) fieldId));
    }

    public boolean isSet() {
        return this.setField_ != null;
    }

    public boolean isSet(F fieldId) {
        return this.setField_ == fieldId;
    }

    public boolean isSet(int fieldId) {
        return isSet(enumForId((short) fieldId));
    }

    public void read(TProtocol iprot) throws TException {
        this.setField_ = null;
        this.value_ = null;
        iprot.readStructBegin();
        TField field = iprot.readFieldBegin();
        this.value_ = readValue(iprot, field);
        if (this.value_ != null) {
            this.setField_ = enumForId(field.id);
        }
        iprot.readFieldEnd();
        iprot.readFieldBegin();
        iprot.readStructEnd();
    }

    public void setFieldValue(F fieldId, Object value) {
        checkType(fieldId, value);
        this.setField_ = fieldId;
        this.value_ = value;
    }

    public void setFieldValue(int fieldId, Object value) {
        setFieldValue(enumForId((short) fieldId), value);
    }

    public void write(TProtocol oprot) throws TException {
        if (getSetField() == null || getFieldValue() == null) {
            throw new TProtocolException("Cannot write a TUnion with no set value!");
        }
        oprot.writeStructBegin(getStructDesc());
        oprot.writeFieldBegin(getFieldDesc(this.setField_));
        writeValue(oprot);
        oprot.writeFieldEnd();
        oprot.writeFieldStop();
        oprot.writeStructEnd();
    }

    public String toString() {
        String result = "<" + getClass().getSimpleName() + " ";
        if (getSetField() != null) {
            String vStr;
            Object v = getFieldValue();
            if (v instanceof byte[]) {
                vStr = bytesToStr((byte[]) v);
            } else {
                vStr = v.toString();
            }
            result = result + getFieldDesc(getSetField()).name + ":" + vStr;
        }
        return result + ">";
    }

    private static String bytesToStr(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        int size = Math.min(bytes.length, 128);
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                sb.append(" ");
            }
            String digit = Integer.toHexString(bytes[i] & 255);
            if (digit.length() <= 1) {
                digit = "0" + digit;
            }
            sb.append(digit);
        }
        if (bytes.length > 128) {
            sb.append(" ...");
        }
        return sb.toString();
    }

    public final void clear() {
        this.setField_ = null;
        this.value_ = null;
    }
}
