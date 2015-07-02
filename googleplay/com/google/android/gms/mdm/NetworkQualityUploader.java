package com.google.android.gms.mdm;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.Builder;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.no;
import com.google.android.gms.internal.ns;

public final class NetworkQualityUploader {
    private static boolean aAT;
    private static final OnConnectionFailedListener aAU;

    static {
        aAT = true;
        aAU = new OnConnectionFailedListener() {
            public void onConnectionFailed(ConnectionResult result) {
                if (ns.DEBUG) {
                    Log.d("Herrevad", "failed to connect to network quality service");
                }
                if (result.getErrorCode() != 7 && result.getErrorCode() != 8) {
                    NetworkQualityUploader.aAT = false;
                }
            }
        };
    }

    public static void logNetworkStats(Context context, Integer latencyMicros, Long throughputBps, Integer latitudeE6, Integer longitudeE6, Bundle customNetStats) {
        if (aAT) {
            final GoogleApiClient build = new Builder(context).addOnConnectionFailedListener(aAU).addApi(no.API).build();
            build.connect();
            no.asf.a(build, latencyMicros, throughputBps, latitudeE6, longitudeE6, customNetStats).setResultCallback(new ResultCallback<Status>() {
                public void n(Status status) {
                    build.disconnect();
                }

                public /* synthetic */ void onResult(Result x0) {
                    n((Status) x0);
                }
            });
        }
    }
}
