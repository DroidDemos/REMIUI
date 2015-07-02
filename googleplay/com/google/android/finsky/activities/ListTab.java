package com.google.android.finsky.activities;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.vending.R;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.activities.TabbedAdapter.TabDataListener;
import com.google.android.finsky.adapters.EmptyRecyclerViewAdapter;
import com.google.android.finsky.adapters.QuickLinkHelper.QuickLinkInfo;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.analytics.PlayStore.PlayStoreUiElement;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.api.model.DfeList;
import com.google.android.finsky.api.model.DfeToc;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.api.model.OnDataChangedListener;
import com.google.android.finsky.fragments.DfeRecyclerBinder;
import com.google.android.finsky.layout.LayoutSwitcher;
import com.google.android.finsky.layout.LayoutSwitcher.RetryButtonListener;
import com.google.android.finsky.layout.actionbar.ActionBarController;
import com.google.android.finsky.layout.play.PlayRecyclerView;
import com.google.android.finsky.layout.play.PlayStoreUiElementNode;
import com.google.android.finsky.layout.play.SelectableUiElementNode;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.utils.ClientMutationCache;
import com.google.android.finsky.utils.ErrorStrings;
import com.google.android.finsky.utils.ObjectMap;
import com.google.android.finsky.utils.UiUtils;
import com.google.android.play.image.BitmapLoader;

public final class ListTab implements ErrorListener, ViewPagerTab, OnDataChangedListener, RetryButtonListener, PlayStoreUiElementNode {
    protected final ActionBarController mActionBarController;
    private DfeRecyclerBinder mBinder;
    private final ClientMutationCache mClientMutationCache;
    private Document mContainerDocument;
    private final DfeApi mDfeApi;
    private ObjectMap mInstanceState;
    private boolean mIsCurrentlySelected;
    private final LayoutInflater mLayoutInflater;
    private LayoutSwitcher mLayoutSwitcher;
    private final DfeList mList;
    protected final NavigationManager mNavigationManager;
    private SelectableUiElementNode mParentNode;
    private final QuickLinkInfo[] mQuickLinks;
    private boolean mRecyclerViewBoundAlready;
    private ViewGroup mRecyclerViewTabWrapper;
    private boolean mShouldDeferDataDisplay;
    private final TabDataListener mTabDataListener;
    private final int mTabMode;
    private final String mTitle;
    protected final DfeToc mToc;
    private PlayStoreUiElement mUiElementProto;

    public ListTab(Context context, NavigationManager navigationManager, BitmapLoader bitmapLoader, DfeApi dfeApi, LayoutInflater inflater, TabData tabData, DfeToc toc, ClientMutationCache clientMutationCache, boolean shouldDeferDataDisplay, ActionBarController actionBarController, int tabMode, TabDataListener tabDataListener) {
        this.mInstanceState = new ObjectMap();
        this.mRecyclerViewBoundAlready = false;
        this.mUiElementProto = FinskyEventLog.obtainPlayStoreUiElement(408);
        this.mLayoutInflater = inflater;
        this.mList = tabData.dfeList;
        this.mQuickLinks = tabData.quickLinks;
        this.mTitle = tabMode == 2 ? tabData.browseTab.title : null;
        this.mParentNode = tabData.elementNode;
        this.mList.setWindowDistance(context.getResources().getInteger(R.integer.num_items_per_list_column) * UiUtils.getFeaturedGridColumnCount(context.getResources(), 1.0d));
        this.mList.addDataChangedListener(this);
        this.mList.addErrorListener(this);
        this.mShouldDeferDataDisplay = shouldDeferDataDisplay;
        this.mList.startLoadItems();
        this.mToc = toc;
        this.mNavigationManager = navigationManager;
        this.mDfeApi = dfeApi;
        this.mClientMutationCache = clientMutationCache;
        this.mActionBarController = actionBarController;
        this.mTabMode = tabMode;
        this.mTabDataListener = tabDataListener;
        createBinder(context, bitmapLoader);
    }

    public void exitDeferredDataDisplayMode() {
        this.mShouldDeferDataDisplay = false;
        syncViewToState();
    }

    private void createBinder(Context context, BitmapLoader bitmapLoader) {
        this.mBinder = new DfeRecyclerBinder(this.mToc, this.mDfeApi, this.mClientMutationCache);
        this.mBinder.init(context, this.mNavigationManager, bitmapLoader);
    }

    public View getView(int backendId) {
        if (this.mRecyclerViewTabWrapper == null) {
            this.mRecyclerViewTabWrapper = (ViewGroup) this.mLayoutInflater.inflate(R.layout.recycler_tab_wrapper, null);
            this.mLayoutSwitcher = new LayoutSwitcher(this.mRecyclerViewTabWrapper, -1, R.id.page_error_indicator, R.id.lists_loading_indicator, this, 0);
            PlayRecyclerView recyclerView = (PlayRecyclerView) this.mRecyclerViewTabWrapper.findViewById(R.id.tab_recycler_view);
            recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
            recyclerView.setAdapter(new EmptyRecyclerViewAdapter());
            syncViewToState();
        }
        return this.mRecyclerViewTabWrapper;
    }

    public void onDestroy() {
        this.mBinder.onDestroyView();
        this.mList.removeDataChangedListener(this);
        this.mList.removeErrorListener(this);
        this.mList.flushUnusedPages();
        this.mRecyclerViewTabWrapper = null;
        this.mRecyclerViewBoundAlready = false;
    }

    public void onDataChanged() {
        syncViewToState();
    }

    public ObjectMap onSaveInstanceState() {
        if (this.mRecyclerViewTabWrapper != null) {
            PlayRecyclerView recyclerView = (PlayRecyclerView) this.mRecyclerViewTabWrapper.findViewById(R.id.tab_recycler_view);
            if (recyclerView.getVisibility() == 0) {
                ObjectMap output = new ObjectMap();
                this.mBinder.onSaveInstanceState(recyclerView, output.getBundle());
                return output;
            }
        }
        return null;
    }

    public void onRestoreInstanceState(ObjectMap instanceState) {
        this.mInstanceState = instanceState;
    }

    private void syncViewToState() {
        if (this.mRecyclerViewTabWrapper != null) {
            if (this.mList.inErrorState()) {
                if (!this.mRecyclerViewBoundAlready) {
                    this.mLayoutSwitcher.switchToErrorMode(ErrorStrings.get(FinskyApp.get(), this.mList.getVolleyError()));
                }
            } else if (this.mShouldDeferDataDisplay || !this.mList.isReady()) {
                this.mLayoutSwitcher.switchToLoadingMode();
            } else {
                if (this.mContainerDocument == null) {
                    this.mContainerDocument = this.mList.getContainerDocument();
                }
                if (this.mContainerDocument != null) {
                    FinskyEventLog.setServerLogCookie(this.mUiElementProto, this.mContainerDocument.getServerLogsCookie());
                }
                this.mLayoutSwitcher.switchToDataMode(R.id.tab_recycler_view);
                bindRecyclerView();
            }
        }
    }

    public void onRetry() {
        this.mList.resetItems();
        this.mList.clearTransientState();
        this.mList.startLoadItems();
        syncViewToState();
    }

    private void bindRecyclerView() {
        if (!this.mRecyclerViewBoundAlready) {
            this.mBinder.setData(this.mList);
            this.mBinder.bind(this.mRecyclerViewTabWrapper, this.mContainerDocument, this.mQuickLinks, this.mTitle, this.mTabMode, this.mInstanceState == null ? null : this.mInstanceState.getBundle(), this.mTabDataListener, this);
            this.mInstanceState = null;
            this.mRecyclerViewBoundAlready = true;
        }
    }

    public void onErrorResponse(VolleyError error) {
        syncViewToState();
    }

    public void setTabSelected(boolean isSelected) {
        if (isSelected != this.mIsCurrentlySelected) {
            if (isSelected) {
                FinskyEventLog.startNewImpression(this.mParentNode);
                this.mParentNode.setNodeSelected(true);
                if (this.mParentNode.getPlayStoreUiElement().child.length == 0) {
                    FinskyEventLog.requestImpressions(this.mRecyclerViewTabWrapper);
                }
            } else {
                this.mParentNode.setNodeSelected(false);
            }
            this.mIsCurrentlySelected = isSelected;
        }
    }

    public PlayStoreUiElement getPlayStoreUiElement() {
        return this.mUiElementProto;
    }

    public PlayStoreUiElementNode getParentNode() {
        return this.mParentNode;
    }

    public void childImpression(PlayStoreUiElementNode childNode) {
        FinskyEventLog.childImpression(this, childNode);
    }
}
