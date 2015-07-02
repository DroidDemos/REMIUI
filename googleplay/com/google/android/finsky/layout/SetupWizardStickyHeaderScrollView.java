package com.google.android.finsky.layout;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowInsets;
import android.widget.ScrollView;

public class SetupWizardStickyHeaderScrollView extends ScrollView {
    private int mStatusBarInset;
    private View mSticky;
    private View mStickyContainer;
    private RectF mStickyRect;

    public SetupWizardStickyHeaderScrollView(Context context) {
        super(context);
        this.mStatusBarInset = 0;
        this.mStickyRect = new RectF();
    }

    public SetupWizardStickyHeaderScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mStatusBarInset = 0;
        this.mStickyRect = new RectF();
    }

    public SetupWizardStickyHeaderScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mStatusBarInset = 0;
        this.mStickyRect = new RectF();
    }

    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (this.mSticky == null) {
            updateStickyView();
        }
    }

    public void updateStickyView() {
        this.mSticky = findViewWithTag("sticky");
        this.mStickyContainer = findViewWithTag("stickyContainer");
    }

    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (!this.mStickyRect.contains(ev.getX(), ev.getY())) {
            return super.dispatchTouchEvent(ev);
        }
        ev.offsetLocation(-this.mStickyRect.left, -this.mStickyRect.top);
        return this.mStickyContainer.dispatchTouchEvent(ev);
    }

    public void draw(Canvas canvas) {
        setVerticalScrollBarEnabled(false);
        super.draw(canvas);
        if (this.mSticky != null) {
            int drawOffset;
            int saveCount = canvas.save();
            View drawTarget = this.mStickyContainer != null ? this.mStickyContainer : this.mSticky;
            if (this.mStickyContainer != null) {
                drawOffset = this.mSticky.getTop();
            } else {
                drawOffset = 0;
            }
            int drawTop = drawTarget.getTop() - getScrollY();
            if (drawTop + drawOffset < this.mStatusBarInset || !drawTarget.isShown()) {
                this.mStickyRect.set(0.0f, (float) ((-drawOffset) + this.mStatusBarInset), (float) drawTarget.getWidth(), (float) ((drawTarget.getHeight() - drawOffset) + this.mStatusBarInset));
                canvas.translate(0.0f, ((float) (-drawTop)) + this.mStickyRect.top);
                canvas.clipRect(0, 0, drawTarget.getWidth(), drawTarget.getHeight());
                drawTarget.draw(canvas);
            } else {
                this.mStickyRect.setEmpty();
            }
            canvas.restoreToCount(saveCount);
        }
        setVerticalScrollBarEnabled(true);
        onDrawScrollBars(canvas);
    }

    public WindowInsets onApplyWindowInsets(WindowInsets insets) {
        if (getFitsSystemWindows()) {
            this.mStatusBarInset = insets.getSystemWindowInsetTop();
        }
        return insets;
    }
}
