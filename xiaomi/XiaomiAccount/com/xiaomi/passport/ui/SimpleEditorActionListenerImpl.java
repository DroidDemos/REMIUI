package com.xiaomi.passport.ui;

import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class SimpleEditorActionListenerImpl implements OnEditorActionListener {
    private int mActionId;
    private Runnable mRunnable;

    public SimpleEditorActionListenerImpl(int actionId, Runnable r) {
        this.mActionId = actionId;
        this.mRunnable = r;
    }

    public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
        if (this.mActionId != actionId) {
            return false;
        }
        if (this.mRunnable != null) {
            this.mRunnable.run();
        }
        return true;
    }
}
