package com.google.android.gms.auth.firstparty.dataservice;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.auth.firstparty.shared.AccountCredentials;
import com.google.android.gms.auth.firstparty.shared.AppDescription;
import com.google.android.gms.auth.firstparty.shared.CaptchaSolution;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class k implements Creator<AccountSignInRequest> {
    static void a(AccountSignInRequest accountSignInRequest, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, accountSignInRequest.version);
        b.a(parcel, 2, accountSignInRequest.callingAppDescription, i, false);
        b.a(parcel, 3, accountSignInRequest.GM);
        b.a(parcel, 4, accountSignInRequest.GN);
        b.a(parcel, 5, accountSignInRequest.Gu, i, false);
        b.a(parcel, 6, accountSignInRequest.GO, i, false);
        b.J(parcel, bU);
    }

    public AccountSignInRequest[] aU(int i) {
        return new AccountSignInRequest[i];
    }

    public AccountSignInRequest aq(Parcel parcel) {
        AccountCredentials accountCredentials = null;
        boolean z = false;
        int bT = a.bT(parcel);
        CaptchaSolution captchaSolution = null;
        boolean z2 = false;
        AppDescription appDescription = null;
        int i = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    appDescription = (AppDescription) a.a(parcel, bS, AppDescription.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    z2 = a.c(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    z = a.c(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    captchaSolution = (CaptchaSolution) a.a(parcel, bS, CaptchaSolution.CREATOR);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    accountCredentials = (AccountCredentials) a.a(parcel, bS, AccountCredentials.CREATOR);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new AccountSignInRequest(i, appDescription, z2, z, captchaSolution, accountCredentials);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return aq(x0);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return aU(x0);
    }
}
