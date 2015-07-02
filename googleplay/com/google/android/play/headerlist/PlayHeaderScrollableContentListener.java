package com.google.android.play.headerlist;

import android.database.DataSetObserver;
import android.widget.Adapter;
import com.google.android.play.headerlist.PlayScrollableContentView.OnScrollListener;

public class PlayHeaderScrollableContentListener implements OnScrollListener {
    private int mAbsoluteY;
    private Adapter mAdapter;
    private final PlayHeaderListLayout mLayout;
    private final DataSetObserver mObserver;
    protected int mScrollState;

    public PlayHeaderScrollableContentListener(PlayHeaderListLayout layout) {
        this.mAbsoluteY = -1;
        this.mLayout = layout;
        this.mObserver = new DataSetObserver() {
            public void onChanged() {
                PlayHeaderScrollableContentListener.this.reset(false);
                PlayHeaderScrollableContentListener.this.mLayout.syncCurrentListViewOnNextScroll();
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
}
