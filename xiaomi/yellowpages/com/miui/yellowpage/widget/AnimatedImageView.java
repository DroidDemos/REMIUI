package com.miui.yellowpage.widget;

import android.content.Context;
import android.graphics.drawable.AnimatedRotateDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

public class AnimatedImageView extends ImageView {
    private AnimatedRotateDrawable uc;
    private boolean ud;

    public AnimatedImageView(Context context) {
        super(context);
    }

    public AnimatedImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    private void updateDrawable() {
        if (isShown() && this.uc != null) {
            this.uc.stop();
        }
        Drawable drawable = getDrawable();
        if (drawable instanceof AnimatedRotateDrawable) {
            this.uc = (AnimatedRotateDrawable) drawable;
            this.uc.setFramesCount(56);
            this.uc.setFramesDuration(32);
            if (isShown() && this.ud) {
                this.uc.start();
                return;
            }
            return;
        }
        this.uc = null;
    }

    private void eu() {
        if (this.uc == null) {
            return;
        }
        if (isShown() && this.ud) {
            this.uc.start();
        } else {
            this.uc.stop();
        }
    }

    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        updateDrawable();
    }

    public void setImageResource(int i) {
        super.setImageResource(i);
        updateDrawable();
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        eu();
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        eu();
    }

    protected void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        eu();
    }
}
