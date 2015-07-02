package com.google.android.gms.search.corpora;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.appdatasearch.RegisterCorpusInfo;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.search.corpora.GetCorpusInfoCall.Response;
import com.google.android.wallet.instrumentmanager.R;

public class d implements Creator<Response> {
    static void a(Response response, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1000, response.mVersionCode);
        b.a(parcel, 1, response.status, i, false);
        b.a(parcel, 2, response.info, i, false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return hw(x0);
    }

    public Response hw(Parcel parcel) {
        RegisterCorpusInfo registerCorpusInfo = null;
        int bT = a.bT(parcel);
        int i = 0;
        Status status = null;
        while (parcel.dataPosition() < bT) {
            int i2;
            RegisterCorpusInfo registerCorpusInfo2;
            Status status2;
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i2 = i;
                    Status status3 = (Status) a.a(parcel, bS, Status.CREATOR);
                    registerCorpusInfo2 = registerCorpusInfo;
                    status2 = status3;
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    registerCorpusInfo2 = (RegisterCorpusInfo) a.a(parcel, bS, RegisterCorpusInfo.CREATOR);
                    status2 = status;
                    i2 = i;
                    break;
                case 1000:
                    RegisterCorpusInfo registerCorpusInfo3 = registerCorpusInfo;
                    status2 = status;
                    i2 = a.g(parcel, bS);
                    registerCorpusInfo2 = registerCorpusInfo3;
                    break;
                default:
                    a.b(parcel, bS);
                    registerCorpusInfo2 = registerCorpusInfo;
                    status2 = status;
                    i2 = i;
                    break;
            }
            i = i2;
            status = status2;
            registerCorpusInfo = registerCorpusInfo2;
        }
        if (parcel.dataPosition() == bT) {
            return new Response(i, status, registerCorpusInfo);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public Response[] kh(int i) {
        return new Response[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return kh(x0);
    }
}
