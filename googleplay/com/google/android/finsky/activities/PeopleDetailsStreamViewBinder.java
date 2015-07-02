package com.google.android.finsky.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.android.vending.R;
import com.google.android.finsky.adapters.CardRecyclerViewAdapter;
import com.google.android.finsky.adapters.EmptyRecyclerViewAdapter;
import com.google.android.finsky.adapters.PeopleDetailsStreamAdapter;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.api.model.DfeList;
import com.google.android.finsky.api.model.DfeToc;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.api.model.OnDataChangedListener;
import com.google.android.finsky.fragments.DetailsViewBinder;
import com.google.android.finsky.layout.LayoutSwitcher;
import com.google.android.finsky.layout.LayoutSwitcher.RetryButtonListener;
import com.google.android.finsky.layout.play.PlayRecyclerView;
import com.google.android.finsky.layout.play.PlayStoreUiElementNode;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.protos.DocDetails.PersonDetails;
import com.google.android.finsky.utils.ClientMutationCache;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.MyPeoplePageHelper;
import com.google.android.play.image.BitmapLoader;

public class PeopleDetailsStreamViewBinder extends DetailsViewBinder implements OnDataChangedListener {
    private PeopleDetailsStreamAdapter mAdapter;
    private BitmapLoader mBitmapLoader;
    private ClientMutationCache mClientMutationCache;
    private DfeList mDfeList;
    private DfeToc mDfeToc;
    private boolean mIsAdapterSet;
    private long mLastRequestTimeMs;
    private PlayStoreUiElementNode mParentNode;
    private PlayRecyclerView mRecyclerView;
    private Bundle mRecyclerViewRestoreBundle;

    public PeopleDetailsStreamViewBinder() {
        this.mRecyclerViewRestoreBundle = new Bundle();
    }

    public void init(Context context, DfeApi api, NavigationManager navManager, BitmapLoader bitmapLoader, DfeToc dfeToc, ClientMutationCache clientMutationCache, PlayStoreUiElementNode parentNode) {
        super.init(context, api, navManager);
        this.mBitmapLoader = bitmapLoader;
        this.mDfeToc = dfeToc;
        this.mClientMutationCache = clientMutationCache;
        this.mParentNode = parentNode;
    }

    public RecyclerView initRecyclerView(View container) {
        this.mRecyclerView = (PlayRecyclerView) container.findViewById(R.id.people_details_stream_list);
        if (this.mRecyclerView.getLayoutManager() == null) {
            this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this.mContext));
        }
        if (this.mRecyclerView.getAdapter() == null) {
            this.mRecyclerView.setAdapter(new EmptyRecyclerViewAdapter());
        }
        return this.mRecyclerView;
    }

    public void bind(View streamContainer, Document doc) {
        super.bind(streamContainer, doc, null);
        if (this.mLayoutSwitcher == null) {
            this.mLayoutSwitcher = new LayoutSwitcher(streamContainer, R.id.people_details_stream_list, new RetryButtonListener() {
                public void onRetry() {
                    PeopleDetailsStreamViewBinder.this.requestData();
                }
            });
        }
        PersonDetails personDetails = this.mDoc.getPersonDetails();
        boolean isOwnProfile = personDetails != null && personDetails.personIsRequester;
        if (isOwnProfile && MyPeoplePageHelper.hasMutationOccurredSince(this.mLastRequestTimeMs)) {
            clearDfeList();
            this.mRecyclerViewRestoreBundle.clear();
        }
        if (this.mDfeList == null || !this.mDfeList.isReady()) {
            this.mLayoutSwitcher.switchToLoadingMode();
            requestData();
            return;
        }
        this.mLayoutSwitcher.switchToDataMode();
        rebindAdapter();
    }

    private void requestData() {
        clearDfeList();
        this.mDfeList = getStreamList();
        this.mDfeList.addDataChangedListener(this);
        this.mDfeList.addErrorListener(this);
        this.mDfeList.startLoadItems();
        this.mLastRequestTimeMs = System.currentTimeMillis();
    }

    private DfeList getStreamList() {
        return new DfeList(this.mDfeApi, this.mDoc.getCoreContentListUrl(), true);
    }

    protected void clearDfeList() {
        if (this.mDfeList != null) {
            this.mDfeList.removeDataChangedListener(this);
            this.mDfeList.removeErrorListener(this);
            this.mDfeList = null;
        }
    }

    public void onDataChanged() {
        if (this.mLayoutSwitcher != null) {
            this.mLayoutSwitcher.switchToDataMode();
        }
        rebindAdapter();
    }

    private void rebindAdapter() {
        if (this.mRecyclerView == null) {
            FinskyLog.w("List view null, ignoring.", new Object[0]);
            return;
        }
        final boolean isRestoring = CardRecyclerViewAdapter.hasRestoreData(this.mRecyclerViewRestoreBundle);
        if (this.mDfeList != null && this.mDfeList.isReady()) {
            if (this.mAdapter == null) {
                this.mAdapter = new PeopleDetailsStreamAdapter(this.mDoc, this.mContext, this.mDfeApi, this.mNavigationManager, this.mBitmapLoader, this.mDfeToc, this.mClientMutationCache, this.mDfeList, isRestoring, this.mParentNode);
            }
            if (this.mIsAdapterSet) {
                this.mAdapter.updateAdapterData(this.mDfeList);
                return;
            }
            this.mIsAdapterSet = true;
            this.mRecyclerView.post(new Runnable() {
                public void run() {
                    if (PeopleDetailsStreamViewBinder.this.mRecyclerView != null && PeopleDetailsStreamViewBinder.this.mAdapter != null) {
                        PeopleDetailsStreamViewBinder.this.mRecyclerView.setAdapter(PeopleDetailsStreamViewBinder.this.mAdapter);
                        if (isRestoring) {
                            PeopleDetailsStreamViewBinder.this.mAdapter.onRestoreInstanceState(PeopleDetailsStreamViewBinder.this.mRecyclerView, PeopleDetailsStreamViewBinder.this.mRecyclerViewRestoreBundle);
                            PeopleDetailsStreamViewBinder.this.mRecyclerViewRestoreBundle.clear();
                        }
                    }
                }
            });
        }
    }

    public void onDestroyView() {
        boolean isOwnProfile = false;
        if (!(this.mRecyclerView == null || this.mRecyclerView.getVisibility() != 0 || this.mAdapter == null)) {
            this.mAdapter.onSaveInstanceState(this.mRecyclerView, this.mRecyclerViewRestoreBundle);
        }
        this.mRecyclerView = null;
        if (this.mAdapter != null) {
            this.mAdapter.onDestroyView();
            this.mAdapter.onDestroy();
            this.mAdapter = null;
            this.mIsAdapterSet = false;
        }
        if (!(this.mDoc == null || this.mDfeList == null)) {
            PersonDetails personDetails = this.mDoc.getPersonDetails();
            if (personDetails != null && personDetails.personIsRequester) {
                isOwnProfile = true;
            }
            if (isOwnProfile) {
                MyPeoplePageHelper.addPeoplePageListUrls(this.mDfeList.getListPageUrls());
            }
        }
        this.mLayoutSwitcher = null;
    }
}
