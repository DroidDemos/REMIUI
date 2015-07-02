package com.google.android.gms.deviceconnection.features;

public interface DeviceFeature {
    String getFeatureName();

    long getLastConnectionTimestampMillis();
}
