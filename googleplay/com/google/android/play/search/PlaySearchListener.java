package com.google.android.play.search;

public interface PlaySearchListener {
    void onModeChanged(int i);

    void onQueryChanged(String str, boolean z);

    void onSearch(String str);

    void onSuggestionClicked(PlaySearchSuggestionModel playSearchSuggestionModel);
}
