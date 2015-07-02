package com.google.android.gms.fitness.request;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class k implements Creator<j> {
    static void a(j jVar, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.a(parcel, 1, jVar.getName(), false);
        b.c(parcel, 1000, jVar.getVersionCode());
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return eg(x0);
    }

    public j eg(Parcel parcel) {
        int bT = a.bT(parcel);
        int i = 0;
        String str = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    str = a.p(parcel, bS);
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
            return new j(i, str);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public j[] fU(int i) {
        return new j[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return fU(x0);
    }
}
