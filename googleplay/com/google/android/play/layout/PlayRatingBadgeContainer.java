package com.google.android.play.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.FrameLayout;
import com.google.android.play.R;

public class PlayRatingBadgeContainer extends FrameLayout {
    private View mBadge;
    private View mRatingBar;

    public PlayRatingBadgeContainer(Context context) {
        this(context, null);
    }

    public PlayRatingBadgeContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mRatingBar = findViewById(R.id.li_rating);
        this.mBadge = findViewById(R.id.li_badge);
    }

    public int getBaseline() {
        if (this.mBadge.getVisibility() != 8) {
            return this.mBadge.getBaseline();
        }
        if (this.mRatingBar.getVisibility() != 8) {
            return this.mRatingBar.getBaseline();
        }
        return getMeasuredHeight();
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int availableWidth = MeasureSpec.getSize(widthMeasureSpec);
        int width = 0;
        int height = 0;
        if (this.mRatingBar.getVisibility() != 8) {
            this.mRatingBar.measure(0, 0);
            if (this.mRatingBar.getMeasuredWidth() > availableWidth) {
                int zeroSpec = MeasureSpec.makeMeasureSpec(0, 1073741824);
                this.mRatingBar.measure(zeroSpec, zeroSpec);
            }
            width = this.mRatingBar.getMeasuredWidth();
            height = this.mRatingBar.getMeasuredHeight();
        }
        if (this.mBadge.getVisibility() != 8) {
            this.mBadge.measure(MeasureSpec.makeMeasureSpec(availableWidth, Integer.MIN_VALUE), 0);
            width = Math.max(width, this.mBadge.getMeasuredWidth());
            height = Math.max(height, this.mBadge.getMeasuredHeight());
        }
        setMeasuredDimension(width, height);
    }

    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        if (this.mRatingBar.getVisibility() != 8) {
            this.mRatingBar.layout(0, 0, this.mRatingBar.getMeasuredWidth(), this.mRatingBar.getMeasuredHeight());
        }
        if (this.mBadge.getVisibility() != 8) {
            this.mBadge.layout(0, 0, this.mBadge.getMeasuredWidth(), this.mBadge.getMeasuredHeight());
        }
    }
}
