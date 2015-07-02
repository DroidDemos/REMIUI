package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.internal.jg.a;
import com.google.android.gms.internal.kb.e;

public class je extends kb<jg> {
    public je(Context context, Looper looper, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, connectionCallbacks, onConnectionFailedListener, (String[]) null);
    }

    protected void a(kj kjVar, e eVar) throws RemoteException {
        kjVar.s(eVar, 6587000, getContext().getPackageName(), new Bundle());
    }

    protected jg aP(IBinder iBinder) {
        return a.aR(iBinder);
    }

    protected String bK() {
        return "com.google.android.gms.clearcut.service.START";
    }

    protected String bL() {
        return "com.google.android.gms.clearcut.internal.IClearcutLoggerService";
    }

    protected /* synthetic */ IInterface p(IBinder iBinder) {
        return aP(iBinder);
    }
}
