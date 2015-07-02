package com.google.android.gms.auth;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;

public class d implements Creator<RecoveryWriteRequest> {
    static void a(RecoveryWriteRequest recoveryWriteRequest, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, recoveryWriteRequest.mVersionCode);
        b.a(parcel, 2, recoveryWriteRequest.mAccount, false);
        b.a(parcel, 3, recoveryWriteRequest.mSecondaryEmail, false);
        b.a(parcel, 4, recoveryWriteRequest.mPhoneNumber, false);
        b.a(parcel, 5, recoveryWriteRequest.mPhoneCountryCode, false);
        b.a(parcel, 6, recoveryWriteRequest.mIsBroadUse);
        b.J(parcel, bU);
    }

    public RecoveryWriteRequest[] aH(int i) {
        return new RecoveryWriteRequest[i];
    }

    public RecoveryWriteRequest ad(Parcel parcel) {
        boolean z = false;
        String str = null;
        int bT = a.bT(parcel);
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
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new RecoveryWriteRequest(i, str4, str3, str2, str, z);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return ad(x0);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return aH(x0);
    }
}
