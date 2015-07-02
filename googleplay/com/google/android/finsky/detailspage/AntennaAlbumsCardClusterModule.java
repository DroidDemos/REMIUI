package com.google.android.finsky.detailspage;

import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.protos.DocAnnotations.SectionMetadata;
import com.google.android.finsky.protos.DocumentV2.SeriesAntenna;

public class AntennaAlbumsCardClusterModule extends DfeListCardClusterModule {
    protected String getHeaderString(Document detailsDoc, boolean hasDetailsDataLoaded) {
        return detailsDoc.getAntennaInfo().sectionAlbums.header;
    }

    protected String getMoreBrowseUrl(Document detailsDoc, boolean hasDetailsDataLoaded) {
        return detailsDoc.getAntennaInfo().sectionAlbums.browseUrl;
    }

    protected String getDfeListUrl(Document detailsDoc, boolean hasDetailsDataLoaded) {
        SeriesAntenna antennaInfo = detailsDoc.getAntennaInfo();
        if (antennaInfo == null) {
            return null;
        }
        SectionMetadata albumMetadata = antennaInfo.sectionAlbums;
        if (albumMetadata != null) {
            return albumMetadata.listUrl;
        }
        return null;
    }

    public boolean supportsTwoRows(Document detailsDoc, boolean hasDetailsDataLoaded) {
        return true;
    }
}
