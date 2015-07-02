package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class am implements Creator<al> {
    static void a(al alVar, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, alVar.mVersionCode);
        b.a(parcel, 2, alVar.getId(), false);
        b.a(parcel, 3, alVar.getDisplayName(), false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return iR(x0);
    }

    public al iR(Parcel parcel) {
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
            return new al(i, str2, str);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public al[] lR(int i) {
        return new al[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return lR(x0);
    }
}
