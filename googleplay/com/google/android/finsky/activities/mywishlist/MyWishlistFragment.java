package com.google.android.finsky.activities.mywishlist;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.vending.R;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.adapters.CardRecyclerViewAdapter;
import com.google.android.finsky.adapters.EmptyRecyclerViewAdapter;
import com.google.android.finsky.adapters.MyWishlistRecyclerViewAdapter;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.analytics.PlayStore.PlayStoreUiElement;
import com.google.android.finsky.api.model.DfeList;
import com.google.android.finsky.fragments.PageFragment;
import com.google.android.finsky.layout.ContentFrame;
import com.google.android.finsky.layout.HeaderLayoutSwitcher;
import com.google.android.finsky.layout.LayoutSwitcher;
import com.google.android.finsky.layout.play.FinskyHeaderListLayout;
import com.google.android.finsky.layout.play.FinskyHeaderListLayout.FinskyConfigurator;
import com.google.android.finsky.layout.play.PlayRecyclerView;
import com.google.android.finsky.library.Libraries;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.WishlistHelper;
import com.google.android.play.headerlist.PlayHeaderListLayout;

public class MyWishlistFragment extends PageFragment {
    private MyWishlistRecyclerViewAdapter mAdapter;
    private String mBreadcrumb;
    private DfeList mDfeList;
    private boolean mIsAdapterSet;
    private long mLastLoadedTimeMs;
    private Libraries mLibraries;
    private PlayRecyclerView mMyWishlistView;
    private Bundle mRecyclerViewRestoreBundle;
    private PlayStoreUiElement mUiElementProto;

    public MyWishlistFragment() {
        this.mRecyclerViewRestoreBundle = new Bundle();
        this.mUiElementProto = FinskyEventLog.obtainPlayStoreUiElement(5);
    }

    public static MyWishlistFragment newInstance() {
        MyWishlistFragment fragment = new MyWishlistFragment();
        fragment.setDfeToc(FinskyApp.get().getToc());
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        this.mLibraries = FinskyApp.get().getLibraries();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ContentFrame frame = (ContentFrame) super.onCreateView(inflater, container, savedInstanceState);
        FinskyHeaderListLayout headerListLayout = this.mDataView;
        headerListLayout.configure(new FinskyConfigurator(headerListLayout.getContext()) {
            protected void addContentView(LayoutInflater inflater, ViewGroup root) {
                inflater.inflate(R.layout.wishlist_panel, root);
            }

            protected int getListViewId() {
                return R.id.tab_recycler_view;
            }
        });
        headerListLayout.setContentViewId(R.id.wishlist_panel);
        return frame;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.mBreadcrumb = this.mContext.getString(R.string.menu_my_wishlist);
        this.mMyWishlistView = (PlayRecyclerView) this.mDataView.findViewById(R.id.tab_recycler_view);
        this.mMyWishlistView.setVisibility(0);
        this.mMyWishlistView.setLayoutManager(new LinearLayoutManager(this.mContext));
        this.mMyWishlistView.setAdapter(new EmptyRecyclerViewAdapter());
        rebindActionBar();
        if (WishlistHelper.hasMutationOccurredSince(this.mLastLoadedTimeMs)) {
            clearDfeList();
            this.mRecyclerViewRestoreBundle.clear();
        }
        if (isDataReady()) {
            rebindAdapter();
        } else {
            requestData();
            switchToLoading();
        }
        this.mActionBarController.enableActionBarOverlay();
    }

    protected LayoutSwitcher createLayoutSwitcher(ContentFrame frame) {
        return new HeaderLayoutSwitcher(frame, R.id.page_content, R.id.page_error_indicator, R.id.loading_indicator, this, 2);
    }

    protected int getLayoutRes() {
        return R.layout.header_list_container;
    }

    public boolean isDataReady() {
        if (this.mDfeList == null) {
            return false;
        }
        return this.mDfeList.isReady();
    }

    protected void requestData() {
        clearDfeList();
        this.mDfeList = getLibraryList();
        this.mDfeList.addDataChangedListener(this);
        this.mDfeList.addErrorListener(this);
        this.mDfeList.startLoadItems();
        this.mLastLoadedTimeMs = System.currentTimeMillis();
    }

    protected void clearDfeList() {
        if (this.mDfeList != null) {
            this.mDfeList.removeDataChangedListener(this);
            this.mDfeList.removeErrorListener(this);
            this.mDfeList = null;
        }
    }

    private DfeList getLibraryList() {
        return new DfeList(this.mDfeApi, this.mDfeApi.getLibraryUrl(0, "u-wl", 7, this.mLibraries.getAccountLibrary(FinskyApp.get().getCurrentAccount()).getServerToken("u-wl")), true);
    }

    public void onDataChanged() {
        super.onDataChanged();
        rebindAdapter();
    }

    public void rebindActionBar() {
        if (this.mDataView != null) {
            this.mDataView.setFloatingControlsBackground(new ColorDrawable(getActionBarColor()));
        }
        this.mPageFragmentHost.updateBreadcrumb(this.mBreadcrumb);
        this.mPageFragmentHost.updateCurrentBackendId(0, true);
        this.mPageFragmentHost.updateActionBarMode(false);
    }

    private void rebindAdapter() {
        if (this.mMyWishlistView == null) {
            FinskyLog.w("Recycler view null, ignoring.", new Object[0]);
            return;
        }
        final boolean isRestoring = CardRecyclerViewAdapter.hasRestoreData(this.mRecyclerViewRestoreBundle);
        if (this.mAdapter == null) {
            FinskyEventLog.setServerLogCookie(this.mUiElementProto, this.mDfeList.getContainerDocument().getServerLogsCookie());
            this.mAdapter = new MyWishlistRecyclerViewAdapter(this.mContext, this.mDfeApi, this.mNavigationManager, this.mBitmapLoader, getToc(), FinskyApp.get().getClientMutationCache(FinskyApp.get().getCurrentAccountName()), this.mDfeList, this, isRestoring);
            WishlistHelper.addWishlistStatusListener(this.mAdapter);
        }
        if (this.mIsAdapterSet) {
            this.mAdapter.updateAdapterData(this.mDfeList);
            return;
        }
        this.mIsAdapterSet = true;
        this.mMyWishlistView.post(new Runnable() {
            public void run() {
                if (MyWishlistFragment.this.mMyWishlistView != null && MyWishlistFragment.this.mAdapter != null) {
                    MyWishlistFragment.this.mMyWishlistView.setAdapter(MyWishlistFragment.this.mAdapter);
                    if (isRestoring) {
                        MyWishlistFragment.this.mAdapter.onRestoreInstanceState(MyWishlistFragment.this.mMyWishlistView, MyWishlistFragment.this.mRecyclerViewRestoreBundle);
                        MyWishlistFragment.this.mRecyclerViewRestoreBundle.clear();
                    }
                    MyWishlistFragment.this.mMyWishlistView.setEmptyView(MyWishlistFragment.this.mDataView.findViewById(R.id.no_results_view));
                }
            }
        });
    }

    public void onDestroyView() {
        if (!(this.mMyWishlistView == null || this.mMyWishlistView.getVisibility() != 0 || this.mAdapter == null)) {
            this.mAdapter.onSaveInstanceState(this.mMyWishlistView, this.mRecyclerViewRestoreBundle);
        }
        this.mMyWishlistView = null;
        if (this.mAdapter != null) {
            this.mAdapter.onDestroyView();
            this.mAdapter.onDestroy();
            WishlistHelper.removeWishlistStatusListener(this.mAdapter);
            this.mAdapter = null;
            this.mIsAdapterSet = false;
        }
        if (this.mDataView instanceof PlayHeaderListLayout) {
            ((PlayHeaderListLayout) this.mDataView).detach();
        }
        super.onDestroyView();
    }

    protected void rebindViews() {
    }

    protected void onInitViewBinders() {
    }

    public PlayStoreUiElement getPlayStoreUiElement() {
        return this.mUiElementProto;
    }
}
