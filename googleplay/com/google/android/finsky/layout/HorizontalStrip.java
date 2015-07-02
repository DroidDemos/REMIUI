package com.google.android.finsky.layout;

import android.content.Context;
import android.content.res.Resources;
import android.database.DataSetObserver;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnFocusChangeListener;
import com.android.vending.R;
import com.google.android.finsky.adapters.ImageStripAdapter;
import com.google.android.finsky.protos.Common.Image.Dimension;

public class HorizontalStrip extends AbsHorizontalStrip {
    private ImageStripAdapter mAdapter;
    private final Dimension mDimension;
    private int mEdgeFadeColor;
    protected final float mScreenScaleFactor;

    public HorizontalStrip(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public HorizontalStrip(Context context, AttributeSet attributeSet, int defStyle) {
        super(context, attributeSet, defStyle);
        this.mDimension = new Dimension();
        Resources resources = context.getResources();
        this.mScreenScaleFactor = resources.getDisplayMetrics().density;
        this.mEdgeFadeColor = resources.getColor(R.color.screenshot_edge_fade);
    }

    public void setAdapter(ImageStripAdapter adapter) {
        this.mAdapter = adapter;
        if (this.mAdapter != null) {
            this.mAdapter.registerDataSetObserver(new DataSetObserver() {
                public void onChanged() {
                    HorizontalStrip.this.syncChildViews();
                }

                public void onInvalidated() {
                    HorizontalStrip.this.recreateChildViews();
                }
            });
        }
        recreateChildViews();
    }

    private void recreateChildViews() {
        removeAllViews();
        if (this.mAdapter != null) {
            for (int i = 0; i < this.mAdapter.getChildCount(); i++) {
                View childView = this.mAdapter.getViewAt(this.mContext, this, i);
                final int childIndex = i;
                childView.setOnFocusChangeListener(new OnFocusChangeListener() {
                    public void onFocusChange(View v, boolean hasFocus) {
                        if (hasFocus) {
                            HorizontalStrip.this.onChildAcquiredFocus(childIndex);
                        }
                    }
                });
                addView(childView);
            }
            syncChildViews();
        }
    }

    private void syncChildViews() {
        boolean hasAllImages = true;
        for (int i = 0; i < this.mAdapter.getChildCount(); i++) {
            View child = getChildAt(i);
            Drawable drawable = this.mAdapter.getImageAt(i);
            if (child instanceof AppScreenshot) {
                AppScreenshot appScreenshot = (AppScreenshot) child;
                if (drawable == null || appScreenshot.hasScreenshotDrawable()) {
                    hasAllImages = false;
                } else {
                    appScreenshot.setScreenshotDrawable(drawable);
                }
            }
        }
        if (hasAllImages) {
            requestLayout();
        }
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int stripHeight = MeasureSpec.getSize(heightMeasureSpec);
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            int childWidth = getChildWidth(i);
            int childHeight = getChildHeight(i);
            if (childHeight != 0) {
                float scalingFactor = ((float) stripHeight) / ((float) childHeight);
                if (((double) scalingFactor) < 1.0d) {
                    childWidth = (int) (((float) childWidth) * scalingFactor);
                }
            }
            child.measure(MeasureSpec.makeMeasureSpec(childWidth, 1073741824), heightMeasureSpec);
        }
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), stripHeight);
    }

    private int getChildWidth(int childIndex) {
        this.mAdapter.getImageDimensionAt(childIndex, this.mDimension, this.mScreenScaleFactor);
        return this.mDimension.width;
    }

    private int getChildHeight(int childIndex) {
        this.mAdapter.getImageDimensionAt(childIndex, this.mDimension, this.mScreenScaleFactor);
        return this.mDimension.height;
    }

    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (this.mAdapter != null) {
            int height = getHeight();
            this.mTotalChildrenWidth = 0.0f;
            int x = getPaddingLeft() + this.mLeadingMargin;
            for (int i = 0; i < getChildCount(); i++) {
                View child = getChildAt(i);
                int childWidth = child.getMeasuredWidth();
                child.layout(x, 0, x + childWidth, height);
                x += this.mGapMargin + childWidth;
                this.mTotalChildrenWidth += (float) childWidth;
            }
            this.mTotalChildrenWidth += (float) (this.mGapMargin * (getChildCount() - 1));
            this.mTotalChildrenWidth += (float) this.mLeadingMargin;
        }
    }

    protected float getLeftEdgeOfChildOnLeft(float xOffset) {
        int result = 0;
        int currLeft = 0;
        for (int i = 0; i < getChildCount(); i++) {
            currLeft += getTotalChildWidth(i);
            if (((float) currLeft) > xOffset) {
                break;
            }
            result = currLeft;
        }
        return (float) result;
    }

    private int getTotalChildWidth(int index) {
        return getChildAt(index).getWidth() + (index == 0 ? this.mLeadingMargin : this.mGapMargin);
    }

    protected float getLeftEdgeOfChild(int index) {
        int result = 0;
        int currLeft = 0;
        int i = 0;
        while (i < index) {
            currLeft += getTotalChildWidth(i);
            result = currLeft;
            i++;
        }
        if (i != 0) {
            result += this.mGapMargin;
        }
        return (float) result;
    }

    protected float getLeftEdgeOfChildOnRight(float xOffset) {
        int result = 0;
        int currLeft = 0;
        for (int i = 0; i < getChildCount(); i++) {
            currLeft += getTotalChildWidth(i);
            result = currLeft;
            if (((float) currLeft) > xOffset) {
                break;
            }
        }
        return (float) (result + this.mGapMargin);
    }

    protected float getLeftFadingEdgeStrength() {
        float scrollPosition = getScrollPosition();
        if (scrollPosition >= 0.0f) {
            return 0.0f;
        }
        float pixelsBeyondLeft = -scrollPosition;
        int fadeLength = getHorizontalFadingEdgeLength();
        if (pixelsBeyondLeft > ((float) fadeLength)) {
            return 1.0f;
        }
        return pixelsBeyondLeft / ((float) fadeLength);
    }

    protected float getRightFadingEdgeStrength() {
        float pixelsBeyondRight = (getScrollPosition() + this.mTotalChildrenWidth) - ((float) getWidth());
        if (pixelsBeyondRight <= 0.0f) {
            return 0.0f;
        }
        int fadeLength = getHorizontalFadingEdgeLength();
        if (pixelsBeyondRight > ((float) fadeLength)) {
            return 1.0f;
        }
        return pixelsBeyondRight / ((float) fadeLength);
    }

    public int getSolidColor() {
        return this.mEdgeFadeColor;
    }

    public void onDestroyView() {
        setAdapter(null);
    }
}
