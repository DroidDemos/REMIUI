package com.google.android.finsky.receivers;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.text.TextUtils;
import com.android.vending.R;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.activities.AppActionAnalyzer;
import com.google.android.finsky.analytics.PlayStore.AppData;
import com.google.android.finsky.api.DfeUtils;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.appstate.AppStates;
import com.google.android.finsky.appstate.AppStates.AppState;
import com.google.android.finsky.appstate.InstallerDataStore;
import com.google.android.finsky.appstate.InstallerDataStore.InstallerData;
import com.google.android.finsky.appstate.InstallerDataStore.InstallerData.Builder;
import com.google.android.finsky.appstate.PackageStateRepository.PackageState;
import com.google.android.finsky.download.Download;
import com.google.android.finsky.download.DownloadProgress;
import com.google.android.finsky.download.DownloadQueue;
import com.google.android.finsky.download.DownloadQueueListener;
import com.google.android.finsky.installer.IMultiUserCoordinatorService;
import com.google.android.finsky.installer.IMultiUserCoordinatorService.Stub;
import com.google.android.finsky.installer.IMultiUserCoordinatorServiceListener;
import com.google.android.finsky.installer.InstallPolicies;
import com.google.android.finsky.installer.InstallerListener;
import com.google.android.finsky.installer.InstallerListener.InstallerPackageEvent;
import com.google.android.finsky.installer.MultiUserCoordinatorService;
import com.google.android.finsky.installer.PackageInstallerFacade;
import com.google.android.finsky.installer.PackageInstallerFactory;
import com.google.android.finsky.library.Libraries;
import com.google.android.finsky.receivers.Installer.InstallerProgressReport;
import com.google.android.finsky.receivers.Installer.InstallerState;
import com.google.android.finsky.receivers.PackageMonitorReceiver.PackageStatusListener;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.Lists;
import com.google.android.finsky.utils.NotificationManager;
import com.google.android.finsky.utils.Notifier;
import com.google.android.finsky.utils.Sets;
import com.google.android.finsky.utils.Users;
import com.google.android.finsky.utils.Utils;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class InstallerImpl implements DownloadQueueListener, Installer, PackageStatusListener {
    private static InstallerProgressReport PROGRESS_DOWNLOAD_PENDING;
    private static InstallerProgressReport PROGRESS_NOT_TRACKED;
    private static InstallerProgressReport PROGRESS_UNINSTALLING;
    private final AppStates mAppStates;
    private final Context mContext;
    private IMultiUserCoordinatorService mCoordinatorService;
    private final DownloadQueue mDownloadQueue;
    private final Handler mHandler;
    private final InstallPolicies mInstallPolicies;
    private final InstallerDataStore mInstallerDataStore;
    private InstallerTask mInstallerTask;
    private final Libraries mLibraries;
    IMultiUserCoordinatorServiceListener mListener;
    private final List<InstallerListener> mListeners;
    private final Notifier mNotifier;
    private final PackageInstallerFacade mPackageInstaller;
    private final PackageMonitorReceiver mPackageMonitorReceiver;
    private List<String> mPriorityPackages;
    private boolean mRunning;
    private RemoteServiceConnection mServiceConnection;
    private ArrayList<Runnable> mServiceConnectionCallbacks;
    private Set<String> mUninstallingPackages;
    private final Users mUsers;

    class RemoteServiceConnection implements ServiceConnection {
        RemoteServiceConnection() {
        }

        public void onServiceConnected(ComponentName className, IBinder boundService) {
            Utils.ensureOnMainThread();
            InstallerImpl.this.mCoordinatorService = Stub.asInterface(boundService);
            try {
                InstallerImpl.this.mCoordinatorService.registerListener(InstallerImpl.this.mListener);
                for (int i = 0; i < InstallerImpl.this.mServiceConnectionCallbacks.size(); i++) {
                    ((Runnable) InstallerImpl.this.mServiceConnectionCallbacks.get(i)).run();
                }
                InstallerImpl.this.mServiceConnectionCallbacks.clear();
            } catch (RemoteException e) {
                FinskyLog.w("Couldn't register listener *** received %s", e);
                InstallerImpl.this.mContext.unbindService(InstallerImpl.this.mServiceConnection);
                InstallerImpl.this.mCoordinatorService = null;
                InstallerImpl.this.mServiceConnectionCallbacks.clear();
                if (!InstallerImpl.this.mRunning) {
                    FinskyLog.w("Force-starting the installer after connection failure", new Object[0]);
                    InstallerImpl.this.mRunning = true;
                    InstallerImpl.this.kick(true);
                }
            }
        }

        public void onServiceDisconnected(ComponentName className) {
        }
    }

    public InstallerImpl(Context context, AppStates appStates, Libraries libraries, DownloadQueue downloadQueue, Notifier notifier, InstallPolicies installPolicies, PackageMonitorReceiver packageMonitorReceiver, Users users, PackageInstallerFacade packageInstaller) {
        this.mServiceConnection = null;
        this.mServiceConnectionCallbacks = Lists.newArrayList();
        this.mListener = new IMultiUserCoordinatorServiceListener.Stub() {
            public void packageAcquired(String packageName) {
            }

            public void packageReleased(final String packageName) {
                InstallerImpl.this.mHandler.post(new Runnable() {
                    public void run() {
                        InstallerImpl.this.mAppStates.getPackageStateRepository().invalidate(packageName);
                        InstallerImpl.this.kick(false);
                    }
                });
            }
        };
        this.mContext = context;
        this.mAppStates = appStates;
        this.mLibraries = libraries;
        this.mDownloadQueue = downloadQueue;
        this.mNotifier = notifier;
        this.mInstallPolicies = installPolicies;
        this.mPackageMonitorReceiver = packageMonitorReceiver;
        this.mUsers = users;
        this.mPackageInstaller = packageInstaller;
        this.mListeners = Lists.newArrayList();
        this.mInstallerDataStore = appStates.getInstallerDataStore();
        this.mHandler = new Handler(Looper.getMainLooper());
        this.mRunning = false;
        this.mUninstallingPackages = Sets.newHashSet();
        this.mPriorityPackages = Lists.newArrayList();
    }

    public void start(final Runnable afterStartedRunnable) {
        this.mNotifier.hideInstallingMessage();
        this.mDownloadQueue.addListener(this);
        this.mPackageMonitorReceiver.attach(this);
        new AsyncTask<Void, Void, List<DownloadProgress>>() {
            protected List<DownloadProgress> doInBackground(Void... params) {
                InstallerImpl.this.removeAllFilesFromCacheDir("patches");
                InstallerImpl.this.removeAllFilesFromCacheDir("copies");
                InstallerImpl.this.mAppStates.blockingLoad();
                return InstallerImpl.this.mDownloadQueue.getRunningDownloads();
            }

            protected void onPostExecute(final List<DownloadProgress> runningDownloads) {
                InstallerImpl.this.recoverPriorityList(InstallerImpl.this.mAppStates.getAppsToInstall());
                boolean immediateStart = true;
                if (runningDownloads != null) {
                    if (InstallerImpl.this.multiUserMode()) {
                        immediateStart = false;
                        InstallerImpl.this.bindToMultiUserCoordinator(new Runnable() {
                            public void run() {
                                InstallerImpl.this.recoverRunningDownloads(runningDownloads);
                                InstallerImpl.this.pruneSessions();
                                InstallerImpl.this.mRunning = true;
                                InstallerImpl.this.kick(true);
                                afterStartedRunnable.run();
                            }
                        });
                    } else {
                        InstallerImpl.this.recoverRunningDownloads(runningDownloads);
                    }
                }
                if (immediateStart) {
                    InstallerImpl.this.pruneSessions();
                    InstallerImpl.this.mRunning = true;
                    InstallerImpl.this.kick(true);
                    afterStartedRunnable.run();
                }
            }
        }.execute(new Void[0]);
    }

    private void removeAllFilesFromCacheDir(String dirName) {
        File cacheDirectory = new File(FinskyApp.get().getCacheDir(), dirName);
        if (cacheDirectory.exists()) {
            File[] files = cacheDirectory.listFiles();
            if (files != null && files.length != 0) {
                for (File file : files) {
                    file.delete();
                }
            }
        }
    }

    private void recoverPriorityList(List<AppState> appsToInstall) {
        for (AppState appState : appsToInstall) {
            int flags = appState.installerData.getFlags();
            if ((flags & 16384) != 0) {
                this.mPriorityPackages.add(0, appState.packageName);
            } else if ((32768 & flags) != 0) {
                this.mPriorityPackages.add(appState.packageName);
            }
        }
    }

    private void recoverRunningDownloads(List<DownloadProgress> runningDownloads) {
        boolean oneRecovered = false;
        for (DownloadProgress record : runningDownloads) {
            FinskyLog.d("Attempt recovery of %s %d", record.contentUri, Integer.valueOf(record.statusCode));
            if (oneRecovered || !recoverDownload(record.contentUri, record.statusCode)) {
                FinskyLog.d("Releasing %s %d", record.contentUri, Integer.valueOf(record.statusCode));
                this.mDownloadQueue.release(record.contentUri);
            } else {
                oneRecovered = true;
            }
        }
    }

    private void pruneSessions() {
        List<AppState> appsToInstall = this.mAppStates.getAppsToInstall();
        List<String> activePackages = Lists.newArrayList(appsToInstall.size());
        for (AppState appToInstall : appsToInstall) {
            activePackages.add(appToInstall.packageName);
        }
        this.mPackageInstaller.pruneSessions(activePackages);
    }

    public void promiseInstall(String packageName, long installSize, String title, Bitmap bitmap) {
        if (this.mPackageInstaller.hasSession(packageName)) {
            FinskyLog.w("Promising install for already-existing session for %s", packageName);
            this.mPackageInstaller.cancelSession(packageName);
        }
        this.mPackageInstaller.createSession(packageName, installSize, title, bitmap);
    }

    public void requestInstall(String docId, int packageVersion, String accountName, String title, boolean deferred, String reason, int priority) {
        InstallerTask.checkForEmptyTitle("requestInstall", docId, title, null);
        if (getState(docId) != InstallerState.NOT_TRACKED) {
            FinskyLog.d("Dropping install request for %s because already installing", docId);
            return;
        }
        AppState appState = this.mAppStates.getApp(docId);
        int newVersionCode = packageVersion;
        PackageState packageState = appState != null ? appState.packageManagerState : null;
        int oldVersionCode = packageState != null ? packageState.installedVersion : -1;
        AppData logAppData = new AppData();
        logAppData.version = newVersionCode;
        logAppData.hasVersion = true;
        if (oldVersionCode > -1) {
            logAppData.oldVersion = oldVersionCode;
            logAppData.hasOldVersion = true;
        }
        if (packageState != null) {
            logAppData.systemApp = appState.packageManagerState.isSystemApp;
            logAppData.hasSystemApp = true;
        }
        if (newVersionCode <= oldVersionCode) {
            FinskyLog.e("Skipping attempt to download %s version %d over version %d", docId, Integer.valueOf(newVersionCode), Integer.valueOf(oldVersionCode));
            this.mPackageInstaller.cancelSession(docId);
            FinskyApp.get().getEventLogger().logBackgroundEvent(112, docId, "older-version", 0, null, logAppData);
            if (((appState.installerData != null ? appState.installerData.getFlags() : 0) & 1) == 0) {
                this.mNotifier.showInstallationFailureMessage(title, docId, this.mContext.getString(R.string.install_failed_already_exists), -1);
                return;
            }
            return;
        }
        FinskyLog.d("Request install of %s v=%d for %s", docId, Integer.valueOf(packageVersion), reason);
        FinskyApp.get().getEventLogger().logBackgroundEvent(105, docId, reason, 0, null, logAppData);
        if (!this.mPackageInstaller.hasSession(docId)) {
            this.mPackageInstaller.createSession(docId, 0, title, null);
        }
        InstallerData oldRow = appState != null ? appState.installerData : null;
        Builder builder = Builder.buildUpon(oldRow, docId);
        builder.setDesiredVersion(packageVersion);
        builder.setLastNotifiedVersion(packageVersion);
        builder.setAccountName(accountName);
        builder.setTitle(title);
        builder.setDeliveryData(null, 0);
        builder.setInstallerState(0);
        builder.setDownloadUri(null);
        int flags = ((((oldRow != null ? oldRow.getFlags() : 0) & -13) & -1537) & -12289) & -49153;
        if (priority == 1) {
            flags |= 16384;
            this.mPriorityPackages.add(0, docId);
        } else if (priority == 2) {
            flags |= 32768;
            this.mPriorityPackages.add(docId);
        }
        builder.setFlags(flags);
        this.mInstallerDataStore.put(builder.build());
        notifyListeners(docId, InstallerPackageEvent.DOWNLOAD_PENDING, 0);
        if (!deferred) {
            kick(false);
        }
    }

    public void updateInstalledApps(List<Document> documentsToUpdate, String reason, boolean showErrors, boolean deferred, boolean mobileDataProhibited, int priority) {
        for (Document document : documentsToUpdate) {
            String packageName = document.getDocId();
            setVisibility(packageName, true, showErrors, true);
            if (mobileDataProhibited) {
                setMobileDataProhibited(packageName);
            }
            updateSingleInstalledApp(packageName, document.getVersionCode(), document.getTitle(), reason, deferred, priority);
        }
    }

    public void updateSingleInstalledApp(String packageName, int versionCode, String packageTitle, String reason, boolean deferred, int priority) {
        InstallerTask.checkForEmptyTitle("updateSingleInstalledApp", packageName, packageTitle, null);
        AppState appState = this.mAppStates.getApp(packageName);
        if (appState == null || appState.packageManagerState == null) {
            FinskyLog.d("Cannot update %s because not installed.", packageName);
            this.mPackageInstaller.cancelSession(packageName);
            return;
        }
        String updateAccountName = AppActionAnalyzer.getAppDetailsAccount(packageName, FinskyApp.get().getCurrentAccountName(), this.mAppStates, this.mLibraries);
        if (TextUtils.isEmpty(updateAccountName)) {
            FinskyLog.d("Cannot update %s because cannot determine update account.", packageName);
            this.mPackageInstaller.cancelSession(packageName);
            return;
        }
        requestInstall(packageName, versionCode, updateAccountName, packageTitle, deferred, reason, priority);
    }

    public void setMobileDataAllowed(String packageName) {
        InstallerDataStore installerDataStore = this.mAppStates.getInstallerDataStore();
        InstallerData installerData = installerDataStore.get(packageName);
        int oldFlags = 0;
        if (installerData != null) {
            oldFlags = installerData.getFlags();
        }
        int newFlags = oldFlags | 2;
        if (newFlags != oldFlags) {
            installerDataStore.setFlags(packageName, newFlags);
        }
    }

    public void setMobileDataProhibited(String packageName) {
        InstallerDataStore installerDataStore = this.mAppStates.getInstallerDataStore();
        InstallerData installerData = installerDataStore.get(packageName);
        int oldFlags = 0;
        if (installerData != null) {
            oldFlags = installerData.getFlags();
        }
        int newFlags = (oldFlags | 2048) & -3;
        if (newFlags != oldFlags) {
            installerDataStore.setFlags(packageName, newFlags);
        }
    }

    public void setVisibility(String packageName, boolean showProgress, boolean showErrors, boolean showComplete) {
        InstallerDataStore installerDataStore = this.mAppStates.getInstallerDataStore();
        InstallerData installerData = installerDataStore.get(packageName);
        int oldFlags = 0;
        if (installerData != null) {
            oldFlags = installerData.getFlags();
        }
        int newFlags = oldFlags & -146;
        if (!showProgress) {
            newFlags |= 16;
        }
        if (!showErrors) {
            newFlags |= 1;
        }
        if (!showComplete) {
            newFlags |= 128;
        }
        if (newFlags != oldFlags) {
            installerDataStore.setFlags(packageName, newFlags);
        }
    }

    public void setEarlyUpdate(String packageName) {
        InstallerDataStore installerDataStore = this.mAppStates.getInstallerDataStore();
        InstallerData installerData = installerDataStore.get(packageName);
        int oldFlags = 0;
        if (installerData != null) {
            oldFlags = installerData.getFlags();
        }
        int newFlags = oldFlags | 65536;
        if (newFlags != oldFlags) {
            installerDataStore.setFlags(packageName, newFlags);
        }
    }

    public void setDeliveryToken(String packageName, String deliveryToken) {
        this.mAppStates.getInstallerDataStore().setDeliveryToken(packageName, deliveryToken);
    }

    public void startDeferredInstalls() {
        kick(true);
    }

    public void cancel(String packageName) {
        InstallerTask runningTask = getInstallerTask(packageName);
        if (runningTask != null) {
            runningTask.cancel(true);
        } else {
            cancelPendingInstall(this.mAppStates.getApp(packageName));
        }
        kick(true);
    }

    public void cancelAll() {
        if (this.mInstallerTask != null) {
            this.mInstallerTask.cancel(true);
        }
        for (AppState appToInstall : this.mAppStates.getAppsToInstall()) {
            cancelPendingInstall(appToInstall);
        }
        kick(true);
    }

    private void cancelPendingInstall(AppState appState) {
        if (appState != null) {
            String packageName = appState.packageName;
            FinskyLog.d("Cancel pending install of %s", packageName);
            this.mPackageInstaller.cancelSession(packageName);
            if (appState.installerData != null) {
                clearInstallerState(appState);
                notifyListeners(packageName, InstallerPackageEvent.DOWNLOAD_CANCELLED, 0);
            }
        }
    }

    void clearInstallerState(AppState appState) {
        if (appState != null && appState.installerData != null) {
            String packageName = appState.packageName;
            this.mInstallerDataStore.setDesiredVersion(packageName, -1);
            this.mInstallerDataStore.setInstallerState(packageName, 0, null);
            this.mInstallerDataStore.setFlags(packageName, 0);
            this.mInstallerDataStore.setDeliveryToken(packageName, null);
        }
    }

    private boolean recoverDownload(Uri contentUri, int status) {
        String uriString = contentUri != null ? contentUri.toString() : null;
        if (TextUtils.isEmpty(uriString)) {
            return false;
        }
        if (this.mInstallerTask != null) {
            FinskyLog.w("tried recovery while already handling %s", this.mInstallerTask.packageName);
            return false;
        }
        InstallerData recoverInstallerData = null;
        for (InstallerData installerData : this.mAppStates.getInstallerDataStore().getAll()) {
            if (uriString.equals(installerData.getDownloadUri())) {
                recoverInstallerData = installerData;
                break;
            }
        }
        if (recoverInstallerData == null) {
            return false;
        }
        String packageName = recoverInstallerData.getPackageName();
        FinskyLog.d("Recovering download for running %s", packageName);
        if (multiUserMode()) {
            try {
                if (!this.mCoordinatorService.acquirePackage(packageName)) {
                    FinskyLog.w("Can't recover %s *** cannot acquire", packageName);
                    return false;
                }
            } catch (RemoteException e) {
                FinskyLog.w("Acquiring %s *** received %s", packageName, e);
            }
        }
        InstallerTask recoverTask = new InstallerTask(packageName, this, this.mAppStates, this.mDownloadQueue, this.mNotifier, this.mInstallPolicies, this.mPackageInstaller);
        if (recoverTask.recover(contentUri, status)) {
            this.mInstallerTask = recoverTask;
            return true;
        }
        if (multiUserMode()) {
            try {
                this.mCoordinatorService.releasePackage(packageName);
            } catch (RemoteException e2) {
                FinskyLog.w("Releasing %s *** received %s", packageName, e2);
            }
        }
        return false;
    }

    public boolean isBusy() {
        return false;
    }

    public void uninstallAssetSilently(String packageName) {
        if (TextUtils.isEmpty(packageName)) {
            FinskyLog.w("Unexpected empty package name", new Object[0]);
            return;
        }
        InstallerTask runningTask = getInstallerTask(packageName);
        if (runningTask != null) {
            runningTask.cancel(true);
        }
        PackageInfo packageInfo = null;
        try {
            packageInfo = this.mContext.getPackageManager().getPackageInfo(packageName, 0);
        } catch (NameNotFoundException e) {
        }
        AppData appData = null;
        if (packageInfo != null) {
            appData = new AppData();
            appData.oldVersion = packageInfo.versionCode;
            appData.hasOldVersion = true;
            appData.systemApp = (packageInfo.applicationInfo.flags & 1) != 0;
            appData.hasSystemApp = true;
        }
        FinskyApp.get().getEventLogger().logBackgroundEvent(114, packageName, null, 0, null, appData);
        if (this.mInstallerDataStore.get(packageName) != null) {
            this.mInstallerDataStore.setDesiredVersion(packageName, -1);
        }
        try {
            this.mContext.getPackageManager().getPackageInfo(packageName, 0);
            this.mUninstallingPackages.add(packageName);
            notifyListeners(packageName, InstallerPackageEvent.UNINSTALLING, 0);
            PackageInstallerFactory.getPackageInstaller().uninstallPackage(packageName);
        } catch (NameNotFoundException e2) {
            FinskyLog.d("Skipping uninstall of %s - already uninstalled.", packageName);
        }
    }

    public void uninstallPackagesByUid(String packageName) {
        PackageManager packageManager = this.mContext.getPackageManager();
        try {
            for (String uidPackageName : packageManager.getPackagesForUid(packageManager.getPackageInfo(packageName, 0).applicationInfo.uid)) {
                FinskyLog.d("Removing package '%s' (child of '%s')", uidPackageName, packageName);
                uninstallAssetSilently(uidPackageName);
            }
        } catch (NameNotFoundException e) {
            FinskyLog.d("Skipping uninstall of %s - already uninstalled.", packageName);
        }
    }

    public InstallerState getState(String packageName) {
        InstallerTask runningTask = getInstallerTask(packageName);
        if (runningTask != null) {
            return runningTask.getState();
        }
        if (this.mUninstallingPackages.contains(packageName)) {
            return InstallerState.UNINSTALLING;
        }
        AppState appState = this.mAppStates.getApp(packageName);
        if (appState != null) {
            int installedVersion = -1;
            if (appState.packageManagerState != null) {
                installedVersion = appState.packageManagerState.installedVersion;
            }
            if (appState.installerData != null && appState.installerData.getDesiredVersion() > installedVersion) {
                return InstallerState.DOWNLOAD_PENDING;
            }
        }
        return InstallerState.NOT_TRACKED;
    }

    static {
        PROGRESS_NOT_TRACKED = new InstallerProgressReport(InstallerState.NOT_TRACKED, 0, 0, 0);
        PROGRESS_DOWNLOAD_PENDING = new InstallerProgressReport(InstallerState.DOWNLOAD_PENDING, 0, 0, 0);
        PROGRESS_UNINSTALLING = new InstallerProgressReport(InstallerState.UNINSTALLING, 0, 0, 0);
    }

    public InstallerProgressReport getProgress(String packageName) {
        InstallerTask runningTask = getInstallerTask(packageName);
        if (runningTask != null) {
            return runningTask.getProgress();
        }
        if (this.mUninstallingPackages.contains(packageName)) {
            return PROGRESS_UNINSTALLING;
        }
        AppState appState = this.mAppStates.getApp(packageName);
        if (appState != null) {
            int installedVersion = -1;
            if (appState.packageManagerState != null) {
                installedVersion = appState.packageManagerState.installedVersion;
            }
            if (appState.installerData != null && appState.installerData.getDesiredVersion() > installedVersion) {
                return PROGRESS_DOWNLOAD_PENDING;
            }
        }
        return PROGRESS_NOT_TRACKED;
    }

    public void addListener(InstallerListener listener) {
        Utils.ensureOnMainThread();
        this.mListeners.add(listener);
    }

    public void removeListener(InstallerListener listener) {
        Utils.ensureOnMainThread();
        this.mListeners.remove(listener);
    }

    void notifyListeners(final String packageName, final InstallerPackageEvent event, final int statusCode) {
        this.mHandler.post(new Runnable() {
            public void run() {
                for (InstallerListener listener : Lists.newArrayList(InstallerImpl.this.mListeners)) {
                    try {
                        listener.onInstallPackageEvent(packageName, event, statusCode);
                    } catch (Exception e) {
                        FinskyLog.wtf(e, "Exception caught in InstallerListener", new Object[0]);
                    }
                }
            }
        });
    }

    public void onStart(Download download) {
        InstallerTask task = getInstallerTask(download);
        FinskyApp.get().getEventLogger().logBackgroundEvent(101, download.toString(), null, 0, null, task == null ? null : task.getAppData());
        if (task != null) {
            task.onStart(download);
        }
    }

    public void onComplete(Download download) {
        InstallerTask task = getInstallerTask(download);
        FinskyApp.get().getEventLogger().logBackgroundEvent(102, download.toString(), null, 0, null, task == null ? null : task.getAppData());
        if (task != null) {
            task.onComplete(download);
        }
    }

    public void onCancel(Download download) {
        InstallerTask task = getInstallerTask(download);
        FinskyApp.get().getEventLogger().logBackgroundEvent(103, download.toString(), null, 0, null, task == null ? null : task.getAppData());
        if (task != null) {
            task.cancel(true);
        }
    }

    public void onError(Download download, int httpStatus) {
        InstallerTask task = getInstallerTask(download);
        FinskyApp.get().getEventLogger().logBackgroundEvent(104, download.toString(), null, httpStatus, null, task == null ? null : task.getAppData());
        if (task != null) {
            task.onError(download, httpStatus);
        }
    }

    public void onProgress(Download download, DownloadProgress progress) {
        InstallerTask task = getInstallerTask(download);
        if (task != null) {
            task.onProgress(download, progress);
        }
    }

    public void onNotificationClicked(Download download) {
        String packageName = download.getPackageName();
        if (packageName == null) {
            FinskyLog.d("Discarding notification click, no packageName for %s", download.toString());
            return;
        }
        Intent intent = NotificationManager.createDefaultClickIntent(this.mContext, packageName, null, null, DfeUtils.createDetailsUrlFromId(packageName));
        intent.setFlags(268435456);
        this.mContext.startActivity(intent);
    }

    private void kick(boolean postToHandler) {
        if (postToHandler) {
            this.mHandler.post(new Runnable() {
                public void run() {
                    InstallerImpl.this.kick(false);
                }
            });
        } else if (!this.mRunning) {
            FinskyLog.d("Installer kick - no action, not running yet", new Object[0]);
        } else if (this.mInstallerTask != null) {
        } else {
            if (multiUserMode()) {
                kickMultiUser();
                return;
            }
            AppState appToInstall = selectNextTask(this.mAppStates.getAppsToInstall(), this.mPriorityPackages);
            if (appToInstall != null) {
                FinskyLog.d("Installer kick - starting %s", appToInstall.packageName);
                this.mInstallerTask = new InstallerTask(appToInstall.packageName, this, this.mAppStates, this.mDownloadQueue, this.mNotifier, this.mInstallPolicies, this.mPackageInstaller);
                this.mInstallerTask.start();
            }
        }
    }

    AppState selectNextTask(List<AppState> appsToInstall, List<String> priorityList) {
        if (appsToInstall.isEmpty()) {
            return null;
        }
        while (!priorityList.isEmpty()) {
            String priorityPackage = (String) priorityList.remove(0);
            for (int i = 0; i < appsToInstall.size(); i++) {
                AppState candidate = (AppState) appsToInstall.get(i);
                if (candidate.packageName.equals(priorityPackage)) {
                    return candidate;
                }
            }
            FinskyLog.d("Unexpected: Priority package %s no longer installable", priorityPackage);
        }
        Set<String> foregroundPackages = InstallPolicies.getForegroundPackages(this.mContext);
        for (AppState app : appsToInstall) {
            if (!foregroundPackages.contains(app.packageName)) {
                return app;
            }
        }
        failPendingForegroundInstalls(appsToInstall);
        return null;
    }

    AppState selectNextTaskMultiUser(List<AppState> appsToInstall, List<String> priorityList, IMultiUserCoordinatorService coordinatorService) {
        int i;
        for (i = priorityList.size() - 1; i >= 0; i--) {
            String priorityPackage = (String) priorityList.get(i);
            boolean found = false;
            for (int j = 0; j < appsToInstall.size(); j++) {
                if (((AppState) appsToInstall.get(j)).packageName.equals(priorityPackage)) {
                    appsToInstall.add(0, (AppState) appsToInstall.remove(j));
                    found = true;
                    break;
                }
            }
            if (!found) {
                priorityList.remove(i);
            }
        }
        Set<String> packagesToSkip = InstallPolicies.getForegroundPackages(this.mContext);
        packagesToSkip.removeAll(priorityList);
        Set<AppState> foregroundAppsSkipped = Sets.newHashSet();
        i = 0;
        while (i < appsToInstall.size()) {
            AppState appToInstall = (AppState) appsToInstall.get(i);
            try {
                if (packagesToSkip.contains(appToInstall.packageName)) {
                    foregroundAppsSkipped.add(appToInstall);
                } else {
                    if (coordinatorService.acquirePackage(appToInstall.packageName)) {
                        priorityList.remove(appToInstall.packageName);
                        return appToInstall;
                    }
                    FinskyLog.d("Skipping install of %s - not acquired", appToInstall.packageName);
                }
                i++;
            } catch (RemoteException e) {
                FinskyLog.w("Couldn't acquire %s (proceed anyway) received %s", appToInstall.packageName, e);
                return appToInstall;
            }
        }
        failPendingForegroundInstalls(foregroundAppsSkipped);
        return null;
    }

    private void failPendingForegroundInstalls(Collection<AppState> apps) {
        for (AppState appState : apps) {
            clearInstallerState(appState);
            FinskyApp.get().getEventLogger().logBackgroundEvent(111, appState.packageName, "foreground", 978, null, null);
            notifyListeners(appState.packageName, InstallerPackageEvent.INSTALL_ERROR, 978);
        }
    }

    private boolean multiUserMode() {
        return this.mUsers.hasMultipleUsers();
    }

    private void kickMultiUser() {
        List<AppState> appsToInstall = this.mAppStates.getAppsToInstall();
        if (appsToInstall.isEmpty()) {
            unbindMultiUserCoordinator();
        } else if (this.mCoordinatorService == null) {
            bindToMultiUserCoordinator(new Runnable() {
                public void run() {
                    InstallerImpl.this.kick(false);
                }
            });
        } else {
            AppState appToInstall = selectNextTaskMultiUser(appsToInstall, this.mPriorityPackages, this.mCoordinatorService);
            if (appToInstall != null) {
                FinskyLog.d("Installer kick - starting %s", appToInstall.packageName);
                this.mInstallerTask = new InstallerTask(appToInstall.packageName, this, this.mAppStates, this.mDownloadQueue, this.mNotifier, this.mInstallPolicies, this.mPackageInstaller);
                this.mInstallerTask.start();
            }
        }
    }

    private void releaseMultiUserPackage(final String packageName) {
        bindToMultiUserCoordinator(new Runnable() {
            public void run() {
                try {
                    InstallerImpl.this.mCoordinatorService.releasePackage(packageName);
                } catch (RemoteException e) {
                    FinskyLog.w("Couldn't release %s *** received %s", packageName, e);
                }
            }
        });
    }

    private InstallerTask getInstallerTask(String packageName) {
        if (this.mInstallerTask == null || !this.mInstallerTask.packageName.equals(packageName)) {
            return null;
        }
        return this.mInstallerTask;
    }

    private InstallerTask getInstallerTask(Download download) {
        String packageName = download.getPackageName();
        if (packageName == null) {
            return null;
        }
        InstallerTask task = getInstallerTask(packageName);
        if (task == null) {
            this.mDownloadQueue.cancel(download);
            return null;
        }
        AppState appState = this.mAppStates.getApp(packageName);
        if (appState != null && appState.installerData != null) {
            return task;
        }
        this.mDownloadQueue.cancel(download);
        return null;
    }

    void taskFinished(InstallerTask installerTask) {
        if (!(this.mInstallerTask == null || installerTask == this.mInstallerTask)) {
            FinskyLog.wtf("Unexpected (late?) finish of task for %s", installerTask.packageName);
        }
        this.mInstallerTask = null;
        if (multiUserMode()) {
            releaseMultiUserPackage(installerTask.packageName);
        }
        kick(true);
    }

    private void bindToMultiUserCoordinator(Runnable runAfter) {
        Utils.ensureOnMainThread();
        if (this.mCoordinatorService != null) {
            runAfter.run();
            return;
        }
        this.mServiceConnectionCallbacks.add(runAfter);
        if (this.mServiceConnection == null) {
            this.mServiceConnection = new RemoteServiceConnection();
            if (!this.mContext.bindService(MultiUserCoordinatorService.createBindIntent(this.mContext), this.mServiceConnection, 5)) {
                FinskyLog.w("Couldn't start service for %s", MultiUserCoordinatorService.createBindIntent(this.mContext));
            }
        }
    }

    private void unbindMultiUserCoordinator() {
        Utils.ensureOnMainThread();
        if (this.mServiceConnection != null) {
            try {
                if (this.mCoordinatorService != null) {
                    this.mCoordinatorService.registerListener(null);
                    this.mCoordinatorService.releaseAllPackages();
                }
            } catch (RemoteException e) {
                FinskyLog.w("Couldn't sign out from coordinator *** received %s", e);
            }
            this.mCoordinatorService = null;
            this.mContext.unbindService(this.mServiceConnection);
            this.mServiceConnection = null;
        }
    }

    public void onPackageAdded(String packageName) {
        this.mNotifier.hidePackageRemovedMessage(packageName);
    }

    public void onPackageRemoved(String packageName, boolean replacing) {
        if (replacing) {
            this.mNotifier.hideInstallingMessage();
        } else {
            this.mNotifier.hideAllMessagesForPackage(packageName);
            this.mNotifier.hidePackageRemoveRequestMessage(packageName);
        }
        this.mUninstallingPackages.remove(packageName);
        AppState appState = this.mAppStates.getApp(packageName);
        if (!(appState == null || appState.installerData == null)) {
            boolean clearDesiredVersion = false;
            InstallerData installerData = appState.installerData;
            if (installerData.getDesiredVersion() != -1) {
                if (!replacing || appState.packageManagerState == null) {
                    clearDesiredVersion = true;
                } else {
                    if (appState.packageManagerState.installedVersion < installerData.getDesiredVersion()) {
                        clearDesiredVersion = true;
                    }
                }
            }
            if (clearDesiredVersion) {
                this.mInstallerDataStore.setDesiredVersion(packageName, -1);
            }
        }
        notifyListeners(packageName, InstallerPackageEvent.UNINSTALLED, 0);
    }

    public void onPackageChanged(String packageName) {
    }

    public void onPackageAvailabilityChanged(String[] packageNames, boolean available) {
    }

    public void onPackageFirstLaunch(String packageName) {
    }
}
