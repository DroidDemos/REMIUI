package com.google.android.finsky.fragments;

import android.content.Context;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.play.image.BitmapLoader;

public abstract class ViewBinder<T> {
    protected BitmapLoader mBitmapLoader;
    protected Context mContext;
    protected T mData;
    protected NavigationManager mNavManager;

    public void init(Context context, NavigationManager nm, BitmapLoader bitmapLoader) {
        if (this.mContext != context) {
            this.mContext = context;
            this.mNavManager = nm;
            this.mBitmapLoader = bitmapLoader;
            this.mData = null;
        }
    }

    public void setData(T data) {
        this.mData = data;
    }
}
