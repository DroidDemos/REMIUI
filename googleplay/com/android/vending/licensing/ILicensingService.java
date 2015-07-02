package com.android.vending.licensing;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.wallet.instrumentmanager.R;

public interface ILicensingService extends IInterface {

    public static abstract class Stub extends Binder implements ILicensingService {
        public Stub() {
            attachInterface(this, "com.android.vending.licensing.ILicensingService");
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            switch (code) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    data.enforceInterface("com.android.vending.licensing.ILicensingService");
                    checkLicense(data.readLong(), data.readString(), com.android.vending.licensing.ILicenseResultListener.Stub.asInterface(data.readStrongBinder()));
                    return true;
                case 1598968902:
                    reply.writeString("com.android.vending.licensing.ILicensingService");
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }
    }

    void checkLicense(long j, String str, ILicenseResultListener iLicenseResultListener) throws RemoteException;
}
