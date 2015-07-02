package com.google.android.finsky.installer;

import android.app.ActivityManager;
import android.app.ActivityManager.RecentTaskInfo;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build.VERSION;
import android.os.PowerManager;
import android.os.StatFs;
import android.provider.Settings.Global;
import android.provider.Settings.Secure;
import android.provider.Settings.SettingNotFoundException;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.analytics.PlayStore.AppData;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.appstate.AppStates;
import com.google.android.finsky.appstate.AppStates.AppState;
import com.google.android.finsky.appstate.InstallerDataStore.AutoUpdateState;
import com.google.android.finsky.appstate.InstallerDataStore.InstallerData;
import com.google.android.finsky.appstate.PackageStateRepository.PackageState;
import com.google.android.finsky.config.G;
import com.google.android.finsky.layout.AppPermissionAdapter;
import com.google.android.finsky.library.Libraries;
import com.google.android.finsky.protos.DocDetails.AppDetails;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.LibraryUtils;
import com.google.android.finsky.utils.Lists;
import com.google.android.finsky.utils.PermissionsBucketer;
import com.google.android.finsky.utils.PermissionsBucketer.PermissionData;
import com.google.android.finsky.utils.Sets;
import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class InstallPolicies {
    private static final List<String> DEBUG_FORCE_LARGE_SIZE;
    private static final List<String> DEBUG_FORCE_PERMISSIONS;
    private static final boolean SUPPORTS_MOBILE_HOTSPOT;
    private final AppStates mAppStates;
    private final ConnectivityManager mConnectivityManager;
    private final Libraries mLibraries;
    private long mMaxBytesOverMobile;
    private long mMaxBytesOverMobileRecommended;
    private final PackageManager mPackageManager;

    public static class InstallWarnings {
        public boolean autoUpdateDisabled;
        public boolean largeDownload;
        public boolean newPermissions;

        public InstallWarnings() {
            this.largeDownload = false;
            this.newPermissions = false;
            this.autoUpdateDisabled = false;
        }

        public boolean warningRequired() {
            return this.autoUpdateDisabled || this.largeDownload || this.newPermissions;
        }
    }

    static {
        boolean z;
        if (VERSION.SDK_INT >= 16) {
            z = true;
        } else {
            z = false;
        }
        SUPPORTS_MOBILE_HOTSPOT = z;
        DEBUG_FORCE_LARGE_SIZE = Lists.newArrayList();
        DEBUG_FORCE_PERMISSIONS = Lists.newArrayList();
    }

    public InstallPolicies(ContentResolver resolver, PackageManager packageManager, AppStates appStates, Libraries libraries) {
        setMobileDownloadThresholds(resolver);
        this.mPackageManager = packageManager;
        this.mConnectivityManager = (ConnectivityManager) FinskyApp.get().getSystemService("connectivity");
        this.mAppStates = appStates;
        this.mLibraries = libraries;
    }

    public boolean isMobileNetwork() {
        NetworkInfo info = this.mConnectivityManager.getNetworkInfo(1);
        if (info == null || !info.isConnected()) {
            return true;
        }
        return false;
    }

    public boolean isWifiNetwork() {
        NetworkInfo wifiInfo = this.mConnectivityManager.getNetworkInfo(1);
        if (wifiInfo == null || !wifiInfo.isConnected()) {
            return false;
        }
        return true;
    }

    public boolean isMobileHotspot() {
        if (SUPPORTS_MOBILE_HOTSPOT && isWifiNetwork() && this.mConnectivityManager.isActiveNetworkMetered()) {
            return true;
        }
        return false;
    }

    public boolean hasMobileNetwork() {
        if (this.mConnectivityManager.getNetworkInfo(0) != null) {
            return true;
        }
        return false;
    }

    public boolean hasNetwork() {
        NetworkInfo info = this.mConnectivityManager.getActiveNetworkInfo();
        return info != null && info.isConnected();
    }

    public long getMaxBytesOverMobileRecommended() {
        return this.mMaxBytesOverMobileRecommended;
    }

    public long getMaxBytesOverMobile() {
        return this.mMaxBytesOverMobile;
    }

    private void setMobileDownloadThresholds(ContentResolver resolver) {
        long newLimit;
        this.mMaxBytesOverMobile = ((Long) G.downloadBytesOverMobileMaximum.get()).longValue();
        this.mMaxBytesOverMobileRecommended = ((Long) G.downloadBytesOverMobileRecommended.get()).longValue();
        try {
            newLimit = Secure.getLong(resolver, "download_manager_max_bytes_over_mobile");
            if (newLimit > 0 && newLimit < this.mMaxBytesOverMobile) {
                this.mMaxBytesOverMobile = newLimit;
            }
        } catch (SettingNotFoundException e) {
        }
        try {
            newLimit = Secure.getLong(resolver, "download_manager_recommended_max_bytes_over_mobile");
            if (newLimit > 0 && newLimit < this.mMaxBytesOverMobileRecommended) {
                this.mMaxBytesOverMobileRecommended = newLimit;
            }
        } catch (SettingNotFoundException e2) {
        }
        this.mMaxBytesOverMobileRecommended = Math.min(this.mMaxBytesOverMobileRecommended, this.mMaxBytesOverMobile);
    }

    private long getStorageLowBytes(long partitionTotalBytes, ContentResolver resolver) {
        long finskyMinFreeBytes = ((Long) G.downloadFreeSpaceThresholdBytes.get()).longValue();
        if (finskyMinFreeBytes > 0) {
            return finskyMinFreeBytes;
        }
        int minFreePercent;
        long minFreeBytes;
        if (VERSION.SDK_INT >= 17) {
            minFreePercent = Global.getInt(resolver, "sys_storage_threshold_percentage", 10);
            minFreeBytes = Global.getLong(resolver, "sys_storage_threshold_max_bytes", 524288000);
        } else {
            minFreePercent = Secure.getInt(resolver, "sys_storage_threshold_percentage", 10);
            minFreeBytes = Secure.getLong(resolver, "sys_storage_threshold_max_bytes", 524288000);
        }
        return Math.min(minFreeBytes, (((long) minFreePercent) * partitionTotalBytes) / 100);
    }

    public boolean isFreeSpaceSufficient(long apkSize, File path, ContentResolver resolver) {
        long partitionAvailableBytes;
        long partitionTotalBytes;
        StatFs partitionStats = new StatFs(path.getAbsolutePath());
        if (VERSION.SDK_INT >= 18) {
            partitionAvailableBytes = partitionStats.getAvailableBytes();
            partitionTotalBytes = partitionStats.getTotalBytes();
        } else {
            long blockSize = (long) partitionStats.getBlockSize();
            partitionAvailableBytes = blockSize * ((long) partitionStats.getAvailableBlocks());
            partitionTotalBytes = blockSize * ((long) partitionStats.getBlockCount());
        }
        if (partitionAvailableBytes - ((((long) ((Integer) G.downloadFreeSpaceApkSizeFactor.get()).intValue()) * apkSize) / 100) >= getStorageLowBytes(partitionTotalBytes, resolver)) {
            return true;
        }
        return false;
    }

    public List<Document> getApplicationsWithUpdates(List<Document> docs) {
        List<Document> outdated = Lists.newArrayList();
        for (Document doc : docs) {
            if (canUpdateApp(this.mAppStates.getPackageStateRepository().get(doc.getAppDetails().packageName), doc)) {
                outdated.add(doc);
            }
        }
        return outdated;
    }

    public boolean canUpdateApp(PackageState packageState, Document app) {
        if (packageState == null) {
            return false;
        }
        if (this.mLibraries.isLoaded()) {
            int installedVersion = packageState.installedVersion;
            int serverAssetVersion = app.getAppDetails().versionCode;
            if (packageState.isDisabled) {
                return false;
            }
            String packageName = packageState.packageName;
            if (serverAssetVersion <= installedVersion) {
                return false;
            }
            if (LibraryUtils.isAvailable(app, null, this.mLibraries)) {
                return true;
            }
            FinskyLog.d("Cannot update unavailable app: pkg=%s,restriction=%d", packageName, Integer.valueOf(app.getAvailabilityRestriction()));
            return false;
        }
        FinskyLog.wtf("Library not loaded.", new Object[0]);
        return false;
    }

    public List<Document> getApplicationsEligibleForAutoUpdate(List<Document> docsFromServer) {
        if (this.mLibraries.isLoaded()) {
            long autoUpdateSizeLimit = getAutoUpdateSizeLimit();
            Set<String> foregroundPackages = getForegroundPackages(FinskyApp.get());
            List<Document> docsToUpdate = Lists.newArrayList();
            for (Document doc : docsFromServer) {
                AppDetails appDetails = doc.getAppDetails();
                String packageName = appDetails.packageName;
                AppState appState = this.mAppStates.getApp(packageName);
                if (appState == null || appState.packageManagerState == null) {
                    FinskyLog.w("Server thinks we have an asset that we don't have : %s", packageName);
                } else if (appDetails.versionCode > appState.packageManagerState.installedVersion && !getInstallWarningsForDocument(autoUpdateSizeLimit, packageName, appDetails, true).warningRequired()) {
                    if (((Boolean) G.autoUpdateExcludeRunningPackagesPre.get()).booleanValue() && foregroundPackages.contains(packageName)) {
                        FinskyLog.w("Exclude auto-update for foreground package: %s", packageName);
                        AppData appData = new AppData();
                        appData.version = appDetails.versionCode;
                        appData.hasVersion = true;
                        appData.oldVersion = appState.packageManagerState.installedVersion;
                        appData.hasOldVersion = true;
                        appData.systemApp = appState.packageManagerState.isSystemApp;
                        appData.hasSystemApp = true;
                        FinskyApp.get().getEventLogger().logBackgroundEvent(121, packageName, null, 0, null, appData);
                    } else {
                        docsToUpdate.add(doc);
                    }
                }
            }
            return docsToUpdate;
        }
        FinskyLog.wtf("Library not loaded.", new Object[0]);
        return Collections.emptyList();
    }

    public InstallWarnings getInstallWarningsForDocument(Document doc) {
        return getUpdateWarningsForDocument(doc, false);
    }

    public InstallWarnings getUpdateWarningsForDocument(Document doc, boolean treatDisabledUpdatesAsWarnings) {
        return getInstallWarningsForDocument(getAutoUpdateSizeLimit(), doc.getDocId(), doc.getAppDetails(), treatDisabledUpdatesAsWarnings);
    }

    public List<Document> getAppsThatRequireUpdateWarnings(List<Document> docs, boolean treatDisabledUpdatesAsWarnings) {
        long autoUpdateSizeLimit = getAutoUpdateSizeLimit();
        List<Document> appsThatRequireUpdateWarnings = Lists.newArrayList();
        for (Document doc : docs) {
            if (getInstallWarningsForDocument(autoUpdateSizeLimit, doc.getDocId(), doc.getAppDetails(), treatDisabledUpdatesAsWarnings).warningRequired()) {
                appsThatRequireUpdateWarnings.add(doc);
            }
        }
        return appsThatRequireUpdateWarnings;
    }

    private InstallWarnings getInstallWarningsForDocument(long downloadSizeLimit, String packageName, AppDetails appDetails, boolean treatDisabledUpdatesAsWarnings) {
        InstallWarnings installWarnings = new InstallWarnings();
        if (DEBUG_FORCE_LARGE_SIZE.contains(packageName)) {
            FinskyLog.w("Forcing true for size limit for package %s", packageName);
            installWarnings.largeDownload = true;
        }
        if ((appDetails.hasInstallationSize ? appDetails.installationSize : 0) >= downloadSizeLimit) {
            installWarnings.largeDownload = true;
        }
        AppState appState = this.mAppStates.getApp(appDetails.packageName);
        boolean isNewInstall = appState == null || appState.packageManagerState == null;
        if (isNewInstall) {
            installWarnings.newPermissions = true;
        } else {
            Set<String> installedPermissions = AppPermissionAdapter.loadLocalAssetPermissions(AppPermissionAdapter.getPackageInfo(this.mPackageManager, packageName));
            boolean hasAcceptedBuckets = appState.installerData != null && appState.installerData.getPermissionsVersion() == 1;
            PermissionData data = PermissionsBucketer.getPermissionBuckets(appDetails.permission, installedPermissions, hasAcceptedBuckets);
            if (DEBUG_FORCE_PERMISSIONS.contains(packageName) || data.mNewPermissions.size() > 0 || data.mForcePermissionPrompt) {
                installWarnings.newPermissions = true;
            }
        }
        if (!isNewInstall && treatDisabledUpdatesAsWarnings && appState.installerData != null && appState.installerData.getAutoUpdate() == AutoUpdateState.DISABLED) {
            installWarnings.autoUpdateDisabled = true;
        }
        return installWarnings;
    }

    private long getAutoUpdateSizeLimit() {
        return isMobileNetwork() ? getMaxBytesOverMobileRecommended() : Long.MAX_VALUE;
    }

    public List<Document> getApplicationsEligibleForNewUpdateNotification(List<Document> docsToUpdate) {
        List<Document> docsToNotify = Lists.newArrayList();
        for (Document doc : docsToUpdate) {
            AppDetails appDetails = doc.getAppDetails();
            InstallerData installerData = this.mAppStates.getInstallerDataStore().get(appDetails.packageName);
            if (this.mAppStates.getApp(appDetails.packageName).installerData == null || appDetails.versionCode > installerData.getLastNotifiedVersion()) {
                docsToNotify.add(doc);
            }
        }
        return docsToNotify;
    }

    public static Set<String> getForegroundPackages(Context context) {
        Set<String> result = Sets.newHashSet();
        ActivityManager am = (ActivityManager) context.getSystemService("activity");
        if (((PowerManager) context.getSystemService("power")).isScreenOn()) {
            List<RecentTaskInfo> tasks = am.getRecentTasks(1, 1);
            if (tasks.size() > 0) {
                RecentTaskInfo task = (RecentTaskInfo) tasks.get(0);
                if (task.baseIntent != null) {
                    ComponentName baseComponent = task.baseIntent.getComponent();
                    if (baseComponent != null) {
                        result.add(baseComponent.getPackageName());
                    }
                }
            }
        }
        if (((Boolean) G.autoUpdateExcludeForegroundServices.get()).booleanValue()) {
            List<RunningServiceInfo> services = am.getRunningServices(Integer.MAX_VALUE);
            if (services != null) {
                int serviceCount = services.size();
                for (int i = 0; i < serviceCount; i++) {
                    RunningServiceInfo service = (RunningServiceInfo) services.get(i);
                    if (service.foreground) {
                        result.add(service.service.getPackageName());
                    }
                }
            }
        }
        return result;
    }
}
