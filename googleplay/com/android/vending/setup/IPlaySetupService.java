package com.android.vending.setup;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.wallet.instrumentmanager.R;

public interface IPlaySetupService extends IInterface {

    public static abstract class Stub extends Binder implements IPlaySetupService {
        public Stub() {
            attachInterface(this, "com.android.vending.setup.IPlaySetupService");
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            int i = 0;
            Bundle _result;
            switch (code) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    data.enforceInterface("com.android.vending.setup.IPlaySetupService");
                    _result = getEarlyUpdate();
                    reply.writeNoException();
                    if (_result != null) {
                        reply.writeInt(1);
                        _result.writeToParcel(reply, 1);
                        return true;
                    }
                    reply.writeInt(0);
                    return true;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    data.enforceInterface("com.android.vending.setup.IPlaySetupService");
                    startEarlyUpdate();
                    reply.writeNoException();
                    return true;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    data.enforceInterface("com.android.vending.setup.IPlaySetupService");
                    boolean _result2 = cancelEarlyUpdate();
                    reply.writeNoException();
                    if (_result2) {
                        i = 1;
                    }
                    reply.writeInt(i);
                    return true;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    data.enforceInterface("com.android.vending.setup.IPlaySetupService");
                    _result = getRestoreFlow(data.readString());
                    reply.writeNoException();
                    if (_result != null) {
                        reply.writeInt(1);
                        _result.writeToParcel(reply, 1);
                        return true;
                    }
                    reply.writeInt(0);
                    return true;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    data.enforceInterface("com.android.vending.setup.IPlaySetupService");
                    startVpa();
                    reply.writeNoException();
                    return true;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    data.enforceInterface("com.android.vending.setup.IPlaySetupService");
                    _result = getFinalHoldFlow();
                    reply.writeNoException();
                    if (_result != null) {
                        reply.writeInt(1);
                        _result.writeToParcel(reply, 1);
                        return true;
                    }
                    reply.writeInt(0);
                    return true;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                    data.enforceInterface("com.android.vending.setup.IPlaySetupService");
                    startDownloads();
                    reply.writeNoException();
                    return true;
                case 1598968902:
                    reply.writeString("com.android.vending.setup.IPlaySetupService");
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }
    }

    boolean cancelEarlyUpdate() throws RemoteException;

    Bundle getEarlyUpdate() throws RemoteException;

    Bundle getFinalHoldFlow() throws RemoteException;

    Bundle getRestoreFlow(String str) throws RemoteException;

    void startDownloads() throws RemoteException;

    void startEarlyUpdate() throws RemoteException;

    void startVpa() throws RemoteException;
}
