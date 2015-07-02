package org.apache.thrift.meta_data;

public class MapMetaData extends FieldValueMetaData {
    public final FieldValueMetaData a;
    public final FieldValueMetaData c;

    public MapMetaData(byte b, FieldValueMetaData fieldValueMetaData, FieldValueMetaData fieldValueMetaData2) {
        super(b);
        this.a = fieldValueMetaData;
        this.c = fieldValueMetaData2;
    }
}
