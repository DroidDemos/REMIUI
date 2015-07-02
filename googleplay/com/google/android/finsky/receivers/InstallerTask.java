package com.google.android.finsky.receivers;

import android.accounts.Account;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.Intent.ShortcutIconResource;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Environment;
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
import com.google.android.finsky.activities.AppActionAnalyzer;
import com.google.android.finsky.analytics.PlayStore.AppData;
import com.google.android.finsky.api.AccountHandler;
import com.google.android.finsky.api.DfeServerError;
import com.google.android.finsky.appstate.AppStates;
import com.google.android.finsky.appstate.AppStates.AppState;
import com.google.android.finsky.appstate.InstallerDataStore;
import com.google.android.finsky.appstate.InstallerDataStore.InstallerData;
import com.google.android.finsky.appstate.PackageStateRepository.PackageState;
import com.google.android.finsky.config.G;
import com.google.android.finsky.download.Download;
import com.google.android.finsky.download.DownloadImpl;
import com.google.android.finsky.download.DownloadManagerConstants;
import com.google.android.finsky.download.DownloadProgress;
import com.google.android.finsky.download.DownloadQueue;
import com.google.android.finsky.download.Storage;
import com.google.android.finsky.download.obb.Obb;
import com.google.android.finsky.download.obb.ObbFactory;
import com.google.android.finsky.installer.Gdiff;
import com.google.android.finsky.installer.Gdiff.FileFormatException;
import com.google.android.finsky.installer.InstallPolicies;
import com.google.android.finsky.installer.InstallerListener.InstallerPackageEvent;
import com.google.android.finsky.installer.PackageInstallerFacade;
import com.google.android.finsky.installer.PackageInstallerFacade.InstallListener;
import com.google.android.finsky.library.AccountLibrary;
import com.google.android.finsky.local.AssetUtils;
import com.google.android.finsky.protos.AndroidAppDelivery.AndroidAppDeliveryData;
import com.google.android.finsky.protos.AndroidAppDelivery.AndroidAppPatchData;
import com.google.android.finsky.protos.AndroidAppDelivery.HttpCookie;
import com.google.android.finsky.protos.Delivery.DeliveryResponse;
import com.google.android.finsky.receivers.Installer.InstallerProgressReport;
import com.google.android.finsky.receivers.Installer.InstallerState;
import com.google.android.finsky.utils.ConsistencyTokenHelper;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.Notifier;
import com.google.android.finsky.utils.PackageManagerUtils;
import com.google.android.finsky.utils.SelfUpdateScheduler;
import com.google.android.finsky.utils.Sha1Util;
import com.google.android.finsky.utils.Sha1Util.DigestResult;
import com.google.android.finsky.utils.Sha1Util.DigestStream;
import com.google.android.finsky.utils.Utils;
import com.google.android.finsky.utils.VendingPreferences;
import com.google.android.volley.DisplayMessageError;
import com.google.android.wallet.instrumentmanager.R;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.util.zip.GZIPInputStream;

class InstallerTask {
    private static InstallerProgressReport PROGRESS_INSTALLING;
    private static InstallerProgressReport PROGRESS_NOT_TRACKED;
    private static final String[] SUPPORTED_PATCH_FORMATS;
    private long mApkCompleted;
    private long mApkSize;
    private final AppStates mAppStates;
    private final DownloadQueue mDownloadQueue;
    private int mDownloadStatus;
    private final InstallPolicies mInstallPolicies;
    private final InstallerImpl mInstaller;
    private final InstallerDataStore mInstallerDataStore;
    private boolean mIsUpdate;
    private AppData mLogAppData;
    private boolean mMobileDataAllowed;
    private final Notifier mNotifier;
    private Obb mObbMain;
    private long mObbMainCompleted;
    private long mObbMainSize;
    private Obb mObbPatch;
    private long mObbPatchCompleted;
    private long mObbPatchSize;
    private final PackageInstallerFacade mPackageInstaller;
    private int mRecoveredIntoState;
    private boolean mShowCompletionNotifications;
    private boolean mShowErrorNotifications;
    private boolean mShowProgress;
    private String mTitle;
    private long mTotalSize;
    public final String packageName;

    static /* synthetic */ class AnonymousClass10 {
        static final /* synthetic */ int[] $SwitchMap$com$google$android$finsky$receivers$Installer$InstallerState;

        static {
            $SwitchMap$com$google$android$finsky$receivers$Installer$InstallerState = new int[InstallerState.values().length];
            try {
                $SwitchMap$com$google$android$finsky$receivers$Installer$InstallerState[InstallerState.NOT_TRACKED.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$google$android$finsky$receivers$Installer$InstallerState[InstallerState.INSTALLING.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    static {
        SUPPORTED_PATCH_FORMATS = new String[]{"1", "2"};
        PROGRESS_NOT_TRACKED = new InstallerProgressReport(InstallerState.NOT_TRACKED, 0, 0, 0);
        PROGRESS_INSTALLING = new InstallerProgressReport(InstallerState.INSTALLING, 0, 0, 0);
    }

    public InstallerTask(String packageName, InstallerImpl installer, AppStates appStates, DownloadQueue downloadQueue, Notifier notifier, InstallPolicies installPolicies, PackageInstallerFacade packageInstaller) {
        this.packageName = packageName;
        this.mInstaller = installer;
        this.mAppStates = appStates;
        this.mDownloadQueue = downloadQueue;
        this.mNotifier = notifier;
        this.mInstallerDataStore = appStates.getInstallerDataStore();
        this.mInstallPolicies = installPolicies;
        this.mPackageInstaller = packageInstaller;
    }

    public void start() {
        AppState appState = this.mAppStates.getApp(this.packageName);
        if (appState == null || appState.installerData == null) {
            FinskyLog.wtf("Unexpected missing installer data for %s", this.packageName);
            cancel(true);
            return;
        }
        InstallerData installerData = appState.installerData;
        int state = installerData.getInstallerState();
        populateFields(appState);
        if (state > 0 && installerData.getDeliveryData() != null) {
            processDeliveryData(installerData, false);
        }
        int newState = -1;
        this.mRecoveredIntoState = state;
        switch (state) {
            case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
            case com.google.android.play.R.styleable.Theme_colorControlActivated /*80*/:
                break;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                newState = 0;
                break;
            case com.google.android.play.R.styleable.Toolbar_navigationIcon /*20*/:
            case com.google.android.play.R.styleable.Theme_actionModeCopyDrawable /*30*/:
            case com.google.android.play.R.styleable.Theme_dropdownListPreferredItemHeight /*40*/:
                newState = 10;
                break;
            case com.google.android.play.R.styleable.Theme_actionModeCloseButtonStyle /*25*/:
            case com.google.android.play.R.styleable.Theme_actionModeWebSearchDrawable /*35*/:
            case com.google.android.play.R.styleable.Theme_buttonBarStyle /*45*/:
                FinskyLog.w("Cannot restart %s from downloading state %d", this.packageName, Integer.valueOf(state));
                cancel(false);
                notifyListeners(InstallerPackageEvent.DOWNLOAD_ERROR, 905);
                showDownloadNotification(905, null);
                return;
            case com.google.android.play.R.styleable.Theme_dividerHorizontal /*50*/:
                newState = 60;
                break;
            case com.google.android.play.R.styleable.Theme_toolbarStyle /*52*/:
            case com.google.android.play.R.styleable.Theme_popupWindowStyle /*55*/:
            case com.google.android.play.R.styleable.Theme_editTextBackground /*57*/:
                FinskyLog.w("Restarting %d for %s", Integer.valueOf(state), this.packageName);
                cancelCleanup(appState);
                notifyListeners(InstallerPackageEvent.DOWNLOAD_ERROR, 907);
                showDownloadNotification(907, null);
                return;
            case com.google.android.play.R.styleable.Theme_textAppearanceSearchResultSubtitle /*60*/:
            case com.google.android.play.R.styleable.Theme_textAppearanceListItem /*70*/:
                newState = 70;
                break;
            default:
                FinskyLog.wtf("Unknown state %d for %s", Integer.valueOf(state), this.packageName);
                cancel(true);
                return;
        }
        if (!(newState == -1 || newState == state)) {
            setInstallerState(newState, installerData.getDownloadUri());
        }
        advanceState();
    }

    public boolean recover(Uri contentUri, int status) {
        AppState appState = this.mAppStates.getApp(this.packageName);
        InstallerData installerData = appState.installerData;
        if (installerData == null || installerData.getDeliveryData() == null) {
            FinskyLog.d("Recovery of %s skipped because incomplete installerdata", this.packageName);
            return false;
        }
        populateFields(appState);
        processDeliveryData(appState.installerData, false);
        int installed = appState.packageManagerState != null ? appState.packageManagerState.installedVersion : -1;
        int desired = installerData.getDesiredVersion();
        int state = installerData.getInstallerState();
        this.mRecoveredIntoState = state;
        switch (state) {
            case com.google.android.play.R.styleable.Theme_actionModeCloseButtonStyle /*25*/:
                return recoverObb(appState, contentUri, status, desired, installed, false);
            case com.google.android.play.R.styleable.Theme_actionModeWebSearchDrawable /*35*/:
                return recoverObb(appState, contentUri, status, desired, installed, true);
            case com.google.android.play.R.styleable.Theme_buttonBarStyle /*45*/:
                return recoverApk(appState, contentUri, status, desired, installed);
            case com.google.android.play.R.styleable.Theme_dividerHorizontal /*50*/:
            case com.google.android.play.R.styleable.Theme_textAppearanceSearchResultSubtitle /*60*/:
                return recoverInstalling(appState, contentUri, status, desired, installed);
            case com.google.android.play.R.styleable.Theme_toolbarStyle /*52*/:
                FinskyLog.d("Recovery of %s skipped because state= %d", this.packageName, Integer.valueOf(state));
                cancelCleanup(appState);
                return false;
            case com.google.android.play.R.styleable.Theme_popupWindowStyle /*55*/:
                FinskyLog.d("Recovery of %s skipped because state= %d", this.packageName, Integer.valueOf(state));
                cancelCleanup(appState);
                return false;
            case com.google.android.play.R.styleable.Theme_editTextBackground /*57*/:
                FinskyLog.d("Recovery of %s skipped because state= %d", this.packageName, Integer.valueOf(state));
                cancelCleanup(appState);
                return false;
            default:
                FinskyLog.d("Recovery of %s skipped because state= %d", this.packageName, Integer.valueOf(state));
                return false;
        }
    }

    private boolean recoverObb(AppState appState, Uri contentUri, int status, int desired, int installed, boolean isPatch) {
        String tag = isPatch ? "Patch" : "Main";
        if (desired <= installed) {
            FinskyLog.d("Recovery of %s %s Obb skipped because desired= %d installed= %d", this.packageName, tag, Integer.valueOf(desired), Integer.valueOf(installed));
            return false;
        } else if (!DownloadManagerConstants.isStatusCompleted(status) && status != 198) {
            FinskyLog.d("Recovery of %s %s Obb into downloading OBB state", this.packageName, tag);
            Download download = generateObbDownload(appState.installerData, isPatch ? this.mObbPatch : this.mObbMain);
            download.setContentUri(contentUri);
            this.mDownloadQueue.addRecoveredDownload(download);
            return true;
        } else if (DownloadManagerConstants.isStatusSuccess(status)) {
            Obb obb = isPatch ? this.mObbPatch : this.mObbMain;
            obb.syncStateWithStorage();
            if (obb.getState() != 4 || obb.finalizeTempFile()) {
                FinskyLog.d("Recovery of %s %s Obb into ready to install state", this.packageName, tag);
                setInstallerState(isPatch ? 40 : 30, contentUri.toString());
                advanceState();
                return true;
            }
            FinskyLog.d("Recovery of %s %s Obb skipped - finalize failed", this.packageName, tag);
            cancel(false);
            int code = isPatch ? 912 : 911;
            notifyListeners(InstallerPackageEvent.DOWNLOAD_ERROR, code);
            showDownloadNotification(code, null);
            return false;
        } else {
            FinskyLog.d("Recovery of %s %s Obb into error state, status= %d", this.packageName, tag, Integer.valueOf(status));
            cancel(false);
            notifyListeners(InstallerPackageEvent.DOWNLOAD_ERROR, status);
            showDownloadNotification(status, null);
            return false;
        }
    }

    private boolean recoverApk(AppState appState, Uri contentUri, int status, int desired, int installed) {
        if (desired <= installed) {
            FinskyLog.d("Recovery of %s skipped because desired= %d installed= %d", this.packageName, Integer.valueOf(desired), Integer.valueOf(installed));
            return false;
        } else if (!DownloadManagerConstants.isStatusCompleted(status) && status != 198) {
            FinskyLog.d("Recovery of %s into downloading APK state", this.packageName);
            Download download = generateDownload(appState.installerData);
            download.setContentUri(contentUri);
            this.mDownloadQueue.addRecoveredDownload(download);
            return true;
        } else if (DownloadManagerConstants.isStatusSuccess(status)) {
            FinskyLog.d("Recovery of %s into ready to install state", this.packageName);
            setInstallerState(50, contentUri.toString());
            advanceState();
            return true;
        } else {
            FinskyLog.d("Recovery of %s into error state, status= %d", this.packageName, Integer.valueOf(status));
            cancel(false);
            notifyListeners(InstallerPackageEvent.DOWNLOAD_ERROR, status);
            showDownloadNotification(status, null);
            return false;
        }
    }

    private boolean recoverInstalling(AppState appState, Uri contentUri, int status, int desired, int installed) {
        if (desired < installed) {
            FinskyLog.d("Recovery of %s skipped because desired= %d installed= %d", this.packageName, Integer.valueOf(desired), Integer.valueOf(installed));
        } else if (desired == installed) {
            FinskyLog.d("Recovery of %s - installation seems complete", this.packageName);
            setInstallerState(70, contentUri.toString());
            advanceState();
        } else {
            FinskyLog.d("Recovery of %s with incomplete installation", this.packageName);
            cancel(false);
            notifyListeners(InstallerPackageEvent.UNINSTALLED, status);
        }
        return false;
    }

    private void populateFields(AppState appState) {
        boolean z;
        boolean z2 = false;
        InstallerData installerData = appState.installerData;
        if (appState.packageManagerState != null) {
            z = true;
        } else {
            z = false;
        }
        this.mIsUpdate = z;
        this.mTitle = installerData.getTitle();
        checkForEmptyTitle("populateFields", this.packageName, this.mTitle, installerData);
        int flags = installerData.getFlags();
        if ((flags & 16) == 0) {
            z = true;
        } else {
            z = false;
        }
        this.mShowProgress = z;
        if ((flags & 1) == 0) {
            z = true;
        } else {
            z = false;
        }
        this.mShowErrorNotifications = z;
        if ((flags & 128) == 0) {
            z2 = true;
        }
        this.mShowCompletionNotifications = z2;
        this.mLogAppData = new AppData();
        this.mLogAppData.version = installerData.getDesiredVersion();
        this.mLogAppData.hasVersion = true;
        if (appState.packageManagerState != null) {
            this.mLogAppData.oldVersion = appState.packageManagerState.installedVersion;
            this.mLogAppData.hasOldVersion = true;
            this.mLogAppData.systemApp = appState.packageManagerState.isSystemApp;
            this.mLogAppData.hasSystemApp = true;
        }
        if ((flags & 4096) != 0) {
            this.mLogAppData.downloadExternal = true;
            this.mLogAppData.hasDownloadExternal = true;
        }
        if ((flags & 512) != 0) {
            this.mLogAppData.downloadGzip = true;
            this.mLogAppData.hasDownloadGzip = true;
        }
        if ((flags & 4) != 0) {
            this.mLogAppData.downloadPatch = true;
            this.mLogAppData.hasDownloadPatch = true;
            if (isGzippedPatch(installerData)) {
                this.mLogAppData.downloadGzip = true;
                this.mLogAppData.hasDownloadGzip = true;
            }
        }
    }

    public static void checkForEmptyTitle(String caller, String packageName, String title, InstallerData installerData) {
        if (TextUtils.isEmpty(title)) {
            if (installerData == null) {
                FinskyLog.e("b/11413796 - installerData is null", new Object[0]);
            } else {
                FinskyLog.e("b/11413796 - acct:  %s", FinskyLog.scrubPii(installerData.getAccountName()));
                FinskyLog.e("b/11413796 - vers:  %d", Integer.valueOf(installerData.getDesiredVersion()));
                FinskyLog.e("b/11413796 - uri:   %s", installerData.getDownloadUri());
                FinskyLog.e("b/11413796 - flags: %d", Integer.valueOf(installerData.getFlags()));
                FinskyLog.e("b/11413796 - state: %d", Integer.valueOf(installerData.getInstallerState()));
                FinskyLog.e("b/11413796 - pkg:   %s", installerData.getPackageName());
                String str = "b/11413796 - data?  %b";
                Object[] objArr = new Object[1];
                objArr[0] = Boolean.valueOf(installerData.getDeliveryData() != null);
                FinskyLog.e(str, objArr);
            }
            if (title == null) {
                FinskyLog.wtf("b/11413796 - %s for %s (null title)", caller, packageName);
            } else {
                FinskyLog.wtf("b/11413796 - %s for %s (empty title)", caller, packageName);
            }
        }
    }

    public AppData getAppData() {
        return this.mLogAppData;
    }

    private void showDownloadNotification(int errorCode, String serverMessage) {
        if (this.mShowErrorNotifications) {
            this.mNotifier.showDownloadErrorMessage(this.mTitle, this.packageName, errorCode, serverMessage, this.mIsUpdate);
        }
    }

    public void cancel(boolean sendNotify) {
        AppState appState = this.mAppStates.getApp(this.packageName);
        if (appState == null || appState.installerData == null || appState.installerData.getInstallerState() < 50) {
            cancelCleanup(appState);
            if (sendNotify) {
                notifyListeners(InstallerPackageEvent.DOWNLOAD_CANCELLED, 0);
                return;
            }
            return;
        }
        FinskyLog.d("Cannot cancel installing %s - too late", this.packageName);
    }

    private void cancelCleanup(AppState appState) {
        File tempFile;
        FinskyLog.d("Cancel running installation of %s", this.packageName);
        Download download = this.mDownloadQueue.getByPackageName(this.packageName);
        if (download != null) {
            this.mDownloadQueue.cancel(download);
        }
        this.mPackageInstaller.cancelSession(this.packageName);
        this.mInstaller.clearInstallerState(appState);
        cleanExternalStorage();
        if (!(this.mObbMain != null || this.mObbPatch != null || appState == null || appState.installerData == null || appState.installerData.getDeliveryData() == null)) {
            processDeliveryData(appState.installerData, false);
        }
        if (this.mObbMain != null) {
            tempFile = this.mObbMain.getTempFile();
            if (tempFile != null) {
                tempFile.delete();
            }
        }
        if (this.mObbPatch != null) {
            tempFile = this.mObbPatch.getTempFile();
            if (tempFile != null) {
                tempFile.delete();
            }
        }
        this.mInstaller.taskFinished(this);
    }

    public InstallerState getState() {
        AppState appState = this.mAppStates.getApp(this.packageName);
        if (appState == null || appState.installerData == null) {
            return InstallerState.NOT_TRACKED;
        }
        switch (appState.installerData.getInstallerState()) {
            case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
            case com.google.android.play.R.styleable.Theme_textAppearanceListItem /*70*/:
            case com.google.android.play.R.styleable.Theme_colorControlActivated /*80*/:
                return InstallerState.NOT_TRACKED;
            case com.google.android.play.R.styleable.Theme_toolbarStyle /*52*/:
            case com.google.android.play.R.styleable.Theme_popupWindowStyle /*55*/:
            case com.google.android.play.R.styleable.Theme_editTextBackground /*57*/:
            case com.google.android.play.R.styleable.Theme_textAppearanceSearchResultSubtitle /*60*/:
                return InstallerState.INSTALLING;
            default:
                return InstallerState.DOWNLOADING;
        }
    }

    public InstallerProgressReport getProgress() {
        switch (AnonymousClass10.$SwitchMap$com$google$android$finsky$receivers$Installer$InstallerState[getState().ordinal()]) {
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                return PROGRESS_NOT_TRACKED;
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                return PROGRESS_INSTALLING;
            default:
                return new InstallerProgressReport(InstallerState.DOWNLOADING, (this.mApkCompleted + this.mObbMainCompleted) + this.mObbPatchCompleted, this.mTotalSize, this.mDownloadStatus);
        }
    }

    private void advanceState() {
        AppState appState = this.mAppStates.getApp(this.packageName);
        if (appState == null || appState.installerData == null) {
            FinskyLog.wtf("Unexpected missing installer data for %s", this.packageName);
            cancel(true);
            return;
        }
        InstallerData installerData = appState.installerData;
        switch (installerData.getInstallerState()) {
            case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                int desiredVersion = installerData.getDesiredVersion();
                PackageState packageState = appState.packageManagerState;
                int installedVersion = packageState != null ? packageState.installedVersion : -1;
                if (installedVersion >= desiredVersion) {
                    if (installedVersion > desiredVersion) {
                        this.mAppStates.getInstallerDataStore().setDesiredVersion(this.packageName, installedVersion);
                    }
                    setInstallerState(70, (String) null);
                    advanceState();
                    return;
                }
                int otherUserVersionCode = getInstalledVersionForOtherUser(appState);
                if (otherUserVersionCode >= desiredVersion) {
                    if (otherUserVersionCode > desiredVersion) {
                        this.mAppStates.getInstallerDataStore().setDesiredVersion(this.packageName, otherUserVersionCode);
                    }
                    this.mInstallerDataStore.setFlags(this.packageName, installerData.getFlags() | 32);
                    setInstallerState(60, (String) null);
                    advanceState();
                    return;
                } else if (!checkValidDeliveryData(installerData)) {
                    requestDeliveryData(appState);
                    return;
                }
                break;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                break;
            case com.google.android.play.R.styleable.Toolbar_navigationIcon /*20*/:
            case com.google.android.play.R.styleable.Theme_actionModeCopyDrawable /*30*/:
            case com.google.android.play.R.styleable.Theme_dropdownListPreferredItemHeight /*40*/:
                break;
            case com.google.android.play.R.styleable.Theme_actionModeCloseButtonStyle /*25*/:
            case com.google.android.play.R.styleable.Theme_actionModeWebSearchDrawable /*35*/:
            case com.google.android.play.R.styleable.Theme_buttonBarStyle /*45*/:
                return;
            case com.google.android.play.R.styleable.Theme_dividerHorizontal /*50*/:
                if (!startApplyingPatch(appState) && !startCopyFromDownload(appState)) {
                    startVerifyDownload(appState);
                    return;
                }
                return;
            case com.google.android.play.R.styleable.Theme_textAppearanceSearchResultSubtitle /*60*/:
                startInstaller(appState);
                return;
            case com.google.android.play.R.styleable.Theme_textAppearanceListItem /*70*/:
                cleanup(appState);
                return;
            case com.google.android.play.R.styleable.Theme_colorControlActivated /*80*/:
                this.mInstaller.clearInstallerState(appState);
                this.mInstaller.taskFinished(this);
                return;
            default:
                FinskyLog.wtf("Bad state %d for %s", Integer.valueOf(installerData.getInstallerState()), this.packageName);
                cancel(true);
                return;
        }
        processDeliveryData(installerData, false);
        startNextDownload(installerData);
    }

    private int getInstalledVersionForOtherUser(AppState appState) {
        int i = -1;
        if (FinskyApp.get().getUsers().supportsMultiUser() && appState.packageManagerState == null) {
            try {
                PackageInfo info = FinskyApp.get().getPackageManager().getPackageInfo(this.packageName, 8192);
                if ((info.applicationInfo.flags & 8388608) == 0) {
                    FinskyLog.d("Found %s version %d installed for another user", this.packageName, Integer.valueOf(info.versionCode));
                    i = info.versionCode;
                }
            } catch (NameNotFoundException e) {
            }
        }
        return i;
    }

    private String getCertHashForOtherUser() {
        int i = 0;
        String str = null;
        if (FinskyApp.get().getUsers().supportsMultiUser()) {
            try {
                PackageInfo info = FinskyApp.get().getPackageManager().getPackageInfo(this.packageName, 8256);
                if ((info.applicationInfo.flags & 8388608) == 0) {
                    String str2 = "Found %s with %d signatures installed for another user";
                    Object[] objArr = new Object[2];
                    objArr[0] = this.packageName;
                    if (info.signatures != null) {
                        i = info.signatures.length;
                    }
                    objArr[1] = Integer.valueOf(i);
                    FinskyLog.d(str2, objArr);
                    str = computeCertificateHash(info);
                }
            } catch (NameNotFoundException e) {
            }
        }
        return str;
    }

    private static String computeCertificateHash(PackageInfo packageInfo) {
        if (packageInfo.signatures == null || packageInfo.signatures.length == 0) {
            return null;
        }
        return Sha1Util.secureHash(packageInfo.signatures[0].toByteArray());
    }

    private boolean checkValidDeliveryData(InstallerData installerData) {
        if (installerData.getDeliveryData() == null) {
            return false;
        }
        long timestampMs = installerData.getDeliveryDataTimestampMs();
        if (timestampMs == 0) {
            return true;
        }
        if (timestampMs + ((Long) G.deliveryDataExpirationMs.get()).longValue() >= System.currentTimeMillis()) {
            return true;
        }
        this.mInstallerDataStore.setDeliveryData(this.packageName, null, 0);
        int flags = installerData.getFlags();
        int newFlags = flags & -5;
        if (newFlags != flags) {
            this.mInstallerDataStore.setFlags(this.packageName, newFlags);
        }
        return false;
    }

    private void requestDeliveryData(AppState appState) {
        String certificateHash;
        checkForEmptyTitle("requestDeliveryData", this.packageName, this.mTitle, appState.installerData);
        InstallerData installerData = appState.installerData;
        final String packageName = installerData.getPackageName();
        final int packageVersion = installerData.getDesiredVersion();
        boolean isEarlyUpdate = (installerData.getFlags() & 65536) != 0;
        final String deliveryToken = installerData.getDeliveryToken();
        Account account = null;
        String accountName = installerData.getAccountForUpdate();
        final FinskyApp finskyApp = FinskyApp.get();
        if (!isEarlyUpdate) {
            if (!TextUtils.isEmpty(accountName)) {
                account = AccountHandler.findAccount(accountName, finskyApp);
                if (account == null) {
                    FinskyLog.w("Account %s for update of %s no longer exists.", FinskyLog.scrubPii(accountName), packageName);
                    this.mInstallerDataStore.setAccountForUpdate(packageName, null);
                }
            }
            if (account == null) {
                accountName = installerData.getAccountName();
                account = AccountHandler.findAccount(accountName, finskyApp);
            }
            if (account == null) {
                FinskyLog.d("Invalid account %s", accountName);
                cancel(false);
                notifyListeners(InstallerPackageEvent.DOWNLOAD_ERROR, 906);
                showDownloadNotification(906, null);
                return;
            }
        }
        final Account finalAccount = account;
        final String finalAccountName = accountName;
        Integer previousVersion = null;
        if (appState.packageManagerState != null && ((Boolean) G.downloadSendBaseVersionCode.get()).booleanValue()) {
            previousVersion = Integer.valueOf(appState.packageManagerState.installedVersion);
        }
        final Integer finalPreviousVersion = previousVersion;
        if (appState.packageManagerState != null) {
            certificateHash = appState.packageManagerState.certificateHashes[0];
        } else {
            certificateHash = getCertHashForOtherUser();
        }
        final String finalCertificateHash = certificateHash;
        this.mPackageInstaller.reportProgress(packageName, 0, 0);
        final Listener<DeliveryResponse> responseListener = new Listener<DeliveryResponse>() {
            public void onResponse(DeliveryResponse deliveryResponse) {
                final int responseCode = deliveryResponse.status;
                if (responseCode == 1) {
                    InstallerTask.this.mInstallerDataStore.setDeliveryData(packageName, deliveryResponse.appDeliveryData, System.currentTimeMillis());
                    InstallerData updated = InstallerTask.this.mAppStates.getApp(packageName).installerData;
                    InstallerTask.this.processDeliveryData(updated, true);
                    InstallerTask.this.startNextDownload(updated);
                    return;
                }
                FinskyLog.d("Received non-OK response %d", Integer.valueOf(responseCode));
                InstallerTask.this.cancel(false);
                Runnable finishHandling = new Runnable() {
                    public void run() {
                        int deliveryError = InstallerTask.this.deliveryResponseToInstallerError(responseCode);
                        FinskyApp.get().getEventLogger().logBackgroundEvent(104, packageName, null, deliveryError, null, InstallerTask.this.mLogAppData);
                        InstallerTask.this.notifyListeners(InstallerPackageEvent.DOWNLOAD_ERROR, deliveryError);
                        InstallerTask.this.showDownloadNotification(deliveryError, null);
                    }
                };
                if (responseCode == 5) {
                    FinskyApp.get().clearRequestCacheAsync(finishHandling);
                } else {
                    finishHandling.run();
                }
            }
        };
        final ErrorListener errorListener = new ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
                String serverMessage;
                int volleyErrorCode = InstallerTask.this.volleyErrorToInstallerError(volleyError);
                if (volleyError instanceof DisplayMessageError) {
                    serverMessage = ((DisplayMessageError) volleyError).getDisplayErrorHtml();
                } else {
                    serverMessage = null;
                }
                FinskyLog.d("Received VolleyError %d (%s)", Integer.valueOf(volleyErrorCode), serverMessage);
                InstallerTask.this.cancel(false);
                FinskyApp.get().getEventLogger().logBackgroundEvent(104, packageName, null, volleyErrorCode, null, InstallerTask.this.mLogAppData);
                InstallerTask.this.notifyListeners(InstallerPackageEvent.DOWNLOAD_ERROR, volleyErrorCode);
                InstallerTask.this.showDownloadNotification(volleyErrorCode, serverMessage);
            }
        };
        final boolean z = isEarlyUpdate;
        final String str = packageName;
        ConsistencyTokenHelper.get(finskyApp, new ConsistencyTokenHelper.Listener() {
            public void onTokenReceived(String consistencyToken) {
                if (z) {
                    FinskyLog.d("Request earlyDelivery for %s", str);
                    String certificateHashMd5 = null;
                    if (finskyApp.getPackageName().equals(str)) {
                        certificateHashMd5 = SelfUpdateScheduler.getCertificateHashSelfUpdateMD5(finskyApp);
                    }
                    finskyApp.getDfeApiNonAuthenticated().earlyDelivery(str, 1, Integer.valueOf(packageVersion), finalPreviousVersion, InstallerTask.SUPPORTED_PATCH_FORMATS, certificateHashMd5, finalCertificateHash, deliveryToken, consistencyToken, responseListener, errorListener);
                    return;
                }
                AccountLibrary accountLibrary = finskyApp.getLibraries().getAccountLibrary(finalAccount);
                String str = consistencyToken;
                finskyApp.getDfeApi(finalAccountName).delivery(str, 1, accountLibrary.getServerToken(AccountLibrary.LIBRARY_ID_APPS), Integer.valueOf(packageVersion), finalPreviousVersion, InstallerTask.SUPPORTED_PATCH_FORMATS, null, finalCertificateHash, deliveryToken, str, responseListener, errorListener);
            }
        });
        setInstallerState(10, installerData.getDownloadUri());
    }

    private int deliveryResponseToInstallerError(int deliveryResponseStatus) {
        switch (deliveryResponseStatus) {
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                return 940;
            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                return 941;
            case R.styleable.WalletImFormEditText_required /*4*/:
                return 942;
            case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                return 944;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                return 945;
            default:
                return 943;
        }
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

    private void processDeliveryData(InstallerData installerData, boolean canSetMobileDataAllowed) {
        boolean z = true;
        AndroidAppDeliveryData deliveryData = installerData.getDeliveryData();
        this.mApkSize = deliveryData.downloadSize;
        this.mObbMainSize = 0;
        this.mObbPatchSize = 0;
        this.mTotalSize = this.mApkSize;
        this.mApkCompleted = 0;
        this.mObbMainCompleted = 0;
        this.mObbPatchCompleted = 0;
        if (deliveryData.additionalFile.length > 0) {
            this.mObbMain = AssetUtils.extractObb(deliveryData, installerData.getPackageName(), false);
            if (this.mObbMain != null) {
                this.mObbMain.syncStateWithStorage();
                if (this.mObbMain.getState() == 4) {
                    this.mObbMainSize = this.mObbMain.getSize();
                    this.mTotalSize += this.mObbMainSize;
                }
            }
            this.mObbPatch = AssetUtils.extractObb(deliveryData, installerData.getPackageName(), true);
            if (this.mObbPatch != null) {
                this.mObbMain.syncStateWithStorage();
                if (this.mObbPatch.getState() == 4) {
                    this.mObbPatchSize = this.mObbPatch.getSize();
                    this.mTotalSize += this.mObbPatchSize;
                }
            }
        }
        int flags = installerData.getFlags();
        if ((flags & 2048) != 0) {
            this.mMobileDataAllowed = false;
            return;
        }
        boolean z2;
        if ((flags & 2) != 0) {
            z2 = true;
        } else {
            z2 = false;
        }
        this.mMobileDataAllowed = z2;
        if (canSetMobileDataAllowed && !this.mMobileDataAllowed) {
            if (this.mTotalSize >= this.mInstallPolicies.getMaxBytesOverMobileRecommended()) {
                z = false;
            }
            this.mMobileDataAllowed = z;
            if (this.mMobileDataAllowed) {
                this.mInstaller.setMobileDataAllowed(this.packageName);
            }
        }
        if (!this.mPackageInstaller.hasSession(this.packageName)) {
            String title = installerData.getTitle();
            checkForEmptyTitle("processDeliveryData", this.packageName, title, installerData);
            this.mPackageInstaller.createSession(this.packageName, this.mApkSize, title, null);
        }
    }

    private void startNextDownload(InstallerData installerData) {
        Download download = null;
        int state = installerData.getInstallerState();
        if (state < 20) {
            state = 20;
        }
        int nextState = -1;
        if (!requireInternalStorageOrCancel(installerData)) {
            if (installerData.getDeliveryData().encryptionParams != null) {
                FinskyLog.wtf("Server sent encryption params for %s", installerData.getPackageName());
                cancel(false);
                notifyListeners(InstallerPackageEvent.DOWNLOAD_ERROR, 913);
                showDownloadNotification(913, null);
                FinskyApp.get().getEventLogger().logBackgroundEvent(112, packageName, null, 913, null, this.mLogAppData);
                return;
            }
            switch (state) {
                case com.google.android.play.R.styleable.Toolbar_navigationIcon /*20*/:
                    if (this.mObbMain != null) {
                        if (!requireExternalStorageOrCancel(installerData)) {
                            this.mObbMain.syncStateWithStorage();
                            if (this.mObbMain.getState() == 4) {
                                this.mObbMain.getTempFile().delete();
                                download = generateObbDownload(installerData, this.mObbMain);
                                nextState = 20;
                                break;
                            }
                        }
                        return;
                    }
                case com.google.android.play.R.styleable.Theme_actionModeCopyDrawable /*30*/:
                    if (this.mObbPatch != null) {
                        if (!requireExternalStorageOrCancel(installerData)) {
                            this.mObbPatch.syncStateWithStorage();
                            if (this.mObbPatch.getState() == 4) {
                                this.mObbPatch.getTempFile().delete();
                                download = generateObbDownload(installerData, this.mObbPatch);
                                nextState = 30;
                                break;
                            }
                        }
                        return;
                    }
                case com.google.android.play.R.styleable.Theme_dropdownListPreferredItemHeight /*40*/:
                    download = generateDownload(installerData);
                    nextState = 40;
                    break;
            }
            if (nextState >= 0) {
                this.mDownloadQueue.add(download);
                FinskyApp.get().getEventLogger().logBackgroundEvent(100, download.toString(), null, 0, null, this.mLogAppData);
                setInstallerState(nextState, (String) null);
                return;
            }
            FinskyLog.e("Unexpected download start states for %s: %d %d", this.packageName, Integer.valueOf(installerData.getInstallerState()), Integer.valueOf(nextState));
            cancel(false);
            notifyListeners(InstallerPackageEvent.DOWNLOAD_ERROR, 902);
            showDownloadNotification(902, null);
        }
    }

    private boolean requireInternalStorageOrCancel(InstallerData installerData) {
        if (this.mInstallPolicies.isFreeSpaceSufficient(installerData.getDeliveryData().downloadSize, Environment.getDataDirectory(), FinskyApp.get().getContentResolver())) {
            return false;
        }
        FinskyLog.e("Cancel download of %s because insufficient free space", this.packageName);
        cancel(false);
        notifyListeners(InstallerPackageEvent.DOWNLOAD_ERROR, 908);
        FinskyApp.get().getEventLogger().logBackgroundEvent(112, this.packageName, "no-internal-storage", 0, null, this.mLogAppData);
        if (this.mShowErrorNotifications) {
            this.mNotifier.showInternalStorageFull(installerData.getTitle(), this.packageName);
        }
        return true;
    }

    private boolean requireExternalStorageOrCancel(InstallerData installerData) {
        if (Storage.externalStorageAvailable()) {
            return false;
        }
        FinskyLog.e("Cancel download of %s because no external storage for OBB", this.packageName);
        cancel(false);
        notifyListeners(InstallerPackageEvent.DOWNLOAD_ERROR, 901);
        FinskyApp.get().getEventLogger().logBackgroundEvent(112, this.packageName, "obb-no-external-storage", 0, null, this.mLogAppData);
        if (this.mShowErrorNotifications) {
            this.mNotifier.showExternalStorageMissing(installerData.getTitle(), this.packageName);
        }
        return true;
    }

    private Download generateDownload(InstallerData installerData) {
        String downloadUri;
        long actualSize;
        long maxDownloadSize;
        AndroidAppDeliveryData deliveryData = installerData.getDeliveryData();
        String title = installerData.getTitle();
        String packageName = installerData.getPackageName();
        HttpCookie authCookie = deliveryData.downloadAuthCookie[0];
        Uri fileUri = null;
        int oldFlags = installerData.getFlags();
        int newFlags = oldFlags;
        if (canDownloadApkToExternalStorage(installerData)) {
            newFlags |= 4096;
            this.mLogAppData.downloadExternal = true;
            this.mLogAppData.hasDownloadExternal = true;
            fileUri = Uri.fromFile(new File(FinskyApp.get().getExternalFilesDir(null), String.valueOf(System.currentTimeMillis())));
            FinskyLog.d("Downloading %s via external storage", packageName);
        }
        if (canDownloadPatch(installerData)) {
            AndroidAppPatchData patchData = deliveryData.patchData;
            downloadUri = patchData.downloadUrl;
            actualSize = -1;
            maxDownloadSize = patchData.maxPatchSize;
            newFlags |= 4;
            this.mLogAppData.downloadPatch = true;
            this.mLogAppData.hasDownloadPatch = true;
            if (isGzippedPatch(installerData)) {
                this.mLogAppData.downloadGzip = true;
                this.mLogAppData.hasDownloadGzip = true;
            }
        } else {
            if (canDownloadGzippedApk(installerData, Storage.dataPartitionAvailableSpace())) {
                downloadUri = deliveryData.gzippedDownloadUrl;
                actualSize = -1;
                maxDownloadSize = deliveryData.gzippedDownloadSize;
                newFlags |= 512;
                this.mLogAppData.downloadGzip = true;
                this.mLogAppData.hasDownloadGzip = true;
            } else {
                downloadUri = deliveryData.downloadUrl;
                actualSize = deliveryData.downloadSize;
                maxDownloadSize = actualSize;
            }
        }
        if (newFlags != oldFlags) {
            this.mInstallerDataStore.setFlags(packageName, newFlags);
        }
        return new DownloadImpl(downloadUri, title, packageName, authCookie.name, authCookie.value, fileUri, actualSize, maxDownloadSize, null, !this.mMobileDataAllowed, !this.mShowProgress);
    }

    private Download generateObbDownload(InstallerData installerData, Obb obb) {
        Context context = FinskyApp.get();
        AndroidAppDeliveryData deliveryData = installerData.getDeliveryData();
        String title = context.getString(com.android.vending.R.string.notification_additional_data, new Object[]{installerData.getTitle()});
        String packageName = installerData.getPackageName();
        HttpCookie authCookie = deliveryData.downloadAuthCookie[0];
        Uri fileUri = Uri.fromFile(obb.getTempFile());
        long downloadSize = deliveryData.downloadSize;
        return new DownloadImpl(obb.getUrl(), title, packageName, authCookie.name, authCookie.value, fileUri, downloadSize, downloadSize, obb, !this.mMobileDataAllowed, !this.mShowProgress);
    }

    private void startInstaller(AppState appState) {
        if (!startActivation(appState)) {
            int obbState;
            boolean z;
            boolean forwardLock;
            InstallerData installerData = appState.installerData;
            AndroidAppDeliveryData deliveryData = installerData.getDeliveryData();
            if (this.mObbMain != null) {
                this.mObbMain.syncStateWithStorage();
                obbState = this.mObbMain.getState();
                if (!(obbState == 5 || obbState == 3)) {
                    FinskyLog.e("Can't find main obb file for %s", this.packageName);
                    cancel(false);
                    notifyListeners(InstallerPackageEvent.DOWNLOAD_ERROR, 911);
                    showDownloadNotification(911, null);
                }
            }
            if (this.mObbPatch != null) {
                this.mObbPatch.syncStateWithStorage();
                obbState = this.mObbPatch.getState();
                if (!(obbState == 5 || obbState == 3)) {
                    FinskyLog.e("Can't find patch obb file for %s", this.packageName);
                    cancel(false);
                    notifyListeners(InstallerPackageEvent.DOWNLOAD_ERROR, 912);
                    showDownloadNotification(912, null);
                }
            }
            String downloadUri = installerData.getDownloadUri();
            if (appState.packageManagerState != null) {
                z = true;
            } else {
                z = false;
            }
            this.mIsUpdate = z;
            if (VERSION.SDK_INT < ((Integer) G.preserveForwardLockApiMin.get()).intValue() || VERSION.SDK_INT > ((Integer) G.preserveForwardLockApiMax.get()).intValue() || !isInstalledForwardLocked(this.packageName, null)) {
                forwardLock = false;
            } else {
                forwardLock = true;
            }
            FinskyApp.get().getEventLogger().logBackgroundEvent(106, this.packageName, null, 0, null, this.mLogAppData);
            FinskyLog.d("Begin install of %s", this.packageName);
            if (this.mShowProgress) {
                FinskyApp.get().getNotifier().showInstallingMessage(this.mTitle, this.packageName, this.mIsUpdate);
            }
            this.mPackageInstaller.install(this.packageName, forwardLock, getInstallerListener(downloadUri));
            setInstallerState(60, downloadUri);
            notifyListeners(InstallerPackageEvent.INSTALLING, 0);
        }
    }

    private InstallListener getInstallerListener(final String downloadUri) {
        return new InstallListener() {
            public void installSucceeded() {
                FinskyLog.d("Successful install of %s", InstallerTask.this.packageName);
                if (InstallerTask.this.mShowCompletionNotifications && !InstallerTask.this.mIsUpdate && ((Boolean) VendingPreferences.AUTO_ADD_SHORTCUTS.get()).booleanValue()) {
                    InstallerTask.this.addAppShortcut();
                }
                commonFinish(110, 0, null);
            }

            public void installFailed(int errorCode, String exceptionType) {
                FinskyLog.w("Install failure of %s: %d %s", InstallerTask.this.packageName, Integer.valueOf(errorCode), exceptionType);
                if (InstallerTask.this.mShowErrorNotifications) {
                    InstallerTask.this.notifyFailedInstall(InstallerTask.this.packageName, errorCode);
                }
                commonFinish(111, errorCode, exceptionType);
            }

            private void commonFinish(int logType, int logErrorCode, String logExceptionType) {
                if (downloadUri != null) {
                    InstallerTask.this.mDownloadQueue.release(Uri.parse(downloadUri));
                }
                FinskyApp.get().getEventLogger().logBackgroundEvent(logType, InstallerTask.this.packageName, null, logErrorCode, logExceptionType, InstallerTask.this.mLogAppData);
                InstallerTask.this.setInstallerState(70, (String) null);
                InstallerTask.this.advanceState();
            }
        };
    }

    private boolean startActivation(final AppState appState) {
        if ((appState.installerData.getFlags() & 32) == 0) {
            return false;
        }
        new AsyncTask<Void, Void, Integer>() {
            protected Integer doInBackground(Void... params) {
                try {
                    return Integer.valueOf(PackageManagerUtils.installExistingPackage(FinskyApp.get(), appState.packageName));
                } catch (NameNotFoundException e) {
                    return Integer.valueOf(-3);
                }
            }

            protected void onPostExecute(Integer status) {
                int errorCode = 0;
                FinskyApp.get().getPackageInfoRepository().invalidate(appState.packageName);
                if (status.intValue() != 1) {
                    InstallerTask.this.notifyFailedInstall(appState.packageName, status.intValue());
                    errorCode = status.intValue();
                } else if (InstallerTask.this.mShowCompletionNotifications && ((Boolean) VendingPreferences.AUTO_ADD_SHORTCUTS.get()).booleanValue()) {
                    InstallerTask.this.addAppShortcut();
                }
                FinskyLog.d("installExistingPackage %s result %d", appState.packageName, status);
                FinskyApp.get().getEventLogger().logBackgroundEvent(109, InstallerTask.this.packageName, null, errorCode, null, InstallerTask.this.mLogAppData);
                InstallerTask.this.setInstallerState(70, (String) null);
                InstallerTask.this.advanceState();
            }
        }.execute(new Void[0]);
        setInstallerState(60, (String) null);
        notifyListeners(InstallerPackageEvent.INSTALLING, 0);
        return true;
    }

    private void notifyFailedInstall(String packageName, int returnCode) {
        String message;
        int internalReturnCode;
        int messageId = getInstallFailMessageId(returnCode);
        Context appContext = FinskyApp.get();
        if (messageId >= 0) {
            message = appContext.getString(messageId);
        } else {
            message = appContext.getString(com.android.vending.R.string.install_failed_unknown, new Object[]{Integer.valueOf(returnCode)});
        }
        switch (returnCode) {
            case -104:
                internalReturnCode = 1;
                break;
            default:
                internalReturnCode = -1;
                break;
        }
        Notifier notifier = FinskyApp.get().getNotifier();
        notifier.hideAllMessagesForPackage(packageName);
        notifier.showInstallationFailureMessage(this.mTitle, packageName, message, internalReturnCode);
    }

    private static int getInstallFailMessageId(int returnCode) {
        switch (returnCode) {
            case -109:
                return com.android.vending.R.string.install_parse_failed_bad_manifest;
            case -108:
                return com.android.vending.R.string.install_parse_failed_bad_manifest;
            case -107:
                return com.android.vending.R.string.install_failed_invalid_apk;
            case -106:
                return com.android.vending.R.string.install_parse_failed_bad_package_name;
            case -105:
                return com.android.vending.R.string.install_parse_failed_bad_certificates;
            case -104:
                return com.android.vending.R.string.install_parse_failed_mismatched_certificates;
            case -103:
                return com.android.vending.R.string.install_parse_failed_bad_certificates;
            case -101:
                return com.android.vending.R.string.install_parse_failed_bad_manifest;
            case -100:
                return com.android.vending.R.string.install_failed_invalid_apk;
            case -23:
                return com.android.vending.R.string.install_failed_package_changed;
            case -22:
                return com.android.vending.R.string.install_failed_verification_failure;
            case -21:
                return com.android.vending.R.string.install_failed_verification_timeout;
            case -20:
            case -19:
                return com.android.vending.R.string.install_invalid_install_location;
            case -18:
                return com.android.vending.R.string.install_failed_container_error;
            case -17:
                return com.android.vending.R.string.install_failed_missing_feature;
            case -16:
                return com.android.vending.R.string.install_failed_cpu_abi_incompatible;
            case -14:
                return com.android.vending.R.string.install_failed_newer_sdk;
            case -13:
                return com.android.vending.R.string.install_failed_conflicting_provider;
            case -12:
                return com.android.vending.R.string.install_failed_older_sdk;
            case -11:
                return com.android.vending.R.string.install_failed_invalid_apk;
            case -10:
                return com.android.vending.R.string.install_failed_replace_couldnt_delete;
            case -9:
                return com.android.vending.R.string.install_failed_missing_shared_library;
            case -8:
                return com.android.vending.R.string.install_failed_shared_user_incompatible;
            case -7:
                return com.android.vending.R.string.install_failed_update_incompatible;
            case -5:
            case -1:
                return com.android.vending.R.string.install_failed_already_exists;
            case -4:
                return com.android.vending.R.string.install_failed_insufficient_storage;
            case -3:
                return com.android.vending.R.string.install_failed_invalid_uri;
            case -2:
                return com.android.vending.R.string.install_failed_invalid_apk;
            default:
                return -1;
        }
    }

    public void addAppShortcut() {
        Context context = FinskyApp.get();
        Object[] objArr;
        if (new AppActionAnalyzer(this.packageName, FinskyApp.get().getAppStates(), FinskyApp.get().getLibraries()).isLaunchable) {
            PackageManager pm = context.getPackageManager();
            try {
                ApplicationInfo ai = pm.getApplicationInfo(this.packageName, 0);
                Resources res = pm.getResourcesForApplication(ai);
                String iconResourceName = res.getResourceName(ai.icon);
                Intent launchIntent = pm.getLaunchIntentForPackage(this.packageName);
                int labelResourceId = pm.getActivityInfo(launchIntent.getComponent(), 0).labelRes;
                CharSequence shortcutName = labelResourceId != 0 ? res.getString(labelResourceId) : pm.getApplicationLabel(ai);
                Intent installShortcut = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
                installShortcut.putExtra("android.intent.extra.shortcut.NAME", shortcutName);
                installShortcut.putExtra("android.intent.extra.shortcut.INTENT", launchIntent);
                ShortcutIconResource icon = new ShortcutIconResource();
                icon.packageName = this.packageName;
                icon.resourceName = iconResourceName;
                installShortcut.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", icon);
                installShortcut.putExtra("duplicate", false);
                context.sendBroadcast(installShortcut);
                objArr = new Object[1];
                objArr[0] = this.packageName;
                FinskyLog.d("Requested shortcut for %s", objArr);
                return;
            } catch (NotFoundException e) {
                objArr = new Object[1];
                objArr[0] = this.packageName;
                FinskyLog.w("Unable to load resources for %s", objArr);
                return;
            } catch (Exception e2) {
                objArr = new Object[1];
                objArr[0] = this.packageName;
                FinskyLog.wtf(e2, "Unable to add shortcut for %s", objArr);
                return;
            }
        }
        objArr = new Object[1];
        objArr[0] = this.packageName;
        FinskyLog.d("Skip shortcut for non-launchable %s", objArr);
    }

    private void cleanup(AppState appState) {
        int installedVersion = -1;
        if (appState.packageManagerState != null) {
            installedVersion = appState.packageManagerState.installedVersion;
        }
        if (installedVersion != appState.installerData.getDesiredVersion()) {
            cancelCleanup(appState);
            notifyListeners(InstallerPackageEvent.INSTALL_ERROR, 910);
            return;
        }
        if (!(this.mObbMain == null || this.mObbMain.getState() == 5)) {
            this.mObbMain.syncStateWithStorage();
            if (this.mObbMain.getState() != 3) {
                FinskyLog.e("Lost main obb file for %s", this.packageName);
                cancelCleanup(appState);
                notifyListeners(InstallerPackageEvent.INSTALL_ERROR, 911);
                showDownloadNotification(911, null);
                return;
            }
        }
        if (!(this.mObbPatch == null || this.mObbPatch.getState() == 5)) {
            this.mObbPatch.syncStateWithStorage();
            if (this.mObbPatch.getState() != 3) {
                FinskyLog.e("Lost patch obb file for %s", this.packageName);
                cancelCleanup(appState);
                notifyListeners(InstallerPackageEvent.INSTALL_ERROR, 912);
                showDownloadNotification(912, null);
                return;
            }
        }
        cleanObbDirectory(this.mObbMain, this.mObbPatch, this.packageName);
        cleanExternalStorage();
        setInstallerState(80, (String) null);
        notifyListeners(InstallerPackageEvent.INSTALLED, 0);
        if (this.mIsUpdate) {
            this.mInstallerDataStore.setLastUpdateTimestampMs(this.packageName, System.currentTimeMillis());
        }
        InstallerData installerData = appState.installerData;
        checkForEmptyTitle("cleanup", this.packageName, installerData.getTitle(), installerData);
        if (this.mShowCompletionNotifications) {
            this.mNotifier.showSuccessfulInstallMessage(installerData.getTitle(), this.packageName, installerData.getContinueUrl(), this.mIsUpdate);
        } else {
            this.mNotifier.hideInstallingMessage();
        }
        advanceState();
    }

    public static void cleanObbDirectory(Obb mainObb, Obb patchObb, String packageName) {
        if (mainObb != null || patchObb != null || !FinskyApp.get().getExperiments().isEnabled("cl:installer.preserve_old_obb_files")) {
            File mainObbFile = null;
            if (mainObb != null && mainObb.getState() == 3) {
                mainObbFile = mainObb.getFile();
            }
            File patchObbFile = null;
            if (patchObb != null && patchObb.getState() == 3) {
                patchObbFile = patchObb.getFile();
            }
            File[] targets = ObbFactory.getParentDirectory(packageName).listFiles();
            if (targets != null) {
                for (File target : targets) {
                    if ((mainObbFile == null || !mainObbFile.equals(target)) && (patchObbFile == null || !patchObbFile.equals(target))) {
                        FinskyLog.d("OBB cleanup %s", target);
                        target.delete();
                    }
                }
            }
        }
    }

    private void cleanExternalStorage() {
        File externalFilesDir = FinskyApp.get().getExternalFilesDir(null);
        if (externalFilesDir != null) {
            File[] files = externalFilesDir.listFiles();
            if (files != null) {
                for (File file : files) {
                    file.delete();
                }
            }
        }
    }

    public void onStart(Download download) {
        int newState = -1;
        switch (this.mAppStates.getApp(download.getPackageName()).installerData.getInstallerState()) {
            case com.google.android.play.R.styleable.Toolbar_navigationIcon /*20*/:
            case com.google.android.play.R.styleable.Theme_actionModeCloseButtonStyle /*25*/:
                newState = 25;
                break;
            case com.google.android.play.R.styleable.Theme_actionModeCopyDrawable /*30*/:
            case com.google.android.play.R.styleable.Theme_actionModeWebSearchDrawable /*35*/:
                newState = 35;
                break;
            case com.google.android.play.R.styleable.Theme_dropdownListPreferredItemHeight /*40*/:
            case com.google.android.play.R.styleable.Theme_buttonBarStyle /*45*/:
                newState = 45;
                break;
        }
        if (newState >= 0) {
            setInstallerState(newState, download.getContentUri());
            notifyListeners(InstallerPackageEvent.DOWNLOADING, 0);
            return;
        }
        FinskyLog.e("Unexpected download start states for %s: %d %d", packageName, Integer.valueOf(installerData.getInstallerState()), Integer.valueOf(newState));
        cancel(false);
        notifyListeners(InstallerPackageEvent.DOWNLOAD_ERROR, 903);
        showDownloadNotification(903, null);
    }

    public void onComplete(Download download) {
        boolean internalIsObbMain;
        InstallerData installerData = this.mAppStates.getApp(this.packageName).installerData;
        int newState = -1;
        int errorCode = 904;
        boolean internalIsObb = download.isObb();
        if (!internalIsObb || download.getObb().isPatch()) {
            internalIsObbMain = false;
        } else {
            internalIsObbMain = true;
        }
        switch (installerData.getInstallerState()) {
            case com.google.android.play.R.styleable.Theme_actionModeCloseButtonStyle /*25*/:
                if (internalIsObb && internalIsObbMain) {
                    if (!this.mObbMain.finalizeTempFile()) {
                        FinskyLog.e("Can't finalize main obb file for %s", this.packageName);
                        errorCode = 911;
                        break;
                    }
                    newState = 30;
                    break;
                }
            case com.google.android.play.R.styleable.Theme_actionModeWebSearchDrawable /*35*/:
                if (internalIsObb && !internalIsObbMain) {
                    if (!this.mObbPatch.finalizeTempFile()) {
                        FinskyLog.e("Can't finalize patch obb file for %s", this.packageName);
                        errorCode = 912;
                        break;
                    }
                    newState = 40;
                    break;
                }
            case com.google.android.play.R.styleable.Theme_buttonBarStyle /*45*/:
                if (!internalIsObb) {
                    newState = 50;
                    break;
                }
                break;
        }
        if (newState >= 0) {
            setInstallerState(newState, download.getContentUri());
            advanceState();
            return;
        }
        FinskyLog.e("Unexpected download completion states for %s: %d %d %b %b", this.packageName, Integer.valueOf(installerData.getInstallerState()), Integer.valueOf(newState), Boolean.valueOf(internalIsObb), Boolean.valueOf(internalIsObbMain));
        cancel(false);
        notifyListeners(InstallerPackageEvent.DOWNLOAD_ERROR, errorCode);
        showDownloadNotification(errorCode, null);
    }

    public void onError(Download download, int httpStatus) {
        boolean restartableError;
        if (httpStatus == 420 || (httpStatus >= 500 && httpStatus <= 599)) {
            restartableError = true;
        } else {
            restartableError = false;
        }
        if (!restartableError || (!tryRestartWithInhibitFlag(4, 8) && !tryRestartWithInhibitFlag(512, 1024))) {
            cancel(false);
            if (httpStatus != 198) {
                showDownloadNotification(httpStatus, null);
            } else if (this.mShowErrorNotifications) {
                if (download.isObb()) {
                    this.mNotifier.showExternalStorageFull(this.mTitle, this.packageName);
                } else {
                    this.mNotifier.showInternalStorageFull(this.mTitle, this.packageName);
                }
            }
            notifyListeners(InstallerPackageEvent.DOWNLOAD_ERROR, httpStatus);
        }
    }

    public void onProgress(Download download, DownloadProgress progress) {
        if (!download.isObb()) {
            this.mApkCompleted = progress.bytesCompleted;
            if (progress.bytesCompleted > 0 && this.mAppStates.getInstallerDataStore().get(this.packageName).getFirstDownloadMs() == 0) {
                this.mInstallerDataStore.setFirstDownloadMs(this.packageName, System.currentTimeMillis());
            }
        } else if (download.getObb().isPatch()) {
            this.mObbPatchCompleted = progress.bytesCompleted;
        } else {
            this.mObbMainCompleted = progress.bytesCompleted;
        }
        this.mDownloadStatus = progress.statusCode;
        notifyListeners(InstallerPackageEvent.DOWNLOADING, 0);
        this.mPackageInstaller.reportProgress(this.packageName, (this.mApkCompleted + this.mObbMainCompleted) + this.mObbPatchCompleted, this.mTotalSize);
    }

    private void notifyListeners(InstallerPackageEvent event, int statusCode) {
        this.mInstaller.notifyListeners(this.packageName, event, statusCode);
    }

    private void setInstallerState(int newState, String contentUri) {
        this.mInstallerDataStore.setInstallerState(this.packageName, newState, contentUri);
    }

    private void setInstallerState(int newState, Uri contentUri) {
        setInstallerState(newState, contentUri != null ? contentUri.toString() : null);
    }

    public String toString() {
        return this.packageName;
    }

    private boolean canDownloadApkToExternalStorage(InstallerData installerData) {
        int flags = installerData.getFlags();
        if ((65536 & flags) != 0) {
            return false;
        }
        if ((flags & 4096) != 0) {
            return true;
        }
        if ((flags & 8192) != 0) {
            return false;
        }
        long downloadSize = installerData.getDeliveryData().downloadSize;
        long minDownloadSizeForExternal = ((Long) G.downloadExternalFileSizeMinBytes.get()).longValue();
        if (FinskyApp.get().getExperiments(installerData.getAccountName()).isEnabled("cl:installer.force_through_sdcard")) {
            minDownloadSizeForExternal = 0;
        }
        if (downloadSize < minDownloadSizeForExternal) {
            return false;
        }
        long partitionAvailableBytes = Storage.externalStorageAvailableSpace();
        if (partitionAvailableBytes <= 0) {
            return false;
        }
        if (partitionAvailableBytes - ((Long) G.downloadExternalFreeSpaceThresholdBytes.get()).longValue() < (((long) ((Integer) G.downloadExternalFreeSpaceFactor.get()).intValue()) * downloadSize) / 100) {
            return false;
        }
        return true;
    }

    private boolean canDownloadPatch(InstallerData installerData) {
        int flags = installerData.getFlags();
        String packageName = installerData.getPackageName();
        AndroidAppDeliveryData deliveryData = installerData.getDeliveryData();
        if ((flags & 4) != 0) {
            if (deliveryData.patchData != null) {
                return true;
            }
            FinskyLog.wtf("Missing patch for %s while is_patch set in %d", packageName, Integer.valueOf(flags));
            this.mInstallerDataStore.setFlags(packageName, flags & -5);
        }
        if ((flags & 8) != 0) {
            return false;
        }
        AndroidAppPatchData patchData = deliveryData.patchData;
        if (patchData == null) {
            return false;
        }
        PackageState packageState = this.mAppStates.getPackageStateRepository().get(packageName);
        if (packageState == null) {
            logPatchFailure(packageName, "no-base-app-installed", 0);
            return false;
        }
        int expectedVersion = patchData.baseVersionCode;
        int i = packageState.installedVersion;
        if (r0 != expectedVersion) {
            logPatchFailure(packageName, "wrong-base-app-installed", 0);
            Object[] objArr = new Object[3];
            objArr[0] = packageName;
            objArr[1] = Integer.valueOf(expectedVersion);
            objArr[2] = Integer.valueOf(packageState.installedVersion);
            FinskyLog.d("Cannot patch %s, need version %d but has %d", objArr);
            return false;
        }
        String[] returnSourceDir = new String[1];
        boolean isForwardLocked = isInstalledForwardLocked(packageName, returnSourceDir);
        String sourceDir = returnSourceDir[0];
        if (isForwardLocked) {
            logPatchFailure(packageName, "base-app-dirs-mismatch", 0);
            FinskyLog.d("Cannot patch %s, source dir is %s", packageName, sourceDir);
            return false;
        }
        if (Storage.dataPartitionAvailableSpace() < (((long) ((Integer) G.downloadPatchFreeSpaceFactor.get()).intValue()) * deliveryData.downloadSize) / 100) {
            logPatchFailure(packageName, "free-space", 0);
            FinskyLog.d("Cannot patch %s, need %d, free %d", packageName, Long.valueOf(needFree), Long.valueOf(freeOnData));
            return false;
        }
        File fromFile = new File(sourceDir);
        if (fromFile.exists()) {
            try {
                DigestResult digest = Sha1Util.secureHash(new FileInputStream(fromFile));
                if (patchData.baseSignature.equals(digest.hashBase64)) {
                    return true;
                }
                logPatchFailure(packageName, "base-file-signature", 0);
                objArr = new Object[3];
                objArr[1] = patchData.baseSignature;
                objArr[2] = digest.hashBase64;
                FinskyLog.d("Cannot patch %s, bad hash, expect %s actual %s", objArr);
                return false;
            } catch (FileNotFoundException e) {
                logPatchFailure(packageName, "base-file-FileNotFoundException", 0);
                FinskyLog.d("Cannot patch %s, FileNotFoundException, %s", packageName, fromFile);
                return false;
            } catch (IOException ioe) {
                logPatchFailure(packageName, "base-file-otherexception", 0);
                FinskyLog.d("Cannot patch %s, unexpected exception %s", packageName, ioe.getClass().getSimpleName());
                return false;
            }
        }
        logPatchFailure(packageName, "base-file-exists", 0);
        FinskyLog.d("Cannot patch %s, file does not exist %s", packageName, fromFile);
        return false;
    }

    boolean canDownloadGzippedApk(InstallerData installerData, long freeOnData) {
        int flags = installerData.getFlags();
        String packageName = installerData.getPackageName();
        AndroidAppDeliveryData deliveryData = installerData.getDeliveryData();
        if ((flags & 512) != 0) {
            if (deliveryData.gzippedDownloadUrl != null) {
                return true;
            }
            FinskyLog.wtf("Missing gzipped apk for %s while apk_is_gzipped set in %d", packageName, Integer.valueOf(flags));
            this.mInstallerDataStore.setFlags(packageName, flags & -513);
        }
        if ((flags & 1024) != 0) {
            return false;
        }
        if (TextUtils.isEmpty(deliveryData.gzippedDownloadUrl)) {
            return false;
        }
        if (freeOnData >= (110 * (deliveryData.downloadSize + deliveryData.gzippedDownloadSize)) / 100) {
            return true;
        }
        logCopyFailure(packageName, "gzip-free-space", 0);
        FinskyLog.d("Cannot use gzipped apk %s, need %d, free %d", packageName, Long.valueOf(needFree), Long.valueOf(freeOnData));
        return false;
    }

    private void logVerificationFailure(String packageName, String reason, int errorCode) {
        FinskyApp.get().getEventLogger().logBackgroundEvent(111, packageName, reason, errorCode, null, this.mLogAppData);
    }

    private void logCopyFailure(String packageName, String reason, int errorCode) {
        if (errorCode == 0) {
            errorCode = 963;
        }
        FinskyApp.get().getEventLogger().logBackgroundEvent(127, packageName, reason, errorCode, null, this.mLogAppData);
    }

    private void logPatchFailure(String packageName, String reason, int errorCode) {
        if (errorCode == 0) {
            errorCode = 917;
        }
        FinskyApp.get().getEventLogger().logBackgroundEvent(108, packageName, reason, errorCode, null, this.mLogAppData);
    }

    private int checkAndLogDigestError(String packageName, DigestResult digestResult, long expectByteCount, String expectSignature) {
        if (digestResult == null) {
            FinskyLog.e("No digestResult for %s", packageName);
            return 961;
        } else if (expectByteCount != digestResult.byteCount) {
            FinskyLog.e("Signature check of %s failed, size expected=%d actual=%d", packageName, Long.valueOf(expectByteCount), Long.valueOf(digestResult.byteCount));
            return 919;
        } else if (expectSignature.equals(digestResult.hashBase64)) {
            return 0;
        } else {
            FinskyLog.e("Signature check of %s failed, hash expected=%s actual=%s", packageName, expectSignature, digestResult.hashBase64);
            return 960;
        }
    }

    private void startVerifyDownload(AppState appState) {
        final InstallerData installerData = appState.installerData;
        final Uri downloadUri = Uri.parse(installerData.getDownloadUri());
        setInstallerState(57, downloadUri);
        FinskyLog.d("Prepare to verify %s from %s", this.packageName, downloadUriString);
        Utils.executeMultiThreaded(new AsyncTask<Void, Void, DigestResult>() {
            protected DigestResult doInBackground(Void... params) {
                DigestResult digestResult = null;
                String packageName = installerData.getPackageName();
                try {
                    try {
                        digestResult = Sha1Util.secureHash(FinskyApp.get().getContentResolver().openInputStream(downloadUri));
                    } catch (IOException ioe) {
                        FinskyLog.w("IOException while copying %s: %s", packageName, ioe);
                    }
                } catch (FileNotFoundException e) {
                    FinskyLog.w("FileNotFoundException for %s: %s", packageName, downloadUri);
                }
                return digestResult;
            }

            protected void onPostExecute(DigestResult digestResult) {
                int errorCode = InstallerTask.this.checkAndLogDigestError(InstallerTask.this.packageName, digestResult, installerData.getDeliveryData().downloadSize, installerData.getDeliveryData().signature);
                if (errorCode != 0) {
                    InstallerTask.this.mDownloadQueue.release(downloadUri);
                    FinskyLog.e("Error while verifying download for %s", InstallerTask.this.packageName);
                    cancel(false);
                    InstallerTask.this.logVerificationFailure(InstallerTask.this.packageName, null, errorCode);
                    InstallerTask.this.notifyListeners(InstallerPackageEvent.INSTALL_ERROR, errorCode);
                    InstallerTask.this.showDownloadNotification(errorCode, null);
                    return;
                }
                InstallerTask.this.mPackageInstaller.setInstallUri(InstallerTask.this.packageName, downloadUri);
                InstallerTask.this.setInstallerState(60, downloadUri);
                InstallerTask.this.advanceState();
            }
        }, new Void[0]);
    }

    private boolean startCopyFromDownload(AppState appState) {
        final InstallerData installerData = appState.installerData;
        int flags = installerData.getFlags();
        final boolean isGzipped = (flags & 512) != 0;
        final boolean isExternal = (flags & 4096) != 0;
        boolean isRequiredByPackageInstaller = this.mPackageInstaller.requireCopy(isExternal);
        if (!isGzipped && !isRequiredByPackageInstaller) {
            return false;
        }
        final Uri downloadUri = Uri.parse(installerData.getDownloadUri());
        setInstallerState(52, downloadUri);
        final long expectedSize = installerData.getDeliveryData().downloadSize;
        FinskyLog.d("Prepare to copy %s from %s (expect %d bytes)", this.packageName, downloadUriString, Long.valueOf(expectedSize));
        Utils.executeMultiThreaded(new AsyncTask<Void, Void, DigestResult>() {
            protected DigestResult doInBackground(Void... params) {
                String packageName = installerData.getPackageName();
                InputStream inputStream = null;
                OutputStream outputStream = null;
                try {
                    inputStream = FinskyApp.get().getContentResolver().openInputStream(downloadUri);
                    if (isGzipped) {
                        try {
                            inputStream = new GZIPInputStream(new BufferedInputStream(inputStream));
                        } catch (IOException e) {
                            InstallerTask.this.logCopyFailure(packageName, "gzip-IOException", 0);
                            FinskyLog.w("IOException %s", e.getMessage());
                            Utils.safeClose(inputStream);
                            Utils.safeClose(outputStream);
                            return null;
                        }
                    }
                    try {
                        outputStream = InstallerTask.this.mPackageInstaller.getStream(packageName, packageName, expectedSize);
                        DigestResult digestResult = null;
                        try {
                            DigestStream digestOutputStream = new DigestStream(outputStream);
                            Utils.copy(inputStream, digestOutputStream);
                            digestResult = digestOutputStream.getDigest();
                            try {
                                InstallerTask.this.mPackageInstaller.finishStream(outputStream);
                                return digestResult;
                            } catch (IOException ioe) {
                                InstallerTask.this.logCopyFailure(packageName, "finish-IOException", 0);
                                FinskyLog.w("IOException while finishing %s: %s", packageName, ioe);
                                Utils.safeClose(inputStream);
                                Utils.safeClose(outputStream);
                                return null;
                            }
                        } catch (IOException ioe2) {
                            InstallerTask.this.logCopyFailure(packageName, "copy-IOException", 0);
                            FinskyLog.w("IOException while copying %s: %s", packageName, ioe2);
                            Utils.safeClose(inputStream);
                            Utils.safeClose(outputStream);
                            return null;
                        }
                    } catch (IOException ioe22) {
                        InstallerTask.this.logCopyFailure(packageName, "open-IOException", 0);
                        FinskyLog.w("IOException while copying %s: %s", packageName, ioe22);
                        Utils.safeClose(inputStream);
                        Utils.safeClose(outputStream);
                        return null;
                    }
                } catch (FileNotFoundException e2) {
                    InstallerTask.this.logCopyFailure(packageName, "source-FileNotFoundException", 0);
                    FinskyLog.w("FileNotFoundException %s", downloadUri);
                    return null;
                } finally {
                    Utils.safeClose(inputStream);
                    Utils.safeClose(outputStream);
                }
            }

            protected void onPostExecute(DigestResult digestResult) {
                int verificationError;
                InstallerTask.this.mDownloadQueue.release(downloadUri);
                if (digestResult != null) {
                    verificationError = InstallerTask.this.checkAndLogDigestError(InstallerTask.this.packageName, digestResult, installerData.getDeliveryData().downloadSize, installerData.getDeliveryData().signature);
                    if (verificationError != 0) {
                        InstallerTask.this.logCopyFailure(InstallerTask.this.packageName, "copy-verification", verificationError);
                    }
                } else {
                    verificationError = 963;
                }
                if (verificationError != 0) {
                    InstallerTask.this.cleanExternalStorage();
                    if (isGzipped) {
                        if (InstallerTask.this.tryRestartWithInhibitFlag(512, 1024)) {
                            return;
                        }
                    } else if (isExternal && InstallerTask.this.tryRestartWithInhibitFlag(4096, 8192)) {
                        return;
                    }
                    FinskyLog.e("Error while copying download for %s. isExternal=%b isGzip=%b", InstallerTask.this.packageName, Boolean.valueOf(isGzipped), Boolean.valueOf(isExternal));
                    cancel(false);
                    InstallerTask.this.notifyListeners(InstallerPackageEvent.INSTALL_ERROR, verificationError);
                    InstallerTask.this.showDownloadNotification(verificationError, null);
                    return;
                }
                FinskyApp.get().getEventLogger().logBackgroundEvent(127, InstallerTask.this.packageName, null, 0, null, InstallerTask.this.mLogAppData);
                FinskyLog.d("Successfully copied APK to update %s", InstallerTask.this.packageName);
                InstallerTask.this.setInstallerState(60, (String) null);
                InstallerTask.this.advanceState();
            }
        }, new Void[0]);
        return true;
    }

    private boolean startApplyingPatch(AppState appState) {
        final InstallerData installerData = appState.installerData;
        if ((installerData.getFlags() & 4) == 0) {
            return false;
        }
        final String downloadUriString = installerData.getDownloadUri();
        final Uri downloadUri = Uri.parse(downloadUriString);
        setInstallerState(55, downloadUri);
        FinskyLog.d("Prepare to patch %s from %s", this.packageName, downloadUriString);
        final long expectedSize = installerData.getDeliveryData().downloadSize;
        Utils.executeMultiThreaded(new AsyncTask<Void, Void, DigestResult>() {
            protected DigestResult doInBackground(Void... params) {
                Throwable th;
                Object[] objArr;
                String packageName = installerData.getPackageName();
                RandomAccessFile inputFile = null;
                InputStream patchStream = null;
                OutputStream outputStream = null;
                try {
                    Context appContext = FinskyApp.get();
                    ContentResolver resolver = appContext.getContentResolver();
                    RandomAccessFile inputFile2 = new RandomAccessFile(new File(appContext.getPackageManager().getApplicationInfo(packageName, 0).sourceDir), "r");
                    try {
                        patchStream = InstallerTask.this.getPatch(appContext, downloadUri, installerData);
                        if (patchStream == null) {
                            Utils.safeClose(inputFile2);
                            Utils.safeClose(patchStream);
                            Utils.safeClose(outputStream);
                            inputFile = inputFile2;
                            return null;
                        }
                        outputStream = InstallerTask.this.mPackageInstaller.getStream(packageName, packageName, expectedSize);
                        long maxOutputLength = installerData.getDeliveryData().downloadSize;
                        try {
                            DigestStream digestOutputStream = new DigestStream(outputStream);
                            Gdiff.patch(inputFile2, patchStream, digestOutputStream, maxOutputLength);
                            DigestResult digestResult = digestOutputStream.getDigest();
                            try {
                                InstallerTask.this.mPackageInstaller.finishStream(outputStream);
                                Utils.safeClose(inputFile2);
                                Utils.safeClose(patchStream);
                                Utils.safeClose(outputStream);
                                inputFile = inputFile2;
                                return digestResult;
                            } catch (IOException ioe) {
                                InstallerTask.this.logCopyFailure(packageName, "finish-IOException", 0);
                                FinskyLog.w("IOException while finishing %s: %s", packageName, ioe);
                                Utils.safeClose(inputFile2);
                                Utils.safeClose(patchStream);
                                Utils.safeClose(outputStream);
                                inputFile = inputFile2;
                                return null;
                            }
                        } catch (FileFormatException e) {
                            InstallerTask.this.logPatchFailure(packageName, "gdiff-FileFormatException", 0);
                            String contentType = resolver.getType(Uri.parse(downloadUriString.toString().replaceFirst("my_downloads", "public_downloads")));
                            FinskyLog.w("Patch %s (content-type '%s') is not Gdiff", packageName, contentType);
                            Utils.safeClose(inputFile2);
                            Utils.safeClose(patchStream);
                            Utils.safeClose(outputStream);
                            inputFile = inputFile2;
                            return null;
                        } catch (IOException e2) {
                            InstallerTask.this.logPatchFailure(packageName, "gdiff-IOException", 0);
                            FinskyLog.w("Patch %s failed with exception ", packageName, e2);
                            Utils.safeClose(inputFile2);
                            Utils.safeClose(patchStream);
                            Utils.safeClose(outputStream);
                            inputFile = inputFile2;
                            return null;
                        } catch (Exception e3) {
                            InstallerTask.this.logPatchFailure(packageName, "gdiff-Exception", 0);
                            FinskyLog.w("Patch %s failed with exception ", packageName, e3);
                            Utils.safeClose(inputFile2);
                            Utils.safeClose(patchStream);
                            Utils.safeClose(outputStream);
                            inputFile = inputFile2;
                            return null;
                        }
                    } catch (IOException ioe2) {
                        InstallerTask.this.logCopyFailure(packageName, "patch-IOException", 0);
                        FinskyLog.w("IOException while patching %s: %s", packageName, ioe2);
                        Utils.safeClose(inputFile2);
                        Utils.safeClose(patchStream);
                        Utils.safeClose(outputStream);
                        inputFile = inputFile2;
                        return null;
                    } catch (Throwable th2) {
                        th = th2;
                        inputFile = inputFile2;
                        Utils.safeClose(inputFile);
                        Utils.safeClose(patchStream);
                        Utils.safeClose(outputStream);
                        throw th;
                    }
                } catch (NameNotFoundException e4) {
                    InstallerTask.this.logPatchFailure(packageName, "source-NameNotFoundException", 0);
                    objArr = new Object[1];
                    objArr[0] = downloadUri;
                    FinskyLog.w("NameNotFoundException %s", objArr);
                    Utils.safeClose(null);
                    Utils.safeClose(patchStream);
                    Utils.safeClose(outputStream);
                    return null;
                } catch (FileNotFoundException e5) {
                    InstallerTask.this.logPatchFailure(packageName, "source-FileNotFoundException", 0);
                    objArr = new Object[1];
                    objArr[0] = downloadUri;
                    FinskyLog.w("FileNotFoundException %s", objArr);
                    Utils.safeClose(null);
                    Utils.safeClose(patchStream);
                    Utils.safeClose(outputStream);
                    return null;
                } catch (Throwable th3) {
                    th = th3;
                    Utils.safeClose(inputFile);
                    Utils.safeClose(patchStream);
                    Utils.safeClose(outputStream);
                    throw th;
                }
            }

            protected void onPostExecute(DigestResult digestResult) {
                InstallerTask.this.mDownloadQueue.release(downloadUri);
                int verificationError = 0;
                if (digestResult != null) {
                    verificationError = InstallerTask.this.checkAndLogDigestError(InstallerTask.this.packageName, digestResult, installerData.getDeliveryData().downloadSize, installerData.getDeliveryData().signature);
                    if (verificationError != 0) {
                        InstallerTask.this.logPatchFailure(InstallerTask.this.packageName, "gdiff-verification", verificationError);
                    }
                }
                if (digestResult == null || verificationError != 0) {
                    InstallerTask.this.cleanExternalStorage();
                    InstallerTask.this.tryRestartWithInhibitFlag(4, 8);
                    return;
                }
                FinskyApp.get().getEventLogger().logBackgroundEvent(108, InstallerTask.this.packageName, null, 0, null, InstallerTask.this.mLogAppData);
                FinskyLog.d("Successfully applied patch to update %s", InstallerTask.this.packageName);
                InstallerTask.this.setInstallerState(60, (String) null);
                InstallerTask.this.advanceState();
            }
        }, new Void[0]);
        return true;
    }

    InputStream getPatch(Context appContext, Uri downloadUri, InstallerData installerData) {
        try {
            InputStream openInputStream = appContext.getContentResolver().openInputStream(downloadUri);
            if (isGzippedPatch(installerData)) {
                return new GZIPInputStream(new BufferedInputStream(openInputStream));
            }
            return openInputStream;
        } catch (FileNotFoundException e1) {
            FinskyLog.w("FileNotFoundException %s %s", downloadUri, e1.getMessage());
            logPatchFailure(this.packageName, "patch-FileNotFoundException", 0);
            return null;
        } catch (IOException e) {
            FinskyLog.w("IOException %s %s", downloadUri, e.getMessage());
            logPatchFailure(this.packageName, "patch-IOException", 0);
            return null;
        }
    }

    static boolean isGzippedPatch(InstallerData data) {
        if (data == null) {
            return false;
        }
        AndroidAppDeliveryData deliveryData = data.getDeliveryData();
        if (deliveryData == null || deliveryData.patchData == null || deliveryData.patchData.patchFormat != 2) {
            return false;
        }
        return true;
    }

    private boolean isInstalledForwardLocked(String packageName, String[] returnSourceDir) {
        try {
            ApplicationInfo applicationInfo = FinskyApp.get().getPackageManager().getApplicationInfo(packageName, 0);
            String sourceDir = applicationInfo.sourceDir;
            String publicSourceDir = applicationInfo.publicSourceDir;
            if (returnSourceDir != null) {
                returnSourceDir[0] = sourceDir;
            }
            if (TextUtils.isEmpty(sourceDir) || !sourceDir.equals(publicSourceDir)) {
                return true;
            }
            return false;
        } catch (NameNotFoundException e) {
            return false;
        }
    }

    private boolean tryRestartWithInhibitFlag(int testAndClearFlag, int inhibitFlag) {
        boolean wasFlagSet = true;
        FinskyLog.d("Retry download of %s (inhibit %d)", this.packageName, Integer.valueOf(inhibitFlag));
        InstallerDataStore installerDataStore = this.mInstallerDataStore;
        int flags = installerDataStore.get(this.packageName).getFlags();
        if ((flags & testAndClearFlag) == 0) {
            wasFlagSet = false;
        }
        if (wasFlagSet) {
            installerDataStore.setFlags(this.packageName, (flags | inhibitFlag) & (testAndClearFlag ^ -1));
            if ((testAndClearFlag & 4) != 0) {
                this.mLogAppData.downloadPatch = false;
                this.mLogAppData.hasDownloadPatch = false;
                this.mLogAppData.downloadGzip = false;
                this.mLogAppData.hasDownloadGzip = false;
            }
            if ((testAndClearFlag & 512) != 0) {
                this.mLogAppData.downloadGzip = false;
                this.mLogAppData.hasDownloadGzip = false;
            }
            if ((testAndClearFlag & 4096) != 0) {
                this.mLogAppData.downloadExternal = false;
                this.mLogAppData.hasDownloadExternal = false;
            }
            setInstallerState(40, (String) null);
            advanceState();
        }
        return wasFlagSet;
    }
}
