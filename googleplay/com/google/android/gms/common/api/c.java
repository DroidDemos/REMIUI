package com.google.android.gms.common.api;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.internal.jw;
import com.google.android.gms.internal.kc;
import com.google.android.gms.internal.kn;
import com.google.android.wallet.instrumentmanager.R;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

final class c implements GoogleApiClient {
    private final Looper JL;
    private final Set<d<?>> Kk;
    private final Condition SD;
    private final kc SE;
    private final int SF;
    final Queue<d<?>> SG;
    private ConnectionResult SH;
    private int SI;
    private volatile int SJ;
    private volatile boolean SK;
    private boolean SL;
    private int SM;
    private long SN;
    private long SO;
    final Handler SP;
    BroadcastReceiver SQ;
    private final Bundle SR;
    private final Map<com.google.android.gms.common.api.Api.c<?>, com.google.android.gms.common.api.Api.a> SS;
    private final List<String> ST;
    private boolean SU;
    final Set<d<?>> SV;
    private final ConnectionCallbacks SW;
    private final com.google.android.gms.internal.kc.b SX;
    private final b Sm;
    private final Context mContext;
    private final Lock zM;

    interface d<A extends com.google.android.gms.common.api.Api.a> {
        void a(b bVar);

        void b(A a) throws DeadObjectException;

        void cancel();

        com.google.android.gms.common.api.Api.c<A> hV();

        int ib();

        void r(Status status);
    }

    interface b {
        void b(d<?> dVar);
    }

    private static class a extends BroadcastReceiver {
        private WeakReference<c> Td;

        a(c cVar) {
            this.Td = new WeakReference(cVar);
        }

        public void onReceive(Context context, Intent intent) {
            Uri data = intent.getData();
            String str = null;
            if (data != null) {
                str = data.getSchemeSpecificPart();
            }
            if (str != null && str.equals("com.google.android.gms")) {
                c cVar = (c) this.Td.get();
                if (cVar != null && !cVar.isConnected() && !cVar.isConnecting() && cVar.ih()) {
                    cVar.connect();
                }
            }
        }
    }

    private class c extends Handler {
        final /* synthetic */ c SY;

        c(c cVar, Looper looper) {
            this.SY = cVar;
            super(looper);
        }

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    this.SY.ii();
                    return;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    this.SY.resume();
                    return;
                default:
                    Log.w("GoogleApiClientImpl", "Unknown message id: " + msg.what);
                    return;
            }
        }
    }

    public c(Context context, Looper looper, jw jwVar, Map<Api<?>, ApiOptions> map, Set<ConnectionCallbacks> set, Set<OnConnectionFailedListener> set2, int i) {
        this.zM = new ReentrantLock();
        this.SD = this.zM.newCondition();
        this.SG = new LinkedList();
        this.SJ = 4;
        this.SL = false;
        this.SN = 120000;
        this.SO = 5000;
        this.SR = new Bundle();
        this.SS = new HashMap();
        this.Kk = Collections.newSetFromMap(new WeakHashMap());
        this.SV = Collections.newSetFromMap(new ConcurrentHashMap());
        this.Sm = new b(this) {
            final /* synthetic */ c SY;

            {
                this.SY = r1;
            }

            public void b(d<?> dVar) {
                this.SY.SV.remove(dVar);
            }
        };
        this.SW = new ConnectionCallbacks(this) {
            final /* synthetic */ c SY;

            {
                this.SY = r1;
            }

            public void onConnected(Bundle connectionHint) {
                this.SY.zM.lock();
                try {
                    if (this.SY.SJ == 1) {
                        if (connectionHint != null) {
                            this.SY.SR.putAll(connectionHint);
                        }
                        this.SY.if();
                    }
                    this.SY.zM.unlock();
                } catch (Throwable th) {
                    this.SY.zM.unlock();
                }
            }

            public void onConnectionSuspended(int cause) {
                this.SY.zM.lock();
                switch (cause) {
                    case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                        if (!this.SY.ih()) {
                            this.SY.SK = true;
                            this.SY.SQ = new a(this.SY);
                            IntentFilter intentFilter = new IntentFilter("android.intent.action.PACKAGE_ADDED");
                            intentFilter.addDataScheme("package");
                            this.SY.mContext.registerReceiver(this.SY.SQ, intentFilter);
                            this.SY.SP.sendMessageDelayed(this.SY.SP.obtainMessage(1), this.SY.SN);
                            this.SY.SP.sendMessageDelayed(this.SY.SP.obtainMessage(2), this.SY.SO);
                            this.SY.cN(cause);
                            break;
                        }
                        this.SY.zM.unlock();
                        return;
                    case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                        try {
                            this.SY.cN(cause);
                            this.SY.connect();
                            break;
                        } catch (Throwable th) {
                            this.SY.zM.unlock();
                        }
                }
                this.SY.zM.unlock();
            }
        };
        this.SX = new com.google.android.gms.internal.kc.b(this) {
            final /* synthetic */ c SY;

            {
                this.SY = r1;
            }

            public Bundle ht() {
                return null;
            }

            public boolean ij() {
                return this.SY.SU;
            }

            public boolean isConnected() {
                return this.SY.isConnected();
            }
        };
        this.mContext = context;
        this.SE = new kc(context, looper, this.SX);
        this.JL = looper;
        this.SP = new c(this, looper);
        this.SF = i;
        for (ConnectionCallbacks registerConnectionCallbacks : set) {
            this.SE.registerConnectionCallbacks(registerConnectionCallbacks);
        }
        for (OnConnectionFailedListener registerConnectionFailedListener : set2) {
            this.SE.registerConnectionFailedListener(registerConnectionFailedListener);
        }
        for (Api api : map.keySet()) {
            final com.google.android.gms.common.api.Api.b hT = api.hT();
            Object obj = map.get(api);
            this.SS.put(api.hV(), a(hT, obj, context, looper, jwVar, this.SW, new OnConnectionFailedListener(this) {
                final /* synthetic */ c SY;

                public void onConnectionFailed(ConnectionResult result) {
                    this.SY.zM.lock();
                    try {
                        if (this.SY.SH == null || hT.getPriority() < this.SY.SI) {
                            this.SY.SH = result;
                            this.SY.SI = hT.getPriority();
                        }
                        this.SY.if();
                    } finally {
                        this.SY.zM.unlock();
                    }
                }
            }));
        }
        this.ST = Collections.unmodifiableList(jwVar.iH());
    }

    private static <C extends com.google.android.gms.common.api.Api.a, O> C a(com.google.android.gms.common.api.Api.b<C, O> bVar, Object obj, Context context, Looper looper, jw jwVar, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
        return bVar.a(context, looper, jwVar, obj, connectionCallbacks, onConnectionFailedListener);
    }

    private <A extends com.google.android.gms.common.api.Api.a> void a(d<A> dVar) throws DeadObjectException {
        this.zM.lock();
        try {
            kn.b(dVar.hV() != null, (Object) "This task can not be executed or enqueued (it's probably a Batch or malformed)");
            this.SV.add(dVar);
            dVar.a(this.Sm);
            if (ih()) {
                dVar.r(new Status(8));
                return;
            }
            dVar.b(a(dVar.hV()));
            this.zM.unlock();
        } finally {
            this.zM.unlock();
        }
    }

    private void cN(int i) {
        this.zM.lock();
        try {
            if (this.SJ != 3) {
                if (i == -1) {
                    Iterator it;
                    d dVar;
                    if (isConnecting()) {
                        it = this.SG.iterator();
                        while (it.hasNext()) {
                            dVar = (d) it.next();
                            if (dVar.ib() != 1) {
                                dVar.cancel();
                                it.remove();
                            }
                        }
                    } else {
                        this.SG.clear();
                    }
                    for (d dVar2 : this.SV) {
                        dVar2.cancel();
                    }
                    this.SV.clear();
                    for (d clear : this.Kk) {
                        clear.clear();
                    }
                    this.Kk.clear();
                    if (this.SH == null && !this.SG.isEmpty()) {
                        this.SL = true;
                        return;
                    }
                }
                boolean isConnecting = isConnecting();
                boolean isConnected = isConnected();
                this.SJ = 3;
                if (isConnecting) {
                    if (i == -1) {
                        this.SH = null;
                    }
                    this.SD.signalAll();
                }
                this.SU = false;
                for (com.google.android.gms.common.api.Api.a aVar : this.SS.values()) {
                    if (aVar.isConnected()) {
                        aVar.disconnect();
                    }
                }
                this.SU = true;
                this.SJ = 4;
                if (isConnected) {
                    if (i != -1) {
                        this.SE.dh(i);
                    }
                    this.SU = false;
                }
            }
            this.zM.unlock();
        } finally {
            this.zM.unlock();
        }
    }

    private void if() {
        this.SM--;
        if (this.SM != 0) {
            return;
        }
        if (this.SH != null) {
            this.SL = false;
            cN(3);
            if (!(ih() && GooglePlayServicesUtil.isPlayServicesPossiblyUpdating(this.mContext, this.SH.getErrorCode()))) {
                ii();
                this.SE.b(this.SH);
            }
            this.SU = false;
            return;
        }
        this.SJ = 2;
        ii();
        this.SD.signalAll();
        ig();
        if (this.SL) {
            this.SL = false;
            cN(-1);
            return;
        }
        this.SE.h(this.SR.isEmpty() ? null : this.SR);
    }

    private void ig() {
        this.zM.lock();
        try {
            boolean z = isConnected() || ih();
            kn.a(z, "GoogleApiClient is not connected yet.");
            while (!this.SG.isEmpty()) {
                a((d) this.SG.remove());
            }
            this.zM.unlock();
        } catch (Throwable e) {
            Log.w("GoogleApiClientImpl", "Service died while flushing queue", e);
        } catch (Throwable th) {
            this.zM.unlock();
        }
    }

    private void ii() {
        this.zM.lock();
        try {
            if (this.SK) {
                this.SK = false;
                this.SP.removeMessages(2);
                this.SP.removeMessages(1);
                this.mContext.unregisterReceiver(this.SQ);
                this.zM.unlock();
            }
        } finally {
            this.zM.unlock();
        }
    }

    private void resume() {
        this.zM.lock();
        try {
            if (ih()) {
                connect();
            }
            this.zM.unlock();
        } catch (Throwable th) {
            this.zM.unlock();
        }
    }

    public <C extends com.google.android.gms.common.api.Api.a> C a(com.google.android.gms.common.api.Api.c<C> cVar) {
        Object obj = (com.google.android.gms.common.api.Api.a) this.SS.get(cVar);
        kn.b(obj, (Object) "Appropriate Api was not requested.");
        return obj;
    }

    public <A extends com.google.android.gms.common.api.Api.a, R extends Result, T extends com.google.android.gms.common.api.BaseImplementation.a<R, A>> T a(T t) {
        this.zM.lock();
        try {
            if (isConnected()) {
                b((com.google.android.gms.common.api.BaseImplementation.a) t);
            } else {
                this.SG.add(t);
            }
            this.zM.unlock();
            return t;
        } catch (Throwable th) {
            this.zM.unlock();
        }
    }

    public <A extends com.google.android.gms.common.api.Api.a, T extends com.google.android.gms.common.api.BaseImplementation.a<? extends Result, A>> T b(T t) {
        boolean z = isConnected() || ih();
        kn.a(z, "GoogleApiClient is not connected yet.");
        ig();
        try {
            a((d) t);
        } catch (DeadObjectException e) {
            cN(1);
        }
        return t;
    }

    public void connect() {
        this.zM.lock();
        try {
            this.SL = false;
            if (isConnected() || isConnecting()) {
                this.zM.unlock();
                return;
            }
            this.SU = true;
            this.SH = null;
            this.SJ = 1;
            this.SR.clear();
            this.SM = this.SS.size();
            for (com.google.android.gms.common.api.Api.a connect : this.SS.values()) {
                connect.connect();
            }
            this.zM.unlock();
        } catch (Throwable th) {
            this.zM.unlock();
        }
    }

    public void disconnect() {
        ii();
        cN(-1);
    }

    public Looper getLooper() {
        return this.JL;
    }

    boolean ih() {
        return this.SK;
    }

    public boolean isConnected() {
        return this.SJ == 2;
    }

    public boolean isConnecting() {
        return this.SJ == 1;
    }

    public void registerConnectionCallbacks(ConnectionCallbacks listener) {
        this.SE.registerConnectionCallbacks(listener);
    }

    public void registerConnectionFailedListener(OnConnectionFailedListener listener) {
        this.SE.registerConnectionFailedListener(listener);
    }

    public void unregisterConnectionCallbacks(ConnectionCallbacks listener) {
        this.SE.unregisterConnectionCallbacks(listener);
    }

    public void unregisterConnectionFailedListener(OnConnectionFailedListener listener) {
        this.SE.unregisterConnectionFailedListener(listener);
    }
}
