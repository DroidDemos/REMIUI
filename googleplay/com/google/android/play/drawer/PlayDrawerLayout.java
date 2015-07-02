package com.google.android.play.drawer;

import android.accounts.Account;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build.VERSION;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.DrawerLayout.DrawerListener;
import android.support.v4.widget.DrawerLayout.LayoutParams;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnApplyWindowInsetsListener;
import android.view.WindowInsets;
import android.widget.ListView;
import com.google.android.finsky.protos.DocumentV2.DocV2;
import com.google.android.play.R;
import com.google.android.play.dfe.api.PlayDfeApiProvider;
import com.google.android.play.image.BitmapLoader;
import com.google.android.play.utils.PlayCommonLog;
import com.google.android.play.utils.PlayUtils;
import java.util.List;

public class PlayDrawerLayout extends DrawerLayout implements DrawerListener {
    private PlayDrawerAdapter mDrawerAdapter;
    private ListView mDrawerList;
    private DrawerListener mDrawerListener;
    private float mDrawerSlideOffset;
    private int mDrawerState;
    private ActionBarDrawerToggle mDrawerToggle;
    private boolean mIsConfigured;

    public interface PlayDrawerContentClickListener {
        void onAccountListToggleButtonClicked(boolean z);

        boolean onCurrentAccountClicked(boolean z, DocV2 docV2);

        void onDownloadToggleClicked(boolean z);

        boolean onPrimaryActionClicked(PlayDrawerPrimaryAction playDrawerPrimaryAction);

        boolean onSecondaryAccountClicked(String str);

        boolean onSecondaryActionClicked(PlayDrawerSecondaryAction playDrawerSecondaryAction);
    }

    public static class PlayDrawerDownloadSwitchConfig {
        public final String actionText;
        public final int checkedTextColor;
        public final boolean isChecked;
        public final int thumbDrawableId;
        public final int trackDrawableId;
    }

    public static class PlayDrawerPrimaryAction {
        public final Runnable actionSelectedRunnable;
        public final String actionText;
        public final int activeIconResId;
        public final int activeTextColorResId;
        public final int iconResId;
        public final boolean isActive;
        public final boolean isAvailableInDownloadOnly;

        public PlayDrawerPrimaryAction(String actionText, int iconResId, int activeIconResId, int activeTextColorResId, boolean isActive, boolean isAvailableInDownloadOnly, Runnable actionSelectedRunnable) {
            this.actionText = actionText;
            this.iconResId = iconResId;
            this.activeIconResId = activeIconResId;
            this.activeTextColorResId = activeTextColorResId;
            this.isActive = isActive;
            this.isAvailableInDownloadOnly = isAvailableInDownloadOnly;
            this.actionSelectedRunnable = actionSelectedRunnable;
        }
    }

    public static class PlayDrawerSecondaryAction {
        public final Runnable actionSelectedRunnable;
        public final String actionText;

        public PlayDrawerSecondaryAction(String actionText, Runnable actionSelectedRunnable) {
            this.actionText = actionText;
            this.actionSelectedRunnable = actionSelectedRunnable;
        }
    }

    public PlayDrawerLayout(Context context) {
        this(context, null);
    }

    public PlayDrawerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        setDrawerShadow(R.drawable.drawer_shadow, PlayUtils.useLtr(context) ? 3 : 5);
        super.setDrawerListener(this);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mDrawerList = (ListView) findViewById(R.id.play_drawer_list);
        if (VERSION.SDK_INT >= 21) {
            this.mDrawerList.setOnApplyWindowInsetsListener(new OnApplyWindowInsetsListener() {
                public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
                    return windowInsets.consumeSystemWindowInsets();
                }
            });
        }
    }

    public void configure(Activity activity, boolean isAccountListExpanded, int actionBarHeight, PlayDfeApiProvider playDfeApiProvider, BitmapLoader bitmapLoader, PlayDrawerContentClickListener playDrawerContentClickListener) {
        configureNoToggle(activity, isAccountListExpanded, actionBarHeight, playDfeApiProvider, bitmapLoader, playDrawerContentClickListener);
        this.mDrawerToggle = new ActionBarDrawerToggle(activity, this, R.string.play_drawer_open, R.string.play_drawer_close);
    }

    public void configureNoToggle(Activity activity, boolean isAccountListExpanded, int actionBarHeight, PlayDfeApiProvider playDfeApiProvider, BitmapLoader bitmapLoader, PlayDrawerContentClickListener playDrawerContentClickListener) {
        if (this.mIsConfigured) {
            PlayCommonLog.wtf("PlayDrawer is already configured", new Object[0]);
        }
        this.mIsConfigured = true;
        setActionBarHeight(actionBarHeight);
        this.mDrawerAdapter = new PlayDrawerAdapter(activity, isAccountListExpanded, playDrawerContentClickListener, playDfeApiProvider, bitmapLoader, this, this.mDrawerList);
        this.mDrawerList.setAdapter(this.mDrawerAdapter);
        setDrawerTitle(8388611, activity.getString(R.string.play_drawer_title));
    }

    public void updateContent(String currentAccountName, Account[] accounts, List<PlayDrawerPrimaryAction> primaryActions, PlayDrawerDownloadSwitchConfig downloadSwitchConfig, List<PlayDrawerSecondaryAction> secondaryActions) {
        checkIsConfigured();
        this.mDrawerAdapter.updateContent(currentAccountName, accounts, primaryActions, downloadSwitchConfig, secondaryActions);
    }

    public void setActionBarHeight(int customActionBarHeight) {
        checkIsConfigured();
        Resources res = getResources();
        int screenWidth = res.getDisplayMetrics().widthPixels;
        LayoutParams lp = (LayoutParams) this.mDrawerList.getLayoutParams();
        lp.width = Math.min(res.getDimensionPixelSize(R.dimen.play_drawer_max_width), screenWidth - customActionBarHeight);
        this.mDrawerList.requestLayout();
    }

    public void setCurrentAvatarClickable(boolean clickable) {
        checkIsConfigured();
        this.mDrawerAdapter.setCurrentAvatarClickable(clickable);
    }

    private void checkIsConfigured() {
        if (!this.mIsConfigured) {
            PlayCommonLog.wtf("Play Drawer configure was not called", new Object[0]);
        }
    }

    public boolean isDrawerOpen() {
        checkIsConfigured();
        return isDrawerOpen(this.mDrawerList);
    }

    public void setDrawerToggle(ActionBarDrawerToggle toggle) {
        this.mDrawerToggle = toggle;
        if (this.mDrawerToggle != null) {
            this.mDrawerToggle.syncState();
        }
    }

    public void closeDrawer() {
        checkIsConfigured();
        if (isDrawerOpen(this.mDrawerList)) {
            closeDrawer(this.mDrawerList);
        }
    }

    public void openDrawer() {
        checkIsConfigured();
        if (!isDrawerOpen(this.mDrawerList)) {
            openDrawer(this.mDrawerList);
        }
    }

    public void setDrawerIndicatorEnabled(boolean enabled) {
        checkIsConfigured();
        if (this.mDrawerToggle != null) {
            this.mDrawerToggle.setDrawerIndicatorEnabled(enabled);
        }
    }

    public void syncDrawerIndicator() {
        checkIsConfigured();
        if (this.mDrawerToggle != null) {
            this.mDrawerToggle.syncState();
        }
    }

    public boolean isAccountListExpanded() {
        checkIsConfigured();
        return this.mDrawerAdapter.isAccountListExpanded();
    }

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (this.mIsConfigured && this.mDrawerToggle != null) {
            this.mDrawerToggle.onConfigurationChanged(newConfig);
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        checkIsConfigured();
        return this.mDrawerToggle != null && this.mDrawerToggle.onOptionsItemSelected(item);
    }

    public void onDrawerClosed(View drawerView) {
        if (this.mDrawerToggle != null) {
            this.mDrawerToggle.onDrawerClosed(drawerView);
        }
        this.mDrawerAdapter.collapseAccountListIfNeeded();
        if (this.mDrawerListener != null) {
            this.mDrawerListener.onDrawerClosed(drawerView);
        }
    }

    public void onDrawerOpened(View drawerView) {
        if (this.mDrawerToggle != null) {
            this.mDrawerToggle.onDrawerOpened(drawerView);
        }
        if (this.mDrawerListener != null) {
            this.mDrawerListener.onDrawerOpened(drawerView);
        }
    }

    public void onDrawerSlide(View drawerView, float slideOffset) {
        if (this.mDrawerState == 2 && slideOffset < this.mDrawerSlideOffset) {
            this.mDrawerAdapter.collapseAccountListIfNeeded();
        }
        this.mDrawerSlideOffset = slideOffset;
        if (this.mDrawerToggle != null) {
            this.mDrawerToggle.onDrawerSlide(drawerView, slideOffset);
        }
        if (this.mDrawerListener != null) {
            this.mDrawerListener.onDrawerSlide(drawerView, slideOffset);
        }
    }

    public void onDrawerStateChanged(int newState) {
        this.mDrawerState = newState;
        if (this.mDrawerToggle != null) {
            this.mDrawerToggle.onDrawerStateChanged(newState);
        }
        if (this.mDrawerListener != null) {
            this.mDrawerListener.onDrawerStateChanged(newState);
        }
    }

    public final void setDrawerListener(DrawerListener listener) {
        this.mDrawerListener = listener;
    }

    public boolean onInterceptTouchEvent(MotionEvent ev) {
        try {
            return super.onInterceptTouchEvent(ev);
        } catch (NullPointerException e) {
            return false;
        }
    }
}
