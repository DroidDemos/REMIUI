package com.google.android.finsky.services;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.wallet.instrumentmanager.R;

public interface IPlayGearheadService extends IInterface {

    public static abstract class Stub extends Binder implements IPlayGearheadService {
        public Stub() {
            attachInterface(this, "com.google.android.finsky.services.IPlayGearheadService");
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            switch (code) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    data.enforceInterface("com.google.android.finsky.services.IPlayGearheadService");
                    Bundle _result = validatePackageAcquiredByPlay(data.readString());
                    reply.writeNoException();
                    if (_result != null) {
                        reply.writeInt(1);
                        _result.writeToParcel(reply, 1);
                        return true;
                    }
                    reply.writeInt(0);
                    return true;
                case 1598968902:
                    reply.writeString("com.google.android.finsky.services.IPlayGearheadService");
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }
    }

    Bundle validatePackageAcquiredByPlay(String str) throws RemoteException;
}
