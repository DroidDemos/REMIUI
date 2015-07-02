package com.google.android.gms.car;

import android.os.Handler;
import android.os.RemoteException;
import android.util.Log;

public class CarMediaManager {
    private final am Li;
    private final a Lj;
    private CarMediaListener Lk;
    private final ao Ll;
    private final b Lm;
    private final Handler mHandler;

    public interface CarMediaListener {
    }

    private class a extends com.google.android.gms.car.an.a {
        final /* synthetic */ CarMediaManager Ln;

        public void f(String str, int i) throws RemoteException {
            this.Ln.mHandler.sendMessage(this.Ln.mHandler.obtainMessage(1, i, 0, str));
        }

        public void onGetNode(String path, int start, boolean albumArt) throws RemoteException {
            this.Ln.mHandler.sendMessage(this.Ln.mHandler.obtainMessage(2, start, albumArt ? 1 : 0, path));
        }
    }

    private class b extends com.google.android.gms.car.ap.a {
        final /* synthetic */ CarMediaManager Ln;

        public void ci(int i) throws RemoteException {
            this.Ln.mHandler.sendMessage(this.Ln.mHandler.obtainMessage(3, i, 0));
        }
    }

    void handleCarDisconnection() {
        if (CarLog.isLoggable("CAR.MEDIA", 3)) {
            Log.d("CAR.MEDIA", "handleCarDisconnection");
        }
        unregisterListener();
        try {
            this.Li.b(this.Lj);
        } catch (Exception e) {
        }
        try {
            this.Ll.b(this.Lm);
        } catch (Exception e2) {
        }
    }

    public void unregisterListener() {
        this.Lk = null;
    }
}
