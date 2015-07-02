package com.google.android.finsky.appstate;

import android.accounts.Account;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.analytics.PlayStore.WifiAutoUpdateAttempt;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.api.model.MultiWayUpdateController;
import com.google.android.finsky.api.model.OnDataChangedListener;
import com.google.android.finsky.appstate.InstallerDataStore.AutoUpdateState;
import com.google.android.finsky.appstate.InstallerDataStore.InstallerData;
import com.google.android.finsky.config.G;
import com.google.android.finsky.gearhead.GearheadStateMonitor;
import com.google.android.finsky.installer.InstallPolicies;
import com.google.android.finsky.library.Libraries;
import com.google.android.finsky.protos.DocDetails.AppDetails;
import com.google.android.finsky.receivers.Installer;
import com.google.android.finsky.services.CheckPreconditionsAndAutoUpdateScheduler;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.FinskyPreferences;
import com.google.android.finsky.utils.Notifier;
import com.google.android.finsky.utils.Utils;
import com.google.android.finsky.utils.VendingPreferences;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class UpdateChecker {
    private final AppStates mAppStates;
    private final Context mContext;
    private MultiWayUpdateController mDfeModel;
    private final InstallPolicies mInstallPolicies;
    private final Installer mInstaller;
    private final Libraries mLibraries;
    private final Notifier mNotifier;

    public UpdateChecker(Context context, Libraries libraries, AppStates appStates, InstallPolicies installPolicies, Installer installer, Notifier notifier) {
        this.mContext = context;
        this.mLibraries = libraries;
        this.mAppStates = appStates;
        this.mInstallPolicies = installPolicies;
        this.mInstaller = installer;
        this.mNotifier = notifier;
    }

    public void checkForUpdates(Runnable successRunnable, Runnable errorRunnable, String logReason, boolean invokedInJobService) {
        if (!invokedInJobService) {
            CheckPreconditionsAndAutoUpdateScheduler.cancelCheck();
        }
        final Account currentAccount = FinskyApp.get().getCurrentAccount();
        if (currentAccount == null) {
            if (successRunnable != null) {
                successRunnable.run();
            }
        } else if (!this.mAppStates.isLoaded()) {
            FinskyLog.wtf("Require loaded app states to perform update check.", new Object[0]);
            if (errorRunnable != null) {
                errorRunnable.run();
            }
        } else if (!this.mLibraries.isLoaded()) {
            FinskyLog.wtf("Require loaded libraries to perform update check.", new Object[0]);
            if (errorRunnable != null) {
                errorRunnable.run();
            }
        } else if (GearheadStateMonitor.isReady()) {
            migrateAutoUpdateSettings(this.mAppStates);
            final GmsCoreHelper gmsCoreHelper = new GmsCoreHelper(this.mLibraries, this.mAppStates, this.mContext);
            final int accountHashBeforeAsyncTask = this.mLibraries.getLoadedAccountHash();
            final Runnable runnable = errorRunnable;
            final String str = logReason;
            final boolean z = invokedInJobService;
            final Runnable runnable2 = successRunnable;
            new AsyncTask<Void, Void, Map<String, List<String>>>() {
                protected Map<String, List<String>> doInBackground(Void... params) {
                    return UpdateChecker.this.mAppStates.getOwnedByAccountBlocking(UpdateChecker.this.mLibraries, true);
                }

                protected void onPostExecute(Map<String, List<String>> docIdsByAccount) {
                    for (List<String> accountDocs : docIdsByAccount.values()) {
                        GmsCoreHelper.insertGmsCore(accountDocs);
                    }
                    MultiWayUpdateController.selectAccountsForUpdateChecks(FinskyApp.get().getInstallerDataStore(), currentAccount.name, docIdsByAccount);
                    UpdateChecker.this.mDfeModel = new MultiWayUpdateController(UpdateChecker.this.mAppStates.getInstallerDataStore(), UpdateChecker.this.mLibraries);
                    UpdateChecker.this.mDfeModel.addDataChangedListener(new OnDataChangedListener() {
                        public void onDataChanged() {
                            if (UpdateChecker.this.mLibraries.getLoadedAccountHash() != accountHashBeforeAsyncTask) {
                                FinskyLog.w("Skipping update check because account hash changed.", new Object[0]);
                                if (runnable != null) {
                                    runnable.run();
                                    return;
                                }
                                return;
                            }
                            List<Document> documents = UpdateChecker.this.mDfeModel.getDocuments();
                            Iterator<Document> iter = documents.iterator();
                            while (iter.hasNext()) {
                                Document document = (Document) iter.next();
                                if (GmsCoreHelper.isGmsCore(document)) {
                                    gmsCoreHelper.updateGmsCore(document);
                                    iter.remove();
                                    break;
                                }
                            }
                            UpdateChecker.this.handleUpdates(documents, str, z);
                            if (runnable2 != null) {
                                runnable2.run();
                            }
                        }
                    });
                    UpdateChecker.this.mDfeModel.addErrorListener(new ErrorListener() {
                        public void onErrorResponse(VolleyError volleyError) {
                            FinskyLog.w("Update check failed: %s", volleyError);
                            if (runnable != null) {
                                runnable.run();
                            }
                        }
                    });
                    UpdateChecker.this.mDfeModel.addRequests(docIdsByAccount);
                }
            }.execute(new Void[0]);
        } else {
            FinskyLog.wtf("Require initialized Gearhead monitor to perform update check.", new Object[0]);
            if (errorRunnable != null) {
                errorRunnable.run();
            }
        }
    }

    private void handleUpdates(List<Document> docs, String logReason, boolean invokedInJobService) {
        boolean deferred = ((Boolean) FinskyPreferences.autoUpdateFirstTimeForAccounts.get()).booleanValue() && ((Long) G.autoUpdateDeliveryHoldoffMs.get()).longValue() > 0;
        FinskyPreferences.autoUpdateFirstTimeForAccounts.put(Boolean.valueOf(false));
        List<Document> appsWithUpdates = this.mInstallPolicies.getApplicationsWithUpdates(docs);
        if (((Boolean) FinskyPreferences.AUTO_UPDATE_ENABLED.get()).booleanValue()) {
            List<Document> autoUpdateApps = this.mInstallPolicies.getApplicationsEligibleForAutoUpdate(appsWithUpdates);
            if (autoUpdateApps.isEmpty()) {
                logNoWifiAutoUpdate();
            } else {
                boolean gearheadIsProjecting = GearheadStateMonitor.isProjecting();
                boolean autoUpdateWifiOnly = ((Boolean) FinskyPreferences.AUTO_UPDATE_WIFI_ONLY.get()).booleanValue();
                boolean isOkToUpdateOnWifi = this.mInstallPolicies.isWifiNetwork() && !this.mInstallPolicies.isMobileHotspot();
                boolean jobSchedulerEnabled = CheckPreconditionsAndAutoUpdateScheduler.isJobSchedulerEnabled();
                if (!gearheadIsProjecting && (!autoUpdateWifiOnly || invokedInJobService || (isOkToUpdateOnWifi && !jobSchedulerEnabled))) {
                    this.mInstaller.updateInstalledApps(autoUpdateApps, "auto_update", false, deferred, autoUpdateWifiOnly, 3);
                    if (deferred) {
                        FinskyLog.d("Auto-update of %d packages will defer for %d ms", Integer.valueOf(autoUpdateApps.size()), G.autoUpdateDeliveryHoldoffMs.get());
                        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                            public void run() {
                                UpdateChecker.this.mInstaller.startDeferredInstalls();
                            }
                        }, ((Long) G.autoUpdateDeliveryHoldoffMs.get()).longValue());
                    }
                    appsWithUpdates.removeAll(autoUpdateApps);
                    this.mNotifier.hideUpdatesAvailableMessage();
                    if (autoUpdateWifiOnly) {
                        logWifiAutoUpdate(true, logReason, false);
                    }
                } else if (gearheadIsProjecting) {
                    CheckPreconditionsAndAutoUpdateScheduler.scheduleCheck(2);
                    logWifiAutoUpdate(false, logReason, true);
                } else if (autoUpdateWifiOnly) {
                    CheckPreconditionsAndAutoUpdateScheduler.scheduleCheck(1);
                    logWifiAutoUpdate(false, logReason, false);
                }
            }
        } else {
            logNoWifiAutoUpdate();
        }
        showUpdateNotifications(appsWithUpdates);
    }

    private void showUpdateNotifications(List<Document> allUpdates) {
        if (!allUpdates.isEmpty()) {
            List<Document> newUpdates = this.mInstallPolicies.getApplicationsEligibleForNewUpdateNotification(allUpdates);
            markAppsAsNotified(newUpdates);
            if (((Boolean) VendingPreferences.NOTIFY_UPDATES.get()).booleanValue()) {
                List<Document> allUpdatesApprovalRequired = this.mInstallPolicies.getAppsThatRequireUpdateWarnings(allUpdates, true);
                List<Document> newUpdatesApprovalRequired = this.mInstallPolicies.getAppsThatRequireUpdateWarnings(newUpdates, true);
                boolean autoUpdateEnabled = ((Boolean) FinskyPreferences.AUTO_UPDATE_ENABLED.get()).booleanValue();
                int numAllUpdates = allUpdates.size();
                int numAllApproval = allUpdatesApprovalRequired.size();
                int numNewUpdates = newUpdates.size();
                boolean doNewUpdatesNeedApproval = newUpdatesApprovalRequired.size() > 0;
                long timeSinceLastUpdateNoti = System.currentTimeMillis() - ((Long) FinskyPreferences.lastUpdateAvailNotificationTimestampMs.get()).longValue();
                if (!autoUpdateEnabled && numNewUpdates > 0) {
                    FinskyLog.d("Notifying user that [%d/%d] applications have new updates.", Integer.valueOf(numNewUpdates), Integer.valueOf(numAllUpdates));
                    this.mNotifier.showNewUpdatesAvailableMessage(newUpdates, numAllUpdates);
                    FinskyPreferences.lastUpdateAvailNotificationTimestampMs.put(Long.valueOf(System.currentTimeMillis()));
                } else if (autoUpdateEnabled && doNewUpdatesNeedApproval) {
                    FinskyLog.d("Notifying user [%d/%d] applications have updates that require approval.", Integer.valueOf(numAllApproval), Integer.valueOf(numAllUpdates));
                    this.mNotifier.showUpdatesNeedApprovalMessage(allUpdatesApprovalRequired);
                    FinskyPreferences.lastUpdateAvailNotificationTimestampMs.put(Long.valueOf(System.currentTimeMillis()));
                } else if (timeSinceLastUpdateNoti > ((Long) G.outstandingNotificationTimeDelayMs.get()).longValue()) {
                    FinskyLog.d("Notifying user that %d applications have outstanding updates.", Integer.valueOf(numAllUpdates));
                    this.mNotifier.showOutstandingUpdatesMessage(allUpdates);
                    FinskyPreferences.lastUpdateAvailNotificationTimestampMs.put(Long.valueOf(System.currentTimeMillis()));
                }
            }
        }
    }

    private void markAppsAsNotified(List<Document> docsNotified) {
        for (Document doc : docsNotified) {
            AppDetails appDetails = doc.getAppDetails();
            String packageName = appDetails.packageName;
            InstallerData installerData = this.mAppStates.getInstallerDataStore().get(appDetails.packageName);
            int version = appDetails.versionCode;
            if (installerData == null || version > installerData.getLastNotifiedVersion()) {
                this.mAppStates.getInstallerDataStore().setLastNotifiedVersion(packageName, version);
            }
        }
    }

    public static void migrateAutoUpdateSettings(AppStates appStates) {
        boolean shouldClobberSettings = false;
        Utils.ensureOnMainThread();
        if (!appStates.isLoaded()) {
            FinskyLog.wtf("Require loaded app states to migrate auto-update state.", new Object[0]);
        }
        boolean oldGlobalExists = VendingPreferences.AUTO_UPDATE_BY_DEFAULT.exists();
        boolean newGlobalExists = FinskyPreferences.AUTO_UPDATE_ENABLED.exists();
        if (!oldGlobalExists && newGlobalExists) {
            return;
        }
        if (oldGlobalExists || newGlobalExists || FinskyApp.get().getVersionCodeOfLastRun() != -1) {
            boolean autoUpdateByDefault = ((Boolean) VendingPreferences.AUTO_UPDATE_BY_DEFAULT.get()).booleanValue();
            FinskyPreferences.AUTO_UPDATE_ENABLED.put(Boolean.valueOf(autoUpdateByDefault));
            if (!FinskyPreferences.AUTO_UPDATE_WIFI_ONLY.exists()) {
                boolean wifiOnly = true;
                if (VendingPreferences.AUTO_UPDATE_WIFI_ONLY.exists()) {
                    wifiOnly = ((Boolean) VendingPreferences.AUTO_UPDATE_WIFI_ONLY.get()).booleanValue();
                }
                FinskyPreferences.AUTO_UPDATE_WIFI_ONLY.put(Boolean.valueOf(wifiOnly));
            }
            if (!autoUpdateByDefault) {
                shouldClobberSettings = true;
            }
            migrateAllAppsToUseGlobalUpdateSetting(appStates, shouldClobberSettings, AutoUpdateState.USE_GLOBAL, "version");
            VendingPreferences.AUTO_UPDATE_BY_DEFAULT.remove();
            VendingPreferences.AUTO_UPDATE_WIFI_ONLY.remove();
            FinskyPreferences.hadPreJsAutoUpdateSettings.put(Boolean.valueOf(true));
            return;
        }
        FinskyPreferences.AUTO_UPDATE_ENABLED.put(Boolean.valueOf(true));
        FinskyPreferences.AUTO_UPDATE_WIFI_ONLY.put(Boolean.valueOf(true));
    }

    public static void setAllAppsToUseGlobalDefault(AppStates appStates) {
        migrateAllAppsToUseGlobalUpdateSetting(appStates, true, AutoUpdateState.USE_GLOBAL, "cleanup");
    }

    public static void migrateAllAppsToUseGlobalUpdateSetting(AppStates appStates, boolean clobberUserSettings, AutoUpdateState defaultState, String logReason) {
        FinskyApp.get().getEventLogger().logBackgroundEvent(404, null, logReason, 0, null, null);
        InstallerDataStore installerDataStore = appStates.getInstallerDataStore();
        for (InstallerData installerData : installerDataStore.getAll()) {
            if (!GmsCoreHelper.isGmsCore(installerData.getPackageName())) {
                AutoUpdateState oldState = installerData.getAutoUpdate();
                if (clobberUserSettings || oldState == AutoUpdateState.DEFAULT) {
                    installerDataStore.setAutoUpdate(installerData.getPackageName(), defaultState);
                    FinskyLog.d("Migrate %s autoupdate from default to %s", packageName, defaultState);
                }
            }
        }
    }

    public static void logWifiAutoUpdate(boolean success, String logReason, boolean projecting) {
        FinskyEventLog log = FinskyApp.get().getEventLogger();
        long currentTimeMs = System.currentTimeMillis();
        WifiAutoUpdateAttempt attempt = new WifiAutoUpdateAttempt();
        int numFailedAttempts = ((Integer) FinskyPreferences.wifiAutoUpdateFailedAttempts.get()).intValue();
        if (success) {
            attempt.numFailedAttempts = numFailedAttempts;
            numFailedAttempts = 0;
        } else {
            numFailedAttempts++;
            attempt.numFailedAttempts = numFailedAttempts;
        }
        attempt.hasNumFailedAttempts = true;
        attempt.skippedDueToProjection = projecting;
        long firstFailMs = ((Long) FinskyPreferences.wifiAutoUpdateFirstFailMs.get()).longValue();
        long timeSinceFirstFailMs = 0;
        if (firstFailMs != 0) {
            timeSinceFirstFailMs = currentTimeMs - firstFailMs;
        }
        attempt.timeSinceFirstFailMs = timeSinceFirstFailMs;
        attempt.hasTimeSinceFirstFailMs = true;
        if (success) {
            firstFailMs = 0;
        } else if (numFailedAttempts == 1) {
            firstFailMs = currentTimeMs;
        }
        long wifiCheckIntervalMs = ((Long) G.autoUpdateWifiCheckIntervalMs.get()).longValue();
        attempt.autoUpdateSuccess = success;
        attempt.hasAutoUpdateSuccess = true;
        attempt.wifiCheckIntervalMs = wifiCheckIntervalMs;
        attempt.hasWifiCheckIntervalMs = true;
        log.logWifiAutoUpdateAttempt(attempt, logReason);
        FinskyPreferences.wifiAutoUpdateFailedAttempts.put(Integer.valueOf(numFailedAttempts));
        FinskyPreferences.wifiAutoUpdateFirstFailMs.put(Long.valueOf(firstFailMs));
    }

    public static void logNoWifiAutoUpdate() {
        if (((Integer) FinskyPreferences.wifiAutoUpdateFailedAttempts.get()).intValue() > 0) {
            logWifiAutoUpdate(true, "auto_update_obsolete", false);
        }
    }
}
