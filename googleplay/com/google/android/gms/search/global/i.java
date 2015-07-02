package com.google.android.gms.search.global;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.search.global.SetExperimentIdsCall.Request;
import com.google.android.wallet.instrumentmanager.R;

public class i implements Creator<Request> {
    static void a(Request request, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1000, request.mVersionCode);
        b.a(parcel, 1, request.serializedExperimentConfig, false);
        b.a(parcel, 2, request.emergency);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return hJ(x0);
    }

    public Request hJ(Parcel parcel) {
        boolean z = false;
        int bT = a.bT(parcel);
        byte[] bArr = null;
        int i = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    bArr = a.s(parcel, bS);
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
            return new Request(i, bArr, z);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public Request[] ku(int i) {
        return new Request[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return ku(x0);
    }
}
