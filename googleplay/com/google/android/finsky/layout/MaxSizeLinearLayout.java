package com.google.android.finsky.layout;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import com.android.vending.R;

public class MaxSizeLinearLayout extends MaxWidthLinearLayout {
    private int mMaxHeight;

    public MaxSizeLinearLayout(Context context) {
        this(context, null);
    }

    public MaxSizeLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray viewAttrs = context.obtainStyledAttributes(attrs, R.styleable.MaxSizeLayoutHeight);
        this.mMaxHeight = viewAttrs.getDimensionPixelSize(0, 0);
        viewAttrs.recycle();
    }

    public void setMaxHeight(int maxHeight) {
        if (maxHeight != this.mMaxHeight) {
            this.mMaxHeight = maxHeight;
            requestLayout();
        }
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int measuredHeight = MeasureSpec.getSize(heightMeasureSpec);
        if (this.mMaxHeight > 0 && this.mMaxHeight < measuredHeight) {
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(this.mMaxHeight, MeasureSpec.getMode(heightMeasureSpec));
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
