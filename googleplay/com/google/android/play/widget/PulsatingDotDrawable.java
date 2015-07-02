package com.google.android.play.widget;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;

public class PulsatingDotDrawable extends Drawable implements Animatable {
    protected boolean mIsAnimating;
    protected final float mMinRadius;
    protected final long mOffsetMs;
    protected final Paint mPaint;
    protected final long mPeriodMs;
    protected final float mRadiusSpan;
    protected long mStartMs;

    public PulsatingDotDrawable(int color, long periodMs, long offsetMs, float minRadius, float maxRadius) {
        this.mPeriodMs = periodMs;
        this.mOffsetMs = offsetMs;
        this.mMinRadius = Math.max(0.0f, minRadius);
        this.mRadiusSpan = Math.min(maxRadius, 1.0f) - minRadius;
        this.mPaint = new Paint();
        this.mPaint.setAntiAlias(true);
        this.mPaint.setColor(color);
        this.mPaint.setStyle(Style.FILL_AND_STROKE);
    }

    public void draw(Canvas canvas) {
        int width = canvas.getWidth();
        int height = canvas.getHeight();
        canvas.saveLayerAlpha(0.0f, 0.0f, (float) width, (float) height, 255, 31);
        canvas.drawCircle(((float) width) / 2.0f, ((float) height) / 2.0f, (calculateRadiusFraction() * ((float) Math.min(width, height))) / 2.0f, this.mPaint);
        canvas.restore();
        if (isRunning()) {
            invalidateSelf();
        }
    }

    public int getOpacity() {
        return -3;
    }

    public void setAlpha(int alpha) {
        this.mPaint.setAlpha(alpha);
    }

    public void setColorFilter(ColorFilter cf) {
        this.mPaint.setColorFilter(cf);
    }

    public boolean isRunning() {
        return this.mIsAnimating;
    }

    public void start() {
        if (!isRunning()) {
            this.mStartMs = System.currentTimeMillis();
            this.mIsAnimating = true;
            invalidateSelf();
        }
    }

    public void stop() {
        this.mIsAnimating = false;
    }

    protected float calculateRadiusFraction() {
        return this.mMinRadius + (this.mRadiusSpan * (0.5f + (((float) Math.sin(6.283185307179586d * ((double) (((float) ((this.mOffsetMs + System.currentTimeMillis()) % this.mPeriodMs)) / ((float) this.mPeriodMs))))) * 0.5f)));
    }
}
