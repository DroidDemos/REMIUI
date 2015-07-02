package com.google.android.finsky.widget.recommendation;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.RemoteViews;
import com.android.vending.R;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.api.model.DfeToc;
import com.google.android.finsky.protos.Toc.CorpusMetadata;
import com.google.android.finsky.services.LoadRecommendationsService;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.IntentUtils;
import com.google.android.finsky.widget.BaseWidgetProvider;
import com.google.android.finsky.widget.TrampolineActivity;
import com.google.android.finsky.widget.WidgetTypeMap;
import com.google.android.finsky.widget.WidgetUtils;

public class RecommendedWidgetProvider extends BaseWidgetProvider {
    private static final int[] VIEW_IDS;

    static {
        VIEW_IDS = new int[]{R.id.page_error_indicator, R.id.widget_flipper, R.id.widget_background_accessibility, R.id.interactive_container, R.id.empty_container, R.id.widget_loading_indicator};
    }

    public void onAppWidgetOptionsChanged(Context context, AppWidgetManager appWidgetManager, int appWidgetId, Bundle newOptions) {
        updateWidgets(context, appWidgetManager, appWidgetId);
        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetId, R.id.widget_flipper);
    }

    protected void updateWidgets(Context context, AppWidgetManager manager, int... appWidgetIds) {
        DfeApi dfeApi = FinskyApp.get().getDfeApi();
        for (int appWidgetId : appWidgetIds) {
            if (dfeApi == null) {
                showAccountsNeeded(context, appWidgetId);
            } else {
                String type = WidgetTypeMap.get(context).get(RecommendedWidgetProvider.class, appWidgetId);
                if (type == null) {
                    showConfigurationNeeded(context, appWidgetId);
                } else {
                    int backendId = WidgetUtils.translate(type);
                    manager.updateAppWidget(appWidgetId, getBaseWithVisibleViews(context, R.id.widget_loading_indicator));
                    LoadRecommendationsService.load(context, appWidgetId, backendId);
                }
            }
        }
    }

    protected int getWidgetClassId() {
        return 2;
    }

    public static void showData(Context context, int appWidgetId, String title, int backendId) {
        AppWidgetManager.getInstance(context).updateAppWidget(appWidgetId, getWidgetWithTitle(context, title, appWidgetId, backendId));
        AppWidgetManager.getInstance(context).notifyAppWidgetViewDataChanged(appWidgetId, R.id.widget_flipper);
    }

    public static void showError(Context context, int appWidgetId, String errorMessage) {
        RemoteViews views = getBaseWithVisibleViews(context, R.id.page_error_indicator);
        views.setTextViewText(R.id.error_msg, errorMessage);
        AppWidgetManager.getInstance(context).updateAppWidget(appWidgetId, views);
    }

    private static void showConfigurationNeeded(Context context, int appWidgetId) {
        showInteractiveError(context, appWidgetId, R.string.widget_configuration_needed, TrampolineActivity.getPendingLaunchIntent(context, RecommendedTrampoline.class, appWidgetId));
    }

    private static void showAccountsNeeded(Context context, int appWidgetId) {
        showInteractiveError(context, appWidgetId, R.string.auth_required_error, BaseWidgetProvider.getAddAccountIntent(context));
    }

    private static void showInteractiveError(Context context, int appWidgetId, int errorMsgResId, PendingIntent operation) {
        RemoteViews views = getBaseWithVisibleViews(context, R.id.interactive_container, R.id.widget_background_accessibility);
        views.setTextViewText(R.id.interactive_error_msg, context.getString(errorMsgResId));
        views.setOnClickPendingIntent(R.id.widget_background_accessibility, operation);
        AppWidgetManager.getInstance(context).updateAppWidget(appWidgetId, views);
    }

    private static RemoteViews getWidgetWithTitle(Context context, String title, int appWidgetId, int backendId) {
        RemoteViews views = getBaseWithVisibleViews(context, R.id.widget_flipper);
        views.setEmptyView(R.id.widget_flipper, R.id.empty_container);
        int[] dimens = BaseWidgetProvider.getBoundingBoxes(context, appWidgetId);
        int titleHeight = WidgetUtils.getDips(context, R.dimen.now_playing_title_height);
        int landscapeHeight = dimens[1] - titleHeight;
        int portraitHeight = dimens[3] - titleHeight;
        Intent intent = new Intent(context, RecommendationsViewService.class);
        intent.setData(Uri.parse("content://market/factory/for/" + appWidgetId));
        intent.putExtra("appWidgetId", appWidgetId);
        intent.putExtra("RecWidget.heightLandscape", landscapeHeight);
        intent.putExtra("RecWidget.heightPortrait", portraitHeight);
        views.setRemoteAdapter(R.id.widget_flipper, intent);
        if (!TextUtils.isEmpty(title)) {
            views.setTextViewText(R.id.widget_title, title.toUpperCase());
            String browseUrl = getRecommendationsBrowseUrl(backendId);
            if (TextUtils.isEmpty(browseUrl)) {
                FinskyLog.e("No browse URL found for backend=%s", Integer.valueOf(backendId));
            } else {
                views.setOnClickPendingIntent(R.id.widget_title_accessibility_overlay, PendingIntent.getActivity(context, 0, IntentUtils.createBrowseIntent(context, browseUrl, title, backendId, true), 0));
                views.setViewVisibility(R.id.widget_title_accessibility_overlay, 0);
                views.setContentDescription(R.id.widget_title_accessibility_overlay, title);
            }
        }
        return views;
    }

    private static String getRecommendationsBrowseUrl(int backendId) {
        DfeToc toc = FinskyApp.get().getToc();
        if (toc == null) {
            return null;
        }
        if (backendId == 0) {
            return toc.getHomeUrl();
        }
        CorpusMetadata metadata = toc.getCorpus(backendId);
        if (metadata != null) {
            return metadata.landingUrl;
        }
        return null;
    }

    private static RemoteViews getBaseWithVisibleViews(Context context, int... visibleIds) {
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.rec_widget_base);
        views.setImageViewResource(R.id.widget_title_icon, R.drawable.ic_play_widgets_store);
        views.setTextViewText(R.id.widget_title, "");
        views.setContentDescription(R.id.widget_title_icon, "");
        for (int viewId : VIEW_IDS) {
            views.setViewVisibility(viewId, 8);
        }
        for (int viewId2 : visibleIds) {
            views.setViewVisibility(viewId2, 0);
        }
        return views;
    }
}
