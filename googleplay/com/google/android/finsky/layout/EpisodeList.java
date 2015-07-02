package com.google.android.finsky.layout;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.android.vending.R;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.analytics.PlayStore.PlayStoreUiElement;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.api.model.DfeList;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.api.model.OnDataChangedListener;
import com.google.android.finsky.fragments.PageFragment;
import com.google.android.finsky.layout.EpisodeSnippet.OnCollapsedStateChanged;
import com.google.android.finsky.layout.play.PlayStoreUiElementNode;
import com.google.android.finsky.library.AccountLibrary;
import com.google.android.finsky.library.Libraries;
import com.google.android.finsky.library.Libraries.Listener;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.utils.ErrorStrings;
import com.google.android.finsky.utils.LibraryUtils;
import com.google.android.finsky.utils.Lists;
import com.google.android.finsky.utils.Maps;
import com.google.android.finsky.utils.Sets;
import com.google.android.play.image.BitmapLoader;
import com.google.android.play.layout.PlayActionButton;
import com.google.protobuf.nano.WireFormatNano;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class EpisodeList extends LinearLayout implements OnItemSelectedListener, ErrorListener, OnDataChangedListener, OnCollapsedStateChanged, PlayStoreUiElementNode, Listener {
    private BitmapLoader mBitmapLoader;
    private PlayActionButton mBuyButton;
    private DfeApi mDfeApi;
    private String mEpisodeIdFromBundle;
    private final Map<String, EpisodeSnippet> mEpisodeSnippets;
    private LinearLayout mEpisodesContainer;
    private Pair<String, Set<String>> mEpisodesInLibraryFromFirstLoad;
    private View mInProgressSnippet;
    private Libraries mLibraries;
    private View mLoadingOverlay;
    private NavigationManager mNavigationManager;
    private Document mOldSeason;
    private PlayStoreUiElementNode mParentNode;
    private String mSeasonIdFromBundle;
    private List<Document> mSeasonList;
    private SeasonSelectionListener mSeasonSelectedListener;
    private Spinner mSeasonSpinner;
    private Document mSelectedSeason;
    private DfeList mSelectedSeasonRequest;
    private PlayStoreUiElement mUiElementProto;

    public interface SeasonSelectionListener {
        void onSeasonSelected(Document document);
    }

    private class SeasonListAdapter extends ArrayAdapter<Document> {
        public SeasonListAdapter(Context context, List<Document> seasons) {
            super(context, R.layout.tv_season_spinner_item, seasons.toArray(new Document[0]));
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.tv_season_spinner_selected_item, parent, false);
            ((TextView) convertView).setText(getSeasonTitle((Document) getItem(position)));
            return convertView;
        }

        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.tv_season_spinner_item, parent, false);
            }
            Document item = (Document) getItem(position);
            TextView spinnerText = (TextView) convertView.findViewById(R.id.dropdown_text);
            spinnerText.setText(getSeasonTitle((Document) getItem(position)));
            Resources resources = EpisodeList.this.getResources();
            if (item == EpisodeList.this.mSelectedSeason) {
                convertView.setBackgroundColor(resources.getColor(R.color.play_movies_primary));
                spinnerText.setTextColor(EpisodeList.this.getResources().getColor(R.color.white));
            } else {
                convertView.setBackgroundResource(R.drawable.play_highlight_overlay_light);
                spinnerText.setTextColor(resources.getColor(R.color.play_fg_primary));
            }
            return convertView;
        }

        private String getSeasonTitle(Document item) {
            return item.getTitle().toUpperCase();
        }
    }

    public EpisodeList(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mEpisodeSnippets = Maps.newHashMap();
        this.mUiElementProto = FinskyEventLog.obtainPlayStoreUiElement(211);
        this.mParentNode = null;
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mEpisodesContainer = (LinearLayout) findViewById(R.id.episodes);
        this.mLoadingOverlay = findViewById(R.id.overlay);
        this.mSeasonSpinner = (Spinner) findViewById(R.id.header_spinner);
        this.mBuyButton = (PlayActionButton) findViewById(R.id.buy_button);
        this.mInProgressSnippet = findViewById(R.id.episode_list_in_progress_snippet);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.mSeasonSpinner != null && this.mSeasonSpinner.getAdapter() == null) {
            this.mSeasonSpinner.setAdapter(new ArrayAdapter(getContext(), R.layout.tv_season_spinner_item));
        }
    }

    protected void onDetachedFromWindow() {
        if (this.mLibraries != null) {
            this.mLibraries.removeListener(this);
        }
        if (this.mSelectedSeasonRequest != null) {
            this.mSelectedSeasonRequest.removeDataChangedListener(this);
            this.mSelectedSeasonRequest.removeErrorListener(this);
        }
        this.mSelectedSeasonRequest = null;
        super.onDetachedFromWindow();
    }

    public void setSeasonList(PageFragment parentFragment, DfeApi dfeApi, Libraries libraries, NavigationManager navigationManager, BitmapLoader bitmapLoader, Bundle restoreBundle, List<Document> seasonList, String selectedSeasonId, String selectedEpisodeId, PlayStoreUiElementNode parentNode) {
        if (seasonList == null || seasonList.isEmpty()) {
            hideUi();
            return;
        }
        this.mSeasonList = seasonList;
        this.mLibraries = libraries;
        this.mDfeApi = dfeApi;
        this.mNavigationManager = navigationManager;
        this.mBitmapLoader = bitmapLoader;
        this.mParentNode = parentNode;
        this.mLibraries.removeListener(this);
        this.mLibraries.addListener(this);
        setDefaultSelectionState(selectedSeasonId, selectedEpisodeId);
        this.mSeasonSpinner.setAdapter(new SeasonListAdapter(getContext(), this.mSeasonList));
        this.mSeasonSpinner.setOnItemSelectedListener(this);
        this.mSelectedSeason = (Document) this.mSeasonList.get(0);
        if (!TextUtils.isEmpty(this.mSeasonIdFromBundle)) {
            for (Document season : this.mSeasonList) {
                if (season.getDocId().equals(this.mSeasonIdFromBundle)) {
                    this.mSelectedSeason = season;
                }
            }
        }
        updateSeasonNode();
        this.mSeasonSpinner.setSelection(this.mSeasonList.indexOf(this.mSelectedSeason));
        if (this.mSeasonList.size() == 1) {
            this.mSeasonSpinner.setClickable(false);
            this.mSeasonSpinner.setBackgroundResource(0);
        }
        updateSeasonBuyButton();
    }

    private void setDefaultSelectionState(String selectedSeasonId, String selectedEpisodeId) {
        if (TextUtils.isEmpty(this.mSeasonIdFromBundle)) {
            this.mSeasonIdFromBundle = selectedSeasonId;
        }
        if (TextUtils.isEmpty(this.mEpisodeIdFromBundle)) {
            this.mEpisodeIdFromBundle = selectedEpisodeId;
        }
    }

    private void setEpisodeList(Document selectedSeason, List<Document> episodeList) {
        if (!this.mEpisodeSnippets.isEmpty()) {
            this.mEpisodeSnippets.clear();
            this.mEpisodesContainer.removeAllViews();
        }
        this.mSelectedSeason = selectedSeason;
        updateSeasonNode();
        EpisodeSnippet selectedSnippet = null;
        for (Document episode : episodeList) {
            EpisodeSnippet snippet = (EpisodeSnippet) LayoutInflater.from(getContext()).inflate(R.layout.episode_snippet, this.mEpisodesContainer, false);
            boolean isNewPurchase = LibraryUtils.isOwned(episode, FinskyApp.get().getLibraries()) && !((Set) this.mEpisodesInLibraryFromFirstLoad.second).contains(episode.getDocId());
            snippet.setEpisodeDetails(this.mSelectedSeason, episode, this.mBitmapLoader, this.mNavigationManager, isNewPurchase, this, this);
            this.mEpisodesContainer.addView(snippet);
            this.mEpisodeSnippets.put(episode.getDocId(), snippet);
            if (episode.getDocId().equals(this.mEpisodeIdFromBundle)) {
                selectedSnippet = snippet;
            }
        }
        if (selectedSnippet != null) {
            selectedSnippet.expand();
        }
        if (EpisodeSnippet.hasSeasonOffer(this.mSelectedSeason) && this.mSelectedSeason.isInProgressSeason()) {
            this.mInProgressSnippet.setVisibility(0);
        } else {
            this.mInProgressSnippet.setVisibility(8);
        }
        this.mEpisodeIdFromBundle = null;
    }

    private void updateSeasonNode() {
        if (!(this.mUiElementProto.serverLogsCookie.length == 0 || Arrays.equals(this.mUiElementProto.serverLogsCookie, this.mSelectedSeason.getServerLogsCookie()))) {
            this.mUiElementProto.child = PlayStoreUiElement.emptyArray();
            this.mUiElementProto.hasServerLogsCookie = false;
            this.mUiElementProto.serverLogsCookie = WireFormatNano.EMPTY_BYTES;
        }
        FinskyEventLog.setServerLogCookie(this.mUiElementProto, this.mSelectedSeason.getServerLogsCookie());
        if (this.mSeasonSelectedListener != null) {
            this.mSeasonSelectedListener.onSeasonSelected(this.mSelectedSeason);
        }
    }

    public String getSelectedSeasonId() {
        return this.mSelectedSeason != null ? this.mSelectedSeason.getDocId() : null;
    }

    public String getSelectedEpisodeId() {
        if (this.mEpisodeSnippets != null) {
            for (String key : this.mEpisodeSnippets.keySet()) {
                if (((EpisodeSnippet) this.mEpisodeSnippets.get(key)).isExpanded()) {
                    return key;
                }
            }
        }
        return null;
    }

    private void hideUi() {
        ViewGroup parent = (ViewGroup) getParent();
        if (parent != null) {
            parent.removeView(this);
        } else {
            setVisibility(8);
        }
    }

    public void onCollapsedStateChanged(EpisodeSnippet snippet, boolean collapsed) {
        for (EpisodeSnippet current : this.mEpisodeSnippets.values()) {
            if (current != snippet) {
                current.collapseWithoutNotifyingListeners();
            }
        }
    }

    public void saveInstanceState(Bundle savedInstanceState) {
        if (!TextUtils.isEmpty(getSelectedSeasonId())) {
            savedInstanceState.putString("SeasonListViewBinder.SelectedSeasonId", getSelectedSeasonId());
        }
        if (!TextUtils.isEmpty(getSelectedEpisodeId())) {
            savedInstanceState.putString("SeasonListViewBinder.SelectedEpisodeId", getSelectedEpisodeId());
        }
        if (this.mEpisodesInLibraryFromFirstLoad != null && this.mEpisodesInLibraryFromFirstLoad.second != null) {
            savedInstanceState.putStringArrayList("SeasonListViewBinder.OwnedEpisodes", new ArrayList(this.mEpisodesInLibraryFromFirstLoad.second));
        }
    }

    public void restoreInstanceState(Bundle savedInstanceState) {
        if (!TextUtils.isEmpty(savedInstanceState.getString("SeasonListViewBinder.SelectedSeasonId"))) {
            this.mSeasonIdFromBundle = savedInstanceState.getString("SeasonListViewBinder.SelectedSeasonId");
        }
        if (!TextUtils.isEmpty(savedInstanceState.getString("SeasonListViewBinder.SelectedEpisodeId"))) {
            this.mEpisodeIdFromBundle = savedInstanceState.getString("SeasonListViewBinder.SelectedEpisodeId");
        }
        ArrayList<String> ownedEpisodes = savedInstanceState.getStringArrayList("SeasonListViewBinder.OwnedEpisodes");
        if (ownedEpisodes != null) {
            this.mEpisodesInLibraryFromFirstLoad = new Pair(this.mSeasonIdFromBundle, new HashSet(ownedEpisodes));
        }
    }

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        this.mOldSeason = this.mSelectedSeason;
        this.mSelectedSeason = (Document) parent.getAdapter().getItem(position);
        updateSeasonNode();
        updateSeasonBuyButton();
        shouldEnableLoadingOverlay(true);
        if (this.mSelectedSeasonRequest != null) {
            this.mSelectedSeasonRequest.removeDataChangedListener(this);
            this.mSelectedSeasonRequest.removeErrorListener(this);
        }
        this.mSelectedSeasonRequest = new DfeList(this.mDfeApi, this.mSelectedSeason.getCoreContentListUrl(), false);
        this.mSelectedSeasonRequest.addDataChangedListener(this);
        this.mSelectedSeasonRequest.addErrorListener(this);
        this.mSelectedSeasonRequest.startLoadItems();
    }

    private void updateSeasonBuyButton() {
        EpisodeSnippet.updateBuyButtonState(getResources(), this.mBuyButton, null, null, null, this.mSelectedSeason, false, this.mNavigationManager, this);
    }

    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    public void onAllLibrariesLoaded() {
    }

    public void onLibraryContentsChanged(AccountLibrary library) {
        if (this.mSelectedSeason != null) {
            updateSeasonBuyButton();
            onDataChanged();
        }
    }

    public void onErrorResponse(VolleyError error) {
        shouldEnableLoadingOverlay(false);
        if (!(this.mOldSeason == null || this.mOldSeason == this.mSelectedSeason)) {
            this.mSelectedSeason = this.mOldSeason;
            updateSeasonNode();
            this.mSeasonSpinner.setSelection(this.mSeasonList.indexOf(this.mOldSeason));
        }
        Toast.makeText(getContext(), ErrorStrings.get(getContext(), error), 0).show();
    }

    public void onDataChanged() {
        boolean firstTimeThroughForSeasonId = false;
        shouldEnableLoadingOverlay(false);
        if (this.mEpisodesInLibraryFromFirstLoad == null || !((String) this.mEpisodesInLibraryFromFirstLoad.first).equals(this.mSelectedSeason.getDocId())) {
            firstTimeThroughForSeasonId = true;
        }
        Set<String> ownedDocumentsFromLibrary = Sets.newHashSet();
        List<Document> episodes = Lists.newArrayList();
        for (int i = 0; i < this.mSelectedSeasonRequest.getCount(); i++) {
            Document episode = (Document) this.mSelectedSeasonRequest.getItem(i);
            episodes.add(episode);
            if (firstTimeThroughForSeasonId && LibraryUtils.isOwned(episode, this.mLibraries)) {
                ownedDocumentsFromLibrary.add(episode.getDocId());
            }
        }
        if (firstTimeThroughForSeasonId) {
            this.mEpisodesInLibraryFromFirstLoad = new Pair(this.mSelectedSeason.getDocId(), ownedDocumentsFromLibrary);
        }
        setEpisodeList(this.mSelectedSeason, episodes);
    }

    public void addSeasonSelectionListener(SeasonSelectionListener listener) {
        this.mSeasonSelectedListener = listener;
    }

    public void removeSeasonSelectionListener() {
        this.mSeasonSelectedListener = null;
    }

    private void shouldEnableLoadingOverlay(boolean enabled) {
        if (enabled) {
            this.mLoadingOverlay.setVisibility(0);
            this.mLoadingOverlay.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                }
            });
            return;
        }
        this.mLoadingOverlay.setVisibility(4);
        this.mLoadingOverlay.setOnClickListener(null);
    }

    public PlayStoreUiElement getPlayStoreUiElement() {
        return this.mUiElementProto;
    }

    public PlayStoreUiElementNode getParentNode() {
        return this.mParentNode;
    }

    public void childImpression(PlayStoreUiElementNode childNode) {
        FinskyEventLog.childImpression(this, childNode);
    }
}
