package com.google.android.gms.search.queries;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.appdatasearch.PhraseAffinityResponse;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.search.queries.GetPhraseAffinityCall.Response;
import com.google.android.wallet.instrumentmanager.R;

public class d implements Creator<Response> {
    static void a(Response response, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1000, response.mVersionCode);
        b.a(parcel, 1, response.status, i, false);
        b.a(parcel, 2, response.affinity, i, false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return hO(x0);
    }

    public Response hO(Parcel parcel) {
        PhraseAffinityResponse phraseAffinityResponse = null;
        int bT = a.bT(parcel);
        int i = 0;
        Status status = null;
        while (parcel.dataPosition() < bT) {
            int i2;
            PhraseAffinityResponse phraseAffinityResponse2;
            Status status2;
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i2 = i;
                    Status status3 = (Status) a.a(parcel, bS, Status.CREATOR);
                    phraseAffinityResponse2 = phraseAffinityResponse;
                    status2 = status3;
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    phraseAffinityResponse2 = (PhraseAffinityResponse) a.a(parcel, bS, PhraseAffinityResponse.CREATOR);
                    status2 = status;
                    i2 = i;
                    break;
                case 1000:
                    PhraseAffinityResponse phraseAffinityResponse3 = phraseAffinityResponse;
                    status2 = status;
                    i2 = a.g(parcel, bS);
                    phraseAffinityResponse2 = phraseAffinityResponse3;
                    break;
                default:
                    a.b(parcel, bS);
                    phraseAffinityResponse2 = phraseAffinityResponse;
                    status2 = status;
                    i2 = i;
                    break;
            }
            i = i2;
            status = status2;
            phraseAffinityResponse = phraseAffinityResponse2;
        }
        if (parcel.dataPosition() == bT) {
            return new Response(i, status, phraseAffinityResponse);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public Response[] kz(int i) {
        return new Response[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return kz(x0);
    }
}
