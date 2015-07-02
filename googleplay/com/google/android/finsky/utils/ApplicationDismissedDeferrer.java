package com.google.android.finsky.utils;

import android.app.ActivityManager;
import android.app.ActivityManager.RecentTaskInfo;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import java.util.List;

public class ApplicationDismissedDeferrer {
    private final Context mContext;

    public ApplicationDismissedDeferrer(Context context) {
        this.mContext = context;
    }

    public void runOnApplicationClose(final Runnable runnable, final int pollingIntervalMs) {
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            public void run() {
                if (ApplicationDismissedDeferrer.this.isContextPackageInBackground()) {
                    runnable.run();
                } else {
                    ApplicationDismissedDeferrer.this.runOnApplicationClose(runnable, pollingIntervalMs);
                }
            }
        }, (long) pollingIntervalMs);
    }

    private boolean isContextPackageInBackground() {
        List<RecentTaskInfo> tasks = ((ActivityManager) this.mContext.getSystemService("activity")).getRecentTasks(1, 0);
        if (tasks.size() == 0) {
            return true;
        }
        RecentTaskInfo task = (RecentTaskInfo) tasks.get(0);
        if (task.baseIntent == null || task.baseIntent.getComponent() == null || this.mContext.getPackageName().equals(task.baseIntent.getComponent().getPackageName())) {
            return false;
        }
        return true;
    }
}
