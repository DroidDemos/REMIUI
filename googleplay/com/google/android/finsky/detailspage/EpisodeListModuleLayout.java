package com.google.android.finsky.detailspage;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
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
import com.android.vending.R;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.layout.EpisodeSnippet;
import com.google.android.finsky.layout.EpisodeSnippet.OnCollapsedStateChanged;
import com.google.android.finsky.layout.play.PlayStoreUiElementNode;
import com.google.android.finsky.library.Library;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.utils.LibraryUtils;
import com.google.android.play.image.BitmapLoader;
import com.google.android.play.layout.PlayActionButton;
import java.util.List;
import java.util.Set;

public class EpisodeListModuleLayout extends LinearLayout implements OnCollapsedStateChanged {
    private BitmapLoader mBitmapLoader;
    private PlayActionButton mBuyButton;
    private Document mCurrentSeason;
    private EpisodeSelectionListener mEpisodeSelectionListener;
    private LinearLayout mEpisodesContainer;
    private boolean mHasBinded;
    private View mInProgressSnippet;
    private View mLoadingOverlay;
    private NavigationManager mNavigationManager;
    private PlayStoreUiElementNode mPlayStoreUiElementNode;
    private Spinner mSeasonSpinner;
    private List<Document> mSeasons;

    public interface EpisodeSelectionListener {
        void onEpisodeSelected(Document document);

        void onSeasonSelected(Document document);
    }

    public class SeasonListAdapter extends ArrayAdapter<Document> {
        public SeasonListAdapter(Context context, List<Document> seasons) {
            super(context, R.layout.tv_season_spinner_item, seasons.toArray(new Document[seasons.size()]));
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.tv_season_spinner_selected_item, parent, false);
            }
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
            Resources resources = getContext().getResources();
            if (item == EpisodeListModuleLayout.this.mCurrentSeason) {
                convertView.setBackgroundColor(resources.getColor(R.color.play_movies_primary));
                spinnerText.setTextColor(resources.getColor(R.color.white));
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

    public EpisodeListModuleLayout(Context context) {
        super(context);
    }

    public EpisodeListModuleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EpisodeListModuleLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void onFinishInflate() {
        this.mEpisodesContainer = (LinearLayout) findViewById(R.id.episodes);
        this.mLoadingOverlay = findViewById(R.id.overlay);
        this.mBuyButton = (PlayActionButton) findViewById(R.id.buy_button);
        this.mInProgressSnippet = findViewById(R.id.episode_list_in_progress_snippet);
        this.mSeasonSpinner = (Spinner) findViewById(R.id.header_spinner);
        this.mSeasonSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                EpisodeListModuleLayout.this.mEpisodeSelectionListener.onSeasonSelected((Document) adapterView.getAdapter().getItem(i));
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    public void bind(EpisodeSelectionListener episodeSelectedListener, BitmapLoader bitmapLoader, PlayStoreUiElementNode node, NavigationManager navManager, List<Document> seasons, Document selectedSeason, List<Document> episodes, Document selectedEpisode, Library library, Set<String> initiallyOwnedEpisodes, boolean isLoading) {
        this.mHasBinded = true;
        this.mBitmapLoader = bitmapLoader;
        this.mPlayStoreUiElementNode = node;
        this.mNavigationManager = navManager;
        this.mEpisodeSelectionListener = episodeSelectedListener;
        if (this.mSeasons != seasons) {
            this.mSeasons = seasons;
            this.mSeasonSpinner.setAdapter(new SeasonListAdapter(getContext(), this.mSeasons));
        }
        if (this.mCurrentSeason != selectedSeason) {
            this.mCurrentSeason = selectedSeason;
            this.mSeasonSpinner.setSelection(seasons.indexOf(selectedSeason));
            updateSeasonBuyButton(selectedSeason);
        }
        if (seasons.size() <= 1) {
            this.mSeasonSpinner.setClickable(false);
            this.mSeasonSpinner.setBackgroundResource(0);
        }
        if (isLoading) {
            showLoadingOverlay();
            return;
        }
        updateEpisodeList(selectedSeason, episodes, selectedEpisode, library, initiallyOwnedEpisodes);
        hideLoadingOverlay();
    }

    public void updateEpisodeList(Document selectedSeason, List<Document> episodes, Document selectedEpisode, Library library, Set<String> initiallyOwnedEpisodes) {
        int i;
        EpisodeSnippet selectedSnippet = null;
        int episodeCount = episodes.size();
        int existingSnippets = this.mEpisodesContainer.getChildCount();
        LayoutInflater inflater = null;
        for (i = 0; i < episodeCount; i++) {
            Document episode = (Document) episodes.get(i);
            EpisodeSnippet snippet;
            boolean isNewSnippet;
            boolean isNewPurchase;
            if (i < existingSnippets) {
                snippet = (EpisodeSnippet) this.mEpisodesContainer.getChildAt(i);
                isNewSnippet = false;
                if (snippet.getEpisode() == episode) {
                }
                if (episode == selectedEpisode) {
                    selectedSnippet = snippet;
                }
                isNewPurchase = LibraryUtils.isOwned(episode, library) && !initiallyOwnedEpisodes.contains(episode.getDocId());
                snippet.setEpisodeDetails(selectedSeason, episode, this.mBitmapLoader, this.mNavigationManager, isNewPurchase, this, this.mPlayStoreUiElementNode);
                if (isNewSnippet) {
                    snippet.updateContentView();
                } else {
                    this.mEpisodesContainer.addView(snippet, i);
                }
                snippet.setVisibility(0);
            } else {
                if (inflater == null) {
                    inflater = LayoutInflater.from(getContext());
                }
                snippet = (EpisodeSnippet) inflater.inflate(R.layout.episode_snippet, this.mEpisodesContainer, false);
                isNewSnippet = true;
                if (episode == selectedEpisode) {
                    selectedSnippet = snippet;
                }
                if (!LibraryUtils.isOwned(episode, library)) {
                }
                snippet.setEpisodeDetails(selectedSeason, episode, this.mBitmapLoader, this.mNavigationManager, isNewPurchase, this, this.mPlayStoreUiElementNode);
                if (isNewSnippet) {
                    snippet.updateContentView();
                } else {
                    this.mEpisodesContainer.addView(snippet, i);
                }
                snippet.setVisibility(0);
            }
        }
        for (i = episodeCount; i < existingSnippets; i++) {
            this.mEpisodesContainer.getChildAt(i).setVisibility(8);
        }
        if (!(selectedSnippet == null || selectedSnippet.isExpanded())) {
            selectedSnippet.expand();
        }
        this.mEpisodesContainer.refreshDrawableState();
        if (EpisodeSnippet.hasSeasonOffer(selectedSeason) && selectedSeason.isInProgressSeason()) {
            this.mInProgressSnippet.setVisibility(0);
        } else {
            this.mInProgressSnippet.setVisibility(8);
        }
    }

    public void updateSeasonBuyButton(Document selectedSeason) {
        EpisodeSnippet.updateBuyButtonState(getResources(), this.mBuyButton, null, null, null, selectedSeason, false, this.mNavigationManager, this.mPlayStoreUiElementNode);
    }

    public boolean hasBinded() {
        return this.mHasBinded;
    }

    public void showLoadingOverlay() {
        if (this.mLoadingOverlay != null) {
            this.mLoadingOverlay.setVisibility(0);
            this.mLoadingOverlay.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                }
            });
        }
    }

    public void hideLoadingOverlay() {
        if (this.mLoadingOverlay != null) {
            this.mLoadingOverlay.setVisibility(4);
            this.mLoadingOverlay.setOnClickListener(null);
        }
    }

    public void onCollapsedStateChanged(EpisodeSnippet changedSnippet, boolean collapsed) {
        int snippetCount = this.mEpisodesContainer.getChildCount();
        for (int i = 0; i < snippetCount; i++) {
            EpisodeSnippet other = (EpisodeSnippet) this.mEpisodesContainer.getChildAt(i);
            if (other != changedSnippet) {
                other.collapseWithoutNotifyingListeners();
            }
        }
        if (changedSnippet.isExpanded()) {
            this.mEpisodeSelectionListener.onEpisodeSelected(changedSnippet.getEpisode());
        } else {
            this.mEpisodeSelectionListener.onEpisodeSelected(null);
        }
    }

    public void setSelectedSeasonIndex(int seasonIndex) {
        this.mSeasonSpinner.setSelection(seasonIndex);
    }
}
