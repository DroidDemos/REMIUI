package com.google.android.finsky.providers;

import android.content.Context;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.net.Uri.Builder;
import android.text.TextUtils;
import com.android.vending.R;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.config.G;
import com.google.android.finsky.protos.SearchSuggest.NavSuggestion;
import com.google.android.finsky.protos.SearchSuggest.SearchSuggestResponse;
import com.google.android.finsky.protos.SearchSuggest.Suggestion;
import com.google.android.finsky.providers.AppIconProvider.AppIconLoader;
import com.google.android.finsky.providers.AppIconProvider.TimedOnCompleteListener;
import com.google.android.finsky.search.RefCountedOnCompleteListener;
import com.google.android.finsky.search.SuggestionFetcher;
import com.google.android.finsky.utils.DocUtils;
import com.google.android.finsky.utils.FinskyLog;

class SearchSuggestionFetcherLegacy extends SuggestionFetcher {
    private static String[] BACKEND_ID_TO_ICON_PACKAGE_NAME;
    private static Boolean mIsGoogleTV;
    private final int mBackendId;
    private final int mIconSize;
    private final SuggestionHandlerLegacy mSuggestionHandler;

    static {
        mIsGoogleTV = null;
        BACKEND_ID_TO_ICON_PACKAGE_NAME = new String[]{null, "com.google.android.apps.books", "com.google.android.music", null, "com.google.android.videos", null, "com.google.android.apps.magazines", "com.google.android.videos"};
    }

    public SearchSuggestionFetcherLegacy(Context context, int backendId, String query, SuggestionHandlerLegacy suggestionHandler) {
        super(query, context);
        this.mBackendId = backendId;
        this.mIconSize = context.getResources().getDimensionPixelSize(R.dimen.suggestion_icon_size);
        this.mSuggestionHandler = suggestionHandler;
    }

    private Uri getBackendCanonicalIconUri(int backendId) {
        try {
            String pkgName = BACKEND_ID_TO_ICON_PACKAGE_NAME[backendId];
            int icon = ((ResolveInfo) this.mContext.getPackageManager().queryIntentActivities(this.mContext.getPackageManager().getLaunchIntentForPackage(pkgName), 65536).get(0)).activityInfo.applicationInfo.icon;
            if (icon != 0) {
                return new Builder().scheme("android.resource").authority(pkgName).path(Integer.toString(icon)).build();
            }
        } catch (Exception e) {
        }
        return null;
    }

    private Uri getIconUri(NavSuggestion navSuggestion, final RefCountedOnCompleteListener listener) {
        boolean noIcon;
        String docId = navSuggestion.docId;
        if (navSuggestion.hasImageBlob || navSuggestion.image != null) {
            noIcon = false;
        } else {
            noIcon = true;
        }
        if (noIcon) {
            return getBackendCanonicalIconUri(DocUtils.docidToBackend(docId));
        }
        TimedOnCompleteListener onComplete = new TimedOnCompleteListener() {
            protected void onComplete() {
                listener.onComplete();
            }
        };
        AppIconLoader iconLoader = new AppIconLoader(this.mContext, docId);
        listener.addProducer();
        if (navSuggestion.hasImageBlob) {
            iconLoader.loadToFileFromBlob(navSuggestion.imageBlob, onComplete);
        } else if (navSuggestion.image != null) {
            iconLoader.loadToFileFromUrl(navSuggestion.image, onComplete);
        } else {
            FinskyLog.w("Nav suggestion for %s has no blob, no image", docId);
            listener.onComplete();
            return null;
        }
        return AppIconProvider.CONTENT_URI.buildUpon().appendPath(docId).build();
    }

    private boolean isGoogleTv() {
        if (mIsGoogleTV == null) {
            mIsGoogleTV = Boolean.valueOf(this.mContext.getPackageManager().hasSystemFeature("com.google.android.tv"));
        }
        return mIsGoogleTV.booleanValue();
    }

    protected void makeRequest(OnCompleteListener listener) {
        if (TextUtils.isEmpty(this.mQuery)) {
            listener.onComplete();
            return;
        }
        boolean requestNavigationalSuggestions = !isGoogleTv() || ((Boolean) G.gtvNavigationalSuggestEnabled.get()).booleanValue();
        final RefCountedOnCompleteListener refCountedListener = new RefCountedOnCompleteListener(listener);
        FinskyApp.get().getDfeApi().searchSuggest(this.mQuery, this.mBackendId, this.mIconSize, true, requestNavigationalSuggestions, new Listener<SearchSuggestResponse>() {
            public void onResponse(SearchSuggestResponse response) {
                int responseCount = response.suggestion.length;
                SearchSuggestionFetcherLegacy.this.mSuggestionHandler.sendSuggestionsReceivedLog(3, responseCount, response.serverLogsCookie, SearchSuggestionFetcherLegacy.this.mStartTimeMs);
                for (int i = 0; i < responseCount; i++) {
                    Suggestion suggestion = response.suggestion[i];
                    if (suggestion.type == 2) {
                        SearchSuggestionFetcherLegacy.this.mSuggestionHandler.addRow(i, Integer.valueOf(17301583), suggestion.suggestedQuery, suggestion.serverLogsCookie);
                    } else if (suggestion.type == 3) {
                        NavSuggestion navSuggestion = suggestion.navSuggestion;
                        SearchSuggestionFetcherLegacy.this.mSuggestionHandler.addRow(i, SearchSuggestionFetcherLegacy.this.getIconUri(navSuggestion, refCountedListener), navSuggestion.description, null, "com.google.android.finsky.NAVIGATIONAL_SUGGESTION", "https://market.android.com/details?id=" + navSuggestion.docId + "&feature=sugg", suggestion.serverLogsCookie);
                    } else {
                        FinskyLog.w("Unknown suggestion type %d", Integer.valueOf(suggestion.type));
                    }
                }
                refCountedListener.onComplete();
            }
        }, new ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                refCountedListener.onComplete();
            }
        });
        startRequestLatencyTimer();
    }
}
