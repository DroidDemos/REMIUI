package com.google.android.finsky.detailspage;

import com.android.vending.R;
import com.google.android.finsky.api.model.Document;

public class RelatedCardClusterModule extends DfeListCardClusterModule {
    protected String getHeaderString(Document detailsDoc, boolean hasDetailsDataLoaded) {
        return detailsDoc.getRelatedHeader();
    }

    protected String getMoreBrowseUrl(Document detailsDoc, boolean hasDetailsDataLoaded) {
        return detailsDoc.getRelatedBrowseUrl();
    }

    protected String getDfeListUrl(Document detailsDoc, boolean hasDetailsDataLoaded) {
        return detailsDoc.getRelatedListUrl();
    }

    public boolean supportsTwoRows(Document detailsDoc, boolean hasDetailsDataLoaded) {
        return detailsDoc.getBackend() == 2;
    }

    public int getLayoutResId() {
        if (isArtistCluster()) {
            return R.layout.artist_card_cluster_module;
        }
        return super.getLayoutResId();
    }

    private boolean isArtistCluster() {
        if (((Data) this.mModuleData).dfeList.getCount() <= 0 || ((Document) ((Data) this.mModuleData).dfeList.getItem(0)).getArtistDetails() == null) {
            return false;
        }
        return true;
    }
}
