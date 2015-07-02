package android.support.v7.widget;

import android.support.v7.internal.app.WindowCallback;
import android.support.v7.view.ActionMode;
import android.support.v7.view.ActionMode.Callback;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class WindowCallbackWrapper implements WindowCallback {
    private WindowCallback mWrapped;

    public WindowCallbackWrapper(WindowCallback wrapped) {
        if (wrapped == null) {
            throw new IllegalArgumentException("Window callback may not be null");
        }
        this.mWrapped = wrapped;
    }

    public boolean onMenuItemSelected(int featureId, MenuItem menuItem) {
        return this.mWrapped.onMenuItemSelected(featureId, menuItem);
    }

    public boolean onCreatePanelMenu(int featureId, Menu menu) {
        return this.mWrapped.onCreatePanelMenu(featureId, menu);
    }

    public boolean onPreparePanel(int featureId, View menuView, Menu menu) {
        return this.mWrapped.onPreparePanel(featureId, menuView, menu);
    }

    public void onPanelClosed(int featureId, Menu menu) {
        this.mWrapped.onPanelClosed(featureId, menu);
    }

    public boolean onMenuOpened(int featureId, Menu menu) {
        return this.mWrapped.onMenuOpened(featureId, menu);
    }

    public ActionMode startActionMode(Callback callback) {
        return this.mWrapped.startActionMode(callback);
    }

    public View onCreatePanelView(int featureId) {
        return this.mWrapped.onCreatePanelView(featureId);
    }
}
