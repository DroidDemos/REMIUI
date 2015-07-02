package com.google.android.gms.auth.firstparty.dataservice;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.auth.firstparty.shared.AppDescription;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class x implements Creator<OtpRequest> {
    static void a(OtpRequest otpRequest, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, otpRequest.Gf);
        b.a(parcel, 2, otpRequest.accountName, false);
        b.a(parcel, 3, otpRequest.callerDescription, i, false);
        b.a(parcel, 4, otpRequest.challenge, false);
        b.a(parcel, 5, otpRequest.isLegacyRequest);
        b.J(parcel, bU);
    }

    public OtpRequest aC(Parcel parcel) {
        boolean z = false;
        byte[] bArr = null;
        int bT = a.bT(parcel);
        AppDescription appDescription = null;
        String str = null;
        int i = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    str = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    appDescription = (AppDescription) a.a(parcel, bS, AppDescription.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    bArr = a.s(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    z = a.c(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new OtpRequest(i, str, appDescription, bArr, z);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public OtpRequest[] bg(int i) {
        return new OtpRequest[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return aC(x0);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return bg(x0);
    }
}
