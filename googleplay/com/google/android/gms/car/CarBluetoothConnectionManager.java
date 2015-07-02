package com.google.android.gms.car;

import android.os.Handler;
import android.os.RemoteException;
import android.util.Log;

public class CarBluetoothConnectionManager {
    private final a Kh;

    private class a extends com.google.android.gms.car.ai.a {
        private final Handler mHandler;

        public void onCarDelayedPairing() throws RemoteException {
            if (CarLog.isLoggable("CarBluetoothClient", 3)) {
                Log.d("CarBluetoothClient", "onCarDelayedPairing");
            }
            this.mHandler.sendMessage(this.mHandler.obtainMessage(2));
        }

        void onCarDisconnected() {
            if (CarLog.isLoggable("CarBluetoothClient", 3)) {
                Log.d("CarBluetoothClient", "onCarDisconnected");
            }
            this.mHandler.sendMessageAtFrontOfQueue(this.mHandler.obtainMessage(7));
        }

        public void onDisabled() throws RemoteException {
            if (CarLog.isLoggable("CarBluetoothClient", 3)) {
                Log.d("CarBluetoothClient", "onDisabled");
            }
            this.mHandler.sendMessage(this.mHandler.obtainMessage(1));
        }

        public void onEnabled() throws RemoteException {
            if (CarLog.isLoggable("CarBluetoothClient", 3)) {
                Log.d("CarBluetoothClient", "onEnabled");
            }
            this.mHandler.sendMessage(this.mHandler.obtainMessage(0));
        }

        public void onHfpConnected() throws RemoteException {
            if (CarLog.isLoggable("CarBluetoothClient", 3)) {
                Log.d("CarBluetoothClient", "onHfpConnected");
            }
            this.mHandler.sendMessage(this.mHandler.obtainMessage(5));
        }

        public void onHfpDisconnected() throws RemoteException {
            if (CarLog.isLoggable("CarBluetoothClient", 3)) {
                Log.d("CarBluetoothClient", "onHfpDisconnected");
            }
            this.mHandler.sendMessage(this.mHandler.obtainMessage(6));
        }

        public void onPaired() throws RemoteException {
            if (CarLog.isLoggable("CarBluetoothClient", 3)) {
                Log.d("CarBluetoothClient", "onPaired");
            }
            this.mHandler.sendMessage(this.mHandler.obtainMessage(3));
        }

        public void onUnpaired() throws RemoteException {
            if (CarLog.isLoggable("CarBluetoothClient", 3)) {
                Log.d("CarBluetoothClient", "onUnpaired");
            }
            this.mHandler.sendMessage(this.mHandler.obtainMessage(4));
        }
    }

    void handleCarDisconnection() {
        if (CarLog.isLoggable("CAR.BT", 3)) {
            Log.d("CAR.BT", "handleCarDisconnection");
        }
        this.Kh.onCarDisconnected();
    }
}
