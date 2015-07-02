package android.support.v7.app;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle.Delegate;
import android.support.v7.appcompat.R;
import android.support.v7.internal.app.WindowCallback;
import android.support.v7.internal.view.SupportMenuInflater;
import android.support.v7.internal.widget.TintTypedArray;
import android.support.v7.view.ActionMode;
import android.support.v7.view.ActionMode.Callback;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

abstract class ActionBarActivityDelegate {
    private ActionBar mActionBar;
    final ActionBarActivity mActivity;
    final WindowCallback mDefaultWindowCallback;
    boolean mHasActionBar;
    private boolean mIsDestroyed;
    boolean mIsFloating;
    private MenuInflater mMenuInflater;
    boolean mOverlayActionBar;
    boolean mOverlayActionMode;
    private WindowCallback mWindowCallback;

    private class ActionBarDrawableToggleImpl implements Delegate {
        private ActionBarDrawableToggleImpl() {
        }

        public Drawable getThemeUpIndicator() {
            TintTypedArray a = TintTypedArray.obtainStyledAttributes(getActionBarThemedContext(), null, new int[]{ActionBarActivityDelegate.this.getHomeAsUpIndicatorAttrId()});
            Drawable result = a.getDrawable(0);
            a.recycle();
            return result;
        }

        public Context getActionBarThemedContext() {
            return ActionBarActivityDelegate.this.getActionBarThemedContext();
        }

        public boolean isNavigationVisible() {
            ActionBar ab = ActionBarActivityDelegate.this.getSupportActionBar();
            return (ab == null || (ab.getDisplayOptions() & 4) == 0) ? false : true;
        }

        public void setActionBarUpIndicator(Drawable upDrawable, int contentDescRes) {
            ActionBar ab = ActionBarActivityDelegate.this.getSupportActionBar();
            if (ab != null) {
                ab.setHomeAsUpIndicator(upDrawable);
                ab.setHomeActionContentDescription(contentDescRes);
            }
        }

        public void setActionBarDescription(int contentDescRes) {
            ActionBar ab = ActionBarActivityDelegate.this.getSupportActionBar();
            if (ab != null) {
                ab.setHomeActionContentDescription(contentDescRes);
            }
        }
    }

    abstract void addContentView(View view, LayoutParams layoutParams);

    abstract ActionBar createSupportActionBar();

    abstract View createView(String str, Context context, AttributeSet attributeSet);

    abstract boolean dispatchKeyEvent(KeyEvent keyEvent);

    abstract int getHomeAsUpIndicatorAttrId();

    abstract boolean onBackPressed();

    abstract void onConfigurationChanged(Configuration configuration);

    abstract void onContentChanged();

    abstract boolean onCreatePanelMenu(int i, Menu menu);

    abstract boolean onKeyShortcut(int i, KeyEvent keyEvent);

    abstract boolean onMenuOpened(int i, Menu menu);

    abstract void onPanelClosed(int i, Menu menu);

    abstract void onPostCreate(Bundle bundle);

    abstract void onPostResume();

    abstract boolean onPreparePanel(int i, View view, Menu menu);

    abstract void onStop();

    abstract void onTitleChanged(CharSequence charSequence);

    abstract void setContentView(int i);

    abstract void setContentView(View view);

    abstract void setContentView(View view, LayoutParams layoutParams);

    abstract void setSupportActionBar(Toolbar toolbar);

    abstract ActionMode startSupportActionModeFromWindow(Callback callback);

    abstract void supportInvalidateOptionsMenu();

    static ActionBarActivityDelegate createDelegate(ActionBarActivity activity) {
        if (VERSION.SDK_INT >= 11) {
            return new ActionBarActivityDelegateHC(activity);
        }
        return new ActionBarActivityDelegateBase(activity);
    }

    ActionBarActivityDelegate(ActionBarActivity activity) {
        this.mDefaultWindowCallback = new WindowCallback() {
            public boolean onMenuItemSelected(int featureId, MenuItem menuItem) {
                return ActionBarActivityDelegate.this.mActivity.onMenuItemSelected(featureId, menuItem);
            }

            public boolean onCreatePanelMenu(int featureId, Menu menu) {
                return ActionBarActivityDelegate.this.mActivity.superOnCreatePanelMenu(featureId, menu);
            }

            public boolean onPreparePanel(int featureId, View menuView, Menu menu) {
                return ActionBarActivityDelegate.this.mActivity.superOnPreparePanel(featureId, menuView, menu);
            }

            public void onPanelClosed(int featureId, Menu menu) {
                ActionBarActivityDelegate.this.mActivity.onPanelClosed(featureId, menu);
            }

            public boolean onMenuOpened(int featureId, Menu menu) {
                return ActionBarActivityDelegate.this.mActivity.onMenuOpened(featureId, menu);
            }

            public ActionMode startActionMode(Callback callback) {
                return ActionBarActivityDelegate.this.startSupportActionModeFromWindow(callback);
            }

            public View onCreatePanelView(int featureId) {
                return ActionBarActivityDelegate.this.mActivity.onCreatePanelView(featureId);
            }
        };
        this.mActivity = activity;
        this.mWindowCallback = this.mDefaultWindowCallback;
    }

    final ActionBar getSupportActionBar() {
        if (this.mHasActionBar && this.mActionBar == null) {
            this.mActionBar = createSupportActionBar();
        }
        return this.mActionBar;
    }

    final ActionBar peekSupportActionBar() {
        return this.mActionBar;
    }

    protected final void setSupportActionBar(ActionBar actionBar) {
        this.mActionBar = actionBar;
    }

    MenuInflater getMenuInflater() {
        if (this.mMenuInflater == null) {
            this.mMenuInflater = new SupportMenuInflater(getActionBarThemedContext());
        }
        return this.mMenuInflater;
    }

    void onCreate(Bundle savedInstanceState) {
        TypedArray a = this.mActivity.obtainStyledAttributes(R.styleable.Theme);
        if (a.hasValue(R.styleable.Theme_windowActionBar)) {
            if (a.getBoolean(R.styleable.Theme_windowActionBar, false)) {
                this.mHasActionBar = true;
            }
            if (a.getBoolean(R.styleable.Theme_windowActionBarOverlay, false)) {
                this.mOverlayActionBar = true;
            }
            if (a.getBoolean(R.styleable.Theme_windowActionModeOverlay, false)) {
                this.mOverlayActionMode = true;
            }
            this.mIsFloating = a.getBoolean(R.styleable.Theme_android_windowIsFloating, false);
            a.recycle();
            return;
        }
        a.recycle();
        throw new IllegalStateException("You need to use a Theme.AppCompat theme (or descendant) with this activity.");
    }

    boolean onPrepareOptionsPanel(View view, Menu menu) {
        if (VERSION.SDK_INT < 16) {
            return this.mActivity.onPrepareOptionsMenu(menu);
        }
        return this.mActivity.superOnPrepareOptionsPanel(view, menu);
    }

    final Delegate getV7DrawerToggleDelegate() {
        return new ActionBarDrawableToggleImpl();
    }

    protected final Context getActionBarThemedContext() {
        Context context = null;
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            context = ab.getThemedContext();
        }
        if (context == null) {
            return this.mActivity;
        }
        return context;
    }

    final void setWindowCallback(WindowCallback callback) {
        if (callback == null) {
            throw new IllegalArgumentException("callback can not be null");
        }
        this.mWindowCallback = callback;
    }

    final WindowCallback getWindowCallback() {
        return this.mWindowCallback;
    }

    final void destroy() {
        this.mIsDestroyed = true;
    }

    final boolean isDestroyed() {
        return this.mIsDestroyed;
    }
}
