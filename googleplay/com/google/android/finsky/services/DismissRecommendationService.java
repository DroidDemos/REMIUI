package com.google.android.finsky.services;

import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri.Builder;
import com.android.volley.toolbox.RequestFuture;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.config.G;
import com.google.android.finsky.library.Library;
import com.google.android.finsky.protos.RateSuggestedContentResponse;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.widget.BaseWidgetProvider;
import com.google.android.finsky.widget.recommendation.RecommendationList;
import com.google.android.finsky.widget.recommendation.RecommendationsStore;
import com.google.android.finsky.widget.recommendation.RecommendationsViewFactory;
import com.google.android.finsky.widget.recommendation.RecommendedWidgetProvider;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class DismissRecommendationService extends IntentService {
    public DismissRecommendationService() {
        super(DismissRecommendationService.class.getSimpleName());
    }

    protected void onHandleIntent(Intent intent) {
        int appWidgetId = intent.getIntExtra("appWidgetId", 0);
        String documentId = intent.getStringExtra("documentId");
        String dismissalUrl = intent.getStringExtra("dismissalUrl");
        int backendId = intent.getIntExtra("backendId", 3);
        Library library = FinskyApp.get().getLibraries();
        DfeApi dfeApi = FinskyApp.get().getDfeApi();
        if (dfeApi == null) {
            BaseWidgetProvider.update(this, RecommendedWidgetProvider.class, appWidgetId);
            return;
        }
        RecommendationList recList = null;
        try {
            recList = RecommendationsStore.getRecommendations(this, dfeApi, backendId, library);
            recList.remove(documentId);
            recList.writeToDisk(this);
            RecommendationsViewFactory.notifyDataSetChanged(this, appWidgetId);
            if (false) {
                FinskyLog.d("Invalidating cached recs for %d", Integer.valueOf(backendId));
                dfeApi.invalidateListCache(RecommendationsStore.getRecsWidgetUrl(backendId), true);
                RecommendationsStore.deleteCachedRecommendations(this, backendId);
            }
        } catch (ExecutionException e) {
            FinskyLog.d("Execution exception while fetching: %s", e);
            if (1 != null) {
                FinskyLog.d("Invalidating cached recs for %d", Integer.valueOf(backendId));
                dfeApi.invalidateListCache(RecommendationsStore.getRecsWidgetUrl(backendId), true);
                RecommendationsStore.deleteCachedRecommendations(this, backendId);
            }
        } catch (InterruptedException e2) {
            FinskyLog.d("Interrupted while fetching: %s", e2);
            if (1 != null) {
                FinskyLog.d("Invalidating cached recs for %d", Integer.valueOf(backendId));
                dfeApi.invalidateListCache(RecommendationsStore.getRecsWidgetUrl(backendId), true);
                RecommendationsStore.deleteCachedRecommendations(this, backendId);
            }
        } catch (TimeoutException e3) {
            FinskyLog.d("Timed out while fetching: %s", e3);
            if (1 != null) {
                FinskyLog.d("Invalidating cached recs for %d", Integer.valueOf(backendId));
                dfeApi.invalidateListCache(RecommendationsStore.getRecsWidgetUrl(backendId), true);
                RecommendationsStore.deleteCachedRecommendations(this, backendId);
            }
        } catch (Throwable th) {
            if (1 != null) {
                FinskyLog.d("Invalidating cached recs for %d", Integer.valueOf(backendId));
                dfeApi.invalidateListCache(RecommendationsStore.getRecsWidgetUrl(backendId), true);
                RecommendationsStore.deleteCachedRecommendations(this, backendId);
            }
        }
        FinskyLog.d("Dismissing id=[%s], url=[%s]", documentId, dismissalUrl);
        RequestFuture<RateSuggestedContentResponse> future = RequestFuture.newFuture();
        future.setRequest(dfeApi.rateSuggestedContent(dismissalUrl, future, future));
        try {
            future.get(((Long) G.recommendationsFetchTimeoutMs.get()).longValue(), TimeUnit.MILLISECONDS);
            if (recList != null && recList.needsBackfill()) {
                dfeApi.invalidateListCache(RecommendationsStore.getRecsWidgetUrl(backendId), true);
                RecommendationsStore.performBackFill(dfeApi, this, recList, library, appWidgetId);
            }
            FinskyLog.d("Dismissed %s", dismissalUrl);
        } catch (InterruptedException e4) {
            FinskyLog.e("Interrupted while dismissing", new Object[0]);
        } catch (ExecutionException e5) {
            FinskyLog.e("Exception while dismissing: %s", e5);
        } catch (TimeoutException e6) {
            FinskyLog.e("Timed out while dismissing", new Object[0]);
        }
    }

    public static PendingIntent getDismissPendingIntent(Context context, int appWidgetId, Document document, int backendId, int position) {
        if (!document.hasNeutralDismissal()) {
            return null;
        }
        String dismissalUrl = document.getNeutralDismissal().url;
        Builder dummyUriForDeDuping = new Builder();
        dummyUriForDeDuping.scheme("content");
        dummyUriForDeDuping.authority("dismiss");
        dummyUriForDeDuping.appendPath(String.valueOf(appWidgetId));
        dummyUriForDeDuping.appendPath(String.valueOf(position));
        dummyUriForDeDuping.appendPath(dismissalUrl);
        Intent dismiss = new Intent(context, DismissRecommendationService.class);
        dismiss.setData(dummyUriForDeDuping.build());
        dismiss.putExtra("appWidgetId", appWidgetId);
        dismiss.putExtra("documentId", document.getDocId());
        dismiss.putExtra("dismissalUrl", dismissalUrl);
        dismiss.putExtra("backendId", backendId);
        return PendingIntent.getService(context, 0, dismiss, 0);
    }
}
