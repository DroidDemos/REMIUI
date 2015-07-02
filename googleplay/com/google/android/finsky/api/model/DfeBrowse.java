package com.google.android.finsky.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.android.volley.Response.Listener;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.protos.Browse.BrowseResponse;
import com.google.android.finsky.protos.Browse.BrowseTab;
import com.google.android.finsky.protos.Browse.QuickLink;
import com.google.android.finsky.utils.ParcelableProto;

public class DfeBrowse extends DfeModel implements Parcelable, Listener<BrowseResponse> {
    public static Creator<DfeBrowse> CREATOR;
    private BrowseResponse mBrowseResponse;

    public DfeBrowse(BrowseResponse browseResponse) {
        this.mBrowseResponse = browseResponse;
    }

    public DfeBrowse(DfeApi dfeApi, String landingUrl) {
        dfeApi.getBrowseLayout(landingUrl, this, this);
    }

    public String getTitle() {
        return this.mBrowseResponse.title;
    }

    public int getBackendId() {
        return this.mBrowseResponse.backendId;
    }

    public int getLandingTabIndex() {
        return this.mBrowseResponse.landingTabIndex;
    }

    public BrowseTab[] getBrowseTabs() {
        return this.mBrowseResponse.browseTab;
    }

    public QuickLink[] getQuickLinkList() {
        return this.mBrowseResponse.quickLink;
    }

    public int getQuickLinkTabIndex() {
        return this.mBrowseResponse.quickLinkTabIndex;
    }

    public int getQuickLinkFallbackTabIndex() {
        return this.mBrowseResponse.quickLinkFallbackTabIndex;
    }

    public byte[] getServerLogsCookie() {
        if (this.mBrowseResponse == null || this.mBrowseResponse.serverLogsCookie.length == 0) {
            return null;
        }
        return this.mBrowseResponse.serverLogsCookie;
    }

    public boolean isReady() {
        return this.mBrowseResponse != null;
    }

    public void onResponse(BrowseResponse response) {
        clearErrors();
        this.mBrowseResponse = response;
        notifyDataSetChanged();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(ParcelableProto.forProto(this.mBrowseResponse), 0);
    }

    static {
        CREATOR = new Creator<DfeBrowse>() {
            public DfeBrowse createFromParcel(Parcel source) {
                return new DfeBrowse((BrowseResponse) ParcelableProto.getProtoFromParcel(source));
            }

            public DfeBrowse[] newArray(int size) {
                return new DfeBrowse[size];
            }
        };
    }
}
