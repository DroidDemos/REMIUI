package com.google.android.finsky.receivers;

import android.appwidget.AppWidgetManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import com.android.vending.MarketWidgetProvider;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.widget.consumption.NowPlayingWidgetProvider;
import com.google.android.finsky.widget.recommendation.RecommendedWidgetProvider;

public class UpdateWidgetsReceiver extends BroadcastReceiver {
    private static Class<?>[] COMPONENTS;

    static {
        COMPONENTS = new Class[]{MarketWidgetProvider.class, RecommendedWidgetProvider.class, NowPlayingWidgetProvider.class};
    }

    public void onReceive(Context context, Intent intent) {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        for (Class<?> component : COMPONENTS) {
            ComponentName componentName = new ComponentName(context, component);
            int[] appWidgetIds = appWidgetManager.getAppWidgetIds(componentName);
            Intent updateIntent = new Intent("android.appwidget.action.APPWIDGET_UPDATE");
            updateIntent.putExtra("appWidgetIds", appWidgetIds);
            updateIntent.setComponent(componentName);
            context.sendBroadcast(updateIntent);
            FinskyLog.d("Updated %d %s widgets (%s)", Integer.valueOf(appWidgetIds.length), component.getSimpleName(), intent.getAction());
        }
    }
}
