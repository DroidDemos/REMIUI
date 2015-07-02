package com.google.android.finsky.providers;

import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.util.Base64;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.analytics.BackgroundEventBuilder;
import com.google.android.finsky.analytics.PlayStore.SearchSuggestionReport;
import com.google.android.finsky.utils.Sets;
import com.google.protobuf.nano.MessageNano;
import java.util.Set;

final class SuggestionHandlerLegacy {
    private static final String[] COLUMNS;
    private final Set<String> mAlreadyAddedSuggestions;
    protected final Context mContext;
    private String mEncodedReport;
    protected final String mQuery;
    private SearchSuggestionReport mReport;
    private final MatrixCursor mResults;

    static {
        COLUMNS = new String[]{"_id", "suggest_icon_1", "suggest_text_1", "suggest_text_2", "suggest_intent_query", "suggest_intent_action", "suggest_intent_data", "suggest_intent_extra_data"};
    }

    public SuggestionHandlerLegacy(String query, Context context) {
        this.mAlreadyAddedSuggestions = Sets.newHashSet();
        this.mResults = new MatrixCursor(COLUMNS);
        this.mEncodedReport = null;
        this.mReport = null;
        this.mQuery = query;
        this.mContext = context;
    }

    public void sendSuggestionsReceivedLog(int sourceServerId, int count, byte[] serverLogsCookie, long startTimeMs) {
        long latencyMs = System.currentTimeMillis() - startTimeMs;
        SearchSuggestionReport report = new SearchSuggestionReport();
        report.query = this.mQuery;
        report.hasQuery = true;
        report.clientLatencyMs = latencyMs;
        report.hasClientLatencyMs = true;
        report.source = sourceServerId;
        report.hasSource = true;
        report.resultCount = count;
        report.hasResultCount = true;
        if (serverLogsCookie != null && serverLogsCookie.length > 0) {
            report.responseServerLogsCookie = serverLogsCookie;
            report.hasResponseServerLogsCookie = true;
        }
        FinskyApp.get().getEventLogger().logBackgroundEvent(new BackgroundEventBuilder(510).setSearchSuggestionReport(report).build());
        this.mEncodedReport = Base64.encodeToString(MessageNano.toByteArray(report), 10);
        this.mReport = report;
    }

    public Cursor getSuggestions() {
        return this.mResults;
    }

    public void addRow(int id, Object icon, String name, byte[] serverLogsCookie) {
        addRow(id, icon, name, null, "android.intent.action.SEARCH", null, serverLogsCookie);
    }

    public void addRow(int id, Object icon, String name, String description, String action, String data, byte[] serverLogsCookie) {
        if (!this.mAlreadyAddedSuggestions.contains(name)) {
            Object[] row = new Object[COLUMNS.length];
            row[0] = Integer.valueOf(id);
            row[1] = icon;
            row[2] = name;
            row[3] = description;
            row[4] = name;
            row[5] = action;
            row[6] = data;
            if (serverLogsCookie == null || serverLogsCookie.length <= 0) {
                row[7] = this.mEncodedReport;
            } else {
                this.mReport.suggestionServerLogsCookie = serverLogsCookie;
                this.mReport.hasSuggestionServerLogsCookie = true;
                row[7] = Base64.encodeToString(MessageNano.toByteArray(this.mReport), 10);
            }
            this.mAlreadyAddedSuggestions.add(name);
            this.mResults.addRow(row);
        }
    }
}
