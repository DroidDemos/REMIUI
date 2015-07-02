package com.google.android.finsky.search;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.android.finsky.FinskyApp;
import org.json.JSONArray;
import org.json.JSONException;

class MusicSearchSuggestionFetcher extends SuggestionFetcher {
    static final Uri BASE_URI;
    private final SearchLogger mLogger;
    private String mRequestUrl;
    protected final SuggestionHandler mSuggestionHandler;

    static {
        BASE_URI = Uri.parse("http://www.google.com/complete/search");
    }

    public MusicSearchSuggestionFetcher(String query, Context context, SuggestionHandler suggestionHandler, SearchLogger searchLogger) {
        super(query, context);
        this.mRequestUrl = constructUrl();
        this.mSuggestionHandler = suggestionHandler;
        this.mLogger = searchLogger;
    }

    protected void makeRequest(final OnCompleteListener listener) {
        if (TextUtils.isEmpty(this.mQuery)) {
            listener.onComplete();
            return;
        }
        FinskyApp.get().getRequestQueue().add(new JsonArrayRequest(this.mRequestUrl, new Listener<JSONArray>() {
            public void onResponse(JSONArray responseArray) {
                try {
                    responseArray = responseArray.getJSONArray(1);
                    int responseCount = responseArray.length();
                    MusicSearchSuggestionFetcher.this.mLogger.logSuggestionsReceived(MusicSearchSuggestionFetcher.this.mQuery, 1, responseCount, null, MusicSearchSuggestionFetcher.this.mStartTimeMs);
                    for (int i = 0; i < responseCount; i++) {
                        MusicSearchSuggestionFetcher.this.mSuggestionHandler.addRow(responseArray.getString(i), null, null, null, null, null, false);
                    }
                } catch (JSONException e) {
                } finally {
                    listener.onComplete();
                }
            }
        }, new ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                listener.onComplete();
            }
        }));
        startRequestLatencyTimer();
    }

    private String constructUrl() {
        return BASE_URI.buildUpon().appendQueryParameter("q", this.mQuery).appendQueryParameter("json", "1").appendQueryParameter("hl", getLanguage()).appendQueryParameter("gl", getCountry()).appendQueryParameter("ds", "cse").appendQueryParameter("client", "partner").appendQueryParameter("partnerid", "skyjam-store").build().toString();
    }
}
