package com.google.android.gms.auth.firstparty.dataservice;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class aa implements Creator<PasswordCheckResponse> {
    static void a(PasswordCheckResponse passwordCheckResponse, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, passwordCheckResponse.version);
        b.a(parcel, 2, passwordCheckResponse.status, false);
        b.a(parcel, 3, passwordCheckResponse.HD, false);
        b.a(parcel, 4, passwordCheckResponse.Gx, false);
        b.J(parcel, bU);
    }

    public PasswordCheckResponse aF(Parcel parcel) {
        String str = null;
        int bT = a.bT(parcel);
        int i = 0;
        String str2 = null;
        String str3 = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    str3 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    str2 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    str = a.p(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new PasswordCheckResponse(i, str3, str2, str);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public PasswordCheckResponse[] bj(int i) {
        return new PasswordCheckResponse[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return aF(x0);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return bj(x0);
    }
}
