package com.google.android.finsky.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.android.vending.R;
import com.google.android.finsky.api.model.OnDataChangedListener;
import com.google.android.finsky.layout.ErrorFooter;
import com.google.android.finsky.layout.IdentifiableLinearLayout;
import com.google.android.finsky.navigationmanager.NavigationManager;

public abstract class PaginatedRecyclerViewAdapter extends Adapter implements OnDataChangedListener {
    protected final Context mContext;
    private FooterMode mFooterMode;
    protected final LayoutInflater mLayoutInflater;
    protected final NavigationManager mNavigationManager;
    protected OnClickListener mRetryClickListener;

    protected enum FooterMode {
        NONE,
        LOADING,
        ERROR
    }

    protected abstract String getLastRequestError();

    protected abstract boolean isMoreDataAvailable();

    protected abstract void retryLoadingItems();

    public PaginatedRecyclerViewAdapter(Context context, NavigationManager navManager, boolean isRequestInErrorState, boolean isRequestLoading) {
        this.mRetryClickListener = new OnClickListener() {
            public void onClick(View v) {
                if (PaginatedRecyclerViewAdapter.this.mFooterMode == FooterMode.ERROR) {
                    PaginatedRecyclerViewAdapter.this.retryLoadingItems();
                }
                PaginatedRecyclerViewAdapter.this.setFooterMode(FooterMode.LOADING);
            }
        };
        this.mContext = context;
        this.mNavigationManager = navManager;
        this.mLayoutInflater = LayoutInflater.from(context);
        if (isRequestInErrorState) {
            this.mFooterMode = FooterMode.ERROR;
        } else if (isRequestLoading) {
            this.mFooterMode = FooterMode.LOADING;
        } else {
            this.mFooterMode = FooterMode.NONE;
        }
    }

    protected View createLoadingFooterView(ViewGroup parent) {
        return inflate(R.layout.loading_footer, parent, false);
    }

    protected void bindLoadingFooterView(View view) {
        ((IdentifiableLinearLayout) view).setIdentifier("loading_footer");
    }

    protected View createErrorFooterView(ViewGroup parent) {
        return inflate(R.layout.error_footer, parent, false);
    }

    protected View bindErrorFooterView(View view) {
        ErrorFooter errorFooter = (ErrorFooter) view;
        errorFooter.configure(getLastRequestError(), this.mRetryClickListener);
        errorFooter.setIdentifier("error_footer");
        return errorFooter;
    }

    public long getItemId(int position) {
        return (long) position;
    }

    protected View inflate(int resource, ViewGroup root, boolean attachToRoot) {
        return this.mLayoutInflater.inflate(resource, root, attachToRoot);
    }

    private void setFooterMode(FooterMode mode) {
        this.mFooterMode = mode;
        notifyDataSetChanged();
    }

    public void triggerFooterErrorMode() {
        setFooterMode(FooterMode.ERROR);
    }

    protected FooterMode getFooterMode() {
        return this.mFooterMode;
    }

    public void onDataChanged() {
        if (isMoreDataAvailable()) {
            setFooterMode(FooterMode.LOADING);
        } else {
            setFooterMode(FooterMode.NONE);
        }
    }
}
