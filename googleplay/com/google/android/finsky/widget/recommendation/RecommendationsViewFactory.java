package com.google.android.finsky.widget.recommendation;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService.RemoteViewsFactory;
import com.android.vending.R;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.library.Library;
import com.google.android.finsky.services.DismissRecommendationService;
import com.google.android.finsky.utils.CorpusResourceUtils;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.widget.AdvanceFlipperReceiver;
import com.google.android.finsky.widget.BaseWidgetProvider;
import com.google.android.finsky.widget.WidgetTypeMap;
import com.google.android.finsky.widget.WidgetUtils;
import com.google.android.play.image.BitmapLoader;
import java.util.concurrent.Semaphore;

public class RecommendationsViewFactory implements RemoteViewsFactory {
    private final int mAppWidgetId;
    private final BitmapLoader mBitmapLoader;
    private final int mContentHeightLandDp;
    private final int mContentHeightPortDp;
    private final Context mContext;
    private DfeApi mDfeApi;
    private RecommendationList mItems;
    private final Library mLibrary;
    private final int mMaxImageHeight;
    private final WidgetTypeMap mTypeMap;

    public RecommendationsViewFactory(Context context, int appWidgetId, int heightLandDp, int heightPortDp) {
        this.mContext = context;
        this.mAppWidgetId = appWidgetId;
        this.mContentHeightLandDp = heightLandDp;
        this.mContentHeightPortDp = heightPortDp;
        this.mLibrary = FinskyApp.get().getLibraries();
        this.mMaxImageHeight = context.getResources().getDimensionPixelSize(R.dimen.rec_widget_square_width);
        this.mBitmapLoader = FinskyApp.get().getBitmapLoader();
        this.mTypeMap = WidgetTypeMap.get(context);
    }

    public void onCreate() {
    }

    public void onDataSetChanged() {
        int widgetBackend = getWidgetBackend();
        if (widgetBackend == -1) {
            BaseWidgetProvider.update(this.mContext, RecommendedWidgetProvider.class, this.mAppWidgetId);
            return;
        }
        this.mDfeApi = FinskyApp.get().getDfeApi();
        this.mItems = getRecommendationItems(widgetBackend);
    }

    public void onDestroy() {
    }

    public int getCount() {
        return this.mItems != null ? this.mItems.size() : 0;
    }

    public RemoteViews getViewAt(int position) {
        int widgetBackend = getWidgetBackend();
        if (widgetBackend == -1) {
            BaseWidgetProvider.update(this.mContext, RecommendedWidgetProvider.class, this.mAppWidgetId);
            return null;
        }
        if (this.mItems == null) {
            this.mItems = getRecommendationItems(widgetBackend);
            if (this.mItems == null) {
                return null;
            }
        }
        if (position < this.mItems.size()) {
            return new RemoteViews(getChildViewAt(position, 2, widgetBackend), getChildViewAt(position, 1, widgetBackend));
        }
        FinskyLog.d("Item out of bounds (pos = %d, size = %d)", Integer.valueOf(position), Integer.valueOf(this.mItems.size()));
        return null;
    }

    private RemoteViews getChildViewAt(int position, int orientation, int widgetBackend) {
        Recommendation item = this.mItems.get(position);
        Bitmap bitmap = RecommendationsStore.getBitmap(this.mBitmapLoader, item, this.mMaxImageHeight);
        int layoutRes = getLayoutRes(this.mContext, item.getImageType(), widgetBackend, item.getBackend(), getLastMeasuredWidth(orientation), orientation == 2 ? this.mContentHeightLandDp : this.mContentHeightPortDp);
        RemoteViews child = new RemoteViews(this.mContext.getPackageName(), layoutRes);
        populateItem(this.mContext, child, item, bitmap, layoutRes);
        setIntents(this.mContext, child, item, position, this.mAppWidgetId, widgetBackend);
        return child;
    }

    public RemoteViews getLoadingView() {
        return new RemoteViews(this.mContext.getPackageName(), R.layout.rec_widget_loading_indicator);
    }

    public int getViewTypeCount() {
        return 4;
    }

    public long getItemId(int position) {
        return (long) position;
    }

    public boolean hasStableIds() {
        return true;
    }

    private RecommendationList getRecommendationItems(int widgetBackend) {
        return RecommendationsStore.getRecommendationsOrShowError(this.mContext, this.mDfeApi, widgetBackend, this.mAppWidgetId, this.mLibrary);
    }

    private int getLastMeasuredWidth(int orientation) {
        return AppWidgetManager.getInstance(this.mContext).getAppWidgetOptions(this.mAppWidgetId).getInt(orientation == 1 ? "appWidgetMinWidth" : "appWidgetMaxWidth");
    }

    private int getWidgetBackend() {
        final Semaphore lock = new Semaphore(0);
        final int[] backend = new int[1];
        Runnable runnable = new Runnable() {
            public void run() {
                String widgetType = RecommendationsViewFactory.this.mTypeMap.get(RecommendedWidgetProvider.class, RecommendationsViewFactory.this.mAppWidgetId);
                if (widgetType == null) {
                    backend[0] = -1;
                } else {
                    backend[0] = WidgetUtils.translate(widgetType);
                }
                lock.release();
            }
        };
        if (Looper.getMainLooper() != Looper.myLooper()) {
            new Handler(Looper.getMainLooper()).post(runnable);
            lock.acquireUninterruptibly();
        }
        return backend[0];
    }

    private static void populateItem(Context context, RemoteViews views, Recommendation item, Bitmap bitmap, int layoutRes) {
        Document document = item.getDocument();
        views.setTextViewText(R.id.widget_title, document.getTitle());
        views.setTextViewText(R.id.widget_creator, document.getCreator());
        views.setTextViewText(R.id.widget_reason, document.getDescriptionReason());
        views.setImageViewBitmap(R.id.widget_promo, bitmap);
        views.setImageViewResource(R.id.widget_strip, CorpusResourceUtils.getRecommendationWidgetStripResourceId(document.getBackend()));
        views.setContentDescription(R.id.widget_title, context.getString(CorpusResourceUtils.getTitleContentDescriptionResourceId(context.getResources(), document.getDocumentType()), new Object[]{document.getTitle()}));
    }

    private static void setIntents(Context context, RemoteViews views, Recommendation item, int itemIndex, int appWidgetId, int widgetBackendId) {
        views.setOnClickPendingIntent(R.id.widget_content_accessibility, OpenRecommendationReceiver.getIntent(context, item.getDocument(), widgetBackendId, appWidgetId));
        views.setOnClickPendingIntent(R.id.widget_refresh, AdvanceFlipperReceiver.getIntent(context, appWidgetId));
        views.setOnClickPendingIntent(R.id.widget_dismiss, DismissRecommendationService.getDismissPendingIntent(context, appWidgetId, item.getDocument(), widgetBackendId, itemIndex));
    }

    private static int getLayoutRes(Context context, int imageType, int widgetBackend, int itemBackend, int widthInDips, int contentHeightInDips) {
        int promoBreakpointDips = (int) (2.5d * ((double) contentHeightInDips));
        switch (widgetBackend) {
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                switch (itemBackend) {
                    case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_required /*4*/:
                    case com.google.android.wallet.instrumentmanager.R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                        return R.layout.rec_widget_side_by_side_portrait;
                    case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                        return R.layout.rec_widget_side_by_side_square;
                    default:
                        switch (imageType) {
                            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                                if (widthInDips >= promoBreakpointDips) {
                                    return R.layout.rec_widget_side_by_side_promo;
                                }
                                return R.layout.rec_widget_full_width_promo;
                            default:
                                return R.layout.rec_widget_side_by_side_square;
                        }
                }
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_required /*4*/:
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                return R.layout.rec_widget_side_by_side_portrait;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                return R.layout.rec_widget_side_by_side_square;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                if (imageType != 2) {
                    return R.layout.rec_widget_side_by_side_square;
                }
                if (widthInDips < promoBreakpointDips) {
                    return R.layout.rec_widget_full_width_promo;
                }
                return R.layout.rec_widget_side_by_side_promo;
            default:
                throw new IllegalArgumentException("Invalid backend: " + widgetBackend);
        }
    }

    public static void notifyDataSetChanged(Context context, int... appWidgetIds) {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        if (appWidgetIds == null || appWidgetIds.length == 0) {
            appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(context, RecommendedWidgetProvider.class));
        }
        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.widget_flipper);
    }
}
