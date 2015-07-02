package com.google.android.finsky.receivers;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.appstate.InstallerDataStore.InstallerData;
import com.google.android.finsky.config.G;
import com.google.android.finsky.utils.FinskyLog;

public class ExpireLaunchUrlReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        FinskyApp.get().getInstallerDataStore().setContinueUrl(intent.getExtras().getString("package_name"), null);
        FinskyLog.d("Removed expired continue URL", new Object[0]);
    }

    public static void cancel(Context context, String packageName) {
        if (shouldSetAlarm(packageName)) {
            ((AlarmManager) context.getSystemService("alarm")).cancel(createPendingIntent(context, packageName));
        }
    }

    public static void schedule(Context context, String packageName) {
        if (shouldSetAlarm(packageName)) {
            ((AlarmManager) context.getSystemService("alarm")).set(1, System.currentTimeMillis() + ((Long) G.continueUrlExpirationMs.get()).longValue(), createPendingIntent(context, packageName));
        }
    }

    private static boolean shouldSetAlarm(String packageName) {
        return VERSION.SDK_INT < 13 && hasContinueUrl(packageName);
    }

    private static boolean hasContinueUrl(String packageName) {
        InstallerData data = FinskyApp.get().getInstallerDataStore().get(packageName);
        return (data == null || TextUtils.isEmpty(data.getContinueUrl())) ? false : true;
    }

    private static PendingIntent createPendingIntent(Context context, String packageName) {
        Intent intent = new Intent(context, ExpireLaunchUrlReceiver.class);
        intent.putExtra("package_name", packageName);
        return PendingIntent.getBroadcast(context, 1, intent, 1073741824);
    }
}
