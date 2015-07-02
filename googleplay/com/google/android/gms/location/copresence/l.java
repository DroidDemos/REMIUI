package com.google.android.gms.location.copresence;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;
import java.util.List;

public class l implements Creator<People> {
    static void a(People people, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, people.getVersionCode());
        b.c(parcel, 2, people.getPersonIds(), false);
        b.c(parcel, 3, people.getGroup());
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return fu(x0);
    }

    public People fu(Parcel parcel) {
        int i = 0;
        int bT = a.bT(parcel);
        List list = null;
        int i2 = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i2 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    list = a.E(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    i = a.g(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new People(i2, list, i);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public People[] hL(int i) {
        return new People[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return hL(x0);
    }
}
