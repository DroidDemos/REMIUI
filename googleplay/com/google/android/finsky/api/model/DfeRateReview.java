package com.google.android.finsky.api.model;

import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.protos.Rev.ReviewResponse;

public class DfeRateReview extends DfeModel implements Listener<ReviewResponse> {
    private boolean mResponseRecieved;

    public DfeRateReview(DfeApi dfeApi, String docId, String reviewId, int rating) {
        dfeApi.rateReview(docId, reviewId, rating, this, this);
    }

    public boolean isReady() {
        return this.mResponseRecieved;
    }

    public void onResponse(ReviewResponse response) {
        this.mResponseRecieved = true;
        notifyDataSetChanged();
        unregisterAll();
    }

    public void onErrorResponse(VolleyError error) {
        this.mResponseRecieved = true;
        super.onErrorResponse(error);
        unregisterAll();
    }
}
