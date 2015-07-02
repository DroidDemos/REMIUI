package com.miui.yellowpage.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;

/* compiled from: InstallYellowPageShortCutIconActivity */
class ae implements OnCancelListener {
    final /* synthetic */ InstallYellowPageShortCutIconActivity we;

    ae(InstallYellowPageShortCutIconActivity installYellowPageShortCutIconActivity) {
        this.we = installYellowPageShortCutIconActivity;
    }

    public void onCancel(DialogInterface dialogInterface) {
        this.we.finish();
    }
}
