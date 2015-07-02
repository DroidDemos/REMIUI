package com.miui.yellowpage.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.miui.yellowpage.base.utils.Network;

/* compiled from: BaseFragment */
class bP extends BroadcastReceiver {
    final /* synthetic */ cs Ao;

    private bP(cs csVar) {
        this.Ao = csVar;
    }

    public void onReceive(Context context, Intent intent) {
        boolean isNetWorkConnected = Network.isNetWorkConnected(this.Ao.mActivity);
        if (!this.Ao.mNetworkConnected && isNetWorkConnected) {
            this.Ao.onNetworkConnected();
        }
        this.Ao.mNetworkConnected = isNetWorkConnected;
    }
}
