package com.google.android.finsky.layout;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.LinearLayout;
import com.android.vending.R;

public class DetailsButtonRowLayout extends LinearLayout {
    private final int mChildGap;
    private final boolean mIsWideLayout;

    public DetailsButtonRowLayout(Context context) {
        this(context, null);
    }

    public DetailsButtonRowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        Resources res = context.getResources();
        this.mChildGap = (res.getDimensionPixelSize(R.dimen.details_new_content_margin) * 2) / 3;
        this.mIsWideLayout = res.getBoolean(R.bool.use_wide_details_layout);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int i;
        int childCount = getChildCount();
        int visibleChildCount = 0;
        int childTotalWidth = 0;
        int height = 0;
        int paddingVertical = getPaddingTop() + getPaddingBottom();
        for (i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() == 0) {
                visibleChildCount++;
                child.measure(0, 0);
                childTotalWidth += child.getMeasuredWidth();
                height = Math.max(height, child.getMeasuredHeight());
            }
        }
        if (visibleChildCount > 0) {
            childTotalWidth += this.mChildGap * (visibleChildCount - 1);
        }
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        if (widthMode != Integer.MIN_VALUE || childTotalWidth > widthSize) {
            int extraWidthToDistribute = widthSize - childTotalWidth;
            if (extraWidthToDistribute > 0 && (visibleChildCount == 1 || this.mIsWideLayout)) {
                extraWidthToDistribute = 0;
            }
            if (extraWidthToDistribute != 0) {
                height = 0;
                for (i = 0; i < childCount; i++) {
                    child = getChildAt(i);
                    if (child.getVisibility() == 0) {
                        int widthDelta = extraWidthToDistribute / visibleChildCount;
                        child.measure(MeasureSpec.makeMeasureSpec(child.getMeasuredWidth() + widthDelta, 1073741824), 0);
                        height = Math.max(height, child.getMeasuredHeight());
                        extraWidthToDistribute -= widthDelta;
                        visibleChildCount--;
                    }
                }
            }
            setMeasuredDimension(widthSize, height + paddingVertical);
            return;
        }
        setMeasuredDimension(childTotalWidth, height + paddingVertical);
    }

    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int rightX = getWidth();
        int childCount = getChildCount();
        int y = getPaddingTop();
        for (int i = childCount - 1; i >= 0; i--) {
            View child = getChildAt(i);
            if (child.getVisibility() == 0) {
                int childWidth = child.getMeasuredWidth();
                child.layout(rightX - childWidth, y, rightX, child.getMeasuredHeight() + y);
                rightX -= this.mChildGap + childWidth;
            }
        }
    }
}
