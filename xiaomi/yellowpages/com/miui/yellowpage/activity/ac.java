package com.miui.yellowpage.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

/* compiled from: InstallYellowPageShortCutIconActivity */
class ac implements OnClickListener {
    final /* synthetic */ InstallYellowPageShortCutIconActivity we;

    ac(InstallYellowPageShortCutIconActivity installYellowPageShortCutIconActivity) {
        this.we = installYellowPageShortCutIconActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        new c(this).execute(new Void[0]);
    }
}
