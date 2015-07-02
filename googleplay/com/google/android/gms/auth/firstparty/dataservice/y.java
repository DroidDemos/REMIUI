package com.google.android.gms.auth.firstparty.dataservice;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class y implements Creator<OtpResponse> {
    static void a(OtpResponse otpResponse, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, otpResponse.Gf);
        b.a(parcel, 2, otpResponse.otp, false);
        b.J(parcel, bU);
    }

    public OtpResponse aD(Parcel parcel) {
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
            return new OtpResponse(i, str);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public OtpResponse[] bh(int i) {
        return new OtpResponse[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return aD(x0);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return bh(x0);
    }
}
