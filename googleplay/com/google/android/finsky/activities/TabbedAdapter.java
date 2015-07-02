package com.google.android.finsky.activities;

import android.content.Context;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.vending.R;
import com.google.android.finsky.activities.TabbedBrowseFragment.BackgroundViewConfigurator;
import com.google.android.finsky.adapters.QuickLinkHelper;
import com.google.android.finsky.adapters.QuickLinkHelper.QuickLinkInfo;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.api.model.DfeList;
import com.google.android.finsky.api.model.DfeToc;
import com.google.android.finsky.layout.actionbar.ActionBarController;
import com.google.android.finsky.layout.play.PlayStoreUiElementNode;
import com.google.android.finsky.layout.play.SelectableUiElementNode;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.protos.Browse.BrowseTab;
import com.google.android.finsky.protos.Browse.QuickLink;
import com.google.android.finsky.utils.ClientMutationCache;
import com.google.android.finsky.utils.Lists;
import com.google.android.finsky.utils.ObjectMap;
import com.google.android.finsky.utils.Utils;
import com.google.android.play.image.BitmapLoader;
import java.util.ArrayList;
import java.util.List;

public class TabbedAdapter extends PagerAdapter {
    private final ActionBarController mActionBarController;
    private final int mBackendId;
    private final BitmapLoader mBitmapLoader;
    private final ClientMutationCache mClientMutationCache;
    private final Context mContext;
    private int mCurrentlySelectedTab;
    private final DfeApi mDfeApi;
    private final DfeToc mDfeToc;
    private final LayoutInflater mLayoutInflater;
    private final NavigationManager mNavigationManager;
    private final PlayStoreUiElementNode mParent;
    List<TabData> mTabDataList;
    private final TabDataListener mTabDataListener;
    private final TabSelectionTracker mTabSelectionTracker;

    static class TabData {
        public final BrowseTab browseTab;
        public DfeList dfeList;
        public SelectableUiElementNode elementNode;
        private ObjectMap instanceState;
        public final boolean isCategoryTab;
        public QuickLinkInfo[] quickLinks;
        public ViewPagerTab viewPagerTab;

        public TabData(BrowseTab browseTab, boolean isCategoryTab) {
            this.browseTab = browseTab;
            this.isCategoryTab = isCategoryTab;
        }
    }

    public interface TabDataListener {
        void onTabDataReady(BackgroundViewConfigurator backgroundViewConfigurator);
    }

    private static class TabSelectionTracker implements Runnable {
        private int mCurrScrollState;
        private List<ListTab> mDeferredTabs;
        private Handler mHandler;
        private boolean mShouldDeferListTabDataDisplay;

        private TabSelectionTracker() {
            this.mHandler = new Handler();
            this.mDeferredTabs = Lists.newArrayList();
            this.mCurrScrollState = 0;
        }

        private void onBeforeTabSelected(int pageIndex) {
            this.mShouldDeferListTabDataDisplay = true;
            this.mHandler.postDelayed(this, 500);
        }

        private void onPageScrollStateChanged(int scrollState) {
            Utils.ensureOnMainThread();
            this.mCurrScrollState = scrollState;
            if (scrollState == 2) {
                this.mHandler.removeCallbacks(this);
                this.mShouldDeferListTabDataDisplay = true;
            }
            if (scrollState == 0) {
                exitDeferredDataDisplayMode();
            }
        }

        private void addDeferredTab(ListTab deferred) {
            this.mDeferredTabs.add(deferred);
        }

        private void exitDeferredDataDisplayMode() {
            Utils.ensureOnMainThread();
            for (ListTab deferred : this.mDeferredTabs) {
                deferred.exitDeferredDataDisplayMode();
            }
            this.mDeferredTabs.clear();
            this.mShouldDeferListTabDataDisplay = false;
        }

        public void run() {
            if (this.mCurrScrollState == 0) {
                exitDeferredDataDisplayMode();
            }
        }

        public boolean shouldDeferListTabDataDisplay() {
            return this.mShouldDeferListTabDataDisplay;
        }
    }

    public TabbedAdapter(Context context, LayoutInflater inflater, NavigationManager navManager, DfeToc toc, DfeApi dfeApi, ClientMutationCache clientMutationCache, BitmapLoader loader, BrowseTab[] browseTabs, QuickLink[] quickLinks, int quickLinkTabIndex, int quickLinkFallbackTabIndex, int backendId, ObjectMap tabRestoreState, PlayStoreUiElementNode parent, ActionBarController actionBarController, TabDataListener tabDataListener) {
        this.mTabDataList = Lists.newArrayList();
        this.mTabSelectionTracker = new TabSelectionTracker();
        this.mContext = context;
        this.mLayoutInflater = inflater;
        this.mNavigationManager = navManager;
        this.mDfeToc = toc;
        this.mDfeApi = dfeApi;
        this.mClientMutationCache = clientMutationCache;
        this.mBitmapLoader = loader;
        this.mBackendId = backendId;
        this.mParent = parent;
        this.mCurrentlySelectedTab = -1;
        this.mActionBarController = actionBarController;
        this.mTabDataListener = tabDataListener;
        generateTabList(browseTabs, tabRestoreState);
        generateQuickLinks(quickLinks, quickLinkTabIndex, quickLinkFallbackTabIndex);
        showQuickLinksIfNoListsExist(quickLinks);
    }

    private void showQuickLinksIfNoListsExist(QuickLink[] quickLinks) {
        if (this.mTabDataList.size() == 0 && quickLinks != null && quickLinks.length > 0) {
            TabData fallbackTab = new TabData(new BrowseTab(), true);
            QuickLinkInfo[] quickLinkInfos = new QuickLinkInfo[quickLinks.length];
            for (int i = 0; i < quickLinks.length; i++) {
                quickLinkInfos[i] = new QuickLinkInfo(quickLinks[i], this.mBackendId);
            }
            fallbackTab.quickLinks = quickLinkInfos;
            fallbackTab.elementNode = new SelectableUiElementNode(401, null, null, this.mParent);
            this.mTabDataList.add(fallbackTab);
        }
    }

    private void generateQuickLinks(QuickLink[] quickLinks, int quickLinkTabIndex, int quickLinkFallbackTabIndex) {
        if (!this.mTabDataList.isEmpty()) {
            List<QuickLinkInfo> quickLinkInfos = null;
            if (quickLinks != null && quickLinks.length > 0) {
                quickLinkInfos = Lists.newArrayList();
                for (QuickLink link : quickLinks) {
                    quickLinkInfos.add(new QuickLinkInfo(link, link.backendId));
                }
            }
            if (quickLinkInfos != null && quickLinkInfos.size() > 0) {
                List<QuickLinkInfo> quickLinkTabOutput = Lists.newArrayList();
                List<QuickLinkInfo> quickLinkFallbackTabOutput = Lists.newArrayList();
                QuickLinkHelper.getQuickLinksForStream(this.mContext, quickLinkInfos, quickLinkTabOutput, quickLinkFallbackTabOutput);
                TabData quickLinkTab = (TabData) this.mTabDataList.get(quickLinkTabIndex);
                TabData quickLinkFallbackTab = quickLinkFallbackTabIndex != -1 ? (TabData) this.mTabDataList.get(quickLinkFallbackTabIndex) : null;
                quickLinkTab.quickLinks = (QuickLinkInfo[]) quickLinkTabOutput.toArray(new QuickLinkInfo[quickLinkTabOutput.size()]);
                if (quickLinkFallbackTab != null) {
                    quickLinkFallbackTab.quickLinks = (QuickLinkInfo[]) quickLinkFallbackTabOutput.toArray(new QuickLinkInfo[quickLinkFallbackTabOutput.size()]);
                }
            }
        }
    }

    public void destroyItem(ViewGroup viewPager, int position, Object object) {
        ViewPagerTab tab = (ViewPagerTab) object;
        ((ViewPager) viewPager).removeView(tab.getView(this.mBackendId));
        TabData tabData = (TabData) this.mTabDataList.get(position);
        if (tabData.dfeList != null) {
            tabData.dfeList.flushUnusedPages();
            tabData.dfeList.clearTransientState();
        }
        tabData.instanceState = tabData.viewPagerTab.onSaveInstanceState();
        tabData.viewPagerTab = null;
        tab.onDestroy();
    }

    public void onSaveInstanceState(ObjectMap instanceState) {
        instanceState.put("TabbedAdapter.TabInstanceStates", getTabInstanceStates());
        instanceState.put("TabbedAdapter.TabDfeLists", getDfeLists());
    }

    private ArrayList<ObjectMap> getTabInstanceStates() {
        if (this.mTabDataList == null || this.mTabDataList.isEmpty()) {
            return null;
        }
        ArrayList<ObjectMap> instanceStates = new ArrayList();
        for (TabData tabData : this.mTabDataList) {
            if (tabData.viewPagerTab != null) {
                instanceStates.add(tabData.viewPagerTab.onSaveInstanceState());
            } else {
                instanceStates.add(tabData.instanceState);
            }
        }
        return instanceStates;
    }

    private ArrayList<DfeList> getDfeLists() {
        if (this.mTabDataList == null || this.mTabDataList.isEmpty()) {
            return null;
        }
        ArrayList<DfeList> dfeLists = Lists.newArrayList();
        for (TabData tabData : this.mTabDataList) {
            dfeLists.add(tabData.dfeList);
        }
        return dfeLists;
    }

    public int getCount() {
        return this.mTabDataList.size();
    }

    public Object instantiateItem(ViewGroup viewPager, int position) {
        ViewPagerTab tab;
        TabData tabData = (TabData) this.mTabDataList.get(position);
        BrowseTab browseTab = tabData.browseTab;
        boolean shouldDeferListTabDataDisplay = this.mTabSelectionTracker.shouldDeferListTabDataDisplay();
        int tabMode = getCount() > 1 ? 0 : 2;
        if (tabData.isCategoryTab) {
            tab = new CategoryTab(this.mContext, this.mNavigationManager, this.mBitmapLoader, this.mLayoutInflater, tabData, this.mDfeToc, tabMode);
        } else {
            if (tabData.dfeList == null) {
                tabData.dfeList = new DfeList(this.mDfeApi, browseTab.listUrl, true);
            }
            ViewPagerTab listTab = new ListTab(this.mContext, this.mNavigationManager, this.mBitmapLoader, this.mDfeApi, this.mLayoutInflater, tabData, this.mDfeToc, this.mClientMutationCache, shouldDeferListTabDataDisplay, this.mActionBarController, tabMode, this.mTabDataListener);
        }
        tab.onRestoreInstanceState(tabData.instanceState);
        tab.setTabSelected(this.mCurrentlySelectedTab == position);
        tabData.viewPagerTab = tab;
        viewPager.addView(tab.getView(this.mBackendId));
        if (shouldDeferListTabDataDisplay && (tab instanceof ListTab)) {
            this.mTabSelectionTracker.addDeferredTab((ListTab) tab);
        }
        return tab;
    }

    public String getPageTitle(int index) {
        if (index >= this.mTabDataList.size()) {
            return "";
        }
        return ((TabData) this.mTabDataList.get(index)).browseTab.title.toUpperCase();
    }

    public void onPageSelected(int position) {
        int i = 0;
        while (i < this.mTabDataList.size()) {
            if (((TabData) this.mTabDataList.get(i)).viewPagerTab != null) {
                ((TabData) this.mTabDataList.get(i)).viewPagerTab.setTabSelected(i == position);
            }
            i++;
        }
        this.mCurrentlySelectedTab = position;
    }

    public boolean isViewFromObject(View view, Object object) {
        return ((ViewPagerTab) object).getView(this.mBackendId) == view;
    }

    public float getPageWidth(int position) {
        if (position == 0 && this.mTabDataList.size() > 1 && ((TabData) this.mTabDataList.get(0)).isCategoryTab) {
            return ((float) this.mContext.getResources().getInteger(R.integer.category_tab_width_in_percent)) / 100.0f;
        }
        return 1.0f;
    }

    public void restoreState(Parcelable state, ClassLoader loader) {
    }

    public Parcelable saveState() {
        return null;
    }

    public void startUpdate(ViewGroup container) {
    }

    public void finishUpdate(ViewGroup viewPager) {
    }

    private void generateTabList(BrowseTab[] browseTabs, ObjectMap restoreState) {
        int i;
        this.mTabDataList.clear();
        for (BrowseTab browseTab : browseTabs) {
            TabData tabData = new TabData(browseTab, browseTab.category.length > 0);
            tabData.elementNode = new SelectableUiElementNode(403, browseTab.serverLogsCookie, null, this.mParent);
            this.mTabDataList.add(tabData);
        }
        List<ObjectMap> scrollPositions = restoreScrollPositions(restoreState);
        List<DfeList> restoredDfeLists = restoreDfeLists(restoreState);
        boolean restoreDfeLists = restoredDfeLists != null && restoredDfeLists.size() == this.mTabDataList.size();
        boolean restoreScrollPositions = scrollPositions != null && scrollPositions.size() == this.mTabDataList.size();
        for (i = 0; i < browseTabs.length; i++) {
            tabData = (TabData) this.mTabDataList.get(i);
            if (restoreDfeLists) {
                tabData.dfeList = (DfeList) restoredDfeLists.get(i);
            }
            if (restoreScrollPositions) {
                tabData.instanceState = (ObjectMap) scrollPositions.get(i);
            }
        }
    }

    private List<ObjectMap> restoreScrollPositions(ObjectMap instanceState) {
        if (instanceState == null || !instanceState.containsKey("TabbedAdapter.TabInstanceStates")) {
            return null;
        }
        return instanceState.getList("TabbedAdapter.TabInstanceStates");
    }

    private List<DfeList> restoreDfeLists(ObjectMap instanceState) {
        List<DfeList> lists = null;
        if (instanceState != null && instanceState.containsKey("TabbedAdapter.TabDfeLists")) {
            lists = instanceState.getList("TabbedAdapter.TabDfeLists");
            if (lists != null) {
                for (DfeList list : lists) {
                    if (list != null) {
                        list.setDfeApi(this.mDfeApi);
                    }
                }
            }
        }
        return lists;
    }

    public void onBeforeTabSelected(int pageIndex) {
        this.mTabSelectionTracker.onBeforeTabSelected(pageIndex);
    }

    public void onPageScrollStateChanged(int scrollState) {
        this.mTabSelectionTracker.onPageScrollStateChanged(scrollState);
    }
}
