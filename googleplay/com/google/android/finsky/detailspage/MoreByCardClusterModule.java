package com.google.android.finsky.detailspage;

import android.content.res.Resources;
import com.android.vending.R;
import com.google.android.finsky.api.model.Document;

public class MoreByCardClusterModule extends DfeListCardClusterModule {
    protected boolean requiresFullDocument() {
        return true;
    }

    protected boolean showsCreatorDataInHeader(Document detailsDoc) {
        return (detailsDoc.getCreatorDoc() == null || detailsDoc.getDocumentType() == 2) ? false : true;
    }

    protected String getHeaderString(Document detailsDoc, boolean hasDetailsDataLoaded) {
        return showsCreatorDataInHeader(detailsDoc) ? detailsDoc.getCreator() : detailsDoc.getMoreByHeader();
    }

    protected String getMoreBrowseUrl(Document detailsDoc, boolean hasDetailsDataLoaded) {
        return showsCreatorDataInHeader(detailsDoc) ? null : detailsDoc.getMoreByBrowseUrl();
    }

    protected String getDfeListUrl(Document detailsDoc, boolean hasDetailsDataLoaded) {
        return detailsDoc.getMoreByListUrl();
    }

    protected String getMoreString(Document detailsDoc, boolean hasBrowseUrl) {
        if (detailsDoc.getCreatorDoc() == null) {
            return super.getMoreString(detailsDoc, hasBrowseUrl);
        }
        Resources res = this.mContext.getResources();
        String result = res.getString(R.string.see_more_results_no_count);
        if (detailsDoc.getDocumentType() == 5) {
            return res.getString(R.string.explore);
        }
        return result;
    }
}
