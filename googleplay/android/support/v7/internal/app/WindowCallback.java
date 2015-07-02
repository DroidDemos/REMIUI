package android.support.v7.internal.app;

import android.support.v7.view.ActionMode;
import android.support.v7.view.ActionMode.Callback;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public interface WindowCallback {
    boolean onCreatePanelMenu(int i, Menu menu);

    View onCreatePanelView(int i);

    boolean onMenuItemSelected(int i, MenuItem menuItem);

    boolean onMenuOpened(int i, Menu menu);

    void onPanelClosed(int i, Menu menu);

    boolean onPreparePanel(int i, View view, Menu menu);

    ActionMode startActionMode(Callback callback);
}
