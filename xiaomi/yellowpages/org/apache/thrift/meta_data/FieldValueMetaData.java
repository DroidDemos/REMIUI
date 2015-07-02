package org.apache.thrift.meta_data;

import java.io.Serializable;

public class FieldValueMetaData implements Serializable {
    private final boolean a;
    public final byte b;
    private final String c;

    public FieldValueMetaData(byte b) {
        this.b = b;
        this.a = false;
        this.c = null;
    }
}
