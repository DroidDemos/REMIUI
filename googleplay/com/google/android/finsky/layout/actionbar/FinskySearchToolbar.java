package com.google.android.finsky.layout.actionbar;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.v4.view.ActionProvider;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.MenuItemCompat.OnActionExpandListener;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import com.android.vending.R;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.search.FinskySearch;
import com.google.android.finsky.utils.Maps;
import com.google.android.finsky.utils.UiUtils;
import com.google.android.play.search.PlaySearchListener;
import com.google.android.play.search.PlaySearchSuggestionModel;
import java.util.Map;
import java.util.Map.Entry;

public class FinskySearchToolbar extends Toolbar implements OnActionExpandListener {
    private static Boolean sSearchBoxOnlyModeSupported;
    private ActionBarController mActionBarController;
    private ActionProvider mActionProvider;
    private FinskySearch mActionView;
    private Drawable mBackground;
    private boolean mIsInSearchBoxOnlyMode;
    private View mLegacySearchActionView;
    private final Map<View, Integer> mPreviousVisibility;
    private final int mSearchBoxHorizontalPadding;
    private final int mSearchBoxTopPadding;
    private FinskySearch mSearchView;

    public static boolean supportsSearchBoxOnlyMode() {
        if (sSearchBoxOnlyModeSupported == null) {
            sSearchBoxOnlyModeSupported = Boolean.valueOf(FinskyApp.get().getExperiments().isEnabled("cl:search.dora_searchbox_enabled"));
        }
        return sSearchBoxOnlyModeSupported.booleanValue();
    }

    public static int getToolbarHeight(Context context) {
        return context.getResources().getDimensionPixelSize(R.dimen.search_toolbar_height);
    }

    public FinskySearchToolbar(Context context) {
        this(context, null);
    }

    public FinskySearchToolbar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mPreviousVisibility = Maps.newHashMap();
        Resources res = context.getResources();
        this.mSearchBoxHorizontalPadding = (UiUtils.getGridHorizontalPadding(res) - res.getDimensionPixelSize(R.dimen.search_bg_9_baked_margin)) + res.getDimensionPixelSize(R.dimen.play_card_default_inset);
        this.mSearchBoxTopPadding = res.getDimensionPixelSize(R.dimen.search_toolbar_padding_top);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mSearchView = (FinskySearch) findViewById(R.id.toolbar_play_search);
        this.mSearchView.setSearchBoxPadding(this.mSearchBoxHorizontalPadding, this.mSearchBoxTopPadding, this.mSearchBoxHorizontalPadding, 0);
        this.mSearchView.configure(FinskyApp.get().getBitmapLoader());
        this.mSearchView.switchToMode(1);
        this.mBackground = super.getBackground();
        this.mActionView = (FinskySearch) LayoutInflater.from(getContext()).inflate(R.layout.search, this, false);
        this.mActionView.configure(FinskyApp.get().getBitmapLoader());
        this.mActionView.switchToMode(1);
        this.mActionView.setListener(new PlaySearchListener() {
            public void onModeChanged(int searchMode) {
                if (searchMode == 1) {
                    if (FinskySearchToolbar.this.mActionBarController != null) {
                        FinskySearchToolbar.this.mActionBarController.exitActionBarSearchMode();
                    }
                    FinskySearchToolbar.this.mActionView.collapseWithAnimation(new Runnable() {
                        public void run() {
                            FinskySearchToolbar.this.collapseActionView();
                            FinskySearchToolbar.this.updateHeight();
                        }
                    });
                }
            }

            public void onQueryChanged(String query, boolean canUpdateSuggestions) {
            }

            public void onSuggestionClicked(PlaySearchSuggestionModel model) {
                FinskySearchToolbar.this.mActionView.switchToMode(1);
            }

            public void onSearch(String query) {
                FinskySearchToolbar.this.mActionView.switchToMode(1);
            }
        });
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        ensureChildrenVisibilityForCurrentMode();
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public void setMode(boolean searchBoxOnly) {
        if (searchBoxOnly) {
            if (!supportsSearchBoxOnlyMode()) {
                throw new UnsupportedOperationException("search-box-only mode is not supported.");
            } else if (hasExpandedActionView()) {
                collapseActionView();
            }
        }
        this.mIsInSearchBoxOnlyMode = searchBoxOnly;
        updateHeight();
    }

    public void setBackgroundDrawable(Drawable background) {
        this.mBackground = background;
        if (this.mIsInSearchBoxOnlyMode) {
            background = null;
        }
        super.setBackgroundDrawable(background);
    }

    public void setNavigationOnClickListener(final OnClickListener listener) {
        super.setNavigationOnClickListener(listener);
        if (supportsSearchBoxOnlyMode()) {
            this.mSearchView.setOnNavButtonClickListener(new OnClickListener() {
                public void onClick(View v) {
                    if (FinskySearchToolbar.this.mSearchView.getMode() == 1 && listener != null) {
                        listener.onClick(v);
                    }
                }
            });
        }
    }

    public void setCurrentBackendId(int backendId) {
        if (supportsSearchBoxOnlyMode()) {
            this.mSearchView.setCurrentBackendId(backendId);
            this.mActionView.setCurrentBackendId(backendId);
        }
    }

    public void setNavigationManager(NavigationManager navigationManager) {
        if (supportsSearchBoxOnlyMode()) {
            this.mSearchView.setNavigationManager(navigationManager);
            this.mActionView.setNavigationManager(navigationManager);
        }
    }

    public void configureSearchMenuItem(Menu menu) {
        MenuItem searchItem = menu.findItem(R.id.search_button);
        View searchActionView = MenuItemCompat.getActionView(searchItem);
        if (!(this.mLegacySearchActionView != null || searchActionView == null || (searchActionView instanceof FinskySearch))) {
            this.mLegacySearchActionView = searchActionView;
        }
        if (searchItem != null) {
            MenuItemCompat.setActionProvider(searchItem, getActionProvider());
            MenuItemCompat.setOnActionExpandListener(searchItem, this);
        }
    }

    public boolean isSearchBoxPresent() {
        return this.mIsInSearchBoxOnlyMode || (supportsSearchBoxOnlyMode() && hasExpandedActionView());
    }

    public void setActionBarController(ActionBarController actionBarController) {
        this.mActionBarController = actionBarController;
    }

    private ActionProvider getActionProvider() {
        if (this.mActionProvider == null) {
            this.mActionProvider = new ActionProvider(getContext()) {
                public View onCreateActionView() {
                    return FinskySearchToolbar.supportsSearchBoxOnlyMode() ? FinskySearchToolbar.this.mActionView : FinskySearchToolbar.this.mLegacySearchActionView;
                }
            };
        }
        return this.mActionProvider;
    }

    private void ensureChildrenVisibilityForCurrentMode() {
        if (isSearchBoxPresent()) {
            super.setBackgroundDrawable(null);
            if (hasExpandedActionView()) {
                setActionViewLayoutParams();
            }
            hideChildren();
        } else {
            super.setBackgroundDrawable(this.mBackground);
            restoreChildrenVisibility();
        }
        this.mSearchView.setVisibility(this.mIsInSearchBoxOnlyMode ? 0 : 8);
    }

    private void updateHeight() {
        updateHeight(isSearchBoxPresent());
    }

    private void updateHeight(boolean searchBoxPresent) {
        LayoutParams lp = getLayoutParams();
        if (lp != null) {
            int newHeight = searchBoxPresent ? -2 : getToolbarHeight(getContext());
            int minHeight = searchBoxPresent ? 0 : getToolbarHeight(getContext());
            if (lp.height != newHeight) {
                lp.height = newHeight;
                setMinimumHeight(minHeight);
            }
        }
    }

    private void restoreChildrenVisibility() {
        if (this.mPreviousVisibility.size() != 0) {
            for (Entry<View, Integer> oneChildEntry : this.mPreviousVisibility.entrySet()) {
                if (oneChildEntry.getKey() != null) {
                    ((View) oneChildEntry.getKey()).setVisibility(((Integer) oneChildEntry.getValue()).intValue());
                }
            }
            this.mPreviousVisibility.clear();
        }
    }

    private void hideChildren() {
        View searchViewToDisplay = hasExpandedActionView() ? this.mActionView : this.mSearchView;
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            if (!(child == null || child == searchViewToDisplay || child.getVisibility() == 8)) {
                this.mPreviousVisibility.put(child, Integer.valueOf(child.getVisibility()));
                child.setVisibility(8);
            }
        }
    }

    private void setActionViewLayoutParams() {
        Toolbar.LayoutParams lp = (Toolbar.LayoutParams) this.mActionView.getLayoutParams();
        if (lp.width != -1) {
            lp.width = -1;
            this.mActionView.setLayoutParams(lp);
        }
    }

    public boolean onMenuItemActionExpand(MenuItem item) {
        if (supportsSearchBoxOnlyMode() && VERSION.SDK_INT >= 21) {
            View iconView = findViewById(item.getItemId());
            Rect iconBounds = new Rect();
            iconView.getGlobalVisibleRect(iconBounds);
            this.mActionView.setRevealCenter(new Point(iconBounds.centerX(), iconBounds.centerY()));
        }
        updateHeight(supportsSearchBoxOnlyMode());
        if (this.mActionBarController != null) {
            this.mActionBarController.enterActionBarSearchMode();
        }
        return true;
    }

    public boolean onMenuItemActionCollapse(MenuItem item) {
        if (!(this.mActionBarController == null || supportsSearchBoxOnlyMode())) {
            this.mActionBarController.exitActionBarSearchMode();
        }
        return true;
    }
}
