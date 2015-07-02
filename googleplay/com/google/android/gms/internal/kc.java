package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import java.util.ArrayList;
import java.util.Iterator;

public final class kc {
    private final b Wa;
    private final ArrayList<ConnectionCallbacks> Wb;
    final ArrayList<ConnectionCallbacks> Wc;
    private boolean Wd;
    private final ArrayList<OnConnectionFailedListener> We;
    private final Handler mHandler;

    public interface b {
        Bundle ht();

        boolean ij();

        boolean isConnected();
    }

    final class a extends Handler {
        final /* synthetic */ kc Wf;

        public a(kc kcVar, Looper looper) {
            this.Wf = kcVar;
            super(looper);
        }

        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                synchronized (this.Wf.Wb) {
                    if (this.Wf.Wa.ij() && this.Wf.Wa.isConnected() && this.Wf.Wb.contains(msg.obj)) {
                        ((ConnectionCallbacks) msg.obj).onConnected(this.Wf.Wa.ht());
                    }
                }
                return;
            }
            Log.wtf("GmsClientEvents", "Don't know how to handle this message.");
        }
    }

    public kc(Context context, Looper looper, b bVar) {
        this.Wb = new ArrayList();
        this.Wc = new ArrayList();
        this.Wd = false;
        this.We = new ArrayList();
        this.Wa = bVar;
        this.mHandler = new a(this, looper);
    }

    public void b(ConnectionResult connectionResult) {
        this.mHandler.removeMessages(1);
        synchronized (this.We) {
            Iterator it = new ArrayList(this.We).iterator();
            while (it.hasNext()) {
                OnConnectionFailedListener onConnectionFailedListener = (OnConnectionFailedListener) it.next();
                if (!this.Wa.ij()) {
                    return;
                } else if (this.We.contains(onConnectionFailedListener)) {
                    onConnectionFailedListener.onConnectionFailed(connectionResult);
                }
            }
        }
    }

    protected void dT() {
        synchronized (this.Wb) {
            h(this.Wa.ht());
        }
    }

    public void dh(int i) {
        this.mHandler.removeMessages(1);
        synchronized (this.Wb) {
            this.Wd = true;
            Iterator it = new ArrayList(this.Wb).iterator();
            while (it.hasNext()) {
                ConnectionCallbacks connectionCallbacks = (ConnectionCallbacks) it.next();
                if (!this.Wa.ij()) {
                    break;
                } else if (this.Wb.contains(connectionCallbacks)) {
                    connectionCallbacks.onConnectionSuspended(i);
                }
            }
            this.Wd = false;
        }
    }

    public void h(Bundle bundle) {
        boolean z = true;
        synchronized (this.Wb) {
            kn.K(!this.Wd);
            this.mHandler.removeMessages(1);
            this.Wd = true;
            if (this.Wc.size() != 0) {
                z = false;
            }
            kn.K(z);
            Iterator it = new ArrayList(this.Wb).iterator();
            while (it.hasNext()) {
                ConnectionCallbacks connectionCallbacks = (ConnectionCallbacks) it.next();
                if (!this.Wa.ij() || !this.Wa.isConnected()) {
                    break;
                } else if (!this.Wc.contains(connectionCallbacks)) {
                    connectionCallbacks.onConnected(bundle);
                }
            }
            this.Wc.clear();
            this.Wd = false;
        }
    }

    public void registerConnectionCallbacks(ConnectionCallbacks listener) {
        kn.k(listener);
        synchronized (this.Wb) {
            if (this.Wb.contains(listener)) {
                Log.w("GmsClientEvents", "registerConnectionCallbacks(): listener " + listener + " is already registered");
            } else {
                this.Wb.add(listener);
            }
        }
        if (this.Wa.isConnected()) {
            this.mHandler.sendMessage(this.mHandler.obtainMessage(1, listener));
        }
    }

    public void registerConnectionFailedListener(OnConnectionFailedListener listener) {
        kn.k(listener);
        synchronized (this.We) {
            if (this.We.contains(listener)) {
                Log.w("GmsClientEvents", "registerConnectionFailedListener(): listener " + listener + " is already registered");
            } else {
                this.We.add(listener);
            }
        }
    }

    public void unregisterConnectionCallbacks(ConnectionCallbacks listener) {
        kn.k(listener);
        synchronized (this.Wb) {
            if (this.Wb != null) {
                if (!this.Wb.remove(listener)) {
                    Log.w("GmsClientEvents", "unregisterConnectionCallbacks(): listener " + listener + " not found");
                } else if (this.Wd) {
                    this.Wc.add(listener);
                }
            }
        }
    }

    public void unregisterConnectionFailedListener(OnConnectionFailedListener listener) {
        kn.k(listener);
        synchronized (this.We) {
            if (!(this.We == null || this.We.remove(listener))) {
                Log.w("GmsClientEvents", "unregisterConnectionFailedListener(): listener " + listener + " not found");
            }
        }
    }
}
