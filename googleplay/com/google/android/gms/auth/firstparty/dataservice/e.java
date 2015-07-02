package com.google.android.gms.auth.firstparty.dataservice;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class e implements Creator<AccountRecoveryGuidance> {
    static void a(AccountRecoveryGuidance accountRecoveryGuidance, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, accountRecoveryGuidance.version);
        b.a(parcel, 2, accountRecoveryGuidance.accountName, false);
        b.a(parcel, 3, accountRecoveryGuidance.isRecoveryInfoNeeded);
        b.a(parcel, 4, accountRecoveryGuidance.isRecoveryInterstitialSuggested);
        b.a(parcel, 5, accountRecoveryGuidance.isRecoveryUpdateAllowed);
        b.J(parcel, bU);
    }

    public AccountRecoveryGuidance[] aO(int i) {
        return new AccountRecoveryGuidance[i];
    }

    public AccountRecoveryGuidance ak(Parcel parcel) {
        boolean z = false;
        int bT = a.bT(parcel);
        String str = null;
        boolean z2 = false;
        boolean z3 = false;
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
                    z3 = a.c(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    z2 = a.c(parcel, bS);
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
            return new AccountRecoveryGuidance(i, str, z3, z2, z);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return ak(x0);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return aO(x0);
    }
}
