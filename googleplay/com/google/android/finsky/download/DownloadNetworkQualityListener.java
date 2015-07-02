package com.google.android.finsky.download;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.SystemClock;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.Utils;
import com.google.android.gms.mdm.NetworkQualityUploader;
import com.google.android.wallet.instrumentmanager.R;

public class DownloadNetworkQualityListener implements DownloadQueueListener {
    private long mBytesStart;
    private ConnectivityManager mConnectivityManager;
    private Download mCurrentDownload;
    private final Handler mNetworkQueryHandler;
    private NetworkState mNetworkStart;
    private long mUptimeMillisStart;
    private WifiManager mWifiManager;

    public static class NetworkState {
        public NetworkInfo networkInfo;
        public WifiInfo wifiInfo;

        public NetworkState() {
            this.networkInfo = null;
            this.wifiInfo = null;
        }
    }

    public DownloadNetworkQualityListener() {
        this.mConnectivityManager = null;
        this.mWifiManager = null;
        this.mConnectivityManager = (ConnectivityManager) FinskyApp.get().getSystemService("connectivity");
        this.mWifiManager = (WifiManager) FinskyApp.get().getSystemService("wifi");
        HandlerThread networkQueryHandlerThread = new HandlerThread("NetworkQualityQueryThread");
        networkQueryHandlerThread.start();
        this.mNetworkQueryHandler = new Handler(networkQueryHandlerThread.getLooper());
        resetMeasurement();
    }

    public void onNotificationClicked(Download download) {
    }

    public void onStart(Download download) {
        resetMeasurement();
    }

    private void resetMeasurement() {
        this.mBytesStart = -1;
        this.mUptimeMillisStart = -1;
        this.mNetworkStart = null;
        this.mCurrentDownload = null;
    }

    public void onCancel(Download download) {
        resetMeasurement();
    }

    public void onError(Download download, int httpStatus) {
        resetMeasurement();
    }

    public void onComplete(Download download) {
        if (this.mBytesStart >= 0 && this.mUptimeMillisStart >= 0) {
            final long bytesTransferred = download.getProgress().bytesCompleted - this.mBytesStart;
            final long bytesPerSecond = (1000 * bytesTransferred) / Math.max(1, SystemClock.uptimeMillis() - this.mUptimeMillisStart);
            final NetworkState networkStart = this.mNetworkStart;
            resetMeasurement();
            if (bytesPerSecond < 0 || bytesTransferred < 0) {
                FinskyLog.w("Throughput or bytes transferred were calculated incorrectly or not at all", new Object[0]);
            } else {
                this.mNetworkQueryHandler.post(new Runnable() {
                    public void run() {
                        if (DownloadNetworkQualityListener.this.maySubmit(networkStart, DownloadNetworkQualityListener.this.getCurrentNetworkState())) {
                            Bundle customNetStats = new Bundle();
                            customNetStats.putString("bytes_transferred", Long.toString(bytesTransferred));
                            NetworkQualityUploader.logNetworkStats(FinskyApp.get(), null, Long.valueOf(bytesPerSecond), null, null, customNetStats);
                        }
                    }
                });
            }
        }
    }

    public void onProgress(final Download download, DownloadProgress downloadProgress) {
        double progress = ((double) downloadProgress.bytesCompleted) / ((double) downloadProgress.bytesTotal);
        if (!(this.mCurrentDownload == null || this.mCurrentDownload.equals(download))) {
            resetMeasurement();
        }
        if (this.mCurrentDownload == null && progress >= 0.2d && progress < 0.9d) {
            this.mCurrentDownload = download;
            this.mBytesStart = downloadProgress.bytesCompleted;
            this.mUptimeMillisStart = SystemClock.uptimeMillis();
            this.mNetworkQueryHandler.post(new Runnable() {
                public void run() {
                    NetworkState networkState = DownloadNetworkQualityListener.this.getCurrentNetworkState();
                    if (download.equals(DownloadNetworkQualityListener.this.mCurrentDownload)) {
                        DownloadNetworkQualityListener.this.mNetworkStart = networkState;
                    }
                }
            });
        }
    }

    private NetworkState getCurrentNetworkState() {
        Utils.ensureNotOnMainThread();
        NetworkState networkState = new NetworkState();
        networkState.networkInfo = this.mConnectivityManager.getActiveNetworkInfo();
        if (networkState.networkInfo != null && 1 == networkState.networkInfo.getType()) {
            networkState.wifiInfo = this.mWifiManager.getConnectionInfo();
        }
        return networkState;
    }

    private boolean maySubmit(NetworkState networkStart, NetworkState networkEnd) {
        if (networkStart == null || networkEnd == null || networkStart.networkInfo == null || networkEnd.networkInfo == null) {
            FinskyLog.w("Missing start or end network state", new Object[0]);
            return false;
        } else if (networkStart.networkInfo.getType() != networkEnd.networkInfo.getType()) {
            FinskyLog.d("Network type has changed (%d to %d)", Integer.valueOf(networkStart.networkInfo.getType()), Integer.valueOf(networkEnd.networkInfo.getType()));
            return false;
        } else {
            switch (networkStart.networkInfo.getType()) {
                case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                    boolean subTypeSame;
                    if (networkStart.networkInfo.getSubtype() == networkEnd.networkInfo.getSubtype()) {
                        subTypeSame = true;
                    } else {
                        subTypeSame = false;
                    }
                    if (subTypeSame) {
                        return subTypeSame;
                    }
                    FinskyLog.d("Network subtype has changed (%d to %d)", Integer.valueOf(networkStart.networkInfo.getSubtype()), Integer.valueOf(networkEnd.networkInfo.getSubtype()));
                    return subTypeSame;
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    if (networkStart.wifiInfo != null && networkEnd.wifiInfo != null && networkStart.wifiInfo.getSSID().equals(networkEnd.wifiInfo.getSSID())) {
                        return true;
                    }
                    FinskyLog.d("Wifi network changed", new Object[0]);
                    return false;
                default:
                    return true;
            }
        }
    }
}
