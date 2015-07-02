package com.google.android.finsky.api.model;

import com.android.volley.Response.Listener;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.protos.Details.DetailsResponse;
import com.google.android.finsky.protos.Details.DiscoveryBadge;
import com.google.android.finsky.protos.DocumentV2.Review;
import java.util.Collection;

public class DfeDetails extends DfeModel implements Listener<DetailsResponse> {
    private DetailsResponse mDetailsResponse;
    private final String mDetailsUrl;

    public DfeDetails(DfeApi dfeApi, String detailsUrl) {
        this(dfeApi, detailsUrl, false, null);
    }

    public DfeDetails(DfeApi dfeApi, String detailsUrl, boolean noPrefetch, Collection<String> voucherIds) {
        this.mDetailsUrl = detailsUrl;
        dfeApi.getDetails(this.mDetailsUrl, noPrefetch, false, voucherIds, this, this);
    }

    public Document getDocument() {
        if (this.mDetailsResponse == null || this.mDetailsResponse.docV2 == null) {
            return null;
        }
        return new Document(this.mDetailsResponse.docV2);
    }

    public boolean isReady() {
        return this.mDetailsResponse != null;
    }

    public void onResponse(DetailsResponse response) {
        this.mDetailsResponse = response;
        notifyDataSetChanged();
    }

    public Review getUserReview() {
        if (this.mDetailsResponse == null || this.mDetailsResponse.userReview == null) {
            return null;
        }
        return this.mDetailsResponse.userReview;
    }

    public String getFooterHtml() {
        if (this.mDetailsResponse == null || this.mDetailsResponse.footerHtml.length() == 0) {
            return null;
        }
        return this.mDetailsResponse.footerHtml;
    }

    public byte[] getServerLogsCookie() {
        if (this.mDetailsResponse == null || this.mDetailsResponse.serverLogsCookie.length == 0) {
            return null;
        }
        return this.mDetailsResponse.serverLogsCookie;
    }

    public DiscoveryBadge[] getDiscoveryBadges() {
        if (this.mDetailsResponse == null) {
            return null;
        }
        return this.mDetailsResponse.discoveryBadge;
    }
}
