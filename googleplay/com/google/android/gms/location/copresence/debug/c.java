package com.google.android.gms.location.copresence.debug;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class c implements Creator<b> {
    static void a(b bVar, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1000, bVar.getVersionCode());
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return fx(x0);
    }

    public b fx(Parcel parcel) {
        int bT = a.bT(parcel);
        int i = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case 1000:
                    i = a.g(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new b(i);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public b[] hO(int i) {
        return new b[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return hO(x0);
    }
}
