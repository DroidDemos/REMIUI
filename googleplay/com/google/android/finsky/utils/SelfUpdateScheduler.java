package com.google.android.finsky.utils;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.analytics.PlayStore.AppData;
import com.google.android.finsky.download.Download;
import com.google.android.finsky.download.DownloadImpl;
import com.google.android.finsky.download.DownloadProgress;
import com.google.android.finsky.download.DownloadQueue;
import com.google.android.finsky.download.DownloadQueueListener;
import com.google.android.finsky.protos.AndroidAppDelivery.AndroidAppDeliveryData;
import com.google.android.finsky.protos.AndroidAppDelivery.HttpCookie;
import com.google.android.finsky.protos.Delivery.DeliveryResponse;
import com.google.android.finsky.protos.SelfUpdate.SelfUpdateResponse;
import com.google.android.finsky.protos.Toc.SelfUpdateConfig;
import com.google.android.finsky.protos.Toc.TocResponse;
import com.google.android.finsky.utils.ConsistencyTokenHelper.Listener;
import com.google.android.finsky.utils.PackageManagerHelper.InstallPackageListener;

public class SelfUpdateScheduler implements DownloadQueueListener {
    private static String sCertificateHash;
    private static String sCertificateHashSelfUpdateMD5;
    private final DownloadQueue mDownloadQueue;
    private String mDownloadSignature;
    private long mDownloadSize;
    private AppData mLogAppData;
    private int mMarketVersion;
    private ApplicationDismissedDeferrer mOnAppExitDeferrer;
    private Download mUpdateDownload;
    private boolean mUpdateInProgress;

    public SelfUpdateScheduler(DownloadQueue downloadQueue, int marketVersion) {
        this.mUpdateInProgress = false;
        this.mLogAppData = null;
        this.mDownloadSize = -1;
        this.mDownloadSignature = null;
        this.mUpdateDownload = null;
        this.mDownloadQueue = downloadQueue;
        this.mMarketVersion = marketVersion;
    }

    public int getNewVersion(TocResponse response) {
        if (response.selfUpdateConfig == null) {
            return -1;
        }
        SelfUpdateConfig config = response.selfUpdateConfig;
        if (config.hasLatestClientVersionCode) {
            return config.latestClientVersionCode;
        }
        return -1;
    }

    public int getNewVersion(SelfUpdateResponse response) {
        if (response.hasLatestClientVersionCode) {
            return response.latestClientVersionCode;
        }
        return -1;
    }

    public boolean isSelfUpdateRunning() {
        return this.mUpdateInProgress;
    }

    public boolean checkForSelfUpdate(int serverMarketVersion, String accountName) {
        if (this.mUpdateInProgress) {
            FinskyLog.d("Skipping DFE self-update check as there is an update already queued.", new Object[0]);
            return true;
        } else if (this.mMarketVersion >= serverMarketVersion) {
            FinskyLog.d("Skipping DFE self-update. Local Version [%d] >= Server Version [%d]", Integer.valueOf(this.mMarketVersion), Integer.valueOf(serverMarketVersion));
            return false;
        } else {
            FinskyLog.d("Starting DFE self-update from local version [%d] to server version [%d]", Integer.valueOf(this.mMarketVersion), Integer.valueOf(serverMarketVersion));
            this.mUpdateInProgress = true;
            this.mLogAppData = new AppData();
            this.mLogAppData.oldVersion = this.mMarketVersion;
            this.mLogAppData.hasOldVersion = true;
            this.mLogAppData.version = serverMarketVersion;
            this.mLogAppData.hasVersion = true;
            this.mLogAppData.systemApp = true;
            this.mLogAppData.hasSystemApp = true;
            Context context = FinskyApp.get();
            final String vendingPackageName = context.getPackageName();
            final String certificateHashSelfUpdateMD5 = getCertificateHashSelfUpdateMD5(context);
            final String certificateHash = getCertificateHash(context);
            final String str = accountName;
            final int i = serverMarketVersion;
            ConsistencyTokenHelper.get(context, new Listener() {
                public void onTokenReceived(String token) {
                    FinskyApp.get().getDfeApi(str).delivery(vendingPackageName, 1, null, Integer.valueOf(i), null, null, certificateHashSelfUpdateMD5, certificateHash, null, token, new Response.Listener<DeliveryResponse>() {
                        public void onResponse(DeliveryResponse deliveryResponse) {
                            if (deliveryResponse.status != 1) {
                                FinskyLog.w("SelfUpdate non-OK response - %d", Integer.valueOf(deliveryResponse.status));
                            } else if (deliveryResponse.appDeliveryData == null) {
                                FinskyLog.w("SelfUpdate response missing appDeliveryData", new Object[0]);
                            } else {
                                AndroidAppDeliveryData deliveryData = deliveryResponse.appDeliveryData;
                                HttpCookie authCookie = deliveryData.downloadAuthCookie[0];
                                SelfUpdateScheduler.this.startSelfUpdateDownload(deliveryData.downloadUrl, authCookie.name, authCookie.value, deliveryData.hasDownloadSize ? deliveryData.downloadSize : -1, deliveryData.hasSignature ? deliveryData.signature : null);
                            }
                        }
                    }, new ErrorListener() {
                        public void onErrorResponse(VolleyError volleyError) {
                            FinskyLog.w("SelfUpdate error - %s", volleyError);
                        }
                    });
                }
            });
            return true;
        }
    }

    private void startSelfUpdateDownload(String url, String cookieName, String cookieValue, long downloadSize, String downloadSignature) {
        this.mDownloadSize = downloadSize;
        this.mDownloadSignature = downloadSignature;
        Download download = new DownloadImpl(url, "", null, cookieName, cookieValue, null, -1, -1, null, false, true);
        this.mUpdateDownload = download;
        this.mDownloadQueue.addListener(this);
        this.mDownloadQueue.add(download);
        FinskyApp.get().getEventLogger().logBackgroundEvent(100, FinskyApp.get().getPackageName(), null, 0, null, this.mLogAppData);
    }

    public void onComplete(final Download download) {
        if (download != this.mUpdateDownload) {
            FinskyLog.d("Self-update ignoring completed download " + download, new Object[0]);
            return;
        }
        final String packageName = FinskyApp.get().getPackageName();
        FinskyApp.get().getEventLogger().logBackgroundEvent(102, packageName, null, 0, null, this.mLogAppData);
        this.mUpdateDownload = null;
        if (this.mOnAppExitDeferrer != null) {
            FinskyLog.d("Self-update package Uri was already assigned!", new Object[0]);
            return;
        }
        FinskyLog.d("Self-update ready to be installed, waiting for market to close.", new Object[0]);
        this.mOnAppExitDeferrer = new ApplicationDismissedDeferrer(FinskyApp.get());
        this.mOnAppExitDeferrer.runOnApplicationClose(new Runnable() {
            public void run() {
                PackageManagerHelper.installPackage(download.getContentUri(), SelfUpdateScheduler.this.mDownloadSize, SelfUpdateScheduler.this.mDownloadSignature, new InstallPackageListener() {
                    public void installSucceeded() {
                        FinskyLog.wtf("Unexpected install success for %s", packageName);
                    }

                    public void installFailed(int errorCode, String exceptionType) {
                        FinskyApp.get().getEventLogger().logBackgroundEvent(111, packageName, null, errorCode, exceptionType, SelfUpdateScheduler.this.mLogAppData);
                    }
                }, false, "");
            }
        }, 10000);
    }

    public void onError(Download download, int httpStatus) {
        if (download == this.mUpdateDownload) {
            FinskyApp.get().getEventLogger().logBackgroundEvent(104, FinskyApp.get().getPackageName(), null, httpStatus, null, this.mLogAppData);
            FinskyLog.e("Self-update failed because of HTTP error code: %d", Integer.valueOf(httpStatus));
        }
    }

    public void onStart(Download download) {
        if (download == this.mUpdateDownload) {
            FinskyApp.get().getEventLogger().logBackgroundEvent(101, FinskyApp.get().getPackageName(), null, 0, null, this.mLogAppData);
        }
    }

    public void onCancel(Download download) {
    }

    public void onNotificationClicked(Download download) {
    }

    public void onProgress(Download download, DownloadProgress progress) {
    }

    public static synchronized String getCertificateHashSelfUpdateMD5(Context context) {
        String str;
        synchronized (SelfUpdateScheduler.class) {
            if (sCertificateHashSelfUpdateMD5 == null) {
                computeClientHashes(context);
            }
            str = sCertificateHashSelfUpdateMD5;
        }
        return str;
    }

    public static synchronized String getCertificateHash(Context context) {
        String str;
        synchronized (SelfUpdateScheduler.class) {
            if (sCertificateHash == null) {
                computeClientHashes(context);
            }
            str = sCertificateHash;
        }
        return str;
    }

    private static void computeClientHashes(Context context) {
        try {
            byte[] signature = context.getPackageManager().getPackageInfo(context.getPackageName(), 64).signatures[0].toByteArray();
            sCertificateHashSelfUpdateMD5 = Md5Util.secureHash(signature);
            sCertificateHash = Sha1Util.secureHash(signature);
        } catch (NameNotFoundException e) {
            FinskyLog.wtf("Unable to find package info for %s - %s", vendingPackageName, e);
            sCertificateHashSelfUpdateMD5 = "signature-hash-NameNotFoundException";
            sCertificateHash = "certificate-hash-NameNotFoundException";
        }
    }
}
