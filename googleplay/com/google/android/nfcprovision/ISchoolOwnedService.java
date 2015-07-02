package com.google.android.nfcprovision;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.wallet.instrumentmanager.R;

public interface ISchoolOwnedService extends IInterface {

    public static abstract class Stub extends Binder implements ISchoolOwnedService {

        private static class Proxy implements ISchoolOwnedService {
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public boolean isSchoolOwned() throws RemoteException {
                boolean _result = true;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("com.google.android.nfcprovision.ISchoolOwnedService");
                    this.mRemote.transact(1, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() == 0) {
                        _result = false;
                    }
                    _reply.recycle();
                    _data.recycle();
                    return _result;
                } catch (Throwable th) {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static ISchoolOwnedService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface("com.google.android.nfcprovision.ISchoolOwnedService");
            if (iin == null || !(iin instanceof ISchoolOwnedService)) {
                return new Proxy(obj);
            }
            return (ISchoolOwnedService) iin;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            switch (code) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    data.enforceInterface("com.google.android.nfcprovision.ISchoolOwnedService");
                    boolean _result = isSchoolOwned();
                    reply.writeNoException();
                    reply.writeInt(_result ? 1 : 0);
                    return true;
                case 1598968902:
                    reply.writeString("com.google.android.nfcprovision.ISchoolOwnedService");
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }
    }

    boolean isSchoolOwned() throws RemoteException;
}
