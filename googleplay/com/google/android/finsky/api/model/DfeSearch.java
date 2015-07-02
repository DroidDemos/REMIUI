package com.google.android.finsky.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.android.volley.Request;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.protos.DocumentV2.DocV2;
import com.google.android.finsky.protos.Search.RelatedSearch;
import com.google.android.finsky.protos.Search.SearchResponse;
import java.util.ArrayList;
import java.util.List;

public class DfeSearch extends ContainerList<SearchResponse> implements Parcelable {
    public static Creator<DfeSearch> CREATOR;
    private Boolean mAggregatedQuery;
    private DfeApi mDfeApi;
    private boolean mFullPageReplaced;
    private final String mInitialUrl;
    private String mQuery;
    private String mSuggestedQuery;

    private DfeSearch(List<UrlOffsetPair> urlList, int currentCount, String query, Boolean isAggregatedQuery) {
        String str = null;
        super(urlList, currentCount, true);
        this.mFullPageReplaced = false;
        this.mAggregatedQuery = null;
        this.mQuery = query;
        this.mAggregatedQuery = isAggregatedQuery;
        if (urlList.size() > 0) {
            str = ((UrlOffsetPair) urlList.get(0)).url;
        }
        this.mInitialUrl = str;
    }

    public DfeSearch(DfeApi api, String query, String searchUrl) {
        super(searchUrl);
        this.mFullPageReplaced = false;
        this.mAggregatedQuery = null;
        this.mInitialUrl = searchUrl;
        this.mDfeApi = api;
        this.mQuery = query;
    }

    protected Request<?> makeRequest(String url) {
        return this.mDfeApi.search(url, this, this);
    }

    protected Document[] getItemsFromResponse(SearchResponse response) {
        if (this.mAggregatedQuery == null) {
            this.mAggregatedQuery = Boolean.valueOf(response.aggregateQuery);
        }
        if (response.suggestedQuery.length() > 0) {
            this.mSuggestedQuery = response.suggestedQuery;
            this.mFullPageReplaced = response.fullPageReplaced;
        }
        if (response.doc == null || response.doc.length == 0) {
            return new Document[0];
        }
        return updateContainerAndGetItems(response.doc[0]);
    }

    public boolean hasBackendId() {
        return this.mAggregatedQuery != null;
    }

    public boolean isAggregateResult() {
        return this.mAggregatedQuery.booleanValue();
    }

    public RelatedSearch[] getRelatedSearches() {
        return ((SearchResponse) this.mLastResponse).relatedSearch;
    }

    public boolean containsHighlight() {
        return ((SearchResponse) this.mLastResponse).containsSnow;
    }

    public String getSuggestedQuery() {
        return this.mSuggestedQuery;
    }

    public boolean isFullPageReplaced() {
        return this.mFullPageReplaced;
    }

    public String getQuery() {
        return this.mQuery;
    }

    public int getBackendId() {
        if (isAggregateResult()) {
            return 0;
        }
        return super.getBackendId();
    }

    protected String getNextPageUrl(SearchResponse response) {
        if (response.doc.length != 1) {
            return null;
        }
        DocV2 dfeDoc = response.doc[0];
        if (dfeDoc.containerMetadata != null) {
            return dfeDoc.containerMetadata.nextPageUrl;
        }
        return null;
    }

    public byte[] getServerLogsCookie() {
        if (this.mLastResponse == null || ((SearchResponse) this.mLastResponse).serverLogsCookie.length == 0) {
            return null;
        }
        return ((SearchResponse) this.mLastResponse).serverLogsCookie;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mUrlOffsetList.size());
        for (UrlOffsetPair wrapper : this.mUrlOffsetList) {
            dest.writeInt(wrapper.offset);
            dest.writeString(wrapper.url);
        }
        dest.writeInt(getCount());
        dest.writeString(this.mQuery);
        if (this.mAggregatedQuery == null) {
            dest.writeInt(-1);
        } else {
            dest.writeInt(this.mAggregatedQuery.booleanValue() ? 1 : 0);
        }
    }

    static {
        CREATOR = new Creator<DfeSearch>() {
            public DfeSearch createFromParcel(Parcel source) {
                boolean z = true;
                int urlMapCount = source.readInt();
                List<UrlOffsetPair> urls = new ArrayList();
                for (int i = 0; i < urlMapCount; i++) {
                    urls.add(new UrlOffsetPair(source.readInt(), source.readString()));
                }
                int count = source.readInt();
                String query = source.readString();
                int aggregatedQueryInt = source.readInt();
                Boolean isAggregatedQuery = null;
                if (aggregatedQueryInt >= 0) {
                    if (aggregatedQueryInt != 1) {
                        z = false;
                    }
                    isAggregatedQuery = Boolean.valueOf(z);
                }
                return new DfeSearch(urls, count, query, isAggregatedQuery);
            }

            public DfeSearch[] newArray(int size) {
                return new DfeSearch[size];
            }
        };
    }

    protected void clearDiskCache() {
        throw new IllegalStateException("not supported");
    }
}
