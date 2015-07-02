package com.google.android.finsky.search;

import android.content.Context;
import android.os.AsyncTask;
import android.util.AttributeSet;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.utils.Utils;
import com.google.android.play.search.PlaySearch;
import com.google.android.play.search.PlaySearchSuggestionModel;
import java.util.List;

public class FinskySearch extends PlaySearch {
    private final Context mContext;
    private int mCurrentBackendId;
    private final SearchLogger mLogger;
    private NavigationManager mNavigationManager;
    private AsyncTask<Void, Void, List<PlaySearchSuggestionModel>> mSuggestionsFetcher;
    private final boolean mZeroQueryDisabled;

    public FinskySearch(Context context) {
        this(context, null);
    }

    public FinskySearch(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        this.mZeroQueryDisabled = !FinskyApp.get().getExperiments().isEnabled("cl:search.dora_searchbox_zero_query_suggest_enabled");
        this.mLogger = new SearchLogger();
    }

    public void setNavigationManager(NavigationManager navigationManager) {
        this.mNavigationManager = navigationManager;
    }

    public void setCurrentBackendId(int backendId) {
        this.mCurrentBackendId = backendId;
    }

    public void onSearch(String query) {
        super.onSearch(query);
        triggerSearch(query);
    }

    public void onQueryChanged(final String query, boolean canUpdateSuggestions) {
        super.onQueryChanged(query, canUpdateSuggestions);
        if (getMode() != 1 && canUpdateSuggestions) {
            if (this.mSuggestionsFetcher != null) {
                this.mSuggestionsFetcher.cancel(true);
            }
            this.mSuggestionsFetcher = new AsyncTask<Void, Void, List<PlaySearchSuggestionModel>>() {
                protected List<PlaySearchSuggestionModel> doInBackground(Void... params) {
                    SuggestionFetcher searchSuggestionFetcher;
                    SuggestionHandler suggestionHandler = new SuggestionHandler();
                    if (FinskySearch.this.mCurrentBackendId != 2 || FinskyApp.get().getExperiments().isEnabled("cl:search.use_dfe_for_music_search_suggestions")) {
                        searchSuggestionFetcher = new SearchSuggestionFetcher(FinskySearch.this.mContext, FinskySearch.this.mCurrentBackendId, query, suggestionHandler, FinskySearch.this.mZeroQueryDisabled, FinskySearch.this.mLogger);
                    } else {
                        searchSuggestionFetcher = new MusicSearchSuggestionFetcher(query, FinskySearch.this.getContext(), suggestionHandler, FinskySearch.this.mLogger);
                    }
                    new RecentSearchSuggestionFetcher(query, FinskySearch.this.mContext, suggestionHandler).gatherSuggestions();
                    searchSuggestionFetcher.gatherSuggestions();
                    return suggestionHandler.getSuggestions();
                }

                protected void onPostExecute(List<PlaySearchSuggestionModel> suggestionModels) {
                    if (!isCancelled()) {
                        FinskySearch.this.setSuggestions(suggestionModels);
                    }
                }
            };
            Utils.executeMultiThreaded(this.mSuggestionsFetcher, new Void[0]);
        }
    }

    public void onSuggestionClicked(PlaySearchSuggestionModel model) {
        FinskySearchSuggestionModel finskyModel = (FinskySearchSuggestionModel) model;
        super.onSuggestionClicked(model);
        String query = model.displayText;
        this.mLogger.logSuggestionClicked(model, query);
        if (finskyModel.link == null) {
            triggerSearch(query);
        } else {
            this.mNavigationManager.resolveLink(finskyModel.link, FinskyApp.get().getToc(), this.mContext.getPackageManager());
        }
    }

    private void triggerSearch(String query) {
        FinskyApp.get().getRecentSuggestions().saveRecentQuery(query, null);
        if (this.mNavigationManager != null) {
            this.mNavigationManager.goToSearch(query, this.mCurrentBackendId, null);
        }
    }
}
