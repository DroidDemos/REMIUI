package com.android.vending.licensing;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.wallet.instrumentmanager.R;

public interface ILicenseResultListener extends IInterface {

    public static abstract class Stub extends Binder implements ILicenseResultListener {

        private static class Proxy implements ILicenseResultListener {
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public void verifyLicense(int responseCode, String signedData, String signature) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("com.android.vending.licensing.ILicenseResultListener");
                    _data.writeInt(responseCode);
                    _data.writeString(signedData);
                    _data.writeString(signature);
                    this.mRemote.transact(1, _data, null, 1);
                } finally {
                    _data.recycle();
                }
            }
        }

        public static ILicenseResultListener asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface("com.android.vending.licensing.ILicenseResultListener");
            if (iin == null || !(iin instanceof ILicenseResultListener)) {
                return new Proxy(obj);
            }
            return (ILicenseResultListener) iin;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            switch (code) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    data.enforceInterface("com.android.vending.licensing.ILicenseResultListener");
                    verifyLicense(data.readInt(), data.readString(), data.readString());
                    return true;
                case 1598968902:
                    reply.writeString("com.android.vending.licensing.ILicenseResultListener");
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }
    }

    void verifyLicense(int i, String str, String str2) throws RemoteException;
}
