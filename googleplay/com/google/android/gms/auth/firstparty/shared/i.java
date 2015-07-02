package com.google.android.gms.auth.firstparty.shared;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class i implements Creator<PlusProfileCreationResponse> {
    static void a(PlusProfileCreationResponse plusProfileCreationResponse, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, plusProfileCreationResponse.version);
        b.a(parcel, 2, plusProfileCreationResponse.IK, false);
        b.J(parcel, bU);
    }

    public PlusProfileCreationResponse[] bL(int i) {
        return new PlusProfileCreationResponse[i];
    }

    public PlusProfileCreationResponse bh(Parcel parcel) {
        int bT = a.bT(parcel);
        int i = 0;
        String str = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    str = a.p(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new PlusProfileCreationResponse(i, str);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return bh(x0);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return bL(x0);
    }
}
