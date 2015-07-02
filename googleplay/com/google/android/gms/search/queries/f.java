package com.google.android.gms.search.queries;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.appdatasearch.SearchResults;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.search.queries.GlobalQueryCall.Response;
import com.google.android.wallet.instrumentmanager.R;

public class f implements Creator<Response> {
    static void a(Response response, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1000, response.mVersionCode);
        b.a(parcel, 1, response.status, i, false);
        b.a(parcel, 2, response.documents, i, false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return hQ(x0);
    }

    public Response hQ(Parcel parcel) {
        SearchResults searchResults = null;
        int bT = a.bT(parcel);
        int i = 0;
        Status status = null;
        while (parcel.dataPosition() < bT) {
            int i2;
            SearchResults searchResults2;
            Status status2;
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i2 = i;
                    Status status3 = (Status) a.a(parcel, bS, Status.CREATOR);
                    searchResults2 = searchResults;
                    status2 = status3;
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    searchResults2 = (SearchResults) a.a(parcel, bS, SearchResults.CREATOR);
                    status2 = status;
                    i2 = i;
                    break;
                case 1000:
                    SearchResults searchResults3 = searchResults;
                    status2 = status;
                    i2 = a.g(parcel, bS);
                    searchResults2 = searchResults3;
                    break;
                default:
                    a.b(parcel, bS);
                    searchResults2 = searchResults;
                    status2 = status;
                    i2 = i;
                    break;
            }
            i = i2;
            status = status2;
            searchResults = searchResults2;
        }
        if (parcel.dataPosition() == bT) {
            return new Response(i, status, searchResults);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public Response[] kB(int i) {
        return new Response[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return kB(x0);
    }
}
