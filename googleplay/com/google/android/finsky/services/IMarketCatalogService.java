package com.google.android.finsky.services;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.wallet.instrumentmanager.R;

public interface IMarketCatalogService extends IInterface {

    public static abstract class Stub extends Binder implements IMarketCatalogService {
        public Stub() {
            attachInterface(this, "com.google.android.finsky.services.IMarketCatalogService");
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            switch (code) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    data.enforceInterface("com.google.android.finsky.services.IMarketCatalogService");
                    boolean _result = isBackendEnabled(data.readString(), data.readInt());
                    reply.writeNoException();
                    reply.writeInt(_result ? 1 : 0);
                    return true;
                case 1598968902:
                    reply.writeString("com.google.android.finsky.services.IMarketCatalogService");
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }
    }

    boolean isBackendEnabled(String str, int i) throws RemoteException;
}
