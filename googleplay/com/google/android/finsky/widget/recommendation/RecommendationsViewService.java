package com.google.android.finsky.widget.recommendation;

import android.content.Intent;
import android.widget.RemoteViewsService;
import android.widget.RemoteViewsService.RemoteViewsFactory;

public class RecommendationsViewService extends RemoteViewsService {
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new RecommendationsViewFactory(this, intent.getIntExtra("appWidgetId", 0), intent.getIntExtra("RecWidget.heightLandscape", 0), intent.getIntExtra("RecWidget.heightPortrait", 0));
    }
}
