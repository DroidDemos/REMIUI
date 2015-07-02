package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.drive.query.Query;
import com.google.android.wallet.instrumentmanager.R;

public class bb implements Creator<QueryRequest> {
    static void a(QueryRequest queryRequest, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, queryRequest.mVersionCode);
        b.a(parcel, 2, queryRequest.abd, i, false);
        b.J(parcel, bU);
    }

    public QueryRequest cU(Parcel parcel) {
        int bT = a.bT(parcel);
        int i = 0;
        Query query = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    query = (Query) a.a(parcel, bS, Query.CREATOR);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new QueryRequest(i, query);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return cU(x0);
    }

    public QueryRequest[] eC(int i) {
        return new QueryRequest[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return eC(x0);
    }
}
