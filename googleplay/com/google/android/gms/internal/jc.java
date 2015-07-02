package com.google.android.gms.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.wallet.instrumentmanager.R;

public interface jc extends IInterface {

    public static abstract class a extends Binder implements jc {

        private static class a implements jc {
            private IBinder ld;

            a(IBinder iBinder) {
                this.ld = iBinder;
            }

            public IBinder asBinder() {
                return this.ld;
            }

            public String hN() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.checkin.internal.ICheckinService");
                    this.ld.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    String readString = obtain2.readString();
                    return readString;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static jc aO(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.checkin.internal.ICheckinService");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof jc)) ? new a(iBinder) : (jc) queryLocalInterface;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            switch (code) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    data.enforceInterface("com.google.android.gms.checkin.internal.ICheckinService");
                    String hN = hN();
                    reply.writeNoException();
                    reply.writeString(hN);
                    return true;
                case 1598968902:
                    reply.writeString("com.google.android.gms.checkin.internal.ICheckinService");
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }
    }

    String hN() throws RemoteException;
}
