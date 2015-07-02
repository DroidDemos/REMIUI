package com.google.android.finsky.layout;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.FrameLayout;
import com.android.vending.R;

public class MarginBox extends FrameLayout {
    protected int mEdgePadding;
    protected int mMaxContentWidth;

    public MarginBox(Context context) {
        this(context, null);
    }

    public MarginBox(Context context, AttributeSet attrs) {
        super(context, attrs);
        Resources res = context.getResources();
        this.mMaxContentWidth = res.getDimensionPixelSize(R.dimen.play_collection_max_width);
        this.mEdgePadding = res.getDimensionPixelSize(R.dimen.play_collection_edge_padding) - res.getDimensionPixelSize(R.dimen.play_collection_card_spacing);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int childWidthSpec = MeasureSpec.makeMeasureSpec(Math.min(this.mMaxContentWidth, width - (this.mEdgePadding * 2)), 1073741824);
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            if (child.getLayoutParams().height == -1) {
                child.measure(childWidthSpec, MeasureSpec.makeMeasureSpec(height, 1073741824));
            } else {
                child.measure(childWidthSpec, MeasureSpec.makeMeasureSpec(height, Integer.MIN_VALUE));
            }
        }
        setMeasuredDimension(width, height);
    }

    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int width = getWidth();
        int height = getHeight();
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            int childWidth = child.getMeasuredWidth();
            int childHeight = child.getMeasuredHeight();
            int childLeft = (width - childWidth) / 2;
            int childTop = (height - childHeight) / 2;
            child.layout(childLeft, childTop, childLeft + childWidth, childTop + childHeight);
        }
    }
}
