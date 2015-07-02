package com.google.android.finsky.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.android.vending.R;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.layout.FreeSongOfTheDayAlbumView;
import com.google.android.finsky.layout.FreeSongOfTheDaySummary;
import com.google.android.finsky.protos.DocumentV2.DealOfTheDay;
import com.google.android.finsky.utils.CorpusResourceUtils;

public class FreeSongOfTheDayFragment extends DetailsDataBasedFragment {
    public static FreeSongOfTheDayFragment newInstance(Document document, String url) {
        FreeSongOfTheDayFragment fragment = new FreeSongOfTheDayFragment();
        fragment.setDfeTocAndUrl(FinskyApp.get().getToc(), url);
        fragment.setInitialDocument(document);
        return fragment;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.mPageFragmentHost.getActionBarController().disableActionBarOverlay();
    }

    protected void onInitViewBinders() {
    }

    public void rebindActionBar() {
        this.mPageFragmentHost.updateBreadcrumb(null);
        this.mPageFragmentHost.updateActionBarMode(false);
        if (getDocument() != null) {
            this.mPageFragmentHost.updateCurrentBackendId(getDocument().getBackend(), false);
        }
    }

    protected int getLayoutRes() {
        return R.layout.free_song_of_the_day_frame;
    }

    protected void rebindViews(Bundle savedInstanceState) {
        rebindActionBar();
        Document doc = getDocument();
        Document songDoc = doc.getChildAt(0);
        DealOfTheDay dealOfTheDayInfo = doc.getDealOfTheDayInfo();
        View fragmentView = getView();
        TextView header = (TextView) fragmentView.findViewById(R.id.header);
        header.setText(dealOfTheDayInfo.featuredHeader.toUpperCase());
        header.setTextColor(CorpusResourceUtils.getSecondaryTextColor(this.mContext, doc.getBackend()));
        fragmentView.findViewById(R.id.header_separator).setBackgroundColor(CorpusResourceUtils.getPrimaryColor(this.mContext, doc.getBackend()));
        ((FreeSongOfTheDaySummary) fragmentView.findViewById(R.id.summary_panel)).showSummary(songDoc, this.mNavigationManager, this);
        ((TextView) fragmentView.findViewById(R.id.description)).setText(doc.getDescription());
        FreeSongOfTheDayAlbumView albumPanel = (FreeSongOfTheDayAlbumView) fragmentView.findViewById(R.id.album_panel);
        if (albumPanel != null) {
            albumPanel.init(this.mDfeApi, this.mNavigationManager, this.mBitmapLoader);
            albumPanel.bind(doc, songDoc.getDetailsUrl(), this);
        }
    }

    protected void recordState(Bundle savedInstanceState) {
    }

    protected int getPlayStoreUiElementType() {
        return 8;
    }
}
