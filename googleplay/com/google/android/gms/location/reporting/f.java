package com.google.android.gms.location.reporting;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class f implements Creator<UploadRequestResult> {
    static void a(UploadRequestResult uploadRequestResult, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, uploadRequestResult.getVersionCode());
        b.c(parcel, 2, uploadRequestResult.getResultCode());
        b.a(parcel, 3, uploadRequestResult.getRequestId());
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return gf(x0);
    }

    public UploadRequestResult gf(Parcel parcel) {
        int i = 0;
        int bT = a.bT(parcel);
        long j = 0;
        int i2 = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i2 = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    i = a.g(parcel, bS);
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
            return new UploadRequestResult(i2, i, j);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public UploadRequestResult[] iA(int i) {
        return new UploadRequestResult[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return iA(x0);
    }
}
