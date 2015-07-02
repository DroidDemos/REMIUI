package com.google.android.finsky.layout.play;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.android.vending.R;

public class SingleLineContainer extends LinearLayout {
    private View mFlexibleChild;
    private final int mFlexibleChildId;

    public SingleLineContainer(Context context) {
        this(context, null);
    }

    public SingleLineContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.SingleLineContainer);
        this.mFlexibleChildId = attributes.getResourceId(0, 0);
        attributes.recycle();
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        if (this.mFlexibleChildId != 0) {
            this.mFlexibleChild = findViewById(this.mFlexibleChildId);
        }
    }

    private boolean isCenteredVertically(View childView) {
        if (childView.getBaseline() == -1 || (((LayoutParams) childView.getLayoutParams()).gravity & 112) == 16) {
            return true;
        }
        return false;
    }

    protected final void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int i;
        int fullWidth = MeasureSpec.getSize(widthMeasureSpec);
        int availableWidth = (fullWidth - getPaddingLeft()) - getPaddingRight();
        int childCount = getChildCount();
        int baseline = 0;
        for (i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() != 8) {
                child.measure(0, 0);
                if (!isCenteredVertically(child)) {
                    baseline = Math.max(baseline, child.getBaseline());
                }
            }
        }
        int heightBelowBaseline = 0;
        int totalWidth = 0;
        int heightOfTallestChildWithNoBaseline = 0;
        for (i = 0; i < childCount; i++) {
            child = getChildAt(i);
            if (child.getVisibility() != 8) {
                int childMeasuredHeight = child.getMeasuredHeight();
                if (isCenteredVertically(child)) {
                    heightOfTallestChildWithNoBaseline = Math.max(heightOfTallestChildWithNoBaseline, childMeasuredHeight);
                } else {
                    heightBelowBaseline = Math.max(heightBelowBaseline, childMeasuredHeight - child.getBaseline());
                }
                LayoutParams childLp = (LayoutParams) child.getLayoutParams();
                totalWidth += (childLp.leftMargin + child.getMeasuredWidth()) + childLp.rightMargin;
            }
        }
        int totalHeight = Math.max(baseline + heightBelowBaseline, heightOfTallestChildWithNoBaseline) + (getPaddingTop() + getPaddingBottom());
        if (!(totalWidth <= availableWidth || this.mFlexibleChild == null || this.mFlexibleChild.getVisibility() == 8)) {
            this.mFlexibleChild.measure(MeasureSpec.makeMeasureSpec(this.mFlexibleChild.getMeasuredWidth() - (totalWidth - availableWidth), 1073741824), MeasureSpec.makeMeasureSpec(this.mFlexibleChild.getMeasuredHeight(), 1073741824));
        }
        setMeasuredDimension(fullWidth, totalHeight);
    }

    protected final void onLayout(boolean changed, int l, int t, int r, int b) {
        int i;
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int height = getHeight();
        int childCount = getChildCount();
        int baseline = 0;
        for (i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() != 8) {
                baseline = Math.max(baseline, child.getBaseline());
            }
        }
        int x = getPaddingLeft();
        for (i = 0; i < childCount; i++) {
            child = getChildAt(i);
            if (child.getVisibility() != 8) {
                int childWidth = child.getMeasuredWidth();
                MarginLayoutParams childLp = (MarginLayoutParams) child.getLayoutParams();
                x += childLp.leftMargin;
                int childY = isCenteredVertically(child) ? paddingTop + ((((height - paddingTop) - paddingBottom) - child.getMeasuredHeight()) / 2) : (paddingTop + baseline) - child.getBaseline();
                child.layout(x, childY, x + childWidth, child.getMeasuredHeight() + childY);
                x += childLp.rightMargin + childWidth;
            }
        }
    }
}
