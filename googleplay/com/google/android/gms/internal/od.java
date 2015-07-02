package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.common.people.data.Audience;
import com.google.android.wallet.instrumentmanager.R;

public class od implements Creator<oc> {
    static void a(oc ocVar, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.a(parcel, 1, ocVar.getName(), false);
        b.c(parcel, 1000, ocVar.getVersionCode());
        b.a(parcel, 2, ocVar.getAcl(), i, false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return fA(x0);
    }

    public oc fA(Parcel parcel) {
        Audience audience = null;
        int bT = a.bT(parcel);
        int i = 0;
        String str = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    str = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    audience = (Audience) a.a(parcel, bS, Audience.CREATOR);
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
            return new oc(i, str, audience);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public oc[] hR(int i) {
        return new oc[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return hR(x0);
    }
}
