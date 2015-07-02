package com.google.android.finsky.activities;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.TextUtils;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.vending.R;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.activities.TabbedAdapter.TabDataListener;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.analytics.PlayStore.PlayStoreUiElement;
import com.google.android.finsky.api.model.DfeBrowse;
import com.google.android.finsky.api.model.DfeToc;
import com.google.android.finsky.fragments.UrlBasedPageFragment;
import com.google.android.finsky.layout.ContentFrame;
import com.google.android.finsky.layout.HeaderLayoutSwitcher;
import com.google.android.finsky.layout.HeroGraphicView;
import com.google.android.finsky.layout.LayoutSwitcher;
import com.google.android.finsky.layout.actionbar.ActionBarBackgroundUpdater;
import com.google.android.finsky.layout.actionbar.FinskySearchToolbar;
import com.google.android.finsky.layout.play.FinskyHeaderListLayout;
import com.google.android.finsky.layout.play.FinskyHeaderListLayout.FinskyConfigurator;
import com.google.android.finsky.protos.Toc.CorpusMetadata;
import com.google.android.finsky.utils.CorpusResourceUtils;
import com.google.android.finsky.utils.ObjectMap;
import com.google.android.finsky.utils.UiUtils;
import com.google.android.play.headerlist.PlayHeaderListLayout;
import com.google.android.play.headerlist.PlayHeaderListLayout.OnTabSelectedListener;

public class TabbedBrowseFragment extends UrlBasedPageFragment implements OnPageChangeListener, TabDataListener, OnTabSelectedListener {
    private ActionBarBackgroundUpdater mActionBarBackgroundUpdater;
    private int mBackendId;
    private int mBackgroundType;
    private HeroGraphicView mBackgroundView;
    private String mBreadcrumb;
    private DfeBrowse mBrowseData;
    private ObjectMap mFragmentObjectMap;
    private Bundle mFrameworkBundle;
    private int mHeaderShadowMode;
    private int mRestorePrevSelectedTabIndex;
    private String mRevealNextPageTransition;
    private TabbedAdapter mTabbedAdapter;
    private PlayStoreUiElement mUiElementProto;
    private ViewPager mViewPager;

    public interface BackgroundViewConfigurator {
        void configureBackgroundView(HeroGraphicView heroGraphicView);

        boolean hasBackgroundView();
    }

    private class PlayHeaderListConfigurator extends FinskyConfigurator {
        private final int mBackgroundMode;

        public PlayHeaderListConfigurator(Context context, int backgroundMode) {
            super(context);
            this.mBackgroundMode = backgroundMode;
        }

        protected void addBackgroundView(LayoutInflater inflater, ViewGroup root) {
            TabbedBrowseFragment.this.mBackgroundView = (HeroGraphicView) inflater.inflate(R.layout.hero_graphic, root, false);
            TabbedBrowseFragment.this.mBackgroundView.setVisibility(4);
            root.addView(TabbedBrowseFragment.this.mBackgroundView);
        }

        protected void addContentView(LayoutInflater inflater, ViewGroup root) {
            inflater.inflate(R.layout.header_list_pager, root);
        }

        protected boolean hasViewPager() {
            return true;
        }

        protected int getViewPagerId() {
            return R.id.viewpager;
        }

        protected int getTabMode() {
            return 2;
        }

        protected int getTabPaddingMode() {
            return 1;
        }

        protected int getHeaderMode() {
            return 1;
        }

        protected int getListViewId() {
            return R.id.tab_recycler_view;
        }

        protected boolean alwaysUseFloatingBackground() {
            return false;
        }

        protected float getBackgroundViewParallaxRatio() {
            return 0.8f;
        }

        protected int getHeaderShadowMode() {
            return this.mBackgroundMode != 2 ? 3 : 2;
        }
    }

    public TabbedBrowseFragment() {
        this.mUiElementProto = FinskyEventLog.obtainPlayStoreUiElement(1);
        this.mBackendId = 0;
        this.mRestorePrevSelectedTabIndex = -1;
        this.mFrameworkBundle = new Bundle();
        this.mFragmentObjectMap = new ObjectMap();
        this.mHeaderShadowMode = 3;
    }

    public static TabbedBrowseFragment newInstance(String url, String title, int backendId, DfeToc dfeToc, Pair<View, String> sharedElement) {
        boolean isInSearchBoxOnlyMode = true;
        int backgroundType = 0;
        TabbedBrowseFragment fragment = new TabbedBrowseFragment();
        if (!(sharedElement == null || TextUtils.isEmpty((CharSequence) sharedElement.second))) {
            fragment.mRevealNextPageTransition = (String) sharedElement.second;
        }
        if (backendId >= 0) {
            fragment.mBackendId = backendId;
        }
        if (!TextUtils.isEmpty(title)) {
            fragment.mBreadcrumb = title;
        }
        if (DfeToc.isAggregatedHome(dfeToc, url)) {
            if (!FinskySearchToolbar.supportsSearchBoxOnlyMode() || dfeToc.getCorpusList().size() <= 1) {
                isInSearchBoxOnlyMode = false;
            }
            if (isInSearchBoxOnlyMode) {
                backgroundType = 2;
            }
        } else {
            backgroundType = 1;
        }
        fragment.setDfeTocAndUrl(dfeToc, url);
        fragment.setArgument("TabbedBrowseFragment.BackgroundType", backgroundType);
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        this.mBackgroundType = getArguments().getInt("TabbedBrowseFragment.BackgroundType");
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ContentFrame frame = (ContentFrame) super.onCreateView(inflater, container, savedInstanceState);
        FinskyHeaderListLayout headerListLayout = this.mDataView;
        headerListLayout.configure(new PlayHeaderListConfigurator(headerListLayout.getContext(), this.mBackgroundType));
        headerListLayout.setContentViewId(R.id.viewpager);
        headerListLayout.configureEventInterception(this.mBackgroundView);
        if (this.mBackgroundType != 2) {
            headerListLayout.setAlwaysUseFloatingBackground(true);
            headerListLayout.setFloatingControlsBackground(getBackgroundColorDrawable());
        }
        return frame;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            this.mFrameworkBundle = savedInstanceState;
        }
        if (!this.mFrameworkBundle.isEmpty()) {
            restoreFromFrameworkBundle();
        }
        if (isDataReady()) {
            rebindViews();
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

    private void restoreFromFrameworkBundle() {
        if (this.mFrameworkBundle.containsKey("TabbedBrowseFragment.Breadcrumb")) {
            this.mBreadcrumb = this.mFrameworkBundle.getString("TabbedBrowseFragment.Breadcrumb");
        } else {
            this.mBreadcrumb = null;
        }
        if (this.mFrameworkBundle.containsKey("TabbedBrowseFragment.BackendId")) {
            this.mBackendId = this.mFrameworkBundle.getInt("TabbedBrowseFragment.BackendId");
        }
        if (this.mFrameworkBundle.containsKey("TabbedBrowseFragment.CurrentTab")) {
            this.mRestorePrevSelectedTabIndex = this.mFrameworkBundle.getInt("TabbedBrowseFragment.CurrentTab");
        }
    }

    public void refresh() {
        if (isDataReady()) {
            rebindViews();
        } else {
            super.refresh();
        }
    }

    protected int getLayoutRes() {
        return R.layout.header_list_container;
    }

    public boolean isDataReady() {
        return this.mBrowseData != null && this.mBrowseData.isReady();
    }

    private String getBreadcrumbText() {
        String breadcrumb = this.mBrowseData.getTitle();
        if (breadcrumb != null) {
            return breadcrumb;
        }
        CorpusMetadata data = getToc().getCorpus(this.mBackendId);
        if (data == null) {
            return "";
        }
        if (this.mNavigationManager.canGoUp()) {
            return data.name;
        }
        return this.mContext.getString(R.string.launcher_name);
    }

    public void onDataChanged() {
        if (isDataReady()) {
            FinskyEventLog.setServerLogCookie(this.mUiElementProto, this.mBrowseData.getServerLogsCookie());
            super.onDataChanged();
        }
    }

    public void rebindViews() {
        switchToData();
        if (this.mViewPager == null || this.mTabbedAdapter == null) {
            int currentTabIndex;
            this.mBackendId = this.mBrowseData.getBackendId();
            this.mBreadcrumb = getBreadcrumbText();
            rebindActionBar();
            if (!TextUtils.isEmpty(this.mBreadcrumb)) {
                UiUtils.sendAccessibilityEventWithText(this.mContext, this.mBreadcrumb, getView());
            }
            this.mTabbedAdapter = new TabbedAdapter(this.mContext, getActivity().getLayoutInflater(), this.mNavigationManager, getToc(), this.mDfeApi, FinskyApp.get().getClientMutationCache(FinskyApp.get().getCurrentAccountName()), this.mBitmapLoader, this.mBrowseData.getBrowseTabs(), this.mBrowseData.getQuickLinkList(), this.mBrowseData.getQuickLinkTabIndex(), this.mBrowseData.getQuickLinkFallbackTabIndex(), this.mBackendId, this.mFragmentObjectMap, this, this.mActionBarController, this);
            this.mViewPager = (ViewPager) this.mDataView.findViewById(R.id.viewpager);
            this.mViewPager.setAdapter(this.mTabbedAdapter);
            this.mViewPager.setPageMargin(getResources().getDimensionPixelSize(R.dimen.swipey_tab_gutter_width));
            PlayHeaderListLayout headerListLayout = (PlayHeaderListLayout) this.mDataView;
            headerListLayout.notifyPagerAdapterChanged();
            int newTabMode = this.mTabbedAdapter.getCount() > 1 ? 0 : 2;
            headerListLayout.setTabMode(newTabMode, FinskyHeaderListLayout.getMinimumHeaderHeight(this.mContext, newTabMode, 0));
            headerListLayout.setOnPageChangeListener(this);
            headerListLayout.setOnTabSelectedListener(this);
            if (this.mBackgroundType != 2) {
                headerListLayout.setFloatingControlsBackground(getBackgroundColorDrawable());
            }
            if (this.mRestorePrevSelectedTabIndex != -1) {
                currentTabIndex = this.mRestorePrevSelectedTabIndex;
                this.mRestorePrevSelectedTabIndex = -1;
            } else {
                currentTabIndex = this.mBrowseData.getLandingTabIndex();
            }
            if (this.mViewPager.getCurrentItem() == currentTabIndex) {
                this.mTabbedAdapter.onPageSelected(currentTabIndex);
            } else {
                this.mViewPager.setCurrentItem(currentTabIndex, false);
            }
        }
    }

    private ColorDrawable getBackgroundColorDrawable() {
        return new ColorDrawable(getActionBarColor());
    }

    protected int getActionBarColor() {
        return CorpusResourceUtils.getPrimaryColor(getActivity(), this.mBackendId);
    }

    public void onTabDataReady(final BackgroundViewConfigurator backgroundViewConfigurator) {
        boolean z = true;
        PlayHeaderListLayout headerListLayout = this.mDataView;
        if (this.mTabbedAdapter.getCount() == 1 && backgroundViewConfigurator.hasBackgroundView()) {
            this.mBackgroundView.setVisibility(0);
            headerListLayout.post(new Runnable() {
                public void run() {
                    backgroundViewConfigurator.configureBackgroundView(TabbedBrowseFragment.this.mBackgroundView);
                }
            });
            headerListLayout.setAlwaysUseFloatingBackground(false);
            this.mHeaderShadowMode = 1;
            headerListLayout.setHeaderShadowMode(this.mHeaderShadowMode);
            this.mActionBarBackgroundUpdater = new ActionBarBackgroundUpdater(headerListLayout);
            headerListLayout.setOnLayoutChangedListener(this.mActionBarBackgroundUpdater);
            this.mActionBarBackgroundUpdater.updateBackground();
            return;
        }
        this.mBackgroundView.setVisibility(4);
        if (this.mBackgroundType == 2) {
            z = false;
        }
        headerListLayout.setAlwaysUseFloatingBackground(z);
        headerListLayout.setOnLayoutChangedListener(null);
    }

    public void rebindActionBar() {
        boolean searchBoxOnly = true;
        this.mPageFragmentHost.updateBreadcrumb(this.mBreadcrumb);
        this.mPageFragmentHost.updateCurrentBackendId(this.mBackendId, true);
        if (!(FinskySearchToolbar.supportsSearchBoxOnlyMode() && DfeToc.isAggregatedHome(getToc(), getUrl()) && getToc().getCorpusList().size() > 1)) {
            searchBoxOnly = false;
        }
        this.mPageFragmentHost.updateActionBarMode(searchBoxOnly);
    }

    protected void onInitViewBinders() {
    }

    protected void requestData() {
        clearState();
        this.mBrowseData = new DfeBrowse(this.mDfeApi, this.mUrl);
        this.mBrowseData.addDataChangedListener(this);
        this.mBrowseData.addErrorListener(this);
    }

    private void clearState() {
        if (this.mDataView != null) {
            PlayHeaderListLayout headerListLayout = this.mDataView;
            headerListLayout.setOnPageChangeListener(null);
            headerListLayout.setOnTabSelectedListener(null);
        }
        if (this.mViewPager != null) {
            this.mViewPager.setAdapter(null);
            this.mViewPager = null;
        }
        this.mTabbedAdapter = null;
    }

    public void onSaveInstanceState(Bundle bundle) {
        recordState();
        bundle.putAll(this.mFrameworkBundle);
        super.onSaveInstanceState(bundle);
    }

    private void recordState() {
        if (isDataReady()) {
            if (this.mBreadcrumb != null) {
                this.mFrameworkBundle.putString("TabbedBrowseFragment.Breadcrumb", this.mBreadcrumb);
            }
            this.mFrameworkBundle.putInt("TabbedBrowseFragment.BackendId", this.mBackendId);
            if (this.mViewPager != null) {
                this.mFrameworkBundle.putInt("TabbedBrowseFragment.CurrentTab", this.mViewPager.getCurrentItem());
                this.mTabbedAdapter.onSaveInstanceState(this.mFragmentObjectMap);
            }
        }
    }

    public void onDestroyView() {
        recordState();
        clearState();
        if (this.mDataView instanceof PlayHeaderListLayout) {
            this.mDataView.detach();
            if (this.mActionBarBackgroundUpdater != null) {
                this.mActionBarBackgroundUpdater.resetBackground();
                this.mActionBarBackgroundUpdater = null;
            }
        }
        this.mBackgroundView = null;
        super.onDestroyView();
    }

    public int getBackendId() {
        return this.mBackendId;
    }

    public void onPageScrollStateChanged(int scrollState) {
        this.mTabbedAdapter.onPageScrollStateChanged(scrollState);
    }

    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    public void onPageSelected(int position) {
        this.mTabbedAdapter.onPageSelected(position);
        if (!TextUtils.isEmpty(this.mTabbedAdapter.getPageTitle(position))) {
            UiUtils.sendAccessibilityEventWithText(this.mContext, this.mContext.getString(R.string.accessibility_event_tab_selected, new Object[]{selectedTabTitle}), this.mViewPager);
        }
    }

    public void onBeforeTabSelected(int pagerIndex) {
        this.mTabbedAdapter.onBeforeTabSelected(pagerIndex);
    }

    public void onTabSelected(int pagerIndex) {
    }

    public PlayStoreUiElement getPlayStoreUiElement() {
        return this.mUiElementProto;
    }

    public void onEnterActionBarSearchMode() {
        if (this.mTabbedAdapter != null && this.mTabbedAdapter.getCount() == 1) {
            super.onEnterActionBarSearchMode();
        }
    }

    public void onExitActionBarSearchMode() {
        if (this.mTabbedAdapter != null && this.mTabbedAdapter.getCount() == 1) {
            super.onExitActionBarSearchMode();
        }
    }

    protected int getDefaultHeaderShadowMode() {
        return this.mHeaderShadowMode;
    }
}
