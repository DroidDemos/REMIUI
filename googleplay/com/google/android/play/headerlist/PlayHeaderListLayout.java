package com.google.android.play.headerlist;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Outline;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v4.view.ViewPagerHelper;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowInsets;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.ScaleAnimation;
import android.view.animation.Transformation;
import android.view.animation.TranslateAnimation;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.google.android.play.R;
import com.google.android.play.headerlist.PlayScrollableContentView.OnScrollListener;
import com.google.android.play.widget.ScrollProxyView;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.WeakHashMap;

public class PlayHeaderListLayout extends FrameLayout implements OnRefreshListener {
    static final boolean SUPPORT_DRAW_STATUS_BAR;
    private static final boolean SUPPORT_ELEVATION;
    private static final boolean USE_ANIMATIONS;
    private static Map<View, Integer> sActionbarAttachedCount;
    private int mAbsoluteY;
    private float mActionBarTitleAlpha;
    private boolean mAllowImmersiveBackground;
    private View mAltPlayBackground;
    private AnimationCompat mAltPlayBackgroundCompat;
    private boolean mAlwaysUseFloatingBackground;
    private Map<String, ObjectAnimator> mAnimatorMap;
    OnScrollListener mAppContentViewOnScrollListener;
    AbsListView.OnScrollListener mAppListViewOnScrollListener;
    RecyclerView.OnScrollListener mAppRecyclerViewOnScrollListener;
    private boolean mAttached;
    private boolean mAutoHideToolbarTitle;
    private FrameLayout mBackgroundContainer;
    private AnimationCompat mBackgroundContainerCompat;
    private final float mBackgroundFadeInOffsetThresholdPx;
    private float mBackgroundParallaxRatio;
    private float mBannerFraction;
    private CharSequence mBannerText;
    private TextView mBannerTextView;
    private AnimationCompat mBannerTextViewCompat;
    private boolean mBannerVisibleGB;
    private View mContentContainer;
    private AnimationCompat mContentContainerCompat;
    private boolean mContentProtectionEnabled;
    private boolean mControlsAreFloating;
    private ViewGroup mControlsContainer;
    private AnimationCompat mControlsContainerCompat;
    private OnPageChangeListener mExternalPageChangeListener;
    private Drawable mFloatingControlsBackground;
    private float mFloatingFraction;
    private final Handler mHandler;
    private boolean mHasPullToRefresh;
    private boolean mHasViewPager;
    private int mHeaderBottomMargin;
    private int mHeaderHeight;
    private int mHeaderMode;
    private View mHeaderShadow;
    private AnimationCompat mHeaderShadowCompat;
    private int mHeaderShadowMode;
    private boolean mHeaderShadowVisible;
    private int mHeroAnimationMode;
    private FrameLayout mHeroContainer;
    private AnimationCompat mHeroContainerCompat;
    private boolean mHeroVisible;
    private boolean mLastScrollWasDown;
    LayoutListener mLayoutListener;
    private int mListViewId;
    private boolean mLockHeader;
    private final OnPageChangeListener mOnPageChangeListener;
    private int mPendingListSync;
    private SavedState mPendingSavedState;
    private int mPullToRefreshAdapterPage;
    PullToRefreshProvider mPullToRefreshProvider;
    private ScrollProxyView mScrollProxyView;
    private final Runnable mSnapControlsDownIfNeededRunnable;
    private final Runnable mSnapControlsUpIfNeededRunnable;
    private int mSpacerId;
    private int mStatusBarHeight;
    private PlayHeaderStatusBarUnderlay mStatusBarUnderlay;
    private boolean mSuppressIdleOnScroll;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private AnimationCompat mSwipeRefreshLayoutCompat;
    private int mTabA11yMode;
    private View mTabBar;
    private TextView mTabBarTitleView;
    private int mTabMode;
    private int mTabPaddingMode;
    private PlayHeaderListTabStrip mTabStrip;
    private Runnable mTemporaryBannerGoneRunnable;
    private final Runnable mTemporaryBannerTimeoutRunnable;
    private Toolbar mToolbar;
    private View mToolbarContainer;
    private float mToolbarContainerA11yOffset;
    private AnimationCompat mToolbarContainerCompat;
    private final PlayHeaderScrollableContentListener mTrackedListContentViewScrollListener;
    private final PlayHeaderListRecyclerViewListener mTrackedListRecyclerViewScrollListener;
    private final PlayHeaderListOnScrollListener mTrackedListScrollListener;
    private ViewGroup mTrackedListView;
    private boolean mUpdateContentPositionOnLayout;
    private boolean mUseBuiltInToolbar;
    private ViewPager mViewPager;
    private int mViewPagerId;

    public static abstract class Configurator {
        protected final Context mContext;

        protected abstract void addContentView(LayoutInflater layoutInflater, ViewGroup viewGroup);

        protected abstract int getHeaderHeight();

        protected abstract int getTabMode();

        protected abstract boolean hasViewPager();

        public Configurator(Context context) {
            this.mContext = context;
        }

        protected void addBackgroundView(LayoutInflater inflater, ViewGroup root) {
        }

        protected float getBackgroundViewParallaxRatio() {
            return 0.7f;
        }

        protected boolean alwaysUseFloatingBackground() {
            return !PlayHeaderListLayout.USE_ANIMATIONS;
        }

        protected int getTabPaddingMode() {
            return alwaysUseFloatingBackground() ? 1 : 0;
        }

        protected void addHeroView(LayoutInflater inflater, ViewGroup root) {
        }

        protected int getHeaderBottomMargin() {
            return 0;
        }

        protected boolean useBuiltInActionBar() {
            return false;
        }

        protected int getViewPagerId() {
            return R.id.play_header_viewpager;
        }

        protected int getListViewId() {
            return R.id.play_header_listview;
        }

        protected int getHeaderSpacerId() {
            return R.id.play_header_spacer;
        }

        protected int getHeroAnimationMode() {
            return 0;
        }

        protected int getContentProtectionMode() {
            return 0;
        }

        @Deprecated
        protected Drawable getContentProtectionBackground(Context context) {
            return new ColorDrawable(context.getResources().getColor(R.color.play_main_background));
        }

        protected int getToolbarTitleMode() {
            return 0;
        }

        protected int getHeaderMode() {
            return 0;
        }

        protected int getHeaderShadowMode() {
            return 0;
        }

        protected PlayHeaderListTabStrip getCustomTabStrip(Context context, LayoutInflater inflater) {
            return null;
        }

        protected int getTabAccessibilityMode() {
            return 0;
        }

        protected boolean allowImmersiveBackground() {
            return false;
        }

        protected int getStatusBarOverlayColor() {
            TypedArray array = this.mContext.obtainStyledAttributes(new int[]{16843857});
            int color = array.getColor(0, 0);
            array.recycle();
            return color;
        }

        protected int getStatusBarUnderlayColor() {
            TypedArray array = this.mContext.obtainStyledAttributes(new int[]{R.attr.colorPrimary});
            int color = array.getColor(0, 0);
            array.recycle();
            return color;
        }
    }

    public interface OnTabSelectedListener {
        void onBeforeTabSelected(int i);

        void onTabSelected(int i);
    }

    public interface LayoutListener {
        void onPlayHeaderListLayoutChanged();
    }

    private abstract class FloatAnimation extends Animation {
        private final float mFrom;
        private final float mTo;

        protected abstract void setValue(float f);

        protected FloatAnimation(float from, float to) {
            this.mFrom = from;
            this.mTo = to;
        }

        protected void applyTransformation(float interpolatedTime, Transformation t) {
            super.applyTransformation(interpolatedTime, t);
            setValue(((this.mTo - this.mFrom) * interpolatedTime) + this.mFrom);
        }
    }

    private class ActionBarTitleAlphaAnimation extends FloatAnimation {
        public ActionBarTitleAlphaAnimation(float from, float to) {
            super(from, to);
        }

        protected void setValue(float value) {
            PlayHeaderListLayout.this.setActionBarTitleAlpha(value);
        }
    }

    private static class AnimationCompat {
        private float mAlpha;
        private float mScale;
        private float mTranslationX;
        private float mTranslationY;
        private final View mView;

        public AnimationCompat(View view) {
            this.mAlpha = 1.0f;
            this.mTranslationY = 0.0f;
            this.mTranslationX = 0.0f;
            this.mScale = 1.0f;
            this.mView = view;
        }

        public void setAlpha(float alpha) {
            if (this.mView != null) {
                if (VERSION.SDK_INT >= 11) {
                    this.mView.setAlpha(alpha);
                } else if (this.mAlpha != alpha) {
                    this.mAlpha = alpha;
                    AlphaAnimation animation = new AlphaAnimation(alpha, alpha);
                    animation.setDuration(0);
                    animation.setFillAfter(true);
                    this.mView.startAnimation(animation);
                }
            }
        }

        public float getAlpha() {
            if (this.mView == null) {
                return this.mAlpha;
            }
            if (VERSION.SDK_INT >= 11) {
                return this.mView.getAlpha();
            }
            return this.mAlpha;
        }

        public void animateAlpha(final float toAlpha, int duration) {
            if (this.mView != null) {
                if (VERSION.SDK_INT >= 12) {
                    this.mView.animate().alpha(toAlpha).setDuration((long) duration);
                    return;
                }
                AlphaAnimation animation = new AlphaAnimation(this.mAlpha, toAlpha);
                animation.setDuration((long) duration);
                animation.setFillAfter(true);
                if (VERSION.SDK_INT < 12) {
                    animation.setAnimationListener(new AnimationListener() {
                        public void onAnimationStart(Animation animation) {
                        }

                        public void onAnimationRepeat(Animation animation) {
                        }

                        public void onAnimationEnd(Animation animation) {
                            AnimationCompat.this.mAlpha = toAlpha;
                        }
                    });
                }
                this.mView.startAnimation(animation);
            }
        }

        public void animateZ(float z, int duration, int startDelay) {
            if (PlayHeaderListLayout.SUPPORT_ELEVATION) {
                this.mView.animate().z(z).setDuration((long) duration).setStartDelay((long) startDelay);
            }
        }

        public float getTranslationY() {
            if (this.mView == null) {
                return this.mTranslationY;
            }
            if (VERSION.SDK_INT >= 11) {
                return this.mView.getTranslationY();
            }
            return this.mTranslationY;
        }

        public void setTranslationY(float translationY) {
            if (this.mView == null) {
                this.mTranslationY = translationY;
            } else if (VERSION.SDK_INT >= 11) {
                this.mView.setTranslationY(translationY);
            } else if (this.mTranslationY != translationY) {
                this.mTranslationY = translationY;
                TranslateAnimation animation = new TranslateAnimation(0.0f, 0.0f, translationY, translationY);
                animation.setDuration(0);
                animation.setFillAfter(true);
                this.mView.startAnimation(animation);
            }
        }

        public void setTranslationX(float translationX) {
            if (this.mView == null) {
                this.mTranslationX = translationX;
            } else if (VERSION.SDK_INT >= 11) {
                this.mView.setTranslationX(translationX);
            } else if (this.mTranslationX != translationX) {
                this.mTranslationX = translationX;
                TranslateAnimation animation = new TranslateAnimation(translationX, translationX, 0.0f, 0.0f);
                animation.setDuration(0);
                animation.setFillAfter(true);
                this.mView.startAnimation(animation);
            }
        }

        public void setScale(float scale) {
            if (this.mView == null) {
                this.mScale = scale;
            } else if (VERSION.SDK_INT >= 11) {
                this.mView.setScaleX(scale);
                this.mView.setScaleY(scale);
            } else if (this.mScale != scale) {
                this.mScale = scale;
                ScaleAnimation animation = new ScaleAnimation(this.mScale, this.mScale, this.mScale, this.mScale);
                animation.setDuration(0);
                animation.setFillAfter(true);
                this.mView.startAnimation(animation);
            }
        }

        public void animateScale(final float toScale, int duration) {
            if (this.mView == null) {
                this.mScale = toScale;
            } else if (VERSION.SDK_INT >= 12) {
                this.mView.animate().scaleX(toScale).scaleY(toScale).setDuration((long) duration);
            } else {
                ScaleAnimation animation = new ScaleAnimation(this.mScale, toScale, this.mScale, toScale);
                animation.setDuration((long) duration);
                animation.setFillAfter(true);
                if (VERSION.SDK_INT < 12) {
                    animation.setAnimationListener(new AnimationListener() {
                        public void onAnimationStart(Animation animation) {
                        }

                        public void onAnimationRepeat(Animation animation) {
                        }

                        public void onAnimationEnd(Animation animation) {
                            AnimationCompat.this.mScale = toScale;
                        }
                    });
                }
                this.mView.startAnimation(animation);
            }
        }
    }

    private class FloatingFractionAnimation extends FloatAnimation {
        public FloatingFractionAnimation(float from, float to) {
            super(from, to);
        }

        protected void setValue(float value) {
            PlayHeaderListLayout.this.setFloatingFraction(value);
        }
    }

    public static abstract class PullToRefreshProvider {
        public abstract void onPulledToRefresh();

        public void onPullToRefreshDisplayedPageChanged(SwipeRefreshLayout swipeRefreshLayout, int pagerAdapterIndex) {
        }

        public boolean supportsPullToRefresh(int pagerAdapterIndex) {
            return true;
        }

        public boolean isPageRefreshing(int pagerAdapterIndex) {
            return false;
        }

        public int getOffsetMode() {
            return 0;
        }

        public int getPositionMode() {
            return 0;
        }
    }

    static class SavedState extends BaseSavedState {
        public static final Creator<SavedState> CREATOR;
        final float mFloatingFraction;

        SavedState(Parcelable superState, PlayHeaderListLayout layout) {
            super(superState);
            this.mFloatingFraction = layout.mFloatingFraction;
        }

        private SavedState(Parcel in) {
            super(in);
            this.mFloatingFraction = in.readFloat();
        }

        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeFloat(this.mFloatingFraction);
        }

        public String toString() {
            return String.format(Locale.US, "floatingFraction: %f", new Object[]{Float.valueOf(this.mFloatingFraction)});
        }

        static {
            CREATOR = new Creator<SavedState>() {
                public SavedState createFromParcel(Parcel in) {
                    return new SavedState(in);
                }

                public SavedState[] newArray(int size) {
                    return new SavedState[size];
                }
            };
        }
    }

    static {
        boolean z;
        boolean z2 = true;
        USE_ANIMATIONS = VERSION.SDK_INT > 10;
        if (VERSION.SDK_INT >= 21) {
            z = true;
        } else {
            z = false;
        }
        SUPPORT_ELEVATION = z;
        if (VERSION.SDK_INT < 21) {
            z2 = false;
        }
        SUPPORT_DRAW_STATUS_BAR = z2;
        sActionbarAttachedCount = new WeakHashMap();
    }

    public PlayHeaderListLayout(Context context) {
        this(context, null);
    }

    public PlayHeaderListLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PlayHeaderListLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mHandler = new Handler();
        this.mOnPageChangeListener = new OnPageChangeListener() {
            public void onPageScrollStateChanged(int state) {
                if (PlayHeaderListLayout.this.mExternalPageChangeListener != null) {
                    PlayHeaderListLayout.this.mExternalPageChangeListener.onPageScrollStateChanged(state);
                }
                if (state == 0) {
                    return;
                }
                if (PlayHeaderListLayout.this.mHeaderMode == 0 || PlayHeaderListLayout.this.mHeaderMode == 2) {
                    PlayHeaderListLayout.this.snapControlsIfNeeded(true, true, true);
                }
            }

            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                updatePullToRefreshPosition(position, positionOffset, positionOffsetPixels);
                if (PlayHeaderListLayout.this.mExternalPageChangeListener != null) {
                    PlayHeaderListLayout.this.mExternalPageChangeListener.onPageScrolled(position, positionOffset, positionOffsetPixels);
                }
            }

            public void onPageSelected(int position) {
                PlayHeaderListLayout.this.updateActiveListView();
                if (PlayHeaderListLayout.this.mExternalPageChangeListener != null) {
                    PlayHeaderListLayout.this.mExternalPageChangeListener.onPageSelected(position);
                }
            }

            private void updatePullToRefreshPosition(int pagePosition, float pagePositionOffset, int pagePositionOffsetPixels) {
                if (PlayHeaderListLayout.this.mPullToRefreshProvider != null) {
                    int pullToRefreshAdapterPage = pagePosition + (pagePositionOffset > 0.5f ? 1 : 0);
                    boolean isRefreshing = PlayHeaderListLayout.this.mSwipeRefreshLayout.isRefreshing();
                    if (pullToRefreshAdapterPage != PlayHeaderListLayout.this.mPullToRefreshAdapterPage) {
                        PlayHeaderListLayout.this.mPullToRefreshAdapterPage = pullToRefreshAdapterPage;
                        isRefreshing = PlayHeaderListLayout.this.mPullToRefreshProvider.isPageRefreshing(PlayHeaderListLayout.this.mPullToRefreshAdapterPage);
                        PlayHeaderListLayout.this.mSwipeRefreshLayout.setRefreshing(isRefreshing);
                        PlayHeaderListLayout.this.mPullToRefreshProvider.onPullToRefreshDisplayedPageChanged(PlayHeaderListLayout.this.mSwipeRefreshLayout, pullToRefreshAdapterPage);
                    }
                    switch (PlayHeaderListLayout.this.mPullToRefreshProvider.getPositionMode()) {
                        case com.google.android.wallet.instrumentmanager.R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                            return;
                        case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                            if (!PlayHeaderListLayout.USE_ANIMATIONS) {
                                return;
                            }
                            if (isRefreshing) {
                                PlayHeaderListLayout.this.mSwipeRefreshLayoutCompat.setAlpha(Math.max(0.0f, Math.abs((0.5f - pagePositionOffset) * 2.0f)));
                                PlayHeaderListLayout.this.mSwipeRefreshLayoutCompat.setTranslationX((float) (pagePositionOffset > 0.5f ? PlayHeaderListLayout.this.mViewPager.getWidth() - pagePositionOffsetPixels : pagePositionOffsetPixels * -1));
                                return;
                            }
                            PlayHeaderListLayout.this.mSwipeRefreshLayoutCompat.setAlpha(0.0f);
                            PlayHeaderListLayout.this.mSwipeRefreshLayoutCompat.setTranslationX(0.0f);
                            return;
                        default:
                            return;
                    }
                }
            }
        };
        this.mHeroAnimationMode = 0;
        this.mPullToRefreshAdapterPage = -1;
        this.mHeroVisible = true;
        this.mActionBarTitleAlpha = 0.5f;
        this.mLastScrollWasDown = true;
        this.mTrackedListScrollListener = new PlayHeaderListOnScrollListener(this);
        this.mTrackedListRecyclerViewScrollListener = new PlayHeaderListRecyclerViewListener(this);
        this.mTrackedListContentViewScrollListener = new PlayHeaderScrollableContentListener(this);
        this.mAnimatorMap = new HashMap();
        this.mSnapControlsUpIfNeededRunnable = new Runnable() {
            public void run() {
                PlayHeaderListLayout.this.snapControlsIfNeeded(false, false, true);
            }
        };
        this.mSnapControlsDownIfNeededRunnable = new Runnable() {
            public void run() {
                PlayHeaderListLayout.this.snapControlsIfNeeded(true, false, true);
            }
        };
        this.mTemporaryBannerTimeoutRunnable = new Runnable() {
            public void run() {
                if (PlayHeaderListLayout.this.mTemporaryBannerGoneRunnable != null) {
                    PlayHeaderListLayout.this.mTemporaryBannerGoneRunnable.run();
                    PlayHeaderListLayout.this.mTemporaryBannerGoneRunnable = null;
                }
                PlayHeaderListLayout.this.setBannerText(null);
            }
        };
        this.mBackgroundFadeInOffsetThresholdPx = context.getResources().getDisplayMetrics().density * 20.0f;
    }

    public void configure(Configurator configurator) {
        boolean z;
        int tabPaddingMode;
        this.mBackgroundParallaxRatio = configurator.getBackgroundViewParallaxRatio();
        this.mListViewId = configurator.getListViewId();
        this.mViewPagerId = configurator.getViewPagerId();
        this.mSpacerId = configurator.getHeaderSpacerId();
        this.mSpacerId = this.mSpacerId == 0 ? R.id.play_header_spacer : this.mSpacerId;
        this.mUseBuiltInToolbar = configurator.useBuiltInActionBar();
        this.mHasViewPager = configurator.hasViewPager();
        this.mTabMode = configurator.getTabMode();
        if (configurator.getContentProtectionMode() == 1) {
            z = true;
        } else {
            z = false;
        }
        this.mContentProtectionEnabled = z;
        this.mHeroAnimationMode = configurator.getHeroAnimationMode();
        if (configurator.getToolbarTitleMode() == 0) {
            z = true;
        } else {
            z = false;
        }
        this.mAutoHideToolbarTitle = z;
        this.mHeaderMode = configurator.getHeaderMode();
        this.mHeaderShadowMode = configurator.getHeaderShadowMode();
        this.mAlwaysUseFloatingBackground = configurator.alwaysUseFloatingBackground();
        if (SUPPORT_DRAW_STATUS_BAR && configurator.allowImmersiveBackground() && this.mAutoHideToolbarTitle && !this.mAlwaysUseFloatingBackground) {
            z = true;
        } else {
            z = false;
        }
        this.mAllowImmersiveBackground = z;
        if (USE_ANIMATIONS) {
            tabPaddingMode = configurator.getTabPaddingMode();
        } else {
            tabPaddingMode = 1;
        }
        this.mTabPaddingMode = tabPaddingMode;
        this.mTabA11yMode = configurator.getTabAccessibilityMode();
        LayoutInflater inflater = LayoutInflater.from(getContext());
        PlayHeaderListTabStrip customTabStrip = configurator.getCustomTabStrip(getContext(), inflater);
        inflater.inflate(USE_ANIMATIONS ? R.layout.play_header_list_layout : R.layout.play_header_list_layout_gb, this);
        this.mBackgroundContainer = (FrameLayout) findViewById(R.id.background_container);
        this.mBackgroundContainerCompat = new AnimationCompat(this.mBackgroundContainer);
        this.mAltPlayBackground = findViewById(R.id.alt_play_background);
        this.mAltPlayBackgroundCompat = new AnimationCompat(this.mAltPlayBackground);
        this.mContentContainer = findViewById(R.id.content_container);
        this.mContentContainerCompat = new AnimationCompat(this.mContentContainer);
        this.mControlsContainer = (ViewGroup) findViewById(R.id.controls_container);
        this.mControlsContainerCompat = new AnimationCompat(this.mControlsContainer);
        this.mHeaderShadow = findViewById(R.id.header_shadow);
        this.mHeaderShadowCompat = new AnimationCompat(this.mHeaderShadow);
        this.mHeroContainer = (FrameLayout) findViewById(R.id.hero_container);
        this.mHeroContainerCompat = new AnimationCompat(this.mHeroContainer);
        this.mTabBar = findViewById(R.id.tab_bar);
        this.mTabStrip = (PlayHeaderListTabStrip) findViewById(R.id.pager_tab_strip);
        if (customTabStrip != null) {
            replaceTabStrip(this.mTabStrip, customTabStrip);
            this.mTabStrip = customTabStrip;
        }
        this.mTabStrip.setExternalOnPageChangeListener(this.mOnPageChangeListener);
        this.mTabBarTitleView = (TextView) findViewById(R.id.tab_bar_title);
        this.mHeaderHeight = configurator.getHeaderHeight();
        this.mHeaderBottomMargin = configurator.getHeaderBottomMargin();
        setControlsContainerHeight(this.mHeaderHeight - this.mHeaderBottomMargin);
        if (!SUPPORT_ELEVATION) {
            updateHeaderShadowTopMargin();
        }
        this.mToolbar = getToolbar();
        this.mToolbarContainer = getToolbarContainer();
        this.mToolbarContainerCompat = new AnimationCompat(this.mToolbarContainer);
        this.mBannerTextView = (TextView) findViewById(R.id.play_header_banner);
        this.mBannerTextViewCompat = new AnimationCompat(this.mBannerTextView);
        if (this.mAllowImmersiveBackground) {
            this.mStatusBarUnderlay = (PlayHeaderStatusBarUnderlay) findViewById(R.id.play_header_status_bar_underlay);
            this.mStatusBarUnderlay.setVisibility(0);
            this.mStatusBarUnderlay.setOutlineProvider(null);
            this.mStatusBarUnderlay.initColors(configurator.getStatusBarOverlayColor(), configurator.getStatusBarUnderlayColor());
            this.mBannerTextView.setBackground(null);
        }
        this.mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        this.mSwipeRefreshLayoutCompat = new AnimationCompat(this.mSwipeRefreshLayout);
        this.mSwipeRefreshLayout.setOnRefreshListener(this);
        this.mScrollProxyView = (ScrollProxyView) findViewById(R.id.scroll_proxy);
        setPullToRefreshEnabled(false);
        if (USE_ANIMATIONS) {
            configurator.addHeroView(inflater, this.mHeroContainer);
            configurator.addBackgroundView(inflater, this.mBackgroundContainer);
        } else if (!this.mAlwaysUseFloatingBackground) {
            configurator.addBackgroundView(inflater, this.mBackgroundContainer);
        }
        ViewGroup contentContainer = this.mContentContainer;
        configurator.addContentView(inflater, contentContainer);
        if (contentContainer.getChildCount() == 1) {
            View contentView = contentContainer.getChildAt(0);
            int contentContainerIndex = indexOfChild(this.mContentContainer);
            removeViewAt(contentContainerIndex);
            contentContainer.removeViewAt(0);
            addView(contentView, contentContainerIndex);
            this.mContentContainer = contentView;
            this.mContentContainerCompat = new AnimationCompat(this.mContentContainer);
        }
        if (USE_ANIMATIONS && this.mUseBuiltInToolbar) {
            this.mToolbarContainerA11yOffset = 1.0f * getResources().getDisplayMetrics().density;
        }
        updateTabBarVisibility();
        setupBackground(configurator);
        if (this.mAlwaysUseFloatingBackground) {
            setControlsBackground(this.mFloatingControlsBackground, false);
        }
        this.mBannerTextViewCompat.setTranslationY((float) (-getBannerHeight()));
        if (this.mAllowImmersiveBackground) {
            updateLayoutForStatusBarHeight(false);
        }
        setBannerFraction(this.mBannerFraction);
        updateTabPadding(false);
        updateTabContrast();
    }

    public void setAlwaysUseFloatingBackground(boolean alwaysUseFloatingBackground) {
        this.mAlwaysUseFloatingBackground = alwaysUseFloatingBackground;
        if (this.mAlwaysUseFloatingBackground) {
            setControlsBackground(this.mFloatingControlsBackground, false);
        } else {
            setControlsBackground(null, true);
        }
    }

    private static void replaceTabStrip(PlayHeaderListTabStrip stripToReplace, PlayHeaderListTabStrip strip) {
        LayoutParams lp = stripToReplace.getLayoutParams();
        ViewGroup parent = (ViewGroup) stripToReplace.getParent();
        int viewIndex = parent.indexOfChild(stripToReplace);
        parent.removeViewAt(viewIndex);
        parent.addView(strip, viewIndex, lp);
        View stripToReplaceChild = stripToReplace.getChildAt(0);
        stripToReplace.removeViewAt(0);
        strip.addView(stripToReplaceChild);
        strip.getSubViewReferences();
    }

    public Parcelable onSaveInstanceState() {
        return new SavedState(super.onSaveInstanceState(), this);
    }

    public void onRestoreInstanceState(Parcelable state) {
        if (state instanceof SavedState) {
            SavedState savedState = (SavedState) state;
            super.onRestoreInstanceState(savedState.getSuperState());
            this.mPendingSavedState = savedState;
            return;
        }
        super.onRestoreInstanceState(state);
    }

    private void restorePendingSavedStateIfNeeded() {
        if (this.mPendingSavedState != null && this.mTrackedListView != null) {
            updateContentPosition(false);
            setFloatingFraction(this.mPendingSavedState.mFloatingFraction, false);
            layout();
            syncListViews(false);
            this.mPendingSavedState = null;
            this.mSuppressIdleOnScroll = false;
        }
    }

    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        boolean hadPendingState;
        super.onLayout(changed, left, top, right, bottom);
        setupViewPagerIfNeeded();
        updateActiveListView();
        if (this.mPendingSavedState != null) {
            hadPendingState = true;
        } else {
            hadPendingState = false;
        }
        restorePendingSavedStateIfNeeded();
        if (!hadPendingState) {
            if (changed) {
                syncCurrentListViewOnNextScroll();
            }
            if (this.mUpdateContentPositionOnLayout) {
                updateContentPosition(true);
                this.mUpdateContentPositionOnLayout = false;
            }
        }
        switch (this.mPendingListSync) {
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                syncListViews(false);
                break;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                syncListViews(true);
                break;
        }
        if (changed) {
            updateHeaderShadow();
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (!this.mHasPullToRefresh) {
            return super.onInterceptTouchEvent(ev);
        }
        MotionEvent cloneEvent = MotionEvent.obtain(ev);
        boolean intercept = this.mSwipeRefreshLayout.onInterceptTouchEvent(cloneEvent);
        cloneEvent.recycle();
        if (!intercept || this.mSwipeRefreshLayoutCompat.getAlpha() >= 1.0f) {
            return intercept;
        }
        this.mSwipeRefreshLayoutCompat.setAlpha(1.0f);
        return intercept;
    }

    public boolean onTouchEvent(MotionEvent ev) {
        if (!this.mHasPullToRefresh) {
            return super.onTouchEvent(ev);
        }
        MotionEvent cloneEvent = MotionEvent.obtain(ev);
        boolean result = this.mSwipeRefreshLayout.onTouchEvent(cloneEvent);
        cloneEvent.recycle();
        return result;
    }

    public WindowInsets onApplyWindowInsets(WindowInsets insets) {
        int insetTop = insets.getSystemWindowInsetTop();
        int oldStatusBarHeight = this.mStatusBarHeight;
        this.mStatusBarHeight = Math.max(insetTop, oldStatusBarHeight);
        if (!this.mAllowImmersiveBackground) {
            return super.onApplyWindowInsets(insets);
        }
        if (this.mStatusBarHeight != oldStatusBarHeight) {
            updateLayoutForStatusBarHeight(true);
        }
        return insets.replaceSystemWindowInsets(insets.getSystemWindowInsetLeft(), 0, insets.getSystemWindowInsetRight(), insets.getSystemWindowInsetBottom());
    }

    private void updateLayoutForStatusBarHeight(boolean layout) {
        if (this.mAllowImmersiveBackground) {
            ((FrameLayout.LayoutParams) this.mStatusBarUnderlay.getLayoutParams()).height = this.mStatusBarHeight + this.mBannerTextView.getLayoutParams().height;
            this.mStatusBarUnderlay.setStatusBarHeight(this.mStatusBarHeight);
            this.mStatusBarUnderlay.requestLayout();
            setChildTopMargin(this.mAltPlayBackground, this.mStatusBarHeight);
            setChildTopMargin(this.mContentContainer, this.mStatusBarHeight);
            setChildTopMargin(this.mControlsContainer, this.mStatusBarHeight - 1);
            if (this.mUseBuiltInToolbar) {
                setChildTopMargin(this.mToolbarContainer, (int) (((float) this.mStatusBarHeight) - this.mToolbarContainerA11yOffset));
            }
            if (layout) {
                layout();
            }
        }
    }

    private void setChildTopMargin(View child, int topMargin) {
        ((FrameLayout.LayoutParams) child.getLayoutParams()).topMargin = topMargin;
        child.requestLayout();
    }

    private boolean isPullToRefreshEnabled() {
        return this.mScrollProxyView.getScrollY() == 0;
    }

    private void setPullToRefreshEnabled(boolean enabled) {
        if (enabled != isPullToRefreshEnabled()) {
            this.mScrollProxyView.scrollTo(0, enabled ? 0 : 1);
        }
    }

    public final void onRefresh() {
        if (this.mPullToRefreshProvider != null) {
            this.mPullToRefreshProvider.onPulledToRefresh();
        }
    }

    public void setPullToRefreshProvider(PullToRefreshProvider pullToRefreshProvider) {
        boolean z = false;
        if (this.mSwipeRefreshLayout == null) {
            throw new IllegalStateException("Cannot initialize pull to refresh before HeaderListLayout has been configured");
        }
        this.mSwipeRefreshLayout.setRefreshing(false);
        this.mSwipeRefreshLayoutCompat.setAlpha(1.0f);
        this.mSwipeRefreshLayoutCompat.setTranslationY(0.0f);
        this.mPullToRefreshProvider = pullToRefreshProvider;
        updateActiveListView();
        View swipeRefreshParent = findViewById(R.id.swipe_refresh_layout_parent);
        swipeRefreshParent.setVisibility(this.mPullToRefreshProvider != null ? 0 : 8);
        if (this.mPullToRefreshProvider != null) {
            int computedTopMargin;
            switch (this.mPullToRefreshProvider.getOffsetMode()) {
                case com.google.android.wallet.instrumentmanager.R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                    computedTopMargin = 0;
                    break;
                case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    computedTopMargin = getActionBarHeight();
                    break;
                case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    computedTopMargin = (this.mHeaderHeight - this.mHeaderBottomMargin) - 1;
                    break;
                default:
                    throw new IllegalArgumentException("Illegal offset mode specified in PullToRefreshProvider.getOffsetMode()");
            }
            swipeRefreshParent.setPadding(0, computedTopMargin, 0, 0);
            if (this.mAbsoluteY == 0) {
                z = true;
            }
            setPullToRefreshEnabled(z);
            return;
        }
        setPullToRefreshEnabled(false);
    }

    public SwipeRefreshLayout getSwipeRefreshLayout() {
        return this.mSwipeRefreshLayout;
    }

    public void setFloatingControlsBackground(Drawable background) {
        setFloatingControlsBackground(background, false);
    }

    public void setFloatingControlsBackground(Drawable background, boolean animate) {
        this.mFloatingControlsBackground = background;
        if ((this.mControlsContainer != null && this.mControlsAreFloating) || this.mAlwaysUseFloatingBackground) {
            setControlsBackground(this.mFloatingControlsBackground, animate);
        }
    }

    public void setSingleTabTitle(CharSequence title) {
        this.mTabBarTitleView.setText(title);
    }

    public void setSingleTabTitle(int titleResId) {
        this.mTabBarTitleView.setText(titleResId);
    }

    public void setSingleTabContentDescription(CharSequence contentDescription) {
        this.mTabBarTitleView.setContentDescription(contentDescription);
    }

    public void setOnPageChangeListener(OnPageChangeListener onPageChangeListener) {
        this.mExternalPageChangeListener = onPageChangeListener;
    }

    public void setOnScrollListener(AbsListView.OnScrollListener onScrollListener) {
        this.mAppListViewOnScrollListener = onScrollListener;
    }

    public void setOnScrollListener(RecyclerView.OnScrollListener onScrollListener) {
        this.mAppRecyclerViewOnScrollListener = onScrollListener;
    }

    public void setOnScrollListener(OnScrollListener onScrollListener) {
        this.mAppContentViewOnScrollListener = onScrollListener;
    }

    public void setOnLayoutChangedListener(LayoutListener layoutListener) {
        this.mLayoutListener = layoutListener;
    }

    public void setOnTabSelectedListener(OnTabSelectedListener listener) {
        this.mTabStrip.setOnTabSelectedListener(listener);
    }

    public void setBannerText(CharSequence bannerText, boolean animate) {
        this.mHandler.removeCallbacks(this.mTemporaryBannerTimeoutRunnable);
        this.mTemporaryBannerGoneRunnable = null;
        this.mBannerText = bannerText;
        if (bannerText == null) {
            hideBanner(animate);
            return;
        }
        this.mBannerTextView.setText(bannerText);
        showBanner(animate);
    }

    public void setBannerText(CharSequence bannerText) {
        setBannerText(bannerText, true);
    }

    public void setBannerText(int textResId, boolean animate) {
        if (textResId == 0) {
            setBannerText(null);
        } else {
            setBannerText(getResources().getText(textResId), animate);
        }
    }

    public void setBannerText(int textResId) {
        setBannerText(textResId, true);
    }

    public void setBannerOnClickListener(OnClickListener onClickListener) {
        this.mBannerTextView.setOnClickListener(onClickListener);
    }

    public CharSequence getBannerText() {
        return this.mBannerText;
    }

    public int getStatusBarHeight() {
        return this.mAllowImmersiveBackground ? this.mStatusBarHeight : 0;
    }

    public void setHeaderShadowMode(int headerShadowMode) {
        if (this.mHeaderShadowMode != headerShadowMode) {
            this.mHeaderShadowMode = headerShadowMode;
            layout();
        }
    }

    public void setTabMode(int tabMode, int headerHeight) {
        boolean syncListViews = false;
        if (this.mTabMode != tabMode) {
            this.mTabMode = tabMode;
            updateTabBarVisibility();
            syncListViews = true;
        }
        if (headerHeight != this.mHeaderHeight) {
            this.mHeaderHeight = headerHeight;
            syncListViews = true;
            setControlsContainerHeight(this.mHeaderHeight - this.mHeaderBottomMargin);
            if (!SUPPORT_ELEVATION) {
                updateHeaderShadowTopMargin();
            }
        }
        if (syncListViews) {
            syncListViews(true);
        }
        updateTabPadding(false);
        layout();
    }

    public int getTabMode() {
        return this.mTabMode;
    }

    public int getHeaderHeight() {
        return this.mHeaderHeight;
    }

    public float getVisibleHeaderHeight() {
        if (!this.mControlsAreFloating) {
            return getContentPosition() - ((float) this.mHeaderBottomMargin);
        }
        return getNonScrollingFloatingHeaderHeight() + (this.mFloatingFraction * getScrollingFloatingHeaderHeight());
    }

    public boolean isHeaderFloating() {
        return this.mControlsAreFloating || this.mAlwaysUseFloatingBackground;
    }

    protected final void setActionBarTitleAlpha(float alpha) {
        if (this.mActionBarTitleAlpha != alpha) {
            this.mActionBarTitleAlpha = alpha;
            applyActionBarTitleAlpha(this.mToolbar, alpha);
        }
    }

    protected void applyActionBarTitleAlpha(Toolbar toolbar, float alpha) {
        int titleColor = 16777215 | (Math.max(0, Math.min(255, Math.round(255.0f * alpha))) << 24);
        toolbar.setTitleTextColor(titleColor);
        toolbar.setSubtitleTextColor(titleColor);
    }

    protected final float getActionBarTitleAlpha() {
        return this.mActionBarTitleAlpha;
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        attachIfNeeded();
    }

    private void attachIfNeeded() {
        int i = 1;
        if (!this.mAttached) {
            this.mAttached = true;
            Integer currentAttachCount = (Integer) sActionbarAttachedCount.get(this.mToolbar);
            if (currentAttachCount != null) {
                i = currentAttachCount.intValue() + 1;
            }
            sActionbarAttachedCount.put(this.mToolbar, Integer.valueOf(i));
            setupViewPagerIfNeeded();
            updateHeaderShadow();
            if (this.mAutoHideToolbarTitle) {
                setActionBarTitleAlpha(0.0f);
            } else {
                setActionBarTitleAlpha(1.0f);
            }
            updateActiveListView();
        }
    }

    public void onDetachedFromWindow() {
        detachIfNeeded();
        super.onDetachedFromWindow();
    }

    private void detachIfNeeded() {
        int i = 0;
        if (this.mAttached) {
            this.mAttached = false;
            Integer currentAttachCount = (Integer) sActionbarAttachedCount.get(this.mToolbar);
            if (currentAttachCount != null) {
                i = currentAttachCount.intValue() - 1;
            }
            currentAttachCount = Integer.valueOf(i);
            if (currentAttachCount.intValue() == 0) {
                sActionbarAttachedCount.remove(this.mToolbar);
            } else {
                sActionbarAttachedCount.put(this.mToolbar, currentAttachCount);
            }
            setActiveListView(null);
            this.mHandler.removeCallbacksAndMessages(null);
            if (!this.mUseBuiltInToolbar && currentAttachCount.intValue() == 0) {
                this.mToolbarContainerCompat.setTranslationY(0.0f);
            }
            this.mTemporaryBannerGoneRunnable = null;
        }
    }

    public void detach() {
        detachIfNeeded();
    }

    private boolean setActiveListView(ViewGroup listView) {
        int i = 0;
        if (this.mTrackedListView != listView) {
            boolean hadTrackedListView;
            if (this.mTrackedListView != null) {
                hadTrackedListView = true;
            } else {
                hadTrackedListView = false;
            }
            if (this.mTrackedListView != null) {
                if (this.mTrackedListView instanceof ListView) {
                    ((ListView) this.mTrackedListView).setOnScrollListener(null);
                    this.mTrackedListScrollListener.reset();
                } else if (this.mTrackedListView instanceof RecyclerView) {
                    ((RecyclerView) this.mTrackedListView).setOnScrollListener(null);
                    this.mTrackedListRecyclerViewScrollListener.reset();
                } else if (this.mTrackedListView instanceof PlayScrollableContentView) {
                    ((PlayScrollableContentView) this.mTrackedListView).setOnScrollListener(null);
                    this.mTrackedListContentViewScrollListener.reset();
                }
                this.mSuppressIdleOnScroll = true;
            }
            this.mTrackedListView = listView;
            if (this.mTrackedListView == null) {
                return false;
            }
            boolean oldSuppressIdleOnScroll = this.mSuppressIdleOnScroll;
            if (!this.mSuppressIdleOnScroll) {
                this.mSuppressIdleOnScroll = this.mTrackedListView.isLayoutRequested();
            }
            if (this.mTrackedListView instanceof ListView) {
                ((ListView) this.mTrackedListView).setOnScrollListener(this.mTrackedListScrollListener);
            } else if (this.mTrackedListView instanceof RecyclerView) {
                ((RecyclerView) this.mTrackedListView).setOnScrollListener(this.mTrackedListRecyclerViewScrollListener);
            } else if (this.mTrackedListView instanceof PlayScrollableContentView) {
                ((PlayScrollableContentView) this.mTrackedListView).setOnScrollListener(this.mTrackedListContentViewScrollListener);
            }
            this.mSuppressIdleOnScroll = oldSuppressIdleOnScroll;
            if (hadTrackedListView) {
                syncListViews(true);
            }
            if (this.mPullToRefreshProvider != null) {
                PullToRefreshProvider pullToRefreshProvider = this.mPullToRefreshProvider;
                if (this.mHasViewPager) {
                    i = this.mViewPager.getCurrentItem();
                }
                this.mHasPullToRefresh = pullToRefreshProvider.supportsPullToRefresh(i);
            } else {
                this.mHasPullToRefresh = false;
            }
            return true;
        } else if (this.mTrackedListView != null) {
            return true;
        } else {
            return false;
        }
    }

    private void updateActiveListView() {
        if (!setActiveListView(getListView(1))) {
        }
    }

    private int relativeToAbsolute(int relativePosition) {
        int position = this.mViewPager.getCurrentItem();
        if (relativePosition == 0) {
            position--;
        }
        if (relativePosition == 2) {
            return position + 1;
        }
        return position;
    }

    private View getViewPagerView(int relativePosition) {
        int absolutePosition = relativeToAbsolute(relativePosition);
        if (this.mViewPager == null || this.mViewPager.getAdapter() == null || absolutePosition < 0 || absolutePosition >= this.mViewPager.getAdapter().getCount()) {
            return null;
        }
        for (int child = 0; child < this.mViewPager.getChildCount(); child++) {
            View view = this.mViewPager.getChildAt(child);
            Integer position = ViewPagerHelper.getChildViewPosition(this.mViewPager, view);
            if (position != null && position.intValue() == absolutePosition) {
                return view;
            }
        }
        return null;
    }

    public ViewGroup getCurrentListView() {
        return getListView(1);
    }

    private ViewGroup getListView(int relativePosition) {
        if (this.mViewPager == null && relativePosition == 1) {
            return validateListView(this.mContentContainer.findViewById(this.mListViewId));
        }
        View currentPagerView = getViewPagerView(relativePosition);
        if (currentPagerView != null) {
            return validateListView(currentPagerView.findViewById(this.mListViewId));
        }
        return null;
    }

    private static ViewGroup validateListView(View view) {
        if (view == null || (view instanceof ListView) || (view instanceof RecyclerView) || (view instanceof PlayScrollableContentView)) {
            return (ViewGroup) view;
        }
        throw new IllegalStateException("Found a view that isn't a ListView or a RecyclerView or a PlayScrollableContentView implementation");
    }

    private float getContentPosition() {
        return this.mAbsoluteY == -1 ? (float) this.mHeaderBottomMargin : (float) Math.max(this.mHeaderBottomMargin, this.mHeaderHeight - this.mAbsoluteY);
    }

    private float getFullFloatingHeaderHeight() {
        return ((float) getActionBarHeight()) + getVisibleTabBarHeight();
    }

    private float getNonScrollingFloatingHeaderHeight() {
        if (this.mLockHeader) {
            return getVisibleTabBarHeight() + ((float) getActionBarHeight());
        }
        switch (this.mHeaderMode) {
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                return 0.0f + getVisibleTabBarHeight();
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                return 0.0f + ((float) getActionBarHeight());
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                return 0.0f + (getVisibleTabBarHeight() + ((float) getActionBarHeight()));
            default:
                return 0.0f;
        }
    }

    private float getScrollingFloatingHeaderHeight() {
        if (this.mLockHeader) {
            return 0.0f;
        }
        switch (this.mHeaderMode) {
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                return getVisibleTabBarHeight() + ((float) getActionBarHeight());
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                return (float) getActionBarHeight();
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                return getVisibleTabBarHeight();
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                return 0.0f;
            default:
                throw new IllegalStateException();
        }
    }

    private float getVisibleTabBarHeight() {
        return (float) getTabBarHeight(getContext(), this.mTabMode);
    }

    private static int getActionBarHeight(Context context) {
        return context.getResources().getDimensionPixelSize(R.dimen.abc_action_bar_default_height_material);
    }

    public int getActionBarHeight() {
        return getActionBarHeight(getContext());
    }

    private float getFloatingHeaderElevation() {
        return (float) getResources().getDimensionPixelSize(R.dimen.play_header_list_floating_elevation);
    }

    private static int getTabBarHeight(Context context, int tabMode) {
        switch (tabMode) {
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                return context.getResources().getDimensionPixelSize(R.dimen.play_header_list_tab_strip_height);
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                return 0;
            default:
                throw new IllegalStateException();
        }
    }

    public int getTabBarHeight() {
        return getTabBarHeight(getContext(), this.mTabMode);
    }

    public int getBannerHeight() {
        return getResources().getDimensionPixelSize(R.dimen.play_header_list_banner_height);
    }

    private void updateTabBarVisibility() {
        switch (this.mTabMode) {
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                this.mTabBar.setVisibility(0);
                this.mTabBarTitleView.setVisibility(4);
                this.mTabStrip.setVisibility(0);
                return;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                this.mTabBar.setVisibility(0);
                this.mTabBarTitleView.setVisibility(0);
                this.mTabStrip.setVisibility(4);
                return;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                this.mTabBar.setVisibility(4);
                this.mTabBarTitleView.setVisibility(0);
                this.mTabStrip.setVisibility(0);
                return;
            default:
                throw new IllegalStateException("Unexpected tab mode: " + this.mTabMode);
        }
    }

    private void setupBackground(Configurator configurator) {
        if (this.mContentProtectionEnabled) {
            this.mAltPlayBackground.setBackgroundDrawable(configurator.getContentProtectionBackground(getContext()));
            this.mAltPlayBackground.setVisibility(0);
        }
    }

    private void setupViewPagerIfNeeded() {
        if (this.mHasViewPager && this.mViewPager == null) {
            ViewPager viewPager = (ViewPager) this.mContentContainer.findViewById(this.mViewPagerId);
            if (viewPager != null) {
                setViewPager(viewPager);
            }
        }
    }

    public void setViewPager(ViewPager viewPager) {
        this.mViewPager = viewPager;
        this.mTabStrip.setViewPager(viewPager);
    }

    public void notifyPagerAdapterChanged() {
        this.mTabStrip.notifyPagerAdapterChanged();
    }

    protected final View tryFindHeaderSpacerView(ViewGroup listView) {
        for (int i = 0; i < listView.getChildCount(); i++) {
            View child = listView.getChildAt(i);
            if (child.getId() == this.mSpacerId) {
                return child;
            }
        }
        return null;
    }

    public int tryGetCollectionViewAbsoluteY(ViewGroup listOrRecyclerView) {
        if (listOrRecyclerView instanceof ListView) {
            ListView listView = (ListView) listOrRecyclerView;
            int firstVisibleItem = listView.getFirstVisiblePosition();
            int visibleItemCount = listView.getChildCount();
            if (firstVisibleItem != 0 || visibleItemCount <= 0) {
                return -1;
            }
            return -listView.getChildAt(0).getTop();
        } else if (!(listOrRecyclerView instanceof RecyclerView) && !(listOrRecyclerView instanceof PlayScrollableContentView)) {
            return -1;
        } else {
            View headerSpacer = tryFindHeaderSpacerView(listOrRecyclerView);
            if (headerSpacer != null) {
                return -headerSpacer.getTop();
            }
            return -1;
        }
    }

    public int tryGetContentTop(ViewGroup listOrRecyclerView) {
        View headerSpacer = tryFindHeaderSpacerView(listOrRecyclerView);
        if (headerSpacer != null) {
            return headerSpacer.getBottom();
        }
        return -1;
    }

    void syncCurrentListViewOnNextScroll() {
        this.mPendingListSync = 2;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void layout() {
        /*
        r28 = this;
        r7 = r28.getBannerHeight();
        r22 = r28.getStatusBarHeight();
        r0 = r28;
        r0 = r0.mBannerFraction;
        r25 = r0;
        r26 = r7 + r22;
        r0 = r26;
        r0 = (float) r0;
        r26 = r0;
        r6 = r25 * r26;
        r0 = (float) r7;
        r25 = r0;
        r9 = r6 - r25;
        r0 = r28;
        r0 = r0.mBannerTextViewCompat;
        r25 = r0;
        r0 = r25;
        r0.setTranslationY(r9);
        r25 = 0;
        r0 = r22;
        r0 = (float) r0;
        r26 = r0;
        r26 = r6 - r26;
        r8 = java.lang.Math.max(r25, r26);
        r0 = r28;
        r0 = r0.mContentContainerCompat;
        r25 = r0;
        r0 = r25;
        r0.setTranslationY(r8);
        r12 = r8;
        r10 = r28.getContentPosition();
        r21 = r28.getScrollingFloatingHeaderHeight();
        r0 = r28;
        r0 = r0.mControlsAreFloating;
        r25 = r0;
        if (r25 == 0) goto L_0x01a2;
    L_0x0050:
        r0 = r28;
        r0 = r0.mHeaderHeight;
        r25 = r0;
        r0 = r25;
        r0 = -r0;
        r25 = r0;
        r0 = r25;
        r0 = (float) r0;
        r25 = r0;
        r26 = r28.getNonScrollingFloatingHeaderHeight();
        r25 = r25 + r26;
        r0 = r28;
        r0 = r0.mFloatingFraction;
        r26 = r0;
        r26 = r26 * r21;
        r25 = r25 + r26;
        r0 = r28;
        r0 = r0.mHeaderBottomMargin;
        r26 = r0;
        r0 = r26;
        r0 = (float) r0;
        r26 = r0;
        r25 = r25 + r26;
        r12 = r12 + r25;
    L_0x007f:
        r0 = r28;
        r0 = r0.mControlsContainerCompat;
        r25 = r0;
        r0 = r25;
        r0.setTranslationY(r12);
        r0 = r28;
        r0 = r0.mHeaderShadowCompat;
        r25 = r0;
        r0 = r25;
        r0.setTranslationY(r12);
        r0 = r28;
        r0 = r0.mAllowImmersiveBackground;
        r25 = r0;
        if (r25 == 0) goto L_0x00c5;
    L_0x009d:
        r0 = r28;
        r0 = r0.mStatusBarUnderlay;
        r26 = r0;
        r0 = r28;
        r0 = r0.mControlsAreFloating;
        r27 = r0;
        r0 = r28;
        r0 = r0.mHeaderBottomMargin;
        r25 = r0;
        r0 = r25;
        r0 = (float) r0;
        r25 = r0;
        r25 = r10 - r25;
        r25 = (r25 > r21 ? 1 : (r25 == r21 ? 0 : -1));
        if (r25 >= 0) goto L_0x01b3;
    L_0x00ba:
        r25 = 1;
    L_0x00bc:
        r0 = r26;
        r1 = r27;
        r2 = r25;
        r0.update(r6, r1, r2);
    L_0x00c5:
        r4 = r8;
        r0 = r28;
        r0 = r0.mHeaderMode;
        r25 = r0;
        if (r25 == 0) goto L_0x00dc;
    L_0x00ce:
        r0 = r28;
        r0 = r0.mHeaderMode;
        r25 = r0;
        r26 = 1;
        r0 = r25;
        r1 = r26;
        if (r0 != r1) goto L_0x00f2;
    L_0x00dc:
        r0 = r28;
        r0 = r0.mControlsAreFloating;
        r25 = r0;
        if (r25 == 0) goto L_0x01b7;
    L_0x00e4:
        r25 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
        r0 = r28;
        r0 = r0.mFloatingFraction;
        r26 = r0;
        r25 = r25 - r26;
        r25 = r25 * r21;
        r4 = r4 - r25;
    L_0x00f2:
        r0 = r28;
        r0 = r0.mToolbarContainerCompat;
        r25 = r0;
        r0 = r28;
        r0 = r0.mToolbarContainerA11yOffset;
        r26 = r0;
        r26 = r26 + r4;
        r25.setTranslationY(r26);
        r25 = USE_ANIMATIONS;
        if (r25 == 0) goto L_0x013b;
    L_0x0107:
        r0 = r28;
        r0 = r0.mHeroContainer;
        r25 = r0;
        r25 = r25.getMeasuredHeight();
        r0 = r25;
        r15 = (float) r0;
        r0 = r28;
        r0 = r0.mHeaderHeight;
        r25 = r0;
        r0 = r25;
        r0 = (float) r0;
        r25 = r0;
        r25 = r25 - r15;
        r0 = r28;
        r0 = r0.mHeaderBottomMargin;
        r26 = r0;
        r0 = r26;
        r0 = (float) r0;
        r26 = r0;
        r25 = r25 - r26;
        r26 = 1056964608; // 0x3f000000 float:0.5 double:5.222099017E-315;
        r19 = r25 * r26;
        r0 = r28;
        r0 = r0.mHeroAnimationMode;
        r25 = r0;
        switch(r25) {
            case 0: goto L_0x01df;
            case 1: goto L_0x01df;
            case 2: goto L_0x01fe;
            default: goto L_0x013b;
        };
    L_0x013b:
        r20 = r8;
        r0 = r28;
        r0 = r0.mContentProtectionEnabled;
        r25 = r0;
        if (r25 == 0) goto L_0x0173;
    L_0x0145:
        r0 = r28;
        r0 = r0.mTrackedListView;
        r25 = r0;
        if (r25 == 0) goto L_0x0166;
    L_0x014d:
        r0 = r28;
        r0 = r0.mTrackedListView;
        r25 = r0;
        r0 = r28;
        r1 = r25;
        r11 = r0.tryGetContentTop(r1);
        r25 = -1;
        r0 = r25;
        if (r11 == r0) goto L_0x0166;
    L_0x0161:
        r0 = (float) r11;
        r25 = r0;
        r20 = r20 + r25;
    L_0x0166:
        r0 = r28;
        r0 = r0.mAltPlayBackgroundCompat;
        r25 = r0;
        r0 = r25;
        r1 = r20;
        r0.setTranslationY(r1);
    L_0x0173:
        r5 = r8;
        r0 = r28;
        r0 = r0.mAbsoluteY;
        r25 = r0;
        r26 = -1;
        r0 = r25;
        r1 = r26;
        if (r0 != r1) goto L_0x0224;
    L_0x0182:
        r0 = r28;
        r0 = r0.mBackgroundContainer;
        r25 = r0;
        r26 = 4;
        r25.setVisibility(r26);
    L_0x018d:
        r28.updateHeaderShadow();
        r0 = r28;
        r0 = r0.mLayoutListener;
        r25 = r0;
        if (r25 == 0) goto L_0x01a1;
    L_0x0198:
        r0 = r28;
        r0 = r0.mLayoutListener;
        r25 = r0;
        r25.onPlayHeaderListLayoutChanged();
    L_0x01a1:
        return;
    L_0x01a2:
        r0 = r28;
        r0 = r0.mHeaderHeight;
        r25 = r0;
        r0 = r25;
        r0 = (float) r0;
        r25 = r0;
        r25 = r10 - r25;
        r12 = r12 + r25;
        goto L_0x007f;
    L_0x01b3:
        r25 = 0;
        goto L_0x00bc;
    L_0x01b7:
        r0 = r28;
        r0 = r0.mHeaderBottomMargin;
        r25 = r0;
        r0 = r25;
        r0 = (float) r0;
        r25 = r0;
        r25 = r10 - r25;
        r25 = r25 + r8;
        r26 = r28.getVisibleTabBarHeight();
        r25 = r25 - r26;
        r26 = r28.getActionBarHeight();
        r0 = r26;
        r0 = (float) r0;
        r26 = r0;
        r17 = r25 - r26;
        r0 = r17;
        r4 = java.lang.Math.min(r4, r0);
        goto L_0x00f2;
    L_0x01df:
        r16 = r12 + r19;
        r25 = r28.getActionBarHeight();
        r0 = r25;
        r0 = (float) r0;
        r25 = r0;
        r3 = r4 + r25;
        r25 = (r16 > r3 ? 1 : (r16 == r3 ? 0 : -1));
        if (r25 < 0) goto L_0x01fc;
    L_0x01f0:
        r14 = 1;
    L_0x01f1:
        r25 = 1;
        r0 = r28;
        r1 = r25;
        r0.setHeroElementVisible(r14, r1);
        goto L_0x013b;
    L_0x01fc:
        r14 = 0;
        goto L_0x01f1;
    L_0x01fe:
        r25 = 0;
        r26 = r15 + r12;
        r26 = r26 / r15;
        r13 = java.lang.Math.max(r25, r26);
        r25 = 0;
        r25 = (r13 > r25 ? 1 : (r13 == r25 ? 0 : -1));
        if (r25 <= 0) goto L_0x0221;
    L_0x020e:
        r25 = 1;
    L_0x0210:
        r0 = r25;
        r1 = r28;
        r1.mHeroVisible = r0;
        r25 = 0;
        r0 = r28;
        r1 = r25;
        r0.setHeroAnimationValue(r13, r1);
        goto L_0x013b;
    L_0x0221:
        r25 = 0;
        goto L_0x0210;
    L_0x0224:
        r24 = 0;
        r0 = r28;
        r0 = r0.mBackgroundContainer;
        r25 = r0;
        r25 = r25.getVisibility();
        r26 = 4;
        r0 = r25;
        r1 = r26;
        if (r0 != r1) goto L_0x0245;
    L_0x0238:
        r0 = r28;
        r0 = r0.mBackgroundContainer;
        r25 = r0;
        r26 = 0;
        r25.setVisibility(r26);
        r24 = 1;
    L_0x0245:
        r0 = r28;
        r0 = r0.mBackgroundContainer;
        r25 = r0;
        r25 = r25.getMeasuredHeight();
        r0 = r25;
        r0 = -r0;
        r25 = r0;
        r0 = r25;
        r0 = (float) r0;
        r25 = r0;
        r0 = r28;
        r0 = r0.mBackgroundParallaxRatio;
        r26 = r0;
        r18 = r25 / r26;
        r0 = r28;
        r0 = r0.mAbsoluteY;
        r25 = r0;
        r0 = r25;
        r0 = -r0;
        r25 = r0;
        r0 = r25;
        r0 = (float) r0;
        r25 = r0;
        r0 = r28;
        r0 = r0.mBackgroundParallaxRatio;
        r26 = r0;
        r25 = r25 * r26;
        r5 = r5 + r25;
        r0 = r18;
        r5 = java.lang.Math.max(r0, r5);
        r0 = r28;
        r0 = r0.mBackgroundContainerCompat;
        r25 = r0;
        r0 = r25;
        r0.setTranslationY(r5);
        r0 = r28;
        r0 = r0.mBackgroundContainer;
        r25 = r0;
        r25 = r25.getMeasuredHeight();
        r0 = r25;
        r0 = (float) r0;
        r25 = r0;
        r25 = r25 + r5;
        r23 = r25 - r8;
        if (r24 == 0) goto L_0x018d;
    L_0x02a1:
        r0 = r28;
        r0 = r0.mBackgroundFadeInOffsetThresholdPx;
        r25 = r0;
        r25 = (r23 > r25 ? 1 : (r23 == r25 ? 0 : -1));
        if (r25 <= 0) goto L_0x018d;
    L_0x02ab:
        r0 = r28;
        r0 = r0.mBackgroundContainerCompat;
        r25 = r0;
        r26 = 0;
        r25.setAlpha(r26);
        r0 = r28;
        r0 = r0.mBackgroundContainerCompat;
        r25 = r0;
        r26 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
        r27 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        r25.animateAlpha(r26, r27);
        goto L_0x018d;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.play.headerlist.PlayHeaderListLayout.layout():void");
    }

    void onScroll(int scrollState, int deltaY, int absoluteY) {
        boolean z = true;
        if (this.mPullToRefreshProvider != null) {
            setPullToRefreshEnabled(absoluteY == 0);
        }
        if (!this.mSuppressIdleOnScroll || scrollState != 0) {
            this.mAbsoluteY = absoluteY;
            switch (scrollState) {
                case com.google.android.wallet.instrumentmanager.R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                    this.mLastScrollWasDown = true;
                    break;
                case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    if (((float) deltaY) > 0.0f) {
                        z = false;
                    }
                    this.mLastScrollWasDown = z;
                    break;
            }
            if (!updateFloatingState() && this.mControlsAreFloating) {
                if (getScrollingFloatingHeaderHeight() == 0.0f) {
                    this.mFloatingFraction = 1.0f;
                } else {
                    this.mFloatingFraction -= ((float) deltaY) / getScrollingFloatingHeaderHeight();
                    this.mFloatingFraction = Math.min(1.0f, Math.max(0.0f, this.mFloatingFraction));
                }
            }
            layout();
        }
    }

    private boolean shouldFloat() {
        boolean shouldFloat = this.mControlsAreFloating;
        if (this.mControlsAreFloating) {
            float snapThreshold = (((float) this.mHeaderHeight) - getFullFloatingHeaderHeight()) - ((float) this.mHeaderBottomMargin);
            if (this.mAbsoluteY == -1 || Math.round((float) this.mAbsoluteY) > Math.round(snapThreshold)) {
                return true;
            }
            return false;
        }
        shouldFloat = Math.round((float) this.mAbsoluteY) >= Math.round(((float) (this.mHeaderHeight - this.mHeaderBottomMargin)) - getNonScrollingFloatingHeaderHeight()) || this.mAbsoluteY == -1;
        return shouldFloat;
    }

    private boolean updateFloatingState() {
        boolean shouldFloat = shouldFloat();
        if (shouldFloat == this.mControlsAreFloating) {
            return false;
        }
        setControlsFloating(shouldFloat, true);
        return true;
    }

    private boolean isTrackedListViewReady() {
        return isListViewReady(this.mTrackedListView);
    }

    protected boolean isListViewReady(ViewGroup listView) {
        if (listView == null || getAdapterCount(listView) <= 1) {
            return false;
        }
        if (listView.getChildCount() > 1) {
            return true;
        }
        if (listView.getChildCount() != 1) {
            return false;
        }
        if (listView.getChildAt(0).getId() == this.mSpacerId) {
            return false;
        }
        return true;
    }

    private void updateContentPosition(boolean doLayout) {
        if (isTrackedListViewReady()) {
            this.mAbsoluteY = tryGetCollectionViewAbsoluteY(this.mTrackedListView);
            if (this.mPullToRefreshProvider != null) {
                setPullToRefreshEnabled(this.mAbsoluteY == 0);
            }
            updateFloatingState();
            if (doLayout) {
                layout();
            }
        }
    }

    void onScrollStateChanged(int newState) {
        this.mHandler.removeCallbacks(this.mSnapControlsUpIfNeededRunnable);
        this.mHandler.removeCallbacks(this.mSnapControlsDownIfNeededRunnable);
        if (newState == 0) {
            boolean snapDown;
            boolean mustSnapDown = getContentPosition() > ((float) this.mHeaderBottomMargin);
            if (this.mLastScrollWasDown) {
                float scrollUpThreshold = ((float) getActionBarHeight()) * 0.5f;
                float visibleHeaderHeight = getVisibleHeaderHeight();
                if (mustSnapDown || visibleHeaderHeight >= scrollUpThreshold) {
                    snapDown = true;
                } else {
                    snapDown = false;
                }
            } else {
                snapDown = mustSnapDown;
            }
            this.mHandler.postDelayed(snapDown ? this.mSnapControlsDownIfNeededRunnable : this.mSnapControlsUpIfNeededRunnable, 50);
            syncListViews(false);
        }
    }

    private void setHeroElementVisible(boolean visible, boolean animate) {
        if (this.mHeroVisible != visible) {
            this.mHeroVisible = visible;
            setHeroAnimationValue(this.mHeroVisible ? 1.0f : 0.0f, animate);
        }
    }

    public boolean getHeroElementVisible() {
        return this.mHeroVisible;
    }

    private void setHeroAnimationValue(float value, boolean animate) {
        if (animate) {
            switch (this.mHeroAnimationMode) {
                case com.google.android.wallet.instrumentmanager.R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                    this.mHeroContainerCompat.animateScale(value, 100);
                    return;
                case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    this.mHeroContainerCompat.animateAlpha(value, 200);
                    return;
                default:
                    return;
            }
        }
        switch (this.mHeroAnimationMode) {
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                this.mHeroContainerCompat.setScale(value);
                return;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                this.mHeroContainerCompat.setAlpha(value);
                return;
            default:
                return;
        }
    }

    private void snapControlsIfNeeded(boolean snapDown, boolean snapEvenIfInvisible, boolean animate) {
        if (this.mHeaderMode != 3) {
            this.mHandler.removeCallbacks(this.mSnapControlsUpIfNeededRunnable);
            this.mHandler.removeCallbacks(this.mSnapControlsDownIfNeededRunnable);
            float visibleControlHeight = getVisibleHeaderHeight();
            float minVisibleControlHeightForSnap = getNonScrollingFloatingHeaderHeight();
            float maxVisibleControlHeightForSnap = getFullFloatingHeaderHeight();
            if ((snapEvenIfInvisible || visibleControlHeight > minVisibleControlHeightForSnap) && visibleControlHeight < maxVisibleControlHeightForSnap) {
                float newFloatingFraction = snapDown ? 1.0f : 0.0f;
                if (!this.mControlsAreFloating) {
                    if (visibleControlHeight < maxVisibleControlHeightForSnap) {
                        setControlsFloating(true, animate);
                    } else {
                        return;
                    }
                }
                if (!animate) {
                    setFloatingFraction(newFloatingFraction, true);
                } else if (VERSION.SDK_INT >= 11) {
                    getFloatAnimator("floatingFraction", getFloatingFraction(), newFloatingFraction).setDuration(200).start();
                } else {
                    FloatingFractionAnimation animation = new FloatingFractionAnimation(getFloatingFraction(), newFloatingFraction);
                    animation.setDuration(200);
                    startAnimation(animation);
                }
            }
        }
    }

    protected void setFloatingFraction(float fraction) {
        setFloatingFraction(fraction, true);
    }

    private final void setFloatingFraction(float fraction, boolean layout) {
        if (this.mControlsAreFloating && this.mFloatingFraction != fraction) {
            this.mFloatingFraction = fraction;
            if (layout) {
                layout();
                syncListViews(false);
            }
        }
    }

    protected final float getFloatingFraction() {
        if (this.mControlsAreFloating) {
            return this.mFloatingFraction;
        }
        return 0.0f;
    }

    private Drawable makeNonFloatingBackground() {
        return new ColorDrawable(0);
    }

    private void setControlsFloating(boolean floating, boolean animate) {
        if (this.mControlsAreFloating != floating) {
            if (floating) {
                float visibleScrollingControlHeight = getVisibleHeaderHeight() - getNonScrollingFloatingHeaderHeight();
                float scrollingFloatingHeaderHeight = getScrollingFloatingHeaderHeight();
                if (scrollingFloatingHeaderHeight == 0.0f) {
                    this.mFloatingFraction = 1.0f;
                } else {
                    this.mFloatingFraction = Math.max(0.0f, Math.min(1.0f, visibleScrollingControlHeight / scrollingFloatingHeaderHeight));
                }
            } else {
                this.mFloatingFraction = 0.0f;
            }
            this.mControlsAreFloating = floating;
            if (!this.mAlwaysUseFloatingBackground) {
                if (this.mControlsAreFloating) {
                    setControlsBackground(this.mFloatingControlsBackground, animate);
                } else {
                    setControlsBackground(makeNonFloatingBackground(), animate);
                }
            }
            if (this.mAutoHideToolbarTitle) {
                setActionBarTitleVisibility(this.mControlsAreFloating, animate);
            }
            updateHeaderShadow();
            updateTabPadding(animate);
            updateTabContrast();
        }
    }

    private void updateTabPadding(boolean animate) {
        if (this.mTabMode == 0) {
            boolean useFloatingTabPadding;
            switch (this.mTabPaddingMode) {
                case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    useFloatingTabPadding = true;
                    break;
                case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    useFloatingTabPadding = false;
                    break;
                default:
                    useFloatingTabPadding = this.mControlsAreFloating;
                    break;
            }
            this.mTabStrip.setUseFloatingTabPadding(useFloatingTabPadding, animate);
        }
    }

    private void updateTabContrast() {
        boolean useHighContrast = false;
        switch (this.mTabA11yMode) {
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                if (this.mControlsAreFloating || this.mAlwaysUseFloatingBackground) {
                    useHighContrast = true;
                }
                break;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                if (!(this.mControlsAreFloating || this.mAlwaysUseFloatingBackground)) {
                    useHighContrast = true;
                }
                break;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                useHighContrast = true;
                break;
            default:
                useHighContrast = false;
                break;
        }
        this.mTabStrip.setUseHighContrast(useHighContrast);
    }

    private void setControlsBackground(Drawable toDrawable, boolean animate) {
        if (animate && USE_ANIMATIONS) {
            Drawable fromDrawable = this.mControlsContainer.getBackground();
            if (fromDrawable == null) {
                fromDrawable = makeNonFloatingBackground();
            }
            if (toDrawable == null) {
                toDrawable = makeNonFloatingBackground();
            }
            if (fromDrawable != toDrawable) {
                TransitionDrawable newBackground = new TransitionDrawable(new Drawable[]{fromDrawable, toDrawable}) {
                    public void getOutline(Outline outline) {
                        outline.setRect(getBounds());
                        outline.setAlpha(1.0f);
                    }
                };
                newBackground.setCrossFadeEnabled(true);
                newBackground.startTransition(300);
                this.mControlsContainer.setBackgroundDrawable(newBackground);
                return;
            }
            return;
        }
        this.mControlsContainer.setBackgroundDrawable(toDrawable);
    }

    private boolean isBackgroundVisible() {
        if (this.mBackgroundContainer.getVisibility() != 0) {
            return false;
        }
        if (getMeasuredHeight() == 0) {
            return true;
        }
        if ((this.mControlsAreFloating || this.mAlwaysUseFloatingBackground) && Math.max(0.0f, Math.max(0.0f, ((float) this.mBackgroundContainer.getMeasuredHeight()) + this.mBackgroundContainerCompat.getTranslationY()) - getVisibleHeaderHeight()) <= 0.0f) {
            return false;
        }
        return true;
    }

    private void updateHeaderShadow() {
        boolean headerShadowShouldBeVisible = true;
        switch (this.mHeaderShadowMode) {
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                if (!this.mControlsAreFloating || getVisibleHeaderHeight() <= 0.0f) {
                    headerShadowShouldBeVisible = false;
                }
                break;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                headerShadowShouldBeVisible = false;
                break;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                if (getVisibleHeaderHeight() <= 0.0f) {
                    headerShadowShouldBeVisible = false;
                }
                break;
            default:
                if (!(this.mControlsAreFloating || this.mAlwaysUseFloatingBackground) || getVisibleHeaderHeight() <= 0.0f || isBackgroundVisible()) {
                    headerShadowShouldBeVisible = false;
                    break;
                }
        }
        setHeaderShadowVisible(headerShadowShouldBeVisible);
    }

    private void setHeaderShadowVisible(boolean visible) {
        int delay = 0;
        if (this.mHeaderShadowVisible != visible) {
            this.mHeaderShadowVisible = visible;
            if (SUPPORT_ELEVATION) {
                boolean delayAnimation;
                int animationDuration;
                float floatingFraction = getFloatingFraction();
                if (!visible || ((double) floatingFraction) <= 0.25d) {
                    delayAnimation = false;
                } else {
                    delayAnimation = true;
                }
                float desiredElevation = visible ? getFloatingHeaderElevation() : 0.0f;
                if (visible) {
                    animationDuration = 150;
                } else {
                    animationDuration = 0;
                }
                if (delayAnimation) {
                    delay = 100;
                }
                this.mControlsContainerCompat.animateZ(desiredElevation, animationDuration, delay);
                this.mToolbarContainerCompat.animateZ(desiredElevation, animationDuration, delay);
                this.mBannerTextView.animate().z(desiredElevation).setStartDelay((long) delay).setDuration((long) animationDuration);
                if (this.mAllowImmersiveBackground) {
                    this.mStatusBarUnderlay.animate().z(desiredElevation).setStartDelay((long) delay).setDuration((long) animationDuration);
                    return;
                }
                return;
            }
            View view = this.mHeaderShadow;
            if (!visible) {
                delay = 4;
            }
            view.setVisibility(delay);
        }
    }

    @Deprecated
    public View getActionBarView() {
        if (this.mUseBuiltInToolbar) {
            return getToolbar();
        }
        return getToolbarContainer();
    }

    public View getToolbarContainer() {
        if (this.mToolbarContainer != null) {
            return this.mToolbarContainer;
        }
        if (!this.mUseBuiltInToolbar) {
            return ((Activity) getContext()).getWindow().findViewById(R.id.action_bar_container);
        }
        View container = findViewById(R.id.toolbar_container);
        container.setVisibility(0);
        return container;
    }

    public Toolbar getToolbar() {
        if (this.mToolbar != null) {
            return this.mToolbar;
        }
        if (!this.mUseBuiltInToolbar) {
            return (Toolbar) ((Activity) getContext()).getWindow().findViewById(R.id.action_bar);
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.play_header_toolbar);
        ((ActionBarActivity) getContext()).setSupportActionBar(toolbar);
        return toolbar;
    }

    private void setActionBarTitleVisibility(boolean visible, boolean animate) {
        float toAlpha = visible ? 1.0f : 0.0f;
        if (!animate) {
            setActionBarTitleAlpha(toAlpha);
        } else if (toAlpha == getActionBarTitleAlpha()) {
        } else {
            if (VERSION.SDK_INT >= 11) {
                getFloatAnimator("actionBarTitleAlpha", getActionBarTitleAlpha(), toAlpha).setDuration(200).start();
                return;
            }
            ActionBarTitleAlphaAnimation anim = new ActionBarTitleAlphaAnimation(getActionBarTitleAlpha(), toAlpha);
            anim.setDuration(200);
            startAnimation(anim);
        }
    }

    private void syncListViews(boolean includeCurrent) {
        int i = 2;
        if (this.mViewPager != null) {
            boolean retryCurrent = false;
            if (includeCurrent) {
                retryCurrent = syncViewPagerListView(1);
                if (!retryCurrent) {
                    this.mSuppressIdleOnScroll = false;
                }
            }
            if ((retryCurrent | syncViewPagerListView(0)) | syncViewPagerListView(2)) {
                if (!retryCurrent) {
                    i = 1;
                }
                this.mPendingListSync = i;
                return;
            }
            this.mPendingListSync = 0;
        }
    }

    private boolean syncViewPagerListView(int relativePosition) {
        boolean z = true;
        if (this.mViewPager == null || this.mViewPager.getAdapter() == null) {
            return false;
        }
        int position = relativeToAbsolute(relativePosition);
        if (position < 0 || position >= this.mViewPager.getAdapter().getCount()) {
            return false;
        }
        ViewGroup listView = getListView(relativePosition);
        if (relativePosition != 1) {
            z = false;
        }
        return syncListView(listView, z);
    }

    private boolean syncListView(ViewGroup listView, boolean isCurrent) {
        if (!isListViewReady(listView)) {
            return true;
        }
        int contentTop = tryGetContentTop(listView);
        if (contentTop != -1) {
            int scrollBy = contentTop - getDesiredContentTop();
            if (this.mControlsAreFloating && scrollBy < 0) {
                return false;
            }
            if (Math.abs(scrollBy) >= 1 && ViewCompat.canScrollVertically(listView, scrollBy)) {
                this.mSuppressIdleOnScroll = true;
                scrollListViewBy(listView, scrollBy);
                this.mSuppressIdleOnScroll = false;
                if (isCurrent) {
                    this.mUpdateContentPositionOnLayout = true;
                }
            } else if (isCurrent) {
                updateContentPosition(true);
            }
            return false;
        } else if (this.mControlsAreFloating) {
            return false;
        } else {
            this.mSuppressIdleOnScroll = true;
            scrollListViewToTop(listView);
            this.mSuppressIdleOnScroll = false;
            return true;
        }
    }

    private int getDesiredContentTop() {
        boolean alignToFloatingHeight = this.mControlsAreFloating && (this.mHeaderMode == 0 || this.mHeaderMode == 2);
        return ((int) (alignToFloatingHeight ? getFullFloatingHeaderHeight() : getVisibleHeaderHeight())) + this.mHeaderBottomMargin;
    }

    private static void scrollListViewBy(ViewGroup view, int scrollBy) {
        if (view instanceof ListView) {
            ListView listView = (ListView) view;
            if (VERSION.SDK_INT >= 19) {
                listView.scrollListBy(scrollBy);
            } else {
                listView.smoothScrollBy(scrollBy, 0);
            }
        } else if (view instanceof RecyclerView) {
            ((RecyclerView) view).scrollBy(0, scrollBy);
        } else if (view instanceof PlayScrollableContentView) {
            ((PlayScrollableContentView) view).scrollBy(0, scrollBy);
        }
    }

    private static void scrollListViewToTop(ViewGroup view) {
        if (view instanceof ListView) {
            ((ListView) view).setSelectionFromTop(0, 0);
        } else if (view instanceof RecyclerView) {
            ((RecyclerView) view).scrollToPosition(0);
        } else if (view instanceof PlayScrollableContentView) {
            ((PlayScrollableContentView) view).scrollTo(0, 0);
        }
    }

    private static int getAdapterCount(ViewGroup view) {
        if (view instanceof ListView) {
            ListAdapter adapter = ((ListView) view).getAdapter();
            if (adapter == null) {
                return 0;
            }
            return adapter.getCount();
        } else if (view instanceof RecyclerView) {
            Adapter<?> adapter2 = ((RecyclerView) view).getAdapter();
            if (adapter2 != null) {
                return adapter2.getItemCount();
            }
            return 0;
        } else if (view instanceof PlayScrollableContentView) {
            android.widget.Adapter adapter3 = ((PlayScrollableContentView) view).getAdapter();
            if (adapter3 != null) {
                return adapter3.getCount();
            }
            return 0;
        } else {
            throw new IllegalStateException("Unexpected listview type: " + view);
        }
    }

    private void showBanner(boolean animate) {
        if (USE_ANIMATIONS) {
            setBannerFraction(1.0f, animate);
            setContentContainerMargins(0, getBannerHeight());
        } else if (!this.mBannerVisibleGB) {
            this.mBannerVisibleGB = true;
            this.mBannerTextView.setVisibility(0);
            setContentContainerMargins(getBannerHeight(), 0);
        }
    }

    private void hideBanner(boolean animate) {
        if (USE_ANIMATIONS) {
            setBannerFraction(0.0f, animate);
            setContentContainerMargins(0, 0);
        } else if (this.mBannerVisibleGB) {
            this.mBannerVisibleGB = false;
            this.mBannerTextView.setVisibility(8);
            setContentContainerMargins(0, 0);
        }
    }

    private void setBannerFraction(float bannerFraction, boolean animate) {
        if (this.mBannerFraction != bannerFraction) {
            if (animate && USE_ANIMATIONS) {
                getFloatAnimator("bannerFraction", getBannerFraction(), bannerFraction).setDuration(200).start();
                return;
            }
            this.mBannerFraction = bannerFraction;
            layout();
        }
    }

    protected final void setBannerFraction(float bannerFraction) {
        setBannerFraction(bannerFraction, false);
    }

    protected final float getBannerFraction() {
        return this.mBannerFraction;
    }

    private void setControlsContainerHeight(int height) {
        FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) this.mControlsContainer.getLayoutParams();
        lp.height = height;
        this.mControlsContainer.setLayoutParams(lp);
    }

    private void updateHeaderShadowTopMargin() {
        setHeaderShadowTopMargin((this.mHeaderHeight - this.mHeaderBottomMargin) - 1);
    }

    private void setHeaderShadowTopMargin(int topMargin) {
        FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) this.mHeaderShadow.getLayoutParams();
        lp.setMargins(0, topMargin, 0, 0);
        this.mHeaderShadow.setLayoutParams(lp);
    }

    private void setContentContainerMargins(int topMargin, int bottomMargin) {
        FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) this.mContentContainer.getLayoutParams();
        lp.setMargins(0, getStatusBarHeight() + topMargin, 0, bottomMargin);
        this.mContentContainer.setLayoutParams(lp);
    }

    private ObjectAnimator getFloatAnimator(String propertyName, float startValue, float endValue) {
        ObjectAnimator animator = (ObjectAnimator) this.mAnimatorMap.get(propertyName);
        if (animator != null) {
            animator.cancel();
        }
        animator = ObjectAnimator.ofFloat(this, propertyName, new float[]{startValue, endValue});
        this.mAnimatorMap.put(propertyName, animator);
        return animator;
    }
}
