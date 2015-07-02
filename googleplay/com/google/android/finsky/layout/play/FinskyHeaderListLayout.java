package com.google.android.finsky.layout.play;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import com.android.vending.R;
import com.google.android.finsky.layout.HeroGraphicView;
import com.google.android.finsky.layout.actionbar.FinskySearchToolbar;
import com.google.android.finsky.utils.UiUtils;
import com.google.android.play.headerlist.PlayHeaderListLayout;
import com.google.android.play.headerlist.PlayHeaderListLayout.Configurator;

public class FinskyHeaderListLayout extends PlayHeaderListLayout {
    private HeroGraphicView mBackgroundView;
    private int mContentViewId;
    private float mLastMotionX;
    private float mLastMotionY;
    private final int mScrollThreshold;
    private int mSideMargin;
    private final boolean mUseWideLayout;
    private float mXDistanceScrolledSinceLastDown;
    private float mYDistanceScrolledSinceLastDown;

    public static abstract class FinskyConfigurator extends Configurator {
        public FinskyConfigurator(Context context) {
            super(context);
        }

        protected void addBackgroundView(LayoutInflater inflater, ViewGroup root) {
        }

        protected void addHeroView(LayoutInflater inflater, ViewGroup root) {
        }

        protected int getHeaderHeight() {
            return FinskySearchToolbar.getToolbarHeight(this.mContext);
        }

        protected boolean hasViewPager() {
            return false;
        }

        protected int getTabMode() {
            return 2;
        }

        protected int getHeaderMode() {
            return 0;
        }

        protected int getContentProtectionMode() {
            return 0;
        }

        protected boolean alwaysUseFloatingBackground() {
            return true;
        }

        protected int getToolbarTitleMode() {
            return 1;
        }
    }

    public interface StreamSpacer {
    }

    public static int getMinimumHeaderHeight(Context context, int tabMode, int headerBottomMargin) {
        return (FinskySearchToolbar.getToolbarHeight(context) + headerBottomMargin) + (tabMode == 2 ? 0 : context.getResources().getDimensionPixelSize(R.dimen.play_header_list_tab_strip_height));
    }

    public FinskyHeaderListLayout(Context context) {
        this(context, null);
    }

    public FinskyHeaderListLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mScrollThreshold = ViewConfiguration.get(context).getScaledTouchSlop();
        this.mUseWideLayout = context.getResources().getBoolean(R.bool.use_wide_details_layout);
    }

    public void setContentViewId(int contentViewId) {
        this.mContentViewId = contentViewId;
    }

    public void showContentView() {
        if (this.mContentViewId > 0) {
            findViewById(this.mContentViewId).setVisibility(0);
        }
    }

    public void hideContentView() {
        if (this.mContentViewId > 0) {
            findViewById(this.mContentViewId).setVisibility(8);
        }
    }

    public void configureEventInterception(HeroGraphicView backgroundView) {
        this.mBackgroundView = backgroundView;
    }

    public boolean onInterceptTouchEvent(MotionEvent ev) {
        ViewGroup mainContent = getCurrentListView();
        if (mainContent == null) {
            return super.onInterceptTouchEvent(ev);
        }
        if (this.mBackgroundView == null || this.mBackgroundView.getVisibility() != 0 || !this.mBackgroundView.isClickable()) {
            return super.onInterceptTouchEvent(ev);
        }
        float currMotionX = ev.getX();
        float currMotionY = ev.getY();
        switch (ev.getAction()) {
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                this.mXDistanceScrolledSinceLastDown = 0.0f;
                this.mYDistanceScrolledSinceLastDown = 0.0f;
                break;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                this.mXDistanceScrolledSinceLastDown += Math.abs(currMotionX - this.mLastMotionX);
                this.mYDistanceScrolledSinceLastDown += Math.abs(currMotionY - this.mLastMotionY);
                break;
        }
        this.mLastMotionX = currMotionX;
        this.mLastMotionY = currMotionY;
        if (this.mXDistanceScrolledSinceLastDown > ((float) this.mScrollThreshold) || this.mYDistanceScrolledSinceLastDown > ((float) this.mScrollThreshold)) {
            sendCancelEventToBackgroundLayer(this.mBackgroundView, ev);
            return false;
        } else if (currMotionX <= ((float) this.mSideMargin) || currMotionX >= ((float) (getWidth() - this.mSideMargin))) {
            passThroughEvent = MotionEvent.obtain(ev);
            this.mBackgroundView.dispatchTouchEvent(passThroughEvent);
            passThroughEvent.recycle();
            return false;
        } else if ((mainContent instanceof RecyclerView) && (((RecyclerView) mainContent).findChildViewUnder(currMotionX, currMotionY) instanceof StreamSpacer)) {
            passThroughEvent = MotionEvent.obtain(ev);
            this.mBackgroundView.dispatchTouchEvent(passThroughEvent);
            passThroughEvent.recycle();
            return false;
        } else {
            sendCancelEventToBackgroundLayer(this.mBackgroundView, ev);
            return super.onInterceptTouchEvent(ev);
        }
    }

    private void sendCancelEventToBackgroundLayer(View backgroundLayer, MotionEvent ev) {
        MotionEvent cancelEvent = MotionEvent.obtain(ev);
        cancelEvent.setAction(3);
        backgroundLayer.dispatchTouchEvent(cancelEvent);
        cancelEvent.recycle();
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int contentColumnWidth;
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        if (this.mUseWideLayout) {
            contentColumnWidth = UiUtils.getGridColumnContentWidth(getResources());
        } else {
            contentColumnWidth = width;
        }
        this.mSideMargin = (width - Math.min(width, contentColumnWidth)) / 2;
    }

    public int getActionBarHeight() {
        return FinskySearchToolbar.getToolbarHeight(getContext());
    }
}
