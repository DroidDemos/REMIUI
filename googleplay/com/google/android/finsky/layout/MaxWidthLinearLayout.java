package com.google.android.finsky.layout;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.widget.LinearLayout;
import com.android.vending.R;

public class MaxWidthLinearLayout extends LinearLayout {
    private int mMaxWidth;

    public MaxWidthLinearLayout(Context context) {
        this(context, null);
    }

    public MaxWidthLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray viewAttrs = context.obtainStyledAttributes(attrs, R.styleable.MaxWidthView);
        this.mMaxWidth = viewAttrs.getDimensionPixelSize(0, 0);
        viewAttrs.recycle();
    }

    public void setMaxWidth(int maxWidth) {
        if (maxWidth != this.mMaxWidth) {
            this.mMaxWidth = maxWidth;
            requestLayout();
        }
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int measuredWidth = MeasureSpec.getSize(widthMeasureSpec);
        if (this.mMaxWidth > 0 && this.mMaxWidth < measuredWidth) {
            widthMeasureSpec = MeasureSpec.makeMeasureSpec(this.mMaxWidth, MeasureSpec.getMode(widthMeasureSpec));
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
