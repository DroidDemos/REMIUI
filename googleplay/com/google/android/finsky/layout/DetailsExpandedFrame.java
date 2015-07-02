package com.google.android.finsky.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.animation.Animation;
import com.android.vending.R;
import com.google.android.finsky.utils.PlayAnimationUtils;
import com.google.android.finsky.utils.PlayAnimationUtils.AnimationListenerAdapter;

public class DetailsExpandedFrame extends ViewGroup {
    private View mContentScroller;
    private View mLeftBar;
    private View mRightBar;
    private int mScrollerWidth;

    public DetailsExpandedFrame(Context context) {
        this(context, null);
    }

    public DetailsExpandedFrame(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mLeftBar = findViewById(R.id.left_bar);
        this.mRightBar = findViewById(R.id.right_bar);
        this.mContentScroller = findViewById(R.id.details_expanded_scroller);
    }

    public void setScrollerWidth(int scrollerWidth) {
        this.mScrollerWidth = scrollerWidth;
    }

    public void setSideBarProportion(float sideBarProportion) {
        int fullBarWidth = (getWidth() - this.mScrollerWidth) / 2;
        int sidePadding = fullBarWidth - ((int) (((float) fullBarWidth) * sideBarProportion));
        setPadding(sidePadding, 0, sidePadding, 0);
    }

    public void fadeInSideBarsPreIcs() {
        Context context = getContext();
        setSideBarProportion(1.0f);
        this.mLeftBar.startAnimation(PlayAnimationUtils.getFadeInAnimation(context, 150, 150, null));
        this.mRightBar.startAnimation(PlayAnimationUtils.getFadeInAnimation(context, 150, 150, null));
    }

    public void fadeOutSideBarsPreIcs() {
        Context context = getContext();
        this.mLeftBar.startAnimation(PlayAnimationUtils.getFadeOutAnimation(context, 0, 150, null));
        this.mRightBar.startAnimation(PlayAnimationUtils.getFadeOutAnimation(context, 0, 150, new AnimationListenerAdapter() {
            public void onAnimationEnd(Animation animation) {
                DetailsExpandedFrame.this.setSideBarProportion(0.0f);
            }
        }));
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int scrollerWidth = Math.min(this.mScrollerWidth, width);
        this.mContentScroller.measure(MeasureSpec.makeMeasureSpec(scrollerWidth, 1073741824), heightMeasureSpec);
        int barWidth = (width - scrollerWidth) / 2;
        if (barWidth == 0) {
            this.mLeftBar.setVisibility(8);
            this.mRightBar.setVisibility(8);
        } else {
            this.mLeftBar.setVisibility(0);
            this.mRightBar.setVisibility(0);
            this.mLeftBar.measure(MeasureSpec.makeMeasureSpec(barWidth - getPaddingLeft(), 1073741824), heightMeasureSpec);
            this.mRightBar.measure(MeasureSpec.makeMeasureSpec(barWidth - getPaddingRight(), 1073741824), heightMeasureSpec);
        }
        setMeasuredDimension(width, MeasureSpec.getSize(heightMeasureSpec));
    }

    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int width = getWidth();
        int height = getHeight();
        int scrollerWidth = this.mContentScroller.getMeasuredWidth();
        int scrollerX = (width - scrollerWidth) / 2;
        this.mContentScroller.layout(scrollerX, 0, scrollerX + scrollerWidth, height);
        if (this.mLeftBar.getVisibility() == 0) {
            this.mLeftBar.layout(scrollerX - this.mLeftBar.getMeasuredWidth(), 0, scrollerX, height);
        }
        if (this.mRightBar.getVisibility() == 0) {
            this.mRightBar.layout(scrollerX + scrollerWidth, 0, (scrollerX + scrollerWidth) + this.mRightBar.getMeasuredWidth(), height);
        }
    }
}
