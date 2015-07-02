package com.google.android.finsky.activities.myapps;

import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.activities.AuthenticatedActivity;
import com.google.android.finsky.activities.ViewPagerTab;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.api.model.DfeToc;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.utils.Lists;
import com.google.android.finsky.utils.ObjectMap;
import com.google.android.play.image.BitmapLoader;
import com.google.android.wallet.instrumentmanager.R;
import java.util.ArrayList;
import java.util.List;

public class MyAppsTabbedAdapter extends PagerAdapter {
    private final AuthenticatedActivity mAuthenticatedActivity;
    private final BitmapLoader mBitmapLoader;
    private final DfeApi mDfeApi;
    private final DfeToc mDfeToc;
    private final MyAppsTabbedFragment mFragment;
    private final boolean mHasSubscriptions;
    private final NavigationManager mNavigationManager;
    protected final List<TabType> mTabDataList;
    private final List<String> mTabTitles;

    public static class TabType {
        public MyAppsTab<?> slidingPanelTab;
        private ObjectMap tabBundle;
        public final int type;

        public TabType(int type) {
            this.type = type;
        }
    }

    public MyAppsTabbedAdapter(AuthenticatedActivity authenticatedActivity, NavigationManager navManager, DfeApi dfeApi, DfeToc dfeToc, BitmapLoader loader, boolean enableSubscriptions, ObjectMap frameworkObjectBundle, MyAppsTabbedFragment fragment) {
        this.mTabDataList = Lists.newArrayList();
        this.mAuthenticatedActivity = authenticatedActivity;
        this.mNavigationManager = navManager;
        this.mBitmapLoader = loader;
        this.mDfeApi = dfeApi;
        this.mDfeToc = dfeToc;
        this.mHasSubscriptions = enableSubscriptions;
        this.mFragment = fragment;
        generateTabList(frameworkObjectBundle);
        this.mTabTitles = getTitles();
    }

    public void destroyItem(ViewGroup viewPager, int position, Object object) {
        ViewPagerTab tab = (ViewPagerTab) object;
        ((ViewPager) viewPager).removeView(tab.getView(3));
        TabType tabType = (TabType) this.mTabDataList.get(position);
        tabType.tabBundle = tabType.slidingPanelTab.onSaveInstanceState();
        tabType.slidingPanelTab = null;
        tab.onDestroy();
    }

    public void onSaveInstanceState(ObjectMap bundle) {
        bundle.put("MyAppsTabbedAdapter.TabBundles", getTabInstanceStates());
    }

    public int getTabType(int tabIndex) {
        return ((TabType) this.mTabDataList.get(tabIndex)).type;
    }

    private ArrayList<ObjectMap> getTabInstanceStates() {
        if (this.mTabDataList == null || this.mTabDataList.isEmpty()) {
            return null;
        }
        ArrayList<ObjectMap> bundles = new ArrayList();
        for (TabType tabData : this.mTabDataList) {
            if (tabData.slidingPanelTab != null) {
                bundles.add(tabData.slidingPanelTab.onSaveInstanceState());
            } else {
                bundles.add(tabData.tabBundle);
            }
        }
        return bundles;
    }

    public int getCount() {
        return this.mTabDataList.size();
    }

    boolean finishActiveMode() {
        boolean wasInActiveMode = false;
        for (TabType tabType : this.mTabDataList) {
            MyAppsTab<?> panelTab = tabType.slidingPanelTab;
            if (panelTab != null && panelTab.finishActiveMode()) {
                wasInActiveMode = true;
            }
        }
        return wasInActiveMode;
    }

    public Object instantiateItem(ViewGroup viewPager, int position) {
        TabType tabType = (TabType) this.mTabDataList.get(position);
        MyAppsTab<?> tab = tabType.slidingPanelTab;
        if (tab == null) {
            switch (tabType.type) {
                case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                    tab = new MyAppsSubscriptionsTab(this.mAuthenticatedActivity, this.mDfeApi, this.mDfeToc, this.mNavigationManager, this.mBitmapLoader);
                    break;
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    tab = new MyAppsInstalledTab(this.mAuthenticatedActivity, this.mDfeApi, this.mDfeToc, this.mNavigationManager, this.mBitmapLoader, this.mFragment);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    tab = new MyAppsLibraryTab(this.mAuthenticatedActivity, this.mDfeApi, this.mDfeToc, this.mNavigationManager, this.mBitmapLoader, this.mFragment, FinskyApp.get().getLibraries().getAccountLibrary(this.mDfeApi.getAccount()), this.mFragment);
                    break;
            }
        }
        tabType.slidingPanelTab = tab;
        viewPager.addView(tab.getView(3));
        tab.onRestoreInstanceState(tabType.tabBundle);
        tab.loadData();
        return tab;
    }

    private List<String> getTitles() {
        List<String> titles = Lists.newArrayList();
        if (this.mHasSubscriptions) {
            titles.add(this.mAuthenticatedActivity.getString(com.android.vending.R.string.my_apps_tab_subscriptions).toUpperCase());
        }
        titles.add(this.mAuthenticatedActivity.getString(com.android.vending.R.string.my_apps_tab_installed).toUpperCase());
        titles.add(this.mAuthenticatedActivity.getString(com.android.vending.R.string.my_apps_tab_library).toUpperCase());
        return titles;
    }

    public String getPageTitle(int index) {
        return (String) this.mTabTitles.get(index);
    }

    public void onPageSelected(int position) {
        int i = 0;
        while (i < this.mTabDataList.size()) {
            if (((TabType) this.mTabDataList.get(i)).slidingPanelTab != null) {
                ((TabType) this.mTabDataList.get(i)).slidingPanelTab.setTabSelected(i == position);
            }
            i++;
        }
    }

    public boolean isViewFromObject(View view, Object object) {
        return ((ViewPagerTab) object).getView(3) == view;
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

    private void generateTabList(ObjectMap restoreBundle) {
        boolean restoreTabBundles = true;
        List<ObjectMap> tabBundles = restoreTabBundles(restoreBundle);
        this.mTabDataList.clear();
        if (this.mHasSubscriptions) {
            this.mTabDataList.add(new TabType(0));
        }
        this.mTabDataList.add(new TabType(1));
        this.mTabDataList.add(new TabType(2));
        if (tabBundles == null || tabBundles.size() != this.mTabDataList.size()) {
            restoreTabBundles = false;
        }
        for (int i = 0; i < this.mTabDataList.size(); i++) {
            if (restoreTabBundles) {
                ((TabType) this.mTabDataList.get(i)).tabBundle = (ObjectMap) tabBundles.get(i);
            }
        }
    }

    private List<ObjectMap> restoreTabBundles(ObjectMap bundle) {
        if (bundle == null || !bundle.containsKey("MyAppsTabbedAdapter.TabBundles")) {
            return null;
        }
        return bundle.getList("MyAppsTabbedAdapter.TabBundles");
    }

    public void removeLibraryItems(List<String> docids) {
        for (int i = 0; i < this.mTabDataList.size(); i++) {
            TabType tabType = (TabType) this.mTabDataList.get(i);
            if (tabType.type == 2) {
                tabType.slidingPanelTab.removeItems(docids);
            }
        }
    }
}
