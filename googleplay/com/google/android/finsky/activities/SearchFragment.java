package com.google.android.finsky.activities;

import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.android.vending.R;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.adapters.CardRecyclerViewAdapter;
import com.google.android.finsky.adapters.SearchAdapter;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.analytics.PlayStore.PlayStoreSearchEvent;
import com.google.android.finsky.analytics.PlayStore.PlayStoreUiElement;
import com.google.android.finsky.api.DfeUtils;
import com.google.android.finsky.api.model.DfeSearch;
import com.google.android.finsky.fragments.PageFragment;
import com.google.android.finsky.layout.ContentFrame;
import com.google.android.finsky.layout.HeaderLayoutSwitcher;
import com.google.android.finsky.layout.LayoutSwitcher;
import com.google.android.finsky.layout.play.FinskyHeaderListLayout;
import com.google.android.finsky.layout.play.FinskyHeaderListLayout.FinskyConfigurator;
import com.google.android.finsky.layout.play.GenericUiElementNode;
import com.google.android.finsky.layout.play.PlayRecyclerView;
import com.google.android.finsky.utils.CorpusResourceUtils;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.UiUtils;
import com.google.android.play.headerlist.PlayHeaderListLayout;

public class SearchFragment extends PageFragment {
    private CardRecyclerViewAdapter mAdapter;
    private int mContextBackendId;
    private boolean mIsAdapterSet;
    private String mQuery;
    private PlayRecyclerView mRecyclerView;
    private Bundle mRecyclerViewRestoreBundle;
    private boolean mRetriedSearch;
    private DfeSearch mSearchData;
    private String mSearchUrl;
    private PlayStoreUiElement mUiElementProto;
    private boolean mhasLoggedSearchEvent;

    public SearchFragment() {
        this.mRetriedSearch = false;
        this.mRecyclerViewRestoreBundle = new Bundle();
        this.mUiElementProto = FinskyEventLog.obtainPlayStoreUiElement(3);
    }

    public static SearchFragment newInstance(String query, String searchUrl, int backendId) {
        SearchFragment fragment = new SearchFragment();
        fragment.setDfeToc(FinskyApp.get().getToc());
        fragment.setArgument("SearchFragment.searchUrl", searchUrl);
        String str = "SearchFragment.query";
        if (query == null) {
            query = "";
        }
        fragment.setArgument(str, query);
        str = "SearchFragment.backendId";
        if (backendId < 0) {
            backendId = 0;
        }
        fragment.setArgument(str, backendId);
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mQuery = getArguments().getString("SearchFragment.query");
        this.mSearchUrl = getArguments().getString("SearchFragment.searchUrl");
        this.mContextBackendId = getArguments().getInt("SearchFragment.backendId");
        setRetainInstance(true);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ContentFrame frame = (ContentFrame) super.onCreateView(inflater, container, savedInstanceState);
        FinskyHeaderListLayout headerListLayout = this.mDataView;
        headerListLayout.configure(new FinskyConfigurator(headerListLayout.getContext()) {
            protected void addContentView(LayoutInflater inflater, ViewGroup root) {
                inflater.inflate(R.layout.search_results, root);
            }

            protected int getListViewId() {
                return R.id.search_results_list;
            }
        });
        headerListLayout.setContentViewId(R.id.search_results);
        return frame;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (this.mSearchData != null && this.mSearchData.hasBackendId()) {
            this.mPageFragmentHost.updateCurrentBackendId(this.mSearchData.getBackendId(), true);
        }
        rebindActionBar();
        this.mRecyclerView = (PlayRecyclerView) this.mDataView.findViewById(R.id.search_results_list);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this.mContext));
        this.mRecyclerView.setVisibility(0);
        ((TextView) this.mDataView.findViewById(R.id.no_results_textview)).setText(getResources().getString(R.string.no_results_for_query, new Object[]{this.mQuery}));
        if (isDataReady()) {
            rebindAdapter();
        } else {
            switchToLoading();
            requestData();
            rebindActionBar();
        }
        this.mActionBarController.enableActionBarOverlay();
    }

    protected LayoutSwitcher createLayoutSwitcher(ContentFrame frame) {
        return new HeaderLayoutSwitcher(frame, R.id.page_content, R.id.page_error_indicator, R.id.loading_indicator, this, 2);
    }

    public void rebindAdapter() {
        if (this.mRecyclerView == null) {
            FinskyLog.w("RecyclerView null, ignoring.", new Object[0]);
        } else if (isDataReady()) {
            final boolean isRestoring = CardRecyclerViewAdapter.hasRestoreData(this.mRecyclerViewRestoreBundle);
            if (this.mAdapter == null) {
                byte[] logsCookieFromRootDoc;
                FinskyEventLog.setServerLogCookie(this.mUiElementProto, this.mSearchData.getServerLogsCookie());
                if (this.mSearchData.getContainerDocument() != null) {
                    logsCookieFromRootDoc = this.mSearchData.getContainerDocument().getServerLogsCookie();
                } else {
                    logsCookieFromRootDoc = null;
                }
                GenericUiElementNode tabElementNode = new GenericUiElementNode(408, logsCookieFromRootDoc, null, this);
                childImpression(tabElementNode);
                this.mNavigationManager.setCanTriggerSearchSurvey(this.mSearchData.containsHighlight());
                this.mAdapter = new SearchAdapter(this.mContext, this.mDfeApi, this.mNavigationManager, this.mBitmapLoader, getToc(), FinskyApp.get().getClientMutationCache(FinskyApp.get().getCurrentAccountName()), this.mSearchData, isRestoring, tabElementNode);
            }
            if (this.mIsAdapterSet) {
                this.mAdapter.updateAdapterData(this.mSearchData);
                return;
            }
            this.mIsAdapterSet = true;
            this.mRecyclerView.post(new Runnable() {
                public void run() {
                    if (SearchFragment.this.mRecyclerView != null && SearchFragment.this.mAdapter != null) {
                        SearchFragment.this.mRecyclerView.setAdapter(SearchFragment.this.mAdapter);
                        if (isRestoring) {
                            SearchFragment.this.mAdapter.onRestoreInstanceState(SearchFragment.this.mRecyclerView, SearchFragment.this.mRecyclerViewRestoreBundle);
                            SearchFragment.this.mRecyclerViewRestoreBundle.clear();
                        }
                        SearchFragment.this.mRecyclerView.setEmptyView(SearchFragment.this.mDataView.findViewById(R.id.no_results_view));
                    }
                }
            });
        }
    }

    protected int getLayoutRes() {
        return R.layout.header_list_container;
    }

    public void refresh() {
        if (!isDataReady()) {
            this.mSearchData.clearTransientState();
            super.refresh();
        }
    }

    public void onDataChanged() {
        if (this.mSearchData.isReady() && this.mSearchData.getContainerDocument() == null) {
            if (!this.mRetriedSearch) {
                String allCorpusSearch = DfeUtils.formSearchUrl(this.mQuery, 0);
                if (!allCorpusSearch.equals(this.mSearchUrl)) {
                    int backendIdForMessage = this.mSearchData.getBackendId();
                    UiUtils.sendAccessibilityEventWithText(this.mContext, getResources().getString(CorpusResourceUtils.getNoSearchResultsMessageId(backendIdForMessage), new Object[]{this.mQuery}), this.mRecyclerView);
                    this.mRetriedSearch = true;
                    this.mSearchUrl = allCorpusSearch;
                    this.mSearchData.removeDataChangedListener(this);
                    this.mSearchData.removeErrorListener(this);
                    this.mSearchData = null;
                    this.mhasLoggedSearchEvent = false;
                    requestData();
                    return;
                }
            }
            UiUtils.sendAccessibilityEventWithText(this.mContext, getResources().getString(R.string.no_results_for_query, new Object[]{this.mQuery}), this.mRecyclerView);
        }
        super.onDataChanged();
    }

    protected void rebindViews() {
        rebindAdapter();
        rebindActionBar();
    }

    public void rebindActionBar() {
        ((MainActivity) this.mPageFragmentHost).getActionBarHelper().setDefaultSearchQuery(this.mQuery);
        int backendId = getBackendId();
        if (backendId == 3 && this.mQuery.startsWith("pub:")) {
            this.mPageFragmentHost.updateBreadcrumb(this.mContext.getResources().getString(R.string.apps_by, new Object[]{this.mQuery.replaceFirst("pub:", "")}));
        } else {
            Resources res = getResources();
            this.mPageFragmentHost.updateBreadcrumb(res.getString(res.getBoolean(R.bool.use_wide_details_layout) ? R.string.search_result_title_long : R.string.search_result_title, new Object[]{this.mQuery}));
        }
        if (this.mDataView != null) {
            this.mDataView.setFloatingControlsBackground(new ColorDrawable(CorpusResourceUtils.getPrimaryColor(this.mContext, backendId)));
        }
        this.mPageFragmentHost.updateCurrentBackendId(backendId, true);
        this.mPageFragmentHost.updateActionBarMode(false);
    }

    private int getBackendId() {
        return (this.mSearchData == null || !this.mSearchData.hasBackendId()) ? this.mContextBackendId : this.mSearchData.getBackendId();
    }

    protected void requestData() {
        if (this.mSearchData == null) {
            this.mSearchData = new DfeSearch(this.mDfeApi, this.mQuery, this.mSearchUrl);
            this.mSearchData.addDataChangedListener(this);
            this.mSearchData.addErrorListener(this);
            if (!this.mhasLoggedSearchEvent) {
                PlayStoreSearchEvent event = FinskyEventLog.obtainPlayStoreSearchEvent();
                if (this.mQuery != null) {
                    event.query = this.mQuery;
                    event.hasQuery = true;
                }
                if (this.mSearchUrl != null) {
                    event.queryUrl = this.mSearchUrl;
                    event.hasQueryUrl = true;
                }
                FinskyApp.get().getEventLogger().logSearchEvent(event);
                this.mhasLoggedSearchEvent = true;
            }
        }
        this.mSearchData.startLoadItems();
    }

    public void onDestroyView() {
        if (!(this.mAdapter == null || this.mRecyclerView == null || this.mRecyclerView.getVisibility() != 0)) {
            this.mAdapter.onSaveInstanceState(this.mRecyclerView, this.mRecyclerViewRestoreBundle);
        }
        if (this.mRecyclerView != null) {
            this.mRecyclerView.setRecyclerListener(null);
        }
        this.mRecyclerView = null;
        if (this.mAdapter != null) {
            this.mAdapter.onDestroyView();
            this.mAdapter.onDestroy();
            this.mAdapter = null;
        }
        this.mIsAdapterSet = false;
        ((MainActivity) this.mPageFragmentHost).getActionBarHelper().setDefaultSearchQuery("");
        if (this.mDataView instanceof PlayHeaderListLayout) {
            ((PlayHeaderListLayout) this.mDataView).detach();
        }
        super.onDestroyView();
    }

    public boolean isDataReady() {
        return this.mSearchData != null && this.mSearchData.isReady();
    }

    protected void onInitViewBinders() {
    }

    protected int getActionBarColor() {
        return CorpusResourceUtils.getPrimaryColor(this.mContext, getBackendId());
    }

    public PlayStoreUiElement getPlayStoreUiElement() {
        return this.mUiElementProto;
    }
}
