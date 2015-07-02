package com.google.android.finsky.fragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.android.vending.R;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.layout.LayoutSwitcher;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.utils.ErrorStrings;

public abstract class DetailsViewBinder implements ErrorListener {
    protected Context mContext;
    protected DfeApi mDfeApi;
    protected Document mDoc;
    protected int mHeaderLayoutId;
    protected LayoutInflater mInflater;
    protected View mLayout;
    protected LayoutSwitcher mLayoutSwitcher;
    protected NavigationManager mNavigationManager;

    public void init(Context context, DfeApi api, NavigationManager navManager) {
        this.mContext = context;
        this.mDfeApi = api;
        this.mNavigationManager = navManager;
        this.mInflater = LayoutInflater.from(this.mContext);
        this.mHeaderLayoutId = R.id.header;
    }

    public void bind(View view, Document doc, String headerString) {
        this.mLayout = view;
        this.mDoc = doc;
        bind(view, headerString, doc.getBackend());
    }

    public void bind(View view, String headerString, int headerBackendId) {
        this.mLayout = view;
        setupHeader(headerString, headerBackendId);
    }

    protected void setupHeader(String headerString, int headerBackendId) {
        TextView headerView = (TextView) this.mLayout.findViewById(this.mHeaderLayoutId);
        if (headerView != null) {
            headerView.setText(headerString);
        }
    }

    public void onErrorResponse(VolleyError error) {
        if (this.mLayoutSwitcher != null) {
            this.mLayoutSwitcher.switchToErrorMode(ErrorStrings.get(this.mContext, error));
        }
    }
}
