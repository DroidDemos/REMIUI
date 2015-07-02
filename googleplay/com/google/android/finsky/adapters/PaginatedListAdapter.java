package com.google.android.finsky.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.android.vending.R;
import com.google.android.finsky.api.model.OnDataChangedListener;
import com.google.android.finsky.layout.ErrorFooter;
import com.google.android.finsky.layout.IdentifiableLinearLayout;
import com.google.android.finsky.navigationmanager.NavigationManager;

public abstract class PaginatedListAdapter extends BaseAdapter implements OnDataChangedListener {
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

    public PaginatedListAdapter(Context context, NavigationManager navManager, boolean isRequestInErrorState, boolean isRequestLoading) {
        this.mRetryClickListener = new OnClickListener() {
            public void onClick(View v) {
                if (PaginatedListAdapter.this.mFooterMode == FooterMode.ERROR) {
                    PaginatedListAdapter.this.retryLoadingItems();
                }
                PaginatedListAdapter.this.setFooterMode(FooterMode.LOADING);
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

    protected View getLoadingFooterView(View convertView, ViewGroup parent) {
        IdentifiableLinearLayout view = (IdentifiableLinearLayout) (convertView != null ? convertView : inflate(R.layout.loading_footer, parent, false));
        view.setIdentifier("loading_footer");
        return view;
    }

    protected View getErrorFooterView(View convertView, ViewGroup parent) {
        ErrorFooter errorFooter = (ErrorFooter) (convertView != null ? convertView : inflate(R.layout.error_footer, parent, false));
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
