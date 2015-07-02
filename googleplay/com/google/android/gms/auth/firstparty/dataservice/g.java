package com.google.android.gms.auth.firstparty.dataservice;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.auth.firstparty.shared.AppDescription;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class g implements Creator<AccountRecoveryUpdateRequest> {
    static void a(AccountRecoveryUpdateRequest accountRecoveryUpdateRequest, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, accountRecoveryUpdateRequest.version);
        b.a(parcel, 2, accountRecoveryUpdateRequest.accountName, false);
        b.a(parcel, 3, accountRecoveryUpdateRequest.secondaryEmail, false);
        b.a(parcel, 4, accountRecoveryUpdateRequest.phoneNumber, false);
        b.a(parcel, 5, accountRecoveryUpdateRequest.phoneCountryCode, false);
        b.a(parcel, 6, accountRecoveryUpdateRequest.isBroadUse);
        b.a(parcel, 7, accountRecoveryUpdateRequest.callingAppDescription, i, false);
        b.J(parcel, bU);
    }

    public AccountRecoveryUpdateRequest[] aQ(int i) {
        return new AccountRecoveryUpdateRequest[i];
    }

    public AccountRecoveryUpdateRequest am(Parcel parcel) {
        boolean z = false;
        AppDescription appDescription = null;
        int bT = a.bT(parcel);
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        int i = 0;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    str4 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    str3 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    str2 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    str = a.p(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    z = a.c(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                    appDescription = (AppDescription) a.a(parcel, bS, AppDescription.CREATOR);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new AccountRecoveryUpdateRequest(i, str4, str3, str2, str, z, appDescription);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return am(x0);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return aQ(x0);
    }
}
