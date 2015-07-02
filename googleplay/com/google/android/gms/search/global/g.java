package com.google.android.gms.search.global;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.search.global.GetPendingExperimentIdsCall.Request;

public class g implements Creator<Request> {
    static void a(Request request, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1000, request.mVersionCode);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return hH(x0);
    }

    public Request hH(Parcel parcel) {
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
            return new Request(i);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public Request[] ks(int i) {
        return new Request[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return ks(x0);
    }
}
