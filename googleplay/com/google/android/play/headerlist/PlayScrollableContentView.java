package com.google.android.play.headerlist;

import android.widget.Adapter;

public interface PlayScrollableContentView {

    public interface OnScrollListener {
    }

    Adapter getAdapter();

    void scrollBy(int i, int i2);

    void scrollTo(int i, int i2);

    void setOnScrollListener(OnScrollListener onScrollListener);
}
