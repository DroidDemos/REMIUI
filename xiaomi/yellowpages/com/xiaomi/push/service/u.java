package com.xiaomi.push.service;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Pair;
import com.xiaomi.f.a.c.b;
import java.util.ArrayList;
import java.util.List;

public class u extends HandlerThread {
    private volatile long a;
    private volatile boolean b;
    private volatile Handler vt;
    private List vu;

    public u(String str) {
        super(str);
        this.a = 0;
        this.b = false;
        this.vu = new ArrayList();
    }

    public boolean I(int i) {
        return this.vt != null ? this.vt.hasMessages(i) : false;
    }

    public void a() {
        for (int i = 1; i < 14; i++) {
            a(i);
        }
    }

    public void a(int i) {
        if (this.vt != null) {
            this.vt.removeMessages(i);
        }
    }

    public void a(int i, Object obj) {
        if (this.vt != null) {
            this.vt.removeMessages(i, obj);
        }
    }

    public void a(b bVar, long j) {
        synchronized (this.vu) {
            if (this.vt != null) {
                Message obtain = Message.obtain();
                obtain.what = bVar.d;
                obtain.obj = bVar;
                this.vt.sendMessageDelayed(obtain, j);
            } else {
                b.a("the job is pended, the controller is not ready.");
                this.vu.add(new Pair(bVar, Long.valueOf(j)));
            }
        }
    }

    public boolean b() {
        return this.b && System.currentTimeMillis() - this.a > 600000;
    }

    protected void onLooperPrepared() {
        this.vt = new B(this, getLooper());
        synchronized (this.vu) {
            for (Pair pair : this.vu) {
                b.a("executing the pending job.");
                a((b) pair.first, ((Long) pair.second).longValue());
            }
            this.vu.clear();
        }
    }
}
