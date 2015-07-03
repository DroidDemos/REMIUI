package com.xiaomi.accounts;

import android.accounts.Account;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IAccountManagerService extends IInterface {

    public static abstract class Stub extends Binder implements IAccountManagerService {
        private static final String DESCRIPTOR = "com.xiaomi.accounts.IAccountManagerService";
        static final int TRANSACTION_addAccount = 6;
        static final int TRANSACTION_addAcount = 16;
        static final int TRANSACTION_clearPassword = 12;
        static final int TRANSACTION_confirmCredentials = 19;
        static final int TRANSACTION_editProperties = 18;
        static final int TRANSACTION_getAccounts = 3;
        static final int TRANSACTION_getAccountsByFeatures = 5;
        static final int TRANSACTION_getAuthToken = 15;
        static final int TRANSACTION_getAuthTokenLabel = 20;
        static final int TRANSACTION_getPassword = 1;
        static final int TRANSACTION_getUserData = 2;
        static final int TRANSACTION_hasFeatures = 4;
        static final int TRANSACTION_invalidateAuthToken = 8;
        static final int TRANSACTION_peekAuthToken = 9;
        static final int TRANSACTION_removeAccount = 7;
        static final int TRANSACTION_setAuthToken = 10;
        static final int TRANSACTION_setPassword = 11;
        static final int TRANSACTION_setUserData = 13;
        static final int TRANSACTION_updateAppPermission = 14;
        static final int TRANSACTION_updateCredentials = 17;

        private static class Proxy implements IAccountManagerService {
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

            public String getPassword(Account account) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (account != null) {
                        _data.writeInt(Stub.TRANSACTION_getPassword);
                        account.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_getPassword, _data, _reply, 0);
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public String getUserData(Account account, String key) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (account != null) {
                        _data.writeInt(Stub.TRANSACTION_getPassword);
                        account.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeString(key);
                    this.mRemote.transact(Stub.TRANSACTION_getUserData, _data, _reply, 0);
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public Account[] getAccounts(String accountType) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(accountType);
                    this.mRemote.transact(Stub.TRANSACTION_getAccounts, _data, _reply, 0);
                    _reply.readException();
                    Account[] _result = (Account[]) _reply.createTypedArray(Account.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void hasFeatures(IAccountManagerResponse response, Account account, String[] features) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(response != null ? response.asBinder() : null);
                    if (account != null) {
                        _data.writeInt(Stub.TRANSACTION_getPassword);
                        account.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeStringArray(features);
                    this.mRemote.transact(Stub.TRANSACTION_hasFeatures, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void getAccountsByFeatures(IAccountManagerResponse response, String accountType, String[] features) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(response != null ? response.asBinder() : null);
                    _data.writeString(accountType);
                    _data.writeStringArray(features);
                    this.mRemote.transact(Stub.TRANSACTION_getAccountsByFeatures, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public boolean addAccount(Account account, String password, Bundle extras) throws RemoteException {
                boolean _result = true;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (account != null) {
                        _data.writeInt(Stub.TRANSACTION_getPassword);
                        account.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeString(password);
                    if (extras != null) {
                        _data.writeInt(Stub.TRANSACTION_getPassword);
                        extras.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_addAccount, _data, _reply, 0);
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

            public void removeAccount(IAccountManagerResponse response, Account account) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(response != null ? response.asBinder() : null);
                    if (account != null) {
                        _data.writeInt(Stub.TRANSACTION_getPassword);
                        account.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_removeAccount, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void invalidateAuthToken(String accountType, String authToken) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(accountType);
                    _data.writeString(authToken);
                    this.mRemote.transact(Stub.TRANSACTION_invalidateAuthToken, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public String peekAuthToken(Account account, String authTokenType) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (account != null) {
                        _data.writeInt(Stub.TRANSACTION_getPassword);
                        account.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeString(authTokenType);
                    this.mRemote.transact(Stub.TRANSACTION_peekAuthToken, _data, _reply, 0);
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void setAuthToken(Account account, String authTokenType, String authToken) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (account != null) {
                        _data.writeInt(Stub.TRANSACTION_getPassword);
                        account.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeString(authTokenType);
                    _data.writeString(authToken);
                    this.mRemote.transact(Stub.TRANSACTION_setAuthToken, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void setPassword(Account account, String password) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (account != null) {
                        _data.writeInt(Stub.TRANSACTION_getPassword);
                        account.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeString(password);
                    this.mRemote.transact(Stub.TRANSACTION_setPassword, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void clearPassword(Account account) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (account != null) {
                        _data.writeInt(Stub.TRANSACTION_getPassword);
                        account.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_clearPassword, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void setUserData(Account account, String key, String value) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (account != null) {
                        _data.writeInt(Stub.TRANSACTION_getPassword);
                        account.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeString(key);
                    _data.writeString(value);
                    this.mRemote.transact(Stub.TRANSACTION_setUserData, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void updateAppPermission(Account account, String authTokenType, int uid, boolean value) throws RemoteException {
                int i = Stub.TRANSACTION_getPassword;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (account != null) {
                        _data.writeInt(Stub.TRANSACTION_getPassword);
                        account.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeString(authTokenType);
                    _data.writeInt(uid);
                    if (!value) {
                        i = 0;
                    }
                    _data.writeInt(i);
                    this.mRemote.transact(Stub.TRANSACTION_updateAppPermission, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void getAuthToken(IAccountManagerResponse response, Account account, String authTokenType, boolean notifyOnAuthFailure, boolean expectActivityLaunch, Bundle options) throws RemoteException {
                int i = Stub.TRANSACTION_getPassword;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(response != null ? response.asBinder() : null);
                    if (account != null) {
                        _data.writeInt(Stub.TRANSACTION_getPassword);
                        account.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeString(authTokenType);
                    _data.writeInt(notifyOnAuthFailure ? Stub.TRANSACTION_getPassword : 0);
                    if (!expectActivityLaunch) {
                        i = 0;
                    }
                    _data.writeInt(i);
                    if (options != null) {
                        _data.writeInt(Stub.TRANSACTION_getPassword);
                        options.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_getAuthToken, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void addAcount(IAccountManagerResponse response, String accountType, String authTokenType, String[] requiredFeatures, boolean expectActivityLaunch, Bundle options) throws RemoteException {
                int i = Stub.TRANSACTION_getPassword;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(response != null ? response.asBinder() : null);
                    _data.writeString(accountType);
                    _data.writeString(authTokenType);
                    _data.writeStringArray(requiredFeatures);
                    if (!expectActivityLaunch) {
                        i = 0;
                    }
                    _data.writeInt(i);
                    if (options != null) {
                        _data.writeInt(Stub.TRANSACTION_getPassword);
                        options.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_addAcount, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void updateCredentials(IAccountManagerResponse response, Account account, String authTokenType, boolean expectActivityLaunch, Bundle options) throws RemoteException {
                int i = Stub.TRANSACTION_getPassword;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(response != null ? response.asBinder() : null);
                    if (account != null) {
                        _data.writeInt(Stub.TRANSACTION_getPassword);
                        account.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeString(authTokenType);
                    if (!expectActivityLaunch) {
                        i = 0;
                    }
                    _data.writeInt(i);
                    if (options != null) {
                        _data.writeInt(Stub.TRANSACTION_getPassword);
                        options.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_updateCredentials, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void editProperties(IAccountManagerResponse response, String accountType, boolean expectActivityLaunch) throws RemoteException {
                int i = 0;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(response != null ? response.asBinder() : null);
                    _data.writeString(accountType);
                    if (expectActivityLaunch) {
                        i = Stub.TRANSACTION_getPassword;
                    }
                    _data.writeInt(i);
                    this.mRemote.transact(Stub.TRANSACTION_editProperties, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void confirmCredentials(IAccountManagerResponse response, Account account, Bundle options, boolean expectActivityLaunch) throws RemoteException {
                int i = Stub.TRANSACTION_getPassword;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(response != null ? response.asBinder() : null);
                    if (account != null) {
                        _data.writeInt(Stub.TRANSACTION_getPassword);
                        account.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (options != null) {
                        _data.writeInt(Stub.TRANSACTION_getPassword);
                        options.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (!expectActivityLaunch) {
                        i = 0;
                    }
                    _data.writeInt(i);
                    this.mRemote.transact(Stub.TRANSACTION_confirmCredentials, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void getAuthTokenLabel(IAccountManagerResponse response, String accountType, String authTokenType) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(response != null ? response.asBinder() : null);
                    _data.writeString(accountType);
                    _data.writeString(authTokenType);
                    this.mRemote.transact(Stub.TRANSACTION_getAuthTokenLabel, _data, _reply, 0);
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

        public static IAccountManagerService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IAccountManagerService)) {
                return new Proxy(obj);
            }
            return (IAccountManagerService) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            int i = 0;
            Account _arg0;
            String _result;
            IAccountManagerResponse _arg02;
            Account _arg1;
            String _arg12;
            Bundle _arg2;
            boolean _arg3;
            String _arg22;
            boolean _arg4;
            Bundle _arg5;
            switch (code) {
                case TRANSACTION_getPassword /*1*/:
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg0 = (Account) Account.CREATOR.createFromParcel(data);
                    } else {
                        _arg0 = null;
                    }
                    _result = getPassword(_arg0);
                    reply.writeNoException();
                    reply.writeString(_result);
                    return true;
                case TRANSACTION_getUserData /*2*/:
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg0 = (Account) Account.CREATOR.createFromParcel(data);
                    } else {
                        _arg0 = null;
                    }
                    _result = getUserData(_arg0, data.readString());
                    reply.writeNoException();
                    reply.writeString(_result);
                    return true;
                case TRANSACTION_getAccounts /*3*/:
                    data.enforceInterface(DESCRIPTOR);
                    Account[] _result2 = getAccounts(data.readString());
                    reply.writeNoException();
                    reply.writeTypedArray(_result2, TRANSACTION_getPassword);
                    return true;
                case TRANSACTION_hasFeatures /*4*/:
                    data.enforceInterface(DESCRIPTOR);
                    _arg02 = com.xiaomi.accounts.IAccountManagerResponse.Stub.asInterface(data.readStrongBinder());
                    if (data.readInt() != 0) {
                        _arg1 = (Account) Account.CREATOR.createFromParcel(data);
                    } else {
                        _arg1 = null;
                    }
                    hasFeatures(_arg02, _arg1, data.createStringArray());
                    reply.writeNoException();
                    return true;
                case TRANSACTION_getAccountsByFeatures /*5*/:
                    data.enforceInterface(DESCRIPTOR);
                    getAccountsByFeatures(com.xiaomi.accounts.IAccountManagerResponse.Stub.asInterface(data.readStrongBinder()), data.readString(), data.createStringArray());
                    reply.writeNoException();
                    return true;
                case TRANSACTION_addAccount /*6*/:
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg0 = (Account) Account.CREATOR.createFromParcel(data);
                    } else {
                        _arg0 = null;
                    }
                    _arg12 = data.readString();
                    if (data.readInt() != 0) {
                        _arg2 = (Bundle) Bundle.CREATOR.createFromParcel(data);
                    } else {
                        _arg2 = null;
                    }
                    boolean _result3 = addAccount(_arg0, _arg12, _arg2);
                    reply.writeNoException();
                    if (_result3) {
                        i = TRANSACTION_getPassword;
                    }
                    reply.writeInt(i);
                    return true;
                case TRANSACTION_removeAccount /*7*/:
                    data.enforceInterface(DESCRIPTOR);
                    _arg02 = com.xiaomi.accounts.IAccountManagerResponse.Stub.asInterface(data.readStrongBinder());
                    if (data.readInt() != 0) {
                        _arg1 = (Account) Account.CREATOR.createFromParcel(data);
                    } else {
                        _arg1 = null;
                    }
                    removeAccount(_arg02, _arg1);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_invalidateAuthToken /*8*/:
                    data.enforceInterface(DESCRIPTOR);
                    invalidateAuthToken(data.readString(), data.readString());
                    reply.writeNoException();
                    return true;
                case TRANSACTION_peekAuthToken /*9*/:
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg0 = (Account) Account.CREATOR.createFromParcel(data);
                    } else {
                        _arg0 = null;
                    }
                    _result = peekAuthToken(_arg0, data.readString());
                    reply.writeNoException();
                    reply.writeString(_result);
                    return true;
                case TRANSACTION_setAuthToken /*10*/:
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg0 = (Account) Account.CREATOR.createFromParcel(data);
                    } else {
                        _arg0 = null;
                    }
                    setAuthToken(_arg0, data.readString(), data.readString());
                    reply.writeNoException();
                    return true;
                case TRANSACTION_setPassword /*11*/:
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg0 = (Account) Account.CREATOR.createFromParcel(data);
                    } else {
                        _arg0 = null;
                    }
                    setPassword(_arg0, data.readString());
                    reply.writeNoException();
                    return true;
                case TRANSACTION_clearPassword /*12*/:
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg0 = (Account) Account.CREATOR.createFromParcel(data);
                    } else {
                        _arg0 = null;
                    }
                    clearPassword(_arg0);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_setUserData /*13*/:
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg0 = (Account) Account.CREATOR.createFromParcel(data);
                    } else {
                        _arg0 = null;
                    }
                    setUserData(_arg0, data.readString(), data.readString());
                    reply.writeNoException();
                    return true;
                case TRANSACTION_updateAppPermission /*14*/:
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg0 = (Account) Account.CREATOR.createFromParcel(data);
                    } else {
                        _arg0 = null;
                    }
                    _arg12 = data.readString();
                    int _arg23 = data.readInt();
                    if (data.readInt() != 0) {
                        _arg3 = true;
                    } else {
                        _arg3 = false;
                    }
                    updateAppPermission(_arg0, _arg12, _arg23, _arg3);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_getAuthToken /*15*/:
                    data.enforceInterface(DESCRIPTOR);
                    _arg02 = com.xiaomi.accounts.IAccountManagerResponse.Stub.asInterface(data.readStrongBinder());
                    if (data.readInt() != 0) {
                        _arg1 = (Account) Account.CREATOR.createFromParcel(data);
                    } else {
                        _arg1 = null;
                    }
                    _arg22 = data.readString();
                    if (data.readInt() != 0) {
                        _arg3 = true;
                    } else {
                        _arg3 = false;
                    }
                    if (data.readInt() != 0) {
                        _arg4 = true;
                    } else {
                        _arg4 = false;
                    }
                    if (data.readInt() != 0) {
                        _arg5 = (Bundle) Bundle.CREATOR.createFromParcel(data);
                    } else {
                        _arg5 = null;
                    }
                    getAuthToken(_arg02, _arg1, _arg22, _arg3, _arg4, _arg5);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_addAcount /*16*/:
                    data.enforceInterface(DESCRIPTOR);
                    _arg02 = com.xiaomi.accounts.IAccountManagerResponse.Stub.asInterface(data.readStrongBinder());
                    _arg12 = data.readString();
                    _arg22 = data.readString();
                    String[] _arg32 = data.createStringArray();
                    if (data.readInt() != 0) {
                        _arg4 = true;
                    } else {
                        _arg4 = false;
                    }
                    if (data.readInt() != 0) {
                        _arg5 = (Bundle) Bundle.CREATOR.createFromParcel(data);
                    } else {
                        _arg5 = null;
                    }
                    addAcount(_arg02, _arg12, _arg22, _arg32, _arg4, _arg5);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_updateCredentials /*17*/:
                    Bundle _arg42;
                    data.enforceInterface(DESCRIPTOR);
                    _arg02 = com.xiaomi.accounts.IAccountManagerResponse.Stub.asInterface(data.readStrongBinder());
                    if (data.readInt() != 0) {
                        _arg1 = (Account) Account.CREATOR.createFromParcel(data);
                    } else {
                        _arg1 = null;
                    }
                    _arg22 = data.readString();
                    if (data.readInt() != 0) {
                        _arg3 = true;
                    } else {
                        _arg3 = false;
                    }
                    if (data.readInt() != 0) {
                        _arg42 = (Bundle) Bundle.CREATOR.createFromParcel(data);
                    } else {
                        _arg42 = null;
                    }
                    updateCredentials(_arg02, _arg1, _arg22, _arg3, _arg42);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_editProperties /*18*/:
                    boolean _arg24;
                    data.enforceInterface(DESCRIPTOR);
                    _arg02 = com.xiaomi.accounts.IAccountManagerResponse.Stub.asInterface(data.readStrongBinder());
                    _arg12 = data.readString();
                    if (data.readInt() != 0) {
                        _arg24 = true;
                    } else {
                        _arg24 = false;
                    }
                    editProperties(_arg02, _arg12, _arg24);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_confirmCredentials /*19*/:
                    data.enforceInterface(DESCRIPTOR);
                    _arg02 = com.xiaomi.accounts.IAccountManagerResponse.Stub.asInterface(data.readStrongBinder());
                    if (data.readInt() != 0) {
                        _arg1 = (Account) Account.CREATOR.createFromParcel(data);
                    } else {
                        _arg1 = null;
                    }
                    if (data.readInt() != 0) {
                        _arg2 = (Bundle) Bundle.CREATOR.createFromParcel(data);
                    } else {
                        _arg2 = null;
                    }
                    if (data.readInt() != 0) {
                        _arg3 = true;
                    } else {
                        _arg3 = false;
                    }
                    confirmCredentials(_arg02, _arg1, _arg2, _arg3);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_getAuthTokenLabel /*20*/:
                    data.enforceInterface(DESCRIPTOR);
                    getAuthTokenLabel(com.xiaomi.accounts.IAccountManagerResponse.Stub.asInterface(data.readStrongBinder()), data.readString(), data.readString());
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

    boolean addAccount(Account account, String str, Bundle bundle) throws RemoteException;

    void addAcount(IAccountManagerResponse iAccountManagerResponse, String str, String str2, String[] strArr, boolean z, Bundle bundle) throws RemoteException;

    void clearPassword(Account account) throws RemoteException;

    void confirmCredentials(IAccountManagerResponse iAccountManagerResponse, Account account, Bundle bundle, boolean z) throws RemoteException;

    void editProperties(IAccountManagerResponse iAccountManagerResponse, String str, boolean z) throws RemoteException;

    Account[] getAccounts(String str) throws RemoteException;

    void getAccountsByFeatures(IAccountManagerResponse iAccountManagerResponse, String str, String[] strArr) throws RemoteException;

    void getAuthToken(IAccountManagerResponse iAccountManagerResponse, Account account, String str, boolean z, boolean z2, Bundle bundle) throws RemoteException;

    void getAuthTokenLabel(IAccountManagerResponse iAccountManagerResponse, String str, String str2) throws RemoteException;

    String getPassword(Account account) throws RemoteException;

    String getUserData(Account account, String str) throws RemoteException;

    void hasFeatures(IAccountManagerResponse iAccountManagerResponse, Account account, String[] strArr) throws RemoteException;

    void invalidateAuthToken(String str, String str2) throws RemoteException;

    String peekAuthToken(Account account, String str) throws RemoteException;

    void removeAccount(IAccountManagerResponse iAccountManagerResponse, Account account) throws RemoteException;

    void setAuthToken(Account account, String str, String str2) throws RemoteException;

    void setPassword(Account account, String str) throws RemoteException;

    void setUserData(Account account, String str, String str2) throws RemoteException;

    void updateAppPermission(Account account, String str, int i, boolean z) throws RemoteException;

    void updateCredentials(IAccountManagerResponse iAccountManagerResponse, Account account, String str, boolean z, Bundle bundle) throws RemoteException;
}
