package android.support.v7.internal.app;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources.Theme;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.OnMenuVisibilityListener;
import android.support.v7.internal.view.menu.ListMenuPresenter;
import android.support.v7.internal.view.menu.MenuBuilder;
import android.support.v7.internal.view.menu.MenuPresenter.Callback;
import android.support.v7.internal.widget.DecorToolbar;
import android.support.v7.internal.widget.ToolbarWidgetWrapper;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.Toolbar.OnMenuItemClickListener;
import android.support.v7.widget.WindowCallbackWrapper;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import com.google.android.wallet.instrumentmanager.R;
import java.util.ArrayList;

public class ToolbarActionBar extends ActionBar {
    private DecorToolbar mDecorToolbar;
    private boolean mLastMenuVisibility;
    private ListMenuPresenter mListMenuPresenter;
    private boolean mMenuCallbackSet;
    private final OnMenuItemClickListener mMenuClicker;
    private final Runnable mMenuInvalidator;
    private ArrayList<OnMenuVisibilityListener> mMenuVisibilityListeners;
    private boolean mToolbarMenuPrepared;
    private Window mWindow;
    private WindowCallback mWindowCallback;

    private final class ActionMenuPresenterCallback implements Callback {
        private boolean mClosingActionMenu;

        private ActionMenuPresenterCallback() {
        }

        public boolean onOpenSubMenu(MenuBuilder subMenu) {
            if (ToolbarActionBar.this.mWindowCallback == null) {
                return false;
            }
            ToolbarActionBar.this.mWindowCallback.onMenuOpened(8, subMenu);
            return true;
        }

        public void onCloseMenu(MenuBuilder menu, boolean allMenusAreClosing) {
            if (!this.mClosingActionMenu) {
                this.mClosingActionMenu = true;
                ToolbarActionBar.this.mDecorToolbar.dismissPopupMenus();
                if (ToolbarActionBar.this.mWindowCallback != null) {
                    ToolbarActionBar.this.mWindowCallback.onPanelClosed(8, menu);
                }
                this.mClosingActionMenu = false;
            }
        }
    }

    private final class MenuBuilderCallback implements MenuBuilder.Callback {
        private MenuBuilderCallback() {
        }

        public boolean onMenuItemSelected(MenuBuilder menu, MenuItem item) {
            return false;
        }

        public void onMenuModeChange(MenuBuilder menu) {
            if (ToolbarActionBar.this.mWindowCallback == null) {
                return;
            }
            if (ToolbarActionBar.this.mDecorToolbar.isOverflowMenuShowing()) {
                ToolbarActionBar.this.mWindowCallback.onPanelClosed(8, menu);
            } else if (ToolbarActionBar.this.mWindowCallback.onPreparePanel(0, null, menu)) {
                ToolbarActionBar.this.mWindowCallback.onMenuOpened(8, menu);
            }
        }
    }

    private final class PanelMenuPresenterCallback implements Callback {
        private PanelMenuPresenterCallback() {
        }

        public void onCloseMenu(MenuBuilder menu, boolean allMenusAreClosing) {
            if (ToolbarActionBar.this.mWindowCallback != null) {
                ToolbarActionBar.this.mWindowCallback.onPanelClosed(0, menu);
            }
            ToolbarActionBar.this.mWindow.closePanel(0);
        }

        public boolean onOpenSubMenu(MenuBuilder subMenu) {
            if (subMenu == null && ToolbarActionBar.this.mWindowCallback != null) {
                ToolbarActionBar.this.mWindowCallback.onMenuOpened(0, subMenu);
            }
            return true;
        }
    }

    private class ToolbarCallbackWrapper extends WindowCallbackWrapper {
        public ToolbarCallbackWrapper(WindowCallback wrapped) {
            super(wrapped);
        }

        public boolean onPreparePanel(int featureId, View view, Menu menu) {
            boolean result = super.onPreparePanel(featureId, view, menu);
            if (result && !ToolbarActionBar.this.mToolbarMenuPrepared) {
                ToolbarActionBar.this.mDecorToolbar.setMenuPrepared();
                ToolbarActionBar.this.mToolbarMenuPrepared = true;
            }
            return result;
        }

        public View onCreatePanelView(int featureId) {
            switch (featureId) {
                case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                    Menu menu = ToolbarActionBar.this.mDecorToolbar.getMenu();
                    if (ToolbarActionBar.this.mWindowCallback != null && ToolbarActionBar.this.mWindowCallback.onPreparePanel(featureId, null, menu) && ToolbarActionBar.this.mWindowCallback.onMenuOpened(featureId, menu)) {
                        return ToolbarActionBar.this.getListMenuView(menu);
                    }
            }
            return super.onCreatePanelView(featureId);
        }
    }

    public ToolbarActionBar(Toolbar toolbar, CharSequence title, Window window, WindowCallback windowCallback) {
        this.mMenuVisibilityListeners = new ArrayList();
        this.mMenuInvalidator = new Runnable() {
            public void run() {
                ToolbarActionBar.this.populateOptionsMenu();
            }
        };
        this.mMenuClicker = new OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                return ToolbarActionBar.this.mWindowCallback.onMenuItemSelected(0, item);
            }
        };
        this.mDecorToolbar = new ToolbarWidgetWrapper(toolbar, false);
        this.mWindowCallback = new ToolbarCallbackWrapper(windowCallback);
        this.mDecorToolbar.setWindowCallback(this.mWindowCallback);
        toolbar.setOnMenuItemClickListener(this.mMenuClicker);
        this.mDecorToolbar.setWindowTitle(title);
        this.mWindow = window;
    }

    public WindowCallback getWrappedWindowCallback() {
        return this.mWindowCallback;
    }

    public void setHomeButtonEnabled(boolean enabled) {
    }

    public void setElevation(float elevation) {
        ViewCompat.setElevation(this.mDecorToolbar.getViewGroup(), elevation);
    }

    public Context getThemedContext() {
        return this.mDecorToolbar.getContext();
    }

    public void setHomeAsUpIndicator(Drawable indicator) {
        this.mDecorToolbar.setNavigationIcon(indicator);
    }

    public void setHomeAsUpIndicator(int resId) {
        this.mDecorToolbar.setNavigationIcon(resId);
    }

    public void setDefaultDisplayHomeAsUpEnabled(boolean enabled) {
    }

    public void setHomeActionContentDescription(int resId) {
        this.mDecorToolbar.setNavigationContentDescription(resId);
    }

    public void setShowHideAnimationEnabled(boolean enabled) {
    }

    public void onConfigurationChanged(Configuration config) {
        super.onConfigurationChanged(config);
    }

    public ActionMode startActionMode(ActionMode.Callback callback) {
        return this.mWindowCallback.startActionMode(callback);
    }

    public void setTitle(CharSequence title) {
        this.mDecorToolbar.setTitle(title);
    }

    public void setWindowTitle(CharSequence title) {
        this.mDecorToolbar.setWindowTitle(title);
    }

    public void setDisplayOptions(int options, int mask) {
        this.mDecorToolbar.setDisplayOptions((options & mask) | ((mask ^ -1) & this.mDecorToolbar.getDisplayOptions()));
    }

    public void setDisplayHomeAsUpEnabled(boolean showHomeAsUp) {
        setDisplayOptions(showHomeAsUp ? 4 : 0, 4);
    }

    public void setBackgroundDrawable(Drawable d) {
        this.mDecorToolbar.setBackgroundDrawable(d);
    }

    public int getDisplayOptions() {
        return this.mDecorToolbar.getDisplayOptions();
    }

    public int getHeight() {
        return this.mDecorToolbar.getHeight();
    }

    public boolean invalidateOptionsMenu() {
        this.mDecorToolbar.getViewGroup().removeCallbacks(this.mMenuInvalidator);
        ViewCompat.postOnAnimation(this.mDecorToolbar.getViewGroup(), this.mMenuInvalidator);
        return true;
    }

    public boolean collapseActionView() {
        if (!this.mDecorToolbar.hasExpandedActionView()) {
            return false;
        }
        this.mDecorToolbar.collapseActionView();
        return true;
    }

    void populateOptionsMenu() {
        MenuBuilder mb = null;
        Menu menu = getMenu();
        if (menu instanceof MenuBuilder) {
            mb = (MenuBuilder) menu;
        }
        if (mb != null) {
            mb.stopDispatchingItemsChanged();
        }
        try {
            menu.clear();
            if (!(this.mWindowCallback.onCreatePanelMenu(0, menu) && this.mWindowCallback.onPreparePanel(0, null, menu))) {
                menu.clear();
            }
            if (mb != null) {
                mb.startDispatchingItemsChanged();
            }
        } catch (Throwable th) {
            if (mb != null) {
                mb.startDispatchingItemsChanged();
            }
        }
    }

    public boolean onKeyShortcut(int keyCode, KeyEvent ev) {
        Menu menu = getMenu();
        if (menu != null) {
            return menu.performShortcut(keyCode, ev, 0);
        }
        return false;
    }

    public void dispatchMenuVisibilityChanged(boolean isVisible) {
        if (isVisible != this.mLastMenuVisibility) {
            this.mLastMenuVisibility = isVisible;
            int count = this.mMenuVisibilityListeners.size();
            for (int i = 0; i < count; i++) {
                ((OnMenuVisibilityListener) this.mMenuVisibilityListeners.get(i)).onMenuVisibilityChanged(isVisible);
            }
        }
    }

    private View getListMenuView(Menu menu) {
        ensureListMenuPresenter(menu);
        if (menu == null || this.mListMenuPresenter == null || this.mListMenuPresenter.getAdapter().getCount() <= 0) {
            return null;
        }
        return (View) this.mListMenuPresenter.getMenuView(this.mDecorToolbar.getViewGroup());
    }

    private void ensureListMenuPresenter(Menu menu) {
        if (this.mListMenuPresenter == null && (menu instanceof MenuBuilder)) {
            MenuBuilder mb = (MenuBuilder) menu;
            Context context = this.mDecorToolbar.getContext();
            TypedValue outValue = new TypedValue();
            Theme widgetTheme = context.getResources().newTheme();
            widgetTheme.setTo(context.getTheme());
            widgetTheme.resolveAttribute(android.support.v7.appcompat.R.attr.panelMenuListTheme, outValue, true);
            if (outValue.resourceId != 0) {
                widgetTheme.applyStyle(outValue.resourceId, true);
            } else {
                widgetTheme.applyStyle(android.support.v7.appcompat.R.style.Theme_AppCompat_CompactMenu, true);
            }
            Context context2 = new ContextThemeWrapper(context, 0);
            context2.getTheme().setTo(widgetTheme);
            this.mListMenuPresenter = new ListMenuPresenter(context2, android.support.v7.appcompat.R.layout.abc_list_menu_item_layout);
            this.mListMenuPresenter.setCallback(new PanelMenuPresenterCallback());
            mb.addMenuPresenter(this.mListMenuPresenter);
        }
    }

    private Menu getMenu() {
        if (!this.mMenuCallbackSet) {
            this.mDecorToolbar.setMenuCallbacks(new ActionMenuPresenterCallback(), new MenuBuilderCallback());
            this.mMenuCallbackSet = true;
        }
        return this.mDecorToolbar.getMenu();
    }
}
