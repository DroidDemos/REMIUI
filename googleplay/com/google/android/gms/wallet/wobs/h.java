package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class h implements Creator<UriData> {
    static void a(UriData uriData, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, uriData.getVersionCode());
        b.a(parcel, 2, uriData.aRz, false);
        b.a(parcel, 3, uriData.description, false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return iB(x0);
    }

    public UriData iB(Parcel parcel) {
        String str = null;
        int bT = a.bT(parcel);
        int i = 0;
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
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new UriData(i, str2, str);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public UriData[] lB(int i) {
        return new UriData[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return lB(x0);
    }
}
