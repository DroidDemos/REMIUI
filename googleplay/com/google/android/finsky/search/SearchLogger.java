package com.google.android.finsky.search;

import android.text.TextUtils;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.analytics.BackgroundEventBuilder;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.analytics.PlayStore.SearchSuggestionReport;
import com.google.android.play.search.PlaySearchSuggestionModel;

public class SearchLogger {
    private final SearchSuggestionReport mGenericSuggestionReport;

    public SearchLogger() {
        this.mGenericSuggestionReport = FinskyEventLog.obtainPlayStoreSearchSuggestionReport();
    }

    public void logSuggestionsReceived(String query, int sourceServerId, int count, byte[] responseServerLogsCookie, long startTimeMs) {
        long latencyMs = System.currentTimeMillis() - startTimeMs;
        SearchSuggestionReport report = FinskyEventLog.obtainPlayStoreSearchSuggestionReport();
        copySuggestionReportFields(query, sourceServerId, count, responseServerLogsCookie, latencyMs, report);
        this.mGenericSuggestionReport.clear();
        copySuggestionReportFields(report, this.mGenericSuggestionReport);
        FinskyApp.get().getEventLogger().logBackgroundEvent(new BackgroundEventBuilder(510).setSearchSuggestionReport(report).build());
    }

    public void logSuggestionClicked(PlaySearchSuggestionModel model, String query) {
        FinskySearchSuggestionModel finskyModel = (FinskySearchSuggestionModel) model;
        if (this.mGenericSuggestionReport != null && !finskyModel.isRecentSearchSuggestion) {
            SearchSuggestionReport report = FinskyEventLog.obtainPlayStoreSearchSuggestionReport();
            copySuggestionReportFields(this.mGenericSuggestionReport, report);
            if (finskyModel.serverLogsCookie != null && finskyModel.serverLogsCookie.length > 0) {
                report.suggestionServerLogsCookie = finskyModel.serverLogsCookie;
                report.hasSuggestionServerLogsCookie = true;
            }
            if (!TextUtils.isEmpty(model.docId)) {
                query = model.docId;
            }
            report.suggestedQuery = query;
            report.hasSuggestedQuery = true;
            FinskyApp.get().getEventLogger().logBackgroundEvent(new BackgroundEventBuilder(511).setSearchSuggestionReport(report).build());
        }
    }

    private void copySuggestionReportFields(SearchSuggestionReport src, SearchSuggestionReport dest) {
        copySuggestionReportFields(src.query, src.source, src.resultCount, src.responseServerLogsCookie, src.clientLatencyMs, dest);
    }

    private void copySuggestionReportFields(String query, int sourceServerId, int count, byte[] responseServerLogsCookie, long latencyMs, SearchSuggestionReport report) {
        report.query = query;
        report.hasQuery = true;
        report.clientLatencyMs = latencyMs;
        report.hasClientLatencyMs = true;
        report.source = sourceServerId;
        report.hasSource = true;
        report.resultCount = count;
        report.hasResultCount = true;
        if (responseServerLogsCookie != null && responseServerLogsCookie.length > 0) {
            report.responseServerLogsCookie = responseServerLogsCookie;
            report.hasResponseServerLogsCookie = true;
        }
    }
}
