package com.google.android.play.headerlist;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

class PlayHeaderStatusBarUnderlay extends View implements AnimatorListener {
    private static Interpolator sLinearInterpolator;
    private ObjectAnimator mAlphaAnimator;
    private int mDrawHeight;
    private int mFadeDirection;
    private int mOverlayColor;
    private final Paint mPaint;
    private boolean mProtectingControls;
    private int mStatusBarHeight;
    private int mUnderlayColor;

    public PlayHeaderStatusBarUnderlay(Context context) {
        this(context, null);
    }

    public PlayHeaderStatusBarUnderlay(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (PlayHeaderListLayout.SUPPORT_DRAW_STATUS_BAR) {
            this.mPaint = new Paint();
            this.mPaint.setAntiAlias(false);
            setAlpha(0.0f);
            return;
        }
        this.mPaint = null;
    }

    public void initColors(int overlayColor, int underlayColor) {
        this.mOverlayColor = overlayColor;
        this.mUnderlayColor = underlayColor;
    }

    public void setStatusBarHeight(int statusBarHeight) {
        if (PlayHeaderListLayout.SUPPORT_DRAW_STATUS_BAR && this.mStatusBarHeight != statusBarHeight) {
            this.mStatusBarHeight = statusBarHeight;
            invalidate();
        }
    }

    protected void onDraw(Canvas canvas) {
        if (PlayHeaderListLayout.SUPPORT_DRAW_STATUS_BAR) {
            int width = getWidth();
            this.mPaint.setColor(this.mUnderlayColor);
            canvas.drawRect(0.0f, 0.0f, (float) width, (float) this.mDrawHeight, this.mPaint);
            if (this.mDrawHeight > this.mStatusBarHeight) {
                this.mPaint.setColor(this.mOverlayColor);
                canvas.drawRect(0.0f, (float) this.mStatusBarHeight, (float) width, (float) this.mDrawHeight, this.mPaint);
            }
        }
    }

    public void update(float bannerBottom, boolean controlsFloating, boolean controlsSqueezed) {
        if (PlayHeaderListLayout.SUPPORT_DRAW_STATUS_BAR) {
            boolean z;
            boolean wasProtectingControls = this.mProtectingControls;
            if (controlsFloating || controlsSqueezed) {
                z = true;
            } else {
                z = false;
            }
            this.mProtectingControls = z;
            int targetSize = Math.round(bannerBottom);
            if (this.mProtectingControls && this.mStatusBarHeight > targetSize) {
                targetSize = this.mStatusBarHeight;
            }
            if (bannerBottom >= 1.0f || controlsFloating) {
                setDrawHeight(targetSize);
                fade(1, false);
                return;
            }
            int targetFadeDirection;
            if (this.mProtectingControls) {
                targetFadeDirection = 1;
            } else {
                targetFadeDirection = 2;
            }
            if (this.mFadeDirection != targetFadeDirection) {
                if (!this.mProtectingControls && wasProtectingControls) {
                    targetSize = this.mStatusBarHeight;
                }
                setDrawHeight(targetSize);
                if (targetSize == 0) {
                    fade(2, false);
                } else {
                    fade(targetFadeDirection, true);
                }
            }
        }
    }

    private void setDrawHeight(int height) {
        if (this.mDrawHeight != height) {
            this.mDrawHeight = height;
            invalidate();
        }
    }

    private void fade(int direction, boolean animate) {
        float currentAlpha = getAlpha();
        if (this.mAlphaAnimator != null && this.mAlphaAnimator.isStarted()) {
            currentAlpha = ((Float) this.mAlphaAnimator.getAnimatedValue()).floatValue();
            this.mAlphaAnimator.cancel();
        }
        float targetAlpha = direction == 1 ? 1.0f : 0.0f;
        if (!animate || currentAlpha == targetAlpha) {
            setAlpha(targetAlpha);
            return;
        }
        if (sLinearInterpolator == null) {
            sLinearInterpolator = new LinearInterpolator();
        }
        this.mFadeDirection = direction;
        this.mAlphaAnimator = ObjectAnimator.ofFloat(this, ALPHA, new float[]{currentAlpha, targetAlpha});
        this.mAlphaAnimator.setDuration((long) (300.0f * Math.abs(targetAlpha - currentAlpha)));
        this.mAlphaAnimator.setInterpolator(sLinearInterpolator);
        this.mAlphaAnimator.addListener(this);
        this.mAlphaAnimator.start();
    }

    public void onAnimationStart(Animator animation) {
    }

    public void onAnimationEnd(Animator animation) {
        if (animation == this.mAlphaAnimator) {
            boolean wasFadingOut = this.mFadeDirection == 2;
            this.mFadeDirection = 0;
            this.mAlphaAnimator = null;
            if (wasFadingOut) {
                setMinimumHeight(0);
            }
        }
    }

    public void onAnimationCancel(Animator animation) {
        if (animation == this.mAlphaAnimator) {
            this.mFadeDirection = 0;
            this.mAlphaAnimator = null;
        }
    }

    public void onAnimationRepeat(Animator animation) {
    }
}
