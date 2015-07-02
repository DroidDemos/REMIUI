package com.miui.yellowpage.ui;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import com.miui.yellowpage.base.model.HmsMessageNotification;

/* compiled from: HmsMessageNotificationListItem */
class d implements OnClickListener {
    final /* synthetic */ HmsMessageNotification cs;
    final /* synthetic */ HmsMessageNotificationListItem ct;

    d(HmsMessageNotificationListItem hmsMessageNotificationListItem, HmsMessageNotification hmsMessageNotification) {
        this.ct = hmsMessageNotificationListItem;
        this.cs = hmsMessageNotification;
    }

    public void onClick(View view) {
        Intent intent = new Intent("com.miui.yellowpage.action.VIEW_MIXIN");
        intent.putExtra("mi_id", this.cs.getMiId());
        this.ct.getContext().startService(intent);
    }
}
