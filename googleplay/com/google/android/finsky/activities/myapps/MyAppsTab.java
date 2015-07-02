package com.google.android.finsky.activities.myapps;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewParent;
import android.widget.ListView;
import android.widget.TextView;
import com.android.vending.R;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.activities.AuthenticatedActivity;
import com.google.android.finsky.activities.ViewPagerTab;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.api.model.DfeModel;
import com.google.android.finsky.api.model.DfeToc;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.api.model.OnDataChangedListener;
import com.google.android.finsky.installer.InstallerListener;
import com.google.android.finsky.library.Libraries;
import com.google.android.finsky.library.Libraries.Listener;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.receivers.Installer;
import com.google.android.finsky.utils.ErrorStrings;

public abstract class MyAppsTab<T extends DfeModel> implements OnClickListener, ErrorListener, ViewPagerTab, OnDataChangedListener, InstallerListener, Listener {
    protected final AuthenticatedActivity mAuthenticatedActivity;
    protected final DfeApi mDfeApi;
    protected T mDfeModel;
    protected final DfeToc mDfeToc;
    protected final Installer mInstaller;
    private boolean mIsSelectedTab;
    private VolleyError mLastVolleyError;
    protected final LayoutInflater mLayoutInflater;
    protected final Libraries mLibraries;
    protected final NavigationManager mNavigationManager;

    protected abstract MyAppsListAdapter getAdapter();

    protected abstract Document getDocumentForView(View view);

    protected abstract View getFullView();

    protected abstract ListView getListView();

    protected abstract void requestData();

    protected abstract void retryFromError();

    protected MyAppsTab(AuthenticatedActivity authenticatedActivity, DfeApi dfeApi, DfeToc dfeToc, NavigationManager navigationManager) {
        this.mIsSelectedTab = false;
        this.mAuthenticatedActivity = authenticatedActivity;
        this.mLayoutInflater = LayoutInflater.from(this.mAuthenticatedActivity);
        this.mDfeApi = dfeApi;
        this.mDfeToc = dfeToc;
        this.mNavigationManager = navigationManager;
        this.mInstaller = FinskyApp.get().getInstaller();
        this.mInstaller.addListener(this);
        this.mLibraries = FinskyApp.get().getLibraries();
        this.mLibraries.addListener(this);
    }

    public void onDestroy() {
        clearState();
        this.mInstaller.removeListener(this);
        this.mLibraries.removeListener(this);
    }

    protected final boolean isDataReady() {
        return this.mDfeModel != null && this.mDfeModel.isReady();
    }

    final void loadData() {
        requestData();
        if (isDataReady()) {
            onDataChanged();
        }
    }

    protected void clearState() {
        if (this.mDfeModel != null) {
            this.mDfeModel.removeDataChangedListener(this);
            this.mDfeModel.removeErrorListener(this);
            this.mDfeModel = null;
        }
    }

    public void onClick(View view) {
        this.mNavigationManager.getClickListener(getAdapter().getDocument(getPositionForView(view)), null).onClick(view);
    }

    protected int getPositionForView(View view) {
        if (view.getId() == R.id.accessibility_overlay) {
            view = (View) view.getParent();
        }
        if (getDocumentForView(view) == null) {
            return -1;
        }
        View v = view;
        ViewParent listView = getListView();
        while (v != null) {
            ViewParent parent = v.getParent();
            if (parent == listView) {
                return listView.getPositionForView(view);
            }
            if (!(parent instanceof View)) {
                return -1;
            }
            v = (View) parent;
        }
        return -1;
    }

    protected void syncViewToState() {
        View fullView = getFullView();
        View loadingIndicator = fullView.findViewById(R.id.lists_loading_indicator);
        View errorIndicator = fullView.findViewById(R.id.page_error_indicator);
        ListView contentListView = (ListView) fullView.findViewById(R.id.my_apps_content_list);
        if (this.mLastVolleyError != null) {
            errorIndicator.setVisibility(0);
            ((TextView) errorIndicator.findViewById(R.id.error_msg)).setText(ErrorStrings.get(FinskyApp.get(), this.mLastVolleyError));
            errorIndicator.findViewById(R.id.retry_button).setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    MyAppsTab.this.retryFromError();
                    MyAppsTab.this.syncViewToState();
                }
            });
            loadingIndicator.setVisibility(8);
            contentListView.setVisibility(8);
        } else if (isDataReady()) {
            contentListView.setVisibility(0);
            errorIndicator.setVisibility(8);
            loadingIndicator.setVisibility(8);
        } else {
            loadingIndicator.setVisibility(0);
            errorIndicator.setVisibility(8);
            contentListView.setVisibility(8);
        }
    }

    public void onErrorResponse(VolleyError error) {
        this.mLastVolleyError = error;
        syncViewToState();
    }

    public void onDataChanged() {
        this.mLastVolleyError = null;
    }

    public void setTabSelected(boolean isSelected) {
        this.mIsSelectedTab = isSelected;
    }

    protected boolean finishActiveMode() {
        return false;
    }

    public void onAllLibrariesLoaded() {
    }

    protected void configureEmptyUI(boolean showAccountSelector, int descriptionTextId) {
        MyAppsEmptyView emptyView = (MyAppsEmptyView) getFullView().findViewById(R.id.no_results_view);
        if (emptyView != null) {
            emptyView.configure(this.mDfeToc, this.mNavigationManager, this.mAuthenticatedActivity, showAccountSelector, descriptionTextId);
            getListView().setEmptyView(emptyView);
        }
    }
}
