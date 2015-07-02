package com.google.android.finsky.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.android.volley.Request;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.protos.DocList.ListResponse;
import com.google.android.finsky.protos.DocumentV2.DocV2;
import com.google.android.finsky.utils.ArrayUtils;
import java.util.ArrayList;
import java.util.List;

public class DfeList extends ContainerList<ListResponse> implements Parcelable {
    public static Creator<DfeList> CREATOR;
    private DfeApi mDfeApi;
    private String mFilteredDocId;
    private String mInitialListUrl;

    private DfeList(List<UrlOffsetPair> urlList, int currentCount, boolean autoLoadNextPage, String filteredDocId) {
        String str = null;
        super(urlList, currentCount, autoLoadNextPage);
        this.mFilteredDocId = null;
        this.mFilteredDocId = filteredDocId;
        if (urlList.size() > 0) {
            str = ((UrlOffsetPair) urlList.get(0)).url;
        }
        this.mInitialListUrl = str;
    }

    public String getInitialUrl() {
        return this.mInitialListUrl;
    }

    public DfeList(DfeApi api, String url, boolean autoLoadNextPage) {
        super(url, autoLoadNextPage);
        this.mFilteredDocId = null;
        this.mInitialListUrl = url;
        this.mDfeApi = api;
    }

    public void clearDataAndReplaceInitialUrl(String url) {
        this.mInitialListUrl = url;
        super.clearDataAndReplaceInitialUrl(url);
    }

    protected Request<?> makeRequest(String url) {
        return this.mDfeApi.getList(url, this, this);
    }

    protected Document[] getItemsFromResponse(ListResponse response) {
        if (response.doc == null || response.doc.length == 0) {
            return new Document[0];
        }
        Document[] items = updateContainerAndGetItems(response.doc[0]);
        if (TextUtils.isEmpty(this.mFilteredDocId)) {
            return items;
        }
        int filterIndex = -1;
        for (int i = 0; i < items.length; i++) {
            if (items[i].getDocId().equals(this.mFilteredDocId)) {
                filterIndex = i;
                break;
            }
        }
        if (filterIndex != -1) {
            return (Document[]) ArrayUtils.remove(items, filterIndex);
        }
        return items;
    }

    public void setDfeApi(DfeApi api) {
        this.mDfeApi = api;
    }

    public void filterDocId(String docId) {
        this.mFilteredDocId = docId;
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
        dest.writeInt(this.mAutoLoadNextPage ? 1 : 0);
        dest.writeString(this.mFilteredDocId);
    }

    static {
        CREATOR = new Creator<DfeList>() {
            public DfeList createFromParcel(Parcel source) {
                boolean autoLoadNextPage = true;
                int urlMapCount = source.readInt();
                List<UrlOffsetPair> urls = new ArrayList();
                for (int i = 0; i < urlMapCount; i++) {
                    urls.add(new UrlOffsetPair(source.readInt(), source.readString()));
                }
                int count = source.readInt();
                if (source.readInt() != 1) {
                    autoLoadNextPage = false;
                }
                return new DfeList(urls, count, autoLoadNextPage, source.readString());
            }

            public DfeList[] newArray(int size) {
                return new DfeList[size];
            }
        };
    }

    protected String getNextPageUrl(ListResponse response) {
        if (response.doc.length != 1) {
            return null;
        }
        DocV2 dfeDoc = response.doc[0];
        if (dfeDoc.containerMetadata != null) {
            return dfeDoc.containerMetadata.nextPageUrl;
        }
        return null;
    }

    protected void clearDiskCache() {
        for (int i = 0; i < this.mUrlOffsetList.size(); i++) {
            this.mDfeApi.invalidateListCache(((UrlOffsetPair) this.mUrlOffsetList.get(i)).url, true);
        }
    }
}
