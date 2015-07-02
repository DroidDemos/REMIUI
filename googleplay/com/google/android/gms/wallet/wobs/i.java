package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class i implements Creator<WalletObjectMessage> {
    static void a(WalletObjectMessage walletObjectMessage, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, walletObjectMessage.getVersionCode());
        b.a(parcel, 2, walletObjectMessage.aVY, false);
        b.a(parcel, 3, walletObjectMessage.tU, false);
        b.a(parcel, 4, walletObjectMessage.aWb, i, false);
        b.a(parcel, 5, walletObjectMessage.aWc, i, false);
        b.a(parcel, 6, walletObjectMessage.aWd, i, false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return iC(x0);
    }

    public WalletObjectMessage iC(Parcel parcel) {
        UriData uriData = null;
        int bT = a.bT(parcel);
        int i = 0;
        UriData uriData2 = null;
        TimeInterval timeInterval = null;
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
                    timeInterval = (TimeInterval) a.a(parcel, bS, TimeInterval.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    uriData2 = (UriData) a.a(parcel, bS, UriData.CREATOR);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    uriData = (UriData) a.a(parcel, bS, UriData.CREATOR);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new WalletObjectMessage(i, str2, str, timeInterval, uriData2, uriData);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public WalletObjectMessage[] lC(int i) {
        return new WalletObjectMessage[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return lC(x0);
    }
}
