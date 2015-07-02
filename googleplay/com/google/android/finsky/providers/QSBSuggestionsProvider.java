package com.google.android.finsky.providers;

import android.content.SearchRecentSuggestionsProvider;
import android.database.Cursor;
import android.net.Uri;

public class QSBSuggestionsProvider extends SearchRecentSuggestionsProvider {
    public QSBSuggestionsProvider() {
        setupSuggestions("com.google.android.finsky.QSBSuggestionsProvider2", 1);
    }

    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        if (selectionArgs == null || selectionArgs.length == 0) {
            throw new IllegalArgumentException("SelectionArgs must be provided for the Uri: " + uri);
        }
        String query = selectionArgs[0].toLowerCase();
        SuggestionHandlerLegacy suggestionHandler = new SuggestionHandlerLegacy(query, getContext());
        new SearchSuggestionFetcherLegacy(getContext(), 0, query, suggestionHandler).gatherSuggestions();
        return suggestionHandler.getSuggestions();
    }
}
