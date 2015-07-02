package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class j implements Creator<MatchAllFilter> {
    static void a(MatchAllFilter matchAllFilter, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1000, matchAllFilter.mVersionCode);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return do(x0);
    }

    public MatchAllFilter do(Parcel parcel) {
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
            return new MatchAllFilter(i);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public MatchAllFilter[] eW(int i) {
        return new MatchAllFilter[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return eW(x0);
    }
}
