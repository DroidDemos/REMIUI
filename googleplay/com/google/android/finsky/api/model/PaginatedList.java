package com.google.android.finsky.api.model;

import android.text.TextUtils;
import com.android.volley.Request;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import java.util.ArrayList;
import java.util.List;

public abstract class PaginatedList<T, D> extends DfeModel implements Listener<T> {
    protected final boolean mAutoLoadNextPage;
    private int mCurrentOffset;
    private Request<?> mCurrentRequest;
    private final List<D> mItems;
    private boolean mItemsRemoved;
    private int mItemsUntilEndCount;
    private int mLastPositionRequested;
    protected T mLastResponse;
    private boolean mMoreAvailable;
    protected List<UrlOffsetPair> mUrlOffsetList;
    private int mWindowDistance;

    protected static class UrlOffsetPair {
        public final int offset;
        public final String url;

        public UrlOffsetPair(int offset, String url) {
            this.offset = offset;
            this.url = url;
        }
    }

    protected abstract void clearDiskCache();

    protected abstract D[] getItemsFromResponse(T t);

    protected abstract String getNextPageUrl(T t);

    protected abstract Request<?> makeRequest(String str);

    protected PaginatedList(String url) {
        this(url, true);
    }

    protected PaginatedList(String url, boolean autoLoadNextPage) {
        this.mWindowDistance = 12;
        this.mItems = new ArrayList();
        this.mItemsUntilEndCount = 4;
        this.mUrlOffsetList = new ArrayList();
        this.mUrlOffsetList.add(new UrlOffsetPair(0, url));
        this.mMoreAvailable = true;
        this.mAutoLoadNextPage = autoLoadNextPage;
    }

    protected PaginatedList(List<UrlOffsetPair> urlMap, int currentCount, boolean autoLoadNextPage) {
        this(null, autoLoadNextPage);
        this.mUrlOffsetList = urlMap;
        for (int i = 0; i < currentCount; i++) {
            this.mItems.add(null);
        }
    }

    public void clearDataAndReplaceInitialUrl(String url) {
        this.mUrlOffsetList.clear();
        this.mUrlOffsetList.add(new UrlOffsetPair(0, url));
        resetItems();
    }

    public List<String> getListPageUrls() {
        List<String> urls = new ArrayList(this.mUrlOffsetList.size());
        for (UrlOffsetPair pair : this.mUrlOffsetList) {
            urls.add(pair.url);
        }
        return urls;
    }

    public void clearTransientState() {
        this.mCurrentRequest = null;
    }

    public int getCount() {
        return this.mItems.size();
    }

    public void setWindowDistance(int distance) {
        this.mWindowDistance = distance;
    }

    public boolean isReady() {
        return this.mLastResponse != null || this.mItems.size() > 0;
    }

    public void onErrorResponse(VolleyError error) {
        clearTransientState();
        super.onErrorResponse(error);
    }

    public void startLoadItems() {
        if (this.mMoreAvailable && getCount() == 0) {
            clearErrors();
            requestMoreItemsIfNoRequestExists((UrlOffsetPair) this.mUrlOffsetList.get(0));
        }
    }

    public final D getItem(int position, boolean autoFetchNewItems) {
        if (autoFetchNewItems) {
            this.mLastPositionRequested = position;
        }
        D result = null;
        if (position < 0) {
            throw new IllegalArgumentException("Can't return an item with a negative index: " + position);
        }
        if (position < getCount()) {
            UrlOffsetPair wrapper;
            result = this.mItems.get(position);
            if (this.mAutoLoadNextPage && this.mMoreAvailable && position >= getCount() - this.mItemsUntilEndCount) {
                if (this.mItemsRemoved) {
                    for (int i = 0; i < this.mUrlOffsetList.size(); i++) {
                        if (((UrlOffsetPair) this.mUrlOffsetList.get(i)).offset > this.mItems.size()) {
                            int newUrlListSize = Math.max(1, i);
                            while (this.mUrlOffsetList.size() > newUrlListSize) {
                                this.mUrlOffsetList.remove(this.mUrlOffsetList.size() - 1);
                            }
                            wrapper = (UrlOffsetPair) this.mUrlOffsetList.get(this.mUrlOffsetList.size() - 1);
                            if (autoFetchNewItems) {
                                requestMoreItemsIfNoRequestExists(wrapper);
                            }
                        }
                    }
                } else {
                    wrapper = (UrlOffsetPair) this.mUrlOffsetList.get(this.mUrlOffsetList.size() - 1);
                    if (autoFetchNewItems) {
                        requestMoreItemsIfNoRequestExists(wrapper);
                    }
                }
            }
            if (result == null) {
                wrapper = null;
                for (UrlOffsetPair currentWrapper : this.mUrlOffsetList) {
                    if (currentWrapper.offset > position) {
                        break;
                    }
                    wrapper = currentWrapper;
                }
                requestMoreItemsIfNoRequestExists(wrapper);
            }
        }
        return result;
    }

    public final D getItem(int position) {
        return getItem(position, true);
    }

    public void removeItem(int position) {
        this.mItems.remove(position);
        this.mItemsRemoved = true;
        if (!(this.mCurrentRequest == null || this.mCurrentRequest.isCanceled())) {
            this.mCurrentRequest.cancel();
        }
        clearDiskCache();
    }

    public void retryLoadItems() {
        if (inErrorState()) {
            clearTransientState();
            clearErrors();
            UrlOffsetPair wrapper = null;
            if (this.mCurrentOffset != -1) {
                for (UrlOffsetPair currentWrapper : this.mUrlOffsetList) {
                    if (this.mCurrentOffset == currentWrapper.offset) {
                        wrapper = currentWrapper;
                        break;
                    }
                }
            }
            if (wrapper == null) {
                wrapper = (UrlOffsetPair) this.mUrlOffsetList.get(this.mUrlOffsetList.size() - 1);
            }
            requestMoreItemsIfNoRequestExists(wrapper);
        }
    }

    public void flushUnusedPages() {
        if (this.mLastPositionRequested >= 0) {
            int i = 0;
            while (i < this.mItems.size()) {
                if (i < (this.mLastPositionRequested - this.mWindowDistance) - 1 || i >= this.mLastPositionRequested + this.mWindowDistance) {
                    this.mItems.set(i, null);
                }
                i++;
            }
        }
    }

    public void resetItems() {
        this.mMoreAvailable = true;
        this.mItems.clear();
        notifyDataSetChanged();
    }

    public boolean isMoreAvailable() {
        return this.mMoreAvailable;
    }

    private void updateItemsUntilEndCount(int lastPageSize) {
        if (this.mItemsUntilEndCount <= 0) {
            this.mItemsUntilEndCount = 4;
        } else {
            this.mItemsUntilEndCount = Math.max(1, lastPageSize / 4);
        }
    }

    public void onResponse(T response) {
        clearErrors();
        this.mLastResponse = response;
        int oldSize = this.mItems.size();
        D[] items = getItemsFromResponse(response);
        updateItemsUntilEndCount(items.length);
        for (int i = 0; i < items.length; i++) {
            if (this.mCurrentOffset + i < this.mItems.size()) {
                this.mItems.set(this.mCurrentOffset + i, items[i]);
            } else {
                this.mItems.add(items[i]);
            }
        }
        String nextPageUrl = getNextPageUrl(response);
        boolean isNextRequestAtEndOfListAvail = false;
        if (!TextUtils.isEmpty(nextPageUrl) && (this.mCurrentOffset == oldSize || this.mItemsRemoved)) {
            this.mUrlOffsetList.add(new UrlOffsetPair(this.mItems.size(), nextPageUrl));
        }
        if (this.mItemsRemoved) {
            this.mItemsRemoved = false;
        }
        if (this.mItems.size() == ((UrlOffsetPair) this.mUrlOffsetList.get(this.mUrlOffsetList.size() - 1)).offset && items.length > 0) {
            isNextRequestAtEndOfListAvail = true;
        }
        boolean z = isNextRequestAtEndOfListAvail && this.mAutoLoadNextPage;
        this.mMoreAvailable = z;
        clearTransientState();
        notifyDataSetChanged();
    }

    private void requestMoreItemsIfNoRequestExists(UrlOffsetPair urlOffsetPair) {
        if (!inErrorState()) {
            if (!(this.mCurrentRequest == null || this.mCurrentRequest.isCanceled())) {
                if (!this.mCurrentRequest.getUrl().endsWith(urlOffsetPair.url)) {
                    this.mCurrentRequest.cancel();
                } else {
                    return;
                }
            }
            this.mCurrentOffset = urlOffsetPair.offset;
            this.mCurrentRequest = makeRequest(urlOffsetPair.url);
        }
    }
}
