package com.google.android.finsky.services;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.job.JobInfo.Builder;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.PersistableBundle;
import android.os.SystemClock;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.config.G;
import com.google.android.finsky.utils.FinskyLog;

public class CheckPreconditionsAndAutoUpdateScheduler {
    public static void scheduleCheck(int scheduleReason) {
        Context context = FinskyApp.get();
        if (isJobSchedulerEnabled()) {
            scheduleJob(context, scheduleReason);
            return;
        }
        AlarmManager am = (AlarmManager) context.getSystemService("alarm");
        PendingIntent pendingIntent = getPendingIntent();
        Long wifiCheckIntervalMs = (Long) G.autoUpdateWifiCheckIntervalMs.get();
        if (wifiCheckIntervalMs.longValue() > 0) {
            am.set(3, SystemClock.elapsedRealtime() + wifiCheckIntervalMs.longValue(), pendingIntent);
        }
        FinskyLog.d("Scheduling auto-update wifi check using AlarmManager.", new Object[0]);
    }

    private static void scheduleJob(Context context, int scheduleReason) {
        JobScheduler jobScheduler = (JobScheduler) context.getSystemService("jobscheduler");
        Builder builder = new Builder(821848294, new ComponentName(context, CheckPreconditionsAndAutoUpdateJobService.class));
        if ((scheduleReason & 2) != 0) {
            long minDelayMs = ((Long) G.autoUpdateJobSchedulerGearheadDelayMs.get()).longValue();
            builder.setMinimumLatency(minDelayMs);
            builder.setBackoffCriteria(minDelayMs, 1);
            builder.setOverrideDeadline(((Long) G.autoUpdateJobSchedulerGearheadTimeoutMs.get()).longValue());
        } else {
            builder.setRequiredNetworkType(2).setRequiresCharging(true).setOverrideDeadline(((Long) G.autoUpdateJobSchedulerTimeoutMs.get()).longValue());
        }
        PersistableBundle extras = new PersistableBundle();
        extras.putInt("Finksy.AutoUpdateRetryReason", scheduleReason);
        builder.setExtras(extras);
        jobScheduler.schedule(builder.build());
        FinskyLog.d("Scheduling auto-update check using JobScheduler.", new Object[0]);
    }

    public static void cancelCheck() {
        FinskyApp context = FinskyApp.get();
        if (VERSION.SDK_INT >= 21) {
            ((JobScheduler) context.getSystemService("jobscheduler")).cancel(821848294);
        }
        ((AlarmManager) context.getSystemService("alarm")).cancel(getPendingIntent());
        FinskyLog.d("Canceling auto-update wifi check.", new Object[0]);
    }

    private static PendingIntent getPendingIntent() {
        return PendingIntent.getService(FinskyApp.get(), 0, new Intent(FinskyApp.get(), CheckWifiAndAutoUpdate.class), 0);
    }

    public static boolean isJobSchedulerEnabled() {
        return VERSION.SDK_INT >= 21 && ((Long) G.autoUpdateJobSchedulerTimeoutMs.get()).longValue() > 0;
    }
}
