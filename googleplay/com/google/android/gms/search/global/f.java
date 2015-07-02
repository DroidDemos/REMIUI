package com.google.android.gms.search.global;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.search.global.GetGlobalSearchSourcesCall.GlobalSearchSource;
import com.google.android.gms.search.global.GetGlobalSearchSourcesCall.Response;
import com.google.android.wallet.instrumentmanager.R;

public class f implements Creator<Response> {
    static void a(Response response, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1000, response.mVersionCode);
        b.a(parcel, 1, response.status, i, false);
        b.a(parcel, 2, response.sources, i, false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return hG(x0);
    }

    public Response hG(Parcel parcel) {
        GlobalSearchSource[] globalSearchSourceArr = null;
        int bT = a.bT(parcel);
        int i = 0;
        Status status = null;
        while (parcel.dataPosition() < bT) {
            int i2;
            GlobalSearchSource[] globalSearchSourceArr2;
            Status status2;
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i2 = i;
                    Status status3 = (Status) a.a(parcel, bS, Status.CREATOR);
                    globalSearchSourceArr2 = globalSearchSourceArr;
                    status2 = status3;
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    globalSearchSourceArr2 = (GlobalSearchSource[]) a.b(parcel, bS, GlobalSearchSource.CREATOR);
                    status2 = status;
                    i2 = i;
                    break;
                case 1000:
                    GlobalSearchSource[] globalSearchSourceArr3 = globalSearchSourceArr;
                    status2 = status;
                    i2 = a.g(parcel, bS);
                    globalSearchSourceArr2 = globalSearchSourceArr3;
                    break;
                default:
                    a.b(parcel, bS);
                    globalSearchSourceArr2 = globalSearchSourceArr;
                    status2 = status;
                    i2 = i;
                    break;
            }
            i = i2;
            status = status2;
            globalSearchSourceArr = globalSearchSourceArr2;
        }
        if (parcel.dataPosition() == bT) {
            return new Response(i, status, globalSearchSourceArr);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public Response[] kr(int i) {
        return new Response[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return kr(x0);
    }
}
