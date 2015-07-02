package com.google.android.gms.search.global;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.search.global.GetGlobalSearchSourcesCall.Request;
import com.google.android.wallet.instrumentmanager.R;

public class e implements Creator<Request> {
    static void a(Request request, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1000, request.mVersionCode);
        b.a(parcel, 1, request.wantDisabledSources);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return hF(x0);
    }

    public Request hF(Parcel parcel) {
        boolean z = false;
        int bT = a.bT(parcel);
        int i = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
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
            return new Request(i, z);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public Request[] kq(int i) {
        return new Request[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return kq(x0);
    }
}
