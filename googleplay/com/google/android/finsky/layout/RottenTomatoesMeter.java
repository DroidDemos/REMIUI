package com.google.android.finsky.layout;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.View;
import com.android.vending.R;

public class RottenTomatoesMeter extends View {
    private final int mAccentFillColor;
    private final int mBackgroundFillColor;
    private int mCurrentPercentValue;
    private final Paint mFillPaint;

    public RottenTomatoesMeter(Context context) {
        this(context, null);
    }

    public RottenTomatoesMeter(Context context, AttributeSet attrs) {
        super(context, attrs);
        Resources res = context.getResources();
        this.mBackgroundFillColor = res.getColor(R.color.play_main_background);
        this.mAccentFillColor = res.getColor(R.color.play_movies_primary);
        this.mFillPaint = new Paint();
        this.mFillPaint.setStyle(Style.FILL);
        this.mFillPaint.setAntiAlias(true);
        setWillNotDraw(false);
    }

    public void setPercentValue(int percentValue) {
        if (this.mCurrentPercentValue != percentValue) {
            this.mCurrentPercentValue = percentValue;
            invalidate();
        }
    }

    protected void onDraw(Canvas canvas) {
        int width = getWidth();
        int height = getHeight();
        int filledWidth = (this.mCurrentPercentValue * width) / 100;
        if (filledWidth > 0) {
            this.mFillPaint.setColor(this.mAccentFillColor);
            canvas.drawRect(0.0f, 0.0f, (float) filledWidth, (float) height, this.mFillPaint);
        }
        int backgroundWidth = width - filledWidth;
        if (backgroundWidth > 0) {
            this.mFillPaint.setColor(this.mBackgroundFillColor);
            canvas.drawRect((float) (width - backgroundWidth), 0.0f, (float) width, (float) height, this.mFillPaint);
        }
    }
}
