package com.miui.yellowpage.activity;

import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

/* compiled from: RemindUserSuspectNumberActivity */
class r implements OnCheckedChangeListener {
    final /* synthetic */ RemindUserSuspectNumberActivity lJ;

    r(RemindUserSuspectNumberActivity remindUserSuspectNumberActivity) {
        this.lJ = remindUserSuspectNumberActivity;
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        this.lJ.rb = !z;
    }
}
