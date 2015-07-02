package com.google.android.finsky.widget.consumption;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.google.android.finsky.utils.IntentUtils;

public class EnableAppReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        int backendId = intent.getIntExtra("backendId", 0);
        context.getPackageManager().setApplicationEnabledSetting(IntentUtils.getPackageName(backendId), 1, 0);
        Intent updateIntent = new Intent("com.android.vending.action.APPWIDGET_UPDATE_CONSUMPTION_DATA");
        updateIntent.setClass(context, NowPlayingWidgetProvider.class);
        updateIntent.putExtra("backendId", backendId);
        context.sendBroadcast(updateIntent);
    }

    public static PendingIntent getEnableIntent(Context context, int backendId) {
        Intent intent = new Intent(context, EnableAppReceiver.class);
        intent.setData(Uri.parse("content://" + context.getPackageName() + "/enable/" + backendId));
        intent.putExtra("backendId", backendId);
        return PendingIntent.getBroadcast(context, 0, intent, 0);
    }
}
