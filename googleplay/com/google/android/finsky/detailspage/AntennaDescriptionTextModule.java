package com.google.android.finsky.detailspage;

import android.content.res.Resources;
import android.text.Html;
import android.text.TextUtils;
import com.android.vending.R;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.protos.DocumentV2.SeriesAntenna;
import com.google.android.finsky.utils.UiUtils;

public class AntennaDescriptionTextModule extends TextModule {
    protected Data getData(Document detailsDoc, boolean hasDetailsLoaded) {
        CharSequence body = Html.fromHtml(detailsDoc.getRawDescription());
        if (TextUtils.isEmpty(body)) {
            return null;
        }
        SeriesAntenna antennaInfo = detailsDoc.getAntennaInfo();
        if (antennaInfo == null) {
            return null;
        }
        Data data = new Data();
        data.backend = detailsDoc.getBackend();
        data.docType = detailsDoc.getDocumentType();
        data.callout = null;
        data.calloutGravity = 3;
        data.restrictCalloutMaxLines = false;
        Resources res = this.mContext.getResources();
        data.bodyHeader = null;
        data.body = body;
        int backgroundFillColor = res.getColor(R.color.white);
        if (detailsDoc.hasImages(1)) {
            backgroundFillColor = UiUtils.getFillColor(antennaInfo, backgroundFillColor);
        }
        data.backgroundFillColor = Integer.valueOf(backgroundFillColor);
        return data;
    }
}
