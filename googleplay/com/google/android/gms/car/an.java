package com.google.android.gms.car;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.wallet.instrumentmanager.R;

public interface an extends IInterface {

    public static abstract class a extends Binder implements an {

        private static class a implements an {
            private IBinder ld;

            a(IBinder iBinder) {
                this.ld = iBinder;
            }

            public IBinder asBinder() {
                return this.ld;
            }

            public void f(String str, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICarMediaBrowserEventListener");
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    this.ld.transact(2, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onGetNode(String path, int start, boolean getAlbumArt) throws RemoteException {
                int i = 1;
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICarMediaBrowserEventListener");
                    obtain.writeString(path);
                    obtain.writeInt(start);
                    if (!getAlbumArt) {
                        i = 0;
                    }
                    obtain.writeInt(i);
                    this.ld.transact(1, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }
        }

        public static an aq(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.car.ICarMediaBrowserEventListener");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof an)) ? new a(iBinder) : (an) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            switch (code) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    data.enforceInterface("com.google.android.gms.car.ICarMediaBrowserEventListener");
                    onGetNode(data.readString(), data.readInt(), data.readInt() != 0);
                    return true;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    data.enforceInterface("com.google.android.gms.car.ICarMediaBrowserEventListener");
                    f(data.readString(), data.readInt());
                    return true;
                case 1598968902:
                    reply.writeString("com.google.android.gms.car.ICarMediaBrowserEventListener");
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }
    }

    void f(String str, int i) throws RemoteException;

    void onGetNode(String str, int i, boolean z) throws RemoteException;
}
