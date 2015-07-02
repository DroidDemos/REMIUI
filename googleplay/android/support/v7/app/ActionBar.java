package android.support.v7.app;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v7.appcompat.R;
import android.support.v7.view.ActionMode;
import android.support.v7.view.ActionMode.Callback;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup.MarginLayoutParams;

public abstract class ActionBar {

    public static class LayoutParams extends MarginLayoutParams {
        public int gravity;

        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
            this.gravity = 0;
            TypedArray a = c.obtainStyledAttributes(attrs, R.styleable.ActionBarLayout);
            this.gravity = a.getInt(R.styleable.ActionBarLayout_android_layout_gravity, 0);
            a.recycle();
        }

        public LayoutParams(int width, int height) {
            super(width, height);
            this.gravity = 0;
            this.gravity = 8388627;
        }

        public LayoutParams(LayoutParams source) {
            super(source);
            this.gravity = 0;
            this.gravity = source.gravity;
        }

        public LayoutParams(android.view.ViewGroup.LayoutParams source) {
            super(source);
            this.gravity = 0;
        }
    }

    public interface OnMenuVisibilityListener {
        void onMenuVisibilityChanged(boolean z);
    }

    public static abstract class Tab {
        public abstract CharSequence getContentDescription();

        public abstract View getCustomView();

        public abstract Drawable getIcon();

        public abstract CharSequence getText();

        public abstract void select();
    }

    public abstract int getDisplayOptions();

    public abstract int getHeight();

    public abstract void setBackgroundDrawable(Drawable drawable);

    public abstract void setDisplayHomeAsUpEnabled(boolean z);

    public abstract void setTitle(CharSequence charSequence);

    public void setHomeButtonEnabled(boolean enabled) {
    }

    public Context getThemedContext() {
        return null;
    }

    public void setHomeAsUpIndicator(Drawable indicator) {
    }

    public void setHomeAsUpIndicator(int resId) {
    }

    public void setHomeActionContentDescription(int resId) {
    }

    public void setHideOnContentScrollEnabled(boolean hideOnContentScroll) {
        if (hideOnContentScroll) {
            throw new UnsupportedOperationException("Hide on content scroll is not supported in this action bar configuration.");
        }
    }

    public void setElevation(float elevation) {
        if (elevation != 0.0f) {
            throw new UnsupportedOperationException("Setting a non-zero elevation is not supported in this action bar configuration.");
        }
    }

    public void setDefaultDisplayHomeAsUpEnabled(boolean enabled) {
    }

    public void setShowHideAnimationEnabled(boolean enabled) {
    }

    public void onConfigurationChanged(Configuration config) {
    }

    public void dispatchMenuVisibilityChanged(boolean visible) {
    }

    public ActionMode startActionMode(Callback callback) {
        return null;
    }

    public boolean invalidateOptionsMenu() {
        return false;
    }

    public boolean onKeyShortcut(int keyCode, KeyEvent ev) {
        return false;
    }

    public boolean collapseActionView() {
        return false;
    }

    public void setWindowTitle(CharSequence title) {
    }
}
