package com.google.android.gms.car;

import android.os.Handler;
import android.os.IBinder.DeathRecipient;
import android.os.RemoteException;

public class CarAudioTrack implements DeathRecipient {
    private final CarAudioManager JN;
    private final int JQ;
    private final af JV;
    private final a JY;
    private volatile boolean JZ;
    private final Handler mHandler;
    private int mPlayState;

    private class a extends com.google.android.gms.car.ag.a {
        final /* synthetic */ CarAudioTrack Kf;

        public void bW(int i) {
            if (i == 3) {
                this.Kf.bV(2);
            } else {
                this.Kf.bV(1);
            }
            this.Kf.mHandler.sendMessage(this.Kf.mHandler.obtainMessage(2, i, 0));
        }

        public void gf() {
            this.Kf.mHandler.sendMessage(this.Kf.mHandler.obtainMessage(1));
        }
    }

    private synchronized void bV(int i) {
        this.mPlayState = i;
    }

    public void binderDied() {
        this.JV.asBinder().unlinkToDeath(this, 0);
        this.JZ = true;
        this.JN.bT(this.JQ);
    }

    public synchronized void release() {
        if (!this.JZ) {
            this.JN.bT(this.JQ);
            try {
                this.JV.a(this.JY);
            } catch (RemoteException e) {
            }
            this.JV.asBinder().unlinkToDeath(this, 0);
            this.JZ = true;
        }
    }
}
