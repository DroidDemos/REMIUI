package com.google.android.libraries.bind.widget;

import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import java.util.HashSet;
import java.util.Set;

public class MulticastOnScrollListener implements OnScrollListener {
    private final Set<OnScrollListener> listeners;

    public MulticastOnScrollListener() {
        this.listeners = new HashSet();
    }

    public MulticastOnScrollListener add(OnScrollListener listener) {
        this.listeners.add(listener);
        return this;
    }

    public MulticastOnScrollListener remove(OnScrollListener listener) {
        this.listeners.remove(listener);
        return this;
    }

    public MulticastOnScrollListener clear() {
        this.listeners.clear();
        return this;
    }

    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        for (OnScrollListener listener : this.listeners) {
            listener.onScroll(view, firstVisibleItem, visibleItemCount, totalItemCount);
        }
    }

    public void onScrollStateChanged(AbsListView view, int scrollState) {
        for (OnScrollListener listener : this.listeners) {
            listener.onScrollStateChanged(view, scrollState);
        }
    }
}
