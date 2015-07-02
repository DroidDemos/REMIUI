package com.google.android.play.image;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import com.google.android.play.R;

public class AvatarCropTransformation implements BitmapTransformation {
    private static AvatarCropTransformation sInstanceNoRing;
    private static AvatarCropTransformation sInstanceWithRing;
    private final Paint mCropPaint;
    private final int mDecorationThresholdMax;
    private final int mDecorationThresholdMin;
    private final Paint mDefaultOutlinePaint;
    private final Paint mDefaultRingPaint;
    private final Rect mDestinationRectangle;
    private final boolean mDrawRingAboveThreshold;
    private final float mDropShadowSizeMax;
    private final float mDropShadowSizeMin;
    private final Paint mFillToSizePaint;
    private final int mFocusedOutlineColor;
    private boolean mForceFill;
    private final Paint mHighlightOutlinePaint;
    private final Paint mPressedFillPaint;
    private final int mPressedOutlineColor;
    private final RectF mRectangle;
    private final Paint mResizePaint;
    private final int mRingSizeMax;
    private final int mRingSizeMin;
    private final Rect mSourceRectangle;

    public static synchronized AvatarCropTransformation getFullAvatarCrop(Resources res) {
        AvatarCropTransformation avatarCropTransformation;
        synchronized (AvatarCropTransformation.class) {
            if (sInstanceWithRing == null) {
                sInstanceWithRing = new AvatarCropTransformation(res, true);
            }
            avatarCropTransformation = sInstanceWithRing;
        }
        return avatarCropTransformation;
    }

    public static synchronized AvatarCropTransformation getNoRingAvatarCrop(Resources res) {
        AvatarCropTransformation avatarCropTransformation;
        synchronized (AvatarCropTransformation.class) {
            if (sInstanceNoRing == null) {
                sInstanceNoRing = new AvatarCropTransformation(res, false);
            }
            avatarCropTransformation = sInstanceNoRing;
        }
        return avatarCropTransformation;
    }

    public AvatarCropTransformation(Resources res, boolean drawRingAboveThreshold) {
        this.mForceFill = false;
        this.mDecorationThresholdMin = res.getDimensionPixelSize(R.dimen.play_avatar_decoration_threshold_min);
        this.mDecorationThresholdMax = res.getDimensionPixelSize(R.dimen.play_avatar_decoration_threshold_max);
        this.mRingSizeMin = res.getDimensionPixelSize(R.dimen.play_avatar_ring_size_min);
        this.mRingSizeMax = res.getDimensionPixelSize(R.dimen.play_avatar_ring_size_max);
        this.mDropShadowSizeMin = ((float) res.getDimensionPixelSize(R.dimen.play_avatar_drop_shadow_min)) * 0.5f;
        this.mDropShadowSizeMax = ((float) res.getDimensionPixelSize(R.dimen.play_avatar_drop_shadow_max)) * 0.5f;
        int outlineColor = res.getColor(R.color.play_avatar_outline);
        int ringColor = res.getColor(R.color.play_white);
        float outlineStrokeWidth = 0.5f * ((float) res.getDimensionPixelSize(R.dimen.play_avatar_noring_outline));
        this.mDefaultOutlinePaint = new Paint();
        this.mDefaultOutlinePaint.setColor(outlineColor);
        this.mDefaultOutlinePaint.setStrokeWidth(outlineStrokeWidth);
        this.mDefaultOutlinePaint.setStyle(Style.STROKE);
        this.mDefaultOutlinePaint.setAntiAlias(true);
        this.mDefaultRingPaint = new Paint();
        this.mDefaultRingPaint.setColor(ringColor);
        this.mDefaultRingPaint.setStyle(Style.STROKE);
        this.mDefaultRingPaint.setAntiAlias(true);
        this.mCropPaint = new Paint(2);
        this.mCropPaint.setAntiAlias(true);
        this.mResizePaint = new Paint(2);
        this.mRectangle = new RectF();
        this.mPressedFillPaint = new Paint();
        this.mPressedFillPaint.setColor(res.getColor(R.color.play_avatar_pressed_fill));
        this.mPressedFillPaint.setAntiAlias(true);
        this.mPressedFillPaint.setStyle(Style.FILL);
        this.mPressedOutlineColor = res.getColor(R.color.play_avatar_pressed_outline);
        this.mFocusedOutlineColor = res.getColor(R.color.play_avatar_focused_outline);
        this.mHighlightOutlinePaint = new Paint();
        this.mHighlightOutlinePaint.setAntiAlias(true);
        this.mHighlightOutlinePaint.setStrokeWidth(outlineStrokeWidth);
        this.mHighlightOutlinePaint.setStyle(Style.STROKE);
        this.mFillToSizePaint = new Paint();
        this.mFillToSizePaint.setColor(res.getColor(R.color.play_white));
        this.mFillToSizePaint.setStyle(Style.FILL);
        this.mSourceRectangle = new Rect();
        this.mDestinationRectangle = new Rect();
        this.mDrawRingAboveThreshold = drawRingAboveThreshold;
    }

    private float getDropShadowSize(int avatarWidth, int avatarHeight) {
        int avatarSize = Math.max(avatarWidth, avatarHeight);
        return avatarSize < this.mDecorationThresholdMin ? 0.0f : interpolate((float) this.mDecorationThresholdMin, (float) this.mDecorationThresholdMax, this.mDropShadowSizeMin, this.mDropShadowSizeMax, (float) avatarSize);
    }

    private float getRingOutlineSize(int avatarWidth, int avatarHeight) {
        return interpolate((float) this.mDecorationThresholdMin, (float) this.mDecorationThresholdMax, (float) this.mRingSizeMin, (float) this.mRingSizeMax, (float) Math.max(avatarWidth, avatarHeight));
    }

    public int getTransformationInset(int outputWidth, int outputHeight) {
        if (Math.max(outputWidth, outputHeight) < this.mDecorationThresholdMin) {
            return 0;
        }
        return (int) ((2.0f * getRingOutlineSize(outputWidth, outputHeight)) + getDropShadowSize(outputWidth, outputHeight));
    }

    public Bitmap transform(Bitmap source, int outputWidth, int outputHeight) {
        if (source == null) {
            return null;
        }
        int sourceWidth;
        int avatarSize = Math.max(outputWidth, outputHeight);
        boolean drawDecorations = avatarSize >= this.mDecorationThresholdMin;
        float ringOutline = this.mDrawRingAboveThreshold ? getRingOutlineSize(outputWidth, outputHeight) : 0.0f;
        float dropShadowSize = getDropShadowSize(outputWidth, outputHeight);
        int dropShadowAlpha = (int) interpolate((float) this.mDecorationThresholdMin, (float) this.mDecorationThresholdMax, 48.0f, 64.0f, (float) avatarSize);
        int transformationInset = getTransformationInset(outputWidth, outputHeight);
        int dropShadowColor = dropShadowAlpha << 24;
        int sourceWidth2 = source.getWidth();
        int sourceHeight = source.getHeight();
        int largerSourceSize = Math.max(sourceWidth2, sourceHeight);
        int smallerSourceSize = Math.min(sourceWidth2, sourceHeight);
        if (Math.abs(sourceWidth2 - sourceHeight) > 1 || largerSourceSize < avatarSize - transformationInset || smallerSourceSize > avatarSize || this.mForceFill) {
            Bitmap padded = Bitmap.createBitmap(avatarSize, avatarSize, Config.ARGB_8888);
            Canvas paddedCanvas = new Canvas(padded);
            paddedCanvas.drawRect(0.0f, 0.0f, (float) avatarSize, (float) avatarSize, this.mFillToSizePaint);
            this.mSourceRectangle.set(0, 0, sourceWidth2, sourceHeight);
            float scaleFactor = ((float) avatarSize) / ((float) Math.max(sourceWidth2, sourceHeight));
            int destinationWidth = (int) (((float) sourceWidth2) * scaleFactor);
            int destinationHeight = (int) (((float) sourceHeight) * scaleFactor);
            int destinationX = (avatarSize - destinationWidth) / 2;
            int destinationY = (avatarSize - destinationHeight) / 2;
            this.mDestinationRectangle.set(destinationX, destinationY, destinationX + destinationWidth, destinationY + destinationHeight);
            paddedCanvas.drawBitmap(source, this.mSourceRectangle, this.mDestinationRectangle, this.mResizePaint);
            source = padded;
            sourceWidth = source.getWidth();
            sourceHeight = source.getHeight();
        } else {
            sourceWidth = sourceWidth2;
        }
        this.mCropPaint.setShader(new BitmapShader(source, TileMode.CLAMP, TileMode.CLAMP));
        Bitmap round = Bitmap.createBitmap(avatarSize, avatarSize, Config.ARGB_8888);
        Canvas roundCanvas = new Canvas(round);
        float cornerRadius = ((float) avatarSize) / 2.0f;
        float boundsDelta = 1.0f;
        if (drawDecorations) {
            boundsDelta = 1.0f + dropShadowSize;
            roundCanvas.translate(dropShadowSize / 2.0f, 0.0f);
        }
        this.mRectangle.set(0.0f, 0.0f, ((float) outputWidth) - boundsDelta, ((float) outputWidth) - boundsDelta);
        if (drawDecorations) {
            drawOutline(roundCanvas, dropShadowSize, dropShadowColor);
            drawAvatar(roundCanvas, sourceWidth, cornerRadius, ringOutline, ((((float) avatarSize) - dropShadowSize) - Math.max(1.0f, 2.0f * ringOutline)) / ((float) sourceWidth));
            if (this.mDrawRingAboveThreshold) {
                drawRing(roundCanvas, ringOutline);
            }
        } else {
            drawAvatar(roundCanvas, sourceWidth, cornerRadius, 0.0f, 1.0f);
            drawOutline(roundCanvas, 0.0f, 0);
        }
        this.mCropPaint.setShader(null);
        return round;
    }

    private float interpolate(float fromRangeMin, float fromRangeMax, float toRangeMin, float toRangeMax, float from) {
        if (from <= fromRangeMin) {
            return toRangeMin;
        }
        if (from >= fromRangeMax) {
            return toRangeMax;
        }
        return fromRangeMin != fromRangeMax ? toRangeMin + (((from - fromRangeMin) * (toRangeMax - toRangeMin)) / (fromRangeMax - fromRangeMin)) : toRangeMin;
    }

    private void drawAvatar(Canvas canvas, int sourceSize, float cornerRadius, float extraInset, float scale) {
        float origLeft = this.mRectangle.left;
        float origRight = this.mRectangle.right;
        float origTop = this.mRectangle.top;
        float origBottom = this.mRectangle.bottom;
        this.mRectangle.left = 0.0f;
        this.mRectangle.top = 0.0f;
        this.mRectangle.right = (float) sourceSize;
        this.mRectangle.bottom = (float) sourceSize;
        canvas.save();
        canvas.scale(scale, scale);
        canvas.translate(extraInset, extraInset);
        canvas.drawRoundRect(this.mRectangle, cornerRadius, cornerRadius, this.mCropPaint);
        canvas.restore();
        this.mRectangle.left = origLeft;
        this.mRectangle.right = origRight;
        this.mRectangle.top = origTop;
        this.mRectangle.bottom = origBottom;
    }

    private void drawOutline(Canvas canvas, float dropShadowRadius, int dropShadowColor) {
        float origLeft = this.mRectangle.left;
        float origRight = this.mRectangle.right;
        float origTop = this.mRectangle.top;
        float origBottom = this.mRectangle.bottom;
        this.mDefaultOutlinePaint.setStrokeWidth(2.0f * dropShadowRadius);
        float inset = this.mDefaultOutlinePaint.getStrokeWidth() / 2.0f;
        RectF rectF = this.mRectangle;
        rectF.left += inset - (dropShadowRadius / 3.0f);
        rectF = this.mRectangle;
        rectF.top += inset + dropShadowRadius;
        rectF = this.mRectangle;
        rectF.right -= inset - (dropShadowRadius / 3.0f);
        rectF = this.mRectangle;
        rectF.bottom -= inset - dropShadowRadius;
        this.mDefaultOutlinePaint.setColor(dropShadowColor);
        canvas.drawOval(this.mRectangle, this.mDefaultOutlinePaint);
        this.mRectangle.left = origLeft;
        this.mRectangle.right = origRight;
        this.mRectangle.top = origTop;
        this.mRectangle.bottom = origBottom;
    }

    private void drawRing(Canvas canvas, float ringOutline) {
        float origLeft = this.mRectangle.left;
        float origRight = this.mRectangle.right;
        float origTop = this.mRectangle.top;
        float origBottom = this.mRectangle.bottom;
        this.mDefaultRingPaint.setStrokeWidth(ringOutline);
        float inset = ringOutline / 2.0f;
        RectF rectF = this.mRectangle;
        rectF.left += inset;
        rectF = this.mRectangle;
        rectF.top += inset;
        rectF = this.mRectangle;
        rectF.right -= inset;
        rectF = this.mRectangle;
        rectF.bottom -= inset;
        canvas.drawOval(this.mRectangle, this.mDefaultRingPaint);
        this.mRectangle.left = origLeft;
        this.mRectangle.right = origRight;
        this.mRectangle.top = origTop;
        this.mRectangle.bottom = origBottom;
    }

    public void drawFocusedOverlay(Canvas canvas, int width, int height) {
        float offset = getDropShadowSize(width, height);
        width = (int) (((float) width) - offset);
        height = (int) (((float) height) - offset);
        canvas.save();
        canvas.translate(offset / 2.0f, 0.0f);
        float overlayRadius = Math.min(((float) width) / 2.0f, ((float) height) / 2.0f);
        float outline = this.mHighlightOutlinePaint.getStrokeWidth();
        this.mHighlightOutlinePaint.setColor(this.mFocusedOutlineColor);
        canvas.drawCircle(overlayRadius, overlayRadius, overlayRadius - (outline / 2.0f), this.mHighlightOutlinePaint);
        canvas.restore();
    }

    public void drawPressedOverlay(Canvas canvas, int width, int height) {
        float offset = getDropShadowSize(width, height);
        width = (int) (((float) width) - offset);
        height = (int) (((float) height) - offset);
        canvas.save();
        canvas.translate(offset / 2.0f, 0.0f);
        float overlayRadius = Math.min(((float) width) / 2.0f, ((float) height) / 2.0f);
        canvas.drawCircle(overlayRadius, overlayRadius, overlayRadius, this.mPressedFillPaint);
        float outline = this.mHighlightOutlinePaint.getStrokeWidth();
        this.mHighlightOutlinePaint.setColor(this.mPressedOutlineColor);
        canvas.drawCircle(overlayRadius, overlayRadius, overlayRadius - (outline / 2.0f), this.mHighlightOutlinePaint);
        canvas.restore();
    }

    public void setFillToSizeColor(int color) {
        this.mFillToSizePaint.setColor(color);
        this.mForceFill = true;
    }
}
