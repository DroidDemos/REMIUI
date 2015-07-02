package org.apache.thrift.meta_data;

import java.io.Serializable;
import org.apache.thrift.protocol.TType;

public class FieldValueMetaData implements Serializable {
    private final boolean isTypedefType;
    public final byte type;
    private final String typedefName;

    public FieldValueMetaData(byte type) {
        this.type = type;
        this.isTypedefType = false;
        this.typedefName = null;
    }

    public FieldValueMetaData(byte type, String typedefName) {
        this.type = type;
        this.isTypedefType = true;
        this.typedefName = typedefName;
    }

    public boolean isTypedef() {
        return this.isTypedefType;
    }

    public String getTypedefName() {
        return this.typedefName;
    }

    public boolean isStruct() {
        return this.type == TType.STRUCT;
    }

    public boolean isContainer() {
        return this.type == TType.LIST || this.type == TType.MAP || this.type == TType.SET;
    }
}
