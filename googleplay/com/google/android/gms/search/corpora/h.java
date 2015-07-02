package com.google.android.gms.search.corpora;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.search.corpora.RequestIndexingCall.Response;
import com.google.android.wallet.instrumentmanager.R;

public class h implements Creator<Response> {
    static void a(Response response, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1000, response.mVersionCode);
        b.a(parcel, 1, response.status, i, false);
        b.a(parcel, 2, response.scheduled);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return hA(x0);
    }

    public Response hA(Parcel parcel) {
        boolean z = false;
        int bT = a.bT(parcel);
        Status status = null;
        int i = 0;
        while (parcel.dataPosition() < bT) {
            int i2;
            boolean z2;
            Status status2;
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i2 = i;
                    Status status3 = (Status) a.a(parcel, bS, Status.CREATOR);
                    z2 = z;
                    status2 = status3;
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    z2 = a.c(parcel, bS);
                    status2 = status;
                    i2 = i;
                    break;
                case 1000:
                    boolean z3 = z;
                    status2 = status;
                    i2 = a.g(parcel, bS);
                    z2 = z3;
                    break;
                default:
                    a.b(parcel, bS);
                    z2 = z;
                    status2 = status;
                    i2 = i;
                    break;
            }
            i = i2;
            status = status2;
            z = z2;
        }
        if (parcel.dataPosition() == bT) {
            return new Response(i, status, z);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public Response[] kl(int i) {
        return new Response[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return kl(x0);
    }
}
