package com.google.android.gms.wallet.shared;

import android.accounts.Account;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class a implements Creator<ApplicationParameters> {
    static void a(ApplicationParameters applicationParameters, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, applicationParameters.mVersionCode);
        b.c(parcel, 2, applicationParameters.aUz);
        b.a(parcel, 3, applicationParameters.aVv, i, false);
        b.a(parcel, 4, applicationParameters.mArgs, false);
        b.a(parcel, 5, applicationParameters.aVw);
        b.c(parcel, 6, applicationParameters.mTheme);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return iq(x0);
    }

    public ApplicationParameters iq(Parcel parcel) {
        Bundle bundle = null;
        int i = 0;
        int bT = com.google.android.gms.common.internal.safeparcel.a.bT(parcel);
        int i2 = 1;
        boolean z = false;
        Account account = null;
        int i3 = 0;
        while (parcel.dataPosition() < bT) {
            int bS = com.google.android.gms.common.internal.safeparcel.a.bS(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i3 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    i2 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    account = (Account) com.google.android.gms.common.internal.safeparcel.a.a(parcel, bS, Account.CREATOR);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    bundle = com.google.android.gms.common.internal.safeparcel.a.r(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    z = com.google.android.gms.common.internal.safeparcel.a.c(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    i = com.google.android.gms.common.internal.safeparcel.a.g(parcel, bS);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new ApplicationParameters(i3, i2, account, bundle, z, i);
        }
        throw new com.google.android.gms.common.internal.safeparcel.a.a("Overread allowed size end=" + bT, parcel);
    }

    public ApplicationParameters[] lp(int i) {
        return new ApplicationParameters[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return lp(x0);
    }
}
