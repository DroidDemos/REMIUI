package com.google.android.play.headerlist;

import android.app.Activity;
import android.content.Context;
import android.database.DataSetObserver;
import android.os.Build.VERSION;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.TextView;
import com.google.android.libraries.bind.bidi.BidiPagingHelper;
import com.google.android.play.R;
import com.google.android.play.headerlist.PlayHeaderListLayout.OnTabSelectedListener;
import java.lang.ref.WeakReference;

public class PlayHeaderListTabStrip extends FrameLayout {
    private boolean mAnimateContainerPadding;
    private boolean mEnablePagerScrollSync;
    private OnPageChangeListener mExternalPageChangeListener;
    private int mLastScrollto;
    private int mMaxTabWidthPx;
    private OnTabSelectedListener mOnTabSelectedListener;
    private final PageListener mPageListener;
    private ViewPager mPager;
    private HorizontalScrollView mScrollView;
    private float mSmoothScrollThresholdPx;
    private PlayHeaderListTabContainer mTabContainer;
    private boolean mUseHighContrast;
    private WeakReference<PagerAdapter> mWatchingAdapter;

    private class PageListener extends DataSetObserver implements OnPageChangeListener {
        private int mScrollState;

        private PageListener() {
        }

        public void onPageScrolled(int visualPosition, float visualPositionOffset, int visualPositionOffsetPixels) {
            if (PlayHeaderListTabStrip.this.mExternalPageChangeListener != null) {
                PlayHeaderListTabStrip.this.mExternalPageChangeListener.onPageScrolled(visualPosition, visualPositionOffset, visualPositionOffsetPixels);
            }
            if (PlayHeaderListTabStrip.this.mEnablePagerScrollSync) {
                int tabStripChildCount = PlayHeaderListTabStrip.this.mTabContainer.getChildCount();
                if (tabStripChildCount != 0 && visualPosition >= 0 && visualPosition < tabStripChildCount) {
                    PlayHeaderListTabStrip.this.mTabContainer.onPageScrolled(visualPosition, visualPositionOffset);
                    View selectedTitle = PlayHeaderListTabStrip.this.mTabContainer.getChildAt(visualPosition);
                    int selectedTitleWidth = selectedTitle == null ? 0 : selectedTitle.getWidth();
                    View nextTitle = PlayHeaderListTabStrip.this.mTabContainer.getChildAt(visualPosition + 1);
                    PlayHeaderListTabStrip.this.scrollToVisualPosition(visualPosition, (int) ((((float) (selectedTitleWidth + (nextTitle == null ? 0 : nextTitle.getWidth()))) * visualPositionOffset) * 0.5f), false);
                }
            }
        }

        public void onPageSelected(int visualPosition) {
            if (PlayHeaderListTabStrip.this.mExternalPageChangeListener != null) {
                PlayHeaderListTabStrip.this.mExternalPageChangeListener.onPageSelected(visualPosition);
            }
            PlayHeaderListTabStrip.this.mTabContainer.onPageSelected(visualPosition);
            PlayHeaderListTabStrip.this.updateSelectedTab(false);
        }

        public void onPageScrollStateChanged(int state) {
            this.mScrollState = state;
            if (PlayHeaderListTabStrip.this.mExternalPageChangeListener != null) {
                PlayHeaderListTabStrip.this.mExternalPageChangeListener.onPageScrollStateChanged(state);
            }
            if (this.mScrollState == 0) {
                PlayHeaderListTabStrip.this.mEnablePagerScrollSync = true;
            }
        }

        public void onChanged() {
            PlayHeaderListTabStrip.this.updateTabs();
        }
    }

    public PlayHeaderListTabStrip(Context context) {
        this(context, null, 0);
    }

    public PlayHeaderListTabStrip(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PlayHeaderListTabStrip(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mPageListener = new PageListener();
        this.mEnablePagerScrollSync = true;
        this.mSmoothScrollThresholdPx = getResources().getDisplayMetrics().density * 5.0f;
    }

    void setExternalOnPageChangeListener(OnPageChangeListener onPageChangeListener) {
        this.mExternalPageChangeListener = onPageChangeListener;
    }

    void setUseHighContrast(boolean useHighContrast) {
        if (this.mUseHighContrast != useHighContrast) {
            this.mUseHighContrast = useHighContrast;
            int tabStripChildCount = this.mTabContainer.getChildCount();
            for (int i = 0; i < tabStripChildCount; i++) {
                applyTabContrastMode(this.mTabContainer.getChildAt(i), this.mUseHighContrast);
            }
        }
    }

    void setUseFloatingTabPadding(boolean useFloatingTabPadding, boolean animate) {
        this.mTabContainer.setUseFloatingTabPadding(useFloatingTabPadding);
        this.mAnimateContainerPadding = animate;
        updateSelectedTab(false);
    }

    void setOnTabSelectedListener(OnTabSelectedListener listener) {
        this.mOnTabSelectedListener = listener;
    }

    protected void onFinishInflate() {
        getSubViewReferences();
        this.mTabContainer.setSelectedIndicatorColor(getResources().getColor(R.color.play_header_list_tab_underline_color));
    }

    void getSubViewReferences() {
        this.mScrollView = (HorizontalScrollView) findViewById(R.id.play_header_list_tab_scroll);
        this.mTabContainer = (PlayHeaderListTabContainer) findViewById(R.id.play_header_list_tab_container);
    }

    public void setViewPager(ViewPager viewPager) {
        if (this.mPager != null) {
            this.mPager.setOnPageChangeListener(null);
        }
        this.mPager = viewPager;
        if (this.mPager != null) {
            this.mPager.setOnPageChangeListener(this.mPageListener);
        }
        notifyPagerAdapterChanged();
    }

    private void updateAdapter(PagerAdapter oldAdapter, PagerAdapter newAdapter) {
        if (oldAdapter != null) {
            oldAdapter.unregisterDataSetObserver(this.mPageListener);
            this.mWatchingAdapter = null;
        }
        if (newAdapter != null) {
            newAdapter.registerDataSetObserver(this.mPageListener);
            this.mWatchingAdapter = new WeakReference(newAdapter);
        }
        updateTabs();
    }

    public void notifyPagerAdapterChanged() {
        PagerAdapter pagerAdapter = null;
        PagerAdapter adapter = this.mPager == null ? null : this.mPager.getAdapter();
        if (this.mWatchingAdapter != null) {
            pagerAdapter = (PagerAdapter) this.mWatchingAdapter.get();
        }
        updateAdapter(pagerAdapter, adapter);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        if (width > 0) {
            this.mTabContainer.setStripWidth(width);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private void updateTabs() {
        this.mTabContainer.removeAllViews();
        PagerAdapter adapter = this.mPager == null ? null : this.mPager.getAdapter();
        if (adapter != null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            int tabCount = adapter.getCount();
            for (int visualPosition = 0; visualPosition < tabCount; visualPosition++) {
                int logicalPosition = BidiPagingHelper.getLogicalPosition(adapter, visualPosition);
                View tabView = makeTabView(inflater, this.mTabContainer, logicalPosition);
                bindTabViewData(tabView, adapter, logicalPosition);
                this.mTabContainer.addView(tabView);
            }
            this.mTabContainer.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
                public void onGlobalLayout() {
                    PlayHeaderListTabStrip.this.scrollToVisualPosition(PlayHeaderListTabStrip.this.mPager.getCurrentItem(), 0, false);
                    PlayHeaderListTabStrip.this.mTabContainer.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                }
            });
            updateSelectedTab(false);
            this.mTabContainer.invalidateTabPadding();
        }
    }

    protected View makeTabView(LayoutInflater inflater, ViewGroup parent, int logicalPosition) {
        TextView tabView = (TextView) inflater.inflate(R.layout.play_header_list_tab_text, parent, false);
        tabView.setMaxWidth(getMaxTabWidth());
        applyTabContrastMode(tabView, this.mUseHighContrast);
        return tabView;
    }

    protected void bindTabViewData(View tabView, PagerAdapter adapter, int logicalPosition) {
        TextView title = (TextView) tabView;
        title.setText(adapter.getPageTitle(logicalPosition));
        title.setOnClickListener(makeOnTabClickListener(BidiPagingHelper.getVisualPosition(adapter, logicalPosition)));
    }

    protected void applyTabContrastMode(View tabView, boolean useHighContrast) {
        TextView title = (TextView) tabView;
        if (useHighContrast) {
            title.setBackgroundResource(R.drawable.play_header_list_tab_high_contrast_bg);
            title.setTextColor(-1);
            return;
        }
        title.setBackgroundResource(0);
        title.setTextColor(getResources().getColorStateList(R.color.play_header_list_tab_text_color));
    }

    protected int getMaxTabWidth() {
        if (this.mMaxTabWidthPx == 0) {
            DisplayMetrics metrics = new DisplayMetrics();
            ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(metrics);
            this.mMaxTabWidthPx = metrics.widthPixels;
        }
        return this.mMaxTabWidthPx;
    }

    protected final OnClickListener makeOnTabClickListener(final int pagerPosition) {
        return new OnClickListener() {
            public void onClick(View v) {
                if (PlayHeaderListTabStrip.this.mOnTabSelectedListener != null) {
                    PlayHeaderListTabStrip.this.mOnTabSelectedListener.onBeforeTabSelected(pagerPosition);
                }
                PlayHeaderListTabStrip.this.mEnablePagerScrollSync = false;
                PlayHeaderListTabStrip.this.updateSelectedTab(pagerPosition, true);
                PlayHeaderListTabStrip.this.mPager.setCurrentItem(pagerPosition);
                if (PlayHeaderListTabStrip.this.mOnTabSelectedListener != null) {
                    PlayHeaderListTabStrip.this.mOnTabSelectedListener.onTabSelected(pagerPosition);
                }
            }
        };
    }

    public String getCurrentTabTitle() {
        if (this.mPager == null || this.mPager.getAdapter() == null) {
            return null;
        }
        return (String) this.mPager.getAdapter().getPageTitle(this.mPager.getCurrentItem());
    }

    private void scrollToVisualPosition(int visualPosition, int extraOffset, boolean animate) {
        if (this.mScrollView != null) {
            int tabStripChildCount = this.mTabContainer.getChildCount();
            if (tabStripChildCount != 0 && visualPosition >= 0 && visualPosition < tabStripChildCount) {
                View selectedChild = this.mTabContainer.getChildAt(visualPosition);
                if (selectedChild != null && selectedChild.getMeasuredWidth() != 0) {
                    int targetScrollX = ((selectedChild.getLeft() + extraOffset) - (getWidth() / 2)) + (selectedChild.getWidth() / 2);
                    if (targetScrollX != this.mLastScrollto) {
                        boolean smoothScroll;
                        if (((float) Math.abs(targetScrollX - this.mScrollView.getScrollX())) > this.mSmoothScrollThresholdPx) {
                            smoothScroll = true;
                        } else {
                            smoothScroll = false;
                        }
                        if (smoothScroll && animate) {
                            this.mScrollView.smoothScrollTo(targetScrollX, 0);
                        } else {
                            this.mScrollView.scrollTo(targetScrollX, 0);
                        }
                        this.mLastScrollto = targetScrollX;
                    }
                }
            }
        }
    }

    protected void updateSelectedTab(boolean animate) {
        if (this.mPager != null) {
            updateSelectedTab(this.mPager.getCurrentItem(), animate);
        }
    }

    protected void updateSelectedTab(int visualPosition, boolean animate) {
        scrollToVisualPosition(visualPosition, 0, animate);
        for (int i = 0; i < this.mTabContainer.getChildCount(); i++) {
            boolean z;
            View childAt = this.mTabContainer.getChildAt(i);
            if (i == visualPosition) {
                z = true;
            } else {
                z = false;
            }
            childAt.setSelected(z);
        }
    }

    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        int oldPosition;
        if (this.mAnimateContainerPadding) {
            oldPosition = getSelectedTabPosition();
        } else {
            oldPosition = 0;
        }
        super.onLayout(changed, left, top, right, bottom);
        if (this.mAnimateContainerPadding) {
            updateSelectedTab(false);
            if (VERSION.SDK_INT >= 12) {
                int newPosition = getSelectedTabPosition();
                if (newPosition != oldPosition) {
                    this.mTabContainer.setTranslationX((float) (-(newPosition - oldPosition)));
                    this.mTabContainer.animate().translationX(0.0f).setDuration(200);
                }
            }
        }
    }

    private int getSelectedTabPosition() {
        if (this.mPager == null) {
            return 0;
        }
        View childView = this.mTabContainer.getChildAt(this.mPager.getCurrentItem());
        if (childView != null) {
            return childView.getLeft() - this.mScrollView.getScrollX();
        }
        return 0;
    }
}
