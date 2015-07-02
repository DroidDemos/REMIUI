package com.google.android.gms.car;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.wallet.instrumentmanager.R;

public interface al extends IInterface {

    public static abstract class a extends Binder implements al {

        private static class a implements al {
            private IBinder ld;

            a(IBinder iBinder) {
                this.ld = iBinder;
            }

            public IBinder asBinder() {
                return this.ld;
            }

            public void onConnected(int connectionType) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICarConnectionListener");
                    obtain.writeInt(connectionType);
                    this.ld.transact(1, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onDisconnected() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICarConnectionListener");
                    this.ld.transact(2, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }
        }

        public a() {
            attachInterface(this, "com.google.android.gms.car.ICarConnectionListener");
        }

        public static al ao(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.car.ICarConnectionListener");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof al)) ? new a(iBinder) : (al) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            switch (code) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    data.enforceInterface("com.google.android.gms.car.ICarConnectionListener");
                    onConnected(data.readInt());
                    return true;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    data.enforceInterface("com.google.android.gms.car.ICarConnectionListener");
                    onDisconnected();
                    return true;
                case 1598968902:
                    reply.writeString("com.google.android.gms.car.ICarConnectionListener");
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }
    }

    void onConnected(int i) throws RemoteException;

    void onDisconnected() throws RemoteException;
}
