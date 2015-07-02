package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class f implements Creator<TextModuleData> {
    static void a(TextModuleData textModuleData, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, textModuleData.getVersionCode());
        b.a(parcel, 2, textModuleData.aVY, false);
        b.a(parcel, 3, textModuleData.tU, false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return iz(x0);
    }

    public TextModuleData iz(Parcel parcel) {
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
            return new TextModuleData(i, str2, str);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public TextModuleData[] lz(int i) {
        return new TextModuleData[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return lz(x0);
    }
}
