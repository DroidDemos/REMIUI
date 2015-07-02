package com.android.vending.billing;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.wallet.instrumentmanager.R;
import java.util.List;

public interface IFirstPartyInAppBillingService extends IInterface {

    public static abstract class Stub extends Binder implements IFirstPartyInAppBillingService {
        public Stub() {
            attachInterface(this, "com.android.vending.billing.IFirstPartyInAppBillingService");
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            int _result;
            String _arg0;
            int _arg1;
            String _arg2;
            Bundle _result2;
            switch (code) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    data.enforceInterface("com.android.vending.billing.IFirstPartyInAppBillingService");
                    _result = isBillingSupported(data.readString(), data.readInt(), data.readString(), data.readString());
                    reply.writeNoException();
                    reply.writeInt(_result);
                    return true;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    Bundle _arg4;
                    data.enforceInterface("com.android.vending.billing.IFirstPartyInAppBillingService");
                    _arg0 = data.readString();
                    _arg1 = data.readInt();
                    _arg2 = data.readString();
                    String _arg3 = data.readString();
                    if (data.readInt() != 0) {
                        _arg4 = (Bundle) Bundle.CREATOR.createFromParcel(data);
                    } else {
                        _arg4 = null;
                    }
                    _result2 = getSkuDetails(_arg0, _arg1, _arg2, _arg3, _arg4);
                    reply.writeNoException();
                    if (_result2 != null) {
                        reply.writeInt(1);
                        _result2.writeToParcel(reply, 1);
                    } else {
                        reply.writeInt(0);
                    }
                    return true;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    data.enforceInterface("com.android.vending.billing.IFirstPartyInAppBillingService");
                    _result2 = getBuyIntent(data.readString(), data.readInt(), data.readString(), data.readString(), data.readString(), data.readString());
                    reply.writeNoException();
                    if (_result2 != null) {
                        reply.writeInt(1);
                        _result2.writeToParcel(reply, 1);
                    } else {
                        reply.writeInt(0);
                    }
                    return true;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    data.enforceInterface("com.android.vending.billing.IFirstPartyInAppBillingService");
                    _result2 = getPurchases(data.readString(), data.readInt(), data.readString(), data.readString(), data.readString());
                    reply.writeNoException();
                    if (_result2 != null) {
                        reply.writeInt(1);
                        _result2.writeToParcel(reply, 1);
                    } else {
                        reply.writeInt(0);
                    }
                    return true;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    data.enforceInterface("com.android.vending.billing.IFirstPartyInAppBillingService");
                    _result = consumePurchase(data.readString(), data.readInt(), data.readString(), data.readString());
                    reply.writeNoException();
                    reply.writeInt(_result);
                    return true;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    data.enforceInterface("com.android.vending.billing.IFirstPartyInAppBillingService");
                    _result = isPromoEligible(data.readString(), data.readInt(), data.readString(), data.readString());
                    reply.writeNoException();
                    reply.writeInt(_result);
                    return true;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                    data.enforceInterface("com.android.vending.billing.IFirstPartyInAppBillingService");
                    _arg0 = data.readString();
                    _arg1 = data.readInt();
                    _arg2 = data.readString();
                    List<String> _arg32 = data.createStringArrayList();
                    String _arg42 = data.readString();
                    String readString = data.readString();
                    _result2 = getBuyIntentToReplaceSkus(_arg0, _arg1, _arg2, _arg32, _arg42, _arg5, data.readString());
                    reply.writeNoException();
                    if (_result2 != null) {
                        reply.writeInt(1);
                        _result2.writeToParcel(reply, 1);
                    } else {
                        reply.writeInt(0);
                    }
                    return true;
                case 1598968902:
                    reply.writeString("com.android.vending.billing.IFirstPartyInAppBillingService");
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }
    }

    int consumePurchase(String str, int i, String str2, String str3) throws RemoteException;

    Bundle getBuyIntent(String str, int i, String str2, String str3, String str4, String str5) throws RemoteException;

    Bundle getBuyIntentToReplaceSkus(String str, int i, String str2, List<String> list, String str3, String str4, String str5) throws RemoteException;

    Bundle getPurchases(String str, int i, String str2, String str3, String str4) throws RemoteException;

    Bundle getSkuDetails(String str, int i, String str2, String str3, Bundle bundle) throws RemoteException;

    int isBillingSupported(String str, int i, String str2, String str3) throws RemoteException;

    int isPromoEligible(String str, int i, String str2, String str3) throws RemoteException;
}
