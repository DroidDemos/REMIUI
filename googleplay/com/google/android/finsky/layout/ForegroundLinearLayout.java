package com.google.android.finsky.layout;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.v4.view.GravityCompat;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class ForegroundLinearLayout extends LinearLayout {
    private static boolean IS_HC_OR_ABOVE;
    private static boolean IS_JBMR1_OR_ABOVE;
    private boolean mForegroundBoundsChanged;
    private Drawable mForegroundDrawable;
    private int mForegroundPaddingBottom;
    private int mForegroundPaddingLeft;
    private int mForegroundPaddingRight;
    private int mForegroundPaddingTop;
    private final Rect mOverlayBounds;
    private final Rect mSelfBounds;

    static {
        boolean z;
        boolean z2 = true;
        if (VERSION.SDK_INT >= 11) {
            z = true;
        } else {
            z = false;
        }
        IS_HC_OR_ABOVE = z;
        if (VERSION.SDK_INT < 17) {
            z2 = false;
        }
        IS_JBMR1_OR_ABOVE = z2;
    }

    public ForegroundLinearLayout(Context context) {
        this(context, null);
    }

    public ForegroundLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mForegroundPaddingLeft = 0;
        this.mForegroundPaddingTop = 0;
        this.mForegroundPaddingRight = 0;
        this.mForegroundPaddingBottom = 0;
        this.mSelfBounds = new Rect();
        this.mOverlayBounds = new Rect();
        this.mForegroundBoundsChanged = false;
        TypedArray attributes = context.obtainStyledAttributes(attrs, new int[]{16843017});
        Drawable foregroundDrawable = attributes.getDrawable(0);
        if (foregroundDrawable != null) {
            setForeground(foregroundDrawable);
        }
        attributes.recycle();
    }

    public void setForeground(Drawable d) {
        if (this.mForegroundDrawable != d) {
            if (this.mForegroundDrawable != null) {
                this.mForegroundDrawable.setCallback(null);
                unscheduleDrawable(this.mForegroundDrawable);
            }
            this.mForegroundDrawable = d;
            this.mForegroundPaddingLeft = 0;
            this.mForegroundPaddingTop = 0;
            this.mForegroundPaddingRight = 0;
            this.mForegroundPaddingBottom = 0;
            if (d != null) {
                setWillNotDraw(false);
                d.setCallback(this);
                if (d.isStateful()) {
                    d.setState(getDrawableState());
                }
                Rect padding = new Rect();
                if (d.getPadding(padding)) {
                    this.mForegroundPaddingLeft = padding.left;
                    this.mForegroundPaddingTop = padding.top;
                    this.mForegroundPaddingRight = padding.right;
                    this.mForegroundPaddingBottom = padding.bottom;
                }
            } else {
                setWillNotDraw(true);
            }
            requestLayout();
            invalidate();
        }
    }

    protected boolean verifyDrawable(Drawable who) {
        return super.verifyDrawable(who) || who == this.mForegroundDrawable;
    }

    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        if (IS_HC_OR_ABOVE && this.mForegroundDrawable != null) {
            this.mForegroundDrawable.jumpToCurrentState();
        }
    }

    protected void drawableStateChanged() {
        super.drawableStateChanged();
        if (this.mForegroundDrawable != null && this.mForegroundDrawable.isStateful()) {
            this.mForegroundDrawable.setState(getDrawableState());
        }
    }

    public void drawableHotspotChanged(float x, float y) {
        super.drawableHotspotChanged(x, y);
        if (this.mForegroundDrawable != null) {
            this.mForegroundDrawable.setHotspot(x, y);
        }
    }

    public void setVisibility(int visibility) {
        super.setVisibility(visibility);
        if (this.mForegroundDrawable != null) {
            boolean isVisible;
            if (visibility == 0) {
                isVisible = true;
            } else {
                isVisible = false;
            }
            this.mForegroundDrawable.setVisible(isVisible, false);
        }
    }

    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        this.mForegroundBoundsChanged = true;
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.mForegroundBoundsChanged = true;
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (this.mForegroundDrawable != null) {
            Drawable foreground = this.mForegroundDrawable;
            if (this.mForegroundBoundsChanged) {
                this.mForegroundBoundsChanged = false;
                Rect selfBounds = this.mSelfBounds;
                Rect overlayBounds = this.mOverlayBounds;
                selfBounds.set(this.mForegroundPaddingLeft, this.mForegroundPaddingTop, getWidth() - this.mForegroundPaddingRight, getHeight() - this.mForegroundPaddingBottom);
                if (IS_JBMR1_OR_ABOVE) {
                    GravityCompat.apply(119, foreground.getIntrinsicWidth(), foreground.getIntrinsicHeight(), selfBounds, overlayBounds, getLayoutDirection());
                } else {
                    this.mOverlayBounds.set(selfBounds);
                }
                foreground.setBounds(overlayBounds);
            }
            foreground.draw(canvas);
        }
    }
}
