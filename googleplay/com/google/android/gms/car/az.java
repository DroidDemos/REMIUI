package com.google.android.gms.car;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.wallet.instrumentmanager.R;

public interface az extends IInterface {

    public static abstract class a extends Binder implements az {

        private static class a implements az {
            private IBinder ld;

            a(IBinder iBinder) {
                this.ld = iBinder;
            }

            public IBinder asBinder() {
                return this.ld;
            }

            public void onSensorInjectorExpired(int sensorType) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICarSensorEventInjector");
                    obtain.writeInt(sensorType);
                    this.ld.transact(1, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }
        }

        public static az aB(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.car.ICarSensorEventInjector");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof az)) ? new a(iBinder) : (az) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            switch (code) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    data.enforceInterface("com.google.android.gms.car.ICarSensorEventInjector");
                    onSensorInjectorExpired(data.readInt());
                    return true;
                case 1598968902:
                    reply.writeString("com.google.android.gms.car.ICarSensorEventInjector");
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }
    }

    void onSensorInjectorExpired(int i) throws RemoteException;
}
