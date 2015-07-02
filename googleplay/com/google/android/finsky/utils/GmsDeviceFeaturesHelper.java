package com.google.android.finsky.utils;

import android.content.Context;
import android.os.Bundle;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.analytics.BackgroundEventBuilder;
import com.google.android.finsky.analytics.PlayStore.DeviceFeature;
import com.google.android.finsky.analytics.PlayStore.DeviceFeature.DeviceFeatureInfo;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.Builder;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.deviceconnection.DeviceConnections;
import com.google.android.gms.deviceconnection.DeviceConnections.GetDeviceFeaturesResult;
import com.google.android.gms.deviceconnection.features.DeviceFeatureBuffer;
import java.util.List;

public class GmsDeviceFeaturesHelper implements ConnectionCallbacks, OnConnectionFailedListener {
    private final Context mContext;
    GoogleApiClient mGoogleApiClient;

    public GmsDeviceFeaturesHelper(Context context) {
        this.mContext = context;
    }

    public void fetchAndLogDeviceFeatures() {
        this.mGoogleApiClient = new Builder(this.mContext, this, this).addApi(DeviceConnections.API).build();
        this.mGoogleApiClient.connect();
    }

    private void makeGmsRequest() {
        DeviceConnections.getDeviceFeaturesRestricted(this.mGoogleApiClient).setResultCallback(new ResultCallback<GetDeviceFeaturesResult>() {
            public void onResult(GetDeviceFeaturesResult result) {
                if (result.getStatus().isSuccess()) {
                    DeviceFeatureBuffer deviceFeatureBuffer = result.getSummaries();
                    try {
                        GmsDeviceFeaturesHelper.this.logDeviceFeatures(deviceFeatureBuffer);
                    } finally {
                        deviceFeatureBuffer.release();
                    }
                }
            }
        });
    }

    private void logDeviceFeatures(DeviceFeatureBuffer deviceFeatureBuffer) {
        if (deviceFeatureBuffer.getCount() != 0 && FinskyApp.get().getCurrentAccount() != null) {
            DeviceFeature deviceFeature = getNewDeviceFeatures(deviceFeatureBuffer);
            if (deviceFeature == null) {
                FinskyPreferences.lastDeviceFeatureLoggedTimestampMs.put(Long.valueOf(System.currentTimeMillis()));
                return;
            }
            FinskyLog.d("Logging %d device features.", Integer.valueOf(deviceFeature.deviceFeatureInfo.length));
            FinskyApp.get().getEventLogger().logBackgroundEvent(new BackgroundEventBuilder(4).setDeviceFeatures(deviceFeature).build());
            FinskyPreferences.lastDeviceFeatureLoggedTimestampMs.put(Long.valueOf(System.currentTimeMillis()));
            this.mGoogleApiClient.disconnect();
        }
    }

    private DeviceFeature getNewDeviceFeatures(DeviceFeatureBuffer deviceFeatureBuffer) {
        List<DeviceFeatureInfo> deviceFeatureInfos = Lists.newArrayList();
        long lastLoggedTimestamp = ((Long) FinskyPreferences.lastDeviceFeatureLoggedTimestampMs.get()).longValue();
        for (int i = 0; i < deviceFeatureBuffer.getCount(); i++) {
            com.google.android.gms.deviceconnection.features.DeviceFeature from = deviceFeatureBuffer.get(i);
            long lastConnectionTimestampMillis = from.getLastConnectionTimestampMillis();
            if (lastConnectionTimestampMillis >= lastLoggedTimestamp) {
                DeviceFeatureInfo to = new DeviceFeatureInfo();
                to.featureName = from.getFeatureName();
                to.hasFeatureName = true;
                to.lastConnectionTimeMs = lastConnectionTimestampMillis;
                to.hasLastConnectionTimeMs = true;
                deviceFeatureInfos.add(to);
            }
        }
        if (deviceFeatureInfos.isEmpty()) {
            return null;
        }
        DeviceFeature deviceFeature = new DeviceFeature();
        deviceFeature.deviceFeatureInfo = new DeviceFeatureInfo[deviceFeatureInfos.size()];
        deviceFeatureInfos.toArray(deviceFeature.deviceFeatureInfo);
        return deviceFeature;
    }

    public void onConnected(Bundle bundle) {
        makeGmsRequest();
    }

    public void onConnectionSuspended(int cause) {
    }

    public void onConnectionFailed(ConnectionResult result) {
        int errorCode = result.getErrorCode();
        if (errorCode != 1 && errorCode != 2 && errorCode != 3) {
            FinskyLog.w("onConnectionFailed result: %s", result);
        }
    }
}
