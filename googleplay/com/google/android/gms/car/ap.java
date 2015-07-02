package com.google.android.gms.car;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.wallet.instrumentmanager.R;

public interface ap extends IInterface {

    public static abstract class a extends Binder implements ap {

        private static class a implements ap {
            private IBinder ld;

            a(IBinder iBinder) {
                this.ld = iBinder;
            }

            public IBinder asBinder() {
                return this.ld;
            }

            public void ci(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICarMediaPlaybackStatusEventListener");
                    obtain.writeInt(i);
                    this.ld.transact(1, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }
        }

        public static ap as(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.car.ICarMediaPlaybackStatusEventListener");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof ap)) ? new a(iBinder) : (ap) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            switch (code) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    data.enforceInterface("com.google.android.gms.car.ICarMediaPlaybackStatusEventListener");
                    ci(data.readInt());
                    return true;
                case 1598968902:
                    reply.writeString("com.google.android.gms.car.ICarMediaPlaybackStatusEventListener");
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }
    }

    void ci(int i) throws RemoteException;
}
