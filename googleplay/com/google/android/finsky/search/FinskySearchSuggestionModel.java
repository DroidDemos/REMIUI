package com.google.android.finsky.search;

import android.graphics.drawable.Drawable;
import com.google.android.finsky.protos.DocAnnotations.Link;
import com.google.android.play.search.PlaySearchSuggestionModel;

public class FinskySearchSuggestionModel extends PlaySearchSuggestionModel {
    public final boolean isRecentSearchSuggestion;
    public final Link link;
    public final byte[] serverLogsCookie;

    public FinskySearchSuggestionModel(String displayText, String docId, Drawable defaultIconDrawable, String iconUrl, boolean iconUrlSupportsFifeOptions, Link link, byte[] serverLogsCookie, boolean isRecentSearchSuggestion) {
        super(displayText, docId, defaultIconDrawable, iconUrl, iconUrlSupportsFifeOptions);
        this.link = link;
        this.serverLogsCookie = serverLogsCookie;
        this.isRecentSearchSuggestion = isRecentSearchSuggestion;
    }
}
