package android.support.v7.internal.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.support.v7.appcompat.R;
import android.support.v7.internal.app.WindowCallback;
import android.support.v7.internal.view.menu.ActionMenuItem;
import android.support.v7.internal.view.menu.MenuBuilder;
import android.support.v7.internal.view.menu.MenuPresenter.Callback;
import android.support.v7.widget.ActionMenuPresenter;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;

public class ToolbarWidgetWrapper implements DecorToolbar {
    private ActionMenuPresenter mActionMenuPresenter;
    private View mCustomView;
    private int mDefaultNavigationContentDescription;
    private Drawable mDefaultNavigationIcon;
    private int mDisplayOpts;
    private CharSequence mHomeDescription;
    private Drawable mIcon;
    private Drawable mLogo;
    private boolean mMenuPrepared;
    private Drawable mNavIcon;
    private int mNavigationMode;
    private CharSequence mSubtitle;
    private View mTabView;
    private final TintManager mTintManager;
    private CharSequence mTitle;
    private boolean mTitleSet;
    private Toolbar mToolbar;
    private WindowCallback mWindowCallback;

    public ToolbarWidgetWrapper(Toolbar toolbar, boolean style) {
        this(toolbar, style, R.string.abc_action_bar_up_description, R.drawable.abc_ic_ab_back_mtrl_am_alpha);
    }

    public ToolbarWidgetWrapper(Toolbar toolbar, boolean style, int defaultNavigationContentDescription, int defaultNavigationIcon) {
        this.mNavigationMode = 0;
        this.mDefaultNavigationContentDescription = 0;
        this.mToolbar = toolbar;
        this.mTitle = toolbar.getTitle();
        this.mSubtitle = toolbar.getSubtitle();
        this.mTitleSet = this.mTitle != null;
        this.mNavIcon = this.mToolbar.getNavigationIcon();
        if (style) {
            TintTypedArray a = TintTypedArray.obtainStyledAttributes(toolbar.getContext(), null, R.styleable.ActionBar, R.attr.actionBarStyle, 0);
            CharSequence title = a.getText(R.styleable.ActionBar_title);
            if (!TextUtils.isEmpty(title)) {
                setTitle(title);
            }
            CharSequence subtitle = a.getText(R.styleable.ActionBar_subtitle);
            if (!TextUtils.isEmpty(subtitle)) {
                setSubtitle(subtitle);
            }
            Drawable logo = a.getDrawable(R.styleable.ActionBar_logo);
            if (logo != null) {
                setLogo(logo);
            }
            Drawable icon = a.getDrawable(R.styleable.ActionBar_icon);
            if (icon != null) {
                setIcon(icon);
            }
            Drawable navIcon = a.getDrawable(R.styleable.ActionBar_homeAsUpIndicator);
            if (navIcon != null) {
                setNavigationIcon(navIcon);
            }
            setDisplayOptions(a.getInt(R.styleable.ActionBar_displayOptions, 0));
            int customNavId = a.getResourceId(R.styleable.ActionBar_customNavigationLayout, 0);
            if (customNavId != 0) {
                setCustomView(LayoutInflater.from(this.mToolbar.getContext()).inflate(customNavId, this.mToolbar, false));
                setDisplayOptions(this.mDisplayOpts | 16);
            }
            int height = a.getLayoutDimension(R.styleable.ActionBar_height, 0);
            if (height > 0) {
                LayoutParams lp = this.mToolbar.getLayoutParams();
                lp.height = height;
                this.mToolbar.setLayoutParams(lp);
            }
            int contentInsetStart = a.getDimensionPixelOffset(R.styleable.ActionBar_contentInsetStart, -1);
            int contentInsetEnd = a.getDimensionPixelOffset(R.styleable.ActionBar_contentInsetEnd, -1);
            if (contentInsetStart >= 0 || contentInsetEnd >= 0) {
                this.mToolbar.setContentInsetsRelative(Math.max(contentInsetStart, 0), Math.max(contentInsetEnd, 0));
            }
            int titleTextStyle = a.getResourceId(R.styleable.ActionBar_titleTextStyle, 0);
            if (titleTextStyle != 0) {
                this.mToolbar.setTitleTextAppearance(this.mToolbar.getContext(), titleTextStyle);
            }
            int subtitleTextStyle = a.getResourceId(R.styleable.ActionBar_subtitleTextStyle, 0);
            if (subtitleTextStyle != 0) {
                this.mToolbar.setSubtitleTextAppearance(this.mToolbar.getContext(), subtitleTextStyle);
            }
            int popupTheme = a.getResourceId(R.styleable.ActionBar_popupTheme, 0);
            if (popupTheme != 0) {
                this.mToolbar.setPopupTheme(popupTheme);
            }
            a.recycle();
            this.mTintManager = a.getTintManager();
        } else {
            this.mDisplayOpts = detectDisplayOptions();
            this.mTintManager = new TintManager(toolbar.getContext());
        }
        setDefaultNavigationContentDescription(defaultNavigationContentDescription);
        this.mHomeDescription = this.mToolbar.getNavigationContentDescription();
        setDefaultNavigationIcon(this.mTintManager.getDrawable(defaultNavigationIcon));
        this.mToolbar.setNavigationOnClickListener(new OnClickListener() {
            final ActionMenuItem mNavItem;

            {
                this.mNavItem = new ActionMenuItem(ToolbarWidgetWrapper.this.mToolbar.getContext(), 0, 16908332, 0, 0, ToolbarWidgetWrapper.this.mTitle);
            }

            public void onClick(View v) {
                if (ToolbarWidgetWrapper.this.mWindowCallback != null && ToolbarWidgetWrapper.this.mMenuPrepared) {
                    ToolbarWidgetWrapper.this.mWindowCallback.onMenuItemSelected(0, this.mNavItem);
                }
            }
        });
    }

    public void setDefaultNavigationContentDescription(int defaultNavigationContentDescription) {
        if (defaultNavigationContentDescription != this.mDefaultNavigationContentDescription) {
            this.mDefaultNavigationContentDescription = defaultNavigationContentDescription;
            if (TextUtils.isEmpty(this.mToolbar.getNavigationContentDescription())) {
                setNavigationContentDescription(this.mDefaultNavigationContentDescription);
            }
        }
    }

    public void setDefaultNavigationIcon(Drawable defaultNavigationIcon) {
        if (this.mDefaultNavigationIcon != defaultNavigationIcon) {
            this.mDefaultNavigationIcon = defaultNavigationIcon;
            updateNavigationIcon();
        }
    }

    private int detectDisplayOptions() {
        if (this.mToolbar.getNavigationIcon() != null) {
            return 11 | 4;
        }
        return 11;
    }

    public ViewGroup getViewGroup() {
        return this.mToolbar;
    }

    public Context getContext() {
        return this.mToolbar.getContext();
    }

    public boolean isSplit() {
        return false;
    }

    public boolean hasExpandedActionView() {
        return this.mToolbar.hasExpandedActionView();
    }

    public void collapseActionView() {
        this.mToolbar.collapseActionView();
    }

    public void setWindowCallback(WindowCallback cb) {
        this.mWindowCallback = cb;
    }

    public void setWindowTitle(CharSequence title) {
        if (!this.mTitleSet) {
            setTitleInt(title);
        }
    }

    public CharSequence getTitle() {
        return this.mToolbar.getTitle();
    }

    public void setTitle(CharSequence title) {
        this.mTitleSet = true;
        setTitleInt(title);
    }

    private void setTitleInt(CharSequence title) {
        this.mTitle = title;
        if ((this.mDisplayOpts & 8) != 0) {
            this.mToolbar.setTitle(title);
        }
    }

    public void setSubtitle(CharSequence subtitle) {
        this.mSubtitle = subtitle;
        if ((this.mDisplayOpts & 8) != 0) {
            this.mToolbar.setSubtitle(subtitle);
        }
    }

    public void initProgress() {
        Log.i("ToolbarWidgetWrapper", "Progress display unsupported");
    }

    public void initIndeterminateProgress() {
        Log.i("ToolbarWidgetWrapper", "Progress display unsupported");
    }

    public void setIcon(int resId) {
        setIcon(resId != 0 ? this.mTintManager.getDrawable(resId) : null);
    }

    public void setIcon(Drawable d) {
        this.mIcon = d;
        updateToolbarLogo();
    }

    public void setLogo(int resId) {
        setLogo(resId != 0 ? this.mTintManager.getDrawable(resId) : null);
    }

    public void setLogo(Drawable d) {
        this.mLogo = d;
        updateToolbarLogo();
    }

    private void updateToolbarLogo() {
        Drawable logo = null;
        if ((this.mDisplayOpts & 2) != 0) {
            logo = (this.mDisplayOpts & 1) != 0 ? this.mLogo != null ? this.mLogo : this.mIcon : this.mIcon;
        }
        this.mToolbar.setLogo(logo);
    }

    public boolean canShowOverflowMenu() {
        return this.mToolbar.canShowOverflowMenu();
    }

    public boolean isOverflowMenuShowing() {
        return this.mToolbar.isOverflowMenuShowing();
    }

    public boolean isOverflowMenuShowPending() {
        return this.mToolbar.isOverflowMenuShowPending();
    }

    public boolean showOverflowMenu() {
        return this.mToolbar.showOverflowMenu();
    }

    public boolean hideOverflowMenu() {
        return this.mToolbar.hideOverflowMenu();
    }

    public void setMenuPrepared() {
        this.mMenuPrepared = true;
    }

    public void setMenu(Menu menu, Callback cb) {
        if (this.mActionMenuPresenter == null) {
            this.mActionMenuPresenter = new ActionMenuPresenter(this.mToolbar.getContext());
            this.mActionMenuPresenter.setId(R.id.action_menu_presenter);
        }
        this.mActionMenuPresenter.setCallback(cb);
        this.mToolbar.setMenu((MenuBuilder) menu, this.mActionMenuPresenter);
    }

    public void dismissPopupMenus() {
        this.mToolbar.dismissPopupMenus();
    }

    public int getDisplayOptions() {
        return this.mDisplayOpts;
    }

    public void setDisplayOptions(int newOpts) {
        int changed = this.mDisplayOpts ^ newOpts;
        this.mDisplayOpts = newOpts;
        if (changed != 0) {
            if ((changed & 4) != 0) {
                if ((newOpts & 4) != 0) {
                    updateNavigationIcon();
                    updateHomeAccessibility();
                } else {
                    this.mToolbar.setNavigationIcon(null);
                }
            }
            if ((changed & 3) != 0) {
                updateToolbarLogo();
            }
            if ((changed & 8) != 0) {
                if ((newOpts & 8) != 0) {
                    this.mToolbar.setTitle(this.mTitle);
                    this.mToolbar.setSubtitle(this.mSubtitle);
                } else {
                    this.mToolbar.setTitle(null);
                    this.mToolbar.setSubtitle(null);
                }
            }
            if ((changed & 16) != 0 && this.mCustomView != null) {
                if ((newOpts & 16) != 0) {
                    this.mToolbar.addView(this.mCustomView);
                } else {
                    this.mToolbar.removeView(this.mCustomView);
                }
            }
        }
    }

    public void setEmbeddedTabView(ScrollingTabContainerView tabView) {
        if (this.mTabView != null && this.mTabView.getParent() == this.mToolbar) {
            this.mToolbar.removeView(this.mTabView);
        }
        this.mTabView = tabView;
        if (tabView != null && this.mNavigationMode == 2) {
            this.mToolbar.addView(this.mTabView, 0);
            Toolbar.LayoutParams lp = (Toolbar.LayoutParams) this.mTabView.getLayoutParams();
            lp.width = -2;
            lp.height = -2;
            lp.gravity = 8388691;
            tabView.setAllowCollapse(true);
        }
    }

    public void setCollapsible(boolean collapsible) {
        this.mToolbar.setCollapsible(collapsible);
    }

    public void setHomeButtonEnabled(boolean enable) {
    }

    public int getNavigationMode() {
        return this.mNavigationMode;
    }

    public void setCustomView(View view) {
        if (!(this.mCustomView == null || (this.mDisplayOpts & 16) == 0)) {
            this.mToolbar.removeView(this.mCustomView);
        }
        this.mCustomView = view;
        if (view != null && (this.mDisplayOpts & 16) != 0) {
            this.mToolbar.addView(this.mCustomView);
        }
    }

    public void animateToVisibility(int visibility) {
        if (visibility == 8) {
            ViewCompat.animate(this.mToolbar).alpha(0.0f).setListener(new ViewPropertyAnimatorListenerAdapter() {
                private boolean mCanceled;

                {
                    this.mCanceled = false;
                }

                public void onAnimationEnd(View view) {
                    if (!this.mCanceled) {
                        ToolbarWidgetWrapper.this.mToolbar.setVisibility(8);
                    }
                }

                public void onAnimationCancel(View view) {
                    this.mCanceled = true;
                }
            });
        } else if (visibility == 0) {
            ViewCompat.animate(this.mToolbar).alpha(1.0f).setListener(new ViewPropertyAnimatorListenerAdapter() {
                public void onAnimationStart(View view) {
                    ToolbarWidgetWrapper.this.mToolbar.setVisibility(0);
                }
            });
        }
    }

    public void setNavigationIcon(Drawable icon) {
        this.mNavIcon = icon;
        updateNavigationIcon();
    }

    public void setNavigationIcon(int resId) {
        setNavigationIcon(resId != 0 ? this.mTintManager.getDrawable(resId) : null);
    }

    public void setNavigationContentDescription(CharSequence description) {
        this.mHomeDescription = description;
        updateHomeAccessibility();
    }

    public void setNavigationContentDescription(int resId) {
        setNavigationContentDescription(resId == 0 ? null : getContext().getString(resId));
    }

    private void updateHomeAccessibility() {
        if ((this.mDisplayOpts & 4) == 0) {
            return;
        }
        if (TextUtils.isEmpty(this.mHomeDescription)) {
            this.mToolbar.setNavigationContentDescription(this.mDefaultNavigationContentDescription);
        } else {
            this.mToolbar.setNavigationContentDescription(this.mHomeDescription);
        }
    }

    private void updateNavigationIcon() {
        if ((this.mDisplayOpts & 4) != 0) {
            this.mToolbar.setNavigationIcon(this.mNavIcon != null ? this.mNavIcon : this.mDefaultNavigationIcon);
        }
    }

    public void setBackgroundDrawable(Drawable d) {
        this.mToolbar.setBackgroundDrawable(d);
    }

    public int getHeight() {
        return this.mToolbar.getHeight();
    }

    public void setMenuCallbacks(Callback actionMenuPresenterCallback, MenuBuilder.Callback menuBuilderCallback) {
        this.mToolbar.setMenuCallbacks(actionMenuPresenterCallback, menuBuilderCallback);
    }

    public Menu getMenu() {
        return this.mToolbar.getMenu();
    }
}
