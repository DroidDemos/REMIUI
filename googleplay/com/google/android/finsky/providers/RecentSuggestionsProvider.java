package com.google.android.finsky.providers;

import android.content.Intent;
import android.content.SearchRecentSuggestionsProvider;
import android.net.Uri;
import android.util.Base64;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.analytics.BackgroundEventBuilder;
import com.google.android.finsky.analytics.PlayStore.SearchSuggestionReport;
import com.google.android.finsky.experiments.FinskyExperiments;
import com.google.android.finsky.utils.FinskyLog;
import com.google.protobuf.nano.InvalidProtocolBufferNanoException;

public class RecentSuggestionsProvider extends SearchRecentSuggestionsProvider {
    private static int sCurrentBackendId;

    public RecentSuggestionsProvider() {
        setupSuggestions("com.google.android.finsky.RecentSuggestionsProvider", 1);
    }

    public static void setCurrentBackendId(int backendId) {
        sCurrentBackendId = backendId;
    }

    public static void sendSuggestionClickedLog(String suggestionData, String suggestedQuery) {
        try {
            SearchSuggestionReport report = SearchSuggestionReport.parseFrom(Base64.decode(suggestionData, 10));
            report.suggestedQuery = suggestedQuery;
            report.hasSuggestedQuery = true;
            FinskyApp.get().getEventLogger().logBackgroundEvent(new BackgroundEventBuilder(511).setSearchSuggestionReport(report).build());
        } catch (IllegalArgumentException iae) {
            FinskyLog.w("Couldn't decode bytes from suggestion", iae);
        } catch (InvalidProtocolBufferNanoException ipbne) {
            FinskyLog.w("Couldn't reconstitute proto from suggestion", ipbne);
        }
    }

    public static String getDocIdFromNavigationalQuery(Intent fromIntent) {
        Uri data = fromIntent.getData();
        if (data == null) {
            return null;
        }
        return data.getQueryParameter("id");
    }

    public static int getMaxRecentSuggestions() {
        FinskyExperiments experiments = FinskyApp.get().getExperiments();
        if (experiments.isEnabled("cl:search.cap_local_suggestions_2")) {
            return 2;
        }
        if (experiments.isEnabled("cl:search.cap_local_suggestions_3")) {
            return 3;
        }
        return Integer.MAX_VALUE;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.database.Cursor query(android.net.Uri r8, java.lang.String[] r9, java.lang.String r10, java.lang.String[] r11, java.lang.String r12) {
        /*
        r7 = this;
        r2 = "recent-only";
        r2 = r8.getQueryParameter(r2);
        if (r2 == 0) goto L_0x000d;
    L_0x0008:
        r2 = super.query(r8, r9, r10, r11, r12);
    L_0x000c:
        return r2;
    L_0x000d:
        if (r11 == 0) goto L_0x0012;
    L_0x000f:
        r2 = r11.length;
        if (r2 != 0) goto L_0x002b;
    L_0x0012:
        r2 = new java.lang.IllegalArgumentException;
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r5 = "SelectionArgs must be provided for the Uri: ";
        r3 = r3.append(r5);
        r3 = r3.append(r8);
        r3 = r3.toString();
        r2.<init>(r3);
        throw r2;
    L_0x002b:
        r2 = 0;
        r2 = r11[r2];
        r1 = r2.toLowerCase();
        r4 = new com.google.android.finsky.providers.SuggestionHandlerLegacy;
        r2 = r7.getContext();
        r4.<init>(r1, r2);
        r0 = new com.google.android.finsky.providers.RecentSearchSuggestionFetcherLegacy;
        r2 = super.query(r8, r9, r10, r11, r12);
        r3 = r7.getContext();
        r5 = com.google.android.finsky.FinskyApp.get();
        r5 = r5.getExperiments();
        r0.<init>(r1, r2, r3, r4, r5);
        r2 = sCurrentBackendId;
        switch(r2) {
            case 2: goto L_0x006b;
            default: goto L_0x0055;
        };
    L_0x0055:
        r6 = new com.google.android.finsky.providers.SearchSuggestionFetcherLegacy;
        r2 = r7.getContext();
        r3 = sCurrentBackendId;
        r6.<init>(r2, r3, r1, r4);
    L_0x0060:
        r0.gatherSuggestions();
        r6.gatherSuggestions();
        r2 = r4.getSuggestions();
        goto L_0x000c;
    L_0x006b:
        r2 = com.google.android.finsky.FinskyApp.get();
        r2 = r2.getExperiments();
        r3 = "cl:search.use_dfe_for_music_search_suggestions";
        r2 = r2.isEnabled(r3);
        if (r2 != 0) goto L_0x0055;
    L_0x007b:
        r6 = new com.google.android.finsky.providers.MusicSearchSuggestionFetcherLegacy;
        r2 = r7.getContext();
        r6.<init>(r1, r2, r4);
        goto L_0x0060;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.finsky.providers.RecentSuggestionsProvider.query(android.net.Uri, java.lang.String[], java.lang.String, java.lang.String[], java.lang.String):android.database.Cursor");
    }
}
