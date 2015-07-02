package com.miui.yellowpage.ui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.view.ActionMode;

/* compiled from: InquiryHistoryFragment */
class bh implements OnDismissListener {
    final /* synthetic */ aH qg;
    final /* synthetic */ ActionMode val$mode;

    bh(aH aHVar, ActionMode actionMode) {
        this.qg = aHVar;
        this.val$mode = actionMode;
    }

    public void onDismiss(DialogInterface dialogInterface) {
        this.val$mode.finish();
    }
}
