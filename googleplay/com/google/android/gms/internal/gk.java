package com.google.android.gms.internal;

@fd
public abstract class gk {
    private final Runnable my;
    private volatile Thread wt;

    public gk() {
        this.my = new Runnable(this) {
            final /* synthetic */ gk wu;

            {
                this.wu = r1;
            }

            public final void run() {
                this.wu.wt = Thread.currentThread();
                this.wu.cx();
            }
        };
    }

    public abstract void cx();

    public final void start() {
        gm.a(this.my);
    }
}
