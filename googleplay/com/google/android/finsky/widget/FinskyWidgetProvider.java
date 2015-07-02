package com.google.android.finsky.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import com.android.vending.R;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.activities.MainActivity;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.config.G;
import com.google.android.finsky.protos.Common.Image;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.IntentUtils;
import com.google.android.finsky.utils.Maps;
import com.google.android.finsky.utils.image.ThumbnailUtils;
import com.google.android.finsky.widget.WidgetModel.ImageSelector;
import com.google.android.finsky.widget.WidgetModel.PromotionalItem;
import com.google.android.finsky.widget.WidgetModel.RefreshListener;
import com.google.android.play.image.BitmapLoader;
import java.util.HashMap;

public abstract class FinskyWidgetProvider extends BaseWidgetProvider {
    private static final HashMap<String, int[]> DOCUMENT_TYPES;
    private static final ImageSelector mImageSelector;
    private final BitmapLoader mBitmapLoader;
    private final HashMap<String, String> mUrls;

    static {
        mImageSelector = new ImageSelector() {
            public Image getImage(Document document, int maxHeight) {
                return ThumbnailUtils.getPromoBitmapFromDocument(document, 0, maxHeight);
            }

            public int getImageType(Document document) {
                return 2;
            }
        };
        DOCUMENT_TYPES = new HashMap<String, int[]>() {
            {
                put("apps", new int[]{1});
                put("books", new int[]{9, 5, 12});
                put("music", new int[]{2, 3, 4, 12});
                put("movies", new int[]{6, 12});
            }
        };
    }

    public FinskyWidgetProvider() {
        this.mUrls = Maps.newHashMap();
        this.mBitmapLoader = FinskyApp.get().getBitmapLoader();
        this.mUrls.put("apps", G.appsWidgetUrl.get());
        this.mUrls.put("books", G.booksWidgetUrl.get());
        this.mUrls.put("movies", G.moviesWidgetUrl.get());
        this.mUrls.put("music", G.musicWidgetUrl.get());
    }

    protected void updateWidgets(Context context, AppWidgetManager appWidgetManager, int... appWidgetIds) {
        if (appWidgetIds != null) {
            WidgetTypeMap typeMap = WidgetTypeMap.get(context);
            DfeApi dfeApi = FinskyApp.get().getDfeApi();
            for (int appWidgetId : appWidgetIds) {
                if (getBackend(typeMap, appWidgetId) == null) {
                    FinskyLog.d("Defaulting %d to %s", Integer.valueOf(appWidgetId), "apps");
                    typeMap.put(getClass(), appWidgetId, "apps");
                }
                WidgetModel widgetModel = new WidgetModel(this.mBitmapLoader, getValidDocumentTypes(typeMap, appWidgetId), mImageSelector, R.dimen.widget_image_max_height, 10);
                clearList(context, appWidgetId, typeMap, widgetModel);
                if (dfeApi == null) {
                    showError(context, appWidgetId);
                } else {
                    rebindWidget(context, appWidgetId, typeMap, widgetModel);
                    refreshList(context, appWidgetId, typeMap, widgetModel);
                }
            }
        }
    }

    private void clearList(Context context, int appWidgetId, WidgetTypeMap typeMap, WidgetModel widgetInfo) {
        widgetInfo.reset();
        rebindWidget(context, appWidgetId, typeMap, widgetInfo);
    }

    private String getDfeUrl(WidgetTypeMap typeMap, int appWidgetId) {
        return (String) this.mUrls.get(typeMap.get(getClass(), appWidgetId));
    }

    private void refreshList(Context context, int appWidgetId, WidgetTypeMap typeMap, WidgetModel widgetInfo) {
        DfeApi dfeApi = FinskyApp.get().getDfeApi();
        String dfeUrl = getDfeUrl(typeMap, appWidgetId);
        if (dfeUrl == null) {
            FinskyLog.d("%d has null URL", Integer.valueOf(appWidgetId));
            showError(context, appWidgetId);
            return;
        }
        final Context context2 = context;
        final int i = appWidgetId;
        final WidgetTypeMap widgetTypeMap = typeMap;
        final WidgetModel widgetModel = widgetInfo;
        widgetInfo.refresh(context, dfeApi, dfeUrl, new RefreshListener() {
            public void onError(String error) {
                FinskyLog.e("Failed to load list for widget! %s", error);
                FinskyWidgetProvider.this.showError(context2, i);
            }

            public void onData() {
                FinskyWidgetProvider.this.rebindWidget(context2, i, widgetTypeMap, widgetModel);
            }
        });
    }

    private void rebindWidget(Context context, int appWidgetId, WidgetTypeMap typeMap, WidgetModel widgetInfo) {
        RemoteViews widget = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
        if (widgetInfo.getItems().isEmpty()) {
            showEmptyState(context, widget);
        } else {
            widget.removeAllViews(R.id.widget_flipper);
            widget.setViewVisibility(R.id.widget_empty, 8);
            widget.setViewVisibility(R.id.widget_flipper, 0);
            for (PromotionalItem promo : widgetInfo.getItems()) {
                RemoteViews promoView = buildView(context, promo);
                promoView.setOnClickPendingIntent(R.id.widget_base, buildViewIntent(context, promo.doc));
                promoView.setImageViewResource(R.id.widget_logo, R.mipmap.ic_launcher_play_store);
                widget.addView(R.id.widget_flipper, promoView);
            }
        }
        AppWidgetManager.getInstance(context).updateAppWidget(appWidgetId, widget);
    }

    private RemoteViews buildView(Context context, PromotionalItem promo) {
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_item);
        remoteViews.setTextViewText(R.id.widget_title, promo.title);
        remoteViews.setTextViewText(R.id.widget_creator, promo.developer);
        remoteViews.setImageViewBitmap(R.id.widget_promo, promo.image);
        return remoteViews;
    }

    private PendingIntent buildViewIntent(Context context, Document doc) {
        Intent intent = IntentUtils.createViewDocumentIntent(context, doc);
        intent.addFlags(268435456);
        return PendingIntent.getActivity(context, 0, intent, 134217728);
    }

    private void showEmptyState(Context context, RemoteViews remoteViews) {
        remoteViews.setImageViewResource(R.id.app_icon, R.mipmap.ic_launcher_play_store);
        remoteViews.removeAllViews(R.id.widget_flipper);
        remoteViews.setViewVisibility(R.id.widget_empty, 0);
        remoteViews.setViewVisibility(R.id.widget_flipper, 8);
        remoteViews.setOnClickPendingIntent(R.id.widget_empty, getLaunchMarketIntent(context));
    }

    private void showError(Context context, int appWidgetId) {
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
        showEmptyState(context, remoteViews);
        AppWidgetManager.getInstance(context).updateAppWidget(appWidgetId, remoteViews);
    }

    private String getBackend(WidgetTypeMap typeMap, int appWidgetId) {
        return typeMap.get(getClass(), appWidgetId);
    }

    private int[] getValidDocumentTypes(WidgetTypeMap typeMap, int appWidgetId) {
        return (int[]) DOCUMENT_TYPES.get(getBackend(typeMap, appWidgetId));
    }

    protected static PendingIntent getLaunchMarketIntent(Context context) {
        return PendingIntent.getActivity(context, 0, new Intent(context, MainActivity.class), 134217728);
    }
}
