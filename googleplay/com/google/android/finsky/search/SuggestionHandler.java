package com.google.android.finsky.search;

import android.graphics.drawable.Drawable;
import com.google.android.finsky.protos.Common.Image;
import com.google.android.finsky.protos.DocAnnotations.Link;
import com.google.android.finsky.protos.SearchSuggest.Suggestion;
import com.google.android.finsky.utils.Lists;
import com.google.android.finsky.utils.Sets;
import com.google.android.play.search.PlaySearchSuggestionModel;
import java.util.List;
import java.util.Set;

final class SuggestionHandler {
    private final Set<String> mAlreadyAddedSuggestions;
    private final List<PlaySearchSuggestionModel> mModels;

    SuggestionHandler() {
        this.mModels = Lists.newArrayList();
        this.mAlreadyAddedSuggestions = Sets.newHashSet();
    }

    public List<PlaySearchSuggestionModel> getSuggestions() {
        return this.mModels;
    }

    public void addSuggestion(Suggestion suggestion) {
        String docId;
        Link link = null;
        if (suggestion.document != null) {
            docId = suggestion.document.docid;
        } else {
            docId = null;
        }
        if (suggestion.type == 3) {
            link = suggestion.link;
        }
        addRow(suggestion.displayText, docId, null, suggestion.image, link, suggestion.serverLogsCookie, false);
    }

    public PlaySearchSuggestionModel addRow(String displayText, String docId, Drawable defaultIconDrawable, Image iconImage, Link link, byte[] serverLogsCookie, boolean isRecentSearchSuggestion) {
        if (this.mAlreadyAddedSuggestions.contains(displayText)) {
            return null;
        }
        String iconUrl = iconImage != null ? iconImage.imageUrl : null;
        PlaySearchSuggestionModel model = new FinskySearchSuggestionModel(displayText, docId, defaultIconDrawable, iconUrl, iconUrl != null ? iconImage.supportsFifeUrlOptions : false, link, serverLogsCookie, isRecentSearchSuggestion);
        this.mModels.add(model);
        this.mAlreadyAddedSuggestions.add(displayText);
        return model;
    }
}
