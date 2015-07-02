package com.google.android.finsky.receivers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.analytics.PlayStore.AppData;
import com.google.android.finsky.download.DownloadReceiver;
import com.google.android.finsky.utils.FinskyLog;

public class DownloadTickleReceiver extends DownloadReceiver {
    public void onReceive(Context context, final Intent intent) {
        if (intent.getAction().equals("com.google.android.c2dm.intent.RECEIVE")) {
            setResultCode(-1);
            if (intent.getStringExtra("from").equals("google.com")) {
                FinskyApp.get().getAppStates().load(new Runnable() {
                    public void run() {
                        DownloadTickleReceiver.this.finishOnReceive(intent);
                    }
                });
            }
        }
    }

    private void finishOnReceive(Intent intent) {
        FinskyApp finskyApp = FinskyApp.get();
        Bundle bundle = intent.getExtras();
        boolean serverInitiated = bundle.containsKey("server_initiated");
        int index = getApplicationCount(bundle) - 1;
        while (index >= 0) {
            String suffix = index == 0 ? "" : "_" + index;
            String title = bundle.getString("asset_name" + suffix);
            String packageName = bundle.getString("asset_package" + suffix);
            if (serverInitiated) {
                int packageVersion = Integer.parseInt(bundle.getString("asset_version_code" + suffix));
                String account = bundle.getString("user_email" + suffix);
                AppData appData = new AppData();
                appData.version = packageVersion;
                appData.hasVersion = true;
                finskyApp.getEventLogger().logBackgroundEvent(201, packageName, null, 0, null, appData);
                finskyApp.getInstaller().requestInstall(packageName, packageVersion, account, title, false, "tickle", 2);
            } else {
                FinskyLog.d("Ignoring download tickle with !server_initiated: pkg=%s", packageName);
            }
            index--;
        }
    }

    private int getApplicationCount(Bundle bundle) {
        int appCount = 0;
        do {
            appCount++;
        } while (bundle.getString("assetid_" + appCount) != null);
        return appCount;
    }
}
