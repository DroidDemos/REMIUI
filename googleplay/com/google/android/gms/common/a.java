package com.google.android.gms.common;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Looper;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class a implements ServiceConnection {
    boolean Rh;
    private final BlockingQueue<IBinder> Ri;

    public a() {
        this.Rh = false;
        this.Ri = new LinkedBlockingQueue();
    }

    public IBinder hO() throws InterruptedException {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            throw new IllegalStateException("BlockingServiceConnection.getService() called on main thread");
        } else if (this.Rh) {
            throw new IllegalStateException();
        } else {
            this.Rh = true;
            return (IBinder) this.Ri.take();
        }
    }

    public void onServiceConnected(ComponentName name, IBinder service) {
        this.Ri.add(service);
    }

    public void onServiceDisconnected(ComponentName name) {
    }
}
