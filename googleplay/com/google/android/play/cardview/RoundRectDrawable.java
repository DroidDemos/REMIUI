package com.google.android.play.cardview;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.Rect;
import android.graphics.RectF;

class RoundRectDrawable extends CardViewBackgroundDrawable {
    private final RectF mBoundsF;
    private final Rect mBoundsI;

    RoundRectDrawable(ColorStateList colorStateList, float radius, float inset) {
        super(colorStateList, radius, inset);
        this.mBoundsF = new RectF();
        this.mBoundsI = new Rect();
    }

    public void draw(Canvas canvas) {
        canvas.drawRoundRect(this.mBoundsF, this.mCornerRadius, this.mCornerRadius, this.mPaint);
    }

    private void updateBounds(Rect bounds) {
        this.mBoundsI.set(bounds);
        this.mBoundsI.inset((int) Math.ceil((double) this.mInset), (int) Math.ceil((double) this.mInset));
        this.mBoundsF.set(this.mBoundsI);
    }

    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        updateBounds(bounds);
    }

    public void getOutline(Outline outline) {
        outline.setRoundRect(this.mBoundsI, this.mCornerRadius);
    }

    public void setAlpha(int alpha) {
    }

    public void setColorFilter(ColorFilter cf) {
    }
}
