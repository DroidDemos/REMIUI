package com.google.android.finsky.widget;

import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.os.Build.VERSION;
import com.android.vending.R;

public abstract class WidgetTrampolineActivity extends TrampolineActivity {
    protected abstract Class<? extends BaseWidgetProvider> getWidgetClass();

    public void finish(int resultCode, String type) {
        int appWidgetId = getIntent().getIntExtra("appWidgetId", 0);
        if (type != null) {
            WidgetTypeMap.get(this).put(getWidgetClass(), appWidgetId, type);
        }
        Intent result = new Intent();
        result.putExtra("appWidgetId", appWidgetId);
        setResult(resultCode, result);
        finish();
        int[] appWidgetIds = new int[]{appWidgetId};
        Intent intent = new Intent("android.appwidget.action.APPWIDGET_UPDATE");
        intent.putExtra("appWidgetIds", appWidgetIds);
        intent.setClass(this, getWidgetClass());
        sendBroadcast(intent);
        if (VERSION.SDK_INT >= 11) {
            AppWidgetManager.getInstance(this).notifyAppWidgetViewDataChanged(appWidgetIds, R.id.widget_flipper);
        }
    }
}
