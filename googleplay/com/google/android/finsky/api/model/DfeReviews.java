package com.google.android.finsky.api.model;

import com.android.volley.Request;
import com.android.volley.Response.Listener;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.protos.DocumentV2.Review;
import com.google.android.finsky.protos.Rev.CriticReviewsResponse;
import com.google.android.finsky.protos.Rev.ReviewResponse;

public class DfeReviews extends PaginatedList<ReviewResponse, Review> implements Listener<ReviewResponse> {
    private DfeApi mDfeApi;
    private boolean mFilterByDevice;
    private boolean mFilterByVersion;
    private int mRating;
    private CriticReviewsResponse mRottenTomatoesReviewData;
    private int mSortType;
    private int mVersionCode;

    public DfeReviews(DfeApi dfeApi, String reviewsUrl, int versionCode, boolean autoLoadNextPage) {
        super(reviewsUrl, autoLoadNextPage);
        this.mDfeApi = dfeApi;
        this.mFilterByVersion = false;
        this.mFilterByDevice = false;
        this.mRating = 0;
        this.mVersionCode = versionCode;
        this.mSortType = -1;
    }

    public void refetchReviews() {
        resetItems();
        startLoadItems();
    }

    public boolean shouldFilterByDevice() {
        return this.mFilterByDevice;
    }

    public int getVersionFilter() {
        return this.mFilterByVersion ? this.mVersionCode : -1;
    }

    public boolean currentlyFilteringByVersion() {
        return this.mFilterByVersion;
    }

    public void setFilters(boolean filterByVersion, boolean filterByDevice) {
        this.mFilterByVersion = filterByVersion;
        this.mFilterByDevice = filterByDevice;
    }

    public int getRatingFilter() {
        return this.mRating;
    }

    public void setSortType(int sortType) {
        this.mSortType = sortType;
    }

    public int getSortType() {
        return this.mSortType;
    }

    protected Request<?> makeRequest(String url) {
        return this.mDfeApi.getReviews(url, this.mFilterByDevice, this.mFilterByVersion ? this.mVersionCode : -1, this.mRating, this.mSortType, this, this);
    }

    protected Review[] getItemsFromResponse(ReviewResponse response) {
        if (response.criticReviewsResponse == null) {
            return response.getResponse.review;
        }
        this.mRottenTomatoesReviewData = response.criticReviewsResponse;
        return this.mRottenTomatoesReviewData.review;
    }

    protected String getNextPageUrl(ReviewResponse response) {
        return response.nextPageUrl;
    }

    protected void clearDiskCache() {
        throw new IllegalStateException("not supported");
    }

    public CriticReviewsResponse getRottenTomatoesData() {
        return this.mRottenTomatoesReviewData;
    }
}
