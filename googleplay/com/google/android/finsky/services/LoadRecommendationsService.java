package com.google.android.finsky.services;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.widget.BaseWidgetProvider;
import com.google.android.finsky.widget.recommendation.RecommendationList;
import com.google.android.finsky.widget.recommendation.RecommendationsStore;
import com.google.android.finsky.widget.recommendation.RecommendedWidgetProvider;

public class LoadRecommendationsService extends IntentService {
    public LoadRecommendationsService() {
        super(LoadRecommendationsService.class.getSimpleName());
    }

    protected void onHandleIntent(Intent intent) {
        int backendId = intent.getIntExtra("backendId", -1);
        int appWidgetId = intent.getIntExtra("appWidgetId", 0);
        DfeApi dfeApi = FinskyApp.get().getDfeApi();
        if (dfeApi == null) {
            BaseWidgetProvider.update(this, RecommendedWidgetProvider.class, appWidgetId);
            return;
        }
        RecommendationList recList = RecommendationsStore.getRecommendationsOrShowError(this, dfeApi, backendId, appWidgetId, FinskyApp.get().getLibraries());
        if (recList != null) {
            RecommendedWidgetProvider.showData(this, appWidgetId, recList.getTitle(), backendId);
        }
    }

    public static void load(Context context, int appWidgetId, int backendId) {
        Intent intent = new Intent(context, LoadRecommendationsService.class);
        intent.setData(Uri.parse("content://market/load/for/" + appWidgetId));
        intent.putExtra("backendId", backendId);
        intent.putExtra("appWidgetId", appWidgetId);
        context.startService(intent);
    }
}
