package com.google.android.finsky.layout;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import com.android.vending.R;
import com.google.android.finsky.utils.IntMath;

public class DetailsSectionStack extends LinearLayout {
    private final int mHalfSeparatorThickness;
    private final int mMaxSectionSeparatorAlpha;
    private float mSectionSeparatorAlphaMultiplier;
    private final int mSectionSeparatorColor;
    private final int mSectionSeparatorInset;
    private final Paint mSectionSeparatorPaint;
    private boolean mShowSeparators;
    private boolean mShowTrailingSeparator;

    public interface NoTopSeparator {
    }

    public interface NoBottomSeparator {
    }

    public DetailsSectionStack(Context context) {
        this(context, null);
    }

    public DetailsSectionStack(Context context, AttributeSet attrs) {
        super(context, attrs);
        Resources res = context.getResources();
        TypedArray viewAttrs = context.obtainStyledAttributes(attrs, R.styleable.DetailsSectionStack);
        this.mSectionSeparatorInset = viewAttrs.getDimensionPixelSize(0, 0);
        this.mShowTrailingSeparator = viewAttrs.getBoolean(1, false);
        viewAttrs.recycle();
        this.mSectionSeparatorPaint = new Paint();
        this.mSectionSeparatorColor = res.getColor(R.color.play_translucent_separator_line);
        this.mMaxSectionSeparatorAlpha = Color.alpha(this.mSectionSeparatorColor);
        this.mSectionSeparatorPaint.setColor(this.mSectionSeparatorColor);
        int separatorThickness = res.getDimensionPixelSize(R.dimen.play_hairline_separator_thickness);
        this.mHalfSeparatorThickness = IntMath.ceil(separatorThickness, 2);
        this.mSectionSeparatorPaint.setStrokeWidth((float) separatorThickness);
        this.mShowSeparators = true;
        this.mSectionSeparatorAlphaMultiplier = 1.0f;
        setWillNotDraw(false);
    }

    public void setSeparatorsVisible(boolean showSeparators) {
        if (this.mShowSeparators != showSeparators) {
            this.mShowSeparators = showSeparators;
            invalidate();
        }
    }

    public void setSectionSeparatorAlphaMultiplier(float sectionSeparatorAlphaMultiplier) {
        if (this.mSectionSeparatorAlphaMultiplier != sectionSeparatorAlphaMultiplier) {
            this.mSectionSeparatorAlphaMultiplier = sectionSeparatorAlphaMultiplier;
            this.mSectionSeparatorPaint.setColor((((int) (this.mSectionSeparatorAlphaMultiplier * ((float) this.mMaxSectionSeparatorAlpha))) << 24) | (this.mSectionSeparatorColor & 16777215));
            invalidate();
        }
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (this.mShowSeparators) {
            boolean isFirstVisibleChild = true;
            boolean skipNextVisibleChild = false;
            int childCount = getChildCount();
            View lastVisibleChild = null;
            for (int i = 0; i < childCount; i++) {
                View currChild = getChildAt(i);
                if (currChild.getVisibility() == 0) {
                    lastVisibleChild = currChild;
                    if (skipNextVisibleChild || (currChild instanceof NoTopSeparator)) {
                        skipNextVisibleChild = currChild instanceof NoBottomSeparator;
                        isFirstVisibleChild = false;
                    } else {
                        if (!isFirstVisibleChild) {
                            int topY = currChild.getTop();
                            canvas.drawLine((float) (currChild.getLeft() + this.mSectionSeparatorInset), (float) topY, (float) (currChild.getRight() - this.mSectionSeparatorInset), (float) topY, this.mSectionSeparatorPaint);
                        }
                        skipNextVisibleChild = currChild instanceof NoBottomSeparator;
                        isFirstVisibleChild = false;
                    }
                }
            }
            if (this.mShowTrailingSeparator && lastVisibleChild != null) {
                int bottomY = lastVisibleChild.getBottom() - this.mHalfSeparatorThickness;
                canvas.drawLine((float) (lastVisibleChild.getLeft() + this.mSectionSeparatorInset), (float) bottomY, (float) (lastVisibleChild.getRight() - this.mSectionSeparatorInset), (float) bottomY, this.mSectionSeparatorPaint);
            }
        }
    }
}
