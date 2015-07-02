package com.google.android.finsky.detailspage;

import com.google.android.finsky.api.model.Document;

public class BodyOfWorkCardClusterModule extends DfeListCardClusterModule {
    protected String getHeaderString(Document detailsDoc, boolean hasDetailsDataLoaded) {
        return detailsDoc.getBodyOfWorkHeader();
    }

    protected String getMoreBrowseUrl(Document detailsDoc, boolean hasDetailsDataLoaded) {
        return detailsDoc.getBodyOfWorkBrowseUrl();
    }

    protected String getDfeListUrl(Document detailsDoc, boolean hasDetailsDataLoaded) {
        return detailsDoc.getBodyOfWorkListUrl();
    }

    public boolean supportsTwoRows(Document detailsDoc, boolean hasDetailsDataLoaded) {
        return detailsDoc.getDocumentType() == 30;
    }
}
