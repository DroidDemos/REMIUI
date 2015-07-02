package com.google.android.gms.appdatasearch;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class ab implements Creator<RequestIndexingSpecification> {
    static void a(RequestIndexingSpecification requestIndexingSpecification, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1000, requestIndexingSpecification.mVersionCode);
        b.J(parcel, bU);
    }

    public RequestIndexingSpecification L(Parcel parcel) {
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
            return new RequestIndexingSpecification(i);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public RequestIndexingSpecification[] af(int i) {
        return new RequestIndexingSpecification[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return L(x0);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return af(x0);
    }
}
