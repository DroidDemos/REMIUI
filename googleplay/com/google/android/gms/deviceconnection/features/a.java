package com.google.android.gms.deviceconnection.features;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.c;
import com.google.android.gms.internal.kl;

public final class a extends c implements DeviceFeature {
    public a(DataHolder dataHolder, int i) {
        super(dataHolder, i);
    }

    public String getFeatureName() {
        return getString("feature_name");
    }

    public long getLastConnectionTimestampMillis() {
        return getLong("last_connection_timestamp");
    }

    public String toString() {
        return kl.j(this).a("FeatureName", getFeatureName()).a("Timestamp", Long.valueOf(getLastConnectionTimestampMillis())).toString();
    }
}
