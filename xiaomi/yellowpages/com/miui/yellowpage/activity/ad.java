package com.miui.yellowpage.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

/* compiled from: InstallYellowPageShortCutIconActivity */
class ad implements OnClickListener {
    final /* synthetic */ InstallYellowPageShortCutIconActivity we;

    ad(InstallYellowPageShortCutIconActivity installYellowPageShortCutIconActivity) {
        this.we = installYellowPageShortCutIconActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.we.finish();
    }
}
