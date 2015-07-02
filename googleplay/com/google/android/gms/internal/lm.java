package com.google.android.gms.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.deviceconnection.DeviceConnections.GetDeviceFeaturesResult;
import com.google.android.gms.deviceconnection.features.DeviceFeatureBuffer;
import com.google.android.gms.internal.kb.e;

public final class lm extends kb<lo> {
    private final long XX;

    private final class a extends ll {
        private final com.google.android.gms.common.api.BaseImplementation.b<GetDeviceFeaturesResult> Fm;
        final /* synthetic */ lm XY;

        public a(lm lmVar, com.google.android.gms.common.api.BaseImplementation.b<GetDeviceFeaturesResult> bVar) {
            this.XY = lmVar;
            this.Fm = (com.google.android.gms.common.api.BaseImplementation.b) kn.b((Object) bVar, (Object) "Holder must not be null");
        }

        public void b(DataHolder dataHolder) {
            this.Fm.d(new b(this.XY, dataHolder));
        }
    }

    private final class b implements Result, GetDeviceFeaturesResult {
        private final Status EU;
        final /* synthetic */ lm XY;
        private final DeviceFeatureBuffer XZ;

        public b(lm lmVar, DataHolder dataHolder) {
            this.XY = lmVar;
            this.EU = new Status(dataHolder.getStatusCode());
            this.XZ = new DeviceFeatureBuffer(dataHolder);
        }

        public Status getStatus() {
            return this.EU;
        }

        public DeviceFeatureBuffer getSummaries() {
            return this.XZ;
        }

        public void release() {
            this.XZ.release();
        }
    }

    public lm(Context context, Looper looper, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener, String[] strArr) {
        super(context, looper, connectionCallbacks, onConnectionFailedListener, strArr);
        this.XX = (long) hashCode();
    }

    private void jz() {
        Log.w("DeviceConnectionClient", "service died");
    }

    protected void a(kj kjVar, e eVar) throws RemoteException {
        kjVar.l(eVar, 6587000, getContext().getPackageName());
    }

    protected String bK() {
        return "com.google.android.gms.deviceconnection.service.START";
    }

    protected String bL() {
        return "com.google.android.gms.deviceconnection.internal.IDeviceConnectionService";
    }

    protected lo bh(IBinder iBinder) {
        return com.google.android.gms.internal.lo.a.bj(iBinder);
    }

    public void g(com.google.android.gms.common.api.BaseImplementation.b<GetDeviceFeaturesResult> bVar) {
        try {
            ((lo) iP()).a(new a(this, bVar));
        } catch (RemoteException e) {
            jz();
        }
    }

    protected /* synthetic */ IInterface p(IBinder iBinder) {
        return bh(iBinder);
    }
}
