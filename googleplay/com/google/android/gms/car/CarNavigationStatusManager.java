package com.google.android.gms.car;

import android.os.Handler;
import android.os.RemoteException;
import android.util.Log;

public class CarNavigationStatusManager {
    private int LA;
    private int LB;
    private final as Lu;
    private a Lv;
    private CarNavigationStatusListener Lw;
    private int Lx;
    private int Ly;
    private int Lz;
    private final Handler mHandler;

    public interface CarNavigationStatusListener {
    }

    private class a extends com.google.android.gms.car.at.a {
        final /* synthetic */ CarNavigationStatusManager LC;

        public void onStart(int minIntervalMs, int instrumentClusterType, int imageHeight, int imageWidth, int imageColourDepthBits) throws RemoteException {
            if (CarLog.isLoggable("CAR.SENSOR", 3)) {
                Log.d("CAR.SENSOR", "onStart(" + minIntervalMs + ", " + instrumentClusterType + ", " + imageHeight + ", " + imageWidth + ", " + imageColourDepthBits + ")");
            }
            this.LC.Lx = minIntervalMs;
            this.LC.Ly = instrumentClusterType;
            this.LC.Lz = imageHeight;
            this.LC.LA = imageWidth;
            this.LC.LB = imageColourDepthBits;
            this.LC.mHandler.sendMessage(this.LC.mHandler.obtainMessage(1));
        }

        public void onStop() throws RemoteException {
            this.LC.mHandler.sendMessage(this.LC.mHandler.obtainMessage(2));
        }
    }

    private void b(RemoteException remoteException) {
        if (CarLog.isLoggable("CAR.SENSOR", 4)) {
            Log.i("CAR.SENSOR", "RemoteException from car service:" + remoteException.getMessage());
        }
    }

    void handleCarDisconnection() {
        if (CarLog.isLoggable("CAR.SENSOR", 3)) {
            Log.d("CAR.SENSOR", "handleCarDisconnection");
        }
        unregisterListener();
    }

    public void unregisterListener() {
        try {
            this.Lu.b(this.Lv);
        } catch (IllegalStateException e) {
        } catch (RemoteException e2) {
            b(e2);
        }
        this.Lw = null;
    }
}
