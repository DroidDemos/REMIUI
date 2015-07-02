package com.google.android.gms.appdatasearch;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.appdatasearch.GetRecentContextCall.Response;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;
import java.util.ArrayList;
import java.util.List;

public class j implements Creator<Response> {
    static void a(Response response, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1000, response.mVersionCode);
        b.a(parcel, 1, response.status, i, false);
        b.d(parcel, 2, response.context, false);
        b.J(parcel, bU);
    }

    public Response[] O(int i) {
        return new Response[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return u(x0);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return O(x0);
    }

    public Response u(Parcel parcel) {
        List list = null;
        int bT = a.bT(parcel);
        int i = 0;
        Status status = null;
        while (parcel.dataPosition() < bT) {
            int i2;
            Status status2;
            ArrayList c;
            int bS = a.bS(parcel);
            List list2;
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i2 = i;
                    Status status3 = (Status) a.a(parcel, bS, Status.CREATOR);
                    list2 = list;
                    status2 = status3;
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    c = a.c(parcel, bS, UsageInfo.CREATOR);
                    status2 = status;
                    i2 = i;
                    break;
                case 1000:
                    List list3 = list;
                    status2 = status;
                    i2 = a.g(parcel, bS);
                    list2 = list3;
                    break;
                default:
                    a.b(parcel, bS);
                    c = list;
                    status2 = status;
                    i2 = i;
                    break;
            }
            i = i2;
            status = status2;
            Object obj = c;
        }
        if (parcel.dataPosition() == bT) {
            return new Response(i, status, list);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }
}
