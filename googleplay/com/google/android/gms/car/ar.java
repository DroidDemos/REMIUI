package com.google.android.gms.car;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.wallet.instrumentmanager.R;

public interface ar extends IInterface {

    public static abstract class a extends Binder implements ar {

        private static class a implements ar {
            private IBinder ld;

            a(IBinder iBinder) {
                this.ld = iBinder;
            }

            public IBinder asBinder() {
                return this.ld;
            }

            public void onIntegerMessage(int category, int key, int value) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICarMessageCallback");
                    obtain.writeInt(category);
                    obtain.writeInt(key);
                    obtain.writeInt(value);
                    this.ld.transact(1, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onOwnershipLost(int category) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICarMessageCallback");
                    obtain.writeInt(category);
                    this.ld.transact(2, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }
        }

        public static ar au(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.car.ICarMessageCallback");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof ar)) ? new a(iBinder) : (ar) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            switch (code) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    data.enforceInterface("com.google.android.gms.car.ICarMessageCallback");
                    onIntegerMessage(data.readInt(), data.readInt(), data.readInt());
                    return true;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    data.enforceInterface("com.google.android.gms.car.ICarMessageCallback");
                    onOwnershipLost(data.readInt());
                    return true;
                case 1598968902:
                    reply.writeString("com.google.android.gms.car.ICarMessageCallback");
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }
    }

    void onIntegerMessage(int i, int i2, int i3) throws RemoteException;

    void onOwnershipLost(int i) throws RemoteException;
}
