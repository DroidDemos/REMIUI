package com.google.android.finsky.widget.recommendation;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import com.android.vending.R;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.RequestFuture;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.appstate.PackageStateRepository;
import com.google.android.finsky.config.G;
import com.google.android.finsky.library.Library;
import com.google.android.finsky.protos.Common.Image;
import com.google.android.finsky.protos.DocList.ListResponse;
import com.google.android.finsky.utils.BackgroundThreadFactory;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.FinskyPreferences;
import com.google.android.finsky.utils.LibraryUtils;
import com.google.android.finsky.utils.ParcelUtils;
import com.google.android.finsky.utils.Utils;
import com.google.android.play.image.BitmapLoader;
import com.google.android.play.image.BitmapLoader.BitmapContainer;
import com.google.android.play.image.BitmapLoader.BitmapLoadedHandler;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class RecommendationsStore {
    private static final String CACHE_FILE_PREFIX;
    private static final ExecutorService sWriteThread;

    static {
        sWriteThread = Executors.newSingleThreadExecutor(new BackgroundThreadFactory());
        CACHE_FILE_PREFIX = RecommendationList.class.getSimpleName();
    }

    public static RecommendationList getRecommendations(Context context, DfeApi dfeApi, int backendId, Library library) throws InterruptedException, ExecutionException, TimeoutException {
        RecommendationList recList = null;
        File cacheFile = getCacheFile(context, backendId);
        if (cacheFile.exists()) {
            recList = (RecommendationList) ParcelUtils.readFromDisk(cacheFile);
            if (recList != null) {
                recList.removeExpiredRecommendations();
            }
        }
        if (recList == null || recList.isEmpty()) {
            recList = loadDocumentsFromNetwork(context, dfeApi, backendId, library);
            try {
                ParcelUtils.writeToDisk(cacheFile, recList);
            } catch (IOException e) {
                FinskyLog.e(e, "Unable to cache recs for %d", Integer.valueOf(backendId));
            }
        }
        return recList;
    }

    public static void deleteCachedRecommendations(Context context, int backendId) {
        getCacheFile(context, backendId).delete();
    }

    public static RecommendationList getRecommendationsOrShowError(Context context, DfeApi dfeApi, int backendId, int appWidgetId, Library library) {
        Utils.ensureNotOnMainThread();
        RecommendationList recList = null;
        String errorString = null;
        try {
            recList = getRecommendations(context, dfeApi, backendId, library);
            if (errorString != null) {
                RecommendedWidgetProvider.showError(context, appWidgetId, errorString);
            }
        } catch (InterruptedException e) {
            FinskyLog.e(e, "Error loading recs widget: %s", e.getMessage());
            errorString = context.getString(R.string.network_error);
            if (errorString != null) {
                RecommendedWidgetProvider.showError(context, appWidgetId, errorString);
            }
        } catch (ExecutionException e2) {
            FinskyLog.e(e2, "Error loading recs widget: %s", e2.getCause().getMessage());
            errorString = context.getString(R.string.network_error);
            if (errorString != null) {
                RecommendedWidgetProvider.showError(context, appWidgetId, errorString);
            }
        } catch (TimeoutException e3) {
            FinskyLog.e(e3, "Error loading recs widget: %s", e3.getMessage());
            errorString = context.getString(R.string.timeout_error);
            if (errorString != null) {
                RecommendedWidgetProvider.showError(context, appWidgetId, errorString);
            }
        } catch (IllegalStateException e4) {
            errorString = context.getString(R.string.no_recs_found_error);
            if (errorString != null) {
                RecommendedWidgetProvider.showError(context, appWidgetId, errorString);
            }
        } catch (Throwable th) {
            if (errorString != null) {
                RecommendedWidgetProvider.showError(context, appWidgetId, errorString);
            }
        }
        return recList;
    }

    private static RecommendationList loadDocumentsFromNetwork(Context context, DfeApi dfeApi, int backendId, Library library) throws InterruptedException, ExecutionException, TimeoutException {
        RequestFuture<ListResponse> future = RequestFuture.newFuture();
        String recsUrl = getRecsWidgetUrl(backendId);
        if (TextUtils.isEmpty(recsUrl)) {
            FinskyLog.e("No recs widget url provided in loadDocsFromNetwork().", new Object[0]);
            throw new IllegalStateException("No recs url provided");
        }
        dfeApi.getList(recsUrl, future, future);
        return parseNetworkResponse((ListResponse) future.get(((Long) G.recommendationsFetchTimeoutMs.get()).longValue(), TimeUnit.MILLISECONDS), backendId, library);
    }

    public static void performBackFill(DfeApi dfeApi, final Context context, final RecommendationList oldRecs, final Library library, final int appWidgetId) {
        String recsUrl = getRecsWidgetUrl(oldRecs.getBackendId());
        if (TextUtils.isEmpty(recsUrl)) {
            FinskyLog.e("No recs widget url provided in performBackFill()", new Object[0]);
        } else {
            dfeApi.getList(recsUrl, new Listener<ListResponse>() {
                public void onResponse(ListResponse response) {
                    oldRecs.merge(RecommendationsStore.parseNetworkResponse(response, oldRecs.getBackendId(), library));
                    RecommendationsStore.sWriteThread.execute(new Runnable() {
                        public void run() {
                            oldRecs.writeToDisk(context);
                            RecommendationsViewFactory.notifyDataSetChanged(context, appWidgetId);
                        }
                    });
                }
            }, new ErrorListener() {
                public void onErrorResponse(VolleyError error) {
                    FinskyLog.e("Error while fetching more recs: %s", error);
                }
            });
        }
    }

    public static String getRecsWidgetUrl(int backendId) {
        return (String) FinskyPreferences.widgetUrlsByBackend.get(backendId).get();
    }

    private static RecommendationList parseNetworkResponse(ListResponse response, int backendId, Library library) {
        if (response.doc.length == 0) {
            return null;
        }
        Document container = new Document(response.doc[0]);
        RecommendationList recList = new RecommendationList(container.getTitle(), backendId);
        int N = container.getChildCount();
        PackageStateRepository repo = FinskyApp.get().getPackageInfoRepository();
        for (int i = 0; i < N; i++) {
            Document document = container.getChildAt(i);
            boolean isInstalledApp = false;
            if (document.getDocumentType() == 1) {
                if (repo.get(document.getAppDetails().packageName) != null) {
                    isInstalledApp = true;
                } else {
                    isInstalledApp = false;
                }
            }
            if (!LibraryUtils.isOwned(document, library) && !isInstalledApp) {
                recList.add(new Recommendation(document));
            } else if (FinskyLog.DEBUG) {
                FinskyLog.v("Already own %s, skipping", document.getDocId());
            }
        }
        return recList;
    }

    public static Bitmap getBitmap(BitmapLoader loader, Recommendation item, int maxHeight) {
        Image image = item.getImage();
        if (image == null) {
            return null;
        }
        int requestHeight;
        if (image.supportsFifeUrlOptions) {
            requestHeight = maxHeight;
        } else {
            requestHeight = 0;
        }
        final Semaphore lock = new Semaphore(0);
        final Bitmap[] bitmap = new Bitmap[1];
        BitmapContainer container = loader.get(image.imageUrl, 0, requestHeight, new BitmapLoadedHandler() {
            public void onResponse(BitmapContainer result) {
                bitmap[0] = result.getBitmap();
                lock.release();
            }
        });
        if (container.getBitmap() != null) {
            bitmap[0] = container.getBitmap();
        } else {
            try {
                if (!lock.tryAcquire(((Long) G.recommendationsFetchTimeoutMs.get()).longValue(), TimeUnit.MILLISECONDS)) {
                    FinskyLog.e("Timed out while fetching %s", image.imageUrl);
                }
            } catch (InterruptedException e) {
                FinskyLog.e("Interrupted while fetching %s", image.imageUrl);
            }
        }
        return bitmap[0];
    }

    public static File getCacheFile(Context context, int backend) {
        File cacheDir = new File(context.getCacheDir(), "recs");
        cacheDir.mkdirs();
        return new File(cacheDir, CACHE_FILE_PREFIX + "_" + backend + ".cache");
    }
}
