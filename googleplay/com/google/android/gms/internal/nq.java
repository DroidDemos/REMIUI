package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.internal.kb.e;
import com.google.android.gms.internal.qf.a;

public class nq extends kb<qf> {
    public nq(Context context, Looper looper, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, connectionCallbacks, onConnectionFailedListener, new String[0]);
    }

    protected void a(kj kjVar, e eVar) throws RemoteException {
        kjVar.k(eVar, 6587000, getContext().getPackageName(), Bundle.EMPTY);
    }

    public void b(Bundle bundle, Bundle bundle2) {
        dR();
        try {
            ((qf) iP()).a(getContext().getPackageName(), bundle, bundle2);
        } catch (RemoteException e) {
            Log.w("Herrevad", "NetworkQualityClient not connected soon after checkConnected.  Discarding network quality data");
        }
    }

    protected String bK() {
        return "com.google.android.gms.mdm.services.START";
    }

    protected String bL() {
        return "com.google.android.gms.mdm.internal.INetworkQualityService";
    }

    public qf ce(IBinder iBinder) {
        return a.dl(iBinder);
    }

    public /* synthetic */ IInterface p(IBinder iBinder) {
        return ce(iBinder);
    }
}
