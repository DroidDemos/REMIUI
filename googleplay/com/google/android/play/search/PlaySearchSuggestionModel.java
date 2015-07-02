package com.google.android.play.search;

import android.graphics.drawable.Drawable;

public class PlaySearchSuggestionModel {
    public final Drawable defaultIconDrawable;
    public final String displayText;
    public final String docId;
    public final String iconUrl;
    public final boolean iconUrlSupportsFifeOptions;

    public PlaySearchSuggestionModel(String displayText, String docId, Drawable defaultIconDrawable, String iconUrl, boolean iconUrlSupportsFifeOptions) {
        this.displayText = displayText;
        this.docId = docId;
        this.defaultIconDrawable = defaultIconDrawable;
        this.iconUrl = iconUrl;
        this.iconUrlSupportsFifeOptions = iconUrlSupportsFifeOptions;
    }
}
