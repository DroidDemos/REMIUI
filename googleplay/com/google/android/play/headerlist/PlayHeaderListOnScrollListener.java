package com.google.android.play.headerlist;

import android.database.DataSetObserver;
import android.util.SparseIntArray;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.Adapter;

public class PlayHeaderListOnScrollListener implements OnScrollListener {
    private int mAbsoluteY;
    private Adapter mAdapter;
    private final PlayHeaderListLayout mLayout;
    private final DataSetObserver mObserver;
    private int mPreviousRelativeTopIndex;
    private final SparseIntArray[] mRelativeTops;
    protected int mScrollState;

    public PlayHeaderListOnScrollListener(PlayHeaderListLayout layout) {
        this.mRelativeTops = new SparseIntArray[]{new SparseIntArray(), new SparseIntArray()};
        this.mAbsoluteY = -1;
        this.mLayout = layout;
        this.mObserver = new DataSetObserver() {
            public void onChanged() {
                PlayHeaderListOnScrollListener.this.reset(false);
                PlayHeaderListOnScrollListener.this.mLayout.syncCurrentListViewOnNextScroll();
            }

            public void onInvalidated() {
                onChanged();
            }
        };
    }

    void reset() {
        reset(true);
    }

    private void reset(boolean resetAdapter) {
        previousRelativeTops().clear();
        this.mAbsoluteY = -1;
        if (resetAdapter) {
            updateAdapter(null);
        }
        this.mScrollState = 0;
    }

    private void updateAdapter(Adapter adapter) {
        if (this.mAdapter != adapter) {
            if (this.mAdapter != null) {
                this.mAdapter.unregisterDataSetObserver(this.mObserver);
            }
            this.mAdapter = adapter;
            if (this.mAdapter != null) {
                this.mAdapter.registerDataSetObserver(this.mObserver);
            }
            reset(false);
        }
    }

    private SparseIntArray previousRelativeTops() {
        return this.mRelativeTops[this.mPreviousRelativeTopIndex];
    }

    private SparseIntArray currentRelativeTops() {
        return this.mRelativeTops[(this.mPreviousRelativeTopIndex + 1) % 2];
    }

    private SparseIntArray computeRelativeTops(AbsListView view, int firstVisibleItem, int visibleItemCount, SparseIntArray relativeTops) {
        relativeTops.clear();
        for (int i = firstVisibleItem; i < firstVisibleItem + visibleItemCount; i++) {
            relativeTops.put(i, view.getChildAt(i - firstVisibleItem).getTop());
        }
        return relativeTops;
    }

    public void onScrollStateChanged(AbsListView view, int scrollState) {
        updateAdapter(view.getAdapter());
        this.mScrollState = scrollState;
        this.mLayout.onScrollStateChanged(scrollState);
        if (this.mLayout.mAppListViewOnScrollListener != null) {
            this.mLayout.mAppListViewOnScrollListener.onScrollStateChanged(view, this.mScrollState);
        }
    }

    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        visibleItemCount = Math.min(visibleItemCount, totalItemCount);
        updateAdapter(view.getAdapter());
        int deltaY = 0;
        int foundTop = -1;
        SparseIntArray previousRelativeTops = previousRelativeTops();
        SparseIntArray currentRelativeTops = currentRelativeTops();
        computeRelativeTops(view, firstVisibleItem, visibleItemCount, currentRelativeTops);
        for (int i = firstVisibleItem; i < firstVisibleItem + visibleItemCount; i++) {
            foundTop = previousRelativeTops.get(i, -1);
            if (foundTop != -1) {
                deltaY = foundTop - currentRelativeTops.get(i);
                break;
            }
        }
        this.mPreviousRelativeTopIndex = (this.mPreviousRelativeTopIndex + 1) % 2;
        if (this.mAbsoluteY == -1 || foundTop == -1) {
            this.mAbsoluteY = this.mLayout.tryGetCollectionViewAbsoluteY(view);
        } else {
            this.mAbsoluteY += deltaY;
        }
        this.mLayout.onScroll(this.mScrollState, deltaY, view.getChildCount() == 0 ? 0 : this.mAbsoluteY);
        if (this.mLayout.mAppListViewOnScrollListener != null) {
            this.mLayout.mAppListViewOnScrollListener.onScroll(view, firstVisibleItem, visibleItemCount, totalItemCount);
        }
    }
}
