package com.google.android.play;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.wallet.instrumentmanager.R;
import java.util.List;

public interface IUserContentService extends IInterface {

    public static abstract class Stub extends Binder implements IUserContentService {

        private static class Proxy implements IUserContentService {
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public List<Bundle> getDocuments(int dataTypeToFetch, int numItemsToReturn) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("com.google.android.play.IUserContentService");
                    _data.writeInt(dataTypeToFetch);
                    _data.writeInt(numItemsToReturn);
                    this.mRemote.transact(1, _data, _reply, 0);
                    _reply.readException();
                    List<Bundle> _result = _reply.createTypedArrayList(Bundle.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static IUserContentService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface("com.google.android.play.IUserContentService");
            if (iin == null || !(iin instanceof IUserContentService)) {
                return new Proxy(obj);
            }
            return (IUserContentService) iin;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            switch (code) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    data.enforceInterface("com.google.android.play.IUserContentService");
                    List<Bundle> _result = getDocuments(data.readInt(), data.readInt());
                    reply.writeNoException();
                    reply.writeTypedList(_result);
                    return true;
                case 1598968902:
                    reply.writeString("com.google.android.play.IUserContentService");
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }
    }

    List<Bundle> getDocuments(int i, int i2) throws RemoteException;
}
