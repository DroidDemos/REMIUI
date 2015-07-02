package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class ob implements Creator<oa> {
    static void a(oa oaVar, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.a(parcel, 1, oaVar.getId(), false);
        b.c(parcel, 1000, oaVar.getVersionCode());
        b.a(parcel, 2, oaVar.isOptedIn());
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return fz(x0);
    }

    public oa fz(Parcel parcel) {
        boolean z = false;
        int bT = a.bT(parcel);
        String str = null;
        int i = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    str = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    z = a.c(parcel, bS);
                    break;
                case 1000:
                    i = a.g(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new oa(i, str, z);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public oa[] hQ(int i) {
        return new oa[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return hQ(x0);
    }
}
