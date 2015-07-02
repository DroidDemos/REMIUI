package com.google.android.play.search;

import android.content.Context;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.ActionMode.Callback;
import android.view.KeyEvent;
import android.view.KeyEvent.DispatcherState;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class PlaySearchEditText extends EditText {
    public PlaySearchEditText(Context context) {
        super(context);
    }

    public PlaySearchEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (VERSION.SDK_INT >= 11) {
            disableContextualActionBar();
        }
    }

    public boolean onKeyPreIme(int keyCode, KeyEvent event) {
        if (keyCode == 4 && hasFocus() && event.getAction() == 1) {
            DispatcherState state = getKeyDispatcherState();
            if (state != null) {
                state.handleUpEvent(event);
            }
            if (!event.isCanceled()) {
                clearFocus();
                return true;
            }
        }
        return super.onKeyPreIme(keyCode, event);
    }

    private void disableContextualActionBar() {
        setCustomSelectionActionModeCallback(new Callback() {
            public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
                return false;
            }

            public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                return false;
            }

            public void onDestroyActionMode(ActionMode actionMode) {
            }

            public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                return false;
            }
        });
    }
}
