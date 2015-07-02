package com.google.android.gms.auth.firstparty.dataservice;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.auth.firstparty.shared.AppDescription;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class d implements Creator<AccountRecoveryDataRequest> {
    static void a(AccountRecoveryDataRequest accountRecoveryDataRequest, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, accountRecoveryDataRequest.version);
        b.a(parcel, 2, accountRecoveryDataRequest.accountName, false);
        b.a(parcel, 3, accountRecoveryDataRequest.isClearUpdateSuggested);
        b.a(parcel, 4, accountRecoveryDataRequest.callingAppDescription, i, false);
        b.a(parcel, 5, accountRecoveryDataRequest.requestDescription, false);
        b.J(parcel, bU);
    }

    public AccountRecoveryDataRequest[] aN(int i) {
        return new AccountRecoveryDataRequest[i];
    }

    public AccountRecoveryDataRequest aj(Parcel parcel) {
        boolean z = false;
        String str = null;
        int bT = a.bT(parcel);
        AppDescription appDescription = null;
        String str2 = null;
        int i = 0;
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
                    z = a.c(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    appDescription = (AppDescription) a.a(parcel, bS, AppDescription.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    str = a.p(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new AccountRecoveryDataRequest(i, str2, z, appDescription, str);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return aj(x0);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return aN(x0);
    }
}
