package com.google.android.finsky.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import com.android.vending.R;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.api.model.DfeSearch;
import com.google.android.finsky.api.model.DfeToc;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.layout.SearchResultCorrectionLayout;
import com.google.android.finsky.layout.play.Identifiable;
import com.google.android.finsky.layout.play.PlayRecyclerView;
import com.google.android.finsky.layout.play.PlayStoreUiElementNode;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.protos.Search.RelatedSearch;
import com.google.android.finsky.utils.ClientMutationCache;
import com.google.android.finsky.utils.CorpusResourceUtils;
import com.google.android.play.image.BitmapLoader;

public class SearchAdapter extends CardRecyclerViewAdapter {
    private final DfeSearch mDfeSearch;
    private final boolean mHasSuggestedQuery;
    private final boolean mShouldHideOrdinalsFromSearchResults;

    private static class StringBasedSpinnerAdapter extends ArrayAdapter<String> {
        public StringBasedSpinnerAdapter(Context context, String[] list) {
            super(context, 17367049, list);
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.search_spinner_selected_item, parent, false);
            ((TextView) convertView).setText((CharSequence) getItem(position));
            return convertView;
        }

        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(17367049, parent, false);
            }
            ((TextView) convertView.findViewById(16908308)).setText((CharSequence) getItem(position));
            return convertView;
        }
    }

    public SearchAdapter(Context context, DfeApi dfeApi, NavigationManager navigationManager, BitmapLoader bitmapLoader, DfeToc toc, ClientMutationCache clientMutationCache, DfeSearch dfeSearch, boolean isRestoring, PlayStoreUiElementNode parentNode) {
        super(context, dfeApi, navigationManager, bitmapLoader, toc, clientMutationCache, dfeSearch, null, null, isRestoring, false, 2, parentNode);
        this.mDfeSearch = dfeSearch;
        this.mHasSuggestedQuery = !TextUtils.isEmpty(this.mDfeSearch.getSuggestedQuery());
        this.mShouldHideOrdinalsFromSearchResults = FinskyApp.get().getExperiments().isEnabled("cl:search.hide_ordinals_from_search_results");
    }

    protected OnClickListener getClusterClickListener(Document clusterDoc, PlayStoreUiElementNode clusterNode) {
        if (NavigationManager.hasClickListener(clusterDoc)) {
            return this.mNavigationManager.getClickListener(clusterDoc, clusterNode, this.mDfeSearch.getQuery(), clusterDoc.getBackend());
        }
        return null;
    }

    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        if (viewHolder.getItemViewType() == 25) {
            bindSuggestionHeaderView(viewHolder.itemView);
        } else {
            super.onBindViewHolder(viewHolder, position);
        }
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 25) {
            return new PlayRecyclerView.ViewHolder(inflate(R.layout.search_result_correction, parent, false));
        }
        return super.onCreateViewHolder(parent, viewType);
    }

    public int getItemViewType(int position) {
        if (this.mHasSuggestedQuery) {
            if (position == 1) {
                return 25;
            }
            if (position > 1) {
                position--;
            }
        }
        return super.getItemViewType(position);
    }

    protected boolean hasFilters() {
        return this.mDfeSearch.getRelatedSearches().length > 0;
    }

    protected int getDisplayIndex(int trueIndex) {
        if (this.mShouldHideOrdinalsFromSearchResults) {
            return -1;
        }
        return super.getDisplayIndex(trueIndex);
    }

    protected void bindSpinnerData(Identifiable identifiable, final Spinner spinner, View corpusHeaderStrip) {
        identifiable.setIdentifier("corpus_selector");
        final RelatedSearch[] related = this.mDfeSearch.getRelatedSearches();
        String[] spinnerTextArray = new String[related.length];
        int selectedIndex = 0;
        int selectedBackend = 3;
        for (int i = 0; i < spinnerTextArray.length; i++) {
            RelatedSearch entry = related[i];
            spinnerTextArray[i] = entry.header;
            if (selectedIndex == 0 && entry.current) {
                selectedIndex = i;
                selectedBackend = entry.backendId;
            }
        }
        spinner.setBackgroundResource(CorpusResourceUtils.getCorpusSpinnerDrawable(selectedBackend));
        corpusHeaderStrip.setBackgroundColor(CorpusResourceUtils.getPrimaryColor(this.mContext, selectedBackend));
        int horizontalMargin = spinner.getResources().getDimensionPixelSize(R.dimen.search_spinner_selected_margin_left) + this.mCardContentPadding;
        MarginLayoutParams spinnerLp = (MarginLayoutParams) spinner.getLayoutParams();
        spinnerLp.leftMargin = horizontalMargin;
        spinnerLp.rightMargin = horizontalMargin;
        spinner.setLayoutParams(spinnerLp);
        spinner.setAdapter(new StringBasedSpinnerAdapter(this.mContext, spinnerTextArray));
        spinner.setSelection(selectedIndex);
        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                RelatedSearch item = related[position];
                if (SearchAdapter.this.mNavigationManager.getCurrentPageType() == 7 && spinner.getVisibility() == 0 && !item.current) {
                    SearchAdapter.this.mNavigationManager.goToSearch(item.searchUrl, SearchAdapter.this.mDfeSearch.getQuery(), item.backendId, SearchAdapter.this.mParentNode);
                }
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    public int getDataRowsCount() {
        return (this.mHasSuggestedQuery ? 1 : 0) + super.getDataRowsCount();
    }

    protected int getPrependedRowsCount() {
        return (this.mHasSuggestedQuery ? 1 : 0) + super.getPrependedRowsCount();
    }

    private void bindSuggestionHeaderView(SearchResultCorrectionLayout headerView) {
        String originalQuery = this.mDfeSearch.getQuery();
        String suggestionQuery = this.mDfeSearch.getSuggestedQuery();
        boolean fullPageReplaced = this.mDfeSearch.isFullPageReplaced();
        headerView.bind(originalQuery, suggestionQuery, fullPageReplaced);
        if (fullPageReplaced) {
            headerView.setOnClickListener(makeReplacedClickListener(originalQuery));
            headerView.setClickable(true);
            headerView.setFocusable(true);
        } else {
            headerView.setOnClickListener(makeSuggestionClickListener(suggestionQuery));
            headerView.setClickable(true);
            headerView.setFocusable(true);
        }
        headerView.setIdentifier("suggestion_header");
    }

    private OnClickListener makeSuggestionClickListener(final String suggestionString) {
        return new OnClickListener() {
            public void onClick(View v) {
                FinskyApp.get().getRecentSuggestions().saveRecentQuery(suggestionString, null);
                SearchAdapter.this.mNavigationManager.searchFromSuggestion(suggestionString, SearchAdapter.this.mDfeSearch.getBackendId());
            }
        };
    }

    private OnClickListener makeReplacedClickListener(final String originalString) {
        return new OnClickListener() {
            public void onClick(View v) {
                FinskyApp.get().getRecentSuggestions().saveRecentQuery(originalString, null);
                SearchAdapter.this.mNavigationManager.searchFromFullPageReplaced(originalString, SearchAdapter.this.mDfeSearch.getBackendId());
            }
        };
    }
}
