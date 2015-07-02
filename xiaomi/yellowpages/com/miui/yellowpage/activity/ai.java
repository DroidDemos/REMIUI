package com.miui.yellowpage.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.miui.yellowpage.base.utils.Permission;

/* compiled from: UserNoticeActivity */
class ai implements OnClickListener {
    final /* synthetic */ UserNoticeActivity zW;

    ai(UserNoticeActivity userNoticeActivity) {
        this.zW = userNoticeActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        Permission.setNetworkingAllowedTemporarily(this.zW, false);
        Permission.setNetworkingAllowedPermanently(this.zW, false);
        this.zW.setResult(0);
        this.zW.finish();
    }
}
