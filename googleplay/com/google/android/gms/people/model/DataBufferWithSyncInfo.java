package com.google.android.gms.people.model;

import com.google.android.gms.common.data.DataBuffer;
import com.google.android.gms.common.data.DataHolder;

public abstract class DataBufferWithSyncInfo<T> extends DataBuffer<T> {
    protected DataBufferWithSyncInfo(DataHolder dataHolder) {
        super(dataHolder);
    }
}
