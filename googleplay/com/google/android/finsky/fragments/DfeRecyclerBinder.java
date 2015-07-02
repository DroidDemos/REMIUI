package com.google.android.finsky.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.android.vending.R;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.activities.TabbedAdapter.TabDataListener;
import com.google.android.finsky.adapters.CardRecyclerViewAdapter;
import com.google.android.finsky.adapters.CardStreamMarginItemDecoration;
import com.google.android.finsky.adapters.EditorialRecyclerViewAdapter;
import com.google.android.finsky.adapters.QuickLinkHelper.QuickLinkInfo;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.api.model.ContainerList;
import com.google.android.finsky.api.model.DfeList;
import com.google.android.finsky.api.model.DfeToc;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.api.model.OnDataChangedListener;
import com.google.android.finsky.layout.play.PlayRecyclerView;
import com.google.android.finsky.layout.play.PlayStoreUiElementNode;
import com.google.android.finsky.protos.Common.Image;
import com.google.android.finsky.utils.ClientMutationCache;
import com.google.android.play.image.FifeImageView;

public class DfeRecyclerBinder extends ViewBinder<DfeList> implements ErrorListener, OnDataChangedListener {
    private CardRecyclerViewAdapter mAdapter;
    private final ClientMutationCache mClientMutationCache;
    private ViewGroup mContentLayout;
    private final DfeApi mDfeApi;
    private boolean mHasLoadedAtLeastOnce;
    private PlayRecyclerView mRecyclerView;
    private TabDataListener mTabDataListener;
    protected DfeToc mToc;

    public DfeRecyclerBinder(DfeToc toc, DfeApi dfeApi, ClientMutationCache clientMutationCache) {
        this.mToc = toc;
        this.mDfeApi = dfeApi;
        this.mClientMutationCache = clientMutationCache;
    }

    public void setData(DfeList data) {
        detachFromData();
        super.setData(data);
        this.mHasLoadedAtLeastOnce = false;
        if (this.mData != null) {
            ((DfeList) this.mData).addDataChangedListener(this);
            ((DfeList) this.mData).addErrorListener(this);
        }
    }

    public void bind(ViewGroup root, Document containerDocument, QuickLinkInfo[] quickLinks, String title, int tabMode, Bundle savedInstanceState, TabDataListener tabDataListener, PlayStoreUiElementNode parentNode) {
        this.mContentLayout = root;
        this.mTabDataListener = tabDataListener;
        this.mRecyclerView = (PlayRecyclerView) root.findViewById(R.id.tab_recycler_view);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this.mContext));
        if (containerDocument == null || (containerDocument.getChildCount() == 0 && (this.mData == null || ((DfeList) this.mData).getCount() == 0))) {
            View noResultsView = this.mContentLayout.findViewById(R.id.no_results_view);
            if (noResultsView != null) {
                FifeImageView emptyImageContainer = (FifeImageView) noResultsView.findViewById(R.id.empty_list_image);
                if (containerDocument == null || !containerDocument.hasImages(4)) {
                    emptyImageContainer.setVisibility(8);
                } else {
                    Image emptyImage = (Image) containerDocument.getImages(4).get(0);
                    emptyImageContainer.setImage(emptyImage.imageUrl, emptyImage.supportsFifeUrlOptions, FinskyApp.get().getBitmapLoader());
                    emptyImageContainer.setVisibility(0);
                }
                if (containerDocument != null && containerDocument.hasEmptyContainer()) {
                    String emptyMessage = containerDocument.getEmptyContainer().emptyMessage;
                    if (!TextUtils.isEmpty(emptyMessage)) {
                        ((TextView) noResultsView.findViewById(R.id.no_results_textview)).setText(emptyMessage);
                    }
                }
                this.mRecyclerView.setEmptyView(noResultsView);
                return;
            }
            return;
        }
        if (this.mAdapter != null) {
            this.mAdapter.onDestroyView();
            this.mAdapter.onDestroy();
        }
        this.mAdapter = getAdapter(containerDocument, quickLinks, title, tabMode, savedInstanceState, parentNode);
        if (this.mHasLoadedAtLeastOnce) {
            this.mRecyclerView.setEmptyView(null);
        } else {
            this.mRecyclerView.setEmptyView(this.mContentLayout.findViewById(R.id.no_results_view));
        }
        this.mRecyclerView.setAdapter(this.mAdapter);
        this.mRecyclerView.addItemDecoration(new CardStreamMarginItemDecoration(root.getContext()));
        if (this.mTabDataListener != null) {
            this.mTabDataListener.onTabDataReady(this.mAdapter);
        }
        if (savedInstanceState != null) {
            this.mAdapter.onRestoreInstanceState(this.mRecyclerView, savedInstanceState);
        }
    }

    public void onErrorResponse(VolleyError error) {
        if (this.mRecyclerView != null) {
            this.mAdapter.triggerFooterErrorMode();
        }
    }

    public void onDataChanged() {
        if (!(this.mHasLoadedAtLeastOnce || this.mRecyclerView == null)) {
            this.mRecyclerView.setEmptyView(this.mContentLayout.findViewById(R.id.no_results_view));
            this.mHasLoadedAtLeastOnce = true;
        }
        if (this.mAdapter != null) {
            this.mAdapter.notifyDataSetChanged();
        }
    }

    private void detachFromData() {
        if (this.mData != null) {
            ((DfeList) this.mData).removeDataChangedListener(this);
            ((DfeList) this.mData).removeErrorListener(this);
            this.mData = null;
        }
    }

    public void onDestroyView() {
        detachFromData();
        if (this.mAdapter != null) {
            this.mAdapter.onDestroyView();
            this.mAdapter.onDestroy();
            this.mAdapter = null;
        }
        this.mRecyclerView = null;
    }

    public void onSaveInstanceState(PlayRecyclerView recyclerView, Bundle savedInstanceState) {
        if (this.mAdapter != null) {
            this.mAdapter.onSaveInstanceState(recyclerView, savedInstanceState);
        }
    }

    private CardRecyclerViewAdapter getAdapter(Document containerDocument, QuickLinkInfo[] quickLinks, String title, int tabMode, Bundle savedInstanceState, PlayStoreUiElementNode parentNode) {
        boolean isRestoring = savedInstanceState != null;
        if (containerDocument.hasEditorialSeriesContainer()) {
            return new EditorialRecyclerViewAdapter(this.mContext, this.mDfeApi, this.mNavManager, this.mBitmapLoader, this.mToc, this.mClientMutationCache, containerDocument, (DfeList) this.mData, isRestoring, parentNode);
        }
        boolean showReasons = containerDocument.hasRecommendationsContainerTemplate() || containerDocument.hasRecommendationsContainerWithHeaderTemplate();
        return new CardRecyclerViewAdapter(this.mContext, this.mDfeApi, this.mNavManager, this.mBitmapLoader, this.mToc, this.mClientMutationCache, (ContainerList) this.mData, quickLinks, title, isRestoring, showReasons, tabMode, parentNode);
    }
}
