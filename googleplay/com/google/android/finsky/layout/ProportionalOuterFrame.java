package com.google.android.finsky.layout;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import com.android.vending.R;

public class ProportionalOuterFrame extends ViewGroup {
    private float mProportion;
    private int mTitleHeight;

    public ProportionalOuterFrame(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        TypedArray viewAttrs = context.obtainStyledAttributes(attrs, R.styleable.ProportionalOuterFrame);
        this.mProportion = viewAttrs.getFraction(0, 1, 1, 1.0f);
        viewAttrs.recycle();
    }

    public ProportionalOuterFrame(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProportionalOuterFrame(Context context) {
        this(context, null);
    }

    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (getChildCount() > 0) {
            View child = getChildAt(0);
            int leftMargin = (getWidth() - child.getMeasuredWidth()) / 2;
            child.layout(leftMargin, 0, child.getMeasuredWidth() + leftMargin, getHeight());
        }
    }

    public void onConfigurationChanged(Configuration config) {
        super.onConfigurationChanged(config);
        this.mTitleHeight = 0;
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        this.mTitleHeight = Math.max(this.mTitleHeight, (int) (((float) height) * getContext().getResources().getFraction(R.dimen.setup_wizard_title_height_fraction, 1, 1)));
        if (getChildCount() > 0) {
            measureChild(getChildAt(0), MeasureSpec.makeMeasureSpec((int) (((float) width) * this.mProportion), 1073741824), heightMeasureSpec);
        }
        View title = findViewById(R.id.title);
        if (title != null) {
            title.setMinimumHeight(this.mTitleHeight);
        }
        setMeasuredDimension(width, height);
    }
}
