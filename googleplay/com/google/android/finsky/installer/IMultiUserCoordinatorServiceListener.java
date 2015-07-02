package com.google.android.finsky.installer;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.wallet.instrumentmanager.R;

public interface IMultiUserCoordinatorServiceListener extends IInterface {

    public static abstract class Stub extends Binder implements IMultiUserCoordinatorServiceListener {

        private static class Proxy implements IMultiUserCoordinatorServiceListener {
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public void packageAcquired(String packageName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("com.google.android.finsky.installer.IMultiUserCoordinatorServiceListener");
                    _data.writeString(packageName);
                    this.mRemote.transact(1, _data, null, 1);
                } finally {
                    _data.recycle();
                }
            }

            public void packageReleased(String packageName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("com.google.android.finsky.installer.IMultiUserCoordinatorServiceListener");
                    _data.writeString(packageName);
                    this.mRemote.transact(2, _data, null, 1);
                } finally {
                    _data.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, "com.google.android.finsky.installer.IMultiUserCoordinatorServiceListener");
        }

        public static IMultiUserCoordinatorServiceListener asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface("com.google.android.finsky.installer.IMultiUserCoordinatorServiceListener");
            if (iin == null || !(iin instanceof IMultiUserCoordinatorServiceListener)) {
                return new Proxy(obj);
            }
            return (IMultiUserCoordinatorServiceListener) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            switch (code) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    data.enforceInterface("com.google.android.finsky.installer.IMultiUserCoordinatorServiceListener");
                    packageAcquired(data.readString());
                    return true;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    data.enforceInterface("com.google.android.finsky.installer.IMultiUserCoordinatorServiceListener");
                    packageReleased(data.readString());
                    return true;
                case 1598968902:
                    reply.writeString("com.google.android.finsky.installer.IMultiUserCoordinatorServiceListener");
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }
    }

    void packageAcquired(String str) throws RemoteException;

    void packageReleased(String str) throws RemoteException;
}
