package com.android.vending.billing;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.wallet.instrumentmanager.R;

public interface IBillingAccountService extends IInterface {

    public static abstract class Stub extends Binder implements IBillingAccountService {
        public Stub() {
            attachInterface(this, "com.android.vending.billing.IBillingAccountService");
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            switch (code) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    data.enforceInterface("com.android.vending.billing.IBillingAccountService");
                    int _result = hasValidCreditCard(data.readString());
                    reply.writeNoException();
                    reply.writeInt(_result);
                    return true;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    data.enforceInterface("com.android.vending.billing.IBillingAccountService");
                    Bundle _result2 = getOffers(data.readString());
                    reply.writeNoException();
                    if (_result2 != null) {
                        reply.writeInt(1);
                        _result2.writeToParcel(reply, 1);
                        return true;
                    }
                    reply.writeInt(0);
                    return true;
                case 1598968902:
                    reply.writeString("com.android.vending.billing.IBillingAccountService");
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }
    }

    Bundle getOffers(String str) throws RemoteException;

    int hasValidCreditCard(String str) throws RemoteException;
}
