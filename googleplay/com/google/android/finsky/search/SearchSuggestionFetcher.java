package com.google.android.finsky.search;

import android.content.Context;
import android.text.TextUtils;
import com.android.vending.R;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.config.G;
import com.google.android.finsky.protos.SearchSuggest.SearchSuggestResponse;
import com.google.android.finsky.protos.SearchSuggest.Suggestion;

class SearchSuggestionFetcher extends SuggestionFetcher {
    private static Boolean sIsGoogleTV;
    private final int mBackendId;
    private final int mIconSize;
    private final SearchLogger mLogger;
    private final SuggestionHandler mSuggestionHandler;
    private final boolean mZeroQueryDisabled;

    static {
        sIsGoogleTV = null;
    }

    public SearchSuggestionFetcher(Context context, int backendId, String query, SuggestionHandler suggestionHandler, boolean zeroQueryDisabled, SearchLogger logger) {
        super(query, context);
        this.mBackendId = backendId;
        this.mSuggestionHandler = suggestionHandler;
        this.mZeroQueryDisabled = zeroQueryDisabled;
        this.mIconSize = context.getResources().getDimensionPixelSize(R.dimen.play_search_suggestion_icon_size);
        this.mLogger = logger;
        if (sIsGoogleTV == null) {
            sIsGoogleTV = Boolean.valueOf(this.mContext.getPackageManager().hasSystemFeature("com.google.android.tv"));
        }
    }

    protected void makeRequest(final OnCompleteListener listener) {
        if (this.mZeroQueryDisabled && TextUtils.isEmpty(this.mQuery)) {
            listener.onComplete();
            return;
        }
        boolean requestNavigationalSuggestions = !sIsGoogleTV.booleanValue() || ((Boolean) G.gtvNavigationalSuggestEnabled.get()).booleanValue();
        FinskyApp.get().getDfeApi().searchSuggest(this.mQuery, this.mBackendId, this.mIconSize, true, requestNavigationalSuggestions, new Listener<SearchSuggestResponse>() {
            public void onResponse(SearchSuggestResponse response) {
                SearchSuggestionFetcher.this.mLogger.logSuggestionsReceived(SearchSuggestionFetcher.this.mQuery, 3, response.suggestion.length, response.serverLogsCookie, SearchSuggestionFetcher.this.mStartTimeMs);
                for (Suggestion addSuggestion : response.suggestion) {
                    SearchSuggestionFetcher.this.mSuggestionHandler.addSuggestion(addSuggestion);
                }
                listener.onComplete();
            }
        }, new ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                listener.onComplete();
            }
        });
        startRequestLatencyTimer();
    }
}
