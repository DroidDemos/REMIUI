package com.google.android.gms.search.global;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.search.global.GetPendingExperimentIdsCall.Response;
import com.google.android.wallet.instrumentmanager.R;

public class h implements Creator<Response> {
    static void a(Response response, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1000, response.mVersionCode);
        b.a(parcel, 1, response.status, i, false);
        b.a(parcel, 2, response.experimentIds, false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return hI(x0);
    }

    public Response hI(Parcel parcel) {
        int[] iArr = null;
        int bT = a.bT(parcel);
        int i = 0;
        Status status = null;
        while (parcel.dataPosition() < bT) {
            int i2;
            int[] iArr2;
            Status status2;
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i2 = i;
                    Status status3 = (Status) a.a(parcel, bS, Status.CREATOR);
                    iArr2 = iArr;
                    status2 = status3;
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    iArr2 = a.v(parcel, bS);
                    status2 = status;
                    i2 = i;
                    break;
                case 1000:
                    int[] iArr3 = iArr;
                    status2 = status;
                    i2 = a.g(parcel, bS);
                    iArr2 = iArr3;
                    break;
                default:
                    a.b(parcel, bS);
                    iArr2 = iArr;
                    status2 = status;
                    i2 = i;
                    break;
            }
            i = i2;
            status = status2;
            iArr = iArr2;
        }
        if (parcel.dataPosition() == bT) {
            return new Response(i, status, iArr);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public Response[] kt(int i) {
        return new Response[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return kt(x0);
    }
}
