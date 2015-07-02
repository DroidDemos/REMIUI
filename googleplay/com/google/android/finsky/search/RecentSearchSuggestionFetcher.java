package com.google.android.finsky.search;

import android.content.Context;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.net.Uri.Builder;
import com.android.vending.R;
import com.google.android.finsky.providers.RecentSuggestionsProvider;

class RecentSearchSuggestionFetcher extends SuggestionFetcher {
    private static final Uri RECENT_SEARCH_URI;
    private Drawable mRecentIcon;
    private final SuggestionHandler mSuggestionHandler;

    static {
        RECENT_SEARCH_URI = new Builder().scheme("content").authority("com.google.android.finsky.RecentSuggestionsProvider").appendPath("search_suggest_query").appendQueryParameter("recent-only", "true").build();
    }

    public RecentSearchSuggestionFetcher(String query, Context context, SuggestionHandler suggestionHandler) {
        super(query, context);
        this.mSuggestionHandler = suggestionHandler;
        this.mRecentIcon = this.mContext.getResources().getDrawable(R.drawable.ic_search_recent);
    }

    protected void makeRequest(OnCompleteListener listener) {
        Cursor historyCursor = this.mContext.getContentResolver().query(RECENT_SEARCH_URI, null, " ?", new String[]{this.mQuery}, null);
        if (historyCursor != null) {
            int textColumn = historyCursor.getColumnIndex("suggest_text_1");
            if (textColumn >= 0) {
                int resultCount = 0;
                int maxResults = RecentSuggestionsProvider.getMaxRecentSuggestions();
                historyCursor.moveToPosition(0);
                while (!historyCursor.isAfterLast() && resultCount < maxResults) {
                    String suggestion = historyCursor.getString(textColumn);
                    if (suggestion.startsWith(this.mQuery)) {
                        resultCount++;
                        this.mSuggestionHandler.addRow(suggestion, null, this.mRecentIcon, null, null, null, true);
                    }
                    historyCursor.moveToNext();
                }
                historyCursor.close();
            }
        }
        listener.onComplete();
    }
}
