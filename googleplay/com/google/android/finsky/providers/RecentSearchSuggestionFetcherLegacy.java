package com.google.android.finsky.providers;

import android.content.Context;
import android.database.Cursor;
import com.google.android.finsky.experiments.FinskyExperiments;
import com.google.android.finsky.search.SuggestionFetcher;

class RecentSearchSuggestionFetcherLegacy extends SuggestionFetcher {
    private final Cursor mCursor;
    private final FinskyExperiments mFinskyExperiments;
    protected final SuggestionHandlerLegacy mSuggestionHandler;

    public RecentSearchSuggestionFetcherLegacy(String query, Cursor historyCursor, Context context, SuggestionHandlerLegacy suggestionHandler, FinskyExperiments finskyExperiments) {
        super(query, context);
        this.mCursor = historyCursor;
        this.mFinskyExperiments = finskyExperiments;
        this.mSuggestionHandler = suggestionHandler;
    }

    protected void makeRequest(OnCompleteListener listener) {
        int col = 0;
        int idColumn = 0;
        int textColumn = 0;
        for (String historyCol : this.mCursor.getColumnNames()) {
            if (historyCol.equalsIgnoreCase("_id")) {
                idColumn = col;
            } else if (historyCol.equalsIgnoreCase("suggest_text_1")) {
                textColumn = col;
            }
            col++;
        }
        int resultCount = 0;
        int maxResults = RecentSuggestionsProvider.getMaxRecentSuggestions();
        this.mCursor.moveToPosition(0);
        while (!this.mCursor.isAfterLast() && resultCount < maxResults) {
            String suggestion = this.mCursor.getString(textColumn);
            if (suggestion.startsWith(this.mQuery)) {
                resultCount++;
                this.mSuggestionHandler.addRow(this.mCursor.getInt(idColumn), Integer.valueOf(17301578), suggestion, null);
            }
            this.mCursor.moveToNext();
        }
        this.mCursor.close();
        listener.onComplete();
    }
}
