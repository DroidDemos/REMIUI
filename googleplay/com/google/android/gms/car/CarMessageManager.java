package com.google.android.gms.car;

import android.os.Handler;
import android.os.RemoteException;
import android.util.Log;
import java.lang.ref.WeakReference;

public class CarMessageManager {
    private final aq Lo;
    private final a Lp;
    private volatile CarMessageListener Lq;
    private final Handler mHandler;

    public interface CarMessageListener {
    }

    private static class a extends com.google.android.gms.car.ar.a {
        private final WeakReference<CarMessageManager> Lt;

        public void onIntegerMessage(int category, int key, int value) {
            CarMessageManager carMessageManager = (CarMessageManager) this.Lt.get();
            if (carMessageManager != null) {
                carMessageManager.mHandler.sendMessage(carMessageManager.mHandler.obtainMessage(1, category, key, new Integer(value)));
            }
        }

        public void onOwnershipLost(int category) {
            CarMessageManager carMessageManager = (CarMessageManager) this.Lt.get();
            if (carMessageManager != null) {
                carMessageManager.mHandler.sendMessage(carMessageManager.mHandler.obtainMessage(2, category, 0));
            }
        }
    }

    void handleCarDisconnection() {
        if (CarLog.isLoggable("CAR.MSG", 3)) {
            Log.d("CAR.MSG", "handleCarDisconnection");
        }
        try {
            this.Lo.b(this.Lp);
        } catch (IllegalStateException e) {
        } catch (RemoteException e2) {
        }
        this.Lq = null;
    }
}
