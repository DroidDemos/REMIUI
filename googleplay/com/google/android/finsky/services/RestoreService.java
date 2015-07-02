package com.google.android.finsky.services;

import android.accounts.Account;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.IBinder;
import android.text.TextUtils;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.analytics.BackgroundEventBuilder;
import com.google.android.finsky.analytics.PlayStore.AppData;
import com.google.android.finsky.api.AccountHandler;
import com.google.android.finsky.api.DfeApiConfig;
import com.google.android.finsky.api.DfeServerError;
import com.google.android.finsky.appstate.PackageStateRepository.PackageState;
import com.google.android.finsky.config.G;
import com.google.android.finsky.installer.InstallerListener;
import com.google.android.finsky.installer.InstallerListener.InstallerPackageEvent;
import com.google.android.finsky.installer.PackageInstallerFacade;
import com.google.android.finsky.installer.PackageInstallerFactory;
import com.google.android.finsky.protos.Restore.BackupDocumentInfo;
import com.google.android.finsky.protos.Restore.GetBackupDocumentChoicesResponse;
import com.google.android.finsky.receivers.Installer;
import com.google.android.finsky.utils.ConsistencyTokenHelper;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.FinskyPreferences;
import com.google.android.finsky.utils.Lists;
import com.google.android.finsky.utils.Maps;
import com.google.android.finsky.utils.Utils;
import com.google.android.finsky.utils.persistence.FileBasedKeyValueStore;
import com.google.android.finsky.utils.persistence.WriteThroughKeyValueStore;
import com.google.android.play.image.BitmapLoader.BitmapContainer;
import com.google.android.play.image.BitmapLoader.BitmapLoadedHandler;
import com.google.android.volley.DisplayMessageError;
import com.google.android.wallet.instrumentmanager.R;
import java.util.Map;
import java.util.Map.Entry;

public class RestoreService extends Service {
    private static final Boolean DEBUG_SELF_ANDROID_ID;
    private static int[] sErrorRetryBlacklist;
    private static RestoreService sInstance;
    private boolean mAddedInstallerListener;
    private int mAppIconSize;
    private Map<String, BitmapContainer> mBitmapContainers;
    private int mDebugCountAlreadyInstalled;
    private int mDebugCountAlreadyOtherAccount;
    private int mDebugCountAlreadyTracked;
    private int mDebugCountMaxAttemptsExceeded;
    private boolean mHandledStartupIntent;
    private SetupHoldListener mListener;
    private PackageInstallerFacade mPackageInstaller;
    private int mServiceStartId;
    private RestoreTracker mTracker;

    static /* synthetic */ class AnonymousClass5 {
        static final /* synthetic */ int[] $SwitchMap$com$google$android$finsky$installer$InstallerListener$InstallerPackageEvent;

        static {
            $SwitchMap$com$google$android$finsky$installer$InstallerListener$InstallerPackageEvent = new int[InstallerPackageEvent.values().length];
            try {
                $SwitchMap$com$google$android$finsky$installer$InstallerListener$InstallerPackageEvent[InstallerPackageEvent.DOWNLOAD_PENDING.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$google$android$finsky$installer$InstallerListener$InstallerPackageEvent[InstallerPackageEvent.DOWNLOADING.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$google$android$finsky$installer$InstallerListener$InstallerPackageEvent[InstallerPackageEvent.INSTALLING.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$google$android$finsky$installer$InstallerListener$InstallerPackageEvent[InstallerPackageEvent.UNINSTALLING.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$google$android$finsky$installer$InstallerListener$InstallerPackageEvent[InstallerPackageEvent.DOWNLOAD_CANCELLED.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$google$android$finsky$installer$InstallerListener$InstallerPackageEvent[InstallerPackageEvent.DOWNLOAD_ERROR.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$com$google$android$finsky$installer$InstallerListener$InstallerPackageEvent[InstallerPackageEvent.INSTALL_ERROR.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$com$google$android$finsky$installer$InstallerListener$InstallerPackageEvent[InstallerPackageEvent.INSTALLED.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                $SwitchMap$com$google$android$finsky$installer$InstallerListener$InstallerPackageEvent[InstallerPackageEvent.UNINSTALLED.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
        }
    }

    private static class AccountFetchStatus {
        String androidId;
        int attempts;
        boolean inFlight;

        private AccountFetchStatus() {
        }
    }

    private static class FetchBitmapStatus {
        int attempts;
        String bitmapUrl;
        long retryTime;

        private FetchBitmapStatus() {
        }
    }

    private static class PackageInstallStatus {
        String accountName;
        String appIconUrl;
        int attempts;
        String deliveryToken;
        int priority;
        long retryTime;
        String title;
        int versionCode;
        boolean visible;

        private PackageInstallStatus() {
        }
    }

    class RestoreResponseListener implements Listener<GetBackupDocumentChoicesResponse> {
        private final String mAccountName;

        public RestoreResponseListener(String accountName) {
            this.mAccountName = accountName;
        }

        public void onResponse(GetBackupDocumentChoicesResponse response) {
            RestoreService.this.mDebugCountAlreadyTracked = 0;
            RestoreService.this.mDebugCountAlreadyOtherAccount = 0;
            RestoreService.this.mDebugCountAlreadyInstalled = 0;
            RestoreService.this.mDebugCountMaxAttemptsExceeded = 0;
            int numDeferred = 0;
            Installer installer = FinskyApp.get().getInstaller();
            for (BackupDocumentInfo documentInfo : response.backupDocumentInfo) {
                String packageName = documentInfo.docid.backendDocid;
                int packageVersion = documentInfo.versionCode;
                String packageTitle = documentInfo.title;
                if (RestoreService.this.shouldRestore(packageName, packageVersion, this.mAccountName, installer)) {
                    int priority = 3;
                    if (documentInfo.hasRestorePriority && documentInfo.restorePriority < 100) {
                        priority = 1;
                    }
                    String appIconUrl = null;
                    if (documentInfo.thumbnailImage != null) {
                        appIconUrl = documentInfo.thumbnailImage.imageUrl;
                    }
                    RestoreService.this.mTracker.startPackage(packageName, packageVersion, this.mAccountName, packageTitle, priority, null, true, appIconUrl);
                    installer.setVisibility(packageName, true, false, false);
                    installer.requestInstall(packageName, packageVersion, this.mAccountName, packageTitle, true, "restore", priority);
                    numDeferred++;
                    if (!TextUtils.isEmpty(appIconUrl)) {
                        RestoreService.this.startBitmapDownload(packageName, appIconUrl);
                    }
                }
            }
            FinskyLog.d("Attempted to restore %d assets.", Integer.valueOf(response.backupDocumentInfo.length));
            FinskyLog.d("  Skipped (already tracked): %d", Integer.valueOf(RestoreService.this.mDebugCountAlreadyTracked));
            FinskyLog.d("  Skipped (other account): %d", Integer.valueOf(RestoreService.this.mDebugCountAlreadyOtherAccount));
            FinskyLog.d("  Skipped (attempts exceeded): %d", Integer.valueOf(RestoreService.this.mDebugCountMaxAttemptsExceeded));
            FinskyLog.d("  Skipped (already installed): %d", Integer.valueOf(RestoreService.this.mDebugCountAlreadyInstalled));
            if (numDeferred > 0) {
                FinskyLog.d("  Posted for deferred download/install: %d", Integer.valueOf(numDeferred));
                if (((Boolean) FinskyPreferences.setupWizardStartDownloads.get()).booleanValue()) {
                    installer.startDeferredInstalls();
                } else {
                    long holdoff;
                    if (VERSION.SDK_INT >= 21) {
                        holdoff = ((Long) G.appRestoreFailsafeMs.get()).longValue();
                    } else {
                        holdoff = ((Long) G.appRestoreHoldoffMs.get()).longValue();
                    }
                    RestoreService.this.setAlarm(RestoreService.getKickIntent(RestoreService.this.getApplicationContext()), holdoff);
                }
            }
            RestoreService.this.mTracker.finishAccount(this.mAccountName, true, 0);
        }
    }

    private class RestoreTracker implements InstallerListener {
        private final String ACCOUNT_MAP_KEY_AID;
        private final String ACCOUNT_MAP_KEY_ATTEMPTS;
        private final String KEY_VALUE_DIRECTORY;
        private final String KEY_VALUE_FILE_ACCOUNTS;
        private final String KEY_VALUE_FILE_PACKAGES;
        private final String PACKAGE_MAP_ACCOUNT_NAME;
        private final String PACKAGE_MAP_APP_ICON_URL;
        private final String PACKAGE_MAP_DELIVERY_TOKEN;
        private final String PACKAGE_MAP_KEY_ATTEMPTS;
        private final String PACKAGE_MAP_PRIORITY;
        private final String PACKAGE_MAP_RETRY_TIME;
        private final String PACKAGE_MAP_TITLE;
        private final String PACKAGE_MAP_VERSION_CODE;
        private final String PACKAGE_MAP_VISIBLE;
        private final Map<String, AccountFetchStatus> mAccountStatusMap;
        private WriteThroughKeyValueStore mAccountStore;
        private final Map<String, FetchBitmapStatus> mBitmapStatusMap;
        private int mFailed;
        private String mInstallerRunningPackage;
        private final Map<String, PackageInstallStatus> mPackageStatusMap;
        private WriteThroughKeyValueStore mPackagesStore;
        private int mStartupRefCount;
        private int mSucceeded;

        private RestoreTracker() {
            this.KEY_VALUE_DIRECTORY = "RestoreTracker";
            this.KEY_VALUE_FILE_ACCOUNTS = "account-";
            this.KEY_VALUE_FILE_PACKAGES = "package-";
            this.ACCOUNT_MAP_KEY_ATTEMPTS = "attempts";
            this.ACCOUNT_MAP_KEY_AID = "aid";
            this.PACKAGE_MAP_KEY_ATTEMPTS = "attempts";
            this.PACKAGE_MAP_VERSION_CODE = "versionCode";
            this.PACKAGE_MAP_ACCOUNT_NAME = "accountName";
            this.PACKAGE_MAP_TITLE = "title";
            this.PACKAGE_MAP_PRIORITY = "priority";
            this.PACKAGE_MAP_DELIVERY_TOKEN = "deliveryToken";
            this.PACKAGE_MAP_VISIBLE = "visible";
            this.PACKAGE_MAP_APP_ICON_URL = "appIconUrl";
            this.PACKAGE_MAP_RETRY_TIME = "retryTime";
            this.mAccountStatusMap = Maps.newHashMap();
            this.mPackageStatusMap = Maps.newHashMap();
            this.mBitmapStatusMap = Maps.newHashMap();
            this.mStartupRefCount = 0;
            this.mSucceeded = 0;
            this.mFailed = 0;
            this.mInstallerRunningPackage = null;
        }

        public void initAccountStore(Context context, final Runnable onComplete) {
            if (this.mAccountStore == null) {
                this.mAccountStore = new WriteThroughKeyValueStore(new FileBasedKeyValueStore(context.getDir("RestoreTracker", 0), "account-"));
                this.mAccountStore.load(new Runnable() {
                    public void run() {
                        Context context = RestoreService.this;
                        Map<String, Map<String, String>> fetchedMap = RestoreTracker.this.mAccountStore.fetchAll();
                        if (fetchedMap.size() > 0) {
                            for (Entry<String, Map<String, String>> entry : fetchedMap.entrySet()) {
                                String key = (String) entry.getKey();
                                String accountName = Uri.decode(key);
                                AccountFetchStatus status = RestoreTracker.this.convertEntryToStatus(context, accountName, (Map) entry.getValue());
                                if (status == null) {
                                    RestoreTracker.this.mAccountStore.delete(key);
                                } else {
                                    RestoreTracker.this.mAccountStatusMap.put(accountName, status);
                                }
                            }
                        }
                        onComplete.run();
                    }
                });
                return;
            }
            this.mAccountStore.load(onComplete);
        }

        private AccountFetchStatus convertEntryToStatus(Context context, String accountName, Map<String, String> valueMap) {
            if (AccountHandler.findAccount(accountName, context) == null) {
                FinskyLog.w("Unknown account %s", FinskyLog.scrubPii(accountName));
                return null;
            }
            String countString = (String) valueMap.get("attempts");
            String aid = (String) valueMap.get("aid");
            if (TextUtils.isEmpty(countString) || TextUtils.isEmpty(aid)) {
                FinskyLog.w("Missing data for account %s", FinskyLog.scrubPii(accountName));
                return null;
            }
            try {
                int count = Integer.valueOf(countString).intValue();
                if (count < 0 || count >= ((Integer) G.appRestoreFetchListMaxAttempts.get()).intValue()) {
                    FinskyLog.d("Reached limit %d for %s", Integer.valueOf(count), FinskyLog.scrubPii(accountName));
                    return null;
                }
                AccountFetchStatus result = new AccountFetchStatus();
                result.attempts = count;
                result.androidId = aid;
                return result;
            } catch (NumberFormatException e) {
                FinskyLog.w("Bad data for account %s (%s, %s)", FinskyLog.scrubPii(accountName), countString, aid);
                return null;
            }
        }

        public void startAccount(String accountName, String aid) {
            AccountFetchStatus status = (AccountFetchStatus) this.mAccountStatusMap.get(accountName);
            if (status == null) {
                status = new AccountFetchStatus();
                status.attempts = 0;
                this.mAccountStatusMap.put(accountName, status);
            }
            status.attempts++;
            status.androidId = aid;
            status.inFlight = true;
            writeAccountStatus(accountName);
        }

        public void finishAccount(String accountName, boolean success, int errorCode) {
            int i;
            AccountFetchStatus status = (AccountFetchStatus) this.mAccountStatusMap.get(accountName);
            BackgroundEventBuilder errorCode2 = new BackgroundEventBuilder(118).setErrorCode(errorCode);
            if (status != null) {
                i = status.attempts;
            } else {
                i = 0;
            }
            FinskyApp.get().getEventLogger(accountName).logBackgroundEvent(errorCode2.setAttempts(i).build());
            if (success) {
                this.mAccountStatusMap.remove(accountName);
                writeAccountStatus(accountName);
            } else if (status != null) {
                status.inFlight = false;
            }
            stopServiceIfDone();
        }

        public boolean tryAgainOrDeleteAccount(String accountName) {
            AccountFetchStatus status = (AccountFetchStatus) this.mAccountStatusMap.get(accountName);
            if (status == null || status.attempts < ((Integer) G.appRestoreFetchListMaxAttempts.get()).intValue()) {
                return true;
            }
            FinskyLog.d("Reached limit %d for %s", Integer.valueOf(status.attempts), FinskyLog.scrubPii(accountName));
            this.mAccountStatusMap.remove(accountName);
            writeAccountStatus(accountName);
            return false;
        }

        public boolean isAccountInFlight(String accountName) {
            AccountFetchStatus status = (AccountFetchStatus) this.mAccountStatusMap.get(accountName);
            return status != null && status.inFlight;
        }

        private void writeAccountStatus(String accountName) {
            String key = Uri.encode(accountName);
            AccountFetchStatus status = (AccountFetchStatus) this.mAccountStatusMap.get(accountName);
            if (status == null) {
                this.mAccountStore.delete(key);
                return;
            }
            Map<String, String> writeMap = Maps.newHashMap();
            writeMap.put("attempts", Integer.toString(status.attempts));
            writeMap.put("aid", status.androidId);
            this.mAccountStore.put(key, writeMap);
        }

        private void initPackagesStore(Context context, final Runnable onComplete) {
            if (this.mPackagesStore == null) {
                this.mPackagesStore = new WriteThroughKeyValueStore(new FileBasedKeyValueStore(context.getDir("RestoreTracker", 0), "package-"));
                this.mPackagesStore.load(new Runnable() {
                    public void run() {
                        Context context = RestoreService.this;
                        Map<String, Map<String, String>> fetchedMap = RestoreTracker.this.mPackagesStore.fetchAll();
                        if (fetchedMap.size() > 0) {
                            for (Entry<String, Map<String, String>> entry : fetchedMap.entrySet()) {
                                String key = (String) entry.getKey();
                                String packageName = Uri.decode(key);
                                PackageInstallStatus status = RestoreTracker.this.convertEntryToPackageStatus(context, packageName, (Map) entry.getValue());
                                if (status == null) {
                                    RestoreTracker.this.mPackagesStore.delete(key);
                                } else {
                                    RestoreTracker.this.mPackageStatusMap.put(packageName, status);
                                }
                            }
                        }
                        onComplete.run();
                    }
                });
                return;
            }
            this.mPackagesStore.load(onComplete);
        }

        private PackageInstallStatus convertEntryToPackageStatus(Context context, String packageName, Map<String, String> valueMap) {
            try {
                FinskyApp.get().getPackageManager().getPackageInfo(packageName, 0);
                return null;
            } catch (NameNotFoundException e) {
                String countString = (String) valueMap.get("attempts");
                String versionCodeString = (String) valueMap.get("versionCode");
                String accountName = (String) valueMap.get("accountName");
                String title = (String) valueMap.get("title");
                String priorityString = (String) valueMap.get("priority");
                String deliveryToken = (String) valueMap.get("deliveryToken");
                String visibleString = (String) valueMap.get("visible");
                String appIconUrl = (String) valueMap.get("appIconUrl");
                String retryTimeString = (String) valueMap.get("retryTime");
                if (TextUtils.isEmpty(countString) || TextUtils.isEmpty(versionCodeString) || TextUtils.isEmpty(accountName) || TextUtils.isEmpty(title) || TextUtils.isEmpty(priorityString) || TextUtils.isEmpty(visibleString) || TextUtils.isEmpty(appIconUrl) || TextUtils.isEmpty(retryTimeString)) {
                    FinskyLog.w("Missing data for package %s", packageName);
                    return null;
                }
                try {
                    int count = Integer.valueOf(countString).intValue();
                    int versionCode = Integer.valueOf(versionCodeString).intValue();
                    int priority = Integer.valueOf(priorityString).intValue();
                    boolean visible = Boolean.valueOf(visibleString).booleanValue();
                    long retryTime = Long.valueOf(retryTimeString).longValue();
                    if (count < 0 || count >= ((Integer) G.appRestoreDownloadMaxAttempts.get()).intValue()) {
                        FinskyLog.d("Reached limit %d for %s", Integer.valueOf(count), packageName);
                        return null;
                    } else if (AccountHandler.findAccount(accountName, context) == null) {
                        FinskyLog.w("Unknown account %s", FinskyLog.scrubPii(accountName));
                        return null;
                    } else {
                        PackageInstallStatus result = new PackageInstallStatus();
                        result.attempts = count;
                        result.versionCode = versionCode;
                        result.accountName = accountName;
                        result.title = title;
                        result.priority = priority;
                        result.deliveryToken = deliveryToken;
                        result.visible = visible;
                        result.appIconUrl = appIconUrl;
                        result.retryTime = retryTime;
                        return result;
                    }
                } catch (NumberFormatException e2) {
                    FinskyLog.w("Bad data for package %s (%s, %s, %s, %s, %s, %s)", packageName, countString, versionCodeString, FinskyLog.scrubPii(accountName), title, priorityString, visibleString, retryTimeString);
                    return null;
                }
            }
        }

        public void startPackage(String packageName, int versionCode, String accountName, String title, int priority, String deliveryToken, boolean visible, String appIconUrl) {
            PackageInstallStatus status = (PackageInstallStatus) this.mPackageStatusMap.get(packageName);
            if (status == null) {
                status = new PackageInstallStatus();
                status.attempts = 0;
                this.mPackageStatusMap.put(packageName, status);
            }
            status.attempts++;
            status.versionCode = versionCode;
            status.accountName = accountName;
            status.title = title;
            status.priority = priority;
            status.deliveryToken = deliveryToken;
            status.visible = visible;
            status.appIconUrl = appIconUrl;
            status.retryTime = 0;
            writePackageStatus(packageName);
        }

        public void finishPackage(String packageName, boolean success, boolean willRetry) {
            if (success) {
                this.mSucceeded++;
            } else if (!willRetry) {
                this.mFailed++;
            }
            if (success || !willRetry) {
                this.mPackageStatusMap.remove(packageName);
                writePackageStatus(packageName);
            }
            stopServiceIfDone();
        }

        public void writePackageRetryTime(String packageName, long retryTime) {
            PackageInstallStatus status = (PackageInstallStatus) this.mPackageStatusMap.get(packageName);
            if (status == null) {
                FinskyLog.d("Unexpected missing package %s, can't write retry time", packageName);
                return;
            }
            status.retryTime = retryTime;
            writePackageStatus(packageName);
        }

        public boolean tryAgainPackage(String packageName) {
            PackageInstallStatus status = (PackageInstallStatus) this.mPackageStatusMap.get(packageName);
            if (status == null) {
                return false;
            }
            if (status.attempts < ((Integer) G.appRestoreDownloadMaxAttempts.get()).intValue()) {
                return true;
            }
            FinskyLog.d("Reached limit %d for %s", Integer.valueOf(status.attempts), packageName);
            return false;
        }

        private void writePackageStatus(String packageName) {
            String key = Uri.encode(packageName);
            PackageInstallStatus status = (PackageInstallStatus) this.mPackageStatusMap.get(packageName);
            if (status == null) {
                this.mPackagesStore.delete(key);
                return;
            }
            Map<String, String> writeMap = Maps.newHashMap();
            writeMap.put("attempts", Integer.toString(status.attempts));
            writeMap.put("versionCode", Integer.toString(status.versionCode));
            writeMap.put("accountName", status.accountName);
            writeMap.put("title", status.title);
            writeMap.put("priority", Integer.toString(status.priority));
            if (!TextUtils.isEmpty(status.deliveryToken)) {
                writeMap.put("deliveryToken", status.deliveryToken);
            }
            writeMap.put("visible", Boolean.toString(status.visible));
            writeMap.put("appIconUrl", status.appIconUrl);
            writeMap.put("retryTime", Long.toString(status.retryTime));
            this.mPackagesStore.put(key, writeMap);
        }

        public void startBitmap(String packageName, String bitmapUrl) {
            FetchBitmapStatus status = (FetchBitmapStatus) this.mBitmapStatusMap.get(packageName);
            if (status == null) {
                status = new FetchBitmapStatus();
                status.attempts = 0;
                this.mBitmapStatusMap.put(packageName, status);
            }
            status.attempts++;
            status.bitmapUrl = bitmapUrl;
            status.retryTime = 0;
        }

        public void finishBitmap(String packageName) {
            this.mBitmapStatusMap.remove(packageName);
            stopServiceIfDone();
        }

        public boolean tryAgainBitmap(String packageName) {
            FetchBitmapStatus status = (FetchBitmapStatus) this.mBitmapStatusMap.get(packageName);
            if (status == null) {
                return false;
            }
            if (status.attempts >= ((Integer) G.appRestoreAppIconMaxAttempts.get()).intValue()) {
                FinskyLog.d("Reached limit %d for %s", Integer.valueOf(status.attempts), packageName);
                return false;
            } else if (!this.mPackageStatusMap.containsKey(packageName)) {
                return false;
            } else {
                if (FinskyApp.get().getPackageInfoRepository().get(packageName) != null) {
                    return false;
                }
                return true;
            }
        }

        public void stopServiceIfDone() {
            if (this.mPackageStatusMap.isEmpty() && this.mAccountStatusMap.isEmpty() && this.mBitmapStatusMap.isEmpty() && this.mStartupRefCount <= 0) {
                FinskyLog.d("Restore complete with %d success and %d failed.", Integer.valueOf(this.mSucceeded), Integer.valueOf(this.mFailed));
                RestoreService.this.notifyListener(1, null, null, false);
                RestoreService.this.stopSelf(RestoreService.this.mServiceStartId);
            }
        }

        public boolean shouldHold(String ignorePackage) {
            if (!this.mAccountStatusMap.isEmpty()) {
                for (AccountFetchStatus accountFetchStatus : this.mAccountStatusMap.values()) {
                    if (accountFetchStatus.inFlight) {
                        return true;
                    }
                }
            }
            if (!this.mPackageStatusMap.isEmpty()) {
                for (Entry<String, PackageInstallStatus> entry : this.mPackageStatusMap.entrySet()) {
                    if ((ignorePackage == null || !ignorePackage.equals(entry.getKey())) && ((PackageInstallStatus) entry.getValue()).priority == 1) {
                        return true;
                    }
                }
            }
            return false;
        }

        public void onInstallPackageEvent(String packageName, InstallerPackageEvent event, int statusCode) {
            boolean cancelBitmap = false;
            switch (AnonymousClass5.$SwitchMap$com$google$android$finsky$installer$InstallerListener$InstallerPackageEvent[event.ordinal()]) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                case R.styleable.WalletImFormEditText_required /*4*/:
                    trackPackageForListener(packageName, true, false);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    FinskyLog.e("Restore package %s download cancelled", packageName);
                    trackPackageForListener(packageName, false, false);
                    finishPackage(packageName, false, false);
                    cancelBitmap = true;
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    FinskyLog.e("Restore package %s download error %d", packageName, Integer.valueOf(statusCode));
                    boolean willRetry = !RestoreService.this.inErrorRetryBlacklist(statusCode) && RestoreService.this.mTracker.tryAgainPackage(packageName);
                    trackPackageForListener(packageName, false, willRetry);
                    finishPackage(packageName, false, willRetry);
                    if (!willRetry) {
                        cancelBitmap = true;
                        break;
                    }
                    Long l;
                    Intent intent = RestoreService.getPackageRestoreIntent(RestoreService.this, packageName);
                    PackageInstallStatus status = (PackageInstallStatus) this.mPackageStatusMap.get(packageName);
                    if (status == null || status.priority != 1) {
                        l = (Long) G.appRestoreRetryDownloadHoldoffMs.get();
                    } else {
                        l = (Long) G.appRestoreRetryDownloadHoldoffHighPriorityMs.get();
                    }
                    RestoreService.this.mTracker.writePackageRetryTime(packageName, RestoreService.this.setAlarm(intent, RestoreService.this.jitterDelay(l.longValue())));
                    FinskyApp.get().getInstaller().promiseInstall(packageName, 0, status.title, null);
                    RestoreService.this.startBitmapDownload(packageName, status.appIconUrl);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                    FinskyLog.e("Restore package %s install error %d", packageName, Integer.valueOf(statusCode));
                    trackPackageForListener(packageName, false, false);
                    finishPackage(packageName, false, false);
                    cancelBitmap = true;
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                    FinskyLog.d("Restore package %s install complete", packageName);
                    trackPackageForListener(packageName, false, false);
                    finishPackage(packageName, true, false);
                    cancelBitmap = true;
                    break;
                default:
                    FinskyLog.wtf("enum %s", event);
                    break;
            }
            if (cancelBitmap) {
                RestoreService.this.cancelBitmapDownload(packageName);
            }
        }

        private void trackPackageForListener(String packageName, boolean running, boolean willRetry) {
            PackageInstallStatus status = (PackageInstallStatus) this.mPackageStatusMap.get(packageName);
            if (status != null && status.priority == 1) {
                if (running) {
                    this.mInstallerRunningPackage = packageName;
                    if (status.visible) {
                        RestoreService.this.notifyListener(3, packageName, status.title, true);
                        return;
                    } else {
                        RestoreService.this.notifyListener(2, packageName, null, false);
                        return;
                    }
                }
                this.mInstallerRunningPackage = null;
                if (willRetry) {
                    RestoreService.this.notifyListener(2, packageName, null, false);
                } else if (!shouldHold(packageName)) {
                    RestoreService.this.notifyListener(1, packageName, null, false);
                }
            }
        }
    }

    public RestoreService() {
        this.mBitmapContainers = Maps.newHashMap();
    }

    static {
        DEBUG_SELF_ANDROID_ID = Boolean.valueOf(false);
    }

    public static void recoverRestore(Context context) {
        Context appContext = context.getApplicationContext();
        Intent intent = new Intent(appContext, RestoreService.class);
        intent.putExtra("startup", true);
        intent.setData(Uri.parse("restoreservice://startup"));
        appContext.startService(intent);
    }

    public static void restoreAccounts(Context context, String androidId, String singleAccount) {
        if (((Boolean) FinskyPreferences.directedRestoreStarted.get()).booleanValue()) {
            FinskyLog.d("Skipping restore for %s because directedRestoreStarted=true", FinskyLog.scrubPii(singleAccount));
            return;
        }
        Context appContext = context.getApplicationContext();
        appContext.startService(getRestoreIntent(androidId, singleAccount, appContext));
    }

    public static void restorePackages(Context context, boolean userDirected, String accountName, boolean visible, String[] packageNames, int[] versionCodes, String[] titles, int[] priorities, String[] deliveryTokens, String[] appIconUrls) {
        if (userDirected) {
            FinskyPreferences.directedRestoreStarted.put(Boolean.valueOf(true));
        }
        Context appContext = context.getApplicationContext();
        Intent intent = new Intent(appContext, RestoreService.class);
        intent.putExtra("authAccount", accountName);
        intent.putExtra("visible", visible);
        intent.putExtra("array_packages", packageNames);
        intent.putExtra("array_version_codes", versionCodes);
        intent.putExtra("array_titles", titles);
        intent.putExtra("array_priorities", priorities);
        if (deliveryTokens != null) {
            intent.putExtra("array_delivery_tokens", deliveryTokens);
        }
        intent.putExtra("array_app_icon_urls", appIconUrls);
        intent.setData(Uri.parse("restoreservice://restorepackages"));
        appContext.startService(intent);
    }

    private static Intent getRestoreIntent(String androidId, String singleAccount, Context appContext) {
        Intent intent = new Intent(appContext, RestoreService.class);
        intent.putExtra("aid", androidId);
        intent.putExtra("authAccount", singleAccount);
        Builder ub = new Builder();
        ub.scheme("restoreservice").appendPath("restoreaccount").appendPath(androidId);
        if (!TextUtils.isEmpty(singleAccount)) {
            ub.appendPath(singleAccount);
        }
        intent.setData(ub.build());
        return intent;
    }

    private static Intent getPackageRestoreIntent(Context context, String packageName) {
        Intent intent = new Intent(context, RestoreService.class);
        intent.putExtra("package", packageName);
        Builder ub = new Builder();
        ub.scheme("restoreservice").appendPath("restorepackage").appendPath(packageName);
        intent.setData(ub.build());
        return intent;
    }

    private static Intent getKickIntent(Context appContext) {
        Intent intent = new Intent(appContext, RestoreService.class);
        intent.putExtra("kick_installer", true);
        intent.setData(Uri.parse("restoreservice://kick"));
        return intent;
    }

    public void onCreate() {
        sInstance = this;
        this.mTracker = new RestoreTracker();
        this.mPackageInstaller = PackageInstallerFactory.getPackageInstaller();
        this.mAppIconSize = this.mPackageInstaller.getAppIconSize();
    }

    public void onDestroy() {
        if (this.mAddedInstallerListener) {
            FinskyApp.get().getInstaller().removeListener(this.mTracker);
        }
        this.mTracker = null;
        notifyListener(1, null, null, false);
        sInstance = null;
    }

    private void addInstallerListener() {
        if (!this.mAddedInstallerListener) {
            FinskyApp.get().getInstaller().addListener(this.mTracker);
            this.mAddedInstallerListener = true;
        }
    }

    private boolean inErrorRetryBlacklist(int statusCode) {
        int i;
        if (sErrorRetryBlacklist == null) {
            String[] codes = Utils.commaUnpackStrings((String) G.appRestoreHttpStatusBlacklist.get());
            sErrorRetryBlacklist = new int[codes.length];
            for (i = 0; i < codes.length; i++) {
                try {
                    sErrorRetryBlacklist[i] = Integer.valueOf(codes[i]).intValue();
                } catch (NumberFormatException e) {
                    sErrorRetryBlacklist[i] = Integer.MIN_VALUE;
                }
            }
        }
        for (int i2 : sErrorRetryBlacklist) {
            if (statusCode == i2) {
                return true;
            }
        }
        return false;
    }

    private long jitterDelay(long delay) {
        return (long) (((double) delay) * (0.75d + (Math.random() / 2.0d)));
    }

    public int onStartCommand(final Intent intent, int flags, int startId) {
        this.mServiceStartId = startId;
        this.mTracker.mStartupRefCount = this.mTracker.mStartupRefCount + 1;
        Runnable continueRunnable = new Runnable() {
            private int mLoaded;

            public void run() {
                this.mLoaded++;
                if (this.mLoaded == 4) {
                    RestoreService.this.mTracker.mStartupRefCount = RestoreService.this.mTracker.mStartupRefCount - 1;
                    boolean keepRunning = RestoreService.this.handleIntent(intent);
                    RestoreService.this.addInstallerListener();
                    if (!keepRunning) {
                        RestoreService.this.mTracker.stopServiceIfDone();
                    }
                }
            }
        };
        this.mTracker.initAccountStore(getApplicationContext(), continueRunnable);
        this.mTracker.initPackagesStore(getApplicationContext(), continueRunnable);
        FinskyApp.get().getLibraries().load(continueRunnable);
        FinskyApp.get().getAppStates().load(continueRunnable);
        return 3;
    }

    private boolean handleIntent(Intent intent) {
        if (intent.getBooleanExtra("startup", false)) {
            return handleStartupIntent();
        }
        if (intent.getBooleanExtra("kick_installer", false)) {
            FinskyApp.get().getInstaller().startDeferredInstalls();
            return false;
        }
        if (intent.hasExtra("package")) {
            return handleRetryPackageIntent(intent);
        }
        if (intent.hasExtra("array_packages")) {
            String accountName = intent.getStringExtra("authAccount");
            boolean visible = intent.getBooleanExtra("visible", false);
            String[] packageNames = intent.getStringArrayExtra("array_packages");
            int[] versionCodes = intent.getIntArrayExtra("array_version_codes");
            String[] titles = intent.getStringArrayExtra("array_titles");
            int[] priorities = intent.getIntArrayExtra("array_priorities");
            String[] deliveryTokens = intent.getStringArrayExtra("array_delivery_tokens");
            String[] appIconUrls = intent.getStringArrayExtra("array_app_icon_urls");
            int numRestored = 0;
            int i = 0;
            while (i < packageNames.length) {
                if (restorePackage(accountName, packageNames[i], versionCodes[i], titles[i], priorities[i], deliveryTokens != null ? deliveryTokens[i] : null, visible, appIconUrls[i])) {
                    numRestored++;
                }
                i++;
            }
            FinskyLog.d("Start restore of %d packages (%d skipped) for acct:%s", Integer.valueOf(packageNames.length), Integer.valueOf(packageNames.length - numRestored), FinskyLog.scrubPii(accountName));
            if (numRestored > 0) {
                if (((Boolean) FinskyPreferences.setupWizardStartDownloads.get()).booleanValue()) {
                    FinskyApp.get().getInstaller().startDeferredInstalls();
                } else {
                    setAlarm(getKickIntent(getApplicationContext()), ((Long) G.appRestoreFailsafeMs.get()).longValue());
                }
            }
            return false;
        }
        String aidString = intent.getStringExtra("aid");
        if (TextUtils.isEmpty(aidString)) {
            FinskyLog.e("Expecting a non-empty aid extra", new Object[0]);
            return false;
        }
        if (DEBUG_SELF_ANDROID_ID.booleanValue() && aidString.equals("self")) {
            aidString = Long.toHexString(((Long) DfeApiConfig.androidId.get()).longValue());
            FinskyLog.d("Using own current android-id %s for test restore", aidString);
        }
        try {
            Long.parseLong(aidString, 16);
            String specificAccount = intent.getStringExtra("authAccount");
            if (specificAccount != null) {
                if (AccountHandler.findAccount(specificAccount, FinskyApp.get()) == null) {
                    FinskyLog.e("Can't find restore acct:%s", FinskyLog.scrubPii(specificAccount));
                    return false;
                }
                restore(aidString, specificAccount);
            } else {
                Account[] accounts = AccountHandler.getAccounts(this);
                if (accounts.length <= 0) {
                    FinskyLog.e("RestoreService can't run - no accounts configured on device!", new Object[0]);
                    return false;
                }
                for (Account account : accounts) {
                    restore(aidString, account.name);
                }
            }
            return true;
        } catch (NumberFormatException e) {
            FinskyLog.e("Provided aid can't be parsed as long", new Object[0]);
            return false;
        }
    }

    private boolean handleStartupIntent() {
        if (this.mHandledStartupIntent) {
            FinskyLog.w("Redelivery of startup intent - dropping it", new Object[0]);
            return false;
        }
        this.mHandledStartupIntent = true;
        boolean keepServiceRunning = false;
        if (!this.mTracker.mAccountStatusMap.isEmpty()) {
            for (String accountName : this.mTracker.mAccountStatusMap.keySet()) {
                FinskyLog.d("Recover fetch for account %s", FinskyLog.scrubPii(accountName));
                restore(((AccountFetchStatus) this.mTracker.mAccountStatusMap.get(accountName)).androidId, accountName);
                keepServiceRunning = true;
            }
        }
        if (this.mTracker.mPackageStatusMap.isEmpty()) {
            return keepServiceRunning;
        }
        Installer installer = FinskyApp.get().getInstaller();
        for (String packageName : Lists.newArrayList(this.mTracker.mPackageStatusMap.keySet())) {
            if (installer.getState(packageName).isDownloadingOrInstalling()) {
                keepServiceRunning = true;
            } else {
                PackageInstallStatus status = (PackageInstallStatus) this.mTracker.mPackageStatusMap.get(packageName);
                if (status != null) {
                    if (status.retryTime != 0) {
                        if (System.currentTimeMillis() < status.retryTime + ((Long) G.appRestoreRetryDownloadHoldoffMs.get()).longValue()) {
                        }
                    }
                    FinskyLog.d("Overdue alarm for %s so retry immediately", packageName);
                    if (!doRetryPackage(packageName)) {
                        this.mTracker.mPackageStatusMap.remove(packageName);
                        this.mTracker.writePackageStatus(packageName);
                    }
                }
            }
        }
        return keepServiceRunning;
    }

    private boolean handleRetryPackageIntent(Intent intent) {
        String packageName = intent.getStringExtra("package");
        if (FinskyApp.get().getInstaller().getState(packageName).isDownloadingOrInstalling()) {
            return true;
        }
        return doRetryPackage(packageName);
    }

    private boolean doRetryPackage(String packageName) {
        Installer installer = FinskyApp.get().getInstaller();
        if (this.mTracker.tryAgainPackage(packageName)) {
            PackageInstallStatus status = (PackageInstallStatus) this.mTracker.mPackageStatusMap.get(packageName);
            if (shouldRestore(packageName, status.versionCode, status.accountName, installer)) {
                this.mTracker.startPackage(packageName, status.versionCode, status.accountName, status.title, status.priority, status.deliveryToken, status.visible, status.appIconUrl);
                installer.setVisibility(packageName, status.visible, false, false);
                if (!TextUtils.isEmpty(status.deliveryToken)) {
                    installer.setDeliveryToken(packageName, status.deliveryToken);
                }
                installer.requestInstall(packageName, status.versionCode, status.accountName, status.title, false, "restore", status.priority);
                if (!TextUtils.isEmpty(status.appIconUrl)) {
                    startBitmapDownload(packageName, status.appIconUrl);
                }
                return true;
            }
            this.mTracker.mPackageStatusMap.remove(packageName);
            this.mTracker.writePackageStatus(packageName);
            return false;
        }
        this.mTracker.mPackageStatusMap.remove(packageName);
        this.mTracker.writePackageStatus(packageName);
        return false;
    }

    private long setAlarm(Intent withIntent, long intervalMs) {
        if (TextUtils.isEmpty(withIntent.getDataString())) {
            throw new IllegalArgumentException("Alarm intent needs data URI");
        }
        Context context = getApplicationContext();
        long wakeTimeMs = System.currentTimeMillis() + intervalMs;
        ((AlarmManager) context.getSystemService("alarm")).set(0, wakeTimeMs, PendingIntent.getService(context, 0, withIntent, 0));
        return wakeTimeMs;
    }

    private void restore(String aid, String accountName) {
        if (this.mTracker.isAccountInFlight(accountName)) {
            FinskyLog.d("Skip restore acct:%s already started", FinskyLog.scrubPii(accountName));
            return;
        }
        try {
            final long finalAid = Long.parseLong(aid, 16);
            FinskyLog.d("Start restore aid:%s acct:%s", FinskyLog.scrubPii(aid), FinskyLog.scrubPii(accountName));
            this.mTracker.startAccount(accountName, aid);
            final String str = accountName;
            final String str2 = aid;
            ConsistencyTokenHelper.get(FinskyApp.get(), new ConsistencyTokenHelper.Listener() {
                public void onTokenReceived(String consistencyToken) {
                    FinskyApp.get().getDfeApi(str).getBackupDocumentChoices(finalAid, consistencyToken, new RestoreResponseListener(str), new ErrorListener() {
                        public void onErrorResponse(VolleyError error) {
                            RestoreService.this.mTracker.finishAccount(str, false, RestoreService.this.volleyErrorToInstallerError(error));
                            FinskyLog.w("Error while getting list of applications to restore from server: %s", error.getMessage());
                            if (RestoreService.this.mTracker.tryAgainOrDeleteAccount(str)) {
                                RestoreService.this.setAlarm(RestoreService.getRestoreIntent(str2, str, RestoreService.this), RestoreService.this.jitterDelay(((Long) G.appRestoreRetryFetchListHoldoffMs.get()).longValue()));
                            }
                        }
                    });
                }
            });
        } catch (NumberFormatException e) {
            FinskyLog.e("Provided aid can't be parsed as long: %s", FinskyLog.scrubPii(aid));
        }
    }

    private boolean restorePackage(String accountName, String packageName, int versionCode, String title, int priorityLevel, String deliveryToken, boolean visible, String appIconUrl) {
        Installer installer = FinskyApp.get().getInstaller();
        if (!shouldRestore(packageName, versionCode, accountName, installer)) {
            return false;
        }
        this.mTracker.startPackage(packageName, versionCode, accountName, title, priorityLevel, deliveryToken, visible, appIconUrl);
        installer.setVisibility(packageName, visible, false, false);
        if (!TextUtils.isEmpty(deliveryToken)) {
            installer.setDeliveryToken(packageName, deliveryToken);
        }
        installer.requestInstall(packageName, versionCode, accountName, title, true, "restore", priorityLevel);
        if (!TextUtils.isEmpty(appIconUrl)) {
            startBitmapDownload(packageName, appIconUrl);
        }
        return true;
    }

    private int volleyErrorToInstallerError(VolleyError volleyError) {
        if (volleyError instanceof AuthFailureError) {
            return 920;
        }
        if (volleyError instanceof DisplayMessageError) {
            return 921;
        }
        if (volleyError instanceof DfeServerError) {
            return 922;
        }
        if (volleyError instanceof NetworkError) {
            return 923;
        }
        if (volleyError instanceof NoConnectionError) {
            return 924;
        }
        if (volleyError instanceof ParseError) {
            return 925;
        }
        if (volleyError instanceof ServerError) {
            return 926;
        }
        if (volleyError instanceof TimeoutError) {
            return 927;
        }
        return 928;
    }

    private boolean shouldRestore(String packageName, int packageVersion, String accountName, Installer installer) {
        AppData appData = new AppData();
        appData.version = packageVersion;
        appData.hasVersion = true;
        if (this.mTracker.mPackageStatusMap.get(packageName) == null || this.mTracker.tryAgainPackage(packageName)) {
            PackageInstallStatus packageStatus = (PackageInstallStatus) this.mTracker.mPackageStatusMap.get(packageName);
            if (packageStatus != null && !accountName.equals(packageStatus.accountName)) {
                this.mDebugCountAlreadyOtherAccount++;
                FinskyApp.get().getEventLogger().logBackgroundEvent(113, packageName, "other-account", 0, null, appData);
                FinskyLog.d("Skipping restore of %s v:%d because already restoring for another account", packageName, Integer.valueOf(packageVersion));
                return false;
            } else if (installer.getState(packageName).isDownloadingOrInstalling()) {
                this.mDebugCountAlreadyTracked++;
                FinskyApp.get().getEventLogger().logBackgroundEvent(113, packageName, "is-tracked", 0, null, appData);
                FinskyLog.d("Skipping restore of %s because already restoring", packageName);
                return false;
            } else {
                PackageState packageState = FinskyApp.get().getPackageInfoRepository().get(packageName);
                if (packageState == null || packageState.installedVersion < packageVersion) {
                    FinskyLog.d("Should attempt restore of %s", packageName);
                    return true;
                }
                this.mDebugCountAlreadyInstalled++;
                appData.oldVersion = packageState.installedVersion;
                appData.hasOldVersion = true;
                FinskyApp.get().getEventLogger().logBackgroundEvent(113, packageName, "already-installed", 0, null, appData);
                FinskyLog.d("Skipping restore of %s v:%d because v:%d is installed", packageName, Integer.valueOf(packageVersion), Integer.valueOf(packageState.installedVersion));
                return false;
            }
        }
        this.mDebugCountMaxAttemptsExceeded++;
        FinskyApp.get().getEventLogger().logBackgroundEvent(113, packageName, "retry-expired", 0, null, appData);
        return false;
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    private void startBitmapDownload(final String packageName, String bitmapUrl) {
        if (this.mAppIconSize < 0 || TextUtils.isEmpty(bitmapUrl)) {
            return;
        }
        if (this.mBitmapContainers.containsKey(packageName)) {
            FinskyLog.w("Request for already-downloading bitmap for %s", packageName);
            return;
        }
        this.mTracker.startBitmap(packageName, bitmapUrl);
        BitmapContainer container = FinskyApp.get().getBitmapLoader().get(bitmapUrl, this.mAppIconSize, this.mAppIconSize, false, new BitmapLoadedHandler() {
            public void onResponse(BitmapContainer container) {
                Bitmap bitmap = container.getBitmap();
                if (bitmap != null) {
                    FinskyLog.d("Received appIcon for %s", packageName);
                    RestoreService.this.mBitmapContainers.remove(packageName);
                    RestoreService.this.deliverBitmap(packageName, bitmap);
                    return;
                }
                FinskyLog.w("Unable to download appIcon for %s", packageName);
                if (RestoreService.this.mTracker.tryAgainBitmap(packageName)) {
                    FinskyLog.w("Unable to download appIcon for %s", packageName);
                    RestoreService.this.cancelBitmapDownload(packageName);
                    return;
                }
                RestoreService.this.cancelBitmapDownload(packageName);
            }
        });
        Bitmap bitmap = container.getBitmap();
        if (bitmap != null) {
            FinskyLog.d("Received cached bitmap for %s", packageName);
            deliverBitmap(packageName, bitmap);
            return;
        }
        FinskyLog.d("Waiting for bitmap for %s", packageName);
        this.mBitmapContainers.put(packageName, container);
    }

    private void cancelBitmapDownload(String packageName) {
        FinskyLog.d("Canceling bitmap for %s", packageName);
        BitmapContainer container = (BitmapContainer) this.mBitmapContainers.remove(packageName);
        if (container != null) {
            container.cancelRequest();
        }
        this.mTracker.finishBitmap(packageName);
    }

    private void deliverBitmap(String packageName, Bitmap bitmap) {
        this.mPackageInstaller.setAppIcon(packageName, bitmap);
        this.mTracker.finishBitmap(packageName);
    }

    public static boolean shouldHold() {
        return sInstance != null && sInstance.mTracker.shouldHold(null);
    }

    public static boolean registerListener(SetupHoldListener listener) {
        if (listener == null) {
            if (sInstance == null) {
                return true;
            }
            sInstance.mListener = null;
            return true;
        } else if (sInstance == null || !sInstance.mTracker.shouldHold(null)) {
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
                if (RestoreService.this.mTracker.shouldHold(null)) {
                    if (RestoreService.this.mTracker.mInstallerRunningPackage != null) {
                        String packageName = RestoreService.this.mTracker.mInstallerRunningPackage;
                        PackageInstallStatus status = (PackageInstallStatus) RestoreService.this.mTracker.mPackageStatusMap.get(packageName);
                        if (status != null && status.visible) {
                            RestoreService.this.notifyListener(3, packageName, status.title, true);
                            return;
                        }
                    }
                    RestoreService.this.notifyListener(2, null, null, false);
                    return;
                }
                RestoreService.this.notifyListener(1, null, null, false);
            }
        });
    }

    private void notifyListener(int eventCode, String packageName, String title, boolean cancelable) {
        if (this.mListener != null) {
            this.mListener.onStatusChange(eventCode, packageName, title, cancelable, "RestoreService");
            if (eventCode == 1) {
                this.mListener = null;
            }
        }
    }
}
