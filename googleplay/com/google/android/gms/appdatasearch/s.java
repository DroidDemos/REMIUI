package com.google.android.gms.appdatasearch;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class s implements Creator<PIMEUpdateResponse> {
    static void a(PIMEUpdateResponse pIMEUpdateResponse, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.a(parcel, 1, pIMEUpdateResponse.mErrorMessage, false);
        b.c(parcel, 1000, pIMEUpdateResponse.mVersionCode);
        b.a(parcel, 2, pIMEUpdateResponse.nextIterToken, false);
        b.a(parcel, 3, pIMEUpdateResponse.updates, i, false);
        b.J(parcel, bU);
    }

    public PIMEUpdateResponse C(Parcel parcel) {
        PIMEUpdate[] pIMEUpdateArr = null;
        int bT = a.bT(parcel);
        int i = 0;
        byte[] bArr = null;
        String str = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    str = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    bArr = a.s(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    pIMEUpdateArr = (PIMEUpdate[]) a.b(parcel, bS, PIMEUpdate.CREATOR);
                    break;
                case 1000:
                    i = a.g(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new PIMEUpdateResponse(i, str, bArr, pIMEUpdateArr);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public PIMEUpdateResponse[] W(int i) {
        return new PIMEUpdateResponse[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return C(x0);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return W(x0);
    }
}
