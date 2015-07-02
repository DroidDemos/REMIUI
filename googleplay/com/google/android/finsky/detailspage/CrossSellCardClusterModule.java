package com.google.android.finsky.detailspage;

import com.google.android.finsky.api.model.Document;

public class CrossSellCardClusterModule extends DfeListCardClusterModule {
    protected String getHeaderString(Document detailsDoc, boolean hasDetailsDataLoaded) {
        return detailsDoc.getCrossSellHeader();
    }

    protected String getMoreBrowseUrl(Document detailsDoc, boolean hasDetailsDataLoaded) {
        return detailsDoc.getCrossSellBrowseUrl();
    }

    protected String getDfeListUrl(Document detailsDoc, boolean hasDetailsDataLoaded) {
        return detailsDoc.getCrossSellListUrl();
    }
}
