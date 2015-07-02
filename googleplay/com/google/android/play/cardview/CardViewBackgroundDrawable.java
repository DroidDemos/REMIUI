package com.google.android.play.cardview;

import android.content.res.ColorStateList;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;

public abstract class CardViewBackgroundDrawable extends Drawable {
    protected ColorStateList mColorStateList;
    protected float mCornerRadius;
    protected float mInset;
    protected Paint mPaint;

    CardViewBackgroundDrawable(ColorStateList colorStateList, float radius, float inset) {
        this.mCornerRadius = radius;
        this.mColorStateList = colorStateList;
        this.mPaint = new Paint(5);
        this.mPaint.setColor(this.mColorStateList.getDefaultColor());
        this.mInset = inset;
    }

    protected boolean onStateChange(int[] states) {
        if (this.mColorStateList == null || !this.mColorStateList.isStateful()) {
            return super.onStateChange(states);
        }
        this.mPaint.setColor(this.mColorStateList.getColorForState(states, this.mColorStateList.getDefaultColor()));
        invalidateSelf();
        return true;
    }

    public boolean isStateful() {
        return this.mColorStateList != null && this.mColorStateList.isStateful();
    }

    public void setBackgroundColor(int color) {
        this.mColorStateList = null;
        this.mPaint.setColor(color);
        invalidateSelf();
    }

    public void setBackgroundColorStateList(ColorStateList colorStateList) {
        this.mColorStateList = colorStateList;
        this.mPaint.setColor(this.mColorStateList.getColorForState(getState(), this.mColorStateList.getDefaultColor()));
        invalidateSelf();
    }

    public int getOpacity() {
        return -1;
    }
}
