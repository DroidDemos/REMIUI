package com.google.android.gms.auth;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;
import java.util.List;

public class c implements Creator<RecoveryReadResponse> {
    static void a(RecoveryReadResponse recoveryReadResponse, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, recoveryReadResponse.mVersionCode);
        b.a(parcel, 2, recoveryReadResponse.mSecondaryEmail, false);
        b.a(parcel, 3, recoveryReadResponse.mPhoneNumber, false);
        b.a(parcel, 4, recoveryReadResponse.mPhoneCountryCode, false);
        b.d(parcel, 5, recoveryReadResponse.mCountryList, false);
        b.a(parcel, 6, recoveryReadResponse.mError, false);
        b.a(parcel, 7, recoveryReadResponse.mAction, false);
        b.a(parcel, 8, recoveryReadResponse.mAllowedOptions, false);
        b.J(parcel, bU);
    }

    public RecoveryReadResponse[] aG(int i) {
        return new RecoveryReadResponse[i];
    }

    public RecoveryReadResponse ac(Parcel parcel) {
        String str = null;
        int bT = a.bT(parcel);
        int i = 0;
        String str2 = null;
        String str3 = null;
        List list = null;
        String str4 = null;
        String str5 = null;
        String str6 = null;
        while (parcel.dataPosition() < bT) {
            int bS = a.bS(parcel);
            switch (a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    str6 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    str5 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    str4 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    list = a.c(parcel, bS, Country.CREATOR);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    str3 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                    str2 = a.p(parcel, bS);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                    str = a.p(parcel, bS);
                    break;
                default:
                    a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new RecoveryReadResponse(i, str6, str5, str4, list, str3, str2, str);
        }
        throw new a.a("Overread allowed size end=" + bT, parcel);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return ac(x0);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return aG(x0);
    }
}
