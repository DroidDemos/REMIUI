package com.google.android.gms.ads.identifier;

public interface AdIdProvider {
    String getAdId();

    String getPublicAndroidId();

    Boolean isLimitAdTrackingEnabled();

    void refreshCachedData();
}
