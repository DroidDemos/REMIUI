package com.google.android.finsky.services;

import android.app.Service;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.IBinder;
import android.text.TextUtils;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.appstate.PackageStateRepository.PackageState;
import com.google.android.finsky.protos.Preloads.Preload;
import com.google.android.finsky.protos.Preloads.PreloadsResponse;
import com.google.android.finsky.utils.ConsistencyTokenHelper;
import com.google.android.finsky.utils.DeviceConfigurationHelper;
import com.google.android.finsky.utils.DeviceConfigurationHelper.Listener;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.Utils;
import java.util.List;

public class VpaService extends Service {
    private static VpaService sInstance;
    private SetupHoldListener mListener;
    private int mServiceStartId;
    private int mStartupRefCount;
    private boolean mVpaRunning;

    public VpaService() {
        this.mStartupRefCount = 0;
    }

    static {
        sInstance = null;
    }

    public static void startVpa() {
        FinskyApp appContext = FinskyApp.get();
        Intent intent = new Intent(appContext, VpaService.class);
        intent.setData(Uri.parse("playsetupservice://startvpa"));
        appContext.startService(intent);
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        this.mServiceStartId = startId;
        this.mStartupRefCount++;
        FinskyApp.get().getAppStates().load(new Runnable() {
            public void run() {
                VpaService.this.mStartupRefCount = VpaService.this.mStartupRefCount - 1;
                if (!VpaService.this.handleStartVpaCommand()) {
                    VpaService.this.stopServiceIfDone();
                }
            }
        });
        return 3;
    }

    private void stopServiceIfDone() {
        Utils.ensureOnMainThread();
        if (this.mStartupRefCount == 0 && !this.mVpaRunning) {
            notifyListener(1, null, false);
            stopSelf(this.mServiceStartId);
        }
    }

    public void onCreate() {
        sInstance = this;
    }

    public void onDestroy() {
        notifyListener(1, null, false);
        sInstance = null;
    }

    private boolean handleStartVpaCommand() {
        if (this.mVpaRunning) {
            FinskyLog.wtf("Received command to load VPA while already handling", new Object[0]);
            return false;
        }
        this.mVpaRunning = true;
        Utils.executeMultiThreaded(new AsyncTask<Void, Void, String>() {
            protected String doInBackground(Void... voids) {
                Intent intent = new Intent();
                intent.setAction("android.autoinstalls.config.action.PLAY_AUTO_INSTALL");
                List<ResolveInfo> receivers = FinskyApp.get().getPackageManager().queryBroadcastReceivers(intent, 0);
                if (receivers.size() > 0) {
                    String packageName = ((ResolveInfo) receivers.get(0)).activityInfo.packageName;
                    PackageState packageState = FinskyApp.get().getPackageInfoRepository().get(packageName);
                    if (packageState == null) {
                        FinskyLog.wtf("Null PackageState for potential VPA stub %s", packageName);
                        return null;
                    }
                    int versionCode = packageState.installedVersion;
                    boolean isSystem = packageState.isSystemApp;
                    boolean isUpdatedSystem = packageState.isUpdatedSystemApp;
                    if ((versionCode == 1 || isUpdatedSystem) && isSystem) {
                        FinskyLog.d("Found VPA stub %s:%d", packageName, Integer.valueOf(versionCode));
                        return packageName;
                    }
                    FinskyLog.d("Rejected VPA stub %s:%d", packageName, Integer.valueOf(versionCode));
                }
                return null;
            }

            protected void onPostExecute(String packageName) {
                if (packageName == null) {
                    FinskyLog.d("No VPA stub found - stopping service", new Object[0]);
                    VpaService.this.mVpaRunning = false;
                    VpaService.this.stopServiceIfDone();
                    return;
                }
                VpaService.this.vpaGetDeviceConfig(packageName);
            }
        }, new Void[0]);
        return true;
    }

    private void vpaGetDeviceConfig(final String configPackageName) {
        String deviceConfigToken = DeviceConfigurationHelper.getToken();
        if (TextUtils.isEmpty(deviceConfigToken)) {
            DeviceConfigurationHelper.requestToken(FinskyApp.get().getDfeApi(), new Listener() {
                public void onSuccess() {
                    VpaService.this.vpaGetConsistencyToken(configPackageName, DeviceConfigurationHelper.getToken());
                }

                public void onError(VolleyError error) {
                    VpaService.this.vpaGetConsistencyToken(configPackageName, null);
                }
            });
        } else {
            vpaGetConsistencyToken(configPackageName, deviceConfigToken);
        }
    }

    private void vpaGetConsistencyToken(final String configPackageName, final String deviceConfigToken) {
        ConsistencyTokenHelper.get(FinskyApp.get(), new ConsistencyTokenHelper.Listener() {
            public void onTokenReceived(String consistencyToken) {
                VpaService.this.vpaGetPreloads(configPackageName, deviceConfigToken, consistencyToken);
            }
        });
    }

    private void vpaGetPreloads(String configPackageName, String deviceConfigToken, String consistencyToken) {
        DfeApi dfeApi = FinskyApp.get().getDfeApi();
        final String accountName = dfeApi.getAccountName();
        dfeApi.preloads(deviceConfigToken, consistencyToken, configPackageName, new Response.Listener<PreloadsResponse>() {
            public void onResponse(PreloadsResponse preloadsResponse) {
                FinskyLog.d("VPA response received", new Object[0]);
                Preload configPreload = preloadsResponse.configPreload;
                if (configPreload != null) {
                    VpaService.this.downloadVpaConfig(accountName, configPreload);
                }
                if (preloadsResponse.appPreload.length > 0) {
                    VpaService.this.downloadPackages(accountName, preloadsResponse.appPreload, 2, true);
                }
                VpaService.this.mVpaRunning = false;
                VpaService.this.stopServiceIfDone();
            }
        }, new ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
                FinskyLog.w("Failed to retrieve preloads: %s", volleyError);
                VpaService.this.mVpaRunning = false;
                VpaService.this.stopServiceIfDone();
            }
        });
    }

    private void downloadVpaConfig(String accountName, Preload preloadConfig) {
        Preload[] preloadPackages = new Preload[]{preloadConfig};
        if (TextUtils.isEmpty(preloadConfig.title)) {
            preloadConfig.title = preloadConfig.docid.backendDocid;
        }
        downloadPackages(accountName, preloadPackages, 1, false);
    }

    private void downloadPackages(String accountName, Preload[] preloadPackages, int priority, boolean visible) {
        int preloadAppsCount = preloadPackages.length;
        String[] packageNames = new String[preloadAppsCount];
        int[] versionCodes = new int[preloadAppsCount];
        String[] titles = new String[preloadAppsCount];
        int[] priorities = new int[preloadAppsCount];
        String[] deliveryTokens = new String[preloadAppsCount];
        String[] appIconUrls = new String[preloadAppsCount];
        for (int i = 0; i < preloadPackages.length; i++) {
            Preload preload = preloadPackages[i];
            packageNames[i] = preload.docid.backendDocid;
            versionCodes[i] = preload.versionCode;
            titles[i] = preload.title;
            priorities[i] = priority;
            deliveryTokens[i] = preload.deliveryToken;
            if (preload.icon != null && preload.icon.hasImageUrl && preload.icon.hasSupportsFifeUrlOptions && preload.icon.supportsFifeUrlOptions) {
                appIconUrls[i] = preload.icon.imageUrl;
            } else {
                appIconUrls[i] = null;
            }
            FinskyLog.d("Requesting preload of %s:%d", preload.docid.backendDocid, Integer.valueOf(preload.versionCode));
        }
        RestoreService.restorePackages(getApplicationContext(), false, accountName, visible, packageNames, versionCodes, titles, priorities, deliveryTokens, appIconUrls);
    }

    public static boolean shouldHold() {
        return sInstance != null && sInstance.mVpaRunning;
    }

    public static boolean registerListener(SetupHoldListener listener) {
        if (listener == null) {
            if (sInstance == null) {
                return true;
            }
            sInstance.mListener = null;
            return true;
        } else if (sInstance == null || !sInstance.mVpaRunning) {
            return false;
        } else {
            sInstance.registerAndNotifyListener(listener);
            return true;
        }
    }

    private void registerAndNotifyListener(SetupHoldListener listener) {
        this.mListener = listener;
        new Handler(getMainLooper()).post(new Runnable() {
            public void run() {
                if (VpaService.this.mVpaRunning) {
                    VpaService.this.notifyListener(2, null, false);
                } else {
                    VpaService.this.notifyListener(1, null, false);
                }
            }
        });
    }

    private void notifyListener(int eventCode, String packageName, boolean cancelable) {
        if (this.mListener != null) {
            this.mListener.onStatusChange(eventCode, packageName, null, cancelable, "VpaService");
            if (eventCode == 1) {
                this.mListener = null;
            }
        }
    }
}
