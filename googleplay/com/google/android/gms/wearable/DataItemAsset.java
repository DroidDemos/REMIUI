package com.google.android.gms.wearable;

import com.google.android.gms.common.data.Freezable;

public interface DataItemAsset extends Freezable<DataItemAsset> {
    String getDataItemKey();

    String getId();
}
