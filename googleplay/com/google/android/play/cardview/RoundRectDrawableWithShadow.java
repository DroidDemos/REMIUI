package com.google.android.play.cardview;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.FillType;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.os.Build.VERSION;
import com.google.android.play.R;

class RoundRectDrawableWithShadow extends CardViewBackgroundDrawable {
    private static RectF sCornerRect;
    private final RectF mCardBounds;
    private Paint mCornerShadowPaint;
    private Path mCornerShadowPath;
    private boolean mDirty;
    private Paint mEdgeShadowPaint;
    private float mRawShadowSize;
    private final int mShadowEndColor;
    private float mShadowSize;
    private final int mShadowStartColor;

    RoundRectDrawableWithShadow(Resources resources, ColorStateList colorStateList, float radius, float shadowSize, float inset) {
        super(colorStateList, radius, inset);
        this.mDirty = true;
        this.mShadowStartColor = resources.getColor(R.color.play_card_shadow_start_color);
        this.mShadowEndColor = resources.getColor(R.color.play_card_shadow_end_color);
        setShadowSize(shadowSize);
        this.mCornerShadowPaint = new Paint(5);
        this.mCornerShadowPaint.setStyle(Style.FILL);
        this.mCornerShadowPaint.setDither(true);
        this.mCardBounds = new RectF();
        this.mEdgeShadowPaint = new Paint(this.mCornerShadowPaint);
    }

    public void setAlpha(int alpha) {
        this.mPaint.setAlpha(alpha);
        this.mCornerShadowPaint.setAlpha(alpha);
        this.mEdgeShadowPaint.setAlpha(alpha);
    }

    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.mDirty = true;
    }

    void setShadowSize(float shadowSize) {
        if (shadowSize < 0.0f) {
            throw new IllegalArgumentException("invalid shadow size");
        } else if (this.mRawShadowSize != shadowSize) {
            this.mRawShadowSize = shadowSize;
            this.mShadowSize = 1.5f * shadowSize;
            this.mDirty = true;
            invalidateSelf();
        }
    }

    public void setColorFilter(ColorFilter cf) {
        this.mPaint.setColorFilter(cf);
        this.mCornerShadowPaint.setColorFilter(cf);
        this.mEdgeShadowPaint.setColorFilter(cf);
    }

    public int getOpacity() {
        return -1;
    }

    public void draw(Canvas canvas) {
        if (this.mDirty) {
            buildComponents(getBounds());
            this.mDirty = false;
        }
        canvas.translate(0.0f, this.mRawShadowSize / 2.0f);
        drawShadow(canvas);
        canvas.translate(0.0f, (-this.mRawShadowSize) / 2.0f);
        if (VERSION.SDK_INT >= 17) {
            canvas.drawRoundRect(this.mCardBounds, this.mCornerRadius, this.mCornerRadius, this.mPaint);
            return;
        }
        if (sCornerRect == null) {
            sCornerRect = new RectF();
        }
        float diameter = this.mCornerRadius * 2.0f;
        float innerWidth = this.mCardBounds.width() - diameter;
        float innerHeight = this.mCardBounds.height() - diameter;
        sCornerRect.set(this.mCardBounds.left, this.mCardBounds.top, this.mCardBounds.left + (this.mCornerRadius * 2.0f), this.mCardBounds.top + (this.mCornerRadius * 2.0f));
        canvas.drawArc(sCornerRect, 180.0f, 90.0f, true, this.mPaint);
        sCornerRect.offset(innerWidth, 0.0f);
        canvas.drawArc(sCornerRect, 270.0f, 90.0f, true, this.mPaint);
        sCornerRect.offset(0.0f, innerHeight);
        canvas.drawArc(sCornerRect, 0.0f, 90.0f, true, this.mPaint);
        sCornerRect.offset(-innerWidth, 0.0f);
        canvas.drawArc(sCornerRect, 90.0f, 90.0f, true, this.mPaint);
        Canvas canvas2 = canvas;
        canvas2.drawRect(this.mCornerRadius + this.mCardBounds.left, this.mCardBounds.top, this.mCardBounds.right - this.mCornerRadius, this.mCornerRadius + this.mCardBounds.top, this.mPaint);
        canvas2 = canvas;
        canvas2.drawRect(this.mCornerRadius + this.mCardBounds.left, this.mCardBounds.bottom - this.mCornerRadius, this.mCardBounds.right - this.mCornerRadius, this.mCardBounds.bottom, this.mPaint);
        canvas2 = canvas;
        canvas2.drawRect(this.mCardBounds.left, this.mCornerRadius + this.mCardBounds.top, this.mCardBounds.right, this.mCardBounds.bottom - this.mCornerRadius, this.mPaint);
    }

    private void drawShadow(Canvas canvas) {
        boolean drawHorizontalEdges;
        boolean drawVerticalEdges;
        float edgeShadowTop = (-this.mCornerRadius) - this.mShadowSize;
        float inset = this.mCornerRadius + (this.mRawShadowSize / 2.0f);
        if (this.mCardBounds.width() - (2.0f * inset) > 0.0f) {
            drawHorizontalEdges = true;
        } else {
            drawHorizontalEdges = false;
        }
        if (this.mCardBounds.height() - (2.0f * inset) > 0.0f) {
            drawVerticalEdges = true;
        } else {
            drawVerticalEdges = false;
        }
        int saved = canvas.save();
        canvas.translate(this.mCardBounds.left + inset, this.mCardBounds.top + inset);
        canvas.drawPath(this.mCornerShadowPath, this.mCornerShadowPaint);
        if (drawHorizontalEdges) {
            canvas.drawRect(0.0f, edgeShadowTop, this.mCardBounds.width() - (2.0f * inset), -this.mCornerRadius, this.mEdgeShadowPaint);
        }
        canvas.restoreToCount(saved);
        saved = canvas.save();
        canvas.translate(this.mCardBounds.right - inset, this.mCardBounds.bottom - inset);
        canvas.rotate(180.0f);
        canvas.drawPath(this.mCornerShadowPath, this.mCornerShadowPaint);
        if (drawHorizontalEdges) {
            Canvas canvas2 = canvas;
            canvas2.drawRect(0.0f, edgeShadowTop, this.mCardBounds.width() - (2.0f * inset), this.mShadowSize + (-this.mCornerRadius), this.mEdgeShadowPaint);
        }
        canvas.restoreToCount(saved);
        saved = canvas.save();
        canvas.translate(this.mCardBounds.left + inset, this.mCardBounds.bottom - inset);
        canvas.rotate(270.0f);
        canvas.drawPath(this.mCornerShadowPath, this.mCornerShadowPaint);
        if (drawVerticalEdges) {
            canvas.drawRect(0.0f, edgeShadowTop, this.mCardBounds.height() - (2.0f * inset), -this.mCornerRadius, this.mEdgeShadowPaint);
        }
        canvas.restoreToCount(saved);
        saved = canvas.save();
        canvas.translate(this.mCardBounds.right - inset, this.mCardBounds.top + inset);
        canvas.rotate(90.0f);
        canvas.drawPath(this.mCornerShadowPath, this.mCornerShadowPaint);
        if (drawVerticalEdges) {
            canvas.drawRect(0.0f, edgeShadowTop, this.mCardBounds.height() - (2.0f * inset), -this.mCornerRadius, this.mEdgeShadowPaint);
        }
        canvas.restoreToCount(saved);
    }

    private void buildShadowCorners() {
        RectF innerBounds = new RectF(-this.mCornerRadius, -this.mCornerRadius, this.mCornerRadius, this.mCornerRadius);
        RectF outerBounds = new RectF(innerBounds);
        outerBounds.inset(-this.mShadowSize, -this.mShadowSize);
        if (this.mCornerShadowPath == null) {
            this.mCornerShadowPath = new Path();
        } else {
            this.mCornerShadowPath.reset();
        }
        this.mCornerShadowPath.setFillType(FillType.EVEN_ODD);
        this.mCornerShadowPath.moveTo(-this.mCornerRadius, 0.0f);
        this.mCornerShadowPath.rLineTo(-this.mShadowSize, 0.0f);
        this.mCornerShadowPath.arcTo(outerBounds, 180.0f, 90.0f, false);
        this.mCornerShadowPath.arcTo(innerBounds, 270.0f, -90.0f, false);
        this.mCornerShadowPath.close();
        float startRatio = this.mCornerRadius / (this.mCornerRadius + this.mShadowSize);
        this.mCornerShadowPaint.setShader(new RadialGradient(0.0f, 0.0f, this.mCornerRadius + this.mShadowSize, new int[]{this.mShadowStartColor, this.mShadowStartColor, this.mShadowEndColor}, new float[]{0.0f, startRatio, 1.0f}, TileMode.CLAMP));
        this.mEdgeShadowPaint.setShader(new LinearGradient(0.0f, (-this.mCornerRadius) + this.mShadowSize, 0.0f, (-this.mCornerRadius) - this.mShadowSize, new int[]{this.mShadowStartColor, this.mShadowStartColor, this.mShadowEndColor}, new float[]{0.0f, 0.5f, 1.0f}, TileMode.CLAMP));
    }

    private void buildComponents(Rect bounds) {
        this.mCardBounds.set(bounds);
        this.mCardBounds.inset(this.mInset, this.mInset);
        buildShadowCorners();
    }
}
