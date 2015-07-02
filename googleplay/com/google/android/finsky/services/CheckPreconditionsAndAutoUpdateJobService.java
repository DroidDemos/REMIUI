package com.google.android.finsky.services;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.PersistableBundle;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.appstate.UpdateChecker;
import com.google.android.finsky.gearhead.GearheadStateMonitor;
import com.google.android.finsky.utils.FinskyLog;

public class CheckPreconditionsAndAutoUpdateJobService extends JobService {
    public boolean onStartJob(final JobParameters jobParameters) {
        if (!jobParameters.isOverrideDeadlineExpired() || (getRetryReason(jobParameters) & 1) == 0) {
            FinskyLog.d("JobScheduler invoked, loading libraries.", new Object[0]);
            Runnable continueRunnable = new Runnable() {
                private int mLoaded;

                public void run() {
                    this.mLoaded++;
                    if (this.mLoaded == 3) {
                        CheckPreconditionsAndAutoUpdateJobService.this.onLoaded(jobParameters);
                    }
                }
            };
            FinskyApp.get().getLibraries().load(continueRunnable);
            FinskyApp.get().getAppStates().load(continueRunnable);
            GearheadStateMonitor.initialize(continueRunnable);
            return true;
        }
        FinskyLog.d("Timed out waiting for job to be scheduled.", new Object[0]);
        jobFinished(jobParameters, true);
        return false;
    }

    private void onLoaded(final JobParameters jobParameters) {
        if (jobParameters.isOverrideDeadlineExpired() || (getRetryReason(jobParameters) & 2) == 0 || !GearheadStateMonitor.isProjecting()) {
            FinskyApp finskyApp = FinskyApp.get();
            new UpdateChecker(finskyApp, finskyApp.getLibraries(), finskyApp.getAppStates(), finskyApp.getInstallPolicies(), finskyApp.getInstaller(), finskyApp.getNotifier()).checkForUpdates(new Runnable() {
                public void run() {
                    CheckPreconditionsAndAutoUpdateJobService.this.jobFinished(jobParameters, false);
                    FinskyLog.d("auto-updates finished successfully.", new Object[0]);
                }
            }, new Runnable() {
                public void run() {
                    CheckPreconditionsAndAutoUpdateJobService.this.jobFinished(jobParameters, false);
                    FinskyLog.d("finished w/error. waiting for next daily hygiene.", new Object[0]);
                }
            }, "wifi_checker", true);
            return;
        }
        jobFinished(jobParameters, true);
    }

    public boolean onStopJob(JobParameters jobParameters) {
        return false;
    }

    private int getRetryReason(JobParameters parameters) {
        PersistableBundle extras = parameters.getExtras();
        if (extras != null) {
            return extras.getInt("Finksy.AutoUpdateRetryReason", 0);
        }
        return 0;
    }
}
