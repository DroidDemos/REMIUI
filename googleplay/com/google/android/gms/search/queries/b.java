package com.google.android.gms.search.queries;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.appdatasearch.DocumentResults;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.search.queries.GetDocumentsCall.Response;
import com.google.android.wallet.instrumentmanager.R;

public class b implements Creator<Response> {
    static void a(Response response, Parcel parcel, int i) {
        int bU = com.google.android.gms.common.internal.safeparcel.b.bU(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1000, response.mVersionCode);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 1, response.status, i, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, response.documents, i, false);
        com.google.android.gms.common.internal.safeparcel.b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return hM(x0);
    }

    public Response hM(Parcel parcel) {
        DocumentResults documentResults = null;
        int bT = a.bT(parcel);
        int i = 0;
        Status status = null;
        while (parcel.dataPosition() < bT) {
            int i2;
            DocumentResults documentResults2;
            Status status2;
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i2 = i;
                    Status status3 = (Status) a.a(parcel, bS, Status.CREATOR);
                    documentResults2 = documentResults;
                    status2 = status3;
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    documentResults2 = (DocumentResults) a.a(parcel, bS, DocumentResults.CREATOR);
                    status2 = status;
                    i2 = i;
                    break;
                case 1000:
                    DocumentResults documentResults3 = documentResults;
                    status2 = status;
                    i2 = a.g(parcel, bS);
                    documentResults2 = documentResults3;
                    break;
                default:
                    a.b(parcel, bS);
                    documentResults2 = documentResults;
                    status2 = status;
                    i2 = i;
                    break;
            }
            i = i2;
            status = status2;
            documentResults = documentResults2;
        }
        if (parcel.dataPosition() == bT) {
            return new Response(i, status, documentResults);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public Response[] kx(int i) {
        return new Response[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return kx(x0);
    }
}
