package com.android.common;

import android.app.SearchableInfo;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri.Builder;
import com.android.common.speech.LoggingEvents;

public class Search {
    public static final String SOURCE = "source";
    public static final String SUGGEST_COLUMN_LAST_ACCESS_HINT = "suggest_last_access_hint";

    private Search() {
    }

    public static Cursor getSuggestions(Context context, SearchableInfo searchable, String query) {
        return getSuggestions(context, searchable, query, -1);
    }

    public static Cursor getSuggestions(Context context, SearchableInfo searchable, String query, int limit) {
        if (searchable == null) {
            return null;
        }
        String authority = searchable.getSuggestAuthority();
        if (authority == null) {
            return null;
        }
        Builder uriBuilder = new Builder().scheme("content").authority(authority).query(LoggingEvents.EXTRA_CALLING_APP_NAME).fragment(LoggingEvents.EXTRA_CALLING_APP_NAME);
        String contentPath = searchable.getSuggestPath();
        if (contentPath != null) {
            uriBuilder.appendEncodedPath(contentPath);
        }
        uriBuilder.appendPath("search_suggest_query");
        String selection = searchable.getSuggestSelection();
        String[] selArgs = null;
        if (selection != null) {
            selArgs = new String[]{query};
        } else {
            uriBuilder.appendPath(query);
        }
        if (limit > 0) {
            uriBuilder.appendQueryParameter("limit", String.valueOf(limit));
        }
        return context.getContentResolver().query(uriBuilder.build(), null, selection, selArgs, null);
    }
}
