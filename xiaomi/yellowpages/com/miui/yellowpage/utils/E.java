package com.miui.yellowpage.utils;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* compiled from: Download */
final class E extends BroadcastReceiver {
    final /* synthetic */ DownloadManager DP;
    final /* synthetic */ long val$id;

    E(long j, DownloadManager downloadManager) {
        this.val$id = j;
        this.DP = downloadManager;
    }

    public void onReceive(Context context, Intent intent) {
        long longExtra = intent.getLongExtra("extra_download_id", -1);
        if (this.val$id == longExtra && "android.intent.action.DOWNLOAD_COMPLETE".equals(intent.getAction())) {
            z.a(this.DP, context, longExtra);
        }
    }
}
