package com.miui.yellowpage.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import miui.provider.ExtraSettings.System;

/* compiled from: RemindUserSuspectNumberActivity */
class t implements OnClickListener {
    final /* synthetic */ RemindUserSuspectNumberActivity lJ;

    t(RemindUserSuspectNumberActivity remindUserSuspectNumberActivity) {
        this.lJ = remindUserSuspectNumberActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        if (!this.lJ.rb) {
            System.putBoolean(this.lJ.getContentResolver(), "yellow_page_ignore_remind_user_suspect_number", true);
        }
        this.lJ.finish();
    }
}
