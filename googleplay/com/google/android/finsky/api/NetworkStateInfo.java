package com.google.android.finsky.api;

import android.content.Context;
import android.net.NetworkInfo;
import com.google.android.finsky.receivers.NetworkStateChangedReceiver;

public class NetworkStateInfo implements NetworkStateProvider {
    private Context mContext;

    public NetworkStateInfo(Context context) {
        this.mContext = context;
    }

    public NetworkInfo getCurrentNetworkInfo() {
        return NetworkStateChangedReceiver.getCachedNetworkInfo(this.mContext);
    }
}
