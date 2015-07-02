package com.google.android.finsky.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

public class HistogramBar extends View {
    private int mHeight;
    private double mMaxWidth;
    private double mWidthPercentage;

    public HistogramBar(Context context) {
        this(context, null);
    }

    public HistogramBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setMaxBarWidth(double maxWidth) {
        this.mMaxWidth = maxWidth;
    }

    public void setWidthPercentage(double widthPercentage) {
        this.mWidthPercentage = widthPercentage;
    }

    public void setBarHeight(int height) {
        this.mHeight = height;
    }

    public void setColor(int colorResourceId) {
        super.setBackgroundColor(getContext().getResources().getColor(colorResourceId));
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int fill = (int) (this.mWidthPercentage * this.mMaxWidth);
        if (fill >= 0) {
            setMeasuredDimension(fill, this.mHeight);
        }
    }

    public int getBaseline() {
        return getMeasuredHeight();
    }
}
