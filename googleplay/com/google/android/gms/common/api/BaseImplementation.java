package com.google.android.gms.common.api;

import android.os.DeadObjectException;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import android.util.Pair;
import com.google.android.gms.common.api.Api.c;
import com.google.android.gms.internal.kh;
import com.google.android.gms.internal.kn;
import com.google.android.wallet.instrumentmanager.R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CountDownLatch;

public class BaseImplementation {

    public interface b<R> {
        void d(R r);
    }

    public static abstract class AbstractPendingResult<R extends Result> implements b<R>, PendingResult<R> {
        private final Object Sd;
        private final ArrayList<com.google.android.gms.common.api.PendingResult.a> Se;
        private ResultCallback<R> Sf;
        private volatile R Sg;
        private volatile boolean Sh;
        private boolean Si;
        private boolean Sj;
        private kh Sk;
        protected final CallbackHandler<R> mHandler;
        private final CountDownLatch mu;

        protected AbstractPendingResult(Looper looper) {
            this.Sd = new Object();
            this.mu = new CountDownLatch(1);
            this.Se = new ArrayList();
            this.mHandler = new CallbackHandler(looper);
        }

        private void c(R r) {
            this.Sg = r;
            this.Sk = null;
            this.mu.countDown();
            Status status = this.Sg.getStatus();
            if (this.Sf != null) {
                this.mHandler.removeTimeoutMessages();
                if (!this.Si) {
                    this.mHandler.sendResultCallback(this.Sf, hW());
                }
            }
            Iterator it = this.Se.iterator();
            while (it.hasNext()) {
                ((com.google.android.gms.common.api.PendingResult.a) it.next()).s(status);
            }
            this.Se.clear();
        }

        private R hW() {
            R r;
            synchronized (this.Sd) {
                kn.a(!this.Sh, "Result has already been consumed.");
                kn.a(isReady(), "Result is not ready.");
                r = this.Sg;
                hX();
            }
            return r;
        }

        private void hZ() {
            synchronized (this.Sd) {
                if (!isReady()) {
                    b(b(Status.Tk));
                    this.Sj = true;
                }
            }
        }

        protected abstract R b(Status status);

        public final void b(R r) {
            boolean z = true;
            synchronized (this.Sd) {
                if (this.Sj || this.Si) {
                    BaseImplementation.a(r);
                    return;
                }
                kn.a(!isReady(), "Results have already been set");
                if (this.Sh) {
                    z = false;
                }
                kn.a(z, "Result has already been consumed");
                c(r);
            }
        }

        public void cancel() {
            synchronized (this.Sd) {
                if (this.Si || this.Sh) {
                    return;
                }
                if (this.Sk != null) {
                    try {
                        this.Sk.cancel();
                    } catch (RemoteException e) {
                    }
                }
                BaseImplementation.a(this.Sg);
                this.Sf = null;
                this.Si = true;
                c(b(Status.Tl));
            }
        }

        public /* synthetic */ void d(Object obj) {
            b((Result) obj);
        }

        protected void hX() {
            this.Sh = true;
            this.Sg = null;
            this.Sf = null;
        }

        public boolean isCanceled() {
            boolean z;
            synchronized (this.Sd) {
                z = this.Si;
            }
            return z;
        }

        public final boolean isReady() {
            return this.mu.getCount() == 0;
        }

        public final void setResultCallback(ResultCallback<R> callback) {
            kn.a(!this.Sh, "Result has already been consumed.");
            synchronized (this.Sd) {
                if (isCanceled()) {
                    return;
                }
                if (isReady()) {
                    this.mHandler.sendResultCallback(callback, hW());
                } else {
                    this.Sf = callback;
                }
            }
        }
    }

    public static class CallbackHandler<R extends Result> extends Handler {
        public CallbackHandler() {
            this(Looper.getMainLooper());
        }

        public CallbackHandler(Looper looper) {
            super(looper);
        }

        protected void deliverResultCallback(ResultCallback<R> callback, R result) {
            try {
                callback.onResult(result);
            } catch (RuntimeException e) {
                BaseImplementation.a(result);
                throw e;
            }
        }

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    Pair pair = (Pair) msg.obj;
                    deliverResultCallback((ResultCallback) pair.first, (Result) pair.second);
                    return;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    ((AbstractPendingResult) msg.obj).hZ();
                    return;
                default:
                    Log.wtf("GoogleApi", "Don't know how to handle this message.");
                    return;
            }
        }

        public void removeTimeoutMessages() {
            removeMessages(2);
        }

        public void sendResultCallback(ResultCallback<R> callback, R result) {
            sendMessage(obtainMessage(1, new Pair(callback, result)));
        }
    }

    public static abstract class a<R extends Result, A extends com.google.android.gms.common.api.Api.a> extends AbstractPendingResult<R> implements d<A> {
        private final c<A> Sb;
        private final GoogleApiClient Sl;
        private b Sm;

        protected a(c<A> cVar, GoogleApiClient googleApiClient) {
            super(googleApiClient.getLooper());
            this.Sb = (c) kn.k(cVar);
            this.Sl = googleApiClient;
        }

        private void c(RemoteException remoteException) {
            r(new Status(8, remoteException.getLocalizedMessage(), null));
        }

        protected abstract void a(A a) throws RemoteException;

        public void a(b bVar) {
            this.Sm = bVar;
        }

        public final void b(A a) throws DeadObjectException {
            try {
                a((com.google.android.gms.common.api.Api.a) a);
            } catch (RemoteException e) {
                c(e);
                throw e;
            } catch (RemoteException e2) {
                c(e2);
            }
        }

        public final c<A> hV() {
            return this.Sb;
        }

        protected void hX() {
            super.hX();
            if (this.Sm != null) {
                this.Sm.b(this);
                this.Sm = null;
            }
        }

        public int ib() {
            return 0;
        }

        public final void r(Status status) {
            kn.b(!status.isSuccess(), (Object) "Failed result must not be success");
            b(b(status));
        }
    }

    static void a(Result result) {
        if (result instanceof Releasable) {
            try {
                ((Releasable) result).release();
            } catch (Throwable e) {
                Log.w("GoogleApi", "Unable to release " + result, e);
            }
        }
    }
}
