package com.google.android.play.search;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

public class ArrowOrBurgerDrawable extends Drawable {
    private static final float HALF_SQRT_2;
    private float mArrowness;
    private final boolean mIsRtl;
    private final Paint mPaint;
    private float mPreDrawScale;

    static {
        HALF_SQRT_2 = (float) (Math.sqrt(2.0d) / 2.0d);
    }

    public ArrowOrBurgerDrawable(int color, boolean isRtl) {
        this.mPaint = new Paint();
        this.mArrowness = 1.0f;
        this.mPaint.setColor(color);
        this.mPaint.setStrokeCap(Cap.SQUARE);
        this.mPaint.setStrokeWidth(0.286f);
        this.mPaint.setAntiAlias(true);
        this.mIsRtl = isRtl;
    }

    protected void onBoundsChange(Rect bounds) {
        this.mPreDrawScale = ((float) Math.min(bounds.width(), bounds.height())) / 2.572f;
        this.mPaint.setStrokeWidth(0.286f);
    }

    public void draw(Canvas canvas) {
        canvas.save();
        canvas.translate((float) getBounds().centerX(), (float) getBounds().centerY());
        canvas.scale(this.mPreDrawScale, this.mPreDrawScale);
        canvas.rotate(((float) (this.mIsRtl ? 180 : 0)) + (180.0f * (1.0f - this.mArrowness)));
        float burger_length = 1.0f + ((0.5f - this.mArrowness) * 0.286f);
        canvas.drawLine(-burger_length, 0.0f, burger_length, 0.0f, this.mPaint);
        float rotation = 45.0f * this.mArrowness;
        float length = burger_length + (this.mArrowness * (HALF_SQRT_2 - burger_length));
        canvas.rotate(rotation);
        canvas.drawLine(-length, HALF_SQRT_2, length, HALF_SQRT_2, this.mPaint);
        canvas.rotate(-2.0f * rotation);
        canvas.drawLine(-length, -HALF_SQRT_2, length, -HALF_SQRT_2, this.mPaint);
        canvas.restore();
    }

    public void setHowArrowIsTheBurger(float howMuch) {
        this.mArrowness = howMuch;
        invalidateSelf();
    }

    public void setAsBurger(boolean burger) {
        setHowArrowIsTheBurger(burger ? 0.0f : 1.0f);
    }

    public void setAlpha(int alpha) {
        this.mPaint.setAlpha(alpha);
    }

    public void setColorFilter(ColorFilter cf) {
        this.mPaint.setColorFilter(cf);
    }

    public int getOpacity() {
        return -2;
    }
}
