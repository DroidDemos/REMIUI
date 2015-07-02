package com.google.android.finsky.setup;

import android.accounts.Account;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.SystemClock;
import com.android.vending.setup.IPlaySetupService.Stub;
import com.android.volley.toolbox.RequestFuture;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.analytics.PlayStore.PlayStoreBackgroundActionEvent;
import com.google.android.finsky.api.AccountHandler;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.api.DfeApiConfig;
import com.google.android.finsky.config.G;
import com.google.android.finsky.installer.InstallerListener;
import com.google.android.finsky.installer.InstallerListener.InstallerPackageEvent;
import com.google.android.finsky.protos.DeviceConfigurationProto;
import com.google.android.finsky.protos.EarlyUpdate.EarlyDocumentInfo;
import com.google.android.finsky.protos.EarlyUpdate.EarlyUpdateResponse;
import com.google.android.finsky.protos.Restore.BackupDeviceInfo;
import com.google.android.finsky.protos.Restore.GetBackupDeviceChoicesResponse;
import com.google.android.finsky.receivers.Installer;
import com.google.android.finsky.services.RestoreService;
import com.google.android.finsky.services.VpaService;
import com.google.android.finsky.utils.ConsistencyTokenHelper;
import com.google.android.finsky.utils.DeviceConfigurationHelper;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.FinskyPreferences;
import com.google.android.finsky.utils.Utils;
import com.google.android.volley.DisplayMessageError;
import com.google.android.wallet.instrumentmanager.R;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class PlaySetupService extends Service implements InstallerListener {
    private Semaphore mEarlyUpdateLock;
    private String mEarlyUpdatePackage;
    private boolean mEarlyUpdatePackageIsCritical;
    private Bundle mLastEarlyUpdateResponse;
    private boolean mListenerAdded;
    private int mServiceStartId;
    private int mStartupRefCount;

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
                $SwitchMap$com$google$android$finsky$installer$InstallerListener$InstallerPackageEvent[InstallerPackageEvent.DOWNLOAD_CANCELLED.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$google$android$finsky$installer$InstallerListener$InstallerPackageEvent[InstallerPackageEvent.DOWNLOAD_ERROR.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$google$android$finsky$installer$InstallerListener$InstallerPackageEvent[InstallerPackageEvent.INSTALL_ERROR.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$com$google$android$finsky$installer$InstallerListener$InstallerPackageEvent[InstallerPackageEvent.INSTALLED.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$com$google$android$finsky$installer$InstallerListener$InstallerPackageEvent[InstallerPackageEvent.UNINSTALLING.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                $SwitchMap$com$google$android$finsky$installer$InstallerListener$InstallerPackageEvent[InstallerPackageEvent.UNINSTALLED.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
        }
    }

    public PlaySetupService() {
        this.mStartupRefCount = 0;
        this.mEarlyUpdateLock = null;
    }

    public IBinder onBind(Intent intent) {
        return new Stub() {
            public Bundle getEarlyUpdate() {
                return PlaySetupService.this.doGetEarlyUpdate();
            }

            public void startEarlyUpdate() {
                PlaySetupService.this.doStartEarlyUpdate();
            }

            public boolean cancelEarlyUpdate() {
                return PlaySetupService.this.doCancelEarlyUpdate();
            }

            public Bundle getRestoreFlow(String accountName) {
                return PlaySetupService.this.doGetRestoreFlow(accountName);
            }

            public void startVpa() {
                PlaySetupService.this.doStartVpa();
            }

            public Bundle getFinalHoldFlow() {
                return PlaySetupService.this.doGetFinalHoldFlow();
            }

            public void startDownloads() {
                PlaySetupService.this.doStartDownloads();
            }
        };
    }

    private void earlyUpdatePackageCommand(String packageName, int versionCode, String title, boolean critical) {
        Context appContext = getApplicationContext();
        Intent intent = new Intent(appContext, PlaySetupService.class);
        intent.putExtra("package", packageName);
        intent.putExtra("version_code", versionCode);
        intent.putExtra("title", title);
        intent.putExtra("critical", critical);
        intent.setData(Uri.parse("playsetupservice://earlyupdatepackage"));
        appContext.startService(intent);
    }

    public int onStartCommand(final Intent intent, int flags, int startId) {
        this.mServiceStartId = startId;
        this.mStartupRefCount++;
        FinskyApp.get().getAppStates().load(new Runnable() {
            public void run() {
                PlaySetupService.this.mStartupRefCount = PlaySetupService.this.mStartupRefCount - 1;
                if (!PlaySetupService.this.handleIntent(intent)) {
                    PlaySetupService.this.stopServiceIfDone();
                }
            }
        });
        return 3;
    }

    private void stopServiceIfDone() {
        Utils.ensureOnMainThread();
        if (this.mStartupRefCount == 0 && this.mEarlyUpdatePackage == null) {
            if (this.mListenerAdded) {
                FinskyApp.get().getInstaller().removeListener(this);
                this.mListenerAdded = false;
            }
            stopSelf(this.mServiceStartId);
        }
    }

    public void onCreate() {
        this.mEarlyUpdateLock = new Semaphore(1);
    }

    public void onDestroy() {
        if (this.mListenerAdded) {
            FinskyApp.get().getInstaller().removeListener(this);
            this.mListenerAdded = false;
        }
        this.mEarlyUpdateLock = null;
    }

    private boolean handleIntent(Intent intent) {
        if (intent.hasExtra("package") && intent.hasExtra("version_code") && intent.hasExtra("title")) {
            return handleEarlyUpdatePackageCommand(intent.getStringExtra("package"), intent.getIntExtra("version_code", 0), intent.getStringExtra("title"), intent.getBooleanExtra("critical", false));
        }
        FinskyLog.wtf("Unknown command intent %s", intent);
        return false;
    }

    private Bundle doGetEarlyUpdate() {
        if (!((Boolean) G.setupWizardEarlyUpdateEnable.get()).booleanValue()) {
            return null;
        }
        Utils.ensureNotOnMainThread();
        if (((Long) DfeApiConfig.androidId.get()).longValue() == 0) {
            FinskyLog.w("Unexpected android-id = 0", new Object[0]);
        }
        try {
            this.mEarlyUpdateLock.tryAcquire(((Long) G.setupWizardEarlyUpdateDeadlockMaxMs.get()).longValue(), TimeUnit.MILLISECONDS);
            this.mEarlyUpdateLock.release();
        } catch (InterruptedException e) {
            FinskyLog.wtf("Deadlocked - race condition longer than expected?", new Object[0]);
        }
        synchronized (this) {
            this.mLastEarlyUpdateResponse = null;
        }
        DeviceConfigurationProto deviceConfiguration = null;
        try {
            deviceConfiguration = DeviceConfigurationHelper.getDeviceConfiguration();
        } catch (Exception e2) {
            FinskyLog.wtf(e2, "Exception while getting device configuration.", new Object[0]);
        }
        DfeApi dfeApi = FinskyApp.get().getDfeApiNonAuthenticated();
        RequestFuture<EarlyUpdateResponse> future = RequestFuture.newFuture();
        dfeApi.earlyUpdate(deviceConfiguration, future, future);
        try {
            EarlyUpdateResponse response = (EarlyUpdateResponse) future.get();
            Object[] objArr = new Object[1];
            objArr[0] = Integer.valueOf(response.earlyDocumentInfo.length);
            FinskyLog.d("Received EarlyUpdate with %d entries", objArr);
            int pendingPackageCount = 0;
            Bundle result = null;
            PackageManager packageManager = FinskyApp.get().getPackageManager();
            for (EarlyDocumentInfo documentInfo : response.earlyDocumentInfo) {
                String packageName = documentInfo.docid.backendDocid;
                if (!((Boolean) FinskyPreferences.earlyUpdateSkipPackage.get(packageName).get()).booleanValue()) {
                    int installedVersion = 0;
                    try {
                        installedVersion = packageManager.getPackageInfo(packageName, 0).versionCode;
                    } catch (NameNotFoundException e3) {
                    }
                    int i = documentInfo.versionCode;
                    if (installedVersion < r0) {
                        pendingPackageCount++;
                        if (result == null) {
                            result = new Bundle();
                            result.putString("package_name", packageName);
                            result.putInt("version_code", documentInfo.versionCode);
                            result.putString("title", documentInfo.title);
                            result.putBoolean("critical", documentInfo.critical);
                        }
                    }
                }
            }
            if (result != null) {
                result.putInt("package_count", pendingPackageCount);
            }
            synchronized (this) {
                this.mLastEarlyUpdateResponse = result;
            }
            return result;
        } catch (InterruptedException e4) {
            FinskyLog.e(e4, "Interrupted while loading EarlyUpdate", new Object[0]);
            return null;
        } catch (ExecutionException e5) {
            FinskyLog.e("Error while loading EarlyUpdate: %s", e5.getCause().getMessage());
            return null;
        }
    }

    private void doStartEarlyUpdate() {
        if (((Boolean) G.setupWizardEarlyUpdateEnable.get()).booleanValue()) {
            Bundle lastResponse;
            synchronized (this) {
                lastResponse = this.mLastEarlyUpdateResponse;
            }
            if (lastResponse == null) {
                FinskyLog.wtf("Started early-update when no earlier response given", new Object[0]);
                return;
            } else {
                earlyUpdatePackageCommand(lastResponse.getString("package_name"), lastResponse.getInt("version_code"), lastResponse.getString("title"), lastResponse.getBoolean("critical"));
                return;
            }
        }
        FinskyLog.wtf("Started early-update when disabled", new Object[0]);
    }

    private boolean handleEarlyUpdatePackageCommand(String packageName, int versionCode, String title, boolean critical) {
        if (this.mEarlyUpdatePackage != null) {
            FinskyLog.wtf("Received command to early-update %s while already handling %s", packageName, this.mEarlyUpdatePackage);
        }
        this.mEarlyUpdatePackage = packageName;
        this.mEarlyUpdatePackageIsCritical = critical;
        Installer installer = FinskyApp.get().getInstaller();
        if (!this.mListenerAdded) {
            installer.addListener(this);
            this.mListenerAdded = true;
        }
        installer.setMobileDataAllowed(packageName);
        installer.setVisibility(packageName, false, false, false);
        installer.setEarlyUpdate(packageName);
        installer.requestInstall(packageName, versionCode, null, title, false, "early-update", 1);
        return true;
    }

    private boolean doCancelEarlyUpdate() {
        if (((Boolean) G.setupWizardEarlyUpdateEnable.get()).booleanValue()) {
            FutureTask<Boolean> canceler = new FutureTask(new Callable<Boolean>() {
                public Boolean call() {
                    if (PlaySetupService.this.mEarlyUpdatePackage == null) {
                        return Boolean.valueOf(true);
                    }
                    Installer installer = FinskyApp.get().getInstaller();
                    installer.cancel(PlaySetupService.this.mEarlyUpdatePackage);
                    if (installer.getState(PlaySetupService.this.mEarlyUpdatePackage).isDownloadingOrInstalling()) {
                        return Boolean.valueOf(false);
                    }
                    return Boolean.valueOf(true);
                }
            });
            new Handler(getMainLooper()).post(canceler);
            try {
                return ((Boolean) canceler.get()).booleanValue();
            } catch (InterruptedException e) {
                FinskyLog.wtf(e, "Canceler interrupted", new Object[0]);
                return true;
            } catch (ExecutionException e2) {
                FinskyLog.wtf(e2, "Canceler crashed", new Object[0]);
                return true;
            }
        }
        FinskyLog.wtf("Canceled early-update when disabled", new Object[0]);
        return true;
    }

    public void onInstallPackageEvent(String packageName, InstallerPackageEvent event, int statusCode) {
        if (this.mEarlyUpdatePackage != null && this.mEarlyUpdatePackage.equals(packageName)) {
            FinskyLog.d("EarlyUpdate %s: %s", packageName, event);
            boolean stopService = false;
            boolean skipPackage = false;
            switch (AnonymousClass5.$SwitchMap$com$google$android$finsky$installer$InstallerListener$InstallerPackageEvent[event.ordinal()]) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    if (!this.mEarlyUpdateLock.tryAcquire()) {
                        FinskyLog.wtf("Couldn't acquire mutex for pending %s", packageName);
                        break;
                    }
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    stopService = true;
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    synchronized (this) {
                        if (!this.mEarlyUpdatePackageIsCritical) {
                            skipPackage = true;
                        }
                    }
                    stopService = true;
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    skipPackage = true;
                    stopService = true;
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                    stopService = true;
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                    FinskyLog.wtf("EarlyUpdate %s: unexpected %s", packageName, event);
                    break;
            }
            if (skipPackage) {
                FinskyPreferences.earlyUpdateSkipPackage.get(packageName).put(Boolean.valueOf(true));
            }
            if (stopService) {
                this.mEarlyUpdatePackage = null;
                this.mEarlyUpdateLock.release();
                stopServiceIfDone();
            }
        }
    }

    private Bundle doGetRestoreFlow(String accountName) {
        if (((Long) DfeApiConfig.androidId.get()).longValue() == 0) {
            FinskyLog.w("Unexpected android-id = 0", new Object[0]);
        }
        Account account = AccountHandler.findAccount(accountName, this);
        if (account == null) {
            FinskyLog.e("Received invalid account name: %s", FinskyLog.scrubPii(accountName));
            return null;
        }
        BackupDeviceInfo[] backupDevices = getBackupDevices(FinskyApp.get().getDfeApi(account.name));
        if (backupDevices == null || backupDevices.length == 0) {
            return null;
        }
        Intent restoreAppsIntent = SetupWizardRestoreAppsActivity.createIntent(account.name, backupDevices);
        Bundle result = new Bundle();
        result.putParcelable("available_restore_intent", restoreAppsIntent);
        return result;
    }

    private BackupDeviceInfo[] getBackupDevices(DfeApi dfeApi) {
        String consistencyToken = ConsistencyTokenHelper.getBlocking(FinskyApp.get());
        RequestFuture<GetBackupDeviceChoicesResponse> future = RequestFuture.newFuture();
        long startTimeMs = SystemClock.elapsedRealtime();
        dfeApi.getBackupDeviceChoices(consistencyToken, future, future);
        try {
            BackupDeviceInfo[] backupDeviceInfos = ((GetBackupDeviceChoicesResponse) future.get()).backupDeviceInfo;
            logBackgroundEvent(-1, null, dfeApi, startTimeMs);
            FinskyLog.d("getBackupDeviceChoices returned with %d devices", Integer.valueOf(backupDeviceInfos.length));
            return backupDeviceInfos;
        } catch (InterruptedException e) {
            FinskyLog.wtf("Unable to fetch backup devices, InterruptedException: %s", e.getMessage());
            logBackgroundEvent(1, e, dfeApi, startTimeMs);
            return null;
        } catch (ExecutionException e2) {
            Throwable volleyError = e2.getCause();
            String serverMessage = volleyError instanceof DisplayMessageError ? ((DisplayMessageError) volleyError).getDisplayErrorHtml() : null;
            FinskyLog.e("Unable to fetch backup devices: %s (%s)", volleyError, serverMessage);
            logBackgroundEvent(1, volleyError, dfeApi, startTimeMs);
            return null;
        }
    }

    private void logBackgroundEvent(int resultCode, Throwable exception, DfeApi dfeApi, long startTimeMs) {
        long latencyMs = SystemClock.elapsedRealtime() - startTimeMs;
        PlayStoreBackgroundActionEvent event = FinskyEventLog.obtainPlayStoreBackgroundActionEvent();
        event.type = 125;
        event.hasType = true;
        if (resultCode != -1) {
            event.errorCode = resultCode;
            event.hasErrorCode = true;
        }
        if (exception != null) {
            event.exceptionType = exception.getClass().getSimpleName();
            event.hasExceptionType = true;
        }
        if (startTimeMs != 0) {
            event.clientLatencyMs = latencyMs;
            event.hasClientLatencyMs = true;
        }
        FinskyApp.get().getEventLogger(dfeApi.getAccountName()).logBackgroundEvent(event);
    }

    private void doStartVpa() {
        if (((Long) DfeApiConfig.androidId.get()).longValue() == 0) {
            FinskyLog.w("Unexpected android-id = 0", new Object[0]);
        }
        FinskyLog.d("Starting VPA", new Object[0]);
        VpaService.startVpa();
    }

    private Bundle doGetFinalHoldFlow() {
        startDeferredInstalls();
        FinskyLog.d("Getting final hold flow", new Object[0]);
        Bundle result = new Bundle();
        if (VpaService.shouldHold() || RestoreService.shouldHold()) {
            result.putParcelable("final_hold_intent", SetupWizardFinalHoldActivity.createIntent());
        }
        return result;
    }

    private void doStartDownloads() {
        if (((Long) DfeApiConfig.androidId.get()).longValue() == 0) {
            FinskyLog.w("Unexpected android-id = 0", new Object[0]);
        }
        FinskyLog.d("Setup Wizard reports OK to begin downloading apps", new Object[0]);
        startDeferredInstalls();
        FinskyPreferences.setupWizardStartDownloads.put(Boolean.valueOf(true));
    }

    private void startDeferredInstalls() {
        new Handler(getMainLooper()).post(new Runnable() {
            public void run() {
                FinskyApp.get().getInstaller().startDeferredInstalls();
            }
        });
    }
}
