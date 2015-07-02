package com.google.android.finsky.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;
import com.android.vending.R;

public class AdvanceFlipperReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        advance(context, intent.getIntExtra("appWidgetId", 0));
    }

    public static void advance(Context context, int appWidgetId) {
        if (appWidgetId != 0) {
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.rec_widget_base);
            views.showNext(R.id.widget_flipper);
            AppWidgetManager.getInstance(context).partiallyUpdateAppWidget(appWidgetId, views);
        }
    }

    public static PendingIntent getIntent(Context context, int appWidgetId) {
        Intent intent = new Intent(context, AdvanceFlipperReceiver.class);
        intent.setData(Uri.parse("content://widget/advance/" + appWidgetId));
        intent.putExtra("appWidgetId", appWidgetId);
        return PendingIntent.getBroadcast(context, 0, intent, 0);
    }
}
