package android.support.v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MarginLayoutParamsCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.R;
import android.support.v7.internal.view.SupportMenuInflater;
import android.support.v7.internal.view.menu.MenuBuilder;
import android.support.v7.internal.view.menu.MenuItemImpl;
import android.support.v7.internal.view.menu.MenuPresenter;
import android.support.v7.internal.view.menu.MenuPresenter.Callback;
import android.support.v7.internal.view.menu.SubMenuBuilder;
import android.support.v7.internal.widget.DecorToolbar;
import android.support.v7.internal.widget.RtlSpacingHelper;
import android.support.v7.internal.widget.TintManager;
import android.support.v7.internal.widget.TintTypedArray;
import android.support.v7.internal.widget.ToolbarWidgetWrapper;
import android.support.v7.internal.widget.ViewUtils;
import android.support.v7.view.CollapsibleActionView;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class Toolbar extends ViewGroup {
    private Callback mActionMenuPresenterCallback;
    private int mButtonGravity;
    private ImageButton mCollapseButtonView;
    private CharSequence mCollapseDescription;
    private Drawable mCollapseIcon;
    private boolean mCollapsible;
    private final RtlSpacingHelper mContentInsets;
    private boolean mEatingHover;
    private boolean mEatingTouch;
    View mExpandedActionView;
    private ExpandedActionViewMenuPresenter mExpandedMenuPresenter;
    private int mGravity;
    private ImageView mLogoView;
    private int mMaxButtonHeight;
    private MenuBuilder.Callback mMenuBuilderCallback;
    private ActionMenuView mMenuView;
    private final android.support.v7.widget.ActionMenuView.OnMenuItemClickListener mMenuViewItemClickListener;
    private int mMinHeight;
    private ImageButton mNavButtonView;
    private OnMenuItemClickListener mOnMenuItemClickListener;
    private ActionMenuPresenter mOuterActionMenuPresenter;
    private Context mPopupContext;
    private int mPopupTheme;
    private final Runnable mShowOverflowMenuRunnable;
    private CharSequence mSubtitleText;
    private int mSubtitleTextAppearance;
    private int mSubtitleTextColor;
    private TextView mSubtitleTextView;
    private final int[] mTempMargins;
    private final ArrayList<View> mTempViews;
    private final TintManager mTintManager;
    private int mTitleMarginBottom;
    private int mTitleMarginEnd;
    private int mTitleMarginStart;
    private int mTitleMarginTop;
    private CharSequence mTitleText;
    private int mTitleTextAppearance;
    private int mTitleTextColor;
    private TextView mTitleTextView;
    private ToolbarWidgetWrapper mWrapper;

    public interface OnMenuItemClickListener {
        boolean onMenuItemClick(MenuItem menuItem);
    }

    private class ExpandedActionViewMenuPresenter implements MenuPresenter {
        MenuItemImpl mCurrentExpandedItem;
        MenuBuilder mMenu;

        private ExpandedActionViewMenuPresenter() {
        }

        public void initForMenu(Context context, MenuBuilder menu) {
            if (!(this.mMenu == null || this.mCurrentExpandedItem == null)) {
                this.mMenu.collapseItemActionView(this.mCurrentExpandedItem);
            }
            this.mMenu = menu;
        }

        public void updateMenuView(boolean cleared) {
            if (this.mCurrentExpandedItem != null) {
                boolean found = false;
                if (this.mMenu != null) {
                    int count = this.mMenu.size();
                    for (int i = 0; i < count; i++) {
                        if (this.mMenu.getItem(i) == this.mCurrentExpandedItem) {
                            found = true;
                            break;
                        }
                    }
                }
                if (!found) {
                    collapseItemActionView(this.mMenu, this.mCurrentExpandedItem);
                }
            }
        }

        public boolean onSubMenuSelected(SubMenuBuilder subMenu) {
            return false;
        }

        public void onCloseMenu(MenuBuilder menu, boolean allMenusAreClosing) {
        }

        public boolean flagActionItems() {
            return false;
        }

        public boolean expandItemActionView(MenuBuilder menu, MenuItemImpl item) {
            Toolbar.this.ensureCollapseButtonView();
            if (Toolbar.this.mCollapseButtonView.getParent() != Toolbar.this) {
                Toolbar.this.addView(Toolbar.this.mCollapseButtonView);
            }
            Toolbar.this.mExpandedActionView = item.getActionView();
            this.mCurrentExpandedItem = item;
            if (Toolbar.this.mExpandedActionView.getParent() != Toolbar.this) {
                LayoutParams lp = Toolbar.this.generateDefaultLayoutParams();
                lp.gravity = 8388611 | (Toolbar.this.mButtonGravity & 112);
                lp.mViewType = 2;
                Toolbar.this.mExpandedActionView.setLayoutParams(lp);
                Toolbar.this.addView(Toolbar.this.mExpandedActionView);
            }
            Toolbar.this.setChildVisibilityForExpandedActionView(true);
            Toolbar.this.requestLayout();
            item.setActionViewExpanded(true);
            if (Toolbar.this.mExpandedActionView instanceof CollapsibleActionView) {
                ((CollapsibleActionView) Toolbar.this.mExpandedActionView).onActionViewExpanded();
            }
            return true;
        }

        public boolean collapseItemActionView(MenuBuilder menu, MenuItemImpl item) {
            if (Toolbar.this.mExpandedActionView instanceof CollapsibleActionView) {
                ((CollapsibleActionView) Toolbar.this.mExpandedActionView).onActionViewCollapsed();
            }
            Toolbar.this.removeView(Toolbar.this.mExpandedActionView);
            Toolbar.this.removeView(Toolbar.this.mCollapseButtonView);
            Toolbar.this.mExpandedActionView = null;
            Toolbar.this.setChildVisibilityForExpandedActionView(false);
            this.mCurrentExpandedItem = null;
            Toolbar.this.requestLayout();
            item.setActionViewExpanded(false);
            return true;
        }
    }

    public static class LayoutParams extends android.support.v7.app.ActionBar.LayoutParams {
        int mViewType;

        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
            this.mViewType = 0;
        }

        public LayoutParams(int width, int height) {
            super(width, height);
            this.mViewType = 0;
            this.gravity = 8388627;
        }

        public LayoutParams(LayoutParams source) {
            super((android.support.v7.app.ActionBar.LayoutParams) source);
            this.mViewType = 0;
            this.mViewType = source.mViewType;
        }

        public LayoutParams(android.support.v7.app.ActionBar.LayoutParams source) {
            super(source);
            this.mViewType = 0;
        }

        public LayoutParams(MarginLayoutParams source) {
            super((android.view.ViewGroup.LayoutParams) source);
            this.mViewType = 0;
            copyMarginsFromCompat(source);
        }

        public LayoutParams(android.view.ViewGroup.LayoutParams source) {
            super(source);
            this.mViewType = 0;
        }

        void copyMarginsFromCompat(MarginLayoutParams source) {
            this.leftMargin = source.leftMargin;
            this.topMargin = source.topMargin;
            this.rightMargin = source.rightMargin;
            this.bottomMargin = source.bottomMargin;
        }
    }

    static class SavedState extends BaseSavedState {
        public static final Creator<SavedState> CREATOR;
        public int expandedMenuItemId;
        public boolean isOverflowOpen;

        public SavedState(Parcel source) {
            super(source);
            this.expandedMenuItemId = source.readInt();
            this.isOverflowOpen = source.readInt() != 0;
        }

        public SavedState(Parcelable superState) {
            super(superState);
        }

        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeInt(this.expandedMenuItemId);
            out.writeInt(this.isOverflowOpen ? 1 : 0);
        }

        static {
            CREATOR = new Creator<SavedState>() {
                public SavedState createFromParcel(Parcel source) {
                    return new SavedState(source);
                }

                public SavedState[] newArray(int size) {
                    return new SavedState[size];
                }
            };
        }
    }

    public Toolbar(Context context) {
        this(context, null);
    }

    public Toolbar(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.toolbarStyle);
    }

    public Toolbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(themifyContext(context, attrs, defStyleAttr), attrs, defStyleAttr);
        this.mContentInsets = new RtlSpacingHelper();
        this.mGravity = 8388627;
        this.mTempViews = new ArrayList();
        this.mTempMargins = new int[2];
        this.mMenuViewItemClickListener = new android.support.v7.widget.ActionMenuView.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                if (Toolbar.this.mOnMenuItemClickListener != null) {
                    return Toolbar.this.mOnMenuItemClickListener.onMenuItemClick(item);
                }
                return false;
            }
        };
        this.mShowOverflowMenuRunnable = new Runnable() {
            public void run() {
                Toolbar.this.showOverflowMenu();
            }
        };
        TintTypedArray a = TintTypedArray.obtainStyledAttributes(getContext(), attrs, R.styleable.Toolbar, defStyleAttr, 0);
        this.mTitleTextAppearance = a.getResourceId(R.styleable.Toolbar_titleTextAppearance, 0);
        this.mSubtitleTextAppearance = a.getResourceId(R.styleable.Toolbar_subtitleTextAppearance, 0);
        this.mGravity = a.getInteger(R.styleable.Toolbar_android_gravity, this.mGravity);
        this.mButtonGravity = 48;
        int dimensionPixelOffset = a.getDimensionPixelOffset(R.styleable.Toolbar_titleMargins, 0);
        this.mTitleMarginBottom = dimensionPixelOffset;
        this.mTitleMarginTop = dimensionPixelOffset;
        this.mTitleMarginEnd = dimensionPixelOffset;
        this.mTitleMarginStart = dimensionPixelOffset;
        int marginStart = a.getDimensionPixelOffset(R.styleable.Toolbar_titleMarginStart, -1);
        if (marginStart >= 0) {
            this.mTitleMarginStart = marginStart;
        }
        int marginEnd = a.getDimensionPixelOffset(R.styleable.Toolbar_titleMarginEnd, -1);
        if (marginEnd >= 0) {
            this.mTitleMarginEnd = marginEnd;
        }
        int marginTop = a.getDimensionPixelOffset(R.styleable.Toolbar_titleMarginTop, -1);
        if (marginTop >= 0) {
            this.mTitleMarginTop = marginTop;
        }
        int marginBottom = a.getDimensionPixelOffset(R.styleable.Toolbar_titleMarginBottom, -1);
        if (marginBottom >= 0) {
            this.mTitleMarginBottom = marginBottom;
        }
        this.mMaxButtonHeight = a.getDimensionPixelSize(R.styleable.Toolbar_maxButtonHeight, -1);
        int contentInsetStart = a.getDimensionPixelOffset(R.styleable.Toolbar_contentInsetStart, Integer.MIN_VALUE);
        int contentInsetEnd = a.getDimensionPixelOffset(R.styleable.Toolbar_contentInsetEnd, Integer.MIN_VALUE);
        int contentInsetLeft = a.getDimensionPixelSize(R.styleable.Toolbar_contentInsetLeft, 0);
        int contentInsetRight = a.getDimensionPixelSize(R.styleable.Toolbar_contentInsetRight, 0);
        this.mContentInsets.setAbsolute(contentInsetLeft, contentInsetRight);
        if (!(contentInsetStart == Integer.MIN_VALUE && contentInsetEnd == Integer.MIN_VALUE)) {
            this.mContentInsets.setRelative(contentInsetStart, contentInsetEnd);
        }
        this.mCollapseIcon = a.getDrawable(R.styleable.Toolbar_collapseIcon);
        this.mCollapseDescription = a.getText(R.styleable.Toolbar_collapseContentDescription);
        CharSequence title = a.getText(R.styleable.Toolbar_title);
        if (!TextUtils.isEmpty(title)) {
            setTitle(title);
        }
        CharSequence subtitle = a.getText(R.styleable.Toolbar_subtitle);
        if (!TextUtils.isEmpty(subtitle)) {
            setSubtitle(subtitle);
        }
        this.mPopupContext = getContext();
        setPopupTheme(a.getResourceId(R.styleable.Toolbar_popupTheme, 0));
        Drawable navIcon = a.getDrawable(R.styleable.Toolbar_navigationIcon);
        if (navIcon != null) {
            setNavigationIcon(navIcon);
        }
        CharSequence navDesc = a.getText(R.styleable.Toolbar_navigationContentDescription);
        if (!TextUtils.isEmpty(navDesc)) {
            setNavigationContentDescription(navDesc);
        }
        this.mMinHeight = a.getDimensionPixelSize(R.styleable.Toolbar_android_minHeight, 0);
        a.recycle();
        this.mTintManager = a.getTintManager();
    }

    public void setPopupTheme(int resId) {
        if (this.mPopupTheme != resId) {
            this.mPopupTheme = resId;
            if (resId == 0) {
                this.mPopupContext = getContext();
            } else {
                this.mPopupContext = new ContextThemeWrapper(getContext(), resId);
            }
        }
    }

    public int getPopupTheme() {
        return this.mPopupTheme;
    }

    public void onRtlPropertiesChanged(int layoutDirection) {
        boolean z = true;
        if (VERSION.SDK_INT >= 17) {
            super.onRtlPropertiesChanged(layoutDirection);
        }
        RtlSpacingHelper rtlSpacingHelper = this.mContentInsets;
        if (layoutDirection != 1) {
            z = false;
        }
        rtlSpacingHelper.setDirection(z);
    }

    public void setLogo(int resId) {
        setLogo(this.mTintManager.getDrawable(resId));
    }

    public boolean canShowOverflowMenu() {
        return getVisibility() == 0 && this.mMenuView != null && this.mMenuView.isOverflowReserved();
    }

    public boolean isOverflowMenuShowing() {
        return this.mMenuView != null && this.mMenuView.isOverflowMenuShowing();
    }

    public boolean isOverflowMenuShowPending() {
        return this.mMenuView != null && this.mMenuView.isOverflowMenuShowPending();
    }

    public boolean showOverflowMenu() {
        return this.mMenuView != null && this.mMenuView.showOverflowMenu();
    }

    public boolean hideOverflowMenu() {
        return this.mMenuView != null && this.mMenuView.hideOverflowMenu();
    }

    public void setMenu(MenuBuilder menu, ActionMenuPresenter outerPresenter) {
        if (menu != null || this.mMenuView != null) {
            ensureMenuView();
            MenuBuilder oldMenu = this.mMenuView.peekMenu();
            if (oldMenu != menu) {
                if (oldMenu != null) {
                    oldMenu.removeMenuPresenter(this.mOuterActionMenuPresenter);
                    oldMenu.removeMenuPresenter(this.mExpandedMenuPresenter);
                }
                if (this.mExpandedMenuPresenter == null) {
                    this.mExpandedMenuPresenter = new ExpandedActionViewMenuPresenter();
                }
                outerPresenter.setExpandedActionViewsExclusive(true);
                if (menu != null) {
                    menu.addMenuPresenter(outerPresenter, this.mPopupContext);
                    menu.addMenuPresenter(this.mExpandedMenuPresenter, this.mPopupContext);
                } else {
                    outerPresenter.initForMenu(this.mPopupContext, null);
                    this.mExpandedMenuPresenter.initForMenu(this.mPopupContext, null);
                    outerPresenter.updateMenuView(true);
                    this.mExpandedMenuPresenter.updateMenuView(true);
                }
                this.mMenuView.setPopupTheme(this.mPopupTheme);
                this.mMenuView.setPresenter(outerPresenter);
                this.mOuterActionMenuPresenter = outerPresenter;
            }
        }
    }

    public void dismissPopupMenus() {
        if (this.mMenuView != null) {
            this.mMenuView.dismissPopupMenus();
        }
    }

    public void setLogo(Drawable drawable) {
        if (drawable != null) {
            ensureLogoView();
            if (this.mLogoView.getParent() == null) {
                addSystemView(this.mLogoView);
                updateChildVisibilityForExpandedActionView(this.mLogoView);
            }
        } else if (!(this.mLogoView == null || this.mLogoView.getParent() == null)) {
            removeView(this.mLogoView);
        }
        if (this.mLogoView != null) {
            this.mLogoView.setImageDrawable(drawable);
        }
    }

    public Drawable getLogo() {
        return this.mLogoView != null ? this.mLogoView.getDrawable() : null;
    }

    public void setLogoDescription(int resId) {
        setLogoDescription(getContext().getText(resId));
    }

    public void setLogoDescription(CharSequence description) {
        if (!TextUtils.isEmpty(description)) {
            ensureLogoView();
        }
        if (this.mLogoView != null) {
            this.mLogoView.setContentDescription(description);
        }
    }

    public CharSequence getLogoDescription() {
        return this.mLogoView != null ? this.mLogoView.getContentDescription() : null;
    }

    private void ensureLogoView() {
        if (this.mLogoView == null) {
            this.mLogoView = new ImageView(getContext());
        }
    }

    public boolean hasExpandedActionView() {
        return (this.mExpandedMenuPresenter == null || this.mExpandedMenuPresenter.mCurrentExpandedItem == null) ? false : true;
    }

    public void collapseActionView() {
        MenuItemImpl item = this.mExpandedMenuPresenter == null ? null : this.mExpandedMenuPresenter.mCurrentExpandedItem;
        if (item != null) {
            item.collapseActionView();
        }
    }

    public CharSequence getTitle() {
        return this.mTitleText;
    }

    public void setTitle(int resId) {
        setTitle(getContext().getText(resId));
    }

    public void setTitle(CharSequence title) {
        if (!TextUtils.isEmpty(title)) {
            if (this.mTitleTextView == null) {
                Context context = getContext();
                this.mTitleTextView = new TextView(context);
                this.mTitleTextView.setSingleLine();
                this.mTitleTextView.setEllipsize(TruncateAt.END);
                if (this.mTitleTextAppearance != 0) {
                    this.mTitleTextView.setTextAppearance(context, this.mTitleTextAppearance);
                }
                if (this.mTitleTextColor != 0) {
                    this.mTitleTextView.setTextColor(this.mTitleTextColor);
                }
            }
            if (this.mTitleTextView.getParent() == null) {
                addSystemView(this.mTitleTextView);
                updateChildVisibilityForExpandedActionView(this.mTitleTextView);
            }
        } else if (!(this.mTitleTextView == null || this.mTitleTextView.getParent() == null)) {
            removeView(this.mTitleTextView);
        }
        if (this.mTitleTextView != null) {
            this.mTitleTextView.setText(title);
        }
        this.mTitleText = title;
    }

    public CharSequence getSubtitle() {
        return this.mSubtitleText;
    }

    public void setSubtitle(int resId) {
        setSubtitle(getContext().getText(resId));
    }

    public void setSubtitle(CharSequence subtitle) {
        if (!TextUtils.isEmpty(subtitle)) {
            if (this.mSubtitleTextView == null) {
                Context context = getContext();
                this.mSubtitleTextView = new TextView(context);
                this.mSubtitleTextView.setSingleLine();
                this.mSubtitleTextView.setEllipsize(TruncateAt.END);
                if (this.mSubtitleTextAppearance != 0) {
                    this.mSubtitleTextView.setTextAppearance(context, this.mSubtitleTextAppearance);
                }
                if (this.mSubtitleTextColor != 0) {
                    this.mSubtitleTextView.setTextColor(this.mSubtitleTextColor);
                }
            }
            if (this.mSubtitleTextView.getParent() == null) {
                addSystemView(this.mSubtitleTextView);
                updateChildVisibilityForExpandedActionView(this.mSubtitleTextView);
            }
        } else if (!(this.mSubtitleTextView == null || this.mSubtitleTextView.getParent() == null)) {
            removeView(this.mSubtitleTextView);
        }
        if (this.mSubtitleTextView != null) {
            this.mSubtitleTextView.setText(subtitle);
        }
        this.mSubtitleText = subtitle;
    }

    public void setTitleTextAppearance(Context context, int resId) {
        this.mTitleTextAppearance = resId;
        if (this.mTitleTextView != null) {
            this.mTitleTextView.setTextAppearance(context, resId);
        }
    }

    public void setSubtitleTextAppearance(Context context, int resId) {
        this.mSubtitleTextAppearance = resId;
        if (this.mSubtitleTextView != null) {
            this.mSubtitleTextView.setTextAppearance(context, resId);
        }
    }

    public void setTitleTextColor(int color) {
        this.mTitleTextColor = color;
        if (this.mTitleTextView != null) {
            this.mTitleTextView.setTextColor(color);
        }
    }

    public void setSubtitleTextColor(int color) {
        this.mSubtitleTextColor = color;
        if (this.mSubtitleTextView != null) {
            this.mSubtitleTextView.setTextColor(color);
        }
    }

    public CharSequence getNavigationContentDescription() {
        return this.mNavButtonView != null ? this.mNavButtonView.getContentDescription() : null;
    }

    public void setNavigationContentDescription(int resId) {
        setNavigationContentDescription(resId != 0 ? getContext().getText(resId) : null);
    }

    public void setNavigationContentDescription(CharSequence description) {
        if (!TextUtils.isEmpty(description)) {
            ensureNavButtonView();
        }
        if (this.mNavButtonView != null) {
            this.mNavButtonView.setContentDescription(description);
        }
    }

    public void setNavigationIcon(int resId) {
        setNavigationIcon(this.mTintManager.getDrawable(resId));
    }

    public void setNavigationIcon(Drawable icon) {
        if (icon != null) {
            ensureNavButtonView();
            if (this.mNavButtonView.getParent() == null) {
                addSystemView(this.mNavButtonView);
                updateChildVisibilityForExpandedActionView(this.mNavButtonView);
            }
        } else if (!(this.mNavButtonView == null || this.mNavButtonView.getParent() == null)) {
            removeView(this.mNavButtonView);
        }
        if (this.mNavButtonView != null) {
            this.mNavButtonView.setImageDrawable(icon);
        }
    }

    public Drawable getNavigationIcon() {
        return this.mNavButtonView != null ? this.mNavButtonView.getDrawable() : null;
    }

    public void setNavigationOnClickListener(OnClickListener listener) {
        ensureNavButtonView();
        this.mNavButtonView.setOnClickListener(listener);
    }

    public Menu getMenu() {
        ensureMenu();
        return this.mMenuView.getMenu();
    }

    private void ensureMenu() {
        ensureMenuView();
        if (this.mMenuView.peekMenu() == null) {
            MenuBuilder menu = (MenuBuilder) this.mMenuView.getMenu();
            if (this.mExpandedMenuPresenter == null) {
                this.mExpandedMenuPresenter = new ExpandedActionViewMenuPresenter();
            }
            this.mMenuView.setExpandedActionViewsExclusive(true);
            menu.addMenuPresenter(this.mExpandedMenuPresenter, this.mPopupContext);
        }
    }

    private void ensureMenuView() {
        if (this.mMenuView == null) {
            this.mMenuView = new ActionMenuView(getContext());
            this.mMenuView.setPopupTheme(this.mPopupTheme);
            this.mMenuView.setOnMenuItemClickListener(this.mMenuViewItemClickListener);
            this.mMenuView.setMenuCallbacks(this.mActionMenuPresenterCallback, this.mMenuBuilderCallback);
            LayoutParams lp = generateDefaultLayoutParams();
            lp.gravity = 8388613 | (this.mButtonGravity & 112);
            this.mMenuView.setLayoutParams(lp);
            addSystemView(this.mMenuView);
        }
    }

    private MenuInflater getMenuInflater() {
        return new SupportMenuInflater(getContext());
    }

    public void setOnMenuItemClickListener(OnMenuItemClickListener listener) {
        this.mOnMenuItemClickListener = listener;
    }

    public void setContentInsetsRelative(int contentInsetStart, int contentInsetEnd) {
        this.mContentInsets.setRelative(contentInsetStart, contentInsetEnd);
    }

    public int getContentInsetStart() {
        return this.mContentInsets.getStart();
    }

    public int getContentInsetEnd() {
        return this.mContentInsets.getEnd();
    }

    public int getContentInsetLeft() {
        return this.mContentInsets.getLeft();
    }

    public int getContentInsetRight() {
        return this.mContentInsets.getRight();
    }

    private void ensureNavButtonView() {
        if (this.mNavButtonView == null) {
            this.mNavButtonView = new ImageButton(getContext(), null, R.attr.toolbarNavigationButtonStyle);
            LayoutParams lp = generateDefaultLayoutParams();
            lp.gravity = 8388611 | (this.mButtonGravity & 112);
            this.mNavButtonView.setLayoutParams(lp);
        }
    }

    private void ensureCollapseButtonView() {
        if (this.mCollapseButtonView == null) {
            this.mCollapseButtonView = new ImageButton(getContext(), null, R.attr.toolbarNavigationButtonStyle);
            this.mCollapseButtonView.setImageDrawable(this.mCollapseIcon);
            this.mCollapseButtonView.setContentDescription(this.mCollapseDescription);
            LayoutParams lp = generateDefaultLayoutParams();
            lp.gravity = 8388611 | (this.mButtonGravity & 112);
            lp.mViewType = 2;
            this.mCollapseButtonView.setLayoutParams(lp);
            this.mCollapseButtonView.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    Toolbar.this.collapseActionView();
                }
            });
        }
    }

    private void addSystemView(View v) {
        LayoutParams lp;
        android.view.ViewGroup.LayoutParams vlp = v.getLayoutParams();
        if (vlp == null) {
            lp = generateDefaultLayoutParams();
        } else if (checkLayoutParams(vlp)) {
            lp = (LayoutParams) vlp;
        } else {
            lp = generateLayoutParams(vlp);
        }
        lp.mViewType = 1;
        addView(v, lp);
    }

    protected Parcelable onSaveInstanceState() {
        SavedState state = new SavedState(super.onSaveInstanceState());
        if (!(this.mExpandedMenuPresenter == null || this.mExpandedMenuPresenter.mCurrentExpandedItem == null)) {
            state.expandedMenuItemId = this.mExpandedMenuPresenter.mCurrentExpandedItem.getItemId();
        }
        state.isOverflowOpen = isOverflowMenuShowing();
        return state;
    }

    protected void onRestoreInstanceState(Parcelable state) {
        SavedState ss = (SavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());
        Menu menu = this.mMenuView != null ? this.mMenuView.peekMenu() : null;
        if (!(ss.expandedMenuItemId == 0 || this.mExpandedMenuPresenter == null || menu == null)) {
            MenuItem item = menu.findItem(ss.expandedMenuItemId);
            if (item != null) {
                MenuItemCompat.expandActionView(item);
            }
        }
        if (ss.isOverflowOpen) {
            postShowOverflowMenu();
        }
    }

    private void postShowOverflowMenu() {
        removeCallbacks(this.mShowOverflowMenuRunnable);
        post(this.mShowOverflowMenuRunnable);
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeCallbacks(this.mShowOverflowMenuRunnable);
    }

    public boolean onTouchEvent(MotionEvent ev) {
        int action = MotionEventCompat.getActionMasked(ev);
        if (action == 0) {
            this.mEatingTouch = false;
        }
        if (!this.mEatingTouch) {
            boolean handled = super.onTouchEvent(ev);
            if (action == 0 && !handled) {
                this.mEatingTouch = true;
            }
        }
        if (action == 1 || action == 3) {
            this.mEatingTouch = false;
        }
        return true;
    }

    public boolean onHoverEvent(MotionEvent ev) {
        int action = MotionEventCompat.getActionMasked(ev);
        if (action == 9) {
            this.mEatingHover = false;
        }
        if (!this.mEatingHover) {
            boolean handled = super.onHoverEvent(ev);
            if (action == 9 && !handled) {
                this.mEatingHover = true;
            }
        }
        if (action == 10 || action == 3) {
            this.mEatingHover = false;
        }
        return true;
    }

    private void measureChildConstrained(View child, int parentWidthSpec, int widthUsed, int parentHeightSpec, int heightUsed, int heightConstraint) {
        MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
        int childWidthSpec = getChildMeasureSpec(parentWidthSpec, (((getPaddingLeft() + getPaddingRight()) + lp.leftMargin) + lp.rightMargin) + widthUsed, lp.width);
        int childHeightSpec = getChildMeasureSpec(parentHeightSpec, (((getPaddingTop() + getPaddingBottom()) + lp.topMargin) + lp.bottomMargin) + heightUsed, lp.height);
        int childHeightMode = MeasureSpec.getMode(childHeightSpec);
        if (childHeightMode != 1073741824 && heightConstraint >= 0) {
            childHeightSpec = MeasureSpec.makeMeasureSpec(childHeightMode != 0 ? Math.min(MeasureSpec.getSize(childHeightSpec), heightConstraint) : heightConstraint, 1073741824);
        }
        child.measure(childWidthSpec, childHeightSpec);
    }

    private int measureChildCollapseMargins(View child, int parentWidthMeasureSpec, int widthUsed, int parentHeightMeasureSpec, int heightUsed, int[] collapsingMargins) {
        MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
        int leftDiff = lp.leftMargin - collapsingMargins[0];
        int rightDiff = lp.rightMargin - collapsingMargins[1];
        int hMargins = Math.max(0, leftDiff) + Math.max(0, rightDiff);
        collapsingMargins[0] = Math.max(0, -leftDiff);
        collapsingMargins[1] = Math.max(0, -rightDiff);
        child.measure(getChildMeasureSpec(parentWidthMeasureSpec, ((getPaddingLeft() + getPaddingRight()) + hMargins) + widthUsed, lp.width), getChildMeasureSpec(parentHeightMeasureSpec, (((getPaddingTop() + getPaddingBottom()) + lp.topMargin) + lp.bottomMargin) + heightUsed, lp.height));
        return child.getMeasuredWidth() + hMargins;
    }

    private boolean shouldCollapse() {
        if (!this.mCollapsible) {
            return false;
        }
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            if (shouldLayout(child) && child.getMeasuredWidth() > 0 && child.getMeasuredHeight() > 0) {
                return false;
            }
        }
        return true;
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int marginStartIndex;
        int marginEndIndex;
        int i;
        int height = 0;
        int childState = 0;
        int[] collapsingMargins = this.mTempMargins;
        if (ViewUtils.isLayoutRtl(this)) {
            marginStartIndex = 1;
            marginEndIndex = 0;
        } else {
            marginStartIndex = 0;
            marginEndIndex = 1;
        }
        int navWidth = 0;
        if (shouldLayout(this.mNavButtonView)) {
            measureChildConstrained(this.mNavButtonView, widthMeasureSpec, 0, heightMeasureSpec, 0, this.mMaxButtonHeight);
            navWidth = this.mNavButtonView.getMeasuredWidth() + getHorizontalMargins(this.mNavButtonView);
            i = 0;
            height = Math.max(i, this.mNavButtonView.getMeasuredHeight() + getVerticalMargins(this.mNavButtonView));
            childState = ViewUtils.combineMeasuredStates(0, ViewCompat.getMeasuredState(this.mNavButtonView));
        }
        if (shouldLayout(this.mCollapseButtonView)) {
            measureChildConstrained(this.mCollapseButtonView, widthMeasureSpec, 0, heightMeasureSpec, 0, this.mMaxButtonHeight);
            navWidth = this.mCollapseButtonView.getMeasuredWidth() + getHorizontalMargins(this.mCollapseButtonView);
            i = height;
            height = Math.max(i, this.mCollapseButtonView.getMeasuredHeight() + getVerticalMargins(this.mCollapseButtonView));
            childState = ViewUtils.combineMeasuredStates(childState, ViewCompat.getMeasuredState(this.mCollapseButtonView));
        }
        int contentInsetStart = getContentInsetStart();
        int width = 0 + Math.max(contentInsetStart, navWidth);
        collapsingMargins[marginStartIndex] = Math.max(0, contentInsetStart - navWidth);
        int menuWidth = 0;
        if (shouldLayout(this.mMenuView)) {
            measureChildConstrained(this.mMenuView, widthMeasureSpec, width, heightMeasureSpec, 0, this.mMaxButtonHeight);
            menuWidth = this.mMenuView.getMeasuredWidth() + getHorizontalMargins(this.mMenuView);
            i = height;
            height = Math.max(i, this.mMenuView.getMeasuredHeight() + getVerticalMargins(this.mMenuView));
            childState = ViewUtils.combineMeasuredStates(childState, ViewCompat.getMeasuredState(this.mMenuView));
        }
        int contentInsetEnd = getContentInsetEnd();
        width += Math.max(contentInsetEnd, menuWidth);
        collapsingMargins[marginEndIndex] = Math.max(0, contentInsetEnd - menuWidth);
        if (shouldLayout(this.mExpandedActionView)) {
            width += measureChildCollapseMargins(this.mExpandedActionView, widthMeasureSpec, width, heightMeasureSpec, 0, collapsingMargins);
            i = height;
            height = Math.max(i, this.mExpandedActionView.getMeasuredHeight() + getVerticalMargins(this.mExpandedActionView));
            childState = ViewUtils.combineMeasuredStates(childState, ViewCompat.getMeasuredState(this.mExpandedActionView));
        }
        if (shouldLayout(this.mLogoView)) {
            width += measureChildCollapseMargins(this.mLogoView, widthMeasureSpec, width, heightMeasureSpec, 0, collapsingMargins);
            i = height;
            height = Math.max(i, this.mLogoView.getMeasuredHeight() + getVerticalMargins(this.mLogoView));
            childState = ViewUtils.combineMeasuredStates(childState, ViewCompat.getMeasuredState(this.mLogoView));
        }
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View child = getChildAt(i2);
            if (((LayoutParams) child.getLayoutParams()).mViewType == 0 && shouldLayout(child)) {
                width += measureChildCollapseMargins(child, widthMeasureSpec, width, heightMeasureSpec, 0, collapsingMargins);
                height = Math.max(height, child.getMeasuredHeight() + getVerticalMargins(child));
                childState = ViewUtils.combineMeasuredStates(childState, ViewCompat.getMeasuredState(child));
            }
        }
        int titleWidth = 0;
        int titleHeight = 0;
        int titleVertMargins = this.mTitleMarginTop + this.mTitleMarginBottom;
        int titleHorizMargins = this.mTitleMarginStart + this.mTitleMarginEnd;
        if (shouldLayout(this.mTitleTextView)) {
            titleWidth = measureChildCollapseMargins(this.mTitleTextView, widthMeasureSpec, width + titleHorizMargins, heightMeasureSpec, titleVertMargins, collapsingMargins);
            titleWidth = this.mTitleTextView.getMeasuredWidth() + getHorizontalMargins(this.mTitleTextView);
            titleHeight = this.mTitleTextView.getMeasuredHeight() + getVerticalMargins(this.mTitleTextView);
            childState = ViewUtils.combineMeasuredStates(childState, ViewCompat.getMeasuredState(this.mTitleTextView));
        }
        if (shouldLayout(this.mSubtitleTextView)) {
            i = titleWidth;
            titleWidth = Math.max(i, measureChildCollapseMargins(this.mSubtitleTextView, widthMeasureSpec, width + titleHorizMargins, heightMeasureSpec, titleHeight + titleVertMargins, collapsingMargins));
            titleHeight += this.mSubtitleTextView.getMeasuredHeight() + getVerticalMargins(this.mSubtitleTextView);
            childState = ViewUtils.combineMeasuredStates(childState, ViewCompat.getMeasuredState(this.mSubtitleTextView));
        }
        width += titleWidth;
        height = Math.max(height, titleHeight) + (getPaddingTop() + getPaddingBottom());
        i = widthMeasureSpec;
        int measuredWidth = ViewCompat.resolveSizeAndState(Math.max(width + (getPaddingLeft() + getPaddingRight()), getSuggestedMinimumWidth()), i, -16777216 & childState);
        i = heightMeasureSpec;
        int measuredHeight = ViewCompat.resolveSizeAndState(Math.max(height, getSuggestedMinimumHeight()), i, childState << 16);
        if (shouldCollapse()) {
            measuredHeight = 0;
        }
        setMeasuredDimension(measuredWidth, measuredHeight);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected void onLayout(boolean r51, int r52, int r53, int r54, int r55) {
        /*
        r50 = this;
        r46 = android.support.v4.view.ViewCompat.getLayoutDirection(r50);
        r47 = 1;
        r0 = r46;
        r1 = r47;
        if (r0 != r1) goto L_0x0319;
    L_0x000c:
        r16 = 1;
    L_0x000e:
        r45 = r50.getWidth();
        r14 = r50.getHeight();
        r24 = r50.getPaddingLeft();
        r25 = r50.getPaddingRight();
        r26 = r50.getPaddingTop();
        r23 = r50.getPaddingBottom();
        r20 = r24;
        r29 = r45 - r25;
        r0 = r50;
        r12 = r0.mTempMargins;
        r46 = 0;
        r47 = 1;
        r48 = 0;
        r12[r47] = r48;
        r12[r46] = r48;
        r5 = r50.getMinimumHeightCompat();
        r0 = r50;
        r0 = r0.mNavButtonView;
        r46 = r0;
        r0 = r50;
        r1 = r46;
        r46 = r0.shouldLayout(r1);
        if (r46 == 0) goto L_0x005e;
    L_0x004c:
        if (r16 == 0) goto L_0x031d;
    L_0x004e:
        r0 = r50;
        r0 = r0.mNavButtonView;
        r46 = r0;
        r0 = r50;
        r1 = r46;
        r2 = r29;
        r29 = r0.layoutChildRight(r1, r2, r12, r5);
    L_0x005e:
        r0 = r50;
        r0 = r0.mCollapseButtonView;
        r46 = r0;
        r0 = r50;
        r1 = r46;
        r46 = r0.shouldLayout(r1);
        if (r46 == 0) goto L_0x0080;
    L_0x006e:
        if (r16 == 0) goto L_0x032f;
    L_0x0070:
        r0 = r50;
        r0 = r0.mCollapseButtonView;
        r46 = r0;
        r0 = r50;
        r1 = r46;
        r2 = r29;
        r29 = r0.layoutChildRight(r1, r2, r12, r5);
    L_0x0080:
        r0 = r50;
        r0 = r0.mMenuView;
        r46 = r0;
        r0 = r50;
        r1 = r46;
        r46 = r0.shouldLayout(r1);
        if (r46 == 0) goto L_0x00a2;
    L_0x0090:
        if (r16 == 0) goto L_0x0341;
    L_0x0092:
        r0 = r50;
        r0 = r0.mMenuView;
        r46 = r0;
        r0 = r50;
        r1 = r46;
        r2 = r20;
        r20 = r0.layoutChildLeft(r1, r2, r12, r5);
    L_0x00a2:
        r46 = 0;
        r47 = 0;
        r48 = r50.getContentInsetLeft();
        r48 = r48 - r20;
        r47 = java.lang.Math.max(r47, r48);
        r12[r46] = r47;
        r46 = 1;
        r47 = 0;
        r48 = r50.getContentInsetRight();
        r49 = r45 - r25;
        r49 = r49 - r29;
        r48 = r48 - r49;
        r47 = java.lang.Math.max(r47, r48);
        r12[r46] = r47;
        r46 = r50.getContentInsetLeft();
        r0 = r20;
        r1 = r46;
        r20 = java.lang.Math.max(r0, r1);
        r46 = r45 - r25;
        r47 = r50.getContentInsetRight();
        r46 = r46 - r47;
        r0 = r29;
        r1 = r46;
        r29 = java.lang.Math.min(r0, r1);
        r0 = r50;
        r0 = r0.mExpandedActionView;
        r46 = r0;
        r0 = r50;
        r1 = r46;
        r46 = r0.shouldLayout(r1);
        if (r46 == 0) goto L_0x0104;
    L_0x00f2:
        if (r16 == 0) goto L_0x0353;
    L_0x00f4:
        r0 = r50;
        r0 = r0.mExpandedActionView;
        r46 = r0;
        r0 = r50;
        r1 = r46;
        r2 = r29;
        r29 = r0.layoutChildRight(r1, r2, r12, r5);
    L_0x0104:
        r0 = r50;
        r0 = r0.mLogoView;
        r46 = r0;
        r0 = r50;
        r1 = r46;
        r46 = r0.shouldLayout(r1);
        if (r46 == 0) goto L_0x0126;
    L_0x0114:
        if (r16 == 0) goto L_0x0365;
    L_0x0116:
        r0 = r50;
        r0 = r0.mLogoView;
        r46 = r0;
        r0 = r50;
        r1 = r46;
        r2 = r29;
        r29 = r0.layoutChildRight(r1, r2, r12, r5);
    L_0x0126:
        r0 = r50;
        r0 = r0.mTitleTextView;
        r46 = r0;
        r0 = r50;
        r1 = r46;
        r18 = r0.shouldLayout(r1);
        r0 = r50;
        r0 = r0.mSubtitleTextView;
        r46 = r0;
        r0 = r50;
        r1 = r46;
        r17 = r0.shouldLayout(r1);
        r39 = 0;
        if (r18 == 0) goto L_0x016e;
    L_0x0146:
        r0 = r50;
        r0 = r0.mTitleTextView;
        r46 = r0;
        r22 = r46.getLayoutParams();
        r22 = (android.support.v7.widget.Toolbar.LayoutParams) r22;
        r0 = r22;
        r0 = r0.topMargin;
        r46 = r0;
        r0 = r50;
        r0 = r0.mTitleTextView;
        r47 = r0;
        r47 = r47.getMeasuredHeight();
        r46 = r46 + r47;
        r0 = r22;
        r0 = r0.bottomMargin;
        r47 = r0;
        r46 = r46 + r47;
        r39 = r39 + r46;
    L_0x016e:
        if (r17 == 0) goto L_0x0198;
    L_0x0170:
        r0 = r50;
        r0 = r0.mSubtitleTextView;
        r46 = r0;
        r22 = r46.getLayoutParams();
        r22 = (android.support.v7.widget.Toolbar.LayoutParams) r22;
        r0 = r22;
        r0 = r0.topMargin;
        r46 = r0;
        r0 = r50;
        r0 = r0.mSubtitleTextView;
        r47 = r0;
        r47 = r47.getMeasuredHeight();
        r46 = r46 + r47;
        r0 = r22;
        r0 = r0.bottomMargin;
        r47 = r0;
        r46 = r46 + r47;
        r39 = r39 + r46;
    L_0x0198:
        if (r18 != 0) goto L_0x019c;
    L_0x019a:
        if (r17 == 0) goto L_0x02de;
    L_0x019c:
        if (r18 == 0) goto L_0x0377;
    L_0x019e:
        r0 = r50;
        r0 = r0.mTitleTextView;
        r43 = r0;
    L_0x01a4:
        if (r17 == 0) goto L_0x037f;
    L_0x01a6:
        r0 = r50;
        r6 = r0.mSubtitleTextView;
    L_0x01aa:
        r44 = r43.getLayoutParams();
        r44 = (android.support.v7.widget.Toolbar.LayoutParams) r44;
        r7 = r6.getLayoutParams();
        r7 = (android.support.v7.widget.Toolbar.LayoutParams) r7;
        if (r18 == 0) goto L_0x01c4;
    L_0x01b8:
        r0 = r50;
        r0 = r0.mTitleTextView;
        r46 = r0;
        r46 = r46.getMeasuredWidth();
        if (r46 > 0) goto L_0x01d2;
    L_0x01c4:
        if (r17 == 0) goto L_0x0385;
    L_0x01c6:
        r0 = r50;
        r0 = r0.mSubtitleTextView;
        r46 = r0;
        r46 = r46.getMeasuredWidth();
        if (r46 <= 0) goto L_0x0385;
    L_0x01d2:
        r38 = 1;
    L_0x01d4:
        r0 = r50;
        r0 = r0.mGravity;
        r46 = r0;
        r46 = r46 & 112;
        switch(r46) {
            case 48: goto L_0x0389;
            case 80: goto L_0x03d3;
            default: goto L_0x01df;
        };
    L_0x01df:
        r46 = r14 - r26;
        r31 = r46 - r23;
        r46 = r31 - r39;
        r32 = r46 / 2;
        r0 = r44;
        r0 = r0.topMargin;
        r46 = r0;
        r0 = r50;
        r0 = r0.mTitleMarginTop;
        r47 = r0;
        r46 = r46 + r47;
        r0 = r32;
        r1 = r46;
        if (r0 >= r1) goto L_0x039f;
    L_0x01fb:
        r0 = r44;
        r0 = r0.topMargin;
        r46 = r0;
        r0 = r50;
        r0 = r0.mTitleMarginTop;
        r47 = r0;
        r32 = r46 + r47;
    L_0x0209:
        r42 = r26 + r32;
    L_0x020b:
        if (r16 == 0) goto L_0x03eb;
    L_0x020d:
        if (r38 == 0) goto L_0x03e7;
    L_0x020f:
        r0 = r50;
        r0 = r0.mTitleMarginStart;
        r46 = r0;
    L_0x0215:
        r47 = 1;
        r47 = r12[r47];
        r28 = r46 - r47;
        r46 = 0;
        r0 = r46;
        r1 = r28;
        r46 = java.lang.Math.max(r0, r1);
        r29 = r29 - r46;
        r46 = 1;
        r47 = 0;
        r0 = r28;
        r0 = -r0;
        r48 = r0;
        r47 = java.lang.Math.max(r47, r48);
        r12[r46] = r47;
        r41 = r29;
        r36 = r29;
        if (r18 == 0) goto L_0x0283;
    L_0x023c:
        r0 = r50;
        r0 = r0.mTitleTextView;
        r46 = r0;
        r22 = r46.getLayoutParams();
        r22 = (android.support.v7.widget.Toolbar.LayoutParams) r22;
        r0 = r50;
        r0 = r0.mTitleTextView;
        r46 = r0;
        r46 = r46.getMeasuredWidth();
        r40 = r41 - r46;
        r0 = r50;
        r0 = r0.mTitleTextView;
        r46 = r0;
        r46 = r46.getMeasuredHeight();
        r37 = r42 + r46;
        r0 = r50;
        r0 = r0.mTitleTextView;
        r46 = r0;
        r0 = r46;
        r1 = r40;
        r2 = r42;
        r3 = r41;
        r4 = r37;
        r0.layout(r1, r2, r3, r4);
        r0 = r50;
        r0 = r0.mTitleMarginEnd;
        r46 = r0;
        r41 = r40 - r46;
        r0 = r22;
        r0 = r0.bottomMargin;
        r46 = r0;
        r42 = r37 + r46;
    L_0x0283:
        if (r17 == 0) goto L_0x02d4;
    L_0x0285:
        r0 = r50;
        r0 = r0.mSubtitleTextView;
        r46 = r0;
        r22 = r46.getLayoutParams();
        r22 = (android.support.v7.widget.Toolbar.LayoutParams) r22;
        r0 = r22;
        r0 = r0.topMargin;
        r46 = r0;
        r42 = r42 + r46;
        r0 = r50;
        r0 = r0.mSubtitleTextView;
        r46 = r0;
        r46 = r46.getMeasuredWidth();
        r35 = r36 - r46;
        r0 = r50;
        r0 = r0.mSubtitleTextView;
        r46 = r0;
        r46 = r46.getMeasuredHeight();
        r34 = r42 + r46;
        r0 = r50;
        r0 = r0.mSubtitleTextView;
        r46 = r0;
        r0 = r46;
        r1 = r35;
        r2 = r42;
        r3 = r36;
        r4 = r34;
        r0.layout(r1, r2, r3, r4);
        r0 = r50;
        r0 = r0.mTitleMarginEnd;
        r46 = r0;
        r36 = r36 - r46;
        r0 = r22;
        r0 = r0.bottomMargin;
        r46 = r0;
        r42 = r34 + r46;
    L_0x02d4:
        if (r38 == 0) goto L_0x02de;
    L_0x02d6:
        r0 = r41;
        r1 = r36;
        r29 = java.lang.Math.min(r0, r1);
    L_0x02de:
        r0 = r50;
        r0 = r0.mTempViews;
        r46 = r0;
        r47 = 3;
        r0 = r50;
        r1 = r46;
        r2 = r47;
        r0.addCustomViewsWithGravity(r1, r2);
        r0 = r50;
        r0 = r0.mTempViews;
        r46 = r0;
        r21 = r46.size();
        r15 = 0;
    L_0x02fa:
        r0 = r21;
        if (r15 >= r0) goto L_0x04c2;
    L_0x02fe:
        r0 = r50;
        r0 = r0.mTempViews;
        r46 = r0;
        r0 = r46;
        r46 = r0.get(r15);
        r46 = (android.view.View) r46;
        r0 = r50;
        r1 = r46;
        r2 = r20;
        r20 = r0.layoutChildLeft(r1, r2, r12, r5);
        r15 = r15 + 1;
        goto L_0x02fa;
    L_0x0319:
        r16 = 0;
        goto L_0x000e;
    L_0x031d:
        r0 = r50;
        r0 = r0.mNavButtonView;
        r46 = r0;
        r0 = r50;
        r1 = r46;
        r2 = r20;
        r20 = r0.layoutChildLeft(r1, r2, r12, r5);
        goto L_0x005e;
    L_0x032f:
        r0 = r50;
        r0 = r0.mCollapseButtonView;
        r46 = r0;
        r0 = r50;
        r1 = r46;
        r2 = r20;
        r20 = r0.layoutChildLeft(r1, r2, r12, r5);
        goto L_0x0080;
    L_0x0341:
        r0 = r50;
        r0 = r0.mMenuView;
        r46 = r0;
        r0 = r50;
        r1 = r46;
        r2 = r29;
        r29 = r0.layoutChildRight(r1, r2, r12, r5);
        goto L_0x00a2;
    L_0x0353:
        r0 = r50;
        r0 = r0.mExpandedActionView;
        r46 = r0;
        r0 = r50;
        r1 = r46;
        r2 = r20;
        r20 = r0.layoutChildLeft(r1, r2, r12, r5);
        goto L_0x0104;
    L_0x0365:
        r0 = r50;
        r0 = r0.mLogoView;
        r46 = r0;
        r0 = r50;
        r1 = r46;
        r2 = r20;
        r20 = r0.layoutChildLeft(r1, r2, r12, r5);
        goto L_0x0126;
    L_0x0377:
        r0 = r50;
        r0 = r0.mSubtitleTextView;
        r43 = r0;
        goto L_0x01a4;
    L_0x037f:
        r0 = r50;
        r6 = r0.mTitleTextView;
        goto L_0x01aa;
    L_0x0385:
        r38 = 0;
        goto L_0x01d4;
    L_0x0389:
        r46 = r50.getPaddingTop();
        r0 = r44;
        r0 = r0.topMargin;
        r47 = r0;
        r46 = r46 + r47;
        r0 = r50;
        r0 = r0.mTitleMarginTop;
        r47 = r0;
        r42 = r46 + r47;
        goto L_0x020b;
    L_0x039f:
        r46 = r14 - r23;
        r46 = r46 - r39;
        r46 = r46 - r32;
        r33 = r46 - r26;
        r0 = r44;
        r0 = r0.bottomMargin;
        r46 = r0;
        r0 = r50;
        r0 = r0.mTitleMarginBottom;
        r47 = r0;
        r46 = r46 + r47;
        r0 = r33;
        r1 = r46;
        if (r0 >= r1) goto L_0x0209;
    L_0x03bb:
        r46 = 0;
        r0 = r7.bottomMargin;
        r47 = r0;
        r0 = r50;
        r0 = r0.mTitleMarginBottom;
        r48 = r0;
        r47 = r47 + r48;
        r47 = r47 - r33;
        r47 = r32 - r47;
        r32 = java.lang.Math.max(r46, r47);
        goto L_0x0209;
    L_0x03d3:
        r46 = r14 - r23;
        r0 = r7.bottomMargin;
        r47 = r0;
        r46 = r46 - r47;
        r0 = r50;
        r0 = r0.mTitleMarginBottom;
        r47 = r0;
        r46 = r46 - r47;
        r42 = r46 - r39;
        goto L_0x020b;
    L_0x03e7:
        r46 = 0;
        goto L_0x0215;
    L_0x03eb:
        if (r38 == 0) goto L_0x04be;
    L_0x03ed:
        r0 = r50;
        r0 = r0.mTitleMarginStart;
        r46 = r0;
    L_0x03f3:
        r47 = 0;
        r47 = r12[r47];
        r19 = r46 - r47;
        r46 = 0;
        r0 = r46;
        r1 = r19;
        r46 = java.lang.Math.max(r0, r1);
        r20 = r20 + r46;
        r46 = 0;
        r47 = 0;
        r0 = r19;
        r0 = -r0;
        r48 = r0;
        r47 = java.lang.Math.max(r47, r48);
        r12[r46] = r47;
        r40 = r20;
        r35 = r20;
        if (r18 == 0) goto L_0x0461;
    L_0x041a:
        r0 = r50;
        r0 = r0.mTitleTextView;
        r46 = r0;
        r22 = r46.getLayoutParams();
        r22 = (android.support.v7.widget.Toolbar.LayoutParams) r22;
        r0 = r50;
        r0 = r0.mTitleTextView;
        r46 = r0;
        r46 = r46.getMeasuredWidth();
        r41 = r40 + r46;
        r0 = r50;
        r0 = r0.mTitleTextView;
        r46 = r0;
        r46 = r46.getMeasuredHeight();
        r37 = r42 + r46;
        r0 = r50;
        r0 = r0.mTitleTextView;
        r46 = r0;
        r0 = r46;
        r1 = r40;
        r2 = r42;
        r3 = r41;
        r4 = r37;
        r0.layout(r1, r2, r3, r4);
        r0 = r50;
        r0 = r0.mTitleMarginEnd;
        r46 = r0;
        r40 = r41 + r46;
        r0 = r22;
        r0 = r0.bottomMargin;
        r46 = r0;
        r42 = r37 + r46;
    L_0x0461:
        if (r17 == 0) goto L_0x04b2;
    L_0x0463:
        r0 = r50;
        r0 = r0.mSubtitleTextView;
        r46 = r0;
        r22 = r46.getLayoutParams();
        r22 = (android.support.v7.widget.Toolbar.LayoutParams) r22;
        r0 = r22;
        r0 = r0.topMargin;
        r46 = r0;
        r42 = r42 + r46;
        r0 = r50;
        r0 = r0.mSubtitleTextView;
        r46 = r0;
        r46 = r46.getMeasuredWidth();
        r36 = r35 + r46;
        r0 = r50;
        r0 = r0.mSubtitleTextView;
        r46 = r0;
        r46 = r46.getMeasuredHeight();
        r34 = r42 + r46;
        r0 = r50;
        r0 = r0.mSubtitleTextView;
        r46 = r0;
        r0 = r46;
        r1 = r35;
        r2 = r42;
        r3 = r36;
        r4 = r34;
        r0.layout(r1, r2, r3, r4);
        r0 = r50;
        r0 = r0.mTitleMarginEnd;
        r46 = r0;
        r35 = r36 + r46;
        r0 = r22;
        r0 = r0.bottomMargin;
        r46 = r0;
        r42 = r34 + r46;
    L_0x04b2:
        if (r38 == 0) goto L_0x02de;
    L_0x04b4:
        r0 = r40;
        r1 = r35;
        r20 = java.lang.Math.max(r0, r1);
        goto L_0x02de;
    L_0x04be:
        r46 = 0;
        goto L_0x03f3;
    L_0x04c2:
        r0 = r50;
        r0 = r0.mTempViews;
        r46 = r0;
        r47 = 5;
        r0 = r50;
        r1 = r46;
        r2 = r47;
        r0.addCustomViewsWithGravity(r1, r2);
        r0 = r50;
        r0 = r0.mTempViews;
        r46 = r0;
        r30 = r46.size();
        r15 = 0;
    L_0x04de:
        r0 = r30;
        if (r15 >= r0) goto L_0x04fd;
    L_0x04e2:
        r0 = r50;
        r0 = r0.mTempViews;
        r46 = r0;
        r0 = r46;
        r46 = r0.get(r15);
        r46 = (android.view.View) r46;
        r0 = r50;
        r1 = r46;
        r2 = r29;
        r29 = r0.layoutChildRight(r1, r2, r12, r5);
        r15 = r15 + 1;
        goto L_0x04de;
    L_0x04fd:
        r0 = r50;
        r0 = r0.mTempViews;
        r46 = r0;
        r47 = 1;
        r0 = r50;
        r1 = r46;
        r2 = r47;
        r0.addCustomViewsWithGravity(r1, r2);
        r0 = r50;
        r0 = r0.mTempViews;
        r46 = r0;
        r0 = r50;
        r1 = r46;
        r11 = r0.getViewListMeasuredWidth(r1, r12);
        r46 = r45 - r24;
        r46 = r46 - r25;
        r46 = r46 / 2;
        r27 = r24 + r46;
        r13 = r11 / 2;
        r8 = r27 - r13;
        r9 = r8 + r11;
        r0 = r20;
        if (r8 >= r0) goto L_0x0556;
    L_0x052e:
        r8 = r20;
    L_0x0530:
        r0 = r50;
        r0 = r0.mTempViews;
        r46 = r0;
        r10 = r46.size();
        r15 = 0;
    L_0x053b:
        if (r15 >= r10) goto L_0x055f;
    L_0x053d:
        r0 = r50;
        r0 = r0.mTempViews;
        r46 = r0;
        r0 = r46;
        r46 = r0.get(r15);
        r46 = (android.view.View) r46;
        r0 = r50;
        r1 = r46;
        r8 = r0.layoutChildLeft(r1, r8, r12, r5);
        r15 = r15 + 1;
        goto L_0x053b;
    L_0x0556:
        r0 = r29;
        if (r9 <= r0) goto L_0x0530;
    L_0x055a:
        r46 = r9 - r29;
        r8 = r8 - r46;
        goto L_0x0530;
    L_0x055f:
        r0 = r50;
        r0 = r0.mTempViews;
        r46 = r0;
        r46.clear();
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.Toolbar.onLayout(boolean, int, int, int, int):void");
    }

    private int getViewListMeasuredWidth(List<View> views, int[] collapsingMargins) {
        int collapseLeft = collapsingMargins[0];
        int collapseRight = collapsingMargins[1];
        int width = 0;
        int count = views.size();
        for (int i = 0; i < count; i++) {
            View v = (View) views.get(i);
            LayoutParams lp = (LayoutParams) v.getLayoutParams();
            int l = lp.leftMargin - collapseLeft;
            int r = lp.rightMargin - collapseRight;
            int leftMargin = Math.max(0, l);
            int rightMargin = Math.max(0, r);
            collapseLeft = Math.max(0, -l);
            collapseRight = Math.max(0, -r);
            width += (v.getMeasuredWidth() + leftMargin) + rightMargin;
        }
        return width;
    }

    private int layoutChildLeft(View child, int left, int[] collapsingMargins, int alignmentHeight) {
        LayoutParams lp = (LayoutParams) child.getLayoutParams();
        int l = lp.leftMargin - collapsingMargins[0];
        left += Math.max(0, l);
        collapsingMargins[0] = Math.max(0, -l);
        int top = getChildTop(child, alignmentHeight);
        int childWidth = child.getMeasuredWidth();
        child.layout(left, top, left + childWidth, child.getMeasuredHeight() + top);
        return left + (lp.rightMargin + childWidth);
    }

    private int layoutChildRight(View child, int right, int[] collapsingMargins, int alignmentHeight) {
        LayoutParams lp = (LayoutParams) child.getLayoutParams();
        int r = lp.rightMargin - collapsingMargins[1];
        right -= Math.max(0, r);
        collapsingMargins[1] = Math.max(0, -r);
        int top = getChildTop(child, alignmentHeight);
        int childWidth = child.getMeasuredWidth();
        child.layout(right - childWidth, top, right, child.getMeasuredHeight() + top);
        return right - (lp.leftMargin + childWidth);
    }

    private int getChildTop(View child, int alignmentHeight) {
        int alignmentOffset;
        LayoutParams lp = (LayoutParams) child.getLayoutParams();
        int childHeight = child.getMeasuredHeight();
        if (alignmentHeight > 0) {
            alignmentOffset = (childHeight - alignmentHeight) / 2;
        } else {
            alignmentOffset = 0;
        }
        switch (getChildVerticalGravity(lp.gravity)) {
            case com.google.android.play.R.styleable.Theme_selectableItemBackgroundBorderless /*48*/:
                return getPaddingTop() - alignmentOffset;
            case com.google.android.play.R.styleable.Theme_colorControlActivated /*80*/:
                return (((getHeight() - getPaddingBottom()) - childHeight) - lp.bottomMargin) - alignmentOffset;
            default:
                int paddingTop = getPaddingTop();
                int paddingBottom = getPaddingBottom();
                int height = getHeight();
                int spaceAbove = (((height - paddingTop) - paddingBottom) - childHeight) / 2;
                if (spaceAbove < lp.topMargin) {
                    spaceAbove = lp.topMargin;
                } else {
                    int spaceBelow = (((height - paddingBottom) - childHeight) - spaceAbove) - paddingTop;
                    if (spaceBelow < lp.bottomMargin) {
                        spaceAbove = Math.max(0, spaceAbove - (lp.bottomMargin - spaceBelow));
                    }
                }
                return paddingTop + spaceAbove;
        }
    }

    private int getChildVerticalGravity(int gravity) {
        int vgrav = gravity & 112;
        switch (vgrav) {
            case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
            case com.google.android.play.R.styleable.Theme_selectableItemBackgroundBorderless /*48*/:
            case com.google.android.play.R.styleable.Theme_colorControlActivated /*80*/:
                return vgrav;
            default:
                return this.mGravity & 112;
        }
    }

    private void addCustomViewsWithGravity(List<View> views, int gravity) {
        boolean isRtl = true;
        if (ViewCompat.getLayoutDirection(this) != 1) {
            isRtl = false;
        }
        int childCount = getChildCount();
        int absGrav = GravityCompat.getAbsoluteGravity(gravity, ViewCompat.getLayoutDirection(this));
        views.clear();
        int i;
        View child;
        LayoutParams lp;
        if (isRtl) {
            for (i = childCount - 1; i >= 0; i--) {
                child = getChildAt(i);
                lp = (LayoutParams) child.getLayoutParams();
                if (lp.mViewType == 0 && shouldLayout(child) && getChildHorizontalGravity(lp.gravity) == absGrav) {
                    views.add(child);
                }
            }
            return;
        }
        for (i = 0; i < childCount; i++) {
            child = getChildAt(i);
            lp = (LayoutParams) child.getLayoutParams();
            if (lp.mViewType == 0 && shouldLayout(child) && getChildHorizontalGravity(lp.gravity) == absGrav) {
                views.add(child);
            }
        }
    }

    private int getChildHorizontalGravity(int gravity) {
        int ld = ViewCompat.getLayoutDirection(this);
        int hGrav = GravityCompat.getAbsoluteGravity(gravity, ld) & 7;
        switch (hGrav) {
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                return hGrav;
            default:
                return ld == 1 ? 5 : 3;
        }
    }

    private boolean shouldLayout(View view) {
        return (view == null || view.getParent() != this || view.getVisibility() == 8) ? false : true;
    }

    private int getHorizontalMargins(View v) {
        MarginLayoutParams mlp = (MarginLayoutParams) v.getLayoutParams();
        return MarginLayoutParamsCompat.getMarginStart(mlp) + MarginLayoutParamsCompat.getMarginEnd(mlp);
    }

    private int getVerticalMargins(View v) {
        MarginLayoutParams mlp = (MarginLayoutParams) v.getLayoutParams();
        return mlp.topMargin + mlp.bottomMargin;
    }

    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(), attrs);
    }

    protected LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams p) {
        if (p instanceof LayoutParams) {
            return new LayoutParams((LayoutParams) p);
        }
        if (p instanceof android.support.v7.app.ActionBar.LayoutParams) {
            return new LayoutParams((android.support.v7.app.ActionBar.LayoutParams) p);
        }
        if (p instanceof MarginLayoutParams) {
            return new LayoutParams((MarginLayoutParams) p);
        }
        return new LayoutParams(p);
    }

    protected LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-2, -2);
    }

    protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams p) {
        return super.checkLayoutParams(p) && (p instanceof LayoutParams);
    }

    public DecorToolbar getWrapper() {
        if (this.mWrapper == null) {
            this.mWrapper = new ToolbarWidgetWrapper(this, true);
        }
        return this.mWrapper;
    }

    private void setChildVisibilityForExpandedActionView(boolean expand) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            if (!(((LayoutParams) child.getLayoutParams()).mViewType == 2 || child == this.mMenuView)) {
                child.setVisibility(expand ? 8 : 0);
            }
        }
    }

    private void updateChildVisibilityForExpandedActionView(View child) {
        if (((LayoutParams) child.getLayoutParams()).mViewType != 2 && child != this.mMenuView) {
            child.setVisibility(this.mExpandedActionView != null ? 8 : 0);
        }
    }

    public void setCollapsible(boolean collapsible) {
        this.mCollapsible = collapsible;
        requestLayout();
    }

    public void setMenuCallbacks(Callback pcb, MenuBuilder.Callback mcb) {
        this.mActionMenuPresenterCallback = pcb;
        this.mMenuBuilderCallback = mcb;
    }

    public void setMinimumHeight(int minHeight) {
        this.mMinHeight = minHeight;
        super.setMinimumHeight(minHeight);
    }

    private int getMinimumHeightCompat() {
        if (VERSION.SDK_INT >= 16) {
            return ViewCompat.getMinimumHeight(this);
        }
        return this.mMinHeight;
    }

    private static Context themifyContext(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.Toolbar, defStyleAttr, 0);
        int themeId = a.getResourceId(R.styleable.Toolbar_theme, 0);
        if (themeId != 0) {
            context = new ContextThemeWrapper(context, themeId);
        }
        a.recycle();
        return context;
    }
}
