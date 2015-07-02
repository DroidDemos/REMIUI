package com.google.android.finsky.detailspage;

import android.text.TextUtils;
import com.android.vending.R;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.protos.DocAnnotations.SectionMetadata;
import com.google.android.finsky.protos.DocumentV2.SeriesAntenna;

public class AntennaSongListModule extends SongListModule {
    protected Data getData(Document detailsDoc) {
        SectionMetadata sectionTracks = detailsDoc.getAntennaInfo().sectionTracks;
        Data data = new Data();
        data.album = detailsDoc;
        data.useActualTrackNumbers = false;
        data.highlightedSongDocId = null;
        data.title = this.mContext.getResources().getString(R.string.antenna_playlist);
        data.subtitle = sectionTracks.header;
        data.songsListUrl = sectionTracks.listUrl;
        return data;
    }

    protected boolean hasSongList(Document doc) {
        SeriesAntenna antennaInfo = doc.getAntennaInfo();
        if (antennaInfo == null || antennaInfo.sectionTracks == null || TextUtils.isEmpty(antennaInfo.sectionTracks.listUrl)) {
            return false;
        }
        return true;
    }
}
