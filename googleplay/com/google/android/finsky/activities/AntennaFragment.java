package com.google.android.finsky.activities;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import com.android.vending.R;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.layout.DetailsPackSection;
import com.google.android.finsky.layout.DetailsSecondaryActionsSection;
import com.google.android.finsky.layout.DetailsTextSection;
import com.google.android.finsky.layout.SongList;
import com.google.android.finsky.protos.DocAnnotations.SectionMetadata;
import com.google.android.finsky.protos.DocumentV2.SeriesAntenna;
import com.google.android.finsky.utils.UiUtils;

public class AntennaFragment extends DetailsDataBasedFragment {
    private final int mDescriptionPaddingLeftRight;
    private final int mExtraPaddingLeftRight;
    private final int mMaxRelatedItemRows;
    private final SongListViewBinder mSongListViewBinder;

    public static AntennaFragment newInstance(Document document, String url) {
        AntennaFragment fragment = new AntennaFragment();
        fragment.setDfeTocAndUrl(FinskyApp.get().getToc(), url);
        fragment.setInitialDocument(document);
        return fragment;
    }

    public AntennaFragment() {
        this.mSongListViewBinder = new SongListViewBinder();
        Resources res = FinskyApp.get().getResources();
        this.mMaxRelatedItemRows = res.getInteger(R.integer.antenna_num_item_rows);
        this.mDescriptionPaddingLeftRight = res.getDimensionPixelSize(R.dimen.details_listing_section_extra_padding);
        this.mExtraPaddingLeftRight = res.getDimensionPixelSize(R.dimen.play_collection_edge_padding);
    }

    protected void onInitViewBinders() {
        this.mSongListViewBinder.init(this.mContext, this.mDfeApi, this.mNavigationManager);
    }

    protected int getLayoutRes() {
        return R.layout.antenna_frame;
    }

    protected void rebindViews(Bundle savedInstanceState) {
        rebindActionBar();
        Document doc = getDocument();
        SeriesAntenna antennaInfo = doc.getAntennaInfo();
        View fragmentView = getView();
        configurePromoHeroImage(fragmentView, doc, savedInstanceState);
        DetailsTextSection descriptionPanel = (DetailsTextSection) fragmentView.findViewById(R.id.description_panel);
        if (descriptionPanel != null) {
            int fullDescriptionPaddingLeftRight = this.mDescriptionPaddingLeftRight + this.mExtraPaddingLeftRight;
            descriptionPanel.setPadding(fullDescriptionPaddingLeftRight, descriptionPanel.getPaddingTop(), fullDescriptionPaddingLeftRight, descriptionPanel.getPaddingBottom());
            int backgroundFillColor = getResources().getColor(R.color.white);
            if (doc.hasImages(1)) {
                backgroundFillColor = UiUtils.getFillColor(antennaInfo, backgroundFillColor);
            }
            descriptionPanel.bindEditorialDescription(this.mNavigationManager, doc, true, savedInstanceState, this, backgroundFillColor, this);
        }
        SongList songList = (SongList) fragmentView.findViewById(R.id.song_list);
        if (songList != null) {
            SectionMetadata sectionTracks = antennaInfo.sectionTracks;
            this.mSongListViewBinder.bind(songList, null, getResources().getString(R.string.antenna_playlist), sectionTracks.header, sectionTracks.listUrl, false, Integer.MAX_VALUE, hasDetailsDataLoaded(), getLibraries(), this.mBitmapLoader, this);
        }
        DetailsPackSection bodyOfWorkPanel = (DetailsPackSection) fragmentView.findViewById(R.id.body_of_work_panel);
        if (bodyOfWorkPanel != null) {
            SectionMetadata albumMetadata = antennaInfo.sectionAlbums;
            bodyOfWorkPanel.init(this.mDfeApi, this.mNavigationManager, this.mBitmapLoader, getToc());
            Document document = doc;
            bodyOfWorkPanel.bind(document, albumMetadata.header, albumMetadata.descriptionHtml, albumMetadata.listUrl, albumMetadata.browseUrl, this.mCardItemsPerRow, this.mMaxRelatedItemRows, true, this);
        }
        DetailsSecondaryActionsSection secondaryActionsPanel = (DetailsSecondaryActionsSection) fragmentView.findViewById(R.id.secondary_actions_panel);
        if (secondaryActionsPanel != null) {
            secondaryActionsPanel.setPadding(this.mExtraPaddingLeftRight, secondaryActionsPanel.getPaddingTop(), this.mExtraPaddingLeftRight, secondaryActionsPanel.getPaddingBottom());
            Document document2 = doc;
            Bundle bundle = savedInstanceState;
            secondaryActionsPanel.bind(document2, true, this.mUrl, bundle, this.mDfeApi, this);
        }
    }

    protected void bindPromoHeroImage(Document doc, Bundle savedInstanceState) {
        this.mPromoHeroView.bindDetailsAntenna(doc, this.mBitmapLoader, this);
    }

    public void rebindActionBar() {
        this.mPageFragmentHost.updateBreadcrumb(null);
        this.mPageFragmentHost.updateActionBarMode(false);
        Document doc = getDocument();
        if (doc != null) {
            this.mPageFragmentHost.updateCurrentBackendId(doc.getBackend(), false);
        }
    }

    public void onDestroyView() {
        recordState();
        this.mSongListViewBinder.onDestroyView();
        super.onDestroyView();
    }

    protected void recordState(Bundle savedInstanceState) {
        View view = getView();
        if (view != null) {
            DetailsSecondaryActionsSection secondaryActionsPanel;
            if (view.findViewById(R.id.description_panel) != null) {
                secondaryActionsPanel = (DetailsSecondaryActionsSection) view.findViewById(R.id.secondary_actions_panel);
            } else {
                secondaryActionsPanel = (DetailsSecondaryActionsSection) view.findViewById(R.id.secondary_actions_panel);
            }
            if (secondaryActionsPanel != null) {
                secondaryActionsPanel.onSavedInstanceState(savedInstanceState);
            }
        }
    }

    protected int getPlayStoreUiElementType() {
        return 6;
    }
}
