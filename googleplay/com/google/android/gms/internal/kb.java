package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.wallet.instrumentmanager.R;
import java.util.ArrayList;

public abstract class kb<T extends IInterface> implements com.google.android.gms.common.api.Api.a, com.google.android.gms.internal.kc.b {
    public static final String[] GOOGLE_PLUS_REQUIRED_FEATURES;
    private final Looper JL;
    private final kc SE;
    private T VN;
    private final ArrayList<b<?>> VO;
    private com.google.android.gms.internal.kb$com.google.android.gms.internal.kb.f VP;
    private int VQ;
    private final String[] VR;
    boolean VS;
    private final Context mContext;
    final Handler mHandler;
    private final Object mK;

    final class a extends Handler {
        final /* synthetic */ kb VT;

        public a(kb kbVar, Looper looper) {
            this.VT = kbVar;
            super(looper);
        }

        public void handleMessage(Message msg) {
            b bVar;
            if (msg.what == 1 && !this.VT.isConnecting()) {
                bVar = (b) msg.obj;
                bVar.iQ();
                bVar.unregister();
            } else if (msg.what == 3) {
                this.VT.SE.b(new ConnectionResult(((Integer) msg.obj).intValue(), null));
            } else if (msg.what == 4) {
                this.VT.a(4, null);
                this.VT.SE.dh(((Integer) msg.obj).intValue());
                this.VT.a(4, 1, null);
            } else if (msg.what == 2 && !this.VT.isConnected()) {
                bVar = (b) msg.obj;
                bVar.iQ();
                bVar.unregister();
            } else if (msg.what == 2 || msg.what == 1) {
                ((b) msg.obj).iR();
            } else {
                Log.wtf("GmsClient", "Don't know how to handle this message.");
            }
        }
    }

    protected abstract class b<TListener> {
        final /* synthetic */ kb VT;
        private boolean VU;
        private TListener mListener;

        public b(kb kbVar, TListener tListener) {
            this.VT = kbVar;
            this.mListener = tListener;
            this.VU = false;
        }

        protected abstract void i(TListener tListener);

        protected abstract void iQ();

        public void iR() {
            synchronized (this) {
                Object obj = this.mListener;
                if (this.VU) {
                    Log.w("GmsClient", "Callback proxy " + this + " being reused. This is not safe.");
                }
            }
            if (obj != null) {
                try {
                    i(obj);
                } catch (RuntimeException e) {
                    iQ();
                    throw e;
                }
            }
            iQ();
            synchronized (this) {
                this.VU = true;
            }
            unregister();
        }

        public void iS() {
            synchronized (this) {
                this.mListener = null;
            }
        }

        public void unregister() {
            iS();
            synchronized (this.VT.VO) {
                this.VT.VO.remove(this);
            }
        }
    }

    public static final class c implements ConnectionCallbacks {
        private final GooglePlayServicesClient.ConnectionCallbacks VV;

        public c(GooglePlayServicesClient.ConnectionCallbacks connectionCallbacks) {
            this.VV = connectionCallbacks;
        }

        public boolean equals(Object other) {
            return other instanceof c ? this.VV.equals(((c) other).VV) : this.VV.equals(other);
        }

        public void onConnected(Bundle connectionHint) {
            this.VV.onConnected(connectionHint);
        }

        public void onConnectionSuspended(int cause) {
            this.VV.onDisconnected();
        }
    }

    public static final class e extends com.google.android.gms.internal.ki.a {
        private kb VW;

        public e(kb kbVar) {
            this.VW = kbVar;
        }

        public void b(int i, IBinder iBinder, Bundle bundle) {
            kn.b((Object) "onPostInitComplete can be called only once per call to getServiceFromBroker", this.VW);
            this.VW.a(i, iBinder, bundle);
            this.VW = null;
        }
    }

    public final class f implements ServiceConnection {
        final /* synthetic */ kb VT;

        public f(kb kbVar) {
            this.VT = kbVar;
        }

        public void onServiceConnected(ComponentName component, IBinder binder) {
            this.VT.aX(binder);
        }

        public void onServiceDisconnected(ComponentName component) {
            this.VT.mHandler.sendMessage(this.VT.mHandler.obtainMessage(4, Integer.valueOf(1)));
        }
    }

    public static final class g implements OnConnectionFailedListener {
        private final GooglePlayServicesClient.OnConnectionFailedListener VX;

        public g(GooglePlayServicesClient.OnConnectionFailedListener onConnectionFailedListener) {
            this.VX = onConnectionFailedListener;
        }

        public boolean equals(Object other) {
            return other instanceof g ? this.VX.equals(((g) other).VX) : this.VX.equals(other);
        }

        public void onConnectionFailed(ConnectionResult result) {
            this.VX.onConnectionFailed(result);
        }
    }

    protected final class h extends b<Boolean> {
        final /* synthetic */ kb VT;
        public final Bundle VY;
        public final IBinder VZ;
        public final int statusCode;

        public h(kb kbVar, int i, IBinder iBinder, Bundle bundle) {
            this.VT = kbVar;
            super(kbVar, Boolean.valueOf(true));
            this.statusCode = i;
            this.VZ = iBinder;
            this.VY = bundle;
        }

        protected void b(Boolean bool) {
            if (bool == null) {
                this.VT.a(1, null);
                return;
            }
            switch (this.statusCode) {
                case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                    try {
                        if (this.VT.bL().equals(this.VZ.getInterfaceDescriptor())) {
                            IInterface p = this.VT.p(this.VZ);
                            if (p != null) {
                                this.VT.a(3, p);
                                this.VT.SE.dT();
                                return;
                            }
                        }
                    } catch (RemoteException e) {
                    }
                    kd.L(this.VT.mContext).b(this.VT.bK(), this.VT.VP);
                    this.VT.VP = null;
                    this.VT.a(1, null);
                    this.VT.SE.b(new ConnectionResult(8, null));
                    return;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                    this.VT.a(1, null);
                    throw new IllegalStateException("A fatal developer error has occurred. Check the logs for further information.");
                default:
                    PendingIntent pendingIntent = this.VY != null ? (PendingIntent) this.VY.getParcelable("pendingIntent") : null;
                    if (this.VT.VP != null) {
                        kd.L(this.VT.mContext).b(this.VT.bK(), this.VT.VP);
                        this.VT.VP = null;
                    }
                    this.VT.a(1, null);
                    this.VT.SE.b(new ConnectionResult(this.statusCode, pendingIntent));
                    return;
            }
        }

        protected /* synthetic */ void i(Object obj) {
            b((Boolean) obj);
        }

        protected void iQ() {
        }
    }

    static {
        GOOGLE_PLUS_REQUIRED_FEATURES = new String[]{"service_esmobile", "service_googleme"};
    }

    protected kb(Context context, Looper looper, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener, String... strArr) {
        this.mK = new Object();
        this.VO = new ArrayList();
        this.VQ = 1;
        this.VS = false;
        this.mContext = (Context) kn.k(context);
        this.JL = (Looper) kn.b((Object) looper, (Object) "Looper must not be null");
        this.SE = new kc(context, looper, this);
        this.mHandler = new a(this, looper);
        c(strArr);
        this.VR = strArr;
        registerConnectionCallbacks((ConnectionCallbacks) kn.k(connectionCallbacks));
        registerConnectionFailedListener((OnConnectionFailedListener) kn.k(onConnectionFailedListener));
    }

    private void a(int i, T t) {
        boolean z = true;
        if ((i == 3) != (t != null)) {
            z = false;
        }
        kn.L(z);
        synchronized (this.mK) {
            this.VQ = i;
            this.VN = t;
        }
    }

    private boolean a(int i, int i2, T t) {
        boolean z;
        synchronized (this.mK) {
            if (this.VQ != i) {
                z = false;
            } else {
                a(i2, (IInterface) t);
                z = true;
            }
        }
        return z;
    }

    protected void a(int i, IBinder iBinder, Bundle bundle) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(1, new h(this, i, iBinder, bundle)));
    }

    @Deprecated
    public final void a(b<?> bVar) {
        synchronized (this.VO) {
            this.VO.add(bVar);
        }
        this.mHandler.sendMessage(this.mHandler.obtainMessage(2, bVar));
    }

    protected abstract void a(kj kjVar, e eVar) throws RemoteException;

    protected final void aX(IBinder iBinder) {
        try {
            a(com.google.android.gms.internal.kj.a.ba(iBinder), new e(this));
        } catch (DeadObjectException e) {
            Log.w("GmsClient", "service died");
            dg(1);
        } catch (Throwable e2) {
            Log.w("GmsClient", "Remote exception occurred", e2);
        }
    }

    protected abstract String bK();

    protected abstract String bL();

    protected void c(String... strArr) {
    }

    public void connect() {
        this.VS = true;
        a(2, null);
        int isGooglePlayServicesAvailable = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this.mContext);
        if (isGooglePlayServicesAvailable != 0) {
            a(1, null);
            this.mHandler.sendMessage(this.mHandler.obtainMessage(3, Integer.valueOf(isGooglePlayServicesAvailable)));
            return;
        }
        if (this.VP != null) {
            Log.e("GmsClient", "Calling connect() while still connected, missing disconnect() for " + bK());
            kd.L(this.mContext).b(bK(), this.VP);
        }
        this.VP = new f(this);
        if (!kd.L(this.mContext).a(bK(), this.VP)) {
            Log.e("GmsClient", "unable to connect to service: " + bK());
            this.mHandler.sendMessage(this.mHandler.obtainMessage(3, Integer.valueOf(9)));
        }
    }

    protected final void dR() {
        if (!isConnected()) {
            throw new IllegalStateException("Not connected. Call connect() and wait for onConnected() to be called.");
        }
    }

    public void dg(int i) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(4, Integer.valueOf(i)));
    }

    public void disconnect() {
        this.VS = false;
        synchronized (this.VO) {
            int size = this.VO.size();
            for (int i = 0; i < size; i++) {
                ((b) this.VO.get(i)).iS();
            }
            this.VO.clear();
        }
        a(1, null);
        if (this.VP != null) {
            kd.L(this.mContext).b(bK(), this.VP);
            this.VP = null;
        }
    }

    public final Context getContext() {
        return this.mContext;
    }

    public final Looper getLooper() {
        return this.JL;
    }

    public Bundle ht() {
        return null;
    }

    public final T iP() throws DeadObjectException {
        T t;
        synchronized (this.mK) {
            if (this.VQ == 4) {
                throw new DeadObjectException();
            }
            dR();
            kn.a(this.VN != null, "Client is connected but service is null");
            t = this.VN;
        }
        return t;
    }

    public boolean ij() {
        return this.VS;
    }

    public boolean isConnected() {
        boolean z;
        synchronized (this.mK) {
            z = this.VQ == 3;
        }
        return z;
    }

    public boolean isConnecting() {
        boolean z;
        synchronized (this.mK) {
            z = this.VQ == 2;
        }
        return z;
    }

    protected abstract T p(IBinder iBinder);

    @Deprecated
    public void registerConnectionCallbacks(GooglePlayServicesClient.ConnectionCallbacks listener) {
        this.SE.registerConnectionCallbacks(new c(listener));
    }

    public void registerConnectionCallbacks(ConnectionCallbacks listener) {
        this.SE.registerConnectionCallbacks(listener);
    }

    @Deprecated
    public void registerConnectionFailedListener(GooglePlayServicesClient.OnConnectionFailedListener listener) {
        this.SE.registerConnectionFailedListener(listener);
    }

    public void registerConnectionFailedListener(OnConnectionFailedListener listener) {
        this.SE.registerConnectionFailedListener(listener);
    }

    @Deprecated
    public void unregisterConnectionCallbacks(GooglePlayServicesClient.ConnectionCallbacks listener) {
        this.SE.unregisterConnectionCallbacks(new c(listener));
    }

    @Deprecated
    public void unregisterConnectionFailedListener(GooglePlayServicesClient.OnConnectionFailedListener listener) {
        this.SE.unregisterConnectionFailedListener(listener);
    }
}
