package com.xiaomi.activate;

import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.PendingIntent.CanceledException;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import com.xiaomi.xmsf.R;
import miui.telephony.CloudTelephonyManager;

public class ActivateProgressController {
    private final PendingIntent[] mActivateStatusCallbacks;
    private final int[] mActivateStatusDescs;
    private final Context mContext;
    private final Handler mHandler;
    private volatile boolean[] mQuiet;

    public ActivateProgressController(Context context) {
        this.mActivateStatusCallbacks = new PendingIntent[2];
        this.mActivateStatusDescs = new int[]{-1, -1};
        this.mHandler = new Handler();
        this.mQuiet = new boolean[2];
        this.mContext = context;
    }

    public void setQuiet(int simIndex, boolean quiet) {
        this.mQuiet[simIndex] = quiet;
    }

    public void reportActivateStatus(int simIndex, int desc, boolean failed, PendingIntent pi) {
        if (!this.mQuiet[simIndex]) {
            final int i = simIndex;
            final int i2 = desc;
            final boolean z = failed;
            final PendingIntent pendingIntent = pi;
            this.mHandler.post(new Runnable() {
                public void run() {
                    ActivateProgressController.this.mActivateStatusDescs[i] = i2;
                    if (ActivateProgressController.this.mActivateStatusCallbacks[i] != null) {
                        ActivateProgressController.this.invokeCallback(ActivateProgressController.this.mActivateStatusCallbacks[i], i2);
                    } else {
                        ActivateProgressController.this.notifyActivateStatus(i, i2, z, pendingIntent);
                    }
                }
            });
        }
    }

    private void notifyActivateStatus(int simIndex, int desc, boolean failed, PendingIntent pi) {
        NotificationManager nm = (NotificationManager) this.mContext.getSystemService("notification");
        if (desc != -1) {
            String title;
            if (CloudTelephonyManager.getAvailableSimCount() >= 2) {
                if (failed) {
                    title = this.mContext.getString(R.string.activate_phone_failed_notif_title_msim, new Object[]{Integer.valueOf(simIndex + 1)});
                } else {
                    title = this.mContext.getString(R.string.activate_status_title_activating_msim, new Object[]{Integer.valueOf(simIndex + 1)});
                }
            } else if (failed) {
                title = this.mContext.getString(R.string.activate_phone_failed_notif_title);
            } else {
                title = this.mContext.getString(R.string.activate_status_title_activating);
            }
            Builder builder = new Builder(this.mContext);
            builder.setContentTitle(title);
            builder.setContentText(this.mContext.getString(desc));
            builder.setSmallIcon(ActivateExternal.getSysInteface().getAppIconRes());
            builder.setAutoCancel(true);
            builder.setWhen(System.currentTimeMillis());
            builder.setContentIntent(pi);
            nm.notify(simIndex + 2, builder.build());
            return;
        }
        nm.cancel(simIndex + 2);
    }

    private void invokeCallback(PendingIntent callback, int desc) {
        try {
            callback.send(this.mContext, 0, new Intent().putExtra("message", desc == -1 ? null : this.mContext.getString(desc)));
        } catch (CanceledException e) {
        }
    }

    public PendingIntent setActivateStatusCallback(int simIndex, PendingIntent callback) {
        PendingIntent oldCallback = this.mActivateStatusCallbacks[simIndex];
        if (callback != oldCallback) {
            this.mActivateStatusCallbacks[simIndex] = callback;
            if (callback != null) {
                invokeCallback(callback, this.mActivateStatusDescs[simIndex]);
            } else {
                notifyActivateStatus(simIndex, this.mActivateStatusDescs[simIndex], false, null);
            }
            if (oldCallback != null) {
                invokeCallback(oldCallback, -1);
            } else {
                notifyActivateStatus(simIndex, -1, false, null);
            }
        }
        return oldCallback;
    }
}
