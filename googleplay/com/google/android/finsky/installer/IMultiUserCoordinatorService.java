package com.google.android.finsky.installer;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.wallet.instrumentmanager.R;

public interface IMultiUserCoordinatorService extends IInterface {

    public static abstract class Stub extends Binder implements IMultiUserCoordinatorService {

        private static class Proxy implements IMultiUserCoordinatorService {
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public void registerListener(IMultiUserCoordinatorServiceListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("com.google.android.finsky.installer.IMultiUserCoordinatorService");
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    this.mRemote.transact(1, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public boolean acquirePackage(String packageName) throws RemoteException {
                boolean _result = false;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("com.google.android.finsky.installer.IMultiUserCoordinatorService");
                    _data.writeString(packageName);
                    this.mRemote.transact(2, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    _reply.recycle();
                    _data.recycle();
                    return _result;
                } catch (Throwable th) {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void releasePackage(String packageName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("com.google.android.finsky.installer.IMultiUserCoordinatorService");
                    _data.writeString(packageName);
                    this.mRemote.transact(3, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void releaseAllPackages() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("com.google.android.finsky.installer.IMultiUserCoordinatorService");
                    this.mRemote.transact(4, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, "com.google.android.finsky.installer.IMultiUserCoordinatorService");
        }

        public static IMultiUserCoordinatorService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface("com.google.android.finsky.installer.IMultiUserCoordinatorService");
            if (iin == null || !(iin instanceof IMultiUserCoordinatorService)) {
                return new Proxy(obj);
            }
            return (IMultiUserCoordinatorService) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            switch (code) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    data.enforceInterface("com.google.android.finsky.installer.IMultiUserCoordinatorService");
                    registerListener(com.google.android.finsky.installer.IMultiUserCoordinatorServiceListener.Stub.asInterface(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    data.enforceInterface("com.google.android.finsky.installer.IMultiUserCoordinatorService");
                    boolean _result = acquirePackage(data.readString());
                    reply.writeNoException();
                    reply.writeInt(_result ? 1 : 0);
                    return true;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    data.enforceInterface("com.google.android.finsky.installer.IMultiUserCoordinatorService");
                    releasePackage(data.readString());
                    reply.writeNoException();
                    return true;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    data.enforceInterface("com.google.android.finsky.installer.IMultiUserCoordinatorService");
                    releaseAllPackages();
                    reply.writeNoException();
                    return true;
                case 1598968902:
                    reply.writeString("com.google.android.finsky.installer.IMultiUserCoordinatorService");
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }
    }

    boolean acquirePackage(String str) throws RemoteException;

    void registerListener(IMultiUserCoordinatorServiceListener iMultiUserCoordinatorServiceListener) throws RemoteException;

    void releaseAllPackages() throws RemoteException;

    void releasePackage(String str) throws RemoteException;
}
