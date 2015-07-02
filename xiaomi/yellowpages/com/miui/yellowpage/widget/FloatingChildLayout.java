package com.miui.yellowpage.widget;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewPropertyAnimator;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import com.miui.yellowpage.R;
import com.miui.yellowpage.base.utils.Log;

public class FloatingChildLayout extends FrameLayout {
    private int rC;
    private View rD;
    private Rect rE;
    private final int rF;
    private int rG;
    private ObjectAnimator rH;
    private int rI;
    private OnTouchListener rJ;

    public FloatingChildLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.rE = new Rect();
        this.rG = 0;
        this.rH = ObjectAnimator.ofInt(this, "backgroundColorAlpha", new int[]{0, 127});
        this.rI = 0;
        Resources resources = getResources();
        this.rC = resources.getDimensionPixelOffset(R.dimen.quick_contact_top_position);
        this.rF = resources.getInteger(17694720);
        super.setBackground(new ColorDrawable(0));
    }

    protected void onFinishInflate() {
        this.rD = findViewById(16908290);
        this.rD.setDuplicateParentStateEnabled(true);
        this.rD.setScaleX(0.5f);
        this.rD.setScaleY(0.5f);
        this.rD.setAlpha(0.0f);
    }

    public void setBackground(Drawable drawable) {
        Log.wtf("FloatingChildLayout", "don't setBackground(), it is managed internally");
    }

    public void a(Rect rect) {
        this.rE = rect;
        requestLayout();
    }

    private Rect dC() {
        Rect rect = new Rect();
        getWindowVisibleDisplayFrame(rect);
        Rect rect2 = new Rect(this.rE);
        rect2.offset(-rect.left, -rect.top);
        return rect2;
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        View view = this.rD;
        Rect dC = dC();
        int measuredWidth = view.getMeasuredWidth();
        int measuredHeight = view.getMeasuredHeight();
        if (this.rC != -1) {
            a(view, (getWidth() - measuredWidth) / 2, (getHeight() - measuredHeight) / 2);
            return;
        }
        a(view, c(dC.centerX() - (measuredWidth / 2), measuredWidth, getWidth()), c(dC.centerY() - Math.round(((float) measuredHeight) * 0.35f), measuredHeight, getHeight()));
    }

    private static int c(int i, int i2, int i3) {
        if (i2 > i3) {
            return (i3 - i2) / 2;
        }
        return Math.min(Math.max(i, 0), i3 - i2);
    }

    private static void a(View view, int i, int i2) {
        view.layout(i, i2, view.getMeasuredWidth() + i, view.getMeasuredHeight() + i2);
    }

    private void a(boolean z, Runnable runnable) {
        float f;
        float f2 = 1.0f;
        this.rD.setPivotX((float) (this.rE.centerX() - this.rD.getLeft()));
        this.rD.setPivotY((float) (this.rE.centerY() - this.rD.getTop()));
        ViewPropertyAnimator animate = this.rD.animate();
        animate.setDuration((long) getResources().getInteger(17694720));
        animate.setInterpolator(AnimationUtils.loadInterpolator(getContext(), z ? 17563652 : 17563653));
        if (z) {
            f = 0.5f;
        } else {
            f = 1.0f;
        }
        animate.scaleX(f);
        animate.scaleY(f);
        if (z) {
            f2 = 0.0f;
        }
        animate.alpha(f2);
        if (runnable != null) {
            animate.setListener(new b(this, runnable));
        }
    }

    public void a(OnTouchListener onTouchListener) {
        this.rJ = onTouchListener;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.rJ != null) {
            return this.rJ.onTouch(this, motionEvent);
        }
        return false;
    }

    public void b(Runnable runnable) {
        a(false, runnable);
    }

    public void c(Runnable runnable) {
        a(true, runnable);
    }
}
