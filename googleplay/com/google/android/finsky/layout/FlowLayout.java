package com.google.android.finsky.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;

public class FlowLayout extends ViewGroup {
    public FlowLayout(Context context) {
        this(context, null);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width;
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        boolean isFixedSize = widthMode == 1073741824;
        int height = 0;
        int childCount = getChildCount();
        int childWidthSpec = MeasureSpec.makeMeasureSpec(widthSize, Integer.MIN_VALUE);
        int x = 0;
        int currLineWidth = 0;
        int currLineHeight = 0;
        int maxLineWidth = 0;
        for (int i = 0; i < childCount; i++) {
            View currChild = getChildAt(i);
            currChild.measure(childWidthSpec, 0);
            int childMeasuredWidth = currChild.getMeasuredWidth();
            int childMeasuredHeight = currChild.getMeasuredHeight();
            if (x + childMeasuredWidth > widthSize) {
                height += currLineHeight;
                currLineHeight = 0;
                x = 0;
                currLineWidth = 0;
            }
            x += childMeasuredWidth;
            currLineWidth += childMeasuredWidth;
            currLineHeight = Math.max(currLineHeight, childMeasuredHeight);
            maxLineWidth = Math.max(currLineWidth, maxLineWidth);
        }
        height += currLineHeight;
        if (isFixedSize) {
            width = widthSize;
        } else {
            width = maxLineWidth;
        }
        setMeasuredDimension(width, height);
    }

    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int width = getWidth();
        int y = 0;
        int x = 0;
        int childCount = getChildCount();
        int currLineHeight = 0;
        for (int i = 0; i < childCount; i++) {
            View currChild = getChildAt(i);
            int childWidth = currChild.getMeasuredWidth();
            int childHeight = currChild.getMeasuredHeight();
            if (x + childWidth > width) {
                y += currLineHeight;
                currLineHeight = 0;
                x = 0;
            }
            currChild.layout(x, y, x + childWidth, y + childHeight);
            currLineHeight = Math.max(currLineHeight, childHeight);
            x += childWidth;
        }
    }
}
