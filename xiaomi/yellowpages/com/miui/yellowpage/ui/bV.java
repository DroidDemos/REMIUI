package com.miui.yellowpage.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.miui.yellowpage.R;
import com.miui.yellowpage.base.utils.BusinessStatistics;
import com.miui.yellowpage.openapi.PermissionManager.OnCheckFinishedListener;

/* compiled from: FlowOfPackageResultFragment */
class bV extends BroadcastReceiver {
    final /* synthetic */ K BE;

    private bV(K k) {
        this.BE = k;
    }

    public void onReceive(Context context, Intent intent) {
        if ("com.miui.yellowppage.send_sms".equals(intent.getAction())) {
            switch (getResultCode()) {
                case OnCheckFinishedListener.NOT_FOUND /*-1*/:
                    this.BE.ij.setText(R.string.packages_pay_success);
                    this.BE.ik.setText(R.string.packages_wait_for_op_reply);
                    this.BE.ik.setVisibility(0);
                    this.BE.ii.setImageResource(R.drawable.order_success);
                    BusinessStatistics.transaction(context, "flowOfPackages", this.BE.in + "/" + this.BE.io, this.BE.iq);
                    break;
                default:
                    this.BE.ii.setImageResource(R.drawable.order_error);
                    this.BE.ij.setText(168231158);
                    break;
            }
            context.unregisterReceiver(this);
        }
    }
}
