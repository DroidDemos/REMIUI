package com.google.android.finsky.appstate;

import android.accounts.Account;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.activities.AppActionAnalyzer;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.analytics.PlayStore.AppData;
import com.google.android.finsky.analytics.PlayStore.NlpRepairStatus;
import com.google.android.finsky.api.AccountHandler;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.appstate.InstallerDataStore.AutoUpdateState;
import com.google.android.finsky.appstate.InstallerDataStore.InstallerData;
import com.google.android.finsky.appstate.PackageStateRepository.PackageState;
import com.google.android.finsky.config.G;
import com.google.android.finsky.download.Download;
import com.google.android.finsky.download.DownloadImpl;
import com.google.android.finsky.download.DownloadProgress;
import com.google.android.finsky.download.DownloadQueue;
import com.google.android.finsky.download.DownloadQueueListener;
import com.google.android.finsky.library.Libraries;
import com.google.android.finsky.library.Library;
import com.google.android.finsky.protos.DocDetails.AppDetails;
import com.google.android.finsky.receivers.Installer;
import com.google.android.finsky.receivers.PackageMonitorReceiver.PackageStatusListener;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.FinskyPreferences;
import com.google.android.finsky.utils.LibraryUtils;
import com.google.android.finsky.utils.PackageManagerHelper;
import com.google.android.finsky.utils.PackageManagerHelper.InstallPackageListener;
import com.google.android.finsky.utils.PackageManagerUtils;
import com.google.android.finsky.utils.PackageManagerUtils.PackageInstallObserver;
import com.google.android.finsky.utils.PurchaseInitiator;
import com.google.android.finsky.utils.Sha1Util;
import java.io.File;
import java.util.List;
import java.util.Locale;

public class GmsCoreHelper {
    private static final String NLP_PACKAGE_NAME;
    private static final String NLP_SHARED_USER_ID;
    private static Download sNlpDownload;
    private static AppData sNlpLogAppData;
    private final AppStates mAppStates;
    private final Context mContext;
    private final Libraries mLibraries;

    public static class GMSCoreNotifier implements PackageStatusListener {
        private final Context mContext;

        public GMSCoreNotifier(Context context) {
            this.mContext = context;
        }

        public void onPackageAdded(String packageName) {
            boolean isUpdatedSystem = true;
            if (GmsCoreHelper.isGmsCore(packageName)) {
                Intent gmsInstalledIntent = new Intent("com.google.android.gms.GMS_UPDATED");
                gmsInstalledIntent.setPackage("com.google.android.gms");
                this.mContext.sendBroadcast(gmsInstalledIntent);
                try {
                    boolean isSystem;
                    int flags = this.mContext.getPackageManager().getApplicationInfo(packageName, 0).flags;
                    if ((flags & 1) != 0) {
                        isSystem = true;
                    } else {
                        isSystem = false;
                    }
                    if ((flags & 128) == 0) {
                        isUpdatedSystem = false;
                    }
                    if (!isSystem || isUpdatedSystem) {
                        setAutoUpdate(packageName, AutoUpdateState.USE_GLOBAL, "install/update");
                    } else {
                        setAutoUpdate(packageName, AutoUpdateState.DISABLED, "downgrade");
                    }
                } catch (NameNotFoundException e) {
                    FinskyLog.w("NameNotFoundException getting info for %s", packageName);
                    return;
                }
            }
            reconnectNlp(this.mContext, packageName);
        }

        public void onPackageRemoved(String packageName, boolean replacing) {
            if (!replacing && GmsCoreHelper.isGmsCore(packageName)) {
                setAutoUpdate(packageName, AutoUpdateState.DISABLED, "removed");
            }
        }

        public void onPackageChanged(String packageName) {
            reconnectNlp(this.mContext, packageName);
        }

        public void onPackageAvailabilityChanged(String[] packageNames, boolean available) {
        }

        public void onPackageFirstLaunch(String packageName) {
        }

        private void setAutoUpdate(String packageName, AutoUpdateState newState, String reason) {
            final AppStates appStates = FinskyApp.get().getAppStates();
            final String str = packageName;
            final AutoUpdateState autoUpdateState = newState;
            final String str2 = reason;
            appStates.load(new Runnable() {
                public void run() {
                    FinskyLog.d("Set autoupdate of %s to %s (%s)", str, autoUpdateState, str2);
                    appStates.getInstallerDataStore().setAutoUpdate(str, autoUpdateState);
                }
            });
        }

        private static void reconnectNlp(Context context, String packageName) {
            if (VERSION.SDK_INT >= ((Integer) G.nlpReinstallSdkVersionMin.get()).intValue() && VERSION.SDK_INT <= ((Integer) G.nlpReinstallSdkVersionMax.get()).intValue() && !GmsCoreHelper.NLP_PACKAGE_NAME.equals(packageName) && !TextUtils.isEmpty(GmsCoreHelper.NLP_SHARED_USER_ID)) {
                try {
                    if (GmsCoreHelper.NLP_SHARED_USER_ID.equals(context.getPackageManager().getPackageInfo(packageName, 0).sharedUserId)) {
                        FinskyLog.d("Found shared UID match between NLP and %s", packageName);
                        try {
                            PackageManagerUtils.installPackage(FinskyApp.get(), Uri.fromFile(new File(context.getPackageManager().getApplicationInfo(GmsCoreHelper.NLP_PACKAGE_NAME, 0).sourceDir)), new PackageInstallObserver() {
                                public void packageInstalled(String observedPackage, int returnCode) {
                                    if (observedPackage == null) {
                                        observedPackage = GmsCoreHelper.NLP_PACKAGE_NAME;
                                    }
                                    FinskyLog.d("Result %d re-installing %s", Integer.valueOf(returnCode), observedPackage);
                                }
                            }, 2);
                        } catch (NameNotFoundException e) {
                            FinskyLog.w("NameNotFoundException getting info for " + GmsCoreHelper.NLP_PACKAGE_NAME, new Object[0]);
                        }
                    }
                } catch (NameNotFoundException e2) {
                    FinskyLog.w("NameNotFoundException getting info for %s", packageName);
                }
            }
        }
    }

    static {
        NLP_SHARED_USER_ID = (String) G.nlpSharedUserId.get();
        NLP_PACKAGE_NAME = (String) G.nlpPackageName.get();
        sNlpLogAppData = null;
        sNlpDownload = null;
    }

    public GmsCoreHelper(Libraries libraries, AppStates appStates, Context context) {
        this.mLibraries = libraries;
        this.mAppStates = appStates;
        this.mContext = context;
    }

    public static void insertGmsCore(List<String> docIds) {
        if (!isAutoUpdateEnabled()) {
            FinskyLog.d("GMS Core auto updating is disabled", new Object[0]);
        } else if (!docIds.contains("com.google.android.gms")) {
            docIds.add("com.google.android.gms");
        }
    }

    public static void removeGmsCore(List<String> docIds) {
        docIds.remove("com.google.android.gms");
    }

    public static boolean isGmsCore(Document document) {
        AppDetails appDetails = document.getAppDetails();
        if (appDetails == null) {
            return false;
        }
        return isGmsCore(appDetails.packageName);
    }

    public static boolean isGmsCore(String packageName) {
        return "com.google.android.gms".equals(packageName);
    }

    private static boolean isAutoUpdateEnabled() {
        return ((Boolean) G.gmsCoreAutoUpdateEnabled.get()).booleanValue();
    }

    public void updateGmsCore(Document document) {
        if (!isAutoUpdateEnabled()) {
            FinskyLog.d("GMS Core auto update is disabled", new Object[0]);
        } else if (LibraryUtils.isAvailable(document, null, this.mLibraries)) {
            AppDetails appDetails = document.getAppDetails();
            if (appDetails == null) {
                FinskyLog.wtf("Unable to install something without app details", new Object[0]);
                return;
            }
            int installedVersion = -1;
            boolean systemAppWithoutUpdate = false;
            PackageState packageState = FinskyApp.get().getPackageInfoRepository().get("com.google.android.gms");
            if (packageState != null) {
                if (packageState.isDisabledByUser) {
                    FinskyLog.d("Not updating com.google.android.gms (disabled)", new Object[0]);
                    return;
                }
                installedVersion = packageState.installedVersion;
                if (!packageState.isSystemApp || packageState.isUpdatedSystemApp) {
                    systemAppWithoutUpdate = false;
                } else {
                    systemAppWithoutUpdate = true;
                }
            }
            if (installedVersion == -1 || systemAppWithoutUpdate) {
                InstallerData installerData = FinskyApp.get().getInstallerDataStore().get("com.google.android.gms");
                if (installerData != null && installerData.getAutoUpdate() == AutoUpdateState.DISABLED) {
                    FinskyLog.d("Not updating com.google.android.gms (was removed)", new Object[0]);
                    return;
                }
            }
            if (installedVersion < appDetails.versionCode) {
                installGmsCore(document);
            }
        } else {
            FinskyLog.d("GMS Core is not available", new Object[0]);
        }
    }

    private void installGmsCore(Document doc) {
        Installer installer = FinskyApp.get().getInstaller();
        installer.setMobileDataAllowed("com.google.android.gms");
        installer.setVisibility("com.google.android.gms", false, false, false);
        String updateAccountName = AppActionAnalyzer.getAppDetailsAccount("com.google.android.gms", FinskyApp.get().getCurrentAccountName(), this.mAppStates, this.mLibraries);
        Account updateAccount = AccountHandler.findAccount(updateAccountName, this.mContext);
        if (updateAccount == null) {
            FinskyLog.d("Cannot update com.google.android.gms because cannot determine update account " + FinskyLog.scrubPii(updateAccountName), new Object[0]);
            return;
        }
        Library updateAccountLibrary = this.mLibraries.getAccountLibrary(updateAccount);
        if (updateAccountLibrary == null) {
            FinskyLog.d("Cannot update com.google.android.gms because cannot find library for %s.", FinskyLog.scrubPii(updateAccountName));
        } else if (LibraryUtils.isOwned(doc, updateAccountLibrary)) {
            PurchaseInitiator.initiateDownload(updateAccount, doc);
        } else {
            PurchaseInitiator.makeFreePurchase(updateAccount, doc, 1, null, null, true, false);
        }
    }

    public static void cleanupNlp(FinskyApp finskyApp) {
        int newConfigurationId = ((Integer) G.nlpCleanupConfigurationId.get()).intValue();
        if (newConfigurationId != ((Integer) FinskyPreferences.nlpCleanupConfigurationId.get()).intValue()) {
            FinskyLog.d("Resetting state because new config id %d", Integer.valueOf(newConfigurationId));
            FinskyPreferences.nlpCleanupHoldoffUntilBoot.remove();
            FinskyPreferences.nlpCleanupHoldoffAfterInstall.remove();
            FinskyPreferences.nlpCleanupConfigurationId.put(Integer.valueOf(newConfigurationId));
        }
        NlpRepairStatus report = new NlpRepairStatus();
        FinskyLog.d("result=%b type=%d", Boolean.valueOf(checkForNlpDamage(finskyApp, report)), Integer.valueOf(report.repairStatus));
        report.holdoffUntilBoot = ((Boolean) FinskyPreferences.nlpCleanupHoldoffUntilBoot.get()).booleanValue();
        report.hasHoldoffUntilBoot = true;
        report.holdoffAfterInstall = ((Boolean) FinskyPreferences.nlpCleanupHoldoffAfterInstall.get()).booleanValue();
        report.hasHoldoffAfterInstall = true;
        if (checkForNlpDamage(finskyApp, report)) {
            if (((Boolean) FinskyPreferences.nlpCleanupHoldoffUntilBoot.get()).booleanValue() || ((Boolean) FinskyPreferences.nlpCleanupHoldoffAfterInstall.get()).booleanValue()) {
                FinskyLog.d("Skip repair because holdoff set", new Object[0]);
                report.repairStatus = 3;
                report.hasRepairStatus = true;
            } else {
                downloadAndInstallNlpCleanup(finskyApp, report);
            }
        }
        if (((Boolean) G.nlpCleanupLogCommonStatuses.get()).booleanValue() || !isCommonNlpRepairStatus(report.repairStatus)) {
            FinskyApp.get().getEventLogger().logNlpCleanupData(report);
        }
    }

    private static boolean isCommonNlpRepairStatus(int status) {
        return status == 1 || status == 4;
    }

    public static void onBootCompleted() {
        FinskyPreferences.nlpCleanupHoldoffUntilBoot.remove();
    }

    private static boolean checkForNlpDamage(FinskyApp finskyApp, NlpRepairStatus report) {
        if (VERSION.SDK_INT < ((Integer) G.nlpCleanupSdkVersionMin.get()).intValue()) {
            report.repairStatus = 4;
            report.hasRepairStatus = true;
            return false;
        }
        if (VERSION.SDK_INT > ((Integer) G.nlpCleanupSdkVersionMax.get()).intValue()) {
            report.repairStatus = 4;
            report.hasRepairStatus = true;
            return false;
        }
        for (String providerName : ((LocationManager) finskyApp.getSystemService("location")).getAllProviders()) {
            if ("network".equals(providerName)) {
                report.repairStatus = 1;
                report.hasRepairStatus = true;
                return false;
            }
        }
        try {
            String packageName = (String) G.nlpPackageName.get();
            PackageManager packageManager = finskyApp.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(packageName, 8256);
            int packageEnabled = packageManager.getApplicationEnabledSetting(packageName);
            int flags = packageInfo.applicationInfo.flags;
            report.flags = flags;
            report.hasFlags = true;
            int versionCode = packageInfo.versionCode;
            report.versionCode = versionCode;
            report.hasVersionCode = true;
            report.enabled = packageEnabled;
            report.hasEnabled = true;
            if ((flags & ((Integer) G.nlpCleanupFlagsMask.get()).intValue()) != ((Integer) G.nlpCleanupFlagsSet.get()).intValue()) {
                FinskyLog.d("NLP incorrect flags %d", Integer.valueOf(flags));
                report.repairStatus = 6;
                report.hasRepairStatus = true;
                return false;
            } else if (versionCode < ((Integer) G.nlpCleanupNlpVersionMin.get()).intValue()) {
                FinskyLog.d("NLP version %d too low", Integer.valueOf(versionCode));
                report.repairStatus = 7;
                report.hasRepairStatus = true;
                return false;
            } else if (versionCode > ((Integer) G.nlpCleanupNlpVersionMax.get()).intValue()) {
                FinskyLog.d("NLP version %d too high", Integer.valueOf(versionCode));
                report.repairStatus = 7;
                report.hasRepairStatus = true;
                return false;
            } else {
                String expected_release = (String) G.nlpCleanupExpectedSignature.get();
                String expected_testKeys = (String) G.nlpCleanupExpectedSignatureTestKeys.get();
                String hash = Sha1Util.secureHash(packageInfo.signatures[0].toByteArray());
                if (!expected_release.equals(hash)) {
                    if (expected_testKeys.equals(hash)) {
                        report.foundTestKeys = true;
                        report.hasFoundTestKeys = true;
                    } else {
                        FinskyLog.d("NLP signature hash mismatch %s", hash);
                        report.signatureHash = hash;
                        report.hasSignatureHash = hash != null;
                        report.repairStatus = 8;
                        report.hasRepairStatus = true;
                        return false;
                    }
                }
                FinskyLog.d("NLP package found but reported inactive", new Object[0]);
                report.repairStatus = 2;
                report.hasRepairStatus = true;
                return true;
            }
        } catch (NameNotFoundException e) {
            FinskyLog.d("NLP package not found", new Object[0]);
            report.repairStatus = 5;
            report.hasRepairStatus = true;
            return false;
        }
    }

    private static void downloadAndInstallNlpCleanup(FinskyApp finskyApp, NlpRepairStatus report) {
        String url;
        String cookieName;
        String cookieValue;
        sNlpLogAppData = new AppData();
        sNlpLogAppData.oldVersion = report.versionCode;
        sNlpLogAppData.hasOldVersion = true;
        sNlpLogAppData.systemApp = true;
        sNlpLogAppData.hasSystemApp = true;
        sNlpLogAppData.version = report.versionCode;
        sNlpLogAppData.hasVersion = true;
        if (report.foundTestKeys) {
            url = (String) G.nlpCleanupUrlTestKeys.get();
            cookieName = (String) G.nlpCleanupCookieNameTestKeys.get();
            cookieValue = (String) G.nlpCleanupCookieValueTestKeys.get();
        } else {
            url = (String) G.nlpCleanupUrl.get();
            cookieName = (String) G.nlpCleanupCookieName.get();
            cookieValue = (String) G.nlpCleanupCookieValue.get();
        }
        url = String.format(Locale.US, url, new Object[]{Integer.valueOf(report.versionCode)});
        if (TextUtils.isEmpty(cookieName) || TextUtils.isEmpty(cookieValue)) {
            cookieName = null;
            cookieValue = null;
        }
        sNlpDownload = new DownloadImpl(url, "", null, cookieName, cookieValue, null, -1, -1, null, false, true);
        DownloadQueue downloadQueue = finskyApp.getDownloadQueue();
        downloadQueue.addListener(getListener(finskyApp.getEventLogger(), (String) G.nlpPackageName.get()));
        downloadQueue.add(sNlpDownload);
        finskyApp.getEventLogger().logBackgroundEvent(100, (String) G.nlpPackageName.get(), null, 0, null, sNlpLogAppData);
    }

    private static DownloadQueueListener getListener(final FinskyEventLog eventLogger, final String packageName) {
        return new DownloadQueueListener() {
            public void onStart(Download download) {
                if (download == GmsCoreHelper.sNlpDownload) {
                    eventLogger.logBackgroundEvent(101, packageName, null, 0, null, GmsCoreHelper.sNlpLogAppData);
                    FinskyLog.d("NLP fixer download started", new Object[0]);
                }
            }

            public void onComplete(Download download) {
                if (download == GmsCoreHelper.sNlpDownload) {
                    eventLogger.logBackgroundEvent(102, packageName, null, 0, null, GmsCoreHelper.sNlpLogAppData);
                    FinskyLog.d("NLP fixer download completed", new Object[0]);
                    GmsCoreHelper.installNlpCleanup(download, packageName);
                }
            }

            public void onError(Download download, int httpStatus) {
                if (download == GmsCoreHelper.sNlpDownload) {
                    eventLogger.logBackgroundEvent(104, packageName, null, httpStatus, null, GmsCoreHelper.sNlpLogAppData);
                    FinskyLog.e("NLP fixer download failed with HTTP status: %d", Integer.valueOf(httpStatus));
                }
            }

            public void onNotificationClicked(Download download) {
            }

            public void onCancel(Download download) {
            }

            public void onProgress(Download download, DownloadProgress progress) {
            }
        };
    }

    private static void installNlpCleanup(Download download, final String packageName) {
        InstallPackageListener listener = new InstallPackageListener() {
            public void installSucceeded() {
                FinskyLog.d("Installed Nlp Fixer", new Object[0]);
                FinskyPreferences.nlpCleanupHoldoffAfterInstall.put(Boolean.valueOf(true));
                FinskyApp.get().getEventLogger().logBackgroundEvent(110, packageName, null, 0, null, GmsCoreHelper.sNlpLogAppData);
            }

            public void installFailed(int errorCode, String exceptionType) {
                FinskyLog.d("Error installing Nlp fixer %d %s", Integer.valueOf(errorCode), exceptionType);
                FinskyPreferences.nlpCleanupHoldoffUntilBoot.put(Boolean.valueOf(true));
                FinskyApp.get().getEventLogger().logBackgroundEvent(111, packageName, null, errorCode, exceptionType, GmsCoreHelper.sNlpLogAppData);
            }
        };
        boolean allowDowngrade = ((Boolean) G.nlpCleanupDowngradeFlag.get()).booleanValue();
        FinskyApp.get().getEventLogger().logBackgroundEvent(106, packageName, null, 0, null, sNlpLogAppData);
        PackageManagerHelper.installPackageWithDowngrade(download.getContentUri(), listener, true, packageName, allowDowngrade);
    }
}
