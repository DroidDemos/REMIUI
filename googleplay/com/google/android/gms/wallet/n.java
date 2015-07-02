package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.wallet.wobs.CommonWalletObject;
import com.google.android.wallet.instrumentmanager.R;

public class n implements Creator<OfferWalletObject> {
    static void a(OfferWalletObject offerWalletObject, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, offerWalletObject.getVersionCode());
        b.a(parcel, 2, offerWalletObject.fl, false);
        b.a(parcel, 3, offerWalletObject.aUr, false);
        b.a(parcel, 4, offerWalletObject.aUs, i, false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return ij(x0);
    }

    public OfferWalletObject ij(Parcel parcel) {
        CommonWalletObject commonWalletObject = null;
        int bT = a.bT(parcel);
        int i = 0;
        String str = null;
        String str2 = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    str2 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    str = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    commonWalletObject = (CommonWalletObject) a.a(parcel, bS, CommonWalletObject.CREATOR);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new OfferWalletObject(i, str2, str, commonWalletObject);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public OfferWalletObject[] le(int i) {
        return new OfferWalletObject[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return le(x0);
    }
}
