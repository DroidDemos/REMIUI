package com.miui.yellowpage.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;

/* compiled from: RemindUserSuspectNumberActivity */
class s implements OnCancelListener {
    final /* synthetic */ RemindUserSuspectNumberActivity lJ;

    s(RemindUserSuspectNumberActivity remindUserSuspectNumberActivity) {
        this.lJ = remindUserSuspectNumberActivity;
    }

    public void onCancel(DialogInterface dialogInterface) {
        this.lJ.finish();
    }
}
