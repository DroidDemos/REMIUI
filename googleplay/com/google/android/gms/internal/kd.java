package com.google.android.gms.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.IBinder;
import android.os.Message;
import com.google.android.gms.internal.kb$com.google.android.gms.internal.kb$com.google.android.gms.internal.kb;
import com.google.android.gms.internal.kb$com.google.android.gms.internal.kb.f;
import com.google.android.wallet.instrumentmanager.R;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public final class kd implements Callback {
    private static final Object Wg;
    private static kd Wh;
    private final HashMap<String, a> Wi;
    private final Handler mHandler;
    private final Context mR;

    final class a {
        private final String Wj;
        private final a Wk;
        private final HashSet<f> Wl;
        private boolean Wm;
        private IBinder Wn;
        private ComponentName Wo;
        final /* synthetic */ kd Wp;
        private int mState;

        public class a implements ServiceConnection {
            final /* synthetic */ a Wq;

            public a(a aVar) {
                this.Wq = aVar;
            }

            public void onServiceConnected(ComponentName component, IBinder binder) {
                synchronized (this.Wq.Wp.Wi) {
                    this.Wq.Wn = binder;
                    this.Wq.Wo = component;
                    Iterator it = this.Wq.Wl.iterator();
                    while (it.hasNext()) {
                        ((kb.f) it.next()).onServiceConnected(component, binder);
                    }
                    this.Wq.mState = 1;
                }
            }

            public void onServiceDisconnected(ComponentName component) {
                synchronized (this.Wq.Wp.Wi) {
                    this.Wq.Wn = null;
                    this.Wq.Wo = component;
                    Iterator it = this.Wq.Wl.iterator();
                    while (it.hasNext()) {
                        ((kb.f) it.next()).onServiceDisconnected(component);
                    }
                    this.Wq.mState = 2;
                }
            }
        }

        public a(kd kdVar, String str) {
            this.Wp = kdVar;
            this.Wj = str;
            this.Wk = new a(this);
            this.Wl = new HashSet();
            this.mState = 2;
        }

        public void a(kb.f fVar) {
            this.Wl.add(fVar);
        }

        public void b(kb.f fVar) {
            this.Wl.remove(fVar);
        }

        public boolean c(f fVar) {
            return this.Wl.contains(fVar);
        }

        public IBinder getBinder() {
            return this.Wn;
        }

        public ComponentName getComponentName() {
            return this.Wo;
        }

        public int getState() {
            return this.mState;
        }

        public void iT() {
            this.Wm = this.Wp.mR.bindService(new Intent(this.Wj).setPackage("com.google.android.gms"), this.Wk, 129);
            if (this.Wm) {
                this.mState = 3;
            } else {
                this.Wp.mR.unbindService(this.Wk);
            }
        }

        public void iU() {
            this.Wp.mR.unbindService(this.Wk);
            this.Wm = false;
            this.mState = 2;
        }

        public String iV() {
            return this.Wj;
        }

        public boolean iW() {
            return this.Wl.isEmpty();
        }

        public boolean isBound() {
            return this.Wm;
        }
    }

    static {
        Wg = new Object();
    }

    private kd(Context context) {
        this.mHandler = new Handler(context.getMainLooper(), this);
        this.Wi = new HashMap();
        this.mR = context.getApplicationContext();
    }

    public static kd L(Context context) {
        synchronized (Wg) {
            if (Wh == null) {
                Wh = new kd(context.getApplicationContext());
            }
        }
        return Wh;
    }

    public boolean a(String str, f fVar) {
        boolean isBound;
        synchronized (this.Wi) {
            a aVar = (a) this.Wi.get(str);
            if (aVar != null) {
                this.mHandler.removeMessages(0, aVar);
                if (!aVar.c(fVar)) {
                    aVar.a((kb.f) fVar);
                    switch (aVar.getState()) {
                        case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                            fVar.onServiceConnected(aVar.getComponentName(), aVar.getBinder());
                            break;
                        case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                            aVar.iT();
                            break;
                        default:
                            break;
                    }
                }
                throw new IllegalStateException("Trying to bind a GmsServiceConnection that was already connected before.  startServiceAction=" + str);
            }
            aVar = new a(this, str);
            aVar.a((kb.f) fVar);
            aVar.iT();
            this.Wi.put(str, aVar);
            isBound = aVar.isBound();
        }
        return isBound;
    }

    public void b(String str, f fVar) {
        synchronized (this.Wi) {
            a aVar = (a) this.Wi.get(str);
            if (aVar == null) {
                throw new IllegalStateException("Nonexistent connection status for service action: " + str);
            } else if (aVar.c(fVar)) {
                aVar.b(fVar);
                if (aVar.iW()) {
                    this.mHandler.sendMessageDelayed(this.mHandler.obtainMessage(0, aVar), 5000);
                }
            } else {
                throw new IllegalStateException("Trying to unbind a GmsServiceConnection  that was not bound before.  startServiceAction=" + str);
            }
        }
    }

    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                a aVar = (a) msg.obj;
                synchronized (this.Wi) {
                    if (aVar.iW()) {
                        aVar.iU();
                        this.Wi.remove(aVar.iV());
                    }
                }
                return true;
            default:
                return false;
        }
    }
}
