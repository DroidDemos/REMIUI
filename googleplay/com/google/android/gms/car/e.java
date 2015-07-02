package com.google.android.gms.car;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.IBinder;
import android.os.IBinder.DeathRecipient;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.car.Car.CarActivityStartListener;
import com.google.android.gms.car.Car.CarConnectionListener;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.internal.kb;
import com.google.android.gms.internal.kj;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

public class e extends kb<z> {
    private final Object KI;
    private CarAudioManager KJ;
    private CarSensorManager KK;
    private CarNavigationStatusManager KL;
    private CarMediaManager KM;
    private CarCallManager KN;
    private final HashMap<String, Object> KO;
    private CarMessageManager KP;
    private CarBluetoothConnectionManager KQ;
    private final AtomicBoolean KR;
    private final c KS;
    private final ConnectionCallbacks KT;
    private b KU;
    private DeathRecipient KV;

    class a implements ConnectionCallbacks {
        final /* synthetic */ e KW;

        a(e eVar) {
            this.KW = eVar;
        }

        public void onConnected(Bundle connectionHint) {
            if (CarLog.isLoggable("CAR.CLIENT", 3)) {
                Log.d("CAR.CLIENT", "DefaultConnectionCallbacks#onConnected");
            }
            try {
                ((z) this.KW.iP()).a(this.KW.KS);
                this.KW.gw();
            } catch (IllegalStateException e) {
                if (CarLog.isLoggable("CAR.CLIENT", 5)) {
                    Log.w("CAR.CLIENT", "service disconnected while onConnected is called");
                }
            } catch (RemoteException e2) {
                this.KW.b(e2);
            }
        }

        public void onConnectionSuspended(int cause) {
            if (CarLog.isLoggable("CAR.CLIENT", 3)) {
                Log.d("CAR.CLIENT", "DefaultConnectionCallbacks#onConnectionSuspended");
            }
        }
    }

    private static class b extends com.google.android.gms.car.aa.a {
        private final Set<CarActivityStartListener> KX;
        private final Object Ka;

        public void onActivityStarted(Intent intent) {
            synchronized (this.Ka) {
                for (CarActivityStartListener onActivityStarted : this.KX) {
                    onActivityStarted.onActivityStarted(intent);
                }
            }
        }

        public void onNewActivityRequest(Intent intent) {
            synchronized (this.Ka) {
                for (CarActivityStartListener onNewActivityRequest : this.KX) {
                    onNewActivityRequest.onNewActivityRequest(intent);
                }
            }
        }
    }

    private static class c extends com.google.android.gms.car.al.a {
        private CarConnectionListener IU;
        private volatile boolean KY;
        private final WeakReference<e> KZ;
        private final Object Ka;

        public c(e eVar) {
            this.KY = false;
            this.Ka = new Object();
            this.KZ = new WeakReference(eVar);
        }

        private void a(final CarConnectionListener carConnectionListener, final int i) {
            final e eVar = (e) this.KZ.get();
            if (eVar != null) {
                if (CarLog.isLoggable("CAR.CLIENT", 3)) {
                    Log.d("CAR.CLIENT", "ICarConnectionListenerImpl.notifyCarConnectionToClient");
                }
                bf.a(eVar.getLooper(), new Runnable(this) {
                    final /* synthetic */ c Ld;

                    public void run() {
                        if (eVar.isConnected()) {
                            carConnectionListener.onConnected(i);
                        }
                    }
                });
            }
        }

        private void b(final CarConnectionListener carConnectionListener) {
            final e eVar = (e) this.KZ.get();
            if (eVar != null) {
                if (CarLog.isLoggable("CAR.CLIENT", 3)) {
                    Log.d("CAR.CLIENT", "ICarConnectionListenerImpl.notifyCarDisconnectionToClient");
                }
                bf.a(eVar.getLooper(), new Runnable(this) {
                    final /* synthetic */ c Ld;

                    public void run() {
                        if (eVar.isConnected()) {
                            carConnectionListener.onDisconnected();
                        }
                    }
                });
            }
        }

        public void a(CarConnectionListener carConnectionListener) {
            Object obj = 1;
            e eVar = (e) this.KZ.get();
            if (eVar != null) {
                synchronized (this.Ka) {
                    this.KY = false;
                    this.IU = carConnectionListener;
                }
                if (carConnectionListener != null) {
                    try {
                        synchronized (this.Ka) {
                            if (!eVar.gh() || this.KY) {
                                obj = null;
                            } else {
                                this.KY = true;
                            }
                        }
                        if (obj != null) {
                            a(carConnectionListener, eVar.gi());
                        }
                    } catch (CarNotConnectedException e) {
                    }
                }
            }
        }

        public void onConnected(int connectionType) {
            Object obj = 1;
            if (CarLog.isLoggable("CAR.CLIENT", 3)) {
                Log.d("CAR.CLIENT", "ICarConnectionListenerImpl.onConnected");
            }
            synchronized (this.Ka) {
                if (this.KY) {
                    obj = null;
                } else {
                    this.KY = true;
                }
                CarConnectionListener carConnectionListener = this.IU;
            }
            if (obj != null && carConnectionListener != null) {
                a(carConnectionListener, connectionType);
            }
        }

        public void onDisconnected() {
            Object obj = null;
            e eVar = (e) this.KZ.get();
            if (eVar != null) {
                CarConnectionListener carConnectionListener;
                if (CarLog.isLoggable("CAR.CLIENT", 3)) {
                    Log.d("CAR.CLIENT", "ICarConnectionListenerImpl.onDisconnected");
                }
                synchronized (this.Ka) {
                    if (this.KY) {
                        obj = 1;
                    }
                    this.KY = false;
                    carConnectionListener = this.IU;
                }
                eVar.handleCarDisconnection();
                if (carConnectionListener != null && r1 != null) {
                    b(carConnectionListener);
                }
            }
        }
    }

    public e(Context context, Looper looper, CarConnectionListener carConnectionListener, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, connectionCallbacks, onConnectionFailedListener, new String[0]);
        this.KI = new Object();
        this.KO = new HashMap();
        this.KR = new AtomicBoolean(false);
        this.KS = new c(this);
        this.KT = new a(this);
        registerConnectionCallbacks(this.KT);
        this.KS.a(carConnectionListener);
    }

    private void a(RemoteException remoteException) throws CarNotConnectedException {
        b(remoteException);
        throw new CarNotConnectedException();
    }

    private void b(RemoteException remoteException) {
        if (CarLog.isLoggable("CAR.CLIENT", 4)) {
            Log.i("CAR.CLIENT", "Remote exception from car service:" + remoteException.getMessage());
        }
        if (!this.KR.getAndSet(true)) {
            this.KS.onDisconnected();
            if (isConnected()) {
                disconnect();
            }
        } else if (CarLog.isLoggable("CAR.CLIENT", 3)) {
            Log.d("CAR.CLIENT", "Already handling a remote exception, ignoring");
        }
    }

    public static void b(IllegalStateException illegalStateException) throws CarNotConnectedException {
        if (illegalStateException.getMessage().equals("CarNotConnected")) {
            throw new CarNotConnectedException();
        }
        throw illegalStateException;
    }

    private void gv() {
        synchronized (this.KI) {
            if (this.KJ != null) {
                this.KJ.handleCarDisconnection();
                this.KJ = null;
            }
            if (this.KK != null) {
                this.KK.handleCarDisconnection();
                this.KK = null;
            }
            if (this.KP != null) {
                this.KP.handleCarDisconnection();
                this.KP = null;
            }
            if (this.KQ != null) {
                this.KQ.handleCarDisconnection();
                this.KQ = null;
            }
            if (this.KL != null) {
                this.KL.handleCarDisconnection();
                this.KL = null;
            }
            if (this.KM != null) {
                this.KM.handleCarDisconnection();
                this.KM = null;
            }
            if (this.KN != null) {
                this.KN.handleCarDisconnection();
                this.KN = null;
            }
            this.KO.clear();
        }
    }

    private synchronized void gw() {
        if (this.KV == null) {
            this.KV = new DeathRecipient(this) {
                final /* synthetic */ e KW;

                {
                    this.KW = r1;
                }

                public void binderDied() {
                    Log.e("CAR.CLIENT", "ICar died!");
                    this.KW.KS.onDisconnected();
                }
            };
            try {
                ((z) iP()).asBinder().linkToDeath(this.KV, 0);
            } catch (RemoteException e) {
                Log.e("CAR.CLIENT", "Unable to link death recipient to ICar.");
            }
        }
    }

    private synchronized void gx() {
        if (this.KV != null) {
            try {
                ((z) iP()).asBinder().unlinkToDeath(this.KV, 0);
            } catch (DeadObjectException e) {
                Log.e("CAR.CLIENT", "Unable to unlink death recipient from ICar.");
            } catch (IllegalStateException e2) {
            }
            this.KV = null;
        }
    }

    private void handleCarDisconnection() {
        gv();
    }

    protected void a(kj kjVar, com.google.android.gms.internal.kb.e eVar) throws RemoteException {
        Bundle bundle = new Bundle();
        bundle.putString("client_name", "car-1-0");
        kjVar.o(eVar, 6587000, getContext().getPackageName(), bundle);
    }

    protected z ab(IBinder iBinder) {
        return com.google.android.gms.car.z.a.ac(iBinder);
    }

    protected String bK() {
        return "com.google.android.gms.car.service.START";
    }

    protected String bL() {
        return "com.google.android.gms.car.ICar";
    }

    public void connect() {
        if (CarLog.isLoggable("CAR.CLIENT", 3)) {
            Log.d("CAR.CLIENT", "connect");
        }
        super.connect();
    }

    public void disconnect() {
        if (isConnected()) {
            if (CarLog.isLoggable("CAR.CLIENT", 3)) {
                Log.d("CAR.CLIENT", "disconnect");
            }
            gv();
            try {
                ((z) iP()).b(this.KS);
            } catch (RemoteException e) {
            }
            this.KS.a(null);
            gx();
            if (this.KU != null) {
                try {
                    ((z) iP()).b(this.KU);
                } catch (RemoteException e2) {
                }
                this.KU = null;
            }
            super.disconnect();
        }
    }

    public boolean gh() {
        if (!isConnected()) {
            return false;
        }
        try {
            return ((z) iP()).gh();
        } catch (RemoteException e) {
            b(e);
            return false;
        }
    }

    public int gi() throws CarNotConnectedException {
        dR();
        try {
            return ((z) iP()).gi();
        } catch (RemoteException e) {
            a(e);
            return -1;
        } catch (IllegalStateException e2) {
            b(e2);
            return -1;
        }
    }

    protected /* synthetic */ IInterface p(IBinder iBinder) {
        return ab(iBinder);
    }
}
