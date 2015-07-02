package com.google.android.libraries.bind.data;

public interface DataView {
    DataList getDataRow();

    void onDataUpdated(Data data);

    void setDataRow(DataList dataList);
}
