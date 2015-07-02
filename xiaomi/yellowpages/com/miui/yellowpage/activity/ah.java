package com.miui.yellowpage.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.miui.yellowpage.base.reference.RefMethods.Settings.System;
import com.miui.yellowpage.base.utils.Permission;
import com.miui.yellowpage.utils.u;
import com.miui.yellowpage.utils.x;
import miui.app.AlertDialog;

/* compiled from: UserNoticeActivity */
class ah implements OnClickListener {
    final /* synthetic */ UserNoticeActivity zW;

    ah(UserNoticeActivity userNoticeActivity) {
        this.zW = userNoticeActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        Permission.setNetworkingAllowedTemporarily(this.zW, true);
        if ((x.X(this.zW) && ((AlertDialog) dialogInterface).isChecked()) || !x.X(this.zW)) {
            Permission.setNetworkingAllowedPermanently(this.zW, true);
            System.setCloudAntispam(this.zW, true);
            u.x(this.zW);
        }
        this.zW.setResult(-1);
        this.zW.finish();
    }
}
