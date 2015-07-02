package com.google.android.gms.auth.firstparty.dataservice;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.auth.firstparty.shared.CaptchaChallenge;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.wallet.instrumentmanager.R;
import java.util.List;

public class b implements Creator<AccountNameCheckResponse> {
    static void a(AccountNameCheckResponse accountNameCheckResponse, Parcel parcel, int i) {
        int bU = com.google.android.gms.common.internal.safeparcel.b.bU(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, accountNameCheckResponse.version);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, accountNameCheckResponse.Gv, false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 3, accountNameCheckResponse.Gw, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 4, accountNameCheckResponse.Gx, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 5, accountNameCheckResponse.Gy, i, false);
        com.google.android.gms.common.internal.safeparcel.b.J(parcel, bU);
    }

    public AccountNameCheckResponse[] aL(int i) {
        return new AccountNameCheckResponse[i];
    }

    public AccountNameCheckResponse ah(Parcel parcel) {
        CaptchaChallenge captchaChallenge = null;
        int bT = a.bT(parcel);
        int i = 0;
        String str = null;
        List list = null;
        String str2 = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    str2 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    list = a.E(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    str = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    captchaChallenge = (CaptchaChallenge) a.a(parcel, bS, CaptchaChallenge.CREATOR);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new AccountNameCheckResponse(i, str2, list, str, captchaChallenge);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return ah(x0);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return aL(x0);
    }
}
