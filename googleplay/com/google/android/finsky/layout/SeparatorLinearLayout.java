package com.google.android.finsky.layout;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import com.android.vending.R;

public class SeparatorLinearLayout extends LinearLayout {
    private boolean mDrawSeparator;
    private final int mHalfSeparatorThickness;
    private final Paint mSeparatorPaint;
    private int mSeparatorPosition;

    public SeparatorLinearLayout(Context context) {
        this(context, null);
    }

    public SeparatorLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);
        Resources res = context.getResources();
        int separatorThickness = res.getDimensionPixelSize(R.dimen.play_hairline_separator_thickness);
        this.mHalfSeparatorThickness = (separatorThickness + 1) / 2;
        this.mSeparatorPaint = new Paint();
        this.mSeparatorPaint.setColor(getSeparatorColor(res));
        this.mSeparatorPaint.setStrokeWidth((float) separatorThickness);
        this.mDrawSeparator = true;
        TypedArray viewAttrs = context.obtainStyledAttributes(attrs, R.styleable.SeparatorLinearLayout);
        this.mSeparatorPosition = viewAttrs.getInt(0, 2);
        viewAttrs.recycle();
    }

    protected int getSeparatorColor(Resources res) {
        return res.getColor(R.color.play_translucent_separator_line);
    }

    public void showSeparator() {
        if (!this.mDrawSeparator) {
            this.mDrawSeparator = true;
            invalidate();
        }
    }

    public void hideSeparator() {
        if (this.mDrawSeparator) {
            this.mDrawSeparator = false;
            invalidate();
        }
    }

    public void onDraw(Canvas canvas) {
        if (this.mDrawSeparator) {
            if ((this.mSeparatorPosition & 1) != 0) {
                int topY = this.mHalfSeparatorThickness;
                canvas.drawLine(0.0f, (float) topY, (float) getWidth(), (float) topY, this.mSeparatorPaint);
            }
            if ((this.mSeparatorPosition & 2) != 0) {
                int bottomY = getHeight() - this.mHalfSeparatorThickness;
                canvas.drawLine(0.0f, (float) bottomY, (float) getWidth(), (float) bottomY, this.mSeparatorPaint);
            }
        }
        super.onDraw(canvas);
    }
}
