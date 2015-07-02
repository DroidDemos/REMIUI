package com.google.android.play.layout;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import com.google.android.play.R;

public class PlaySeparatorLayout extends RelativeLayout {
    private final int mHalfSeparatorThickness;
    private final int mSeparatorPaddingBottom;
    private final int mSeparatorPaddingLeft;
    private final int mSeparatorPaddingRight;
    private final int mSeparatorPaddingTop;
    private final Paint mSeparatorPaint;
    private final int mSeparatorThickness;
    private boolean mSeparatorVisible;

    public PlaySeparatorLayout(Context context) {
        this(context, null, 0);
    }

    public PlaySeparatorLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PlaySeparatorLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setWillNotDraw(false);
        this.mSeparatorVisible = false;
        Resources res = context.getResources();
        this.mSeparatorThickness = res.getDimensionPixelSize(R.dimen.play_hairline_separator_thickness);
        this.mHalfSeparatorThickness = (this.mSeparatorThickness + 1) / 2;
        this.mSeparatorPaint = new Paint();
        this.mSeparatorPaint.setColor(res.getColor(R.color.play_reason_separator));
        this.mSeparatorPaint.setStrokeWidth((float) this.mSeparatorThickness);
        TypedArray viewAttrs = context.obtainStyledAttributes(attrs, R.styleable.PlaySeparatorLayout);
        this.mSeparatorPaddingTop = viewAttrs.getDimensionPixelSize(R.styleable.PlaySeparatorLayout_separator_padding_top, 0);
        this.mSeparatorPaddingBottom = viewAttrs.getDimensionPixelSize(R.styleable.PlaySeparatorLayout_separator_padding_bottom, 0);
        this.mSeparatorPaddingLeft = viewAttrs.getDimensionPixelSize(R.styleable.PlaySeparatorLayout_separator_padding_left, 0);
        this.mSeparatorPaddingRight = viewAttrs.getDimensionPixelSize(R.styleable.PlaySeparatorLayout_separator_padding_right, 0);
        viewAttrs.recycle();
    }

    public void setSeparatorVisible(boolean isSeparatorVisible) {
        if (this.mSeparatorVisible != isSeparatorVisible) {
            int topPadding;
            this.mSeparatorVisible = isSeparatorVisible;
            if (isSeparatorVisible) {
                topPadding = (this.mSeparatorPaddingTop + this.mSeparatorPaddingBottom) + this.mSeparatorThickness;
            } else {
                topPadding = 0;
            }
            setPadding(0, topPadding, 0, 0);
            invalidate();
        }
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.mSeparatorVisible) {
            int separatorTop = this.mSeparatorPaddingTop + this.mHalfSeparatorThickness;
            canvas.drawLine((float) this.mSeparatorPaddingLeft, (float) separatorTop, (float) (getWidth() - this.mSeparatorPaddingRight), (float) separatorTop, this.mSeparatorPaint);
        }
    }
}
