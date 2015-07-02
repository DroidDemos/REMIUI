package com.google.android.gms.wallet.firstparty;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.wallet.instrumentmanager.R;

public class b implements Creator<GetInstrumentsResponse> {
    static void a(GetInstrumentsResponse getInstrumentsResponse, Parcel parcel, int i) {
        int bU = com.google.android.gms.common.internal.safeparcel.b.bU(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, getInstrumentsResponse.getVersionCode());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, getInstrumentsResponse.aUC, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, getInstrumentsResponse.aUD, false);
        com.google.android.gms.common.internal.safeparcel.b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return im(x0);
    }

    public GetInstrumentsResponse im(Parcel parcel) {
        String[] strArr = null;
        int bT = a.bT(parcel);
        int i = 0;
        byte[][] bArr = (byte[][]) null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    strArr = a.B(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    bArr = a.t(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new GetInstrumentsResponse(i, strArr, bArr);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public GetInstrumentsResponse[] lh(int i) {
        return new GetInstrumentsResponse[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return lh(x0);
    }
}
