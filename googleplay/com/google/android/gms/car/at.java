package com.google.android.gms.car;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.wallet.instrumentmanager.R;

public interface at extends IInterface {

    public static abstract class a extends Binder implements at {

        private static class a implements at {
            private IBinder ld;

            a(IBinder iBinder) {
                this.ld = iBinder;
            }

            public IBinder asBinder() {
                return this.ld;
            }

            public void onStart(int minIntervalMs, int instrumentClusterType, int imageHeight, int imageWidth, int imageColourDepthBits) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICarNavigationStatusEventListener");
                    obtain.writeInt(minIntervalMs);
                    obtain.writeInt(instrumentClusterType);
                    obtain.writeInt(imageHeight);
                    obtain.writeInt(imageWidth);
                    obtain.writeInt(imageColourDepthBits);
                    this.ld.transact(1, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onStop() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICarNavigationStatusEventListener");
                    this.ld.transact(2, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }
        }

        public static at aw(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.car.ICarNavigationStatusEventListener");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof at)) ? new a(iBinder) : (at) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            switch (code) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    data.enforceInterface("com.google.android.gms.car.ICarNavigationStatusEventListener");
                    onStart(data.readInt(), data.readInt(), data.readInt(), data.readInt(), data.readInt());
                    return true;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    data.enforceInterface("com.google.android.gms.car.ICarNavigationStatusEventListener");
                    onStop();
                    return true;
                case 1598968902:
                    reply.writeString("com.google.android.gms.car.ICarNavigationStatusEventListener");
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }
    }

    void onStart(int i, int i2, int i3, int i4, int i5) throws RemoteException;

    void onStop() throws RemoteException;
}
