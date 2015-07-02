package com.google.android.gms.auth.firstparty.dataservice;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.auth.firstparty.shared.AppDescription;
import com.google.android.gms.auth.firstparty.shared.CaptchaSolution;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class a implements Creator<AccountNameCheckRequest> {
    static void a(AccountNameCheckRequest accountNameCheckRequest, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, accountNameCheckRequest.version);
        b.a(parcel, 2, accountNameCheckRequest.Gr, false);
        b.a(parcel, 3, accountNameCheckRequest.Gs, false);
        b.a(parcel, 4, accountNameCheckRequest.Gt, false);
        b.a(parcel, 5, accountNameCheckRequest.callingAppDescription, i, false);
        b.a(parcel, 6, accountNameCheckRequest.Gu, i, false);
        b.J(parcel, bU);
    }

    public AccountNameCheckRequest[] aK(int i) {
        return new AccountNameCheckRequest[i];
    }

    public AccountNameCheckRequest ag(Parcel parcel) {
        CaptchaSolution captchaSolution = null;
        int bT = com.google.android.gms.common.internal.safeparcel.a.bT(parcel);
        int i = 0;
        AppDescription appDescription = null;
        String str = null;
        String str2 = null;
        String str3 = null;
        while (parcel.dataPosition() < bT) {
            int bS = com.google.android.gms.common.internal.safeparcel.a.bS(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = com.google.android.gms.common.internal.safeparcel.a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    str3 = com.google.android.gms.common.internal.safeparcel.a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    str2 = com.google.android.gms.common.internal.safeparcel.a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    str = com.google.android.gms.common.internal.safeparcel.a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    appDescription = (AppDescription) com.google.android.gms.common.internal.safeparcel.a.a(parcel, bS, AppDescription.CREATOR);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    captchaSolution = (CaptchaSolution) com.google.android.gms.common.internal.safeparcel.a.a(parcel, bS, CaptchaSolution.CREATOR);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new AccountNameCheckRequest(i, str3, str2, str, appDescription, captchaSolution);
        }
        throw new com.google.android.gms.common.internal.safeparcel.a.a("Overread allowed size end=" + bT, parcel);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return ag(x0);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return aK(x0);
    }
}
