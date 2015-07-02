package com.google.android.finsky.layout.play;

import android.content.Context;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.AdapterDataObserver;
import android.util.AttributeSet;
import android.view.View;

public class PlayRecyclerView extends RecyclerView {
    private View mEmptyView;
    private AdapterDataObserver mObserver;

    public static class ViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder {
        public ViewHolder(View v) {
            super(v);
        }
    }

    public PlayRecyclerView(Context context) {
        super(context);
    }

    public PlayRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Parcelable saveInstanceState() {
        return super.onSaveInstanceState();
    }

    public void restoreInstanceState(Parcelable state) {
        super.onRestoreInstanceState(state);
    }

    public void setEmptyView(View emptyView) {
        this.mEmptyView = emptyView;
        updateEmptyStatus();
        setupEmptyViewObserver(getAdapter());
    }

    private void setupEmptyViewObserver(Adapter adapter) {
        if (adapter != null) {
            if (this.mObserver != null) {
                adapter.unregisterAdapterDataObserver(this.mObserver);
                this.mObserver = null;
            }
            this.mObserver = new AdapterDataObserver() {
                public void onChanged() {
                    PlayRecyclerView.this.updateEmptyStatus();
                }
            };
            adapter.registerAdapterDataObserver(this.mObserver);
        }
    }

    private void updateEmptyStatus() {
        boolean empty;
        int i = 8;
        Adapter adapter = getAdapter();
        if (adapter == null || adapter.getItemCount() == 0) {
            empty = true;
        } else {
            empty = false;
        }
        if (this.mEmptyView != null) {
            int i2;
            View view = this.mEmptyView;
            if (empty) {
                i2 = 0;
            } else {
                i2 = 8;
            }
            view.setVisibility(i2);
        }
        if (!empty || this.mEmptyView == null) {
            i = 0;
        }
        setVisibility(i);
    }

    public void setAdapter(Adapter adapter) {
        if (!(getAdapter() == null || this.mObserver == null)) {
            getAdapter().unregisterAdapterDataObserver(this.mObserver);
            this.mObserver = null;
        }
        super.setAdapter(adapter);
        setupEmptyViewObserver(adapter);
        updateEmptyStatus();
    }
}
