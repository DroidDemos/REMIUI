package com.google.android.gms.auth;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class e implements Creator<RecoveryWriteResponse> {
    static void a(RecoveryWriteResponse recoveryWriteResponse, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, recoveryWriteResponse.mVersionCode);
        b.a(parcel, 2, recoveryWriteResponse.mErrorCode, false);
        b.J(parcel, bU);
    }

    public RecoveryWriteResponse[] aI(int i) {
        return new RecoveryWriteResponse[i];
    }

    public RecoveryWriteResponse ae(Parcel parcel) {
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
            return new RecoveryWriteResponse(i, str);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return ae(x0);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return aI(x0);
    }
}
