package com.mipay.sdk;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IMipayResponse extends IInterface {

    public static abstract class Stub extends Binder implements IMipayResponse {
        private static final String DESCRIPTOR = "com.mipay.sdk.IMipayResponse";
        static final int TRANSACTION_onError = 2;
        static final int TRANSACTION_onResult = 1;

        private static class Proxy implements IMipayResponse {
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            public void onResult(Bundle value) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (value != null) {
                        _data.writeInt(Stub.TRANSACTION_onResult);
                        value.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_onResult, _data, null, Stub.TRANSACTION_onResult);
                } finally {
                    _data.recycle();
                }
            }

            public void onError(int errorCode, String errorMessage, Bundle value) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(errorCode);
                    _data.writeString(errorMessage);
                    if (value != null) {
                        _data.writeInt(Stub.TRANSACTION_onResult);
                        value.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_onError, _data, null, Stub.TRANSACTION_onResult);
                } finally {
                    _data.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IMipayResponse asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IMipayResponse)) {
                return new Proxy(obj);
            }
            return (IMipayResponse) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            switch (code) {
                case TRANSACTION_onResult /*1*/:
                    Bundle _arg0;
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg0 = (Bundle) Bundle.CREATOR.createFromParcel(data);
                    } else {
                        _arg0 = null;
                    }
                    onResult(_arg0);
                    return true;
                case TRANSACTION_onError /*2*/:
                    Bundle _arg2;
                    data.enforceInterface(DESCRIPTOR);
                    int _arg02 = data.readInt();
                    String _arg1 = data.readString();
                    if (data.readInt() != 0) {
                        _arg2 = (Bundle) Bundle.CREATOR.createFromParcel(data);
                    } else {
                        _arg2 = null;
                    }
                    onError(_arg02, _arg1, _arg2);
                    return true;
                case 1598968902:
                    reply.writeString(DESCRIPTOR);
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }
    }

    void onError(int i, String str, Bundle bundle) throws RemoteException;

    void onResult(Bundle bundle) throws RemoteException;
}
