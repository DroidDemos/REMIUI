package com.xiaomi.accounts;

import android.accounts.Account;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IAccountAuthenticator extends IInterface {

    public static abstract class Stub extends Binder implements IAccountAuthenticator {
        private static final String DESCRIPTOR = "com.xiaomi.accounts.IAccountAuthenticator";
        static final int TRANSACTION_addAccount = 1;
        static final int TRANSACTION_confirmCredentials = 2;
        static final int TRANSACTION_editProperties = 6;
        static final int TRANSACTION_getAccountRemovalAllowed = 8;
        static final int TRANSACTION_getAuthToken = 3;
        static final int TRANSACTION_getAuthTokenLabel = 4;
        static final int TRANSACTION_hasFeatures = 7;
        static final int TRANSACTION_updateCredentials = 5;

        private static class Proxy implements IAccountAuthenticator {
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

            public void addAccount(IAccountAuthenticatorResponse response, String accountType, String authTokenType, String[] requiredFeatures, Bundle options) throws RemoteException {
                IBinder iBinder = null;
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (response != null) {
                        iBinder = response.asBinder();
                    }
                    _data.writeStrongBinder(iBinder);
                    _data.writeString(accountType);
                    _data.writeString(authTokenType);
                    _data.writeStringArray(requiredFeatures);
                    if (options != null) {
                        _data.writeInt(Stub.TRANSACTION_addAccount);
                        options.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_addAccount, _data, null, Stub.TRANSACTION_addAccount);
                } finally {
                    _data.recycle();
                }
            }

            public void confirmCredentials(IAccountAuthenticatorResponse response, Account account, Bundle options) throws RemoteException {
                IBinder iBinder = null;
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (response != null) {
                        iBinder = response.asBinder();
                    }
                    _data.writeStrongBinder(iBinder);
                    if (account != null) {
                        _data.writeInt(Stub.TRANSACTION_addAccount);
                        account.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (options != null) {
                        _data.writeInt(Stub.TRANSACTION_addAccount);
                        options.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_confirmCredentials, _data, null, Stub.TRANSACTION_addAccount);
                } finally {
                    _data.recycle();
                }
            }

            public void getAuthToken(IAccountAuthenticatorResponse response, Account account, String authTokenType, Bundle options) throws RemoteException {
                IBinder iBinder = null;
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (response != null) {
                        iBinder = response.asBinder();
                    }
                    _data.writeStrongBinder(iBinder);
                    if (account != null) {
                        _data.writeInt(Stub.TRANSACTION_addAccount);
                        account.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeString(authTokenType);
                    if (options != null) {
                        _data.writeInt(Stub.TRANSACTION_addAccount);
                        options.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_getAuthToken, _data, null, Stub.TRANSACTION_addAccount);
                } finally {
                    _data.recycle();
                }
            }

            public void getAuthTokenLabel(IAccountAuthenticatorResponse response, String authTokenType) throws RemoteException {
                IBinder iBinder = null;
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (response != null) {
                        iBinder = response.asBinder();
                    }
                    _data.writeStrongBinder(iBinder);
                    _data.writeString(authTokenType);
                    this.mRemote.transact(Stub.TRANSACTION_getAuthTokenLabel, _data, null, Stub.TRANSACTION_addAccount);
                } finally {
                    _data.recycle();
                }
            }

            public void updateCredentials(IAccountAuthenticatorResponse response, Account account, String authTokenType, Bundle options) throws RemoteException {
                IBinder iBinder = null;
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (response != null) {
                        iBinder = response.asBinder();
                    }
                    _data.writeStrongBinder(iBinder);
                    if (account != null) {
                        _data.writeInt(Stub.TRANSACTION_addAccount);
                        account.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeString(authTokenType);
                    if (options != null) {
                        _data.writeInt(Stub.TRANSACTION_addAccount);
                        options.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_updateCredentials, _data, null, Stub.TRANSACTION_addAccount);
                } finally {
                    _data.recycle();
                }
            }

            public void editProperties(IAccountAuthenticatorResponse response, String accountType) throws RemoteException {
                IBinder iBinder = null;
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (response != null) {
                        iBinder = response.asBinder();
                    }
                    _data.writeStrongBinder(iBinder);
                    _data.writeString(accountType);
                    this.mRemote.transact(Stub.TRANSACTION_editProperties, _data, null, Stub.TRANSACTION_addAccount);
                } finally {
                    _data.recycle();
                }
            }

            public void hasFeatures(IAccountAuthenticatorResponse response, Account account, String[] features) throws RemoteException {
                IBinder iBinder = null;
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (response != null) {
                        iBinder = response.asBinder();
                    }
                    _data.writeStrongBinder(iBinder);
                    if (account != null) {
                        _data.writeInt(Stub.TRANSACTION_addAccount);
                        account.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeStringArray(features);
                    this.mRemote.transact(Stub.TRANSACTION_hasFeatures, _data, null, Stub.TRANSACTION_addAccount);
                } finally {
                    _data.recycle();
                }
            }

            public void getAccountRemovalAllowed(IAccountAuthenticatorResponse response, Account account) throws RemoteException {
                IBinder iBinder = null;
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (response != null) {
                        iBinder = response.asBinder();
                    }
                    _data.writeStrongBinder(iBinder);
                    if (account != null) {
                        _data.writeInt(Stub.TRANSACTION_addAccount);
                        account.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_getAccountRemovalAllowed, _data, null, Stub.TRANSACTION_addAccount);
                } finally {
                    _data.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IAccountAuthenticator asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IAccountAuthenticator)) {
                return new Proxy(obj);
            }
            return (IAccountAuthenticator) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            IAccountAuthenticatorResponse _arg0;
            String _arg2;
            Account _arg1;
            Bundle _arg3;
            switch (code) {
                case TRANSACTION_addAccount /*1*/:
                    Bundle _arg4;
                    data.enforceInterface(DESCRIPTOR);
                    _arg0 = com.xiaomi.accounts.IAccountAuthenticatorResponse.Stub.asInterface(data.readStrongBinder());
                    String _arg12 = data.readString();
                    _arg2 = data.readString();
                    String[] _arg32 = data.createStringArray();
                    if (data.readInt() != 0) {
                        _arg4 = (Bundle) Bundle.CREATOR.createFromParcel(data);
                    } else {
                        _arg4 = null;
                    }
                    addAccount(_arg0, _arg12, _arg2, _arg32, _arg4);
                    return true;
                case TRANSACTION_confirmCredentials /*2*/:
                    Bundle _arg22;
                    data.enforceInterface(DESCRIPTOR);
                    _arg0 = com.xiaomi.accounts.IAccountAuthenticatorResponse.Stub.asInterface(data.readStrongBinder());
                    if (data.readInt() != 0) {
                        _arg1 = (Account) Account.CREATOR.createFromParcel(data);
                    } else {
                        _arg1 = null;
                    }
                    if (data.readInt() != 0) {
                        _arg22 = (Bundle) Bundle.CREATOR.createFromParcel(data);
                    } else {
                        _arg22 = null;
                    }
                    confirmCredentials(_arg0, _arg1, _arg22);
                    return true;
                case TRANSACTION_getAuthToken /*3*/:
                    data.enforceInterface(DESCRIPTOR);
                    _arg0 = com.xiaomi.accounts.IAccountAuthenticatorResponse.Stub.asInterface(data.readStrongBinder());
                    if (data.readInt() != 0) {
                        _arg1 = (Account) Account.CREATOR.createFromParcel(data);
                    } else {
                        _arg1 = null;
                    }
                    _arg2 = data.readString();
                    if (data.readInt() != 0) {
                        _arg3 = (Bundle) Bundle.CREATOR.createFromParcel(data);
                    } else {
                        _arg3 = null;
                    }
                    getAuthToken(_arg0, _arg1, _arg2, _arg3);
                    return true;
                case TRANSACTION_getAuthTokenLabel /*4*/:
                    data.enforceInterface(DESCRIPTOR);
                    getAuthTokenLabel(com.xiaomi.accounts.IAccountAuthenticatorResponse.Stub.asInterface(data.readStrongBinder()), data.readString());
                    return true;
                case TRANSACTION_updateCredentials /*5*/:
                    data.enforceInterface(DESCRIPTOR);
                    _arg0 = com.xiaomi.accounts.IAccountAuthenticatorResponse.Stub.asInterface(data.readStrongBinder());
                    if (data.readInt() != 0) {
                        _arg1 = (Account) Account.CREATOR.createFromParcel(data);
                    } else {
                        _arg1 = null;
                    }
                    _arg2 = data.readString();
                    if (data.readInt() != 0) {
                        _arg3 = (Bundle) Bundle.CREATOR.createFromParcel(data);
                    } else {
                        _arg3 = null;
                    }
                    updateCredentials(_arg0, _arg1, _arg2, _arg3);
                    return true;
                case TRANSACTION_editProperties /*6*/:
                    data.enforceInterface(DESCRIPTOR);
                    editProperties(com.xiaomi.accounts.IAccountAuthenticatorResponse.Stub.asInterface(data.readStrongBinder()), data.readString());
                    return true;
                case TRANSACTION_hasFeatures /*7*/:
                    data.enforceInterface(DESCRIPTOR);
                    _arg0 = com.xiaomi.accounts.IAccountAuthenticatorResponse.Stub.asInterface(data.readStrongBinder());
                    if (data.readInt() != 0) {
                        _arg1 = (Account) Account.CREATOR.createFromParcel(data);
                    } else {
                        _arg1 = null;
                    }
                    hasFeatures(_arg0, _arg1, data.createStringArray());
                    return true;
                case TRANSACTION_getAccountRemovalAllowed /*8*/:
                    data.enforceInterface(DESCRIPTOR);
                    _arg0 = com.xiaomi.accounts.IAccountAuthenticatorResponse.Stub.asInterface(data.readStrongBinder());
                    if (data.readInt() != 0) {
                        _arg1 = (Account) Account.CREATOR.createFromParcel(data);
                    } else {
                        _arg1 = null;
                    }
                    getAccountRemovalAllowed(_arg0, _arg1);
                    return true;
                case 1598968902:
                    reply.writeString(DESCRIPTOR);
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }
    }

    void addAccount(IAccountAuthenticatorResponse iAccountAuthenticatorResponse, String str, String str2, String[] strArr, Bundle bundle) throws RemoteException;

    void confirmCredentials(IAccountAuthenticatorResponse iAccountAuthenticatorResponse, Account account, Bundle bundle) throws RemoteException;

    void editProperties(IAccountAuthenticatorResponse iAccountAuthenticatorResponse, String str) throws RemoteException;

    void getAccountRemovalAllowed(IAccountAuthenticatorResponse iAccountAuthenticatorResponse, Account account) throws RemoteException;

    void getAuthToken(IAccountAuthenticatorResponse iAccountAuthenticatorResponse, Account account, String str, Bundle bundle) throws RemoteException;

    void getAuthTokenLabel(IAccountAuthenticatorResponse iAccountAuthenticatorResponse, String str) throws RemoteException;

    void hasFeatures(IAccountAuthenticatorResponse iAccountAuthenticatorResponse, Account account, String[] strArr) throws RemoteException;

    void updateCredentials(IAccountAuthenticatorResponse iAccountAuthenticatorResponse, Account account, String str, Bundle bundle) throws RemoteException;
}
