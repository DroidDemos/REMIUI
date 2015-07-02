package com.google.android.finsky.layout;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.LinearLayout.LayoutParams;
import com.android.vending.R;
import com.google.android.finsky.layout.play.Identifiable;

public class BucketRow extends IdentifiableLinearLayout implements Identifiable {
    private boolean mSameChildHeight;
    private boolean mSameChildWidth;

    public BucketRow(Context context) {
        this(context, null);
    }

    public BucketRow(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(0);
        TypedArray viewAttrs = context.obtainStyledAttributes(attrs, R.styleable.BucketRow);
        this.mSameChildHeight = viewAttrs.getBoolean(0, false);
        this.mSameChildWidth = viewAttrs.getBoolean(1, true);
        viewAttrs.recycle();
    }

    public void setSameChildHeight(boolean sameChildHeight) {
        this.mSameChildHeight = sameChildHeight;
    }

    public void setSameChildWidth(boolean sameChildWidth) {
        this.mSameChildWidth = sameChildWidth;
    }

    public void setContentHorizontalPadding(int cardContentHorizontalPadding) {
        setPadding(cardContentHorizontalPadding, getPaddingTop(), cardContentHorizontalPadding, getPaddingBottom());
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int i;
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int childCount = getChildCount();
        int maxChildHeight = 0;
        int widthLeft = (width - getPaddingLeft()) - getPaddingRight();
        float weightLeft = getWeightSum();
        for (i = 0; i < childCount; i++) {
            int childWidthMeasureSpec;
            View child = getChildAt(i);
            if (this.mSameChildWidth) {
                childWidthMeasureSpec = MeasureSpec.makeMeasureSpec(widthLeft / (childCount - i), 1073741824);
            } else {
                float weight = ((LayoutParams) child.getLayoutParams()).weight;
                childWidthMeasureSpec = MeasureSpec.makeMeasureSpec((int) ((((float) widthLeft) * weight) / weightLeft), 1073741824);
                weightLeft -= weight;
            }
            child.measure(childWidthMeasureSpec, getChildMeasureSpec(0, 0, child.getLayoutParams().height));
            widthLeft -= child.getMeasuredWidth();
            maxChildHeight = Math.max(maxChildHeight, child.getMeasuredHeight());
        }
        if (this.mSameChildHeight) {
            int heightSpec = MeasureSpec.makeMeasureSpec(maxChildHeight, 1073741824);
            for (i = 0; i < childCount; i++) {
                child = getChildAt(i);
                if (child.getMeasuredHeight() != maxChildHeight) {
                    child.measure(MeasureSpec.makeMeasureSpec(child.getMeasuredWidth(), 1073741824), heightSpec);
                }
            }
        }
        setMeasuredDimension(width, maxChildHeight + (getPaddingTop() + getPaddingBottom()));
    }

    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int x = getPaddingLeft();
        int bottomY = getHeight() - getPaddingBottom();
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            MarginLayoutParams childLp = (MarginLayoutParams) child.getLayoutParams();
            child.layout(childLp.leftMargin + x, (bottomY - child.getMeasuredHeight()) - childLp.bottomMargin, child.getMeasuredWidth() + x, bottomY - childLp.bottomMargin);
            x += child.getMeasuredWidth() + childLp.rightMargin;
        }
    }
}
