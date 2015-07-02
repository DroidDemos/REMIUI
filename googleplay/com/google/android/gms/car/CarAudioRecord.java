package com.google.android.gms.car;

import android.os.RemoteException;
import android.util.Log;
import java.io.IOException;
import java.io.InputStream;

public class CarAudioRecord {
    private final CarAudioManager JN;
    private final ad JO;
    private InputStream JP;
    private final int JQ;
    private final ae JT;
    private volatile int mState;

    private void b(RemoteException remoteException) {
        Log.i("CAR.AUDIO", "RemoteException from car service:" + remoteException.getMessage());
        if (this.mState == 1 && this.JP != null) {
            try {
                this.JP.close();
            } catch (IOException e) {
            }
        }
        if (this.mState != 2) {
            this.JN.a(this);
            this.mState = 2;
        }
    }

    synchronized void G(boolean z) {
        if (this.mState != 2) {
            stop();
            if (z) {
                this.JN.a(this);
            }
            try {
                this.JO.e(this.JT);
            } catch (RemoteException e) {
            }
            this.mState = 2;
            if (CarLog.isLoggable("CAR.AUDIO", 3)) {
                Log.d("CAR.AUDIO", "released");
            }
        }
    }

    public int getStreamType() {
        return this.JQ;
    }

    public synchronized void stop() {
        if (this.mState == 1) {
            this.mState = 0;
            try {
                this.JO.b(this.JT);
                this.JP.close();
            } catch (RemoteException e) {
                b(e);
            } catch (IOException e2) {
            }
            if (CarLog.isLoggable("CAR.AUDIO", 3)) {
                Log.d("CAR.AUDIO", "stopped");
            }
        } else if (CarLog.isLoggable("CAR.AUDIO", 3)) {
            Log.d("CAR.AUDIO", "stop while not started");
        }
    }
}
