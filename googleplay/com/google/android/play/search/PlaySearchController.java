package com.google.android.play.search;

import android.view.View;
import android.view.View.OnClickListener;
import com.google.android.play.utils.collections.Lists;
import java.util.ArrayList;

public final class PlaySearchController {
    private String mCurrentQuery;
    private int mCurrentSearchMode;
    private final ArrayList<PlaySearchListener> mListeners;
    private OnClickListener mNavigationClickListener;

    public PlaySearchController() {
        this.mListeners = Lists.newArrayList();
        this.mCurrentQuery = "";
    }

    public void addPlaySearchListener(PlaySearchListener listener) {
        this.mListeners.add(listener);
    }

    public void removePlaySearchListener(PlaySearchListener listener) {
        this.mListeners.remove(listener);
    }

    public void setOnNavButtonClickListener(OnClickListener listener) {
        this.mNavigationClickListener = listener;
    }

    public int getMode() {
        return this.mCurrentSearchMode;
    }

    public String getQuery() {
        return this.mCurrentQuery;
    }

    public void setMode(int searchMode) {
        if (this.mCurrentSearchMode != searchMode) {
            this.mCurrentSearchMode = searchMode;
            for (int i = this.mListeners.size() - 1; i >= 0; i--) {
                ((PlaySearchListener) this.mListeners.get(i)).onModeChanged(searchMode);
            }
        }
    }

    public void setQuery(String query) {
        setQueryInternal(query, true);
    }

    public void notifyQueryChange() {
        notifyQueryChangeInternal(true);
    }

    public void onSuggestionClicked(PlaySearchSuggestionModel model) {
        setQueryInternal(model.displayText, false);
        for (int i = this.mListeners.size() - 1; i >= 0; i--) {
            ((PlaySearchListener) this.mListeners.get(i)).onSuggestionClicked(model);
        }
    }

    public void onSearch() {
        for (int i = this.mListeners.size() - 1; i >= 0; i--) {
            ((PlaySearchListener) this.mListeners.get(i)).onSearch(this.mCurrentQuery);
        }
    }

    public void onNavigationClicked(View view) {
        if (this.mNavigationClickListener != null) {
            this.mNavigationClickListener.onClick(view);
        }
    }

    private void setQueryInternal(String query, boolean canUpdateSuggestions) {
        if (!this.mCurrentQuery.equals(query)) {
            this.mCurrentQuery = query;
            notifyQueryChangeInternal(canUpdateSuggestions);
        }
    }

    private void notifyQueryChangeInternal(boolean canUpdateSuggestions) {
        for (int i = this.mListeners.size() - 1; i >= 0; i--) {
            ((PlaySearchListener) this.mListeners.get(i)).onQueryChanged(this.mCurrentQuery, canUpdateSuggestions);
        }
    }
}
