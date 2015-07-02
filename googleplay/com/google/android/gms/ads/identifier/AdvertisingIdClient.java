package com.google.android.gms.ads.identifier;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Log;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.internal.kn;
import com.google.android.gms.internal.x;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class AdvertisingIdClient {
    com.google.android.gms.common.a lq;
    x lr;
    boolean ls;
    Object lt;
    a lu;
    final long lv;
    private final Context mContext;

    public static final class Info {
        private final String lA;
        private final boolean lB;

        public Info(String advertisingId, boolean limitAdTrackingEnabled) {
            this.lA = advertisingId;
            this.lB = limitAdTrackingEnabled;
        }

        public String getId() {
            return this.lA;
        }

        public boolean isLimitAdTrackingEnabled() {
            return this.lB;
        }

        public String toString() {
            return "{" + this.lA + "}" + this.lB;
        }
    }

    static class a extends Thread {
        private WeakReference<AdvertisingIdClient> lw;
        private long lx;
        CountDownLatch ly;
        boolean lz;

        public a(AdvertisingIdClient advertisingIdClient, long j) {
            this.lw = new WeakReference(advertisingIdClient);
            this.lx = j;
            this.ly = new CountDownLatch(1);
            this.lz = false;
            start();
        }

        private void disconnect() {
            AdvertisingIdClient advertisingIdClient = (AdvertisingIdClient) this.lw.get();
            if (advertisingIdClient != null) {
                advertisingIdClient.finish();
                this.lz = true;
            }
        }

        public boolean aa() {
            return this.lz;
        }

        public void cancel() {
            this.ly.countDown();
        }

        public void run() {
            try {
                if (!this.ly.await(this.lx, TimeUnit.MILLISECONDS)) {
                    disconnect();
                }
            } catch (InterruptedException e) {
                disconnect();
            }
        }
    }

    public AdvertisingIdClient(Context context, long timeoutInMillis) {
        this.lt = new Object();
        kn.k(context);
        this.mContext = context;
        this.ls = false;
        this.lv = timeoutInMillis;
    }

    private void Z() {
        synchronized (this.lt) {
            if (this.lu != null) {
                this.lu.cancel();
                try {
                    this.lu.join();
                } catch (InterruptedException e) {
                }
            }
            if (this.lv > 0) {
                this.lu = new a(this, this.lv);
            }
        }
    }

    static x a(Context context, com.google.android.gms.common.a aVar) throws IOException {
        try {
            return com.google.android.gms.internal.x.a.g(aVar.hO());
        } catch (InterruptedException e) {
            throw new IOException("Interrupted exception");
        }
    }

    public static Info getAdvertisingIdInfo(Context context) throws IOException, IllegalStateException, GooglePlayServicesNotAvailableException, GooglePlayServicesRepairableException {
        AdvertisingIdClient advertisingIdClient = new AdvertisingIdClient(context, -1);
        try {
            advertisingIdClient.b(false);
            Info info = advertisingIdClient.getInfo();
            return info;
        } finally {
            advertisingIdClient.finish();
        }
    }

    static com.google.android.gms.common.a h(Context context) throws IOException, GooglePlayServicesNotAvailableException, GooglePlayServicesRepairableException {
        try {
            context.getPackageManager().getPackageInfo("com.android.vending", 0);
            try {
                GooglePlayServicesUtil.B(context);
                Object aVar = new com.google.android.gms.common.a();
                Intent intent = new Intent("com.google.android.gms.ads.identifier.service.START");
                intent.setPackage("com.google.android.gms");
                if (context.bindService(intent, aVar, 1)) {
                    return aVar;
                }
                throw new IOException("Connection failure");
            } catch (Throwable e) {
                throw new IOException(e);
            }
        } catch (NameNotFoundException e2) {
            throw new GooglePlayServicesNotAvailableException(9);
        }
    }

    protected void b(boolean z) throws IOException, IllegalStateException, GooglePlayServicesNotAvailableException, GooglePlayServicesRepairableException {
        kn.be("Calling this from your main thread can lead to deadlock");
        synchronized (this) {
            if (this.ls) {
                finish();
            }
            this.lq = h(this.mContext);
            this.lr = a(this.mContext, this.lq);
            this.ls = true;
            if (z) {
                Z();
            }
        }
    }

    protected void finalize() throws Throwable {
        finish();
        super.finalize();
    }

    public void finish() {
        kn.be("Calling this from your main thread can lead to deadlock");
        synchronized (this) {
            if (this.mContext == null || this.lq == null) {
                return;
            }
            try {
                if (this.ls) {
                    this.mContext.unbindService(this.lq);
                }
            } catch (Throwable e) {
                Log.i("AdvertisingIdClient", "AdvertisingIdClient unbindService failed.", e);
            }
            this.ls = false;
            this.lr = null;
            this.lq = null;
        }
    }

    public Info getInfo() throws IOException {
        Info info;
        kn.be("Calling this from your main thread can lead to deadlock");
        synchronized (this) {
            if (!this.ls) {
                synchronized (this.lt) {
                    if (this.lu == null || !this.lu.aa()) {
                        throw new IOException("AdvertisingIdClient is not connected.");
                    }
                }
                try {
                    b(false);
                    if (!this.ls) {
                        throw new IOException("AdvertisingIdClient cannot reconnect.");
                    }
                } catch (Throwable e) {
                    Log.i("AdvertisingIdClient", "GMS remote exception ", e);
                    throw new IOException("Remote exception");
                } catch (Throwable e2) {
                    throw new IOException("AdvertisingIdClient cannot reconnect.", e2);
                }
            }
            kn.k(this.lq);
            kn.k(this.lr);
            info = new Info(this.lr.getId(), this.lr.c(true));
        }
        Z();
        return info;
    }
}
