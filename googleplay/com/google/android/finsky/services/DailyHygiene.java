package com.google.android.finsky.services;

import android.accounts.Account;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.IBinder;
import android.provider.Settings.Global;
import android.provider.Settings.Secure;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.analytics.PlayStore.AppData;
import com.google.android.finsky.api.AccountHandler;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.appstate.UpdateChecker;
import com.google.android.finsky.config.G;
import com.google.android.finsky.config.PreferenceFile.SharedPreference;
import com.google.android.finsky.gearhead.GearheadStateMonitor;
import com.google.android.finsky.library.Libraries;
import com.google.android.finsky.library.SQLiteLibrary;
import com.google.android.finsky.protos.SelfUpdate.SelfUpdateResponse;
import com.google.android.finsky.utils.ClientMutationCache;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.FinskyPreferences;
import com.google.android.finsky.utils.GetSelfUpdateHelper;
import com.google.android.finsky.utils.GetSelfUpdateHelper.Listener;
import com.google.android.finsky.utils.GmsDeviceFeaturesHelper;
import com.google.android.finsky.utils.RateReviewHelper;
import com.google.android.finsky.utils.SelfUpdateScheduler;
import com.google.android.vending.verifier.VerifyInstalledPackagesReceiver;

public class DailyHygiene extends Service {
    public static final long BOOT_DELAY_MS;
    private static final long HOLDOFF_INTERVAL_MS;
    public static final long IMMEDIATE_DELAY_MS;
    private static final float INITIAL_SCHEDULE_JITTER_FACTOR;
    private static final long REGULAR_INTERVAL_MS;
    private static final float RESCHEDULE_JITTER_FACTOR;
    private static final int[] RETRY_INTERVALS;
    private static final long RETRY_INTERVAL_MS;
    private static final Intent UPDATE_CHECK;

    static {
        IMMEDIATE_DELAY_MS = ((Long) G.dailyHygieneImmediateDelayMs.get()).longValue();
        BOOT_DELAY_MS = ((Long) G.dailyHygieneBootDelayMs.get()).longValue();
        REGULAR_INTERVAL_MS = ((Long) G.dailyHygieneRegularIntervalMs.get()).longValue();
        HOLDOFF_INTERVAL_MS = ((Long) G.dailyHygieneHoldoffIntervalMs.get()).longValue();
        RETRY_INTERVAL_MS = ((Long) G.dailyHygieneRetryIntervalMs.get()).longValue();
        INITIAL_SCHEDULE_JITTER_FACTOR = ((Float) G.dailyHygieneInitialJitterFactor.get()).floatValue();
        RESCHEDULE_JITTER_FACTOR = ((Float) G.dailyHygieneJitterFactor.get()).floatValue();
        RETRY_INTERVALS = new int[]{1, 3, 9, 27, 81};
        UPDATE_CHECK = new Intent(FinskyApp.get(), DailyHygiene.class);
    }

    public static void goMakeHygieneIfDirty(Context context, int marketVersion) {
        if (needsDailyHygiene() || emergencyDailyHygiene(marketVersion) || hasDatabaseVersionChanged()) {
            FinskyLog.d("Dirty, need more hygiene.", new Object[0]);
            schedule(context, IMMEDIATE_DELAY_MS);
            return;
        }
        FinskyLog.d("No need to run daily hygiene.", new Object[0]);
    }

    public static void schedule(Context context, long delayMs) {
        ((AlarmManager) context.getSystemService("alarm")).set(0, System.currentTimeMillis() + delayMs, PendingIntent.getService(context, 0, UPDATE_CHECK, 0));
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        if (inHoldoffPeriod()) {
            schedule(this, HOLDOFF_INTERVAL_MS);
        } else {
            FinskyLog.d("Beginning daily hygiene", new Object[0]);
            beginSelfUpdateCheck();
        }
        return 2;
    }

    private void beginSelfUpdateCheck() {
        DfeApi dfeApi = FinskyApp.get().getDfeApi();
        if (dfeApi == null) {
            loadAndReplicateAndContinue();
            return;
        }
        final int installedVersion = FinskyApp.get().getVersionCode();
        final AppData appData = new AppData();
        appData.oldVersion = 0;
        appData.hasOldVersion = true;
        appData.systemApp = true;
        appData.hasSystemApp = true;
        final FinskyEventLog eventLogger = FinskyApp.get().getEventLogger();
        final String accountName = dfeApi.getAccountName();
        GetSelfUpdateHelper.getSelfUpdate(dfeApi, new Listener() {
            public void onResponse(SelfUpdateResponse response) {
                SelfUpdateScheduler selfUpdateScheduler = FinskyApp.get().getSelfUpdateScheduler();
                int newVersion = selfUpdateScheduler.getNewVersion(response);
                if (newVersion > 0) {
                    appData.version = newVersion;
                    appData.hasVersion = true;
                }
                eventLogger.logBackgroundEvent(119, FinskyApp.get().getPackageName(), null, 0, null, appData);
                if (selfUpdateScheduler.checkForSelfUpdate(newVersion, accountName) && (((Boolean) G.dailyHygieneHoldoffDuringSelfUpdate.get()).booleanValue() || DailyHygiene.emergencyDailyHygiene(installedVersion))) {
                    FinskyLog.d("Self-update started or running - defer daily hygiene", new Object[0]);
                    DailyHygiene.this.reschedule(true);
                    return;
                }
                DailyHygiene.this.loadAndReplicateAndContinue();
            }

            public void onErrorResponse(VolleyError error) {
                eventLogger.logBackgroundEvent(119, FinskyApp.get().getPackageName(), null, 0, error.getClass().getSimpleName(), appData);
                DailyHygiene.this.loadAndReplicateAndContinue();
            }
        });
    }

    private void loadAndReplicateAndContinue() {
        if (emergencyDailyHygiene(FinskyApp.get().getVersionCode())) {
            reschedule(true);
            return;
        }
        Runnable continueRunnable = new Runnable() {
            private int mLoaded;

            public void run() {
                this.mLoaded++;
                if (this.mLoaded == 4) {
                    DailyHygiene.this.contentSyncAndAutoUpdateAndContinue();
                }
            }
        };
        FinskyApp.get().getLibraries().load(continueRunnable);
        FinskyApp.get().getLibraryReplicators().replicateAllAccounts(continueRunnable, "daily-hygiene");
        FinskyApp.get().getAppStates().load(continueRunnable);
        GearheadStateMonitor.initialize(continueRunnable);
    }

    private void contentSyncAndAutoUpdateAndContinue() {
        ContentSyncService.get().scheduleSync();
        Libraries libraries = FinskyApp.get().getLibraries();
        if (FinskyApp.get().getSelfUpdateScheduler().isSelfUpdateRunning()) {
            logDeviceFeaturesAndContinue(true);
            return;
        }
        UpdateChecker updateChecker = new UpdateChecker(this, libraries, FinskyApp.get().getAppStates(), FinskyApp.get().getInstallPolicies(), FinskyApp.get().getInstaller(), FinskyApp.get().getNotifier());
        libraries.cleanup();
        updateChecker.checkForUpdates(new Runnable() {
            public void run() {
                DailyHygiene.this.logDeviceFeaturesAndContinue(true);
            }
        }, new Runnable() {
            public void run() {
                DailyHygiene.this.logDeviceFeaturesAndContinue(false);
            }
        }, "daily_hygiene", false);
    }

    private void logDeviceFeaturesAndContinue(boolean previousSuccess) {
        GmsDeviceFeaturesHelper gmsDeviceFeaturesHelper = new GmsDeviceFeaturesHelper(FinskyApp.get());
        FinskyLog.d("Logging device features", new Object[0]);
        gmsDeviceFeaturesHelper.fetchAndLogDeviceFeatures();
        flushEventLogsAndContinue(previousSuccess);
    }

    private void flushEventLogsAndContinue(boolean previousSuccess) {
        for (Account account : AccountHandler.getAccounts(FinskyApp.get())) {
            FinskyLog.d("Flushing event logs for %s", FinskyLog.scrubPii(account.name));
            FinskyApp.get().getEventLogger(account);
        }
        verifyInstalledPackagesAndContinue(previousSuccess);
    }

    private void verifyInstalledPackagesAndContinue(boolean previousSuccess) {
        if (((Boolean) G.verifyInstalledPackagesEnabled.get()).booleanValue()) {
            if (System.currentTimeMillis() > ((Long) FinskyPreferences.verifyInstalledPackagesLastSuccessMs.get()).longValue() + ((Long) G.verifyInstalledPackagesMinimumWaitMs.get()).longValue()) {
                VerifyInstalledPackagesReceiver.verifyInstalledPackages(this);
            }
        }
        submitUnsubmittedReviews(previousSuccess);
    }

    private void submitUnsubmittedReviews(boolean previousSuccess) {
        for (Account account : AccountHandler.getAccounts(FinskyApp.get())) {
            RateReviewHelper.updateUnsubmittedReviews(account.name, this);
        }
        ClientMutationCache.pruneUnsubmittedReviews(this);
        reschedule(previousSuccess);
    }

    private void reschedule(boolean previousSuccess) {
        long interval;
        if (previousSuccess) {
            if (((Long) FinskyPreferences.dailyHygieneLastSuccessMs.get()).longValue() == 0) {
                interval = jitter(REGULAR_INTERVAL_MS, INITIAL_SCHEDULE_JITTER_FACTOR);
                FinskyLog.d("Scheduling first run in %1.1f hours", Float.valueOf(((float) interval) / 3600000.0f));
            } else {
                interval = jitter(REGULAR_INTERVAL_MS, RESCHEDULE_JITTER_FACTOR);
            }
            FinskyPreferences.dailyHygieneLastSuccessMs.put(Long.valueOf(System.currentTimeMillis()));
            FinskyPreferences.lastReplicatedDatabaseVersion.put(Integer.valueOf(SQLiteLibrary.getVersion()));
        } else {
            int failures = ((Integer) FinskyPreferences.dailyHygieneFailedCount.get()).intValue() + 1;
            if (failures <= RETRY_INTERVALS.length) {
                interval = jitter(((long) RETRY_INTERVALS[failures - 1]) * RETRY_INTERVAL_MS, RESCHEDULE_JITTER_FACTOR);
                FinskyLog.d("Scheduling new run in %d minutes (failures=%d)", Long.valueOf(interval / 60000), Integer.valueOf(failures));
                FinskyPreferences.dailyHygieneFailedCount.put(Integer.valueOf(failures));
            } else {
                FinskyLog.d("Giving up. (failures=%d)", Integer.valueOf(failures));
                FinskyPreferences.dailyHygieneFailedCount.remove();
                interval = jitter(REGULAR_INTERVAL_MS, RESCHEDULE_JITTER_FACTOR);
            }
        }
        schedule(this, interval);
        stopSelf();
    }

    private static boolean needsDailyHygiene() {
        return ((Long) FinskyPreferences.dailyHygieneLastSuccessMs.get()).longValue() < System.currentTimeMillis() - REGULAR_INTERVAL_MS;
    }

    private static boolean emergencyDailyHygiene(int marketVersionCode) {
        int emergencyRangeLow = ((Integer) G.dailyHygieneImmediateRunVersionCodeLow.get()).intValue();
        int emergencyRangeHigh = ((Integer) G.dailyHygieneImmediateRunVersionCodeHigh.get()).intValue();
        if (emergencyRangeLow <= 0 || emergencyRangeHigh <= 0) {
            return false;
        }
        if (marketVersionCode < emergencyRangeLow || marketVersionCode > emergencyRangeHigh) {
            return false;
        }
        FinskyLog.w("Scheduling emergency daily hygiene, %d <= %d <= %d", Integer.valueOf(emergencyRangeLow), Integer.valueOf(marketVersionCode), Integer.valueOf(emergencyRangeHigh));
        return true;
    }

    private boolean inHoldoffPeriod() {
        SharedPreference<Boolean> dailyHygieneHoldoffCompletePreference = FinskyPreferences.dailyHygieneHoldoffComplete;
        if (((Boolean) dailyHygieneHoldoffCompletePreference.get()).booleanValue()) {
            return false;
        }
        if (isProvisioned()) {
            FinskyLog.d("No holdoff required - already provisioned", new Object[0]);
            dailyHygieneHoldoffCompletePreference.put(Boolean.valueOf(true));
            return false;
        } else if (((Long) G.dailyHygieneProvisionHoldoffMaxMs.get()).longValue() <= 0) {
            FinskyLog.d("No holdoff required - disabled", new Object[0]);
            dailyHygieneHoldoffCompletePreference.put(Boolean.valueOf(true));
            return false;
        } else {
            FinskyLog.d("DailyHygiene holdoff continue", new Object[0]);
            return true;
        }
    }

    public static void cancelHoldoffPeriod() {
        SharedPreference<Boolean> dailyHygieneHoldoffCompletePreference = FinskyPreferences.dailyHygieneHoldoffComplete;
        if (!((Boolean) dailyHygieneHoldoffCompletePreference.get()).booleanValue()) {
            FinskyLog.d("Canceling holdoff. Provisioned=%b", Boolean.valueOf(isProvisioned()));
            dailyHygieneHoldoffCompletePreference.put(Boolean.valueOf(true));
        }
    }

    private static boolean isProvisioned() {
        int value;
        ContentResolver resolver = FinskyApp.get().getContentResolver();
        if (VERSION.SDK_INT >= 17) {
            value = Global.getInt(resolver, "device_provisioned", 0);
        } else {
            value = Secure.getInt(resolver, "device_provisioned", 0);
        }
        if (value != 0) {
            return true;
        }
        return false;
    }

    private static boolean hasDatabaseVersionChanged() {
        return ((Integer) FinskyPreferences.lastReplicatedDatabaseVersion.get()).intValue() != SQLiteLibrary.getVersion();
    }

    private static long jitter(long baseValue, float jitterFactor) {
        return jitterLogic(baseValue, jitterFactor, (float) Math.random());
    }

    protected static long jitterLogic(long baseValue, float jitterFactor, float floatMathRandom) {
        return (long) (((float) baseValue) * (1.0f + ((2.0f * (floatMathRandom - 0.5f)) * jitterFactor)));
    }
}
