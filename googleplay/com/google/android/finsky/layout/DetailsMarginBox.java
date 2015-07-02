package com.google.android.finsky.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewConfiguration;
import com.android.vending.R;
import com.google.android.finsky.utils.UiUtils;

public class DetailsMarginBox extends DetailsColumnLayout {
    private View mCardTransitionTarget;
    private View mDummyHeader;
    protected HeroGraphicView mHeroGraphicView;
    private float mLastMotionX;
    private float mLastMotionY;
    private View mScreenshotGallery;
    private final int mScrollThreshold;
    private final boolean mUseWideLayout;
    private float mXDistanceScrolledSinceLastDown;
    private float mYDistanceScrolledSinceLastDown;

    public DetailsMarginBox(Context context) {
        this(context, null);
    }

    public DetailsMarginBox(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mScrollThreshold = ViewConfiguration.get(context).getScaledTouchSlop();
        this.mUseWideLayout = context.getResources().getBoolean(R.bool.use_wide_details_layout);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mHeroGraphicView = (HeroGraphicView) findViewById(R.id.hero_promo);
        this.mCardTransitionTarget = findViewById(R.id.card_transition_target);
    }

    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (this.mHeroGraphicView != null) {
            if (this.mDetailsExpandedFrame.getVisibility() == 0) {
                sendCancelEventToHero(ev);
            } else {
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
                int mainScrollY = this.mDetailsScroller.getScrollY();
                if (this.mXDistanceScrolledSinceLastDown > ((float) this.mScrollThreshold) || this.mYDistanceScrolledSinceLastDown > ((float) this.mScrollThreshold)) {
                    sendCancelEventToHero(ev);
                } else {
                    int contentSideMargin = this.mDetailsContainer.getSideMargin();
                    if (currMotionX <= ((float) contentSideMargin) || currMotionX >= ((float) (getWidth() - contentSideMargin))) {
                        if (this.mScreenshotGallery == null) {
                            this.mScreenshotGallery = this.mDetailsScroller.findViewById(R.id.screenshots_panel);
                        }
                        if (this.mScreenshotGallery != null) {
                            int screenshotVisibleTop = this.mScreenshotGallery.getTop() - mainScrollY;
                            int screenshotVisibleBottom = screenshotVisibleTop + this.mScreenshotGallery.getHeight();
                            if (currMotionY >= ((float) screenshotVisibleTop) && currMotionY <= ((float) screenshotVisibleBottom)) {
                                sendCancelEventToHero(ev);
                            }
                        }
                        this.mHeroGraphicView.dispatchTouchEvent(ev);
                    } else {
                        if (this.mUseWideLayout && this.mDummyHeader == null) {
                            this.mDummyHeader = this.mDetailsScroller.findViewById(R.id.dummy_header);
                        }
                        if (this.mDummyHeader == null || currMotionY > ((float) Math.max(0, this.mDummyHeader.getHeight() - mainScrollY))) {
                            sendCancelEventToHero(ev);
                        } else {
                            this.mHeroGraphicView.dispatchTouchEvent(ev);
                        }
                    }
                }
            }
        }
        return false;
    }

    private void sendCancelEventToHero(MotionEvent ev) {
        MotionEvent cancelEvent = MotionEvent.obtain(ev);
        cancelEvent.setAction(3);
        this.mHeroGraphicView.dispatchTouchEvent(cancelEvent);
        cancelEvent.recycle();
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int contentColumnWidth;
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        if (this.mHeroGraphicView != null) {
            this.mHeroGraphicView.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(height, Integer.MIN_VALUE));
        }
        if (this.mUseWideLayout) {
            contentColumnWidth = UiUtils.getGridColumnContentWidth(getResources());
        } else {
            contentColumnWidth = width;
        }
        int mainContentWidth = Math.min(width, contentColumnWidth);
        this.mDetailsContainer.setSideMargin((width - mainContentWidth) / 2);
        this.mDetailsExpandedFrame.setScrollerWidth(mainContentWidth);
        this.mDetailsContainer.setExpandCardSections(mainContentWidth < width);
        int childHeightSpec = MeasureSpec.makeMeasureSpec(height, 1073741824);
        this.mDetailsScroller.measure(widthMeasureSpec, childHeightSpec);
        this.mDetailsExpandedFrame.measure(widthMeasureSpec, childHeightSpec);
        if (!(this.mCardTransitionTarget == null || this.mCardTransitionTarget.getVisibility() == 8)) {
            this.mCardTransitionTarget.measure(MeasureSpec.makeMeasureSpec(contentColumnWidth, 1073741824), MeasureSpec.makeMeasureSpec(height - this.mHeroGraphicView.getLeadingSpacerVerticalSpan(), 1073741824));
        }
        setMeasuredDimension(width, height);
    }

    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int width = getWidth();
        int height = getHeight();
        if (this.mHeroGraphicView != null) {
            this.mHeroGraphicView.layout(0, 0, width, this.mHeroGraphicView.getMeasuredHeight());
        }
        this.mDetailsScroller.layout(0, 0, width, height);
        this.mDetailsExpandedFrame.layout(0, 0, width, height);
        if (this.mCardTransitionTarget != null && this.mCardTransitionTarget.getVisibility() != 8) {
            int cardTransitionTargetWidth = this.mCardTransitionTarget.getMeasuredWidth();
            int cardTransitionTargetLeft = (width - cardTransitionTargetWidth) / 2;
            this.mCardTransitionTarget.layout(cardTransitionTargetLeft, height - this.mCardTransitionTarget.getMeasuredHeight(), cardTransitionTargetLeft + cardTransitionTargetWidth, height);
        }
    }
}
