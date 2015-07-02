package com.android.vending.billing;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.wallet.instrumentmanager.R;
import java.util.List;

public interface IInAppBillingService extends IInterface {

    public static abstract class Stub extends Binder implements IInAppBillingService {
        public Stub() {
            attachInterface(this, "com.android.vending.billing.IInAppBillingService");
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            int _result;
            int _arg0;
            String _arg1;
            Bundle _result2;
            switch (code) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    data.enforceInterface("com.android.vending.billing.IInAppBillingService");
                    _result = isBillingSupported(data.readInt(), data.readString(), data.readString());
                    reply.writeNoException();
                    reply.writeInt(_result);
                    return true;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    Bundle _arg3;
                    data.enforceInterface("com.android.vending.billing.IInAppBillingService");
                    _arg0 = data.readInt();
                    _arg1 = data.readString();
                    String _arg2 = data.readString();
                    if (data.readInt() != 0) {
                        _arg3 = (Bundle) Bundle.CREATOR.createFromParcel(data);
                    } else {
                        _arg3 = null;
                    }
                    _result2 = getSkuDetails(_arg0, _arg1, _arg2, _arg3);
                    reply.writeNoException();
                    if (_result2 != null) {
                        reply.writeInt(1);
                        _result2.writeToParcel(reply, 1);
                    } else {
                        reply.writeInt(0);
                    }
                    return true;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    data.enforceInterface("com.android.vending.billing.IInAppBillingService");
                    _result2 = getBuyIntent(data.readInt(), data.readString(), data.readString(), data.readString(), data.readString());
                    reply.writeNoException();
                    if (_result2 != null) {
                        reply.writeInt(1);
                        _result2.writeToParcel(reply, 1);
                    } else {
                        reply.writeInt(0);
                    }
                    return true;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    data.enforceInterface("com.android.vending.billing.IInAppBillingService");
                    _result2 = getPurchases(data.readInt(), data.readString(), data.readString(), data.readString());
                    reply.writeNoException();
                    if (_result2 != null) {
                        reply.writeInt(1);
                        _result2.writeToParcel(reply, 1);
                    } else {
                        reply.writeInt(0);
                    }
                    return true;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    data.enforceInterface("com.android.vending.billing.IInAppBillingService");
                    _result = consumePurchase(data.readInt(), data.readString(), data.readString());
                    reply.writeNoException();
                    reply.writeInt(_result);
                    return true;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    data.enforceInterface("com.android.vending.billing.IInAppBillingService");
                    _result = isPromoEligible(data.readInt(), data.readString(), data.readString());
                    reply.writeNoException();
                    reply.writeInt(_result);
                    return true;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                    data.enforceInterface("com.android.vending.billing.IInAppBillingService");
                    _arg0 = data.readInt();
                    _arg1 = data.readString();
                    List<String> _arg22 = data.createStringArrayList();
                    String _arg32 = data.readString();
                    String readString = data.readString();
                    _result2 = getBuyIntentToReplaceSkus(_arg0, _arg1, _arg22, _arg32, _arg4, data.readString());
                    reply.writeNoException();
                    if (_result2 != null) {
                        reply.writeInt(1);
                        _result2.writeToParcel(reply, 1);
                    } else {
                        reply.writeInt(0);
                    }
                    return true;
                case 1598968902:
                    reply.writeString("com.android.vending.billing.IInAppBillingService");
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }
    }

    int consumePurchase(int i, String str, String str2) throws RemoteException;

    Bundle getBuyIntent(int i, String str, String str2, String str3, String str4) throws RemoteException;

    Bundle getBuyIntentToReplaceSkus(int i, String str, List<String> list, String str2, String str3, String str4) throws RemoteException;

    Bundle getPurchases(int i, String str, String str2, String str3) throws RemoteException;

    Bundle getSkuDetails(int i, String str, String str2, Bundle bundle) throws RemoteException;

    int isBillingSupported(int i, String str, String str2) throws RemoteException;

    int isPromoEligible(int i, String str, String str2) throws RemoteException;
}
