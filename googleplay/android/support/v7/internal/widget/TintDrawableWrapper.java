package android.support.v7.internal.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;

class TintDrawableWrapper extends DrawableWrapper {
    private int mCurrentColor;
    private final Mode mTintMode;
    private final ColorStateList mTintStateList;

    public TintDrawableWrapper(Drawable drawable, ColorStateList tintStateList) {
        this(drawable, tintStateList, TintManager.DEFAULT_MODE);
    }

    public TintDrawableWrapper(Drawable drawable, ColorStateList tintStateList, Mode tintMode) {
        super(drawable);
        this.mTintStateList = tintStateList;
        this.mTintMode = tintMode;
    }

    public boolean isStateful() {
        return (this.mTintStateList != null && this.mTintStateList.isStateful()) || super.isStateful();
    }

    public boolean setState(int[] stateSet) {
        return updateTint(stateSet) || super.setState(stateSet);
    }

    private boolean updateTint(int[] state) {
        if (this.mTintStateList != null) {
            int color = this.mTintStateList.getColorForState(state, this.mCurrentColor);
            if (color != this.mCurrentColor) {
                if (color != 0) {
                    setColorFilter(color, this.mTintMode);
                } else {
                    clearColorFilter();
                }
                this.mCurrentColor = color;
                return true;
            }
        }
        return false;
    }
}
