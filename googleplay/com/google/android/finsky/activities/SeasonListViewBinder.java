package com.google.android.finsky.activities;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;
import com.android.vending.R;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.api.model.DfeList;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.api.model.OnDataChangedListener;
import com.google.android.finsky.fragments.DetailsViewBinder;
import com.google.android.finsky.fragments.PageFragment;
import com.google.android.finsky.layout.EpisodeList;
import com.google.android.finsky.layout.play.PlayStoreUiElementNode;
import com.google.android.finsky.library.Libraries;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.utils.ErrorStrings;
import com.google.android.finsky.utils.Lists;
import com.google.android.play.image.BitmapLoader;
import java.util.List;

public class SeasonListViewBinder extends DetailsViewBinder implements ErrorListener, OnDataChangedListener {
    private BitmapLoader mBitmapLoader;
    private Libraries mLibraries;
    private PageFragment mPageFragment;
    private PlayStoreUiElementNode mParentNode;
    private Bundle mRestoreBundle;
    private DfeList mSeasonsListRequest;
    private String mSelectedEpisodeId;
    private String mSelectedSeasonId;

    public void init(Context context, DfeApi api, NavigationManager navManager, PageFragment pageFragment, BitmapLoader bitmapLoader, Libraries libraries, PlayStoreUiElementNode parentNode) {
        this.mContext = context;
        this.mDfeApi = api;
        this.mNavigationManager = navManager;
        this.mInflater = LayoutInflater.from(this.mContext);
        this.mHeaderLayoutId = R.id.header;
        this.mPageFragment = pageFragment;
        this.mBitmapLoader = bitmapLoader;
        this.mLibraries = libraries;
        this.mParentNode = parentNode;
    }

    public void init(Context context, DfeApi api, NavigationManager navManager) {
        throw new IllegalStateException("this version of init is not supported by this binder.");
    }

    public void bind(Libraries libraries, EpisodeList view, String selectedSeasonId, String selectedEpisodeId, String title, String subtitle, String seasonsListUrl, boolean hasDetailsLoaded, PlayStoreUiElementNode parentNode) {
        super.bind((View) view, title, 4);
        if (hasDetailsLoaded) {
            this.mSelectedEpisodeId = selectedEpisodeId;
            this.mSelectedSeasonId = selectedSeasonId;
            this.mLayout.setVisibility(0);
            detachListeners();
            this.mSeasonsListRequest = new DfeList(this.mDfeApi, seasonsListUrl, false);
            this.mSeasonsListRequest.addDataChangedListener(this);
            this.mSeasonsListRequest.addErrorListener(this);
            this.mSeasonsListRequest.startLoadItems();
            return;
        }
        this.mLayout.setVisibility(8);
    }

    public void onDataChanged() {
        List<Document> seasons = Lists.newArrayList();
        for (int i = 0; i < this.mSeasonsListRequest.getCount(); i++) {
            seasons.add((Document) this.mSeasonsListRequest.getItem(i));
        }
        EpisodeList episodeList = this.mLayout;
        episodeList.restoreInstanceState(this.mRestoreBundle);
        episodeList.setSeasonList(this.mPageFragment, this.mDfeApi, this.mLibraries, this.mNavigationManager, this.mBitmapLoader, this.mRestoreBundle, seasons, this.mSelectedSeasonId, this.mSelectedEpisodeId, this.mParentNode);
    }

    public void onErrorResponse(VolleyError error) {
        Toast.makeText(this.mContext, ErrorStrings.get(this.mContext, error), 0).show();
    }

    public void onDestroyView() {
        detachListeners();
    }

    public void restoreInstanceState(Bundle savedInstanceState) {
        this.mRestoreBundle = savedInstanceState;
    }

    public void saveInstanceState(Bundle savedInstanceState) {
        EpisodeList episodeList = this.mLayout;
        if (episodeList != null) {
            episodeList.saveInstanceState(savedInstanceState);
        }
    }

    private void detachListeners() {
        if (this.mSeasonsListRequest != null) {
            this.mSeasonsListRequest.removeDataChangedListener(this);
            this.mSeasonsListRequest.removeErrorListener(this);
        }
    }
}
