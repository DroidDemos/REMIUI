package com.google.android.finsky.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.widget.recommendation.RecommendationList;
import com.google.android.finsky.widget.recommendation.RecommendationsStore;

public class FetchRecommendationsReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        int backendId = intent.getIntExtra("backendId", -1);
        int appWidgetId = intent.getIntExtra("appWidgetId", 0);
        RecommendationsStore.performBackFill(FinskyApp.get().getDfeApi(), context, new RecommendationList("", backendId), FinskyApp.get().getLibraries(), appWidgetId);
    }
}
