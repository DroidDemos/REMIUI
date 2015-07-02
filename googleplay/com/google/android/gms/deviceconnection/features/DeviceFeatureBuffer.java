package com.google.android.gms.deviceconnection.features;

import com.google.android.gms.common.data.DataBuffer;
import com.google.android.gms.common.data.DataHolder;

public class DeviceFeatureBuffer extends DataBuffer<DeviceFeature> {
    public DeviceFeatureBuffer(DataHolder dataHolder) {
        super(dataHolder);
    }

    public DeviceFeature get(int position) {
        return new a(this.mDataHolder, position);
    }
}
