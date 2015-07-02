package com.google.android.finsky.widget.consumption;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.util.SparseIntArray;
import android.widget.RemoteViews;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.activities.MainActivity;
import com.google.android.finsky.api.model.DfeToc;
import com.google.android.finsky.config.G;
import com.google.android.finsky.protos.Toc.CorpusMetadata;
import com.google.android.finsky.services.ConsumptionAppDataCache;
import com.google.android.finsky.services.ConsumptionAppDoc;
import com.google.android.finsky.services.LoadConsumptionDataService;
import com.google.android.finsky.utils.CorpusResourceUtils;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.FinskyPreferences;
import com.google.android.finsky.utils.IntentUtils;
import com.google.android.finsky.utils.Lists;
import com.google.android.finsky.utils.Maps;
import com.google.android.finsky.widget.BaseWidgetProvider;
import com.google.android.finsky.widget.TrampolineActivity;
import com.google.android.finsky.widget.WidgetTypeMap;
import com.google.android.finsky.widget.WidgetUtils;
import com.google.android.finsky.widget.consumption.BatchedImageLoader.BatchedImageCallback;
import com.google.android.finsky.widget.consumption.ImageBatch.ImageSpec;
import com.google.android.finsky.widget.consumption.NowPlayingArranger.Arrangement;
import com.google.android.wallet.instrumentmanager.R;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

public class NowPlayingWidgetProvider extends BaseWidgetProvider {
    private static final int[] ACCESSIBILITY_OVERLAY_IDS;
    private static final int[] BACKENDS;
    private static final File BG_IMAGE_OVERRIDE;
    private static final String BG_OVERRIDE_IMAGE_PATH;
    private static final int[] CONTAINERS;
    private static final int[] IMAGE_IDS;
    private static final Block[] PORTRAIT_BLOCKS;
    private static final Block PORTRAIT_LARGE_REPLACEMENT;
    private static final Block[] SQUARE_BLOCKS;
    public static BatchedImageLoader mImageLoader;
    private static final Random sRandom;
    private static int[] sSupportedBackendIds;
    private final SparseIntArray mRowStartCounts;

    public static class HotseatDataHolder {
        private boolean mHasDismissedHotseat;
        private boolean mIsBooksChecked;
        private boolean mIsMagazinesChecked;
        private boolean mIsMoviesChecked;
        private boolean mIsMusicChecked;

        public boolean isBackendChecked(int backendId) {
            switch (backendId) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    return this.mIsBooksChecked;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    return this.mIsMusicChecked;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    return this.mIsMoviesChecked;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    return this.mIsMagazinesChecked;
                default:
                    return true;
            }
        }

        public static HotseatDataHolder loadDataFromDisk(int appWidgetId) {
            HotseatDataHolder output = new HotseatDataHolder();
            String serializedData = (String) FinskyPreferences.libraryWidgetData.get(appWidgetId).get();
            if (!TextUtils.isEmpty(serializedData)) {
                String[] pairStrs = serializedData.split("&");
                for (String split : pairStrs) {
                    String[] pair = split.split(",");
                    if (!(pair == null || pair.length != 2 || TextUtils.isEmpty(pair[0]) || TextUtils.isEmpty(pair[1]))) {
                        String key = pair[0];
                        boolean value = Boolean.parseBoolean(pair[1]);
                        if ("d".equals(key)) {
                            output.mHasDismissedHotseat = value;
                        } else if ("mu".equals(key)) {
                            output.mIsMusicChecked = value;
                        } else if ("ma".equals(key)) {
                            output.mIsMagazinesChecked = value;
                        } else if ("mo".equals(key)) {
                            output.mIsMoviesChecked = value;
                        } else if ("b".equals(key)) {
                            output.mIsBooksChecked = value;
                        } else {
                            FinskyLog.e("Malformed data detected in widget pref, ignoring.", new Object[0]);
                        }
                    }
                }
            }
            return output;
        }

        public static void writeBackendChecked(int appWidgetId, int backendId) {
            HotseatDataHolder wrapper = loadDataFromDisk(appWidgetId);
            switch (backendId) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    wrapper.mIsBooksChecked = true;
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    wrapper.mIsMusicChecked = true;
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    wrapper.mIsMoviesChecked = true;
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    wrapper.mIsMagazinesChecked = true;
                    break;
            }
            flushData(appWidgetId, wrapper);
        }

        public static void writeHotseatDismissed(int appWidgetId) {
            HotseatDataHolder wrapper = loadDataFromDisk(appWidgetId);
            wrapper.mHasDismissedHotseat = true;
            flushData(appWidgetId, wrapper);
        }

        private static void flushData(int appWidgetId, HotseatDataHolder wrapper) {
            FinskyPreferences.libraryWidgetData.get(appWidgetId).put((((("" + "d," + Boolean.toString(wrapper.mHasDismissedHotseat)) + "&ma," + Boolean.toString(wrapper.mIsMagazinesChecked)) + "&mo," + Boolean.toString(wrapper.mIsMoviesChecked)) + "&mu," + Boolean.toString(wrapper.mIsMusicChecked)) + "&b," + Boolean.toString(wrapper.mIsBooksChecked));
            FinskyLog.d("Writing data for widget=%d data=%s", Integer.valueOf(appWidgetId), output);
        }
    }

    private class ViewTreeWrapper {
        private int heightRemaining;
        private boolean mLoadedSuccessfully;
        private RemoteViews mRemoteViews;
        private List<ImageSpec> mUris;
        private boolean showBackground;
        private boolean showEmptyBackground;

        static /* synthetic */ boolean access$376(ViewTreeWrapper x0, int x1) {
            boolean z = (byte) (x0.showBackground | x1);
            x0.showBackground = z;
            return z;
        }

        static /* synthetic */ boolean access$472(ViewTreeWrapper x0, int x1) {
            boolean z = (byte) (x0.showEmptyBackground & x1);
            x0.showEmptyBackground = z;
            return z;
        }

        public ViewTreeWrapper(RemoteViews views, boolean success, List<ImageSpec> uris) {
            this.mLoadedSuccessfully = true;
            this.mUris = Lists.newArrayList();
            this.showBackground = false;
            this.showEmptyBackground = false;
            this.mRemoteViews = views;
            this.mLoadedSuccessfully = success;
            this.mUris = uris;
        }

        public ViewTreeWrapper() {
            this.mLoadedSuccessfully = true;
            this.mUris = Lists.newArrayList();
            this.showBackground = false;
            this.showEmptyBackground = false;
        }
    }

    private class WidgetLayout extends ArrayList<List<Block>> {
        final int heightRemaining;
        final boolean showBackground;

        public WidgetLayout(List<List<Block>> rows, boolean showBackground, int heightRemaining) {
            this.showBackground = showBackground;
            this.heightRemaining = heightRemaining;
            if (rows != null) {
                addAll(rows);
            }
        }
    }

    public NowPlayingWidgetProvider() {
        this.mRowStartCounts = new SparseIntArray();
    }

    static {
        BG_OVERRIDE_IMAGE_PATH = (String) G.myLibraryWidgetBackgroundImageLocation.get();
        BG_IMAGE_OVERRIDE = TextUtils.isEmpty(BG_OVERRIDE_IMAGE_PATH) ? null : new File(BG_OVERRIDE_IMAGE_PATH);
        sRandom = new Random();
        sSupportedBackendIds = new int[]{2, 4, 1, 6};
        IMAGE_IDS = new int[]{com.android.vending.R.id.large_image_1, com.android.vending.R.id.large_image_2, com.android.vending.R.id.large_image_3, com.android.vending.R.id.large_image_4};
        ACCESSIBILITY_OVERLAY_IDS = new int[]{com.android.vending.R.id.accessibility_overlay_1, com.android.vending.R.id.accessibility_overlay_2, com.android.vending.R.id.accessibility_overlay_3, com.android.vending.R.id.accessibility_overlay_4};
        BACKENDS = new int[]{1, 4, 2, 6};
        CONTAINERS = new int[]{com.android.vending.R.id.widget_content_1, com.android.vending.R.id.widget_content_2, com.android.vending.R.id.widget_content_3, com.android.vending.R.id.widget_content_4};
        PORTRAIT_LARGE_REPLACEMENT = new Block(com.android.vending.R.layout.now_playing_all_quad_portrait_small).sized(com.android.vending.R.dimen.now_playing_all_portrait_large_width, com.android.vending.R.dimen.now_playing_all_portrait_large_height).hosting(4, com.android.vending.R.dimen.now_playing_all_portrait_small_width, com.android.vending.R.dimen.now_playing_all_portrait_small_height);
        PORTRAIT_BLOCKS = new Block[]{new Block(com.android.vending.R.layout.now_playing_all_portrait_large).sized(com.android.vending.R.dimen.now_playing_all_portrait_large_width, com.android.vending.R.dimen.now_playing_all_portrait_large_height).replaceLastOccurenceInRowWith(PORTRAIT_LARGE_REPLACEMENT).markToSupportMetadata(), new Block(com.android.vending.R.layout.now_playing_all_stack_portrait_small).sized(com.android.vending.R.dimen.now_playing_all_portrait_small_width, com.android.vending.R.dimen.now_playing_all_portrait_large_height).hosting(2, com.android.vending.R.dimen.now_playing_all_portrait_small_width, com.android.vending.R.dimen.now_playing_all_portrait_small_height), new Block(com.android.vending.R.layout.now_playing_all_stack_portrait_very_small_triple).sized(com.android.vending.R.dimen.now_playing_all_portrait_very_small_width, com.android.vending.R.dimen.now_playing_all_portrait_large_height).hosting(3, com.android.vending.R.dimen.now_playing_all_portrait_very_small_width, com.android.vending.R.dimen.now_playing_all_portrait_very_small_height), new Block(com.android.vending.R.layout.now_playing_all_portrait_small).sized(com.android.vending.R.dimen.now_playing_all_portrait_small_width, com.android.vending.R.dimen.now_playing_all_portrait_small_height), new Block(com.android.vending.R.layout.now_playing_all_portrait_very_small).sized(com.android.vending.R.dimen.now_playing_all_portrait_very_small_width, com.android.vending.R.dimen.now_playing_all_portrait_very_small_height)};
        SQUARE_BLOCKS = new Block[]{new Block(com.android.vending.R.layout.now_playing_all_square_very_large).sized(com.android.vending.R.dimen.now_playing_all_square_very_large, com.android.vending.R.dimen.now_playing_all_square_very_large).limitRowStartTo(1).markToSupportMetadata(), new Block(com.android.vending.R.layout.now_playing_all_square_large).sized(com.android.vending.R.dimen.now_playing_all_square_large, com.android.vending.R.dimen.now_playing_all_square_large).markToSupportMetadata(), new Block(com.android.vending.R.layout.now_playing_all_stack_square_small_double).sized(com.android.vending.R.dimen.now_playing_all_square_small, com.android.vending.R.dimen.now_playing_all_square_very_large).hosting(2, com.android.vending.R.dimen.now_playing_all_square_small, com.android.vending.R.dimen.now_playing_all_square_small), new Block(com.android.vending.R.layout.now_playing_all_square_small).sized(com.android.vending.R.dimen.now_playing_all_square_small, com.android.vending.R.dimen.now_playing_all_square_small).limitRowStartTo(1).markToSupportMetadata(), new Block(com.android.vending.R.layout.now_playing_all_stack_square_very_small_quad).sized(com.android.vending.R.dimen.now_playing_all_square_very_small, com.android.vending.R.dimen.now_playing_all_square_very_large).hosting(4, com.android.vending.R.dimen.now_playing_all_square_very_small, com.android.vending.R.dimen.now_playing_all_square_very_small), new Block(com.android.vending.R.layout.now_playing_all_stack_square_very_small_triple).sized(com.android.vending.R.dimen.now_playing_all_square_very_small, com.android.vending.R.dimen.now_playing_all_square_large).hosting(3, com.android.vending.R.dimen.now_playing_all_square_very_small, com.android.vending.R.dimen.now_playing_all_square_very_small), new Block(com.android.vending.R.layout.now_playing_all_stack_square_very_small_double).sized(com.android.vending.R.dimen.now_playing_all_square_very_small, com.android.vending.R.dimen.now_playing_all_square_small).hosting(2, com.android.vending.R.dimen.now_playing_all_square_very_small, com.android.vending.R.dimen.now_playing_all_square_very_small), new Block(com.android.vending.R.layout.now_playing_all_tower_square_large).sized(com.android.vending.R.dimen.now_playing_all_square_large, com.android.vending.R.dimen.now_playing_all_square_very_large).hosting(4).withChild(0, com.android.vending.R.dimen.now_playing_all_square_large, com.android.vending.R.dimen.now_playing_all_square_very_large).withChild(1, com.android.vending.R.dimen.now_playing_all_square_very_small, com.android.vending.R.dimen.now_playing_all_square_very_small).withChild(2, com.android.vending.R.dimen.now_playing_all_square_very_small, com.android.vending.R.dimen.now_playing_all_square_very_small).withChild(3, com.android.vending.R.dimen.now_playing_all_square_very_small, com.android.vending.R.dimen.now_playing_all_square_very_small).limitRowStartTo(0), new Block(com.android.vending.R.layout.now_playing_all_tower_square_small).sized(com.android.vending.R.dimen.now_playing_all_square_small, com.android.vending.R.dimen.now_playing_all_square_large).hosting(3).withChild(0, com.android.vending.R.dimen.now_playing_all_square_small, com.android.vending.R.dimen.now_playing_all_square_small).withChild(1, com.android.vending.R.dimen.now_playing_all_square_very_small, com.android.vending.R.dimen.now_playing_all_square_very_small).withChild(2, com.android.vending.R.dimen.now_playing_all_square_very_small, com.android.vending.R.dimen.now_playing_all_square_very_small).limitRowStartTo(0), new Block(com.android.vending.R.layout.now_playing_all_square_very_small).sized(com.android.vending.R.dimen.now_playing_all_square_very_small, com.android.vending.R.dimen.now_playing_all_square_very_small).markToSupportMetadata()};
    }

    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        int backendId;
        if ("android.intent.action.PACKAGE_CHANGED".equals(intent.getAction())) {
            if (intent.getIntExtra("android.intent.extra.UID", -1) != -1) {
                String packageName = intent.getData().getSchemeSpecificPart();
                WidgetTypeMap typeMap = WidgetTypeMap.get(context);
                backendId = IntentUtils.getBackendId(packageName);
                if (backendId != -1 && backendId != 9) {
                    updateWidgets(context, AppWidgetManager.getInstance(context), typeMap.getWidgets(NowPlayingWidgetProvider.class, WidgetUtils.translate(backendId)));
                }
            }
        } else if ("NowPlayingWidgetProvider.DoneButton".equals(intent.getAction())) {
            appWidgetId = intent.getIntExtra("appWidgetId", -1);
            if (appWidgetId != -1) {
                FinskyLog.d("Received ACTION_DONE_BUTTON, updating widget %d.", Integer.valueOf(appWidgetId));
                HotseatDataHolder.writeHotseatDismissed(appWidgetId);
                updateWidgets(context, AppWidgetManager.getInstance(context), appWidgetId);
                return;
            }
            FinskyLog.e("Received ACTION_DONE_BUTTON, but no appWidgetId was specified.", new Object[0]);
        } else if ("NowPlayingWidgetProvider.WarmWelcome".equals(intent.getAction())) {
            appWidgetId = intent.getIntExtra("appWidgetId", -1);
            backendId = intent.getIntExtra("backendId", -1);
            if (appWidgetId != -1 && backendId != -1) {
                FinskyLog.d("Received ACTION_LAUNCH_WARM_WELCOME for backend %d and widget %d", Integer.valueOf(backendId), Integer.valueOf(appWidgetId));
                HotseatDataHolder.writeBackendChecked(appWidgetId, backendId);
                FinskyApp.get().startActivity(IntentUtils.buildConsumptionAppLaunchIntent(context, backendId, FinskyApp.get().getCurrentAccountName()));
                updateWidgets(context, AppWidgetManager.getInstance(context), appWidgetId);
            }
        }
    }

    protected int getWidgetClassId() {
        return 3;
    }

    public void onAppWidgetOptionsChanged(Context context, AppWidgetManager appWidgetManager, int appWidgetId, Bundle newOptions) {
        updateWidgets(context, appWidgetManager, appWidgetId);
    }

    public void onDeleted(Context context, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            FinskyLog.d("Deleting widget data for widget ID=%d", Integer.valueOf(appWidgetId));
            FinskyPreferences.libraryWidgetData.get(appWidgetId).remove();
        }
        super.onDeleted(context, appWidgetIds);
    }

    protected void updateWidgets(Context context, AppWidgetManager appWidgetManager, int... appWidgetIds) {
        updateWidgets(context, appWidgetManager, getImageLoader(context).getCachedBitmaps(), appWidgetIds);
    }

    protected void updateWidgets(Context context, AppWidgetManager appWidgetManager, Map<ImageSpec, Bitmap> cachedImages, int... appWidgetIds) {
        if (appWidgetIds != null) {
            WidgetTypeMap typeMap = WidgetTypeMap.get(context);
            int titleHeight = WidgetUtils.getDips(context, com.android.vending.R.dimen.now_playing_title_height);
            for (final int appWidgetId : appWidgetIds) {
                if (FinskyApp.get().getDfeApi() == null) {
                    appWidgetManager.updateAppWidget(appWidgetId, generateAccountNeededState(context, appWidgetId, 0));
                } else {
                    String backendType = typeMap.get(getClass(), appWidgetId);
                    if (backendType == null) {
                        appWidgetManager.updateAppWidget(appWidgetId, generateConfigurationState(context, appWidgetId, 0));
                    } else {
                        int backend = WidgetUtils.translate(backendType);
                        if (backend != 0 && !IntentUtils.isConsumptionAppInstalled(context.getPackageManager(), backend)) {
                            appWidgetManager.updateAppWidget(appWidgetId, generateUnavailableState(context, appWidgetId, backend));
                        } else if (isConsumptionAppDisabled(context, backend)) {
                            appWidgetManager.updateAppWidget(appWidgetId, generateDisabledState(context, appWidgetId, backend));
                        } else {
                            int[] dimens = BaseWidgetProvider.getBoundingBoxes(context, appWidgetId);
                            if (dimens[0] == 0 && dimens[1] == 0 && dimens[2] == 0 && dimens[3] == 0) {
                                if (backend == 0) {
                                    warmImageCache(context, backend);
                                }
                                appWidgetManager.updateAppWidget(appWidgetId, generateBaseTree(context, true));
                            } else {
                                int minWidth = dimens[0];
                                int minHeight = dimens[1] - titleHeight;
                                int maxWidth = dimens[2];
                                int maxHeight = dimens[3] - titleHeight;
                                DfeToc dfeToc = FinskyApp.get().getToc();
                                ViewTreeWrapper land = generateViewTree(backend, appWidgetId, dfeToc, context, cachedImages, maxWidth, minHeight);
                                ViewTreeWrapper port = generateViewTree(backend, appWidgetId, dfeToc, context, cachedImages, minWidth, maxHeight);
                                RemoteViews remoteViews = new RemoteViews(land.mRemoteViews, port.mRemoteViews);
                                if (land.mLoadedSuccessfully && port.mLoadedSuccessfully) {
                                    appWidgetManager.updateAppWidget(appWidgetId, remoteViews);
                                } else {
                                    final Context context2 = context;
                                    getImageLoader(context).enqueue(new ImageBatch(backend, mergePortAndLandRequests(port.mUris, land.mUris), new BatchedImageCallback() {
                                        public void onLoaded(Map<ImageSpec, Bitmap> cachedImages) {
                                            NowPlayingWidgetProvider.this.updateWidgets(context2, AppWidgetManager.getInstance(context2), cachedImages, appWidgetId);
                                        }
                                    }));
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private boolean isConsumptionAppDisabled(Context context, int backendId) {
        String packageName = IntentUtils.getPackageName(backendId);
        if (packageName != null && context.getPackageManager().getApplicationEnabledSetting(packageName) == 3) {
            return true;
        }
        return false;
    }

    public void warmImageCache(Context context, int backend) {
        List<ConsumptionAppDocList> docLists = getConsumptionDocLists(backend);
        List<ImageSpec> imagesToPrefetch = Lists.newArrayList();
        for (ConsumptionAppDocList docList : docLists) {
            for (int i = 0; i < Math.min(4, docList.size()); i++) {
                imagesToPrefetch.add(new ImageSpec(((ConsumptionAppDoc) docList.get(i)).getImageUri(), 0, 0));
            }
        }
        if (imagesToPrefetch.size() > 0) {
            if (FinskyLog.DEBUG) {
                FinskyLog.v("Warming cache for %s with %d images", Integer.valueOf(backend), Integer.valueOf(imagesToPrefetch.size()));
            }
            getImageLoader(context).enqueue(new ImageBatch(backend, imagesToPrefetch, null));
        }
    }

    private List<ImageSpec> mergePortAndLandRequests(List<ImageSpec> port, List<ImageSpec> land) {
        List<ImageSpec> output = Lists.newArrayList();
        if (!(land == null || port == null)) {
            boolean found;
            List<ImageSpec> largerSet = land;
            List<ImageSpec> smallerSet = port;
            if (land.size() < port.size()) {
                largerSet = port;
                smallerSet = land;
            }
            for (ImageSpec largeWrapper : largerSet) {
                found = false;
                for (ImageSpec smallWrapper : smallerSet) {
                    if (largeWrapper.uri.equals(smallWrapper.uri)) {
                        output.add(ImageSpec.merge(smallWrapper, largeWrapper));
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    output.add(largeWrapper);
                }
            }
            for (ImageSpec smallWrapper2 : smallerSet) {
                found = false;
                for (ImageSpec outputWrapper : output) {
                    if (outputWrapper.uri.equals(smallWrapper2.uri)) {
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    output.add(smallWrapper2);
                }
            }
        }
        return output;
    }

    private RemoteViews generateBaseTree(Context context, boolean showBackground) {
        RemoteViews views = new RemoteViews(context.getPackageName(), com.android.vending.R.layout.now_playing_base);
        views.setTextViewText(com.android.vending.R.id.widget_title, "");
        views.setImageViewResource(com.android.vending.R.id.widget_title_icon, getHeaderIconRes(0));
        views.setContentDescription(com.android.vending.R.id.widget_title_icon, "");
        int emptyBackgroundRes = getEmptyBackgroundRes(0);
        if (showBackground && emptyBackgroundRes != 0) {
            views.setImageViewResource(com.android.vending.R.id.widget_background_top, emptyBackgroundRes);
        }
        views.removeAllViews(com.android.vending.R.id.widget_content);
        views.setViewVisibility(com.android.vending.R.id.widget_background_accessibility, 8);
        views.setViewVisibility(com.android.vending.R.id.accessibility_overlay, 8);
        views.setViewVisibility(com.android.vending.R.id.hotseat, 8);
        views.setViewVisibility(com.android.vending.R.id.message_holder, 4);
        return views;
    }

    private RemoteViews generateConfigurationState(Context context, int appWidgetId, int backendId) {
        RemoteViews views = generateBaseTree(context, true);
        views.addView(com.android.vending.R.id.widget_content, new RemoteViews(context.getPackageName(), com.android.vending.R.layout.now_playing_configuration_needed));
        views.setOnClickPendingIntent(com.android.vending.R.id.widget_background_accessibility, TrampolineActivity.getPendingLaunchIntent(context, NowPlayingTrampoline.class, appWidgetId));
        views.setViewVisibility(com.android.vending.R.id.widget_background_accessibility, 0);
        useCustomStylingIfNecessary(context, views, backendId);
        return views;
    }

    private RemoteViews generateDisabledState(Context context, int appWidgetId, int backendId) {
        String text;
        RemoteViews views = generateBaseTree(context, true);
        RemoteViews content = new RemoteViews(context.getPackageName(), com.android.vending.R.layout.now_playing_configuration_needed);
        try {
            PackageManager pm = context.getPackageManager();
            text = context.getString(com.android.vending.R.string.widget_consumption_app_disabled, new Object[]{pm.getApplicationInfo(IntentUtils.getPackageName(backendId), 0).loadLabel(pm).toString()});
        } catch (NameNotFoundException e) {
            text = "";
        }
        content.setTextViewText(com.android.vending.R.id.error_msg, text);
        views.addView(com.android.vending.R.id.widget_content, content);
        views.setOnClickPendingIntent(com.android.vending.R.id.widget_background_accessibility, EnableAppReceiver.getEnableIntent(context, backendId));
        views.setViewVisibility(com.android.vending.R.id.widget_background_accessibility, 0);
        useCustomStylingIfNecessary(context, views, backendId);
        return views;
    }

    private boolean useCustomStylingIfNecessary(Context context, RemoteViews widget, int backendId) {
        if (BG_IMAGE_OVERRIDE == null || !BG_IMAGE_OVERRIDE.exists() || backendId != 0) {
            return false;
        }
        widget.setViewVisibility(com.android.vending.R.id.message_holder, 4);
        widget.setTextViewCompoundDrawables(com.android.vending.R.id.message_text, 0, com.android.vending.R.drawable.flo_widget_empty_logo_white, 0, 0);
        widget.setTextViewCompoundDrawables(com.android.vending.R.id.error_msg, 0, com.android.vending.R.drawable.flo_widget_empty_logo_white, 0, 0);
        widget.setImageViewUri(com.android.vending.R.id.widget_background_top, Uri.fromFile(BG_IMAGE_OVERRIDE));
        widget.setOnClickPendingIntent(com.android.vending.R.id.widget_title_accessibility_overlay, null);
        widget.setViewVisibility(com.android.vending.R.id.widget_title_accessibility_overlay, 4);
        widget.setTextColor(com.android.vending.R.id.message_text, context.getResources().getColor(com.android.vending.R.color.play_white));
        widget.setTextColor(com.android.vending.R.id.error_msg, context.getResources().getColor(com.android.vending.R.color.play_white));
        widget.setViewVisibility(com.android.vending.R.id.message_background, 8);
        widget.setTextViewText(com.android.vending.R.id.widget_title, getTitleRes(FinskyApp.get().getToc(), context, backendId).toUpperCase());
        return true;
    }

    private RemoteViews generateUnavailableState(Context context, int appWidgetId, int backendId) {
        RemoteViews views = generateBaseTree(context, true);
        RemoteViews content = new RemoteViews(context.getPackageName(), com.android.vending.R.layout.now_playing_configuration_needed);
        content.setTextViewText(com.android.vending.R.id.error_msg, context.getString(com.android.vending.R.string.widget_consumption_app_uninstalled));
        views.addView(com.android.vending.R.id.widget_content, content);
        Uri uri = new Builder().scheme("https").authority("play.google.com").path("store/apps/details").appendQueryParameter("id", IntentUtils.getPackageName(backendId)).build();
        Intent viewIntent = new Intent(context, MainActivity.class);
        viewIntent.setAction("android.intent.action.VIEW");
        viewIntent.setData(uri);
        viewIntent.addFlags(268435456);
        views.setOnClickPendingIntent(com.android.vending.R.id.widget_background_accessibility, PendingIntent.getActivity(context, 0, viewIntent, 0));
        views.setViewVisibility(com.android.vending.R.id.widget_background_accessibility, 0);
        useCustomStylingIfNecessary(context, views, backendId);
        return views;
    }

    private RemoteViews generateAccountNeededState(Context context, int appWidgetId, int backendId) {
        RemoteViews views = generateBaseTree(context, true);
        RemoteViews content = new RemoteViews(context.getPackageName(), com.android.vending.R.layout.now_playing_account_needed);
        views.removeAllViews(com.android.vending.R.id.widget_content);
        views.addView(com.android.vending.R.id.widget_content, content);
        views.setOnClickPendingIntent(com.android.vending.R.id.widget_background_accessibility, BaseWidgetProvider.getAddAccountIntent(context));
        views.setViewVisibility(com.android.vending.R.id.widget_background_accessibility, 0);
        if (useCustomStylingIfNecessary(context, views, backendId)) {
            views.setTextViewText(com.android.vending.R.id.error_msg, context.getString(com.android.vending.R.string.auth_required_error_oem));
        }
        return views;
    }

    private ViewTreeWrapper generateViewTree(int backend, int appWidgetId, DfeToc dfeToc, Context context, Map<ImageSpec, Bitmap> cachedImages, int areaWidth, int areaHeight) {
        fetchConsumptionDataIfNecessary(context, getBackends(backend));
        RemoteViews widget = generateBaseTree(context, false);
        ViewTreeWrapper viewTreeWrapper = new ViewTreeWrapper();
        viewTreeWrapper.mRemoteViews = widget;
        String title = getTitleRes(dfeToc, context, backend).toUpperCase();
        widget.setTextViewText(com.android.vending.R.id.widget_title, title);
        widget.setImageViewResource(com.android.vending.R.id.widget_title_icon, getHeaderIconRes(backend));
        Intent headerIntent = getHeaderIntent(context, backend, appWidgetId);
        if (headerIntent != null) {
            widget.setOnClickPendingIntent(com.android.vending.R.id.widget_title_accessibility_overlay, PendingIntent.getActivity(context, appWidgetId, headerIntent, 0));
            widget.setViewVisibility(com.android.vending.R.id.widget_title_accessibility_overlay, 0);
            widget.setContentDescription(com.android.vending.R.id.widget_title_accessibility_overlay, title);
        }
        Arrangement arrangement = NowPlayingArranger.arrange(NowPlayingScorer.score(getConsumptionDocLists(backend), context.getResources().getInteger(com.android.vending.R.integer.max_library_widget_backends), System.currentTimeMillis()), 0);
        int N = arrangement.quadrantToData.length;
        int stretchedQuadrant = arrangement.layoutVariant == 0 ? 0 : 1;
        RemoteViews target = widget;
        if (N > 1) {
            int subLayout = getLayout(arrangement);
            widget.removeAllViews(com.android.vending.R.id.widget_content);
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), subLayout);
            widget.addView(com.android.vending.R.id.widget_content, remoteViews);
            target = remoteViews;
        }
        viewTreeWrapper.showBackground = false;
        viewTreeWrapper.showEmptyBackground = true;
        int heightRemaining = 0;
        int i = 0;
        while (i < N) {
            int width = areaWidth;
            int height = areaHeight;
            int container = com.android.vending.R.id.widget_content;
            int location = Arrangement.getLocation(arrangement.quadrantToData.length, i, arrangement.layoutVariant);
            if (N > 1) {
                container = CONTAINERS[i];
                width /= 2;
                if (N == 4 || (N == 3 && i != stretchedQuadrant)) {
                    if (((float) areaHeight) / ((float) width) <= 2.2f) {
                        height = areaHeight / 2;
                    } else if ((location & 1) == 0) {
                        height = (location & 4) != 0 ? (areaHeight * 2) / 3 : areaHeight / 3;
                    } else if ((location & 4) != 0) {
                        height = areaHeight / 3;
                    } else {
                        height = (areaHeight * 2) / 3;
                    }
                    height += heightRemaining;
                }
            }
            ViewTreeWrapper uris = populateWidget(target, appWidgetId, container, location, context, arrangement.quadrantToData[i], width, height, cachedImages);
            if (N == 4 || (N == 3 && i != stretchedQuadrant)) {
                heightRemaining = uris.heightRemaining;
            }
            ViewTreeWrapper.access$376(viewTreeWrapper, uris.showBackground);
            ViewTreeWrapper.access$472(viewTreeWrapper, uris.showEmptyBackground);
            if (!uris.mLoadedSuccessfully) {
                viewTreeWrapper.mLoadedSuccessfully = false;
            }
            viewTreeWrapper.mUris.addAll(uris.mUris);
            i++;
        }
        int backgroundRes = 0;
        if (viewTreeWrapper.showBackground) {
            backgroundRes = getBackgroundRes(backend);
        } else if (viewTreeWrapper.showEmptyBackground) {
            if (backend != 0) {
                backgroundRes = getEmptyBackgroundRes(backend);
                Intent openIntent = IntentUtils.buildConsumptionAppLaunchIntent(context, backend, FinskyApp.get().getCurrentAccountName());
                if (openIntent != null) {
                    widget.setOnClickPendingIntent(com.android.vending.R.id.widget_background_accessibility, PendingIntent.getActivity(context, 0, openIntent, 0));
                }
                widget.setViewVisibility(com.android.vending.R.id.widget_background_accessibility, 0);
            } else if (headerIntent != null) {
                widget.setOnClickPendingIntent(com.android.vending.R.id.widget_background_accessibility, PendingIntent.getActivity(context, appWidgetId, headerIntent, 0));
                widget.setViewVisibility(com.android.vending.R.id.widget_background_accessibility, 0);
            }
        }
        if (!(useCustomStylingIfNecessary(context, widget, backend) || backgroundRes == 0)) {
            widget.setImageViewResource(com.android.vending.R.id.widget_background_top, backgroundRes);
        }
        boolean shouldShowEmptyHolder = viewTreeWrapper.showEmptyBackground && backend == 0 && areaWidth >= WidgetUtils.getDips(context, com.android.vending.R.dimen.hotseat_bar_threshold_width);
        if (shouldShowEmptyHolder) {
            widget.setViewVisibility(com.android.vending.R.id.message_holder, 0);
        } else {
            widget.setViewVisibility(com.android.vending.R.id.message_holder, 4);
        }
        if (backend == 0) {
            updateHotseat(context, dfeToc, widget, appWidgetId, areaWidth, areaHeight);
        } else {
            widget.setViewVisibility(com.android.vending.R.id.hotseat, 8);
        }
        return viewTreeWrapper;
    }

    protected ViewTreeWrapper populateWidget(RemoteViews widget, int appWidgetId, int containerId, int location, Context context, ConsumptionAppDocList docList, int width, int height, Map<ImageSpec, Bitmap> cachedImages) {
        this.mRowStartCounts.clear();
        WidgetLayout rows = new WidgetLayout(null, true, height);
        rows = generateWidgetLayout(context, docList.size(), docList.getBackend(), width, height);
        ViewTreeWrapper viewTree = populateWidget(context, widget, appWidgetId, containerId, location, rows, docList, cachedImages);
        viewTree.showBackground = rows.showBackground;
        viewTree.showEmptyBackground = docList.isEmpty();
        viewTree.heightRemaining = rows.heightRemaining;
        return viewTree;
    }

    private void updateHotseat(Context context, DfeToc dfeToc, RemoteViews remoteViews, int appWidgetId, int areaWidth, int areaHeight) {
        remoteViews.setViewVisibility(com.android.vending.R.id.hotseat, 8);
        if (dfeToc != null) {
            Resources res = context.getResources();
            if (areaHeight > WidgetUtils.getDips(context, com.android.vending.R.dimen.hotseat_bar_threshold_height) && areaWidth > WidgetUtils.getDips(context, com.android.vending.R.dimen.hotseat_bar_threshold_width)) {
                HotseatDataHolder wrapper = HotseatDataHolder.loadDataFromDisk(appWidgetId);
                if (wrapper.mHasDismissedHotseat) {
                    FinskyLog.v("Hiding hotseat because the user had dismissed it for %d", Integer.valueOf(appWidgetId));
                    return;
                }
                Intent intent;
                int corporaMaxCount = areaWidth / WidgetUtils.getDips(context, com.android.vending.R.dimen.hotseat_corpus_title_width);
                ConsumptionAppDataCache appDataCache = ConsumptionAppDataCache.get();
                int corporaWithSufficientlyOwnedDataCount = 0;
                int corporaWithLaunchedAppsCount = 0;
                int corporaTotalCount = 0;
                boolean isAnyContentOwned = false;
                int i = 0;
                while (true) {
                    int length = sSupportedBackendIds.length;
                    if (i >= r0) {
                        break;
                    }
                    int backendId = sSupportedBackendIds[i];
                    CorpusMetadata corpus = dfeToc.getCorpus(backendId);
                    if (corpus != null) {
                        if (IntentUtils.isConsumptionAppInstalled(context.getPackageManager(), backendId)) {
                            if (!IntentUtils.isConsumptionAppDisabled(FinskyApp.get().getPackageInfoRepository(), backendId)) {
                                corporaTotalCount++;
                                RemoteViews remoteViews2 = new RemoteViews(context.getPackageName(), com.android.vending.R.layout.now_playing_widget_hotseat_choice);
                                remoteViews2.setImageViewResource(com.android.vending.R.id.backend_check_icon, WidgetUtils.getHotseatCheckIcon(backendId));
                                remoteViews2.setTextViewText(com.android.vending.R.id.backend_name, corpus.name);
                                remoteViews2.setTextColor(com.android.vending.R.id.backend_name, CorpusResourceUtils.getPrimaryColor(context, backendId));
                                int ownedDocumentCount = appDataCache.getConsumptionAppDataSize(backendId);
                                isAnyContentOwned |= ownedDocumentCount > 0 ? 1 : 0;
                                if (appDataCache.hasConsumptionAppData(backendId) && ownedDocumentCount >= WidgetUtils.getAwarenessThreshold(backendId)) {
                                    corporaWithSufficientlyOwnedDataCount++;
                                    remoteViews2.setViewVisibility(com.android.vending.R.id.backend_check_icon, 0);
                                } else if (wrapper.isBackendChecked(backendId)) {
                                    corporaWithLaunchedAppsCount++;
                                    remoteViews2.setViewVisibility(com.android.vending.R.id.backend_check_icon, 0);
                                } else {
                                    remoteViews2.setViewVisibility(com.android.vending.R.id.backend_check_icon, 8);
                                }
                                intent = new Intent(context, NowPlayingWidgetProvider.class);
                                intent.setAction("NowPlayingWidgetProvider.WarmWelcome");
                                intent.putExtra("appWidgetId", appWidgetId);
                                intent.putExtra("backendId", backendId);
                                remoteViews2.setOnClickPendingIntent(com.android.vending.R.id.container, PendingIntent.getBroadcast(context, sRandom.nextInt(), intent, 0));
                                if (corporaTotalCount == 1) {
                                    remoteViews2.setViewVisibility(com.android.vending.R.id.separator_left, 8);
                                }
                                if (corporaTotalCount <= corporaMaxCount) {
                                    remoteViews.addView(com.android.vending.R.id.hotseat_selectors, remoteViews2);
                                }
                            }
                        }
                    }
                    i++;
                }
                boolean shouldShowDoneButton = corporaWithSufficientlyOwnedDataCount > 0 || (corporaWithLaunchedAppsCount > 0 && isAnyContentOwned);
                if (shouldShowDoneButton) {
                    remoteViews.setTextViewText(com.android.vending.R.id.start_playing, Html.fromHtml(res.getString(com.android.vending.R.string.widget_hotseat_title)));
                    remoteViews.setViewVisibility(com.android.vending.R.id.start_playing_separator, 0);
                    remoteViews.setViewVisibility(com.android.vending.R.id.done_button, 0);
                    intent = new Intent(context, NowPlayingWidgetProvider.class);
                    intent.setAction("NowPlayingWidgetProvider.DoneButton");
                    intent.putExtra("appWidgetId", appWidgetId);
                    remoteViews.setOnClickPendingIntent(com.android.vending.R.id.done_button, PendingIntent.getBroadcast(context, sRandom.nextInt(), intent, 0));
                } else {
                    remoteViews.setTextViewText(com.android.vending.R.id.start_playing, Html.fromHtml(res.getString(com.android.vending.R.string.widget_hotseat_title)));
                    remoteViews.setViewVisibility(com.android.vending.R.id.start_playing_separator, 8);
                    remoteViews.setViewVisibility(com.android.vending.R.id.done_button, 8);
                }
                remoteViews.setViewVisibility(com.android.vending.R.id.hotseat, 0);
            }
        }
    }

    private ViewTreeWrapper populateWidget(Context context, RemoteViews widget, int appWidgetId, int containerId, int location, List<List<Block>> rows, List<ConsumptionAppDoc> docs, Map<ImageSpec, Bitmap> cachedImages) {
        Resources res = context.getResources();
        List<ImageSpec> urisToLoad = Lists.newArrayList();
        boolean shouldAsyncLoadImages = false;
        boolean shownMetadata = false;
        String packageName = context.getPackageName();
        widget.removeAllViews(containerId);
        NowPlayingCellSorter sorter = new NowPlayingCellSorter();
        sorter.sort(rows, res);
        for (int rowIndex = 0; rowIndex < rows.size(); rowIndex++) {
            List<Block> row = (List) rows.get(rowIndex);
            RemoteViews remoteViews = new RemoteViews(packageName, com.android.vending.R.layout.now_playing_row);
            List<RemoteViews> rowItems = Lists.newArrayList();
            for (int blockIndex = 0; blockIndex < row.size(); blockIndex++) {
                Block block = (Block) row.get(blockIndex);
                remoteViews = new RemoteViews(packageName, block.getLayoutId());
                int N = Math.min(IMAGE_IDS.length, block.getNumImages());
                for (int i = 0; i < N; i++) {
                    int docIndex = sorter.getSortedIndex(rowIndex, blockIndex, i);
                    if (docIndex >= 0 && docIndex < docs.size()) {
                        ConsumptionAppDoc doc = (ConsumptionAppDoc) docs.get(docIndex);
                        if (cachedImages != null) {
                            Bitmap cachedBitmap = getCachedImage(context, cachedImages, doc.getImageUri(), block.getImageWidthRes(i), block.getImageHeightRes(i));
                            remoteViews.setImageViewBitmap(IMAGE_IDS[i], cachedBitmap);
                            if (cachedBitmap == null) {
                                shouldAsyncLoadImages = true;
                            }
                        } else {
                            shouldAsyncLoadImages = true;
                        }
                        urisToLoad.add(new ImageSpec(doc.getImageUri(), res.getDimensionPixelSize(block.getImageWidthRes(i)), res.getDimensionPixelSize(block.getImageHeightRes(i))));
                        if (block.supportsMetadata()) {
                            if (TextUtils.isEmpty(doc.getReason()) || shownMetadata) {
                                remoteViews.setViewVisibility(com.android.vending.R.id.metadata, 8);
                            } else {
                                remoteViews.setTextViewText(com.android.vending.R.id.metadata, doc.getReason().toUpperCase());
                                remoteViews.setViewVisibility(com.android.vending.R.id.metadata, 0);
                                shownMetadata = true;
                            }
                        }
                        remoteViews.setOnClickPendingIntent(ACCESSIBILITY_OVERLAY_IDS[i], PendingIntent.getActivity(context, i, doc.getViewIntent(), 0));
                    }
                }
                rowItems.add(remoteViews);
            }
            boolean isInLeftHalf = (location & 1) != 0;
            boolean isInRightHalf = (location & 2) != 0;
            int rowGravity = 1;
            if (isInLeftHalf) {
                rowGravity = 5;
            } else if (isInRightHalf) {
                rowGravity = 3;
            }
            if (rowIndex % 2 == 0) {
                rowGravity |= 80;
            } else {
                rowGravity |= 48;
            }
            remoteViews.setInt(com.android.vending.R.id.now_playing_row, "setGravity", rowGravity);
            if (isInLeftHalf) {
                Collections.reverse(rowItems);
            }
            for (RemoteViews addView : rowItems) {
                remoteViews.addView(com.android.vending.R.id.now_playing_row, addView);
            }
            widget.addView(containerId, remoteViews);
        }
        return new ViewTreeWrapper(null, !shouldAsyncLoadImages, urisToLoad);
    }

    private static Bitmap getCachedImage(Context context, Map<ImageSpec, Bitmap> cachedImages, Uri uriToFind, int width, int height) {
        int targetWidth = WidgetUtils.getDips(context, width);
        int targetHeight = WidgetUtils.getDips(context, height);
        for (ImageSpec wrapper : cachedImages.keySet()) {
            if (wrapper.satisfies(uriToFind, targetWidth, targetHeight)) {
                return (Bitmap) cachedImages.get(wrapper);
            }
        }
        return null;
    }

    private WidgetLayout generateWidgetLayout(Context context, int numDocuments, int listBackend, int rowWidth, int height) {
        List<List<Block>> rows = Lists.newArrayList();
        int numImages = 0;
        while (height > 0 && numDocuments > numImages) {
            int width = rowWidth;
            List<Block> row = Lists.newArrayList();
            int tallestElement = 0;
            int numBlocksAdded = 0;
            boolean isFirstInRow = true;
            while (width > 0 && numDocuments > numImages) {
                int rowHeight;
                if (tallestElement == 0) {
                    rowHeight = height;
                } else {
                    rowHeight = tallestElement;
                }
                Block block = findLargestBlock(context, width, rowHeight, listBackend, numDocuments - numImages, isFirstInRow);
                if (block == null) {
                    break;
                }
                numBlocksAdded++;
                numImages += block.getNumImages();
                row.add(block);
                width -= block.getWidth(context);
                tallestElement = Math.max(tallestElement, block.getHeight(context));
                isFirstInRow = false;
            }
            if (numBlocksAdded == 0) {
                break;
            }
            Map<Block, Integer> replaceable = Maps.newHashMap();
            for (Block block2 : row) {
                if (block2.hasLastOccurenceInRowReplacement()) {
                    replaceable.put(block2, Integer.valueOf((replaceable.containsKey(block2) ? ((Integer) replaceable.get(block2)).intValue() : 0) + 1));
                }
            }
            for (Entry<Block, Integer> replaceableEntry : replaceable.entrySet()) {
                if (((Integer) replaceableEntry.getValue()).intValue() != 1) {
                    Block toReplace = (Block) replaceableEntry.getKey();
                    int lastOccurenceIndex = -1;
                    for (int i = row.size() - 1; i >= 0; i--) {
                        if (row.get(i) == toReplace) {
                            lastOccurenceIndex = i;
                            break;
                        }
                    }
                    Block replacement = toReplace.getLastOccurenceInRowReplacement();
                    int imageCountDifference = replacement.getNumImages() - toReplace.getNumImages();
                    if (imageCountDifference <= 0 || numDocuments - numImages >= imageCountDifference) {
                        row.remove(lastOccurenceIndex);
                        row.add(lastOccurenceIndex, replacement);
                        numImages += imageCountDifference;
                    }
                }
            }
            rows.add(row);
            height -= tallestElement;
        }
        final Context context2 = context;
        Collections.sort(rows, new Comparator<List<Block>>() {
            int getRowCellCount(List<Block> blocks) {
                int result = 0;
                for (Block block : blocks) {
                    result += block.getNumImages();
                }
                return result;
            }

            int getRowAverageHeight(List<Block> blocks, Context context) {
                int totalCount = 0;
                int totalHeight = 0;
                Resources res = context.getResources();
                for (Block block : blocks) {
                    int blockImages = block.getNumImages();
                    for (int i = 0; i < blockImages; i++) {
                        totalHeight += res.getDimensionPixelSize(block.getImageHeightRes(i));
                    }
                    totalCount += blockImages;
                }
                return totalHeight / totalCount;
            }

            public int compare(List<Block> lhs, List<Block> rhs) {
                if (lhs == rhs) {
                    return 0;
                }
                int lHeight = getRowAverageHeight(lhs, context2);
                int rHeight = getRowAverageHeight(rhs, context2);
                if (lHeight == rHeight) {
                    int lCells = getRowCellCount(lhs);
                    int rCells = getRowCellCount(rhs);
                    if (lCells != rCells) {
                        if (lCells >= rCells) {
                            return -1;
                        }
                        return 1;
                    } else if (lhs.hashCode() <= rhs.hashCode()) {
                        return -1;
                    } else {
                        return 1;
                    }
                } else if (lHeight >= rHeight) {
                    return -1;
                } else {
                    return 1;
                }
            }
        });
        return new WidgetLayout(rows, numImages >= numDocuments, height);
    }

    protected Block findLargestBlock(Context context, int availableWidth, int availableHeight, int listBackend, int numImagesLeft, boolean isFirstInRow) {
        Block largest = null;
        for (Block block : getBlocks(listBackend)) {
            int currentRowStarts = this.mRowStartCounts.get(block.hashCode(), 0);
            if (!isFirstInRow || !block.hasMaxRowStartCount() || currentRowStarts < block.getMaxRowStartCount()) {
                boolean taller = true;
                boolean wider = true;
                if (largest != null) {
                    taller = block.getHeight(context) > largest.getHeight(context);
                    wider = block.getWidth(context) >= largest.getWidth(context);
                }
                if (taller && block.getHeight(context) <= availableHeight && wider && block.getWidth(context) <= availableWidth && numImagesLeft >= block.getNumImages()) {
                    largest = block;
                }
            }
        }
        if (largest != null && isFirstInRow) {
            this.mRowStartCounts.put(largest.hashCode(), this.mRowStartCounts.get(largest.hashCode(), 0) + 1);
        }
        return largest;
    }

    private static int getLayout(Arrangement arrangement) {
        switch (arrangement.quadrantToData.length) {
            case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                return 0;
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                return com.android.vending.R.layout.now_playing_widget_two;
            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                if (arrangement.layoutVariant == 0) {
                    return com.android.vending.R.layout.now_playing_widget_three_left;
                }
                return com.android.vending.R.layout.now_playing_widget_three_right;
            case R.styleable.WalletImFormEditText_required /*4*/:
                return com.android.vending.R.layout.now_playing_widget_four;
            default:
                throw new IllegalArgumentException("Invalid count: " + arrangement.quadrantToData.length);
        }
    }

    private static Block[] getBlocks(int listBackend) {
        switch (listBackend) {
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
            case R.styleable.WalletImFormEditText_required /*4*/:
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                return PORTRAIT_BLOCKS;
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                return SQUARE_BLOCKS;
            default:
                throw new IllegalArgumentException("Invalid backend");
        }
    }

    private static Intent getHeaderIntent(Context context, int backend, int appWidgetId) {
        switch (backend) {
            case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                return TrampolineActivity.getLaunchIntent(context, MyLibraryTrampoline.class, appWidgetId);
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
            case R.styleable.WalletImFormEditText_required /*4*/:
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                return IntentUtils.buildConsumptionAppLaunchIntent(context, backend, FinskyApp.get().getCurrentAccountName());
            default:
                return null;
        }
    }

    private static int getBackgroundRes(int backend) {
        switch (backend) {
            case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                return com.android.vending.R.drawable.bg_play_widget_np;
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                return com.android.vending.R.drawable.bg_play_widget_books;
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                return com.android.vending.R.drawable.bg_play_widget_music;
            case R.styleable.WalletImFormEditText_required /*4*/:
                return com.android.vending.R.drawable.bg_play_widget_movies;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                return com.android.vending.R.drawable.bg_play_widget_newsstand;
            default:
                return 0;
        }
    }

    private static int getEmptyBackgroundRes(int backend) {
        switch (backend) {
            case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                return com.android.vending.R.drawable.bg_empty_play_widget_np;
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                return com.android.vending.R.drawable.bg_empty_play_widget_books;
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                return com.android.vending.R.drawable.bg_empty_play_widget_music;
            case R.styleable.WalletImFormEditText_required /*4*/:
                return com.android.vending.R.drawable.bg_empty_play_widget_videos;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                return com.android.vending.R.drawable.bg_empty_play_widget_newsstand;
            default:
                return 0;
        }
    }

    private static String getTitleRes(DfeToc dfeToc, Context context, int backend) {
        switch (backend) {
            case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                return context.getString(com.android.vending.R.string.widget_now_playing);
            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                return context.getString(com.android.vending.R.string.widget_games_now_playing);
            default:
                if (dfeToc == null || dfeToc.getCorpus(backend) == null) {
                    return context.getString(getFallbackTitleRes(backend));
                }
                return dfeToc.getCorpus(backend).libraryName;
        }
    }

    private static int getFallbackTitleRes(int backend) {
        switch (backend) {
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                return com.android.vending.R.string.widget_books_now_playing;
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                return com.android.vending.R.string.widget_music_now_playing;
            case R.styleable.WalletImFormEditText_required /*4*/:
                return com.android.vending.R.string.widget_movies_now_playing;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                return com.android.vending.R.string.widget_newsstand_now_playing;
            default:
                return com.android.vending.R.string.widget_now_playing;
        }
    }

    private static int getHeaderIconRes(int backend) {
        switch (backend) {
            case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                return com.android.vending.R.drawable.ic_play_widgets_np;
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                return com.android.vending.R.drawable.ic_play_widgets_books;
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                return com.android.vending.R.drawable.ic_play_widgets_music;
            case R.styleable.WalletImFormEditText_required /*4*/:
                return com.android.vending.R.drawable.ic_play_widgets_movies;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                return com.android.vending.R.drawable.ic_play_widgets_newsstand;
            default:
                throw new IllegalArgumentException("Invalid backend");
        }
    }

    private static List<ConsumptionAppDocList> getConsumptionDocLists(int widgetBackend) {
        List<ConsumptionAppDocList> allDocs = Lists.newArrayList();
        for (int listBackend : getBackends(widgetBackend)) {
            List<ConsumptionAppDoc> docs = getConsumptionData(listBackend);
            if (!docs.isEmpty()) {
                allDocs.add(new ConsumptionAppDocList(listBackend, docs));
            }
        }
        Collections.sort(allDocs, ConsumptionAppDocList.NEWEST_FIRST);
        return allDocs;
    }

    private static int[] getBackends(int backend) {
        if (backend == 0) {
            return BACKENDS;
        }
        return new int[]{backend};
    }

    private static List<ConsumptionAppDoc> getConsumptionData(int backend) {
        return getDocsWithImages(ConsumptionAppDataCache.get().getConsumptionAppData(backend));
    }

    protected static void fetchConsumptionDataIfNecessary(Context context, int... backendIds) {
        ConsumptionAppDataCache cache = ConsumptionAppDataCache.get();
        int N = backendIds.length;
        int[] backendsToUpdate = new int[N];
        int i = 0;
        int numBackendsToUpdate = 0;
        while (i < N) {
            int numBackendsToUpdate2;
            int backendId = backendIds[i];
            if (!cache.hasConsumptionAppData(backendId) && !cache.isLoadingData(backendId)) {
                cache.startLoading(backendId);
                numBackendsToUpdate2 = numBackendsToUpdate + 1;
                backendsToUpdate[numBackendsToUpdate] = backendId;
            } else if (FinskyLog.DEBUG) {
                FinskyLog.v("Data for [%s] is available or loading, skipping", Integer.valueOf(backendId));
                numBackendsToUpdate2 = numBackendsToUpdate;
            } else {
                numBackendsToUpdate2 = numBackendsToUpdate;
            }
            i++;
            numBackendsToUpdate = numBackendsToUpdate2;
        }
        if (numBackendsToUpdate > 0) {
            int[] copyOfBackendsToUpdate = new int[numBackendsToUpdate];
            System.arraycopy(backendsToUpdate, 0, copyOfBackendsToUpdate, 0, numBackendsToUpdate);
            LoadConsumptionDataService.scheduleAlarmForUpdate(context, copyOfBackendsToUpdate);
        }
    }

    private static BatchedImageLoader getImageLoader(Context context) {
        if (mImageLoader == null) {
            mImageLoader = new BatchedImageLoader(context, FinskyApp.get().getBitmapCache());
        }
        return mImageLoader;
    }

    private static List<ConsumptionAppDoc> getDocsWithImages(List<ConsumptionAppDoc> docs) {
        List<ConsumptionAppDoc> output = Lists.newArrayList();
        if (docs != null) {
            for (ConsumptionAppDoc doc : docs) {
                if (doc.getImageUri() != null) {
                    output.add(doc);
                } else {
                    FinskyLog.d("filtering out docId=[%s] because uri was null", doc.getDocId());
                }
            }
        }
        return output;
    }
}
