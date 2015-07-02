package com.google.android.gms.wallet.firstparty;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class a implements Creator<GetInstrumentsRequest> {
    static void a(GetInstrumentsRequest getInstrumentsRequest, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, getInstrumentsRequest.getVersionCode());
        b.a(parcel, 2, getInstrumentsRequest.aUA, false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return il(x0);
    }

    public GetInstrumentsRequest il(Parcel parcel) {
        int bT = com.google.android.gms.common.internal.safeparcel.a.bT(parcel);
        int i = 0;
        int[] iArr = null;
        while (parcel.dataPosition() < bT) {
            int bS = com.google.android.gms.common.internal.safeparcel.a.bS(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = com.google.android.gms.common.internal.safeparcel.a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    iArr = com.google.android.gms.common.internal.safeparcel.a.v(parcel, bS);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new GetInstrumentsRequest(i, iArr);
        }
        throw new com.google.android.gms.common.internal.safeparcel.a.a("Overread allowed size end=" + bT, parcel);
    }

    public GetInstrumentsRequest[] lg(int i) {
        return new GetInstrumentsRequest[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return lg(x0);
    }
}
