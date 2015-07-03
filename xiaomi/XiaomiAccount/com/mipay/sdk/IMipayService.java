package com.mipay.sdk;

import android.accounts.Account;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IMipayService extends IInterface {

    public static abstract class Stub extends Binder implements IMipayService {
        private static final String DESCRIPTOR = "com.mipay.sdk.IMipayService";
        static final int TRANSACTION_pay = 1;

        private static class Proxy implements IMipayService {
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

            public void pay(IMipayResponse response, Account account, String order, Bundle extra) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(response != null ? response.asBinder() : null);
                    if (account != null) {
                        _data.writeInt(Stub.TRANSACTION_pay);
                        account.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeString(order);
                    if (extra != null) {
                        _data.writeInt(Stub.TRANSACTION_pay);
                        extra.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_pay, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IMipayService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IMipayService)) {
                return new Proxy(obj);
            }
            return (IMipayService) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            switch (code) {
                case TRANSACTION_pay /*1*/:
                    Account _arg1;
                    Bundle _arg3;
                    data.enforceInterface(DESCRIPTOR);
                    IMipayResponse _arg0 = com.mipay.sdk.IMipayResponse.Stub.asInterface(data.readStrongBinder());
                    if (data.readInt() != 0) {
                        _arg1 = (Account) Account.CREATOR.createFromParcel(data);
                    } else {
                        _arg1 = null;
                    }
                    String _arg2 = data.readString();
                    if (data.readInt() != 0) {
                        _arg3 = (Bundle) Bundle.CREATOR.createFromParcel(data);
                    } else {
                        _arg3 = null;
                    }
                    pay(_arg0, _arg1, _arg2, _arg3);
                    reply.writeNoException();
                    return true;
                case 1598968902:
                    reply.writeString(DESCRIPTOR);
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }
    }

    void pay(IMipayResponse iMipayResponse, Account account, String str, Bundle bundle) throws RemoteException;
}
