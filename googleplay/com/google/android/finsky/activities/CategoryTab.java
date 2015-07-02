package com.google.android.finsky.activities;

import android.content.Context;
import android.os.Parcelable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.vending.R;
import com.google.android.finsky.adapters.CategoryAdapter;
import com.google.android.finsky.adapters.QuickLinkHelper.QuickLinkInfo;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.api.model.DfeToc;
import com.google.android.finsky.layout.play.PlayRecyclerView;
import com.google.android.finsky.layout.play.SelectableUiElementNode;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.protos.Browse.BrowseLink;
import com.google.android.finsky.utils.ObjectMap;
import com.google.android.play.image.BitmapLoader;

public class CategoryTab implements ViewPagerTab {
    private final BitmapLoader mBitmapLoader;
    private final BrowseLink[] mCategories;
    private CategoryAdapter mCategoryAdapter;
    private ViewGroup mCategoryView;
    private final Context mContext;
    private ObjectMap mInstanceState;
    private boolean mIsCurrentlySelected;
    private final LayoutInflater mLayoutInflater;
    private final NavigationManager mNavigationManager;
    private SelectableUiElementNode mParentNode;
    private final QuickLinkInfo[] mQuickLinks;
    private PlayRecyclerView mRecyclerView;
    private final int mTabMode;
    private final String mTabTitle;
    private final DfeToc mToc;

    public CategoryTab(Context context, NavigationManager navigationManager, BitmapLoader bitmapLoader, LayoutInflater inflater, TabData tabData, DfeToc toc, int tabMode) {
        this.mInstanceState = new ObjectMap();
        this.mContext = context;
        this.mNavigationManager = navigationManager;
        this.mBitmapLoader = bitmapLoader;
        this.mLayoutInflater = inflater;
        this.mToc = toc;
        this.mTabTitle = tabData.browseTab.title;
        this.mCategories = tabData.browseTab.category;
        this.mQuickLinks = tabData.quickLinks;
        this.mParentNode = tabData.elementNode;
        this.mTabMode = tabMode;
    }

    public View getView(int backendId) {
        if (this.mCategoryView == null) {
            this.mCategoryView = (ViewGroup) this.mLayoutInflater.inflate(R.layout.category_tab, null);
            this.mRecyclerView = (PlayRecyclerView) this.mCategoryView.findViewById(R.id.tab_recycler_view);
            this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this.mContext));
            this.mRecyclerView.setVisibility(0);
            this.mCategoryAdapter = new CategoryAdapter(this.mContext, this.mCategories, this.mNavigationManager, backendId, this.mToc, this.mBitmapLoader, this.mQuickLinks, this.mTabTitle, this.mTabMode, this.mParentNode);
            this.mRecyclerView.setAdapter(this.mCategoryAdapter);
            if (this.mInstanceState.containsKey("CategoryTab.RecyclerViewState")) {
                this.mRecyclerView.restoreInstanceState((Parcelable) this.mInstanceState.get("CategoryTab.RecyclerViewState"));
            }
        }
        return this.mCategoryView;
    }

    public void onDestroy() {
        this.mCategoryAdapter = null;
        this.mCategoryView = null;
    }

    public void setTabSelected(boolean isSelected) {
        if (isSelected != this.mIsCurrentlySelected) {
            if (isSelected) {
                FinskyEventLog.startNewImpression(this.mParentNode);
                this.mParentNode.setNodeSelected(true);
                if (this.mParentNode.getPlayStoreUiElement().child.length == 0) {
                    FinskyEventLog.requestImpressions(this.mCategoryView);
                }
            } else {
                this.mParentNode.setNodeSelected(false);
            }
            this.mIsCurrentlySelected = isSelected;
        }
    }

    public ObjectMap onSaveInstanceState() {
        if (!(this.mRecyclerView == null || this.mCategoryAdapter == null)) {
            this.mInstanceState.put("CategoryTab.RecyclerViewState", this.mRecyclerView.saveInstanceState());
        }
        return this.mInstanceState;
    }

    public void onRestoreInstanceState(ObjectMap instanceState) {
        if (instanceState != null) {
            this.mInstanceState = instanceState;
        }
    }
}
