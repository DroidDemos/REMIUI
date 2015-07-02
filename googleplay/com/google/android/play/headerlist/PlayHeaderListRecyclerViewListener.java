package com.google.android.play.headerlist;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.AdapterDataObserver;
import android.support.v7.widget.RecyclerView.OnScrollListener;

public class PlayHeaderListRecyclerViewListener extends OnScrollListener {
    private int mAbsoluteY;
    private Adapter<?> mAdapter;
    private final PlayHeaderListLayout mLayout;
    private final AdapterDataObserver mObserver;
    protected int mScrollState;

    public PlayHeaderListRecyclerViewListener(PlayHeaderListLayout layout) {
        this.mAbsoluteY = -1;
        this.mLayout = layout;
        this.mObserver = new AdapterDataObserver() {
            public void onChanged() {
                PlayHeaderListRecyclerViewListener.this.reset(false);
                PlayHeaderListRecyclerViewListener.this.mLayout.syncCurrentListViewOnNextScroll();
            }

            public void onItemRangeChanged(int positionStart, int itemCount) {
                onChanged();
            }

            public void onItemRangeInserted(int positionStart, int itemCount) {
                onChanged();
            }

            public void onItemRangeRemoved(int positionStart, int itemCount) {
                onChanged();
            }
        };
    }

    public void reset() {
        reset(true);
    }

    private void reset(boolean resetAdapter) {
        this.mAbsoluteY = -1;
        if (resetAdapter) {
            updateAdapter(null);
        }
        this.mScrollState = 0;
    }

    private void updateAdapter(Adapter<?> adapter) {
        if (this.mAdapter != adapter) {
            if (this.mAdapter != null) {
                this.mAdapter.unregisterAdapterDataObserver(this.mObserver);
            }
            this.mAdapter = adapter;
            if (this.mAdapter != null) {
                this.mAdapter.registerAdapterDataObserver(this.mObserver);
            }
            reset(false);
        }
    }

    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        updateAdapter(recyclerView.getAdapter());
        this.mScrollState = newState;
        this.mLayout.onScrollStateChanged(newState);
        if (this.mLayout.mAppRecyclerViewOnScrollListener != null) {
            this.mLayout.mAppRecyclerViewOnScrollListener.onScrollStateChanged(recyclerView, newState);
        }
    }

    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        updateAdapter(recyclerView.getAdapter());
        if (this.mAbsoluteY == -1) {
            this.mAbsoluteY = this.mLayout.tryGetCollectionViewAbsoluteY(recyclerView);
        } else {
            this.mAbsoluteY += dy;
        }
        this.mLayout.onScroll(this.mScrollState, dy, recyclerView.getChildCount() == 0 ? 0 : this.mAbsoluteY);
        if (this.mLayout.mAppRecyclerViewOnScrollListener != null) {
            this.mLayout.mAppRecyclerViewOnScrollListener.onScrolled(recyclerView, dx, dy);
        }
    }
}
