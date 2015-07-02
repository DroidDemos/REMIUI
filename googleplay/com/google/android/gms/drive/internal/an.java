package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class an implements Creator<OnDownloadProgressResponse> {
    static void a(OnDownloadProgressResponse onDownloadProgressResponse, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, onDownloadProgressResponse.mVersionCode);
        b.a(parcel, 2, onDownloadProgressResponse.aaV);
        b.a(parcel, 3, onDownloadProgressResponse.aaW);
        b.J(parcel, bU);
    }

    public OnDownloadProgressResponse cH(Parcel parcel) {
        long j = 0;
        int bT = a.bT(parcel);
        int i = 0;
        long j2 = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    j2 = a.i(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    j = a.i(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new OnDownloadProgressResponse(i, j2, j);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return cH(x0);
    }

    public OnDownloadProgressResponse[] ep(int i) {
        return new OnDownloadProgressResponse[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return ep(x0);
    }
}
